package com.leonet.common.pojo;

import java.util.Date;
import java.util.List;

public class SaleInvoiceReportPojo {
    private Long invoiceNumber;     // saleId
    private String code;            // referenceno
    private String customerName;    // membername
    private String date;              // creation date (Auditable)
    private double gross;           // total
    private double tax;             // total_tax
    private double total;           // grandtotal
    private double amountPaid;      // paid
    private double netPay;          // grandtotal - paid

    private List<SalesItemReportPojo> items;

	public Long getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Long invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getGross() {
		return gross;
	}

	public void setGross(double gross) {
		this.gross = gross;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public List<SalesItemReportPojo> getItems() {
		return items;
	}

	public void setItems(List<SalesItemReportPojo> items) {
		this.items = items;
	}
    
}
