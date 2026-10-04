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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.AddPaymentReqPojo;
import com.leonet.common.pojo.BulkPaymentPojo;
import com.leonet.common.pojo.FinancialTransactionPojo;
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
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.service.RentalService;
import com.leonet.service.SaleService;
import com.leonet.util.FileUploadUtil;




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
	FileUploadUtil fileUploadUtil;

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

		System.out.println("productRentalPojo   " + productRentalPojo.toString());
		ResultVO resultVO = new ResultVO();

		resultVO = rentalService.addProductRental(productRentalPojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "AddProductRental";

	}

	@GetMapping(value = "/viewProductRental")
	public String viewProductRental(ModelMap modelMap) {
		List<ProductRentalPojo> productRentalPojo = rentalService.getProductRentalList();
		System.out.println("productRentalPojo==" + productRentalPojo.toString());
		modelMap.addAttribute("productRentalPojo", productRentalPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductRental";
	}
	@GetMapping(value = "/images/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		System.out.println("Catagory Controller downloadImageFromFileSystem1 ");
		System.out.print("************inside  downloadImageFromFileSystem1******** ");

		byte[] imageData = rentalService.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);

	}

	@PostMapping("/updateProductRentalDetails")
	public String updateProductRentalDetails(@ModelAttribute("productRental") ProductRentalPojo productRentalPojo,HttpServletRequest request,
			 ModelMap modelMap) throws IOException {

		System.out.println(" productRentalPojo   "+productRentalPojo.getRproductId() + productRentalPojo.toString());

		ResultVO resultVO = new ResultVO();
	
	    resultVO = rentalService.updateProductRentalDetails(productRentalPojo,request);
		
		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewProductRental";
	}

	@GetMapping(value = "/addRentalQuote")
	public String addRentalQuote(Model model) {
		return "AddRentalQuote";
	}

	@GetMapping("/getRentalProducts")
	public @ResponseBody List<ProductRentalPojo> getProducts() {
		List<ProductRentalPojo> productRentalPojo = rentalService.getProductRentalAll();
		System.out.println("productRentalPojo==" + productRentalPojo.toString());
		return productRentalPojo;
	}

	@PostMapping("/addRentalQuote")
	@ResponseBody
	public String addRentalQuote(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		System.out.println("Rental Controller --- Add item Request Pojo JSON ....." + addItemReqPojos.toString());
	

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
		System.out.println("rquotePojo==" + rquotePojo.toString());
		modelMap.addAttribute("rquotePojo", rquotePojo);

		return "ListRentalQuotes";
	}
	
	
	
	
	@PostMapping("/convertToSale")
	public String convertToSale(ModelMap modelMap, @RequestParam("rquoteId") String rquoteId) {

		System.out.println("*********** rquoteId==" + rquoteId);

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
	@GetMapping(value = "/addRentalSale")
	public String addRentalSale(Model model) {
		return "AddRentalSales";
	}
	@PostMapping("/addRentalSale")
	@ResponseBody
	public String addRentalSale(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		System.out.println("Rental Controller --- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = rentalService.addRentalsale(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddRentalSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/AddRentalSales";
		}

	}

	@GetMapping(value = "/listRentalSales")
	public String listRentalSales(ModelMap modelMap) {
		List<RsalePojo> rsalePojo = rentalService.getRentalSaleList();
		System.out.println("rsalePojo==" + rsalePojo.toString());
		modelMap.addAttribute("rsalePojo", rsalePojo);

		return "ListRentalSales";
	}
	
	//@GetMapping(value = "/listRentalQuotesItem")
	@GetMapping("/getRquoteitembyrquoteId")
	public @ResponseBody List<RquoteItemPojo> getRquoteitembyrquoteId(ModelMap modelMap, @RequestParam("rquoteId") String rquoteId) {
			List<RquoteItemPojo> rquoteItemListPojo = rentalService.getRentalQuotesItembyRqId(rquoteId);
			System.out.println("rquoteItemListPojo==" + rquoteItemListPojo.toString());
			modelMap.addAttribute("rquoteItemListPojo", rquoteItemListPojo.toString());
			return rquoteItemListPojo;
		}

	@GetMapping("/getRsalesitembyrsaleId")
	public @ResponseBody List<RsalesItemPojo>  getRquoteitembyrsaleId(ModelMap modelMap, @RequestParam("rsaleId") String rsaleId) {
		List<RsalesItemPojo> rsalesItemListPojo = rentalService.getRentalSalesItembyRsId(rsaleId);
			System.out.println("rsalesItemListPojo==" + rsalesItemListPojo.toString());
			modelMap.addAttribute("rsalesItemListPojo", rsalesItemListPojo.toString());
			return rsalesItemListPojo;
		}
	
	@PostMapping("/addPayment")
	@ResponseBody
	public String addPayment(HttpServletRequest request, @RequestBody AddPaymentReqPojo addPaymentReqPojo,
			  Model modelMap) {

		System.out.println("Sales Controller --- Add Payment Request Pojo JSON ....." + addPaymentReqPojo.toString());

		ResultVO resultVO = rentalService.addPayment(addPaymentReqPojo);

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
			return "redirect:/listRentalSales";
		}

	}
	
	@GetMapping("/getcustomerbymemberId")
	public String getcustomerbymemberId(ModelMap modelMap, @RequestParam("MemberId") long memberId)
	{
		double total =0.0;
		total= rentalService.caltoatl(memberId);
		modelMap.addAttribute("grand_total",total);
		modelMap.addAttribute("memberId",memberId);
		
		double paymenttotal=0.0;
		paymenttotal =rentalService.paymenttoatl(memberId);
		modelMap.addAttribute("payment_total",paymenttotal);
		
		double salepaid = 0.0;
		salepaid = rentalService.rsaletoatl(memberId);
		modelMap.addAttribute("salepaid",salepaid);
		
	
		
		List<RsalePojo> rsalesPojo = rentalService.getRsalesListbyMemberId(memberId);
		
		List<PaymentPojo> paymentPojo = rentalService.getPaymentListbyMemberId(memberId);
		UserRegistrationPojo memberPojo = rentalService.getMemberByMemberid(memberId);
		
		System.out.println("rsalesPojo==" + rsalesPojo.toString());

		System.out.println("paymentPojo==" + paymentPojo.toString());
		modelMap.addAttribute("rsalesPojo", rsalesPojo);
		
	   modelMap.addAttribute("paymentPojo", paymentPojo);
	   modelMap.addAttribute("memberPojo", memberPojo);
		
		
		return "CustomerReport";
		
	}
	
	@GetMapping("/getPaymentsaleid")
	public @ResponseBody List<PaymentPojo> paymentPojoo(ModelMap modelMap, @RequestParam("SaleId") long saleId) {
		
		List<PaymentPojo> paymentPojo = rentalService.getPaymentListbySaleId(saleId);

		
		System.out.println("paymentPojo==" + paymentPojo.toString());
		// customerPojo.toString());
		return paymentPojo;
	}
	@GetMapping(value = "/editRentalQuotes")
	public String editRentalQuotes(Model model, @RequestParam("id") String id, ModelMap modelMap) {
		
		System.out.println("id==" + id);
		RquotePojo quotesPojo = rentalService.getquotebyquotesId(id);
		// LeoLogger.info("Sales Controller ---editQuotes--QuotesPojoo >>>>>>>>" +
		// quotesPojo.toString());

		UserRegistrationPojo memberPojo = rentalService.getMemberByMemberid(quotesPojo.getMember_id());
		// LeoLogger.info("Sales Controller ---editQuotes---MemberPojo >>>>>>>>" +
		// memberPojo.toString());

		List<RquoteItemPojo> quotesItemList = rentalService.getRentalQuotesItembyRqId(id);
		// LeoLogger.info("Sales Controller ---editQuotes--quotesItemList >>>>>>>>" +
		// quotesItemList.toString());
		System.out.println("quotesPojo==" + quotesPojo);

		modelMap.addAttribute("quotesPojo", quotesPojo);
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("quotesItemList", quotesItemList);

		return "EditRentalQuotes";
	}
	@PostMapping("/updateRentalQuotes")
	@ResponseBody
	public ResultVO updateRentalQuotes(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

	

		ResultVO resultVO = rentalService.updateRentalQuotes(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			// return "EditQuotes";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			// return "redirect:/editQuotes";
		}

		return resultVO;

	}
	@GetMapping(value = "/editRentalSales")
	public String editRentalSales(Model model, @RequestParam("id") String id, ModelMap modelMap) {
		
		System.out.println("id==" + id);
		RsalePojo salesPojo = rentalService.getsalebyrsaleId(id);
		System.out.println("memberid==" + salesPojo.getMemberid());
		// LeoLogger.info("Sales Controller ---editQuotes--QuotesPojoo >>>>>>>>" +
		// quotesPojo.toString());

		UserRegistrationPojo memberPojo = rentalService.getMemberByMemberid(salesPojo.getMemberid());
		// LeoLogger.info("Sales Controller ---editQuotes---MemberPojo >>>>>>>>" +
		// memberPojo.toString());

		List<RsalesItemPojo> salesItemList = rentalService.getRentalSalesItembyRsId(id);
		// LeoLogger.info("Sales Controller ---editQuotes--quotesItemList >>>>>>>>" +
		// quotesItemList.toString());
		System.out.println("salesPojo==" + salesPojo);

		modelMap.addAttribute("salesPojo", salesPojo);
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("salesItemList", salesItemList);

		return "EditRentalSales";
	}
	@PostMapping("/updateRentalSales")
	@ResponseBody
	public ResultVO updateRentalSales(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

	

		ResultVO resultVO = rentalService.updateRentalSales(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			// return "EditQuotes";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			// return "redirect:/editQuotes";
		}

		return resultVO;

	}
	
	

	

}
