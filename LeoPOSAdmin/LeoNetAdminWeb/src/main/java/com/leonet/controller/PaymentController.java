/**
 * author MONINDER
 */
package com.leonet.controller;


import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.AddPaymentReqPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.constant.Action;
import com.leonet.service.CatagoryService;
import com.leonet.service.SaleService;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Controller
public class PaymentController {
	
	@Autowired
	CustomFileUploadUtil fileUploadUtil;

	@Autowired
	SaleService saleService;

	@Autowired
	CatagoryService addCatagoryService;
	
	@Autowired
	private com.leonet.service.SysAuditService sysAuditService;


	
	@PostMapping("/addPayment")
	@ResponseBody
	public ResultVO addPayment(HttpServletRequest request, @RequestBody AddPaymentReqPojo addPaymentReqPojo,
			  Model modelMap) {

		LeoLogger.info("Payment Controller --- addPayment---Add Payment Request Pojo JSON ....." + addPaymentReqPojo.toString());
		ResultVO resultVO = new ResultVO();		
		try {
		// ResultVO resultVO = saleService.addPayment(addPaymentReqPojo);
		 resultVO = saleService.addPaymentRefactored(addPaymentReqPojo);
		
	 } catch (Exception e) {
		LeoLogger.error(">>>> addPayment >>>> error >>>> " + e.getMessage());
		e.printStackTrace();
		resultVO.setError(true);
		resultVO.setMsgDescr("Error occured please contact support team!");		
	}
		

    return resultVO;

/*
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			sysAuditService.setSysAudit(Action.ADD_PAYMENT, resultVO.msgDescr);
			return "AddSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			sysAuditService.setSysAudit( Action.ADD_PAYMENT, resultVO.msgDescr);
			return "redirect:/viewSales";
		} */

	}
	
	@PostMapping("/deletePayment")
	public @ResponseBody ResultVO deletePayment(@RequestParam("id") long id) {
		LeoLogger.info("Payment Controller----delete Payment -- paymentid >>>> " + id);
		//ResultVO resultVO = saleService.deletePayment(id);
		ResultVO resultVO = saleService.deletePaymentRefactored(id);
		
		sysAuditService.setSysAudit( Action.DELETE_PAYMENT, resultVO.msgDescr);
		
		return resultVO;
	}
	
	@PostMapping("/deleteBulkPayment")
	public @ResponseBody ResultVO deleteBulkPayment(@RequestParam("id") long id) {
		LeoLogger.info("Payment Controller----delete Bulk Payment -- Bulk paymentid >>>> " + id);
		ResultVO resultVO = saleService.deleteBulkPayment(id);
		return resultVO;
	}


}
