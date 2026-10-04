/**
 * author MONINDER
 */
package com.leonet.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.RquotePojo;
import com.leonet.constant.CommonConstant.ProfileConstant;
import com.leonet.service.SaleService;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Controller
public class RequestQuoteController {

	@Autowired
	SaleService saleService;

	@GetMapping("/sendmail/{id}")
	@ResponseBody
	public String sendMail(HttpServletRequest request, @PathVariable("id") long id,@RequestParam("customMessage") String customMessage, Model modelMap) {
		LeoLogger.info("requestQuotesController --- sendMail");
		LeoLogger.info("requestQuotesController --- sendMail customMessage "+customMessage);

		ResultVO resultVO = saleService.sendEmailR(id, ProfileConstant.REQUEST_QUOTE.getProfile(),customMessage);

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
		return "redirect:/viewRequestQuote";

	}

}
