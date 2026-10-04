
package com.leonet.entity;


import java.math.BigDecimal;

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
@Table(name = "returncash_items")
public class ReturnCashItemsEntity {

	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "returnid")
	private long returnid;
	
	@Column(name = "productid")
	private long productid;
	
	@Column(name = "product_name")
	private String product_name;
	
	@Column(name = "mpn")
	private String mpn;
	
	@Column(name = "quantity")
	private BigDecimal quantity;
	
	@Column(name = "tax")
	private String tax;
	
	@Column(name = "subtotal")
	private double subtotal;
	
	@Column(name = "real_unit_price")
	private double real_unit_price;
	
	@Column(name = "unitid")
	private long unitid;
	
	@Column(name = "unitname")
	private String unitname;
	
	@Column(name = "comment")
	private String comment;

	@Column(name = "total")
	private double total;

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

	

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ReturnCashItemsEntity [id=" + id + ", returnid=" + returnid + ", productid=" + productid + ", product_name="
				+ product_name + ", mpn=" + mpn + ", quantity=" + quantity + ", tax=" + tax + ", subtotal=" + subtotal
				+ ", real_unit_price=" + real_unit_price + ", unitid=" + unitid + ", unitname=" + unitname
				+ ", comment=" + comment + ", total=" + total + "]";
	}
	
	 
}
