// Source URL https://zetcode.com/springboot/servepdf/
// https://www.tutorialspoint.com/itext/itext_adding_areabreak.htm
package com.leonet.util;

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
import com.leonet.common.entity.FinancialTransactionEntity;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SpecialSalesEntity;
import com.leonet.common.pojo.ProfitLossReportPojo;
import com.leonet.entity.MemberUser;
import com.leonet.service.SaleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GeneratePdfReport {
	
	@Value("${imagelogo}")
	private static String imagelogo;
	

	@Autowired(required = false)
	SaleService saleService;

	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

	public static ByteArrayInputStream statementReport(List<FinancialTransactionEntity> financialtrans,
			List<SalesEntity> salesEntity) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			double currentAmount = 0.0, thirtydaybal = 0.0, sixtydaybal = 0.0, ninetydaybal = 0.0, balance = 0.0;

			Calendar cal = Calendar.getInstance();

			Date today = cal.getTime();
			LeoLogger.info("Today : " + cal.getTime());

			// Substract 30 days from the calendar
			cal.add(Calendar.DATE, -30);
			Date thirtyday = cal.getTime();
			LeoLogger.info("30 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -60);
			Date sixtyday = cal.getTime();
			LeoLogger.info("60 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -90);
			Date ninetyday = cal.getTime();
			LeoLogger.info("90 days ago: " + cal.getTime());

			for (SalesEntity salent : salesEntity) {
				if (ninetyday.after(salent.getDue_date())) {
					ninetydaybal = ninetydaybal + (salent.getGrand_total() - salent.getPaid());
				}
				if (ninetyday.after(salent.getDue_date()) && sixtyday.before(salent.getDate())) {
					sixtydaybal = sixtydaybal + (salent.getGrand_total() - salent.getPaid());
				}
				if (sixtyday.after(salent.getDue_date()) && thirtyday.before(salent.getDate())) {
					thirtydaybal = thirtydaybal + (salent.getGrand_total() - salent.getPaid());
				} else {

					currentAmount = currentAmount + (salent.getGrand_total() - salent.getPaid());

				}
				for (FinancialTransactionEntity ft : financialtrans) {
					balance = ft.getBalance();

				}

			}

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 3, 3, 1, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("Date", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Transaction", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Balance", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				PdfPCell cell;
				// int i=0;

				cell = new PdfPCell(new Phrase(ft.getDate().toGMTString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getType() + " Invoice No. : " + ft.getInvoideId()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(ft.getAmount())));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(ft.getBalance())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
				// i=i+1;
				// if(i<0);
				// break;
			}

			// Customer Table

			PdfPTable customerTable = new PdfPTable(1);
			customerTable.setWidthPercentage(20);
			customerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			// customerTable.setWidths(new int[]{3});

			PdfPCell Chcell;
			Chcell = new PdfPCell(new Phrase("To : ", headFont));
			Chcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Chcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;

				PdfPCell Ccell;
				Ccell = new PdfPCell(new Phrase(String.valueOf(ft.getCustomerName())));
				Ccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Ccell.setHorizontalAlignment(Element.ALIGN_CENTER);
				customerTable.addCell(Ccell);
				i = i + 1;
				if (i < 0)
					;
				break;
			}

			// Statement table

			PdfPTable sttable = new PdfPTable(5);
			sttable.setWidthPercentage(100);
			// sttable.setWidths(new int[]{3, 3, 1,2});

			Font stheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell sthcell;
			sthcell = new PdfPCell(new Phrase("Current", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("1-30 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("31-60 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("61-90 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("Amount Due", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			PdfPCell stcell;

			stcell = new PdfPCell(new Phrase(String.valueOf(currentAmount)));
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);
			// LeoLogger.info("amount: " + currentAmount);

			stcell = new PdfPCell(new Phrase(String.valueOf(thirtydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(sixtydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(ninetydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;

				stcell = new PdfPCell(new Phrase(String.valueOf(balance)));
				stcell.setPaddingLeft(5);
				stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				sttable.addCell(stcell);
				i = i + 1;
				if (i < 0)
					;
				break;

			}

			// Statment
			PdfPTable dtable = new PdfPTable(1);
			dtable.setWidthPercentage(40);
			dtable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font dheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell dhcell;
			dhcell = new PdfPCell(new Phrase("Date", dheadFont));
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);

			// for (FinancialTransactionEntity ft : financialtrans) {

			dhcell = new PdfPCell(new Phrase(String.valueOf(new Date())));
			dhcell.setPaddingLeft(5);
			dhcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);
			// }

			// toptable
			PdfPTable ttable = new PdfPTable(2);
			ttable.setWidthPercentage(40);
			ttable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font theadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell thcell;

			thcell = new PdfPCell(new Phrase("Amount Due", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			thcell = new PdfPCell(new Phrase("Amount Enc.", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			PdfPCell sttcell;
			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;
				sttcell = new PdfPCell(new Phrase(String.valueOf(balance)));
				sttcell.setPaddingLeft(2);
				sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				ttable.addCell(sttcell);

				sttcell = new PdfPCell();
				sttcell.setPaddingLeft(2);
				sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// sttcell.setPaddingRight(2);
				ttable.addCell(sttcell);
				i = i + 1;
				if (i < 0)
					;
				break;
			}

			PdfWriter.getInstance(document, out);
			document.open();

			String wtext = "Statement\r\n\n" + "3754 Central American Blvd.\r\n" + "Belize City, Belize\r\n"
					+ "Phone: 207-0669\r\n" + "TIN # 35467." + "\n\n\n";

			Image image = Image.getInstance("classpath:logo_s.png");
			image.scaleAbsolute(230, 70);
			// image.setAbsolutePosition(100f, 500f);

			document.add(image);
			Paragraph para = new Paragraph(wtext);
			para.setSpacingAfter(24f);
			// document.add(image);
			document.add(para);

			document.add(dtable);

			document.add(customerTable);

			Paragraph para3 = new Paragraph(" ");
			para3.setSpacingAfter(32f);
			document.add(para3);

			document.add(ttable);

			document.add(table);

			document.add(sttable);

			document.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	// New

	public static ByteArrayInputStream Report(List<FinancialTransactionEntity> financialtrans,
			List<SalesEntity> salesEntity) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			double currentAmount = 0.0, thirtydaybal = 0.0, sixtydaybal = 0.0, ninetydaybal = 0.0, balance = 0.0;

			Calendar cal = Calendar.getInstance();

			Date today = cal.getTime();
			LeoLogger.info("Today : " + cal.getTime());

			// Substract 30 days from the calendar
			cal.add(Calendar.DATE, -30);
			Date thirtyday = cal.getTime();
			LeoLogger.info("30 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date sixtyday = cal.getTime();
			LeoLogger.info("60 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date ninetyday = cal.getTime();
			LeoLogger.info("90 days ago: " + cal.getTime());

			for (SalesEntity salent : salesEntity) {
				if (ninetyday.after(salent.getDate())) {
					ninetydaybal = ninetydaybal + (salent.getGrand_total() - salent.getPaid());
				}
				if (ninetyday.after(salent.getDate()) && sixtyday.before(salent.getDate())) {
					sixtydaybal = sixtydaybal + (salent.getGrand_total() - salent.getPaid());
				}
				if (sixtyday.after(salent.getDate()) && thirtyday.before(salent.getDate())) {
					thirtydaybal = thirtydaybal + (salent.getGrand_total() - salent.getPaid());
				} else {
					double amount = 0.0;
					amount = currentAmount + (salent.getGrand_total() - salent.getPaid());
					BigDecimal bd_amount = new BigDecimal(0.0);
					bd_amount = new BigDecimal(amount);
					bd_amount = bd_amount.setScale(2, RoundingMode.HALF_UP);

					currentAmount = bd_amount.doubleValue();
					LeoLogger.info("amount: " + currentAmount);

				}
				for (FinancialTransactionEntity ft : financialtrans) {
					balance = ft.getBalance();
					double bal = 0.0;
					bal = ft.getBalance();
					BigDecimal bd_bal = new BigDecimal(0.0);
					bd_bal = new BigDecimal(bal);
					bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);
					balance = bd_bal.doubleValue();

					// LeoLogger.info("balance: " + balance);

				}

			}

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 3, 3, 1, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("Date", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Transaction", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Balance", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				PdfPCell cell;
				// int i=0;

				cell = new PdfPCell(new Phrase(ft.getDate().toGMTString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getType() + " Invoice No. : " + ft.getInvoideId()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				double amountt = 0.0;
				amountt = ft.getAmount();
				BigDecimal bd_amountt = new BigDecimal(0.0);
				bd_amountt = new BigDecimal(amountt);
				bd_amountt = bd_amountt.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_amountt)));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);

				double bal = 0.0;
				bal = ft.getBalance();
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
				// i=i+1;
				// if(i<0);
				// break;
			}

			// Customer Table

			PdfPTable customerTable = new PdfPTable(1);
			customerTable.setWidthPercentage(20);
			customerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			// customerTable.setWidths(new int[]{3});

			PdfPCell Chcell;
			Chcell = new PdfPCell(new Phrase("To : ", headFont));
			Chcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Chcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;

				PdfPCell Ccell;
				Ccell = new PdfPCell(new Phrase(String.valueOf(ft.getCustomerName())));
				Ccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Ccell.setHorizontalAlignment(Element.ALIGN_CENTER);
				customerTable.addCell(Ccell);
				i = i + 1;
				if (i < 0)
					;
				break;
			}

			// Statement table

			PdfPTable sttable = new PdfPTable(5);
			sttable.setWidthPercentage(100);
			// sttable.setWidths(new int[]{3, 3, 1,2});

			Font stheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell sthcell;
			sthcell = new PdfPCell(new Phrase("Current", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("1-30 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("31-60 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("61-90 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("Amount Due", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			PdfPCell stcell;

			stcell = new PdfPCell(new Phrase(String.valueOf(currentAmount)));
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			LeoLogger.info("amount: " + currentAmount);

			stcell = new PdfPCell(new Phrase(String.valueOf(thirtydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(sixtydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(ninetydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;
				double bal = 0.0;
				bal = balance;
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				stcell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				stcell.setPaddingLeft(5);
				stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				sttable.addCell(stcell);
				i = i + 1;
				if (i < 0)
					;
				break;

			}

			// Statment
			PdfPTable dtable = new PdfPTable(1);
			dtable.setWidthPercentage(40);
			dtable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font dheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell dhcell;
			dhcell = new PdfPCell(new Phrase("Date", dheadFont));
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);

			// for (FinancialTransactionEntity ft : financialtrans) {

			dhcell = new PdfPCell(new Phrase(String.valueOf(new Date())));
			dhcell.setPaddingLeft(5);
			dhcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);
			// }

			// toptable
			PdfPTable ttable = new PdfPTable(2);
			ttable.setWidthPercentage(40);
			ttable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font theadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell thcell;

			thcell = new PdfPCell(new Phrase("Amount Due", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			thcell = new PdfPCell(new Phrase("Amount Enc.", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			PdfPCell sttcell;
			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;
				double bal = 0.0;
				bal = balance;
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				sttcell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				sttcell.setPaddingLeft(2);
				sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				ttable.addCell(sttcell);

				sttcell = new PdfPCell();
				sttcell.setPaddingLeft(2);
				sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// sttcell.setPaddingRight(2);
				ttable.addCell(sttcell);
				i = i + 1;
				if (i < 0)
					;
				break;
			}

			PdfWriter.getInstance(document, out);
			document.open();

			String wtext = "Statement\r\n\n" + "3754 Central American Blvd.\r\n" + "Belize City, Belize\r\n"
					+ "Phone: 207-0669\r\n" + "TIN # 35467." + "\n\n\n";

			Image image = Image.getInstance("classpath:logo_s.png");
			image.scaleAbsolute(230, 70);
			// image.setAbsolutePosition(100f, 500f);

			document.add(image);
			Paragraph para = new Paragraph(wtext);
			para.setSpacingAfter(24f);
			// document.add(image);
			document.add(para);

			document.add(dtable);

			document.add(customerTable);

			Paragraph para3 = new Paragraph(" ");
			para3.setSpacingAfter(32f);
			document.add(para3);

			document.add(ttable);

			document.add(table);

			document.add(sttable);

			document.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}

	public static ByteArrayInputStream SReportt(List<FTEntity> financialtrans, List<SpecialSalesEntity> salesEntity,
			MemberUser memberPojo) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			double  balance = 0.0;
				

			BigDecimal todayBalance = new BigDecimal(0.0);
			BigDecimal thirtyDayBalance = new BigDecimal(0.0);
			BigDecimal sixtyDayBalance = new BigDecimal(0.0);
			BigDecimal ninetyDayBalance = new BigDecimal(0.0);
			int lastTransactionIndex = 0;
			if (!financialtrans.isEmpty()) {
				lastTransactionIndex = financialtrans.size() - 1;
				FTEntity ft = financialtrans.get(lastTransactionIndex);
				balance = ft.getBalance();
			}

			BigDecimal amountDue = new BigDecimal(0.0);
			amountDue = new BigDecimal(balance);
			amountDue = amountDue.setScale(2, RoundingMode.HALF_UP);

			Calendar cal = Calendar.getInstance();

			Date today = cal.getTime();
			LeoLogger.info("Today : " + cal.getTime());

			// Substract 30 days from the calendar
			cal.add(Calendar.DATE, -30);
			Date thirtyday = cal.getTime();
			LeoLogger.info("30 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date sixtyday = cal.getTime();
			LeoLogger.info("60 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date ninetyday = cal.getTime();
			LeoLogger.info("90 days ago: " + cal.getTime());

			for (SpecialSalesEntity salent : salesEntity) {

		
				if (sixtyday.after(salent.getDate())) {
					if (salent.getIsActive() == 0) {					
						ninetyDayBalance =	ninetyDayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));										
					}

				} else if (sixtyday.before(salent.getDate()) && thirtyday.after(salent.getDate())) {
					if (salent.getIsActive() == 0) {					
						sixtyDayBalance =	sixtyDayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));										
					}
				} else if (thirtyday.before(salent.getDate()) && !validateDate(today,salent.getDate())) {
					if (salent.getIsActive() == 0) {						
						thirtyDayBalance =	thirtyDayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));															
					}
				} else if (validateDate(today,salent.getDate())) {
					if (salent.getIsActive() == 0) {			
						todayBalance = todayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));		
					}

				}

			}
			
			

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 3, 3, 1, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("Date", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Transaction", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Balance", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (FTEntity ft : financialtrans) {
				PdfPCell cell;
				// int i=0;

				cell = new PdfPCell(new Phrase(ft.getDate().toGMTString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getType() + " Invoice No. : " + ft.getReferenceno()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				double amountt = 0.0;
				amountt = ft.getAmount();
				BigDecimal bd_amountt = new BigDecimal(0.0);
				bd_amountt = new BigDecimal(amountt);
				bd_amountt = bd_amountt.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_amountt)));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);

				double bal = 0.0;
				bal = ft.getBalance();
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
				// i=i+1;
				// if(i<0);
				// break;
			}

			// Customer Table

			PdfPTable customerTable = new PdfPTable(1);
			customerTable.setWidthPercentage(20);
			customerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			// customerTable.setWidths(new int[]{3});

			PdfPCell Chcell;
			Chcell = new PdfPCell(new Phrase("To : ", headFont));
			Chcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Chcell);

			PdfPCell Ccell;
			Ccell = new PdfPCell(new Phrase(String.valueOf(memberPojo.getName())));
			Ccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Ccell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Ccell);

			// Statement table

			PdfPTable sttable = new PdfPTable(5);
			sttable.setWidthPercentage(100);
			// sttable.setWidths(new int[]{3, 3, 1,2});

			Font stheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell sthcell;
			sthcell = new PdfPCell(new Phrase("Current", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("1-30 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("31-60 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("61-90 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("Amount Due", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			PdfPCell stcell;

			todayBalance = todayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Current Day Balance : " + todayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(todayBalance)));
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			thirtyDayBalance = thirtyDayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Thirty Day Balance  ( 30 ) : " + thirtyDayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(thirtyDayBalance)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			
			sixtyDayBalance = sixtyDayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Sixty Day Balance  ( 60 ) : " + sixtyDayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(sixtyDayBalance)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			
			ninetyDayBalance = ninetyDayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Ninty Day Balance  ( 90 ) : " + ninetyDayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(ninetyDayBalance)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(amountDue)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			// Statment
			PdfPTable dtable = new PdfPTable(1);
			dtable.setWidthPercentage(40);
			dtable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font dheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell dhcell;
			dhcell = new PdfPCell(new Phrase("Date", dheadFont));
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);

			// for (FinancialTransactionEntity ft : financialtrans) {

			dhcell = new PdfPCell(new Phrase(String.valueOf(new Date())));
			dhcell.setPaddingLeft(5);
			dhcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);
			// }

			// toptable
			PdfPTable ttable = new PdfPTable(2);
			ttable.setWidthPercentage(40);
			ttable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font theadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell thcell;

			thcell = new PdfPCell(new Phrase("Amount Due", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			thcell = new PdfPCell(new Phrase("Amount Enc.", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			PdfPCell sttcell;

			sttcell = new PdfPCell(new Phrase(String.valueOf(amountDue)));
			sttcell.setPaddingLeft(2);
			sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(sttcell);

			sttcell = new PdfPCell(new Phrase(String.valueOf(memberPojo.getCreditpayment())));
			sttcell.setPaddingLeft(2);
			sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			// sttcell.setPaddingRight(2);
			ttable.addCell(sttcell);

			PdfWriter.getInstance(document, out);
			document.open();

			String wtext = "Statement\r\n\n" + "3754 Central American Blvd.\r\n" + "Belize City, Belize\r\n"
					+ "Phone: 207-0669\r\n" + "TIN # 35467." + "\n\n\n";

			Image image = Image.getInstance("classpath:logo_s.png");
			image.scaleAbsolute(230, 70);
			// image.setAbsolutePosition(100f, 500f);

			document.add(image);
			Paragraph para = new Paragraph(wtext);
			para.setSpacingAfter(24f);
			// document.add(image);
			document.add(para);

			document.add(dtable);

			document.add(customerTable);

			Paragraph para3 = new Paragraph(" ");
			para3.setSpacingAfter(32f);
			document.add(para3);

			document.add(ttable);

			document.add(table);

			document.add(sttable);

			document.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}


	public static ByteArrayInputStream Reportt(List<FTEntity> financialtrans, List<SalesEntity> salesEntity,
			MemberUser memberPojo) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			double  balance = 0.0;
			

			BigDecimal todayBalance = new BigDecimal(0.0);
			BigDecimal thirtyDayBalance = new BigDecimal(0.0);
			BigDecimal sixtyDayBalance = new BigDecimal(0.0);
			BigDecimal ninetyDayBalance = new BigDecimal(0.0);

			// Getting balance from last transaction
			int lastTransactionIndex = 0;
			if (!financialtrans.isEmpty()) {
				lastTransactionIndex = financialtrans.size() - 1;
				FTEntity ft = financialtrans.get(lastTransactionIndex);
				balance = ft.getBalance();
			}

			BigDecimal amountDue = new BigDecimal(0.0);
			amountDue = new BigDecimal(balance);
			amountDue = amountDue.setScale(2, RoundingMode.HALF_UP);

			Calendar cal = Calendar.getInstance();

			Date today = cal.getTime();
			LeoLogger.info("Today : " + cal.getTime());

			// Substract 30 days from the calendar
			cal.add(Calendar.DATE, -30);
			Date thirtyday = cal.getTime();
			LeoLogger.info("30 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date sixtyday = cal.getTime();
			LeoLogger.info("60 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date ninetyday = cal.getTime();
			LeoLogger.info("90 days ago: " + cal.getTime());

			for (SalesEntity salent : salesEntity) {

		
				if (sixtyday.after(salent.getDate())) {
					if (salent.getIsActive() == 0) {					
						ninetyDayBalance =	ninetyDayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));										
					}

				} else if (sixtyday.before(salent.getDate()) && thirtyday.after(salent.getDate())) {
					if (salent.getIsActive() == 0) {					
						sixtyDayBalance =	sixtyDayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));										
					}
				} else if (thirtyday.before(salent.getDate()) && !validateDate(today,salent.getDate())) {
					if (salent.getIsActive() == 0) {						
						thirtyDayBalance =	thirtyDayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));															
					}
				} else if (validateDate(today,salent.getDate())) {
					if (salent.getIsActive() == 0) {			
						todayBalance = todayBalance.add(new BigDecimal(salent.getGrand_total()).subtract( new BigDecimal(salent.getPaid())));		
					}

				}

			}

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 3, 3, 1, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("Date", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Transaction", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Balance", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (FTEntity ft : financialtrans) {
				PdfPCell cell;
				// int i=0;

				cell = new PdfPCell(new Phrase(ft.getDate().toGMTString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getType() + " Invoice No. : " + ft.getReferenceno()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				double amountt = 0.0;
				amountt = ft.getAmount();
				BigDecimal bd_amountt = new BigDecimal(0.0);
				bd_amountt = new BigDecimal(amountt);
				bd_amountt = bd_amountt.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_amountt)));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);

				double bal = 0.0;
				bal = ft.getBalance();
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
				// i=i+1;
				// if(i<0);
				// break;
			}

			// Customer Table

			PdfPTable customerTable = new PdfPTable(1);
			customerTable.setWidthPercentage(20);
			customerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			// customerTable.setWidths(new int[]{3});

			PdfPCell Chcell;
			Chcell = new PdfPCell(new Phrase("To : ", headFont));
			Chcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Chcell);

			PdfPCell Ccell;
			Ccell = new PdfPCell(new Phrase(String.valueOf(memberPojo.getName())));
			Ccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			Ccell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Ccell);

			// Statement table

			PdfPTable sttable = new PdfPTable(5);
			sttable.setWidthPercentage(100);
			// sttable.setWidths(new int[]{3, 3, 1,2});

			Font stheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell sthcell;
			sthcell = new PdfPCell(new Phrase("Current", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("1-30 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("31-60 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("61-90 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("Amount Due", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			PdfPCell stcell;

			todayBalance = todayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Current Day Balance : " + todayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(todayBalance)));
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			thirtyDayBalance = thirtyDayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Thirty Day Balance  ( 30 ) : " + thirtyDayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(thirtyDayBalance)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			
			sixtyDayBalance = sixtyDayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Sixty Day Balance  ( 60 ) : " + sixtyDayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(sixtyDayBalance)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			
			ninetyDayBalance = ninetyDayBalance.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("Ninty Day Balance  ( 90 ) : " + ninetyDayBalance);
			stcell = new PdfPCell(new Phrase(String.valueOf(ninetyDayBalance)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(amountDue)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			// Statment
			PdfPTable dtable = new PdfPTable(1);
			dtable.setWidthPercentage(40);
			dtable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font dheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell dhcell;
			dhcell = new PdfPCell(new Phrase("Date", dheadFont));
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);

			// for (FinancialTransactionEntity ft : financialtrans) {

			dhcell = new PdfPCell(new Phrase(String.valueOf(new Date())));
			dhcell.setPaddingLeft(5);
			dhcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);
			// }

			// toptable
			PdfPTable ttable = new PdfPTable(3); // Changed from 2 to 3 columns
			ttable.setWidthPercentage(60); // Adjusted to accommodate 3 columns
			ttable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font theadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfPCell thcell;

			// Header Row
			thcell = new PdfPCell(new Phrase("Amount Due", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			thcell = new PdfPCell(new Phrase("Available Credit", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			thcell = new PdfPCell(new Phrase("Available Deposit", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			// Data Row
			PdfPCell sttcell;

			sttcell = new PdfPCell(new Phrase(String.format("%.2f", amountDue)));
			sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ttable.addCell(sttcell);

			sttcell = new PdfPCell(new Phrase(String.format("%.2f", memberPojo.getCreditpayment())));
			sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ttable.addCell(sttcell);

			// Add the deposit value
			sttcell = new PdfPCell(new Phrase(String.format("%.2f", memberPojo.getDeposit())));
			sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ttable.addCell(sttcell);


			PdfWriter.getInstance(document, out);
			document.open();

			String wtext = "Statement\r\n\n" + "3754 Central American Blvd.\r\n" + "Belize City, Belize\r\n"
					+ "Phone: 207-0669\r\n" + "TIN # 128693." + "\n\n\n";

			Image image = Image.getInstance("classpath:logo_s.png");
			image.scaleAbsolute(230, 70);
			// image.setAbsolutePosition(100f, 500f);

			document.add(image);
			Paragraph para = new Paragraph(wtext);
			para.setSpacingAfter(24f);
			// document.add(image);
			document.add(para);

			document.add(dtable);

			document.add(customerTable);

			Paragraph para3 = new Paragraph(" ");
			para3.setSpacingAfter(32f);
			document.add(para3);

			document.add(ttable);

			document.add(table);

			document.add(sttable);

			document.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}
	
	
	
	
	
	public static ByteArrayInputStream ProfirLossReport(String startDate, String endDate, List<ProfitLossReportPojo> profitLossSummaryList ) {
	    Document document = new Document();
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    LeoLogger.info("ProfitLossReport ");
	    
	   // LeoLogger.info("ProfitLossReport profitLossSummaryList=="+profitLossSummaryList);
	    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

	    try {
	        // ... (existing code)

	        // Dummy data for Income Table
	    	
	    	
	    	for (ProfitLossReportPojo profitlossreport : profitLossSummaryList) {
	        PdfPTable incomeTable = new PdfPTable(2);
	        PdfPCell ihcell = new PdfPCell(new Phrase("Income", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        ihcell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        ihcell.setColspan(2);
	        incomeTable.addCell(ihcell);

	        PdfPCell icell = new PdfPCell(new Phrase("Sales"));
	        icell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        icell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        incomeTable.addCell(icell);
	        
	       
	        String formattedsale = decimalFormat.format(profitlossreport.getIncome());
	        
	      

	        icell = new PdfPCell(new Phrase(formattedsale));
	        icell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        icell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        incomeTable.addCell(icell);
	        
	        PdfPCell iicell = new PdfPCell(new Phrase("Total Income"));
	        iicell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        iicell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        incomeTable.addCell(iicell);
	     
	        String formattedIncome = decimalFormat.format(profitlossreport.getIncome());

	        PdfPCell totalIncomeAmountCell = new PdfPCell(new Phrase(formattedIncome, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        totalIncomeAmountCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        totalIncomeAmountCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        incomeTable.addCell(totalIncomeAmountCell);

	        // Dummy data for Expense Table
	        PdfPTable expenseTable = new PdfPTable(2);
	        PdfPCell ehcell = new PdfPCell(new Phrase("Cost of Goods Sold", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        ehcell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        ehcell.setColspan(2);
	        expenseTable.addCell(ehcell);

	        // Add your dummy expense data here
	        PdfPCell ecell = new PdfPCell(new Phrase("Cost of Goods Sold"));
	        ecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        ecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expenseTable.addCell(ecell);
	        String formattedcost = decimalFormat.format(profitlossreport.getCostOfGoodsSold());

	        ecell =  new PdfPCell(new Phrase(formattedcost));
	        ecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        ecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expenseTable.addCell(ecell);
	        
	        PdfPCell eecell = new PdfPCell(new Phrase("Total COGS"));
	        eecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        eecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expenseTable.addCell(eecell);
	        
	        eecell = new PdfPCell(new Phrase(formattedcost, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        eecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        eecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        expenseTable.addCell(eecell);

	  

	        // Dummy data for Profit and Loss Table
	        PdfPTable profitLossTable = new PdfPTable(2);
	        PdfPCell plhcell = new PdfPCell(new Phrase("Gross Profit", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        plhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        profitLossTable.addCell(plhcell);
	        String formattedgrossprofit = decimalFormat.format(profitlossreport.getGrossProfit());

	        PdfPCell plrcell =new PdfPCell(new Phrase(formattedgrossprofit));
	        plrcell.setPaddingLeft(2);
	        plrcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        plrcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        profitLossTable.addCell(plrcell);

	        PdfPCell plecell = new PdfPCell(new Phrase("Net Ordinary Income", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        plecell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        profitLossTable.addCell(plecell);

	        PdfPCell plbcell = new PdfPCell(new Phrase(formattedgrossprofit));
	        plbcell.setPaddingLeft(2);
	        plbcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        plbcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        profitLossTable.addCell(plbcell);

	        PdfPCell plnphcell = new PdfPCell(new Phrase("Net Income", FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        plnphcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        profitLossTable.addCell(plnphcell);

	        PdfPCell plnprcell = new PdfPCell(new Phrase(formattedgrossprofit, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
	        plnprcell.setPaddingLeft(2);
	        plnprcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        plnprcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        profitLossTable.addCell(plnprcell);

	        // ... (existing code)

	        PdfWriter.getInstance(document, out);
	        document.open();
	      

	        String wtext = "Supplier Plus Distributor\r\n" + "Profit & Loss\r\n";
	        String dateText = "Start Date: " + startDate + "   End Date: " + endDate;

	        Paragraph para = new Paragraph();
	        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        para.setFont(boldFont);
	        para.setAlignment(Element.ALIGN_CENTER);
	        para.add(wtext);
	        para.add("\r\n"); // Add a line break between existing content and date
	        para.add(dateText); // Add the date information
	        para.setSpacingAfter(24f);

	        document.add(para);


	        // ... (existing code)

	        document.add(incomeTable);

	        Paragraph para4 = new Paragraph(" ");
	        para4.setSpacingAfter(0.0f);
	        document.add(para4);

	        document.add(expenseTable);

	        Paragraph para5 = new Paragraph(" ");
	        para5.setSpacingAfter(0.0f);
	        document.add(para5);
	        
	        Paragraph para6 = new Paragraph(" ");
	        para6.setSpacingAfter(0.0f);
	        document.add(profitLossTable);

	        // ... (existing code)

	        document.close();
	    }

	    } catch (DocumentException ex) {
	        logger.error("Error occurred: {0}", ex);
	    }

	    return new ByteArrayInputStream(out.toByteArray());
	}


	// Additional methods for data population and calculations can be added here

	
	
	public static boolean validateDate(Date date1, Date date2) {
		try {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date1 = sdf.parse(sdf.format(date1));
		date2 = sdf.parse(sdf.format(date2));
		int comparisonResult = date1.compareTo(date2);
		 if (comparisonResult > 0) {
	            return false ;
	        } else if (comparisonResult < 0) {
	            return false;
	        } else {
	            return true;
	        }
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return false;
	}


	public static ByteArrayInputStream SReport(List<FinancialTransactionEntity> financialtrans,
			List<SpecialSalesEntity> salesEntity) {

		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {

			double currentAmount = 0.0, thirtydaybal = 0.0, sixtydaybal = 0.0, ninetydaybal = 0.0, balance = 0.0;

			Calendar cal = Calendar.getInstance();

			Date today = cal.getTime();
			LeoLogger.info("Today : " + cal.getTime());

			// Substract 30 days from the calendar
			cal.add(Calendar.DATE, -30);
			Date thirtyday = cal.getTime();
			LeoLogger.info("30 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date sixtyday = cal.getTime();
			LeoLogger.info("60 days ago: " + cal.getTime());

			cal.add(Calendar.DATE, -30);
			Date ninetyday = cal.getTime();
			LeoLogger.info("90 days ago: " + cal.getTime());

			for (SpecialSalesEntity salent : salesEntity) {
				if (ninetyday.after(salent.getDate())) {
					ninetydaybal = ninetydaybal + (salent.getGrand_total() - salent.getPaid());
				}
				if (ninetyday.after(salent.getDate()) && sixtyday.before(salent.getDate())) {
					sixtydaybal = sixtydaybal + (salent.getGrand_total() - salent.getPaid());
				}
				if (sixtyday.after(salent.getDate()) && thirtyday.before(salent.getDate())) {
					thirtydaybal = thirtydaybal + (salent.getGrand_total() - salent.getPaid());
				} else {
					double amount = 0.0;
					amount = currentAmount + (salent.getGrand_total() - salent.getPaid());
					BigDecimal bd_amount = new BigDecimal(0.0);
					bd_amount = new BigDecimal(amount);
					bd_amount = bd_amount.setScale(2, RoundingMode.HALF_UP);

					currentAmount = bd_amount.doubleValue();
					LeoLogger.info("amount: " + currentAmount);

				}
				for (FinancialTransactionEntity ft : financialtrans) {
					// balance=ft.getBalance();
					double bal = 0.0;
					bal = ft.getBalance();
					BigDecimal bd_bal = new BigDecimal(0.0);
					bd_bal = new BigDecimal(bal);
					bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);
					balance = bd_bal.doubleValue();

					// LeoLogger.info("balance: " + balance);

				}

			}

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setWidths(new int[] { 3, 3, 1, 2 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;

			hcell = new PdfPCell(new Phrase("Date", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Transaction", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Amount", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Balance", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				PdfPCell cell;
				// int i=0;

				cell = new PdfPCell(new Phrase(ft.getDate().toGMTString()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(ft.getType() + " Invoice No. : " + ft.getInvoideId()));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				double amountt = 0.0;
				amountt = ft.getAmount();
				BigDecimal bd_amountt = new BigDecimal(0.0);
				bd_amountt = new BigDecimal(amountt);
				bd_amountt = bd_amountt.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_amountt)));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				table.addCell(cell);

				double bal = 0.0;
				bal = ft.getBalance();
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				cell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
				// i=i+1;
				// if(i<0);
				// break;
			}

			// Customer Table

			PdfPTable customerTable = new PdfPTable(1);
			customerTable.setWidthPercentage(20);
			customerTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			// customerTable.setWidths(new int[]{3});

			PdfPCell Chcell;
			Chcell = new PdfPCell(new Phrase("To : ", headFont));
			Chcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			customerTable.addCell(Chcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;

				PdfPCell Ccell;
				Ccell = new PdfPCell(new Phrase(String.valueOf(ft.getCustomerName())));
				Ccell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				Ccell.setHorizontalAlignment(Element.ALIGN_CENTER);
				customerTable.addCell(Ccell);
				i = i + 1;
				if (i < 0)
					;
				break;
			}

			// Statement table

			PdfPTable sttable = new PdfPTable(5);
			sttable.setWidthPercentage(100);
			// sttable.setWidths(new int[]{3, 3, 1,2});

			Font stheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell sthcell;
			sthcell = new PdfPCell(new Phrase("Current", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("1-30 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("31-60 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("61-90 DAYS PAST DUE", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			sthcell = new PdfPCell(new Phrase("Amount Due", headFont));
			sthcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(sthcell);

			PdfPCell stcell;

			stcell = new PdfPCell(new Phrase(String.valueOf(currentAmount)));
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			LeoLogger.info("amount: " + currentAmount);

			stcell = new PdfPCell(new Phrase(String.valueOf(thirtydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(sixtydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			stcell = new PdfPCell(new Phrase(String.valueOf(ninetydaybal)));
			stcell.setPaddingLeft(5);
			stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			sttable.addCell(stcell);

			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;
				double bal = 0.0;
				bal = balance;
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				stcell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				stcell.setPaddingLeft(5);
				stcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				stcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				sttable.addCell(stcell);
				i = i + 1;
				if (i < 0)
					;
				break;

			}

			// Statment
			PdfPTable dtable = new PdfPTable(1);
			dtable.setWidthPercentage(40);
			dtable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font dheadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell dhcell;
			dhcell = new PdfPCell(new Phrase("Date", dheadFont));
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);

			// for (FinancialTransactionEntity ft : financialtrans) {

			dhcell = new PdfPCell(new Phrase(String.valueOf(new Date())));
			dhcell.setPaddingLeft(5);
			dhcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			dhcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			dtable.addCell(dhcell);
			// }

			// toptable
			PdfPTable ttable = new PdfPTable(2);
			ttable.setWidthPercentage(40);
			ttable.setHorizontalAlignment(Element.ALIGN_RIGHT);

			Font theadFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell thcell;

			thcell = new PdfPCell(new Phrase("Amount Due", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			thcell = new PdfPCell(new Phrase("Amount Enc.", theadFont));
			thcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ttable.addCell(thcell);

			PdfPCell sttcell;
			for (FinancialTransactionEntity ft : financialtrans) {
				int i = 0;
				double bal = 0.0;
				bal = balance;
				BigDecimal bd_bal = new BigDecimal(0.0);
				bd_bal = new BigDecimal(bal);
				bd_bal = bd_bal.setScale(2, RoundingMode.HALF_UP);

				sttcell = new PdfPCell(new Phrase(String.valueOf(bd_bal)));
				sttcell.setPaddingLeft(2);
				sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				ttable.addCell(sttcell);

				sttcell = new PdfPCell();
				sttcell.setPaddingLeft(2);
				sttcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				sttcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				// sttcell.setPaddingRight(2);
				ttable.addCell(sttcell);
				i = i + 1;
				if (i < 0)
					;
				break;
			}

			PdfWriter.getInstance(document, out);
			document.open();

			String wtext = "Statement\r\n\n" + "3754 Central American Blvd.\r\n" + "Belize City, Belize\r\n"
					+ "Phone: 207-0669\r\n" + "TIN # 35467." + "\n\n\n";

			Image image = Image.getInstance("classpath:logo_s.png");
			image.scaleAbsolute(230, 70);
			// image.setAbsolutePosition(100f, 500f);

			document.add(image);
			Paragraph para = new Paragraph(wtext);
			para.setSpacingAfter(24f);
			// document.add(image);
			document.add(para);

			document.add(dtable);

			document.add(customerTable);

			Paragraph para3 = new Paragraph(" ");
			para3.setSpacingAfter(32f);
			document.add(para3);

			document.add(ttable);

			document.add(table);

			document.add(sttable);

			document.close();

		} catch (DocumentException | IOException ex) {

			logger.error("Error occurred: {0}", ex);
		}

		return new ByteArrayInputStream(out.toByteArray());
	}


}
