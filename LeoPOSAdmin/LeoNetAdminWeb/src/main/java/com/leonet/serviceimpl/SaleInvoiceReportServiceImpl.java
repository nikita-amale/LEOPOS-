package com.leonet.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.filter.SalesFilterGroup;
import com.leonet.common.pojo.SaleInvoiceReportPojo;
import com.leonet.mapper.SalesReportMapper;
import com.leonet.repo.SalesItemRepo;
import com.leonet.repo.SalesRepo;
import com.leonet.service.SaleInvoiceReportService;
import com.leonet.spec.SalesSpecification;
import com.leonet.spec.helper.SalesFilterHelper;
import com.leonet.util.LeoLogger;

@Service
public class SaleInvoiceReportServiceImpl implements SaleInvoiceReportService {

	private static final int BATCH_SIZE = 1000;

	@Autowired
	SalesRepo salesRepo;

	@Autowired
	SalesItemRepo salesItemRepo;

	@Autowired
	private SalesReportMapper mapper;

	@Override
	public List<SaleInvoiceReportPojo> getSalesInvoiceReportBetween(Long startId, Long endId, LocalDate filterDate) {

		LeoLogger.info("Fetching sales records | startId={}, endId={}, filterDate={}", startId, endId, filterDate);

		if ((startId == null || endId == null) && filterDate == null) {
			throw new IllegalArgumentException("Please provide either (start & end) or (filterDate).");
		}
		
		// Build filter groups
        List<SalesFilterGroup> groups = new ArrayList<>();

        groups = SalesFilterHelper.buildGroups(startId, endId, filterDate);
		List<SalesEntity> sales = salesRepo.findAll(SalesSpecification.build(groups));
		
		LeoLogger.info("Total sales records fetched: {}", sales.size());

		if (sales.isEmpty()) {
			LeoLogger.warn("No sales found between IDs {} and {}", startId, endId);
			return Collections.emptyList();
		}

		List<Long> saleIds = sales.stream().map(SalesEntity::getSaleId).collect(Collectors.toList());
		LeoLogger.info("Sale IDs extracted for item lookup: {}", saleIds);

		List<SalesItemEntity> allItems = fetchSalesItemsInBatches(saleIds);
		LeoLogger.info("Total sales items fetched: {}", allItems.size());

		Map<Long, List<SalesItemEntity>> itemsGroupedBySaleId = allItems.stream()
				.collect(Collectors.groupingBy(SalesItemEntity::getSaleid));
		LeoLogger.info("Sales items grouped by sale ID. Group count: {}", itemsGroupedBySaleId.size());

		List<SaleInvoiceReportPojo> reports = sales.stream()
				.map(sale -> mapper.mapToSaleDto(sale, itemsGroupedBySaleId.get(sale.getSaleId())))
				.collect(Collectors.toList());

		LeoLogger.info("Report DTOs prepared. Final report size: {}", reports.size());

		return reports;
	}

	private List<SalesItemEntity> fetchSalesItemsInBatches(List<Long> saleIds) {
		List<SalesItemEntity> result = new ArrayList<>();
		for (int i = 0; i < saleIds.size(); i += BATCH_SIZE) {
			int end = Math.min(i + BATCH_SIZE, saleIds.size());
			List<Long> batch = saleIds.subList(i, end);
			result.addAll(salesItemRepo.findAllBySaleIdIn(batch));
		}
		return result;
	}
	
}
