package com.leonet.service;

import java.time.LocalDate;

import com.leonet.common.pojo.ProgressStatus;

public interface SalesPdfRegenerationService {

	void regeneratePdfsAsync(LocalDate startDate, LocalDate endDate, String jobId);
	
	ProgressStatus getProgress(String jobId);
}
