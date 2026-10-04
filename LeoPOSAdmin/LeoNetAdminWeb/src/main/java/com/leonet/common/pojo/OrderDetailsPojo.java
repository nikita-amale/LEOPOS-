/**
 * 
 */
package com.leonet.common.pojo;

import java.util.Date;



/**
 * @author YOGESH
 *
 */
public class OrderDetailsPojo {
	
	private long orderDtlSrno;
	private long orderId;
	private long orderDtlId;
	private long productCode;
	private String productName;
	private String imgName;
	private int qauntity;
	private double productMrp;
	private double productPayableAmount;
	private double totalPaybaleAmount;
	private String discountCode;
	private String userUuid;
	private Date orderDate;
	private long orderSrno;
	private int totalQauntity;
	private double totalAmount;
	private double totalDiscount;
	private double payableAmount;
	private int orderStatus;//0=confirm, 1=dispatch, 2=delivered, 3=reject
	private String paymentJson;
	private int paidStatus;   //0=init, 1=paid, 2=Pending ,3=cancel/Failed, 
	private Long tid;
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
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public long getOrderSrno() {
		return orderSrno;
	}
	public void setOrderSrno(long orderSrno) {
		this.orderSrno = orderSrno;
	}
	public int getTotalQauntity() {
		return totalQauntity;
	}
	public void setTotalQauntity(int totalQauntity) {
		this.totalQauntity = totalQauntity;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public double getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(double payableAmount) {
		this.payableAmount = payableAmount;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaymentJson() {
		return paymentJson;
	}
	public void setPaymentJson(String paymentJson) {
		this.paymentJson = paymentJson;
	}
	public int getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(int paidStatus) {
		this.paidStatus = paidStatus;
	}
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "OrderDetails [orderDtlSrno=" + orderDtlSrno + ", orderId=" + orderId + ", orderDtlId=" + orderDtlId
				+ ", productCode=" + productCode + ", productName=" + productName + ", imgName=" + imgName
				+ ", qauntity=" + qauntity + ", productMrp=" + productMrp + ", productPayableAmount="
				+ productPayableAmount + ", totalPaybaleAmount=" + totalPaybaleAmount + ", discountCode=" + discountCode
				+ ", userUuid=" + userUuid + ", orderDate=" + orderDate + ", orderSrno=" + orderSrno
				+ ", totalQauntity=" + totalQauntity + ", totalAmount=" + totalAmount + ", totalDiscount="
				+ totalDiscount + ", payableAmount=" + payableAmount + ", orderStatus=" + orderStatus + ", paymentJson="
				+ paymentJson + ", paidStatus=" + paidStatus + ", tid=" + tid + "]";
	}
	 
	
}
