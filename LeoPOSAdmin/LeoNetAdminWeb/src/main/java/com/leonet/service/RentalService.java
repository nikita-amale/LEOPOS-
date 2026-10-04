/**
 * 
 */
package com.leonet.service;

import java.util.List;

import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ProductRentalPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.RquoteItemPojo;
import com.leonet.common.pojo.RquotePojo;
import com.leonet.common.pojo.RsalePojo;
import com.leonet.common.pojo.RsalesItemPojo;
import com.leonet.common.pojo.SaleItemPojo;

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

	List<RquotePojo> getRentalQuotesList();

	ResultVO convertToSale(String rquoteId);

	List<RsalePojo> getRentalSaleList();
	List<RquoteItemPojo> getRentalQuotesItemList();
	
	List<RquoteItemPojo> getRentalQuotesItembyRqId(String rquoteId);
	List<RsalesItemPojo>  getRentalSalesItembyRsId(String rsaleId);
	

 
}
