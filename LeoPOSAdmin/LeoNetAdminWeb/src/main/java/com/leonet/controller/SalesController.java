/**
 * author MONINDER
 */
package com.leonet.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.text.pdf.PdfWriter;
import com.leonet.common.entity.RequestQuoteItemEntity;
import com.leonet.common.entity.SpecialSaleRegisterHistory;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.CustomerPurchasePojo;
import com.leonet.common.pojo.CustomerPurchaseSalePojo;
import com.leonet.common.pojo.CustomerReportPojo;
import com.leonet.common.pojo.PaymentPojo;
import com.leonet.common.pojo.PaymentReportPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.QuotesItemPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.RegisterPojo;
import com.leonet.common.pojo.RegisterhistoryPojo;
import com.leonet.common.pojo.RequestQuotePojo;
import com.leonet.common.pojo.RequestQuotesItemsPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.SaleInvoiceReportPojo;
import com.leonet.common.pojo.SaleItemPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.SalesPercentagePojo;
import com.leonet.common.pojo.SpecialSaleRegisterPojo;
import com.leonet.common.pojo.SpecialSalesItemPojo;
import com.leonet.common.pojo.SpecialSalesPojo;
import com.leonet.common.pojo.UnitPojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.constant.Action;
import com.leonet.entity.MemberUser;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.SpecialSaleRegisterHistoryRepository;
import com.leonet.service.CatagoryService;
import com.leonet.service.ImportPurchaseService;
import com.leonet.service.LoginService;
import com.leonet.service.SaleInvoiceReportService;
import com.leonet.service.SaleService;
import com.leonet.util.CurrentUserUtil;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Controller
public class SalesController {
	@Autowired
	LoginService loginService;
	
	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@Autowired
	CustomFileUploadUtil fileUploadUtil;

	@Autowired
	SaleService saleService;

	@Autowired
	CatagoryService addCatagoryService;
	
    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON serialization


	@Autowired(required = false)
	ImportPurchaseService importpurchaseService;
	
	@Autowired
	private com.leonet.service.SysAuditService sysAuditService;
	
	@Autowired
	private SaleInvoiceReportService saleInvoiceReportService;
	
	@Autowired
	SpecialSaleRegisterHistoryRepository specialSaleRegisterHistoryRepository;
	
	@Autowired
	private MemberUserRepo memberUserRepo;

	@GetMapping(value = "/addSales")
	public String addSales(Model model) {
		return "AddSales";
	}

	@PostMapping("/addSales")
	@ResponseBody
	public ResultVO addSales(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {
		
		LeoLogger.info("Sales Controller ---addSales--- Add item Request Pojo JSON .....{}",
				addItemReqPojos.toString());
		
		ResultVO resultVO = new ResultVO();		
		try {
		
			//ResultVO resultVO = saleService.addSale(addItemReqPojos);
			
			 resultVO = saleService.addSaleRefactored(addItemReqPojos);
		
		sysAuditService.setSysAudit( Action.ADD_SALE, resultVO.msgDescr);
		
		} catch (Exception e) {
				LeoLogger.error(">>>> addSales >>>> error >>>> " + e.getMessage());
				e.printStackTrace();
				resultVO.setError(true);
				resultVO.setMsgDescr("Error occured please contact support team!");		
			}
				
		
		return resultVO;

	}
	
	@PostMapping("/addCustomerFromSales")	
	@ResponseBody
	public Map<String, Object> addCustomerFromSales(@ModelAttribute UserRegistrationPojo request) {
	    LeoLogger.info("Sales Controller ---addCustomerFromSales---customer: " + request);
	    
	    ResultVO resultVO = loginService.memberRegistrationProcess(request);
	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("msgDescr", resultVO.getMsgDescr());
	    responseMap.put("error", resultVO.isError());

	    if (!resultVO.isError()) {
	       
	        MemberUser savedCustomer = memberUserRepo.findByUsername(request.getUserName());
	        responseMap.put("customer", savedCustomer);
	    }

	    return responseMap;
	}



	@GetMapping("/getCustomer")
	public @ResponseBody List<UserRegistrationPojo> getCustomerList() {
		List<UserRegistrationPojo> customerPojo = saleService.getCustomerList();
		// LeoLogger.info("Sales Controller ---getCustomer---customerPojo==" +
		// customerPojo.toString());
		return customerPojo;
	}

	@GetMapping("/getProducts")
	public @ResponseBody List<ProductDetailsPojo> getProducts() {
		List<ProductDetailsPojo> productDetailsPojo = addCatagoryService.getProductDetailsAll();
		// LeoLogger.info("Sales Controller ---getProducts--productDetailsPojo==" +
		// productDetailsPojo.toString());
		return productDetailsPojo;
	}

	@GetMapping("/getUnits")
	public @ResponseBody List<UnitPojo> getUnits() {
		List<UnitPojo> unitPojo = addCatagoryService.getUnitsAll();
		// LeoLogger.info("Sales Controller ---productDetailsPojo==" +
		// productDetailsPojo.toString());
		return unitPojo;
	}

	@GetMapping("/getSalesPercent")
	public @ResponseBody List<SalesPercentagePojo> getSalesPercent() {
		List<SalesPercentagePojo> salespercentPojo = addCatagoryService.getSalesPercent();
		// LeoLogger.info("productDetailsPojo==" + productDetailsPojo.toString());
		return salespercentPojo;
	}

	@GetMapping(value = "/viewSales")
	public String viewSales(ModelMap modelMap) {
		List<SalePojo> salesPojo = saleService.getSalesList();

		LeoLogger.info("Sales Controller ---viewSales---");
		// LeoLogger.info("Sales Controller ---viewSales---salesPojo==" +
		// salesPojo.toString());
		modelMap.addAttribute("salesPojo", salesPojo);

		return "ViewSales";
	}

	@GetMapping(value = "/viewSalesNew")
	public String viewSalesNew(ModelMap modelMap) {
		int page = 0;
		// int pageSize=10;
		List<SalePojo> salesPojo = saleService.getSalesListNew(modelMap, page);
		// List<SalePojo> salesPojo =
		// saleService.getSalesListpage(modelMap,page,pageSize);

		// List<SalePojo> salesPojoo =
		// saleService.getSalesListpage(modelMap,page,pageSize);

		LeoLogger.info("Sales Controller ---viewSalesNew---");
		// LeoLogger.info("Sales Controller ---viewSales---salesPojo==" +
		// salesPojo.toString());
		
		  String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role: {}", role);
	    if ("custom".equalsIgnoreCase(role)) {
	        List<SalePojo> filteredList = new ArrayList<>();
	        for (SalePojo sale : salesPojo) {
	            if (!"Special".equalsIgnoreCase(sale.getCtype())) {
	                filteredList.add(sale);
	            }
	        }
	        salesPojo = filteredList;
	    }
		modelMap.addAttribute("salesPojo", salesPojo);
		// modelMap.addAttribute("salesPojo", salesPojoo);
		// modelMap.addAttribute("salesPojo", sales);

		return "ViewSalesNew";
	}

	@GetMapping("/getSalesListNew")
	public String getSalesListNew(ModelMap modelMap, @RequestParam("page") int page) {
		List<SalePojo> salesPojo = saleService.getSalesListNew(modelMap, page);

		LeoLogger.info("Sales Controller ---viewSales---" + page);
		// LeoLogger.info("Sales Controller ---viewSales---salesPojo==" +
		// salesPojo.toString());
		modelMap.addAttribute("salesPojo", salesPojo);

		return "ViewSalesNew";
	}
	


	@GetMapping("/getSalesListpage")
	public String getSalesListpage(ModelMap modelMap, @RequestParam(defaultValue = "0") int page,
			@RequestParam("pageSize") int pageSize) {
		LeoLogger.info("Sales Controller -gettweentyfive");
		List<SalePojo> salesPojo = saleService.getSalesListpage(modelMap, page, pageSize);

		LeoLogger.info("Sales Controller ---viewSales---" + page);
		LeoLogger.info("Sales Controller ---viewSales---salesPojo==" + salesPojo.toString());
		modelMap.addAttribute("salesPojo", salesPojo);

		return "ViewSalesNew";
	}

	@GetMapping("/searchSale")
	public String search(ModelMap modelMap, @RequestParam("search") String search) {

		List<SalePojo> salesPojo = saleService.getSearch( search);

		LeoLogger.info("Sales Controller ---viewSales---" + search);
		// LeoLogger.info("Sales Controller ---viewSales---salesPojo==" +
		// salesPojo.toString());
		modelMap.addAttribute("salesPojo", salesPojo);

		return "ViewSalesNew";
	}
	
	@PostMapping("/searchSalesNew")
	@ResponseBody
	public ResponseEntity<String> searchSalesNew(@RequestBody Map<String, String> payload, HttpServletRequest request) {
	    try {
	        // Validate input
	        if (payload == null || !payload.containsKey("search") || payload.get("search").isEmpty()) {
	            return ResponseEntity.badRequest().body("{\"error\":\"Search parameter is required\"}");
	        }

	        String search = payload.get("search");
	        List<SalePojo> sales = saleService.getSearch(search);

	        LeoLogger.info("SalesController ----searchSales-payload=" + payload);
	        LeoLogger.info("SalesController ----searchSales-sales=" + sales.size());

	        String salesRows = renderSalesRows(sales, request);
	        int totalRecords = sales.size();

	        Map<String, Object> response = new HashMap<>();
	        response.put("salesRows", salesRows);
	        response.put("totalRecords", totalRecords);

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(objectMapper.writeValueAsString(response));
	    } catch (Exception e) {
	        LeoLogger.error("Error in searchSalesNew:", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("{\"error\":\"" + e.getMessage() + "\"}");
	    }
	}

	
	private String renderSalesRows(List<SalePojo> sales, HttpServletRequest request) {
	    StringBuilder html = new StringBuilder();
	    int serialNumber = 1; // Initialize serial number counter

	    html.append("<tbody>"); // Open tbody tag

	    for (SalePojo sale : sales) {
	        long saleId = sale.getSaleId(); // Use Sale ID for unique identifiers
	        html.append("<tr id='").append(saleId).append("'>")
	            .append("<td>").append(serialNumber++).append("</td>") // Serial number
	            .append("<td id='saleDate").append(saleId).append("'>").append(sale.getDate()).append("</td>")
	            .append("<td hidden id='saleId").append(saleId).append("'>").append(sale.getSaleId()).append("</td>")
	            .append("<td id='referenceno").append(saleId).append("'>").append(sale.getReferenceno()).append("</td>")
	            .append("<td hidden id='memberId").append(saleId).append("'>").append(sale.getMemberid()).append("</td>")
	            .append("<td id='customer").append(saleId).append("'>").append(sale.getMember_name()).append("</td>");

	        // Check if the user is an admin (and display customer type)
	        if (userHasAdminAccess()) {
	            html.append("<td id='ctype").append(saleId).append("'>").append(sale.getCtype()).append("</td>");
	        }

	        html.append("<td hidden id='ctypeHidden").append(saleId).append("'>").append(sale.getCtype()).append("</td>")
	            .append("<td id='saleTotal").append(saleId).append("'>").append(formatCurrency(sale.getGrand_total())).append("</td>")
	            .append("<td id='salePaid").append(saleId).append("'>").append(formatCurrency(sale.getPaid())).append("</td>")
	            .append("<td id='saleBalance").append(saleId).append("'>").append(formatCurrency(sale.getGrand_total() - sale.getPaid())).append("</td>")
	            .append("<td id='SaleStatus").append(saleId).append("'>").append(sale.getPaymentstatus()).append("</td>")
	            .append("<td id='active").append(saleId).append("'>")
	            .append(sale.getIsActive() == 0 ? "active" : "delete")
	            .append("</td>")
	            .append("<td hidden id='totaltax").append(saleId).append("'>").append(formatCurrency(sale.getTotal_tax())).append("</td>")
	            .append("<td hidden id='total").append(saleId).append("'>").append(formatCurrency(sale.getTotal())).append("</td>")
	            .append("<td hidden id='createdby").append(saleId).append("'>").append(sale.getCreatedBy()).append("</td>")
	            .append("<td hidden id='note").append(saleId).append("'>").append(sale.getNote()).append("</td>")
	            .append("<td hidden id='po").append(saleId).append("'>").append(sale.getPurchaseorder()).append("</td>")
	            .append("<td hidden id='tax").append(saleId).append("'>").append(sale.getTotal_tax()).append("</td>")
	            .append("<td hidden id='caddress").append(saleId).append("'>").append(sale.getCustomeraddress()).append("</td>")
	            .append("<td hidden id='pincode").append(saleId).append("'>").append(sale.getPincode()).append("</td>")
	            .append("<td hidden id='phonemain").append(saleId).append("'>").append(sale.getPhonemain()).append("</td>")
	            .append("<td>")
	            .append("<div class='dropdown d-flex justify-content-center'>")
	            .append("<button class='btn border-0 dropdown-toggle' type='button' id='dropdownMenuButton").append(saleId).append("' data-bs-toggle='dropdown' aria-expanded='false'>")
	            .append("<i class='fas fa-ellipsis-v'></i></button>")
	            .append("<ul class='dropdown-menu py-0' aria-labelledby='dropdownMenuButton").append(saleId).append("'>")
	            .append("<li><button class='dropdown-item' data-bs-toggle='modal' data-bs-target='#viewsales' onclick='ViewDetails(").append(saleId).append(")'>View Sales</button></li>")
	            .append("<li><button class='dropdown-item' data-bs-toggle='modal' data-bs-target='#viewsales' onclick='ViewDetailsMPN(").append(saleId).append(")'>View Sales Without MPN</button></li>")
	            .append("<li><button class='dropdown-item' data-bs-toggle='modal' data-bs-target='#viewsales10' onclick='ViewDetails10(").append(saleId).append(")'>View Sales 10%</button></li>");

	        // Add payment option for eligible sales
	        if (sale.getIsActive()==0 && (sale.getGrand_total() - sale.getPaid() > 0)) {
	            html.append("<li><button class='dropdown-item' data-bs-toggle='modal' data-bs-target='#modal-payment' onclick='loadPayment(").append(saleId).append(")'>Add Payment</button></li>");
	        }

	        html.append("<li><button class='dropdown-item' data-bs-toggle='modal' data-bs-target='#viewpayment' onclick='ViewPayment(").append(saleId).append(")'>View Payment</button></li>");

	        String contextPath = request.getContextPath();
	        if (userHasAdminAccess() && sale.getIsActive()==0 && sale.getPaid() <= 0) {
	            html.append("<li><a class='dropdown-item' href='").append(contextPath).append("/editSales?id=").append(saleId).append("&ctype=").append(sale.getCtype()).append("'>Edit Sale</a></li>");
	        }

	        if (userHasAdminAccess() && sale.getIsActive()==0) {
	            html.append("<li><button class='dropdown-item' onclick='deleteDetails(").append(saleId).append(", ").append(saleId).append(")'>Delete</button></li>");
	        }

	        html.append("</ul></div></td></tr>");
	    }

	    html.append("</tbody>"); // Close tbody tag
	    return html.toString();
	}

	/*
	 * private String renderSalesRows(List<SalePojo> sales) { StringBuilder html =
	 * new StringBuilder(); int serialNumber = 1; // Initialize serial number
	 * counter
	 * 
	 * for (SalePojo sale : sales) { long saleId = sale.getSaleId(); // Use Sale ID
	 * for unique identifiers html.append("<tr id='").append(saleId).append("'>")
	 * .append("<td>").append(serialNumber++).append("</td>") // Serial number
	 * .append("<td id='saleDate").append(saleId).append("'>").append(sale.getDate()
	 * ).append("</td>")
	 * .append("<td hidden id='saleId").append(saleId).append("'>").append(sale.
	 * getSaleId()).append("</td>")
	 * .append("<td id='referenceno").append(saleId).append("'>").append(sale.
	 * getReferenceno()).append("</td>")
	 * .append("<td hidden id='memberId").append(saleId).append("'>").append(sale.
	 * getMemberid()).append("</td>")
	 * .append("<td id='customer").append(saleId).append("'>").append(sale.
	 * getMember_name()).append("</td>");
	 * 
	 * // Check if the user is an admin (and display customer type) if
	 * (userHasAdminAccess()) {
	 * html.append("<td id='ctype").append(saleId).append("'>").append(sale.getCtype
	 * ()).append("</td>"); }
	 * 
	 * html.append("<td hidden id='ctypeHidden").append(saleId).append("'>").append(
	 * sale.getCtype()).append("</td>")
	 * .append("<td id='saleTotal").append(saleId).append("'>").append(
	 * formatCurrency(sale.getGrand_total())).append("</td>")
	 * .append("<td id='salePaid").append(saleId).append("'>").append(formatCurrency
	 * (sale.getPaid())).append("</td>")
	 * .append("<td id='saleBalance").append(saleId).append("'>").append(
	 * formatCurrency(sale.getGrand_total() - sale.getPaid())).append("</td>")
	 * .append("<td id='SaleStatus").append(saleId).append("'>").append(sale.
	 * getPaymentstatus()).append("</td>")
	 * .append("<td id='active").append(saleId).append("'>").append(sale.getIsActive
	 * ()).append("</td>")
	 * .append("<td hidden id='totaltax").append(saleId).append("'>").append(
	 * formatCurrency(sale.getTotal_tax())).append("</td>")
	 * .append("<td hidden id='total").append(saleId).append("'>").append(
	 * formatCurrency(sale.getTotal())).append("</td>")
	 * .append("<td hidden id='createdby").append(saleId).append("'>").append(sale.
	 * getCreatedBy()).append("</td>")
	 * .append("<td hidden id='note").append(saleId).append("'>").append(sale.
	 * getNote()).append("</td>")
	 * .append("<td hidden id='po").append(saleId).append("'>").append(sale.
	 * getPurchaseorder()).append("</td>")
	 * .append("<td hidden id='tax").append(saleId).append("'>").append(sale.
	 * getTotal_tax()).append("</td>")
	 * .append("<td hidden id='caddress").append(saleId).append("'>").append(sale.
	 * getCustomeraddress()).append("</td>")
	 * .append("<td hidden id='pincode").append(saleId).append("'>").append(sale.
	 * getPincode()).append("</td>")
	 * .append("<td hidden id='phonemain").append(saleId).append("'>").append(sale.
	 * getPhonemain()).append("</td>");
	 * 
	 * // Add action points html.append("<td>")
	 * .append("<button class='btn btn-info' onclick='viewSale(").append(saleId).
	 * append(")'>View</button> ")
	 * .append("<button class='btn btn-warning' onclick='editSale(").append(saleId).
	 * append(")'>Edit</button> ")
	 * .append("<button class='btn btn-danger' onclick='deleteSale(").append(saleId)
	 * .append(")'>Delete</button>") .append("</td>");
	 * 
	 * html.append("</tr>"); }
	 * 
	 * return html.toString(); }
	 */
	
	


	private String formatCurrency(double amount) {
	    return String.format("%.2f", amount);
	}

	// Dummy method to check if user has admin access
	private boolean userHasAdminAccess() {
	    // Implement logic for checking if the user has 'admin' access
	    return true; // Placeholder
	}


	@GetMapping("/searchSpecialSale")
	public String searchSpecialSale(ModelMap modelMap, @RequestParam("search") String search) {

		List<SpecialSalesPojo> specialsalesPojo = saleService.getspecialSearch(search);

		LeoLogger.info("Sales Controller ---viewSales---" + search);
			modelMap.addAttribute("specialsalesPojo", specialsalesPojo);

		return "ViewSpecialSales";
	}
	
	@PostMapping("/searchSpecialSalesNew")
	@ResponseBody
	public ResponseEntity<String> searchSpecialSalesNew(@RequestBody Map<String, String> payload, HttpServletRequest request) {
	    try {
	        // Validate input
	        if (payload == null || !payload.containsKey("search") || payload.get("search").isEmpty()) {
	            return ResponseEntity.badRequest().body("{\"error\":\"Search parameter is required\"}");
	        }

	        String search = payload.get("search");
	        List<SpecialSalesPojo> specialSales = saleService.getspecialSearch(search);

	        LeoLogger.info("SalesController ----searchSpecialSalesNew-payload=" + payload);
	        LeoLogger.info("SalesController ----searchSpecialSalesNew-specialSales=" + specialSales.size());

	        String salesRows = renderSpecialSalesRows(specialSales, request);
	        int totalRecords = specialSales.size();

	        Map<String, Object> response = new HashMap<>();
	        response.put("salesRows", salesRows);
	        response.put("totalRecords", totalRecords);

	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(objectMapper.writeValueAsString(response));
	    } catch (Exception e) {
	        LeoLogger.error("Error in searchSpecialSalesNew:", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("{\"error\":\"" + e.getMessage() + "\"}");
	    }
	}
	
	private String renderSpecialSalesRows(List<SpecialSalesPojo> specialSales, HttpServletRequest request) {
	    StringBuilder html = new StringBuilder();
	    int serialNumber = 1;
	    String contextPath = request.getContextPath();	  

	    html.append("<tbody>");
	    for (SpecialSalesPojo sale : specialSales) {
	        long saleId = sale.getSaleId();
	        html.append("<tr id='saleRow").append(saleId).append("'>") // Unique ID for the row
	            .append("<td id='serialNo").append(serialNumber).append("'>").append(serialNumber++).append("</td>") // Serial No
	            .append("<td id='saleDate").append(saleId).append("'>").append(sale.getDate()).append("</td>") // Date
	            .append("<td hidden id='saleId").append(saleId).append("'>").append(saleId).append("</td>") // Sale ID
	            .append("<td id='referenceno").append(saleId).append("'>").append(sale.getReferenceno()).append("</td>") // Reference No
	            .append("<td hidden id='memberId").append(saleId).append("'>").append(sale.getMemberid()).append("</td>") // Member ID
	            .append("<td id='customer").append(saleId).append("'>").append(sale.getMember_name()).append("</td>") // Customer Name
	            .append("<td id='ctype").append(saleId).append("'>").append(sale.getCtype()).append("</td>") // Customer Type
	            .append("<td id='saleTotal").append(saleId).append("'>").append(formatCurrency(sale.getGrand_total())).append("</td>") // Grand Total
	            .append("<td id='salePaid").append(saleId).append("'>").append(formatCurrency(sale.getPaid())).append("</td>") // Paid
	            .append("<td id='saleBalance").append(saleId).append("'>").append(formatCurrency(sale.getGrand_total() - sale.getPaid())).append("</td>") // Balance
	            .append("<td id='SaleStatus").append(saleId).append("'>").append(sale.getPaymentstatus()).append("</td>") // Status
	            .append("<td hidden id='tax").append(saleId).append("'>").append(formatCurrency(sale.getTotal_tax())).append("</td>") // Total Tax
	            .append("<td hidden id='total").append(saleId).append("'>").append(formatCurrency(sale.getTotal())).append("</td>") // Total
	            .append("<td>")
	            .append("<div class='dropdown d-flex justify-content-center'>")
	            .append("<button class='btn border-0 dropdown-toggle' type='button' data-bs-toggle='dropdown'>")
	            .append("<i class='fas fa-ellipsis-v'></i></button>")
	            .append("<ul class='dropdown-menu py-0'>")
	            
	            // View Details option
	            .append("<li><button type='button' class='dropdown-item' data-bs-toggle='modal' data-bs-target='#viewsales' ")
	            .append("onclick='ViewDetails(").append(saleId).append(")'>View Receipt</button></li>")
	            
	            // View Sales 10% option
	            .append("<li><button type='button' class='dropdown-item' data-bs-toggle='modal' data-bs-target='#viewsales10' ")
	            .append("onclick='ViewDetails10(").append(saleId).append(")'>View Sales 10%</button></li>")

	            // Add Payment option (only if grand_total > paid)
	            .append("<li><c:if test='${specialsalesPojo.grand_total - specialsalesPojo.paid > 0}'>")
	            .append("<a type='button' class='dropdown-item' data-toggle='modal' data-target='#modal-payment' ")
	            .append("onclick='loadPayment(").append(saleId).append(")'>Add Payment</a></c:if></li>")

	            // View Payment option
	            .append("<li><button type='button' class='dropdown-item' data-toggle='modal' data-target='#viewpayment' ")
	            .append("onclick='ViewPayment(").append(saleId).append(")'>View Payment</button></li>")
	            
	            	   
	        
	            // Edit Sale option (only if paid <= 0)
	            .append("<li><c:if test='${specialsalesPojo.paid <= 0}'>")
	         //   .append("<a type='button' class='dropdown-item' href='/editSales?id=").append(saleId).append("'>Edit Sale</a>")
	            .append("<a type='button' class='dropdown-item' href='").append(contextPath).append("/editSales?id=").append(saleId).append("&ctype=").append(sale.getCtype()).append("'>Edit Sale</a>")
	            .append("</c:if></li>")

	            // Delete Sale option
	            .append("<li><a type='button' class='dropdown-item text-danger' onclick='deleteDetails(")
	            .append(saleId).append(")'>Delete Sale</a></li>")

	            .append("</ul></div></td>")
	            .append("</tr>");
	    }
	    html.append("</tbody>");
	    return html.toString();
	}


	@GetMapping(value = "/viewRegister")
	public String viewRegister(ModelMap modelMap) {
		LeoLogger.info("Sales Controller ---viewRegister---");

	    String role = CurrentUserUtil.getRole();
	    LeoLogger.info("Logged-in User Role: {}", role);

	    // Get all registers only once
	    List<RegisterPojo> registerPojo = saleService.getRegisterList();

	    // If role is NOT admin → keep only today's records
	    if (!"admin".equalsIgnoreCase(role)) {

	        LocalDate today = LocalDate.now();

	        registerPojo = registerPojo.stream()
	                .filter(r -> {
	                    if (r.getDate() == null) return false;
	                    return r.getDate().toInstant()
	                            .atZone(ZoneId.systemDefault())
	                            .toLocalDate()
	                            .isEqual(today);
	                })
	                .collect(Collectors.toList());;
	    }

	
		modelMap.addAttribute("registerPojo", registerPojo);

		return "ViewRegister";
	}
	
	@GetMapping("/viewSpecialRegister")
	public String viewRegister(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "20") int size,
	        ModelMap modelMap) {

	    final String method = "viewSpecialRegister";

	    LeoLogger.info("[{}] Request received | page={} size={}", method, page, size);

	    Page<SpecialSaleRegisterPojo> registerPage =
	            saleService.getSpecialSaleRegisterList(page, size);

	    List<SpecialSaleRegisterPojo> registerPojo = registerPage.getContent();

	    LeoLogger.info("[{}] Records fetched | currentPage={} totalPages={} totalRecords={}",
	            method,
	            page,
	            registerPage.getTotalPages(),
	            registerPage.getTotalElements());

	    modelMap.addAttribute("registerPojo", registerPojo);
	    modelMap.addAttribute("currentPage", page);
	    modelMap.addAttribute("totalPages", registerPage.getTotalPages());

	    LeoLogger.info("[{}] Returning SpecialSaleRegister view", method);

	    return "SpecialSaleRegister";
	}
	
	@GetMapping("/special/cash-payments")
	@ResponseBody
	public List<SpecialSaleRegisterHistory> getSpecialCashPaymentsByDate(
	        @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

	    final String method = "getSpecialCashPaymentsByDate";

	    LeoLogger.info("[{}] START - Fetching special cash payments for date {}", method, date);

	    List<SpecialSaleRegisterHistory> payments =
	            specialSaleRegisterHistoryRepository.findByRegisterDate(date);

	    if (payments.isEmpty()) {
	        LeoLogger.info("[{}] No special cash payments found for date {}", method, date);
	    } else {
	        LeoLogger.info("[{}] {} special cash payment records found for date {}", 
	                method, payments.size(), date);
	    }

	    LeoLogger.info("[{}] END - Returning special cash payment list", method);

	    return payments;
	}
	
	@GetMapping(value = "/creditcardpayment")   
	 public @ResponseBody List<RegisterhistoryPojo> registerhistoryPojoo(ModelMap modelMap, @RequestParam("date")  String date)  {
		LeoLogger.info("Sales Controller ---creditcardpayment---");
		LeoLogger.info("Sales Controller ---creditcardpayment---date"+date);
		

	
		List<RegisterhistoryPojo> registerhistoryPojo = saleService.getAmountList(date);
		// LeoLogger.info("Sales Controller ---viewRegister---registerPojo==" +
		// registerPojo.toString());
		modelMap.addAttribute("registerhistoryPojo", registerhistoryPojo);

		return registerhistoryPojo;
	}
	
	@GetMapping(value = "/chequepayment")   
	 public @ResponseBody List<RegisterhistoryPojo> chequepayment(ModelMap modelMap, @RequestParam("date")  String date)  {
		LeoLogger.info("Sales Controller ---creditcardpayment---");
		LeoLogger.info("Sales Controller ---creditcardpayment---date"+date);
		

	
		List<RegisterhistoryPojo> registerhistoryPojo = saleService.getchequeAmountList(date);
		// LeoLogger.info("Sales Controller ---viewRegister---registerPojo==" +
		// registerPojo.toString());
		modelMap.addAttribute("chequepayment", registerhistoryPojo);

		return registerhistoryPojo;
	}
	@GetMapping(value = "/cashpayment")   
	 public @ResponseBody List<RegisterhistoryPojo> cashpayment(ModelMap modelMap, @RequestParam("date")  String date)  {
		LeoLogger.info("Sales Controller ---creditcardpayment---");
		LeoLogger.info("Sales Controller ---creditcardpayment---date"+date);
		

	
		List<RegisterhistoryPojo> registerhistoryPojo = saleService.getcashAmountList(date);
		// LeoLogger.info("Sales Controller ---viewRegister---registerPojo==" +
		// registerPojo.toString());
		modelMap.addAttribute("cashpayment", registerhistoryPojo);

		return registerhistoryPojo;
	}
	
	@GetMapping(value = "/onlinepayment")   
	 public @ResponseBody List<RegisterhistoryPojo> onlinepayment(ModelMap modelMap, @RequestParam("date")  String date)  {
		LeoLogger.info("Sales Controller ---creditcardpayment---");
		LeoLogger.info("Sales Controller ---creditcardpayment---date"+date);
		

	
		List<RegisterhistoryPojo> registerhistoryPojo = saleService.getonlineAmountList(date);
		// LeoLogger.info("Sales Controller ---viewRegister---registerPojo==" +
		// registerPojo.toString());
		modelMap.addAttribute("onlinepayment", registerhistoryPojo);

		return registerhistoryPojo;
	}


	@GetMapping(value = "/requestQuote")
	public String requestQuote(Model model) {
		return "RequestQuote";
	}

	@PostMapping("/requestQuote")
	@ResponseBody
	public String requestQuote(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info(
				"Sales Controller ---requestQuote--- Add Request Quote Pojo JSON ....." + addItemReqPojos.toString());

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
		LeoLogger.info("Sales Controller ---viewRequestQuote");
		// LeoLogger.info("Sales Controller ---viewRequestQuote---requestQuotePojo==" +
		// requestQuotePojo.toString());
		modelMap.addAttribute("requestQuotePojo", requestQuotePojo);

		return "viewRequestQuote";
	}

	@PostMapping("/downloadPDF")
	public @ResponseBody ResultVO downloadPDF(@RequestParam("rqCode") long id) {

		ResultVO resultVO = saleService.downloadPDF(id);
		// LeoLogger.info("Sales Controller ---downloadPDF--rqId==" +
		// String.valueOf(id));
		// LeoLogger.info("Sales Controller ---downloadPDF--resultVO==" +
		// resultVO.toString());
		return resultVO;
	}

	@GetMapping(value = "/addQuotes")
	public String addQuotes(Model model) {
		return "AddQuotes";
	}

	@GetMapping(value = "/viewQuotes")
	public String viewQuotes(ModelMap modelMap) {
	//	List<QuotesPojo> quotesPojo = saleService.getQuotesList();
		LeoLogger.info("Sales Controller ---viewQuotes");
		// LeoLogger.info("Sales Controller ---viewQuotes---quotesPojo==" +
		// quotesPojo.toString());
		
		  String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role: {}", role);

		    // Get all registers only once
		    List<QuotesPojo> quotesPojo = saleService.getQuotesList();

		    // If role is NOT admin → keep only today's records
		    LeoLogger.info("UnFiltered count: " + quotesPojo.size());
		    if (!"admin".equalsIgnoreCase(role)) {

		       // LocalDate today = LocalDate.now();

		        quotesPojo = quotesPojo.stream()
		                .filter(r -> {
		                	  if (r.getDate() == null) return false;
		                      String type = r.getCtype();
		                      LeoLogger.info("CTYPE: " + r.getCtype());
		                      if (type == null) return false;

		                      type = type.trim().toLowerCase();
		                      return "General".equalsIgnoreCase(type) || "WholeSellers".equalsIgnoreCase(type);
		                })
		                .collect(Collectors.toList());
		        LeoLogger.info("Filtered count: " + quotesPojo.size());

		    }

		
		modelMap.addAttribute("quotesPojo", quotesPojo);

		return "ViewQuotes";
	}

	@PostMapping("/addQuotes")
	@ResponseBody
	public ResultVO addQuotes(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {
		
		ResultVO resonse = new ResultVO();
		LeoLogger
				.info("Sales Controller ---addQuotes--- Add item Request Pojo JSON ....." + addItemReqPojos.toString());
		
		try {

	//	ResultVO resultVO = saleService.addQuotes(addItemReqPojos);
		
		 resonse = saleService.addQuoteRefactored(addItemReqPojos);

		sysAuditService.setSysAudit( Action.ADD_QUOTE, resonse.msgDescr);
		
	 } catch (Exception e) {
		LeoLogger.error(">>>> addQuoteRefactored >>>> error >>>> " + e.getMessage());
		e.printStackTrace();
		resonse.setError(true);
		resonse.setMsgDescr("Error occured please contact support team!");		
	}
		
		return resonse;
	}

	@GetMapping(value = "/viewQuotesNew")
	public String viewQuotesNew(ModelMap modelMap) {
		int page = 0;
		List<QuotesPojo> quotesPojo = saleService.getQuotesListNew(modelMap, page);
		
		LeoLogger.info("Sale Controller ---viewQuotes");
	    String role = CurrentUserUtil.getRole();
	    LeoLogger.info("Logged-in User Role: {}", role);

	    // Apply same logic used in /viewSalesNew
	    if ("custom".equalsIgnoreCase(role) || "cashier".equalsIgnoreCase(role) || "sales".equalsIgnoreCase(role)) {
	        List<QuotesPojo> filteredList = new ArrayList<>();
	        for (QuotesPojo quote : quotesPojo) {
	            if (!"Special".equalsIgnoreCase(quote.getCtype())) {
	                filteredList.add(quote);
	            }
	        }
	        quotesPojo = filteredList;
	    }
		modelMap.addAttribute("quotesPojo", quotesPojo);

		return "ViewQuotesNew";
	}

	@GetMapping("/getQuotesListNew")
	public String getQuotesListNew(ModelMap modelMap, @RequestParam("page") int page) {
		List<QuotesPojo> quotesPojo = saleService.getQuotesListNew(modelMap, page);

		LeoLogger.info("Sales Controller ---viewSales---" + page);
		// LeoLogger.info("Sales Controller ---viewSales---salesPojo==" +
		// salesPojo.toString());
		
	    String role = CurrentUserUtil.getRole();
	    LeoLogger.info("Logged-in User Role: {}", role);

	    // Apply same logic used in /viewSalesNew
	    if ("custom".equalsIgnoreCase(role) || "cashier".equalsIgnoreCase(role) || "sales".equalsIgnoreCase(role)) {
	        List<QuotesPojo> filteredList = new ArrayList<>();
	        for (QuotesPojo quote : quotesPojo) {
	            if (!"Special".equalsIgnoreCase(quote.getCtype())) {
	                filteredList.add(quote);
	            }
	        }
	        quotesPojo = filteredList;
	    }
		modelMap.addAttribute("quotesPojo", quotesPojo);

		return "ViewQuotesNew";
	}

	@GetMapping("/getQuotesListpage")
	public String getQuotesListpage(ModelMap modelMap, @RequestParam(defaultValue = "0") int page,
			@RequestParam("pageSize") int pageSize) {
		LeoLogger.info("Sales Controller -getQuotesListpage");
		List<QuotesPojo> quotesPojo = saleService.getQuotesListpage(modelMap, page, pageSize);

		LeoLogger.info("Sales Controller ---viewquotes---" + page);
		LeoLogger.info("Sales Controller ---viewquotes---salesPojo==" + quotesPojo.toString());
		modelMap.addAttribute("quotesPojo", quotesPojo);

		return "ViewQuotesNew";
	}

	@GetMapping("/searchQuote")
	public String searchQuote(ModelMap modelMap, @RequestParam("search") String search) {

		List<QuotesPojo> quotesPojo = saleService.getSearchQuotes(modelMap, search);

		LeoLogger.info("Sales Controller ---searchQuote---" + search);
		// LeoLogger.info("Sales Controller ---viewSales---salesPojo==" +
		// salesPojo.toString());
		
	    String role = CurrentUserUtil.getRole();
	    LeoLogger.info("Logged-in User Role: {}", role);

	    // Apply same logic used in /viewSalesNew
	    if ("custom".equalsIgnoreCase(role) || "cashier".equalsIgnoreCase(role) || "sales".equalsIgnoreCase(role)) {
	        List<QuotesPojo> filteredList = new ArrayList<>();
	        for (QuotesPojo quote : quotesPojo) {
	            if (!"Special".equalsIgnoreCase(quote.getCtype())) {
	                filteredList.add(quote);
	            }
	        }
	        quotesPojo = filteredList;
	    }
		modelMap.addAttribute("quotesPojo", quotesPojo);

		return "ViewQuotesNew";
	}

	@GetMapping("/getSaleitembysaleId")
	public @ResponseBody List<SaleItemPojo> getSaleitembysaleId(ModelMap modelMap,
			@RequestParam("saleId") String saleId) {
		 List<SaleItemPojo> salesItemListPojo = saleService.getSalesItembysaleId(saleId, false);
		
		// List<SaleItemPojo> salesItemListPojo = saleService.getSaleItemListBySaleIdRefactored(Long.valueOf(saleId));
		
		
		LeoLogger.info("Sales Controller ---getSaleitembysaleId");
		// LeoLogger.info("Sales Controller
		// ---getSaleitembysaleId---salesItemListPojo==" +
		// salesItemListPojo.toString());
		modelMap.addAttribute("salesItemListPojo", salesItemListPojo.toString());
		return salesItemListPojo;
	}

	@GetMapping("/getSalebyMemberId")
	public @ResponseBody List<SalePojo> getSalebyMemberId(ModelMap modelMap,
			@RequestParam("memberId") String memberId) {
		List<SalePojo> salesListPojo = saleService.getSalesBymemberId(memberId);
		// LeoLogger.info("Sales Controller ---getSalebyMemberId---salesItemListPojo=="
		// + salesListPojo.toString());
		modelMap.addAttribute("salesListPojo", salesListPojo);
		return salesListPojo;
	}

	@GetMapping("/getSpecialSalebyMemberId")
	public @ResponseBody List<SpecialSalesPojo> getSpecialSalebyMemberId(ModelMap modelMap,
			@RequestParam("memberId") String memberId) {
		List<SpecialSalesPojo> specialsalesListPojo = saleService
				.getSpecialSalesListbyMemberId(Long.parseLong(memberId));

		// LeoLogger.info("Sales Controller ---getSalebyMemberId---salesItemListPojo=="
		// + salesListPojo.toString());
		modelMap.addAttribute("specialsalesListPojo", specialsalesListPojo);
		return specialsalesListPojo;
	}

	@GetMapping("/getPendingSalebyMemberId")
	public @ResponseBody List<SalePojo> getPendingSalebyMemberId(ModelMap modelMap,
			@RequestParam("memberId") String memberId) {
		List<SalePojo> salesListPojo = saleService.getPendingSalesBymemberId(memberId);
		// LeoLogger.info("Sales Controller
		// ---getPendingSalebyMemberId---salesItemListPojo==" +
		// salesListPojo.toString());
		modelMap.addAttribute("salesListPojo", salesListPojo);
		return salesListPojo;
	}

	@GetMapping("/getPendingSpecialSalebyMemberId")
	public @ResponseBody List<SpecialSalesPojo> getPendingSpecialSalebyMemberId(ModelMap modelMap,
			@RequestParam("memberId") String memberId) {
		List<SpecialSalesPojo> specialsalesListPojo = saleService.getPendingSpecialSalesBymemberId(memberId);
		// LeoLogger.info("Sales Controller
		// ---getPendingSpeicalSalebyMemberId---salesItemListPojo==" +
		// specialsalesListPojo.toString());
		modelMap.addAttribute("salesListPojo", specialsalesListPojo);
		return specialsalesListPojo;
	}

	@GetMapping("/getQuoteitembyquoteId")
	public @ResponseBody List<QuotesItemPojo> getQuoteitembyquoteId(ModelMap modelMap,
			@RequestParam("quoteId") String quoteId) {
		// LeoLogger.info(quoteId);
		List<QuotesItemPojo> quotesItemListPojo = saleService.getQuotesItembyquoteId(quoteId, false);
		// LeoLogger.info("Sales Controller
		// ---getQuoteitembyquoteId----quotesItemListPojo==" +
		// quotesItemListPojo.toString());
		modelMap.addAttribute("quotesItemListPojo", quotesItemListPojo.toString());
		return quotesItemListPojo;
	}

	@GetMapping("/getRequestQuoteitembyquoteId")
	public @ResponseBody List<RequestQuoteItemEntity> getRequestQuotesitembyquoteId(ModelMap modelMap,
			@RequestParam("quoteId") String quoteId) {
		// LeoLogger.info(quoteId);
		List<RequestQuoteItemEntity> requestquotesItemListPojo = saleService.getRequestQuotesItembyquoteId(quoteId);
		// LeoLogger.info("Sales Controller
		// ---getQuoteitembyquoteId----quotesItemListPojo==" +
		// quotesItemListPojo.toString());
		modelMap.addAttribute("requestquotesItemListPojo", requestquotesItemListPojo.toString());
		return requestquotesItemListPojo;
	}

	@GetMapping(value = "/addSpecialSales")
	public String addSpecialSales(Model model) {
		return "AddSpecialSales";
	}

	@PostMapping("/addSpecialSales")
	@ResponseBody
	public ResultVO addSpecialSales(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info(
				"Sales Controller ---addSpecialSales--- Add item Request Pojo JSON ....." + addItemReqPojos.toString());
		ResultVO resultVO = new ResultVO();
		try {
		// ResultVO resultVO = saleService.addSpecialSale(addItemReqPojos);
		
		 resultVO = saleService.addSpecialSaleRefactored(addItemReqPojos);

		sysAuditService.setSysAudit( Action.ADD_SPECIAL_SALE, resultVO.msgDescr);
		
	}catch (Exception e) {
		LeoLogger.error(">>>> addSpecialSales >>>> error >>>> " + e.getMessage());
		e.printStackTrace();
		resultVO.setError(true);
		resultVO.setMsgDescr("Error occured please contact support team!");		
	}
		

return resultVO;
		
	
	}

	@GetMapping(value = "/viewSpecialSales")
	public String viewSpecialSales(ModelMap modelMap) {
		List<SpecialSalesPojo> specialsalesPojo = saleService.getsaleslist();
		// LeoLogger.info("Sales Controller ---viewSpecialSales---specialsalesPojo==" +
		// specialsalesPojo.toString());
		modelMap.addAttribute("specialsalesPojo", specialsalesPojo);

		return "ViewSpecialSales";
	}

	@GetMapping("/getSpecialSaleitembysaleId")
	public @ResponseBody List<SpecialSalesItemPojo> getSpecialSaleitembysaleId(ModelMap modelMap,
			@RequestParam("saleId") String saleId) {
		List<SpecialSalesItemPojo> specialsalesItemListPojo = saleService.getSpecailSalesItembysaleId(saleId, false);
		
		// List<SpecialSalesItemPojo> specialsalesItemListPojo = saleService.getSpecialSaleItemListBySaleIdRefactored(Long.valueOf(saleId));
		
		
		// LeoLogger.info("Sales Controller 
		// ---getSpecialSaleitembysaleId---specialsalesItemListPojo==" +
		// specialsalesItemListPojo.toString());
		modelMap.addAttribute("specialsalesItemListPojo", specialsalesItemListPojo.toString());
		return specialsalesItemListPojo;
	}

	@PostMapping("/converquotestToSale")
	@ResponseBody
	public ResultVO  converquotestToSale(ModelMap modelMap, @RequestParam("quoteId") String quoteId) {

		// LeoLogger.info("Sales Controller ---converquotestToSale---***********
		// quoteId==" + quoteId);
		LeoLogger.info("Sales Controller ---converquotestToSale---- quoteId:" + quoteId);

		//ResultVO resultVO = saleService.converquotestToSale(quoteId);

		ResultVO resultVO = new ResultVO();		
		try {
		
		 resultVO = saleService.convertQuoteToSaleRefactored(Long.valueOf(quoteId));

	    sysAuditService.setSysAudit( Action.CONVERT_QUOTE_TO_SALE, resultVO.msgDescr);

	 } catch (Exception e) {
		LeoLogger.error(">>>> converquotestToSale >>>> error >>>> " + e.getMessage());
		e.printStackTrace();
		resultVO.setError(true);
		resultVO.setMsgDescr("Error occured please contact support team!");		
	}
		
	    return resultVO;

	}

	@PostMapping("/close")
	public ResultVO close(ModelMap modelMap, @RequestParam("rid") long rid) {

		// LeoLogger.info("Sales Controller ---close---*********** rid==" + rid);

		ResultVO resultVO = saleService.closeregister(rid);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			// return "ViewRegister";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			// return "redirect:/viewRegister";
		}
		return resultVO;

	}

	@PostMapping("/EditDetails")
	public String EditDetails(@ModelAttribute("requestQuotesItems") RequestQuotesItemsPojo requestQuotesItemsPojo,
			ModelMap modelMap) throws IOException {
		LeoLogger.info("\"Sales Controller ---EditDetails");

		// LeoLogger.info("\"Sales Controller ---EditDetails---requestQuotesItemsPojo
		// "+requestQuotesItemsPojo.getMpn() + requestQuotesItemsPojo.toString());

		ResultVO resultVO = new ResultVO();

		resultVO = saleService.updateEditDetails(requestQuotesItemsPojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:viewRequestQuote";
	}

	@GetMapping(value = "/editSales")
	public String editSales(Model model, @RequestParam("id") long id, @RequestParam("ctype") String ctype,
			ModelMap modelMap) {

		LeoLogger.info("\"Sales Controller ---editSales");
		// LeoLogger.info("Sales Controller ---editSales---Ctype >>>>>>>>" + ctype);
		SalePojo salePojo = new SalePojo();

		if (!ctype.equalsIgnoreCase("special")) {
			salePojo = saleService.getsalebySaleId(id);
			// LeoLogger.info("Sales Controller ---editSales---SalePojo >>>>>>>>" +
			// salePojo.toString());
		} else {
			salePojo = saleService.getSpecialsalebySaleId(id);
			// LeoLogger.info("Sales Controller ---editSales---SalePojo >>>>>>>>" +
			// salePojo.toString());
		}

		UserRegistrationPojo memberPojo = saleService.getMemberByMemberid(salePojo.getMemberid());
		// LeoLogger.info("Sales Controller ---editSales---MemberPojo >>>>>>>>" +
		// memberPojo.toString());

		List<SaleItemPojo> saleItemList = saleService.getSalesItembysaleId(String.valueOf(id), false);
		// LeoLogger.info("Sales Controller ---editSales---saleItemList >>>>>>>>" +
		// saleItemList.toString());

		modelMap.addAttribute("salePojo", salePojo);
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("saleItemList", saleItemList);

		return "EditSales";
	}

	@PostMapping("/updateSales")
	@ResponseBody
	public ResultVO updateSales(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {
		// LeoLogger.info("Sales Controller ---updateSales");
		LeoLogger.info(
				"Sales Controller ---updateSales--- Add item Request Pojo JSON ....." + addItemReqPojos.toString());
		ResultVO resultVO = new ResultVO();
   try {
		// ResultVO resultVO = saleService.updateSale(addItemReqPojos);
		
		 resultVO = saleService.updateSaleRefactored(addItemReqPojos);

		sysAuditService.setSysAudit( Action.UPDATE_SALE, resultVO.msgDescr);

	} catch (Exception e) {
		LeoLogger.error(">>>> updateSales >>>> error >>>> " + e.getMessage());
		e.printStackTrace();
		resultVO.setError(true);
		resultVO.setMsgDescr("Error occured please contact support team!");		
	}		

  return resultVO;
	}

	@PostMapping("/updateSpecialSales")
	@ResponseBody
	public ResultVO updateSpecialSales(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info("Sales Controller ---updateSpecialSales--- Add item Request Pojo JSON ....."
				+ addItemReqPojos.toString());
		ResultVO resultVO = new ResultVO();	
   try {
		//ResultVO resultVO = saleService.updateSpecialSales(addItemReqPojos);
		
		 resultVO = saleService.updateSpecialSaleRefactored(addItemReqPojos);

		sysAuditService.setSysAudit(Action.UPDATE_SPECIAL_SALE, resultVO.msgDescr);
		
	} catch (Exception e) {
		LeoLogger.error(">>>> updateSpecialSales >>>> error >>>> " + e.getMessage());
		e.printStackTrace();
		resultVO.setError(true);
		resultVO.setMsgDescr("Error occured please contact support team!");		
	}
		

return resultVO;
	}

	@GetMapping(value = "/editQuotes")
	public String editQuotes(Model model, @RequestParam("id") long id, ModelMap modelMap) {
		LeoLogger.info("Sales Controller ---editQuotes");

		QuotesPojo quotesPojo = saleService.getquotebyquotesId(id);
		// LeoLogger.info("Sales Controller ---editQuotes--QuotesPojoo >>>>>>>>" +
		// quotesPojo.toString());

		UserRegistrationPojo memberPojo = saleService.getMemberByMemberid(quotesPojo.getMemberid());
		// LeoLogger.info("Sales Controller ---editQuotes---MemberPojo >>>>>>>>" +
		// memberPojo.toString());

		List<QuotesItemPojo> quotesItemList = saleService.getQuotesItembyquoteId(String.valueOf(id));
		// LeoLogger.info("Sales Controller ---editQuotes--quotesItemList >>>>>>>>" +
		// quotesItemList.toString());

		modelMap.addAttribute("quotesPojo", quotesPojo);
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("quotesItemList", quotesItemList);

		return "EditQuotes";
	}

	@PostMapping("/updateQuotes")
	@ResponseBody
	public ResultVO updateQuotes(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info(
				"Sales Controller ---updateQuotes-- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

	//	ResultVO resultVO = saleService.updateQuotes(addItemReqPojos);
		
		ResultVO resultVO = saleService.updateQuoteRefactored(addItemReqPojos);
		
		sysAuditService.setSysAudit( Action.UPDATE_QUOTE, resultVO.msgDescr);

		return resultVO;

	}

	@GetMapping(value = "/editRequetQuotes")
	public String editRequetQuotes(Model model, @RequestParam("id") long id, ModelMap modelMap) {
		LeoLogger.info("Sales Controller ---editQuotes");

		RequestQuotePojo requestquotesPojo = saleService.getrequestquotebyquotesId(id);
		// LeoLogger.info("Sales Controller ---editQuotes--QuotesPojoo >>>>>>>>" +
		// quotesPojo.toString());

		// UserRegistrationPojo memberPojo =
		// saleService.getMemberByMemberid(quotesPojo.getMemberid());
		// LeoLogger.info("Sales Controller ---editQuotes---MemberPojo >>>>>>>>" +
		// memberPojo.toString());

		// List<QuotesItemPojo> quotesItemList =
		// saleService.getQuotesItembyquoteId(String.valueOf(id));
		// LeoLogger.info("Sales Controller ---editQuotes--quotesItemList >>>>>>>>" +
		// quotesItemList.toString());

		modelMap.addAttribute("requestquotesPojo", requestquotesPojo);
		// modelMap.addAttribute("memberPojo", memberPojo);
		// modelMap.addAttribute("quotesItemList", quotesItemList);

		return "EditRequestQuote";
	}

	@PostMapping("/updaterequestQuote")
	@ResponseBody
	public ResultVO updaterequestQuote(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info("Sales Controller ---updaterequestQuote-- Add item Request Pojo JSON ....."
				+ addItemReqPojos.toString());

		ResultVO resultVO = saleService.updaterequestQuote(addItemReqPojos);

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

	@GetMapping(value = "/salesReport")
	public String salesReport(ModelMap modelMap, @RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		LeoLogger.info("Sales Controller ---salesReport");
		modelMap.addAttribute("salesReport", saleService.findAllSaleSummary(startDate, endDate));
		return "SalesReport";
	}
	
	/*
	@GetMapping(value = "/monthsalesReport")
	public String monthsalesReport(ModelMap modelMap, @RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		LeoLogger.info("Sales Controller ---lastmonthsalesReport");
		modelMap.addAttribute("salesReport", saleService.findSaleSummary(startDate, endDate));
		LeoLogger.info("Sales Controller ---lastmonthsalesReport" +saleService.findSaleSummary(startDate, endDate));
		return "SaleLastMonthReport";
	} */
	
	@GetMapping(value = "/monthsalesReport")
	public String monthsalesReport(ModelMap modelMap,
	                               @RequestParam(value = "startDate", required = false) String startDate,
	                               @RequestParam(value = "endDate", required = false) String endDate) {
	    LeoLogger.info("Sales Controller --- monthsalesReport");

	    try {
	     
	        modelMap.addAttribute("salesReport", saleService.findSaleSummary(startDate, endDate));
	     
	    } catch (Exception e) {
	        LeoLogger.error("Error occurred while fetching sales report", e);
	        modelMap.addAttribute("errorMessage", "An error occurred while generating the sales report. Please try again later.");
	       
	    }

	    return "SaleLastMonthReport";
	}
	
	@GetMapping(value = "/monthspecialsalesReport")
	public String monthspecialsalesReport(ModelMap modelMap, @RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		LeoLogger.info("Sales Controller ---lastmonthsalesReport");
		modelMap.addAttribute("salesReport", saleService.findSpecialSaleSummary(startDate, endDate));
		return "SpecialSaleLastMonthReport";
	}
	@GetMapping(value = "/replenishmentReport")
	public String replenishmentReport(ModelMap modelMap, @RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		LeoLogger.info("Sales Controller ---ReplenishmentReport");
		LeoLogger.info("Sales Controller ---ReplenishmentRepot startDate"+startDate);
		LeoLogger.info("Sales Controller ---ReplenishmentRepot endDate"+endDate);
		modelMap.addAttribute("replenishmentReport", saleService.findReplenishmentRepotPojo(startDate, endDate));
		return "ReplenishmentReport";
	}

	@GetMapping(value = "/customerPurchaseReport")
	public String customerPurchaseReport(ModelMap modelMap,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		LeoLogger.info("Sales Controller ---customerPurchaseReport");
		
		modelMap.addAttribute("customerPurchaseReport", saleService.findAllCustomerPurchase(startDate, endDate));
		return "CustomerPurchaseReport";
	}
	
	
	@GetMapping(value = "/getSaleID")
	public String getSaleID(Model model, @RequestParam("id") String id, ModelMap modelMap) {
		LeoLogger.info("Sales Controller ---customerPurchaseReport");

		ArrayList<String> valuesList = new ArrayList<>();
		
		List<SaleItemPojo> salesItemListPojos = new ArrayList<>();
		List<SalePojo> salePojoList = new ArrayList<>();
		List<CustomerPurchaseSalePojo> combinedData = new ArrayList<>();
	
		//List<Object> combinedList = new ArrayList<>();
		String values = id;
		String[] commaSeparatedString = values.split(",");
		for (String value : commaSeparatedString) {
			// Do something with each value
			valuesList.add(value);
			System.out.println(value);
			
				 LeoLogger.info("Sales Controller ---customerPurchaseReport reached");
					List<SaleItemPojo> salesItem  = saleService.getSalesItembysaleId(value, false);
				 salesItemListPojos.addAll(salesItem);
				 LeoLogger.info("Sales Controller ---customerPurchaseReport combinedList" + salesItem);
					List<SalePojo> saleItem  = saleService.getSaleidList(value);
					 salePojoList.addAll(saleItem);
					  LeoLogger.info("Sales Controller ---customerPurchaseReport saleItem" + saleItem);
					   
		}
		LeoLogger.info("Sales Controller ---customerPurchaseReport salesItemListPojo" +salesItemListPojos);
		LeoLogger.info("Sales Controller ---customerPurchaseReport salePojoList" + salePojoList);
	//	List<CustomerPurchaseSalePojo> combinedList = combinedData(salesItemListPojos, salePojoList);
		  
       // model.addAttribute("combinedList", combinedList);
		//combinedList.addAll(salesItemListPojos);
		//combinedList.addAll(salePojoList);
		 //LeoLogger.info("Sales Controller ---customerPurchaseReport combinedList1" + combinedList);
		modelMap.addAttribute("valuesList", valuesList);
		modelMap.addAttribute("salesItemListPojo", salesItemListPojos);
		modelMap.addAttribute("salePojoList", salePojoList);
		return "getSaleID";
			    

		}
	
	@GetMapping(value = "/specialcustomerPurchaseReport")
	public String specialcustomerPurchaseReport(ModelMap modelMap,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		LeoLogger.info("Sales Controller ---specialcustomerPurchaseReport");
		
		modelMap.addAttribute("customerPurchaseReport", saleService.findAllSpecialCustomerPurchase(startDate, endDate));
		return "CustomerPurchaseReportSpecial";
	}
	
	@GetMapping(value = "/getSpecialSaleID")
	public String getSpecialSaleID(Model model, @RequestParam("id") String id, ModelMap modelMap) {
		LeoLogger.info("Sales Controller ---customerPurchaseReport");

		ArrayList<String> valuesList = new ArrayList<>();
		
		List<SpecialSalesItemPojo> salesItemListPojos = new ArrayList<>();
		List<SpecialSalesPojo> salePojoList = new ArrayList<>();
		
	
		//List<Object> combinedList = new ArrayList<>();
		String values = id;
		String[] commaSeparatedString = values.split(",");
		for (String value : commaSeparatedString) {
			// Do something with each value
			valuesList.add(value);
			System.out.println(value);
			
				 LeoLogger.info("Sales Controller ---customerPurchaseReport reached");
					List<SpecialSalesItemPojo> salesItem = saleService.getSpecailSalesItembysaleId(value, false);
				 salesItemListPojos.addAll(salesItem);
				 LeoLogger.info("Sales Controller ---customerPurchaseReport combinedList" + salesItem);
					List<SpecialSalesPojo> saleItem  = saleService.getsaleslist(value);
					 salePojoList.addAll(saleItem);
					  LeoLogger.info("Sales Controller ---customerPurchaseReport saleItem" + saleItem);
					   
		}
		LeoLogger.info("Sales Controller ---customerPurchaseReport salesItemListPojo" +salesItemListPojos);
		LeoLogger.info("Sales Controller ---customerPurchaseReport salePojoList" + salePojoList);
	//	List<CustomerPurchaseSalePojo> combinedList = combinedData(salesItemListPojos, salePojoList);
		  
       // model.addAttribute("combinedList", combinedList);
		//combinedList.addAll(salesItemListPojos);
		//combinedList.addAll(salePojoList);
		 //LeoLogger.info("Sales Controller ---customerPurchaseReport combinedList1" + combinedList);
		modelMap.addAttribute("valuesList", valuesList);
		modelMap.addAttribute("salesItemListPojo", salesItemListPojos);
		modelMap.addAttribute("salePojoList", salePojoList);
		return "getSpecialSaleID";
			    

		}
		

	@GetMapping(value = "/salesInvoiceReport")
	public String salesInvoiceReport(Model model) {
		return "SalesInvoiceReport";
	}

	@PostMapping("/saleInvoiceReport")
	public ResponseEntity<List<SaleInvoiceReportPojo>> getSalesInvoiceReport(
			@RequestParam(value = "start", required = false) Long start,
			@RequestParam(value = "end", required = false) Long end,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate filterDate) {
		try {
			List<SaleInvoiceReportPojo> reports = saleInvoiceReportService.getSalesInvoiceReportBetween(start, end,
					filterDate);
			return ResponseEntity.ok(reports);
		} catch (Exception e) {
			LeoLogger.error("error while generating sale invoice report", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}






	@GetMapping(value = "/customerReport")
	public String customerReport(ModelMap modelMap) {

		modelMap.addAttribute("customerReport", saleService.findAllCustomerSummary());
		// LeoLogger.info("Sales Controller
		// ---customerReport"+saleService.findAllCustomerSummary());
		return "CustomerReport";
	}
	
	@GetMapping(value = "/customerReportForCashier")
	public String customerReportForCashier(ModelMap modelMap) {

		modelMap.addAttribute("customerReport", saleService.findAllCustomerSummary());
		// LeoLogger.info("Sales Controller
		// ---customerReport"+saleService.findAllCustomerSummary());
		return "CustomerReportForCashier";
	}

	@GetMapping(value = "/profitlossReport")
	public String profitlossReport(ModelMap modelMap) {
		LeoLogger.info("Sales Controller ---profitlossReport");
		//modelMap.addAttribute("profitlossReport", saleService.findAllProfitLossSummary());
		return "ProfitLossReport";
	}

	//@GetMapping(value = "/paymentReport")
	public String paymentReport(ModelMap modelMap) {
		LeoLogger.info("Sales Controller ---getCustomer---paymentPojo==");
		modelMap.addAttribute("paymentPojo", saleService.findPayemntReport());
		LeoLogger.info("Sales Controller ---paymentReport" +saleService.findPayemntReport());
	
		return "PaymentReport";
	}
	
	@GetMapping("/paymentReport")
	public String paymentReport(
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
	        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "20") int size,
	        @RequestParam(required = false, defaultValue = "false") boolean export,
	        HttpServletResponse response,
	        ModelMap modelMap) throws IOException {

	    String method = "paymentReport";
	    

	    LeoLogger.info("[{}]  Request received | fromDate: {}, toDate: {}, page: {}, size: {}, export: {}",
	            method, fromDate, toDate, page, size, export);

	    if (fromDate == null) fromDate = LocalDate.now();
	    if (toDate == null) toDate = LocalDate.now();

	    Date startDate = Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	    Date endDate = Date.from(
	            toDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()
	    );

	    //  EXPORT 
	    if (export) {
	        LeoLogger.info("[{}] Export requested", method);

	        List<PaymentReportPojo> fullData =
	                saleService.getFullPaymentReport(startDate, endDate);

	        exportPaymentExcel(fullData, response);
	        return null;
	    }

	    // NORMAL PAGINATION
	    Page<PaymentReportPojo> paymentPage =
	            saleService.findPayemntReport(startDate, endDate, page, size);

	    modelMap.addAttribute("paymentPojo", paymentPage.getContent());
	    modelMap.addAttribute("currentPage", page);
	    modelMap.addAttribute("totalPages", paymentPage.getTotalPages());
	    modelMap.addAttribute("fromDate", fromDate);
	    modelMap.addAttribute("toDate", toDate);

	    return "PaymentReport";
	}
	
	
	private void exportPaymentExcel(List<PaymentReportPojo> list, HttpServletResponse response) throws IOException {

	    String method = "exportPaymentExcel";
	    LeoLogger.info("[{}] Export started | total records: {}", method, list.size());

	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Payment Report");

	    // Header
	    String[] cols = { "Id", "BulkId", "Amount", "Type", "MemberId", "Member Name", "Date" };
	    XSSFRow header = sheet.createRow(0);

	    for (int i = 0; i < cols.length; i++) {
	        header.createCell(i).setCellValue(cols[i]);
	    }

	    // Date style
	    XSSFCreationHelper createHelper = workbook.getCreationHelper();
	    XSSFCellStyle dateStyle = workbook.createCellStyle();
	    dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));

	    int rowNum = 1;

	    for (PaymentReportPojo p : list) {
	        XSSFRow row = sheet.createRow(rowNum++);

	        row.createCell(0).setCellValue(p.getId());
	        row.createCell(1).setCellValue(p.getBulkid());
	        row.createCell(2).setCellValue(p.getGrand_total());
	        row.createCell(3).setCellValue(p.getPtype());
	        row.createCell(4).setCellValue(p.getMember_id());
	        row.createCell(5).setCellValue(p.getMember_name());

	        XSSFCell dateCell = row.createCell(6);
	        dateCell.setCellValue(p.getPaymentdate());
	        dateCell.setCellStyle(dateStyle);
	    }

	    // Auto size
	    for (int i = 0; i < cols.length; i++) {
	        sheet.autoSizeColumn(i);
	    }

	    // Response
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

	    String fileName = "PaymentReport_" + LocalDate.now() + ".xlsx";
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

	    try (ServletOutputStream out = response.getOutputStream()) {
	        workbook.write(out);
	    }

	    workbook.close();

	    LeoLogger.info("[{}] Excel generated successfully", method);
	}
	
	
	
	
	@GetMapping(value = "/viewSalesreceipt")
	public String viewSalesreceipt(ModelMap modelMap, @RequestParam("id") long id, @RequestParam("ctype") String ctype,
			Model model) {

		// LeoLogger.info("Sales Controller ---viewSalesreceipt--Ctype >>>>>>>>" +
		// ctype);
		SalePojo salePojo = new SalePojo();

		if (!ctype.equalsIgnoreCase("special")) {
			salePojo = saleService.getsalebySaleId(id);
			List<SaleItemPojo> saleItemList = saleService.getSalesItembysaleId(String.valueOf(id), false);
			// LeoLogger.info("Sales Controller ---viewSalesreceipt--saleItemList >>>>>>>>"
			// + saleItemList.toString());
			// LeoLogger.info("Sales Controller ---viewSalesreceipt--SalePojo >>>>>>>>" +
			// salePojo.toString());
			modelMap.addAttribute("saleItemList", saleItemList);
		} else {
			salePojo = saleService.getSpecialsalebySaleId(id);
			List<SpecialSalesItemPojo> saleItemList = saleService.getSpecailSalesItembysaleId(String.valueOf(id),
					false);
			// LeoLogger.info("Sales Controller ---viewSalesreceipt---specialsaleItemList
			// >>>>>>>>" + saleItemList.toString());
			// LeoLogger.info("Sales Controller ---viewSalesreceipt---SalePojo >>>>>>>>" +
			// salePojo.toString());
			modelMap.addAttribute("saleItemList", saleItemList);
		}

		UserRegistrationPojo memberPojo = saleService.getMemberByMemberid(salePojo.getMemberid());
		LeoLogger.info("Sales Controller ---viewSalesreceipt---MemberPojo >>>>>>>>" + memberPojo.toString());
		// List<SaleItemPojo> saleItemList =
		// saleService.getSalesItembysaleId(String.valueOf(id),false);
		// LeoLogger.info("saleItemList >>>>>>>>" + saleItemList.toString());

		// if(!ctype.equalsIgnoreCase("special")) {

		// }
		// else
		/*
		 * {
		 * 
		 * List<SpecialSalesItemPojo> specialsaleItemList =
		 * saleService.getSpecailSalesItembysaleId(String.valueOf(id),false);
		 * LeoLogger.info("specialsaleItemList >>>>>>>>" +
		 * specialsaleItemList.toString());
		 * 
		 * modelMap.addAttribute("specialsaleItemList", specialsaleItemList); }
		 */

		modelMap.addAttribute("salePojo", salePojo);
		modelMap.addAttribute("memberPojo", memberPojo);

		return "ViewSalesreceipt";
	}

	@GetMapping(value = "/salesReceipt")
	public String salesReceipt(ModelMap modelMap, @RequestParam("id") long id, @RequestParam("ctype") String ctype,
			Model model) {

		// LeoLogger.info("Sales Controller ---salesReceipt---Ctype >>>>>>>>" + ctype);
		SalePojo salePojo = new SalePojo();

		if (!ctype.equalsIgnoreCase("special")) {
			salePojo = saleService.getsalebySaleId(id);
			List<SaleItemPojo> saleItemList = saleService.getSalesItembysaleId(String.valueOf(id), true);
			// LeoLogger.info("Sales Controller ---salesReceipt---saleItemList >>>>>>>>" +
			// saleItemList.toString());
			// LeoLogger.info("Sales Controller ---salesReceipt---SalePojo >>>>>>>>" +
			// salePojo.toString());
			modelMap.addAttribute("saleItemList", saleItemList);
		} else {
			salePojo = saleService.getSpecialsalebySaleId(id);
			List<SpecialSalesItemPojo> saleItemList = saleService.getSpecailSalesItembysaleId(String.valueOf(id), true);
			// LeoLogger.info("Sales Controller ---salesReceipt---specialsaleItemList
			// >>>>>>>>" + saleItemList.toString());
			// LeoLogger.info("Sales Controller ---salesReceipt---SalePojo >>>>>>>>" +
			// salePojo.toString());
			modelMap.addAttribute("saleItemList", saleItemList);
		}

		UserRegistrationPojo memberPojo = saleService.getMemberByMemberid(salePojo.getMemberid());
		// LeoLogger.info("Sales Controller ---salesReceipt---MemberPojo >>>>>>>>" +
		// memberPojo.toString());

		// if(!ctype.equalsIgnoreCase("special")) {

		// }
		// else
		/*
		 * {
		 * 
		 * List<SpecialSalesItemPojo> specialsaleItemList =
		 * saleService.getSpecailSalesItembysaleId(String.valueOf(id),true);
		 * LeoLogger.info("specialsaleItemList >>>>>>>>" +
		 * specialsaleItemList.toString());
		 * 
		 * modelMap.addAttribute("specialsaleItemList", specialsaleItemList); }
		 */

		modelMap.addAttribute("salePojo", salePojo);
		modelMap.addAttribute("memberPojo", memberPojo);
		// modelMap.addAttribute("saleItemList", saleItemList);
		return "SalesReceipt";

	}

	@GetMapping(value = "/getSaleitembysaleIdten")
	public @ResponseBody List<SaleItemPojo> getSaleitembysaleIdten(ModelMap modelMap, @RequestParam("saleId") long id,
			@RequestParam("ctype") String ctype, Model model) {
		LeoLogger.info("Sales Controller ---salesReceipt");
		// LeoLogger.info("Sales Controller ---salesReceipt---Ctype >>>>>>>>" + ctype);

		List<SaleItemPojo> saleItemList = new ArrayList<>();

		if (!ctype.equalsIgnoreCase("special")) {

			saleItemList = saleService.getSalesItembysaleId(String.valueOf(id), true);
			// LeoLogger.info("Sales Controller ---salesReceipt---saleItemList >>>>>>>>" +
			// saleItemList.toString());
		}

		return saleItemList;

	}

	@GetMapping(value = "/getSpecialSaleitembysaleIdten")
	public @ResponseBody List<SpecialSalesItemPojo> getSpecialSaleitembysaleIdten(ModelMap modelMap,
			@RequestParam("saleId") long id, @RequestParam("ctype") String ctype, Model model) {
		LeoLogger.info("Sales Controller ---SpecialsalesReceipt");
		// LeoLogger.info("Sales Controller ---salesReceipt---Ctype >>>>>>>>" + ctype);

		List<SpecialSalesItemPojo> saleItemList = new ArrayList<>();

		if (ctype.equalsIgnoreCase("special")) {
			saleItemList = saleService.getSpecailSalesItembysaleId(String.valueOf(id), true);
			 LeoLogger.info("Sales Controller ---salesReceipt---specialsaleItemList>>>>>>>>" + saleItemList.toString());
		}

		return saleItemList;

	}

	@GetMapping(value = "/viewQuotesreceipt")
	public String viewQuotesreceipt(ModelMap modelMap, @RequestParam("id") long id, @RequestParam("ctype") String ctype,
			Model model) {

		// LeoLogger.info("Sales Controller ---viewQuotesreceipt---Ctype >>>>>>>>" +
		// ctype);
		QuotesPojo quotesPojo = new QuotesPojo();

		// if(!ctype.equalsIgnoreCase("special"))
		// {
		quotesPojo = saleService.getquotesbyquotesId(id);
		// LeoLogger.info("Sales Controller ---viewQuotesreceipt---quotesPojo>>>>>>>>"
		// +quotesPojo.toString());
		// }
		// else
		// {
		// quotesPojo= saleService.getSpecialsalebyquotesId(id);
		// LeoLogger.info("quotesPojo >>>>>>>>" + quotesPojo.toString());
		// }

		UserRegistrationPojo memberPojo = saleService.getMemberByMemberid(quotesPojo.getMemberid());
		// LeoLogger.info("Sales Controller ---viewQuotesreceipt---MemberPojo >>>>>>>>"
		// + memberPojo.toString());

		List<QuotesItemPojo> quotesItemList = saleService.getQuotesItembyquoteId(String.valueOf(id), false);
		// LeoLogger.info("Sales Controller ---viewQuotesreceipt---quotesItemList
		// >>>>>>>>" + quotesItemList.toString());

		modelMap.addAttribute("quotesPojo", quotesPojo);
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("quotesItemList", quotesItemList);
		return "ViewQuotesreceipt";
	}

	@GetMapping(value = "/quotesReceipt")
	public String quotesReceipt(ModelMap modelMap, @RequestParam("id") long id, @RequestParam("ctype") String ctype,
			Model model) {
		// LeoLogger.info("Sales Controller ---quotesReceipt");
		// LeoLogger.info("Sales Controller ---quotesReceipt---Ctype >>>>>>>>" + ctype);
		QuotesPojo quotesPojo = new QuotesPojo();

		// if(!ctype.equalsIgnoreCase("special"))
		// {
		quotesPojo = saleService.getquotesbyquotesId(id);
		// LeoLogger.info("Sales Controller ---quotesReceipt---quotesPojo>>>>>>>>"
		// +quotesPojo.toString());
		// }
		// else
		// {
		// quotesPojo= saleService.getSpecialsalebyquotesId(id);
		// LeoLogger.info("quotesPojo >>>>>>>>" + quotesPojo.toString());
		// }

		UserRegistrationPojo memberPojo = saleService.getMemberByMemberid(quotesPojo.getMemberid());
		// LeoLogger.info("Sales Controller ---quotesReceipt---MemberPojo >>>>>>>>" +
		// memberPojo.toString());

		List<QuotesItemPojo> quotesItemList = saleService.getQuotesItembyquoteId(String.valueOf(id), true);
		// LeoLogger.info("Sales Controller ---quotesReceipt---quotesItemList >>>>>>>>"
		// + quotesItemList.toString());

		modelMap.addAttribute("quotesPojo", quotesPojo);
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("quotesItemList", quotesItemList);
		return "QuotesReceipt";
	}

	@GetMapping(value = "/getQuoteitembyquoteIdTen")
	public @ResponseBody List<QuotesItemPojo> getQuoteitembyquoteIdTen(ModelMap modelMap,
			@RequestParam("quoteId") long id, @RequestParam("ctype") String ctype, Model model) {
		LeoLogger.info("Sales Controller ---quotesReceipt");
		// LeoLogger.info("Sales Controller ---quotesReceipt---Ctype >>>>>>>>" + ctype);

		List<QuotesItemPojo> quotesItemList = saleService.getQuotesItembyquoteId(String.valueOf(id), true);
		// LeoLogger.info("Sales Controller ---quotesReceipt---quotesItemList >>>>>>>>" + quotesItemList.toString());

		modelMap.addAttribute("quotesItemList", quotesItemList);
		return quotesItemList;
	}

	@GetMapping(value = "/totalSalesReport")
	public String totalSalesReport(ModelMap modelMap,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "cType", required = false) String cType) {
		modelMap.addAttribute("salesReport", saleService.findAllTotalSaleReport(startDate, endDate, cType));
		return "TotalSalesReport";
	}
	
	
	@GetMapping(value = "/getInvoiceId")
	public String getInvoiceId(
	        Model model,
	        @RequestParam("id") String id,
	        @RequestParam("referenceno") String referenceno,
	        @RequestParam("productid") Long productId,
	        ModelMap modelMap) {
	  
	    LeoLogger.info("getInvoiceId ---id" + id);
	    LeoLogger.info("getInvoiceId ---productId" +  productId);
	    
	    ArrayList<String> valuesList = new ArrayList<>();

	    List<SaleItemPojo> salesItemListPojos = new ArrayList<>();
	    List<SalePojo> salePojoList = new ArrayList<>();
	    List<SpecialSalesItemPojo> specialSalesItemListPojos = new ArrayList<>();
	    List<SpecialSalesPojo> specialSalePojoList = new ArrayList<>();

	    String values = id; 
	    String[] commaSeparatedString = values.split(",");
	    for (String value : commaSeparatedString) {
	        valuesList.add(value);
	       

	        // Special Sales
	     
	           
	            List<SpecialSalesItemPojo> specialSalesItem = saleService.getSpecailSalesItembysaleId(value, false);
	            specialSalesItemListPojos.addAll(specialSalesItem);
	           // LeoLogger.info("Sales Controller ---customerPurchaseReport combinedList" + specialSalesItem);
	           List<SpecialSalesPojo> specialSaleItem = saleService.getsaleslist(value);
	            specialSalePojoList.addAll(specialSaleItem);
	           // LeoLogger.info("Sales Controller ---customerPurchaseReport saleItem" + specialSaleItem);
	      
	        	// Regular Sales
	        	 List<SaleItemPojo> salesItem = saleService.getSalesItembysaleId(value, false);
	 	        salesItemListPojos.addAll(salesItem);
	 	        //LeoLogger.info("Sales Controller ---getInvoiceId combinedList" + salesItem);
	 	        List<SalePojo> saleItem = saleService.getSaleidList(value);
	 	        salePojoList.addAll(saleItem);
	 	       // LeoLogger.info("Sales Controller ---getInvoiceId saleItem" + saleItem);
	        
	    }

	    LeoLogger.info("Sales Controller ---getInvoiceId salesItemListPojo" + salesItemListPojos);
	    LeoLogger.info("Sales Controller ---getInvoiceId salePojoList" + salePojoList);
	    LeoLogger.info("Sales Controller ---getInvoiceId specialSalesItemListPojo" + specialSalesItemListPojos);
	    LeoLogger.info("Sales Controller ---getInvoiceId specialSalePojoList" + specialSalePojoList);

	    modelMap.addAttribute("valuesList", valuesList);
	    modelMap.addAttribute("productId", productId);
	    modelMap.addAttribute("referenceno", referenceno);
	    modelMap.addAttribute("salesItemListPojo", salesItemListPojos);
	    modelMap.addAttribute("salePojoList", salePojoList);
	    modelMap.addAttribute("specialSalesItemListPojo", specialSalesItemListPojos);
	    modelMap.addAttribute("specialSalePojoList", specialSalePojoList);

	    return "getInvoiceIdForSalesReport";
	}


	@GetMapping(value = "/totalSpecialSalesReport")
	public String totalSpecialSalesReport(ModelMap modelMap,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		// LeoLogger.info(" Sales Controller ---total special sale report .....start
		// date:{} end date:{}", startDate,endDate);
		modelMap.addAttribute("salesReport", saleService.findAllTotalSpecialSaleReport(startDate, endDate));
		return "TotalSpecialSalesReport";
	}

	@PostMapping("/deleteSale")
	public @ResponseBody ResultVO deleteSale(@RequestParam("id") long id) {
		//ResultVO resultVO = saleService.deleteSale(id);
		ResultVO resultVO = saleService.deleteSaleRefactored(id);
		
		sysAuditService.setSysAudit(Action.DELETE_SALE, resultVO.msgDescr);
		
		return resultVO;
	}

	@PostMapping("/deleteSpecialSale")
	public @ResponseBody ResultVO deleteSpecialSale(@RequestParam("id") long id) {
	//	ResultVO resultVO = saleService.deleteSpecialSale(id);
		ResultVO resultVO = saleService.deleteSpecialSaleRefactored(id);
		
		sysAuditService.setSysAudit( Action.DELETE_SPECIAL_SALE, resultVO.msgDescr);
		
		return resultVO;
	}

	@GetMapping("/getPaymentsaleid")
	public @ResponseBody List<PaymentPojo> paymentPojoo(ModelMap modelMap, @RequestParam("SaleId") long saleId,
			@RequestParam("ctype") String ctype) {
		LeoLogger.info("Sales Controller ---getCustomer---paymentPojo==" + saleId);
		List<PaymentPojo> paymentPojo = saleService.getPaymentListbySaleId(saleId, ctype);
		// LeoLogger.info("Sales Controller ---getCustomer---customerPojo==" +
		// customerPojo.toString());
		return paymentPojo;
	}
	
	
	@GetMapping("/getPaymentid")
	public @ResponseBody PaymentPojo paymentidPojo(ModelMap modelMap, @RequestParam("id") long id) {
		LeoLogger.info("Sales Controller ---getCustomer---paymentPojo==" + id);
		PaymentPojo paymentPojo = saleService.getPaymentbyId(id);
		 LeoLogger.info("Sales Controller ---getCustomer---paymentPojo==" +paymentPojo);
		return paymentPojo;
	}
	
	@GetMapping("/getPaymentbulkid")
	public @ResponseBody List<PaymentPojo> paymentbulkidPojo(ModelMap modelMap, @RequestParam("bulkid") long bulkid) {
		LeoLogger.info("Sales Controller ---getCustomer---paymentPojo==" + bulkid);
		List<PaymentPojo> paymentPojo  = saleService.getPaymentListbyBulkId(bulkid);
		 LeoLogger.info("Sales Controller ---getCustomer---paymentPojo==" +paymentPojo);
		return paymentPojo;
	}
	@GetMapping("/download-customer-pdf")
	public ResponseEntity<byte[]> generateCustomerPdfReport() {
	    ResponseEntity<byte[]> docRes = null;
	    try {
	        List<CustomerReportPojo> allCustomerSummary = saleService.findAllCustomerSummary();

	        // Sort by User ID
	        allCustomerSummary.sort(Comparator.comparing(CustomerReportPojo::getId));

	        Document document = new Document();
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        PdfWriter writer = PdfWriter.getInstance(document, out);

	        // Add footer event
	        writer.setPageEvent(new PdfPageEventHelper() {
	            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY);
	            @Override
	            public void onEndPage(PdfWriter writer, Document document) {
	                PdfContentByte cb = writer.getDirectContent();
	                Phrase footer = new Phrase("Generated by System | Page " + writer.getPageNumber(), footerFont);
	                ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,footer,
	                        (document.right() + document.left()) / 2,
	                        document.bottom() - 10, 0);
	            }
	        });

	        document.open();

	        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
	        Paragraph title = new Paragraph("Customer Report", titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        title.setSpacingAfter(20f);
	        document.add(title);

	        String[] headers = {"User_id", "Name", "Email", "Phone", "Total_Sale", "Total_Amount", "Total_Paid", "Balance"};
	        PdfPTable table = new PdfPTable(headers.length);
	        table.setWidthPercentage(100);
	        table.setSpacingBefore(10f);
	        table.setSpacingAfter(10f);
	        table.setWidths(new float[]{1.2f, 2.5f, 4f, 2.5f, 2f, 2f, 2f, 2f});
	        table.setHeaderRows(1); // Repeat header row on each page

	        // Add headers
	        for (String header : headers) {
	            PdfPCell cell = new PdfPCell(new Phrase(header));
	            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
	            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	            cell.setPadding(5);
	            table.addCell(cell);
	        }

	        // Add data
	        for (CustomerReportPojo customer : allCustomerSummary) {
	            table.addCell(String.valueOf(customer.getId()));
	            table.addCell(customer.getName());
	            table.addCell(customer.getEmail());
	            table.addCell(customer.getPhoneno());
	            table.addCell(String.format("%.2f", customer.getTotalSale()));
	            table.addCell(String.format("%.2f", customer.getTotalAmount()));
	            table.addCell(String.format("%.2f", customer.getTotalPaid()));
	            table.addCell(String.format("%.2f", customer.getBalance()));
	        }

	        document.add(table);
	        document.close();

	        docRes = ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=customer_report.pdf")
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(out.toByteArray());

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	    return docRes;
	}

	@GetMapping(value = "/addSalesNew")
	public String addSalesNew(Model model) {
		return "AddSalesNew";
	}



}
