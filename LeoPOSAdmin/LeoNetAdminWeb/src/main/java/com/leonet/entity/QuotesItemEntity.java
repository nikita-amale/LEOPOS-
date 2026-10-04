package com.leonet.entity;


import java.math.BigDecimal;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotes_items")
public class QuotesItemEntity {
	
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "quotesid")
	private long quotesid;
	
	@Column(name = "product_id")
	private long product_id;
	
	@Column(name = "product_code")
	private String product_code;
	
	@Column(name = "product_name")
	private String product_name;
	
	@Column(name = "product_type")
	private String product_type;
	
	@Column(name = "option_id")
	private long option_id;
	
	@Column(name = "quantity")
	private BigDecimal quantity;
	
	@Column(name = "warehouse_id")
	private long warehouse_id;
	
	@Column(name = "item_tax")
	private double item_tax;
	
	@Column(name = "tax_rate_id")
	private long tax_rate_id;
	
	@Column(name = "tax")
	private String tax;
	
	@Column(name = "discount")
	private String discount;
	
	@Column(name = "item_discount")
	private double item_discount;
	
	@Column(name = "subtotal")
	private double subtotal;
	
	@Column(name = "serial_no")
	private String serial_no; 
	
	@Column(name = "real_unit_price")
	private double real_unit_price;
	
	@Column(name = "sale_item_id")
	private long sale_item_id;
	
	@Column(name = "product_unit_id")
	private long product_unit_id;
	
	@Column(name = "unit_quantity")
	private String unit_quantity;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "roll")
	private String roll;
	
	@Column(name = "gst")
	private String gst;
	
	@Column(name = "is_price_change")
	private Long  isPriceChange;

	
	public Long getIsPriceChange() {
		return isPriceChange;
	}

	public void setIsPriceChange(Long isPriceChange) {
		this.isPriceChange = isPriceChange;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getQuotesid() {
		return quotesid;
	}

	public void setQuotesid(long quotesid) {
		this.quotesid = quotesid;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "QuotesItemEntity [id=" + id + ", quotesid=" + quotesid + ", product_id=" + product_id
				+ ", product_code=" + product_code + ", product_name=" + product_name + ", product_type=" + product_type
				+ ", option_id=" + option_id + ", quantity=" + quantity + ", warehouse_id=" + warehouse_id
				+ ", item_tax=" + item_tax + ", tax_rate_id=" + tax_rate_id + ", tax=" + tax + ", discount=" + discount
				+ ", item_discount=" + item_discount + ", subtotal=" + subtotal + ", serial_no=" + serial_no
				+ ", real_unit_price=" + real_unit_price + ", sale_item_id=" + sale_item_id + ", product_unit_id="
				+ product_unit_id + ", unit_quantity=" + unit_quantity + ", comment=" + comment + ", roll=" + roll
				+ ", gst=" + gst + ", isPriceChange=" + isPriceChange + "]";
	}


	
	

}
