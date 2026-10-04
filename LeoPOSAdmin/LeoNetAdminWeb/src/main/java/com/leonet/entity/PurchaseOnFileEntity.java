package com.leonet.entity;


import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.leonet.common.entity.Auditable;


@Entity
@Table(name = "purchase_on_addfile")
public class PurchaseOnFileEntity extends Auditable<String> implements Serializable {
	
	
private static final long serialVersionUID = 7018838303060755967L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "purchaseId")
private long purchaseId;

@Column(name = "date")
private Date date;

@Column(name = "supplier")
private String supplier;

@Column(name = "user_id")
private long user_id;

@Column(name = "warehouse_id")
private long warehouse_id;

@Column(name = "paid")
private double paid;

@Column(name = "balance")
private double balance;

@Column(name = "grand_total")
private double grand_total;

@Column(name = "file")
private long file ;



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




public long getFile() {
	return file;
}

public void setFile(long file) {
	this.file = file;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

@Override
public String toString() {
	return "PurchaseOnFileEntity [purchaseId=" + purchaseId + ", date=" + date + ", supplier=" + supplier + ", user_id="
			+ user_id + ", warehouse_id=" + warehouse_id + ", paid=" + paid + ", balance=" + balance + ", grand_total="
			+ grand_total + ", file=" + file + "]";
}




}
