package com.leonet.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.leonet.common.entity.FTEntity;
import com.leonet.common.entity.RequestQuoteEntity;
import com.leonet.common.entity.RequestQuoteItemEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.entity.SpecialSalesEntity;
import com.leonet.common.entity.SpecialSalesItemEntity;

@Component
public class GeneratePdfUtil {
	@Value("${imagelogo}")
	private static String imagelogo;

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfUtil.class);
	
	

	@Value("${leo.pos.admin.pdfFilePrefix}")
	private String pdfFilePrefix;
	
	@Value("${leo.pos.admin.sale.pdfFilePrefix}")
	private String salePdfFilePrefix;
	
	public static ByteArrayInputStream statementReport(List<RequestQuoteItemEntity> requestquotetrans) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 2, 4, 2, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Product Code", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Product Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Qunatity", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("MPN", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (RequestQuoteItemEntity ft : requestquotetrans) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(ft.getProduct_code()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getProduct_name()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(ft.getQuantity())));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getMpn()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();

			String wtext = "Belize City, Belize\r\n" + "Phone: 207-0669\r\n"
					+ "TIN # 35467.";
			Image image = Image.getInstance("classpath:logo_s.png");
		    image.scaleAbsolute(230, 70);
		  
		    document.add(image);
			Paragraph para = new Paragraph(wtext);
			para.setSpacingAfter(48f);
			document.add(para);

			document.add(table);

			document.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public void generatePdf(List<RequestQuoteItemEntity> requestquotetrans, File file,RequestQuoteEntity rquote)
			throws FileNotFoundException {
	

		Document document = new Document();
		LeoLogger.info("Creating PDF for request quote");

		try {

			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 1, 1, 4, 2, 2 });

			int n =1;
			
			
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			
			hcell = new PdfPCell(new Phrase("Serial No.", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Product Code", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Product Name", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Quantity", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("MPN", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (RequestQuoteItemEntity ft : requestquotetrans) {
				
				LeoLogger.info("Value on variable n ..." + n);

				PdfPCell cell;
				
				cell = new PdfPCell(new Phrase(String.valueOf(n)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getProduct_code()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getProduct_name()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(ft.getQuantity())));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getMpn()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingRight(5);
				table.addCell(cell);
				
				n= n+1;
			}
			// Customer Table

			PdfPTable customerTable = new PdfPTable(1);
			customerTable.setWidthPercentage(20);
			customerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			// customerTable.setWidths(new int[]{3});

			PdfPCell Chcell;
			Chcell = new PdfPCell(new Phrase("Supplier Name : ", headFont));
			Chcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Chcell);
			
			PdfPCell Ccell;
			Ccell = new PdfPCell(new Phrase(String.valueOf(rquote.getSuppliername())));
			Ccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Ccell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Ccell);

		

			PdfWriter.getInstance(document, new FileOutputStream(file));
			document.open();

			String wtext = "3754 Central American Blvd.\r\n"+ "Belize City, Belize\r\n" + "Tel: 501 207-0669\r\n"
					+ "TIN # 128693.\r\n";
			Image image = Image.getInstance("classpath:logo_s.png");
		    image.scaleAbsolute(230, 70);
			
			
		   document.add(image);
			Paragraph para = new Paragraph(wtext);
			para.setSpacingAfter(48f);
			document.add(para);
			
			document.add(customerTable);
			Paragraph para3 = new Paragraph(" ");
			para3.setSpacingAfter(32f);
			document.add(para3);
		

			document.add(table);

			document.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

	}
	
	public void generateSalePdf(List<SalesItemEntity> saleItemList, File file, SalesEntity sale)
			throws FileNotFoundException {

		Document doc = new Document();

		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();

			Image image = Image.getInstance("classpath:logo_s.png");
			image.scaleAbsolute(230, 70);

			doc.add(image);

			PdfPTable table;
			Paragraph paragraph;
			PdfPCell cell;
			PdfPCell hcell;

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("3754 Central American Blvd.", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Tax Invoice", PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("Belize City Belize", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Date :" + sale.getDate(), PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("Tel: 501 207-0669", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Sale Reference No." + sale.getReferenceno(), PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("TIN # 128693", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Sales Person: SalesUser Sales", PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			paragraph = new Paragraph("Bill To:");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);

			paragraph = new Paragraph("Sales Receipt");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);

			table = new PdfPTable(5);
			table.setSpacingBefore(10);
			table.setSpacingAfter(10);
			table.setWidthPercentage(100);

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			hcell = new PdfPCell(new Phrase("No.", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Description", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Quantity", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Unit Price", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Subtotal", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			int n = 1;

			for (SalesItemEntity item : saleItemList) {

				cell = new PdfPCell(new Phrase(String.valueOf(n)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(item.getProduct_name())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(item.getQuantity())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(item.getReal_unit_price())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(item.getSubtotal())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				n++;
			}

			cell = new PdfPCell(new Phrase("Total Amount (BZD)"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(sale.getTotal())));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Total Tax"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(sale.getTotal_tax())));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Grand Total (BZD)"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(sale.getGrand_total())));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			doc.add(table);

			paragraph = new Paragraph("Note:");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);
			doc.add(new Phrase("\n"));

			paragraph = new Paragraph("Created by: ");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);

			paragraph = new Paragraph("Date: " + getFormattedDate());
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);

			doc.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

	}
	
	private PdfPCell getCell(String text, int alignment) {
		PdfPCell cell = new PdfPCell(new Phrase(text));
		cell.setPadding(0);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}
	
	private String getFormattedDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return dateFormat.format(new Date());
	}

	public void generateSpecialSalePdf(List<SpecialSalesItemEntity> saleItemList, File file, SpecialSalesEntity sale) {
		Document doc = new Document();

		try {
			PdfWriter.getInstance(doc, new FileOutputStream(file));
			doc.open();
			PdfPTable table;
			Paragraph paragraph;
			PdfPCell cell;
			PdfPCell hcell;

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("3754 Central American Blvd.", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Tax Invoice", PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("Belize City Belize", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Date :" + sale.getDate(), PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("Tel: 501 207-0669", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Sale Reference No." + sale.getReferenceno(), PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			table = new PdfPTable(2);
			table.setWidthPercentage(100);
			table.addCell(getCell("TIN # 128693", PdfPCell.ALIGN_LEFT));
			table.addCell(getCell("Sales Person: SalesUser Sales", PdfPCell.ALIGN_RIGHT));
			table.setSpacingAfter(10);
			doc.add(table);

			paragraph = new Paragraph("Bill To:");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);

			paragraph = new Paragraph("Sales Receipt");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);
			
			table = new PdfPTable(5);
			table.setSpacingBefore(10);
			table.setSpacingAfter(10);
			table.setWidthPercentage(100);
			
			
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			hcell = new PdfPCell(new Phrase("No.", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Description", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Quantity", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Unit Price", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			hcell = new PdfPCell(new Phrase("Subtotal", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);
			
			
			for (SpecialSalesItemEntity item : saleItemList) {
				
				cell = new PdfPCell(new Phrase(String.valueOf(item.getId())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(item.getProduct_name())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(item.getQuantity())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(item.getReal_unit_price())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Phrase(String.valueOf(item.getSubtotal())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
			}
			
			cell = new PdfPCell(new Phrase("Total Amount (BZD)"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(sale.getTotal())));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Total Tax"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(sale.getTotal_tax())));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Grand Total (BZD)"));
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setColspan(4);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase(String.valueOf(sale.getGrand_total())));
			cell.setPaddingLeft(5);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			doc.add(table);
			
			paragraph = new Paragraph("Note:");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);
			doc.add(new Phrase("\n"));

			paragraph = new Paragraph("Created by: ");
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);
			
			paragraph = new Paragraph("Date: " + getFormattedDate());
			paragraph.setAlignment(Element.ALIGN_LEFT);
			doc.add(paragraph);
			
			doc.close();

		} catch (DocumentException ex) {

			logger.error("Error occurred: {0}", ex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
