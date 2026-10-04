package com.leonet.common.pojo;

public class RsalesItemPojo {
	

	private long id;
	private long rsale_id;
	private long product_id;
	private String product_code;
	private String product_name;
	private String product_type;
	private long option_id;
	private long quantity;
	private double item_tax;
	private long tax_rate_id;
	private String tax;
	private String discount;
	private double item_discount;
	private double subtotal;
	private String serial_no; 
	private double real_unit_price;
	private long rquote_item_id;
	private long product_unit_id;
	private String unit_quantity;
	private String comment;
	private String gst;
	private String roll;

	private String productFileName;

	private String productFilePath;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getRsale_id() {
		return rsale_id;
	}
	public void setRsale_id(long rsale_id) {
		this.rsale_id = rsale_id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
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
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
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
	public long getRquote_item_id() {
		return rquote_item_id;
	}
	public void setRquote_item_id(long rquote_item_id) {
		this.rquote_item_id = rquote_item_id;
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
	
	public String getProductFileName() {
		return productFileName;
	}
	public void setProductFileName(String productFileName) {
		this.productFileName = productFileName;
	}
	public String getProductFilePath() {
		return productFilePath;
	}
	public void setProductFilePath(String productFilePath) {
		this.productFilePath = productFilePath;
	}
	@Override
	public String toString() {
		return "RsalesItemPojo [id=" + id + ", rsale_id=" + rsale_id + ", product_id=" + product_id + ", product_code="
				+ product_code + ", product_name=" + product_name + ", product_type=" + product_type + ", option_id="
				+ option_id + ", quantity=" + quantity + ", item_tax=" + item_tax + ", tax_rate_id=" + tax_rate_id
				+ ", tax=" + tax + ", discount=" + discount + ", item_discount=" + item_discount + ", subtotal="
				+ subtotal + ", serial_no=" + serial_no + ", real_unit_price=" + real_unit_price + ", rquote_item_id="
				+ rquote_item_id + ", product_unit_id=" + product_unit_id + ", unit_quantity=" + unit_quantity
				+ ", comment=" + comment + ", gst=" + gst + ", roll=" + roll + ", productFileName=" + productFileName
				+ ", productFilePath=" + productFilePath + "]";
	}
	
	
	

}
