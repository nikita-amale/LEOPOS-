package com.leonet.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leonet.common.pojo.ProgressStatus;
import com.leonet.service.SalesPdfRegenerationService;

@Controller
public class SalesPdfRegenerationController {

	private final SalesPdfRegenerationService regenerationService;

	public SalesPdfRegenerationController(SalesPdfRegenerationService regenerationService) {
		this.regenerationService = regenerationService;
	}
	
	@GetMapping("viewSalePdf")
	public String getSalesMigration() {
		return "SalesMigration";
	}

	@PostMapping("salesPdfRegenerate")
	public ResponseEntity<String> regeneratePdf(
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
	        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

	    String jobId = UUID.randomUUID().toString();
	    regenerationService.regeneratePdfsAsync(startDate, endDate, jobId);
	    return ResponseEntity.ok(jobId); // return jobId so UI can track
	}

	@GetMapping("salesPdfProgress")
	public ResponseEntity<ProgressStatus> getProgress(@RequestParam String jobId) {
	    return ResponseEntity.ok(regenerationService.getProgress(jobId));
	}

}
