/**
 * 
 */
package com.leonet.service;


import com.leonet.entity.ReturnsEntity;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.ReturnCashItemPojo;
import com.leonet.common.pojo.ReturnCashPojo;
import com.leonet.common.pojo.ReturnItemPojo;
import com.leonet.common.pojo.ReturnPojo;

import com.leonet.common.pojo.UserRegistrationPojo;
 

/**
 * @author MONINDER
 *
 */
public interface ReturnsService {


	ResultVO addReturn(List<AddItemReqPojo> addItemReqPojos,long applyReturn);
	ResultVO addReturnCash(List<AddItemReqPojo> addItemReqPojos);

	List<ReturnPojo> getReturnsList();
	List<ReturnPojo> getReturnsListNew(ModelMap modelMap,int page);
	List<ReturnPojo> getReturnsListpage(ModelMap modelMap,int page,int pageSize);
	List<ReturnPojo> getSearchReturn(ModelMap modelMap,String search);
	
	List<ReturnCashPojo> getReturnscashList();
	List<ReturnCashPojo> getReturnscashListNew(ModelMap modelMap,int page);
	List<ReturnCashPojo> getReturnscashListpage(ModelMap modelMap,int page,int pageSize);
	List<ReturnCashPojo> getSearchReturncash(ModelMap modelMap,String search);

	List<ReturnItemPojo> getReturnItembyreturnId(String returnId);
	List<ReturnCashItemPojo> getReturnItemCashbyreturnId(String returnId);
	

	List<ReturnsEntity> getReturnItembymemberId(long memberId);

	List<ReturnPojo> getReturnsListbyMemberId(long memberId);
	
	ReturnPojo getreturnbyreturnId(String returnId);
	ReturnCashPojo getreturncashbyreturnId(String returnId);
	UserRegistrationPojo getMemberByMemberid(long memberid);
	ResultVO updateReturns(List<AddItemReqPojo> addItemReqPojos,double saleid);
	ResultVO updateReturnsCash(List<AddItemReqPojo> addItemReqPojos,double saleid);
	ResultVO deleteReturn(long id);
	ResultVO deleteReturnCash(long id);
	ResultVO updateAllReturn(List<AddItemReqPojo> addItemReqPojos,long returnId,long applyReturn,boolean isOldCashReturn);
	ResultVO newDeleteReturn(long id);

		
}
