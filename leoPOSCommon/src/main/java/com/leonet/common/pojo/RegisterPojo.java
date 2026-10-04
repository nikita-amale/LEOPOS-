package com.leonet.common.pojo;

import java.util.Date;


public class RegisterPojo {
	
    private Long id;
	
	private Date date;
	
	private String referenceno;
	
	private double cashinhand;
	
	private double cashpayment;
	
	private double chequepayment;
	
	private double creditcardpayment;
	
	private double onlinepayment;
	
	private double otherpayment;

	private double salesamount;
	
	private double refunds;
	
	private String status;
		
	private Long openingbal;
	
	private double closingbal;
	
	private String note;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}

	public double getCashinhand() {
		return cashinhand;
	}

	public void setCashinhand(double cashinhand) {
		this.cashinhand = cashinhand;
	}

	public double getCashpayment() {
		return cashpayment;
	}

	public void setCashpayment(double cashpayment) {
		this.cashpayment = cashpayment;
	}

	public double getChequepayment() {
		return chequepayment;
	}

	public void setChequepayment(double chequepayment) {
		this.chequepayment = chequepayment;
	}

	public double getCreditcardpayment() {
		return creditcardpayment;
	}

	public void setCreditcardpayment(double creditcardpayment) {
		this.creditcardpayment = creditcardpayment;
	}
	
	public double getOtherpayment() {
		return otherpayment;
	}

	public void setOtherpayment(double otherpayment) {
		this.otherpayment = otherpayment;
	}

	public double getOnlinepayment() {
		return onlinepayment;
	}

	public void setOnlinepayment(double onlinepayment) {
		this.onlinepayment = onlinepayment;
	}

	public double getSalesamount() {
		return salesamount;
	}

	public void setSalesamount(double salesamount) {
		this.salesamount = salesamount;
	}

	public double getRefunds() {
		return refunds;
	}

	public void setRefunds(double refunds) {
		this.refunds = refunds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getOpeningbal() {
		return openingbal;
	}

	public void setOpeningbal(Long openingbal) {
		this.openingbal = openingbal;
	}

	public double getClosingbal() {
		return closingbal;
	}

	public void setClosingbal(double closingbal) {
		this.closingbal = closingbal;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "RegisterPojo [id=" + id + ", date=" + date + ", referenceno=" + referenceno + ", cashinhand="
				+ cashinhand + ", cashpayment=" + cashpayment + ", chequepayment=" + chequepayment
				+ ", creditcardpayment=" + creditcardpayment + ", onlinepayment=" + onlinepayment + ", otherpayment="
				+ otherpayment + ", salesamount=" + salesamount + ", refunds=" + refunds + ", status=" + status
				+ ", openingbal=" + openingbal + ", closingbal=" + closingbal + ", note=" + note + "]";
	}
	

}
