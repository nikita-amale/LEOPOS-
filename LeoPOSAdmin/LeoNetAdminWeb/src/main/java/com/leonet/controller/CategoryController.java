/**
 * 
 */
package com.leonet.controller;

import java.io.BufferedReader;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.pojo.AdminDetails;
import com.leonet.common.pojo.BarCodePojo;
import com.leonet.common.pojo.PlanPojo;
import com.leonet.common.pojo.ProductCategoryPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.ProductSubCategoryPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.pojo.VendorPojo;
import com.leonet.service.CatagoryService;
import com.leonet.service.ProductService;
import com.leonet.util.BarcodeGenerator;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.LeoLogger;

import org.springframework.util.StringUtils;

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
	CustomFileUploadUtil fileUploadUtil;

	@Autowired
	CatagoryService addCatagoryService;

	@Autowired
	private ProductService productService;
	
	 
	
    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON serialization


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
		LeoLogger.info("Catagory Controller ---getCatagory---catagoryPojo==" + catagoryPojo.toString());
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
		LeoLogger.info("Catagory Controller---getSubCatagory--subCatagoryPojo==" + subCatagoryPojo.toString());
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
		LeoLogger.info("Catagory Controller---getVendorList--vendorPojo==" + vendorPojo.toString());
		model.addAttribute("vendorPojo", vendorPojo);
		return "viewVendorList";
	}

	@GetMapping("/getVendor")
	public @ResponseBody List<VendorPojo> getVendorList() {
		List<VendorPojo> vendorPojo = addCatagoryService.getVendorList();
		LeoLogger.info("Catagory Controller---getVendor--vendorPojo==" + vendorPojo.toString());
		return vendorPojo;
	}

	@GetMapping("/getPlan")
	public @ResponseBody List<PlanPojo> getPlanList() {
		List<PlanPojo> planPojo = addCatagoryService.getPlanList();
		LeoLogger.info("Catagory Controller---getPlan--planPojo==" + planPojo.toString());
		return planPojo;
	}

	@PostMapping("/deleteVendorDetails")
	public @ResponseBody ResultVO deleteVendorDetails(@RequestParam("id") long id) {
		ResultVO resultVO = addCatagoryService.deleteVendorDetails(id);
		LeoLogger.info("Catagory Controller---deleteVendorDetails--resultVO==" + resultVO.toString());
		return resultVO;
	}
	@GetMapping(value = "/addProduct")
	public String addProduct(Model model) {
		return "AddProducts";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("productDetails") ProductDetailsPojo productDetailsPojo,
			ModelMap modelMap) throws IOException, URISyntaxException {

		LeoLogger.info("Catagory Controller---addProductDetails---productDetailsPojo   " + productDetailsPojo.getcf1());
		ResultVO resultVO = new ResultVO();

		String uploadDir = imagesPath;
		resultVO = addCatagoryService.addProduct(productDetailsPojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "AddProducts";

	}

	@GetMapping(value = "/addProductDetails")
	public String addProductDetails(Model model) {
		return "AddProductDetails";
	}

	@PostMapping("/addProductDetails")
	public String addProductDetails(@ModelAttribute("productDetails") ProductDetailsPojo productDetailsPojo,
			ModelMap modelMap) throws IOException, URISyntaxException {

		LeoLogger.info("Catagory Controller---addProductDetails---productDetailsPojo   " + productDetailsPojo.getcf1());
		ResultVO resultVO = new ResultVO();

		String uploadDir = imagesPath;
		resultVO = addCatagoryService.addProductDetails(productDetailsPojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "AddProductDetails";

	}

	@GetMapping(value = "/images/{fileName}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
		LeoLogger.info("Catagory Controller downloadImageFromFileSystem1 ");
		System.out.print("************inside  downloadImageFromFileSystem1******** ");

		byte[] imageData = addCatagoryService.downloadImageFromFileSystem(fileName);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(imageData);

	}

	@PostMapping("/updateProductDetails")
	public String updateProductDetails(@ModelAttribute("productDetails") ProductDetailsPojo productDetailsPojo,
			ModelMap modelMap, HttpServletRequest request) throws IOException, ServletException {

		LeoLogger.info("Catagory Controller---updateProductDetails-- productDetailsPojo   "
				+ productDetailsPojo.getcf1() + productDetailsPojo.toString());

		ResultVO resultVO = new ResultVO();

		resultVO = addCatagoryService.updateProductDetails(productDetailsPojo, request);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewProductDetailsPage";
	}

	@GetMapping(value = "/viewProductDetails")
	public String viewProductDetails(ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsList();
		// LeoLogger.info("Catagory Controller
		// ---viewProductDetails---productDetailsPojo==" +
		// productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductDetails";
	}

	@GetMapping(value = "/viewProductDetailsPage")
	public String viewProductDetailsPage(ModelMap modelMap) {
		int page = 0;
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsListPage(modelMap, page);
		// LeoLogger.info("Catagory Controller
		// ---viewProductDetails---productDetailsPojo==" +
		// productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		
		  BigDecimal totalQuantity = productService.getTotalQuantity();
		  modelMap.addAttribute("totalQuantity", totalQuantity);
		  LeoLogger.info(" - viewProductDetailsPage - totalQuantity: " + totalQuantity);
		return "ViewProductDetailsPage";
	}

	@GetMapping(value = "/getProductDetailsPage")
	public String getProductDetailsPage(ModelMap modelMap, @RequestParam("page") int page) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsListPage(modelMap, page);
		// LeoLogger.info("Catagory Controller
		// ---viewProductDetails---productDetailsPojo==" +
		// productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);

		  BigDecimal totalQuantity = productService.getTotalQuantity();
		  modelMap.addAttribute("totalQuantity", totalQuantity);
		  LeoLogger.info(" - getProductDetailsPage - totalQuantity: " + totalQuantity);
		return "ViewProductDetailsPage";
	}

	@GetMapping("/getProductDetailsPageNew")
	public String getProductDetailsPageNew(ModelMap modelMap, @RequestParam(defaultValue = "0") int page,
			@RequestParam("pageSize") int pageSize) {
		LeoLogger.info("ProductDetails Controller -getProductDetailsPageNew");
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsPageNew(modelMap, page,
				pageSize);

		LeoLogger.info("ProductDetails Controller ---viewProductDetails---" + page);
		LeoLogger.info("ProductDetails Controller ---viewProductDetails---productDetailsPojo=="
				+ productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);

		return "ViewProductDetailsPage";
	}

	@GetMapping("/searchProduct")
	public String searchProduct(ModelMap modelMap, @RequestParam("search") String search) {

		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getSearchProduct(search);

		LeoLogger.info("ProductController ---viewProduct--searchProduct-" + search.length());
		// LeoLogger.info("Sales Controller ---viewSales---salesPojo==" +
		// salesPojo.toString());
		
		LeoLogger.info("ProductController ----searchProduct-productDetailsPojo size==" + productDetailsPojo.size());

		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("search", search);


		return "ViewProductDetailsPage";
	}

	

	
	/*
	 * @PostMapping("/searchProductNew")
	 * 
	 * @ResponseBody public ResponseEntity<String> searchProductNew(@RequestBody
	 * Map<String, String> payload) { try { String search = payload.get("search");
	 * List<ProductDetailsPojo> products =
	 * addCatagoryService.getSearchProduct(search);
	 * 
	 * LeoLogger.info("ProductController ----searchProduct-payload=" + payload);
	 * LeoLogger.info("ProductController ----searchProduct-products=" +
	 * products.size());
	 * 
	 * String productRows = renderProductRows(products); int totalRecords =
	 * products.size(); String jsonResponse = "{\"productRows\":\"" + productRows +
	 * "\", \"totalRecords\":" + totalRecords + "}"; return
	 * ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonResponse
	 * ); } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\""
	 * + e.getMessage() + "\"}"); } }
	 */

	/*
	 @PostMapping("/searchProductNew")
	    @ResponseBody
	    public ResponseEntity<String> searchProductNew(@RequestBody Map<String, String> payload) {
	        try {
	            // Validate input
	            if (payload == null || !payload.containsKey("search") || payload.get("search").isEmpty()) {
	                return ResponseEntity.badRequest().body("{\"error\":\"Search parameter is required\"}");
	            }

	            String search = payload.get("search");
	            List<ProductDetailsPojo> products = addCatagoryService.getSearchProduct(search);

	            LeoLogger.info("ProductController ----searchProduct-payload=" + payload);
	            LeoLogger.info("ProductController ----searchProduct-products=" + products.size());

	            String productRows = renderProductRows(products);
	            int totalRecords = products.size();

	            Map<String, Object> response = new HashMap<>();
	            response.put("productRows", productRows);
	            response.put("totalRecords", totalRecords);

	            return ResponseEntity.ok()
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .body(objectMapper.writeValueAsString(response));
	        } catch (Exception e) {
	            LeoLogger.error("Error in searchProductNew:", e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("{\"error\":\"" + e.getMessage() + "\"}");
	        }
	    }

*/
	//@PostMapping("/searchProductNew")
	@ResponseBody
	public ResponseEntity<String> searchProductNew(@RequestBody Map<String, String> payload) {
	    try {
	        // Validate input
	    	   LeoLogger.info("#####[searchProductNew]##### search payload:" + payload);
	        if (payload == null || !payload.containsKey("search") || payload.get("search").isEmpty()) {
	            return ResponseEntity.badRequest().body("{\"error\":\"Search parameter is required\"}");
	        }

	        String search = payload.get("search").toLowerCase(); // search term in lowercase
	        LeoLogger.info("#####[searchProductNew]##### search :" + search);
	        List<ProductDetailsPojo> products = addCatagoryService.getSearchProduct(search);

	        LeoLogger.info("#####[searchProductNew]##### search :" + search);
	        LeoLogger.info("#####[searchProductNew]##### size" + products.size());

	        // Sort products so that ones starting with the search term appear first
	        /*
	        products.sort((p1, p2) -> {
	            String s = search.toLowerCase().trim();
	            String name1 = p1.getname() == null ? "" : p1.getname().toLowerCase();
	            String name2 = p2.getname() == null ? "" : p2.getname().toLowerCase();

	            if (name1.equals(s) && !name2.equals(s)) return -1;
	            if (!name1.equals(s) && name2.equals(s)) return 1;

	            if (name1.startsWith(s) && !name2.startsWith(s)) return -1;
	            if (!name1.startsWith(s) && name2.startsWith(s)) return 1;

	            if (name1.contains(s) && !name2.contains(s)) return -1;
	            if (!name1.contains(s) && name2.contains(s)) return 1;

	            return name1.compareTo(name2); // fallback
	        }); */


	        String productRows = renderProductRows(products);
	        int totalRecords = products.size();

	        Map<String, Object> response = new HashMap<>();
	        response.put("productRows", productRows);
	        response.put("totalRecords", totalRecords);

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(objectMapper.writeValueAsString(response));
	    } catch (Exception e) {
	        LeoLogger.error("Error in searchProductNew:", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("{\"error\":\"" + e.getMessage() + "\"}");
	    }
	}
	
	@PostMapping("/searchProductNew")
	@ResponseBody
	public ResponseEntity<String> searchProductNews(@RequestBody Map<String, String> payload) {
	    try {
	        // Validate input
	    	   LeoLogger.info("#####[searchProductNew]##### search payload:" + payload);
	        if (payload == null || !payload.containsKey("search") || payload.get("search").isEmpty()) {
	            return ResponseEntity.badRequest().body("{\"error\":\"Search parameter is required\"}");
	        }

	        String search = payload.get("search").toLowerCase(); // search term in lowercase
	        LeoLogger.info("#####[searchProductNew]##### search :" + search);
	        List<ProductDetailsPojo> products = addCatagoryService.getSearchProduct(search);

	        LeoLogger.info("#####[searchProductNew]##### search :" + search);
	        
	        products.sort(Comparator.comparingInt(p -> matchPriority(p, search)));

	        LeoLogger.info("#####[searchProductNew]##### size: " + products.size());

	  

	        String productRows = renderProductRows(products);
	        int totalRecords = products.size();

	        Map<String, Object> response = new HashMap<>();
	        response.put("productRows", productRows);
	        response.put("totalRecords", totalRecords);

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(objectMapper.writeValueAsString(response));
	    } catch (Exception e) {
	        LeoLogger.error("Error in searchProductNew:", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("{\"error\":\"" + e.getMessage() + "\"}");
	    }
	}
	
	 private int matchPriority(ProductDetailsPojo p, String keyword) {
	    if (p.getcode() != null && p.getcode().toLowerCase().startsWith(keyword)) return 0;
	    if (p.getname() != null && p.getname().toLowerCase().startsWith(keyword)) return 1;
	    if (p.getcf1() != null && p.getcf1().toLowerCase().startsWith(keyword)) return 2;

	    if (p.getcode() != null && p.getcode().toLowerCase().contains(keyword)) return 3;
	    if (p.getname() != null && p.getname().toLowerCase().contains(keyword)) return 4;
	    if (p.getcf1() != null && p.getcf1().toLowerCase().contains(keyword)) return 5;

	    return 6; 
	}


	private String renderProductRows(List<ProductDetailsPojo> products) {
	    StringBuilder html = new StringBuilder();
	    int serialNumber = 1; // Initialize serial number counter
	    
	    for (ProductDetailsPojo product : products) {
	        long productId = product.getProductId(); // Use Product ID for unique identifiers
	        html.append("<tr>")
	            .append("<td>").append(serialNumber++).append("</td>") // Serial number
	            .append("<td id='productId").append(productId).append("'>").append(product.getcode()).append("</td>")
	           .append("<td id='productName").append(productId).append("'>").append(product.getname()).append("</td>")
	         
	            .append("<td><a href='/images/")
	            .append(product.getProductFileName())
	            .append("' data-lightbox='product-gallery' data-title='Product Image'>")
	            .append("<img src='/images/").append(product.getProductFileName())
	            .append("' style='width: 90%; height: 100%;' alt='Product Image'></a></td>")
	            
	          //  .append("<td id='cf1").append(productId).append("'>").append(product.getcf1()).append("</td>")
	          //  .append("<td id='productCost").append(productId).append("'>").append(product.getcost()).append("</td>")
	         //   .append("<td id='rollprice").append(productId).append("'>").append(product.getrollprice()).append("</td>")
	         //   .append("<td id='productPrice").append(productId).append("'>").append(product.getprice()).append("</td>")
	            
	            .append("<td id='cf1").append(productId).append("' contenteditable='true' class='cf1'>").append(product.getcf1()).append("</td>")
	            .append("<td id='productCost").append(productId).append("' contenteditable='true' class='productCost'>").append(String.format("%.2f", product.getcost())).append("</td>")
	            .append("<td id='rollprice").append(productId).append("' contenteditable='true' class='rollprice'>").append(String.format("%.2f", product.getrollprice())).append("</td>")
	            .append("<td id='productPrice").append(productId).append("' contenteditable='true' class='productPrice'>").append(String.format("%.2f", product.getprice())).append("</td>")

	        //    .append("<td id='productQuantity").append(productId).append("'>").append(product.getQuantity()).append("</td>")
	            .append("<td id='productQuantity").append(productId).append("' contenteditable='true' class='productQuantity'>") .append(product.getQuantity()).append("</td>")
	            .append("<td id='productDiscount").append(productId).append("'>").append(product.getpromotion()).append("</td>")
	            .append("<td id='startDate").append(productId).append("'>").append(product.getstart_date()).append("</td>")
	            .append("<td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#modal-lg' onclick='EditDetails(")
	            .append(productId).append(")'>Edit</button></td>")
	            .append("</tr>");
	    }
	    
	    return html.toString();
	}

	
	@GetMapping("/getProductDetailsList")
	public String getProductDetailsList(ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsList();
		// LeoLogger.info("Catagory Controller
		// ---getProductDetailsList---productDetailsPojo==" +
		// productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductDetails";
	}

	@GetMapping("/getProductList")
	public @ResponseBody List<ProductDetailsPojo> getProductList(@RequestParam("subCatagoryId") long subCatagoryId,
			ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetails(subCatagoryId);
		LeoLogger.info("Catagory Controller ---getProductList---productDetailsPojo==" + productDetailsPojo.toString());
		return productDetailsPojo;
	}

	@PostMapping("/deleteProductDetails")
	public @ResponseBody ResultVO deleteProductDetails(@RequestParam("productCode") String productCode) {
		ResultVO resultVO = addCatagoryService.deleteProductDetails(productCode);
		LeoLogger.info("Catagory Controller ---deleteProductDetails---resultVO==" + resultVO.toString());
		return resultVO;
	}

	@GetMapping("/getCategoryDetailsList")
	public String getCategoryList(ModelMap modelMap) {
		List<ProductCategoryPojo> catagoryPojo = addCatagoryService.getCatagoryList();
		LeoLogger.info("Catagory Controller ---getCategoryDetailsList---catagoryPojo==" + catagoryPojo.toString());
		modelMap.addAttribute("catagoryPojo", catagoryPojo);
		return "ViewCategoryDetails";
	}

	@PostMapping("/deleteCategoryDetails")
	public @ResponseBody ResultVO deleteCategoryDetails(@RequestParam("catCode") long catCode) {
		ResultVO resultVO = addCatagoryService.deleteCategoryDetails(catCode);
		LeoLogger.info("Catagory Controller ---deleteCategoryDetails---resultVO==" + resultVO.toString());
		return resultVO;
	}

	@GetMapping(value = "/ViewSubCategoryDetails")
	public String ViewSubCategoryDetails(Model model) {
		return "ViewSubCategoryDetails";
	}

	@GetMapping("/getSubCategoryDetailsList")
	public String getSubCategoryDetailsList(@RequestParam("catagoryId") long catagoryId, ModelMap modelMap) {
		List<ProductSubCategoryPojo> subCatagoryPojo = addCatagoryService.getSubCategoryDetailsList(catagoryId);
		LeoLogger.info(
				"Catagory Controller ---getSubCategoryDetailsList--subCatagoryPojo==" + subCatagoryPojo.toString());
		modelMap.addAttribute("subCatagoryPojo", subCatagoryPojo);
		return "ViewSubCategoryDetails";
	}

	@PostMapping("/deleteSubCategoryDetails")
	public @ResponseBody ResultVO deleteSubCategoryDetails(@RequestParam("subCatCode") long subCatCode) {
		ResultVO resultVO = addCatagoryService.deleteSubCategoryDetails(subCatCode);
		LeoLogger.info("Catagory Controller ---deleteSubCategoryDetails---resultVO==" + resultVO.toString());
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
		LeoLogger.info("Catagory Controller ---addProductBarCodeDetails---barcode ==" + barcode);
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
		LeoLogger.info("Catagory Controller ---getBarCodeList---barCodePojo==" + barCodePojo.toString());
		modelMap.addAttribute("barCodePojo", barCodePojo);
		return "ViewBarCodeDetails";
	}

	@PostMapping("/deleteBarCodeDetails")
	public @ResponseBody ResultVO deleteBarCodeDetails(@RequestParam("barCode") long barCode) {
		ResultVO resultVO = addCatagoryService.deleteBarCodeDetails(barCode);
		LeoLogger.info("Catagory Controller ---deleteBarCodeDetails---resultVO==" + resultVO.toString());
		return resultVO;
	}

	@GetMapping("/getItemCodeList")
	public String getItemCodeList(@RequestParam("itemCode") String itemCode, ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getItemCodeList(itemCode);
		LeoLogger.info("Catagory Controller ---getItemCodeList---productDetailsPojo==" + productDetailsPojo.toString());
		modelMap.addAttribute("productDetailsPojo", productDetailsPojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ViewProductDetails";
	}

	@GetMapping("/getItemNameList")
	public String getItemNameList(@RequestParam("itemName") String itemName, ModelMap modelMap) {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getItemNameList(itemName);
		LeoLogger.info("Catagory Controller ---getItemNameList---productDetailsPojo==" + productDetailsPojo.toString());
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
	public String updateMemberDetails(@ModelAttribute("memberDetails") UserRegistrationPojo memberDetailsPojo,
			ModelMap modelMap) throws IOException {

		LeoLogger.info(
				"Catagory Controller ---updateMemberDetails---memberDetailsPojo   " + memberDetailsPojo.getUserName());

		ResultVO resultVO = new ResultVO();
		resultVO = addCatagoryService.updateMemberDetails(memberDetailsPojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewMemberDetails";
	}
	@PostMapping("/addDeposit")
	public String addDeposit(ModelMap map, @RequestBody UserRegistrationPojo memberDtlVO) {
		LeoLogger.info("CatagoryServiceImpl---UserRegistrationPojo" +memberDtlVO);
		LeoLogger.info("CatagoryServiceImpl---deposit" +memberDtlVO.getDeposit());
		ResultVO resultVO = addCatagoryService.addDeposit(memberDtlVO);
		map.addAttribute("result", resultVO);
		return "redirect:viewMemberDetails";
	}

	@PostMapping("/purchaseMemberPlan")
	public String purchaseMemberPlan(ModelMap modelMap, HttpServletRequest request) throws IOException {

		// LeoLogger.info("planPojo in Controller of Purchase plan "+
		// planPojo.getUserName());
		// LeoLogger.info("planPojo : "+ planPojo.getPlanName());

		String userName = request.getParameter("userName");
		String planId = request.getParameter("id");

		LeoLogger.info("Catagory Controller ---purchaseMemberPlan----Username is  :  " + userName);
		LeoLogger.info("Catagory Controller ---purchaseMemberPlan----Plan ID is  :  " + planId);

		PlanPojo planPojo = new PlanPojo();

		planPojo = addCatagoryService.getplanbyPlanid(planId);

		planPojo.setUserName(userName);
		ResultVO resultVO = new ResultVO();
		resultVO = addCatagoryService.purchasePlanDetails(planPojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewMemberDetails";
	}

	@PostMapping("/editPrice")
	public String editPrice(ModelMap map, @RequestBody ProductDetailsPojo detailsPojo) {

		ResultVO resultVO = productService.editProduct(detailsPojo);
		map.addAttribute("result", resultVO);
		return "redirect:viewProductDetails";
	}
	
	@PostMapping("/editQuantity")
	public String editQuantity(ModelMap map, @RequestBody ProductDetailsPojo detailsPojo) {
	    String methodName = "editQuantity";
		LeoLogger.info("[{}] 📥 Request received: productId={}, quantity={}", methodName, detailsPojo.getProductId(), detailsPojo.getQuantity());

	    ResultVO resultVO = productService.editQuantity(detailsPojo);
	    map.addAttribute("result", resultVO);

	    LeoLogger.info("[{}] ✅ Quantity updated successfully for productId={}", methodName, detailsPojo.getProductId());
	    return "redirect:viewProductDetails";
	}

	
	  @PostMapping("/updateProductCost")
	    public String updateProductCost(
	            @RequestParam("productId") Long productId,
	            @RequestParam("productCost") Float productCost) {
		  
		  LeoLogger.info("Catagory Controller ---updateProductCost----productId  :  " + productId);
		  LeoLogger.info("Catagory Controller ---updateProductCost----productCost  :  " + productCost);
	        
	            productService.updateProductCost(productId, productCost);
	        	return "redirect:viewProductDetails";
	     
	    }

	@PostMapping("/rollprice")
	public String rollprice(ModelMap map, @RequestBody ProductDetailsPojo detailsPojo) {
		LeoLogger.info("Catagory Controller ---productName  " + detailsPojo.getrollprice());
		ResultVO resultVO = productService.rollprice(detailsPojo);
		map.addAttribute("result", resultVO);
		return "redirect:viewProductDetails";
	}
	
	@PostMapping("/productName")
	public String productName(ModelMap map, @RequestBody ProductDetailsPojo detailsPojo) {

		LeoLogger.info("Catagory Controller ---productName  " + detailsPojo.getname());
		ResultVO resultVO = productService.productName(detailsPojo);
		map.addAttribute("result", resultVO);
		return "redirect:viewProductDetails";
	}
	@PostMapping("/mpn")
	public String mpn(ModelMap map, @RequestBody ProductDetailsPojo detailsPojo) {
		LeoLogger.info("Catagory Controller ---productName  " + detailsPojo.getrollprice());
		ResultVO resultVO = productService.mpn(detailsPojo);
		map.addAttribute("result", resultVO);
		return "redirect:viewProductDetails";
	}


	   @PostMapping("/removeImage/{productId}")
	    public String removeImage(ModelMap map, @PathVariable("productId") Long productId) {
	        // Implement logic to remove the image from the database using the productId
	        System.out.println("************ remove image   ====");
	        String response = productService.removeImage(productId);
	       // map.addAttribute("message", response);
	        return "redirect:/viewProductDetailsPage"; // Redirect to the viewProductDetailsPage
	    }
	   
	
	
	   

	   

		
		  
		   
}
