/**
 * 
 */
package com.leonet.common.pojo;

 import java.util.Date;
 

/**
 * @author YOGESH
 *
 */
public class ProductDiscountPojo {

	private long discountId;
	
 	private String discountName;
	
 	private long discountCode;
	
 	private long subCatCode;
	
 	private long catCode;
 
 	private long discountAmount;
	
 	private long productCode;
	
 	private Date fromDate;
	
 	private Date toDate;
	
 	private int isActive; 
	
	private int seq;

	private String catName;
	
	private String subCatName;
	
	private String productName;
	
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

	public long getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(long discountAmount) {
		this.discountAmount = discountAmount;
	}

	public long getProductCode() {
		return productCode;
	}

	public void setProductCode(long productCode) {
		this.productCode = productCode;
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

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductDiscountPojo [discountId=" + discountId + ", discountName=" + discountName + ", discountCode="
				+ discountCode + ", subCatCode=" + subCatCode + ", catCode=" + catCode + ", discountAmount="
				+ discountAmount + ", productCode=" + productCode + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", isActive=" + isActive + ", seq=" + seq + ", catName=" + catName + ", subCatName=" + subCatName
				+ ", productName=" + productName + "]";
	}

	
}
