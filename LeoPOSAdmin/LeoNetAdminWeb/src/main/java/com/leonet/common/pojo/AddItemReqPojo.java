/**
 * 
 */
package com.leonet.common.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Moninder
 *
 */
public class AddItemReqPojo {

	private Long productId;
	private Long customerId;
	private Long saleId;
	private Long applysaleId;
	private BigDecimal price;
	private BigDecimal subtotal;
	private String productName;
	private String note;
	private String quantity;
	private String email;
	private String tax;
	private String roll;
	private String unit; 
	private String unitname;
	private String purchaseorder;
	private Long  isPriceChange;
	private Date deliverydate;
	private Date pickupdate;
	private String applycreditpayment;
    private String cashName;
    private String cashTin;


	public String getCashName() {
		return cashName;
	}

	public void setCashName(String cashName) {
		this.cashName = cashName;
	}

	public String getCashTin() {
		return cashTin;
	}

	public void setCashTin(String cashTin) {
		this.cashTin = cashTin;
	}
	
	
	
	
	
	public Long getIsPriceChange() {
		return isPriceChange;
	}
	public void setIsPriceChange(Long isPriceChange) {
		this.isPriceChange = isPriceChange;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}
	
	
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public Long getApplysaleId() {
		return applysaleId;
	}
	public void setApplysaleId(Long applysaleId) {
		this.applysaleId = applysaleId;
	}
	
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	
	public String getPurchaseorder() {
		return purchaseorder;
	}
	public void setPurchaseorder(String purchaseorder) {
		this.purchaseorder = purchaseorder;
	}
	
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	public Date getPickupdate() {
		return pickupdate;
	}
	public void setPickupdate(Date pickupdate) {
		this.pickupdate = pickupdate;
	}
	
	public String getApplycreditpayment() {
		return applycreditpayment;
	}
	public void setApplycreditpayment(String applycreditpayment) {
		this.applycreditpayment = applycreditpayment;
	}
	@Override
	public String toString() {
		return "AddItemReqPojo [productId=" + productId + ", customerId=" + customerId + ", saleId=" + saleId
				+ ", applysaleId=" + applysaleId + ", price=" + price + ", subtotal=" + subtotal + ", productName="
				+ productName + ", note=" + note + ", quantity=" + quantity + ", email=" + email + ", tax=" + tax
				+ ", roll=" + roll + ", unit=" + unit + ", unitname=" + unitname + ", purchaseorder=" + purchaseorder
				+ ", isPriceChange=" + isPriceChange + ", deliverydate=" + deliverydate + ", pickupdate=" + pickupdate
				+ ", applycreditpayment=" + applycreditpayment +  ", cashName=" + cashName
	            + ", cashTin=" + cashTin
	            + "]";
	
	}
	

	
	
	
	

		
}
