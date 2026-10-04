package com.leonet.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.common.entity.FinancialTransactionEntity;
import com.leonet.common.pojo.AddPaymentReqPojo;
import com.leonet.common.pojo.BulkPaymentPojo;
import com.leonet.common.pojo.BulkSaleEmailPojo;
import com.leonet.common.pojo.FinancialTransactionPojo;
import com.leonet.common.pojo.PaymentPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.ReturnPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.SpecialSalesPojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.service.ReturnsService;
import com.leonet.service.SaleService;
import com.leonet.util.LeoLogger;

@Controller
public class BulkPaymentController {

	@Autowired
	SaleService saleService;
	
	@Autowired
	ReturnsService returnService;

	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@GetMapping("/getcustomerbymemberId")
	public String getcustomerbymemberId(ModelMap modelMap, @RequestParam("MemberId") long memberId,
			@RequestParam("Ctype") String Ctype) {
		double total = 0.0;
		double paymenttotal = 0.0;
		double creditpayment =0.0;
		double creditreturn = 0.0;
		double refund = 0.0;

		if (!Ctype.equalsIgnoreCase("Special")) {
			total = saleService.caltoatl(memberId);
			paymenttotal = saleService.paymenttoatl(memberId,Ctype);
			creditreturn =saleService.creditreturn(memberId);
			refund=saleService.refund(memberId);
			List<SalePojo> salesPojo = saleService.getSalesListbyMemberId(memberId);
			List<SalePojo> salesPojoo = saleService.getSalesListopenbalancebyMemberId(memberId);
			List<BulkPaymentPojo> bulkpaymentPojo = saleService.getBulkPaymentListbyMemberId(memberId);
			List<PaymentPojo> paymentPojo = saleService.getPaymentListOnlybyMemberId(memberId);
			List<PaymentPojo> crpaymentPojo = saleService.getCRPaymentListOnlybyMemberId(memberId);
			List<ReturnPojo> crrpaymentPojo = returnService.getReturnsListbyMemberId(memberId);
			//List<PaymentPojo> ftPojo = saleService.getPaymenttbyMemberId(memberId);
			//LeoLogger.info("BulkPayment Controller ---getcustomerbymemberId---ftEntity" +ftPojo);
		
			

	//		LeoLogger.info("BulkPayment Controller ---getcustomerbymemberId---salesPojo==" + salesPojo.toString());
			modelMap.addAttribute("salesPojo", salesPojo);
			modelMap.addAttribute("salesPojoo", salesPojoo);
			modelMap.addAttribute("bulkpaymentPojo", bulkpaymentPojo);
			modelMap.addAttribute("paymentPojo", paymentPojo);
			modelMap.addAttribute("crpaymentPojo", crpaymentPojo);
			modelMap.addAttribute("crrpaymentPojo", crrpaymentPojo);
			
			//modelMap.addAttribute("ftPojo", ftPojo);

		} else {
			// Need to give provision for Specail and fetch data from specail sales data
			total = saleService.specialcaltoatl(memberId);
			paymenttotal = saleService.specialpaymenttoatl(memberId,Ctype);
			creditreturn =saleService.specialcreditreturn(memberId);
			List<SpecialSalesPojo> specialsalesPojo = saleService.getSpecialSalesListbyMemberId(memberId);
			List<SpecialSalesPojo> specialsalesPojoo = saleService.getSpecialSalesListopenbalancebyMemberId(memberId);
			List<BulkPaymentPojo> bulkpaymentPojo = saleService.getBulkPaymentListbyMemberId(memberId);
			List<PaymentPojo> paymentPojo = saleService.getPaymentListOnlybyMemberId(memberId);
			List<PaymentPojo> crpaymentPojo = saleService.getCRPaymentListOnlybyMemberId(memberId);
			refund=saleService.refund(memberId);
			
		//	LeoLogger.info("BulkPayment Controller ---getcustomerbymemberId---salesPojo==" + specialsalesPojo.toString());
			modelMap.addAttribute("salesPojo", specialsalesPojo);
			modelMap.addAttribute("salesPojoo", specialsalesPojoo);
			modelMap.addAttribute("bulkpaymentPojo", bulkpaymentPojo);
			modelMap.addAttribute("paymentPojo", paymentPojo);
			modelMap.addAttribute("crpaymentPojo", crpaymentPojo);

		}
		UserRegistrationPojo memberPojo = saleService.getMemberByMemberid(memberId);
		LeoLogger.info("BulkPayment Controller ---getcustomerbymemberId---MemberPojo >>>>>>>>" + memberPojo.toString());
		creditpayment = memberPojo.getCreditpayment();
		BigDecimal bd_creditpayment = new BigDecimal(creditpayment);
		bd_creditpayment = bd_creditpayment.setScale(2, RoundingMode.HALF_UP);
		List<FinancialTransactionPojo> financialtransactionPojo = saleService
				.getFinancialTransactionByCustomerId(memberId);
		modelMap.addAttribute("financialtransactionPojo", financialtransactionPojo);
		

		modelMap.addAttribute("grand_total", total);
		modelMap.addAttribute("memberId", memberId);
		modelMap.addAttribute("payment_total", paymenttotal);
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("creditpayment", bd_creditpayment);
		modelMap.addAttribute("creditreturn", creditreturn);
		modelMap.addAttribute("refund", refund);

		double salepaid = 0.0;
		salepaid = saleService.saletoatl(memberId);
		modelMap.addAttribute("salepaid", salepaid);
		
		double paid = 0.0;
		paid = saleService.balance(memberId);
		modelMap.addAttribute("balance", paid);

		return "BulkPayment";

	}

	@PostMapping("/addBulkPayment")
	@ResponseBody
	public String addBulkPayment(HttpServletRequest request, @RequestBody AddPaymentReqPojo addPaymentReqPojo,
			Model modelMap) {

		LeoLogger.info("Bulk Payment Controller --- addBulkPayment--Add Payment Request Pojo JSON ....." + addPaymentReqPojo.toString());

		ResultVO resultVO = saleService.addBulkPayment(addPaymentReqPojo);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/viewSales";
		}

	}

	@GetMapping("/sale/sendmail/{id}")
	@ResponseBody
	public ResultVO sendMail(HttpServletRequest request, @PathVariable("id") long id, Model modelMap) {

		ResultVO resultVO = saleService.sendEmailForSale(id);
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		}
		return resultVO;

	}

	@PostMapping("/sale/{memberId}/bulkSendMail")
	@ResponseBody
	public ResultVO bulkSendMail(HttpServletRequest request, @PathVariable("memberId") Long memberId,
			@RequestBody List<BulkSaleEmailPojo> bulkSaleList, Model modelMap) {

		LeoLogger.info("BulkPayment Controller ---bulkSendMail----inside bulk send mail : " + bulkSaleList);
		ResultVO resultVO = saleService.bulkSendMail(memberId, bulkSaleList);
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		}
		return resultVO;

	}
	
	@PostMapping("/sale/{memberId}/{amount}/{note}/{ptype}/{pref}/bulkPaySelected")
	@ResponseBody
	public ResultVO bulkPaySelected(HttpServletRequest request, @PathVariable("memberId") Long memberId,@PathVariable("amount") Double amount,
			@PathVariable("note") String note,@PathVariable("ptype") String ptype,@PathVariable("pref") String pref,
			@RequestBody List<BulkSaleEmailPojo> bulkSaleList, Model modelMap) {

		LeoLogger.info("BulkPayment Controller --- Add Selected Bulk Payment ----inside bulk payment : " + bulkSaleList);
		LeoLogger.info("BulkPayment Controller --- Amount >>" +amount+ "  note >> " + note + "   ptype >> " + ptype +  "  pref >>" + pref);
		ResultVO resultVO = saleService.bulkPaySelected(memberId,amount,note,ptype,pref, bulkSaleList);
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		}
		return resultVO;

	}
	
	@PostMapping("/sale/{memberId}/{amount}/{note}/{ptype}/{pref}/applyCreditPayment")
	@ResponseBody
	public ResultVO applyCreditPayment(HttpServletRequest request, @PathVariable("memberId") Long memberId,@PathVariable("amount") Double amount,
			@PathVariable("note") String note,@PathVariable("ptype") String ptype,@PathVariable("pref") String pref,
			@RequestBody List<BulkSaleEmailPojo> bulkSaleList, Model modelMap) {

		LeoLogger.info("BulkPayment Controller --- Add Credit Amount as selected Bulk Payment ----inside bulk payment : " + bulkSaleList);
		LeoLogger.info("BulkPayment Controller --- Amount >>" +amount+ "  note >> " + note + "   ptype >> " + ptype +  "  pref >>" + pref);
		ResultVO resultVO = saleService.applyCreditPayment(memberId,amount,note,ptype,pref, bulkSaleList);
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		}
		return resultVO;

	}
	
	
	@GetMapping("/getpaymentbymemberId")
	public @ResponseBody List<PaymentPojo> getpaymentbymemberId(ModelMap modelMap, @RequestParam("MemberId") long memberId,
			@RequestParam("bulkId") long bulkId) {
		LeoLogger.info("Reached Controller");
		List<PaymentPojo> ftPojo = saleService.getPaymenttbyMemberId(memberId,bulkId);

		LeoLogger.info("BulkPayment Controller ---getpaymentbymemberId---paymentPojo==" + ftPojo.toString());
		modelMap.addAttribute("ftPojo", ftPojo);
		return ftPojo;
		
	}
	
	
	@PostMapping("/refundcreditamount")
	@ResponseBody
	public ResultVO refundcreditamount(ModelMap modelMap, @RequestParam("MemberId") long memberId,@RequestParam("Amount") String amount) {

		LeoLogger.info("Bulk Payment Controller --- refundcreditamount--....." + memberId);
		LeoLogger.info("Bulk Payment Controller --- refundcreditamount-amount-....." + amount);

		ResultVO resultVO = saleService.refundcreditamount(memberId,amount);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		}
		return resultVO;

	}
	

}
