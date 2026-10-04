/**
 * 
 */
package com.leonet.service;


import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;

import java.util.List;

import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.RequestQuotePojo;
import com.leonet.common.pojo.ResultVO;
 

/**
 * @author MONINDER
 *
 */
public interface SaleService {


	ResultVO addSale(List<AddItemReqPojo> addItemReqPojos);

	List<UserRegistrationPojo> getCustomerList();

	List<SalePojo> getSalesList();

	ResultVO addRequestquote(List<AddItemReqPojo> addItemReqPojos);

	List<RequestQuotePojo> getRequestQuoteList();

	ResultVO downloadPDF(long id);
	
	ResultVO addQuotes(List<AddItemReqPojo> addItemReqPojos);
	
	List<QuotesPojo> getQuotesList();

	
 
}
