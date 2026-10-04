/**
 * author MONINDER
 */
package com.leonet.controller;

import java.io.IOException;

import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.RequestQuotePojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.service.CatagoryService;
import com.leonet.service.PlanService;
import com.leonet.service.SaleService;
import com.leonet.util.FileUploadUtil;

/**
 * @author MONINDER
 *
 */
@Controller
public class SalesController {
	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@Autowired
	FileUploadUtil fileUploadUtil;

	@Autowired
	SaleService saleService;

	@Autowired
	CatagoryService addCatagoryService;

	@GetMapping(value = "/addSales")
	public String addSales(Model model) {
		return "AddSales";
	}

	@PostMapping("/addSales")
	@ResponseBody
	public String addSales(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			  Model modelMap) {

		System.out.println("Sales Controller --- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = saleService.addSale(addItemReqPojos);

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
			return "redirect:/addSales";
		}

	}

	@GetMapping("/getCustomer")
	public @ResponseBody List<UserRegistrationPojo> getCustomerList() {
		List<UserRegistrationPojo> customerPojo = saleService.getCustomerList();
		System.out.println("customerPojo==" + customerPojo.toString());
		return customerPojo;
	}

	@GetMapping("/getProducts")
	public @ResponseBody List<ProductDetailsPojo> getProducts() {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsAll();
	//	System.out.println("productDetailsPojo==" + productDetailsPojo.toString());
		return productDetailsPojo;
	}
	
	@GetMapping(value = "/viewSales")
	public String viewSales(ModelMap modelMap) {
		List<SalePojo> salesPojo = saleService.getSalesList();
		System.out.println("salesPojo==" + salesPojo.toString());
		modelMap.addAttribute("salesPojo", salesPojo);
		
		return "ViewSales";
	}
	
	@GetMapping(value = "/requestQuote")
	public String requestQuote(Model model) {
		return "RequestQuote";
	}
	
	@PostMapping("/requestQuote")
	@ResponseBody
	public String requestQuote(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			  Model modelMap) {

		System.out.println("Sales Controller --- Add Request Quote Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = saleService.addRequestquote(addItemReqPojos);

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
			return "redirect:/requestQuote";
		}

	}
	
	@GetMapping(value = "/viewRequestQuote")
	public String viewRequestQuote(ModelMap modelMap) {
		List<RequestQuotePojo> requestQuotePojo = saleService.getRequestQuoteList();
		System.out.println("requestQuotePojo==" + requestQuotePojo.toString());
		modelMap.addAttribute("requestQuotePojo", requestQuotePojo);
		
		return "ViewRequestQuote";
	}
	
	@PostMapping("/downloadPDF")
	public @ResponseBody ResultVO downloadPDF(@RequestParam("rqCode") long id) {
		
		ResultVO resultVO = saleService.downloadPDF(id);
		System.out.println("rqId==" + String.valueOf(id));
		System.out.println("resultVO==" + resultVO.toString());
		return resultVO;
	}
	
	@GetMapping(value = "/addQuotes")
	public String addQuotes(Model model) {
		return "AddQuotes";
	}
	
	@GetMapping(value = "/viewQuotes")
	public String viewQuotes(ModelMap modelMap) {
		List<QuotesPojo> quotesPojo = saleService.getQuotesList();
		System.out.println("quotesPojo==" + quotesPojo.toString());
		modelMap.addAttribute("quotesPojo", quotesPojo);
		
		return "ViewQuotes";
	}
	
	@PostMapping("/addQuotes")
	@ResponseBody
	public String addQuotes(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			  Model modelMap) {

		System.out.println("Sales Controller --- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = saleService.addQuotes(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddQuotes";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/addQuotes";
		}

	}


 


}
