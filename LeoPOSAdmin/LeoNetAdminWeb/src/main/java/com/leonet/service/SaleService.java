/**
 * 
 */
package com.leonet.service;

import com.leonet.common.pojo.SalePojo;


import org.springframework.data.domain.Page;
import com.leonet.common.pojo.SaleReportSummaryPojo;
import com.leonet.common.pojo.SpecialSaleMonthReportPojo;
import com.leonet.common.pojo.SpecialSaleRegisterPojo;
import com.leonet.common.pojo.SpecialSalesItemPojo;
import com.leonet.common.pojo.SpecialSalesPojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SpecialSaleRegister;
import com.leonet.common.entity.SpecialSalesEntity;
import com.leonet.common.pojo.SaleItemPojo;
import com.leonet.common.pojo.SaleMonthReportPojo;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.ui.ModelMap;

import com.leonet.common.entity.FTEntity;
import com.leonet.common.entity.FinancialTransactionEntity;
import com.leonet.common.entity.RequestQuoteEntity;
import com.leonet.common.entity.RequestQuoteItemEntity;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.AddPaymentReqPojo;
import com.leonet.common.pojo.BulkPaymentPojo;
import com.leonet.common.pojo.BulkSaleEmailPojo;
import com.leonet.common.pojo.CustomerPurchasePojo;
import com.leonet.common.pojo.CustomerPurchaseSalePojo;
import com.leonet.common.pojo.CustomerPurchaseSpecialPojo;
import com.leonet.common.pojo.CustomerReportPojo;
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

/**
 * @author MONINDER
 *
 */
public interface SaleService {

	ResultVO addSale(List<AddItemReqPojo> addItemReqPojos);
	
	ResultVO addSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception;

	ResultVO updateSale(List<AddItemReqPojo> addItemReqPojos);
	
	ResultVO updateSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception ;

	ResultVO updateQuotes(List<AddItemReqPojo> addItemReqPojos);
	
	ResultVO updateQuoteRefactored(List<AddItemReqPojo> productItemList);
	
	ResultVO updaterequestQuote(List<AddItemReqPojo> addItemReqPojos);

	List<UserRegistrationPojo> getCustomerList();

	List<SalePojo> getSalesList();
	List<SalePojo> getSalesListNew(ModelMap modelMap,int page);
	
	List<SalePojo> getSalesListpage(ModelMap modelMap,int page,int pageSize);
	List<SalePojo> getSearch(String search);
	List<SpecialSalesPojo> getspecialSearch(String search);
	List<SalePojo> getSaleidList(String saleid);


	List<SalePojo> getPendingSalesBymemberId(String memberId);
	
	List<SpecialSalesPojo> getPendingSpecialSalesBymemberId(String memberId);

	List<RegisterPojo> getRegisterList();
	 Page<SpecialSaleRegisterPojo> getSpecialSaleRegisterList(int page, int size);
	List<RegisterhistoryPojo> getAmountList(String date);
	List<RegisterhistoryPojo> getchequeAmountList(String date);
	List<RegisterhistoryPojo> getcashAmountList(String date);
	List<RegisterhistoryPojo> getonlineAmountList(String date);

	ResultVO addRequestquote(List<AddItemReqPojo> addItemReqPojos);

	List<RequestQuotePojo> getRequestQuoteList();

	ResultVO downloadPDF(long id);

	ResultVO addQuotes(List<AddItemReqPojo> addItemReqPojos);
	
	ResultVO addQuoteRefactored(List<AddItemReqPojo> productItemList)  throws Exception;

	List<QuotesPojo> getQuotesList();
	List<QuotesPojo> getQuotesListNew(ModelMap modelMap,int page);
	List<QuotesPojo> getQuotesListpage(ModelMap modelMap,int page,int pageSize);
	List<QuotesPojo> getSearchQuotes(ModelMap modelMap,String search);

	List<SaleItemPojo> getSalesItembysaleId(String saleId, boolean isFromViewSalesReceipt);
	List<SpecialSalesItemPojo> getSpecailSalesItembysaleId(String saleId, boolean isFromViewSalesReceipt);
	
	List<SaleItemPojo> getSaleItemListBySaleIdRefactored(Long saleId);
	List<SpecialSalesItemPojo> getSpecialSaleItemListBySaleIdRefactored(Long saleId);
	
	List<QuotesItemPojo> getQuotesItembyquoteId(String quoteId, boolean isFromViewSalesReceipt);
	List<RequestQuoteItemEntity> getRequestQuotesItembyquoteId(String quoteId);

	List<QuotesItemPojo> getQuotesItembyquoteId(String quoteId);

	ResultVO addPayment(AddPaymentReqPojo addPaymentReqPojos);
	
	ResultVO addPaymentRefactored(AddPaymentReqPojo paymentDto);

	ResultVO addSpecialSale(List<AddItemReqPojo> addItemReqPojos);
	
	ResultVO addSpecialSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception ;

	List<SpecialSalesPojo> getsaleslist();

	List<SpecialSalesItemPojo> getSpecialSalesItembysaleId(String saleId);
	List<SpecialSalesPojo> getsaleslist(String saleid);

	List<FinancialTransactionEntity> getFtByCustomerId(long memberId);
	
	List<FinancialTransactionPojo> getFttByCustomerId(long memberId);
	

	List<RequestQuoteItemEntity> getrqByCustomerId(long memberId);

	List<SalesEntity> getSalesItembymemberId(long memberId);
	List<SpecialSalesEntity> getSpecialSalesItembymemberId(long memberId);

	double caltoatl(long memberId);
	
	double calgrandtoatl();
	double calqtoatl();
	double calptoatl();
	double calrtoatl();
	double calbtoatl();

	double specialcaltoatl(long memberId);

	double paymenttoatl(long memberId,String Ctype);
	double creditreturn(long memberId);
	double specialcreditreturn(long memberId);
	double refund(long memberId);
	
	double creditpayment(long memberId);

	double specialpaymenttoatl(long memberId,String Ctype);

	double saletoatl(long memberId);
	
	double balance(long memberId);

	List<SalePojo> getSalesListbyMemberId(long memberId);
	List<SalePojo> getSalesListopenbalancebyMemberId(long memberId);

	List<SpecialSalesPojo> getSpecialSalesListbyMemberId(long memberId);
	List<SpecialSalesPojo> getSpecialSalesListopenbalancebyMemberId(long memberId);

	ResultVO addBulkPayment(AddPaymentReqPojo addPaymentReqPojo);
	
	ResultVO refundcreditamount(long memberId,String amount);

	ResultVO converquotestToSale(String quoteId);
	
	ResultVO convertQuoteToSaleRefactored(Long quoteId);

	ResultVO closeregister(long rid);

	List<BulkPaymentPojo> getBulkPaymentListbyMemberId(long memberId);

	List<PaymentPojo> getPaymentListbyMemberId(long memberId);
	List<PaymentPojo> getPaymenttbyMemberId(long memberId,long bulkId);
	List<PaymentPojo> getPaymentListbySaleId(long SaleId,String ctype);
	PaymentPojo getPaymentbyId(long id);
	List<PaymentPojo> getPaymentListbyBulkId(long bulkid);
	List<PaymentPojo> getPaymentList();

	List<FinancialTransactionPojo> getFinancialTransactionByCustomerId(long memberId);

	ResultVO updateEditDetails(RequestQuotesItemsPojo requestQuotesItemsPojo);

	SalePojo getsalebySaleId(long id);
	
	QuotesPojo getquotesbyquotesId(long id);

	UserRegistrationPojo getMemberByMemberid(long memberid);

	List<SaleReportSummaryPojo> findAllSaleSummary(String startDate, String endDate);
	List<ReplenishmentRepotPojo> findReplenishmentRepotPojo(String startDate, String endDate);
	List<SaleMonthReportPojo> findSaleSummary(String startDate, String endDate);
	List<SpecialSaleMonthReportPojo> findSpecialSaleSummary(String startDate, String endDate);
	//List<SaleReportSummaryPojo> findAllSaleSummary(String startDate, String endDate);
	List<CustomerPurchasePojo> findAllCustomerPurchase(String startDate, String endDate);
	List<CustomerPurchaseSpecialPojo> findAllSpecialCustomerPurchase(String startDate, String endDate);
	
	List<CustomerReportPojo> findAllCustomerSummary();
	
	List<PaymentReportPojo> findPayemntReport();
	Page<PaymentReportPojo> findPayemntReport(
	        Date fromDate, Date toDate, int page, int size);
	
	List<PaymentReportPojo> getFullPaymentReport(Date fromDate, Date toDate);
	
	List<ProfitLossReportPojo> findAllProfitLossSummary(String startDate, String endDate);


	QuotesPojo getquotebyquotesId(long id);
	
	RequestQuotePojo getrequestquotebyquotesId(long id);

	RequestQuoteEntity findRequestQuoteById(Long requestQuoteId);

	ResultVO sendEmail(long id, String profile);
	ResultVO sendEmailR(long id, String profile,String customMessage);

	List<SalePojo> getSalesBymemberId(String memberId);
	

	ResultVO sendEmailForSale(long id);

	SalePojo getSpecialsalebySaleId(long id);
	
	

	ResultVO updateSpecialSales(List<AddItemReqPojo> addItemReqPojos);  
	
	ResultVO updateSpecialSaleRefactored(List<AddItemReqPojo> productItemList) throws Exception ;

	ResultVO bulkSendMail(Long memberId, List<BulkSaleEmailPojo> saleIdList);
	
	List<SaleReportSummaryPojo> findAllTotalSaleReport(String startDate, String endDate,String ctype);
	
	
	List<SaleReportSummaryPojo> findAllTotalSpecialSaleReport(String startDate, String endDate);
	
	List<FinancialTransactionEntity> findAllStatementSummary(long memberId ,String startDate, String endDate)throws ParseException;
	
	List<FTEntity> findAllStatementtSummary(long memberId ,String startDate, String endDate)throws ParseException;
	
	ResultVO deleteSale(long id);
	
	ResultVO deleteSaleRefactored(Long saleId);
	
	ResultVO deleteSpecialSale(long id);
	
	ResultVO deleteSpecialSaleRefactored(Long saleId);

	ResultVO bulkPaySelected(Long memberId, Double amount, String note, String ptype, String pref,
			List<BulkSaleEmailPojo> bulkSaleList);

	List<PaymentPojo> getPaymentListOnlybyMemberId(long memberId);
	List<PaymentPojo> getCRPaymentListOnlybyMemberId(long memberId);

	ResultVO applyCreditPayment(Long memberId, Double amount, String note, String ptype, String pref,
			List<BulkSaleEmailPojo> bulkSaleList);

	ResultVO deletePayment(long id);
	
	ResultVO deletePaymentRefactored(Long paymentId);

	ResultVO deleteBulkPayment(long bulkid);




}
