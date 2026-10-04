/**

 * 
 */
package com.leonet.entity;

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
@Table(name = "product_Rental")
public class ProductRentalEntity {

	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rproductId")
	private long rproductId;
	
	@Column(name = "rcode")
	private String rcode;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unit")
	private int unit;
	
	@Column(name = "cost")
	private float cost;
	
	@Column(name = "rprice")
	private float rprice;
	
	@Column(name = "category_id")
	private long category_id;
	
	@Column(name = "subcategory_id")
	private long subcategory_id;
	
	@Column(name = "tax_rate")
	private int tax_rate;
	
	@Column(name = "quantity")
	private int quantity;
		
	@Column(name = "product_details")
	private String product_details;
	
	@Column(name = "tax_method")
	private String tax_method;
	
	@Column(name = "creation_date")
	private Date creation_date;
	
	@Column(name ="brand")
	private String brand;

	public long getRproductId() {
		return rproductId;
	}

	public void setRproductId(long rproductId) {
		this.rproductId = rproductId;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getRprice() {
		return rprice;
	}

	public void setRprice(float rprice) {
		this.rprice = rprice;
	}

	public long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(long category_id) {
		this.category_id = category_id;
	}

	public long getSubcategory_id() {
		return subcategory_id;
	}

	public void setSubcategory_id(long subcategory_id) {
		this.subcategory_id = subcategory_id;
	}

	public int getTax_rate() {
		return tax_rate;
	}

	public void setTax_rate(int tax_rate) {
		this.tax_rate = tax_rate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getProduct_details() {
		return product_details;
	}

	public void setProduct_details(String product_details) {
		this.product_details = product_details;
	}

	public String getTax_method() {
		return tax_method;
	}

	public void setTax_method(String tax_method) {
		this.tax_method = tax_method;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "ProductRentalEntity [rproductId=" + rproductId + ", rcode=" + rcode + ", name=" + name + ", unit="
				+ unit + ", cost=" + cost + ", rprice=" + rprice + ",  category_id=" + category_id
				+ ", subcategory_id=" + subcategory_id + ", tax_rate=" + tax_rate + ", quantity=" + quantity
				+ ", product_details=" + product_details + ", tax_method=" + tax_method + ", creation_date="
				+ creation_date + ", brand=" + brand + "]";
	}
	

	
	
	
	
}
