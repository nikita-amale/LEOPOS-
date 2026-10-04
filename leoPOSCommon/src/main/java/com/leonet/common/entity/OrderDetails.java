package com.leonet.common.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_dtl_srno")
	private long orderDtlSrno;
	private long orderId;
	
	@Column(name = "order_dtlid")
	private long orderDtlId=Long.parseLong(new SimpleDateFormat("yyMMddhhmmSSS").format(new Date()))+new Random().nextInt(20);
	
	 
	private long productCode;
	private String productName;
	private String imgName;
	private int qauntity;
	private double productMrp;
	private double productPayableAmount;
	private double totalPaybaleAmount;
	private double totalDiscount;
	private String discountCode;
	private String userUuid;
	private Date orderDate;
	public long getOrderDtlSrno() {
		return orderDtlSrno;
	}
	public void setOrderDtlSrno(long orderDtlSrno) {
		this.orderDtlSrno = orderDtlSrno;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getOrderDtlId() {
		return orderDtlId;
	}
	public void setOrderDtlId(long orderDtlId) {
		this.orderDtlId = orderDtlId;
	}
	public long getProductCode() {
		return productCode;
	}
	public void setProductCode(long productCode) {
		this.productCode = productCode;
	}
	public int getQauntity() {
		return qauntity;
	}
	public void setQauntity(int qauntity) {
		this.qauntity = qauntity;
	}
	public double getProductMrp() {
		return productMrp;
	}
	public void setProductMrp(double productMrp) {
		this.productMrp = productMrp;
	}
	public double getProductPayableAmount() {
		return productPayableAmount;
	}
	public void setProductPayableAmount(double productPayableAmount) {
		this.productPayableAmount = productPayableAmount;
	}
	public double getTotalPaybaleAmount() {
		return totalPaybaleAmount;
	}
	public void setTotalPaybaleAmount(double totalPaybaleAmount) {
		this.totalPaybaleAmount = totalPaybaleAmount;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "OrderDetails [orderDtlSrno=" + orderDtlSrno + ", orderId=" + orderId + ", orderDtlId=" + orderDtlId
				+ ", productCode=" + productCode + ", productName=" + productName + ", imgName=" + imgName
				+ ", qauntity=" + qauntity + ", productMrp=" + productMrp + ", productPayableAmount="
				+ productPayableAmount + ", totalPaybaleAmount=" + totalPaybaleAmount + ", totalDiscount="
				+ totalDiscount + ", discountCode=" + discountCode + ", userUuid=" + userUuid + ", orderDate="
				+ orderDate + "]";
	}
	 
	
}
