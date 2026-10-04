/**
 * 
 */
package com.leonet.controller;

 
import java.io.IOException;

import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.leonet.common.pojo.AdminDetails;
import com.leonet.common.pojo.BarCodePojo;
import com.leonet.common.pojo.PlanPojo;
import com.leonet.common.pojo.ProductCategoryPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.ProductRentalPojo;
import com.leonet.common.pojo.ProductSubCategoryPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.pojo.VendorPojo;
import com.leonet.service.CatagoryService;
import com.leonet.util.BarcodeGenerator;
import com.leonet.util.FileUploadUtil;
import javax.servlet.http.HttpServletRequest;

/**
 * @author YOGESH
 *
 */
@Controller
public class CategoryController {
	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@Autowired
	FileUploadUtil fileUploadUtil;

	@Autowired
	CatagoryService addCatagoryService;

	@GetMapping(value = "/addCatagory")
	public String addCatagory(Model model) {
		return "AddCatagory";
	}

	@PostMapping("/addCatagory")
	public String addCatagory(@ModelAttribute("addCatagory") ProductCategoryPojo catagoryPojo, ModelMap modelMap) {
		ResultVO resultVO = addCatagoryService.addCatagory(catagoryPojo);
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddCatagory";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/getCategoryDetailsList";
		}

	}

	@GetMapping("/getCatagory")
	public @ResponseBody List<ProductCategoryPojo> getCatagoryList() {
		List<ProductCategoryPojo> catagoryPojo = addCatagoryService.getCatagoryList();
		System.out.println("catagoryPojo==" + catagoryPojo.toString());
		return catagoryPojo;
	}

	@GetMapping(value = "/addSubCatagory")
	public String AddSubCatagory(Model model) {
		return "AddSubCatagory";
	}

	@PostMapping("/addSubCatagory")
	public String addSubCatagory(@ModelAttribute("addSubCatagory") ProductSubCategoryPojo subCatagoryPojo,
			ModelMap modelMap) throws IOException {

		ResultVO resultVO = addCatagoryService.addSubCatagory(subCatagoryPojo);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddSubCatagory";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "ViewSubCategoryDetails";
		}

	}

	@GetMapping("/getSubCatagory")
	public @ResponseBody List<ProductSubCategoryPojo> getSubCatagory(@RequestParam("catagoryId") long catagoryId) {
		List<ProductSubCategoryPojo> subCatagoryPojo = addCatagoryService.getSubCatagory(catagoryId);
		System.out.println("subCatagoryPojo==" + subCatagoryPojo.toString());
		return subCatagoryPojo;
	}

	// vendor

	@GetMapping(value = "/addvendor")
	public String addvendor(Model model) {
		return "addvendor";
	}

	@PostMapping("/addvendor")
	public String addVendor(@ModelAttribute("addvendor") VendorPojo vendorPojo, ModelMap modelMap) {
		ResultVO resultVO = addCatagoryService.addvendor(vendorPojo);
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "addvendor";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/getVendorList";
		}
	}

//get
	@GetMapping(value = "/getVendorList")
	public String getVendorList(Model model) {
		List<VendorPojo> vendorPojo = addCatagoryService.getVendorList();
		System.out.println("vendorPojo==" + vendorPojo.toString());
		model.addAttribute("vendorPojo", vendorPojo);
		return "viewVendorList";
	}

	@GetMapping("/getVendor")
	public @ResponseBody List<VendorPojo> getVendorList() {
		List<VendorPojo> vendorPojo = addCatagoryService.getVendorList();
		System.out.println("vendorPojo==" + vendorPojo.toString());
		return vendorPojo;
	}
	
	@GetMapping("/getPlan")
	public @ResponseBody List<PlanPojo> getPlanList() {
		List<PlanPojo> planPojo = addCatagoryService.getPlanList();
		System.out.println("planPojo==" + planPojo.toString());
		return planPojo;
	}

	@PostMapping("/deleteVendorDetails")
	public @ResponseBody ResultVO deleteVendorDetails(@RequestParam("id") long id) {
		ResultVO resultVO = addCatagoryService.deleteVendorDetails(id);
		System.out.println("resultVO==" + resultVO.toString());
		return resultVO;
	}

	@GetMapping(value = "/addProductDetails")
	public String addProductDetails(Model model) {
		return "AddProductDetails";
	}

	@PostMapping("/addProductDetails")
	public String addProductDetails(@ModelAttribute("productDetails") ProductDetailsPojo productDetailsPojo,
		 ModelMap modelMap) throws IOException, URISyntaxException {
 	
		System.out.println("productDetailsPojo   "+productDetailsPojo.getcf1());
		ResultVO resultVO = new ResultVO();
		

		String uploadDir = imagesPath;
		resultVO = addCatagoryService.addProductDetails(productDetailsPojo);

	
		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "AddProductDetails";

	}

	@PostMapping("/updateProductDetails")
	public String updateProductDetails(@ModelAttribute("productDetails") ProductDetailsPojo productDetailsPojo,
			 ModelMap modelMap) throws IOException {

		System.out.println("productDetailsPojo   "+productDetailsPojo.getcf1() + productDetailsPojo.toString());

		ResultVO resultVO = new ResultVO();
	
	    resultVO = addCatagoryService.updateProductDetails(productDetailsPojo);
		
		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewProductDetails";
	}

	
	
	
	@GetMapping(value = "/viewProductDetails")
	public String viewProductDetails(ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsList();
		System.out.println("productDetailsPojo==" + productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductDetails";
	}
 
	@GetMapping("/getProductDetailsList")
	public String getProductDetailsList(ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsList();
		System.out.println("productDetailsPojo==" + productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductDetails";
	}

	@GetMapping("/getProductList")
	public @ResponseBody List<ProductDetailsPojo> getProductList(@RequestParam("subCatagoryId") long subCatagoryId,
			ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetails(subCatagoryId);
		System.out.println("productDetailsPojo==" + productDetailsPojo.toString());
		return productDetailsPojo;
	}

	@PostMapping("/deleteProductDetails")
	public @ResponseBody ResultVO deleteProductDetails(@RequestParam("productCode") String productCode) {
		ResultVO resultVO = addCatagoryService.deleteProductDetails(productCode);
		System.out.println("resultVO==" + resultVO.toString());
		return resultVO;
	}

	@GetMapping("/getCategoryDetailsList")
	public String getCategoryList(ModelMap modelMap) {
		List<ProductCategoryPojo> catagoryPojo = addCatagoryService.getCatagoryList();
		System.out.println("catagoryPojo==" + catagoryPojo.toString());
		modelMap.addAttribute("catagoryPojo", catagoryPojo);
		return "ViewCategoryDetails";
	}

	@PostMapping("/deleteCategoryDetails")
	public @ResponseBody ResultVO deleteCategoryDetails(@RequestParam("catCode") long catCode) {
		ResultVO resultVO = addCatagoryService.deleteCategoryDetails(catCode);
		System.out.println("resultVO==" + resultVO.toString());
		return resultVO;
	}

	@GetMapping(value = "/ViewSubCategoryDetails")
	public String ViewSubCategoryDetails(Model model) {
		return "ViewSubCategoryDetails";
	}

	@GetMapping("/getSubCategoryDetailsList")
	public String getSubCategoryDetailsList(@RequestParam("catagoryId") long catagoryId, ModelMap modelMap) {
		List<ProductSubCategoryPojo> subCatagoryPojo = addCatagoryService.getSubCategoryDetailsList(catagoryId);
		System.out.println("subCatagoryPojo==" + subCatagoryPojo.toString());
		modelMap.addAttribute("subCatagoryPojo", subCatagoryPojo);
		return "ViewSubCategoryDetails";
	}

	@PostMapping("/deleteSubCategoryDetails")
	public @ResponseBody ResultVO deleteSubCategoryDetails(@RequestParam("subCatCode") long subCatCode) {
		ResultVO resultVO = addCatagoryService.deleteSubCategoryDetails(subCatCode);
		System.out.println("resultVO==" + resultVO.toString());
		return resultVO;
	}

	@GetMapping(value = "/viewUserDetails")
	public String viewUserDetails(ModelMap modelMap) {
		List<AdminDetails> userDtlVO = addCatagoryService.getUserDetails();
		modelMap.addAttribute("userDtlVO", userDtlVO);
		return "UserDetails";
	}

	@GetMapping(value = "/pointManagent")
	public String productManagement(ModelMap modelMap) {
		/*
		 * List<UserDtlVO> userDtlVO = addCatagoryService.getUserDetails();
		 * modelMap.addAttribute("userDtlVO", userDtlVO);
		 */
		return "AddPointMgtDetails";
	}

	@GetMapping(value = "/barCodeGenerator")
	public String barCodeGenerator(Model model) {
		return "BarCodeGenerator";
	}

	@PostMapping("/addProductBarCodeDetails")
	public String addProductBarCodeDetails(@ModelAttribute("BarCodePojo") BarCodePojo barCodePojo, ModelMap modelMap)
			throws IOException {

		String productCode = barCodePojo.getProductCode();
		byte[] barcode = BarcodeGenerator.getBarCodeImage(productCode, 100, 100);
		System.out.println("barcode ==" + barcode);
		barCodePojo.setBarCodeImg(barcode);

		ResultVO resultVO = addCatagoryService.addProductBarCodeDetails(barCodePojo);
		if (resultVO.getMsgCode().equals("001")) {

			
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "BarCodeGenerator";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "ViewProductDetails";
		}
	}
	
	

	@GetMapping(value = "/viewBarCodeDetails")
	public String viewBarCodeDetails(Model model) {
		return "ViewBarCodeDetails";
	}

	@GetMapping("/getBarCodeList")
	public String getBarCodeList(@RequestParam("subCatagoryId") long subCatagoryId, ModelMap modelMap) {
		List<BarCodePojo> barCodePojo = addCatagoryService.getBarCodeList(subCatagoryId);
		System.out.println("barCodePojo==" + barCodePojo.toString());
		modelMap.addAttribute("barCodePojo", barCodePojo);
		return "ViewBarCodeDetails";
	}

	@PostMapping("/deleteBarCodeDetails")
	public @ResponseBody ResultVO deleteBarCodeDetails(@RequestParam("barCode") long barCode) {
		ResultVO resultVO = addCatagoryService.deleteBarCodeDetails(barCode);
		System.out.println("resultVO==" + resultVO.toString());
		return resultVO;
	}
	
	@GetMapping("/getItemCodeList")
	public String getItemCodeList(@RequestParam("itemCode") String itemCode, ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getItemCodeList(itemCode);
		System.out.println("productDetailsPojo==" + productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductDetails";
	}

	@GetMapping("/getItemNameList")
	public String getItemNameList(@RequestParam("itemName") String itemName, ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getItemNameList(itemName);
		System.out.println("productDetailsPojo==" + productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductDetails";
	}
	
	@GetMapping(value = "/viewMemberDetails")
	public String viewMemberDetails(ModelMap modelMap) {
		List<UserRegistrationPojo> memberDtlVO = addCatagoryService.getMemberDetails();
		List<PlanPojo> planPojo = addCatagoryService.getPlanList();
		modelMap.addAttribute("memberDtlVO", memberDtlVO);
		modelMap.addAttribute("planPojo", planPojo);
		return "MemberDetails";
	}
	
	@PostMapping("/updateMemberDetails")
	public String updateMemberDetails(@ModelAttribute("memberDetails") UserRegistrationPojo memberDetailsPojo, ModelMap modelMap) 
			throws IOException {

		System.out.println("memberDetailsPojo   "+memberDetailsPojo.getUserName());

		ResultVO resultVO = new ResultVO();
		resultVO = addCatagoryService.updateMemberDetails(memberDetailsPojo);
		
		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewMemberDetails";
	}
	
	@PostMapping("/purchaseMemberPlan")
	public String purchaseMemberPlan( ModelMap modelMap, HttpServletRequest request) 
			throws IOException {

	//	System.out.println("planPojo in Controller of Purchase plan  "+ planPojo.getUserName());
	//	System.out.println("planPojo :  "+ planPojo.getPlanName());

		String userName = request.getParameter("userName");
		String planId = request.getParameter("id");
		
		System.out.println("Username is  :  " + userName);
		System.out.println("Plan ID is  :  " + planId);
		
		PlanPojo planPojo = new PlanPojo();
		
		planPojo = addCatagoryService.getplanbyPlanid(planId);
		
		planPojo.setUserName(userName);
		ResultVO resultVO = new ResultVO();
		resultVO = addCatagoryService.purchasePlanDetails(planPojo);
		
		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewMemberDetails";
	}
	
	
}

