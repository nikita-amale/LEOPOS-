package com.leonet.common.pojo;

import java.util.Date;

public class FTPojo {
	
	private long fanId;
	private Date date;
	private long customerId;
	private String customerName;
	private long invoideId;
	private String type;
	private double amount;
	private Date dueDate;
	private Double balance;
	private String referenceno;
	public long getFanId() {
		return fanId;
	}
	public void setFanId(long fanId) {
		this.fanId = fanId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getInvoideId() {
		return invoideId;
	}
	public void setInvoideId(long invoideId) {
		this.invoideId = invoideId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	@Override
	public String toString() {
		return "FTPojo [fanId=" + fanId + ", date=" + date + ", customerId=" + customerId + ", customerName="
				+ customerName + ", invoideId=" + invoideId + ", type=" + type + ", amount=" + amount + ", dueDate="
				+ dueDate + ", balance=" + balance + ", referenceno=" + referenceno + "]";
	}
	
	
	
	
	 
}
