package com.leonet.serviceimpl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.pojo.ProgressStatus;
import com.leonet.repo.SalesItemRepo;
import com.leonet.repo.SalesRepo;
import com.leonet.service.SalesPdfRegenerationService;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.LeoLogger;

@Service
@EnableAsync
public class SalesPdfRegenerationServiceImpl implements SalesPdfRegenerationService {

	private final SalesRepo salesRepository;
	private final SalesItemRepo salesItemRepository;
	private final CustomFileUploadUtil fileUploadUtil;

	@Value("${leo.pos.admin.sale.filePath}")
	private String saleFilePath;

	@Value("${batch.size:1000}")
	private int batchSize;

	private final Map<String, ProgressStatus> progressMap = new ConcurrentHashMap<>();

	public SalesPdfRegenerationServiceImpl(SalesRepo salesRepository, SalesItemRepo salesItemRepository,
			CustomFileUploadUtil fileUploadUtil) {
		this.salesRepository = salesRepository;
		this.salesItemRepository = salesItemRepository;
		this.fileUploadUtil = fileUploadUtil;
	}

	@Async
	@Override
	public void regeneratePdfsAsync(LocalDate start, LocalDate end, String jobId) {
		LeoLogger.info("Starting async PDF regeneration [{}] from {} to {}", jobId, start, end);

		Date startDate = java.sql.Timestamp.valueOf(start.atStartOfDay());
		Date endDate = java.sql.Timestamp.valueOf(end.plusDays(1).atStartOfDay().minusNanos(1));

		long jobStart = System.currentTimeMillis();
		long totalProcessed = 0L;

		long totalElements = salesRepository.countByDateBetween(startDate, endDate);
		progressMap.put(jobId, new ProgressStatus(totalElements, 0, 0, "0s", "calculating", false, "0s"));

		int page = 0;
		Page<SalesEntity> salesPage;

		do {
			long batchStart = System.currentTimeMillis();
			salesPage = salesRepository.findByDateBetween(startDate, endDate, PageRequest.of(page, batchSize));

			if (!salesPage.hasContent())
				break;

			List<Long> saleIds = salesPage.getContent().stream().map(SalesEntity::getSaleId)
					.collect(Collectors.toList());

			Map<Long, List<SalesItemEntity>> saleItemsMap = salesItemRepository.findBySaleidIn(saleIds).stream()
					.collect(Collectors.groupingBy(SalesItemEntity::getSaleid));

			salesPage.getContent().forEach(sale -> {
				try {
					List<SalesItemEntity> saleItems = saleItemsMap.getOrDefault(sale.getSaleId(),
							Collections.emptyList());

					String newFileName = fileUploadUtil.savePdfFileIntoDir(saleItems, sale);

					if (sale.getFileName() != null) {
						try {
							fileUploadUtil.deletFileFromDir(saleFilePath, sale.getFileName());
						} catch (Exception ignored) {
						}
					}

					sale.setFileName(newFileName);
				} catch (Exception e) {
					LeoLogger.error("Failed regenerating PDF for saleId={}", sale.getSaleId(), e);
				}
			});

			salesRepository.saveAll(salesPage.getContent());

			totalProcessed += salesPage.getNumberOfElements();

			long elapsed = System.currentTimeMillis() - jobStart;
			double percentDone = (totalProcessed * 100.0) / totalElements;
			long estimatedTotalTime = (long) ((elapsed / (double) totalProcessed) * totalElements);
			long eta = estimatedTotalTime - elapsed;

			progressMap.put(jobId, new ProgressStatus(totalElements, totalProcessed, percentDone, formatTime(elapsed), // elapsed
					formatTime(eta), // ETA remaining
					false, formatTime(estimatedTotalTime) // total expected time
			));

			LeoLogger.info("Batch {} done. Processed {} / {} ({:.2f}%). Time: {}, ETA: {}", (page + 1), totalProcessed,
					totalElements, percentDone, formatTime(System.currentTimeMillis() - batchStart), formatTime(eta));

			page++;
		} while (salesPage.hasNext());

		long totalTime = System.currentTimeMillis() - jobStart;

		progressMap.put(jobId, new ProgressStatus(totalElements, totalProcessed, 100, formatTime(totalTime), "0s", true,
				formatTime(totalTime)));

		LeoLogger.info("PDF regeneration [{}] completed. Total processed={} | Total time={}", jobId, totalProcessed,
				formatTime(totalTime));
	}

	@Override
	public ProgressStatus getProgress(String jobId) {
		return progressMap.getOrDefault(jobId, new ProgressStatus());
	}

	private String formatTime(long millis) {
		if (millis < 1000) {
			return millis + " ms";
		}

		long seconds = millis / 1000;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;

		seconds = seconds % 60;
		minutes = minutes % 60;
		hours = hours % 24;

		StringBuilder sb = new StringBuilder();

		if (days > 0) {
			sb.append(days).append("d ");
		}
		if (hours > 0 || days > 0) {
			sb.append(hours).append("h ");
		}
		if (minutes > 0 || hours > 0 || days > 0) {
			sb.append(minutes).append("m ");
		}
		sb.append(seconds).append("s");

		return sb.toString().trim();
	}

}
