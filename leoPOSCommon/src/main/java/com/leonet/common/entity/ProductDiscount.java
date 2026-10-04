/**
 * 
 */
package com.leonet.common.entity;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author YOGESH
 *
 */
@Entity
@Table(name = "product_discount")
public class ProductDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discount_id")
	private long discountId;
	
	@Column(name = "discount_name")
	private String discountName;
	
	@Column(name = "discount_code")
	private long discountCode=Long.parseLong(new SimpleDateFormat("yyMMddhhmmSSS").format(new Date()));;
	
	@Column(name = "sub_cat_code")
	private long subCatCode;
	
	@Column(name = "cat_code")
	private long catCode;
 
	@Column(name = "discount_amount")
	private int discountAmount;
	
	@Column(name = "item_code")
	private String itemCode;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "fromdate")
	private Date fromDate;
	
	@Column(name = "todate")
	private Date toDate;
	
	@Column(name = "is_active")
	private int isActive; 
	
	private int seq;

	public long getDiscountId() {
		return discountId;
	}

	public void setDiscountId(long discountId) {
		this.discountId = discountId;
	}

	public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public long getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(long discountCode) {
		this.discountCode = discountCode;
	}

	public long getSubCatCode() {
		return subCatCode;
	}

	public void setSubCatCode(long subCatCode) {
		this.subCatCode = subCatCode;
	}

	public long getCatCode() {
		return catCode;
	}

	public void setCatCode(long catCode) {
		this.catCode = catCode;
	}

	public int getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}

	
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductDiscount [discountId=" + discountId + ", discountName=" + discountName + ", discountCode="
				+ discountCode + ", subCatCode=" + subCatCode + ", catCode=" + catCode + ", discountAmount="
				+ discountAmount + ", itemCode=" + itemCode + ", productName=" + productName + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + ", isActive=" + isActive + ", seq=" + seq + "]";
	}

	 

	 
}
