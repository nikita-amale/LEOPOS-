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
@Table(name = "financialtransaction")
public class FinancialTransactionEntity {

	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fanId")
	private long fanId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "customerId")
	private long customerId;
	
	@Column(name = "customerName")
	private String customerName;
	
	@Column(name = "invoiceId")
	private long invoideId;
	
	@Column(name = "type")
	private String type;    // I Invoice , P == Payment , CN = credit note , DN = debit note
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "dueDate")
	private Date dueDate;
	
	@Column(name = "balance")
	private double balance;
	
	@Column(name = "referenceno")
	private String referenceno;

	public long getFanId() {
		return fanId;
	}

	public void setFanId(long fanId) {
		this.fanId = fanId;
	}
	
	

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FinancialTransactionEntity [fanId=" + fanId + ", date=" + date + ", customerId=" + customerId
				+ ", customerName=" + customerName + ", invoideId=" + invoideId + ", type=" + type + ", amount="
				+ amount + ", dueDate=" + dueDate + ", balance=" + balance + ", referenceno=" + referenceno + "]";
	}

	
	
	
		 
}
