/**
 * 
 */
package com.leonet.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.leonet.common.entity.RequestQuoteEntity;
import com.leonet.common.entity.RequestQuoteItemEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.entity.SpecialSalesEntity;
import com.leonet.common.entity.SpecialSalesItemEntity;

/**
 * @author YOGESH
 *
 */
@Component
public class CustomFileUploadUtil {

	@Value("${imagesPath}")
	private String filesPath;

	@Value("${leo.pos.admin.pdfPath}")
	private String pdfPath;

	@Value("${leo.pos.admin.pdfFilePrefix}")
	private String pdfFilePrefix;

	@Value("${leo.pos.admin.sale.filePath}")
	private String saleFilePath;

	@Value("${leo.pos.admin.sale.pdfFilePrefix}")
	private String salePdfFilePrefix;

	@Value("${leo.pos.admin.sale.bulkZipFilePrefix}")
	private String bulkZipFilePrefix;

	@Autowired
	private GeneratePdfUtil generatePdfUtil;

//	private Logger logger = Logger.getLogger(CustomFileUploadUtil.class);

	private final static Map<String, String> CREATE_TRUE = Collections.singletonMap("create", "true");

	public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}

	public void uploadFile(MultipartFile file) {
		String uploadDir = filesPath;
		try {
			Path copyLocation = Paths
					.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
			System.out.println("copyLocation  " + copyLocation);
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String savePdfFileIntoDir(String fileName, List<RequestQuoteItemEntity> requestquotetrans,
			RequestQuoteEntity rquote) throws IOException {
		Path uploadPath = Paths.get(pdfPath);
		fileName = (fileName != null && !fileName.isEmpty()) ? fileName
				: pdfFilePrefix + System.currentTimeMillis() + ".pdf";
		Path uploadFileName = Paths.get(pdfPath + File.separator + fileName);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		if (!Files.exists(uploadFileName)) {
			File file = new File(uploadPath + File.separator + fileName);
			generatePdfUtil.generatePdf(requestquotetrans, file, rquote);

		}

		return fileName;
	}

	public ByteArrayInputStream getFile(String fileName) throws IOException {
		Path uploadFileName = Paths.get(pdfPath + File.separator + fileName);
		byte[] b = Files.readAllBytes(uploadFileName);
		ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(b);
		return arrayInputStream;
	}

	public String savePdfFileIntoDir(List<SalesItemEntity> saleItemList, SalesEntity sale) throws IOException {
		Path uploadPath = Paths.get(saleFilePath);
		String fileName = salePdfFilePrefix + System.currentTimeMillis() + ".pdf";
		Path uploadFileName = Paths.get(saleFilePath + File.separator + fileName);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		if (!Files.exists(uploadFileName)) {
			File file = new File(uploadPath + File.separator + fileName);
			generatePdfUtil.generateSalePdf(saleItemList, file, sale);

		}

		return fileName;
	}

	public String saveSpecialPdfFileIntoDir(List<SpecialSalesItemEntity> saleItemList, SpecialSalesEntity sale)
			throws IOException {
		Path uploadPath = Paths.get(saleFilePath);
		String fileName = salePdfFilePrefix + System.currentTimeMillis() + ".pdf";
		Path uploadFileName = Paths.get(saleFilePath + File.separator + fileName);

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		if (!Files.exists(uploadFileName)) {
			File file = new File(uploadPath + File.separator + fileName);
			generatePdfUtil.generateSpecialSalePdf(saleItemList, file, sale);

		}

		return fileName;
	}

	public void deletFileFromDir(String filePath, String fileName) {
		try {
			Path file = Paths.get(filePath, fileName);
			boolean deleted = Files.deleteIfExists(file);
			if (deleted) {
				LeoLogger.debug("Deleted file: {}", file);
			} else {
				LeoLogger.warn("File not found for deletion: {}", file);
			}
		} catch (IOException e) {
			LeoLogger.error("Error while deleting file {}/{}", filePath, fileName, e);
		}
	}

	public String copyFileToZip(List<SalesEntity> saleEmailList, boolean overwriteZipWithSameName) {

	    String memberName = saleEmailList.get(0).getMembername();
	    String sanitizedMemberName = memberName.replaceAll("[^a-zA-Z0-9-_]", "_"); 

	    String fileName = bulkZipFilePrefix + "_" + sanitizedMemberName + "_" + System.currentTimeMillis() + ".zip";
	    
	//	String fileName = bulkZipFilePrefix + System.currentTimeMillis() + ".zip";
		Path destination = Paths.get(saleFilePath + File.separator + fileName);
		URI uri = URI.create("jar:" + destination.toUri());

		final Map<String, String> env;
		if (overwriteZipWithSameName || !Files.exists(destination)) {
			env = CREATE_TRUE;
		} else {
			env = Collections.emptyMap();
		}

		try (FileSystem fs = FileSystems.newFileSystem(uri, env)) {
			for (SalesEntity sale : saleEmailList) {
				Path source = Paths.get(saleFilePath + File.separator + sale.getFileName());
				
				String safeRef = sale.getReferenceno().replaceAll("[/\\\\]", "_"); 
				String fileInsideZip = safeRef + "_" + sale.getFileName();
				Path dest = fs.getPath(fileInsideZip);
		//	Path dest = fs.getPath(sale.getFileName());
				Files.copy(source, dest);
			}
		} catch (IOException e) {
			// logger.error("error copy File to Zip file", e);
		}
		return fileName;
	}
}
