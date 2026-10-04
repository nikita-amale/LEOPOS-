package com.leonet.service;

import java.time.LocalDate;
import java.util.List;

import com.leonet.common.pojo.SaleInvoiceReportPojo;

public interface SaleInvoiceReportService {

	List<SaleInvoiceReportPojo> getSalesInvoiceReportBetween(Long startId, Long endId, LocalDate filterDate);
}
