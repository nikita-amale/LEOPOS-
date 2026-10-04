package com.leonet.common.pojo;

import java.util.Date;

public class AgingDetailsPojo {
	
	private long agingId;
	private Date agingdate;
	private long customerId;
	private double currentbal;
	private double thirtydaybal;
	private float sixtydaybal;
	private double ninetydaybal;
	private double ninetyabovebal;
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
	@Override
	public String toString() {
		return "AgingDetailsPojo [agingId=" + agingId + ", agingdate=" + agingdate + ", customerId=" + customerId
				+ ", currentbal=" + currentbal + ", thirtydaybal=" + thirtydaybal + ", sixtydaybal=" + sixtydaybal
				+ ", ninetydaybal=" + ninetydaybal + ", ninetyabovebal=" + ninetyabovebal + ", invoiceId=" + invoiceId
				+ "]";
	}
	
		
	
}
