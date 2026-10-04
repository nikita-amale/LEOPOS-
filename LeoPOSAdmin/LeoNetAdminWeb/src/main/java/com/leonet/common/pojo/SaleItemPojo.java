package com.leonet.common.pojo;

import java.math.BigDecimal;

public class SaleItemPojo {

	private long id;
	private long saleid;
	private long productid;
	private String product_code;
	private String product_name;
	private String product_type;
	private long option_id;
	private BigDecimal quantity;
	private long warehouse_id;
	private double item_tax;
	private long tax_rate_id;
	private String tax;
	private String discount;
	private double item_discount;
	private double subtotal;
	private String serial_no; 
	private double real_unit_price;
	private long sale_item_id;
	private long product_unit_id;
	private String unit_quantity;
	private String comment;
	private String gst;
	private String roll;
	private String returnqty;
	private double saleSubtotal;
	private double saleGrandTotal;
	private double saleTotalTax;
	
	
	
	
	public double getSaleSubtotal() {
		return saleSubtotal;
	}
	public void setSaleSubtotal(double saleSubtotal) {
		this.saleSubtotal = saleSubtotal;
	}
	public double getSaleGrandTotal() {
		return saleGrandTotal;
	}
	public void setSaleGrandTotal(double saleGrandTotal) {
		this.saleGrandTotal = saleGrandTotal;
	}
	public double getSaleTotalTax() {
		return saleTotalTax;
	}
	public void setSaleTotalTax(double saleTotalTax) {
		this.saleTotalTax = saleTotalTax;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSaleid() {
		return saleid;
	}
	public void setSaleid(long saleid) {
		this.saleid = saleid;
	}
	public long getProduct_id() {
		return productid;
	}
	public void setProduct_id(long product_id) {
		this.productid = product_id;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public long getOption_id() {
		return option_id;
	}
	public void setOption_id(long option_id) {
		this.option_id = option_id;
	}
	
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public long getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public double getItem_tax() {
		return item_tax;
	}
	public void setItem_tax(double item_tax) {
		this.item_tax = item_tax;
	}
	public long getTax_rate_id() {
		return tax_rate_id;
	}
	public void setTax_rate_id(long tax_rate_id) {
		this.tax_rate_id = tax_rate_id;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public double getItem_discount() {
		return item_discount;
	}
	public void setItem_discount(double item_discount) {
		this.item_discount = item_discount;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}
	public double getReal_unit_price() {
		return real_unit_price;
	}
	public void setReal_unit_price(double real_unit_price) {
		this.real_unit_price = real_unit_price;
	}
	public long getSale_item_id() {
		return sale_item_id;
	}
	public void setSale_item_id(long sale_item_id) {
		this.sale_item_id = sale_item_id;
	}
	public long getProduct_unit_id() {
		return product_unit_id;
	}
	public void setProduct_unit_id(long product_unit_id) {
		this.product_unit_id = product_unit_id;
	}
	public String getUnit_quantity() {
		return unit_quantity;
	}
	public void setUnit_quantity(String unit_quantity) {
		this.unit_quantity = unit_quantity;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	
	
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	
	
	public String getReturnqty() {
		return returnqty;
	}
	public void setReturnqty(String returnqty) {
		this.returnqty = returnqty;
	}
	@Override
	public String toString() {
		return "SaleItemPojo [id=" + id + ", saleid=" + saleid + ", productid=" + productid + ", product_code="
				+ product_code + ", product_name=" + product_name + ", product_type=" + product_type + ", option_id="
				+ option_id + ", quantity=" + quantity + ", warehouse_id=" + warehouse_id + ", item_tax=" + item_tax
				+ ", tax_rate_id=" + tax_rate_id + ", tax=" + tax + ", discount=" + discount + ", item_discount="
				+ item_discount + ", subtotal=" + subtotal + ", serial_no=" + serial_no + ", real_unit_price="
				+ real_unit_price + ", sale_item_id=" + sale_item_id + ", product_unit_id=" + product_unit_id
				+ ", unit_quantity=" + unit_quantity + ", comment=" + comment + ", gst=" + gst + ", roll=" + roll
				+ ", returnqty=" + returnqty + ", saleSubtotal=" + saleSubtotal + ", saleGrandTotal=" + saleGrandTotal
				+ ", saleTotalTax=" + saleTotalTax + "]";
	}
	

	

		
 
	 
	 
	 
}
