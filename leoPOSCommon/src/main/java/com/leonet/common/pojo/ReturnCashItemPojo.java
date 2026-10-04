package com.leonet.common.pojo;

public class ReturnCashItemPojo {
	private long id;
	private long returnid;
	private long productid;
	private String product_name;
	private String mpn;
	private long quantity;
	private String tax;
	private double subtotal;
	private double real_unit_price;
	private String comment;
	private double total;
	private long unitid;
	private String unitname;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getReturnid() {
		return returnid;
	}
	public void setReturnid(long returnid) {
		this.returnid = returnid;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getMpn() {
		return mpn;
	}
	public void setMpn(String mpn) {
		this.mpn = mpn;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getReal_unit_price() {
		return real_unit_price;
	}
	public void setReal_unit_price(double real_unit_price) {
		this.real_unit_price = real_unit_price;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	public long getUnitid() {
		return unitid;
	}
	public void setUnitid(long unitid) {
		this.unitid = unitid;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	@Override
	public String toString() {
		return "ReturnCashItemPojo [id=" + id + ", returnid=" + returnid + ", productid=" + productid
				+ ", product_name=" + product_name + ", mpn=" + mpn + ", quantity=" + quantity + ", tax=" + tax
				+ ", subtotal=" + subtotal + ", real_unit_price=" + real_unit_price + ", comment=" + comment
				+ ", total=" + total + ", unitid=" + unitid + ", unitname=" + unitname + "]";
	}
	

}
