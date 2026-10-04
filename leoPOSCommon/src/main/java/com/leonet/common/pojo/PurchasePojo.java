package com.leonet.common.pojo;

import java.util.Date;

import javax.persistence.Column;

public class PurchasePojo {
	
	private long purchaseId;
	

	private Date date;
	
	private String supplier;
	private long user_id;
	
	private long warehouse_id;
	
	private double paid;
	
	private double balance;
	
	private double grand_total;
	private String createdBy;

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}
	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "PurchasePojo [purchaseId=" + purchaseId + ", date=" + date + ", supplier=" + supplier + ", user_id="
				+ user_id + ", warehouse_id=" + warehouse_id + ", paid=" + paid + ", balance=" + balance
				+ ", grand_total=" + grand_total + ", createdBy=" + createdBy + "]";
	}

	
	

}
