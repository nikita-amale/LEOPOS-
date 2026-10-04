package com.leonet.serviceimpl;

import java.io.File;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;





import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.text.ParseException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import javax.annotation.Nonnull;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.leonet.common.entity.FTEntity;
import com.leonet.common.entity.FinancialTransactionEntity;
import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.entity.RequestQuoteEntity;
import com.leonet.common.entity.RequestQuoteItemEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.entity.SpecialSaleRegister;
import com.leonet.common.entity.SpecialSaleRegisterHistory;
import com.leonet.common.entity.SpecialSalesEntity;
import com.leonet.common.entity.SpecialSalesItemEntity;
import com.leonet.common.entity.VendorEntity;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.AddPaymentReqPojo;
import com.leonet.common.pojo.BulkPaymentPojo;
import com.leonet.common.pojo.BulkSaleEmailPojo;
import com.leonet.common.pojo.CustomerPurchasePojo;
import com.leonet.common.pojo.CustomerPurchaseSalePojo;
import com.leonet.common.pojo.CustomerPurchaseSpecialPojo;
import com.leonet.common.pojo.CustomerReportPojo;
import com.leonet.common.pojo.FTPojo;
import com.leonet.common.pojo.FinancialTransactionPojo;
import com.leonet.common.pojo.PaymentPojo;
import com.leonet.common.pojo.PaymentReportPojo;
import com.leonet.common.pojo.ProfitLossReportPojo;
import com.leonet.common.pojo.QuotesItemPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.RegisterPojo;
import com.leonet.common.pojo.RegisterhistoryPojo;
import com.leonet.common.pojo.ReplenishmentRepotPojo;
import com.leonet.common.pojo.RequestQuotePojo;
import com.leonet.common.pojo.RequestQuotesItemsPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.SaleItemPojo;
import com.leonet.common.pojo.SaleMonthReportPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.SaleReportSummaryPojo;
import com.leonet.common.pojo.SalesPercentagePojo;
import com.leonet.common.pojo.SpecialSaleMonthReportPojo;
import com.leonet.common.pojo.SpecialSaleRegisterPojo;
import com.leonet.common.pojo.SpecialSalesItemPojo;
import com.leonet.common.pojo.SpecialSalesPojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.repo.VendorRepo;
import com.leonet.entity.BulkPaymentEntity;
import com.leonet.entity.DeletSaleEntity;
import com.leonet.entity.MemberUser;
import com.leonet.entity.PaymentEntity;
import com.leonet.entity.QuotesEntity;
import com.leonet.entity.QuotesItemEntity;
import com.leonet.entity.RegisterEntity;
import com.leonet.entity.RegisterhistoryEntity;
import com.leonet.entity.ReturnCashEntity;
import com.leonet.entity.ReturnsEntity;
import com.leonet.entity.SalesPercentageEntity;
import com.leonet.entity.UnitEntity;
import com.leonet.repo.AgingDetailsRepo;
import com.leonet.repo.BulkPaymentRepo;
import com.leonet.repo.DeletSaleRepo;
import com.leonet.repo.FTRepo;
import com.leonet.repo.FinancialTransactionRepo;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.PaymentRepo;
import com.leonet.repo.ProductDetailsRepo;
import com.leonet.repo.QuotesItemRepo;
import com.leonet.repo.QuotesRepo;
import com.leonet.repo.RegisterRepo;
import com.leonet.repo.RequestQuoteItemRepo;
import com.leonet.repo.RequestQuoteRepo;
import com.leonet.repo.ReturnsCashRepo;
import com.leonet.repo.ReturnsRepo;
import com.leonet.repo.RgisterhistoryRepo;
import com.leonet.repo.SalesItemRepo;
import com.leonet.repo.SalesPercentageRepo;
import com.leonet.repo.SalesRepo;
import com.leonet.repo.SpecialSaleRegisterHistoryRepository;
import com.leonet.repo.SpecialSaleRegisterRepository;
import com.leonet.repo.SpecialSalesItemRepo;
import com.leonet.repo.SpecialSalesRepo;
import com.leonet.repo.UnitRepo;
import com.leonet.service.CatagoryService;
import com.leonet.service.SaleService;
import com.leonet.util.CurrentUserUtil;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.LeoLogger;
import com.leonet.util.MailSendingAPI;

import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Service
public class SaleServiceImpl implements SaleService {

	private static final Sort sort = null;
	
	@Autowired
	VendorRepo vendorRepo;

	@Autowired
	SalesRepo salesRepo;
	
	@Autowired
	DeletSaleRepo deleteSaleRepo;

	@Autowired
	SalesPercentageRepo salespercentRepo;

	@Autowired
	UnitRepo unitRepo;

	@Autowired
	SalesItemRepo salesItemRepo;

	@Autowired
	MemberUserRepo memberUserRepo;

	@Autowired
	ProductDetailsRepo productDetailsRepo;

	@Autowired
	RequestQuoteRepo requestQuoteRepo;

	@Autowired
	QuotesRepo quotesRepo;

	@Autowired
	QuotesItemRepo quotesItemRepo;

	@Autowired
	RequestQuoteItemRepo requestQuoteItemRepo;

	@Autowired
	SpecialSalesRepo specialsalesRepo;

	@Autowired
	SpecialSalesItemRepo specialsalesItemRepo;

	@Autowired
	FinancialTransactionRepo financialTransactionsRepo;

	@Autowired
	FTRepo fTRepo;

	@Autowired
	SaleService saleService;

	@Autowired
	BulkPaymentRepo bulkPaymentRepo;

	@Autowired
	AgingDetailsRepo agingDetailsRepo;

	@Autowired
	Mapper mapper;

	@Autowired
	PaymentRepo paymentRepo;

	@Autowired
	BulkPaymentRepo bulkpaymentRepo;

	@Autowired
	RegisterRepo registerRepo;

	@Autowired
	RgisterhistoryRepo registerhistoryRepo;

	@Autowired
	ReturnsRepo returnsRepo;

	@Autowired
	ReturnsCashRepo returnscashRepo;

	@Autowired
	CatagoryService addCatagoryService;

	@Autowired
	private CustomFileUploadUtil customFileUploadUtil;

	@Autowired
	private MailSendingAPI mailSendingAPI;
	
	@Autowired
	SpecialSaleRegisterRepository specialSaleRegisterRepo;
	
	@Autowired
	SpecialSaleRegisterHistoryRepository specialSaleRegisterHistoryRepository;

	@Value("${leo.pos.admin.pdfPath}")
	private String pdfPath;

	@Value("${leo.pos.admin.sale.filePath}")
	private String salePdfPath;

	@Value("${leo.pos.amdin.sale.percentage}")
	private Long salePercentage;

	@Value("${leo.pos.amdin.quote.percentage}")
	private Long quotePercentage;

	//private Logger logger = Logger.getLogger(SaleService.class);

	@Override
	public ResultVO addSale(List<AddItemReqPojo> addItemReqPojos) {
		MemberUser memberPojo = new MemberUser();
		// for not allowing sale if amount is pending
		for (AddItemReqPojo additem : addItemReqPojos) {
			// LeoLogger.info("SaleServiceImpl--- ....." + additem);

			memberPojo = memberUserRepo.findById(additem.getCustomerId());

			BigDecimal thresholdAmount = new BigDecimal(memberPojo.getThreshholdamount());
			int thresholdDays = memberPojo.getThreshholddays();
			BigDecimal sixtydaybal = new BigDecimal(0.0);
			BigDecimal abc = new BigDecimal(300.00);
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			LeoLogger.info("Today : " + cal.getTime());
			cal.add(Calendar.DATE, -60);
			Date sixtyday = cal.getTime();
			LeoLogger.info("60 days ago: " + cal.getTime());

			List<SalesEntity> saleentity = salesRepo.findAllByMemberid(additem.getCustomerId());
			//LeoLogger.info("SaleServiceImpl--- ....." + saleentity);
			for (SalesEntity sale : saleentity) {

				if (sixtyday.after(sale.getDate())) {
					LeoLogger.info("Inside if : " + sale.getDate());

					if (sale.getIsActive() == 0 && sale.getPaymentstatus().equalsIgnoreCase("Due")) {
						//LeoLogger.info("Inside if(2) : ");
						double x = sale.getGrand_total() - sale.getPaid();
						LeoLogger.info("Inside x : " + x);
						sixtydaybal = sixtydaybal.add(new BigDecimal((sale.getGrand_total() - sale.getPaid())));
						//LeoLogger.info("Inside sixtydaybal : " + sixtydaybal);
						sixtydaybal = sixtydaybal.setScale(2, RoundingMode.HALF_UP);
						// sixtydaybal =sixtydaybal.doubleValue();
						//LeoLogger.info("Sixty Day Balance found ( 60 ) : " + sixtydaybal);
					}
				}
				if (sixtydaybal.compareTo(thresholdAmount) > 0) {
					memberPojo.setBlocked(1);

				}
			}

		}

		ResultVO resultVO = new ResultVO();

		try {

			SalesEntity saleEntity = new SalesEntity();

			double grand_total = 0, tax_rate = 0, total = 0, unit_price = 0;
			int quantity = 0;

			// Declare Bigdecimal equivalents
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			String Ctype = "";
			double creditamount = 0.0;

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {
				 LeoLogger.info("SaleServiceImpl--- ....." + additem);

				memberPojo = memberUserRepo.findById(additem.getCustomerId());
				saleEntity.setDate(new Date());
				saleEntity.setMemberid(memberPojo.getId());
				saleEntity.setMember_name(memberPojo.getName());
				saleEntity.setCustomeraddress(memberPojo.getAddress());
				saleEntity.setPincode(memberPojo.getPincode());
				saleEntity.setPhonemain(memberPojo.getPhonemain());
				saleEntity.setMembername(memberPojo.getName());

				Ctype = memberPojo.getCtype();
				// LeoLogger.info("SaleServiceImpl---addSale---Member Pojo ....." +
				// memberPojo.toString());

				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				if (productDetailsPojo != null)
					unit_price = additem.getPrice().doubleValue();
				quantity = additem.getQuantity() != null && !additem.getQuantity().isEmpty()
						? Integer.parseInt(additem.getQuantity())
						: 0;
				
				/*
				 * if(!Ctype.equalsIgnoreCase("WholeSellers") &&
				 * productDetailsPojo.getpromotion() !=0 ) {
				 * 
				 * unit_price = unit_price -
				 * ((unit_price*productDetailsPojo.getpromotion())/100);
				 * LeoLogger.info("Applying Promotion for " + productDetailsPojo.getpromotion()
				 * + "% and the new Unit price now is ....." + unit_price);
				 * 
				 * }
				 */
				saleEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);

				LeoLogger.info("SaleServiceImpl---addSale---TAX Rate is...." + additem.getTax());

				if (additem.getTax().equalsIgnoreCase("YES")
						&& !memberPojo.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
					tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				} else {
					tax_rate = 0;
				}
				// saleEntity.setReferenceno("SALE"+currentYear+"/"+currentmonth+"/"+
				// additem.getProductId());

				saleEntity.setTotal_discount(0);

				saleEntity.setCf1(productDetailsPojo.getcf1());

				// saleEntity.setUser_id();
			}

			grand_total = total + tax_rate;
			saleEntity.setOrder_tax(0);
			
			

			bd_tax_rate = new BigDecimal(tax_rate).setScale(2, RoundingMode.HALF_UP);
			tax_rate = bd_tax_rate.doubleValue();

			bd_total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
			total = bd_total.doubleValue();
			saleEntity.setProduct_tax(bd_tax_rate.doubleValue());
			saleEntity.setTotal_tax(bd_tax_rate.doubleValue());
			//LeoLogger.info("SaleServiceImpl---addSale--Tax Rate...tax_rate.." + tax_rate);
			//LeoLogger.info("SaleServiceImpl---addSale--Tax Rate....." + bd_tax_rate);

			saleEntity.setPaymentstatus("Due");
			saleEntity.setOrder_discount(0);
			saleEntity.setCtype(Ctype);

			saleEntity.setTotal(bd_total.doubleValue());
			LeoLogger.info("SaleServiceImpl---addSale--Total....." + bd_total);

			// bd_grand_total = new BigDecimal(grand_total);
			// LeoLogger.info("SaleServiceImpl---addSale--Grand Total.(2)...." +
			// bd_grand_total);
			bd_grand_total = bd_total.add(bd_tax_rate).setScale(2, RoundingMode.HALF_UP);
			//LeoLogger.info("SaleServiceImpl---addSale---Grand Total....." + bd_grand_total);
			saleEntity.setGrand_total(bd_grand_total.doubleValue());
			saleEntity.setGrandtotal(bd_grand_total.toString());
			LeoLogger.info("SaleServiceImpl---addSale--Grand Total.(3)...." + bd_grand_total);

			saleEntity.setIsActive(0);

			SalesEntity saleenty = salesRepo.save(saleEntity);
			UnitEntity unitentity = new UnitEntity();
			List<SalesItemEntity> saleItemList = new ArrayList<>();
			// this loop is for sales breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());
				// LeoLogger.info("SaleServiceImpl---addSale---productDetailsEnt....." +
				// productDetailsEnt);
				saleEntity.setPurchaseorder(additem.getPurchaseorder());

				salesRepo.save(saleEntity);

				if (additem.getUnit() != null && additem.getUnit() != "") {
					unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				}
				//LeoLogger.info("SaleServiceImpl---addSale--unitentity." + unitentity);

				
				BigDecimal price = productDetailsEnt.getprice();
				long qt = Long.parseLong(additem.getQuantity());
				BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(qt));
				BigDecimal tax = totalPrice.multiply(BigDecimal.valueOf(0.125));
				LeoLogger.info("SaleServiceImpl---addSale--price." + price);
				LeoLogger.info("SaleServiceImpl---addSale--totalPrice." + totalPrice);

				//tax = (productDetailsEnt.getprice() * Long.parseLong(additem.getQuantity())) * .125;

				SalesItemEntity salesItemEntity = new SalesItemEntity();
				salesItemEntity.setSale_id(saleenty.getSaleId());
				salesItemEntity.setProduct_id(additem.getProductId());
				// salesItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				salesItemEntity.setItem_tax(additem.getPrice().doubleValue() * 0.125);
				salesItemEntity.setGst("12.5");
				salesItemEntity.setItem_discount(0d);
				salesItemEntity.setProduct_code(additem.getProductId().toString());
				salesItemEntity.setProduct_name(additem.getProductName());
				salesItemEntity.setRoll(additem.getRoll());
				bd_real_unit_price = additem.getPrice();
				bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
				salesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
				salesItemEntity.setUnit_quantity(unitentity.getUnitname());
				salesItemEntity.setReturnqty("0");
				salesItemEntity.setCost(productDetailsEnt.getcost());
				bd_subtotal = additem.getSubtotal();
				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				salesItemEntity.setSubtotal(bd_subtotal.doubleValue());
				salesItemEntity.setSale_item_id(unitentity.getId());
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					// LeoLogger.info(""+unitentity.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					System.out.println(qty);
					System.out.println(unit);
					qty = qty .multiply(unit) ;
					salesItemEntity.setQuantity(qty);
					// LeoLogger.info("SaleServiceImpl---addSale--qt....." +qty);
				} else {
					salesItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
				}

				salesItemEntity.setTax((tax).toString());

				salesItemRepo.save(salesItemEntity);

				saleItemList.add(salesItemEntity);

				// Subtracting Quantities here from products
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					System.out.println(qty);
					qty = qty .multiply(unit) ;
					System.out.println(unit);
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(qty));
				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					productDetailsEnt
							.setQuantity(productDetailsEnt.getQuantity() .subtract(qty));
				}
				productDetailsRepo.save(productDetailsEnt);
			}

			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;

			saleEntity.setReferenceno("SALE" + currentYear + "/" + currentmonth + "/" + saleenty.getSaleId());
			saleEntity.setFileName(customFileUploadUtil.savePdfFileIntoDir(saleItemList, saleenty));
			salesRepo.save(saleEntity);

			// <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

			// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = new Date();

			// convert date to calendar and add 30 days
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);

			Calendar calendarInstance = Calendar.getInstance();
			calendarInstance.add(Calendar.DATE, 30);

			FinancialTransactionEntity ftLatest = financialTransactionsRepo
					.findTopByCustomerIdOrderByFanIdDesc(saleenty.getMemberid());

			FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

			ftEntity.setInvoideId(saleenty.getSaleId());
			ftEntity.setAmount(bd_grand_total.doubleValue());
			ftEntity.setCustomerId(saleenty.getMemberid());
			ftEntity.setCustomerName(saleenty.getMember_name());
			ftEntity.setDate(new Date());
			ftEntity.setDueDate(calendarInstance.getTime());
			ftEntity.setType("Invoice");
			ftEntity.setReferenceno(saleenty.getReferenceno());

			if (ftLatest == null) {
				ftEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal ftTotal = new BigDecimal(0.0);
				BigDecimal fttTotal = new BigDecimal(ftLatest.getBalance());
				fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
				/*LeoLogger.info(
						"SaleServiceImpl---addSale---Latest Financial Transation balance  ....." + bd_grand_total);
				LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....."
						+ ftLatest.getBalance());*/
				ftTotal = bd_grand_total.add(fttTotal);
				ftEntity.setBalance(ftTotal.doubleValue());
			}

			financialTransactionsRepo.save(ftEntity);

			// ************************ Financial transaction Entry ended
			// *******************

			/*
			 * if(memberPojo.getCreditpayment()!=0) { double creditpay=
			 * memberPojo.getCreditpayment(); BigDecimal credit= new
			 * BigDecimal(creditpay-saleenty.getGrand_total()); credit=credit.setScale(2,
			 * RoundingMode.HALF_UP); memberPojo.setCreditpayment(credit.doubleValue()); }
			 */

			// ******FT Entry start******
			FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(saleenty.getMemberid());

			FTEntity fttEntity = new FTEntity();

			fttEntity.setInvoideId(saleenty.getSaleId());
			fttEntity.setAmount(bd_grand_total.doubleValue());
			fttEntity.setCustomerId(saleenty.getMemberid());
			fttEntity.setCustomerName(saleenty.getMember_name());
			fttEntity.setDate(new Date());
			fttEntity.setDueDate(calendarInstance.getTime());
			fttEntity.setType("Invoice");
			fttEntity.setReferenceno(saleenty.getReferenceno());

			if (fttLatest == null) {
				fttEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal fttTotal = new BigDecimal(0.0);
				BigDecimal ftrTotal = new BigDecimal(fttLatest.getBalance());
				ftrTotal = ftrTotal.setScale(2, RoundingMode.HALF_UP);
				/*LeoLogger.info(
						"SaleServiceImpl---addSale---Latest Financial Transation balance  ....." + bd_grand_total);
				LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....."
						+ fttLatest.getBalance());*/
				fttTotal = bd_grand_total.add(ftrTotal);
				fttEntity.setBalance(fttTotal.doubleValue());
			}

			fTRepo.save(fttEntity);
			// ******FT Entry ended******

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
			// >>>>>>>>>>>>>>>>>>>>>>>>>>

			RegisterEntity registerEntity = new RegisterEntity();
		
			List<RegisterEntity> entities = registerRepo.findAllByOrderByIdDesc();
			
			registerEntity = registerRepo.findAByDate(new Date());
			
			if (registerEntity != null)

			{
				registerEntity = entities.get(0);

				 LeoLogger.info("SaleServiceImpl---addSale-- Salesamount before entry..." + registerEntity.getSalesamount());
				BigDecimal ft = new BigDecimal(0.0);
				ft = new BigDecimal(registerEntity.getSalesamount() + bd_grand_total.doubleValue());
				ft = ft.setScale(2, RoundingMode.HALF_UP);
				registerEntity.setSalesamount(ft.doubleValue());
				BigDecimal ftTotal = new BigDecimal(0.0);
				ftTotal = new BigDecimal(registerEntity.getClosingbal() + bd_grand_total.doubleValue());
				ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
				registerEntity.setReferenceno(registerEntity.getReferenceno() + "," + saleenty.getSaleId());
				registerRepo.save(registerEntity);
				 LeoLogger.info("SaleServiceImpl---addSale-- Salesamount after entry..." + registerEntity.getSalesamount());
			} else {
				RegisterEntity newregisterEntity = new RegisterEntity();
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				newregisterEntity.setCashpayment(0.00);
				newregisterEntity.setCreditcardpayment(0.00);
				newregisterEntity.setOpeningbal(1000.00);
				newregisterEntity.setClosingbal(1000.00);
				newregisterEntity.setChequepayment(0.00);
				newregisterEntity.setRefunds(0.00);
				newregisterEntity.setReferenceno(String.valueOf(saleenty.getSaleId()));
				newregisterEntity.setSalesamount(bd_grand_total.doubleValue());
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);

				LeoLogger.info("SaleServiceImpl--- addSale---New Register Entry made"+newregisterEntity.getSalesamount());

			}

			// **************************** Register Details Entry ended
			// ********************

			// ***Registerhistory entry***//
			applyRegisterhistory(saleEntity);
			// *****To check credit amount is there***

			PaymentEntity paymentEntity = new PaymentEntity();
			creditamount = memberPojo.getCreditpayment();
			LeoLogger.info("SaleServiceImpl--- credit amount before apply to sell " + creditamount);
			SalesEntity csaleEntity = salesRepo.findBySaleId(saleEntity.getSaleId());
			LeoLogger.info("****csaleEntity" + csaleEntity.getReferenceno());

			double grandtotal = Double.parseDouble(csaleEntity.getGrandtotal());
			LeoLogger.info("SaleServiceImpl--- sell amount is " + grandtotal);

			// **credit amount to be changed**
			if (grandtotal <= creditamount) {
				memberPojo.setCreditpayment(
						new BigDecimal(creditamount - grandtotal).setScale(2, RoundingMode.HALF_UP).doubleValue());
			} else {
				memberPojo.setCreditpayment(0);
			}

			LeoLogger.info("SaleServiceImpl--- credit amount after apply to sell " + creditamount);
			memberUserRepo.save(memberPojo);

			if (creditamount != 0.0) {
				LeoLogger.info("Customer has a credit amount" + creditamount);

				if (grandtotal <= creditamount) {
					//LeoLogger.info("****If condition creditamount" + creditamount);
					//LeoLogger.info("****If condition creditamount" + grandtotal);
					csaleEntity.setPaid(grandtotal);
					csaleEntity.setPaymentstatus("Paid");
					paymentEntity.setGrand_total(grandtotal);
					salesRepo.save(csaleEntity);

				} else {
					//LeoLogger.info("****else condition creditamount" + creditamount);
					//LeoLogger.info("****else condition creditamount" + grandtotal);
					csaleEntity.setPaid(creditamount);
					csaleEntity.setPaymentstatus("Due");
					paymentEntity.setGrand_total(creditamount);
					salesRepo.save(csaleEntity);
				}
				/*
				 * Date da = new Date(); int yearr = d.getYear(); int currentYearr = year +
				 * 1900; int currentmonthh = d.getMonth() + 1;
				 */
				paymentEntity.setMember_name(csaleEntity.getMembername());
				paymentEntity.setMember_id(csaleEntity.getMemberid());
				paymentEntity.setPtype("CA");
				paymentEntity.setRsaleId(csaleEntity.getSaleId());
				paymentEntity.setSalesreferenceno(csaleEntity.getReferenceno());
				paymentEntity.setstatus("CreditAmount");
				paymentEntity.setCtype(csaleEntity.getCtype());
				paymentRepo.save(paymentEntity);
				paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
						+ csaleEntity.getSaleId() + "-" + paymentEntity.getId());
				paymentEntity.setPaymentdate(d);
				paymentRepo.save(paymentEntity);

				// ***FinancialTransaction Entry***

				FinancialTransactionEntity ftLatestt = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(csaleEntity.getMemberid());

				FinancialTransactionEntity ftEntityy = new FinancialTransactionEntity();

				ftEntityy.setInvoideId(csaleEntity.getSaleId());
				if (grandtotal <= creditamount) {
					ftEntityy.setAmount(grandtotal);
				} else {
					ftEntityy.setAmount(creditamount);
				}
				ftEntityy.setCustomerId(csaleEntity.getMemberid());
				ftEntityy.setCustomerName(csaleEntity.getMember_name());
				ftEntityy.setDate(new Date());
				ftEntityy.setDueDate(calendarInstance.getTime());
				ftEntityy.setType("CreditAmount--Payment");
				ftEntityy.setReferenceno(csaleEntity.getReferenceno());

				if (ftLatestt == null) {
					ftEntityy.setBalance(bd_grand_total.doubleValue());
				} else {
					BigDecimal ftTotal = new BigDecimal(0.0);
					BigDecimal fttTotal = new BigDecimal(ftLatestt.getBalance());
					fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
					BigDecimal gtotal = new BigDecimal(grandtotal);
					BigDecimal camount = new BigDecimal(creditamount);

					LeoLogger.info(
							"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance before credit amount apply  ....."
									+ ftLatestt.getBalance());
					if (grandtotal <= creditamount) {
						ftTotal = fttTotal.subtract(gtotal);
					} else {
						ftTotal = fttTotal.subtract(camount);
					}
					ftEntityy.setBalance(ftTotal.doubleValue());

					LeoLogger.info(
							"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance after credit amount apply  ....."
									+ ftTotal);
				}

				financialTransactionsRepo.save(ftEntityy);
				// Ft entry****

				FTEntity fttLatestt = fTRepo.findTopByCustomerIdOrderByFanIdDesc(saleenty.getMemberid());

				FTEntity fttEntityy = new FTEntity();

				fttEntityy.setInvoideId(csaleEntity.getSaleId());
				if (grandtotal <= creditamount) {
					fttEntityy.setAmount(grandtotal);
				} else {
					fttEntityy.setAmount(creditamount);
				}
				fttEntityy.setCustomerId(csaleEntity.getMemberid());
				fttEntityy.setCustomerName(csaleEntity.getMember_name());
				fttEntityy.setDate(new Date());
				fttEntityy.setDueDate(calendarInstance.getTime());
				fttEntityy.setType("CreditAmount--Payment");
				fttEntityy.setReferenceno(csaleEntity.getReferenceno());

				if (fttLatestt == null) {
					fttEntityy.setBalance(bd_grand_total.doubleValue());
				} else {
					BigDecimal fttTotal = new BigDecimal(0.0);
					BigDecimal ftrTotal = new BigDecimal(fttLatestt.getBalance());
					ftrTotal = ftrTotal.setScale(2, RoundingMode.HALF_UP);
					BigDecimal gtotal = new BigDecimal(grandtotal);
					BigDecimal camount = new BigDecimal(creditamount);

					LeoLogger.info("SaleServiceImpl---SaleServiceImpl--- ft balance before credit amount apply  ....."
							+ fttLatestt.getBalance());

					if (grandtotal <= creditamount) {
						fttTotal = ftrTotal.subtract(gtotal);
					} else {
						fttTotal = ftrTotal.subtract(camount);
					}
					fttEntityy.setBalance(fttTotal.doubleValue());
					LeoLogger.info("SaleServiceImpl---SaleServiceImpl--- ft balance after credit amount apply  ....."
							+ fttTotal);
				}

				fTRepo.save(fttEntityy);

			}

			resultVO.setMsgDescr("Sale Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}

	@Override
	public List<UserRegistrationPojo> getCustomerList() {

		List<MemberUser> memberEntityList = new ArrayList<MemberUser>();
		List<UserRegistrationPojo> customerPojoList = new ArrayList<UserRegistrationPojo>();
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String loggedInRole = "";
	        // Check if the user is authenticated
	        if (authentication != null && authentication.isAuthenticated()) {
	            // Iterate through authorities (roles) and return them
	            for (GrantedAuthority authority : authentication.getAuthorities()) {
	            	loggedInRole = authority.getAuthority();  // Return the role of the user
	            }
	        }
			LeoLogger.info("SaleServiceImpl---loggedInRole-"+loggedInRole);

		try {
			if(loggedInRole.equalsIgnoreCase("sales") || loggedInRole.equalsIgnoreCase("custom")){
				
				memberEntityList = memberUserRepo.findByCtypeNot("Special");

			}else {
				memberEntityList = memberUserRepo.findAll();

			}
			
			
			customerPojoList = mapper.map(memberEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return customerPojoList;
	}

	@Override
	public List<SalePojo> getSalesList() {

		List<SpecialSalesEntity> specialsalesEntityList = new ArrayList<SpecialSalesEntity>();
		List<SalePojo> salePojoList = new ArrayList<SalePojo>();
		List<SalesEntity> salesEntityList = new ArrayList<SalesEntity>();
		Date startDate = new Date();
		Date endDate = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---Start Date is : " + startDate);

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		endDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
		//LeoLogger.info("SaleServiceImpl---getSalesList---in Sales");

		try {

			salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
			for (SalesEntity salesEntityEntityRes : salesEntityList) {

				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityEntityRes, SalePojo.class);
				salePojoList.add(salePojo);

			}

			specialsalesEntityList = specialsalesRepo.findByDateBetweenOrderByDateDesc(startDate, endDate);
			// LeoLogger.info("SaleServiceImpl---getSalesList---specialsalesEntityList is :
			// "
			// + specialsalesEntityList.toString());
			// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
			// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " +
			// startDate);

			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

				SalePojo ssalePojo = new SalePojo();
				ssalePojo = mapper.map(specialsalesEntityRes, SalePojo.class);
				salePojoList.add(ssalePojo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salePojoList;
	}

	@Override
	public ResultVO addRequestquote(List<AddItemReqPojo> addItemReqPojos) {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("SaleServiceImpl---addRequestquote");

			RequestQuoteEntity rqentity = new RequestQuoteEntity();
			String Supplier_Email = "support@leonet.in", Supplier_Name = "";

			// Document document = new Document();

			String path = new File("").getAbsolutePath();

			// LeoLogger.info(path);

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				rqentity.setDate(new Date());
				//rqentity.setEmail(Supplier_Email);
				//Supplier_Email = additem.getEmail();
				Supplier_Name = additem.getNote();

				// LeoLogger.info("Member Pojo ....." + memberPojo.toString());

				rqentity.setReferenceno("POS");
				//rqentity.setEmail(Supplier_Email);
				rqentity.setSuppliername(Supplier_Name);

				// saleEntity.setUser_id();
			}
			VendorEntity vendorEntityList = new VendorEntity();
			vendorEntityList = vendorRepo.findByVendorName(Supplier_Name);
			rqentity.setEmail(vendorEntityList.getEmail());
			LeoLogger.info("SaleServiceImpl---vendorEntityList ==="+vendorEntityList);

			RequestQuoteEntity rqenty = requestQuoteRepo.save(rqentity);

			// PdfWriter.getInstance(document, new FileOutputStream(
			// path + "/src/main/webapp/resources/PO/PO_Supplies_Plus_" + rqenty.getRqId() +
			// ".pdf"));
			// document.open();
			// PdfPTable table = new PdfPTable(3);
			// addTableHeader(table);

			// this loop is for sales breakdown
			List<RequestQuoteItemEntity> requestquoteItemList = new ArrayList<>();
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				RequestQuoteItemEntity rqItemEntity = new RequestQuoteItemEntity();
				rqItemEntity.setRqid(rqenty.getRqId());
				rqItemEntity.setProduct_id(additem.getProductId());
				rqItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				rqItemEntity.setProduct_code(additem.getProductId().toString());
				rqItemEntity.setProduct_name(additem.getProductName());
				rqItemEntity.setMpn(productDetailsEnt.getcf1());

				rqItemEntity = requestQuoteItemRepo.save(rqItemEntity);
				requestquoteItemList.add(rqItemEntity);

				// addRows(table, rqItemEntity);

				// Make PDF and send email attachment
			}

			rqenty.setFileName(customFileUploadUtil.savePdfFileIntoDir("", requestquoteItemList, rqenty));
			requestQuoteRepo.save(rqentity);

			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("PO Raised for " + Supplier_Name, font);

			// document.add(chunk);

			// document.add(table);
			// document.close();

			MailSendingAPI api = new MailSendingAPI();
			String from = "support@leonet.in", pass = "2c;UFEvB90", cc = "moninder@leonet.in",
					subject = "Quote Request", bcc = "", body = "Dear Customer, "
							+ "\n\nAttached please find invoice for Account number r more information.\n\n\nRegards,";

			// api.MailDepartment(from, pass, Supplier_Email, cc, subject, body, document);

			resultVO.setMsgDescr("Request Quote Added Sucessfully and mail sent");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("Product Name", "MPN", "Quantity").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table, RequestQuoteItemEntity rqItemEntity) {
		table.addCell(rqItemEntity.getProduct_name());
		table.addCell(rqItemEntity.getMpn());
		table.addCell(String.valueOf(rqItemEntity.getQuantity()));
	}

	@Override
	public List<RequestQuotePojo> getRequestQuoteList() {
		List<RequestQuoteEntity> requestQuoteEntityList = new ArrayList<RequestQuoteEntity>();
		List<RequestQuotePojo> requestQuotePojoList = new ArrayList<RequestQuotePojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getRequestQuoteList---in Request Quote Listing");
			requestQuoteEntityList = requestQuoteRepo.findAllByOrderByRqIdDesc();
			for (RequestQuoteEntity rqEntityRes : requestQuoteEntityList) {

				RequestQuotePojo requestQuotePojo = new RequestQuotePojo();
				requestQuotePojo = mapper.map(rqEntityRes, RequestQuotePojo.class);
				requestQuotePojoList.add(requestQuotePojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestQuotePojoList;
	}

	@Override
	public ResultVO downloadPDF(long id) {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();
		try {

			LeoLogger.info(
					"SaleServiceImpl---downloadPDF--Downloading \'Maven, Eclipse and OSGi working together\' PDF document...");
			String path = new File("").getAbsolutePath();
			File my_file = new File(path + "/src/main/webapp/resources/PO/PO_Supplies_Plus_" + id + ".pdf");

			saveFileFromUrlWithJavaIO(path + "/src/main/webapp/resources/PO_Supplies_DOWNLOADED_" + id + ".pdf",
					my_file);

			LeoLogger.info(
					"SaleServiceImpl---downloadPDF--Downloaded \'Maven, Eclipse and OSGi working together\' PDF document.");

//			 String fileUrl = path + "/src/main/webapp/resources/PO/PO_Supplies_Plus_" + id + ".pdf";

			resultVO.setMsgDescr("Requested Quote Downloaded !");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;

	}

	// Using Java IO
	public static void saveFileFromUrlWithJavaIO(String fileName, File my_file)
			throws MalformedURLException, IOException {
		FileInputStream in = null;
		FileOutputStream fout = null;
		try {
			in = new FileInputStream(my_file);
			fout = new FileOutputStream(fileName);

			byte data[] = new byte[1024];
			int count;
			while ((count = in.read(data, 0, 1024)) != -1) {
				fout.write(data, 0, count);
			}
		} finally {
			if (in != null)
				in.close();
			if (fout != null)
				fout.close();
		}
	}

	@Override
	public ResultVO addQuotes(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("SaleServiceImpl----addQuotes");

			QuotesEntity quotesEntity = new QuotesEntity();
			double tax_rate = 0, total = 0, quantity = 0, unit_price = 0, subTotal = 0, subTax = 0;
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			BigDecimal bd_subTax = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price_change = new BigDecimal(0.0);

			MemberUser memberPojo = memberUserRepo.findById(addItemReqPojos.get(0).getCustomerId());
			UnitEntity unitentity = new UnitEntity();
		//	SalesPercentageEntity salesPercentageEntity = salespercentRepo.findByCtype(memberPojo.getCtype());
			SalesPercentageEntity salesPercentageEntity = salespercentRepo.findByCtypeAndPricegroup(memberPojo.getCtype(),memberPojo.getPricegroup());
			ProductDetailsEntity productDetailsPojo = null;

			quotesEntity.setOrder_tax(0);
			quotesEntity.setPayment_status("Due");
			quotesEntity.setOrder_discount(0);
			quotesEntity.setquotes_status("Available");
			quotesEntity.setCtype(memberPojo.getCtype());
			quotesEntity.setDate(new Date());
			quotesEntity.setMemberid(memberPojo.getId());
			quotesEntity.setMember_name(memberPojo.getName());
			quotesEntity.setCustomeraddress(memberPojo.getAddress());
			quotesEntity.setPincode(memberPojo.getPincode());
			quotesEntity.setPhonemain(memberPojo.getPhonemain());
			quotesEntity.setMembername(memberPojo.getName());
			quotesEntity.setNote(addItemReqPojos.get(0).getNote());
			

			QuotesEntity quote = quotesRepo.save(quotesEntity);

			LeoLogger.info("SaleServiceImpl---addQuotes----Member id---- " + memberPojo.getId());
			LeoLogger.info("SaleServiceImpl---addQuotes----Member type---- " + memberPojo.getCtype());
			LeoLogger.info("SaleServiceImpl---addQuotes----Member price group---- " + memberPojo.getPricegroup());
			/*LeoLogger.info(
					"SaleServiceImpl---addQuotes----Sales percentage---- " + salesPercentageEntity.getPercentage());*/

			for (AddItemReqPojo additem : addItemReqPojos) {

				productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				LeoLogger.info("SaleServiceImpl---addQuotes----Product Details---- " + productDetailsPojo);
				LeoLogger.info("SaleServiceImpl---addQuotes---additem tax-- " + additem.getTax());

				unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				LeoLogger.info("SaleServiceImpl---addQuotes----Product unit---- " + unitentity.getUnitname());

				if (memberPojo.getCtype().equalsIgnoreCase("Special")
						&& memberPojo.getPricegroup().equalsIgnoreCase("WholeSellers")) {

					LeoLogger.info(
							"SaleServiceImpl---addQuotes---Member Type is Special and price group is WholeSellers ");
					

					if ((additem.getRoll().equalsIgnoreCase("Piece")) || (additem.getRoll().equalsIgnoreCase("ft") || (additem.getRoll().equalsIgnoreCase("10Ft")) )) {
						unit_price = productDetailsPojo.getprice().doubleValue();
						LeoLogger.info("SaleServiceImpl---addQuotes---Product unit price for piece and ft---- "
								+ productDetailsPojo.getprice());
						

					} else if(additem.getRoll().equalsIgnoreCase("20Ft")) {
						unit_price = (productDetailsPojo.getprice().doubleValue());
						unit_price =unit_price *2;
						
					}else if (additem.getRoll().equalsIgnoreCase("Box12")) {
						unit_price = productDetailsPojo.getrollprice();
						LeoLogger.info("SaleServiceImpl---addQuotes---Product unit  price for box---- "
								+ productDetailsPojo.getrollprice());
						if (salesPercentageEntity.getPercentage() > 0) {
							unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
									* salesPercentageEntity.getPercentage() / 100));

							LeoLogger.info(
									"SaleServiceImpl---addQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);

						}

					} 
					else {
						unit_price = productDetailsPojo.getrollprice();
						/*LeoLogger.info("SaleServiceImpl---addQuotes---Product unit  price for wire and roll unit ---- "
								+ productDetailsPojo.getrollprice());*/
					}

					bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

				} else {
					if (salesPercentageEntity.getPercentage() > 0) {
					if ((additem.getRoll().equalsIgnoreCase("Piece")) || (additem.getRoll().equalsIgnoreCase("ft") || (additem.getRoll().equalsIgnoreCase("10Ft")) )) {

							/*LeoLogger.info("SaleServiceImpl---addQuotes---Product unit price for piece and ft---- "
									+ productDetailsPojo.getprice());*/
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---addQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);
							

						}else if(additem.getRoll().equalsIgnoreCase("20Ft")) {
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info("SaleServiceImpl---addQuotes---unit_price before---- "
									+ unit_price);
							unit_price =unit_price *2;
							LeoLogger.info("SaleServiceImpl---addQuotes---unit_price after---- "
									+ unit_price);
						}
                             else if (additem.getRoll().equalsIgnoreCase("Box12")) {

							LeoLogger.info("SaleServiceImpl---addQuotes---Product unit  price for box---- "
									+ productDetailsPojo.getrollprice());
							unit_price = productDetailsPojo.getrollprice();
							if (salesPercentageEntity.getPercentage() > 0) {
								unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
										* salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---addQuotes---Product unit  price after sale percentage  ---- "
												+ unit_price);
							}

						} else {

							LeoLogger.info(
									"SaleServiceImpl---addQuotes---Product unit  price for wire and roll unit ---- "
											+ productDetailsPojo.getrollprice());
							unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
									* salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---addQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);
						}

						bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

						if (productDetailsPojo.getpromotion() > 0) {
							if (!memberPojo.getCtype().equalsIgnoreCase("WholeSellers")) {

								LeoLogger.info("SaleServiceImpl---addQuotes---Product promotion percentage---- "
										+ productDetailsPojo.getpromotion());
								unit_price = (bd_real_unit_price.doubleValue()
										- ((bd_real_unit_price.doubleValue() * productDetailsPojo.getpromotion())
												/ 100));
								LeoLogger.info(
										"SaleServiceImpl---addQuotes---Product unit price after subtracting promotion percentage---- "
												+ unit_price);
							}
						}

						bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

					} else {
						if ((additem.getRoll().equalsIgnoreCase("Piece")) || (additem.getRoll().equalsIgnoreCase("ft") || (additem.getRoll().equalsIgnoreCase("10Ft")) )) {

							LeoLogger.info("SaleServiceImpl---addQuotes---Product unit price for piece and ft---- "
									+ productDetailsPojo.getprice());
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---addQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);
							

						} else if(additem.getRoll().equalsIgnoreCase("20Ft")) {
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info("SaleServiceImpl---converquotestToSale--20ft" + unit_price);
							unit_price =unit_price *2;
							LeoLogger.info("SaleServiceImpl---converquotestToSale--20ft after" + unit_price);
						}else if (additem.getRoll().equalsIgnoreCase("Box12")) {
							LeoLogger.info("SaleServiceImpl---converquotestToSale---unit_price box" + unit_price);
							unit_price = productDetailsPojo.getrollprice();
							if (salesPercentageEntity.getPercentage() > 0) {
								unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
										* salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---addQuotes---Product unit  price after sale percentage  ---- "
												+ unit_price);

							}

						} else {
							LeoLogger.info(
									"SaleServiceImpl---addQuotes---Product unit  price for wire and roll unit ---- "
											+ productDetailsPojo.getrollprice());
							unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
									* salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---addQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);
						}
						bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

					}
				}

				LeoLogger.info("SaleServiceImpl---addQuotes-- unit price after rounding  ....." + bd_real_unit_price);

				quantity = Long.parseLong(additem.getQuantity());

				//LeoLogger.info("SaleServiceImpl---addQuotes----Product quantity for quote---- " + quantity);

				if (additem.getIsPriceChange() == 1) {

					if (additem.getTax().equalsIgnoreCase("YES")) {
						tax_rate = tax_rate + (additem.getPrice().doubleValue() * 0.125 * quantity);
					} else {
						tax_rate = 0;
					}

				} else {

					if (additem.getTax().equalsIgnoreCase("YES")) {
						tax_rate = tax_rate + (unit_price * 0.125 * quantity);
					} else {
						tax_rate = 0;
					}

				}
				if (memberPojo.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
					tax_rate = 0;
				}
				LeoLogger.info("SaleServiceImpl---addQuotes(special)----tax_rate---- " + tax_rate);

				if (additem.getIsPriceChange() == 1) {

					LeoLogger.info("SaleServiceImpl---addQuotes----Product unit price---- " + bd_real_unit_price);

					bd_real_unit_price_change = additem.getPrice().setScale(2, RoundingMode.HALF_UP);

					LeoLogger.info("SaleServiceImpl---addQuotes----Product unit price  change to---- "
							+ bd_real_unit_price_change);

					subTotal = bd_real_unit_price_change.doubleValue() * quantity;
					total += bd_real_unit_price_change.doubleValue() * quantity;

				} else {

					subTotal = bd_real_unit_price.doubleValue() * quantity;
					total += bd_real_unit_price.doubleValue() * quantity;
				}

				bd_tax_rate = new BigDecimal(tax_rate).setScale(2, RoundingMode.HALF_UP);
				tax_rate = bd_tax_rate.doubleValue();

				bd_total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
				total = bd_total.doubleValue();

				//LeoLogger.info("SaleServiceImpl---addQuotes-- tax ...." + bd_tax_rate);
				//LeoLogger.info("SaleServiceImpl---addQuotes-- subtotal  ....." + bd_total);

				QuotesItemEntity quotesItemEntity = new QuotesItemEntity();
				quotesItemEntity.setQuotesid(quote.getQuotesId());
				quotesItemEntity.setProduct_id(additem.getProductId());
				quotesItemEntity.setItem_tax(additem.getPrice().doubleValue() * 0.125);
				quotesItemEntity.setGst("12.5");
				quotesItemEntity.setItem_discount(0d);
				quotesItemEntity.setProduct_code(additem.getProductId().toString());
				quotesItemEntity.setProduct_name(additem.getProductName());
				quotesItemEntity.setRoll(unitentity.getUnitname());
				quotesItemEntity.setSale_item_id(unitentity.getId());

				if (additem.getIsPriceChange() == 1) {
					quotesItemEntity.setReal_unit_price(bd_real_unit_price_change.doubleValue());
				} else {

					quotesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
				}

				bd_subtotal = new BigDecimal(subTotal).setScale(2, RoundingMode.HALF_UP);
				quotesItemEntity.setSubtotal(bd_subtotal.doubleValue());
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					System.out.println(qty);
					System.out.println(unit);
					qty = qty .multiply(unit) ;
					quotesItemEntity.setQuantity(qty);

				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					quotesItemEntity.setQuantity(qty);
				}

				bd_subTax = new BigDecimal(subTax).setScale(2, RoundingMode.HALF_UP);
				quotesItemEntity.setTax(Double.toString(bd_subTax.doubleValue()));

				quotesItemEntity.setIsPriceChange(additem.getIsPriceChange());

				quotesItemRepo.save(quotesItemEntity);

			}

			//LeoLogger.info("SaleServiceImpl---addQuotes--Total tax  ..." + bd_tax_rate);

			quotesEntity.setProduct_tax(bd_tax_rate.doubleValue());
			quotesEntity.setTotal_tax(bd_tax_rate.doubleValue());

			//LeoLogger.info("SaleServiceImpl---addQuotes--Total subtotal....." + bd_total);
			quotesEntity.setTotal(bd_total.doubleValue());

			bd_grand_total = bd_total.add(bd_tax_rate).setScale(2, RoundingMode.HALF_UP);
			//LeoLogger.info("SaleServiceImpl---addQuotes---Grand Total....." + bd_grand_total);
			quotesEntity.setGrandtotal(bd_grand_total.toString());

			/*
			 * for (AddItemReqPojo additem : addItemReqPojos) {
			 * 
			 * ProductDetailsEntity productDetailsEnt =
			 * productDetailsRepo.findByProductId(additem.getProductId()); if
			 * (additem.getUnit() != null && additem.getUnit() != "") { unitentity =
			 * unitRepo.findById(Long.parseLong(additem.getUnit())); }
			 * LeoLogger.info("SaleServiceImpl---addQuotes---unitentity.." + unitentity);
			 * 
			 * double tax = 0;
			 * 
			 * tax = (productDetailsEnt.getprice() * Long.parseLong(additem.getQuantity()))
			 * * .125;
			 * 
			 * QuotesItemEntity quotesItemEntity = new QuotesItemEntity();
			 * quotesItemEntity.setQuotesid(quotesenty.getQuotesId());
			 * quotesItemEntity.setProduct_id(additem.getProductId());
			 * quotesItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
			 * quotesItemEntity.setItem_tax(additem.getPrice() * 0.125);
			 * quotesItemEntity.setGst("12.5"); quotesItemEntity.setItem_discount(0d);
			 * quotesItemEntity.setProduct_code(additem.getProductId().toString());
			 * quotesItemEntity.setProduct_name(additem.getProductName());
			 * quotesItemEntity.setRoll(unitentity.getUnitname());
			 * quotesItemEntity.setSale_item_id(unitentity.getId());
			 * 
			 * bd_real_unit_price = new BigDecimal(additem.getPrice()); bd_real_unit_price =
			 * bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
			 * quotesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
			 * 
			 * bd_subtotal = new BigDecimal(additem.getSubtotal()); bd_subtotal =
			 * bd_subtotal.setScale(2, RoundingMode.HALF_UP);
			 * quotesItemEntity.setSubtotal(bd_subtotal.doubleValue()); if
			 * (!unitentity.getUnitname().equalsIgnoreCase("Piece")) { Long qty =
			 * Long.parseLong(additem.getQuantity()); Long unit = unitentity.getQuantity();
			 * System.out.println(qty); System.out.println(unit); qty = qty * unit;
			 * quotesItemEntity.setQuantity(qty);
			 * 
			 * } else { quotesItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
			 * } quotesItemEntity.setTax(Double.toString(tax));
			 * 
			 * quotesItemRepo.save(quotesItemEntity);
			 * 
			 * }
			 */

			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;

			quotesEntity.setReferenceno("QUOTES" + currentYear + "/" + currentmonth + "/" + quote.getQuotesId());
			quotesRepo.save(quotesEntity);

			resultVO.setMsgDescr("Quotes Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<QuotesPojo> getQuotesList() {
		List<QuotesEntity> quotesEntityList = new ArrayList<QuotesEntity>();
		List<QuotesPojo> quotesPojoList = new ArrayList<QuotesPojo>();
		try {
			LeoLogger.info("SaleServiceImpl--getQuotesList");
			quotesEntityList = quotesRepo.findAllByOrderByQuotesIdDesc();
			for (QuotesEntity quotesEntityEntityRes : quotesEntityList) {

				QuotesPojo quotesPojo = new QuotesPojo();
				quotesPojo = mapper.map(quotesEntityEntityRes, QuotesPojo.class);
				quotesPojoList.add(quotesPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quotesPojoList;
	}

	@Override
	public List<SaleItemPojo> getSalesItembysaleId(String saleId, boolean isFromViewSalesReceipt) {

		List<SalesItemEntity> salesItemEntityList = salesItemRepo.findBySaleidOrderByIdAsc(Long.parseLong(saleId));
		List<SaleItemPojo> salesItemPojoList = new ArrayList<SaleItemPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---in Sales get Items by saleId");

			for (SalesItemEntity salesItemEntityRes : salesItemEntityList) {
				UnitEntity unitentity = unitRepo.findById(salesItemEntityRes.getSale_item_id());

				SaleItemPojo saleItemPojo = new SaleItemPojo();
				saleItemPojo = mapper.map(salesItemEntityRes, SaleItemPojo.class);

				// LeoLogger.info("SaleServiceImpl---getSalesItembysaleId--Printing Sales
				// percentage >>>>>>>>" + salePercentage.toString());
				// LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Prices
				// before >>>>>>>>" + salesItemEntityRes.getSubtotal());

				BigDecimal bd_subtotal = new BigDecimal((salesItemEntityRes.getSubtotal() * salePercentage) / 100);
				BigDecimal bd_unitPrice = new BigDecimal(
						(salesItemEntityRes.getReal_unit_price() * salePercentage) / 100);
				BigDecimal qty = salesItemEntityRes.getQuantity();
				BigDecimal unit = (unitentity.getQuantity());

				// Long bg2 = '328';
				//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  before >>>>>>>>"+ salesItemEntityRes.getQuantity());

				if (!salesItemEntityRes.getRoll().equalsIgnoreCase("Piece")) {

					  BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);
					saleItemPojo.setQuantity(result);
				//	LeoLogger.info("SaleServiceImpl---getSalesItembysaleId--Printing result>>>>>>>>" + qty);

					//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>"+ saleItemPojo.getQuantity());
				} else {
					saleItemPojo.setQuantity(qty);
					//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>"+ saleItemPojo.getQuantity());
				}

				bd_subtotal = bd_subtotal.add(new BigDecimal(salesItemEntityRes.getSubtotal()));
				bd_unitPrice = bd_unitPrice.add(new BigDecimal(salesItemEntityRes.getReal_unit_price()));

				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				bd_unitPrice = bd_unitPrice.setScale(2, RoundingMode.HALF_UP);

				double subTotal = isFromViewSalesReceipt ? bd_subtotal.doubleValue() : salesItemEntityRes.getSubtotal();
				double unitPrice = isFromViewSalesReceipt ? bd_unitPrice.doubleValue()
						: salesItemEntityRes.getReal_unit_price();

				//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing  Prices After >>>>>>>>" + subTotal);

				// saleItemPojo = mapper.map(salesItemEntityRes, SaleItemPojo.class);
				saleItemPojo.setReal_unit_price(unitPrice);
				saleItemPojo.setSubtotal(subTotal);

				SalesEntity sale = salesRepo.findBySaleId(Long.parseLong(saleId));

				saleItemPojo.setSaleGrandTotal(sale.getGrand_total());
				saleItemPojo.setSaleSubtotal(sale.getTotal());
				saleItemPojo.setSaleTotalTax(sale.getTotal_tax());

				salesItemPojoList.add(saleItemPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	//	LeoLogger.info("SaleServiceImpl---getSalesItembysaleId--salesItemPojoList ....." + salesItemPojoList.toString());
		return salesItemPojoList;
	}

	@Override
	public List<QuotesItemPojo> getQuotesItembyquoteId(String quoteId) {
		List<QuotesItemEntity> quotesItemEntityList = quotesItemRepo.findByQuotesid(Long.parseLong(quoteId));
		List<QuotesItemPojo> quotesItemPojoList = new ArrayList<QuotesItemPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getQuotesItembyquoteId---in Quotes get Items by quoteId");

			for (QuotesItemEntity quoteItemEntityRes : quotesItemEntityList) {
				QuotesItemPojo quoteItemPojo = new QuotesItemPojo();

				double subTotal = quoteItemEntityRes.getSubtotal()
						+ ((Math.round(quoteItemEntityRes.getSubtotal() * quotePercentage)) / 100);
				double unitPrice = quoteItemEntityRes.getReal_unit_price()
						+ ((Math.round(quoteItemEntityRes.getReal_unit_price() * quotePercentage) / 100));

				quoteItemPojo = mapper.map(quoteItemEntityRes, QuotesItemPojo.class);

				quoteItemPojo.setSubtotal(subTotal);
				quoteItemPojo.setReal_unit_price(unitPrice);

				quotesItemPojoList.add(quoteItemPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LeoLogger.info(
				"SaleServiceImpl---getQuotesItembyquoteId---quotesItemPojoList ....." + quotesItemPojoList.toString());
		return quotesItemPojoList;

	}

	@Override
	public ResultVO addPayment(AddPaymentReqPojo addPaymentReqPojo) {
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("SaleServiceImpl---addPayment");

			MemberUser memberPojo = memberUserRepo.findById(addPaymentReqPojo.getMemberId());
			PaymentEntity paymentEntity = new PaymentEntity();
			SalesEntity salesEntity = new SalesEntity();
			SpecialSalesEntity specialsalesEntity = new SpecialSalesEntity();
			BigDecimal payamount = new BigDecimal(0.0);
			payamount = new BigDecimal(addPaymentReqPojo.getAmount());
			payamount = payamount.setScale(2, RoundingMode.HALF_UP);
			double origamountt = addPaymentReqPojo.getAmount();

			LeoLogger.info("SaleServiceImpl---addPayment" + payamount);
			if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Refund")) {
				// for sale
				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
					LeoLogger.info("SaleServiceImpl---Refund ...");
					List<SalesEntity> saleEntity = salesRepo.findAllByMemberid(addPaymentReqPojo.getMemberId());
					salesEntity = salesRepo.findBySaleId(addPaymentReqPojo.getSaleId());
					for (SalesEntity sale : saleEntity) {
						sale.setCreditpay(0);
						memberPojo.setDeposit(0);
						// memberPojo.setCreditpayment(0);
					}

				} else {
					LeoLogger.info("SaleServiceImpl---Refund  Special...");
					List<SpecialSalesEntity> ssaleEntity = specialsalesRepo
							.findAllByMemberid(addPaymentReqPojo.getMemberId());
					specialsalesEntity = specialsalesRepo.findBySaleId(addPaymentReqPojo.getSaleId());
					for (SpecialSalesEntity sale : ssaleEntity) {
						sale.setCreditpay(0);
						memberPojo.setDeposit(0);
						// memberPojo.setCreditpayment(0);
					}
				}
			} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Deposit")) {
				// for sale
				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
					LeoLogger.info("SaleServiceImpl---Deposit ...");
					List<SalesEntity> saleEntity = salesRepo.findAllByMemberid(addPaymentReqPojo.getMemberId());
					salesEntity = salesRepo.findBySaleId(addPaymentReqPojo.getSaleId());
					BigDecimal bd_deposit = new BigDecimal(0.0);
					double deposit = addPaymentReqPojo.getAmount();
					bd_deposit = new BigDecimal(deposit);
					bd_deposit = bd_deposit.setScale(2, RoundingMode.HALF_UP);
					BigDecimal bd_total = new BigDecimal(0.0);
					bd_total = new BigDecimal(salesEntity.getGrand_total() - salesEntity.getPaid());
					bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
					BigDecimal creditamt = new BigDecimal(0.0);
					creditamt = new BigDecimal(memberPojo.getDeposit());
					creditamt = creditamt.setScale(2, RoundingMode.HALF_UP);
					//LeoLogger.info("SaleServiceImpl---Deposit ...bd_total" + bd_total);

					for (SalesEntity sale : saleEntity) {

						if (creditamt.compareTo(bd_total) > 0) {
							LeoLogger.info("SaleServiceImpl---Deposit ...if" + bd_total);
							BigDecimal bd_saledeposit = new BigDecimal(0.0);
							double saledeposit = sale.getCreditpay() - deposit;
							bd_saledeposit = new BigDecimal(saledeposit);
							bd_saledeposit = bd_saledeposit.setScale(2, RoundingMode.HALF_UP);
							sale.setCreditpay(bd_saledeposit.doubleValue());

							paymentEntity.setGrand_total(bd_total.doubleValue());
							memberPojo.setDeposit(bd_saledeposit.doubleValue());
							// memberPojo.setCreditpayment(bd_saledeposit.doubleValue());
						} else {
							LeoLogger.info("SaleServiceImpl---Deposit ...else");
							BigDecimal bd_saledeposit = new BigDecimal(0.0);
							double saledeposit = sale.getCreditpay() - creditamt.doubleValue();
							bd_saledeposit = new BigDecimal(saledeposit);
							bd_saledeposit = bd_saledeposit.setScale(2, RoundingMode.HALF_UP);
							sale.setCreditpay(bd_saledeposit.doubleValue());

							LeoLogger.info("SaleServiceImpl---Deposit ...bd_saledeposit" + bd_saledeposit);
							paymentEntity.setGrand_total(deposit);
							memberPojo.setDeposit(bd_saledeposit.doubleValue());
							// memberPojo.setCreditpayment(bd_saledeposit.doubleValue());
						}
					}
					if (creditamt.compareTo(bd_total) > 0) {
						double paid = salesEntity.getPaid();
						BigDecimal bd_paid = new BigDecimal(0.0);
						bd_paid = new BigDecimal(paid);
						bd_paid = bd_paid.setScale(2, RoundingMode.HALF_UP);
						BigDecimal d = new BigDecimal(deposit);
						d = d.setScale(2, RoundingMode.HALF_UP);
						deposit = d.doubleValue();
						salesEntity.setPaid(bd_paid.doubleValue() + deposit);
					} else {
						double paid = salesEntity.getPaid();
						BigDecimal bd_paid = new BigDecimal(0.0);
						bd_paid = new BigDecimal(paid);
						bd_paid = bd_paid.setScale(2, RoundingMode.HALF_UP);
						salesEntity.setPaid(bd_paid.doubleValue() + creditamt.doubleValue());
					}

					if (salesEntity.getGrand_total() - salesEntity.getPaid() == 0) {
						salesEntity.setPaymentstatus("Paid");
						salesEntity.setSale_status("Paid");
					} else {
						salesEntity.setPaymentstatus("Due");
						salesEntity.setSale_status("Due");
					}
					// Entry in payment table
					paymentEntity.setMember_name(salesEntity.getMember_name());
					paymentEntity.setMember_id(salesEntity.getMemberid());
					paymentEntity.setCtype(addPaymentReqPojo.getCtype());
					paymentEntity.setRsaleId(salesEntity.getSaleId());
					paymentEntity.setPtype("Deposit Pay");
					paymentRepo.save(paymentEntity);
					Date d = new Date();
					int year = d.getYear();
					int currentYear = year + 1900;
					int currentmonth = d.getMonth() + 1;

					paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
							+ salesEntity.getSaleId() + "-" + paymentEntity.getId());
					paymentEntity.setPaymentdate(d);

					paymentEntity.setstatus("Deposit Pay");
					paymentEntity.setSalesreferenceno(salesEntity.getReferenceno());
					paymentRepo.save(paymentEntity);

				} else {
					// For special sale
					LeoLogger.info("SaleServiceImpl---Deposit  Special...");
					List<SpecialSalesEntity> ssaleEntity = specialsalesRepo
							.findAllByMemberid(addPaymentReqPojo.getMemberId());
					specialsalesEntity = specialsalesRepo.findBySaleId(addPaymentReqPojo.getSaleId());
					BigDecimal bd_deposit = new BigDecimal(0.0);
					double deposit = addPaymentReqPojo.getAmount();
					bd_deposit = new BigDecimal(deposit);
					bd_deposit = bd_deposit.setScale(2, RoundingMode.HALF_UP);
					BigDecimal bd_total = new BigDecimal(0.0);
					bd_total = new BigDecimal(specialsalesEntity.getGrand_total() - specialsalesEntity.getPaid());
					bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
					//LeoLogger.info("SaleServiceImpl---Deposit ...bd_total" + bd_total);
					BigDecimal creditamt = new BigDecimal(0.0);
					creditamt = new BigDecimal(memberPojo.getDeposit());
					creditamt = creditamt.setScale(2, RoundingMode.HALF_UP);
					//LeoLogger.info("SaleServiceImpl---Deposit ...bd_total" + bd_total);

					for (SpecialSalesEntity sale : ssaleEntity) {
						if (creditamt.compareTo(bd_total) > 0) {
							LeoLogger.info("SaleServiceImpl---Deposit ...if" + bd_total);
							BigDecimal bd_saledeposit = new BigDecimal(0.0);
							double saledeposit = sale.getCreditpay() - deposit;
							bd_saledeposit = new BigDecimal(saledeposit);
							bd_saledeposit = bd_saledeposit.setScale(2, RoundingMode.HALF_UP);
							sale.setCreditpay(bd_saledeposit.doubleValue());

							paymentEntity.setGrand_total(bd_total.doubleValue());
							memberPojo.setDeposit(bd_saledeposit.doubleValue());
							// memberPojo.setCreditpayment(bd_saledeposit.doubleValue());
						} else {
							LeoLogger.info("SaleServiceImpl---Deposit ...else");
							BigDecimal bd_saledeposit = new BigDecimal(0.0);
							double saledeposit = sale.getCreditpay() - creditamt.doubleValue();
							bd_saledeposit = new BigDecimal(saledeposit);
							bd_saledeposit = bd_saledeposit.setScale(2, RoundingMode.HALF_UP);
							sale.setCreditpay(bd_saledeposit.doubleValue());

							LeoLogger.info("SaleServiceImpl---Deposit ...bd_saledeposit" + bd_saledeposit);
							paymentEntity.setGrand_total(deposit);
							memberPojo.setDeposit(bd_saledeposit.doubleValue());
							// memberPojo.setCreditpayment(bd_saledeposit.doubleValue());
						}
					}
					if (creditamt.compareTo(bd_total) > 0) {
						double paid = specialsalesEntity.getPaid();
						BigDecimal bd_paid = new BigDecimal(0.0);
						bd_paid = new BigDecimal(paid);
						bd_paid = bd_paid.setScale(2, RoundingMode.HALF_UP);
						BigDecimal d = new BigDecimal(deposit);
						d = d.setScale(2, RoundingMode.HALF_UP);
						deposit = d.doubleValue();
						specialsalesEntity.setPaid(bd_paid.doubleValue() + deposit);
					} else {
						double paid = specialsalesEntity.getPaid();
						BigDecimal bd_paid = new BigDecimal(0.0);
						bd_paid = new BigDecimal(paid);
						bd_paid = bd_paid.setScale(2, RoundingMode.HALF_UP);
						specialsalesEntity.setPaid(bd_paid.doubleValue() + creditamt.doubleValue());
					}

					if (specialsalesEntity.getGrand_total() - specialsalesEntity.getPaid() == 0) {
						
						specialsalesEntity.setPaymentstatus("Paid");
						specialsalesEntity.setSale_status("Paid");
					} else {
					
						specialsalesEntity.setPaymentstatus("Due");
						specialsalesEntity.setSale_status("Due");
					}
					// Entry in payment table
					paymentEntity.setMember_name(specialsalesEntity.getMember_name());
					paymentEntity.setMember_id(specialsalesEntity.getMemberid());
					paymentEntity.setCtype(addPaymentReqPojo.getCtype());
					paymentEntity.setRsaleId(specialsalesEntity.getSaleId());
					paymentEntity.setPtype("Deposit Pay");
					paymentRepo.save(paymentEntity);
					Date d = new Date();
					int year = d.getYear();
					int currentYear = year + 1900;
					int currentmonth = d.getMonth() + 1;

					paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
							+ specialsalesEntity.getSaleId() + "-" + paymentEntity.getId());
					paymentEntity.setPaymentdate(d);

					paymentEntity.setstatus("Deposit Pay");
					paymentEntity.setSalesreferenceno(specialsalesEntity.getReferenceno());
					paymentRepo.save(paymentEntity);

				}

			} else {

				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
					salesEntity = salesRepo.findBySaleId(addPaymentReqPojo.getSaleId());
					double paid_total = salesEntity.getPaid();

					BigDecimal bd_paid_total = new BigDecimal(0.0);

					paid_total = paid_total + addPaymentReqPojo.getAmount();
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);

					// salesEntity.setPaid(bd_paid_total.doubleValue());
					// double balance = salesEntity.getGrand_total() - salesEntity.getPaid();
					double paid = salesEntity.getGrand_total();
					double amount = addPaymentReqPojo.getAmount();
					BigDecimal balance = new BigDecimal(0.0);
					balance = new BigDecimal(salesEntity.getGrand_total() - salesEntity.getPaid());
					balance = balance.setScale(2, RoundingMode.HALF_UP);
					LeoLogger.info("SaleServiceImpl---Original Payment amount ..." + origamountt);
					LeoLogger.info("SaleServiceImpl---balance ..." + balance);
					if (payamount.doubleValue() >= balance.doubleValue()) {

						salesEntity.setPaymentstatus("Paid");
						salesEntity.setSale_status("Paid");
						salesEntity.setPaid(paid);
						paymentEntity.setMember_id(salesEntity.getMemberid());
						paymentEntity.setMember_name(salesEntity.getMember_name());
						paymentEntity.setGrand_total(balance.doubleValue());
						origamountt = payamount.doubleValue() - balance.doubleValue();
						LeoLogger.info(
								"SaleServiceImpl---Updating Payment entity and settting Sales paid entity as fully paid..."
										+ origamountt);
						//LeoLogger.info("SaleServiceImpl---Original Payment amount left..." + origamountt);

					} else {
						paymentEntity.setGrand_total(payamount.doubleValue());
						paymentEntity.setMember_id(salesEntity.getMemberid());
						paymentEntity.setMember_name(salesEntity.getMember_name());
						balance = new BigDecimal(origamountt).add(new BigDecimal(salesEntity.getPaid()));
						balance = balance.setScale(2, RoundingMode.HALF_UP);
						salesEntity.setPaid(balance.doubleValue());
						payamount = new BigDecimal(0.0);
						/*LeoLogger.info(
								"SaleServiceImpl---Updating Payment entity and settting Sales paid entity as partially paid...");*/
						LeoLogger.info("SaleServiceImpl---Original Payment amount left..." + origamountt);

					}

					salesRepo.save(salesEntity);
					paymentEntity.setstatus("Paid");

					paymentEntity.setPtype(addPaymentReqPojo.getPtype());
					paymentEntity.setPref(addPaymentReqPojo.getPref());
					paymentEntity.setRsaleId(addPaymentReqPojo.getSaleId());
					// paymentEntity.setGrand_total(payamount.doubleValue());
					paymentEntity.setPaymentdate(new Date());
					paymentEntity.setNote(addPaymentReqPojo.getNote());
					paymentEntity.setCtype(addPaymentReqPojo.getCtype());
					paymentRepo.save(paymentEntity);
					Date d = new Date();
					int year = d.getYear();
					int currentYear = year + 1900;
					int currentmonth = d.getMonth() + 1;

					paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
							+ salesEntity.getSaleId() + "-" + paymentEntity.getId());

				}

				else {
					specialsalesEntity = specialsalesRepo.findBySaleId(addPaymentReqPojo.getSaleId());
					double paid_total = specialsalesEntity.getPaid();

					BigDecimal bd_paid_total = new BigDecimal(0.0);

					paid_total = paid_total + addPaymentReqPojo.getAmount();
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);

					// salesEntity.setPaid(bd_paid_total.doubleValue());
					// double balance = salesEntity.getGrand_total() - salesEntity.getPaid();
					double paid = specialsalesEntity.getGrand_total();
					double amount = addPaymentReqPojo.getAmount();
					BigDecimal balance = new BigDecimal(0.0);
					balance = new BigDecimal(specialsalesEntity.getGrand_total() - specialsalesEntity.getPaid());
					balance = balance.setScale(2, RoundingMode.HALF_UP);

					if (payamount.doubleValue() >= balance.doubleValue()) {

						specialsalesEntity.setPaymentstatus("Paid");
						specialsalesEntity.setSale_status("Paid");
						specialsalesEntity.setPaid(paid);
						paymentEntity.setMember_id(specialsalesEntity.getMemberid());
						paymentEntity.setMember_name(specialsalesEntity.getMember_name());
						paymentEntity.setGrand_total(balance.doubleValue());
						origamountt = origamountt - balance.doubleValue();
						LeoLogger.info(
								"SaleServiceImpl---Updating Payment entity and settting Sales paid entity as fully paid..."
										+ origamountt);
						//LeoLogger.info("SaleServiceImpl---Original Payment amount left..." + origamountt);
						LeoLogger.info("SaleServiceImpl---specialsalesEntity.setPaymentstatus..if." + specialsalesEntity.getPaymentstatus());

					} else {
						paymentEntity.setGrand_total(payamount.doubleValue());
						paymentEntity.setMember_id(specialsalesEntity.getMemberid());
						paymentEntity.setMember_name(specialsalesEntity.getMember_name());
						// specialsalesEntity.setPaid(paid);
						balance = new BigDecimal(origamountt).add(new BigDecimal(specialsalesEntity.getPaid()));
						/*LeoLogger.info("SaleServiceImpl---Original Payment specialsalesEntity.getPaid()..."
								+ specialsalesEntity.getPaid());*/

						balance = balance.setScale(2, RoundingMode.HALF_UP);
						specialsalesEntity.setPaid(balance.doubleValue());
						origamountt = 0;
						LeoLogger.info(
								"SaleServiceImpl---Updating Payment entity and settting Sales paid entity as partially paid...");
						//LeoLogger.info("SaleServiceImpl---Original Payment amount left..." + origamountt);
						LeoLogger.info("SaleServiceImpl---specialsalesEntity.setPaymentstatus..else." + specialsalesEntity.getPaymentstatus());

					}

					specialsalesRepo.save(specialsalesEntity);
					paymentEntity.setstatus("Paid");
					paymentEntity.setPtype(addPaymentReqPojo.getPtype());
					paymentEntity.setPref(addPaymentReqPojo.getPref());
					paymentEntity.setRsaleId(addPaymentReqPojo.getSaleId());
					// paymentEntity.setGrand_total(payamount.doubleValue());
					paymentEntity.setPaymentdate(new Date());
					paymentEntity.setNote(addPaymentReqPojo.getNote());
					paymentEntity.setCtype(addPaymentReqPojo.getCtype());
					paymentRepo.save(paymentEntity);
					Date d = new Date();
					int year = d.getYear();
					int currentYear = year + 1900;
					int currentmonth = d.getMonth() + 1;

					paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
							+ specialsalesEntity.getSaleId() + "-" + paymentEntity.getId());
				}
			}

			Date currentDate = new Date();

			// convert date to calendar and add 30 days
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);

			Calendar calendarInstance = Calendar.getInstance();
			calendarInstance.add(Calendar.DATE, 30);

			FinancialTransactionEntity ftLatest = new FinancialTransactionEntity();

			if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
				ftLatest = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(salesEntity.getMemberid());
			} else {
				ftLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntity.getMemberid());
			}
			FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();
			// BigDecimal payamount = new BigDecimal(0.0);
			// payamount = new BigDecimal(addPaymentReqPojo.getAmount());
			// payamount = payamount.setScale(2, RoundingMode.HALF_UP);
			BigDecimal amtt = new BigDecimal(0.0);
			amtt = new BigDecimal(paymentEntity.getGrand_total());
			amtt = amtt.setScale(2, RoundingMode.HALF_UP);
			if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
				ftEntity.setInvoideId(salesEntity.getSaleId());
				ftEntity.setCustomerId(salesEntity.getMemberid());
				ftEntity.setCustomerName(salesEntity.getMember_name());
				ftEntity.setReferenceno(salesEntity.getReferenceno());
			} else {
				ftEntity.setInvoideId(specialsalesEntity.getSaleId());
				ftEntity.setCustomerId(specialsalesEntity.getMemberid());
				ftEntity.setCustomerName(specialsalesEntity.getMember_name());
				ftEntity.setReferenceno(specialsalesEntity.getReferenceno());
			}

			// ftEntity.setInvoideId(salesEntity.getSaleId());
			if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Refund")) {
				ftEntity.setAmount(addPaymentReqPojo.getPayamount());
			} else {
				ftEntity.setAmount(paymentEntity.getGrand_total());
			}
			// ftEntity.setCustomerId(salesEntity.getMemberid());
			// ftEntity.setCustomerName(salesEntity.getMember_name());
			ftEntity.setDate(new Date());
			ftEntity.setDueDate(calendarInstance.getTime());
			if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Deposit")) {
				ftEntity.setType("Deposit Payment");
				ftEntity.setAmount(paymentEntity.getGrand_total());
			} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Refund")) {
				ftEntity.setType("CreditRefund");
			} else {
				ftEntity.setType("Payment");
				ftEntity.setAmount(paymentEntity.getGrand_total());
			}

			if (ftLatest == null) {
				ftEntity.setBalance(payamount.doubleValue());
			} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Refund")) {
				BigDecimal ftTotal = new BigDecimal(0.0);
				LeoLogger.info("SaleServiceImpl---addPayment---Latest Financial Transation balance  ....."
						+ ftLatest.getBalance());
				ftTotal = new BigDecimal(ftLatest.getBalance());
				ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
				// ftTotal = addPaymentReqPojo.getAmount() - ftLatest.getBalance();
				ftEntity.setBalance(ftTotal.doubleValue());
			} else {
				BigDecimal ftTotal = new BigDecimal(0.0);
				LeoLogger.info("SaleServiceImpl---addPayment---Latest Financial Transation balance  ....."
						+ ftLatest.getBalance());
				ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(amtt);
				ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
				// ftTotal = addPaymentReqPojo.getAmount() - ftLatest.getBalance();
				ftEntity.setBalance(ftTotal.doubleValue());
			}

			financialTransactionsRepo.save(ftEntity);

			// ************************ Financial transaction Entry ended
			// *******************

			// *******FT Entry start*****
			FTEntity fttLatest = new FTEntity();

			if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
				fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(salesEntity.getMemberid());
			} else {
				fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntity.getMemberid());
			}
			FTEntity fttEntity = new FTEntity();
			// BigDecimal payamount = new BigDecimal(0.0);
			// payamount = new BigDecimal(addPaymentReqPojo.getAmount());
			// payamount = payamount.setScale(2, RoundingMode.HALF_UP);
			BigDecimal amt = new BigDecimal(0.0);
			amt = new BigDecimal(paymentEntity.getGrand_total());
			amt = amt.setScale(2, RoundingMode.HALF_UP);

			if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
				fttEntity.setInvoideId(salesEntity.getSaleId());
				fttEntity.setCustomerId(salesEntity.getMemberid());
				fttEntity.setCustomerName(salesEntity.getMember_name());
				fttEntity.setReferenceno(salesEntity.getReferenceno());
			} else {
				fttEntity.setInvoideId(specialsalesEntity.getSaleId());
				fttEntity.setCustomerId(specialsalesEntity.getMemberid());
				fttEntity.setCustomerName(specialsalesEntity.getMember_name());
				fttEntity.setReferenceno(specialsalesEntity.getReferenceno());
			}

			// fttEntity.setInvoideId(salesEntity.getSaleId());
			if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Refund")) {
				fttEntity.setAmount(addPaymentReqPojo.getPayamount());
			} else {
				fttEntity.setAmount(paymentEntity.getGrand_total());
			}
			// fttEntity.setCustomerId(salesEntity.getMemberid());
			// fttEntity.setCustomerName(salesEntity.getMember_name());
			fttEntity.setDate(new Date());
			fttEntity.setDueDate(calendarInstance.getTime());
			if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Deposit")) {
				fttEntity.setType("Deposit Payment");
				fttEntity.setAmount(paymentEntity.getGrand_total());
			} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Refund")) {
				fttEntity.setType("CreditRefund");
			} else {
				fttEntity.setType("Payment");
				fttEntity.setAmount(paymentEntity.getGrand_total());
			}

			if (fttLatest == null) {
				fttEntity.setBalance(payamount.doubleValue());
			} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("Refund")) {
				BigDecimal fttTotal = new BigDecimal(0.0);
				LeoLogger.info("SaleServiceImpl---addPayment---Latest Financial Transation balance  ....."
						+ fttLatest.getBalance());
				LeoLogger.info("SaleServiceImpl---addPayment---Latest Financial Transation amt ....." + amt);
				fttTotal = new BigDecimal(fttLatest.getBalance());
				fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
				// ftTotal = addPaymentReqPojo.getAmount() - ftLatest.getBalance();
				fttEntity.setBalance(fttTotal.doubleValue());
			} else {
				BigDecimal fttTotal = new BigDecimal(0.0);
				LeoLogger.info("SaleServiceImpl---addPayment---Latest Financial Transation balance  ....."
						+ fttLatest.getBalance());
				LeoLogger.info("SaleServiceImpl---addPayment---Latest Financial Transation amt ....." + amt);
				fttTotal = new BigDecimal(fttLatest.getBalance()).subtract(amt);
				fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
				// ftTotal = addPaymentReqPojo.getAmount() - ftLatest.getBalance();
				fttEntity.setBalance(fttTotal.doubleValue());
				LeoLogger.info("Balance" + fttTotal);
			}

			fTRepo.save(fttEntity);
			// *******FT Entry ended*****

			/// ****Credit Payment******
			/*
			 * if(origamountt>0) {
			 * 
			 * LeoLogger.
			 * info("Making Credit payment entry for Extra Amount in financial >>> " +
			 * origamountt); BigDecimal amount = new BigDecimal(0.0); amount = new
			 * BigDecimal(origamountt); amount = amount.setScale(2, RoundingMode.HALF_UP);
			 * //MemberUser memberPojo =
			 * memberUserRepo.findById(addPaymentReqPojo.getMemberId());
			 * memberPojo.setCreditpayment(amount.doubleValue());
			 * 
			 * FinancialTransactionEntity ftLatestt = new FinancialTransactionEntity();
			 * 
			 * if (!memberPojo.getCtype().equalsIgnoreCase("Special")) { ftLatestt =
			 * financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(salesEntity.
			 * getMemberid()); } else { ftLatest = financialTransactionsRepo
			 * .findTopByCustomerIdOrderByFanIdDesc(specialsalesEntity.getMemberid()); }
			 * FinancialTransactionEntity ftEntityy = new FinancialTransactionEntity();
			 * 
			 * BigDecimal amttt = new BigDecimal(0.0); amttt = new BigDecimal(origamountt);
			 * amttt = amttt.setScale(2, RoundingMode.HALF_UP); if
			 * (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
			 * ftEntityy.setInvoideId(salesEntity.getSaleId());
			 * ftEntityy.setCustomerId(salesEntity.getMemberid());
			 * ftEntityy.setCustomerName(salesEntity.getMember_name()); }else {
			 * ftEntityy.setInvoideId(specialsalesEntity.getSaleId());
			 * ftEntityy.setCustomerId(specialsalesEntity.getMemberid());
			 * ftEntityy.setCustomerName(specialsalesEntity.getMember_name()); }
			 * 
			 * 
			 * //ftEntity.setInvoideId(salesEntity.getSaleId());
			 * ftEntityy.setAmount(amttt.doubleValue());
			 * //ftEntity.setCustomerId(salesEntity.getMemberid());
			 * //ftEntity.setCustomerName(salesEntity.getMember_name());
			 * ftEntityy.setDate(new Date());
			 * ftEntityy.setDueDate(calendarInstance.getTime());
			 * ftEntityy.setType("CreditPayment");
			 * 
			 * if (ftLatestt == null) { ftEntityy.setBalance(payamount.doubleValue()); }
			 * else { BigDecimal ftTotal = new BigDecimal(0.0); LeoLogger.
			 * info("SaleServiceImpl---addPayment---Latest Financial Transation balance  ....."
			 * + ftLatestt.getBalance()); ftTotal = new
			 * BigDecimal(ftLatestt.getBalance()).subtract(amttt); ftTotal =
			 * ftTotal.setScale(2, RoundingMode.HALF_UP); // ftTotal =
			 * addPaymentReqPojo.getAmount() - ftLatest.getBalance();
			 * ftEntityy.setBalance(ftTotal.doubleValue()); }
			 * 
			 * financialTransactionsRepo.save(ftEntityy);
			 * 
			 * 
			 * // *******FT Entry start***** FTEntity fttLatestt = new FTEntity();
			 * 
			 * if (!memberPojo.getCtype().equalsIgnoreCase("Special")) { fttLatestt =
			 * fTRepo.findTopByCustomerIdOrderByFanIdDesc(salesEntity.getMemberid()); } else
			 * { fttLatestt =
			 * fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntity.getMemberid());
			 * } FTEntity fttEntityy = new FTEntity(); // BigDecimal payamount = new
			 * BigDecimal(0.0); // payamount = new
			 * BigDecimal(addPaymentReqPojo.getAmount()); // payamount =
			 * payamount.setScale(2, RoundingMode.HALF_UP); BigDecimal aamt = new
			 * BigDecimal(0.0); aamt = new BigDecimal(amttt.doubleValue()); aamt =
			 * aamt.setScale(2, RoundingMode.HALF_UP);
			 * 
			 * if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
			 * fttEntityy.setInvoideId(salesEntity.getSaleId());
			 * fttEntityy.setCustomerId(salesEntity.getMemberid());
			 * fttEntityy.setCustomerName(salesEntity.getMember_name()); }else {
			 * fttEntityy.setInvoideId(specialsalesEntity.getSaleId());
			 * fttEntityy.setCustomerId(specialsalesEntity.getMemberid());
			 * fttEntityy.setCustomerName(specialsalesEntity.getMember_name()); }
			 * 
			 * //fttEntity.setInvoideId(salesEntity.getSaleId());
			 * fttEntityy.setAmount(amttt.doubleValue());
			 * //fttEntity.setCustomerId(salesEntity.getMemberid());
			 * //fttEntity.setCustomerName(salesEntity.getMember_name());
			 * fttEntityy.setDate(new Date());
			 * fttEntity.setDueDate(calendarInstance.getTime());
			 * fttEntityy.setType("CreditPayment");
			 * 
			 * if (fttLatest == null) { fttEntityy.setBalance(payamount.doubleValue()); }
			 * else { BigDecimal fttTotal = new BigDecimal(0.0); LeoLogger.
			 * info("SaleServiceImpl---addPayment---Latest Financial Transation balance  ....."
			 * + fttLatestt.getBalance()); LeoLogger.
			 * info("SaleServiceImpl---addPayment---Latest Financial Transation amt ....." +
			 * aamt); fttTotal = new BigDecimal(fttLatestt.getBalance()).subtract(aamt);
			 * fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP); // ftTotal =
			 * addPaymentReqPojo.getAmount() - ftLatest.getBalance();
			 * fttEntityy.setBalance(fttTotal.doubleValue()); LeoLogger.info("Balance" +
			 * fttTotal); }
			 * 
			 * fTRepo.save(fttEntityy);
			 * 
			 * }
			 */

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
			// >>>>>>>>>>>>>>>>>>>>>>>>>>

			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());
			/*
			 * BigDecimal balance = new BigDecimal(0.0); balance = new
			 * BigDecimal(salesEntity.getGrand_total() - salesEntity.getPaid()); balance =
			 * balance.setScale(2, RoundingMode.HALF_UP);
			 * LeoLogger.info("SaleServiceImpl---Original Payment amount ..."+origamountt);
			 * LeoLogger.info("SaleServiceImpl---balance ..."+balance);
			 */

			if (registerEntity != null)

			{
				LeoLogger.info("SaleServiceImpl---addPayment-- Register entry found for today  ....."
						+ registerEntity.getSalesamount());
				LeoLogger.info(
						"SaleServiceImpl---addPayment-- Register entry found for today payamount ....." + payamount);
				if (addPaymentReqPojo.getPtype().equalsIgnoreCase("cash")) {
					if (origamountt != 0) {
						LeoLogger.info("SaleServiceImpl---addPayment-- cash....");
						//LeoLogger.info("SaleServiceImpl---addPayment-- cash..origamountt.." + origamountt);
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getCashpayment() + paymentEntity.getGrand_total());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info("SaleServiceImpl---balance ..." + ftTotal);
						registerEntity.setCashpayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() + paymentEntity.getGrand_total());
					} else {
						LeoLogger.info("SaleServiceImpl---addPayment-- cash....");
						BigDecimal fftTotal = new BigDecimal(0.0);
						fftTotal = new BigDecimal(registerEntity.getCashpayment() + addPaymentReqPojo.getAmount());
						fftTotal = fftTotal.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info("SaleServiceImpl---balance ftTotal..." + fftTotal);
						registerEntity.setCashpayment(fftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() + addPaymentReqPojo.getAmount());
					}
				} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("cheque")) {
					LeoLogger.info("SaleServiceImpl---addPayment-- cheque....");
					BigDecimal ftTotal = new BigDecimal(0.0);
					ftTotal = new BigDecimal(registerEntity.getChequepayment() + addPaymentReqPojo.getAmount());
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					registerEntity.setChequepayment(ftTotal.doubleValue());
					registerEntity.setClosingbal(registerEntity.getClosingbal() + addPaymentReqPojo.getAmount());
				} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("online")) {
					LeoLogger.info("SaleServiceImpl---addPayment-- online....");
					BigDecimal ftTotal = new BigDecimal(0.0);
					ftTotal = new BigDecimal(registerEntity.getOnlinepayment() + addPaymentReqPojo.getAmount());
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					registerEntity.setOnlinepayment(ftTotal.doubleValue());
					registerEntity.setClosingbal(registerEntity.getClosingbal() + addPaymentReqPojo.getAmount());
				} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("other")) {
					LeoLogger.info("SaleServiceImpl---addPayment-- other....");
					BigDecimal ftTotal = new BigDecimal(0.0);
					ftTotal = new BigDecimal(registerEntity.getOtherpayment() + addPaymentReqPojo.getAmount());
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					registerEntity.setOtherpayment(ftTotal.doubleValue());
					// registerEntity.setClosingbal(registerEntity.getClosingbal() +
					// addPaymentReqPojo.getAmount());
				} else {
					BigDecimal ftTotal = new BigDecimal(0.0);
					ftTotal = new BigDecimal(registerEntity.getCreditcardpayment() + addPaymentReqPojo.getAmount());
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					registerEntity.setCreditcardpayment(ftTotal.doubleValue());
					registerEntity.setClosingbal(registerEntity.getClosingbal() + addPaymentReqPojo.getAmount());
				}
				registerRepo.save(registerEntity);
			} else {
				RegisterEntity newregisterEntity = new RegisterEntity();
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				if (addPaymentReqPojo.getPtype().equalsIgnoreCase("cash")) {
					newregisterEntity.setCashpayment(addPaymentReqPojo.getAmount());
				} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("cheque")) {
					newregisterEntity.setChequepayment(addPaymentReqPojo.getAmount());
				} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("online")) {
					newregisterEntity.setOnlinepayment(addPaymentReqPojo.getAmount());
				} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("other")) {
					newregisterEntity.setOtherpayment(addPaymentReqPojo.getAmount());
				} else {
					newregisterEntity.setCreditcardpayment(addPaymentReqPojo.getAmount());
				}
				newregisterEntity.setOpeningbal(1000.00);
				if (!addPaymentReqPojo.getPtype().equalsIgnoreCase("other"))
					newregisterEntity.setClosingbal(1000.00 + addPaymentReqPojo.getAmount());
				newregisterEntity.setRefunds(0.00);
				newregisterEntity.setReferenceno(String.valueOf(salesEntity.getSaleId()));
				newregisterEntity.setSalesamount(0.0);
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);

				LeoLogger.info("SaleServiceImpl---addPayment--- New Register Entry made");

			}

			// **************************** Register Details Entry ended
			// ********************
			// *****Rgister history entry****//
			applyRegisterhistorypayment(paymentEntity);

			resultVO.setMsgDescr("Payment Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}

	@Override
	public ResultVO addSpecialSale(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("SaleServiceImpl---addSpecialSale");

			SpecialSalesEntity specialsaleEntity = new SpecialSalesEntity();
			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			String Ctype = "";
			double creditamount = 0.0;

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				specialsaleEntity.setDate(new Date());
				specialsaleEntity.setMemberid(memberPojo.getId());
				specialsaleEntity.setMember_name(memberPojo.getName());
				specialsaleEntity.setCustomeraddress(memberPojo.getAddress());
				specialsaleEntity.setPhonemain(memberPojo.getPhonemain());
				specialsaleEntity.setPincode(memberPojo.getPincode());
				specialsaleEntity.setMembername(memberPojo.getName());

				// LeoLogger.info("SaleServiceImpl---addSpecialSale---Member Pojo ....." +
				// memberPojo.toString());

				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				if (productDetailsPojo != null)
					unit_price = additem.getPrice().doubleValue();
				quantity = Integer.parseInt(additem.getQuantity());

				/*
				 * This is Promotional discount on Product not applicable to WholeSellers
				 * if(!Ctype.equalsIgnoreCase("WholeSellers") &&
				 * productDetailsPojo.getpromotion() !=0 ) {
				 * 
				 * unit_price = unit_price -
				 * ((unit_price*productDetailsPojo.getpromotion())/100);
				 * LeoLogger.info("Applying Promotion for " + productDetailsPojo.getpromotion()
				 * + "% and the new Unit price now is ....." + unit_price);
				 * 
				 * }
				 */

				specialsaleEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);

				// LeoLogger.info("SaleServiceImpl---addSpecialSale---TAX Rate is...." +
				// additem.getTax());

				// if (additem.getTax().equalsIgnoreCase("YES")) {
				// tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				// } else {
				//tax_rate = 0;
				// }
				// saleEntity.setReferenceno("SALE"+currentYear+"/"+currentmonth+"/"+
				// additem.getProductId());
			
				 if (additem.getTax().equalsIgnoreCase("YES")) { 
					 tax_rate = tax_rate +(unit_price * 0.125 * quantity);
					 } else {
						 tax_rate = 0; 
						 }
				

				specialsaleEntity.setTotal_discount(0);
				Ctype = memberPojo.getCtype();

				// specialsaleEntity.setCf1(productDetailsPojo.getcf1());

				// saleEntity.setUser_id();
			}

			grand_total = total + tax_rate;
			specialsaleEntity.setOrder_tax(0);

			bd_tax_rate = new BigDecimal(tax_rate);
			bd_tax_rate = bd_tax_rate.setScale(2, RoundingMode.HALF_UP);
			specialsaleEntity.setProduct_tax(bd_tax_rate.doubleValue());
			specialsaleEntity.setTotal_tax(bd_tax_rate.doubleValue());
			LeoLogger.info("SaleServiceImpl---addSpecialSale---Tax Rate....." + bd_tax_rate);

			specialsaleEntity.setPaymentstatus("Due");
			specialsaleEntity.setOrder_discount(0);
			specialsaleEntity.setCtype(Ctype);

			bd_total = new BigDecimal(total);
			bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
			specialsaleEntity.setTotal(bd_total.doubleValue());
			LeoLogger.info("SaleServiceImpl---addSpecialSale---Total....." + bd_total);

			bd_grand_total = new BigDecimal(grand_total);
			bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
			specialsaleEntity.setGrand_total(bd_grand_total.doubleValue());
			LeoLogger.info("SaleServiceImpl---addSpecialSale---Grand Total....." + bd_grand_total);
			specialsaleEntity.setIsActive(0);

			SpecialSalesEntity specialsaleenty = specialsalesRepo.save(specialsaleEntity);
			UnitEntity unitentity = new UnitEntity();
			List<SpecialSalesItemEntity> saleItemList = new ArrayList<>();
			// this loop is for sales breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				specialsaleEntity.setPurchaseorder(additem.getPurchaseorder());
				specialsalesRepo.save(specialsaleEntity);

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				if (additem.getUnit() != null && additem.getUnit() != "") {
					unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				}

				BigDecimal price = productDetailsEnt.getprice();
				long qt = Long.parseLong(additem.getQuantity());

				BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(qt));
				BigDecimal tax = totalPrice.multiply(BigDecimal.valueOf(0.125));

				SpecialSalesItemEntity specialsalesItemEntity = new SpecialSalesItemEntity();
				specialsalesItemEntity.setSaleid(specialsaleenty.getSaleId());
				specialsalesItemEntity.setProduct_id(additem.getProductId());
				specialsalesItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
				specialsalesItemEntity.setItem_tax(additem.getPrice().doubleValue() * 0.125);
				specialsalesItemEntity.setGst("12.5");
				specialsalesItemEntity.setItem_discount(0);
				specialsalesItemEntity.setProduct_code(additem.getProductId().toString());
				specialsalesItemEntity.setProduct_name(additem.getProductName());
				specialsalesItemEntity.setRoll(additem.getRoll());
				bd_real_unit_price = additem.getPrice();
				bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
				specialsalesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
				specialsalesItemEntity.setCost(productDetailsEnt.getcost());

				bd_subtotal = additem.getSubtotal();
				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				specialsalesItemEntity.setSubtotal(bd_subtotal.doubleValue());
				specialsalesItemEntity.setSale_item_id(unitentity.getId());
				specialsalesItemEntity.setReturnqty("0");

				specialsalesItemEntity.setTax((tax).toString());
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece")&& !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					// LeoLogger.info(""+unitentity.getQuantity());
					BigDecimal unit =( unitentity.getQuantity());
					System.out.println(qty);
					System.out.println(unit);
					qty = qty .multiply(unit) ;
					specialsalesItemEntity.setQuantity(qty);
					// LeoLogger.info("SaleServiceImpl---addSale--qt....." +qty);
				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					specialsalesItemEntity.setQuantity(qty);
				}

				specialsalesItemRepo.save(specialsalesItemEntity);
				saleItemList.add(specialsalesItemEntity);

				// Subtracting Quantities here from products
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					System.out.println(qty);
					qty = qty .multiply(unit) ;
					System.out.println(unit);
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity()  .subtract(qty));
				}
					else {
						BigDecimal qty = new BigDecimal(additem.getQuantity());
					productDetailsEnt
							.setQuantity(productDetailsEnt.getQuantity().subtract(qty));
					}
				

				productDetailsRepo.save(productDetailsEnt);
			}
			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;

			specialsaleEntity.setReferenceno(
					"SALE" + currentYear + "/" + currentmonth + "/" + specialsaleenty.getSaleId() + "*");
			specialsaleEntity
					.setFileName(customFileUploadUtil.saveSpecialPdfFileIntoDir(saleItemList, specialsaleenty));
			specialsalesRepo.save(specialsaleEntity);

			// <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

			// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = new Date();

			// convert date to calendar and add 30 days
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);

			Calendar calendarInstance = Calendar.getInstance();
			calendarInstance.add(Calendar.DATE, 30);

			FinancialTransactionEntity ftLatest = financialTransactionsRepo
					.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());

			FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

			ftEntity.setInvoideId(specialsaleEntity.getSaleId());
			ftEntity.setAmount(bd_grand_total.doubleValue());
			ftEntity.setCustomerId(specialsaleEntity.getMemberid());
			ftEntity.setCustomerName(specialsaleEntity.getMember_name());
			ftEntity.setDate(new Date());
			ftEntity.setDueDate(calendarInstance.getTime());
			ftEntity.setType("Invoice");
			ftEntity.setReferenceno(specialsaleEntity.getReferenceno());

			if (ftLatest == null) {
				ftEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal ftTotal = new BigDecimal(0.0);
				LeoLogger.info("SaleServiceImpl---addSpecialSale----Latest Financial Transation balance  ....."
						+ ftLatest.getBalance());
				ftTotal = bd_grand_total.add(new BigDecimal(ftLatest.getBalance()));
				ftEntity.setBalance(ftTotal.doubleValue());
			}

			financialTransactionsRepo.save(ftEntity);

			// ************************ Financial transaction Entry ended
			// *******************

			// *******FT Entry start*****
			FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());

			FTEntity fttEntity = new FTEntity();

			fttEntity.setInvoideId(specialsaleEntity.getSaleId());
			fttEntity.setAmount(bd_grand_total.doubleValue());
			fttEntity.setCustomerId(specialsaleEntity.getMemberid());
			fttEntity.setCustomerName(specialsaleEntity.getMember_name());
			fttEntity.setDate(new Date());
			fttEntity.setDueDate(calendarInstance.getTime());
			fttEntity.setType("Invoice");
			fttEntity.setReferenceno(specialsaleEntity.getReferenceno());

			if (fttLatest == null) {
				fttEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal fttTotal = new BigDecimal(0.0);
				LeoLogger.info("SaleServiceImpl---addSpecialSale----Latest Financial Transation balance  ....."
						+ fttLatest.getBalance());
				fttTotal = bd_grand_total.add(new BigDecimal(fttLatest.getBalance()));
				fttEntity.setBalance(fttTotal.doubleValue());
			}

			fTRepo.save(fttEntity);
			// *******FT Entry ended*****

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
			// >>>>>>>>>>>>>>>>>>>>>>>>>>

		

			RegisterEntity registerEntity = new RegisterEntity();
			List<RegisterEntity> entities = registerRepo.findAllByOrderByIdDesc();
			

			registerEntity = registerRepo.findAByDate(new Date());
			LeoLogger.info("SaleServiceImpl---addSpecialSale---perivous registerEntity ....." + registerEntity);

			if (registerEntity != null) {
				registerEntity = entities.get(0);
				LeoLogger.info("SaleServiceImpl---addSpecialSale---registerEntity ....." + registerEntity);
			    LeoLogger.info("SaleServiceImpl---addSpecialSale---before Register entry found for today ....." + registerEntity.getSalesamount());

			 
				registerEntity.setSalesamount(registerEntity.getSalesamount() + bd_grand_total.doubleValue());
				registerEntity.setReferenceno(registerEntity.getReferenceno() + "," + specialsaleEntity.getSaleId());
				registerRepo.save(registerEntity);
				LeoLogger.info("SaleServiceImpl---addSpecialSale---after Register entry found for today ....." + registerEntity.getSalesamount());
			} else {
				RegisterEntity newregisterEntity = new RegisterEntity();
				LeoLogger.info("SaleServiceImpl---addSpecialSale---before  New Register Entry made ....." + newregisterEntity.getSalesamount());
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				newregisterEntity.setCashpayment(0.00);
				newregisterEntity.setCreditcardpayment(0.00);
				newregisterEntity.setOpeningbal(1000.00);
				newregisterEntity.setClosingbal(1000.00);
				newregisterEntity.setChequepayment(0.00);
				newregisterEntity.setOnlinepayment(0.0);
				newregisterEntity.setRefunds(0.00);
				newregisterEntity.setReferenceno(String.valueOf(specialsaleEntity.getSaleId()));
				newregisterEntity.setSalesamount(bd_grand_total.doubleValue());
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);

				LeoLogger.info("SaleServiceImpl---addSpecialSale---after  New Register Entry made ....." + newregisterEntity.getSalesamount());

			}

			// **************************** Register Details Entry ended
			// ********************

			// ***Registery history Entry***
			applyRegisterhistoryspecial(specialsaleEntity);

			// *****To check credit amount is there***
			for (AddItemReqPojo additem : addItemReqPojos) {
				// LeoLogger.info("SaleServiceImpl--- ....." + additem);
				PaymentEntity paymentEntity = new PaymentEntity();

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				creditamount = memberPojo.getCreditpayment();
				LeoLogger.info("****creditamount" + creditamount);

				if (creditamount != 0.0) {
					LeoLogger.info("Customer has a credit amount");
					SpecialSalesEntity csaleEntity = specialsalesRepo.findBySaleId(specialsaleEntity.getSaleId());
					LeoLogger.info("****csaleEntity" + csaleEntity.getReferenceno());
					double grandtotal = csaleEntity.getGrand_total();
					// **credit amount to be changed**
					if (grandtotal < creditamount) {
						memberPojo.setCreditpayment(creditamount - grandtotal);
					} else {
						memberPojo.setCreditpayment(0);
					}
					memberUserRepo.save(memberPojo);
					if (grandtotal < creditamount) {
					//	LeoLogger.info("****If condition creditamount" + creditamount);
					//	LeoLogger.info("****If condition creditamount" + grandtotal);
						csaleEntity.setPaid(grandtotal);
						csaleEntity.setPaymentstatus("Paid");
						paymentEntity.setGrand_total(grandtotal);
						specialsalesRepo.save(csaleEntity);

					} else {
						//LeoLogger.info("****else condition creditamount" + creditamount);
						//LeoLogger.info("****else condition creditamount" + grandtotal);
						csaleEntity.setPaid(creditamount);
						csaleEntity.setPaymentstatus("Due");
						paymentEntity.setGrand_total(creditamount);
						specialsalesRepo.save(csaleEntity);
					}
					paymentEntity.setMember_name(csaleEntity.getMembername());
					paymentEntity.setMember_id(csaleEntity.getMemberid());
					paymentEntity.setPtype("CA");
					paymentEntity.setRsaleId(csaleEntity.getSaleId());
					paymentEntity.setSalesreferenceno(csaleEntity.getReferenceno());
					paymentEntity.setstatus("CreditAmount");
					paymentRepo.save(paymentEntity);
					paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
							+ csaleEntity.getSaleId() + "-" + paymentEntity.getId());
					paymentEntity.setPaymentdate(d);
					paymentRepo.save(paymentEntity);
					// ***FinancialTransaction Entry***

					FinancialTransactionEntity ftLatestt = financialTransactionsRepo
							.findTopByCustomerIdOrderByFanIdDesc(csaleEntity.getMemberid());

					LeoLogger.info(
							"SaleServiceImpl---addspecialSale-crediitamount--Latest Financial Transation balance  ....."
									+ ftLatestt.getBalance());
					FinancialTransactionEntity ftEntityy = new FinancialTransactionEntity();

					ftEntityy.setInvoideId(csaleEntity.getSaleId());
					if (grandtotal < creditamount) {
						ftEntityy.setAmount(grandtotal);
					} else {
						ftEntityy.setAmount(creditamount);
					}
					ftEntityy.setCustomerId(csaleEntity.getMemberid());
					ftEntityy.setCustomerName(csaleEntity.getMember_name());
					ftEntityy.setDate(new Date());
					ftEntityy.setDueDate(calendarInstance.getTime());
					ftEntityy.setType("CreditAmount--Payment");
					ftEntityy.setReferenceno(csaleEntity.getReferenceno());

					if (ftLatestt == null) {
						ftEntityy.setBalance(bd_grand_total.doubleValue());
					} else {
						BigDecimal ftTotal = new BigDecimal(0.0);
						BigDecimal fttTotal = new BigDecimal(ftLatestt.getBalance());
						fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
						BigDecimal gtotal = new BigDecimal(grandtotal);
						BigDecimal camount = new BigDecimal(creditamount);
						LeoLogger.info(
								"SaleServiceImpl--addspecialSales--Creditamount--Latest Financial Transation balance  ....."
										+ bd_grand_total);
						LeoLogger.info("SaleServiceImpl---addspecialSales---Latest Financial Transation balance  ....."
								+ ftLatest.getBalance());
						if (grandtotal < creditamount) {
							ftTotal = gtotal.subtract(fttTotal);
						} else {
							ftTotal = camount.subtract(fttTotal);
						}
						ftEntityy.setBalance(ftTotal.doubleValue());
					}

					financialTransactionsRepo.save(ftEntityy);
					// Ft entry****

					FTEntity fttLatestt = fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsaleenty.getMemberid());
					LeoLogger.info(
							"SaleServiceImpl---addspecialSale-crediitamount--Latest Financial Transation balance  ....."
									+ fttLatest.getBalance());

					FTEntity fttEntityy = new FTEntity();

					fttEntityy.setInvoideId(csaleEntity.getSaleId());
					if (grandtotal < creditamount) {
						fttEntityy.setAmount(grandtotal);
					} else {
						fttEntityy.setAmount(creditamount);
					}
					fttEntityy.setCustomerId(csaleEntity.getMemberid());
					fttEntityy.setCustomerName(csaleEntity.getMember_name());
					fttEntityy.setDate(new Date());
					fttEntityy.setDueDate(calendarInstance.getTime());
					fttEntityy.setType("CreditAmount--Payment");
					fttEntityy.setReferenceno(csaleEntity.getReferenceno());

					if (fttLatestt == null) {
						fttEntityy.setBalance(bd_grand_total.doubleValue());
					} else {
						BigDecimal fttTotal = new BigDecimal(0.0);
						BigDecimal ftrTotal = new BigDecimal(fttLatestt.getBalance());
						ftrTotal = ftrTotal.setScale(2, RoundingMode.HALF_UP);
						BigDecimal gtotal = new BigDecimal(grandtotal);
						BigDecimal camount = new BigDecimal(creditamount);
						LeoLogger.info(
								"SaleServiceImpl--creditamount-addspecialSale---Latest Financial Transation balance  ....."
										+ bd_grand_total);
						LeoLogger.info("SaleServiceImpl---addspecialSale---Latest Financial Transation balance  ....."
								+ fttLatest.getBalance());
						if (grandtotal < creditamount) {
							fttTotal = gtotal.subtract(ftrTotal);
						} else {
							fttTotal = camount.subtract(ftrTotal);
						}
						fttEntityy.setBalance(fttTotal.doubleValue());
					}

					fTRepo.save(fttEntityy);

				}
			}

			resultVO.setMsgDescr(" Special Sale Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<SpecialSalesPojo> getsaleslist() {

		List<SpecialSalesEntity> specialsalesEntityList = new ArrayList<SpecialSalesEntity>();
		List<SpecialSalesPojo> specialsalesPojoList = new ArrayList<SpecialSalesPojo>();

		// LeoLogger.info("SaleServiceImpl---getsaleslist---specialsalesEntityList is :
		// " + specialsalesEntityList.toString());
		specialsalesEntityList = specialsalesRepo.findAllByOrderBySaleIdDesc();

		try {
			LeoLogger.info("SaleServiceImpl--getsaleslist----in  specialSales");
			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

				SpecialSalesPojo specialsalesPojo = new SpecialSalesPojo();
				specialsalesPojo = mapper.map(specialsalesEntityRes, SpecialSalesPojo.class);
				specialsalesPojoList.add(specialsalesPojo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return specialsalesPojoList;
	}

	@Override
	public List<SpecialSalesItemPojo> getSpecialSalesItembysaleId(String saleId) {
		List<SpecialSalesItemEntity> specialsalesItemEntityList = specialsalesItemRepo
				.findBySaleid(Long.parseLong(saleId));
		List<SpecialSalesItemPojo> specialsalesItemPojoList = new ArrayList<SpecialSalesItemPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getSpecialSalesItembysaleId---in Sales get Items by saleId");

			for (SpecialSalesItemEntity specialsalesItemEntityRes : specialsalesItemEntityList) {
				SpecialSalesItemPojo specialsalesItemPojo = new SpecialSalesItemPojo();
				specialsalesItemPojo = mapper.map(specialsalesItemEntityRes, SpecialSalesItemPojo.class);
				specialsalesItemPojoList.add(specialsalesItemPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// LeoLogger.info("SaleServiceImpl---getSpecialSalesItembysaleId---specialsalesItemPojoList
		// ....."
		// + specialsalesItemPojoList.toString());
		return specialsalesItemPojoList;
	}

	@Override
	public List<FinancialTransactionEntity> getFtByCustomerId(long memberId) {
		List<FinancialTransactionEntity> ftList = financialTransactionsRepo.findByCustomerId(memberId);
		return ftList;
	}

	@Override
	public List<RequestQuoteItemEntity> getrqByCustomerId(long memberId) {
		List<RequestQuoteItemEntity> rqList = requestQuoteItemRepo.findByid(memberId);
		return rqList;
	}

	@Override
	public List<SalesEntity> getSalesItembymemberId(long memberId) {
		List<SalesEntity> salesList = salesRepo.findAllByMemberid(memberId);
		return salesList;
	}

	@Override
	public List<SalePojo> getSalesBymemberId(String memberId) {
		List<SalesEntity> salesEntityList = salesRepo.findAllByMemberid(Long.parseLong(memberId));
		List<SalePojo> salesPojoList = new ArrayList<SalePojo>();
		try {
			// LeoLogger.info("SaleServiceImpl---getSalesBymemberId--in Sales get Items by
			// saleId");

			for (SalesEntity salesEntityRes : salesEntityList) {
				if(salesEntityRes.getIsActive()==0) {
				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityRes, SalePojo.class);
				salesPojoList.add(salePojo);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// LeoLogger.info("SaleServiceImpl---getSalesBymemberId---salesItemPojoList
		// ....." + salesPojoList.toString());
		return salesPojoList;
	}

	@Override
	public double caltoatl(long memberId) {
		List<SalesEntity> salesEntity = salesRepo.findAllByMemberid(memberId);
		double grand_total = 0.0;

		if (salesEntity != null) {
			for (SalesEntity salesEnt : salesEntity)
				if (salesEnt.getIsActive() == 0)
					grand_total = grand_total + salesEnt.getGrand_total();

		}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.6f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();
	}

	@Override
	public List<SalePojo> getSalesListbyMemberId(long memberId) {
		LeoLogger.info("SaleServiceImpl---getSalesListbyMemberId---in  getSalesListbyMemberId" + memberId);
		List<SalesEntity> salesEntityList = salesRepo.findAllByMemberidOrderBySaleId(memberId);
		// List<SalesEntity> salesEntityList =
		// salesRepo.findAllByMemberidAndPaymentstatusOrderBySaleId(memberId,"Due");
		List<SalePojo> salesPojoList = new ArrayList<SalePojo>();
		// salesEntityList = salesRepo.findAllOrderBySaleIdDesc();
		try {
			LeoLogger.info("SaleServiceImpl---getSalesListbyMemberId---in  getSalesListbyMemberId");
			// salesEntityList = salesRepo.findAllByMemberid(memberId);

			for (SalesEntity salesEntityRes : salesEntityList) {
				// if(salesEntityRes.equals("Paid")) {
				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityRes, SalePojo.class);
				salesPojoList.add(salePojo);
				// }

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesPojoList;
	}

	@Override
	public ResultVO addBulkPayment(AddPaymentReqPojo addPaymentReqPojo) {
		ResultVO resultVO = new ResultVO();

		try {

			// here we need to implement logic , get all pending sales from oldest to newest
			// and the iterate and start deducting money and adding payment

			double bulkamount = addPaymentReqPojo.getAmount();
			BigDecimal ftbulkamount = new BigDecimal(0.0);
			BigDecimal amount = new BigDecimal(bulkamount);
			BigDecimal amountt = amount.setScale(2, RoundingMode.HALF_UP);
			bulkamount = amountt.doubleValue();
			BulkPaymentEntity bulkPaymentEntity = new BulkPaymentEntity();

			MemberUser memberPojo = memberUserRepo.findById(addPaymentReqPojo.getMemberId());
			LeoLogger.info("SaleServiceImpl---MemberUse" + memberPojo);

			// ********** Enter Bulk Payment *******************
			bulkPaymentEntity.setAmount(amountt.doubleValue());
			bulkPaymentEntity.setDate(new Date());
			bulkPaymentEntity.setMemberId(addPaymentReqPojo.getMemberId());
			bulkPaymentEntity.setMemberName(memberPojo.getName());
			bulkPaymentEntity.setPref(addPaymentReqPojo.getPref());
			bulkPaymentEntity.setPtype(addPaymentReqPojo.getPtype());
			bulkPaymentEntity.setNote(addPaymentReqPojo.getNote());
			// bulkPaymentEntity.setPaymentid(pdfPath);
			bulkPaymentEntity.setStatus("Paid");

			BulkPaymentEntity bkpent = bulkPaymentRepo.save(bulkPaymentEntity);

			if (!addPaymentReqPojo.getCtype().equalsIgnoreCase("Special")) {

				List<SalesEntity> salesEntityList = salesRepo
						.findAllByMemberidAndPaymentstatusAndIsActiveOrderBySaleIdAsc(addPaymentReqPojo.getMemberId(),
								"Due", 0);
				// List<SalesEntity> salesEntityList = salesRepo
				// .findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(addPaymentReqPojo.getMemberId(),
				// "Due");
				/*LeoLogger.info("SaleServiceImpl---addBulkPayment--Sales list in ascending order "
						+ salesEntityList.toString());*/

				// ********** Enter Payment as payment *******************

				LeoLogger.info("SaleServiceImpl---addBulkPayment--Bulk amount is [" + bulkamount + "]");
				BigDecimal db_paidamount = new BigDecimal(0.0);

				for (SalesEntity salesEntityRes : salesEntityList) {

					double pendingamount = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
					LeoLogger.info("SaleServiceImpl---addBulkPayment---Pending amount is [" + pendingamount + "]");

					if (bulkamount >= pendingamount && bulkamount != 0)

					{
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Bulk amount is [" + bulkamount
								+ "] and the remaining amount for invoice [" + salesEntityRes.getSaleId() + "] is ["
								+ (salesEntityRes.getGrand_total() - salesEntityRes.getPaid()) + "]");

						// Addjust Sales entity paid value

						double paidamount = 0.0;
						paidamount = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
						db_paidamount = new BigDecimal(paidamount);
						db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info("SaleServiceImpl---addBulkPayment---db_paidamount is [" + db_paidamount + "]");

						salesEntityRes.setPaid(salesEntityRes.getGrand_total());
						salesEntityRes.setPaymentstatus("Paid");
						salesRepo.save(salesEntityRes);

						// ********** Enter Financial transaction *******************

						FinancialTransactionEntity ftLatest = financialTransactionsRepo
								.findTopByCustomerIdOrderByFanIdDesc(salesEntityRes.getMemberid());

						FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

						ftEntity.setInvoideId(salesEntityRes.getSaleId());
						ftEntity.setAmount(db_paidamount.doubleValue());
						ftEntity.setCustomerId(salesEntityRes.getMemberid());
						ftEntity.setCustomerName(salesEntityRes.getMember_name());
						ftEntity.setDate(new Date());
						ftEntity.setType("BulkPayment");

						if (ftLatest == null) {
							ftEntity.setBalance(paidamount);
						} else {
							BigDecimal ftTotal = new BigDecimal(paidamount);
							LeoLogger.info(
									"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
											+ ftLatest.getBalance());
							ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
							ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
							ftEntity.setBalance(ftTotal.doubleValue());
						}
						financialTransactionsRepo.save(ftEntity);

						// ********** Financial transaction Added *******************

						// *******FT Start******

						ftbulkamount = ftbulkamount.add(db_paidamount);

						// ******FT ended******

						// ********** Enter Payment with bulk id *******************

						PaymentEntity paymentEntity = new PaymentEntity();
						if (bulkamount >= salesEntityRes.getPaid()) {
							paymentEntity.setstatus("Paid");
						} else {
							
							paymentEntity.setstatus("Partial");
						}

						paymentEntity.setMember_id(salesEntityRes.getMemberid());
						paymentEntity.setMember_name(salesEntityRes.getMember_name());
						paymentEntity.setRsaleId(salesEntityRes.getSaleId());
						paymentEntity.setGrand_total(db_paidamount.doubleValue());
						paymentEntity.setPaymentdate(new Date());
						paymentEntity.setBulkid(bkpent.getBulkId());
						paymentEntity.setPref(addPaymentReqPojo.getPref());
						paymentEntity.setPtype(addPaymentReqPojo.getPtype());
						paymentEntity.setNote(addPaymentReqPojo.getNote());
						paymentEntity.setCtype(salesEntityRes.getCtype());
						paymentEntity.setSalesreferenceno(salesEntityRes.getReferenceno());
						Date d = new Date();
						int year = d.getYear();
						int currentYear = year + 1900;
						int currentmonth = d.getMonth() + 1;

						paymentEntity.setReferenceno(
								"BulkPayment" + currentYear + "/" + currentmonth + "/" + ftEntity.getInvoideId());
						paymentRepo.save(paymentEntity);
						

						// ********** Payment Added *******************

						bulkamount = bulkamount - paidamount;
						BigDecimal bulkamountt = new BigDecimal(bulkamount);
						bulkamountt = bulkamountt.setScale(3, RoundingMode.HALF_UP);
						BigDecimal finalbulkamount = bulkamountt.setScale(2, RoundingMode.HALF_UP);
						bulkamount=finalbulkamount.doubleValue();
					
						
						LeoLogger.info("SaleServiceImpl---addBulkPayment---bulkamount.(1)...." + bulkamount);
						bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
						bulkPaymentRepo.save(bulkPaymentEntity);

					} else {
						if (bulkamount != 0) {
							//LeoLogger.info("SaleServiceImpl---addBulkPayment---bulkamount.(2)...." + bulkamount);

							BigDecimal bamount = new BigDecimal(bulkamount);
							bamount = bamount.setScale(3, RoundingMode.HALF_UP);
							BigDecimal bamountt = bamount.setScale(2, RoundingMode.HALF_UP);

							//LeoLogger.info("SaleServiceImpl---addBulkPayment---bulkamount.(3)...." + bamountt);

							BigDecimal setpayamount = new BigDecimal(bulkamount);
							setpayamount = setpayamount.add(new BigDecimal(salesEntityRes.getPaid()));
							setpayamount = setpayamount.setScale(2, RoundingMode.HALF_UP);

							salesEntityRes.setPaid(setpayamount.doubleValue());

							if (bamountt
									.doubleValue() == (salesEntityRes.getGrand_total() - salesEntityRes.getPaid())) {
								salesEntityRes.setPaymentstatus("Paid");
							}
							// ********** Enter Financial transaction as payment *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(salesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(salesEntityRes.getSaleId());
							ftEntity.setAmount(bulkamount);
							ftEntity.setCustomerId(salesEntityRes.getMemberid());
							ftEntity.setCustomerName(salesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("BulkPayment");
							if (ftLatest == null) {
								ftEntity.setBalance(bulkamount);
							} else {
								BigDecimal ftTotal = new BigDecimal(bulkamount);
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added as payment *******************
							// *******FT Start******

							ftbulkamount = ftbulkamount.add(new BigDecimal(bulkamount));

							/*
							 * FTEntity fttLatest =
							 * fTRepo.findTopByCustomerIdOrderByFanIdDesc(salesEntityRes.getMemberid());
							 * 
							 * FTEntity fttEntity = new FTEntity();
							 * 
							 * fttEntity.setInvoideId(salesEntityRes.getSaleId());
							 * fttEntity.setAmount(bulkamount);
							 * fttEntity.setCustomerId(salesEntityRes.getMemberid());
							 * fttEntity.setCustomerName(salesEntityRes.getMember_name());
							 * fttEntity.setDate(new Date()); fttEntity.setType("BulkPayment");
							 * 
							 * if (fttLatest == null) { fttEntity.setBalance(bulkamount); } else {
							 * BigDecimal fttTotal = new BigDecimal(bulkamount); LeoLogger.
							 * info("SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
							 * + ftLatest.getBalance()); fttTotal = new
							 * BigDecimal(fttLatest.getBalance()).subtract(fttTotal); fttTotal =
							 * fttTotal.setScale(2, RoundingMode.HALF_UP);
							 * fttEntity.setBalance(fttTotal.doubleValue()); } fTRepo.save(fttEntity);
							 */
							// ******FT ended******

							// ********** Enter Payment with bulk id *******************

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= salesEntityRes.getPaid()) {
								paymentEntity.setstatus("Paid");
								// salesEntityRes.setPaymentstatus("Paid");
							} else {
								paymentEntity.setstatus("Partial");
							}
							// paymentEntity.setGrand_total(bulkamount);
							paymentEntity.setPref(addPaymentReqPojo.getPref());
							paymentEntity.setPtype(addPaymentReqPojo.getPtype());
							paymentEntity.setMember_id(salesEntityRes.getMemberid());
							paymentEntity.setMember_name(salesEntityRes.getMember_name());
							paymentEntity.setRsaleId(salesEntityRes.getSaleId());
							paymentEntity.setGrand_total(bamountt.doubleValue());
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setPref(addPaymentReqPojo.getPref());
							paymentEntity.setPtype(addPaymentReqPojo.getPtype());
							paymentEntity.setNote(addPaymentReqPojo.getNote());
							paymentEntity.setCtype(salesEntityRes.getCtype());
							paymentEntity.setSalesreferenceno(salesEntityRes.getReferenceno());
							// paymentRepo.save(paymentEntity);
							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno(
									"BulkPayment" + currentYear + "/" + currentmonth + "/" + ftEntity.getInvoideId());
							paymentRepo.save(paymentEntity);

							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);

							// ********** Payment Added *******************
							bulkamount = 0;
							break;

						}
					}
				}
			} else {
				// Special Customer bulk Payment

				List<SpecialSalesEntity> specialsalesEntityList = specialsalesRepo
						.findAllByMemberidAndPaymentstatusAndIsActiveOrderBySaleIdAsc(addPaymentReqPojo.getMemberId(),
								"Due", 0);
				LeoLogger.info("SaleServiceImpl---addBulkPayment--Sales list in ascending order "
						+ specialsalesEntityList.toString());

				// ********** Enter Payment as payment *******************

				LeoLogger
						.info("SaleServiceImpl---addBulkPayment Special customer--Bulk amount is [" + bulkamount + "]");
				BigDecimal db_paidamount = new BigDecimal(0.0);

				for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

					double pendingamount = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
					LeoLogger.info("SaleServiceImpl---addBulkPayment---Pending amount is [" + pendingamount + "]");

					if (bulkamount >= pendingamount)

					{
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Bulk amount is [" + bulkamount
								+ "] and the remaining amount for invoice [" + specialsalesEntityRes.getSaleId()
								+ "] is [" + (specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid())
								+ "]");

						// Addjust Sales entity paid value

						double paidamount = 0.0;
						paidamount = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
						db_paidamount = new BigDecimal(paidamount);
						db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info("SaleServiceImpl---addBulkPayment---db_paidamount is [" + db_paidamount + "]");

						specialsalesEntityRes.setPaid(specialsalesEntityRes.getGrand_total());
						specialsalesEntityRes.setPaymentstatus("Paid");
						specialsalesRepo.save(specialsalesEntityRes);

						// ********** Enter Financial transaction *******************

						FinancialTransactionEntity ftLatest = financialTransactionsRepo
								.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid());

						FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

						ftEntity.setInvoideId(specialsalesEntityRes.getSaleId());
						ftEntity.setAmount(db_paidamount.doubleValue());
						ftEntity.setCustomerId(specialsalesEntityRes.getMemberid());
						ftEntity.setCustomerName(specialsalesEntityRes.getMember_name());
						ftEntity.setDate(new Date());
						ftEntity.setType("BulkPayment");

						if (ftLatest == null) {
							ftEntity.setBalance(paidamount);
						} else {
							BigDecimal ftTotal = new BigDecimal(paidamount);
							LeoLogger.info(
									"SaleServiceImpl---addBulkPayment Special---Latest Financial Transation balance  ....."
											+ ftLatest.getBalance());
							ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
							ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
							ftEntity.setBalance(ftTotal.doubleValue());
						}
						financialTransactionsRepo.save(ftEntity);

						// ********** Financial transaction Added *******************

						// *******FT Start******

						ftbulkamount = ftbulkamount.add(db_paidamount);

						/*
						 * FTEntity fttLatest =
						 * fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid(
						 * ));
						 * 
						 * FTEntity fttEntity = new FTEntity();
						 * 
						 * fttEntity.setInvoideId(specialsalesEntityRes.getSaleId());
						 * fttEntity.setAmount(db_paidamount.doubleValue());
						 * fttEntity.setCustomerId(specialsalesEntityRes.getMemberid());
						 * fttEntity.setCustomerName(specialsalesEntityRes.getMember_name());
						 * fttEntity.setDate(new Date()); fttEntity.setType("BulkPayment");
						 * 
						 * if (fttLatest == null) { fttEntity.setBalance(paidamount); } else {
						 * BigDecimal fttTotal = new BigDecimal(paidamount); LeoLogger.
						 * info("SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
						 * + ftLatest.getBalance()); fttTotal = new
						 * BigDecimal(fttLatest.getBalance()).subtract(fttTotal); fttTotal =
						 * fttTotal.setScale(2, RoundingMode.HALF_UP);
						 * fttEntity.setBalance(fttTotal.doubleValue()); } fTRepo.save(fttEntity);
						 */
						// ******FT ended******

						// ********** Enter Payment with bulk id *******************

						PaymentEntity paymentEntity = new PaymentEntity();
						if (bulkamount >= specialsalesEntityRes.getPaid()) {
							paymentEntity.setstatus("Paid");
						} else {
							paymentEntity.setstatus("Partial");
						}

						paymentEntity.setMember_id(specialsalesEntityRes.getMemberid());
						paymentEntity.setMember_name(specialsalesEntityRes.getMember_name());
						paymentEntity.setRsaleId(specialsalesEntityRes.getSaleId());
						paymentEntity.setGrand_total(db_paidamount.doubleValue());
						paymentEntity.setPaymentdate(new Date());
						paymentEntity.setBulkid(bkpent.getBulkId());
						paymentEntity.setPref(addPaymentReqPojo.getPref());
						paymentEntity.setPtype(addPaymentReqPojo.getPtype());
						paymentEntity.setNote(addPaymentReqPojo.getNote());
						paymentEntity.setCtype(specialsalesEntityRes.getCtype());
						paymentEntity.setSalesreferenceno(specialsalesEntityRes.getReferenceno());
						Date d = new Date();
						int year = d.getYear();
						int currentYear = year + 1900;
						int currentmonth = d.getMonth() + 1;

						paymentEntity.setReferenceno(
								"BulkPayment" + currentYear + "/" + currentmonth + "/" + ftEntity.getInvoideId());
						paymentRepo.save(paymentEntity);

						// ********** Payment Added *******************

						bulkamount = bulkamount - paidamount;
						bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
						bulkPaymentRepo.save(bulkPaymentEntity);

					} else {
						if (bulkamount != 0) {
							BigDecimal bamount = new BigDecimal(bulkamount);
							bamount = bamount.setScale(3, RoundingMode.HALF_UP);
							BigDecimal bamountt = bamount.setScale(2, RoundingMode.HALF_UP);

							BigDecimal setpayamount = new BigDecimal(bulkamount);
							setpayamount = setpayamount.add(new BigDecimal(specialsalesEntityRes.getPaid()));
							setpayamount = setpayamount.setScale(2, RoundingMode.HALF_UP);

							specialsalesEntityRes.setPaid(setpayamount.doubleValue());
							if (bamountt.doubleValue() == (specialsalesEntityRes.getGrand_total()
									- specialsalesEntityRes.getPaid())) {
								specialsalesEntityRes.setPaymentstatus("Paid");
							}

							// ********** Enter Financial transaction as payment *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(specialsalesEntityRes.getSaleId());
							ftEntity.setAmount(bulkamount);
							ftEntity.setCustomerId(specialsalesEntityRes.getMemberid());
							ftEntity.setCustomerName(specialsalesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("BulkPayment");
							if (ftLatest == null) {
								ftEntity.setBalance(bulkamount);
							} else {
								BigDecimal ftTotal = new BigDecimal(bulkamount);
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added as payment *******************
							// *******FT Start******

							ftbulkamount = ftbulkamount.add(new BigDecimal(bulkamount));

							/*
							 * FTEntity fttLatest =
							 * fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid(
							 * ));
							 * 
							 * FTEntity fttEntity = new FTEntity();
							 * 
							 * fttEntity.setInvoideId(specialsalesEntityRes.getSaleId());
							 * fttEntity.setAmount(bulkamount);
							 * fttEntity.setCustomerId(specialsalesEntityRes.getMemberid());
							 * fttEntity.setCustomerName(specialsalesEntityRes.getMember_name());
							 * fttEntity.setDate(new Date()); fttEntity.setType("BulkPayment");
							 * 
							 * if (fttLatest == null) { fttEntity.setBalance(bulkamount); } else {
							 * BigDecimal fttTotal = new BigDecimal(bulkamount); LeoLogger.
							 * info("SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
							 * + ftLatest.getBalance()); fttTotal = new
							 * BigDecimal(fttLatest.getBalance()).subtract(fttTotal); fttTotal =
							 * fttTotal.setScale(2, RoundingMode.HALF_UP);
							 * fttEntity.setBalance(fttTotal.doubleValue()); } fTRepo.save(fttEntity);
							 */
							// ******FT ended******

							// ********** Enter Payment with bulk id *******************

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= specialsalesEntityRes.getPaid()) {
								paymentEntity.setstatus("Paid");
							} else {
								paymentEntity.setstatus("Partial");
							}
							// paymentEntity.setGrand_total(bulkamount);
							paymentEntity.setPref(addPaymentReqPojo.getPref());
							paymentEntity.setPtype(addPaymentReqPojo.getPtype());
							paymentEntity.setMember_id(specialsalesEntityRes.getMemberid());
							paymentEntity.setMember_name(specialsalesEntityRes.getMember_name());
							paymentEntity.setRsaleId(specialsalesEntityRes.getSaleId());
							paymentEntity.setGrand_total(bamountt.doubleValue());
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setPref(addPaymentReqPojo.getPref());
							paymentEntity.setPtype(addPaymentReqPojo.getPtype());
							paymentEntity.setNote(addPaymentReqPojo.getNote());
							paymentEntity.setCtype(specialsalesEntityRes.getCtype());
							paymentEntity.setSalesreferenceno(specialsalesEntityRes.getReferenceno());
							// paymentRepo.save(paymentEntity);
							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno(
									"BulkPayment" + currentYear + "/" + currentmonth + "/" + ftEntity.getInvoideId());
							paymentRepo.save(paymentEntity);

							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);

							// ********** Payment Added *******************
							bulkamount = 0;
							break;

						}
					}
				}

			}

			ftBulkPaymentEntry(memberPojo, ftbulkamount, bulkPaymentEntity);
	
			/*
			 * BigDecimal balance = new BigDecimal(0.0); balance = new
			 * BigDecimal(salesEntity.getGrand_total() - salesEntity.getPaid()); balance =
			 * balance.setScale(2, RoundingMode.HALF_UP);
			 * LeoLogger.info("SaleServiceImpl---Original Payment amount ..."+origamountt);
			 * LeoLogger.info("SaleServiceImpl---balance ..."+balance);
			 */

			if (!addPaymentReqPojo.getCtype().equalsIgnoreCase("Special")) {
			applyRegisterhistorybulkpayment(bulkPaymentEntity);

			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());
			if (registerEntity != null) {
				applyExisitingRegisterbulkPayment(addPaymentReqPojo, bulkPaymentEntity, registerEntity);
			} else {

				applyRegisterhistorybulkpayment(bulkPaymentEntity);
			} 
			
			} else {
				
				  saveSpecialRegisterForPayment(new BigDecimal(bulkPaymentEntity.getAmount()));
				  applySpecialRegisterHistoryBulkPayment(bulkPaymentEntity);
				
			}
			
			

			if (bulkamount > 0)

			{
				// ********** Enter Financial transaction as Credit note *******************

				BigDecimal bdbulkamount = new BigDecimal(bulkamount);
				bdbulkamount = new BigDecimal(memberPojo.getCreditpayment()).add(new BigDecimal(bulkamount));
				bdbulkamount = bdbulkamount.setScale(2, RoundingMode.HALF_UP);
				memberPojo.setCreditpayment(bdbulkamount.doubleValue());
				memberUserRepo.save(memberPojo);

				// ********** Enter Financial transaction *******************

				FinancialTransactionEntity ftLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(memberPojo.getId());

				FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

				ftEntity.setInvoideId(bulkPaymentEntity.getBulkId());
				ftEntity.setAmount(bdbulkamount.doubleValue());
				ftEntity.setCustomerId(memberPojo.getId());
				ftEntity.setCustomerName(memberPojo.getName());
				ftEntity.setDate(new Date());
				ftEntity.setType("Credit Amount:" + bkpent.getBulkId());

				financialTransactionsRepo.save(ftEntity);

				// ********** Financial transaction Added *******************
				// *******FT Start******
				FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(memberPojo.getId());

				FTEntity fttEntity = new FTEntity();

				fttEntity.setInvoideId(bulkPaymentEntity.getBulkId());
				fttEntity.setAmount(bulkamount);
				fttEntity.setCustomerId(memberPojo.getId());
				fttEntity.setCustomerName(memberPojo.getName());
				fttEntity.setDate(new Date());
				fttEntity.setType("Credit Amount");

				if (fttLatest == null) {
					fttEntity.setBalance(ftbulkamount.doubleValue());
				} else {
					BigDecimal fttTotal = new BigDecimal(bdbulkamount.doubleValue());
					LeoLogger.info("SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
							+ fttLatest.getBalance());
					fttTotal = new BigDecimal(fttLatest.getBalance());
					fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
					fttEntity.setBalance(fttTotal.doubleValue());
				}
				fTRepo.save(fttEntity);
				// ******FT ended******/

			}

			resultVO.setMsgDescr("Payment Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	private void applyExisitingRegisterbulkPayment(AddPaymentReqPojo addPaymentReqPojo,
			BulkPaymentEntity bulkPaymentEntity, RegisterEntity registerEntity) {

		LeoLogger.info("SaleServiceImpl---addBulkPayment-- Register entry found for today  ....."
				+ registerEntity.getSalesamount());
		LeoLogger.info("SaleServiceImpl---addBulkPayment-- Register entry found for today payamount ....."
				+ bulkPaymentEntity.getAmount());
		if (addPaymentReqPojo.getPtype().equalsIgnoreCase("cash")) {
			LeoLogger.info("SaleServiceImpl---addBulkPayment-- cash....");
			//LeoLogger.info("SaleServiceImpl---addBulkPayment-- cash..origamountt.." + bulkPaymentEntity.getAmount());
			registerEntity.setCashpayment(registerEntity.getCashpayment() + bulkPaymentEntity.getAmount());
			registerEntity.setClosingbal(registerEntity.getClosingbal() + bulkPaymentEntity.getAmount());
		} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("cheque")) {
			LeoLogger.info("SaleServiceImpl---addBulkPayment-- cheque....");
			//LeoLogger.info("SaleServiceImpl---addBulkPayment-- cheque..origamountt.." + bulkPaymentEntity.getAmount());
			registerEntity.setChequepayment(registerEntity.getChequepayment() + bulkPaymentEntity.getAmount());
			registerEntity.setClosingbal(registerEntity.getClosingbal() + bulkPaymentEntity.getAmount());
		} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("online")) {
			LeoLogger.info("SaleServiceImpl---addBulkPayment-- online....");
			LeoLogger.info("SaleServiceImpl---addBulkPayment-- online..origamountt.." + bulkPaymentEntity.getAmount());
			registerEntity.setOnlinepayment(registerEntity.getOnlinepayment() + bulkPaymentEntity.getAmount());
			registerEntity.setClosingbal(registerEntity.getClosingbal() + bulkPaymentEntity.getAmount());
		} else if (addPaymentReqPojo.getPtype().equalsIgnoreCase("other")) {
			LeoLogger.info("SaleServiceImpl---addBulkPayment-- other....");
			LeoLogger.info("SaleServiceImpl---addBulkPayment-- other..origamountt.." + bulkPaymentEntity.getAmount());
			registerEntity.setOtherpayment(registerEntity.getOtherpayment() + bulkPaymentEntity.getAmount());
			// registerEntity.setClosingbal(registerEntity.getClosingbal() +
			// addPaymentReqPojo.getAmount());
		} else {
			LeoLogger.info("SaleServiceImpl---addBulkPayment-- credit card....");
			LeoLogger.info(
					"SaleServiceImpl---addBulkPayment-- credit card..origamountt.." + bulkPaymentEntity.getAmount());
			registerEntity.setCreditcardpayment(registerEntity.getCreditcardpayment() + bulkPaymentEntity.getAmount());
			registerEntity.setClosingbal(registerEntity.getClosingbal() + bulkPaymentEntity.getAmount());
		}
		registerRepo.save(registerEntity);
	}

	private void ftBulkPaymentEntry(MemberUser memberPojo, BigDecimal ftbulkamount,
			BulkPaymentEntity bulkPaymentEntity) {

		ftbulkamount = ftbulkamount.setScale(2, RoundingMode.HALF_UP);
		FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(memberPojo.getId());

		FTEntity fttEntity = new FTEntity();

		fttEntity.setInvoideId(bulkPaymentEntity.getBulkId());
		fttEntity.setAmount(ftbulkamount.doubleValue());
		fttEntity.setCustomerId(memberPojo.getId());
		fttEntity.setCustomerName(memberPojo.getName());
		fttEntity.setDate(new Date());
		if (bulkPaymentEntity.getPtype() == "Deposit Pay") {
			fttEntity.setType("CreditPayment");
		} else {
			fttEntity.setType("BulkPayment");
		}
		fttEntity.setReferenceno(String.valueOf(bulkPaymentEntity.getBulkId()));

		if (fttLatest == null) {
			fttEntity.setBalance(ftbulkamount.doubleValue());
		} else {
			BigDecimal fttTotal = new BigDecimal(ftbulkamount.doubleValue());
			LeoLogger.info("SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
					+ fttLatest.getBalance());
			fttTotal = new BigDecimal(fttLatest.getBalance()).subtract(fttTotal);
			fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
			fttEntity.setBalance(fttTotal.doubleValue());
		}
		fTRepo.save(fttEntity);

	}

	@Override
	public ResultVO converquotestToSale(String quoteId) {
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("SalesServiceImpl ---converquotestToSale");

			QuotesEntity quoteEntity = quotesRepo.findByquotesId(Long.parseLong(quoteId));
			SalesEntity saleEntity = new SalesEntity();
			SpecialSalesEntity specialsaleEntity = new SpecialSalesEntity();
			String Ctype = "";
			double tax_rate = 0, total = 0, unit_price = 0, subtotal = 0, producttax = 0;
			BigDecimal quantity = new BigDecimal(0.0);
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal unitPrice = new BigDecimal(0.0);
			double creditamount = 0.0;
			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;
			MemberUser memberPojo = memberUserRepo.findById(quoteEntity.getMemberid());
			LeoLogger.info("SaleServiceImpl---converquotestToSale---memberPojo" + memberPojo);
			creditamount=memberPojo.getCreditpayment();
			LeoLogger.info("SaleServiceImpl---converquotestToSale---creditamount" + creditamount);
	
			
	
            
			if (quoteEntity != null)

			{
				Ctype = quoteEntity.getCtype();
			

				if (!quoteEntity.getCtype().equalsIgnoreCase("Special")) {

					quoteEntity.setquotes_status("Converted");

					saleEntity.setDate(new Date());
					saleEntity.setMemberid(quoteEntity.getMemberid());
					saleEntity.setMember_name(quoteEntity.getMember_name());
					saleEntity.setMembername(quoteEntity.getMember_name());
					saleEntity.setOrder_tax(0);
					// saleEntity.setProduct_tax(quoteEntity.getProduct_tax());
					// saleEntity.setTotal_tax(quoteEntity.getTotal_tax());
					saleEntity.setOrder_discount(0);
					// saleEntity.setTotal(quoteEntity.getTotal());
					// saleEntity.setGrand_total(quoteEntity.getGrand_total());
					saleEntity.setCtype(quoteEntity.getCtype());
					// saleEntity.setReferenceno(quoteEntity.getReferenceno());
					// saleEntity.setquoteId(String.valueOf(quoteEntity.getQuotesId()));
					if(creditamount!=0) {
						if (Double.parseDouble(quoteEntity.getGrandtotal()) < creditamount) {
							LeoLogger.info("SaleServiceImpl---converquotestToSale---grandtotal is greater than creditamount" );
							saleEntity.setPaid(Double.parseDouble(quoteEntity.getGrandtotal()));
							saleEntity.setPaymentstatus("Paid");
							memberPojo.setCreditpayment(creditamount - Double.parseDouble(quoteEntity.getGrandtotal()));
							
						}else {
							LeoLogger.info("SaleServiceImpl---converquotestToSale---creditamount is greater than amount" );
							saleEntity.setPaid(creditamount);
							saleEntity.setPaymentstatus("Due");

							memberPojo.setCreditpayment(0);
						}
					}
					else {
						LeoLogger.info("SaleServiceImpl---converquotestToSale---no creditamount" );
						saleEntity.setPaid(0);
						saleEntity.setPaymentstatus("Due");
					}
					saleEntity.setCf1(quoteEntity.getCf1());
					saleEntity.setNote(quoteEntity.getNote());
					saleEntity.setCustomeraddress(quoteEntity.getCustomeraddress());
					saleEntity.setPhonemain(quoteEntity.getPhonemain());
					saleEntity.setPincode(quoteEntity.getPincode());
					salesRepo.save(saleEntity);
			
				}

				else {
					quoteEntity.setquotes_status("Converted");
					specialsaleEntity.setDate(new Date());
					specialsaleEntity.setMemberid(quoteEntity.getMemberid());
					specialsaleEntity.setMember_name(quoteEntity.getMember_name());
					specialsaleEntity.setMembername(quoteEntity.getMember_name());
					specialsaleEntity.setOrder_tax(0);
					// specialsaleEntity.setProduct_tax(quoteEntity.getProduct_tax());
					// specialsaleEntity.setTotal_tax(quoteEntity.getTotal_tax());
					specialsaleEntity.setOrder_discount(0);
					// specialsaleEntity.setTotal(quoteEntity.getTotal());
					// specialsaleEntity.setGrand_total(quoteEntity.getGrand_total());
					specialsaleEntity.setCtype(quoteEntity.getCtype());
					// specialsaleEntity.setReferenceno(quoteEntity.getReferenceno());
					// saleEntity.setquoteId(String.valueOf(quoteEntity.getQuotesId()));
					if(creditamount!=0) {
						if (Double.parseDouble(quoteEntity.getGrandtotal()) < creditamount) {
							LeoLogger.info("SaleServiceImpl---converquotestToSale---grandtotal is greater than creditamount" );
							specialsaleEntity.setPaid(Double.parseDouble(quoteEntity.getGrandtotal()));
							specialsaleEntity.setPaymentstatus("Paid");
							memberPojo.setCreditpayment(creditamount - Double.parseDouble(quoteEntity.getGrandtotal()));
							
						}else {
							LeoLogger.info("SaleServiceImpl---converquotestToSale---creditamount is greater than amount" );
							specialsaleEntity.setPaid(creditamount);
							specialsaleEntity.setPaymentstatus("Due");

							memberPojo.setCreditpayment(0);
						}
					}
					else {
						LeoLogger.info("SaleServiceImpl---converquotestToSale---no creditamount" );
						specialsaleEntity.setPaid(0);
						specialsaleEntity.setPaymentstatus("Due");
					}
					specialsaleEntity.setNote(quoteEntity.getNote());
					specialsaleEntity.setCf1(quoteEntity.getCf1());
					specialsaleEntity.setCustomeraddress(quoteEntity.getCustomeraddress());
					specialsaleEntity.setPhonemain(quoteEntity.getPhonemain());
					specialsaleEntity.setPincode(quoteEntity.getPincode());
					specialsalesRepo.save(specialsaleEntity);

				}

				List<QuotesItemEntity> quotesItemEntity = quotesItemRepo
						.findByQuotesidOrderByIdAsc(Long.parseLong(quoteId));
				UnitEntity unitentity = new UnitEntity();
				List<SalesItemEntity> saleItemEntity = new ArrayList<>();
				List<SpecialSalesItemEntity> specialsaleItemEntity = new ArrayList<>();
			//	SalesPercentageEntity salesPercentageEntity = salespercentRepo.findByCtype(quoteEntity.getCtype());
				SalesPercentageEntity salesPercentageEntity = salespercentRepo.findByCtypeAndPricegroup(quoteEntity.getCtype(),memberPojo.getPricegroup());

				LeoLogger.info("SaleServiceImpl---addQuotes----Member id---- " + memberPojo.getId());
				LeoLogger.info("SaleServiceImpl---addQuotes----Member type---- " + memberPojo.getCtype());
				LeoLogger.info("SaleServiceImpl---addQuotes----Member price group---- " + memberPojo.getPricegroup());
				LeoLogger.info(
						"SaleServiceImpl---addQuotes----Sales percentage---- " + salesPercentageEntity.getPercentage());

				for (QuotesItemEntity rqitems : quotesItemEntity) {

					LeoLogger.info("SaleServiceImpl---converquotestToSale---Quote Item " + rqitems);
					unitentity = unitRepo.findById(rqitems.getSale_item_id());

					LeoLogger.info("SaleServiceImpl---converquotestToSale---unitentity" + unitentity.getUnitname());

					ProductDetailsEntity productDetailsEnt = productDetailsRepo
							.findByProductId(rqitems.getProduct_id());

					LeoLogger.info("SaleServiceImpl---converquotestToSale---Product Details " + productDetailsEnt);

					if (memberPojo.getCtype().equalsIgnoreCase("Special")
							&& memberPojo.getPricegroup().equalsIgnoreCase("WholeSellers")) {

						LeoLogger.info(
								"SaleServiceImpl---converquotestToSale---Member Type is Special and price group is WholeSellers ");
						 if ((rqitems.getRoll().equalsIgnoreCase("Piece")) || (rqitems.getRoll().equalsIgnoreCase("ft") || (rqitems.getRoll().equalsIgnoreCase("10Ft")) )) {
							unit_price = productDetailsEnt.getprice().doubleValue();
							LeoLogger.info(
									"SaleServiceImpl---converquotestToSale---Product unit price for piece and ft---- "
											+ productDetailsEnt.getprice());

						}else if((rqitems.getRoll().equalsIgnoreCase("20Ft"))){
							unit_price = (productDetailsEnt.getprice().doubleValue())*2;
						}
							else if (rqitems.getRoll().equalsIgnoreCase("Box12")) {
						
							unit_price = productDetailsEnt.getrollprice();
							LeoLogger.info("SaleServiceImpl---converquotestToSale---Product unit  price for box---- "
									+ productDetailsEnt.getrollprice());
							if (salesPercentageEntity.getPercentage() > 0) {
								unit_price = (productDetailsEnt.getrollprice() + (productDetailsEnt.getrollprice()
										* salesPercentageEntity.getPercentage() / 100));

								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit  price after sale percentage  ---- "
												+ unit_price);

							}

						} else {
							unit_price = productDetailsEnt.getrollprice();
							LeoLogger.info(
									"SaleServiceImpl---converquotestToSale---Product unit  price for wire and roll unit ---- "
											+ productDetailsEnt.getrollprice());
						}

						bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

					} else {
						if (salesPercentageEntity.getPercentage() > 0) {
							
					    if  ((rqitems.getRoll().equalsIgnoreCase("Piece")) || (rqitems.getRoll().equalsIgnoreCase("ft") || (rqitems.getRoll().equalsIgnoreCase("10Ft")))) {

								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit price for piece and ft---- "
												+ productDetailsEnt.getprice());
								unit_price = (productDetailsEnt.getprice().doubleValue()
										+ (productDetailsEnt.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit  price after sale percentage  ---- "
												+ unit_price);
								


							} else if((rqitems.getRoll().equalsIgnoreCase("20Ft"))) {
								unit_price = (productDetailsEnt.getprice().doubleValue()
										+ (productDetailsEnt.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
								unit_price = unit_price*2;
							}
					    else if (rqitems.getRoll().equalsIgnoreCase("Box12")) {

								LeoLogger
										.info("SaleServiceImpl---converquotestToSale---Product unit  price for box---- "
												+ productDetailsEnt.getrollprice());
								unit_price = productDetailsEnt.getrollprice();
								if (salesPercentageEntity.getPercentage() > 0) {
									unit_price = (productDetailsEnt.getrollprice() + (productDetailsEnt.getrollprice()
											* salesPercentageEntity.getPercentage() / 100));
									LeoLogger.info(
											"SaleServiceImpl---converquotestToSale---Product unit  price after sale percentage  ---- "
													+ unit_price);
								}

							} else {

								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit  price for wire and roll unit ---- "
												+ productDetailsEnt.getrollprice());
								unit_price = (productDetailsEnt.getrollprice() + (productDetailsEnt.getrollprice()
										* salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit  price after sale percentage  ---- "
												+ unit_price);
							}

							bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

							if (productDetailsEnt.getpromotion() > 0) {
								if (!memberPojo.getCtype().equalsIgnoreCase("WholeSellers")) {

									LeoLogger.info(
											"SaleServiceImpl---converquotestToSale---Product promotion percentage---- "
													+ productDetailsEnt.getpromotion());
									unit_price = (bd_real_unit_price.doubleValue()
											- ((bd_real_unit_price.doubleValue() * productDetailsEnt.getpromotion())
													/ 100));
									LeoLogger.info(
											"SaleServiceImpl---converquotestToSale---Product unit price after subtracting promotion percentage---- "
													+ unit_price);
									if((rqitems.getRoll().equalsIgnoreCase("20Ft"))) {
										unit_price = unit_price*2;
									}

								}
							}

							bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

						} else {
							if ((rqitems.getRoll().equalsIgnoreCase("Piece")) || (rqitems.getRoll().equalsIgnoreCase("ft") || (rqitems.getRoll().equalsIgnoreCase("10Ft")) )) {

								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit price for piece and ft---- "
												+ productDetailsEnt.getprice());
								unit_price = (productDetailsEnt.getprice().doubleValue()
										+ (productDetailsEnt.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit  price after sale percentage  ---- "
												+ unit_price);
								


							}else if((rqitems.getRoll().equalsIgnoreCase("20Ft"))) {
								unit_price = (productDetailsEnt.getprice().doubleValue()
										+ (productDetailsEnt.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
								unit_price = unit_price*2;
							} 
							else if (rqitems.getRoll().equalsIgnoreCase("Box12")) {
								LeoLogger.info("SaleServiceImpl---converquotestToSale---unit_price box" + unit_price);
								unit_price = productDetailsEnt.getrollprice();
								if (salesPercentageEntity.getPercentage() > 0) {
									unit_price = (productDetailsEnt.getrollprice() + (productDetailsEnt.getrollprice()
											* salesPercentageEntity.getPercentage() / 100));
									LeoLogger.info(
											"SaleServiceImpl---converquotestToSale---Product unit  price after sale percentage  ---- "
													+ unit_price);

								}

							} else {
								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit  price for wire and roll unit ---- "
												+ productDetailsEnt.getrollprice());
								unit_price = (productDetailsEnt.getrollprice() + (productDetailsEnt.getrollprice()
										* salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---converquotestToSale---Product unit  price after sale percentage  ---- "
												+ unit_price);
							}
							bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

						}
					}

					if (rqitems.getSale_item_id() == 3) {
						quantity = rqitems.getQuantity().divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
					} else if (rqitems.getSale_item_id() == 2) {
						quantity = rqitems.getQuantity().divide(BigDecimal.valueOf(328), RoundingMode.HALF_UP);
					} else if (rqitems.getSale_item_id() == 4) {
						quantity = rqitems.getQuantity().divide(BigDecimal.valueOf(164), RoundingMode.HALF_UP);
					}  else if (rqitems.getSale_item_id() == 9) {
						quantity = rqitems.getQuantity().divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
					} else {
						quantity = rqitems.getQuantity();
					}

					if (!quoteEntity.getCtype().equalsIgnoreCase("Special")) {
						if (rqitems.getIsPriceChange() == 1) {
							bd_real_unit_price = new BigDecimal(rqitems.getReal_unit_price()).setScale(2,
									RoundingMode.HALF_UP);
							if (quoteEntity.getProduct_tax() != 0) {
								tax_rate += (bd_real_unit_price.doubleValue() * 0.125 * quantity.doubleValue());
							} else {
								tax_rate = 0;
							}
							if (memberPojo.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
								tax_rate = 0;
							}
						} else {
							if (quoteEntity.getProduct_tax() != 0) {
								tax_rate += (bd_real_unit_price.doubleValue() * 0.125 * quantity.doubleValue());
							} else {
								tax_rate = 0;
							}

							if (memberPojo.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
								tax_rate = 0;
							}
						}

					} else {
						if (quoteEntity.getProduct_tax() != 0) {
							bd_real_unit_price = new BigDecimal(rqitems.getReal_unit_price()).setScale(2,
									RoundingMode.HALF_UP);
							tax_rate += (bd_real_unit_price.doubleValue() * 0.125 * quantity.doubleValue());
						} else {
							tax_rate = 0;
						}

					}

					if (rqitems.getIsPriceChange() == 1) {
						bd_real_unit_price = new BigDecimal(rqitems.getReal_unit_price()).setScale(2,
								RoundingMode.HALF_UP);
						LeoLogger.info("SaleServiceImpl---converquotestToSale----Product unit price  change to---- "
								+ bd_real_unit_price);
						total += bd_real_unit_price.doubleValue() * quantity.doubleValue();
					} else {

						total += bd_real_unit_price.doubleValue() * quantity.doubleValue();
					}

					bd_tax_rate = new BigDecimal(tax_rate).setScale(2, RoundingMode.HALF_UP);
					tax_rate = bd_tax_rate.doubleValue();

					bd_total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
					total = bd_total.doubleValue();

					subtotal = bd_real_unit_price.doubleValue() * quantity.doubleValue();
					BigDecimal bdsubtotal = new BigDecimal(subtotal);
					bdsubtotal = bdsubtotal.setScale(2, RoundingMode.HALF_UP);
					producttax = productDetailsEnt.gettax_rate();

					LeoLogger.info(
							"SaleServiceImpl---converquotestToSale---quoteItemEntity unit_price" + bd_real_unit_price);
					LeoLogger.info("SaleServiceImpl---converquotestToSale---quoteItemEntity quantity" + quantity);
					LeoLogger.info("SaleServiceImpl---converquotestToSale---quoteItemEntity total" + total);
					LeoLogger.info("SaleServiceImpl---converquotestToSale---quoteItemEntity tax_rate" + tax_rate);

					QuotesItemEntity quoteItemEntity = new QuotesItemEntity();

					quoteItemEntity.setQuotesid(saleEntity.getSaleId());
					quoteItemEntity.setProduct_id(rqitems.getProduct_id());
					quoteItemEntity.setQuantity(rqitems.getQuantity());
					quoteItemEntity.setItem_tax(rqitems.getItem_tax());
					quoteItemEntity.setGst("12.5");
					quoteItemEntity.setItem_discount(0);
					quoteItemEntity.setProduct_code(rqitems.getProduct_code());
					quoteItemEntity.setProduct_name(rqitems.getProduct_name());
					quoteItemEntity.setReal_unit_price(rqitems.getReal_unit_price());
					quoteItemEntity.setSubtotal(rqitems.getSubtotal());
					quoteItemEntity.setTax(rqitems.getTax());
					quoteItemEntity.setRoll(rqitems.getRoll());

					quotesItemRepo.save(quoteItemEntity);

					if (!quoteEntity.getCtype().equalsIgnoreCase("Special")) {
						SalesItemEntity salesItemEntity = new SalesItemEntity();
						salesItemEntity.setSale_id(saleEntity.getSaleId());
						salesItemEntity.setProduct_id(rqitems.getProduct_id());
						// salesItemEntity.setQuantity(rqitems.getQuantity());
						salesItemEntity.setItem_tax(producttax);
						salesItemEntity.setGst("12.5");
						salesItemEntity.setItem_discount(0d);
						salesItemEntity.setProduct_code(rqitems.getProduct_code());
						salesItemEntity.setProduct_name(rqitems.getProduct_name());
						salesItemEntity.setSubtotal(bdsubtotal.doubleValue());

						salesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
						salesItemEntity.setTax(String.valueOf(productDetailsEnt.gettax_rate()));
						salesItemEntity.setRoll(rqitems.getRoll());
						salesItemEntity.setSale_item_id(unitentity.getId());
						salesItemEntity.setUnit_quantity(unitentity.getUnitname());
						salesItemEntity.setCost(productDetailsEnt.getcost());
						salesItemEntity.setReturnqty("0");

						saleEntity.setProduct_tax(bd_tax_rate.doubleValue());
						saleEntity.setTotal_tax(bd_tax_rate.doubleValue());

						saleEntity.setTotal(bd_total.doubleValue());

						bd_grand_total = bd_total.add(bd_tax_rate);

						bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

						saleEntity.setGrand_total(bd_grand_total.doubleValue());
						saleEntity.setGrandtotal(bd_grand_total.toString());
						LeoLogger.info("SaleServiceImpl---converquotestToSale--Grand Total....." + bd_grand_total);
						LeoLogger.info("SaleServiceImpl---converquotestToSale unitentity.getUnitname()."
								+ unitentity.getUnitname());

						if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
							BigDecimal qty  =  (rqitems.getQuantity());
							// LeoLogger.info(""+unitentity.getQuantity());
							BigDecimal unit =(unitentity.getQuantity());
							//System.out.println(result);
							System.out.println(unit);
							BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);
							System.out.println(qty);
							qty = qty .multiply(unit) ;
							salesItemEntity.setQuantity(qty);
							// LeoLogger.info("SaleServiceImpl---addSale--qt....." +qty);
						} else {
							BigDecimal qty  =  (rqitems.getQuantity());
							salesItemEntity.setQuantity(qty);
						}

						salesItemRepo.save(salesItemEntity);
						salesRepo.save(saleEntity);

						if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
							BigDecimal result = (rqitems.getQuantity());
							// LeoLogger.info(""+unitentity.getQuantity());
							BigDecimal unit = ( unitentity.getQuantity());

							  BigDecimal qty = result.divide(unit, 4, RoundingMode.HALF_UP);

							qty = qty .multiply(unit) ;
							System.out.println(unit);
							productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(qty) );
						} else {
							BigDecimal qty = (rqitems.getQuantity());
							productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(qty) );
						}
						productDetailsRepo.save(productDetailsEnt);

					} else {

						SpecialSalesItemEntity specialsalesItemEntity = new SpecialSalesItemEntity();
						specialsalesItemEntity.setSaleid(specialsaleEntity.getSaleId());
						specialsalesItemEntity.setProduct_id(rqitems.getProduct_id());
						// specialsalesItemEntity.setQuantity(rqitems.getQuantity());
						specialsalesItemEntity.setItem_tax(rqitems.getItem_tax());
						specialsalesItemEntity.setGst("12.5");
						specialsalesItemEntity.setItem_discount(0d);
						specialsalesItemEntity.setProduct_code(rqitems.getProduct_code());
						specialsalesItemEntity.setProduct_name(rqitems.getProduct_name());
						specialsalesItemEntity.setSubtotal(bdsubtotal.doubleValue());
						specialsalesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
						specialsalesItemEntity.setTax(String.valueOf(productDetailsEnt.gettax_rate()));
						specialsalesItemEntity.setRoll(rqitems.getRoll());
						specialsalesItemEntity.setSale_item_id(unitentity.getId());
						specialsalesItemEntity.setUnit_quantity(unitentity.getUnitname());
						specialsalesItemEntity.setCost(productDetailsEnt.getcost());
						specialsalesItemEntity.setReturnqty("0");

						if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
							BigDecimal result = (rqitems.getQuantity());
							// LeoLogger.info(""+unitentity.getQuantity());
							BigDecimal unit =( unitentity.getQuantity());
							System.out.println(result);
							System.out.println(unit);
							BigDecimal qty = result.divide(unit, 4, RoundingMode.HALF_UP);
							System.out.println(qty);
							qty = qty .multiply(unit) ;
							specialsalesItemEntity.setQuantity(qty);
							// LeoLogger.info("SaleServiceImpl---addSale--qt....." +qty);
						} else {
							BigDecimal qty = (rqitems.getQuantity());
							specialsalesItemEntity.setQuantity(qty);
						}

						saleEntity.setProduct_tax(bd_tax_rate.doubleValue());
						specialsaleEntity.setTotal_tax(bd_tax_rate.doubleValue());

						specialsaleEntity.setTotal(bd_total.doubleValue());

						bd_grand_total = bd_total.add(bd_tax_rate);
						bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
						specialsaleEntity.setGrand_total(bd_grand_total.doubleValue());
						LeoLogger.info("SaleServiceImpl---converquotestToSale--Grand Total....." + bd_grand_total);

						specialsalesItemRepo.save(specialsalesItemEntity);
						specialsalesRepo.save(specialsaleEntity);

						if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
							BigDecimal qty = (rqitems.getQuantity());
							// LeoLogger.info(""+unitentity.getQuantity());
							BigDecimal unit =(unitentity.getQuantity());

							  BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);

							qty = qty .multiply(unit) ;
							System.out.println(unit);
							productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(qty));
						} else {
							BigDecimal qty = (rqitems.getQuantity());
							productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(qty));
						}
						productDetailsRepo.save(productDetailsEnt);

					}

				}

				/*Date d = new Date();
				int year = d.getYear();
				int currentYear = year + 1900;
				int currentmonth = d.getMonth() + 1;*/
			
				PaymentEntity paymentEntity = new PaymentEntity();

				if (!Ctype.equalsIgnoreCase("Special")) {
					saleEntity.setReferenceno("SALE" + currentYear + "/" + currentmonth + "/" + saleEntity.getSaleId());
					saleEntity.setFileName(customFileUploadUtil.savePdfFileIntoDir(saleItemEntity, saleEntity));
					salesRepo.save(saleEntity);
					
					if(creditamount!=0) {
					paymentEntity.setMember_name(saleEntity.getMembername());
					paymentEntity.setMember_id(saleEntity.getMemberid());
					paymentEntity.setPtype("CA");
					paymentEntity.setRsaleId(saleEntity.getSaleId());
					paymentEntity.setSalesreferenceno(saleEntity.getReferenceno());
					paymentEntity.setstatus("CreditAmount");
					paymentEntity.setGrand_total(saleEntity.getPaid());
					paymentEntity.setCtype(saleEntity.getCtype());
					paymentRepo.save(paymentEntity);
					paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
							+ saleEntity.getSaleId() + "-" + paymentEntity.getId());
					paymentEntity.setPaymentdate(d);
					paymentRepo.save(paymentEntity);
				} 
				}
				else {
					specialsaleEntity.setReferenceno(
							"SALE" + currentYear + "/" + currentmonth + "/" + specialsaleEntity.getSaleId() + "*");
					specialsaleEntity.setFileName(
							customFileUploadUtil.saveSpecialPdfFileIntoDir(specialsaleItemEntity, specialsaleEntity));
					specialsalesRepo.save(specialsaleEntity);
					if(creditamount!=0) {
					paymentEntity.setMember_name(specialsaleEntity.getMembername());
					paymentEntity.setMember_id(specialsaleEntity.getMemberid());
					paymentEntity.setPtype("CA");
					paymentEntity.setRsaleId(specialsaleEntity.getSaleId());
					paymentEntity.setSalesreferenceno(specialsaleEntity.getReferenceno());
					paymentEntity.setstatus("CreditAmount");
					paymentEntity.setGrand_total(specialsaleEntity.getPaid());
					paymentEntity.setCtype(specialsaleEntity.getCtype());
					paymentRepo.save(paymentEntity);
					paymentEntity.setReferenceno("Payment" + currentYear + "/" + currentmonth + "/"
							+ specialsaleEntity.getSaleId() + "-" + paymentEntity.getId());
					paymentEntity.setPaymentdate(d);
					paymentRepo.save(paymentEntity);
				}
				}
				Date currentDate = new Date();
				Calendar c = Calendar.getInstance();
				c.setTime(currentDate);

				Calendar calendarInstance = Calendar.getInstance();
				calendarInstance.add(Calendar.DATE, 30);

				if (!Ctype.equalsIgnoreCase("Special")) {
					FinancialTransactionEntity ftLatest = financialTransactionsRepo
							.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());

					FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

					ftEntity.setInvoideId(saleEntity.getSaleId());
					ftEntity.setAmount(saleEntity.getGrand_total());
					ftEntity.setCustomerId(saleEntity.getMemberid());
					ftEntity.setCustomerName(saleEntity.getMember_name());
					ftEntity.setDate(new Date());
					ftEntity.setDueDate(calendarInstance.getTime());
					ftEntity.setReferenceno(saleEntity.getReferenceno());
					ftEntity.setType("Invoice");

					if (ftLatest == null) {
						ftEntity.setBalance(saleEntity.getGrand_total());
					} else {
						BigDecimal ftTotal = new BigDecimal(0.0);
						BigDecimal grandtotal = new BigDecimal(saleEntity.getGrand_total());
						BigDecimal fttTotal = new BigDecimal(ftLatest.getBalance());
						fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
						ftTotal = grandtotal.add(fttTotal);

						ftEntity.setBalance(ftTotal.doubleValue());
					}

					financialTransactionsRepo.save(ftEntity);
					
					if(creditamount!=0) {
						LeoLogger.info("SaleServiceImpl---converquotestToSale--FinancialTransactionEntity.. if..." + creditamount);
						FinancialTransactionEntity ftLatestt = financialTransactionsRepo
								.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());
						FinancialTransactionEntity ftEntityy = new FinancialTransactionEntity();

						ftEntityy.setInvoideId(saleEntity.getSaleId());
						
							ftEntityy.setAmount(saleEntity.getPaid());
						
						ftEntityy.setCustomerId(saleEntity.getMemberid());
						ftEntityy.setCustomerName(saleEntity.getMember_name());
						ftEntityy.setDate(new Date());
						ftEntityy.setDueDate(calendarInstance.getTime());
						ftEntityy.setType("CreditAmount--Payment");
						ftEntityy.setReferenceno(saleEntity.getReferenceno());

						if (ftLatestt == null) {
							ftEntityy.setBalance(bd_grand_total.doubleValue());
						} else {
							BigDecimal ftTotal = new BigDecimal(0.0);
							BigDecimal fttTotal = new BigDecimal(ftLatestt.getBalance());
							fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
							BigDecimal gtotal = new BigDecimal(saleEntity.getPaid());
							BigDecimal camount = new BigDecimal(creditamount);

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance before credit amount apply  ....."
											+ ftLatestt.getBalance());
							
								ftTotal = fttTotal.subtract(gtotal);
							
							ftEntityy.setBalance(ftTotal.doubleValue());

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance after credit amount apply  ....."
											+ ftTotal);
						}

						financialTransactionsRepo.save(ftEntityy);
					}
				} else {
					FinancialTransactionEntity ftLatest = financialTransactionsRepo
							.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());

					FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();
					ftEntity.setInvoideId(specialsaleEntity.getSaleId());
					ftEntity.setAmount(specialsaleEntity.getGrand_total());
					ftEntity.setCustomerId(specialsaleEntity.getMemberid());
					ftEntity.setCustomerName(specialsaleEntity.getMember_name());
					ftEntity.setDate(new Date());
					ftEntity.setDueDate(calendarInstance.getTime());
					ftEntity.setReferenceno(specialsaleEntity.getReferenceno());
					ftEntity.setType("Invoice");

					if (ftLatest == null) {
						ftEntity.setBalance(specialsaleEntity.getGrand_total());
					} else {
						BigDecimal ftTotal = new BigDecimal(0.0);
						BigDecimal grandtotal = new BigDecimal(specialsaleEntity.getGrand_total());
						BigDecimal fttTotal = new BigDecimal(ftLatest.getBalance());
						fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info(
								"SaleServiceImpl---converquotestToSale---Latest Financial Transation balance ....."
										+ ftLatest.getBalance());
						ftTotal = grandtotal.add(fttTotal);
						LeoLogger.info(
								"SaleServiceImpl---converquotestToSale---Latest Financial Transation balance ....."
										+ ftTotal);
						ftEntity.setBalance(ftTotal.doubleValue());
					}

					financialTransactionsRepo.save(ftEntity);
					
					if(creditamount!=0) {
						LeoLogger.info("SaleServiceImpl---converquotestToSale--FinancialTransactionEntity.. if..." + creditamount);
						FinancialTransactionEntity ftLatestt = financialTransactionsRepo
								.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());
						FinancialTransactionEntity ftEntityy = new FinancialTransactionEntity();

						ftEntityy.setInvoideId(specialsaleEntity.getSaleId());
						
							ftEntityy.setAmount(specialsaleEntity.getPaid());
						
						ftEntityy.setCustomerId(specialsaleEntity.getMemberid());
						ftEntityy.setCustomerName(specialsaleEntity.getMember_name());
						ftEntityy.setDate(new Date());
						ftEntityy.setDueDate(calendarInstance.getTime());
						ftEntityy.setType("CreditAmount--Payment");
						ftEntityy.setReferenceno(specialsaleEntity.getReferenceno());

						if (ftLatestt == null) {
							ftEntityy.setBalance(bd_grand_total.doubleValue());
						} else {
							BigDecimal ftTotal = new BigDecimal(0.0);
							BigDecimal fttTotal = new BigDecimal(ftLatestt.getBalance());
							fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
							BigDecimal gtotal = new BigDecimal(specialsaleEntity.getPaid());
							BigDecimal camount = new BigDecimal(creditamount);

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance before credit amount apply  ....."
											+ ftLatestt.getBalance());
							
								ftTotal = fttTotal.subtract(gtotal);
							
							ftEntityy.setBalance(ftTotal.doubleValue());

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance after credit amount apply  ....."
											+ ftTotal);
						}

						financialTransactionsRepo.save(ftEntityy);
					}
				}

				if (!Ctype.equalsIgnoreCase("Special")) {
					FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());

					FTEntity fttEntity = new FTEntity();

					fttEntity.setInvoideId(saleEntity.getSaleId());
					fttEntity.setAmount(saleEntity.getGrand_total());
					fttEntity.setCustomerId(saleEntity.getMemberid());
					fttEntity.setCustomerName(saleEntity.getMember_name());
					fttEntity.setDate(new Date());
					fttEntity.setDueDate(calendarInstance.getTime());
					fttEntity.setReferenceno(saleEntity.getReferenceno());
					fttEntity.setType("Invoice");

					if (fttLatest == null) {
						fttEntity.setBalance(saleEntity.getGrand_total());
					} else {

						BigDecimal fttTotal = new BigDecimal(0.0);
						BigDecimal grandtotal = new BigDecimal(saleEntity.getGrand_total());
						BigDecimal ftrTotal = new BigDecimal(fttLatest.getBalance());
						ftrTotal = ftrTotal.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info(
								"SaleServiceImpl---addSale---Latest Financial Transation balance  ....." + grandtotal);
						LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....."
								+ fttLatest.getBalance());
						fttTotal = grandtotal.add(ftrTotal);
						fttEntity.setBalance(fttTotal.doubleValue());
					}

					fTRepo.save(fttEntity);
					
					
					if(creditamount!=0) {
						LeoLogger.info("SaleServiceImpl---converquotestToSale--FinancialTransactionEntity.. if..." + creditamount);
						FTEntity ftLatestt = fTRepo.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());
						FTEntity ftEntityy = new FTEntity();

						ftEntityy.setInvoideId(saleEntity.getSaleId());
						
							ftEntityy.setAmount(saleEntity.getPaid());
						
						ftEntityy.setCustomerId(saleEntity.getMemberid());
						ftEntityy.setCustomerName(saleEntity.getMember_name());
						ftEntityy.setDate(new Date());
						ftEntityy.setDueDate(calendarInstance.getTime());
						ftEntityy.setType("CreditAmount--Payment");
						ftEntityy.setReferenceno(saleEntity.getReferenceno());

						if (ftLatestt == null) {
							ftEntityy.setBalance(bd_grand_total.doubleValue());
						} else {
							BigDecimal ftTotal = new BigDecimal(0.0);
							BigDecimal fttTotal = new BigDecimal(ftLatestt.getBalance());
							fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
							BigDecimal gtotal = new BigDecimal(saleEntity.getPaid());
							BigDecimal camount = new BigDecimal(creditamount);

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance before credit amount apply  ....."
											+ ftLatestt.getBalance());
							
								ftTotal = fttTotal.subtract(gtotal);
							
							ftEntityy.setBalance(ftTotal.doubleValue());

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance after credit amount apply  ....."
											+ ftTotal);
						}

						fTRepo.save(ftEntityy);
					}
				} else {
					FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());

					FTEntity fttEntity = new FTEntity();
					LeoLogger
							.info("SaleServiceImpl---addSale---Latest Financial Transation balance  . for special....");
					fttEntity.setInvoideId(specialsaleEntity.getSaleId());
					fttEntity.setAmount(specialsaleEntity.getGrand_total());
					fttEntity.setCustomerId(specialsaleEntity.getMemberid());
					fttEntity.setCustomerName(specialsaleEntity.getMember_name());
					fttEntity.setDate(new Date());
					fttEntity.setDueDate(calendarInstance.getTime());
					fttEntity.setReferenceno(specialsaleEntity.getReferenceno());
					fttEntity.setType("Invoice");

					if (fttLatest == null) {
						fttEntity.setBalance(specialsaleEntity.getGrand_total());
					} else {
						BigDecimal fttTotal = new BigDecimal(0.0);
						BigDecimal grandtotal = new BigDecimal(specialsaleEntity.getGrand_total());
						BigDecimal ftrTotal = new BigDecimal(fttLatest.getBalance());
						ftrTotal = ftrTotal.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info(
								"SaleServiceImpl---addSale---Latest Financial Transation balance  ....." + grandtotal);
						LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....."
								+ fttLatest.getBalance());
						fttTotal = grandtotal.add(ftrTotal);
						LeoLogger.info(
								"SaleServiceImpl---addSale---Latest Financial Transation balance  ....." + fttTotal);
						fttEntity.setBalance(fttTotal.doubleValue());
					}

					fTRepo.save(fttEntity);
					
					if(creditamount!=0) {
						LeoLogger.info("SaleServiceImpl---converquotestToSale--FinancialTransactionEntity.. if..." + creditamount);
						FTEntity ftLatestt = fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());
						FTEntity ftEntityy = new FTEntity();

						ftEntityy.setInvoideId(specialsaleEntity.getSaleId());
						
							ftEntityy.setAmount(specialsaleEntity.getPaid());
						
						ftEntityy.setCustomerId(specialsaleEntity.getMemberid());
						ftEntityy.setCustomerName(specialsaleEntity.getMember_name());
						ftEntityy.setDate(new Date());
						ftEntityy.setDueDate(calendarInstance.getTime());
						ftEntityy.setType("CreditAmount--Payment");
						ftEntityy.setReferenceno(specialsaleEntity.getReferenceno());

						if (ftLatestt == null) {
							ftEntityy.setBalance(bd_grand_total.doubleValue());
						} else {
							BigDecimal ftTotal = new BigDecimal(0.0);
							BigDecimal fttTotal = new BigDecimal(ftLatestt.getBalance());
							fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
							BigDecimal gtotal = new BigDecimal(specialsaleEntity.getPaid());
							BigDecimal camount = new BigDecimal(creditamount);

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance before credit amount apply  ....."
											+ ftLatestt.getBalance());
							
								ftTotal = fttTotal.subtract(gtotal);
							
							ftEntityy.setBalance(ftTotal.doubleValue());

							LeoLogger.info(
									"SaleServiceImpl---SaleServiceImpl--- Financial Transation balance after credit amount apply  ....."
											+ ftTotal);
						}

						fTRepo.save(ftEntityy);
					}
				}
				// ******FT Entry ends

				// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
				// >>>>>>>>>>>>>>>>>>>>>>>>>>

				RegisterEntity registerEntity = new RegisterEntity();
				List<RegisterEntity> entities = registerRepo.findAllByOrderByIdDesc();

				registerEntity = registerRepo.findAByDate(new Date());

				if (!Ctype.equalsIgnoreCase("Special")) {

					if (registerEntity != null)

					{
						registerEntity = entities.get(0);
						LeoLogger.info("SaleServiceImpl---converquotestToSale--  Before Register entry found for today  ....."
								+ registerEntity.getSalesamount());
						registerEntity.setSalesamount(registerEntity.getSalesamount() + saleEntity.getGrand_total());
						registerEntity.setReferenceno(registerEntity.getReferenceno() + "," + saleEntity.getSaleId());
						registerRepo.save(registerEntity);
						LeoLogger.info("SaleServiceImpl---converquotestToSale--  After Register entry found for today  ....."
								+ registerEntity.getSalesamount());
					} else {
						RegisterEntity newregisterEntity = new RegisterEntity();
						LeoLogger.info("SaleServiceImpl---converquotestToSale---Before New Register Entry made"+newregisterEntity.getSalesamount());
						newregisterEntity.setCashinhand(1000.00);
						newregisterEntity.setDate(new Date());
						newregisterEntity.setCashpayment(0.00);
						newregisterEntity.setCreditcardpayment(0.00);
						newregisterEntity.setOpeningbal(1000.00);
						newregisterEntity.setClosingbal(1000.00);
						newregisterEntity.setChequepayment(0.00);
						newregisterEntity.setOnlinepayment(0.00);
						newregisterEntity.setRefunds(0.00);
						newregisterEntity.setReferenceno(String.valueOf(saleEntity.getSaleId()));
						newregisterEntity.setSalesamount(saleEntity.getGrand_total());
						newregisterEntity.setStatus("Open");
						registerRepo.save(newregisterEntity);

						LeoLogger.info("SaleServiceImpl---converquotestToSale---After New Register Entry made"+newregisterEntity.getSalesamount());

					}
				}

				else {
					if (registerEntity != null)

					{
						registerEntity = entities.get(0);
						LeoLogger.info("SaleServiceImpl---converquotestToSale---Before Register entry found for today  ....."
								+ registerEntity.getSalesamount());
						registerEntity
								.setSalesamount(registerEntity.getSalesamount() + specialsaleEntity.getGrand_total());
						registerEntity
								.setReferenceno(registerEntity.getReferenceno() + "," + specialsaleEntity.getSaleId());
						registerRepo.save(registerEntity);
						LeoLogger.info("SaleServiceImpl---converquotestToSale--  After Register entry found for today  ....."
								+ registerEntity.getSalesamount());
					} else {
						RegisterEntity newregisterEntity = new RegisterEntity();
						LeoLogger.info("SaleServiceImpl---converquotestToSale---Before New Register Entry made"+newregisterEntity.getSalesamount());
						newregisterEntity.setCashinhand(1000.00);
						newregisterEntity.setDate(new Date());
						newregisterEntity.setCashpayment(0.00);
						newregisterEntity.setCreditcardpayment(0.00);
						newregisterEntity.setOpeningbal(1000.00);
						newregisterEntity.setClosingbal(1000.00);
						newregisterEntity.setChequepayment(0.00);
						newregisterEntity.setRefunds(0.00);
						newregisterEntity.setReferenceno(String.valueOf(specialsaleEntity.getSaleId()));
						newregisterEntity.setSalesamount(specialsaleEntity.getGrand_total());
						newregisterEntity.setStatus("Open");
						registerRepo.save(newregisterEntity);

						LeoLogger.info("SaleServiceImpl---converquotestToSale---After New Register Entry made"+newregisterEntity.getSalesamount());

					}

				}

				resultVO.setMsgDescr("Quote converted to Sale Sucessfully !");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;

	}

	@Override
	public List<BulkPaymentPojo> getBulkPaymentListbyMemberId(long memberId) {
		List<BulkPaymentEntity> bulkpaymentEntityList = bulkpaymentRepo.findAllByMemberIdOrderByBulkIdDesc(memberId);
		List<BulkPaymentPojo> bulkpaymentPojoList = new ArrayList<BulkPaymentPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getBulkPaymentListbyMemberId--in  getBulkPaymentListbyMemberId");
			for (BulkPaymentEntity bkpaymentEntityRes : bulkpaymentEntityList) {

				BulkPaymentPojo bulkPaymentPojo = new BulkPaymentPojo();
				bulkPaymentPojo = mapper.map(bkpaymentEntityRes, BulkPaymentPojo.class);
				bulkpaymentPojoList.add(bulkPaymentPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bulkpaymentPojoList;
	}

	@Override
	public List<PaymentPojo> getPaymentListbyMemberId(long memberId) {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllBymemberidOrderByIdDesc(memberId);
		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId");
			for (PaymentEntity paymentEntityRes : paymentEntityList) {
				// if (paymentEntityRes.getBulkid() == 0) {

				PaymentPojo PaymentPojo = new PaymentPojo();
				PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
				paymentPojoList.add(PaymentPojo);
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public List<PaymentPojo> getPaymentListOnlybyMemberId(long memberId) {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllBymemberidOrderByIdDesc(memberId);
		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getPaymentListOnlybyMemberId--in  getPaymentListOnlybyMemberId");
			for (PaymentEntity paymentEntityRes : paymentEntityList) {
				if (paymentEntityRes.getBulkid() <= 0) {
					if (!paymentEntityRes.getPtype().equalsIgnoreCase("CR")) {
					//	LeoLogger.info("SaleServiceImpl---Adding Payment pojo listing >>>>>>>>>"
					//			+ paymentEntityRes.getBulkid());
						/*LeoLogger.info("SaleServiceImpl---Adding Payment pojo listing >>>>>>>>>"
								+ paymentEntityRes.getSalesreferenceno());*/
						PaymentPojo PaymentPojo = new PaymentPojo();
						PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
						paymentPojoList.add(PaymentPojo);
						/*LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId"
								+ paymentPojoList.toString());*/
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public List<PaymentPojo> getCRPaymentListOnlybyMemberId(long memberId) {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllBymemberidOrderByIdDesc(memberId);
		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getPaymentListOnlybyMemberId--in  getPaymentListOnlybyMemberId");
			for (PaymentEntity paymentEntityRes : paymentEntityList) {
				if (paymentEntityRes.getBulkid() <= 0) {
					if (paymentEntityRes.getPtype().equalsIgnoreCase("CR")) {
					//	LeoLogger.info("SaleServiceImpl---Adding Payment pojo listing >>>>>>>>>"
					//			+ paymentEntityRes.getBulkid());
					//	LeoLogger.info("SaleServiceImpl---Adding Payment pojo listing >>>>>>>>>"
					//			+ paymentEntityRes.getSalesreferenceno());
						PaymentPojo PaymentPojo = new PaymentPojo();
						PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
						paymentPojoList.add(PaymentPojo);
						/*
						 * LeoLogger.
						 * info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId"
						 * + paymentPojoList.toString());
						 */
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public double paymenttoatl(long memberId,String Ctype) {
		List<PaymentEntity> paymetEntity = paymentRepo.findAllBymemberidAndCtype(memberId,Ctype);
		double grand_total = 0.0;

		if (paymetEntity != null)
			for (PaymentEntity paymentEnt : paymetEntity) {
				if (!paymentEnt.getPtype().equalsIgnoreCase("CR") && !paymentEnt.getPtype().equalsIgnoreCase("CA")) {
					grand_total = grand_total + paymentEnt.getGrand_total();
				}

			}

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

		return bd_grand_total.doubleValue();
	}

	@Override
	public double creditreturn(long memberId) {
		List<PaymentEntity> paymetEntity = paymentRepo.findAllBymemberid(memberId);
		double grand_total = 0.0;

		if (paymetEntity != null)
			for (PaymentEntity paymentEnt : paymetEntity) {
				if (paymentEnt.getPtype().equalsIgnoreCase("CR") ) {
					grand_total = grand_total + paymentEnt.getGrand_total();
				}

			}

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

		return bd_grand_total.doubleValue();
	}

	@Override
	public double specialcreditreturn(long memberId) {
		List<PaymentEntity> paymetEntity = paymentRepo.findAllBymemberid(memberId);
		double grand_total = 0.0;

		if (paymetEntity != null)
			for (PaymentEntity paymentEnt : paymetEntity) {
				if (paymentEnt.getPtype().equalsIgnoreCase("CR")) {
					grand_total = grand_total + paymentEnt.getGrand_total();
				}

			}

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

		return bd_grand_total.doubleValue();
	}

	@Override
	public double refund(long memberId) {
		int isActive = 0;
		List<ReturnCashEntity> returnEntity = returnscashRepo.findAllByMemberidAndIsActive(memberId, isActive);
		double grand_total = 0.0;

		if (returnEntity != null)
			for (ReturnCashEntity returnEnt : returnEntity) {

				grand_total = grand_total + (returnEnt.getAmount() + returnEnt.getTax());

			}

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

		return bd_grand_total.doubleValue();
	}

	@Override
	public double saletoatl(long memberId) {
		List<SalesEntity> salesEntity = salesRepo.findAllByMemberid(memberId);
		double grand_total = 0.0, paid = 0.0, total = 0.0;

		if (salesEntity != null)
			for (SalesEntity salesEnt : salesEntity) {
				grand_total = grand_total + salesEnt.getGrand_total();
				paid = paid + salesEnt.getPaid();

				total = grand_total - paid;

			}
		BigDecimal bd_total = new BigDecimal(total);
		bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);

		return bd_total.doubleValue();
	}

	@Override
	public List<FinancialTransactionPojo> getFinancialTransactionByCustomerId(long memberId) {
		List<FinancialTransactionEntity> financialtransactionEntityList = financialTransactionsRepo
				.findByCustomerIdOrderByFanIdDesc(memberId);
		List<FinancialTransactionPojo> financialtransactionPojoList = new ArrayList<FinancialTransactionPojo>();
		try {
			LeoLogger.info(
					"SaleServiceImpl---getFinancialTransactionByCustomerId---in  getFinancialTransactionPojoListbyMemberId");
			for (FinancialTransactionEntity financialtransactionEntityRes : financialtransactionEntityList) {

				FinancialTransactionPojo financialtransactionPojo = new FinancialTransactionPojo();
				financialtransactionPojo = mapper.map(financialtransactionEntityRes, FinancialTransactionPojo.class);
				financialtransactionPojoList.add(financialtransactionPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return financialtransactionPojoList;
	}

	@Override
	public ResultVO updateEditDetails(RequestQuotesItemsPojo requestQuotesItemsPojo) {
		LeoLogger.info("SaleServiceImpl---updateEditDetails---Printing Here Product Object : "
				+ requestQuotesItemsPojo.toString());
		ResultVO resultVO = new ResultVO();
		try {
			LeoLogger.info("SaleServiceImpl---updateEditDetails");

			RequestQuoteItemEntity requestQuoteEntityRes = requestQuoteItemRepo
					.findByProductid(requestQuotesItemsPojo.getProductid());

			if (requestQuoteEntityRes != null) {
				// LeoLogger.info("SaleServiceImpl---updateEditDetails---in not null");

				requestQuoteEntityRes.setProduct_id(requestQuotesItemsPojo.getProductid());
				requestQuoteEntityRes.setProduct_name(requestQuotesItemsPojo.getProductname());
				requestQuoteEntityRes.setProduct_type(requestQuotesItemsPojo.getProducttype());
				requestQuoteEntityRes.setQuantity(requestQuotesItemsPojo.getQuantity());
				requestQuoteEntityRes.setMpn(requestQuotesItemsPojo.getMpn());

				requestQuoteItemRepo.save(requestQuoteEntityRes);

				resultVO.setMsgDescr("Request quotes Updated Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				resultVO.setMsgDescr("Not Found");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO updateSale(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("SaleServiceImpl---updateSale ");

			Long saleId = 0L;
			BigDecimal Old_sale_grand_total = new BigDecimal(0.0);
			BigDecimal New_sale_grand_total = new BigDecimal(0.0);
			BigDecimal Diff_amount = new BigDecimal(0.0);
			BigDecimal old_returnqty = new BigDecimal(0.0);
			SalesEntity saleEntity = new SalesEntity();

			List<SalesItemEntity> salesItemEntityList = new ArrayList<SalesItemEntity>();

			// Get Sales Entity and Sales Items Entity using the sale id received
			for (AddItemReqPojo additem : addItemReqPojos) {
				saleId = additem.getSaleId();
			}
			// LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> saleId is >>> " +
			// saleId.toString());

			if (saleId != null) {
				saleEntity = salesRepo.findBySaleId(saleId);
				salesItemEntityList = salesItemRepo.findBySaleid(saleId);

				// LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> saleEntity is >>> " +
				// saleEntity.toString());
				// LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> salesItemEntityList is
				// >>> " + salesItemEntityList.toString());

				Old_sale_grand_total = new BigDecimal(saleEntity.getGrand_total());

			}
			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());
			LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> salesamount before deleted >>> " + registerEntity.getSalesamount());
			if (registerEntity != null)

			{
				registerEntity.setSalesamount(registerEntity.getSalesamount() - Old_sale_grand_total.doubleValue());
				registerRepo.save(registerEntity);
				LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> salesamount after deleted >>> " + registerEntity.getSalesamount());
			}

			// Add quantity of products in productdetails for Sales Items received from the
			// above saleitem entity

			for (SalesItemEntity salesItmAdd : salesItemEntityList) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo
						.findByProductId(salesItmAdd.getProduct_id());
				LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> Product Quantity for product "
						+ productDetailsEnt.getname() + "  Before is >>> " + productDetailsEnt.getQuantity());
				productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add( salesItmAdd.getQuantity()) );
				productDetailsRepo.save(productDetailsEnt);
				LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> Product Quantity for product "
						+ productDetailsEnt.getname() + "  After is >>> " + productDetailsEnt.getQuantity());
			}

			// Delete all sales items in salesitems table against the sale id

			salesItemRepo.deleteAll(salesItemEntityList);
			// Update Existing Sale Entity instead of making a new one

			double grand_total = 0, tax_rate = 0, total = 0, unit_price = 0;
			int quantity = 0;

			// Declare Bigdecimal equivalents
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			String Ctype = "";

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());

				// saleEntity.setDate(new Date());
				saleEntity.setMemberid(memberPojo.getId());
				saleEntity.setMember_name(memberPojo.getName());
				Ctype = memberPojo.getCtype();
				// LeoLogger.info("SaleServiceImpl--updateSale----Member Pojo ....." +
				// memberPojo.toString());

				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());
				System.out.println(productDetailsPojo);

				if (productDetailsPojo != null)
					unit_price = additem.getPrice().doubleValue();
				quantity = Integer.parseInt(additem.getQuantity());

				/*
				 * This is Promotional discount on Product not applicable to WholeSellers
				 * if(!Ctype.equalsIgnoreCase("WholeSellers") &&
				 * productDetailsPojo.getpromotion() !=0 ) {
				 * 
				 * unit_price = unit_price -
				 * ((unit_price*productDetailsPojo.getpromotion())/100);
				 * LeoLogger.info("Applying Promotion for " + productDetailsPojo.getpromotion()
				 * + "% and the new Unit price now is ....." + unit_price);
				 * 
				 * }
				 */

				saleEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);

				if (additem.getTax().equalsIgnoreCase("YES")
						&& !memberPojo.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
					tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				} else {
					tax_rate = 0;
				}
				// saleEntity.setReferenceno("POS");

				saleEntity.setTotal_discount(0);

				saleEntity.setCf1(productDetailsPojo.getcf1());
				saleEntity.setPurchaseorder(additem.getPurchaseorder());

				// saleEntity.setUser_id();
			}

			grand_total = total + tax_rate;
			saleEntity.setOrder_tax(0);


			bd_tax_rate = new BigDecimal(tax_rate).setScale(2, RoundingMode.HALF_UP);
			tax_rate = bd_tax_rate.doubleValue();

			bd_total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
			total = bd_total.doubleValue();
			saleEntity.setProduct_tax(bd_tax_rate.doubleValue());
			saleEntity.setTotal_tax(bd_tax_rate.doubleValue());
			LeoLogger.info("SaleServiceImpl---updateSale---Tax Rate....." + bd_tax_rate);

			saleEntity.setPaymentstatus("Due");
			saleEntity.setOrder_discount(0);

			saleEntity.setTotal(bd_total.doubleValue());
			LeoLogger.info("SaleServiceImpl---updateSale---Total....." + bd_total);

			bd_grand_total = bd_total.add(bd_tax_rate).setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("SaleServiceImpl---addSale---Grand Total....." + bd_grand_total);
			saleEntity.setGrand_total(bd_grand_total.doubleValue());
			saleEntity.setGrandtotal(bd_grand_total.toString());
			LeoLogger.info("SaleServiceImpl---updateSale---Grand Total....." + bd_grand_total);
			saleEntity.setIsActive(0);

			New_sale_grand_total = bd_grand_total;

			salesRepo.save(saleEntity);
			UnitEntity unitentity = new UnitEntity();

			LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> saleId is >>> " + saleId.toString());

			// this loop is for sales breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {
				if (additem.getUnit() != null && additem.getUnit() != "") {
					System.out.println(additem.getUnit());
					unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				}

				LeoLogger.info("SaleServiceImpl---updateSale >>>>>>>> In Adding saleentity back saleId is >>> "
						+ saleId.toString());

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				double tax = 0;

				tax = (productDetailsEnt.getprice().doubleValue() * Long.parseLong(additem.getQuantity())) * .125;

				SalesItemEntity salesItemEntity = new SalesItemEntity();
				salesItemEntity.setSale_id(saleId);
				salesItemEntity.setProduct_id(additem.getProductId());
				salesItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
				salesItemEntity.setItem_tax(additem.getPrice().doubleValue() * 0.125);
				salesItemEntity.setGst("12.5");
				salesItemEntity.setItem_discount(0d);
				salesItemEntity.setProduct_code(additem.getProductId().toString());
				salesItemEntity.setProduct_name(additem.getProductName());
				salesItemEntity.setRoll(additem.getRoll());
				salesItemEntity.setReturnqty(old_returnqty.toString());
				salesItemEntity.setCost(productDetailsEnt.getcost());

				bd_real_unit_price = additem.getPrice();
				bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
				salesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
				salesItemEntity.setSale_item_id(unitentity.getId());
				salesItemEntity.setUnit_quantity(unitentity.getUnitname());

				bd_subtotal =additem.getSubtotal();
				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				salesItemEntity.setSubtotal(bd_subtotal.doubleValue());
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					// LeoLogger.info(""+unitentity.getQuantity());
					BigDecimal unit =( unitentity.getQuantity());
					System.out.println(qty);
					System.out.println(unit);
					qty = qty .multiply(unit) ;
					salesItemEntity.setQuantity(qty);
					// LeoLogger.info("SaleServiceImpl---addSale--qt....." +qty);
				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					salesItemEntity.setQuantity(qty);
				}

				salesItemEntity.setTax(Double.toString(tax));

				salesItemRepo.save(salesItemEntity);

				// Subtracting Quantities here from products

				// Subtracting Quantities here from products
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					// System.out.println(qty);
					qty = qty .multiply(unit) ;
					System.out.println(unit);
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(qty));
				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					productDetailsEnt
							.setQuantity(productDetailsEnt.getQuantity().subtract(qty));
				}
				productDetailsRepo.save(productDetailsEnt);

			}

			// <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

			// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = new Date();

			// convert date to calendar and add 30 days
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);

			Calendar calendarInstance = Calendar.getInstance();
			calendarInstance.add(Calendar.DATE, 30);

			FinancialTransactionEntity ftLatest = financialTransactionsRepo
					.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());

			FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

			ftEntity.setInvoideId(saleEntity.getSaleId());
			ftEntity.setReferenceno(saleEntity.getReferenceno());

			if (New_sale_grand_total.compareTo(Old_sale_grand_total) == 1) {
				Diff_amount = New_sale_grand_total.subtract(Old_sale_grand_total);
				Diff_amount = Diff_amount.setScale(2, RoundingMode.HALF_UP);
				ftEntity.setAmount(Diff_amount.doubleValue());
			} else {
				Diff_amount = New_sale_grand_total.subtract(Old_sale_grand_total);
				Diff_amount = Diff_amount.setScale(2, RoundingMode.HALF_UP);
				ftEntity.setAmount(Diff_amount.doubleValue());
			}

			ftEntity.setCustomerId(saleEntity.getMemberid());
			ftEntity.setCustomerName(saleEntity.getMember_name());
			ftEntity.setDate(new Date());
			ftEntity.setDueDate(calendarInstance.getTime());
			ftEntity.setType("Invoice Edit");

			if (ftLatest == null) {
				ftEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal ftTotal = new BigDecimal(0.0);
				// LeoLogger.info("SaleServiceImpl---updateSale--Latest Financial Transation
				// balance ....." + ftLatest.getBalance());
				ftTotal = Diff_amount.add(new BigDecimal(ftLatest.getBalance()));
				ftEntity.setBalance(ftTotal.doubleValue());
			}

			financialTransactionsRepo.save(ftEntity);

			// ************************ Financial transaction Entry ended
			// *******************
			// salesItemRepo.deleteAll(salesItemEntityList);

			// ******FT Entry start******
			FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());
			// fTRepo.deleteAll(salesItemEntityList);
			// LeoLogger.info("SaleServiceImpl---updateSale--FTEntity ....." +
			// fttLatest.getType());
			List<FTEntity> fttList = fTRepo.findByCustomerIdAndInvoideIdAndType(saleEntity.getMemberid(),
					saleEntity.getSaleId(), "Invoice");
			LeoLogger.info("SaleServiceImpl---updateSale--FTEntity  ....." + fttList);

			fTRepo.deleteAll(fttList);

			FTEntity fttEntity = new FTEntity();

			fttEntity.setInvoideId(saleEntity.getSaleId());
			fttEntity.setAmount(bd_grand_total.doubleValue());
			fttEntity.setCustomerId(saleEntity.getMemberid());
			fttEntity.setCustomerName(saleEntity.getMember_name());
			fttEntity.setDate(new Date());
			fttEntity.setDueDate(calendarInstance.getTime());
			fttEntity.setReferenceno(saleEntity.getReferenceno());

			fttEntity.setType("Invoice");

			if (fttLatest == null) {
				fttEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal fttTotal = new BigDecimal(0.0);
				// BigDecimal ftbalance = new BigDecimal(fttLatest.getAmount());
				BigDecimal ftrTotal = new BigDecimal(fttLatest.getBalance());
				ftrTotal = ftrTotal.setScale(2, RoundingMode.HALF_UP);
				LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....." + Diff_amount);
				LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....."
						+ fttLatest.getBalance());
				fttTotal = Diff_amount.add(ftrTotal);
				fttEntity.setBalance(fttTotal.doubleValue());
			}

			fTRepo.save(fttEntity);
			// ******FT Entry ended******

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
			// >>>>>>>>>>>>>>>>>>>>>>>>>>

			RegisterEntity rregisterEntity = new RegisterEntity();
			List<RegisterEntity> entities = registerRepo.findAllByOrderByIdDesc();

			rregisterEntity = registerRepo.findAByDate(new Date());
			
			if (rregisterEntity != null)

			{
				rregisterEntity=entities.get(0);
				LeoLogger.info(" SaleServiceImpl---updateSale--Salesamount before Register entry found for today  ....."
						+ rregisterEntity.getSalesamount());
				rregisterEntity.setSalesamount(rregisterEntity.getSalesamount() + bd_grand_total.doubleValue());
				rregisterEntity.setReferenceno(rregisterEntity.getReferenceno() + "," + saleEntity.getSaleId());
				registerRepo.save(rregisterEntity);
				LeoLogger.info(" SaleServiceImpl---updateSale--Salesamount after Register entry found for today  ....."
						+ rregisterEntity.getSalesamount());
			} else {
				
				RegisterEntity newregisterEntity = new RegisterEntity();
				LeoLogger.info("SaleServiceImpl--- updateSale-Before-New Register Entry made"+newregisterEntity.getSalesamount());
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				newregisterEntity.setCashpayment(0.00);
				newregisterEntity.setCreditcardpayment(0.00);
				newregisterEntity.setOpeningbal(1000.00);
				newregisterEntity.setClosingbal(1000.00);
				newregisterEntity.setChequepayment(0.00);
				newregisterEntity.setRefunds(0.00);
				newregisterEntity.setReferenceno(String.valueOf(saleEntity.getSaleId()));
				newregisterEntity.setSalesamount(bd_grand_total.doubleValue());
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);

				LeoLogger.info("SaleServiceImpl--- updateSale-After-New Register Entry made"+newregisterEntity.getSalesamount());

			}

			// **************************** Register Details Entry ended
			// ********************

			// ****Registerhistory entry***//
			applyRegisterhistorysaleedit(saleEntity);

			resultVO.setMsgDescr("Sale Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	@Override
	public List<RegisterPojo> getRegisterList() {
		List<RegisterEntity> registerEntityList = new ArrayList<RegisterEntity>();
		List<RegisterPojo> registerPojoList = new ArrayList<RegisterPojo>();
		 LocalDate today = LocalDate.now();
		LeoLogger.info("RegisterPojo---RegisterPojo -today--  " + today);
		
		try {
			LeoLogger.info("SaleServiceImpl---getRegisterList---in Register");
			registerEntityList = registerRepo.findAllByOrderByIdDesc();
			for (RegisterEntity registerEntityEntityEntityRes : registerEntityList) {

				RegisterPojo registerPojo = new RegisterPojo();
				registerPojo = mapper.map(registerEntityEntityEntityRes, RegisterPojo.class);
				registerPojoList.add(registerPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return registerPojoList;
	}

	@Override
	public ResultVO closeregister(long rid) {
		ResultVO resultVO = new ResultVO();

		try {

			RegisterEntity registerEntity = registerRepo.findByid((rid));
			LeoLogger.info("SaleServiceImpl---closeregister--*********** rid==" + rid);
			if (registerEntity != null)

			{
				registerEntity.setStatus("Closed");
				registerRepo.save(registerEntity);
				resultVO.setMsgDescr("Register Closed Sucessfully !");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;

	}

	@Override
	public SalePojo getsalebySaleId(long id) {
		SalesEntity saleEntity = new SalesEntity();
		saleEntity = salesRepo.findBySaleId(id);
		LeoLogger.info("SaleServiceImpl---getsalebySaleId---" + saleEntity.toString());
		SalePojo salePojo = new SalePojo();
		salePojo = mapper.map(saleEntity, SalePojo.class);
		salePojo.setCreatedBy(saleEntity.getCreatedBy());
		return salePojo;
	}

	@Override
	public UserRegistrationPojo getMemberByMemberid(long memberid) {
		MemberUser memberEntity = memberUserRepo.findById(memberid);
		UserRegistrationPojo memberPojo = new UserRegistrationPojo();
		memberPojo = mapper.map(memberEntity, UserRegistrationPojo.class);
		return memberPojo;
	}

	@Override
	public List<SaleReportSummaryPojo> findAllSaleSummary(String startDate, String endDate) {
		LeoLogger.info("SalesServiceImpl ---salesReport");
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));
			
		}
		return salesRepo.findAllSaleSummary(startDate, endDate);
	
	}

	private String getFormattedDate(@NonNull LocalDate date) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	@Override
	public QuotesPojo getquotebyquotesId(long id) {
		QuotesEntity quoteEntity = new QuotesEntity();
		quoteEntity = quotesRepo.findByquotesId(id);
		LeoLogger.info("SaleServiceImpl---getquotebyquotesId---" + quoteEntity.toString());
		QuotesPojo quotesPojo = new QuotesPojo();
		quotesPojo = mapper.map(quoteEntity, QuotesPojo.class);
		quotesPojo.setCreatedBy(quoteEntity.getCreatedBy());
		return quotesPojo;
	}

	@Override
	public ResultVO updateQuotes(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("SaleServiceImpl---updateQuotes >>>>>>>> addItemReqPojos >>> " + addItemReqPojos);

			Long quotesId = addItemReqPojos.get(0).getSaleId();

			LeoLogger.info("SaleServiceImpl---updateQuotes >>>>>>>> QuotesId  >>> " + quotesId);

			QuotesEntity quotesEntity = quotesRepo.findByquotesId(quotesId);
			List<QuotesItemEntity> quotesItemEntityList = quotesItemRepo.findByQuotesid(quotesId);

			LeoLogger.info("SaleServiceImpl---updateQuotes >>>>>>>> quotesEntity >>> " + quotesEntity.toString());
			LeoLogger.info("SaleServiceImpl---updateQuotes >>>>>>>> quotesItemEntityList  >>> "
					+ quotesItemEntityList.toString());

			quotesItemRepo.deleteAll(quotesItemEntityList);

			double tax_rate = 0, total = 0, quantity = 0, unit_price = 0, subTotal = 0, subTax = 0;
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			BigDecimal bd_subTax = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price_change = new BigDecimal(0.0);

			MemberUser memberPojo = memberUserRepo.findById(addItemReqPojos.get(0).getCustomerId());
			UnitEntity unitentity = new UnitEntity();
		//	SalesPercentageEntity salesPercentageEntity = salespercentRepo.findByCtype(memberPojo.getCtype());
			SalesPercentageEntity salesPercentageEntity = salespercentRepo.findByCtypeAndPricegroup(memberPojo.getCtype(),memberPojo.getPricegroup());
			ProductDetailsEntity productDetailsPojo = null;

			quotesEntity.setNote(addItemReqPojos.get(0).getNote());

			QuotesEntity quote = quotesRepo.save(quotesEntity);

			LeoLogger.info("SaleServiceImpl---updateQuotes----Member id---- " + memberPojo.getId());
			LeoLogger.info("SaleServiceImpl---updateQuotes----Member type---- " + memberPojo.getCtype());
			LeoLogger.info("SaleServiceImpl---updateQuotes----Member price group---- " + memberPojo.getPricegroup());
			LeoLogger.info(
					"SaleServiceImpl---updateQuotes----Sales percentage---- " + salesPercentageEntity.getPercentage());

			for (AddItemReqPojo additem : addItemReqPojos) {

				productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				LeoLogger.info("SaleServiceImpl---updateQuotes----Product Details---- " + productDetailsPojo);

				unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				LeoLogger.info("SaleServiceImpl---updateQuotes----Product unit---- " + unitentity.getUnitname());

				if (memberPojo.getCtype().equalsIgnoreCase("Special")
						&& memberPojo.getPricegroup().equalsIgnoreCase("WholeSellers")) {

					LeoLogger.info(
							"SaleServiceImpl---updateQuotes---Member Type is Special and price group is WholeSellers ");

					if ((additem.getRoll().equalsIgnoreCase("Piece")) || (additem.getRoll().equalsIgnoreCase("ft")) || (additem.getRoll().equalsIgnoreCase("10Ft"))) {
						unit_price = productDetailsPojo.getprice().doubleValue();
						LeoLogger.info("SaleServiceImpl---updateQuotes---Product unit price for piece and ft---- "
								+ productDetailsPojo.getprice());

					}else if(additem.getRoll().equalsIgnoreCase("20Ft")) {
						unit_price = (productDetailsPojo.getprice().doubleValue())*2;
					}
					else if (additem.getRoll().equalsIgnoreCase("Box12")) {
						unit_price = productDetailsPojo.getrollprice();
						LeoLogger.info("SaleServiceImpl---updateQuotes---Product unit  price for box---- "
								+ productDetailsPojo.getrollprice());
						if (salesPercentageEntity.getPercentage() > 0) {
							unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
									* salesPercentageEntity.getPercentage() / 100));

							LeoLogger.info(
									"SaleServiceImpl---updateQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);

						}

					} else {
						unit_price = productDetailsPojo.getrollprice();
						LeoLogger.info(
								"SaleServiceImpl---updateQuotes---Product unit  price for wire and roll unit ---- "
										+ productDetailsPojo.getrollprice());
					}

					bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

				} else {
					if (salesPercentageEntity.getPercentage() > 0) {

						if ((additem.getRoll().equalsIgnoreCase("Piece"))
								|| (additem.getRoll().equalsIgnoreCase("ft")) || (additem.getRoll().equalsIgnoreCase("10Ft"))) {

							LeoLogger.info("SaleServiceImpl---updateQuotes---Product unit price for piece and ft---- "
									+ productDetailsPojo.getprice());
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---updateQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);

						}else if(additem.getRoll().equalsIgnoreCase("20Ft")) {
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue()* salesPercentageEntity.getPercentage() / 100));
							unit_price=unit_price*2;

						}
						else if (additem.getRoll().equalsIgnoreCase("Box12")) {

							LeoLogger.info("SaleServiceImpl---updateQuotes---Product unit  price for box---- "
									+ productDetailsPojo.getrollprice());
							unit_price = productDetailsPojo.getrollprice();
							if (salesPercentageEntity.getPercentage() > 0) {
								unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
										* salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---updateQuotes---Product unit  price after sale percentage  ---- "
												+ unit_price);
							}

						} else {

							LeoLogger.info(
									"SaleServiceImpl---updateQuotes---Product unit  price for wire and roll unit ---- "
											+ productDetailsPojo.getrollprice());
							unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
									* salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---updateQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);
						}

						bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

						if (productDetailsPojo.getpromotion() > 0) {
							if (!memberPojo.getCtype().equalsIgnoreCase("WholeSellers")) {

								LeoLogger.info("SaleServiceImpl---updateQuotes---Product promotion percentage---- "
										+ productDetailsPojo.getpromotion());
								unit_price = (bd_real_unit_price.doubleValue()
										- ((bd_real_unit_price.doubleValue() * productDetailsPojo.getpromotion())
												/ 100));
								LeoLogger.info(
										"SaleServiceImpl---updateQuotes---Product unit price after subtracting promotion percentage---- "
												+ unit_price);
							}
							if((additem.getRoll().equalsIgnoreCase("20Ft"))){
								unit_price = (bd_real_unit_price.doubleValue()
										- ((bd_real_unit_price.doubleValue() * productDetailsPojo.getpromotion())
												/ 100));
								unit_price = unit_price*2;
							}
						}

						bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

					} else {
						if ((additem.getRoll().equalsIgnoreCase("Piece"))
								|| (additem.getRoll().equalsIgnoreCase("ft")) || (additem.getRoll().equalsIgnoreCase("10Ft"))) {

							LeoLogger.info("SaleServiceImpl---updateQuotes---Product unit price for piece and ft---- "
									+ productDetailsPojo.getprice());
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---updateQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);

						} else if(additem.getRoll().equalsIgnoreCase("20Ft")) {
							unit_price = (productDetailsPojo.getprice().doubleValue()
									+ (productDetailsPojo.getprice().doubleValue() * salesPercentageEntity.getPercentage() / 100));
							unit_price=unit_price*2;
						}else if (additem.getRoll().equalsIgnoreCase("Box12")) {
							LeoLogger.info("SaleServiceImpl---updateQuotes---unit_price box" + unit_price);
							unit_price = productDetailsPojo.getrollprice();
							if (salesPercentageEntity.getPercentage() > 0) {
								unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
										* salesPercentageEntity.getPercentage() / 100));
								LeoLogger.info(
										"SaleServiceImpl---updateQuotes---Product unit  price after sale percentage  ---- "
												+ unit_price);

							}

						} else {
							LeoLogger.info(
									"SaleServiceImpl---updateQuotes---Product unit  price for wire and roll unit ---- "
											+ productDetailsPojo.getrollprice());
							unit_price = (productDetailsPojo.getrollprice() + (productDetailsPojo.getrollprice()
									* salesPercentageEntity.getPercentage() / 100));
							LeoLogger.info(
									"SaleServiceImpl---updateQuotes---Product unit  price after sale percentage  ---- "
											+ unit_price);
						}
						bd_real_unit_price = new BigDecimal(unit_price).setScale(2, RoundingMode.HALF_UP);

					}
				}

				LeoLogger
						.info("SaleServiceImpl---updateQuotes-- unit price after rounding  ....." + bd_real_unit_price);

				quantity = Long.parseLong(additem.getQuantity());

				LeoLogger.info("SaleServiceImpl---updateQuotes----Product quantity for quote---- " + quantity);

				if (additem.getIsPriceChange() == 1) {

					if (additem.getTax().equalsIgnoreCase("YES")) {
						tax_rate = tax_rate + (additem.getPrice().doubleValue() * 0.125 * quantity);
					} else {
						tax_rate = 0;
					}

				} else {

					if (additem.getTax().equalsIgnoreCase("YES")) {
						tax_rate = tax_rate + (unit_price * 0.125 * quantity);
					} else {
						tax_rate = 0;
					}

				}
				if (memberPojo.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
					tax_rate = 0;
				}
				LeoLogger.info("SaleServiceImpl---addQuotes(special)----tax_rate---- " + tax_rate);

				if (additem.getIsPriceChange() == 1) {

					LeoLogger.info("SaleServiceImpl---updateQuotes----Product unit price---- " + bd_real_unit_price);

					bd_real_unit_price_change = additem.getPrice().setScale(2, RoundingMode.HALF_UP);

					LeoLogger.info("SaleServiceImpl---updateQuotes----Product unit price  change to---- "
							+ bd_real_unit_price_change);

					subTotal = bd_real_unit_price_change.doubleValue() * quantity;
					total += bd_real_unit_price_change.doubleValue() * quantity;

				} else {

					subTotal = bd_real_unit_price.doubleValue() * quantity;
					total += bd_real_unit_price.doubleValue() * quantity;
				}

				bd_tax_rate = new BigDecimal(tax_rate).setScale(2, RoundingMode.HALF_UP);
				tax_rate = bd_tax_rate.doubleValue();

				bd_total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
				total = bd_total.doubleValue();

				LeoLogger.info("SaleServiceImpl---updateQuotes-- tax ...." + bd_tax_rate);
				LeoLogger.info("SaleServiceImpl---updateQuotes-- subtotal  ....." + bd_total);

				QuotesItemEntity quotesItemEntity = new QuotesItemEntity();
				quotesItemEntity.setQuotesid(quote.getQuotesId());
				quotesItemEntity.setProduct_id(additem.getProductId());
				quotesItemEntity.setItem_tax(additem.getPrice().doubleValue() * 0.125);
				quotesItemEntity.setGst("12.5");
				quotesItemEntity.setItem_discount(0d);
				quotesItemEntity.setProduct_code(additem.getProductId().toString());
				quotesItemEntity.setProduct_name(additem.getProductName());
				quotesItemEntity.setRoll(unitentity.getUnitname());
				quotesItemEntity.setSale_item_id(unitentity.getId());

				if (additem.getIsPriceChange() == 1) {
					quotesItemEntity.setReal_unit_price(bd_real_unit_price_change.doubleValue());
				} else {

					quotesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
				}

				bd_subtotal = new BigDecimal(subTotal).setScale(2, RoundingMode.HALF_UP);
				quotesItemEntity.setSubtotal(bd_subtotal.doubleValue());
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece") && !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit =( unitentity.getQuantity());
					System.out.println(qty);
					System.out.println(unit);
					qty = qty .multiply(unit) ;
					quotesItemEntity.setQuantity(qty);

				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					quotesItemEntity.setQuantity(qty);
				}

				bd_subTax = new BigDecimal(subTax).setScale(2, RoundingMode.HALF_UP);
				quotesItemEntity.setTax(Double.toString(bd_subTax.doubleValue()));

				quotesItemEntity.setIsPriceChange(additem.getIsPriceChange());

				quotesItemRepo.save(quotesItemEntity);

			}

			LeoLogger.info("SaleServiceImpl---updateQuotes--Total tax  ..." + bd_tax_rate);

			quotesEntity.setProduct_tax(bd_tax_rate.doubleValue());
			quotesEntity.setTotal_tax(bd_tax_rate.doubleValue());

			LeoLogger.info("SaleServiceImpl---updateQuotes--Total subtotal....." + bd_total);
			quotesEntity.setTotal(bd_total.doubleValue());

			bd_grand_total = bd_total.add(bd_tax_rate).setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("SaleServiceImpl---updateQuotes---Grand Total....." + bd_grand_total);
			quotesEntity.setGrandtotal(bd_grand_total.toString());

			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;

			quotesEntity.setReferenceno("QUOTES" + currentYear + "/" + currentmonth + "/" + quote.getQuotesId());
			quotesRepo.save(quotesEntity);

			resultVO.setMsgDescr("Quote Updated Sucessfully!");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	@Override
	public RequestQuoteEntity findRequestQuoteById(Long requestQuoteId) {
		Optional<RequestQuoteEntity> optRequestQuoteEntity = requestQuoteRepo.findById(requestQuoteId);
		if (optRequestQuoteEntity.isPresent()) {
			return optRequestQuoteEntity.get();
		}
		return null;
	}

	@Override
	public ResultVO sendEmail(long id, String profile) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		RequestQuoteEntity requestQuoteEntity = findRequestQuoteById(id);

		if (requestQuoteEntity != null && requestQuoteEntity.getFileName() != null
				&& !requestQuoteEntity.getFileName().isEmpty()) {
			mailSendingAPI.sendMail(requestQuoteEntity.getEmail(), requestQuoteEntity.getFileName(), pdfPath);
			resultVO.setMsgDescr("Email Sent Sucessfully");
			resultVO.setError(false);
		}
		return resultVO;
	}
	
	@Override
	public ResultVO sendEmailR(long id, String profile,String customMessage) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		RequestQuoteEntity requestQuoteEntity = findRequestQuoteById(id);

		if (requestQuoteEntity != null && requestQuoteEntity.getFileName() != null
				&& !requestQuoteEntity.getFileName().isEmpty()) {
			mailSendingAPI.sendMailR(requestQuoteEntity.getEmail(), requestQuoteEntity.getFileName(), pdfPath,customMessage);
			resultVO.setMsgDescr("Email Sent Sucessfully");
			resultVO.setError(false);
		}
		return resultVO;
	}

	@Override
	public List<SpecialSalesPojo> getPendingSpecialSalesBymemberId(String memberId) {

		List<SpecialSalesEntity> specialsalesEntityList = specialsalesRepo
				.findAllByMemberidAndPaymentstatusAndIsActiveOrderBySaleIdAsc(Long.parseLong(memberId), "Due", 0);
		List<SpecialSalesPojo> specialsalesPojoList = new ArrayList<SpecialSalesPojo>();
		try {
			LeoLogger.info(
					"SaleServiceImpl---getPendingSalesBymemberId--in Sales Service impl to get Due Special Sales Items by MemberId");

			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {
				SpecialSalesPojo specialsalePojo = new SpecialSalesPojo();
				specialsalePojo = mapper.map(specialsalesEntityRes, SpecialSalesPojo.class);
				specialsalesPojoList.add(specialsalePojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// LeoLogger.info("SaleServiceImpl---getPendingSpeicalSalesBymemberId---specialsalesPojoList
		// ....." + specialsalesPojoList.toString());
		return specialsalesPojoList;
	}

	@Override
	public List<SalePojo> getPendingSalesBymemberId(String memberId) {
		List<SalesEntity> salesEntityList = salesRepo
				.findAllByMemberidAndPaymentstatusAndIsActiveOrderBySaleIdAsc(Long.parseLong(memberId), "Due", 0);
		List<SalePojo> salesPojoList = new ArrayList<SalePojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getPendingSalesBymemberId--in Sales get Pending Sales Items by saleId");

			for (SalesEntity salesEntityRes : salesEntityList) {
				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityRes, SalePojo.class);
				salesPojoList.add(salePojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// LeoLogger.info("SaleServiceImpl---getPendingSalesBymemberId---salesItemPojoList
		// ....." + salesPojoList.toString());
		return salesPojoList;
	}

	@Override
	public ResultVO sendEmailForSale(long id) {
		SalesEntity sale = salesRepo.findBySaleId(id);
		if (sale != null) {
			MemberUser memberUser = memberUserRepo.findById(sale.getMemberid());
			LeoLogger.info("SaleServiceImpl---memberUser-" +memberUser);
			LeoLogger.info("SaleServiceImpl---memberUser.getEmail()-" +memberUser.getEmail());
			LeoLogger.info("SaleServiceImpl---sale.getFileName()-" +sale.getFileName());
			LeoLogger.info("SaleServiceImpl---salePdfPath-" +salePdfPath);
			mailSendingAPI.sendMail(memberUser.getEmail(), sale.getFileName(), salePdfPath);
			
			return new ResultVO("001", "Email Sent Sucessfully", false, "");
		}

		return new ResultVO("000", "Something Went Wrong", true, "");
	}

	@Override
	public SalePojo getSpecialsalebySaleId(long id) {
		SpecialSalesEntity specialSalesEntity = new SpecialSalesEntity();
		specialSalesEntity = specialsalesRepo.findBySaleId(id);
		LeoLogger.info("SaleServiceImpl---getSpecialsalebySaleId--" + specialSalesEntity.toString());
		SalePojo salePojo = new SalePojo();
		salePojo = mapper.map(specialSalesEntity, SalePojo.class);
		salePojo.setCreatedBy(specialSalesEntity.getCreatedBy());
		return salePojo;
	}

	@Override
	public List<SpecialSalesPojo> getSpecialSalesListbyMemberId(long memberId) {
		LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in  getSalesListbyMemberId" + memberId);
		List<SpecialSalesEntity> specialsalesEntityList = specialsalesRepo.findAllByMemberidOrderBySaleId(memberId);
		List<SpecialSalesPojo> specialsalesPojoList = new ArrayList<SpecialSalesPojo>();
		// specialsalesEntityList = specialsalesRepo.findAllByOrderBySaleIdDesc();
		try {
			LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in  getSalesListbyMemberId");
			// salesEntityList = salesRepo.findAllByMemberid(memberId);
			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {
				if(specialsalesEntityRes.getIsActive()==0) {

				SpecialSalesPojo salePojo = new SpecialSalesPojo();
				salePojo = mapper.map(specialsalesEntityRes, SpecialSalesPojo.class);

				specialsalesPojoList.add(salePojo);
				// LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in
				// getSalesListbyMemberId"+salePojo.toString());)
		//		LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in  getSalesListbyMemberId---list"
		//				+ specialsalesPojoList);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return specialsalesPojoList;
	}

	@Override
	public double specialcaltoatl(long memberId) {
		// List<SalesEntity> salesEntity = salesRepo.findAllByMemberid(memberId);
		List<SpecialSalesEntity> specialsalesEntity = specialsalesRepo.findAllByMemberid(memberId);
		double grand_total = 0.0;

		if (specialsalesEntity != null) {
			for (SpecialSalesEntity salesEnt : specialsalesEntity)
				if (salesEnt.getIsActive() == 0)
					grand_total = grand_total + salesEnt.getGrand_total();
		}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.3f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();

	}

	@Override
	public double specialpaymenttoatl(long memberId,String Ctype) {
		List<PaymentEntity> paymetEntity = paymentRepo.findAllBymemberidAndCtype(memberId,Ctype);
		double grand_total = 0.0;

		if (paymetEntity != null)
			for (PaymentEntity paymentEnt : paymetEntity) {
				if (!paymentEnt.getPtype().equalsIgnoreCase("CR")) {
					grand_total = grand_total + paymentEnt.getGrand_total();
				}

			}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.3f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

		return bd_grand_total.doubleValue();
	}

	@Override
	public ResultVO updateSpecialSales(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			Long saleId = 0L;
			BigDecimal Old_sale_grand_total = new BigDecimal(0.0);
			BigDecimal New_sale_grand_total = new BigDecimal(0.0);
			BigDecimal Diff_amount = new BigDecimal(0.0);
			SpecialSalesEntity specialsaleEntity = new SpecialSalesEntity();

			List<SpecialSalesItemEntity> specialsalesItemEntityList = new ArrayList<SpecialSalesItemEntity>();

			// Get Sales Entity and Sales Items Entity using the sale id received
			for (AddItemReqPojo additem : addItemReqPojos) {
				saleId = additem.getSaleId();
			}
			LeoLogger.info("SaleServiceImpl---updateSpecialSale >>>>>>>> saleId is >>> " + saleId.toString());

			if (saleId != null) {
				specialsaleEntity = specialsalesRepo.findBySaleId(saleId);
				specialsalesItemEntityList = specialsalesItemRepo.findBySaleid(saleId);

				// LeoLogger.info("SaleServiceImpl---updateSpecialSales >>>>>>>> saleEntity is
				// >>> " + specialsaleEntity.toString());
				// LeoLogger.info("SaleServiceImpl---updateSpecialSales >>>>>>>>
				// salesItemEntityList is >>> " + specialsalesItemEntityList.toString());

				Old_sale_grand_total = new BigDecimal(specialsaleEntity.getGrand_total());

			}

			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());
			LeoLogger.info("SaleServiceImpl---updateSpecialSale >>>>>>>>salesamount before deleted  " + registerEntity.getSalesamount());
			if (registerEntity != null)

			{

				registerEntity.setSalesamount(registerEntity.getSalesamount() - Old_sale_grand_total.doubleValue());

				registerRepo.save(registerEntity);
				LeoLogger.info("SaleServiceImpl---updateSpecialSale >>>>>>>>salesamount after deleted  " + registerEntity.getSalesamount());
			}

			// Add quantity of products in productdetails for Sales Items received from the
			// above saleitem entity

			for (SpecialSalesItemEntity salesItmAdd : specialsalesItemEntityList) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo
						.findByProductId(salesItmAdd.getProduct_id());
				LeoLogger.info("SaleServiceImpl---updateSpecialSales >>>>>>>> Product Quantity for product "
						+ productDetailsEnt.getname() + "  Before is >>> " + productDetailsEnt.getQuantity());
				productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(( salesItmAdd.getQuantity())));
				productDetailsRepo.save(productDetailsEnt);
				LeoLogger.info("SaleServiceImpl---updateSpecialSales >>>>>>>> Product Quantity for product "
						+ productDetailsEnt.getname() + "  After is >>> " + productDetailsEnt.getQuantity());
			}

			// Delete all sales items in salesitems table against the sale id

			specialsalesItemRepo.deleteAll(specialsalesItemEntityList);
			// Update Existing Sale Entity instead of making a new one

			double grand_total = 0, tax_rate = 0, total = 0, unit_price = 0;
			int quantity = 0;

			// Declare Bigdecimal equivalents
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			String Ctype = "";

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				// saleEntity.setDate(new Date());
				specialsaleEntity.setMemberid(memberPojo.getId());
				specialsaleEntity.setMember_name(memberPojo.getName());
				Ctype = memberPojo.getCtype();

				// LeoLogger.info("SaleServiceImpl---updateSpecialSales---Member Pojo ....." +
				// memberPojo.toString());

				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				if (productDetailsPojo != null)
					unit_price = additem.getPrice().doubleValue();
				quantity = Integer.parseInt(additem.getQuantity());

				/*
				 * This is Promotional discount on Product not applicable to WholeSellers
				 * if(!Ctype.equalsIgnoreCase("WholeSellers") &&
				 * productDetailsPojo.getpromotion() !=0 ) {
				 * 
				 * unit_price = unit_price -
				 * ((unit_price*productDetailsPojo.getpromotion())/100);
				 * LeoLogger.info("Applying Promotion for " + productDetailsPojo.getpromotion()
				 * + "% and the new Unit price now is ....." + unit_price);
				 * 
				 * }
				 */

				specialsaleEntity.setNote(additem.getNote());
				

				total = total + (unit_price * quantity);
				// tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				//tax_rate = 0;
				if (additem.getTax().equalsIgnoreCase("YES")) {
					tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				} else {
					tax_rate = 0;
				}

				// saleEntity.setReferenceno("POS");

				specialsaleEntity.setTotal_discount(0);

				specialsaleEntity.setCf1(productDetailsPojo.getcf1());

				// saleEntity.setUser_id();
			}

			grand_total = total + tax_rate;
			specialsaleEntity.setOrder_tax(0);

			bd_tax_rate = new BigDecimal(tax_rate);
			bd_tax_rate = bd_tax_rate.setScale(2, RoundingMode.HALF_UP);
			specialsaleEntity.setProduct_tax(bd_tax_rate.doubleValue());
			specialsaleEntity.setTotal_tax(bd_tax_rate.doubleValue());
			LeoLogger.info("SaleServiceImpl---updateSpecialSales--Tax Rate....." + bd_tax_rate);

			specialsaleEntity.setPaymentstatus("Due");
			specialsaleEntity.setOrder_discount(0);

			bd_total = new BigDecimal(total);
			bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
			specialsaleEntity.setTotal(bd_total.doubleValue());
			LeoLogger.info("SaleServiceImpl---updateSpecialSales---Total....." + bd_total);

			bd_grand_total = new BigDecimal(grand_total);
			bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
			specialsaleEntity.setGrand_total(bd_grand_total.doubleValue());
			LeoLogger.info("SaleServiceImpl---updateSpecialSales---Grand Total....." + bd_grand_total);

			New_sale_grand_total = bd_grand_total;
			specialsaleEntity.setIsActive(0);

			specialsalesRepo.save(specialsaleEntity);
			UnitEntity unitentity = new UnitEntity();

			LeoLogger.info("SaleServiceImpl---updateSpecialSales >>>>>>>> saleId is >>> " + saleId.toString());

			// this loop is for sales breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				if (additem.getUnit() != null && additem.getUnit() != "") {
					System.out.println(additem.getUnit());
					unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				}

				LeoLogger.info(
						"SaleServiceImpl---updateSepcialSale >>>>>>>> In Adding speicalsaleentity back saleId is >>> "
								+ saleId.toString());

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				BigDecimal price = productDetailsEnt.getprice();
				long qt = Long.parseLong(additem.getQuantity());

				BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(qt));
				BigDecimal tax = totalPrice.multiply(BigDecimal.valueOf(0.125));

				SpecialSalesItemEntity specialsalesItemEntity = new SpecialSalesItemEntity();
				specialsalesItemEntity.setSaleid(saleId);
				specialsalesItemEntity.setProduct_id(additem.getProductId());
				specialsalesItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
				specialsalesItemEntity.setItem_tax(additem.getPrice().doubleValue() * 0.125);
				specialsalesItemEntity.setGst("12.5");
				specialsalesItemEntity.setItem_discount(0d);
				specialsalesItemEntity.setProduct_code(additem.getProductId().toString());
				specialsalesItemEntity.setProduct_name(additem.getProductName());

				bd_real_unit_price = additem.getPrice();
				bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
				specialsalesItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());
				specialsalesItemEntity.setSale_item_id(unitentity.getId());
				specialsalesItemEntity.setUnit_quantity(unitentity.getUnitname());
				specialsalesItemEntity.setRoll(unitentity.getUnitname());
				specialsalesItemEntity.setReturnqty("0");

				bd_subtotal =additem.getSubtotal();
				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				specialsalesItemEntity.setSubtotal(bd_subtotal.doubleValue());
				specialsalesItemEntity.setCost(productDetailsEnt.getcost());

				specialsalesItemEntity.setTax((tax).toString());
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece")&& !unitentity.getUnitname().equalsIgnoreCase("10Ft")&& !unitentity.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unitentity.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					// LeoLogger.info(""+unitentity.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					System.out.println(qty);
					System.out.println(unit);
					qty = qty .multiply(unit) ;
					specialsalesItemEntity.setQuantity(qty);
					// LeoLogger.info("SaleServiceImpl---addSale--qt....." +qty);
				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					specialsalesItemEntity.setQuantity(qty);
				}

				specialsalesItemRepo.save(specialsalesItemEntity);

				// Subtracting Quantities here from products
				if (!unitentity.getUnitname().equalsIgnoreCase("Piece")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit =(unitentity.getQuantity());
					System.out.println(qty);
					qty = qty .multiply(unit) ;
					System.out.println(unit);
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity().subtract(qty));
				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					productDetailsEnt
							.setQuantity(productDetailsEnt.getQuantity() .subtract(qty));
				}
				productDetailsRepo.save(productDetailsEnt);
			}

			// <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

			// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = new Date();

			// convert date to calendar and add 30 days
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);

			Calendar calendarInstance = Calendar.getInstance();
			calendarInstance.add(Calendar.DATE, 30);

			FinancialTransactionEntity ftLatest = financialTransactionsRepo
					.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());

			FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

			ftEntity.setInvoideId(specialsaleEntity.getSaleId());

			if (New_sale_grand_total.compareTo(Old_sale_grand_total) == 1) {
				Diff_amount = New_sale_grand_total.subtract(Old_sale_grand_total);
				ftEntity.setAmount(Diff_amount.doubleValue());
			} else {
				Diff_amount = New_sale_grand_total.subtract(Old_sale_grand_total);
				ftEntity.setAmount(Diff_amount.doubleValue());
			}

			ftEntity.setCustomerId(specialsaleEntity.getMemberid());
			ftEntity.setCustomerName(specialsaleEntity.getMember_name());
			ftEntity.setDate(new Date());
			ftEntity.setDueDate(calendarInstance.getTime());
			ftEntity.setType("Invoice Edit");
			ftEntity.setReferenceno(specialsaleEntity.getReferenceno());

			if (ftLatest == null) {
				ftEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal ftTotal = new BigDecimal(0.0);
				LeoLogger.info("SaleServiceImpl---updateSpecialSales---Latest Financial Transation balance  ....."
						+ ftLatest.getBalance());
				ftTotal = Diff_amount.add(new BigDecimal(ftLatest.getBalance()));
				ftEntity.setBalance(ftTotal.doubleValue());
			}

			financialTransactionsRepo.save(ftEntity);

			// ************************ Financial transaction Entry ended
			// *******************

			// ******FT Entry start******
			FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsaleEntity.getMemberid());
			// fTRepo.deleteAll(salesItemEntityList);
			// LeoLogger.info("SaleServiceImpl---updateSale--FTEntity ....." +
			// fttLatest.getType());
			List<FTEntity> fttList = fTRepo.findByCustomerIdAndInvoideIdAndType(specialsaleEntity.getMemberid(),
					specialsaleEntity.getSaleId(), "Invoice");
			LeoLogger.info("SaleServiceImpl---updateSale--FTEntity  ....." + fttList);

			fTRepo.deleteAll(fttList);

			FTEntity fttEntity = new FTEntity();

			fttEntity.setInvoideId(specialsaleEntity.getSaleId());
			fttEntity.setAmount(bd_grand_total.doubleValue());
			fttEntity.setCustomerId(specialsaleEntity.getMemberid());
			fttEntity.setCustomerName(specialsaleEntity.getMember_name());
			fttEntity.setDate(new Date());
			fttEntity.setDueDate(calendarInstance.getTime());
			fttEntity.setReferenceno(specialsaleEntity.getReferenceno());

			fttEntity.setType("Invoice");

			if (fttLatest == null) {
				fttEntity.setBalance(bd_grand_total.doubleValue());
			} else {
				BigDecimal fttTotal = new BigDecimal(0.0);
				// BigDecimal ftbalance = new BigDecimal(fttLatest.getAmount());
				BigDecimal ftrTotal = new BigDecimal(fttLatest.getBalance());
				ftrTotal = ftrTotal.setScale(2, RoundingMode.HALF_UP);
				LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....." + Diff_amount);
				LeoLogger.info("SaleServiceImpl---addSale---Latest Financial Transation balance  ....."
						+ fttLatest.getBalance());
				fttTotal = Diff_amount.add(ftrTotal);
				fttEntity.setBalance(fttTotal.doubleValue());
			}

			fTRepo.save(fttEntity);
			// ******FT Entry ended******

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
			// >>>>>>>>>>>>>>>>>>>>>>>>>>

			RegisterEntity rregisterEntity = new RegisterEntity();
			List<RegisterEntity> entities = registerRepo.findAllByOrderByIdDesc();

			rregisterEntity = registerRepo.findAByDate(new Date());

			if (rregisterEntity != null)

			{
				rregisterEntity = entities.get(0);
				 LeoLogger.info("SaleServiceImpl--- updateSpecialSales----rregisterEntity ....." + rregisterEntity);

				 LeoLogger.info("SaleServiceImpl--- updateSpecialSales----Salesamount before -Register entry found ....." + registerEntity.getSalesamount());
				rregisterEntity.setSalesamount(rregisterEntity.getSalesamount() + bd_grand_total.doubleValue());
				rregisterEntity.setReferenceno(rregisterEntity.getReferenceno() + "," + specialsaleEntity.getSaleId());
				registerRepo.save(rregisterEntity);
			} else {
				RegisterEntity newregisterEntity = new RegisterEntity();
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				newregisterEntity.setCashpayment(0.00);
				newregisterEntity.setCreditcardpayment(0.00);
				newregisterEntity.setOpeningbal(1000.00);
				newregisterEntity.setClosingbal(1000.00);
				newregisterEntity.setChequepayment(0.00);
				newregisterEntity.setRefunds(0.00);
				newregisterEntity.setReferenceno(String.valueOf(specialsaleEntity.getSaleId()));
				newregisterEntity.setSalesamount(bd_grand_total.doubleValue());
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);

				LeoLogger.info("SaleServiceImpl---updateSpecialSales-- After New Register Entry made"+newregisterEntity.getSalesamount());

			}

			// **************************** Register Details Entry ended
			// ********************
			// ****Register history Entry****
			applyRegisterhistoryspecialedit(specialsaleEntity);

			resultVO.setMsgDescr("Sale Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	@Override
	public ResultVO bulkSendMail(Long memberId, List<BulkSaleEmailPojo> saleEmailList) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgDescr("Something Went Wrong");
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		List<Long> saleIdList = saleEmailList.stream().map(sale -> sale.getSaleId()).collect(Collectors.toList());
		List<SalesEntity> saleList = salesRepo.findBySaleIdIn(saleIdList);
		MemberUser memberUser = memberUserRepo.findById(memberId);
		if (!CollectionUtils.isEmpty(saleList) && memberUser != null) {
			try {

				String fileName = customFileUploadUtil.copyFileToZip(saleList, false);
			//	for(SalesEntity sale: saleList)
			//	{

			//	mailSendingAPI.sendMail(memberUser.getEmail(), sale.getFileName(), salePdfPath);
			//	}
				
				mailSendingAPI.sendMail(memberUser.getEmail(), fileName, salePdfPath);

				customFileUploadUtil.deletFileFromDir(salePdfPath, fileName);

				resultVO.setMsgDescr("Email Sent Successfully");
				resultVO.setError(false);

			} catch (Exception e) {
			//	logger.error("error in bulk send mail", e);
			}
		}
		return resultVO;
	}

	@Override
	public QuotesPojo getquotesbyquotesId(long id) {
		QuotesEntity quotesEntity = new QuotesEntity();
		quotesEntity = quotesRepo.findByquotesId(id);
		LeoLogger.info("SaleServiceImpl---getquotesbyquotesId---" + quotesEntity.toString());
		QuotesPojo quotesPojo = new QuotesPojo();
		quotesPojo = mapper.map(quotesEntity, QuotesPojo.class);
		return quotesPojo;
	}

	@Override
	public List<QuotesItemPojo> getQuotesItembyquoteId(String quoteId, boolean isFromViewQuotesReceipt) {
		List<QuotesItemEntity> quotesItemEntityList = quotesItemRepo
				.findByQuotesidOrderByIdAsc(Long.parseLong(quoteId));
		LeoLogger.info("SaleServiceImpl---quotesItemRepo" + quotesItemEntityList);
		List<QuotesItemPojo> quotesItemPojoList = new ArrayList<QuotesItemPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---in Quotes get Items by quoteId");

			for (QuotesItemEntity quotesItemEntityRes : quotesItemEntityList) {

				QuotesItemPojo quotesItemPojo = new QuotesItemPojo();
				// quotesItemPojo = mapper.map(quotesItemEntityRes, QuotesItemPojo.class);

				UnitEntity unitentity = unitRepo.findById(quotesItemEntityRes.getSale_item_id());
				quotesItemPojo = mapper.map(quotesItemEntityRes, QuotesItemPojo.class);

				// LeoLogger.info("SaleServiceImpl---getQuotesItembyquoteId--Printing Sales
				// percentage >>>>>>>>" + salePercentage.toString());
				LeoLogger.info("SaleServiceImpl---getQuotesItembyquoteId---Printing  Prices before >>>>>>>>"
						+ quotesItemEntityRes.getSubtotal());

				BigDecimal bd_subtotal = new BigDecimal((quotesItemEntityRes.getSubtotal() * salePercentage) / 100);
				BigDecimal bd_unitPrice = new BigDecimal(
						(quotesItemEntityRes.getReal_unit_price() * salePercentage) / 100);

				bd_subtotal = bd_subtotal.add(new BigDecimal(quotesItemEntityRes.getSubtotal()));
				bd_unitPrice = bd_unitPrice.add(new BigDecimal(quotesItemEntityRes.getReal_unit_price()));

				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				bd_unitPrice = bd_unitPrice.setScale(2, RoundingMode.HALF_UP);

				double subTotal = isFromViewQuotesReceipt ? bd_subtotal.doubleValue()
						: quotesItemEntityRes.getSubtotal();
				double unitPrice = isFromViewQuotesReceipt ? bd_unitPrice.doubleValue()
						: quotesItemEntityRes.getReal_unit_price();
				BigDecimal qty = quotesItemEntityRes.getQuantity();
				BigDecimal unit = (unitentity.getQuantity());

				LeoLogger.info("SaleServiceImpl---getQuotesItembyquoteId---Printing  Prices After >>>>>>>>" + subTotal);
				if (!quotesItemEntityRes.getRoll().equalsIgnoreCase("Piece")) {

					BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);
					quotesItemPojo.setQuantity(result);
					LeoLogger.info("SaleServiceImpl---getSalesItembysaleId--Printing result>>>>>>>>" + qty);

					LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity else after>>>>>>>>"
							+ quotesItemPojo.getQuantity());
				} else {
					quotesItemPojo.setQuantity(qty);
					LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>"
							+ quotesItemPojo.getQuantity());
				}
			

				QuotesEntity quotesEntity = quotesRepo.findByquotesId(Long.parseLong(quoteId));
				LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---quotesEntity>>>>>>>>"
						+quotesEntity);
				LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>"
						+ quotesEntity.getGrandtotal());
				quotesItemPojo.setQuoteSubtotal(quotesEntity.getTotal());
				quotesItemPojo.setQuoteTotalTax(quotesEntity.getTotal_tax());
				quotesItemPojo.setQuoteGrandTotal(Double.parseDouble(quotesEntity.getGrandtotal()));

				quotesItemPojo.setReal_unit_price(unitPrice);
				quotesItemPojo.setSubtotal(subTotal);

				quotesItemPojoList.add(quotesItemPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LeoLogger.info(
				"SaleServiceImpl---getQuotesItembyquoteId--quotesItemPojoList ....." + quotesItemPojoList.toString());
		return quotesItemPojoList;
	}

	@Override
	public List<SpecialSalesItemPojo> getSpecailSalesItembysaleId(String saleId, boolean isFromViewSalesReceipt) {
		List<SpecialSalesItemEntity> specialsalesItemEntityList = specialsalesItemRepo
				.findBySaleidOrderByIdAsc(Long.parseLong(saleId));
		List<SpecialSalesItemPojo> specialsalesItemPojoList = new ArrayList<SpecialSalesItemPojo>();
		
		
		/*SaleItemPojo saleItemPojo = new SaleItemPojo();
		saleItemPojo = mapper.map(salesItemEntityRes, SaleItemPojo.class);

		// LeoLogger.info("SaleServiceImpl---getSalesItembysaleId--Printing Sales
		// percentage >>>>>>>>" + salePercentage.toString());
		// LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Prices
		// before >>>>>>>>" + salesItemEntityRes.getSubtotal());

		BigDecimal bd_subtotal = new BigDecimal((salesItemEntityRes.getSubtotal() * salePercentage) / 100);
		BigDecimal bd_unitPrice = new BigDecimal(
				(salesItemEntityRes.getReal_unit_price() * salePercentage) / 100);
		BigDecimal qty = salesItemEntityRes.getQuantity();
		BigDecimal unit = (unitentity.getQuantity());

		// Long bg2 = '328';
		//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  before >>>>>>>>"+ salesItemEntityRes.getQuantity());

		if (!salesItemEntityRes.getRoll().equalsIgnoreCase("Piece")) {

			  BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);
			saleItemPojo.setQuantity(result);
		//	LeoLogger.info("SaleServiceImpl---getSalesItembysaleId--Printing result>>>>>>>>" + qty);

			//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>"+ saleItemPojo.getQuantity());
		} else {
			saleItemPojo.setQuantity(qty);
			//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>"+ saleItemPojo.getQuantity());
		}

		bd_subtotal = bd_subtotal.add(new BigDecimal(salesItemEntityRes.getSubtotal()));
		bd_unitPrice = bd_unitPrice.add(new BigDecimal(salesItemEntityRes.getReal_unit_price()));

		bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
		bd_unitPrice = bd_unitPrice.setScale(2, RoundingMode.HALF_UP);

		double subTotal = isFromViewSalesReceipt ? bd_subtotal.doubleValue() : salesItemEntityRes.getSubtotal();
		double unitPrice = isFromViewSalesReceipt ? bd_unitPrice.doubleValue()
				: salesItemEntityRes.getReal_unit_price();

		//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing  Prices After >>>>>>>>" + subTotal);

		// saleItemPojo = mapper.map(salesItemEntityRes, SaleItemPojo.class);
		saleItemPojo.setReal_unit_price(unitPrice);
		saleItemPojo.setSubtotal(subTotal);*/
		try {
			LeoLogger.info("SaleServiceImpl---getSpecailSalesItembysaleId--in  Special Sales get Items by saleId");

			for (SpecialSalesItemEntity ssalesItemEntityRes : specialsalesItemEntityList) {
				UnitEntity unitentity = unitRepo.findById(ssalesItemEntityRes.getSale_item_id());

				SpecialSalesItemPojo ssaleItemPojo = new SpecialSalesItemPojo();
				ssaleItemPojo = mapper.map(ssalesItemEntityRes, SpecialSalesItemPojo.class);

				// LeoLogger.info("SaleServiceImpl---getSpecailSalesItembysaleId---Printing
				// Sales percentage >>>>>>>>" + salePercentage.toString());
				LeoLogger.info("SaleServiceImpl---getSpecailSalesItembysaleId---Printing  Prices before >>>>>>>>"
						+ ssalesItemEntityRes.getSubtotal());
				
				BigDecimal bd_subtotal = new BigDecimal((ssalesItemEntityRes.getSubtotal() * salePercentage) / 100);
				BigDecimal bd_unitPrice = new BigDecimal(
						(ssalesItemEntityRes.getReal_unit_price() * salePercentage) / 100);
				BigDecimal qty = ssalesItemEntityRes.getQuantity();
				BigDecimal unit = (unitentity.getQuantity());
				
				

				/*BigDecimal bd_subtotal = new BigDecimal((ssalesItemEntityRes.getSubtotal() * salePercentage) / 100);
				BigDecimal bd_unitPrice = new BigDecimal(
						(ssalesItemEntityRes.getReal_unit_price() * salePercentage) / 100);*/

				bd_subtotal = bd_subtotal.add(new BigDecimal(ssalesItemEntityRes.getSubtotal()));
				bd_unitPrice = bd_unitPrice.add(new BigDecimal(ssalesItemEntityRes.getReal_unit_price()));

				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				bd_unitPrice = bd_unitPrice.setScale(2, RoundingMode.HALF_UP);
				
				
				double subTotal = isFromViewSalesReceipt ? bd_subtotal.doubleValue() : ssalesItemEntityRes.getSubtotal();
				double unitPrice = isFromViewSalesReceipt ? bd_unitPrice.doubleValue()
						: ssalesItemEntityRes.getReal_unit_price();

				/*double subTotal = isFromViewSalesReceipt ? bd_subtotal.doubleValue()
						: ssalesItemEntityRes.getSubtotal();
				double unitPrice = isFromViewSalesReceipt ? bd_unitPrice.doubleValue()
						: ssalesItemEntityRes.getReal_unit_price();*/
				//BigDecimal qty = ssalesItemEntityRes.getQuantity();
				//BigDecimal unit = ssalesItemEntityRes.getQuantity();
				
				
				//LeoLogger.info("SaleServiceImpl---getSpecailSalesItembysaleId--Printing  Prices After >>>>>>>>" + subTotal);
				if (!ssalesItemEntityRes.getRoll().equalsIgnoreCase("Piece") ) {

					BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);
					ssaleItemPojo.setQuantity(result);
					LeoLogger.info("SaleServiceImpl---getSalesItembysaleId--Printing result>>>>>>>>" + qty);

					//LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>+ ssaleItemPojo.getQuantity());
				} else {
					ssaleItemPojo.setQuantity(qty);
					LeoLogger.info("SaleServiceImpl---getSalesItembysaleId---Printing Quantity  after>>>>>>>>"+ ssaleItemPojo.getQuantity());
				}

				ssaleItemPojo.setReal_unit_price(unitPrice);
				ssaleItemPojo.setSubtotal(subTotal);

				SpecialSalesEntity specialSale = specialsalesRepo.findBySaleId(Long.parseLong(saleId));

				ssaleItemPojo.setSpecialSaleGrandTotal(specialSale.getGrand_total());
				ssaleItemPojo.setSpecialSaleSubtotal(specialSale.getTotal());
				ssaleItemPojo.setSpecialSaleTotalTax(specialSale.getTotal_tax());

				specialsalesItemPojoList.add(ssaleItemPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LeoLogger.info("SaleServiceImpl---getSpecailSalesItembysaleId---salesItemPojoList ....."
				+ specialsalesItemPojoList.toString());
		return specialsalesItemPojoList;
	}

	@Override
	public List<SaleReportSummaryPojo> findAllTotalSaleReport(String startDate, String endDate, String ctype) {

		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));

		}
		if (!StringUtils.isBlank(ctype)) {
			return salesRepo.findAllTotalSaleByCType(startDate, endDate, ctype);
		}

		return salesRepo.findAllTotalSale(startDate, endDate);

	}

	@Override
	public List<SaleReportSummaryPojo> findAllTotalSpecialSaleReport(String startDate, String endDate) {
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));

		}
		return specialsalesRepo.findAllTotalSpecialSale(startDate, endDate);
	}

	@Override
	public double balance(long memberId) {

		FinancialTransactionEntity fintransEntity = financialTransactionsRepo
				.findTopByCustomerIdOrderByFanIdDesc(memberId);
		double paid = 0.0;

		if (fintransEntity != null)
			paid = fintransEntity.getBalance();

		BigDecimal bd_total = new BigDecimal(paid);
		bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);

		return bd_total.doubleValue();
	}

	@Override
	public double creditpayment(long memberId) {
		List<FinancialTransactionEntity> salesEntity = financialTransactionsRepo.findByCustomerId(memberId);
		System.out.println(salesEntity);

		double creditpayment = 0.0;

		if (salesEntity != null) {

			for (FinancialTransactionEntity salesEnt : salesEntity) {
				if (salesEnt.getType().equalsIgnoreCase("Credit Payment")
						|| salesEnt.getType().equalsIgnoreCase("Credit Return Payment")) {

					creditpayment = creditpayment + salesEnt.getAmount();

				}
			}
		} // 1st if

		BigDecimal bd_creditpayment = new BigDecimal(creditpayment);
		bd_creditpayment = bd_creditpayment.setScale(2, RoundingMode.HALF_UP);

		return bd_creditpayment.doubleValue();
	}

	@Override
	public List<FinancialTransactionEntity> findAllStatementSummary(long memberId, String startDate, String endDate)
			throws ParseException {
		LeoLogger.info(" Statement Entity " + startDate);
		LeoLogger.info(" Statement Entity " + endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = formatter.parse(startDate);

		Date edate = formatter.parse(endDate);

		LeoLogger.info(" Statement Entity " + sdate);
		LeoLogger.info(" Statement Entity " + edate);

		return financialTransactionsRepo.findByCustomerIdAndDateBetweenOrderByDate(memberId, sdate, edate);
	}

	@Override
	public ResultVO deleteSale(long id) {
		ResultVO resultVO = new ResultVO();
		SalesEntity saleEntity = new SalesEntity();
		List<SalesItemEntity> salesItemEntity = salesItemRepo.findBySaleid(id);
		try {
			saleEntity = salesRepo.findBySaleId(id);
			System.out.println(id);
			if (saleEntity != null) {

				saleEntity.setIsActive(1);
				saleEntity.setSale_status("Deleted");
				salesRepo.save(saleEntity);

				for (SalesItemEntity salesEnt : salesItemEntity) {
					ProductDetailsEntity productDetailsEnt = productDetailsRepo
							.findByProductId(salesEnt.getProduct_id());
					System.out.println(productDetailsEnt);

					BigDecimal qty = (salesEnt.getQuantity());
					// LeoLogger.info(""+unitentity.getQuantity());
					// Long unit=unitentity.getQuantity();
					System.out.println(qty);
					// System.out.println(unit);
					// qty = qty * unit;
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(qty) );
					LeoLogger.info("SaleServiceImpl---addSale--qt....." + qty);
					productDetailsRepo.save(productDetailsEnt);

				}

				// ********** Enter Financial transaction *******************

				FinancialTransactionEntity ftLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());

				FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

				ftEntity.setInvoideId(saleEntity.getSaleId());
				ftEntity.setAmount(saleEntity.getGrand_total());
				ftEntity.setCustomerId(saleEntity.getMemberid());
				ftEntity.setCustomerName(saleEntity.getMember_name());
				ftEntity.setDate(new Date());
				ftEntity.setType("DeleteSale:" + saleEntity.getSaleId());

				if (ftLatest == null) {
					ftEntity.setBalance(-1 * saleEntity.getGrand_total());
				} else {
					BigDecimal ftTotal = new BigDecimal(saleEntity.getGrand_total());
					LeoLogger.info("SaleServiceImpl---DeleteSale---Latest Financial Transation balance  ...."
							+ ftLatest.getBalance());
					ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					ftEntity.setBalance(ftTotal.doubleValue());
				}
				financialTransactionsRepo.save(ftEntity);

				// ********** Financial transaction Added *******************

				// ********** Enter Financial Statement transaction *******************

				FTEntity ftSLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());

				FTEntity ftsEntity = new FTEntity();

				ftsEntity.setInvoideId(saleEntity.getSaleId());
				ftsEntity.setAmount(saleEntity.getGrand_total());
				ftsEntity.setCustomerId(saleEntity.getMemberid());
				ftsEntity.setCustomerName(saleEntity.getMember_name());
				ftsEntity.setDate(new Date());
				ftsEntity.setReferenceno(saleEntity.getReferenceno());
				ftsEntity.setType("DeleteSale:" + saleEntity.getSaleId());

				if (ftSLatest == null) {
					ftsEntity.setBalance(-1 * saleEntity.getGrand_total());
				} else {
					BigDecimal ftTotalS = new BigDecimal(saleEntity.getGrand_total());
					LeoLogger.info("SaleServiceImpl---DeleteSale---Latest Financial Statement balance  ...."
							+ ftSLatest.getBalance());
					ftTotalS = new BigDecimal(ftSLatest.getBalance()).subtract(ftTotalS);
					ftTotalS = ftTotalS.setScale(2, RoundingMode.HALF_UP);
					LeoLogger.info("SaleServiceImpl---DeleteSale---Latest ftTotalS balance  ...." + ftTotalS);
					ftsEntity.setBalance(ftTotalS.doubleValue());
				}
				fTRepo.save(ftsEntity);

				// ********** Financial Statement Added *******************

				// ***Register history Entry***
				applyRegisterhistorydelete(saleEntity);
				RegisterEntity registerEntity = new RegisterEntity();

				registerEntity = registerRepo.findAByDate(new Date());
				LeoLogger.info("SaleServiceImpl---DeleteSale---before registerEntity ...."+ registerEntity.getSalesamount());
				if (registerEntity != null)

				{
					registerEntity.setSalesamount(registerEntity.getSalesamount() - saleEntity.getGrand_total());
					registerRepo.save(registerEntity);
					LeoLogger.info("SaleServiceImpl---DeleteSale---after registerEntity ...."+ registerEntity.getSalesamount());
				}

				resultVO.setMsgDescr("Sale Deleted Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("Not Found");
				resultVO.setError(false);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO deleteSpecialSale(long id) {
		ResultVO resultVO = new ResultVO();
		SpecialSalesEntity saleEntity = new SpecialSalesEntity();
		List<SpecialSalesItemEntity> salesItemEntity = specialsalesItemRepo.findBySaleid(id);
		try {
			saleEntity = specialsalesRepo.findBySaleId(id);
			System.out.println(id);
			if (saleEntity != null) {

				saleEntity.setIsActive(1);
				;
				saleEntity.setSale_status("Deleted");
				specialsalesRepo.save(saleEntity);

				for (SpecialSalesItemEntity salesEnt : salesItemEntity) {
					ProductDetailsEntity productDetailsEnt = productDetailsRepo
							.findByProductId(salesEnt.getProduct_id());
					System.out.println(productDetailsEnt);

					BigDecimal qty =(salesEnt.getQuantity());
					// LeoLogger.info(""+unitentity.getQuantity());
					// Long unit=unitentity.getQuantity();
					System.out.println(qty);
					// System.out.println(unit);
					// qty = qty * unit;
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(qty) );
					LeoLogger.info("SaleServiceImpl---deleteSpecialSale--qt....." + qty);
					productDetailsRepo.save(productDetailsEnt);

				}

				// ********** Enter Financial transaction *******************

				FinancialTransactionEntity ftLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());

				FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

				ftEntity.setInvoideId(saleEntity.getSaleId());
				ftEntity.setAmount(saleEntity.getGrand_total());
				ftEntity.setCustomerId(saleEntity.getMemberid());
				ftEntity.setCustomerName(saleEntity.getMember_name());
				ftEntity.setDate(new Date());
				ftEntity.setType("DeleteSpecialSale:" + saleEntity.getSaleId());

				if (ftLatest == null) {
					ftEntity.setBalance(-1 * saleEntity.getGrand_total());
				} else {
					BigDecimal ftTotal = new BigDecimal(saleEntity.getGrand_total());
					LeoLogger.info(
							"SaleServiceImpl---DeleteSpecialSale---Latest Financial Transation balance  ...Payment.."
									+ ftLatest.getBalance());
					ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					ftEntity.setBalance(ftTotal.doubleValue());
				}
				financialTransactionsRepo.save(ftEntity);

				// ********** Financial transaction Added *******************

				// ********** Enter Statement transaction *******************

				FTEntity ftSLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(saleEntity.getMemberid());
				LeoLogger.info("SaleServiceImpl---DeleteSpecialSale---Latest " + ftSLatest);

				FTEntity ftsEntity = new FTEntity();

				ftsEntity.setInvoideId(saleEntity.getSaleId());
				ftsEntity.setAmount(saleEntity.getGrand_total());
				ftsEntity.setCustomerId(saleEntity.getMemberid());
				ftsEntity.setCustomerName(saleEntity.getMember_name());
				ftsEntity.setDate(new Date());
				ftsEntity.setReferenceno(saleEntity.getReferenceno());
				ftsEntity.setType("DeleteSpecialSale:" + saleEntity.getSaleId());

				if (ftSLatest == null) {
					ftsEntity.setBalance(-1 * saleEntity.getGrand_total());
				} else {
					BigDecimal ftTotalS = new BigDecimal(saleEntity.getGrand_total());
					LeoLogger.info("SaleServiceImpl---DeleteSale---Latest Financial Statement balance  ...."
							+ ftSLatest.getBalance());
					ftTotalS = new BigDecimal(ftSLatest.getBalance()).subtract(ftTotalS);
					ftTotalS = ftTotalS.setScale(2, RoundingMode.HALF_UP);
					LeoLogger.info("SaleServiceImpl---DeleteSale---Latest ftTotalS balance  ...." + ftTotalS);
					ftsEntity.setBalance(ftTotalS.doubleValue());
				}
				fTRepo.save(ftsEntity);

				// ********** Financial Statement Added *******************

				// ***Register history entry***
				applyRegisterhistoryspecialdelete(saleEntity);
				RegisterEntity registerEntity = new RegisterEntity();
				registerEntity = registerRepo.findAByDate(new Date());
				LeoLogger.info("SaleServiceImpl---DeleteSpecialSale--before-registerEntity ...."+ registerEntity.getSalesamount());
				if (registerEntity != null) {
					registerEntity.setSalesamount(registerEntity.getSalesamount() - saleEntity.getGrand_total());
					registerRepo.save(registerEntity);
					LeoLogger.info("SaleServiceImpl---DeleteSpecialSale--after-registerEntity ...."+ registerEntity.getSalesamount());
				}

				resultVO.setMsgDescr("Special Sale Deleted Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("Not Found");
				resultVO.setError(false);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<SpecialSalesEntity> getSpecialSalesItembymemberId(long memberId) {
		List<SpecialSalesEntity> salesList = specialsalesRepo.findAllByMemberid(memberId);
		return salesList;
	}

	@Override
	public List<FinancialTransactionPojo> getFttByCustomerId(long memberId) {
		List<FinancialTransactionEntity> ftList = financialTransactionsRepo.findByCustomerId(memberId);
		List<FinancialTransactionPojo> ftPojoList = new ArrayList<FinancialTransactionPojo>();
		List<BulkPaymentPojo> bulkpaymentPojo = saleService.getBulkPaymentListbyMemberId(memberId);

		// LeoLogger.info("SaleServiceImpl---getsaleslist---specialsalesEntityList is :
		// " + specialsalesEntityList.toString());
		ftList = financialTransactionsRepo.findByCustomerId(memberId);

		for (FinancialTransactionEntity salesEnt : ftList) {
			if (salesEnt.getType().equalsIgnoreCase("Invoice")) {

				FinancialTransactionPojo fttPojo = new FinancialTransactionPojo();
				fttPojo = mapper.map(salesEnt, FinancialTransactionPojo.class);
				ftPojoList.add(fttPojo);
			}
		}
		return ftPojoList;
	}

	@Override
	public List<PaymentPojo> getPaymenttbyMemberId(long memberId, long bulkId) {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllBymemberidOrderByIdAsc(memberId);
		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId");
			for (PaymentEntity paymentEntityRes : paymentEntityList) {
				if (paymentEntityRes.getBulkid() != 0 && paymentEntityRes.getMemberid() == memberId
						&& paymentEntityRes.getBulkid() == bulkId && paymentEntityRes.getGrand_total() != 0) {

					PaymentPojo PaymentPojo = new PaymentPojo();
					PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
					paymentPojoList.add(PaymentPojo);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public List<FTEntity> findAllStatementtSummary(long memberId, String startDate, String endDate)
			throws ParseException {
		LeoLogger.info(" Statement Entity " + startDate);
		LeoLogger.info(" Statement Entity " + endDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date sdate = formatter.parse(startDate);

		Date edate = formatter.parse(endDate);
		LocalDate nextDate = LocalDate.parse(endDate);
		LocalDate newendDate = nextDate.plusDays(1);
		Date enddate = java.sql.Date.valueOf(newendDate);
		LeoLogger.info(" Statement Entity newendDate " + newendDate);
		LeoLogger.info(" Statement Entity enddate " + enddate);

		LeoLogger.info(" Statement Entity " + sdate);
		LeoLogger.info(" Statement Entity " + edate);

		return fTRepo.findByCustomerIdAndDateBetweenOrderByDate(memberId, sdate, enddate);
	}

	@Override
	public ResultVO bulkPaySelected(Long memberId, Double amount, String note, String ptype, String pref,
			List<BulkSaleEmailPojo> bulkSaleList) {

		ResultVO resultVO = new ResultVO();
		resultVO.setMsgDescr("Something Went Wrong");
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		List<Long> saleIdList = bulkSaleList.stream().map(sale -> sale.getSaleId()).collect(Collectors.toList());
		List<SalesEntity> saleList = salesRepo.findBySaleIdIn(saleIdList);
		List<SpecialSalesEntity> specialsalesList = specialsalesRepo.findBySaleIdIn(saleIdList);
		MemberUser memberUser = memberUserRepo.findById(memberId);
		if ((!CollectionUtils.isEmpty(saleList) || !CollectionUtils.isEmpty(specialsalesList)) && memberUser != null) {
			try {

				LeoLogger.info(
						"Managed to reach here now bulk payment needs to be applied to each and every invoice !!!!!!!!");

				// here we need to implement logic , get all pending sales from oldest to newest
				// and the iterate and start deducting money and adding payment

				double bulkamount = amount;
				BigDecimal bd_bulkamount = new BigDecimal(bulkamount);
				BigDecimal ftbulkamount = new BigDecimal(0.0);
				bd_bulkamount = bd_bulkamount.setScale(2, RoundingMode.HALF_UP);
				BulkPaymentEntity bulkPaymentEntity = new BulkPaymentEntity();
				MemberUser memberPojo = memberUserRepo.findById(memberId);
				LeoLogger.info("SaleServiceImpl---MemberUse" + memberPojo);

				// ********** Enter Bulk Payment *******************
				bulkPaymentEntity.setAmount(bd_bulkamount.doubleValue());
				bulkPaymentEntity.setDate(new Date());
				bulkPaymentEntity.setMemberId(memberId);
				bulkPaymentEntity.setMemberName(memberPojo.getName());
				bulkPaymentEntity.setPref(pref);
				bulkPaymentEntity.setPtype(ptype);
				bulkPaymentEntity.setNote(note);
				// bulkPaymentEntity.setPaymentid(pdfPath);
				bulkPaymentEntity.setStatus("Paid");

				BulkPaymentEntity bkpent = bulkPaymentRepo.save(bulkPaymentEntity);

				// ********** Enter Payment as payment *******************

				LeoLogger.info("SaleServiceImpl---addBulkPayment--Bulk amount is [" + bulkamount + "]");
				BigDecimal db_paidamount = new BigDecimal(0.0);

				for (BulkSaleEmailPojo bulksalelist : bulkSaleList) {

					if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {

						SalesEntity salesEntityRes = new SalesEntity();
						salesEntityRes = salesRepo.findBySaleId(bulksalelist.getSaleId());

						double pendingamount = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Pending amount is [" + pendingamount + "]");

						if (bulkamount >= pendingamount)

						{
							LeoLogger.info("SaleServiceImpl---addBulkPayment---Bulk amount is [" + bulkamount
									+ "] and the remaining amount for invoice [" + salesEntityRes.getSaleId() + "] is ["
									+ (salesEntityRes.getGrand_total() - salesEntityRes.getPaid()) + "]");

							// Addjust Sales entity paid value

							double paidamount = 0.0;
							paidamount = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
							db_paidamount = new BigDecimal(paidamount);
							db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);
							LeoLogger.info(
									"SaleServiceImpl---addBulkPayment---db_paidamount is [" + db_paidamount + "]");

							salesEntityRes.setPaid(salesEntityRes.getGrand_total());
							salesEntityRes.setPaymentstatus("Paid");
							salesRepo.save(salesEntityRes);

							// ********** Enter Financial transaction *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(salesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(salesEntityRes.getSaleId());
							ftEntity.setAmount(db_paidamount.doubleValue());
							ftEntity.setCustomerId(salesEntityRes.getMemberid());
							ftEntity.setCustomerName(salesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("BulkPayment:" + bkpent.getBulkId());
							ftEntity.setReferenceno(salesEntityRes.getReferenceno());

							if (ftLatest == null) {
								ftEntity.setBalance(paidamount);
							} else {
								BigDecimal ftTotal = new BigDecimal(paidamount);
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added *******************

							// *******FT Start******
							ftbulkamount = ftbulkamount.add(db_paidamount);

							// ******FT ended******/

							// ********** Enter Payment with bulk id *******************

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= db_paidamount.doubleValue()) {
								paymentEntity.setstatus("Paid");

							} else {
								paymentEntity.setstatus("Partial");

							}

							paymentEntity.setMember_id(salesEntityRes.getMemberid());
							paymentEntity.setMember_name(salesEntityRes.getMember_name());
							paymentEntity.setRsaleId(salesEntityRes.getSaleId());
							paymentEntity.setGrand_total(db_paidamount.doubleValue());
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setPref(pref);
							paymentEntity.setPtype(ptype);
							paymentEntity.setNote(note);
							paymentEntity.setCtype(salesEntityRes.getCtype());
							paymentEntity.setSalesreferenceno(salesEntityRes.getReferenceno());
							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno("BULKPAY" + currentYear + "/" + currentmonth + "/"
									+ salesEntityRes.getSaleId() + "-" + bkpent.getBulkId());
							paymentRepo.save(paymentEntity);

							// ********** Payment Added *******************
							BigDecimal buamount = new BigDecimal(bulkamount - paidamount);
							buamount = buamount.setScale(2, RoundingMode.HALF_UP);
							bulkamount = buamount.doubleValue();
							// bulkamount - paidamount;
							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);
							LeoLogger.info("SaleServiceImpl---addBulkPayment--bulkamount." + bulkamount);

						} else {

							if (bulkamount != 0) {

								double paidamount = 0.0;
								BigDecimal bamount = new BigDecimal(bulkamount);
								bamount = bamount.setScale(3, RoundingMode.HALF_UP);
								BigDecimal bamountt = bamount.setScale(2, RoundingMode.HALF_UP);

								LeoLogger.info("SaleServiceImpl---addBulkPayment---bulkamount.(3)...." + bamountt);
								paidamount = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
								db_paidamount = new BigDecimal(paidamount);
								db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);

								BigDecimal setpayamount = new BigDecimal(bulkamount);

								setpayamount = setpayamount.add(new BigDecimal(salesEntityRes.getPaid()));
								setpayamount = setpayamount.setScale(2, RoundingMode.HALF_UP);
								salesEntityRes.setPaid(setpayamount.doubleValue());
								if (bamountt.doubleValue() == (salesEntityRes.getGrand_total()
										- salesEntityRes.getPaid())) {
									salesEntityRes.setPaymentstatus("Paid");
								}
								salesRepo.save(salesEntityRes);

								// ********** Enter Financial transaction *******************

								FinancialTransactionEntity ftLatest = financialTransactionsRepo
										.findTopByCustomerIdOrderByFanIdDesc(salesEntityRes.getMemberid());

								FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

								ftEntity.setInvoideId(salesEntityRes.getSaleId());
								ftEntity.setAmount(bulkamount);
								ftEntity.setCustomerId(salesEntityRes.getMemberid());
								ftEntity.setCustomerName(salesEntityRes.getMember_name());
								ftEntity.setDate(new Date());
								ftEntity.setType("BulkPayment:" + bkpent.getBulkId());
								ftEntity.setReferenceno(salesEntityRes.getReferenceno());

								if (ftLatest == null) {
									ftEntity.setBalance(bulkamount);
								} else {
									BigDecimal ftTotal = new BigDecimal(bulkamount);
									LeoLogger.info(
											"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
													+ ftLatest.getBalance());
									ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
									ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
									ftEntity.setBalance(ftTotal.doubleValue());
								}
								financialTransactionsRepo.save(ftEntity);

								// ********** Financial transaction Added *******************

								// *******FT Start******
								ftbulkamount = ftbulkamount.add(new BigDecimal(bulkamount));
								// ******FT ended******/

								PaymentEntity paymentEntity = new PaymentEntity();
								if (bulkamount >= salesEntityRes.getPaid()) {
									paymentEntity.setstatus("Paid");

								} else {
									paymentEntity.setstatus("Partial");

								}
								paymentEntity.setGrand_total(bulkamount);
								paymentEntity.setPref(pref);
								paymentEntity.setPtype(ptype);
								paymentEntity.setMember_id(salesEntityRes.getMemberid());
								paymentEntity.setMember_name(salesEntityRes.getMember_name());
								paymentEntity.setRsaleId(salesEntityRes.getSaleId());
								paymentEntity.setGrand_total(bulkamount);
								paymentEntity.setPaymentdate(new Date());
								paymentEntity.setNote(note);
								paymentEntity.setBulkid(bkpent.getBulkId());
								paymentEntity.setCtype(salesEntityRes.getCtype());
								paymentEntity.setSalesreferenceno(salesEntityRes.getReferenceno());

								Date d = new Date();
								int year = d.getYear();
								int currentYear = year + 1900;
								int currentmonth = d.getMonth() + 1;

								paymentEntity.setReferenceno("BULKPAY" + currentYear + "/" + currentmonth + "/"
										+ salesEntityRes.getSaleId() + "-" + bkpent.getBulkId());
								paymentRepo.save(paymentEntity);

								bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
								bulkPaymentRepo.save(bulkPaymentEntity);

								// ********** Payment Added *******************
								bulkamount = 0;
								break;

							}
						}

					} else {

						LeoLogger.info("SaleServiceImpl---addBulkPayment---else part");
						SpecialSalesEntity specialsalesEntityRes = new SpecialSalesEntity();

						specialsalesEntityRes = specialsalesRepo.findBySaleId(bulksalelist.getSaleId());

						double pendingamount = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Pending amount is [" + pendingamount + "]");

						if (bulkamount >= pendingamount)

						{
							LeoLogger.info("SaleServiceImpl---addBulkPayment---Bulk amount is [" + bulkamount
									+ "] and the remaining amount for invoice [" + specialsalesEntityRes.getSaleId()
									+ "] is ["
									+ (specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid()) + "]");

							// Addjust Sales entity paid value

							double paidamount = 0.0;
							paidamount = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
							db_paidamount = new BigDecimal(paidamount);
							db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);
							LeoLogger.info(
									"SaleServiceImpl---addBulkPayment---db_paidamount is [" + db_paidamount + "]");

							specialsalesEntityRes.setPaid(specialsalesEntityRes.getGrand_total());
							specialsalesEntityRes.setPaymentstatus("Paid");
							specialsalesRepo.save(specialsalesEntityRes);

							// ********** Enter Financial transaction *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(specialsalesEntityRes.getSaleId());
							ftEntity.setAmount(db_paidamount.doubleValue());
							ftEntity.setCustomerId(specialsalesEntityRes.getMemberid());
							ftEntity.setCustomerName(specialsalesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("Payment");

							if (ftLatest == null) {
								ftEntity.setBalance(paidamount);
							} else {
								BigDecimal ftTotal = new BigDecimal(paidamount);
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added *******************

							// *******FT Start******
							ftbulkamount = ftbulkamount.add(db_paidamount);

							// ******FT ended******/

							// ********** Enter Payment with bulk id *******************

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= db_paidamount.doubleValue()) {
								paymentEntity.setstatus("Paid");
							} else {
								paymentEntity.setstatus("Partial");
							}

							paymentEntity.setMember_id(specialsalesEntityRes.getMemberid());
							paymentEntity.setMember_name(specialsalesEntityRes.getMember_name());
							paymentEntity.setRsaleId(specialsalesEntityRes.getSaleId());
							paymentEntity.setGrand_total(db_paidamount.doubleValue());
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setPref(pref);
							paymentEntity.setNote(note);
							paymentEntity.setPtype(ptype);
							paymentEntity.setCtype(specialsalesEntityRes.getCtype());
							paymentEntity.setSalesreferenceno(specialsalesEntityRes.getReferenceno());
							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno("BULKPAY" + currentYear + "/" + currentmonth + "/"
									+ specialsalesEntityRes.getSaleId() + "-" + bkpent.getBulkId());
							paymentRepo.save(paymentEntity);

							// ********** Payment Added *******************
							BigDecimal buamount = new BigDecimal(bulkamount - paidamount);
							buamount = buamount.setScale(2, RoundingMode.HALF_UP);
							bulkamount = buamount.doubleValue();

							// bulkamount = bulkamount - paidamount;
							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);

						} else {
							if (bulkamount != 0) {

								double paidamount = 0.0;
								paidamount = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
								db_paidamount = new BigDecimal(paidamount);
								db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);

								BigDecimal setpayamount = new BigDecimal(bulkamount);
								setpayamount = setpayamount.add(new BigDecimal(specialsalesEntityRes.getPaid()));
								setpayamount = setpayamount.setScale(2, RoundingMode.HALF_UP);

								specialsalesEntityRes.setPaid(setpayamount.doubleValue());
								specialsalesRepo.save(specialsalesEntityRes);

								// ********** Enter Financial transaction *******************

								FinancialTransactionEntity ftLatest = financialTransactionsRepo
										.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid());

								FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

								ftEntity.setInvoideId(specialsalesEntityRes.getSaleId());
								ftEntity.setAmount(bulkamount);
								ftEntity.setCustomerId(specialsalesEntityRes.getMemberid());
								ftEntity.setCustomerName(specialsalesEntityRes.getMember_name());
								ftEntity.setDate(new Date());
								ftEntity.setType("BulkPayment:" + bkpent.getBulkId());

								if (ftLatest == null) {
									ftEntity.setBalance(bulkamount);
								} else {
									BigDecimal ftTotal = new BigDecimal(bulkamount);
									LeoLogger.info(
											"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
													+ ftLatest.getBalance());
									ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
									ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
									ftEntity.setBalance(ftTotal.doubleValue());
								}
								financialTransactionsRepo.save(ftEntity);

								// ********** Financial transaction Added *******************

								// *******FT Start******
								ftbulkamount = ftbulkamount.add(new BigDecimal(bulkamount));
								// ******FT ended******/

								// ********** Enter Payment with bulk id *******************

								PaymentEntity paymentEntity = new PaymentEntity();
								if (bulkamount >= specialsalesEntityRes.getPaid()) {
									paymentEntity.setstatus("Paid");
								} else {
									paymentEntity.setstatus("Partial");
								}
								paymentEntity.setGrand_total(bulkamount);
								paymentEntity.setPref(pref);
								paymentEntity.setPtype(ptype);
								paymentEntity.setMember_id(specialsalesEntityRes.getMemberid());
								paymentEntity.setMember_name(specialsalesEntityRes.getMember_name());
								paymentEntity.setRsaleId(specialsalesEntityRes.getSaleId());
								paymentEntity.setGrand_total(bulkamount);
								paymentEntity.setPaymentdate(new Date());
								paymentEntity.setNote(note);
								paymentEntity.setBulkid(bkpent.getBulkId());
								paymentEntity.setCtype(specialsalesEntityRes.getCtype());
								paymentEntity.setSalesreferenceno(specialsalesEntityRes.getReferenceno());
								// paymentRepo.save(paymentEntity);
								Date d = new Date();
								int year = d.getYear();
								int currentYear = year + 1900;
								int currentmonth = d.getMonth() + 1;

								paymentEntity.setReferenceno("BULKPAY" + currentYear + "/" + currentmonth + "/"
										+ specialsalesEntityRes.getSaleId());
								paymentRepo.save(paymentEntity);

								bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
								bulkPaymentRepo.save(bulkPaymentEntity);

								// ********** Payment Added *******************
								bulkamount = 0;
								break;

							}
						}

					}

				}

				ftBulkPaymentEntry(memberPojo, ftbulkamount, bulkPaymentEntity);
			

				if (bulkamount > 0)

				{
					// ********** Enter Financial transaction as Credit note *******************

					BigDecimal bdbulkamount = new BigDecimal(bulkamount);

					bdbulkamount = bdbulkamount.add((new BigDecimal(memberPojo.getCreditpayment())));
					bdbulkamount = bdbulkamount.setScale(2, RoundingMode.HALF_UP);

					memberPojo.setCreditpayment(bdbulkamount.doubleValue());
					memberUserRepo.save(memberPojo);

					// ********** Enter Financial transaction *******************

					FinancialTransactionEntity ftLatest = financialTransactionsRepo
							.findTopByCustomerIdOrderByFanIdDesc(memberPojo.getId());

					FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

					ftEntity.setInvoideId(bulkPaymentEntity.getBulkId());
					ftEntity.setAmount(bdbulkamount.doubleValue());
					ftEntity.setCustomerId(memberPojo.getId());
					ftEntity.setCustomerName(memberPojo.getName());
					ftEntity.setDate(new Date());
					ftEntity.setType("Credit Amount:" + bkpent.getBulkId());
					if (ftLatest == null) {
						ftEntity.setBalance(bdbulkamount.doubleValue());
					} else {
						BigDecimal fttTotal = new BigDecimal(bdbulkamount.doubleValue());
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
								+ ftLatest.getBalance());
						fttTotal = new BigDecimal(ftLatest.getBalance());
						fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
						ftEntity.setBalance(fttTotal.doubleValue());
					}

					financialTransactionsRepo.save(ftEntity);

					// ********** Financial transaction Added *******************
					// *******FT Start******
					FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(memberPojo.getId());

					FTEntity fttEntity = new FTEntity();

					fttEntity.setInvoideId(bulkPaymentEntity.getBulkId());
					fttEntity.setAmount(bulkamount);
					fttEntity.setCustomerId(memberPojo.getId());
					fttEntity.setCustomerName(memberPojo.getName());
					fttEntity.setDate(new Date());
					fttEntity.setType("Credit Amount");

					if (fttLatest == null) {
						fttEntity.setBalance(ftbulkamount.doubleValue());
					} else {
						BigDecimal fttTotal = new BigDecimal(bdbulkamount.doubleValue());
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ....."
								+ fttLatest.getBalance());
						fttTotal = new BigDecimal(fttLatest.getBalance());
						fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
						fttEntity.setBalance(fttTotal.doubleValue());
					}
					fTRepo.save(fttEntity);
					// ******FT ended******/

				}

				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
				applyRegisterhistorybulkpayment(bulkPaymentEntity);
				// **Register entry***
				RegisterEntity registerEntity = new RegisterEntity();

				registerEntity = registerRepo.findAByDate(new Date());
				if (registerEntity != null)

				{
					LeoLogger.info("SaleServiceImpl---addPayment-- Register entry found for today  ....."
							+ registerEntity.getSalesamount());
					if (ptype.equalsIgnoreCase("cash")) {
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getCashpayment() + amount.doubleValue());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						registerEntity.setCashpayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() + amount.doubleValue());
					} else if (ptype.equalsIgnoreCase("cheque")) {
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getChequepayment() + amount.doubleValue());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						registerEntity.setChequepayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() + amount.doubleValue());
					} else if (ptype.equalsIgnoreCase("online")) {
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getOnlinepayment() + amount.doubleValue());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);

						registerEntity.setOnlinepayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() + amount.doubleValue());
					} else {
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getCreditcardpayment() + amount.doubleValue());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						registerEntity.setCreditcardpayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() + amount.doubleValue());
					}
					registerRepo.save(registerEntity);
				} else {
					RegisterEntity newregisterEntity = new RegisterEntity();
					newregisterEntity.setCashinhand(1000.00);
					newregisterEntity.setDate(new Date());
					if (ptype.equalsIgnoreCase("cash")) {
						newregisterEntity.setCashpayment(amount.doubleValue());
					} else if (ptype.equalsIgnoreCase("cheque")) {
						newregisterEntity.setChequepayment(amount.doubleValue());
					} else if (ptype.equalsIgnoreCase("online")) {
						newregisterEntity.setOnlinepayment(amount.doubleValue());
					} else {
						newregisterEntity.setCreditcardpayment(amount.doubleValue());
					}
					newregisterEntity.setOpeningbal(1000.00);
					newregisterEntity.setClosingbal(1000.00);
					newregisterEntity.setRefunds(0.00);
					newregisterEntity.setReferenceno(String.valueOf(bkpent.getBulkId()));
					newregisterEntity.setSalesamount(0.0);
					newregisterEntity.setStatus("Open");
					registerRepo.save(newregisterEntity);

					LeoLogger.info("SaleServiceImpl---bulkPayment--- New Register Entry made");

				}
				
				} else {
					
					  saveSpecialRegisterForPayment(new BigDecimal(bulkPaymentEntity.getAmount()));
					  applySpecialRegisterHistoryBulkPayment(bulkPaymentEntity);
										
				}

				resultVO.setMsgDescr("BUlk Paymentd done !!");
				resultVO.setError(false);

			} catch (Exception e) {
		//		logger.error("error in bulk Payment", e);
			}
		}
		return resultVO;
	}

	@Override
	public List<PaymentPojo> getPaymentListbySaleId(long SaleId, String ctype) {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllByrsaleIdAndCtypeOrderByIdDesc(SaleId, ctype);
	//	LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId" + paymentEntityList);
		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId");
			for (PaymentEntity paymentEntityRes : paymentEntityList) {
				// if (paymentEntityRes.getBulkid() == 0) {

				PaymentPojo PaymentPojo = new PaymentPojo();
				PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
				paymentPojoList.add(PaymentPojo);
				/*
				 * LeoLogger.
				 * info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId"
				 * + paymentPojoList.toString());
				 */
				// }

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public ResultVO applyCreditPayment(Long memberId, Double amount, String note, String ptype, String pref,
			List<BulkSaleEmailPojo> bulkSaleList) {

		ResultVO resultVO = new ResultVO();
		resultVO.setMsgDescr("Something Went Wrong");
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		List<Long> saleIdList = bulkSaleList.stream().map(sale -> sale.getSaleId()).collect(Collectors.toList());
		List<SalesEntity> saleList = salesRepo.findBySaleIdIn(saleIdList);
		List<SpecialSalesEntity> specialsalesList = specialsalesRepo.findBySaleIdIn(saleIdList);
		MemberUser memberUser = memberUserRepo.findById(memberId);
		if (!CollectionUtils.isEmpty(saleList) || CollectionUtils.isEmpty(specialsalesList) && memberUser != null) {
			try {

				LeoLogger.info(
						"Managed to reach here now bulk payment needs to be applied to each and every invoice !!!!!!!!");

				// here we need to implement logic , get all pending sales from oldest to newest
				// and the iterate and start deducting money and adding payment

				double bulkamount = amount;
				BigDecimal bd_bulkamount = new BigDecimal(bulkamount);
				BigDecimal ftbulkamount = new BigDecimal(0.0);
				bd_bulkamount = bd_bulkamount.setScale(2, RoundingMode.HALF_UP);
				BulkPaymentEntity bulkPaymentEntity = new BulkPaymentEntity();
				MemberUser memberPojo = memberUserRepo.findById(memberId);
				LeoLogger.info("SaleServiceImpl---MemberUse" + memberPojo);

				// ********** Enter Bulk Payment *******************
				bulkPaymentEntity.setAmount(bd_bulkamount.doubleValue());
				bulkPaymentEntity.setDate(new Date());
				bulkPaymentEntity.setMemberId(memberId);
				bulkPaymentEntity.setMemberName(memberPojo.getName());
				bulkPaymentEntity.setPref(pref);
				bulkPaymentEntity.setPtype("Deposit Pay");
				bulkPaymentEntity.setNote(note);
				// bulkPaymentEntity.setPaymentid(pdfPath);
				bulkPaymentEntity.setStatus("Paid");

				BulkPaymentEntity bkpent = bulkPaymentRepo.save(bulkPaymentEntity);

				// ********** Enter Payment as payment *******************

				LeoLogger.info("SaleServiceImpl---addBulkPayment--Bulk amount is [" + bulkamount + "]");
				BigDecimal db_paidamount = new BigDecimal(0.0);

				for (BulkSaleEmailPojo bulksalelist : bulkSaleList) {
					if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {

						SalesEntity salesEntityRes = new SalesEntity();
						salesEntityRes = salesRepo.findBySaleId(bulksalelist.getSaleId());

						double pendingamount = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Pending amount is [" + pendingamount + "]");

						if (bulkamount >= pendingamount)

						{
							LeoLogger.info("SaleServiceImpl---addBulkPayment---Bulk amount is [" + bulkamount
									+ "] and the remaining amount for invoice [" + salesEntityRes.getSaleId() + "] is ["
									+ (salesEntityRes.getGrand_total() - salesEntityRes.getPaid()) + "]");

							// Addjust Sales entity paid value

							double paidamount = 0.0;
							paidamount = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
							db_paidamount = new BigDecimal(paidamount);
							db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);
							LeoLogger.info(
									"SaleServiceImpl---addBulkPayment---db_paidamount is [" + db_paidamount + "]");

							salesEntityRes.setPaid(salesEntityRes.getGrand_total());
							salesEntityRes.setPaymentstatus("Paid");
							salesRepo.save(salesEntityRes);

							// ********** Enter Financial transaction *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(salesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(salesEntityRes.getSaleId());
							ftEntity.setAmount(db_paidamount.doubleValue());
							ftEntity.setCustomerId(salesEntityRes.getMemberid());
							ftEntity.setCustomerName(salesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("CreditPayment:" + bkpent.getBulkId());

							if (ftLatest == null) {
								ftEntity.setBalance(paidamount);
							} else {
								BigDecimal ftTotal = new BigDecimal(paidamount);
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added *******************

							// *******FT Start******
							ftbulkamount = ftbulkamount.add(db_paidamount);
							// ******FT ended******/

							// ********** Enter Payment with bulk id *******************

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= db_paidamount.doubleValue()) {
								paymentEntity.setstatus("Paid");
							} else {
								paymentEntity.setstatus("Partial");
							}

							paymentEntity.setMember_id(salesEntityRes.getMemberid());
							paymentEntity.setMember_name(salesEntityRes.getMember_name());
							paymentEntity.setRsaleId(salesEntityRes.getSaleId());
							paymentEntity.setGrand_total(db_paidamount.doubleValue());
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setPref(pref);
							paymentEntity.setPtype("Deposit Pay");
							paymentEntity.setCtype(salesEntityRes.getCtype());
							paymentEntity.setNote(note);
							paymentEntity.setSalesreferenceno(salesEntityRes.getReferenceno());
							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno("CREDITPAY" + currentYear + "/" + currentmonth + "/"
									+ salesEntityRes.getSaleId() + "-" + bkpent.getBulkId());
							paymentRepo.save(paymentEntity);

							// ********** Payment Added *******************
							BigDecimal savecreditamount = new BigDecimal(0.0);

							savecreditamount = new BigDecimal(memberPojo.getCreditpayment())
									.subtract(new BigDecimal(bulkamount));
							savecreditamount = savecreditamount.setScale(2, RoundingMode.HALF_UP);

							memberPojo.setCreditpayment(savecreditamount.doubleValue());
							memberUserRepo.save(memberPojo);

							bulkamount = bulkamount - paidamount;
							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);

						} else {

							BigDecimal db_bulkamount = new BigDecimal(0.0);
							db_bulkamount = new BigDecimal(bulkamount);
							db_bulkamount = db_bulkamount.setScale(2, RoundingMode.HALF_UP);

							BigDecimal setpayamount = new BigDecimal(bulkamount);

							setpayamount = setpayamount.add(new BigDecimal(salesEntityRes.getPaid()));
							setpayamount = setpayamount.setScale(2, RoundingMode.HALF_UP);
							salesEntityRes.setPaid(setpayamount.doubleValue());
							salesRepo.save(salesEntityRes);

							// ********** Enter Financial transaction *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(salesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(salesEntityRes.getSaleId());
							ftEntity.setAmount(db_bulkamount.doubleValue());
							ftEntity.setCustomerId(salesEntityRes.getMemberid());
							ftEntity.setCustomerName(salesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("CreditPayment:" + bkpent.getBulkId());

							if (ftLatest == null) {
								ftEntity.setBalance(db_bulkamount.doubleValue());
							} else {
								BigDecimal ftTotal = new BigDecimal(db_bulkamount.doubleValue());
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added *******************

							// *******FT Start******
							ftbulkamount = ftbulkamount.add(db_bulkamount);
							// ******FT ended******/

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= salesEntityRes.getPaid()) {
								paymentEntity.setstatus("Paid");
							} else {
								paymentEntity.setstatus("Partial");
							}
							paymentEntity.setGrand_total(bulkamount);
							paymentEntity.setPref(pref);
							paymentEntity.setPtype("Deposit Pay");
							paymentEntity.setMember_id(salesEntityRes.getMemberid());
							paymentEntity.setMember_name(salesEntityRes.getMember_name());
							paymentEntity.setRsaleId(salesEntityRes.getSaleId());
							paymentEntity.setGrand_total(bulkamount);
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setNote(note);
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setSalesreferenceno(salesEntityRes.getReferenceno());
							paymentEntity.setCtype(salesEntityRes.getCtype());

							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno("CREDITPAY" + currentYear + "/" + currentmonth + "/"
									+ salesEntityRes.getSaleId() + "-" + bkpent.getBulkId());
							paymentRepo.save(paymentEntity);

							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);

							// ********** Payment Added *******************

							memberPojo.setCreditpayment(0);
							memberUserRepo.save(memberPojo);
							bulkamount = 0;

							break;

						}

					} else {
						LeoLogger.info("SaleServiceImpl---addBulkPayment---else part");
						SpecialSalesEntity specialsalesEntityRes = new SpecialSalesEntity();

						specialsalesEntityRes = specialsalesRepo.findBySaleId(bulksalelist.getSaleId());

						double pendingamount = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
						LeoLogger.info("SaleServiceImpl---addBulkPayment---Pending amount is [" + pendingamount + "]");

						if (bulkamount >= pendingamount)

						{
							LeoLogger.info("SaleServiceImpl---addBulkPayment---Bulk amount is [" + bulkamount
									+ "] and the remaining amount for invoice [" + specialsalesEntityRes.getSaleId()
									+ "] is ["
									+ (specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid()) + "]");

							// Addjust Sales entity paid value

							double paidamount = 0.0;
							paidamount = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
							db_paidamount = new BigDecimal(paidamount);
							db_paidamount = db_paidamount.setScale(2, RoundingMode.HALF_UP);
							LeoLogger.info(
									"SaleServiceImpl---addBulkPayment---db_paidamount is [" + db_paidamount + "]");

							specialsalesEntityRes.setPaid(specialsalesEntityRes.getGrand_total());
							specialsalesEntityRes.setPaymentstatus("Paid");
							specialsalesRepo.save(specialsalesEntityRes);

							// ********** Enter Financial transaction *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(specialsalesEntityRes.getSaleId());
							ftEntity.setAmount(db_paidamount.doubleValue());
							ftEntity.setCustomerId(specialsalesEntityRes.getMemberid());
							ftEntity.setCustomerName(specialsalesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("Payment");

							if (ftLatest == null) {
								ftEntity.setBalance(paidamount);
							} else {
								BigDecimal ftTotal = new BigDecimal(paidamount);
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added *******************

							// *******FT Start******
							ftbulkamount = ftbulkamount.add(db_paidamount);

							// ******FT ended******/

							// ********** Enter Payment with bulk id *******************

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= db_paidamount.doubleValue()) {
								paymentEntity.setstatus("Paid");
							} else {
								paymentEntity.setstatus("Partial");
							}

							paymentEntity.setMember_id(specialsalesEntityRes.getMemberid());
							paymentEntity.setMember_name(specialsalesEntityRes.getMember_name());
							paymentEntity.setRsaleId(specialsalesEntityRes.getSaleId());
							paymentEntity.setGrand_total(db_paidamount.doubleValue());
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setPref(pref);
							paymentEntity.setNote(note);
							paymentEntity.setPtype("Deposit Pay");
							paymentEntity.setCtype(specialsalesEntityRes.getCtype());
							paymentEntity.setSalesreferenceno(specialsalesEntityRes.getReferenceno());
							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno("CREDITPAY" + currentYear + "/" + currentmonth + "/"
									+ specialsalesEntityRes.getSaleId() + "-" + bkpent.getBulkId());
							paymentRepo.save(paymentEntity);

							// ********** Payment Added *******************

							BigDecimal savecreditamount = new BigDecimal(0.0);

							savecreditamount = new BigDecimal(memberPojo.getCreditpayment())
									.subtract(new BigDecimal(bulkamount));
							savecreditamount = savecreditamount.setScale(2, RoundingMode.HALF_UP);

							memberPojo.setCreditpayment(savecreditamount.doubleValue());
							memberUserRepo.save(memberPojo);

							bulkamount = bulkamount - paidamount;
							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);

						} else {

							BigDecimal db_bulkamount = new BigDecimal(0.0);
							db_bulkamount = new BigDecimal(bulkamount);
							db_bulkamount = db_bulkamount.setScale(2, RoundingMode.HALF_UP);

							BigDecimal setpayamount = new BigDecimal(bulkamount);

							setpayamount = setpayamount.add(new BigDecimal(specialsalesEntityRes.getPaid()));
							setpayamount = setpayamount.setScale(2, RoundingMode.HALF_UP);
							specialsalesEntityRes.setPaid(setpayamount.doubleValue());
							specialsalesRepo.save(specialsalesEntityRes);

							// ********** Enter Financial transaction *******************

							FinancialTransactionEntity ftLatest = financialTransactionsRepo
									.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntityRes.getMemberid());

							FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

							ftEntity.setInvoideId(specialsalesEntityRes.getSaleId());
							ftEntity.setAmount(db_bulkamount.doubleValue());
							ftEntity.setCustomerId(specialsalesEntityRes.getMemberid());
							ftEntity.setCustomerName(specialsalesEntityRes.getMember_name());
							ftEntity.setDate(new Date());
							ftEntity.setType("CreditPayment:" + bkpent.getBulkId());

							if (ftLatest == null) {
								ftEntity.setBalance(db_bulkamount.doubleValue());
							} else {
								BigDecimal ftTotal = new BigDecimal(db_bulkamount.doubleValue());
								LeoLogger.info(
										"SaleServiceImpl---addBulkPayment---Latest Financial Transation balance  ...Payment.."
												+ ftLatest.getBalance());
								ftTotal = new BigDecimal(ftLatest.getBalance()).subtract(ftTotal);
								ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
								ftEntity.setBalance(ftTotal.doubleValue());
							}
							financialTransactionsRepo.save(ftEntity);

							// ********** Financial transaction Added *******************

							// *******FT Start******
							ftbulkamount = ftbulkamount.add(db_bulkamount);
							// ******FT ended******/

							// ********** Enter Payment with bulk id *******************

							PaymentEntity paymentEntity = new PaymentEntity();
							if (bulkamount >= specialsalesEntityRes.getPaid()) {
								paymentEntity.setstatus("Paid");
							} else {
								paymentEntity.setstatus("Partial");
							}
							paymentEntity.setGrand_total(bulkamount);
							paymentEntity.setPref(pref);
							paymentEntity.setPtype("Deposit Pay");
							paymentEntity.setMember_id(specialsalesEntityRes.getMemberid());
							paymentEntity.setMember_name(specialsalesEntityRes.getMember_name());
							paymentEntity.setRsaleId(specialsalesEntityRes.getSaleId());
							paymentEntity.setGrand_total(bulkamount);
							paymentEntity.setPaymentdate(new Date());
							paymentEntity.setNote(note);
							paymentEntity.setBulkid(bkpent.getBulkId());
							paymentEntity.setCtype(specialsalesEntityRes.getCtype());
							paymentEntity.setSalesreferenceno(specialsalesEntityRes.getReferenceno());
							// paymentRepo.save(paymentEntity);
							Date d = new Date();
							int year = d.getYear();
							int currentYear = year + 1900;
							int currentmonth = d.getMonth() + 1;

							paymentEntity.setReferenceno("CREDITPAY" + currentYear + "/" + currentmonth + "/"
									+ specialsalesEntityRes.getSaleId());
							paymentRepo.save(paymentEntity);

							bulkPaymentEntity.setPaymentid(Long.toString(paymentEntity.getId()));
							bulkPaymentRepo.save(bulkPaymentEntity);

							memberPojo.setCreditpayment(0);
							memberUserRepo.save(memberPojo);

							// ********** Payment Added *******************
							bulkamount = 0;
							break;

						}

					}

				}
				ftBulkPaymentEntry(memberPojo, ftbulkamount, bulkPaymentEntity);

				if (bulkamount > 0)

				{
					// ********** Enter Financial transaction as Credit note *******************

					// Do nothing

				}

				resultVO.setMsgDescr("BUlk Paymentd done !!");
				resultVO.setError(false);

			} catch (Exception e) {
			//	logger.error("error in bulk Payment", e);
			}
		}
		return resultVO;
	}

	@Override
	public List<CustomerReportPojo> findAllCustomerSummary() {
		List<CustomerReportPojo> salePojoList = new ArrayList<CustomerReportPojo>();

		salePojoList = salesRepo.findAllCustomerReport();
		
		  String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role:"+ role);
		
		   if ("custom".equalsIgnoreCase(role)) {
		        salePojoList = salePojoList.stream()
		                .filter(cust -> !"Special".equalsIgnoreCase(cust.getCtype()))
		                .collect(Collectors.toList());
		       
		    }


		return salePojoList;
	}

	@Override
	public ResultVO deletePayment(long id) {
		ResultVO resultVO = new ResultVO();
		PaymentEntity paymentEntity = new PaymentEntity();

		try {

			BigDecimal balance = new BigDecimal(0.0);
			paymentEntity = paymentRepo.findById(id);
			LeoLogger.info("SaleServiceImpl--- Delete Payment Module --  Finding Payment Entry by payment id...");
			System.out.println(id);
			if (paymentEntity != null) {

				MemberUser memberUser = memberUserRepo.findById(paymentEntity.getMemberid());
				LeoLogger.info("SaleServiceImpl--- Delete Payment Module --  paymentEntity.getPtype.."
						+ paymentEntity.getPtype());
				if ("Deposit Pay".equals(paymentEntity.getPtype())) {
					LeoLogger.info(
							"SaleServiceImpl--- Delete Payment Module --  Deposit if." + paymentEntity.getPtype());
					BigDecimal deposit = new BigDecimal(memberUser.getDeposit());
					BigDecimal grandtotal = new BigDecimal(paymentEntity.getGrand_total());
					deposit = deposit.add(grandtotal);
					memberUser.setDeposit(deposit.doubleValue());
					memberUserRepo.save(memberUser);
					LeoLogger.info("SaleServiceImpl--- Delete Payment Module --  deposit aftter payment delete."
							+ memberUser.getDeposit());
				}

				LeoLogger.info("SaleServiceImpl--- Delete Payment Module --  Memeber and  Sales Entry found ...");
				if (!memberUser.getCtype().equalsIgnoreCase("Special")) {
					SalesEntity salesEntity = salesRepo.findBySaleId(paymentEntity.getRsaleId());
					balance = new BigDecimal(salesEntity.getPaid() - paymentEntity.getGrand_total());
					balance = balance.setScale(2, RoundingMode.HALF_UP);
					LeoLogger.info(
							"SaleServiceImpl---Delete Payment Module ( Non Special ) --  Reseting Sale Paid to ..."
									+ balance);
					salesEntity.setPaid(balance.doubleValue());
					salesEntity.setPaymentstatus("Due");
					salesEntity.setSale_status("Due");
					salesRepo.save(salesEntity);

					// ********** Enter Financial transaction *******************

					FinancialTransactionEntity ftransLatest = financialTransactionsRepo
							.findTopByCustomerIdOrderByFanIdDesc(salesEntity.getMemberid());

					FinancialTransactionEntity ftransEntity = new FinancialTransactionEntity();

					ftransEntity.setInvoideId(salesEntity.getSaleId());
					ftransEntity.setAmount(paymentEntity.getGrand_total());
					ftransEntity.setCustomerId(salesEntity.getMemberid());
					ftransEntity.setCustomerName(salesEntity.getMember_name());
					ftransEntity.setDate(new Date());
					ftransEntity.setType("Reverse Payment");

					if (ftransLatest == null) {
						ftransEntity.setBalance(paymentEntity.getGrand_total() * -1);
					} else {
						BigDecimal ftTotal = new BigDecimal(paymentEntity.getGrand_total());
						LeoLogger.info(
								"SaleServiceImpl---Reverese Payment ( Not Special )---Latest Financial Transation balance  ...Payment.."
										+ ftransLatest.getBalance());
						ftTotal = new BigDecimal(ftransLatest.getBalance()).add(ftTotal);
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						ftransEntity.setBalance(ftTotal.doubleValue());
					}
					financialTransactionsRepo.save(ftransEntity);

					// ********** Financial transaction Added *******************

					// ************************ Enter FT Statement *******************

					FTEntity ftLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(salesEntity.getMemberid());

					FTEntity ftEntity = new FTEntity();

					ftEntity.setInvoideId(salesEntity.getSaleId());
					ftEntity.setAmount(paymentEntity.getGrand_total());
					ftEntity.setCustomerId(salesEntity.getMemberid());
					ftEntity.setCustomerName(salesEntity.getMember_name());
					ftEntity.setDate(new Date());
					ftEntity.setType("Reverse Payment");

					if (ftLatest == null) {
						ftEntity.setBalance(paymentEntity.getGrand_total() * -1);
					} else {
						BigDecimal ftTotal = new BigDecimal(paymentEntity.getGrand_total());
						LeoLogger.info(
								"SaleServiceImpl---Reverese Payment ( Not Special )---Latest Financial Statement balance  ...Payment.."
										+ ftLatest.getBalance());
						ftTotal = new BigDecimal(ftLatest.getBalance()).add(ftTotal);
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						ftEntity.setBalance(ftTotal.doubleValue());
					}
					fTRepo.save(ftEntity);

					// ********** FT Statement Added *******************

				} else

				{
					SpecialSalesEntity specialsalesEntity = specialsalesRepo.findBySaleId(paymentEntity.getRsaleId());
					balance = new BigDecimal(specialsalesEntity.getPaid() - paymentEntity.getGrand_total());
					balance = balance.setScale(2, RoundingMode.HALF_UP);
					LeoLogger.info("SaleServiceImpl---Delete Payment Module ( Special ) --  Reseting Sale Paid to ..."
							+ balance);
					specialsalesEntity.setPaid(balance.doubleValue());
					specialsalesEntity.setPaymentstatus("Due");
					specialsalesEntity.setSale_status("Due");
					specialsalesRepo.save(specialsalesEntity);

					// ********** Enter Financial transaction *******************

					FinancialTransactionEntity ftransLatest = financialTransactionsRepo
							.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntity.getMemberid());

					FinancialTransactionEntity ftransEntity = new FinancialTransactionEntity();

					ftransEntity.setInvoideId(specialsalesEntity.getSaleId());
					ftransEntity.setAmount(paymentEntity.getGrand_total());
					ftransEntity.setCustomerId(specialsalesEntity.getMemberid());
					ftransEntity.setCustomerName(specialsalesEntity.getMember_name());
					ftransEntity.setDate(new Date());
					ftransEntity.setType("Reverse Payment");

					if (ftransLatest == null) {
						ftransEntity.setBalance(paymentEntity.getGrand_total() * -1);
					} else {
						BigDecimal ftTotal = new BigDecimal(paymentEntity.getGrand_total());
						LeoLogger.info(
								"SaleServiceImpl--- Reverse Payment ( Special ) ---Latest Financial Transation balance  ...Payment.."
										+ ftransLatest.getBalance());
						ftTotal = new BigDecimal(ftransLatest.getBalance()).add(ftTotal);
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						ftransEntity.setBalance(ftTotal.doubleValue());
					}
					financialTransactionsRepo.save(ftransEntity);

					// ********** Financial transaction Added *******************

					// ************************ Enter FT Statement *******************

					FTEntity ftLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(specialsalesEntity.getMemberid());

					FTEntity ftEntity = new FTEntity();

					ftEntity.setInvoideId(specialsalesEntity.getSaleId());
					ftEntity.setAmount(paymentEntity.getGrand_total());
					ftEntity.setCustomerId(specialsalesEntity.getMemberid());
					ftEntity.setCustomerName(specialsalesEntity.getMember_name());
					ftEntity.setDate(new Date());
					ftEntity.setType("Reverse Payment");

					if (ftLatest == null) {
						ftEntity.setBalance(paymentEntity.getGrand_total() * -1);
					} else {
						BigDecimal ftTotal = new BigDecimal(paymentEntity.getGrand_total());
						LeoLogger.info(
								"SaleServiceImpl---Reverese Payment ( Not Special )---Latest Financial Statement balance  ...Payment.."
										+ ftLatest.getBalance());
						ftTotal = new BigDecimal(ftLatest.getBalance()).add(ftTotal);
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						ftEntity.setBalance(ftTotal.doubleValue());
					}
					fTRepo.save(ftEntity);

					// ********** FT Statement Added *******************

				}
				// *****Register entry

				RegisterEntity registerEntity = new RegisterEntity();

				registerEntity = registerRepo.findAByDate(new Date());
				/*
				 * BigDecimal balance = new BigDecimal(0.0); balance = new
				 * BigDecimal(salesEntity.getGrand_total() - salesEntity.getPaid()); balance =
				 * balance.setScale(2, RoundingMode.HALF_UP);
				 * LeoLogger.info("SaleServiceImpl---Original Payment amount ..."+origamountt);
				 * LeoLogger.info("SaleServiceImpl---balance ..."+balance);
				 */

				if (registerEntity != null)

				{
					LeoLogger.info("SaleServiceImpl---addPayment-- Register entry found for today  ....."
							+ registerEntity.getSalesamount());
					if (paymentEntity.getPtype().equalsIgnoreCase("cash")) {
						if (!memberUser.getCtype().equalsIgnoreCase("Special")) {
							LeoLogger.info("SaleServiceImpl---addPayment-- cash....");
							LeoLogger.info("SaleServiceImpl---addPayment-- cash..origamountt.."
									+ paymentEntity.getGrand_total());
							BigDecimal ftTotal = new BigDecimal(0.0);
							ftTotal = new BigDecimal(registerEntity.getCashpayment() - paymentEntity.getGrand_total());
							ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
							LeoLogger.info("SaleServiceImpl---balance ..." + ftTotal);
							registerEntity.setCashpayment(ftTotal.doubleValue());
							registerEntity
									.setClosingbal(registerEntity.getClosingbal() - paymentEntity.getGrand_total());
						} else {
							LeoLogger.info("SaleServiceImpl---addPayment-- cash....");
							BigDecimal fftTotal = new BigDecimal(0.0);
							fftTotal = new BigDecimal(registerEntity.getCashpayment() - paymentEntity.getGrand_total());
							fftTotal = fftTotal.setScale(2, RoundingMode.HALF_UP);
							LeoLogger.info("SaleServiceImpl---balance ftTotal..." + fftTotal);
							registerEntity.setCashpayment(fftTotal.doubleValue());
							registerEntity
									.setClosingbal(registerEntity.getClosingbal() - paymentEntity.getGrand_total());
						}
					} else if (paymentEntity.getPtype().equalsIgnoreCase("cheque")) {
						LeoLogger.info("SaleServiceImpl---addPayment-- cheque....");
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getChequepayment() - paymentEntity.getGrand_total());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						registerEntity.setChequepayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() - paymentEntity.getGrand_total());
					} else if (paymentEntity.getPtype().equalsIgnoreCase("online")) {
						LeoLogger.info("SaleServiceImpl---addPayment-- online....");
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getOnlinepayment() - paymentEntity.getGrand_total());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						registerEntity.setOnlinepayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() - paymentEntity.getGrand_total());
					} else {
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(
								registerEntity.getCreditcardpayment() - paymentEntity.getGrand_total());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						registerEntity.setCreditcardpayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() - paymentEntity.getGrand_total());
					}
					registerRepo.save(registerEntity);
				} else {
					RegisterEntity newregisterEntity = new RegisterEntity();
					newregisterEntity.setCashinhand(1000.00);
					newregisterEntity.setDate(new Date());
					if (paymentEntity.getPtype().equalsIgnoreCase("cash")) {
						newregisterEntity.setCashpayment(paymentEntity.getGrand_total());
					} else if (paymentEntity.getPtype().equalsIgnoreCase("cheque")) {
						newregisterEntity.setChequepayment(paymentEntity.getGrand_total());
					} else if (paymentEntity.getPtype().equalsIgnoreCase("online")) {
						newregisterEntity.setOnlinepayment(paymentEntity.getGrand_total());
					} else {
						newregisterEntity.setCreditcardpayment(paymentEntity.getGrand_total());
					}
					newregisterEntity.setOpeningbal(1000.00);
					newregisterEntity.setClosingbal(1000.00 - paymentEntity.getGrand_total());
					newregisterEntity.setRefunds(0.00);
					newregisterEntity.setReferenceno(String.valueOf(paymentEntity.getRsaleId()));
					newregisterEntity.setSalesamount(0.0);
					newregisterEntity.setStatus("Open");
					registerRepo.save(newregisterEntity);

					LeoLogger.info("SaleServiceImpl---addPayment--- New Register Entry made");

				}

				// **************************** Register Details Entry ended
				// *****Register history Entry****
				applyRegisterhistorydeletepayment(paymentEntity);

				paymentRepo.delete(paymentEntity);
				resultVO.setMsgDescr("Payment Deleted Sucessfully");
				resultVO.setError(false);
				return resultVO;

			} else {

				resultVO.setMsgDescr("Not Found");
				resultVO.setError(false);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO deleteBulkPayment(long bulkid) {
		ResultVO resultVO = new ResultVO();
		BulkPaymentEntity bulkpaymentEntity = new BulkPaymentEntity();
		BigDecimal bulkamount = new BigDecimal(0.0);
		BigDecimal origbulkamount = new BigDecimal(0.0);
		BigDecimal balance = new BigDecimal(0.0);

		try {

			bulkpaymentEntity = bulkPaymentRepo.findByBulkId(bulkid);
			if (bulkpaymentEntity != null) {
				bulkamount = new BigDecimal(bulkpaymentEntity.getAmount());
				// origbulkamount = bulkamount;
			}

			LeoLogger.info(
					"SaleServiceImpl--- Delete Bulk Payment Module --  Bulk Payment Entity found  having total amount ..."
							+ bulkpaymentEntity.getAmount());
			List<PaymentEntity> paymentEntityList = new ArrayList<>();
			paymentEntityList = paymentRepo.findAllBybulkid(bulkid);
			LeoLogger.info("SaleServiceImpl--- Delete Bulk Payment Module --  Finding Payments Entry by bulk id... >> "
					+ paymentEntityList.toString());
			MemberUser memberUser = new MemberUser();

			if (paymentEntityList != null) {

				for (PaymentEntity payent : paymentEntityList) {
					memberUser = memberUserRepo.findById(payent.getMemberid());

					LeoLogger.info(
							"SaleServiceImpl--- Delete Bulk Payment Module --  Member and  Sales Entry found ...");
					if (!memberUser.getCtype().equalsIgnoreCase("Special")) {
						SalesEntity salesEntity = salesRepo.findBySaleId(payent.getRsaleId());
						balance = new BigDecimal(salesEntity.getPaid() - payent.getGrand_total());
						balance = balance.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info(
								"SaleServiceImpl---Delete Bulk Payment Module ( Non Special ) --  Reseting Sale Paid to ..."
										+ balance);
						salesEntity.setPaid(balance.doubleValue());
						salesEntity.setPaymentstatus("Due");
						salesEntity.setSale_status("Due");
						salesRepo.save(salesEntity);
						bulkamount = bulkamount.subtract(new BigDecimal(payent.getGrand_total()));
						bulkamount = bulkamount.setScale(2, RoundingMode.HALF_UP);
						origbulkamount = origbulkamount.add(new BigDecimal(payent.getGrand_total()));
						origbulkamount = origbulkamount.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info(
								"SaleServiceImpl---Delete Bulk Payment Module ( Non Special ), Total bulk amount to be deducted >>>> "
										+ origbulkamount);

					} else {
						SpecialSalesEntity specialsalesEntity = specialsalesRepo.findBySaleId(payent.getRsaleId());
						balance = new BigDecimal(specialsalesEntity.getPaid() - payent.getGrand_total());
						balance = balance.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info(
								"SaleServiceImpl---Delete Bulk Payment Module ( Non Special ) --  Reseting Sale Paid to ..."
										+ balance);
						specialsalesEntity.setPaid(balance.doubleValue());
						specialsalesEntity.setPaymentstatus("Due");
						specialsalesEntity.setSale_status("Due");
						specialsalesRepo.save(specialsalesEntity);
						bulkamount = bulkamount.subtract(new BigDecimal(payent.getGrand_total()));
						bulkamount = bulkamount.setScale(2, RoundingMode.HALF_UP);
						origbulkamount = origbulkamount.add(new BigDecimal(payent.getGrand_total()));
						origbulkamount = origbulkamount.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info(
								"SaleServiceImpl---Delete Bulk Payment Module ( Non Special ), Total bulk amount to be deducted >>>> "
										+ origbulkamount);
					}
				}

				// ********** Enter Financial transaction *******************

				FinancialTransactionEntity ftransLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(memberUser.getId());

				FinancialTransactionEntity ftransEntity = new FinancialTransactionEntity();

				ftransEntity.setInvoideId(bulkpaymentEntity.getBulkId());
				ftransEntity.setAmount(origbulkamount.doubleValue());
				ftransEntity.setCustomerId(memberUser.getId());
				ftransEntity.setCustomerName(memberUser.getName());
				ftransEntity.setDate(new Date());
				ftransEntity.setType("Reverse Bulk Payment");

				if (ftransLatest == null) {
					ftransEntity.setBalance(origbulkamount.doubleValue() * -1);
				} else {
					BigDecimal ftTotal = origbulkamount;
					LeoLogger.info(
							"SaleServiceImpl--- Reverse Bulk Payment ---Latest Financial Transation balance  ...Payment.."
									+ ftransLatest.getBalance());
					ftTotal = new BigDecimal(ftransLatest.getBalance()).add(ftTotal);
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					ftransEntity.setBalance(ftTotal.doubleValue());
				}
				financialTransactionsRepo.save(ftransEntity);

				// ********** Financial transaction Added *******************

				// ************************ Enter FT Statement *******************

				FTEntity ftLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(memberUser.getId());

				FTEntity ftEntity = new FTEntity();

				ftEntity.setInvoideId(bulkpaymentEntity.getBulkId());
				ftEntity.setAmount(origbulkamount.doubleValue());
				ftEntity.setCustomerId(memberUser.getId());
				ftEntity.setCustomerName(memberUser.getName());
				ftEntity.setDate(new Date());
				ftEntity.setType("Reverse Bulk Payment");

				if (ftLatest == null) {
					ftEntity.setBalance(origbulkamount.doubleValue() * -1);
				} else {
					BigDecimal ftTotal = origbulkamount;
					LeoLogger.info(
							"SaleServiceImpl---Reverese Bulk Payment ---Latest Financial Statement balance  ...Payment.."
									+ ftLatest.getBalance());
					ftTotal = new BigDecimal(ftLatest.getBalance()).add(ftTotal);
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					ftEntity.setBalance(ftTotal.doubleValue());
				}
				fTRepo.save(ftEntity);

				// ********** FT Statement Added *******************

				if (bulkamount.compareTo(new BigDecimal(0.0)) == 1) {
					BigDecimal bdbulkamount = bulkamount;
					bdbulkamount = new BigDecimal(memberUser.getCreditpayment()).subtract(bulkamount);
					bdbulkamount = bdbulkamount.setScale(2, RoundingMode.HALF_UP);
					memberUser.setCreditpayment(bdbulkamount.doubleValue());
					memberUserRepo.save(memberUser);
					LeoLogger.info(
							"SaleServiceImpl---Delete Bulk Payment Module, Remaining Credit amount to be deducted >>>> "
									+ bulkamount);
				}

				paymentRepo.deleteAll(paymentEntityList);

			}

			bulkPaymentRepo.delete(bulkpaymentEntity);
			// *****Register entry

		
			/*
			 * BigDecimal balance = new BigDecimal(0.0); balance = new
			 * BigDecimal(salesEntity.getGrand_total() - salesEntity.getPaid()); balance =
			 * balance.setScale(2, RoundingMode.HALF_UP);
			 * LeoLogger.info("SaleServiceImpl---Original Payment amount ..."+origamountt);
			 * LeoLogger.info("SaleServiceImpl---balance ..."+balance);
			 */
			
			if (!memberUser.getCtype().equalsIgnoreCase("Special")) {
			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());

			if (registerEntity != null)

			{
				LeoLogger.info("SaleServiceImpl---addPayment-- Register entry found for today  ....."
						+ registerEntity.getSalesamount());
				if (bulkpaymentEntity.getPtype().equalsIgnoreCase("cash")) {
					if (!memberUser.getCtype().equalsIgnoreCase("Special")) {
						LeoLogger.info("SaleServiceImpl---addPayment-- cash....");
						LeoLogger.info("SaleServiceImpl---bulkpaymentEntity-- cash..origamountt.."
								+ bulkpaymentEntity.getAmount());
						BigDecimal ftTotal = new BigDecimal(0.0);
						ftTotal = new BigDecimal(registerEntity.getCashpayment() - bulkpaymentEntity.getAmount());
						ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info("SaleServiceImpl---balance ..." + ftTotal);
						registerEntity.setCashpayment(ftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() - bulkpaymentEntity.getAmount());
					} else {
						LeoLogger.info("SaleServiceImpl---addPayment-- cash....");
						BigDecimal fftTotal = new BigDecimal(0.0);
						fftTotal = new BigDecimal(registerEntity.getCashpayment() - bulkpaymentEntity.getAmount());
						fftTotal = fftTotal.setScale(2, RoundingMode.HALF_UP);
						LeoLogger.info("SaleServiceImpl---balance ftTotal..." + fftTotal);
						registerEntity.setCashpayment(fftTotal.doubleValue());
						registerEntity.setClosingbal(registerEntity.getClosingbal() - bulkpaymentEntity.getAmount());
					}
				} else if (bulkpaymentEntity.getPtype().equalsIgnoreCase("cheque")) {
					LeoLogger.info("SaleServiceImpl---addPayment-- cheque....");
					BigDecimal ftTotal = new BigDecimal(0.0);
					ftTotal = new BigDecimal(registerEntity.getChequepayment() - bulkpaymentEntity.getAmount());
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					registerEntity.setChequepayment(ftTotal.doubleValue());
					registerEntity.setClosingbal(registerEntity.getClosingbal() - bulkpaymentEntity.getAmount());
				} else if (bulkpaymentEntity.getPtype().equalsIgnoreCase("online")) {
					LeoLogger.info("SaleServiceImpl---addPayment-- online....");
					BigDecimal ftTotal = new BigDecimal(0.0);
					ftTotal = new BigDecimal(registerEntity.getOnlinepayment() - bulkpaymentEntity.getAmount());
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					registerEntity.setOnlinepayment(ftTotal.doubleValue());
					registerEntity.setClosingbal(registerEntity.getClosingbal() - bulkpaymentEntity.getAmount());
				} else {
					BigDecimal ftTotal = new BigDecimal(0.0);
					ftTotal = new BigDecimal(registerEntity.getCreditcardpayment() - bulkpaymentEntity.getAmount());
					ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
					registerEntity.setCreditcardpayment(ftTotal.doubleValue());
					registerEntity.setClosingbal(registerEntity.getClosingbal() - bulkpaymentEntity.getAmount());
				}
				registerRepo.save(registerEntity);
			} else {
				RegisterEntity newregisterEntity = new RegisterEntity();
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				if (bulkpaymentEntity.getPtype().equalsIgnoreCase("cash")) {
					newregisterEntity.setCashpayment(bulkpaymentEntity.getAmount());
				} else if (bulkpaymentEntity.getPtype().equalsIgnoreCase("cheque")) {
					newregisterEntity.setChequepayment(bulkpaymentEntity.getAmount());
				} else if (bulkpaymentEntity.getPtype().equalsIgnoreCase("online")) {
					newregisterEntity.setOnlinepayment(bulkpaymentEntity.getAmount());
				} else {
					newregisterEntity.setCreditcardpayment(bulkpaymentEntity.getAmount());
				}
				newregisterEntity.setOpeningbal(1000.00);
				newregisterEntity.setClosingbal(1000.00 - bulkpaymentEntity.getAmount());
				newregisterEntity.setRefunds(0.00);
				newregisterEntity.setReferenceno(String.valueOf(bulkpaymentEntity.getAmount()));
				newregisterEntity.setSalesamount(0.0);
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);

				LeoLogger.info("SaleServiceImpl---bulkpaymentEntity--- New Register Entry made");

			}

			// **************************** Register Details Entry ended
			// ****Register history entry
			applyRegisterhistorydeletebulk(bulkpaymentEntity);
			} else {
				
			       saveSpecialRegisterForDeletePayment(new BigDecimal(bulkpaymentEntity.getAmount()));
			       deleteSpecialRegisterHistoryForBulkPayment(bulkpaymentEntity);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	@Override
	public List<RequestQuoteItemEntity> getRequestQuotesItembyquoteId(String quoteId) {
		List<RequestQuoteItemEntity> requestQuoteEntityList = new ArrayList<RequestQuoteItemEntity>();
		List<RequestQuotesItemsPojo> requestQuotePojoList = new ArrayList<RequestQuotesItemsPojo>();

		try {
			requestQuoteEntityList = requestQuoteItemRepo.findByrqidOrderByIdAsc(Long.parseLong(quoteId));
			requestQuoteEntityList = mapper.map(requestQuoteEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();

		}

		return requestQuoteEntityList;
	}

	@Override
	public RequestQuotePojo getrequestquotebyquotesId(long id) {
		RequestQuoteEntity requestquoteEntity = new RequestQuoteEntity();
		requestquoteEntity = requestQuoteRepo.findByRqId(id);
		LeoLogger.info("SaleServiceImpl---getquotebyquotesId---" + requestquoteEntity.toString());
		RequestQuotePojo rquotesPojo = new RequestQuotePojo();
		rquotesPojo = mapper.map(requestquoteEntity, RequestQuotePojo.class);
		// rquotesPojo.setCreatedBy(requestquoteEntity.getCreatedBy());
		return rquotesPojo;
	}

	@Override
	public ResultVO updaterequestQuote(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();
		try {

			Long quotesId = 0L;
			BigDecimal Old_sale_grand_total = new BigDecimal(0.0);
			BigDecimal New_sale_grand_total = new BigDecimal(0.0);
			BigDecimal Diff_amount = new BigDecimal(0.0);
			RequestQuoteEntity rquotesEntity = new RequestQuoteEntity();

			List<RequestQuoteItemEntity> rquotesItemEntityList = new ArrayList<RequestQuoteItemEntity>();

			// Get Sales Entity and Sales Items Entity using the sale id received
			for (AddItemReqPojo additem : addItemReqPojos) {
				System.out.println(additem);
				quotesId = additem.getSaleId();
			}
			LeoLogger.info("SaleServiceImpl---updateQuotes >>>>>>>> quotesId is >>> " + quotesId.toString());

			if (quotesId != null) {
				rquotesEntity = requestQuoteRepo.findByRqId(quotesId);
				rquotesItemEntityList = requestQuoteItemRepo.findByrqidOrderByIdAsc(quotesId);

				LeoLogger.info(
						"SaleServiceImpl---updateQuotes >>>>>>>> quotesEntity is >>> " + rquotesEntity.toString());
				LeoLogger.info("SaleServiceImpl---updateQuotes >>>>>>>> quotesItemEntityList  is >>> "
						+ rquotesItemEntityList.toString());

				// Old_sale_grand_total = new BigDecimal(quotesEntity.getGrand_total());

			}

			// Add quantity of products in productdetails for Sales Items received from the

			requestQuoteItemRepo.deleteAll(rquotesItemEntityList);
			// quotesItemRepo.deleteAllByquotesid(quotesId);
			// Update Existing Sale Entity instead of making a new one

			String Supplier_Email = "support@leonet.in", Supplier_Name = "";

			// Document document = new Document();

			String path = new File("").getAbsolutePath();

			// LeoLogger.info(path);

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				rquotesEntity.setDate(new Date());
				rquotesEntity.setEmail(Supplier_Email);
				Supplier_Email = additem.getEmail();
				Supplier_Name = additem.getNote();

				// LeoLogger.info("Member Pojo ....." + memberPojo.toString());

				rquotesEntity.setReferenceno("POS");
				rquotesEntity.setEmail(Supplier_Email);
				rquotesEntity.setSuppliername(Supplier_Name);

				// saleEntity.setUser_id();
			}

			RequestQuoteEntity rqenty = requestQuoteRepo.save(rquotesEntity);

			List<RequestQuoteItemEntity> requestquoteItemList = new ArrayList<>();
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				RequestQuoteItemEntity rqItemEntity = new RequestQuoteItemEntity();
				rqItemEntity.setRqid(rqenty.getRqId());
				rqItemEntity.setProduct_id(additem.getProductId());
				rqItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				rqItemEntity.setProduct_code(additem.getProductId().toString());
				rqItemEntity.setProduct_name(additem.getProductName());
				rqItemEntity.setMpn(productDetailsEnt.getcf1());

				rqItemEntity = requestQuoteItemRepo.save(rqItemEntity);
				requestquoteItemList.add(rqItemEntity);

				// addRows(table, rqItemEntity);

				// Make PDF and send email attachment
			}

			rqenty.setFileName(customFileUploadUtil.savePdfFileIntoDir("", requestquoteItemList, rqenty));
			requestQuoteRepo.save(rquotesEntity);

			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("PO Raised for " + Supplier_Name, font);

			// document.add(chunk);

			// document.add(table);
			// document.close();

			MailSendingAPI api = new MailSendingAPI();
			String from = "support@leonet.in", pass = "2c;UFEvB90", cc = "moninder@leonet.in",
					subject = "Quote Request", bcc = "", body = "Dear Customer, "
							+ "\n\nAttached please find invoice for Account number r more information.\n\n\nRegards,";

			// api.MailDepartment(from, pass, Supplier_Email, cc, subject, body, document);

			resultVO.setMsgDescr("Request Quote Added Sucessfully and mail sent");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}
	@Override
	public List<ProfitLossReportPojo> findAllProfitLossSummary(String startDate, String endDate) {
		return salesRepo.findAllProfitLossReport(startDate, endDate);
	}

	@Override
	public double calgrandtoatl() {
		List<SalesEntity> salesEntity = salesRepo.findAll();
		double grand_total = 0.0;

		if (salesEntity != null) {
			for (SalesEntity salesEnt : salesEntity)
				grand_total = grand_total + salesEnt.getGrand_total();

		}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.6f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();
	}

	@Override
	public double calqtoatl() {
		List<QuotesEntity> quotesEntity = quotesRepo.findAll();
		double grand_total = 0.0;

		if (quotesEntity != null) {
			for (QuotesEntity quoteEnt : quotesEntity);


		}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.6f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();
	}

	@Override
	public double calptoatl() {
		List<PaymentEntity> paymentEntity = paymentRepo.findAllBybulkid(0);
		double grand_total = 0.0;

		if (paymentEntity != null) {
			for (PaymentEntity paymentEnt : paymentEntity)
				grand_total = grand_total + paymentEnt.getGrand_total();

		}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.6f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();
	}

	@Override
	public double calrtoatl() {
		List<ReturnsEntity> returnEntity = returnsRepo.findAll();
		double grand_total = 0.0, total = 0.0;

		if (returnEntity != null) {
			for (ReturnsEntity returnEnt : returnEntity)
				//
				grand_total = grand_total + returnEnt.getAmount() + returnEnt.getTax();

		}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.6f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();
	}

	@Override
	public double calbtoatl() {
		List<BulkPaymentEntity> bulkpaymentEntity = bulkpaymentRepo.findAll();
		double grand_total = 0.0;

		if (bulkpaymentEntity != null) {
			for (BulkPaymentEntity bulkpaymentEnt : bulkpaymentEntity)
				grand_total = grand_total + bulkpaymentEnt.getAmount();

		}
		// double roundOff = Math.round(grand_total * 100) / 100;
		// String.format("%.6f", roundOff);

		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();
	}

	private void applyRegisterhistory(SalesEntity saleenty) {

		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(saleenty.getReferenceno());
		newregisterEntity.setSalesamount(saleenty.getGrand_total());
		newregisterEntity.setStatus("Invoice");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistory---New Registerhistory Entry made");
	}

	private void applyRegisterhistoryspecial(SpecialSalesEntity specialsaleEntity) {

		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setOnlinepayment(0.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(specialsaleEntity.getReferenceno());
		newregisterEntity.setSalesamount(specialsaleEntity.getGrand_total());
		newregisterEntity.setStatus("Invoice");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistorysplecial---New Registerhistory Entry made");
	}

	private void applyRegisterhistorypayment(PaymentEntity paymentEntity) {
		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		if (paymentEntity.getPtype().equalsIgnoreCase("cash")) {
			newregisterEntity.setCashpayment(paymentEntity.getGrand_total());
		} else if (paymentEntity.getPtype().equalsIgnoreCase("cheque")) {
			newregisterEntity.setChequepayment(paymentEntity.getGrand_total());
			newregisterEntity.setNote(paymentEntity.getPref());
		} else if (paymentEntity.getPtype().equalsIgnoreCase("online")) {
			newregisterEntity.setOnlinepayment(paymentEntity.getGrand_total());
		} else {
			newregisterEntity.setCreditcardpayment(paymentEntity.getGrand_total());
		}
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00 + paymentEntity.getGrand_total());
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno((paymentEntity.getReferenceno()));
		newregisterEntity.setSalesamount(0.0);
		newregisterEntity.setStatus("Payment");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl---applyRegisterhistorypayment--- New Registerhistory Entry made");

	}

	private void applyRegisterhistorybulkpayment(BulkPaymentEntity bulkPaymentEntity) {
		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		if (bulkPaymentEntity.getPtype().equalsIgnoreCase("cash")) {
			newregisterEntity.setCashpayment(bulkPaymentEntity.getAmount());
		} else if (bulkPaymentEntity.getPtype().equalsIgnoreCase("cheque")) {
			newregisterEntity.setChequepayment(bulkPaymentEntity.getAmount());
			newregisterEntity.setNote(bulkPaymentEntity.getPref());
		} else if (bulkPaymentEntity.getPtype().equalsIgnoreCase("online")) {
			newregisterEntity.setOnlinepayment(bulkPaymentEntity.getAmount());
		} else {
			newregisterEntity.setCreditcardpayment(bulkPaymentEntity.getAmount());
		}
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00 + bulkPaymentEntity.getAmount());
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(String.valueOf(bulkPaymentEntity.getBulkId()));
		newregisterEntity.setSalesamount(0.0);
		newregisterEntity.setStatus("BulkPayment");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl---applyRegisterhistorybulkpayment--- New Registerhistory Entry made");

	}

	private void applyRegisterhistorysaleedit(SalesEntity saleEntity) {
		// After editing/delete if the entry should go once then please uncomment the
		// following
		/*
		 * List<RegisterhistoryEntity> registerList =
		 * registerhistoryRepo.findByreferenceno(saleEntity.getReferenceno());
		 * 
		 * registerhistoryRepo.delete(registerEntity);
		 */

		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(saleEntity.getReferenceno());
		newregisterEntity.setSalesamount(saleEntity.getGrand_total());
		newregisterEntity.setStatus("Invoice Edit");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistory---New Registerhistory Entry made");

	}

	private void applyRegisterhistoryspecialedit(SpecialSalesEntity saleEntity) {
		// After editing/delete if the entry should go once then please uncomment the
		// following
		/*
		 * List<RegisterhistoryEntity> registerList =
		 * registerhistoryRepo.findByreferenceno(saleEntity.getReferenceno());
		 * 
		 * registerhistoryRepo.delete(registerEntity);
		 */

		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(saleEntity.getReferenceno());
		newregisterEntity.setSalesamount(saleEntity.getGrand_total());
		newregisterEntity.setStatus("Invoice Edit");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistory---New Registerhistory Entry made");

	}

	private void applyRegisterhistoryspecialdelete(SpecialSalesEntity saleEntity) {
		// After editing/delete if the entry should go once then please uncomment the
		// following

		/*
		 * List<RegisterhistoryEntity> registerList =
		 * registerhistoryRepo.findByreferenceno(saleEntity.getReferenceno());
		 * //registerList=registerhistoryRepo.findByreferenceno(saleEntity.
		 * getReferenceno()); registerhistoryRepo.deleteAll(registerList);
		 */

		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(saleEntity.getReferenceno());
		newregisterEntity.setSalesamount(0);
		newregisterEntity.setStatus("Delete");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistorydelete---New Registerhistory Entry made");

	}

	private void applyRegisterhistorydelete(SalesEntity saleEntity) {
		// After editing/delete if the entry should go once then please uncomment the
		// following

		/*
		 * List<RegisterhistoryEntity> registerList =
		 * registerhistoryRepo.findByreferenceno(saleEntity.getReferenceno());
		 * //registerList=registerhistoryRepo.findByreferenceno(saleEntity.
		 * getReferenceno()); registerhistoryRepo.deleteAll(registerList);
		 */

		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(saleEntity.getReferenceno());
		newregisterEntity.setSalesamount(newregisterEntity.getSalesamount() - saleEntity.getGrand_total());
		newregisterEntity.setStatus("Delete");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistorydelete---New Registerhistory Entry made");

	}

	private void applyRegisterhistorydeletepayment(PaymentEntity paymentEntity) {
		// After editing/delete if the entry should go once then please uncomment the
		// following

		/*
		 * List<RegisterhistoryEntity> registerList =
		 * registerhistoryRepo.findByreferenceno(paymentEntity.getReferenceno());
		 * 
		 * registerhistoryRepo.deleteAll(registerList);
		 */

		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		if (paymentEntity.getPtype().equalsIgnoreCase("cash")) {
			newregisterEntity.setCashpayment(0);
		} else if (paymentEntity.getPtype().equalsIgnoreCase("cheque")) {
			newregisterEntity.setChequepayment(0);
		} else if (paymentEntity.getPtype().equalsIgnoreCase("online")) {
			newregisterEntity.setOnlinepayment(0);
		} else {
			newregisterEntity.setCreditcardpayment(0);
		}
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno((paymentEntity.getReferenceno()));
		newregisterEntity.setSalesamount(0.0);
		newregisterEntity.setStatus(" DeletePayment");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl---applyRegisterhistorydeletepayment--- New Registerhistory Entry made");

	}

	private void applyRegisterhistorydeletebulk(BulkPaymentEntity bulkPaymentEntity) {

		// After editing/delete if the entry should go once then please uncomment the
		// following

		/*
		 * List<RegisterhistoryEntity> registerList =
		 * registerhistoryRepo.findByreferenceno(bulkPaymentEntity.getReferenceno());
		 * 
		 * registerhistoryRepo.deleteAll(registerList);
		 */
		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		if (bulkPaymentEntity.getPtype().equalsIgnoreCase("cash")) {
			newregisterEntity.setCashpayment(0);
		} else if (bulkPaymentEntity.getPtype().equalsIgnoreCase("cheque")) {
			newregisterEntity.setChequepayment(0);
		} else if (bulkPaymentEntity.getPtype().equalsIgnoreCase("online")) {
			newregisterEntity.setOnlinepayment(0);
		} else {
			newregisterEntity.setCreditcardpayment(0);
		}
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setRefunds(0.00);
		newregisterEntity.setReferenceno(String.valueOf(bulkPaymentEntity.getBulkId()));
		newregisterEntity.setSalesamount(0.0);
		newregisterEntity.setStatus(" Delete BulkPayment");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl---applyRegisterhistorybulkpayment--- New Registerhistory Entry made");

	}

	@Override
	public List<PaymentPojo> getPaymentList() {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllByOrderByIdDesc();
		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {
			LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId");
			for (PaymentEntity paymentEntityRes : paymentEntityList) {

				PaymentPojo PaymentPojo = new PaymentPojo();
				PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
				paymentPojoList.add(PaymentPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public List<SalePojo> getSalesListNew(ModelMap modelMap, int page) {
		List<SpecialSalesEntity> specialsalesEntityList = new ArrayList<SpecialSalesEntity>();
		List<SalePojo> salePojoList = new ArrayList<SalePojo>();
		List<SalesEntity> salesEntityList = new ArrayList<SalesEntity>();
		int recordsLength = 10;

		LeoLogger.info("SaleServiceImpl---getSalesList-");
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String loggedInRole = "";
	        // Check if the user is authenticated
	        if (authentication != null && authentication.isAuthenticated()) {
	            // Iterate through authorities (roles) and return them
	            for (GrantedAuthority authority : authentication.getAuthorities()) {
	            	loggedInRole = authority.getAuthority();  // Return the role of the user
	            }
	        }
			LeoLogger.info("SaleServiceImpl---loggedInRole-"+loggedInRole);

		Pageable paging = PageRequest.of(page, recordsLength);
		Page<SalesEntity> sale;
		// sale = salesRepo.findAll(paging);
		/*if(loggedInRole.equalsIgnoreCase("sales")||(loggedInRole.equalsIgnoreCase("cashier"))) {
			 LocalDate today = LocalDate.now();
		        LocalDate startOfPreviousMonth = today.minusMonths(1).withDayOfMonth(1);
		        LocalDate endOfCurrentMonth = today.withDayOfMonth(today.lengthOfMonth());

		        Date startDate = Date.from(startOfPreviousMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        Date endDate = Date.from(endOfCurrentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        
		        LeoLogger.info("SaleServiceImpl---loggedInRole startDate-"+startDate);
		        LeoLogger.info("SaleServiceImpl---loggedInRole startDate-"+endDate);
			
			//sale = salesRepo.findSalesByCurrentAndPreviousMonth(paging);
			sale = salesRepo.findSalesByDateRange(startDate,endDate,paging);

		}else*/ {
			sale = salesRepo.findAllByOrderBySaleIdDesc(paging);
		}
		
		
		
		
		
		System.out.println("page= " + page);
		System.out.println(sale.getNumber());
		System.out.println(sale.getNumberOfElements());
		System.out.println(sale.getSize());
		System.out.println(sale.getTotalElements());
		System.out.println(sale.getTotalPages());
		System.out.println(sale.hasNext());
		System.out.println(sale.hasPrevious());
		// salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		salesEntityList = sale.getContent();
		modelMap.addAttribute("totalPages", sale.getTotalPages());
		modelMap.addAttribute("totalRecords", sale.getTotalElements());
		modelMap.addAttribute("currentRecords", sale.getNumberOfElements());
		modelMap.addAttribute("previous", sale.hasPrevious());
		modelMap.addAttribute("next", sale.hasNext());
		modelMap.addAttribute("page", sale.getNumber());
		modelMap.addAttribute("pageSize", sale.getSize());

		Date startDate = new Date();
		Date endDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		startDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---Start Date is : " + startDate);

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.add(Calendar.DAY_OF_MONTH, -6);
		endDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
		LeoLogger.info("SaleServiceImpl---getSalesList---in Sales");

		try {

			for (SalesEntity salesEntityEntityRes : salesEntityList) {

				MemberUser memberEnt = memberUserRepo.findById(salesEntityEntityRes.getMemberid());
				// salesEntityEntityRes.setCreditpay(memberEnt.getCreditpayment());
				salesEntityEntityRes.setCreditpay(memberEnt.getDeposit());
				salesRepo.save(salesEntityEntityRes);

				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityEntityRes, SalePojo.class);
				salePojoList.add(salePojo);

			}
			if (page == 0) {

				if(!loggedInRole.equalsIgnoreCase("cashier") && !loggedInRole.equalsIgnoreCase("sales")) {
					LeoLogger.info("user is not sales and not cashier");


				specialsalesEntityList = specialsalesRepo.findByDateBetweenOrderByDateDesc(endDate, startDate);
				// LeoLogger.info("SaleServiceImpl---getSalesList---specialsalesEntityList is :
				// "
				// + specialsalesEntityList.toString());
				// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
				// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " +
				// startDate);

				for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

					MemberUser memberEnt = memberUserRepo.findById(specialsalesEntityRes.getMemberid());
					// specialsalesEntityRes.setCreditpay(memberEnt.getCreditpayment());
					specialsalesEntityRes.setCreditpay(memberEnt.getDeposit());
					specialsalesRepo.save(specialsalesEntityRes);
					SalePojo ssalePojo = new SalePojo();
					ssalePojo = mapper.map(specialsalesEntityRes, SalePojo.class);
					salePojoList.add(ssalePojo);
				}

				
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salePojoList;
	}

	@Override
	public List<SalePojo> getSalesListpage(ModelMap modelMap, int page, int pageSize) {
		List<SpecialSalesEntity> specialsalesEntityList = new ArrayList<SpecialSalesEntity>();
		List<SalePojo> salePojoList = new ArrayList<SalePojo>();
		List<SalesEntity> salesEntityList = new ArrayList<SalesEntity>();
		// int recordsLength=25;

		LeoLogger.info("SaleServiceImpl---getSalesList-");

		Pageable paging = PageRequest.of(page, pageSize);
		Page<SalesEntity> sale;
		// sale = salesRepo.findAll(paging);
		sale = salesRepo.findAllByOrderBySaleIdDesc(paging);
		System.out.println("page= " + page);
		System.out.println(sale.getNumber());
		System.out.println(sale.getNumberOfElements());
		System.out.println(sale.getSize());
		System.out.println(sale.getTotalElements());
		System.out.println(sale.getTotalPages());
		System.out.println(sale.hasNext());
		System.out.println(sale.hasPrevious());
		// salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		salesEntityList = sale.getContent();
		modelMap.addAttribute("totalPages", sale.getTotalPages());
		modelMap.addAttribute("totalRecords", sale.getTotalElements());
		modelMap.addAttribute("currentRecords", sale.getNumberOfElements());
		modelMap.addAttribute("previous", sale.hasPrevious());
		modelMap.addAttribute("next", sale.hasNext());

		Date startDate = new Date();
		Date endDate = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---Start Date is : " + startDate);

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		endDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
		LeoLogger.info("SaleServiceImpl---getSalesList---in Sales");

		try {

			for (SalesEntity salesEntityEntityRes : salesEntityList) {

				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityEntityRes, SalePojo.class);
				salePojoList.add(salePojo);

			}

			specialsalesEntityList = specialsalesRepo.findByDateBetweenOrderByDateDesc(startDate, endDate);
			// LeoLogger.info("SaleServiceImpl---getSalesList---specialsalesEntityList is :
			// "
			// + specialsalesEntityList.toString());
			// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
			// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " +
			// startDate);

			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

				SalePojo ssalePojo = new SalePojo();
				ssalePojo = mapper.map(specialsalesEntityRes, SalePojo.class);
				salePojoList.add(ssalePojo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salePojoList;
	}

	@Override
	public List<SalePojo> getSearch( String search) {
		
		List<SpecialSalesEntity> specialsalesEntityList = new ArrayList<SpecialSalesEntity>();
		List<SalePojo> salePojoList = new ArrayList<SalePojo>();
		List<SalesEntity> salesEntityList = new ArrayList<SalesEntity>();

		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String loggedInRole = "";
	        // Check if the user is authenticated
	        if (authentication != null && authentication.isAuthenticated()) {
	            // Iterate through authorities (roles) and return them
	            for (GrantedAuthority authority : authentication.getAuthorities()) {
	            	loggedInRole = authority.getAuthority();  // Return the role of the user
	            }
	        }
			LeoLogger.info("SaleServiceImpl---loggedInRole for search sales -"+loggedInRole);
			
			
		//	Pageable paging = PageRequest.of(page, recordsLength);
		//	Page<SalesEntity> sale;
			// sale = salesRepo.findAll(paging);
			/*if(loggedInRole.equalsIgnoreCase("sales")||(loggedInRole.equalsIgnoreCase("cashier"))) {
				 LocalDate today = LocalDate.now();
			        LocalDate startOfPreviousMonth = today.minusMonths(1).withDayOfMonth(1);
			        LocalDate endOfCurrentMonth = today.withDayOfMonth(today.lengthOfMonth());

			        Date startDate = Date.from(startOfPreviousMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			        Date endDate = Date.from(endOfCurrentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
				
				//sale = salesRepo.findSalesByCurrentAndPreviousMonth(paging);
			        salesEntityList = salesRepo.findSalesByMultipleFieldsAndDateRange( search, search, search, search,startDate, endDate);


			}else*/ {
				salesEntityList = salesRepo
						.findByCtypeContainingOrReferencenoContainingOrMembernameContainingOrGrandtotalContainingOrderBySaleIdDesc(
								search, search, search, search);
			}
			

		Date startDate = new Date();
		Date endDate = new Date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		startDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---Start Date is : " + startDate);

		cal.setTime(endDate);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		endDate = cal.getTime();

		LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
		LeoLogger.info("SaleServiceImpl---getSalesList---in Sales");

		try {

			
			LeoLogger.info("SaleServiceImpl---getSalesList---in Sales salesEntityList" + salesEntityList.size());
			for (SalesEntity salesEntityEntityRes : salesEntityList) {

				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityEntityRes, SalePojo.class);
				salePojoList.add(salePojo);

			}
              if(!loggedInRole.equalsIgnoreCase("cashier") && !loggedInRole.equalsIgnoreCase("sales")  && !loggedInRole.equalsIgnoreCase("custom")) {
      			LeoLogger.info("SaleServiceImpl---getSalesList---in Sales salesEntityList" + loggedInRole);

            	 // specialsalesEntityList = specialsalesRepo.findByDateBetweenOrderByDateDesc(startDate, endDate);
            	  specialsalesEntityList = specialsalesRepo.searchByDateAndFields(startDate, endDate ,search, search, search, search);

            	  
            	  // LeoLogger.info("SaleServiceImpl---getSalesList---specialsalesEntityList is :
			// "
			// + specialsalesEntityList.toString());
			// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
			// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " +
			// startDate);

			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

				SalePojo ssalePojo = new SalePojo();
				ssalePojo = mapper.map(specialsalesEntityRes, SalePojo.class);
				salePojoList.add(ssalePojo);

			}
              }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			LeoLogger.info("SaleServiceImpl---salePojoList ===" + salePojoList.size());

		return salePojoList;
	}

	@Override
	public List<QuotesPojo> getQuotesListNew(ModelMap modelMap, int page) {
		List<QuotesEntity> quotesEntityList = new ArrayList<QuotesEntity>();
		List<QuotesPojo> quotesPojoList = new ArrayList<QuotesPojo>();
		Page<QuotesEntity> quotes;
		int recordsLength = 10;

		LeoLogger.info("SaleServiceImpl---getSalesList-");
		
		
		
		LeoLogger.info("SaleServiceImpl---getSalesList-");
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String loggedInRole = "";
	        // Check if the user is authenticated
	        if (authentication != null && authentication.isAuthenticated()) {
	            // Iterate through authorities (roles) and return them
	            for (GrantedAuthority authority : authentication.getAuthorities()) {
	            	loggedInRole = authority.getAuthority();  // Return the role of the user
	            }
	        }
			LeoLogger.info("SaleServiceImpl---loggedInRole-"+loggedInRole);

		Pageable paging = PageRequest.of(page, recordsLength);
		Page<SalesEntity> sale;
		// sale = salesRepo.findAll(paging);
		/*if(loggedInRole.equalsIgnoreCase("sales")||(loggedInRole.equalsIgnoreCase("cashier"))) {
			 LocalDate today = LocalDate.now();
		        LocalDate startOfPreviousMonth = today.minusMonths(1).withDayOfMonth(1);
		        LocalDate endOfCurrentMonth = today.withDayOfMonth(today.lengthOfMonth());

		        Date startDate = Date.from(startOfPreviousMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		        Date endDate = Date.from(endOfCurrentMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			//sale = salesRepo.findSalesByCurrentAndPreviousMonth(paging);
		        quotes = quotesRepo.findQuotesByDateRange(startDate,endDate,paging);

		}else*/ {
			quotes = quotesRepo.findAllByOrderByQuotesIdDesc(paging);
		}

		//Pageable paging = PageRequest.of(page, recordsLength);
		//Page<QuotesEntity> quotes;
		// sale = salesRepo.findAll(paging);
		//quotes = quotesRepo.findAllByOrderByQuotesIdDesc(paging);
		System.out.println("page= " + page);
		System.out.println(quotes.getNumber());
		System.out.println(quotes.getNumberOfElements());
		System.out.println(quotes.getSize());
		System.out.println(quotes.getTotalElements());
		System.out.println(quotes.getTotalPages());
		System.out.println(quotes.hasNext());
		System.out.println(quotes.hasPrevious());
		// salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		quotesEntityList = quotes.getContent();
		modelMap.addAttribute("totalPages", quotes.getTotalPages());
		modelMap.addAttribute("totalRecords", quotes.getTotalElements());
		modelMap.addAttribute("currentRecords", quotes.getNumberOfElements());
		modelMap.addAttribute("previous", quotes.hasPrevious());
		modelMap.addAttribute("next", quotes.hasNext());
		modelMap.addAttribute("page", quotes.getNumber());
		modelMap.addAttribute("pageSize", quotes.getSize());

		try {
			LeoLogger.info("SaleServiceImpl--getQuotesList");
			// quotesEntityList = quotesRepo.findAllByOrderByQuotesIdDesc();
			for (QuotesEntity quotesEntityEntityRes : quotesEntityList) {
				
				MemberUser memberEnt = memberUserRepo.findById(quotesEntityEntityRes.getMemberid());
				quotesEntityEntityRes.setCreditpayment(memberEnt.getCreditpayment());
				quotesEntityEntityRes.setBlocked(memberEnt.getBlocked());
				quotesRepo.save(quotesEntityEntityRes);

				QuotesPojo quotesPojo = new QuotesPojo();
				quotesPojo = mapper.map(quotesEntityEntityRes, QuotesPojo.class);
				quotesPojoList.add(quotesPojo);
				
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quotesPojoList;
	}

	@Override
	public List<QuotesPojo> getQuotesListpage(ModelMap modelMap, int page, int pageSize) {
		List<QuotesEntity> quotesEntityList = new ArrayList<QuotesEntity>();
		List<QuotesPojo> quotesPojoList = new ArrayList<QuotesPojo>();
		// int recordsLength=pageSize;

		LeoLogger.info("SaleServiceImpl---getSalesList-");

		Pageable paging = PageRequest.of(page, pageSize);
		Page<QuotesEntity> quotes;
		// sale = salesRepo.findAll(paging);
		quotes = quotesRepo.findAllByOrderByQuotesIdDesc(paging);
		System.out.println("page= " + page);
		System.out.println(quotes.getNumber());
		System.out.println(quotes.getNumberOfElements());
		System.out.println(quotes.getSize());
		System.out.println(quotes.getTotalElements());
		System.out.println(quotes.getTotalPages());
		System.out.println(quotes.hasNext());
		System.out.println(quotes.hasPrevious());
		// salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		quotesEntityList = quotes.getContent();
		modelMap.addAttribute("totalPages", quotes.getTotalPages());
		modelMap.addAttribute("totalRecords", quotes.getTotalElements());
		modelMap.addAttribute("currentRecords", quotes.getNumberOfElements());
		modelMap.addAttribute("previous", quotes.hasPrevious());
		modelMap.addAttribute("next", quotes.hasNext());
		modelMap.addAttribute("page", quotes.getNumber());
		modelMap.addAttribute("pageSize", quotes.getSize());

		try {
			LeoLogger.info("SaleServiceImpl--getQuotesList");
			// quotesEntityList = quotesRepo.findAllByOrderByQuotesIdDesc();
			for (QuotesEntity quotesEntityEntityRes : quotesEntityList) {

				QuotesPojo quotesPojo = new QuotesPojo();
				quotesPojo = mapper.map(quotesEntityEntityRes, QuotesPojo.class);
				quotesPojoList.add(quotesPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quotesPojoList;
	}

	@Override
	public List<QuotesPojo> getSearchQuotes(ModelMap modelMap, String search) {
		List<QuotesEntity> quotesEntityList = new ArrayList<QuotesEntity>();
		List<QuotesPojo> quotesPojoList = new ArrayList<QuotesPojo>();
		try {
			LeoLogger.info("SaleServiceImpl--getQuotesList");
			quotesEntityList = quotesRepo
					.findByCtypeContainingOrReferencenoContainingOrMembernameContainingOrGrandtotalContainingOrderByQuotesIdDesc(search, search, search, search);
			
			LeoLogger.info("SaleServiceImpl--quotesEntityList" + quotesEntityList.toString());
			for (QuotesEntity quotesEntityEntityRes : quotesEntityList) {

				QuotesPojo quotesPojo = new QuotesPojo();
				quotesPojo = mapper.map(quotesEntityEntityRes, QuotesPojo.class);
				quotesPojoList.add(quotesPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quotesPojoList;
	}

	@Override
	public List<CustomerPurchasePojo> findAllCustomerPurchase(String startDate, String endDate) {
		LeoLogger.info("SalesServiceImpl ---customerPurchaseReport");
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));

		}
		return salesRepo.findAllCustomerPurchase(startDate, endDate);
	}

	@Override
	public List<SalePojo> getSaleidList(String saleid) {
		List<SalePojo> salesItemPojoList = new ArrayList<SalePojo>();
		// List<SalesItemEntity> salesItemEntityList =
		// salesItemRepo.findBySaleidOrderByIdAsc(Long.parseLong(saleId));
		List<SalesEntity> salesEntityList = salesRepo.findBySaleIdOrderBySaleIdAsc(Long.parseLong(saleid));
		for (SalesEntity salesEntityEntityRes : salesEntityList) {

			SalePojo salePojo = new SalePojo();
			salePojo = mapper.map(salesEntityEntityRes, SalePojo.class);
			salesItemPojoList.add(salePojo);

		}
		LeoLogger.info("SaleServiceImpl--getSaleidList" + salesItemPojoList);
		return salesItemPojoList;
	}

	@Override
	public List<CustomerPurchaseSpecialPojo> findAllSpecialCustomerPurchase(String startDate, String endDate) {
		LeoLogger.info("SalesServiceImpl ---customerPurchaseReport");
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));

		}
		return specialsalesRepo.findAllCustomerPurchase(startDate, endDate);
	}

	@Override
	public List<SpecialSalesPojo> getsaleslist(String saleid) {
		List<SpecialSalesEntity> specialsalesEntityList = new ArrayList<SpecialSalesEntity>();
		List<SpecialSalesPojo> salesItemPojoList = new ArrayList<SpecialSalesPojo>();

		// LeoLogger.info("SaleServiceImpl---getsaleslist---specialsalesEntityList is :
		// " + specialsalesEntityList.toString());
		specialsalesEntityList = specialsalesRepo.findBySaleIdOrderBySaleIdAsc(Long.parseLong(saleid));

		try {
			LeoLogger.info("SaleServiceImpl--getsaleslist----in  specialSales");
			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

				SpecialSalesPojo specialsalesPojo = new SpecialSalesPojo();
				specialsalesPojo = mapper.map(specialsalesEntityRes, SpecialSalesPojo.class);
				salesItemPojoList.add(specialsalesPojo);
				LeoLogger.info("SaleServiceImpl--getsaleslist----in  specialSales" + salesItemPojoList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesItemPojoList;
	}

	@Override
	public List<SaleMonthReportPojo> findSaleSummary(String startDate, String endDate) {
		LeoLogger.info("SalesServiceImpl ---salemonthReport");
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));

		}
		return salesRepo.findSaleSummary(startDate, endDate);
	}

	@Override
	public List<SpecialSaleMonthReportPojo> findSpecialSaleSummary(String startDate, String endDate) {
		LeoLogger.info("SalesServiceImpl ---salemonthReport");
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));

		}
		return specialsalesRepo.findSpecialSaleSummary(startDate, endDate);
	}

	@Override
	public List<SalePojo> getSalesListopenbalancebyMemberId(long memberId) {
		LeoLogger.info("SaleServiceImpl---getSalesListbyMemberId---in  getSalesListbyMemberId" + memberId);

		List<SalesEntity> salesEntityList = salesRepo.findAllByMemberidAndPaymentstatusOrderBySaleId(memberId, "Due");
		List<SalePojo> salesPojoList = new ArrayList<SalePojo>();
		// salesEntityList = salesRepo.findAllOrderBySaleIdDesc();
		try {
			LeoLogger.info("SaleServiceImpl---getSalesListbyMemberId---in  getSalesListbyMemberId");
			// salesEntityList = salesRepo.findAllByMemberid(memberId);

			for (SalesEntity salesEntityRes : salesEntityList) {
				// if(salesEntityRes.equals("Paid")) {
				SalePojo salePojo = new SalePojo();
				salePojo = mapper.map(salesEntityRes, SalePojo.class);
				salesPojoList.add(salePojo);
				// }

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return salesPojoList;
	}

	@Override
	public List<SpecialSalesPojo> getSpecialSalesListopenbalancebyMemberId(long memberId) {
		LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in  getSalesListbyMemberId" + memberId);
		List<SpecialSalesEntity> specialsalesEntityList = specialsalesRepo
				.findAllByMemberidAndPaymentstatusOrderBySaleId(memberId, "Due");
		List<SpecialSalesPojo> specialsalesPojoList = new ArrayList<SpecialSalesPojo>();
		// specialsalesEntityList = specialsalesRepo.findAllByOrderBySaleIdDesc();
		try {
			LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in  getSalesListbyMemberId");
			// salesEntityList = salesRepo.findAllByMemberid(memberId);
			for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

				SpecialSalesPojo salePojo = new SpecialSalesPojo();
				salePojo = mapper.map(specialsalesEntityRes, SpecialSalesPojo.class);
				specialsalesPojoList.add(salePojo);
				// LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in
				// getSalesListbyMemberId"+salePojo.toString());)
			//	LeoLogger.info("SaleServiceImpl---getSpecialSalesListbyMemberId---in  getSalesListbyMemberId---list"
			//			+ specialsalesPojoList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return specialsalesPojoList;
	}

	@Override
	public List<ReplenishmentRepotPojo> findReplenishmentRepotPojo(String startDate, String endDate) {
		LeoLogger.info("SalesServiceImpl ---ReplenishmentRepot");
		if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			LocalDate today = LocalDate.now();
			startDate = getFormattedDate((today.withDayOfMonth(1)));
			endDate = getFormattedDate(today.withDayOfMonth(today.lengthOfMonth()));

		}
		LeoLogger.info("SalesServiceImpl ---ReplenishmentRepot startDate" + startDate);
		LeoLogger.info("SalesServiceImpl ---ReplenishmentRepot endDate" + endDate);
		return salesRepo.findReplenishmentRepotPojo(startDate, endDate);
	}
	
	
	
	/////////////////////////////////////////////////////   Refactoring sale module by mahesh 1st april 2024 //////////////////////////////////////////////////

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO addSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception {
		
		ResultVO resonse = new ResultVO();
	
		MemberUser customer = memberUserRepo.findById(productItemList.get(0).getCustomerId());
		
		LeoLogger.info(">>>> addSaleRefactored >>>> customer id >>>> " + customer.getId() );
		LeoLogger.info(">>>> addSaleRefactored >>>> customer name >>>> " + customer.getName() );
		LeoLogger.info(">>>> addSaleRefactored >>>> is tax applied >>>> " + productItemList.get(0).getTax() );
		
		SalesEntity sale = new SalesEntity();
		List<SalesItemEntity> saleItemList = new ArrayList<>();
		Optional<SalesEntity> lastSale= salesRepo.findTopByOrderByDateDesc();
		LeoLogger.info(">>>> addSaleRefactored >>>> lastSale>>>> " + lastSale );
		/*
		 String refernceno="";
		
		    
		    LeoLogger.info(">>>> addSaleRefactored >>>> lastSale>>>> " + lastSale );
		   
		    refernceno=lastSale.get().getReferenceno();
		    
		    
		    String saleNumber = refernceno.substring(refernceno.lastIndexOf("/") + 1);
		    
		    int salesNumber = Integer.parseInt(saleNumber); // Convert to integer
		    String newReferenceNo = String.format("%05d", salesNumber + 1); 
		  */
		    
		
		sale.setDate(new Date());
		sale.setMemberid(customer.getId());
		sale.setMember_name(customer.getName());
		sale.setCustomeraddress(customer.getAddress());
		sale.setPincode(customer.getPincode());
		sale.setPhonemain(customer.getPhonemain());
		sale.setMembername(customer.getName());		
		sale.setCreatedDate(new Date());
		sale.setCtype(customer.getCtype());
		sale.setIsActive(0);
		sale.setNote(productItemList.get(0).getNote());		
		sale.setPaymentstatus("Due");
		sale.setSale_status("Due");
		sale.setOrder_discount(0);
		sale.setTotal_discount(0);
		sale.setOrder_tax(0);
		for (AddItemReqPojo item : productItemList) {
			sale.setPurchaseorder(item.getPurchaseorder());
		};
		salesRepo.save(sale);
		
		Long saleId = sale.getSaleId();

		List<Object> saleSubTotalAndTotalTaxAmount =  saveSaleItemListAndUpdateInventory(productItemList, saleId,  customer);

		BigDecimal saleSubTotalAmount = (BigDecimal) saleSubTotalAndTotalTaxAmount.get(0);
		BigDecimal saleTotalTaxAmount =  (BigDecimal) saleSubTotalAndTotalTaxAmount.get(1);
		
		saleItemList = saleSubTotalAndTotalTaxAmount.get(2) != null
				? (List<SalesItemEntity>) saleSubTotalAndTotalTaxAmount.get(2)
				: Collections.EMPTY_LIST;
		
		sale.setTotal(saleSubTotalAmount.doubleValue());

		sale.setProduct_tax(saleTotalTaxAmount.doubleValue());
		sale.setTotal_tax(saleTotalTaxAmount.doubleValue());
		
		LeoLogger.info(">>>> addSaleRefactored >>>> sale sub total amount >>>> " + saleSubTotalAmount );
		LeoLogger.info(">>>> addSaleRefactored >>>> sale total tax amount >>>> " + saleTotalTaxAmount );

		BigDecimal saleTotalAmount = saleSubTotalAmount.add(saleTotalTaxAmount).setScale(2, RoundingMode.HALF_UP);		
		sale.setGrand_total(saleTotalAmount.doubleValue());
		sale.setGrandtotal(saleTotalAmount.toString());
		
		LeoLogger.info(">>>> addSaleRefactored >>>> sale total amount >>>> " + saleTotalAmount );
		
	    LocalDate currentDate = LocalDate.now();
	    
	    
	   /*
	    if(!refernceno.isEmpty()) {
	    	
	    	LeoLogger.info(">>>> addSaleRefactored >>>> last refernceno>>>> " + refernceno );
	    	//sale.setReferenceno(refernceno);
	    	
	    	sale.setReferenceno("SALE" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/" + (newReferenceNo));

	    }else {
	 
	    	sale.setReferenceno("SALE" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/" + (newReferenceNo));
	 
	    } */
	    
	    sale.setReferenceno("SALE" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/" + saleId );
	    sale.setFileName(customFileUploadUtil.savePdfFileIntoDir(saleItemList, sale));
	    
	   
	    Boolean isSpecialSell = false;
	    LeoLogger.info(">>>> addSaleRefactored >>>> sale id >>>> " + saleId);
			   
	    saveFinancialTransactionEntityForSaleAndSpecialSale(saleId ,  sale.getReferenceno(), saleTotalAmount, customer);

	    saveFTForSaleAndSpecialSale( saleId , sale.getReferenceno(), saleTotalAmount, customer);
	    
	    saveRegisterForSaleAndSpecialSale( saleId ,  saleTotalAmount);
	    
	    applyRegisterhistory(sale);

        /// Applying credit amount to sell if customer has 		    
		Double creditAmount =  new BigDecimal(customer.getCreditpayment()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		LeoLogger.info(">>>> addSaleRefactored >>>> customer credit amount >>>> " + creditAmount );
		if (productItemList != null && !productItemList.isEmpty() && productItemList.get(0).getApplycreditpayment().equals("1")) {
		if (creditAmount != 0.0 && creditAmount > 0) {	
		addPaymentByCreditAmount(sale.getSaleId(), isSpecialSell ,customer, creditAmount)  ;
		
		}
		}
		
		resonse.setMsgCode("001");
		resonse.setError(false);
		resonse.setMsgDescr("Sale with sale id "+saleId+" and total sale "+sale.getGrand_total()+" Added Sucessfully!");		
		return resonse;		
		
	}
	
	
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO addSpecialSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception {

		ResultVO resonse = new ResultVO();
	
		MemberUser customer = memberUserRepo.findById(productItemList.get(0).getCustomerId());
		
		LeoLogger.info(">>>> addSpecialSaleRefactored >>>> customer id >>>> " + customer.getId() );
		LeoLogger.info(">>>> addSpecialSaleRefactored >>>> customer name >>>> " + customer.getName() );
		LeoLogger.info(">>>> addSpecialSaleRefactored >>>>is tax applied >>>> " + productItemList.get(0).getTax() );
		
		
		SpecialSalesEntity specialSale = new SpecialSalesEntity();
		List<SpecialSalesItemEntity> specialSaleItemList = new ArrayList<>();
		
		specialSale.setDate(new Date());
		specialSale.setMemberid(customer.getId());
		specialSale.setMember_name(customer.getName());
		specialSale.setCustomeraddress(customer.getAddress());
		specialSale.setPincode(customer.getPincode());
		specialSale.setPhonemain(customer.getPhonemain());
		specialSale.setMembername(customer.getName());		
		specialSale.setCreatedDate(new Date());
		specialSale.setCtype(customer.getCtype());
		specialSale.setIsActive(0);
		specialSale.setNote(productItemList.get(0).getNote());		
		specialSale.setPaymentstatus("Due");
		specialSale.setSale_status("Due");
		specialSale.setOrder_discount(0);
		specialSale.setTotal_discount(0);
		specialSale.setOrder_tax(0);		
		specialsalesRepo.save(specialSale);
		
		Long specialSaleId = specialSale.getSaleId();
	
        List<Object> specialSaleSubTotalAndTotalTaxAmount =  saveSpecialSaleItemListAndUpdateInventory(productItemList, specialSaleId);
		
		BigDecimal saleSubTotalAmount = (BigDecimal) specialSaleSubTotalAndTotalTaxAmount.get(0);
		BigDecimal saleTotalTaxAmount =  (BigDecimal) specialSaleSubTotalAndTotalTaxAmount.get(1);
				
		specialSale.setTotal(saleSubTotalAmount.doubleValue());
		specialSale.setProduct_tax(saleTotalTaxAmount.doubleValue());
		specialSale.setTotal_tax(saleTotalTaxAmount.doubleValue());


		LeoLogger.info(">>>> addSpecialSaleRefactored >>>> special sale total tax amount >>>> " + saleTotalTaxAmount );
		LeoLogger.info(">>>> addSpecialSaleRefactored >>>> special sale sub total amount >>>> " + saleSubTotalAmount );
		
		BigDecimal saleTotalAmount = saleSubTotalAmount.add(saleTotalTaxAmount).setScale(2, RoundingMode.HALF_UP);		
		specialSale.setGrand_total(saleTotalAmount.doubleValue());
		
		LeoLogger.info(">>>> addSpecialSaleRefactored >>>> special sale total amount >>>> " + saleTotalAmount );
	
		
	    LocalDate currentDate = LocalDate.now();
	 
	    specialSale.setReferenceno("SALE" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/" + specialSale.getSaleId() + "*");	    
	    specialSale.setFileName(customFileUploadUtil.saveSpecialPdfFileIntoDir(specialSaleItemList, specialSale));
	    	    
	    Boolean isSpecialSell = true;
	    LeoLogger.info(">>>> addSpecialSaleRefactored >>>> special sale id >>>> " + specialSaleId);

	    saveFinancialTransactionEntityForSaleAndSpecialSale(specialSaleId, specialSale.getReferenceno(), saleTotalAmount, customer);
	  
	    saveFTForSaleAndSpecialSale(specialSaleId , specialSale.getReferenceno(), saleTotalAmount, customer);
	 
	   // saveRegisterForSaleAndSpecialSale(specialSaleId , saleTotalAmount);
	    saveSpecialSaleRegister(saleTotalAmount);
	    
	    applyRegisterhistoryspecial(specialSale);
		
		
        /// Applying credit amount to sell if customer has 		
	    Double creditAmount =  new BigDecimal(customer.getCreditpayment()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		LeoLogger.info(">>>> addSpecialSaleRefactored >>>> customer credit amount >>>> " + creditAmount );
		
		if (creditAmount != 0.0 && creditAmount > 0) 	
		addPaymentByCreditAmount(specialSale.getSaleId(), isSpecialSell ,  customer ,  creditAmount)  ;	 

		  
		resonse.setMsgCode("001");
		resonse.setError(false);
		resonse.setMsgDescr("Special Sale with sale id "+specialSaleId+" and total sale "+specialSale.getGrand_total()+" Added Sucessfully!");
		return resonse;

	}
	
	

	  private void blockedCustomerIfDueAboveThreshold(MemberUser customer) {

				BigDecimal thresholdAmount = new BigDecimal(customer.getThreshholdamount());
				BigDecimal sixtyDaybalance = new BigDecimal(0.0);		
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -60);
				Date sixtyDaysAgo = cal.getTime();
				
				List<SalesEntity> saleList = salesRepo.findAllByMemberid(customer.getId());
				
				for (SalesEntity sale : saleList) {
					if (sixtyDaysAgo.after(sale.getDate())) {
						if (sale.getIsActive() == 0 && sale.getPaymentstatus().equalsIgnoreCase("Due")) {			
							sixtyDaybalance = sixtyDaybalance.add(new BigDecimal((sale.getGrand_total() - sale.getPaid()))).setScale(2, RoundingMode.HALF_UP);				
						}
					}
				
				}
				
				if (sixtyDaybalance.compareTo(thresholdAmount) > 0) {
					customer.setBlocked(1);

				}
	  }
	  
	  
	  private void saveFinancialTransactionEntityForSaleAndSpecialSale(Long  saleOrSpecialSaleId , String referenceNo ,BigDecimal saleTotalAmount, MemberUser customer) {

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			Date dueDate = cal.getTime();
			BigDecimal lastBalance = new BigDecimal(0.00);

			FinancialTransactionEntity lastFinancialTransaction = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
			
			FinancialTransactionEntity newFinancialTransaction = new FinancialTransactionEntity();			
			newFinancialTransaction.setInvoideId(saleOrSpecialSaleId);			
			newFinancialTransaction.setCustomerId(customer.getId());
			newFinancialTransaction.setCustomerName(customer.getName());
			newFinancialTransaction.setReferenceno(referenceNo);
			newFinancialTransaction.setDate(new Date());
			newFinancialTransaction.setDueDate(dueDate);
			newFinancialTransaction.setAmount(saleTotalAmount.doubleValue());
			newFinancialTransaction.setType("Invoice");

			if (lastFinancialTransaction == null) {
				 LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  last balance FinancialTransaction >> " + lastBalance );
				newFinancialTransaction.setBalance(saleTotalAmount.doubleValue());	
				LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  new balance FinancialTransaction >>  " + newFinancialTransaction.getBalance() );
			} else {
			
				 lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
			    LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  last balance FinancialTransaction >> " + lastBalance );
			
				BigDecimal newBalance  = lastBalance.add(saleTotalAmount).setScale(2, RoundingMode.HALF_UP);				
				LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  new balance FinancialTransaction >>  " + newBalance );
				
				newFinancialTransaction.setBalance(newBalance.doubleValue());
			}

			financialTransactionsRepo.save(newFinancialTransaction);
		  
	  }
	  
	  private void saveFTForSaleAndSpecialSale(Long  saleOrSpecialSaleId , String referenceNo ,BigDecimal saleOrSpecialSaleTotalAmount, MemberUser customer) {

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			Date dueDate = cal.getTime();
			BigDecimal lastBalance = new BigDecimal(0.00);
	
			FTEntity lastFT = fTRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());	
			
			FTEntity newFT = new FTEntity();
			newFT.setInvoideId(saleOrSpecialSaleId);			
			newFT.setCustomerId(customer.getId());
			newFT.setCustomerName(customer.getName());
			newFT.setReferenceno(referenceNo);
			newFT.setDate(new Date());
			newFT.setDueDate(dueDate);
			newFT.setAmount(saleOrSpecialSaleTotalAmount.doubleValue());
			newFT.setType("Invoice");

			if (lastFT == null) {
							
			 LeoLogger.info(">>>> saveFTForSaleAndSpecialSale >>>>  last balance FT >> " + lastBalance );
			 newFT.setBalance(saleOrSpecialSaleTotalAmount.doubleValue());	
			 LeoLogger.info(">>>> saveFTForSaleAndSpecialSale >>>>  new balance FT >>  " + newFT.getBalance() );
			 
			} else {
			
				 lastBalance = new BigDecimal(lastFT.getBalance()).setScale(2, RoundingMode.HALF_UP);	
				LeoLogger.info(">>>> saveFTForSaleAndSpecialSale >>>>  last balance FT >>>>" + lastBalance );
				 
				BigDecimal newBalance  = lastBalance.add(saleOrSpecialSaleTotalAmount).setScale(2, RoundingMode.HALF_UP);	
				LeoLogger.info(">>>> saveFTForSaleAndSpecialSale >>>> new balance FT >>>> " + newBalance );
				 
				newFT.setBalance(newBalance.doubleValue());	
			}

			fTRepo.save(newFT);
		  
	  }
	  
	  private void saveRegisterForSaleAndSpecialSale(Long  saleOrSpecialSaleId ,BigDecimal saleOrSpecialSaleTotalAmount) {
		  
		
			RegisterEntity register = registerRepo.findAByDate(new Date());
			LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> today register >>>> " + register);
			BigDecimal newClosingBalnce ;
	
			if (register != null)

			{
				LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> last sale amount >>>> " + register.getSalesamount() );
				BigDecimal newSaleAmount = new BigDecimal(register.getSalesamount()).add(saleOrSpecialSaleTotalAmount).setScale(2, RoundingMode.HALF_UP);			
				register.setSalesamount(newSaleAmount.doubleValue());	
				LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>>  new sale amount >>>> " + newSaleAmount);
				
				 LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> last closing amount >>>> " + register.getClosingbal() );
				 newClosingBalnce = new BigDecimal(register.getClosingbal()).add(saleOrSpecialSaleTotalAmount).setScale(2, RoundingMode.HALF_UP);
				register.setClosingbal(newClosingBalnce.doubleValue());
			    LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>>  new closing amount >>>> " + newClosingBalnce);			
			
				register.setReferenceno(register.getReferenceno() + "," + saleOrSpecialSaleId);
				registerRepo.save(register);
				
			} else {
				RegisterEntity newregister = new RegisterEntity();
				newregister.setCashinhand(1000.00);
				newregister.setDate(new Date());
				newregister.setCashpayment(0.00);
				newregister.setCreditcardpayment(0.00);
				newregister.setOpeningbal(1000.00);
				newregister.setClosingbal(1000.00);
				newregister.setChequepayment(0.00);
				newregister.setRefunds(0.00);			
				newregister.setReferenceno(saleOrSpecialSaleId.toString());				
				newregister.setStatus("Open");
								
				LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> last sale amount >>>> " + newregister.getSalesamount() );
				newregister.setSalesamount(saleOrSpecialSaleTotalAmount.doubleValue());
				LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> new sale amount >>>> " + newregister.getSalesamount());
				
				LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> last closing amount >>>> " + newregister.getClosingbal() );			  	
			    newClosingBalnce = new BigDecimal(newregister.getClosingbal()).add(saleOrSpecialSaleTotalAmount).setScale(2, RoundingMode.HALF_UP);	
			    newregister.setClosingbal(newClosingBalnce.doubleValue());
				LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> new closing amount >>>> " + newregister.getClosingbal());	
				
				registerRepo.save(newregister);
				
				LeoLogger.info(">>>> saveRegisterForSaleAndSpecialSale >>>> new register created >>>> " + newregister);
			}
		  
	  }
	  
	  
	  private void addPaymentByCreditAmount(Long  id , Boolean isSpecialSale , MemberUser customer , Double creditAmount)  {

		  SalesEntity sale = null;
		  SpecialSalesEntity specialSale = null;
		  BigDecimal saleTotalAmount = null;
		  BigDecimal newBalance = new BigDecimal(0.0);
		  BigDecimal lastBalance = new BigDecimal(0.0);
			
		  LocalDate currentDate = LocalDate.now();
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.DATE, 30);
		  Date dueDate = cal.getTime();
		  
          if(isSpecialSale)          
            specialSale = specialsalesRepo.findBySaleId(id);
          else
            sale  = salesRepo.findBySaleId(id);
          
          if(isSpecialSale) 				   
				 saleTotalAmount = new BigDecimal(specialSale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);					
			 else 
				 saleTotalAmount = new BigDecimal(sale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
          
          
          LeoLogger.info(" >>>> addPaymentByCreditAmount >>>> sale/specialSell total amount >>>> " +saleTotalAmount);
		
			// updatating customer creditAmount
			if (saleTotalAmount.doubleValue() <= creditAmount) {
				customer.setCreditpayment(
						new BigDecimal(creditAmount - saleTotalAmount.doubleValue()).setScale(2, RoundingMode.HALF_UP).doubleValue());
			} else {
				customer.setCreditpayment(0);
			}
			
			memberUserRepo.save(customer);
			 LeoLogger.info(" >>>> addPaymentByCreditAmount >>>> customer updated credit amount >>>> " +customer.getCreditpayment());

			
			// Adding entry in payment and updating sale 
			PaymentEntity payment = new PaymentEntity();
			
				if (saleTotalAmount.doubleValue() <= creditAmount) {

					   if(isSpecialSale) 	{			   
						   specialSale.setPaid(saleTotalAmount.doubleValue());
						   specialSale.setPaymentstatus("Paid");						 
							specialsalesRepo.save(specialSale);
					     } else  {
					    	 sale.setPaid(saleTotalAmount.doubleValue());
							 sale.setPaymentstatus("Paid");					
							salesRepo.save(sale);
					     }
					   
					   payment.setGrand_total(saleTotalAmount.doubleValue());

				} else {
					   if(isSpecialSale) 	{			   
						   specialSale.setPaid(creditAmount);						   					   
							specialsalesRepo.save(specialSale);
					     } else  {
					    	 
					    		sale.setPaid(creditAmount);															
								salesRepo.save(sale);
					     }
					   payment.setGrand_total(creditAmount);
				}
			
				payment.setMember_name(customer.getName());
				payment.setMember_id(customer.getId());
				payment.setPtype("CA");
				payment.setstatus("CreditAmount");
				payment.setCtype(customer.getCtype());
				payment.setPaymentdate(new Date());
				paymentRepo.save(payment);			
				
				
			     if(isSpecialSale) 	{			   
			    	 payment.setRsaleId(specialSale.getSaleId());
			    	 payment.setSalesreferenceno(specialSale.getReferenceno());
			    	 payment.setReferenceno("Payment" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/"
								+ specialSale.getSaleId() + "-" + payment.getId());
			     } else  {
			    	 payment.setRsaleId(sale.getSaleId());
			    	 payment.setSalesreferenceno(sale.getReferenceno());
			    	 payment.setReferenceno("Payment" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/"
								+ sale.getSaleId() + "-" + payment.getId());
			     }
			     
			     LeoLogger.info(" >>>> addPaymentByCreditAmount >>>> saving credit amount payment >>>> " +payment);
			
			     
			    // Adding entry in   FinancialTransactionEntity
				FinancialTransactionEntity lastFinancialTransaction = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
				FinancialTransactionEntity newFinancialTransaction = new FinancialTransactionEntity();
				
				if (saleTotalAmount.doubleValue() <= creditAmount) 
					newFinancialTransaction.setAmount(saleTotalAmount.doubleValue());
				else 
					newFinancialTransaction.setAmount(creditAmount);
				
				newFinancialTransaction.setCustomerId(customer.getId());
				newFinancialTransaction.setCustomerName(customer.getName());
				newFinancialTransaction.setDate(new Date());
				newFinancialTransaction.setDueDate(dueDate);
				newFinancialTransaction.setType("CreditAmount--Payment");

				  if(isSpecialSale) 	{			   
					  newFinancialTransaction.setInvoideId(specialSale.getSaleId());
					  newFinancialTransaction.setReferenceno(specialSale.getReferenceno());

				     } else  {
				        newFinancialTransaction.setInvoideId(sale.getSaleId());
						newFinancialTransaction.setReferenceno(sale.getReferenceno());

				     }
				
					
					lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);
				
					if (saleTotalAmount.doubleValue() <= creditAmount) {
						newBalance = lastBalance.subtract(saleTotalAmount);
					} else {
						newBalance = lastBalance.subtract(new BigDecimal(creditAmount));
					}
					
					 LeoLogger.info(" >>>> saveFinancialTransactionEntity >>>> last balance FinancialTransaction >>>> " + lastBalance );
					 LeoLogger.info(" >>>> saveFinancialTransactionEntity >>>> new balance FinancialTransaction >>>> " + newBalance );
					
				newFinancialTransaction.setBalance(newBalance.doubleValue());
				financialTransactionsRepo.save(newFinancialTransaction);
	
				
			    // Adding entry in  FTEntity
				FTEntity lastFT = fTRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
				FTEntity newFT = new FTEntity();
				
				if (saleTotalAmount.doubleValue() <= creditAmount) 
					newFT.setAmount(saleTotalAmount.doubleValue());
				else 
					newFT.setAmount(creditAmount);
				
				newFT.setCustomerId(customer.getId());
				newFT.setCustomerName(customer.getName());
				newFT.setDate(new Date());
				newFT.setDueDate(dueDate);
				newFT.setType("CreditAmount--Payment");

				  if(isSpecialSale) 	{			   
					  newFT.setInvoideId(specialSale.getSaleId());
					  newFT.setReferenceno(specialSale.getReferenceno());
				     } else  {
				    	 newFT.setInvoideId(sale.getSaleId());
						 newFT.setReferenceno(sale.getReferenceno());
				     }

				    lastBalance = new BigDecimal(lastFT.getBalance()).setScale(2, RoundingMode.HALF_UP);
				
					if (saleTotalAmount.doubleValue() <= creditAmount) 
						newBalance = lastBalance.subtract(saleTotalAmount);
					 else 
						newBalance = lastBalance.subtract(new BigDecimal(creditAmount));
					
					newFT.setBalance(newBalance.doubleValue());
					
					 LeoLogger.info(">>>> saveFinancialTransactionEntity >>>> last balance FT >>>> " + lastBalance );
					 LeoLogger.info(">>>> saveFinancialTransactionEntity >>>>  new balance FT >>>> " + newBalance );

				fTRepo.save(newFT);  
	  }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO updateSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception {

		ResultVO resonse = new ResultVO();
		
			MemberUser customer = memberUserRepo.findById(productItemList.get(0).getCustomerId());
			Boolean isSpecialSell = false;
			SalesEntity oldSale  = salesRepo.findBySaleId(productItemList.get(0).getSaleId());
			Long saleId = oldSale.getSaleId();
			
			List<SalesItemEntity> oldSalesItemList  = salesItemRepo.findBySaleid(saleId);
			
			BigDecimal oldSaleTotalAmount = new BigDecimal(oldSale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
			
			LeoLogger.info(">>>> updateSaleRefactored >>>> customer id >>>> " + customer.getId() );
			LeoLogger.info(">>>> updateSaleRefactored >>>> customer name >>>> " + customer.getName() );
			LeoLogger.info(">>>> updateSaleRefactored >>>> sale id >>>> " + saleId);
			LeoLogger.info(">>>> updateSaleRefactored >>>> is tax applied >>>> " + productItemList.get(0).getTax() );
		
				
			//reverseRegisterForSaleAndSpecialSell(oldSaleTotalAmount);
			reverseSpecialSaleRegister(oldSaleTotalAmount);
			
			reverseInventoryForSaleAndSpecialSell(oldSalesItemList , null,  isSpecialSell);
			
			LeoLogger.info(">>>> updateSaleRefactored >>>> removing old Sale Item List >>>> " );
			salesItemRepo.deleteAll(oldSalesItemList);

		List<Object> data = saveSaleItemListAndUpdateInventory( productItemList ,  saleId ,  customer);
		
		BigDecimal updatedSaleSubTotalAmount = (BigDecimal) data.get(0);
		BigDecimal updatedSaleTotalTaxAmount =  (BigDecimal) data.get(1);
		
	
		oldSale.setTotal(updatedSaleSubTotalAmount.doubleValue());
		oldSale.setProduct_tax(updatedSaleTotalTaxAmount.doubleValue());
		oldSale.setTotal_tax(updatedSaleTotalTaxAmount.doubleValue());
		
		LeoLogger.info(">>>> updateSaleRefactored >>>> updated sale total tax amount >>>> " + updatedSaleTotalTaxAmount );
		LeoLogger.info(">>>> updateSaleRefactored >>>> updated sale sub total amount >>>>" + updatedSaleSubTotalAmount );
		
		BigDecimal updatedSaleTotalAmount  = updatedSaleSubTotalAmount.add(updatedSaleTotalTaxAmount).setScale(2, RoundingMode.HALF_UP);		
		oldSale.setGrand_total(updatedSaleTotalAmount.doubleValue());
		oldSale.setGrandtotal(updatedSaleTotalAmount.toString());
		for (AddItemReqPojo item : productItemList) {
			oldSale.setPurchaseorder(item.getPurchaseorder());
			oldSale.setNote(item.getNote());
		}	
	;
		
		LeoLogger.info(">>>> updateSaleRefactored >>>> updated sale total amount >>>>  " + updatedSaleTotalAmount );
	
	    updateFinancialTransactionEntity(saleId, isSpecialSell , oldSaleTotalAmount ,  updatedSaleTotalAmount, customer);

	    updateFT(saleId, isSpecialSell , oldSaleTotalAmount ,  updatedSaleTotalAmount, customer);
	    
	  //  saveRegisterForSaleAndSpecialSale( saleId , updatedSaleTotalAmount);
	    saveSpecialSaleRegister(updatedSaleTotalAmount);
	    
	    applyRegisterhistorysaleedit(oldSale);
	    
		resonse.setMsgCode("001");
		resonse.setError(false);
		resonse.setMsgDescr(" Sale with sale id "+saleId+" and updated total sale "+oldSale.getGrand_total()+" updated Sucessfully!");
		
		return resonse;
	
	
	}
	
	  private void updateFinancialTransactionEntity(Long  id , Boolean isSpecialSale , BigDecimal oldSaleTotalAmount , BigDecimal updatedSaleTotalAmount,  MemberUser customer) {

		  SalesEntity sale = null;
		  SpecialSalesEntity specialSale = null;
		  BigDecimal differenceSaleTotalAmount= null;
		  
          if(isSpecialSale)          
            specialSale = specialsalesRepo.findBySaleId(id);
          else
            sale  = salesRepo.findBySaleId(id);
          	 
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			Date dueDate = cal.getTime();

			FinancialTransactionEntity lastFinancialTransaction = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
			FinancialTransactionEntity newFinancialTransaction = new FinancialTransactionEntity();

			 if(isSpecialSale)  {	
			LeoLogger.info(">>>> updateFinancialTransactionEntity >>>> old special sale total amount >>>> " + oldSaleTotalAmount );
			LeoLogger.info(">>>> updateFinancialTransactionEntity >>>> updated special sale total amount >>>>" + updatedSaleTotalAmount );
				
			newFinancialTransaction.setInvoideId(specialSale.getSaleId());			
			newFinancialTransaction.setCustomerId(specialSale.getMemberid());
			newFinancialTransaction.setCustomerName(specialSale.getMember_name());
			newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
			newFinancialTransaction.setDate(specialSale.getLastModifiedDate());
					
			} else{  
		
			LeoLogger.info(">>>> updateFinancialTransactionEntity >>>> old sale total amount >>>> " + oldSaleTotalAmount );
			LeoLogger.info(">>>> updateFinancialTransactionEntity >>>> updated sale total amount >>>>" + updatedSaleTotalAmount );
			
			newFinancialTransaction.setInvoideId(sale.getSaleId());			
			newFinancialTransaction.setCustomerId(sale.getMemberid());
			newFinancialTransaction.setCustomerName(sale.getMember_name());
			newFinancialTransaction.setReferenceno(sale.getReferenceno());
			newFinancialTransaction.setDate(sale.getLastModifiedDate());
			}

			newFinancialTransaction.setDueDate(dueDate);			
			newFinancialTransaction.setType("Invoice Edit");
			
		
		
			differenceSaleTotalAmount = updatedSaleTotalAmount.subtract(oldSaleTotalAmount).setScale(2, RoundingMode.HALF_UP);			
			newFinancialTransaction.setAmount(differenceSaleTotalAmount.doubleValue());
			LeoLogger.info(">>>> updateFinancialTransactionEntity >>>> difference in total amount >>>> " + differenceSaleTotalAmount );
		
			BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
			LeoLogger.info(">>>> updateFinancialTransactionEntity >>>> last balance FinancialTransaction >>>> " + lastBalance );
			
			BigDecimal newBalance  = differenceSaleTotalAmount.add(lastBalance).setScale(2, RoundingMode.HALF_UP);
			
		    LeoLogger.info(" >>>> updateFinancialTransactionEntity >>>> after updating difference amount, new balance in FinancialTransaction  >>>> " + newBalance );
			newFinancialTransaction.setBalance(newBalance.doubleValue());

			financialTransactionsRepo.save(newFinancialTransaction);
		  
	  }
	  
	  private void updateFT(Long  id , Boolean isSpecialSale , BigDecimal oldSaleTotalAmount , BigDecimal updatedSaleTotalAmount, MemberUser customer) {

		  SalesEntity sale = null;
		  SpecialSalesEntity specialSale = null;
		  BigDecimal differenceSaleTotalAmount= null;
		  List<FTEntity> ftList = null;
		  
          if(isSpecialSale)          
            specialSale = specialsalesRepo.findBySaleId(id);
          else
            sale  = salesRepo.findBySaleId(id);
          	 
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, 30);
			Date dueDate = cal.getTime();

			FTEntity lastFinancialTransaction = fTRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
			
			
			// deleting old sale for requiring one entry  FT for sale
			if(isSpecialSale) 	
				ftList = fTRepo.findByCustomerIdAndInvoideIdAndType(specialSale.getMemberid(), specialSale.getSaleId(), "Invoice");
			else
				ftList = fTRepo.findByCustomerIdAndInvoideIdAndType(sale.getMemberid(), sale.getSaleId(), "Invoice");
			
			LeoLogger.info(">>>> updateFT >>>> deleting old sale/special sale entry from ft " + ftList);
			fTRepo.deleteAll(ftList);
			
			
			FTEntity newFinancialTransaction = new FTEntity();

			 if(isSpecialSale)  {		
		
			LeoLogger.info(">>>> updateFT >>>> old special sale total amount >>>> " + oldSaleTotalAmount );
			LeoLogger.info(">>>> updateFT >>>> updated special sale total amount >>>>" + updatedSaleTotalAmount );
			
			newFinancialTransaction.setInvoideId(specialSale.getSaleId());			
			newFinancialTransaction.setCustomerId(specialSale.getMemberid());
			newFinancialTransaction.setCustomerName(specialSale.getMember_name());
			newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
					
			} else{  

			LeoLogger.info(">>>> updateFT >>>> old  sale total amount >>>> " + oldSaleTotalAmount );
			LeoLogger.info(">>>> updateFT >>>> updated sale total amount >>>>" + updatedSaleTotalAmount );
			
			newFinancialTransaction.setInvoideId(sale.getSaleId());			
			newFinancialTransaction.setCustomerId(sale.getMemberid());
			newFinancialTransaction.setCustomerName(sale.getMember_name());
			newFinancialTransaction.setReferenceno(sale.getReferenceno());
			}

			newFinancialTransaction.setDate(new Date());
			newFinancialTransaction.setDueDate(dueDate);			
			newFinancialTransaction.setType("Invoice");
			newFinancialTransaction.setAmount(updatedSaleTotalAmount.doubleValue());
			
		
			differenceSaleTotalAmount = updatedSaleTotalAmount.subtract(oldSaleTotalAmount).setScale(2, RoundingMode.HALF_UP);						
			LeoLogger.info(">>>> updateFT >>>> difference in total amount >>>> " + differenceSaleTotalAmount );
		
			BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
			LeoLogger.info(">>>> updateFT >>>> last balance FT >>>> " + lastBalance );
			
			BigDecimal newBalance  = differenceSaleTotalAmount.add(lastBalance).setScale(2, RoundingMode.HALF_UP);
	
		    LeoLogger.info(" >>>> updateFT >>>> after updating difference amount, new balance in FT  >>>> " + newBalance );
			newFinancialTransaction.setBalance(newBalance.doubleValue());

			fTRepo.save(newFinancialTransaction);
		  
	  }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO updateSpecialSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception {

		ResultVO resonse = new ResultVO();

			Boolean isSpecialSell = true;
			MemberUser customer = memberUserRepo.findById(productItemList.get(0).getCustomerId());
			
			LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> customer id >>>> " + customer.getId() );
			LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> customer name >>>> " + customer.getName() );
			LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> is tax applied >>>> " + productItemList.get(0).getTax() );

			SpecialSalesEntity oldSpecialSale = specialsalesRepo.findBySaleId(productItemList.get(0).getSaleId());
			BigDecimal oldSpecialSaleTotalAmount = new BigDecimal(oldSpecialSale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
			Long specialSaleId = oldSpecialSale.getSaleId();
			
			LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> special sale id >>>> " + specialSaleId );
			
			List<SpecialSalesItemEntity> oldSpecialSaleItemList =  specialsalesItemRepo.findBySaleid(oldSpecialSale.getSaleId());
				
		//	reverseRegisterForSaleAndSpecialSell(oldSpecialSaleTotalAmount);
			
			reverseSpecialSaleRegister(oldSpecialSaleTotalAmount);
		
			reverseInventoryForSaleAndSpecialSell(null , oldSpecialSaleItemList,  isSpecialSell);
			
			LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> removing old Special Sale Item List >>>> " );
			specialsalesItemRepo.deleteAll(oldSpecialSaleItemList);
		
       List<Object> data = saveSpecialSaleItemListAndUpdateInventory(productItemList , oldSpecialSale.getSaleId());
		
       BigDecimal updatedSpecialSaleSubTotalAmount = (BigDecimal) data.get(0);
       BigDecimal updatedSpecialSaleTotalTaxAmount =  (BigDecimal) data.get(1);
		
	
		oldSpecialSale.setTotal(updatedSpecialSaleSubTotalAmount.doubleValue());
		oldSpecialSale.setProduct_tax(updatedSpecialSaleTotalTaxAmount.doubleValue());
		oldSpecialSale.setTotal_tax(updatedSpecialSaleTotalTaxAmount.doubleValue());
		
		LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> updated special sale total tax amount >>>> " + updatedSpecialSaleTotalTaxAmount );
		LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> updated special sale sub total amount >>>> " + updatedSpecialSaleSubTotalAmount );
		
		BigDecimal updatedSpecialSaleTotalAmount= updatedSpecialSaleSubTotalAmount.add(updatedSpecialSaleTotalTaxAmount).setScale(2, RoundingMode.HALF_UP);		
		oldSpecialSale.setGrand_total(updatedSpecialSaleTotalAmount.doubleValue());
		for (AddItemReqPojo item : productItemList) {
			oldSpecialSale.setPurchaseorder(item.getPurchaseorder());
			oldSpecialSale.setNote(item.getNote());
		}
	
		
		LeoLogger.info(">>>> updateSpecialSaleRefactored >>>> updated special sale total amount >>>> " + updatedSpecialSaleTotalAmount );

	    updateFinancialTransactionEntity(specialSaleId, isSpecialSell , oldSpecialSaleTotalAmount ,  updatedSpecialSaleTotalAmount, customer);
	 
	    updateFT(specialSaleId, isSpecialSell , oldSpecialSaleTotalAmount ,  updatedSpecialSaleTotalAmount, customer);
	
	 //   saveRegisterForSaleAndSpecialSale( specialSaleId , updatedSpecialSaleTotalAmount);
	    
	    saveSpecialSaleRegister(updatedSpecialSaleTotalAmount);
	
		applyRegisterhistoryspecialedit(oldSpecialSale);
		
		resonse.setMsgCode("001");
		resonse.setError(false);
		resonse.setMsgDescr(" Special Sale with sale id "+specialSaleId+" and updated total sale "+oldSpecialSale.getGrand_total()+" updated Sucessfully!");
		return resonse;

	}
	
	  private void reverseRegisterForSaleAndSpecialSell(BigDecimal oldSaleOrSpecialSellTotalAmount)  {
		  
			RegisterEntity register = registerRepo.findAByDate(new Date());
			LeoLogger.info(">>>> reverseRegisterForSaleAndSpecialSell >>>> today register >>>> " + register);

			if (register != null)

			{
				LeoLogger.info(">>>> reverseRegisterForUpdateSaleAndSpecialSell >>>>>>>> salesamount before reversing register >>> " + register.getSalesamount());
				BigDecimal newSaleAmount = new BigDecimal(register.getSalesamount()).subtract(oldSaleOrSpecialSellTotalAmount).setScale(2, RoundingMode.HALF_UP);	
				register.setSalesamount(newSaleAmount.doubleValue());
				registerRepo.save(register);
				LeoLogger.info(">>>> reverseRegisterForUpdateSaleAndSpecialSell >>>>>>>> salesamount after reversing register  >>> " + register.getSalesamount());
			}
		  
	  }
	  
	  private void reverseInventoryForSaleAndSpecialSell(List<SalesItemEntity> oldSaleItemList , List<SpecialSalesItemEntity> oldSpecialSaleItemList , Boolean isSpecialSell )  {
		
		  
		  if(isSpecialSell) {
			for (SpecialSalesItemEntity oldSpecialSaleItem : oldSpecialSaleItemList) {

				ProductDetailsEntity product = productDetailsRepo.findByProductId(oldSpecialSaleItem.getProduct_id());				
				LeoLogger.info(">>>> reverseInventoryForUpdateSaleAndSpecialSell >>>>>>>> Product Quantity for product id "
						+ product.getProductId() + "  Before reversing is >>> " + product.getQuantity());

				product.setQuantity(product.getQuantity().add(( oldSpecialSaleItem.getQuantity())));
				productDetailsRepo.save(product);				
				LeoLogger.info(">>>> reverseInventoryForUpdateSaleAndSpecialSell >>>>>>>> Product Quantity for product id "
						+ product.getProductId() + "  After reversing is >>> " + product.getQuantity());
			}
			
		  }else {

			for (SalesItemEntity oldSalesItem : oldSaleItemList) {

				ProductDetailsEntity product = productDetailsRepo.findByProductId(oldSalesItem.getProduct_id());				
				LeoLogger.info(">>>> reverseInventoryForUpdateSaleAndSpecialSell >>>>>>>> Product Quantity for product id "
						+ product.getProductId() + "  Before reversing is >>> " + product.getQuantity());

				product.setQuantity(product.getQuantity() .add(oldSalesItem.getQuantity())) ;
				productDetailsRepo.save(product);				
				LeoLogger.info(">>>> reverseInventoryForUpdateSaleAndSpecialSell >>>>>>>> Product Quantity for product id "
						+ product.getProductId() + "  After reversing is >>> " + product.getQuantity());
			}
		  }
		  
	  }
	  
	  private  List<Object> saveSpecialSaleItemListAndUpdateInventory(List<AddItemReqPojo> productItemList , Long saleId)  {
		  
	
			BigDecimal specialSaleSubTotalAmount = new BigDecimal(0.0);
			BigDecimal specialSaleTotalTaxAmount = new BigDecimal(0.0);
			BigDecimal productTaxAmount = new BigDecimal(0.0);
			BigDecimal productSubTotalAmount = new BigDecimal(0.0);
			
			List<Object> saleSubTotalAndTotalTax = new ArrayList<>();

			for (AddItemReqPojo item : productItemList) {
				
				LeoLogger.info(" >>>> saveSpecialSaleItemListAndUpdateInventory >>>> product >>>> " + item );
				
				ProductDetailsEntity product = productDetailsRepo.findByProductId(item.getProductId());
				
				BigDecimal productUnitPrice = new BigDecimal( item.getPrice().doubleValue()).setScale(2, RoundingMode.HALF_UP);	
				BigDecimal productQuantity = new BigDecimal (item.getQuantity());
				
				LeoLogger.info(">>>> saveSpecialSaleItemListAndUpdateInventory >>>> product id >>>> " + product.getProductId() );
				LeoLogger.info(">>>> saveSpecialSaleItemListAndUpdateInventory >>>> product unit price >>>>" + productUnitPrice );
				LeoLogger.info(">>>> saveSpecialSaleItemListAndUpdateInventory >>>> product quantity >>>>" + productQuantity );
	  
				specialSaleSubTotalAmount = specialSaleSubTotalAmount.add(productUnitPrice.multiply((productQuantity))).setScale(2, RoundingMode.HALF_UP);	
				productSubTotalAmount = productUnitPrice.multiply((productQuantity)).setScale(2, RoundingMode.HALF_UP);				
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product sub total amount >>>> " + productSubTotalAmount );
			
				/*
				if (item.getTax().equalsIgnoreCase("YES") ){
				
				//	specialSaleTotalTaxAmount = specialSaleTotalTaxAmount.add(productUnitPrice.multiply(new BigDecimal(0.125)).multiply((productQuantity)))
				//			.setScale(2, RoundingMode.HALF_UP);
					productTaxAmount = productUnitPrice.multiply(new BigDecimal(0.125)).multiply((productQuantity)).setScale(2, RoundingMode.HALF_UP);
			} */
				
				LeoLogger.info(">>>> saveSpecialSaleItemListAndUpdateInventory >>>> product tax amount >>>> " + productTaxAmount );
				
	            SpecialSalesItemEntity specialSaleItem = new SpecialSalesItemEntity();
				
				UnitEntity unit = unitRepo.findById(Long.parseLong(item.getUnit()));   
				
				LeoLogger.info(">>>> saveSpecialSaleItemListAndUpdateInventory >>>> product unit name >>>> " + unit.getUnitname() );
				
				specialSaleItem.setSaleid(saleId);
				specialSaleItem.setProduct_id(item.getProductId());
				specialSaleItem.setItem_tax(item.getPrice().doubleValue() * 0.125);			
				specialSaleItem.setGst("12.5");			
				specialSaleItem.setItem_discount(0d);			
				specialSaleItem.setProduct_code(item.getProductId().toString());			
				specialSaleItem.setProduct_name(item.getProductName());			
				specialSaleItem.setRoll(item.getRoll());	
				specialSaleItem.setReal_unit_price(productUnitPrice.doubleValue());
				specialSaleItem.setUnit_quantity(unit.getUnitname());
				specialSaleItem.setSale_item_id(unit.getId());			
				specialSaleItem.setReturnqty("0");			
				specialSaleItem.setCost(product.getcost());	
				specialSaleItem.setSubtotal(productSubTotalAmount.doubleValue());
				specialSaleItem.setTax(productTaxAmount.toString());

				LeoLogger.info(">>>> saveSpecialSaleItemListAndUpdateInventory >>>> product quantity before special sale >>>> " + product.getQuantity() );
				
				if (!unit.getUnitname().equalsIgnoreCase("Piece") && !unit.getUnitname().equalsIgnoreCase("10Ft")&& !unit.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unit.getUnitname().equalsIgnoreCase("Roll 1000 Ft")&& !unit.getUnitname().equalsIgnoreCase("Roll 100 Ft") && !unit.getUnitname().equalsIgnoreCase("Box 50lb") && !unit.getUnitname().equalsIgnoreCase("Box 55lb")) {												
					specialSaleItem.setQuantity(productQuantity .multiply(( unit.getQuantity())));
					
					// updating inventory
					product.setQuantity(product.getQuantity().subtract(productQuantity.multiply(unit.getQuantity()))) ;
				
				} else {
					specialSaleItem.setQuantity(productQuantity);
					
					// updating inventory
					product.setQuantity(product.getQuantity() .subtract(productQuantity));
				}
				
				LeoLogger.info(">>>> saveSpecialSaleItemListAndUpdateInventory >>>> product quantity after special sale >>>> " + product.getQuantity() );
				productDetailsRepo.save(product);

			

				specialsalesItemRepo.save(specialSaleItem);				
		
			}
			
			/*
		    if (productItemList.get(0).getTax().equalsIgnoreCase("YES")) {	
		    	
		   	specialSaleTotalTaxAmount = specialSaleSubTotalAmount.multiply(new BigDecimal(0.125)).setScale(2, RoundingMode.HALF_UP);
						
			} */
			
			saleSubTotalAndTotalTax.add(specialSaleSubTotalAmount);
			saleSubTotalAndTotalTax.add(specialSaleTotalTaxAmount);
	
			return saleSubTotalAndTotalTax ;
		  
	  }
	  
	  private  List<Object> saveSaleItemListAndUpdateInventory(List<AddItemReqPojo> productItemList , Long saleId , MemberUser customer)  {

			BigDecimal saleSubTotalAmount = new BigDecimal(0.0);
			BigDecimal saleTotalTaxAmount = new BigDecimal(0.0);			
			List<Object> saleSubTotalAndTotalTax = new ArrayList<>();
			BigDecimal productSubTotalAmount = new BigDecimal(0.0);
			BigDecimal productTaxAmount = new BigDecimal(0.0);
			
			List<SalesItemEntity> saleItemList = new ArrayList<>();

			for (AddItemReqPojo item : productItemList) {
				
				LeoLogger.info(" >>>> saveSaleItemListAndUpdateInventory >>>> product >>>> " + item );
				
				ProductDetailsEntity product = productDetailsRepo.findByProductId(item.getProductId());
				
				BigDecimal productUnitPrice = new BigDecimal( item.getPrice().doubleValue()).setScale(2, RoundingMode.HALF_UP);	
				BigDecimal productQuantity = new BigDecimal(item.getQuantity());
				
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product id >>>> " + product.getProductId() );
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product unit price >>>> " + productUnitPrice );
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product quantity >>>> " + productQuantity );
	  
				saleSubTotalAmount = saleSubTotalAmount.add(productUnitPrice.multiply((productQuantity))).setScale(2, RoundingMode.HALF_UP);
				
				productSubTotalAmount = productUnitPrice.multiply((productQuantity)).setScale(2, RoundingMode.HALF_UP);				
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product sub total amount >>>> " + productSubTotalAmount );
				
				if (item.getTax().equalsIgnoreCase("YES") && !customer.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
				
				//	saleTotalTaxAmount = saleTotalTaxAmount.add(productUnitPrice.multiply(new BigDecimal(0.125)).multiply((productQuantity)))
				//			.setScale(2, RoundingMode.HALF_UP);
					productTaxAmount = productUnitPrice.multiply(new BigDecimal(0.125)).multiply((productQuantity)).setScale(2, RoundingMode.HALF_UP);
				}
				
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product tax amount >>>> " + productTaxAmount );
				
				SalesItemEntity salesItem = new SalesItemEntity();
				
				UnitEntity unit = unitRepo.findById(Long.parseLong(item.getUnit()));
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product unit name >>>> " + unit.getUnitname() );
	           
				salesItem.setSale_id(saleId);			
				salesItem.setProduct_id(item.getProductId());
				salesItem.setItem_tax(item.getPrice().doubleValue() * 0.125);			
				salesItem.setGst("12.5");			
				salesItem.setItem_discount(0d);			
				salesItem.setProduct_code(item.getProductId().toString());			
				salesItem.setProduct_name(item.getProductName());			
				salesItem.setRoll(item.getRoll());	
				salesItem.setReal_unit_price(productUnitPrice.doubleValue());
				salesItem.setUnit_quantity(unit.getUnitname());
				salesItem.setSale_item_id(unit.getId());			
				salesItem.setReturnqty("0");			
				salesItem.setCost(product.getcost());
				salesItem.setSubtotal(productSubTotalAmount.doubleValue());
				salesItem.setTax(productTaxAmount.toString());
				
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product quantity before sale >>>> " + product.getQuantity() );

				if (!unit.getUnitname().equalsIgnoreCase("Piece") && !unit.getUnitname().equalsIgnoreCase("10Ft")&& !unit.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unit.getUnitname().equalsIgnoreCase("Roll 1000 Ft")&& !unit.getUnitname().equalsIgnoreCase("Roll 100 Ft") && !unit.getUnitname().equalsIgnoreCase("Box 50lb") && !unit.getUnitname().equalsIgnoreCase("Box 55lb") && !unit.getUnitname().equalsIgnoreCase("lb")) {												
					salesItem.setQuantity(productQuantity .multiply(unit.getQuantity()));
					// updating inventory
					LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> updated qty  product.getQuantity>>>> " +product.getQuantity());
					LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> updated qty productQuantity  >>>> " +productQuantity);
					LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> updated qty unit qty >>>> " +unit.getQuantity());
					product.setQuantity(product.getQuantity().subtract(productQuantity.multiply(unit.getQuantity()))) ;
					
				
				} else {
					salesItem.setQuantity(productQuantity);
					// updating inventory
					product.setQuantity(product.getQuantity().subtract(productQuantity) );
				}
				
				LeoLogger.info(">>>> saveSaleItemListAndUpdateInventory >>>> product quantity after sale >>>> " + product.getQuantity() );
				productDetailsRepo.save(product);

				salesItemRepo.save(salesItem);

				saleItemList.add(salesItem);
			}
			
		    if (productItemList.get(0).getTax().equalsIgnoreCase("YES") && !customer.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
		    	
				saleTotalTaxAmount = saleSubTotalAmount.multiply(new BigDecimal(0.125)).setScale(2, RoundingMode.HALF_UP);
				
			}
			
			saleSubTotalAndTotalTax.add(saleSubTotalAmount);
			saleSubTotalAndTotalTax.add(saleTotalTaxAmount);
			
			saleSubTotalAndTotalTax.add(saleItemList);

			return saleSubTotalAndTotalTax ;		  
	  }

	@Override
	public ResultVO deleteSaleRefactored(Long saleId) {

		ResultVO resonse = new ResultVO();

		try {
			Boolean isSpecialSell = false;
			SalesEntity sale = salesRepo.findBySaleId(saleId);
			List<SalesItemEntity> salesItemList = salesItemRepo.findBySaleid(saleId);
			
            MemberUser customer  = memberUserRepo.findById(sale.getMemberid());
			
    		LeoLogger.info(" >>>> deleteSaleRefactored >>>> sale id >>>> " + sale.getSaleId() );
			LeoLogger.info(" >>>> deleteSaleRefactored >>>> customer id >>>> " + customer.getId() );
		
			BigDecimal saleTotalAmount = new BigDecimal(sale.getGrandtotal()).setScale(2, RoundingMode.HALF_UP);
		
				sale.setIsActive(1);
				sale.setSale_status("Deleted");
				//salesRepo.save(sale);
				
				entryForDeleteSale(saleId , sale);

				reverseInventoryForSaleAndSpecialSell(salesItemList , null , isSpecialSell);
				
				updateFinancialTransactionForDeleteSaleAndSpecialSell(saleId , isSpecialSell , saleTotalAmount, customer);
				
				updateFTForDeleteSaleAndSpecialSell(saleId , isSpecialSell , saleTotalAmount, customer );
				
				reverseRegisterForSaleAndSpecialSell(saleTotalAmount);
			
				applyRegisterhistorydelete(sale);
				
				salesRepo.delete(sale);
			

				resonse.setMsgDescr(" Sale with sale id "+saleId+" and  total sale "+sale.getGrand_total()+" deleted Sucessfully!");
				resonse.setMsgCode("001");
				resonse.setError(false);
				return resonse;
		
		} catch (Exception e) {
			LeoLogger.error(">>>> updateSpecialdeleteSaleRefactoredSaleRefactored >>>> error >>>> " + e.getMessage());
			e.printStackTrace();
			resonse.setMsgDescr("Error occured please contact support team!");
			return resonse;
		}
		
	
	}

	private void entryForDeleteSale(Long saleId, SalesEntity sale) {
		// TODO Auto-generated method stub
		DeletSaleEntity dsale = new DeletSaleEntity();
		
		dsale.setCf1(sale.getCf1());
		dsale.setCtype(sale.getCtype());
		dsale.setCustomeraddress(pdfPath);
	//	dsale.setDate(sale.getDate());
		//dsale.setDue_date(sale.getDue_date());
		dsale.setFileName(sale.getFileName());
		dsale.setGrandtotal(sale.getGrandtotal());
		dsale.setMember_name(sale.getMember_name());
		dsale.setMemberid(sale.getMemberid());
		dsale.setMembername(sale.getMembername());
		dsale.setNote(sale.getNote());
		dsale.setOrder_discount(sale.getOrder_discount());
		dsale.setOrder_discount_id(0);
		dsale.setOrder_tax(0);
		dsale.setOrder_tax_id(0);
		dsale.setPaid(0);
		dsale.setPaymentstatus(sale.getPaymentstatus());
		dsale.setPhonemain(sale.getPhonemain());
		dsale.setPincode(sale.getPincode());
		dsale.setProduct_discount(sale.getProduct_discount());
		dsale.setProduct_rollprice(sale.getProduct_rollprice());
		dsale.setProduct_tax(sale.getProduct_tax());
		dsale.setPurchaseorder(sale.getPurchaseorder());
		dsale.setReferenceno(sale.getReferenceno());
		dsale.setSale_status(sale.getSale_status());
		dsale.setSaleId(sale.getSaleId());
		dsale.setTotal(sale.getTotal());
		dsale.setTotal_discount(sale.getTotal_discount());
		
		deleteSaleRepo.save(dsale);
		
		
	}

	@Override
	public ResultVO deleteSpecialSaleRefactored(Long saleId) {

		ResultVO resonse = new ResultVO();

		try {
			Boolean isSpecialSell = true;
			List<SpecialSalesItemEntity> specialSellItemList = specialsalesItemRepo.findBySaleid(saleId);
		
			SpecialSalesEntity specialSell = specialsalesRepo.findBySaleId(saleId);
			
		    MemberUser customer  = memberUserRepo.findById(specialSell.getMemberid());
				
	    	LeoLogger.info(" >>>> deleteSpecialSaleRefactored >>>> special sale id >>>> " + specialSell.getSaleId() );
		    LeoLogger.info(" >>>> deleteSpecialSaleRefactored >>>> customer id >>>> " + customer.getId() );
			
			BigDecimal specialSellTotalAmount = new BigDecimal(specialSell.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
		
			specialSell.setIsActive(1);
			specialSell.setSale_status("Deleted");
			specialsalesRepo.save(specialSell);

				reverseInventoryForSaleAndSpecialSell(null , specialSellItemList , isSpecialSell);
				
				updateFinancialTransactionForDeleteSaleAndSpecialSell(saleId , isSpecialSell , specialSellTotalAmount, customer);
				
				updateFTForDeleteSaleAndSpecialSell(saleId , isSpecialSell , specialSellTotalAmount, customer );
				
			//	reverseRegisterForSaleAndSpecialSell(specialSellTotalAmount);
				reverseSpecialSaleRegister(specialSellTotalAmount);
			
				applyRegisterhistoryspecialdelete(specialSell);

				resonse.setMsgDescr("Special Sale with sale id "+saleId+" and  total sale "+specialSell.getGrand_total()+" deleted Sucessfully!");
				resonse.setError(false);
				return resonse;
		
		} catch (Exception e) {
			LeoLogger.error(">>>> deleteSpecialSaleRefactored >>>> error >>>> " + e.getMessage());
			e.printStackTrace();
			resonse.setMsgDescr("Error occured please contact support team!");
			return resonse;
		}
		
	
	}
	
	
	  private void updateFinancialTransactionForDeleteSaleAndSpecialSell( Long  id , Boolean isSpecialSale , BigDecimal saleTotalAmount,  MemberUser customer  ) {

		  SalesEntity sale = null;
		  SpecialSalesEntity specialSale = null;
	
		  
          if(isSpecialSale)          
            specialSale = specialsalesRepo.findBySaleId(id);
          else
            sale  = salesRepo.findBySaleId(id);
       
			FinancialTransactionEntity lastFinancialTransaction = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
			FinancialTransactionEntity newFinancialTransaction = new FinancialTransactionEntity();

			 if(isSpecialSale)  {				   	
			newFinancialTransaction.setInvoideId(specialSale.getSaleId());			
			newFinancialTransaction.setCustomerId(specialSale.getMemberid());
			newFinancialTransaction.setCustomerName(specialSale.getMember_name());
			newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
			newFinancialTransaction.setType("DeleteSale:" + specialSale.getSaleId());		
			} else{  

			newFinancialTransaction.setInvoideId(sale.getSaleId());			
			newFinancialTransaction.setCustomerId(sale.getMemberid());
			newFinancialTransaction.setCustomerName(sale.getMember_name());
			newFinancialTransaction.setReferenceno(sale.getReferenceno());
			newFinancialTransaction.setType("DeleteSale:" +sale.getSaleId());
			}
			 
			newFinancialTransaction.setAmount(saleTotalAmount.doubleValue());
			newFinancialTransaction.setDate(new Date());			
			
			
			LeoLogger.info(">>>> updateFinancialTransactionForDeleteSaleAndSpecialSell >>>> sale/special sale total amount >>>> " + saleTotalAmount );

			BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
			LeoLogger.info(">>>> updateFinancialTransactionForDeleteSaleAndSpecialSell >>>> last balance FinancialTransaction >>>> " + lastBalance );
			
			BigDecimal newBalance  = lastBalance.subtract(saleTotalAmount).setScale(2, RoundingMode.HALF_UP);
			
		    LeoLogger.info(">>>> updateFinancialTransactionForDeleteSaleAndSpecialSell >>>> new balance FinancialTransaction >>>> " + newBalance );
			newFinancialTransaction.setBalance(newBalance.doubleValue());

			financialTransactionsRepo.save(newFinancialTransaction);
	  }

	
	  
	  private void updateFTForDeleteSaleAndSpecialSell( Long  id , Boolean isSpecialSale , BigDecimal saleTotalAmount,  MemberUser customer ) {

		  SalesEntity sale = null;
		  SpecialSalesEntity specialSale = null;
		  
          if(isSpecialSale)          
            specialSale = specialsalesRepo.findBySaleId(id);
          else
            sale  = salesRepo.findBySaleId(id);
       
          FTEntity lastFinancialTransaction = fTRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
          FTEntity newFinancialTransaction = new FTEntity();

			 if(isSpecialSale)  {				   	
			newFinancialTransaction.setInvoideId(specialSale.getSaleId());			
			newFinancialTransaction.setCustomerId(specialSale.getMemberid());
			newFinancialTransaction.setCustomerName(specialSale.getMember_name());
			newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
			newFinancialTransaction.setType("DeleteSale:" + specialSale.getSaleId());		
			} else{  

			newFinancialTransaction.setInvoideId(sale.getSaleId());			
			newFinancialTransaction.setCustomerId(sale.getMemberid());
			newFinancialTransaction.setCustomerName(sale.getMember_name());
			newFinancialTransaction.setReferenceno(sale.getReferenceno());
			newFinancialTransaction.setType("DeleteSale:" +sale.getSaleId());
			}
			 
			newFinancialTransaction.setAmount(saleTotalAmount.doubleValue());
			newFinancialTransaction.setDate(new Date());			
			
			
			LeoLogger.info(">>>> updateFTForDeleteSaleAndSpecialSell >>>> sale/special sale total amount >>>> " + saleTotalAmount );

			BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
			LeoLogger.info(">>>> updateFTForDeleteSaleAndSpecialSell >>>> last balance FT >>>> " + lastBalance );
			
			BigDecimal newBalance  = lastBalance.subtract(saleTotalAmount).setScale(2, RoundingMode.HALF_UP);
		
		    LeoLogger.info(">>>> updateFTForDeleteSaleAndSpecialSell >>>> new balance FT >>>> " + newBalance );
			newFinancialTransaction.setBalance(newBalance.doubleValue());

			fTRepo.save(newFinancialTransaction);

}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO addPaymentRefactored(AddPaymentReqPojo paymentDto) {

		ResultVO response = new ResultVO();

			MemberUser customer  = memberUserRepo.findById(paymentDto.getMemberId());
			
			LeoLogger.info(" >>>> addPaymentRefactored >>>> customer id >>>> " + customer.getId() );
			LeoLogger.info(" >>>> addPaymentRefactored >>>> customer type >>>> " + customer.getCtype() );	
			LeoLogger.info(" >>>> addPaymentRefactored >>>> pay amount >>>> " + paymentDto.getAmount() );	
			LeoLogger.info(" >>>> addPaymentRefactored >>>> pay type >>>> " + paymentDto.getPtype() );	
			
			Boolean isSpecialSell;
			if(customer.getCtype().equalsIgnoreCase("Special"))
			 isSpecialSell = true;
			else 
				 isSpecialSell = false;
			
			
			Long saleOrSpecialSellId = paymentDto.getSaleId();
			PaymentEntity newPayment = new PaymentEntity();	
			
			applyPaymentForSaleAndSpecialSell(saleOrSpecialSellId, isSpecialSell, paymentDto, customer, newPayment);

			saveFinancialTransactionForPayment(saleOrSpecialSellId, isSpecialSell, newPayment, customer);
			
			saveFTForPayment(saleOrSpecialSellId, isSpecialSell, newPayment, customer);
			
		    if (isSpecialSell) {
		        saveSpecialRegisterForPayment(new BigDecimal(newPayment.getGrand_total()));
		        applySpecialRegisterHistoryPayment(newPayment); // Save in special history

		    } else {
		        saveRegisterForPayment(paymentDto, newPayment);
		        applyRegisterhistorypayment(newPayment); // Save in normal history
		    }

			
			LeoLogger.info(" >>>> addPaymentRefactored >>>> payment is successfully applied with payment id >>>>  " + newPayment.getId() );	

			response.setMsgDescr("New payment with payment id "+newPayment.getId()+" and payment amount "+newPayment.getGrand_total()+" added Sucessfully!");
			response.setMsgCode("001");
			response.setError(false);
			return response;


	}
	
	
	private void applyPaymentForSaleAndSpecialSell(Long saleOrSpecialSellId , Boolean isSpecialSale , AddPaymentReqPojo paymentDto , MemberUser customer , PaymentEntity newPayment  )  {
		
		BigDecimal payAmount = new BigDecimal(paymentDto.getAmount()).setScale(2, RoundingMode.HALF_UP);
		BigDecimal dueAmount ;
		BigDecimal totalPaidAmount; ;
		SalesEntity sale = null;
		SpecialSalesEntity specialSale = null;
		
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		int currentMonth = date.getMonth() + 1;

			 
	  	   newPayment.setMember_id(customer.getId());
   		   newPayment.setMember_name(customer.getName());
           newPayment.setstatus("Paid");
           newPayment.setPtype(paymentDto.getPtype());
           newPayment.setPref(paymentDto.getPref());		
   		   newPayment.setPaymentdate(date);
   		   newPayment.setNote(paymentDto.getNote());
   		   newPayment.setCtype(paymentDto.getCtype()); 
   		   newPayment.setRsaleId(saleOrSpecialSellId);
   		   
   		   paymentRepo.save(newPayment);
		  
         if(isSpecialSale)  {   
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> special sell id >>>> " + saleOrSpecialSellId);
           specialSale = specialsalesRepo.findBySaleId(saleOrSpecialSellId); 
           dueAmount = new BigDecimal( specialSale.getGrand_total()).subtract(new BigDecimal(specialSale.getPaid())).setScale(2, RoundingMode.HALF_UP);
           
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> special sell grand total >>>> " + specialSale.getGrand_total());
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> special sell paid amount >>>> " + specialSale.getPaid());
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> special sell due  amount >>>> " + dueAmount);
        
       	if (payAmount.doubleValue() >= dueAmount.doubleValue()) {
       		specialSale.setPaymentstatus("Paid");
       		specialSale.setSale_status("Paid");
       		
       		totalPaidAmount = new BigDecimal( specialSale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
       		specialSale.setPaid(totalPaidAmount.doubleValue());
       		
       		newPayment.setGrand_total(dueAmount.doubleValue());	
       		LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> special sell is fully paid >>>> " + dueAmount);

		} else {
			totalPaidAmount = new BigDecimal(specialSale.getPaid()).add(payAmount).setScale(2, RoundingMode.HALF_UP);
			specialSale.setPaid(totalPaidAmount.doubleValue());
			
			newPayment.setGrand_total(payAmount.doubleValue());
			LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> special sell is partialy paid >>>> " + payAmount);
		}
       	
     	LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> special sell paid amount after payment >>> " + specialSale.getPaid());
       	
     	newPayment.setSalesreferenceno(specialSale.getReferenceno());
       	newPayment.setReferenceno("Payment" + currentYear + "/" + currentMonth + "/"
				+ specialSale.getSaleId() + "-" + newPayment.getId());
       	
       	 specialsalesRepo.save(specialSale);
       	            
         } else {
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>> sell id >>>> " + saleOrSpecialSellId);
           sale  = salesRepo.findBySaleId(saleOrSpecialSellId);          
           dueAmount = new BigDecimal( sale.getGrand_total()).subtract(new BigDecimal(sale.getPaid())).setScale(2, RoundingMode.HALF_UP);
           
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>>  sell grand total >>>> " + sale.getGrand_total());
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>>  sell paid amount >>>> " + sale.getPaid());
           LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>>  sell due  amount >>>> " + dueAmount);
         
                 
       	if (payAmount.doubleValue() >= dueAmount.doubleValue()) {
       		sale.setPaymentstatus("Paid");
       		sale.setSale_status("Paid");       		
       		totalPaidAmount = new BigDecimal( sale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
       		sale.setPaid(totalPaidAmount.doubleValue());
       		
       		newPayment.setGrand_total(dueAmount.doubleValue());
       		LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>>  sell is fully paid >>>> " + dueAmount);
		} else {			
			
			totalPaidAmount = payAmount.add(new BigDecimal(sale.getPaid())).setScale(2, RoundingMode.HALF_UP);
			sale.setPaid(totalPaidAmount.doubleValue());
			
			newPayment.setGrand_total(payAmount.doubleValue());	
			LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>>  sell is partialy paid >>>> " + payAmount);
		}
       	
    	LeoLogger.info(" >>>>>>>> applyPaymentForSaleAndSpecialSell >>>>>>>  sell paid amount after payment >>> " + sale.getPaid());
    	
    	newPayment.setSalesreferenceno(sale.getReferenceno());
      	newPayment.setReferenceno("Payment" + currentYear + "/" + currentMonth + "/"
				+ sale.getSaleId() + "-" + newPayment.getId());
      	
      	 salesRepo.save(sale);
       	
         }
         
        // if payment done by customer deposit then it should be subtracted from deposit
         if(paymentDto.getPtype().equalsIgnoreCase("Deposit")) {
 			Boolean isDepositAdded = false;
 			addOrSubtractCustomerDeposit(isDepositAdded, new BigDecimal(newPayment.getGrand_total()), customer );
 			}
         
    
	}
	
	
	private void saveFinancialTransactionForPayment(Long id , Boolean isSpecialSale , PaymentEntity newPayment  , MemberUser customer ) {

		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		Date dueDate = cal.getTime();
		
		  SalesEntity sale = null;
		  SpecialSalesEntity specialSale = null;
		  
          if(isSpecialSale)          
            specialSale = specialsalesRepo.findBySaleId(id);
          else
            sale  = salesRepo.findBySaleId(id);
          
          BigDecimal payAmount = new BigDecimal(newPayment.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
       
      	  FinancialTransactionEntity lastFinancialTransaction = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
		  FinancialTransactionEntity newFinancialTransaction = new FinancialTransactionEntity();
		  
		  
			newFinancialTransaction.setCustomerId(customer.getId());
			newFinancialTransaction.setCustomerName(customer.getName());
			newFinancialTransaction.setDate(currentDate);
			newFinancialTransaction.setDueDate(dueDate);
			newFinancialTransaction.setAmount(payAmount.doubleValue());
			
			if (newPayment.getPtype().equalsIgnoreCase("Deposit")) 
				newFinancialTransaction.setType("Deposit Payment");
				else
				newFinancialTransaction.setType("Payment");

		   if(isSpecialSale)  {				   	
			newFinancialTransaction.setInvoideId(specialSale.getSaleId());					
			newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
			LeoLogger.info(" >>>> saveFinancialTransactionForPayment >>>> special sale payment amount >>>> " + payAmount );
				
			} else{  

			newFinancialTransaction.setInvoideId(sale.getSaleId());			
			newFinancialTransaction.setReferenceno(sale.getReferenceno());
			LeoLogger.info(" >>>> saveFinancialTransactionForPayment >>>> sale payment amount >>>> " + payAmount );
			}
		   
		    

			BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
			LeoLogger.info(" >>>> saveFinancialTransactionForPayment >>>> last balance FinancialTransaction >>>> " + lastBalance );
			
			BigDecimal newBalance  = lastBalance.subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
			
		    LeoLogger.info(">>>> saveFinancialTransactionForPayment >>>> new balance after payment in  FinancialTransaction >>>> " + newBalance );
			newFinancialTransaction.setBalance(newBalance.doubleValue());

			financialTransactionsRepo.save(newFinancialTransaction);
	
	}
	
	private void saveFTForPayment(Long id , Boolean isSpecialSale , PaymentEntity newPayment  , MemberUser customer ) {

		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		Date dueDate = cal.getTime();
		
		  SalesEntity sale = null;
		  SpecialSalesEntity specialSale = null;
		  
        if(isSpecialSale)          
          specialSale = specialsalesRepo.findBySaleId(id);
        else
          sale  = salesRepo.findBySaleId(id);
        
        BigDecimal payAmount = new BigDecimal(newPayment.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
     
    	  FTEntity lastFinancialTransaction = fTRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
    	  FTEntity newFinancialTransaction = new FTEntity();

			newFinancialTransaction.setCustomerId(customer.getId());
			newFinancialTransaction.setCustomerName(customer.getName());
			newFinancialTransaction.setDate(currentDate);
			newFinancialTransaction.setDueDate(dueDate);
			newFinancialTransaction.setAmount(payAmount.doubleValue());

			if (newPayment.getPtype().equalsIgnoreCase("Deposit")) 
				newFinancialTransaction.setType("Deposit Payment");
				else
				newFinancialTransaction.setType("Payment");
			
		   if(isSpecialSale)  {				   	
			newFinancialTransaction.setInvoideId(specialSale.getSaleId());					
			newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
			
			  LeoLogger.info(" >>>> saveFTForPayment >>>> special sale payment amount >>>> " + payAmount );
			} else{  

			newFinancialTransaction.setInvoideId(sale.getSaleId());			
			newFinancialTransaction.setReferenceno(sale.getReferenceno());
			  LeoLogger.info(" >>>> saveFTForPayment >>>> sale payment amount >>>> " + payAmount );
			}
		   
		 

		   BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
			LeoLogger.info(" >>>> saveFTForPayment >>>> last balance FT >>>>" + lastBalance );
					
		   BigDecimal newBalance  = lastBalance.subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
					
		   LeoLogger.info(">>>> saveFTForPayment >>>> new balance after payment in  FT >>>> " + newBalance );
		   newFinancialTransaction.setBalance(newBalance.doubleValue());

		   fTRepo.save(newFinancialTransaction);
	
	}
	
	
	private void saveRegisterForPayment(AddPaymentReqPojo paymentDto, PaymentEntity newPayment ) {
		
		Date currentDate = new Date();
		RegisterEntity todayRegister = registerRepo.findAByDate(currentDate);
		LeoLogger.info(">>>> saveRegisterForPayment >>>> today register >>>> " + todayRegister);
		BigDecimal payAmount = new BigDecimal(newPayment.getGrand_total()).setScale(2, RoundingMode.HALF_UP);		
		String pType = paymentDto.getPtype();
		BigDecimal newClosingBalance ;

		if (todayRegister != null)

		{
			if (pType.equalsIgnoreCase("cash")) {
				
				 LeoLogger.info(">>>> saveRegisterForPayment >>>> last cash payment >>>> " + todayRegister.getCashpayment() );
				BigDecimal newCashPayment = payAmount.add(new BigDecimal(todayRegister.getCashpayment())).setScale(2, RoundingMode.HALF_UP);
				todayRegister.setCashpayment(newCashPayment.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>>  new cash payment after adding payment  >>>> " + todayRegister.getCashpayment() );
				
			} else if (pType.equalsIgnoreCase("cheque")) {
				
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last cheque payment >>>>" + todayRegister.getChequepayment());
				BigDecimal newChequePayment = payAmount.add(new BigDecimal(todayRegister.getChequepayment())).setScale(2, RoundingMode.HALF_UP);
				todayRegister.setChequepayment(newChequePayment.doubleValue());;
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new cheque payment after adding payment  >>>> " + todayRegister.getChequepayment());
			
			} else if (pType.equalsIgnoreCase("online")) {
				
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last online payment >>>> " + todayRegister.getOnlinepayment());
				BigDecimal newoOlinePayment = payAmount.add(new BigDecimal(todayRegister.getOnlinepayment())).setScale(2, RoundingMode.HALF_UP);
				todayRegister.setOnlinepayment(newoOlinePayment.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new online payment after adding payment >>>> " + todayRegister.getOnlinepayment());
			
			} else if (pType.equalsIgnoreCase("other") ||pType.equalsIgnoreCase("Deposit")) {
				
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last other payment >>>> " + todayRegister.getOtherpayment());
				BigDecimal newOtherPayment = payAmount.add(new BigDecimal(todayRegister.getOtherpayment())).setScale(2, RoundingMode.HALF_UP);
				todayRegister.setOtherpayment(newOtherPayment.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new other payment after adding payment >>>> " + todayRegister.getOtherpayment());
			} else {
				
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last credit card payment >>>>" + todayRegister.getCreditcardpayment());
				BigDecimal newCreditCardPayment = payAmount.add(new BigDecimal(todayRegister.getCreditcardpayment())).setScale(2, RoundingMode.HALF_UP);
				todayRegister.setCreditcardpayment(newCreditCardPayment.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new credit card payment after adding payment  >>>> " + todayRegister.getCreditcardpayment());
		
			}
			
			  LeoLogger.info(">>>> saveRegisterForPayment >>>> last closing balance >>>> " + todayRegister.getClosingbal() );
			 newClosingBalance = payAmount.add(new BigDecimal(todayRegister.getClosingbal())).setScale(2, RoundingMode.HALF_UP);
			todayRegister.setClosingbal(newClosingBalance.doubleValue());
			LeoLogger.info(">>>> saveRegisterForPayment >>>> new closing balance after adding payment >>>> " + todayRegister.getClosingbal() );
			
			
			registerRepo.save(todayRegister);
		} else {
			
			RegisterEntity newRegister = new RegisterEntity();			
			newRegister.setCashinhand(1000.00);			
			newRegister.setDate(currentDate);
			newRegister.setOpeningbal(1000.00);
			newRegister.setClosingbal(1000.00);
			newRegister.setRefunds(0.00);
			newRegister.setReferenceno(String.valueOf(paymentDto.getSaleId()));
			newRegister.setSalesamount(0.0);
			newRegister.setStatus("Open");
			
			if (pType.equalsIgnoreCase("cash")) {
				 LeoLogger.info(">>>> saveRegisterForPayment >>>> last cash payment >>>> " + newRegister.getCashpayment() );
				newRegister.setCashpayment(payAmount.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>>  new cash payment after adding payment  >>>> " + newRegister.getCashpayment() );
				
			} else if (pType.equalsIgnoreCase("cheque")) {	
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last cheque payment >>>> " + newRegister.getChequepayment());				
				newRegister.setChequepayment(payAmount.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new cheque payment after adding payment  >>>> " + newRegister.getChequepayment());
				
			} else if (pType.equalsIgnoreCase("online")) {
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last online payment >>>> " + newRegister.getOnlinepayment());			
				newRegister.setOnlinepayment(payAmount.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new online payment after adding payment >>>> " + newRegister.getOnlinepayment());
				
			} else if (pType.equalsIgnoreCase("other") || pType.equalsIgnoreCase("Deposit")) {
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last other payment >>>> " + newRegister.getOtherpayment());				
				newRegister.setOtherpayment(payAmount.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new other payment after adding payment >>>> " + newRegister.getOtherpayment());
				
			} else {
				LeoLogger.info(">>>> saveRegisterForPayment >>>> last credit card payment >>>> " + newRegister.getCreditcardpayment());			
				newRegister.setCreditcardpayment(payAmount.doubleValue());
				LeoLogger.info(">>>> saveRegisterForPayment >>>> new credit card payment after adding payment  >>>> " + newRegister.getCreditcardpayment());
			}
			
			 LeoLogger.info(">>>> saveRegisterForPayment >>>> last closing balance >>>> " + newRegister.getClosingbal() );
			 
				if (!pType.equalsIgnoreCase("other")) {				
	 				newClosingBalance = new BigDecimal(newRegister.getClosingbal()).add(payAmount).setScale(2, RoundingMode.HALF_UP);
	 				newRegister.setClosingbal(newClosingBalance.doubleValue());
	 			}
		
			LeoLogger.info(">>>> saveRegisterForPayment >>>> new closing balance after adding payment >>>> " + newRegister.getClosingbal() );
		
			registerRepo.save(newRegister);
			LeoLogger.info(">>>> saveRegisterForPayment >>>> new register created >>>> " + newRegister);
		}
	}

	@Override
	public ResultVO deletePaymentRefactored(Long paymentId) {

		ResultVO resultVO = new ResultVO();
		
		try {
			
		   PaymentEntity payment  = paymentRepo.findById(paymentId);
		   MemberUser customer  = memberUserRepo.findById(payment.getMemberid());
		   BigDecimal payAmount = new BigDecimal(payment.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
		   Long saleOrSpecialSellId = payment.getRsaleId();

			
			LeoLogger.info(" >>>> deletePaymentRefactored >>>> customer id >>>> " + customer.getId() );
			LeoLogger.info(" >>>> deletePaymentRefactored >>>> payment id >>>> " + payment.getId());		
			LeoLogger.info(" >>>> deletePaymentRefactored >>>> pay amount >>>> " + payAmount );	
			LeoLogger.info(" >>>> deletePaymentRefactored >>>> pay type >>>> " + payment.getPtype());	
			LeoLogger.info(" >>>> deletePaymentRefactored >>>> sale/special sale id >>>> " + saleOrSpecialSellId);	
		  
		  
			Boolean isSpecialSell;
			if(payment.getCtype().equalsIgnoreCase("Special"))
			 isSpecialSell = true;
			else 
				 isSpecialSell = false;
			
			// if payment done by customer deposit then it should be added back to deposit
			if(payment.getPtype().equalsIgnoreCase("Deposit")) {
			Boolean isDepositAdded = true;
			addOrSubtractCustomerDeposit(isDepositAdded, payAmount, customer );
			}

			deletePaymentForSaleAndSpecialSell(saleOrSpecialSellId, isSpecialSell, payAmount);
			
			saveFinancialTransactionForDeletePayment(saleOrSpecialSellId, isSpecialSell, payAmount, customer);
			
			saveFTForDeletePayment(saleOrSpecialSellId, isSpecialSell, payAmount, customer);
			
		    // Delete from register / special register
	        if (isSpecialSell) {
	            saveSpecialRegisterForDeletePayment(payAmount);
	            deleteSpecialRegisterHistoryForPayment(payment);
	        } else {
	            saveRegisterForDeletePayment(payment);
	            deleteRegisterHistoryForPayment(payment);
	        	applyRegisterhistorydeletepayment(payment);
	        }

			paymentRepo.delete(payment);
			
			LeoLogger.info(" >>>> addPaymentRefactored >>>> payment is successfully deleted with payment id >>>>  " + paymentId );	

			//resultVO.setMsgDescr(" payment with payment id "+paymentId+" and payment amount "+payAmount+" deleted Sucessfully!");
			String saleRefNo = payment.getSalesreferenceno();

			resultVO.setMsgDescr(
			    "Payment deleted successfully! Payment ID: " + paymentId +
			    ", Amount: " + payAmount +
			    ", Sale Ref No: " + (saleRefNo != null ? saleRefNo : "N/A")
			);
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
			LeoLogger.info(">>>> addPaymentRefactored >>>> error >>>> " + e.getMessage() );
			resultVO.setMsgDescr("error while deleting payment , please contact support team!");
			return resultVO;
		}
	
	
	}
	
	private void deleteRegisterHistoryForPayment(PaymentEntity payment) {

	    List<RegisterhistoryEntity> historyList =
	            registerhistoryRepo.findAllByReferencenoAndStatus(
	                    payment.getReferenceno(),
	                    "Payment"
	            );

	    if (!historyList.isEmpty()) {
	        registerhistoryRepo.deleteAll(historyList);
	        LeoLogger.info(">>>> deleteRegisterHistoryForPayment >>>> Registerhistory entries deleted for payment id >>>> " + payment.getId());
	    }
	}

	private void deletePaymentForSaleAndSpecialSell(Long id , Boolean isSpecialSale ,  BigDecimal payAmount  ) {
		
	   SalesEntity sale = null;
	   SpecialSalesEntity specialSale = null;
	   BigDecimal newPaidAmount;
		  
      if(isSpecialSale)   {       
        specialSale = specialsalesRepo.findBySaleId(id);
        
        LeoLogger.info(" >>>>>>>> deletePaymentForSaleAndSpecialSell >>>>>>> special sell id >>>> " + specialSale.getSaleId());
        LeoLogger.info(" >>>>>>>> deletePaymentForSaleAndSpecialSell >>>>>>> special sell paid amount before subtracting payment >>>> " + specialSale.getPaid());

        newPaidAmount = new BigDecimal(specialSale.getPaid()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
        specialSale.setPaid(newPaidAmount.doubleValue());
        specialSale.setPaymentstatus("Due");
        specialSale.setSale_status("Due");
        specialsalesRepo.save(specialSale);
        LeoLogger.info(" >>>>>>>> deletePaymentForSaleAndSpecialSell >>>>>>> special sell paid amount after subtracting payment >>>> " + specialSale.getPaid());

      }
      else {
        sale  = salesRepo.findBySaleId(id);
        LeoLogger.info(" >>>>>>>> deletePaymentForSaleAndSpecialSell >>>>>>>  sell id >>>> " + sale.getSaleId());
        LeoLogger.info(" >>>>>>>> deletePaymentForSaleAndSpecialSell >>>>>>>  sell paid amount before subtracting payment >>>> " + sale.getPaid());

        
        newPaidAmount = new BigDecimal(sale.getPaid()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
        sale.setPaid(newPaidAmount.doubleValue());
        sale.setPaymentstatus("Due");
    	sale.setSale_status("Due");
		salesRepo.save(sale);
		LeoLogger.info(" >>>>>>>> deletePaymentForSaleAndSpecialSell >>>>>>>  sell paid amount after subtracting payment >>>> " + sale.getPaid());
      
      }
	
	}
	
	
  	private void saveFinancialTransactionForDeletePayment(Long id , Boolean isSpecialSale , BigDecimal payAmount , MemberUser customer ) {
  		
  	  SalesEntity sale = null;
	  SpecialSalesEntity specialSale = null;
	  
      if(isSpecialSale)          
        specialSale = specialsalesRepo.findBySaleId(id);
      else
        sale  = salesRepo.findBySaleId(id);
   
  	  FinancialTransactionEntity lastFinancialTransaction = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
	  FinancialTransactionEntity newFinancialTransaction = new FinancialTransactionEntity();
	  
	  
		newFinancialTransaction.setCustomerId(customer.getId());
		newFinancialTransaction.setCustomerName(customer.getName());
		newFinancialTransaction.setDate(new Date());
		newFinancialTransaction.setAmount(payAmount.doubleValue());
		newFinancialTransaction.setType("Reverse Payment");

	   if(isSpecialSale)  {				   	
		newFinancialTransaction.setInvoideId(specialSale.getSaleId());					
		newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
			
		} else{  

		newFinancialTransaction.setInvoideId(sale.getSaleId());			
		newFinancialTransaction.setReferenceno(sale.getReferenceno());
		
		}
	   
	   BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
	   LeoLogger.info(" >>>> saveFinancialTransactionForDeletePayment >>>> last balance in FinancialTransaction >>>> " + lastBalance );
				
	   BigDecimal newBalance  = lastBalance.add(payAmount).setScale(2, RoundingMode.HALF_UP);				
	   LeoLogger.info(">>>> saveFinancialTransactionForDeletePayment >>>> new balance after reversing payment in  FinancialTransaction >>>> " + newBalance );
	   
	   newFinancialTransaction.setBalance(newBalance.doubleValue());
	   financialTransactionsRepo.save(newFinancialTransaction);

  	}
      
  	
  	private void saveFTForDeletePayment(Long id , Boolean isSpecialSale ,  BigDecimal payAmount , MemberUser customer ) {
  		
  		  SalesEntity sale = null;
  		  SpecialSalesEntity specialSale = null;
  		  
  	      if(isSpecialSale)          
  	        specialSale = specialsalesRepo.findBySaleId(id);
  	      else
  	        sale  = salesRepo.findBySaleId(id);
  	   
  	  	  FTEntity lastFinancialTransaction = fTRepo.findTopByCustomerIdOrderByFanIdDesc(customer.getId());
  	  	  FTEntity newFinancialTransaction = new FTEntity();

  			newFinancialTransaction.setCustomerId(customer.getId());
  			newFinancialTransaction.setCustomerName(customer.getName());
  			newFinancialTransaction.setDate(new Date());
  			newFinancialTransaction.setAmount(payAmount.doubleValue());
  			newFinancialTransaction.setType("Reverse Payment");

  		   if(isSpecialSale)  {				   	
  			newFinancialTransaction.setInvoideId(specialSale.getSaleId());					
  			newFinancialTransaction.setReferenceno(specialSale.getReferenceno());
  				
  			} else{  

  			newFinancialTransaction.setInvoideId(sale.getSaleId());			
  			newFinancialTransaction.setReferenceno(sale.getReferenceno());
  			
  			}
 
		   BigDecimal lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
		   LeoLogger.info(" >>>> saveFTForDeletePayment >>>> last balance FT >>>> " + lastBalance );
					
		   BigDecimal newBalance  = lastBalance.add(payAmount).setScale(2, RoundingMode.HALF_UP);					
		   LeoLogger.info(">>>> saveFTForDeletePayment >>>> new balance after reversing payment in  FT >>>> " + newBalance );
		   
		   newFinancialTransaction.setBalance(newBalance.doubleValue());
  		   fTRepo.save(newFinancialTransaction);
  		
  	}
  	
  	
  	
     private void saveRegisterForDeletePayment( PaymentEntity payment ) {
 		
 		Date currentDate = new Date();
 		RegisterEntity todayRegister = registerRepo.findAByDate(currentDate);
 		LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> today register >>>> " + todayRegister);
 		BigDecimal payAmount = new BigDecimal(payment.getGrand_total()).setScale(2, RoundingMode.HALF_UP);		
 		String pType = payment.getPtype();
 		BigDecimal newClosingBalance ;
 		
 		
 		if (todayRegister != null)

 		{
 			if (pType.equalsIgnoreCase("cash")) {
 				
 				 LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last cash payment >>>> " + todayRegister.getCashpayment() );
 				BigDecimal newCashPayment = new BigDecimal(todayRegister.getCashpayment()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
 				todayRegister.setCashpayment(newCashPayment.doubleValue());
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>>  new cash payment after subtracting payment  >>>> " + todayRegister.getCashpayment() );
 				
 			} else if (pType.equalsIgnoreCase("cheque")) {
 				
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last cheque payment >>>> " + todayRegister.getChequepayment());
 				BigDecimal newChequePayment = new BigDecimal(todayRegister.getChequepayment()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
 				todayRegister.setChequepayment(newChequePayment.doubleValue());
 						
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new cheque payment after subtracting payment  >>>> " + todayRegister.getChequepayment());
 			
 			} else if (pType.equalsIgnoreCase("online")) {
 				
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last online payment >>>> " + todayRegister.getOnlinepayment());
 				BigDecimal newoOlinePayment = new BigDecimal(todayRegister.getOnlinepayment()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
 				todayRegister.setOnlinepayment(newoOlinePayment.doubleValue());
 				
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new online payment after subtracting payment >>>> " + todayRegister.getOnlinepayment());
 			
 			} else if (pType.equalsIgnoreCase("other") || pType.equalsIgnoreCase("Deposit")) {
 				
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last other payment >>>> " + todayRegister.getOtherpayment());
 				BigDecimal newOtherPayment = new BigDecimal(todayRegister.getOtherpayment()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
 				todayRegister.setOtherpayment(newOtherPayment.doubleValue());
 				
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new other payment after subtracting payment >>>> " + todayRegister.getOtherpayment());
 			} else {
 				
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last credit card payment >>>> " + todayRegister.getCreditcardpayment());
 				BigDecimal newCreditCardPayment = new BigDecimal(todayRegister.getCreditcardpayment()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
 				todayRegister.setCreditcardpayment(newCreditCardPayment.doubleValue());
 				
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new credit card payment subtracting adding payment  >>>> " + todayRegister.getCreditcardpayment());
 		
 			}
 			
 			  LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last closing balance >>>> " + todayRegister.getClosingbal() );
 			 newClosingBalance = new BigDecimal(todayRegister.getClosingbal()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
 			todayRegister.setClosingbal(newClosingBalance.doubleValue());
 			LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new closing balance after subtracting payment >>>> " + todayRegister.getClosingbal() );
 			
 			
 			registerRepo.save(todayRegister);
 		} else {
 			
 			RegisterEntity newRegister = new RegisterEntity();			
 			newRegister.setCashinhand(1000.00);			
 			newRegister.setDate(currentDate);
 			newRegister.setOpeningbal(1000.00);
 			newRegister.setClosingbal(1000.00);
 			newRegister.setRefunds(0.00);
 			newRegister.setReferenceno(String.valueOf(payment.getRsaleId()));
 			newRegister.setSalesamount(0.0);
 			newRegister.setStatus("Open");
 			
 			if (pType.equalsIgnoreCase("cash")) {
 				 LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last cash payment >>>> " + newRegister.getCashpayment() );
 				newRegister.setCashpayment(payAmount.doubleValue());
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>>  new cash payment after subtracting payment  >>>> " + newRegister.getCashpayment() );
 				
 			} else if (pType.equalsIgnoreCase("cheque")) {	
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last cheque payment >>>> " + newRegister.getChequepayment());
 				newRegister.setChequepayment(payAmount.doubleValue());
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new cheque payment after subtracting payment  >>>> " + newRegister.getChequepayment());
 				
 			} else if (pType.equalsIgnoreCase("online")) {
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last online payment >>>> " + newRegister.getOnlinepayment());
 				newRegister.setOnlinepayment(payAmount.doubleValue());
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new online payment after subtracting payment >>>> " + newRegister.getOnlinepayment());
 				
 			} else if (pType.equalsIgnoreCase("other") || pType.equalsIgnoreCase("Deposit")) {
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last other payment >>>> " + newRegister.getOtherpayment());
 				newRegister.setOtherpayment(payAmount.doubleValue());
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new other payment after subtracting payment >>>> " + newRegister.getOtherpayment());
 				
 			} else {
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last credit card payment >>>> " + newRegister.getCreditcardpayment());
 				newRegister.setCreditcardpayment(payAmount.doubleValue());
 				LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new credit card payment after subtracting payment  >>>> " + newRegister.getCreditcardpayment());
 			}
 			
 			 LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> last closing balance >>>> " + newRegister.getClosingbal() );
 			 
 			if (!pType.equalsIgnoreCase("other")) {				
 				newClosingBalance = new BigDecimal(newRegister.getClosingbal()).subtract(payAmount).setScale(2, RoundingMode.HALF_UP);
 				newRegister.setClosingbal(newClosingBalance.doubleValue());
 			}
 			
 			LeoLogger.info(">>>> saveRegisterForDeletePayment >>>> new closing balance after subtracting payment >>>> " + newRegister.getClosingbal() );
 		
 			registerRepo.save(newRegister);
 			LeoLogger.info(">>>> saveRegisterForDeletePayment >>>>  new register created  >>>> " + newRegister);
 		}
 	}
     
     private void addOrSubtractCustomerDeposit(  Boolean isDepositAdded ,  BigDecimal depositAmount , MemberUser customer) {
    	 
    	 BigDecimal customerOldDeposit = new BigDecimal(customer.getDeposit());
    	 BigDecimal customerNewDeposit;
    	 
    	 LeoLogger.info(" >>>> addOrSubtractCustomerDeposit >>>> Customer old deposit amount >>>> " + customerOldDeposit );
    	 
    	 if(isDepositAdded)
    		 customerNewDeposit = customerOldDeposit.add(depositAmount).setScale(2, RoundingMode.HALF_UP);
    	 else
    		 customerNewDeposit = customerOldDeposit.subtract(depositAmount).setScale(2, RoundingMode.HALF_UP);
    	 
    	 LeoLogger.info(" >>>> addOrSubtractCustomerDeposit >>>> Customer new deposit amount >>>> " + customerNewDeposit );
    	 
    		customer.setDeposit(customerNewDeposit.doubleValue());
			memberUserRepo.save(customer);
     }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultVO convertQuoteToSaleRefactored(Long quoteId) {

		ResultVO resonse = new ResultVO();

			QuotesEntity quote = quotesRepo.findByquotesId(quoteId);
			MemberUser customer = memberUserRepo.findById(quote.getMemberid());
			
			SalesEntity sale = new SalesEntity();
			SpecialSalesEntity specialsale = new SpecialSalesEntity();
			Boolean isSpecialSell ;
			Long saleOrSpecialSaleId = null;
			String saleOrSpecialSaleReferenceNo = null;
			BigDecimal saleOrSpecialSaleTotalAmount = null;
			
	
				setQuoteDataInSaleOrSpecialSale(quote, sale, specialsale);
				
				if(customer.getCtype().equalsIgnoreCase("Special")) {
					isSpecialSell = true;
					 saleOrSpecialSaleId = specialsale.getSaleId();
					 saleOrSpecialSaleReferenceNo = specialsale.getReferenceno();
				}
				else {
					 saleOrSpecialSaleId = sale.getSaleId();
					 saleOrSpecialSaleReferenceNo = sale.getReferenceno();
					isSpecialSell = false;
				}
		
				
				saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory(quote, customer, saleOrSpecialSaleId, isSpecialSell);
							
				 if(isSpecialSell) 					
					 saleOrSpecialSaleTotalAmount = new BigDecimal(specialsale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
				 else 
					 saleOrSpecialSaleTotalAmount = new BigDecimal(sale.getGrand_total()).setScale(2, RoundingMode.HALF_UP);
				 

				saveFinancialTransactionEntityForSaleAndSpecialSale(saleOrSpecialSaleId ,  saleOrSpecialSaleReferenceNo, saleOrSpecialSaleTotalAmount, customer);

				saveFTForSaleAndSpecialSale( saleOrSpecialSaleId , saleOrSpecialSaleReferenceNo, saleOrSpecialSaleTotalAmount, customer);
				    
			//	saveRegisterForSaleAndSpecialSale( saleOrSpecialSaleId , saleOrSpecialSaleTotalAmount);
				 saveSpecialSaleRegister(saleOrSpecialSaleTotalAmount);
				
				 if(isSpecialSell) 
					 applyRegisterhistoryspecial(specialsale); 
				 else
					 applyRegisterhistory(sale);
						
			    Double creditAmount =  new BigDecimal(customer.getCreditpayment()).setScale(2, RoundingMode.HALF_UP).doubleValue();
				LeoLogger.info(">>>> addSaleRefactored >>>> customer credit amount >>>> " + creditAmount );
				
				if (creditAmount != 0.0 && creditAmount > 0) 						
				addPaymentByCreditAmount(saleOrSpecialSaleId, isSpecialSell ,  customer ,  creditAmount)  ;	
				
				
				quote.setquotes_status("Converted");

				resonse.setMsgDescr("Quote with id "+quoteId +" and quote amount "+quote.getGrandtotal()+" converted to Sale Sucessfully!");
				resonse.setMsgCode("001");
				resonse.setError(false);
				return resonse;


	}
	
	
	     private void setQuoteDataInSaleOrSpecialSale(QuotesEntity quote, SalesEntity sale, SpecialSalesEntity specialSale ) {

	    	LeoLogger.info(" >>>> setQuoteDataInSaleOrSpecialSale >>>> " );
	    	/*
	    	Optional<SalesEntity> lastSale= salesRepo.findTopByOrderByDateDesc();
	    	LeoLogger.info(">>>> addSaleRefactored >>>> lastSale>>>> " + lastSale );
			 String refernceno="";
			 LeoLogger.info(">>>> addSaleRefactored >>>> lastSale>>>> " + lastSale );
			   
			    refernceno=lastSale.get().getReferenceno();
			    
			    
			    String saleNumber = refernceno.substring(refernceno.lastIndexOf("/") + 1);
			    
			    int salesNumber = Integer.parseInt(saleNumber); // Convert to integer
			    String newReferenceNo = String.format("%05d", salesNumber + 1); 
	    	 */
	        LocalDate currentDate = LocalDate.now();	    		  
	    	
	    	if(quote.getCtype().equalsIgnoreCase("Special")) {
	 		specialSale.setDate(new Date());
			specialSale.setMemberid(quote.getMemberid());
			specialSale.setMember_name(quote.getMember_name());
			specialSale.setCustomeraddress(quote.getCustomeraddress());
			specialSale.setPincode(quote.getPincode());
			specialSale.setPhonemain(quote.getPhonemain());
			specialSale.setMembername(quote.getMembername());		
			specialSale.setCtype(quote.getCtype());
			specialSale.setIsActive(0);
			specialSale.setNote(quote.getNote());		
			specialSale.setPaymentstatus("Due");
			specialSale.setSale_status("Due");
			specialSale.setOrder_discount(0);
			specialSale.setTotal_discount(0);
			specialSale.setOrder_tax(0);		
			specialsalesRepo.save(specialSale);
			specialSale.setReferenceno("SALE" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/" + specialSale.getSaleId() + "*");
			
	    	}else {
			
			sale.setDate(new Date());
			sale.setMemberid(quote.getMemberid());
			sale.setMember_name(quote.getMember_name());
			sale.setCustomeraddress(quote.getCustomeraddress());
			sale.setPincode(quote.getPincode());
			sale.setPhonemain(quote.getPhonemain());
			sale.setMembername(quote.getMembername());					
			sale.setCtype(quote.getCtype());
			sale.setIsActive(0);
			sale.setNote(quote.getNote());	
			sale.setPaymentstatus("Due");
			sale.setSale_status("Due");
			sale.setOrder_discount(0);
			sale.setTotal_discount(0);
			sale.setOrder_tax(0);
			salesRepo.save(sale);
			
			//sale.setReferenceno("SALE" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/" + (newReferenceNo));
			sale.setReferenceno("SALE" + currentDate.getYear() + "/" + currentDate.getMonthValue() + "/" + sale.getSaleId());	
	    	}
	    	 
	    	 
	     }
	     
	     private List<AddItemReqPojo>  saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory(QuotesEntity quote, MemberUser customer, Long saleOrSpecialSaleId, Boolean isSpecialSale) {
	    	 
	    	 

	  		  SalesEntity sale = null;
	  		  SpecialSalesEntity specialSale = null;
	  		  
	  	      if(isSpecialSale)          
	  	        specialSale = specialsalesRepo.findBySaleId(saleOrSpecialSaleId);
	  	      else
	  	        sale  = salesRepo.findBySaleId(saleOrSpecialSaleId);
	  	      
	  	      
	           List<QuotesItemEntity> quoteItemList = quotesItemRepo.findByQuotesidOrderByIdAsc(quote.getQuotesId());
	           LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> Quote item list >>>> "+quoteItemList);
	           List<AddItemReqPojo> productItemList = new ArrayList<>();
	           List<SalesItemEntity> saleItemList = new ArrayList<>();
	       	List<SpecialSalesItemEntity> specialSaleItemList = new ArrayList<>();

				String cType = customer.getCtype();
				String priceGroup = customer.getPricegroup();
				LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> customer price group >>>> " + priceGroup);
				
			//	SalesPercentageEntity salesPercentage = salespercentRepo.findByCtype(cType);
				SalesPercentageEntity salesPercentage = salespercentRepo.findByCtypeAndPricegroup(cType,priceGroup);
				LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> sales percentage >>>> " + salesPercentage);
				BigDecimal salesPercentageValue = new BigDecimal(salesPercentage.getPercentage()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
				ProductDetailsEntity product = null;
				UnitEntity unit = null;
				BigDecimal productUnitPrice = null;
				BigDecimal quoteItemQuantity = null;
				BigDecimal quoteSubTotalAmount = new BigDecimal(0.0);
				BigDecimal quoteTotalTaxAmount = new BigDecimal(0.0);	
				BigDecimal quoteItemSubTotalAmount = new BigDecimal(0.0);
				BigDecimal  productQuantity = new BigDecimal(0.0);
				SalesItemEntity salesItem = null;
				SpecialSalesItemEntity specialsaleItem = null;
	
				for (QuotesItemEntity quoteItem : quoteItemList) {
					
			        LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> Quote item  >>>> "+ quoteItem);

					unit = unitRepo.findById(quoteItem.getSale_item_id());
				    product = productDetailsRepo.findByProductId(quoteItem.getProduct_id());
				    
			        LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> unit>>>> " + unit);
												    
					LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product  >>>> " + product);
				   
					 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product id >>>> " + product.getProductId());
					 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product unit name >>>> " + unit.getUnitname());
					

					
					
					if (cType.equalsIgnoreCase("Special")
							&& customer.getPricegroup().equalsIgnoreCase("WholeSellers")) {

						if ((quoteItem.getRoll().equalsIgnoreCase("Piece")) || (quoteItem.getRoll().equalsIgnoreCase("ft")) || (quoteItem.getRoll().equalsIgnoreCase("10Ft")) || (quoteItem.getRoll().equalsIgnoreCase("Roll 100 Ft")) || (quoteItem.getRoll().equalsIgnoreCase("Box 50lb")) || (quoteItem.getRoll().equalsIgnoreCase("Box 55lb"))|| (quoteItem.getRoll().equalsIgnoreCase("Box lb"))) {
							productUnitPrice = product.getprice().setScale(2, RoundingMode.HALF_UP);
		
						}else if(quoteItem.getRoll().equalsIgnoreCase("20Ft")) {
							
							productUnitPrice = product.getprice().multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
						}
						else if (quoteItem.getRoll().equalsIgnoreCase("Box12")) {
							productUnitPrice = new BigDecimal(product.getrollprice()).setScale(2, RoundingMode.HALF_UP);
						
							if (salesPercentage.getPercentage() > 0) {
						
								productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
										.setScale(2, RoundingMode.HALF_UP);
							}

						} else {
							productUnitPrice = new BigDecimal(product.getrollprice()).setScale(2, RoundingMode.HALF_UP);
						
						}

					} else {
						if (salesPercentage.getPercentage() > 0) {
							 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> Special >>>> ");

							if ((quoteItem.getRoll().equalsIgnoreCase("Piece")) || (quoteItem.getRoll().equalsIgnoreCase("ft")) || (quoteItem.getRoll().equalsIgnoreCase("10Ft")) || (quoteItem.getRoll().equalsIgnoreCase("Roll 100 Ft")) || (quoteItem.getRoll().equalsIgnoreCase("Box 50lb")) || (quoteItem.getRoll().equalsIgnoreCase("Box 55lb"))|| (quoteItem.getRoll().equalsIgnoreCase("Box lb"))) {

								productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);
								LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>>product.getprice() special>>>> " +product.getprice());
								 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>>productUnitPrice special>>>> " +productUnitPrice);

							}else if(quoteItem.getRoll().equalsIgnoreCase("20Ft")) {

								productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);
								productUnitPrice = productUnitPrice.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);

							}
							else if (quoteItem.getRoll().equalsIgnoreCase("Box12")) {
		
									productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
											.setScale(2, RoundingMode.HALF_UP);

							} else {
								productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
										.setScale(2, RoundingMode.HALF_UP);					
							}

						

							if (product.getpromotion() > 0) {
								if (!cType.equalsIgnoreCase("WholeSellers")) {

							
									productUnitPrice =  productUnitPrice.subtract(productUnitPrice.multiply(new BigDecimal(product.getpromotion()).divide(new BigDecimal(100)) ))
											.setScale(2, RoundingMode.HALF_UP);	
								}
								
								if((quoteItem.getRoll().equalsIgnoreCase("20Ft"))){						
									
									productUnitPrice =  productUnitPrice.subtract(productUnitPrice.multiply(new BigDecimal(product.getpromotion()).divide(new BigDecimal(100)) ))
											.setScale(2, RoundingMode.HALF_UP);	
									productUnitPrice = productUnitPrice.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
									
								}
							}

						} else {
							if ((quoteItem.getRoll().equalsIgnoreCase("Piece")) || (quoteItem.getRoll().equalsIgnoreCase("ft")) || (quoteItem.getRoll().equalsIgnoreCase("10Ft")) || (quoteItem.getRoll().equalsIgnoreCase("Roll 100 Ft")) || (quoteItem.getRoll().equalsIgnoreCase("Box 50lb")) || (quoteItem.getRoll().equalsIgnoreCase("Box 55lb"))|| (quoteItem.getRoll().equalsIgnoreCase("lb")) || (quoteItem.getRoll().equalsIgnoreCase("Box lb"))) {

								productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);

							} else if(quoteItem.getRoll().equalsIgnoreCase("20Ft")) {
								productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);
								productUnitPrice = productUnitPrice.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
								
							}else if (quoteItem.getRoll().equalsIgnoreCase("Box12")) {
							
								if (salesPercentage.getPercentage() > 0) {
									productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
											.setScale(2, RoundingMode.HALF_UP);
								}

							} else {
								productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
										.setScale(2, RoundingMode.HALF_UP);	
							}

						}
					}
					
					LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product unit price >>>> " + productUnitPrice);


					quoteItemQuantity = quoteItem.getQuantity();
					
					 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product quantity >>>> " + quoteItemQuantity);

					if (quoteItem.getSale_item_id() == 3) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);
					} else if (quoteItem.getSale_item_id() == 2) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(328), RoundingMode.HALF_UP);
					} else if (quoteItem.getSale_item_id() == 4) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(164), RoundingMode.HALF_UP);
					}  else if (quoteItem.getSale_item_id() == 9) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
					}else if(quoteItem.getSale_item_id() == 14) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(39.30), RoundingMode.HALF_UP);
					}else if(quoteItem.getSale_item_id() == 15) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(35.30), RoundingMode.HALF_UP);
					}else if(quoteItem.getSale_item_id() == 16) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(29.00), RoundingMode.HALF_UP);
					}
					else if(quoteItem.getSale_item_id() == 17) {
						quoteItemQuantity = quoteItem.getQuantity().divide(BigDecimal.valueOf(33.17), RoundingMode.HALF_UP);
					}
					else {
						quoteItemQuantity = quoteItem.getQuantity();
					}
					
					LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product quantity after >>>> " + quoteItemQuantity);

					//if (!cType.equalsIgnoreCase("Special")) {
					
					
					////////// commenting below code due to now need to take latest product price only while converting to sale.//////////
					/*
						if (quoteItem.getIsPriceChange() == 1) {
							productUnitPrice = new BigDecimal(quoteItem.getReal_unit_price()).setScale(2,RoundingMode.HALF_UP);
							if (quote.getProduct_tax() != 0) {
								
								quoteTotalTaxAmount = quoteTotalTaxAmount.add(
									    productUnitPrice.multiply(BigDecimal.valueOf(0.125))
									                   .multiply(quoteItemQuantity)
									).setScale(2, RoundingMode.HALF_UP);
							} 
							if (customer.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
								quoteTotalTaxAmount = new BigDecimal(0);
							}
						} else {
							if (quote.getProduct_tax() != 0) {
								quoteTotalTaxAmount = quoteTotalTaxAmount.add(
									    productUnitPrice.multiply(BigDecimal.valueOf(0.125))
									                   .multiply(quoteItemQuantity)
									).setScale(2, RoundingMode.HALF_UP);
							} 

							if (customer.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
								quoteTotalTaxAmount = new BigDecimal(0);
							}
						}  */
					

					//} 
					/*else {
						if (quote.getProduct_tax() != 0) {
							productUnitPrice = new BigDecimal(quoteItem.getReal_unit_price()).setScale(2,RoundingMode.HALF_UP);
							quoteTotalTaxAmount = quoteTotalTaxAmount.add(productUnitPrice.multiply(new BigDecimal(0.125 *quoteItemQuantity))).setScale(2, RoundingMode.HALF_UP);
						}

					}*/
					
					
					if (quote.getProduct_tax() != 0) {
						quoteTotalTaxAmount = quoteTotalTaxAmount.add(
							    productUnitPrice.multiply(BigDecimal.valueOf(0.125))
							                   .multiply(quoteItemQuantity)
							).setScale(2, RoundingMode.HALF_UP);
					} 

					if (customer.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training") || cType.equalsIgnoreCase("Special") ) {
						quoteTotalTaxAmount = new BigDecimal(0);
					}
					
					
					////////// commenting below code due to now need to take latest product price only while converting to sale.//////////
					
					/*
					if (quoteItem.getIsPriceChange() == 1) {
						 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> PriceChange >>>> ");
						productUnitPrice = new BigDecimal(quoteItem.getReal_unit_price()).setScale(2,RoundingMode.HALF_UP);
						quoteItemSubTotalAmount = productUnitPrice.multiply((quoteItemQuantity)).setScale(2, RoundingMode.HALF_UP);
						quoteSubTotalAmount =  quoteSubTotalAmount.add(productUnitPrice.multiply(quoteItemQuantity)).setScale(2, RoundingMode.HALF_UP);
					} else {
						 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> PriceChange not changed>>>> ");
						 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> PriceChange not changed productUnitPrice>>>> "+productUnitPrice);
						 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> PriceChange not changed quoteItemQuantity>>>> "+quoteItemQuantity);
						quoteItemSubTotalAmount = productUnitPrice.multiply(quoteItemQuantity).setScale(2, RoundingMode.HALF_UP);
						 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> PriceChange not changed quoteItemSubTotalAmount>>>> "+quoteItemSubTotalAmount);
						quoteSubTotalAmount =  (quoteSubTotalAmount.add(productUnitPrice.multiply(quoteItemQuantity))).setScale(2, RoundingMode.HALF_UP);
					}  */
					
					quoteItemSubTotalAmount = productUnitPrice.multiply(quoteItemQuantity).setScale(2, RoundingMode.HALF_UP);
					 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> quoteItemSubTotalAmount>>>> "+quoteItemSubTotalAmount);
					quoteSubTotalAmount =  (quoteSubTotalAmount.add(productUnitPrice.multiply(quoteItemQuantity))).setScale(2, RoundingMode.HALF_UP);

			
					QuotesItemEntity quoteItemNew = new QuotesItemEntity();

					quoteItemNew.setQuotesid(saleOrSpecialSaleId);			
					quoteItemNew.setProduct_id(quoteItem.getProduct_id());
					quoteItemNew.setQuantity(quoteItem.getQuantity());
					quoteItemNew.setItem_tax(quoteItem.getItem_tax());
					quoteItemNew.setGst("12.5");
					quoteItemNew.setItem_discount(0);
					quoteItemNew.setProduct_code(quoteItem.getProduct_code());
					quoteItemNew.setProduct_name(quoteItem.getProduct_name());
					quoteItemNew.setReal_unit_price(quoteItem.getReal_unit_price());
					quoteItemNew.setSubtotal(quoteItem.getSubtotal());
					quoteItemNew.setTax(quoteItem.getTax());
					quoteItemNew.setRoll(quoteItem.getRoll());

					quotesItemRepo.save(quoteItemNew); 
					
					
					
					if (!cType.equalsIgnoreCase("Special")) {
						salesItem = new SalesItemEntity();
						salesItem.setSale_id(saleOrSpecialSaleId);
						salesItem.setProduct_id(quoteItem.getProduct_id());
						salesItem.setItem_tax(Double.valueOf(product.gettax_rate())  );
						salesItem.setGst("12.5");
						salesItem.setItem_discount(0d);
						salesItem.setProduct_code(quoteItem.getProduct_code());
						salesItem.setProduct_name(quoteItem.getProduct_name());
					//	salesItem.setSubtotal(quoteItem.getSubtotal());
						salesItem.setSubtotal(quoteItemSubTotalAmount.doubleValue());

						salesItem.setReal_unit_price(productUnitPrice.doubleValue());
						salesItem.setTax(String.valueOf(product.gettax_rate()));
						salesItem.setRoll(quoteItem.getRoll());
						salesItem.setSale_item_id(unit.getId());
						salesItem.setUnit_quantity(unit.getUnitname());
						salesItem.setCost(product.getcost());
						salesItem.setReturnqty("0");

						salesItemRepo.save(salesItem);
					

					
					} else {

					    specialsaleItem = new SpecialSalesItemEntity();
						specialsaleItem.setSaleid(saleOrSpecialSaleId);
						specialsaleItem.setProduct_id(quoteItem.getProduct_id());
						specialsaleItem.setItem_tax(Double.valueOf(product.gettax_rate())  );
						specialsaleItem.setGst("12.5");
						specialsaleItem.setItem_discount(0d);
						specialsaleItem.setProduct_code(quoteItem.getProduct_code());
						specialsaleItem.setProduct_name(quoteItem.getProduct_name());
						specialsaleItem.setSubtotal(quoteItemSubTotalAmount.doubleValue());
						specialsaleItem.setReal_unit_price(productUnitPrice.doubleValue());
						specialsaleItem.setTax(String.valueOf(product.gettax_rate()));
						specialsaleItem.setRoll(quoteItem.getRoll());
						specialsaleItem.setSale_item_id(unit.getId());
						specialsaleItem.setUnit_quantity(unit.getUnitname());
						specialsaleItem.setCost(product.getcost());
						specialsaleItem.setReturnqty("0");
						
						specialsalesItemRepo.save(specialsaleItem);

					}
					
					productQuantity = quoteItem.getQuantity();
					
					LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product old quantity >>>> " + product.getQuantity());
					
                  // updating inventory 
					if (!unit.getUnitname().equalsIgnoreCase("Piece") && !unit.getUnitname().equalsIgnoreCase("10Ft")&& !unit.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unit.getUnitname().equalsIgnoreCase("Roll 1000 Ft")) {
						BigDecimal qty = (quoteItem.getQuantity());
						BigDecimal unitqty =  (unit.getQuantity());
						BigDecimal quantity =  qty.divide(unitqty, 4, RoundingMode.HALF_UP);
					
						quantity = quantity .multiply(unitqty) ;
						productQuantity = quantity;
						product.setQuantity(product.getQuantity() .subtract((quantity)) );
					} else {
						product.setQuantity(product.getQuantity() .subtract((productQuantity)) );
					}
					productDetailsRepo.save(product);
					
					LeoLogger.info(" >>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> product new quantity >>>> " + product.getQuantity());
					
					
	              if (!cType.equalsIgnoreCase("Special")) {
	            		salesItem.setQuantity((productQuantity));	
	            		salesItemRepo.save(salesItem);
	            		
	              }
					else {						
						specialsaleItem.setQuantity((productQuantity));	
						specialsalesItemRepo.save(specialsaleItem);
					}

				

				}
				
				
				 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> total tax amount >>>> " + quoteTotalTaxAmount);
				 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>> sub total  amount >>>> " + quoteSubTotalAmount);

				BigDecimal saleOrSpecialSaleTotalAmount = quoteSubTotalAmount.add(quoteTotalTaxAmount).setScale(2, RoundingMode.HALF_UP);
				 LeoLogger.info(">>>> saveSaleAndSpecialSaleItemListForConvertQuoteToSaleAndUpdateInventory >>>>  total  amount >>>> " + saleOrSpecialSaleTotalAmount);
				if (!cType.equalsIgnoreCase("Special")) {					
					sale.setProduct_tax(quoteTotalTaxAmount.doubleValue());
					sale.setTotal_tax(quoteTotalTaxAmount.doubleValue());
					sale.setTotal(quoteSubTotalAmount.doubleValue());
					sale.setGrand_total(saleOrSpecialSaleTotalAmount.doubleValue());
					sale.setGrandtotal(saleOrSpecialSaleTotalAmount.toString());
					try {
					    sale.setFileName(customFileUploadUtil.savePdfFileIntoDir(saleItemList, sale));
					} catch (IOException e) {
					    e.printStackTrace(); 
					}
					salesRepo.save(sale);
					
					
				}else {
					specialSale.setTotal_tax(quoteTotalTaxAmount.doubleValue());
					specialSale.setProduct_tax(quoteTotalTaxAmount.doubleValue());
					specialSale.setTotal(quoteSubTotalAmount.doubleValue());
					specialSale.setGrand_total(saleOrSpecialSaleTotalAmount.doubleValue());
					try {
					 specialSale.setFileName(customFileUploadUtil.saveSpecialPdfFileIntoDir(specialSaleItemList, specialSale));
					} catch (IOException e) {
					    e.printStackTrace(); 
					}
					specialsalesRepo.save(specialSale);
				}
				
				
				
				return productItemList;
	    	 
	     }
	     
			@Override
			@Transactional(rollbackFor = Exception.class)
			public ResultVO addQuoteRefactored(List<AddItemReqPojo> productItemList) throws Exception {

				    ResultVO resonse = new ResultVO();
				
					MemberUser customer = memberUserRepo.findById(productItemList.get(0).getCustomerId());	
					QuotesEntity quote = new QuotesEntity();
				    quote.setCashName( productItemList.get(0).getCashName());
				    quote.setCashTin( productItemList.get(0).getCashTin());


					LeoLogger.info(">>>> addQuoteRefactored >>>> Member id >>>> " + customer.getId());
					LeoLogger.info(">>>> addQuoteRefactored >>>> Member type >>>> " + customer.getCtype());
					LeoLogger.info(">>>> addQuoteRefactored >>>> Member price group >>>> " + customer.getPricegroup());

					setQuoteData(customer, quote, productItemList.get(0).getNote());
					
					LeoLogger.info(">>>> addQuoteRefactored >>>> Saving Items >>>> " + productItemList.toString());
					setDataAndSaveQuoteItemList(productItemList, customer, quote);

					resonse.setMsgDescr("Quote with id "+quote.getQuotesId() +" and  quote amount "+quote.getGrandtotal()+" added Sucessfully!");
					resonse.setMsgCode("001");
					resonse.setError(false); 			
					return resonse;

			}
			

		@Override
		public ResultVO updateQuoteRefactored(List<AddItemReqPojo> productItemList) {

			ResultVO resonse = new ResultVO();

			try {

				Long quoteId = productItemList.get(0).getSaleId();
			
				MemberUser customer = memberUserRepo.findById(productItemList.get(0).getCustomerId());
				QuotesEntity quote = quotesRepo.findByquotesId(quoteId);
				List<QuotesItemEntity> quotesItemList = quotesItemRepo.findByQuotesid(quoteId);
				
				LeoLogger.info(">>>> updateQuoteRefactored >>>> Quote id >>>> " + quoteId);
				LeoLogger.info(">>>> updateQuoteRefactored >>>> Member id >>>> " + customer.getId());
				LeoLogger.info(">>>> updateQuoteRefactored >>>> Member type >>>> " + customer.getCtype());

				LeoLogger.info(">>>> updateQuoteRefactored >>>> removing old quote Item List  >>>> " + quotesItemList);
				
				quotesItemRepo.deleteAll(quotesItemList);

                setQuoteData(customer, quote, productItemList.get(0).getNote());
				
				setDataAndSaveQuoteItemList(productItemList, customer, quote);

				resonse.setMsgDescr("Quote with id "+quoteId +" and new quote amount "+quote.getGrandtotal()+" updated Sucessfully!");
				resonse.setMsgCode("001");
				resonse.setError(false);
				return resonse;

			} catch (Exception e) {
				LeoLogger.error(">>>> updateQuoteRefactored >>>> error >>>> " + e.getMessage());
				e.printStackTrace();
				resonse.setMsgDescr("Error occured please contact support team!");
				return resonse;
			}
			
		
		}


		
		private void setDataAndSaveQuoteItemList(List<AddItemReqPojo> productItemList , MemberUser customer, QuotesEntity quote)  {
				
			String cType = customer.getCtype();
			String priceGroup = customer.getPricegroup();
		//	SalesPercentageEntity salesPercentage = salespercentRepo.findByCtype(cType);
			SalesPercentageEntity salesPercentage = salespercentRepo.findByCtypeAndPricegroup(cType,priceGroup);
			BigDecimal salesPercentageValue = new BigDecimal(salesPercentage.getPercentage()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
			ProductDetailsEntity product = null;
			UnitEntity unit = null;
			BigDecimal productUnitPrice = null;
			BigDecimal productUnitPriceChange = null;
			BigDecimal productItemQuantity = null;
			BigDecimal quoteSubTotalAmount = new BigDecimal(0.0);
			BigDecimal quoteTotalTaxAmount = new BigDecimal(0.0);	
			BigDecimal quoteItemSubTotalAmount = new BigDecimal(0.0);
		
			 LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> sales percentage >>>> " + salesPercentage.getPercentage());
			 
			
			for (AddItemReqPojo productItem : productItemList) {

				 product = productDetailsRepo.findByProductId(productItem.getProductId());
				 unit = unitRepo.findById(Long.parseLong(productItem.getUnit()));
				 
				 LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> product id >>>> " + product.getProductId());
				 LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> product unit name >>>> " + unit.getUnitname());
				

				if (cType.equalsIgnoreCase("Special")
						&& customer.getPricegroup().equalsIgnoreCase("WholeSellers")) {

					if ((productItem.getRoll().equalsIgnoreCase("Piece"))
							|| (productItem.getRoll().equalsIgnoreCase("ft")) || (productItem.getRoll().equalsIgnoreCase("10Ft"))|| (productItem.getRoll().equalsIgnoreCase("Roll 100 Ft")) || (productItem.getRoll().equalsIgnoreCase("Box 50lb")) || (productItem.getRoll().equalsIgnoreCase("Box 55lb"))|| (productItem.getRoll().equalsIgnoreCase("lb"))) {
						productUnitPrice = product.getprice().setScale(2, RoundingMode.HALF_UP);
	
					}else if(productItem.getRoll().equalsIgnoreCase("20Ft")) {
						
						productUnitPrice = product.getprice().multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
					}
					else if (productItem.getRoll().equalsIgnoreCase("Box12")) {
						productUnitPrice = new BigDecimal(product.getrollprice()).setScale(2, RoundingMode.HALF_UP);
					
						if (salesPercentage.getPercentage() > 0) {
					
							productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
									.setScale(2, RoundingMode.HALF_UP);
						}

					} else {
						productUnitPrice = new BigDecimal(product.getrollprice()).setScale(2, RoundingMode.HALF_UP);
					
					}

				} else {
					if (salesPercentage.getPercentage() > 0) {
						 LeoLogger.info(">>>>>reached>>> product id >>>> " + product.getProductId());

						if ((productItem.getRoll().equalsIgnoreCase("Piece"))
								|| (productItem.getRoll().equalsIgnoreCase("ft")) || (productItem.getRoll().equalsIgnoreCase("10Ft"))|| (productItem.getRoll().equalsIgnoreCase("Roll 100 Ft")) || (productItem.getRoll().equalsIgnoreCase("Box 50lb")) || (productItem.getRoll().equalsIgnoreCase("Box 55lb"))|| (productItem.getRoll().equalsIgnoreCase("lb"))) {

							productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);

						}else if(productItem.getRoll().equalsIgnoreCase("20Ft")) {

							productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);
							productUnitPrice = productUnitPrice.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);

						}
						else if (productItem.getRoll().equalsIgnoreCase("Box12")) {
	
								productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
										.setScale(2, RoundingMode.HALF_UP);

						} else {
							productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
									.setScale(2, RoundingMode.HALF_UP);					
						}

					

						if (product.getpromotion() > 0) {
							if (!cType.equalsIgnoreCase("WholeSellers")) {

						
								productUnitPrice =  productUnitPrice.subtract(productUnitPrice.multiply(new BigDecimal(product.getpromotion()).divide(new BigDecimal(100)) ))
										.setScale(2, RoundingMode.HALF_UP);	
							}
							
							if((productItem.getRoll().equalsIgnoreCase("20Ft"))){						
								
								productUnitPrice =  productUnitPrice.subtract(productUnitPrice.multiply(new BigDecimal(product.getpromotion()).divide(new BigDecimal(100)) ))
										.setScale(2, RoundingMode.HALF_UP);	
								productUnitPrice = productUnitPrice.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
								
							}
						}

					} else {
						if ((productItem.getRoll().equalsIgnoreCase("Piece"))
								|| (productItem.getRoll().equalsIgnoreCase("ft")) || (productItem.getRoll().equalsIgnoreCase("10Ft")) || (productItem.getRoll().equalsIgnoreCase("Roll 100 Ft")) || (productItem.getRoll().equalsIgnoreCase("Box 50lb")) || (productItem.getRoll().equalsIgnoreCase("Box 55lb"))|| (productItem.getRoll().equalsIgnoreCase("lb"))) {

							productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);

						} else if(productItem.getRoll().equalsIgnoreCase("20Ft")) {
							productUnitPrice = product.getprice().add(product.getprice().multiply(salesPercentageValue)).setScale(2, RoundingMode.HALF_UP);
							productUnitPrice = productUnitPrice.multiply(new BigDecimal(2)).setScale(2, RoundingMode.HALF_UP);
							
						}else if (productItem.getRoll().equalsIgnoreCase("Box12")) {
						
							if (salesPercentage.getPercentage() > 0) {
								productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
										.setScale(2, RoundingMode.HALF_UP);
							}

						} else {
							productUnitPrice = new BigDecimal(product.getrollprice()).add(new BigDecimal(product.getrollprice()).multiply(salesPercentageValue))
									.setScale(2, RoundingMode.HALF_UP);	
						}

					}
				}
				
				
				 LeoLogger.info(" >>>> setDataAndSaveQuoteItemList >>>> product unit price after applying >>>> " + productUnitPrice);
		
				productItemQuantity = new BigDecimal(productItem.getQuantity());
				 LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> product quantity >>>> " + productItemQuantity);

				if (productItem.getIsPriceChange() == 1) {

					if (productItem.getTax().equalsIgnoreCase("YES")) {
						quoteTotalTaxAmount = quoteTotalTaxAmount.add(
							    productItem.getPrice()
							               .multiply(BigDecimal.valueOf(0.125))
							               .multiply(productItemQuantity)
							).setScale(2, RoundingMode.HALF_UP);
					} 

				} else {

					if (productItem.getTax().equalsIgnoreCase("YES")) {
						quoteTotalTaxAmount = quoteTotalTaxAmount.add(
							    productUnitPrice.multiply(BigDecimal.valueOf(0.125))
							                   .multiply(productItemQuantity)
							).setScale(2, RoundingMode.HALF_UP);
					} 

				}
				if (customer.getName().equalsIgnoreCase("Def. Infra. Org. Oper. Training")) {
					quoteTotalTaxAmount = new BigDecimal(0);
				}
			
				if (productItem.getIsPriceChange() == 1) {
					productUnitPriceChange = productItem.getPrice().setScale(2, RoundingMode.HALF_UP);
					
					quoteItemSubTotalAmount = productUnitPriceChange.multiply((productItemQuantity)).setScale(2, RoundingMode.HALF_UP);
					quoteSubTotalAmount =  quoteSubTotalAmount.add(productUnitPriceChange.multiply((productItemQuantity)) ).setScale(2, RoundingMode.HALF_UP);					
				} else {
					
					quoteItemSubTotalAmount = productUnitPrice.multiply((productItemQuantity)).setScale(2, RoundingMode.HALF_UP);									
					quoteSubTotalAmount =  quoteSubTotalAmount.add(productUnitPrice.multiply((productItemQuantity)) ).setScale(2, RoundingMode.HALF_UP);
			
				}

			

				QuotesItemEntity quotesItem = new QuotesItemEntity();
				quotesItem.setQuotesid(quote.getQuotesId());
				quotesItem.setProduct_id(productItem.getProductId());
				quotesItem.setItem_tax(productItem.getPrice().doubleValue() * 0.125);
				quotesItem.setGst("12.5");
				quotesItem.setItem_discount(0d);
				quotesItem.setProduct_code(productItem.getProductId().toString());
				quotesItem.setProduct_name(productItem.getProductName());
				quotesItem.setRoll(unit.getUnitname());
				quotesItem.setSale_item_id(unit.getId());

				quotesItem.setTax(Double.toString(quoteTotalTaxAmount.doubleValue()));
				quotesItem.setIsPriceChange(productItem.getIsPriceChange());

				if (productItem.getIsPriceChange() == 1) {
					quotesItem.setReal_unit_price(productUnitPriceChange.doubleValue());
				} else {

					quotesItem.setReal_unit_price(productUnitPrice.doubleValue());
				}

				quotesItem.setSubtotal(quoteItemSubTotalAmount.doubleValue());
				if (!unit.getUnitname().equalsIgnoreCase("Piece") && !unit.getUnitname().equalsIgnoreCase("10Ft")&& !unit.getUnitname().equalsIgnoreCase("Roll 66 Ft") && !unit.getUnitname().equalsIgnoreCase("Roll 1000 Ft")&& !unit.getUnitname().equalsIgnoreCase("lb")) {				
					quotesItem.setQuantity((productItemQuantity).multiply(unit.getQuantity()));

				} else {
					quotesItem.setQuantity(productItemQuantity);
				}

				quotesItemRepo.save(quotesItem);

			}
	
			 LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> quote total tax amount >>>> " + quoteTotalTaxAmount);
			 LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> quote sub total  amount >>>> " + quoteSubTotalAmount);
			quote.setProduct_tax(quoteTotalTaxAmount.doubleValue());
			quote.setTotal_tax(quoteTotalTaxAmount.doubleValue());
			quote.setTotal(quoteSubTotalAmount.doubleValue());
			

			BigDecimal quoteTotalAmount = quoteSubTotalAmount.add(quoteTotalTaxAmount).setScale(2, RoundingMode.HALF_UP);		
			quote.setGrandtotal(quoteTotalAmount.toString());
			
			LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> quote total  amount >>>> " + quoteTotalAmount);
			LeoLogger.info(">>>> setDataAndSaveQuoteItemList >>>> quote total  amount Grand_total>>>> " + quote.getGrandtotal());

			Date today = new Date();
			int year = today.getYear();
			int currentYear = year + 1900;
			int currentMonth = today.getMonth() + 1;
			quote.setReferenceno("QUOTES" + currentYear + "/" + currentMonth + "/" + quote.getQuotesId());
			quotesRepo.save(quote);
		
		}
    
		
		private void setQuoteData(MemberUser customer,QuotesEntity quote, String note) {
			
			try 
			{
				LeoLogger.info(">>>> setQuoteData >>>> ");
				quote.setOrder_tax(0);
				quote.setPayment_status("Due");
				quote.setOrder_discount(0);
				quote.setquotes_status("Available");
				quote.setCtype(customer.getCtype());
				quote.setDate(new Date());
				quote.setMemberid(customer.getId());
				quote.setMember_name(customer.getName());
				quote.setCustomeraddress(customer.getAddress());
				quote.setPincode(customer.getPincode());
				quote.setPhonemain(customer.getPhonemain());
				quote.setMembername(customer.getName());
				quote.setNote(note);
			    quotesRepo.save(quote);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		@Override
		public List<SaleItemPojo> getSaleItemListBySaleIdRefactored(Long saleId) {
			
			LeoLogger.info(">>>> getSaleItemListBySaleIdRefactored >>>> sale id >>>> "+saleId);		
			List<SaleItemPojo> salesItemDtoList = new ArrayList<SaleItemPojo>();
			try {
				
			List<SalesItemEntity> salesItemList = salesItemRepo.findBySaleidOrderByIdAsc(saleId);		
			SalesEntity sale = salesRepo.findBySaleId(saleId);
		
				for (SalesItemEntity saleItem : salesItemList) {
				
					SaleItemPojo saleItemDto = new SaleItemPojo();					
					saleItemDto.setReal_unit_price(saleItem.getReal_unit_price());
					saleItemDto.setRoll(saleItem.getRoll());
					saleItemDto.setSubtotal(saleItem.getSubtotal());
				
					saleItemDto.setProduct_name(saleItem.getProduct_name());
					saleItemDto.setSaleGrandTotal(sale.getGrand_total());
					saleItemDto.setSaleSubtotal(sale.getTotal());
					saleItemDto.setSaleTotalTax(sale.getTotal_tax());
				
					UnitEntity unit = unitRepo.findById(saleItem.getSale_item_id());
					BigDecimal saleItemQuantity = saleItem.getQuantity();
					BigDecimal unitqty =(unit.getQuantity());

					if (!saleItem.getRoll().equalsIgnoreCase("Piece")) {
						
						saleItemQuantity = saleItemQuantity.divide(unitqty, 4, RoundingMode.HALF_UP);
						saleItemDto.setQuantity((saleItemQuantity));
				
					} else {
						saleItemDto.setQuantity(saleItemQuantity);
					
					}

					salesItemDtoList.add(saleItemDto);
					
				}
				
				return salesItemDtoList;
				
			} catch (Exception e) {
				e.printStackTrace();
				LeoLogger.info(">>>> getSaleItemListBySaleIdRefactored >>>> error >>>> "+e.getMessage());	
				return salesItemDtoList;
			}
		
			
		
		}

		@Override
		public List<SpecialSalesItemPojo> getSpecialSaleItemListBySaleIdRefactored(Long saleId) {

			LeoLogger.info(">>>> getSpecialSaleItemListBySaleIdRefactored >>>> special sale id >>>> "+saleId);		
			List<SpecialSalesItemPojo> specialSaleItemDtoList = new ArrayList<SpecialSalesItemPojo>();
			try {
				
			List<SpecialSalesItemEntity> specialSaleItemList = specialsalesItemRepo.findBySaleidOrderByIdAsc(saleId);
			SpecialSalesEntity specialSale = specialsalesRepo.findBySaleId(saleId);
		
				for (SpecialSalesItemEntity specialSaleItem : specialSaleItemList) {
				
					SpecialSalesItemPojo specialSaleItemDto = new SpecialSalesItemPojo();					
					specialSaleItemDto.setReal_unit_price(specialSaleItem.getReal_unit_price());
					specialSaleItemDto.setRoll(specialSaleItem.getRoll());
					specialSaleItemDto.setSubtotal(specialSaleItem.getSubtotal());
					specialSaleItemDto.setQuantity(specialSaleItem.getQuantity());
					specialSaleItemDto.setProduct_name(specialSaleItem.getProduct_name());
					
					UnitEntity unit = unitRepo.findById(specialSaleItem.getSale_item_id());
					BigDecimal specialSaleItemQuantity = specialSaleItem.getQuantity();
					BigDecimal unitqty =(unit.getQuantity());

					if (!specialSaleItem.getRoll().equalsIgnoreCase("Piece")) {
						specialSaleItemQuantity =  specialSaleItemQuantity.divide(unitqty, 4, RoundingMode.HALF_UP);
						specialSaleItemDto.setQuantity(specialSaleItemQuantity);
				
					} else {
						specialSaleItemDto.setQuantity(specialSaleItemQuantity);
					
					}
				
					specialSaleItemDto.setSpecialSaleGrandTotal(specialSale.getGrand_total());
					specialSaleItemDto.setSpecialSaleSubtotal(specialSale.getTotal());
					specialSaleItemDto.setSpecialSaleTotalTax(specialSale.getTotal_tax());

					specialSaleItemDtoList.add(specialSaleItemDto);
					
				}
				
				return specialSaleItemDtoList;
				
			} catch (Exception e) {
				e.printStackTrace();
				LeoLogger.info(">>>> getSpecialSaleItemListBySaleIdRefactored >>>> error >>>> "+e.getMessage());	
				return specialSaleItemDtoList;
			}
		
			
		
		}

		@Override
		public List<RegisterhistoryPojo> getAmountList(String date) {
			List<RegisterhistoryEntity> registerEntityList = new ArrayList<RegisterhistoryEntity>();
			List<RegisterhistoryPojo> registerPojoList = new ArrayList<RegisterhistoryPojo>();
		
			LeoLogger.info("RegisterPojo---RegisterPojo -today--  " + date);
			Date currentDateAsDate = java.sql.Date.valueOf(date);
			try {
				LeoLogger.info("SaleServiceImpl---getRegisterList---in Register");
				 registerEntityList = registerhistoryRepo.findByDate(currentDateAsDate);
				for (RegisterhistoryEntity registerEntityEntityEntityRes : registerEntityList) {
	                 
					if (registerEntityEntityEntityRes.getCreditcardpayment() != 0) {
					    // Add other fields if needed
					    RegisterhistoryPojo registerhistoryPojo = new RegisterhistoryPojo();
					    registerhistoryPojo = mapper.map(registerEntityEntityEntityRes, RegisterhistoryPojo.class);
						registerPojoList.add(registerhistoryPojo);
					}
						
					   
					    LeoLogger.info("RegisterPojo---RegisterPojo -registerPojoList--  " + registerPojoList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return registerPojoList;
		}

		@Override
		public List<RegisterhistoryPojo> getchequeAmountList(String date) {
			List<RegisterhistoryEntity> registerEntityList = new ArrayList<RegisterhistoryEntity>();
			List<RegisterhistoryPojo> registerPojoList = new ArrayList<RegisterhistoryPojo>();
		
			LeoLogger.info("RegisterPojo---RegisterPojo -today--  " + date);
			Date currentDateAsDate = java.sql.Date.valueOf(date);
			try {
				LeoLogger.info("SaleServiceImpl---getRegisterList---in Register");
				 registerEntityList = registerhistoryRepo.findByDate(currentDateAsDate);
				for (RegisterhistoryEntity registerEntityEntityEntityRes : registerEntityList) {
	                 
					if (registerEntityEntityEntityRes.getChequepayment() != 0) {
					    // Add other fields if needed
					    RegisterhistoryPojo registerhistoryPojo = new RegisterhistoryPojo();
					    registerhistoryPojo = mapper.map(registerEntityEntityEntityRes, RegisterhistoryPojo.class);
						registerPojoList.add(registerhistoryPojo);
					}
						
					   
					    LeoLogger.info("RegisterPojo---RegisterPojo -getchequeAmountList--  " + registerPojoList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return registerPojoList;
		}

		@Override
		public List<RegisterhistoryPojo> getcashAmountList(String date) {
			List<RegisterhistoryEntity> registerEntityList = new ArrayList<RegisterhistoryEntity>();
			List<RegisterhistoryPojo> registerPojoList = new ArrayList<RegisterhistoryPojo>();
		
			LeoLogger.info("RegisterPojo---RegisterPojo -today--  " + date);
			Date currentDateAsDate = java.sql.Date.valueOf(date);
			try {
				LeoLogger.info("SaleServiceImpl---getRegisterList---in Register");
				 registerEntityList = registerhistoryRepo.findByDate(currentDateAsDate);
				for (RegisterhistoryEntity registerEntityEntityEntityRes : registerEntityList) {
	                 
					if (registerEntityEntityEntityRes.getCashpayment() != 0) {
					    // Add other fields if needed
					    RegisterhistoryPojo registerhistoryPojo = new RegisterhistoryPojo();
					    registerhistoryPojo = mapper.map(registerEntityEntityEntityRes, RegisterhistoryPojo.class);
						registerPojoList.add(registerhistoryPojo);
					}
						
					   
					    LeoLogger.info("RegisterPojo---RegisterPojo -getchequeAmountList--  " + registerPojoList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return registerPojoList;
		}

		@Override
		public List<RegisterhistoryPojo> getonlineAmountList(String date) {
			List<RegisterhistoryEntity> registerEntityList = new ArrayList<RegisterhistoryEntity>();
			List<RegisterhistoryPojo> registerPojoList = new ArrayList<RegisterhistoryPojo>();
		
			LeoLogger.info("RegisterPojo---RegisterPojo -today--  " + date);
			Date currentDateAsDate = java.sql.Date.valueOf(date);
			try {
				LeoLogger.info("SaleServiceImpl---getRegisterList---in Register");
				 registerEntityList = registerhistoryRepo.findByDate(currentDateAsDate);
				for (RegisterhistoryEntity registerEntityEntityEntityRes : registerEntityList) {
	                 
					if (registerEntityEntityEntityRes.getOnlinepayment() != 0) {
					    // Add other fields if needed
					    RegisterhistoryPojo registerhistoryPojo = new RegisterhistoryPojo();
					    registerhistoryPojo = mapper.map(registerEntityEntityEntityRes, RegisterhistoryPojo.class);
						registerPojoList.add(registerhistoryPojo);
					}
						
					   
					    LeoLogger.info("RegisterPojo---RegisterPojo -getchequeAmountList--  " + registerPojoList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return registerPojoList;
		}

		@Override
		public List<SpecialSalesPojo> getspecialSearch( String search) {
			List<SpecialSalesEntity> specialsalesEntityList = new ArrayList<SpecialSalesEntity>();
			List<SpecialSalesPojo> salePojoList = new ArrayList<SpecialSalesPojo>();
			
			
				
				specialsalesEntityList = specialsalesRepo
						.findByCtypeContainingOrReferencenoContainingOrMembernameContainingOrderBySaleIdDesc(search, search, search );
				// LeoLogger.info("SaleServiceImpl---getSalesList---specialsalesEntityList is :
				// "
				// + specialsalesEntityList.toString());
				// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " + endDate);
				// LeoLogger.info("SaleServiceImpl---getSalesList---End Date is : " +
				// startDate);

				for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

					SpecialSalesPojo ssalePojo = new SpecialSalesPojo();
					ssalePojo = mapper.map(specialsalesEntityRes, SpecialSalesPojo.class);
					salePojoList.add(ssalePojo);

				}

		
			return salePojoList;

		}

		

		@Override
		public ResultVO refundcreditamount(long memberId,String amount) {
			ResultVO resultVO = new ResultVO();
			MemberUser memberUser = memberUserRepo.findById(memberId);
			BigDecimal lastBalance = new BigDecimal(0.00);
             double creditamount=memberUser.getCreditpayment();
             
            		 memberUser.setCreditpayment(creditamount-Double.parseDouble(amount));
            		 FinancialTransactionEntity lastFinancialTransaction = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(memberUser.getId());
         			
         			FinancialTransactionEntity newFinancialTransaction = new FinancialTransactionEntity();				
         			newFinancialTransaction.setCustomerId(memberUser.getId());
         			newFinancialTransaction.setCustomerName(memberUser.getName());
         			newFinancialTransaction.setReferenceno("Refund Credit Amount");
         			newFinancialTransaction.setDate(new Date());
         			newFinancialTransaction.setDueDate(new Date());
         			newFinancialTransaction.setAmount(Double.parseDouble(amount));
         			newFinancialTransaction.setType("Refund Credit Amount");

         			if (lastFinancialTransaction == null) {
         				 LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  last balance FinancialTransaction >> " + lastFinancialTransaction.getBalance() );
         				newFinancialTransaction.setBalance(Double.parseDouble(amount));	
         				LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  new balance FinancialTransaction >>  " + newFinancialTransaction.getBalance() );
         			} else {
         			
         				 lastBalance = new BigDecimal(lastFinancialTransaction.getBalance()).setScale(2, RoundingMode.HALF_UP);					
         			    LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  last balance FinancialTransaction >> " + lastBalance );
         		
         				
         				newFinancialTransaction.setBalance(lastBalance.doubleValue());
         			}

         			financialTransactionsRepo.save(newFinancialTransaction);
         			
         			FTEntity lastFT = fTRepo.findTopByCustomerIdOrderByFanIdDesc(memberUser.getId());
         			
         			FTEntity newFT = new FTEntity();				
         			newFT.setCustomerId(memberUser.getId());
         			newFT.setCustomerName(memberUser.getName());
         			newFT.setReferenceno("Refund Credit Amount");
         			newFT.setDate(new Date());
         			newFT.setDueDate(new Date());
         			newFT.setAmount(Double.parseDouble(amount));
         			newFT.setType("Refund Credit Amount");

         			if (lastFT == null) {
         				 LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  last balance FinancialTransaction >> " + lastFT.getBalance() );
         				newFT.setBalance(Double.parseDouble(amount));	
         				LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  new balance FinancialTransaction >>  " + newFinancialTransaction.getBalance() );
         			} else {
         			
         				 lastBalance = new BigDecimal(lastFT.getBalance()).setScale(2, RoundingMode.HALF_UP);					
         			    LeoLogger.info(">>>> saveFinancialTransactionEntityForSaleAndSpecialSale >>>>  last balance FinancialTransaction >> " + lastBalance );
         		
         				
         			   newFT.setBalance(lastBalance.doubleValue());
         			}

         			fTRepo.save(newFT);
	 
							memberUserRepo.save(memberUser);
							resultVO.setMsgDescr(" Updated Sucessfully");
							resultVO.setMsgCode("001");
							resultVO.setError(false);
							return resultVO;
		}

		@Override
		public List<PaymentReportPojo> findPayemntReport() {
			LeoLogger.info("SalesServiceImpl ---salemonthReport");
			
			return salesRepo.findPaymentReport();
		}
		
		@Override
		public Page<PaymentReportPojo> findPayemntReport(
		        Date fromDate, Date toDate, int page, int size) {

		    String method = "findPayemntReport";

		    LeoLogger.info("[{}] Fetching payment report | fromDate: {}, toDate: {}, page: {}, size: {}",
		            method, fromDate, toDate, page, size);

		    Pageable pageable = PageRequest.of(page, size);

		    Page<Object[]> rawPage = salesRepo.findPaymentReportRaw(fromDate, toDate, pageable);

		    List<PaymentReportPojo> list = rawPage.getContent()
		            .stream()
		            .map(this::mapToPaymentReport)
		            .collect(Collectors.toList());

		    LeoLogger.info("[{}] Total records fetched: {}", method, rawPage.getTotalElements());

		    return new PageImpl<>(list, pageable, rawPage.getTotalElements());
		}
		
		@Override
		public List<PaymentReportPojo> getFullPaymentReport(Date fromDate, Date toDate) {

		    String method = "getFullPaymentReport";

		    List<Object[]> list = salesRepo.findPaymentReportFull(fromDate, toDate);

		    LeoLogger.info("[{}]  Raw records fetched from DB: {}", method, list.size());

		    List<PaymentReportPojo> result = list.stream()
		            .map(this::mapToPaymentReport)
		            .collect(Collectors.toList());

		    LeoLogger.info("[{}]  Mapping completed | Final records: {}", method, result.size());

		    return result;
		}
		
		private PaymentReportPojo mapToPaymentReport(Object[] row) {
		    return new PaymentReportPojo(
		        Long.valueOf(row[0].toString()),
		        Long.valueOf(row[1].toString()),
		        Double.valueOf(row[2].toString()),
		        (String) row[3],
		        Long.valueOf(row[4].toString()),
		        (String) row[5],
		        (Date) row[6]
		    );
		}

		@Override
		public PaymentPojo getPaymentbyId(long id) {
			PaymentEntity paymentEntity = paymentRepo.findById(id);
			LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  paymentEntity"+paymentEntity);
			//	LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId" + paymentEntityList);
				PaymentPojo paymentPojo = null;
				try {
					
					LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId");
					LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId"+id);
				
					  if (paymentEntity != null) {
				            paymentPojo = mapper.map(paymentEntity, PaymentPojo.class);
				        } else {
				            LeoLogger.info("SaleServiceImpl---getPaymentById--No payment found for ID: " + id);
				        }

					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return paymentPojo;
		}

		@Override
		public List<PaymentPojo> getPaymentListbyBulkId(long bulkid) {
			List<PaymentEntity> paymentEntityList = paymentRepo.findAllBybulkid(bulkid);
			//	LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId" + paymentEntityList);
				List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
				try {
					LeoLogger.info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId");
					for (PaymentEntity paymentEntityRes : paymentEntityList) {
						// if (paymentEntityRes.getBulkid() == 0) {

						PaymentPojo PaymentPojo = new PaymentPojo();
						PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
						paymentPojoList.add(PaymentPojo);
						/*
						 * LeoLogger.
						 * info("SaleServiceImpl---getPaymentListbyMemberId--in  getPaymentListbyMemberId"
						 * + paymentPojoList.toString());
						 */
						// }

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return paymentPojoList;
		}
		
		@Override
		public Page<SpecialSaleRegisterPojo> getSpecialSaleRegisterList(int page, int size) {

		    final String method = "getSpecialSaleRegisterList";


		    Pageable pageable = PageRequest.of(
		            page,
		            size,
		            Sort.by("id").descending()
		    );

		    Page<SpecialSaleRegister> registerPage =
		            specialSaleRegisterRepo.findAll(pageable);

		    Page<SpecialSaleRegisterPojo> pojoPage =
		            registerPage.map(this::convertToPojo);

		    LeoLogger.info("[{}] Records fetched | totalElements={} totalPages={}",
		            method,
		            pojoPage.getTotalElements(),
		            pojoPage.getTotalPages());

		    return pojoPage;
		}
		
		private SpecialSaleRegisterPojo convertToPojo(SpecialSaleRegister entity) {

		    SpecialSaleRegisterPojo pojo = new SpecialSaleRegisterPojo();

		    pojo.setId(entity.getId());
		    pojo.setRegisterDate(entity.getRegisterDate());
		    pojo.setCashPayment(entity.getCashPayment());
		    pojo.setOpeningBalance(entity.getOpeningBalance());
		    pojo.setSaleAmount(entity.getSaleAmount());
		    pojo.setClosingBalance(entity.getClosingBalance());

		    return pojo;
		}

		private void saveSpecialSaleRegister(BigDecimal saleTotalAmount) {

		    final String method = "saveSpecialSaleRegister";

		    LocalDate today = LocalDate.now();

		    LeoLogger.info("[{}] Processing special sale register | date={} saleAmount={}",
		            method, today, saleTotalAmount);

		    SpecialSaleRegister register =
		            specialSaleRegisterRepo.findByRegisterDate(today).orElse(null);

		    LeoLogger.info("[{}] Today's register fetched | register={}", method, register);

		    if (register != null) {

		        LeoLogger.info("[{}] Existing register found | currentSaleAmount={} currentClosingBalance={}",
		                method, register.getSaleAmount(), register.getClosingBalance());

		        // Update Sale Amount
		        BigDecimal newSaleAmount = register.getSaleAmount()
		                .add(saleTotalAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setSaleAmount(newSaleAmount);

		        LeoLogger.info("[{}] Sale amount updated | newSaleAmount={}",
		                method, newSaleAmount);

		        // Update Closing Balance
		        BigDecimal newClosingBalance = register.getClosingBalance()
		                .add(saleTotalAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setClosingBalance(newClosingBalance);

		        LeoLogger.info("[{}] Closing balance updated | newClosingBalance={}",
		                method, newClosingBalance);

		        specialSaleRegisterRepo.save(register);

		        LeoLogger.info("[{}] Register updated successfully | registerId={}",
		                method, register.getId());

		    } else {

		        LeoLogger.info("[{}] No register found for date | creating new register",
		                method);

		        SpecialSaleRegister newRegister = new SpecialSaleRegister();

		        newRegister.setRegisterDate(today);

		        BigDecimal openingBalance = new BigDecimal("1000.00");

		        newRegister.setOpeningBalance(openingBalance);

		        newRegister.setSaleAmount(saleTotalAmount);

		        newRegister.setCashPayment(BigDecimal.ZERO);

		        BigDecimal closingBalance = openingBalance
		                .add(saleTotalAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        newRegister.setClosingBalance(closingBalance);

		        specialSaleRegisterRepo.save(newRegister);

		        LeoLogger.info("[{}] New register created | openingBalance={} saleAmount={} closingBalance={}",
		                method, openingBalance, saleTotalAmount, closingBalance);
		    }
		}
		
		private void reverseSpecialSaleRegister(BigDecimal oldSaleAmount) {

		    final String method = "reverseSpecialSaleRegister";

		    LocalDate today = LocalDate.now();

		    LeoLogger.info("[{}] Reversing sale register | date={} oldSaleAmount={}",
		            method, today, oldSaleAmount);

		    SpecialSaleRegister register =
		            specialSaleRegisterRepo.findByRegisterDate(today).orElse(null);

		    LeoLogger.info("[{}] Today's register fetched | register={}", method, register);

		    if (register != null) {

		        LeoLogger.info("[{}] Register found | currentSaleAmount={} currentClosingBalance={}",
		                method, register.getSaleAmount(), register.getClosingBalance());

		        // Reverse Sale Amount
		        BigDecimal updatedSaleAmount = register.getSaleAmount()
		                .subtract(oldSaleAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setSaleAmount(updatedSaleAmount);

		        LeoLogger.info("[{}] Sale amount reversed | updatedSaleAmount={}",
		                method, updatedSaleAmount);

		        // Reverse Closing Balance
		        BigDecimal updatedClosingBalance = register.getClosingBalance()
		                .subtract(oldSaleAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setClosingBalance(updatedClosingBalance);

		        LeoLogger.info("[{}] Closing balance reversed | updatedClosingBalance={}",
		                method, updatedClosingBalance);

		        specialSaleRegisterRepo.save(register);

		        LeoLogger.info("[{}] Register reversed successfully | registerId={}",
		                method, register.getId());

		    } else {

		        LeoLogger.warn("[{}] No register found for today | date={}", method, today);
		    }
		}
		
		private void saveSpecialRegisterForPayment(BigDecimal grandTotal) {

		    final String method = "saveSpecialRegisterForPayment";

		    LocalDate today = LocalDate.now();

		    BigDecimal payAmount = grandTotal.setScale(2, RoundingMode.HALF_UP);

		    LeoLogger.info("[{}] Processing special register payment | date={} amount={}",
		            method, today, payAmount);

		    SpecialSaleRegister register =
		            specialSaleRegisterRepo.findByRegisterDate(today).orElse(null);

		    if (register != null) {

		        LeoLogger.info("[{}] Existing register found | currentCashPayment={} closingBalance={}",
		                method, register.getCashPayment(), register.getClosingBalance());

		        BigDecimal newCashPayment = register.getCashPayment()
		                .add(payAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setCashPayment(newCashPayment);

		        BigDecimal newClosingBalance = register.getClosingBalance()
		                .add(payAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setClosingBalance(newClosingBalance);

		        specialSaleRegisterRepo.save(register);

		        LeoLogger.info("[{}] Special register updated | newCashPayment={} newClosingBalance={}",
		                method, newCashPayment, newClosingBalance);

		    } else {

		        LeoLogger.info("[{}] No register found for today | creating new register", method);

		        SpecialSaleRegister newRegister = new SpecialSaleRegister();

		        BigDecimal openingBalance = new BigDecimal("1000.00");

		        newRegister.setRegisterDate(today);
		        newRegister.setOpeningBalance(openingBalance);
		        newRegister.setCashPayment(payAmount);

		        BigDecimal closingBalance = openingBalance
		                .add(payAmount)
		                .setScale(2, RoundingMode.HALF_UP);

		        newRegister.setClosingBalance(closingBalance);

		        specialSaleRegisterRepo.save(newRegister);

		        LeoLogger.info("[{}] New special register created | openingBalance={} closingBalance={}",
		                method, openingBalance, closingBalance);
		    }
		}
		
		private void saveSpecialRegisterForDeletePayment(BigDecimal payAmount) {

		    final String method = "saveSpecialRegisterForDeletePayment";

		    LocalDate today = LocalDate.now();

		    BigDecimal amount = payAmount.setScale(2, RoundingMode.HALF_UP);

		    LeoLogger.info("[{}] Processing special register delete payment | date={} amount={}",
		            method, today, amount);

		    SpecialSaleRegister register =
		            specialSaleRegisterRepo.findByRegisterDate(today).orElse(null);

		    if (register != null) {

		        LeoLogger.info("[{}] Current register | cashPayment={} closingBalance={}",
		                method, register.getCashPayment(), register.getClosingBalance());

		        BigDecimal newCashPayment = register.getCashPayment()
		                .subtract(amount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setCashPayment(newCashPayment);

		        BigDecimal newClosingBalance = register.getClosingBalance()
		                .subtract(amount)
		                .setScale(2, RoundingMode.HALF_UP);

		        register.setClosingBalance(newClosingBalance);

		        specialSaleRegisterRepo.save(register);

		        LeoLogger.info("[{}] Special register updated after delete | newCashPayment={} newClosingBalance={}",
		                method, newCashPayment, newClosingBalance);

		    } else {

		        LeoLogger.info("[{}] No special register found for today. Nothing to update.", method);
		    }
		}
		
		private void applySpecialRegisterHistoryPayment(PaymentEntity paymentEntity) {

		    final String method = "applySpecialRegisterHistoryPayment";

		    LeoLogger.info("[{}] START - Creating special register history entry", method);

		    SpecialSaleRegisterHistory history = new SpecialSaleRegisterHistory();

		    history.setRegisterDate(LocalDate.now());
		    history.setReferenceNo(paymentEntity.getReferenceno());

		    LeoLogger.info("[{}] Setting register date: {}", method, history.getRegisterDate());
		    LeoLogger.info("[{}] Setting reference no: {}", method, history.getReferenceNo());

		    BigDecimal cashAmount = new BigDecimal(paymentEntity.getGrand_total());

		    LeoLogger.info("[{}] Payment type: {}", method, paymentEntity.getPtype());
		    LeoLogger.info("[{}] Payment amount: {}", method, cashAmount);

		    history.setCashPayment(cashAmount);

		    LeoLogger.info("[{}] Saving special register history entry to database", method);

		    specialSaleRegisterHistoryRepository.save(history);

		    LeoLogger.info("[{}] SUCCESS - Special register history entry saved with reference no {}", 
		            method, history.getReferenceNo());
		}
		
		private void applySpecialRegisterHistoryBulkPayment(BulkPaymentEntity bulkPaymentEntity) {

		    final String method = "applySpecialRegisterHistoryBulkPayment";

		    LeoLogger.info("[{}] START - Creating special register history entry for BULK", method);

		    SpecialSaleRegisterHistory history = new SpecialSaleRegisterHistory();

		    history.setRegisterDate(LocalDate.now());
		    history.setReferenceNo(bulkPaymentEntity.getBulkId()+"");

		    LeoLogger.info("[{}] Setting register date: {}", method, history.getRegisterDate());
		    LeoLogger.info("[{}] Setting bulk id no: {}", method, history.getReferenceNo());

		    BigDecimal cashAmount = new BigDecimal(bulkPaymentEntity.getAmount());

		    LeoLogger.info("[{}] Payment amount: {}", method, cashAmount);

		    history.setCashPayment(cashAmount);

		    specialSaleRegisterHistoryRepository.save(history);

		    LeoLogger.info("[{}] SUCCESS - Special BULK register history saved | ref={}",
		            method, history.getReferenceNo());
		}
		
		private void deleteSpecialRegisterHistoryForPayment(PaymentEntity payment) {

		    final String method = "deleteSpecialRegisterHistoryForPayment";

		    List<SpecialSaleRegisterHistory> historyList =
		            specialSaleRegisterHistoryRepository.findByReferenceNo(payment.getReferenceno());

		    if (historyList.isEmpty()) {
		        LeoLogger.info("[{}] No special register history found for reference no {}. Nothing to delete.",
		                method, payment.getReferenceno());
		        return;
		    }

		    specialSaleRegisterHistoryRepository.deleteAll(historyList);

		    LeoLogger.info("[{}] Deleted {} special register history entries for PaymentId={}, RefNo={}",
		            method, historyList.size(), payment.getId(), payment.getReferenceno());
		}
		
		private void deleteSpecialRegisterHistoryForBulkPayment(BulkPaymentEntity bulkPayment) {

		    final String method = "deleteSpecialRegisterHistoryForBulkPayment";

		    List<SpecialSaleRegisterHistory> historyList =
		            specialSaleRegisterHistoryRepository.findByReferenceNo(bulkPayment.getBulkId()+"");

		    if (historyList == null || historyList.isEmpty()) {
		        LeoLogger.info("[{}] No special register history found for bulk reference no {}. Nothing to delete.",
		                method, bulkPayment.getBulkId());
		        return;
		    }

		    specialSaleRegisterHistoryRepository.deleteAll(historyList);

		    LeoLogger.info("[{}] Deleted {} special register history entries for BulkId={}",
		            method, historyList.size(), bulkPayment.getBulkId());
		}
		
	  }
