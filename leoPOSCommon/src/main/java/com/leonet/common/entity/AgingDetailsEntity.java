/**

 * 
 */
package com.leonet.common.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Moninder
 *
 */
@Entity
@Table(name = "agingdetails")
public class AgingDetailsEntity {

	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "agingId")
	private long agingId;
	
	@Column(name = "agingdate")
	private Date agingdate;
	
	@Column(name = "customerId")
	private long customerId;
	
	@Column(name = "currentbal")
	private double currentbal;
	
	@Column(name ="thirtydaybal")
	private double thirtydaybal;
	
	@Column(name ="sixtydaybal")
	private float sixtydaybal;
	
	@Column(name ="ninetydaybal")
	private double ninetydaybal;
	
	@Column(name ="ninetyabovebal")
	private double ninetyabovebal;
	
	@Column(name = "invoiceId")
	private long invoiceId;

	public long getAgingId() {
		return agingId;
	}

	public void setAgingId(long agingId) {
		this.agingId = agingId;
	}

	public Date getAgingdate() {
		return agingdate;
	}

	public void setAgingdate(Date agingdate) {
		this.agingdate = agingdate;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public double getCurrentbal() {
		return currentbal;
	}

	public void setCurrentbal(double currentbal) {
		this.currentbal = currentbal;
	}

	public double getThirtydaybal() {
		return thirtydaybal;
	}

	public void setThirtydaybal(double thirtydaybal) {
		this.thirtydaybal = thirtydaybal;
	}

	public float getSixtydaybal() {
		return sixtydaybal;
	}

	public void setSixtydaybal(float sixtydaybal) {
		this.sixtydaybal = sixtydaybal;
	}

	public double getNinetydaybal() {
		return ninetydaybal;
	}

	public void setNinetydaybal(double ninetydaybal) {
		this.ninetydaybal = ninetydaybal;
	}

	public double getNinetyabovebal() {
		return ninetyabovebal;
	}

	public void setNinetyabovebal(double ninetyabovebal) {
		this.ninetyabovebal = ninetyabovebal;
	}

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AgingDetailsEntity [agingId=" + agingId + ", agingdate=" + agingdate + ", customerId=" + customerId
				+ ", currentbal=" + currentbal + ", thirtydaybal=" + thirtydaybal + ", sixtydaybal=" + sixtydaybal
				+ ", ninetydaybal=" + ninetydaybal + ", ninetyabovebal=" + ninetyabovebal + ", invoiceId=" + invoiceId
				+ "]";
	}
	
		
		 
}
