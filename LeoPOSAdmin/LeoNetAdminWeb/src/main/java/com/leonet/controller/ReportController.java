/**
 * author MONINDER
 */
package com.leonet.controller;

import java.io.ByteArrayInputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.common.entity.FTEntity;
import com.leonet.common.entity.FinancialTransactionEntity;
import com.leonet.common.entity.RequestQuoteEntity;
import com.leonet.common.entity.RequestQuoteItemEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SpecialSalesEntity;
import com.leonet.common.pojo.ProfitLossReportPojo;
import com.leonet.common.pojo.SysAuditReportDTO;
import com.leonet.constant.Action;
import com.leonet.entity.MemberUser;
import com.leonet.repo.AdminUserRepo;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.SysAuditRepo;
import com.leonet.service.SaleService;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.GeneratePdfReport;
import com.leonet.util.GeneratePdfUtil;

import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.leonet.util.LeoLogger;


/**
 * @author MONINDER
 *
 */
@Controller
public class ReportController {

	@Autowired
	CustomFileUploadUtil fileUploadUtil;

	@Autowired
	SaleService saleService;
	
	@Autowired
	MemberUserRepo memberUserRepo;
	
	@Autowired
	AdminUserRepo adminUserRepo;
	
	@Autowired
	SysAuditRepo sysAuditRepo;
	
	
	@GetMapping("/getStatementbymemberId")
	public @ResponseBody ResponseEntity<InputStreamResource>  getStatementbymemberId(ModelMap modelMap, @RequestParam("MemberId") long memberId)
	{
		List<FinancialTransactionEntity> ftEntity = saleService.getFtByCustomerId(memberId);
		List<SalesEntity> salesEntity = saleService.getSalesItembymemberId(memberId);
		
		LeoLogger.info("Report Controller ---getStatementbymemberId---Financial Statement Entity "  + salesEntity.toString());
		LeoLogger.info("Report Controller ---getStatementbymemberId---Financial Transaction Entity "  + ftEntity.toString());
		ByteArrayInputStream bis = GeneratePdfReport.statementReport(ftEntity,salesEntity);

	//	var headers = new HttpHeaders();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=StatementReport.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		
	}
	@GetMapping("/getStatementquotesbymemberId")
	public @ResponseBody ResponseEntity<InputStreamResource>  getStatemenquotestbymemberId(ModelMap modelMap, @RequestParam("MemberId") Long memberId)
	{
		//List<RequestQuoteItemEntity> ftEntity = saleService.getrqByCustomerId(memberId);
		
		//List<SalesEntity> salesEntity = saleService.getSalesItembymemberId(memberId);

		//LeoLogger.info("Financial Statement Entity "  + salesEntity.toString());
		//LeoLogger.info("Financial Transaction Entity "  + ftEntity.toString());
		
		RequestQuoteEntity requestQuoteEntity = saleService.findRequestQuoteById(memberId);
		byte[] b = new byte[1024];
		ByteArrayInputStream bis = new ByteArrayInputStream(b);
		if (requestQuoteEntity != null && requestQuoteEntity.getFileName() != null
				&& !requestQuoteEntity.getFileName().isEmpty()) {
			try {
				bis = fileUploadUtil.getFile(requestQuoteEntity.getFileName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	//	var headers = new HttpHeaders();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=RequestQuote.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		
	}
	@GetMapping(value = "/statementReport")
	public @ResponseBody ResponseEntity<InputStreamResource>  statementReport(ModelMap modelMap, @RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,@RequestParam("MemberId") Long memberId,@RequestParam(value="Ctype" , required = false) String Ctype )throws ParseException {
		LeoLogger.info("Financial Statement Entity " +startDate );
		LeoLogger.info("Financial Statement Entity " +endDate );
		
		List<FinancialTransactionEntity> ftEntity = saleService.findAllStatementSummary(memberId,startDate, endDate);
		if (!Ctype.equalsIgnoreCase("Special")) {
			LeoLogger.info("SalesEntity  "  );

		List<SalesEntity> salesEntity = saleService.getSalesItembymemberId(memberId);
		
		LeoLogger.info("Financial Statement Entity "  + ftEntity);
		//modelMap.addAttribute("statementReport", saleService.findAllStatementSummary(startDate, endDate));
		ByteArrayInputStream bis = GeneratePdfReport.Report(ftEntity,salesEntity);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=Report.pdf");
		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		
		}else {
			LeoLogger.info("SpecialSalesEntity  ");
			List<SpecialSalesEntity> salesEntity = saleService.getSpecialSalesItembymemberId(memberId);
			LeoLogger.info("salesEntity Statement Entity "  + salesEntity);
			ByteArrayInputStream bis = GeneratePdfReport.SReport(ftEntity,salesEntity);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=Report.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
			
		}
	}
		
		
		
		
		@GetMapping(value = "/newstatementReport")
		public @ResponseBody ResponseEntity<InputStreamResource>  newstatementReport(ModelMap modelMap, @RequestParam(value = "startDate", required = false) String startDate,
				@RequestParam(value = "endDate", required = false) String endDate,@RequestParam("MemberId") Long memberId,@RequestParam(value="Ctype" , required = false) String Ctype )throws ParseException {
			LeoLogger.info("Financial Statement Entity " +startDate );
			LeoLogger.info("Financial Statement Entity " +endDate );
			
			List<FTEntity> ftEntity = saleService.findAllStatementtSummary(memberId,startDate, endDate);
			MemberUser memberPojo = memberUserRepo.findById(memberId);
			if (!Ctype.equalsIgnoreCase("Special")) {
				LeoLogger.info("SalesEntity  "  );

			List<SalesEntity> salesEntity = saleService.getSalesItembymemberId(memberId);
			
			LeoLogger.info("Financial Statement Entity "  + ftEntity);
			//modelMap.addAttribute("statementReport", saleService.findAllStatementSummary(startDate, endDate));
			ByteArrayInputStream bis = GeneratePdfReport.Reportt(ftEntity,salesEntity,memberPojo);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=Report.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
			
			}else {
				LeoLogger.info("SpecialSalesEntity  ");
				List<SpecialSalesEntity> salesEntity = saleService.getSpecialSalesItembymemberId(memberId);
				LeoLogger.info("salesEntity Statement Entity "  + salesEntity);
				ByteArrayInputStream bis = GeneratePdfReport.SReportt(ftEntity,salesEntity,memberPojo);
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Disposition", "inline; filename=Report.pdf");
				return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
						.body(new InputStreamResource(bis));
				
			}
	
			
		

		
		
	}
		
		
		@GetMapping(value = "/ProfitLossReport")
		public @ResponseBody ResponseEntity<InputStreamResource>  ProfitLossReport(ModelMap modelMap, @RequestParam(value = "startDate", required = false) String startDate,
				@RequestParam(value = "endDate", required = false) String endDate)throws ParseException {
			LeoLogger.info("ProfitLossReport Statement Entity " +startDate );
			LeoLogger.info("ProfitLossReport Statement Entity " +endDate );
		
			 List<ProfitLossReportPojo> profitLossSummaryList = saleService.findAllProfitLossSummary(startDate,endDate);
	    	// LeoLogger.info("profitLossSummaryList== "+profitLossSummaryList);
			ByteArrayInputStream bis = GeneratePdfReport.ProfirLossReport(startDate, endDate,profitLossSummaryList);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=Report.pdf");
			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
			
			}
		
		@GetMapping(value = "/sysAuditReport")
		public String sysAuditReport(@RequestParam(defaultValue = "0") int page,
				@RequestParam(defaultValue = "20") int size, @RequestParam(required = false) String startDate,
				@RequestParam(required = false) String endDate, @RequestParam(required = false) Action action,
				@RequestParam(required = false) String userName, // userName filter here
				@RequestParam(defaultValue = "false") boolean export, Model model, HttpServletResponse response)
				throws IOException {

			LeoLogger.info("SysAuditReport ====export=={}", export);

			// Parse startDate and endDate
			Date fromDate = (startDate != null && !startDate.isEmpty()) ? parseDate(startDate) : null;
			Date toDate = (endDate != null && !endDate.isEmpty()) ? parseDate(endDate) : null;
			toDate = setEndOfDay(toDate);

			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);

			// Log the values
			LeoLogger.info("SysAuditReport ====fromDate=={}", fromDate);
			LeoLogger.info("SysAuditReport ====toDate=={}", toDate);
			LeoLogger.info("SysAuditReport ====action=={}", action);
			LeoLogger.info("SysAuditReport ====userName=={}", userName);

			// Adding actions to the model for filtering options
			List<Action> distinctActions = sysAuditRepo.findDistinctActions();
			model.addAttribute("actions", distinctActions);

			List<String> userNames = adminUserRepo.findAllUserNames(); // Fetch list of usernames from UserEntity
			model.addAttribute("userNames", userNames);

			// Check if export is true
			/*
			if (export) {
				List<SysAuditReportDTO> auditList = sysAuditRepo.findAuditWithFiltersJPQL(fromDate, toDate, action,
						userName);
				exportSysAuditReport(auditList, response);
				return null; // No view to render since we are exporting
			} */

			// Fetch paginated report
			Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));
			Page<SysAuditReportDTO> auditPage = sysAuditRepo.findAuditWithPaginationJPQL(fromDate, toDate, action, userName,
					pageable);

			List<SysAuditReportDTO> auditList = auditPage.getContent();
 
			// Calculate pagination attributes
			int startSerialNumber = page * size + 1;
			int totalRecords = startSerialNumber + auditList.size() - 1;
			model.addAttribute("sysAuditList", auditList);
			model.addAttribute("currentPage", auditPage.getNumber());
			model.addAttribute("totalPages", auditPage.getTotalPages());
			model.addAttribute("totalItems", auditPage.getTotalElements());
			model.addAttribute("currentRecords", startSerialNumber);
			model.addAttribute("totalRecords", auditPage.getTotalElements());
			model.addAttribute("action", action);
			model.addAttribute("selectedUser", userName); // userName attribute to pass to the view
			model.addAttribute("noRecords", auditList.isEmpty());

			return "SysAuditReport"; // Return JSP view for the report
		}
		
		private Date parseDate(String dateStr) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			try {
				if (!dateStr.trim().isEmpty()) {
					return formatter.parse(dateStr);
				} else {
					return null;
				}

			} catch (ParseException e) {
				return null;
			}
		}
		
		   public  Date setEndOfDay(Date date) {
		        if (date == null) return null;
		        Calendar cal = Calendar.getInstance();
		        cal.setTime(date);
		        cal.set(Calendar.HOUR_OF_DAY, 23);
		        cal.set(Calendar.MINUTE, 59);
		        cal.set(Calendar.SECOND, 59);
		        return cal.getTime();
		    }
		   

		

		
		
	}



