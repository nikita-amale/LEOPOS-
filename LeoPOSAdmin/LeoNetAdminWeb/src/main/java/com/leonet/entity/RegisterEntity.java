package com.leonet.entity;

import java.util.Date;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "register")
public class RegisterEntity {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "referenceno")
	private String referenceno;
	
	@Column(name = "cashinhand")
	private double cashinhand;
	
	@Column(name = "cashpayment")
	private double cashpayment;
	
	@Column(name = "chequepayment")
	private double chequepayment;
	
	@Column(name = "creditcardpayment")
	private double creditcardpayment;
	
	@Column(name = "onlinepayment")
	private double onlinepayment;
	
	@Column(name = "otherpayment")
	private double otherpayment;
	
	@Column(name = "salesamount")
	private double salesamount;
	
	@Column(name = "refunds")
	private double refunds;
	
	@Column(name = "status")
	private String status;
		
	@Column(name = "openingbal")
	private double openingbal;
	
	@Column(name = "closingbal")
	private double closingbal;
	
	@Column(name = "note")
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

	public double getOpeningbal() {
		return openingbal;
	}

	public void setOpeningbal(double openingbal) {
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
	
	public double getOtherpayment() {
		return otherpayment;
	}

	public void setOtherpayment(double otherpayment) {
		this.otherpayment = otherpayment;
	}

	@Override
	public String toString() {
		return "RegisterEntity [id=" + id + ", date=" + date + ", referenceno=" + referenceno + ", cashinhand="
				+ cashinhand + ", cashpayment=" + cashpayment + ", chequepayment=" + chequepayment
				+ ", creditcardpayment=" + creditcardpayment + ", onlinepayment=" + onlinepayment + ", otherpayment=" + otherpayment
				+ ", salesamount=" + salesamount + ", refunds=" + refunds + ", status=" + status + ", openingbal="
				+ openingbal + ", closingbal=" + closingbal + ", note=" + note + "]";
	}


	
	
		
}
