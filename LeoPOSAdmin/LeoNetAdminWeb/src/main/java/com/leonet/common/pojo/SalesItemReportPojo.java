package com.leonet.common.pojo;

import java.math.BigDecimal;

public class SalesItemReportPojo {
	private String department; // hardcoded
	private String type; // hardcoded
	private String itemCode; // product_code
	private String description; // product_name
	private Long units; // quantity
	private double amount; // quantity * subtotal
	private double average; // subtotal
	private double salePrice; // subtotal
	private int variance = 0; // hardcoded
	private BigDecimal quantity;
	
	

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getVariance() {
		return variance;
	}

	public void setVariance(int variance) {
		this.variance = variance;
	}

}
