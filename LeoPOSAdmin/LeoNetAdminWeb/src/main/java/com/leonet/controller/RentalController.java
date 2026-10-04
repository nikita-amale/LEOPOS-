/**
 * author MONINDER
 */
package com.leonet.controller;

import java.io.IOException;


import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ProductRentalPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.RquoteItemPojo;
import com.leonet.common.pojo.RquotePojo;
import com.leonet.common.pojo.RsalePojo;
import com.leonet.common.pojo.RsalesItemPojo;
import com.leonet.service.RentalService;
import com.leonet.service.SaleService;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Controller
public class RentalController {
	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@Autowired
	CustomFileUploadUtil fileUploadUtil;

	@Autowired
	SaleService saleService;

	@Autowired
	RentalService rentalService;

	@GetMapping(value = "/addProductRental")
	public String addProductRental(Model model) {
		return "AddProductRental";
	}

	@PostMapping("/addProductRental")
	public String addProductRental(@ModelAttribute("productRental") ProductRentalPojo productRentalPojo,
			ModelMap modelMap) throws IOException, URISyntaxException {

		LeoLogger.info("Rental Controller ---addProductRental--- productRentalPojo   " + productRentalPojo.toString());
		ResultVO resultVO = new ResultVO();

		resultVO = rentalService.addProductRental(productRentalPojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "AddProductRental";

	}

	@GetMapping(value = "/viewProductRental")
	public String viewProductRental(ModelMap modelMap) {
		List<ProductRentalPojo> productRentalPojo = rentalService.getProductRentalList();
		LeoLogger.info("Rental Controller ---viewProductRental----productRentalPojo==" + productRentalPojo.toString());
		modelMap.addAttribute("productRentalPojo", productRentalPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductRental";
	}

	@GetMapping(value = "/addRentalQuote")
	public String addRentalQuote(Model model) {
		return "AddRentalQuote";
	}

	@GetMapping("/getRentalProducts")
	public @ResponseBody List<ProductRentalPojo> getProducts() {
		List<ProductRentalPojo> productRentalPojo = rentalService.getProductRentalAll();
		LeoLogger.info("Rental Controller ---getRentalProducts---productRentalPojo==" + productRentalPojo.toString());
		return productRentalPojo;
	}

	@PostMapping("/addRentalQuote")
	@ResponseBody
	public String addRentalQuote(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info("Rental Controller ---addRentalQuote-- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = rentalService.addRentalquote(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddRentalQuote";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/AddRentalQuote";
		}

	}

	@GetMapping(value = "/listRentalQuotes")
	public String listRentalQuotes(ModelMap modelMap) {
		List<RquotePojo> rquotePojo = rentalService.getRentalQuotesList();
		LeoLogger.info("Rental Controller ---listRentalQuotes---rquotePojo==" + rquotePojo.toString());
		modelMap.addAttribute("rquotePojo", rquotePojo);

		return "ListRentalQuotes";
	}
	
	
	
	
	@PostMapping("/convertToSale")
	public String convertToSale(ModelMap modelMap, @RequestParam("rquoteId") String rquoteId) {

		LeoLogger.info("Rental Controller ---convertToSale*********** rquoteId==" + rquoteId);

		ResultVO resultVO = rentalService.convertToSale(rquoteId);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "ListRentalSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/ListRentalQuotes";
		}

	}

	@GetMapping(value = "/listRentalSales")
	public String listRentalSales(ModelMap modelMap) {
		List<RsalePojo> rsalePojo = rentalService.getRentalSaleList();
		LeoLogger.info("Rental Controller ---listRentalSales--rsalePojo==" + rsalePojo.toString());
		modelMap.addAttribute("rsalePojo", rsalePojo);

		return "ListRentalSales";
	}
	
	//@GetMapping(value = "/listRentalQuotesItem")
	@GetMapping("/getRquoteitembyrquoteId")
	public @ResponseBody List<RquoteItemPojo> getRquoteitembyrquoteId(ModelMap modelMap, @RequestParam("rquoteId") String rquoteId) {
			List<RquoteItemPojo> rquoteItemListPojo = rentalService.getRentalQuotesItembyRqId(rquoteId);
			LeoLogger.info("Rental Controller ---getRquoteitembyrquoteId---rquoteItemListPojo==" + rquoteItemListPojo.toString());
			modelMap.addAttribute("rquoteItemListPojo", rquoteItemListPojo.toString());
			return rquoteItemListPojo;
		}

	@GetMapping("/getRsalesitembyrsaleId")
	public @ResponseBody List<RsalesItemPojo>  getRquoteitembyrsaleId(ModelMap modelMap, @RequestParam("rsaleId") String rsaleId) {
		List<RsalesItemPojo> rsalesItemListPojo = rentalService.getRentalSalesItembyRsId(rsaleId);
			LeoLogger.info("Rental Controller ---getRsalesitembyrsaleId---rsalesItemListPojo==" + rsalesItemListPojo.toString());
			modelMap.addAttribute("rsalesItemListPojo", rsalesItemListPojo.toString());
			return rsalesItemListPojo;
		}
	

}
