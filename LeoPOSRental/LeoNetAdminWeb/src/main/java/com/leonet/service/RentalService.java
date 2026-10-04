/**
 * 
 */
package com.leonet.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.AddPaymentReqPojo;
import com.leonet.common.pojo.PaymentPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.ProductRentalPojo;
import com.leonet.common.pojo.QuotesItemPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.RquoteItemPojo;
import com.leonet.common.pojo.RquotePojo;
import com.leonet.common.pojo.RsalePojo;
import com.leonet.common.pojo.RsalesItemPojo;
import com.leonet.common.pojo.SaleItemPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;

/**
 * @author Moninder
 *
 */
public interface RentalService {

	

	ResultVO addProductRental(ProductRentalPojo productRentalPojo);

	List<ProductRentalPojo> getProductRentalList();

	ResultVO deleteProductRental(String productCode);

	List<ProductRentalPojo> getProductRental(long subCatagoryId);

	ResultVO updateProductRental(ProductRentalPojo productRentalPojo);

	List<ProductRentalPojo> getProductRentalAll();

	ResultVO addRentalquote(List<AddItemReqPojo> addItemReqPojos);
	ResultVO addRentalsale(List<AddItemReqPojo> addItemReqPojos);

	List<RquotePojo> getRentalQuotesList();

	ResultVO convertToSale(String rquoteId);
	

	List<RsalePojo> getRentalSaleList();
	List<RquoteItemPojo> getRentalQuotesItemList();
	
	List<RquoteItemPojo> getRentalQuotesItembyRqId(String rquoteId);
	List<RsalesItemPojo>  getRentalSalesItembyRsId(String rsaleId);
	
	
	ResultVO updateProductRentalDetails(ProductRentalPojo productRentalPojo,HttpServletRequest request);
	ResultVO addPayment(AddPaymentReqPojo addPaymentReqPojos);
	
	List<RsalePojo> getRsalesListbyMemberId(long memberId);
	List<PaymentPojo> getPaymentListbyMemberId(long memberId);
	List<PaymentPojo> getPaymentListbySaleId(long SaleId);
	UserRegistrationPojo getMemberByMemberid(long memberid);
	RquotePojo getquotebyquotesId(String id);
	ResultVO updateRentalQuotes(List<AddItemReqPojo> addItemReqPojos);
	RsalePojo getsalebyrsaleId(String id);
	ResultVO updateRentalSales(List<AddItemReqPojo> addItemReqPojos);
	
	double caltoatl(long memberId);
	double paymenttoatl(long memberId);
	double rsaletoatl(long memberId);
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException;

	

 
}
