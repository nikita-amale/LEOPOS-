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
@Table(name = "order_header")
public class OrderHeader {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_srno")
	private long orderSrno;
	
	
	@Column(name = "order_id")
	private long orderId=Long.parseLong(new SimpleDateFormat("yyMMddhhmmSSS").format(new Date()))+new Random().nextInt(20);
	
	private String userUuid;
	private String contactName;
	private Date orderDate;
	private int totalQauntity;
	private double totalAmount;
	private double totalDiscount;
	private double payableAmount;
	private String address;
	private String city;
	private String pinCode;
	private String mobileNo;
	private String email;
	private int orderStatus;//0=confirm, 1=dispatch, 2=delivered, 3=reject
	
	private String paymentJson;
	private int paidStatus;   //0=init, 1=paid, 2=Pending ,3=cancel/Failed, 
	private Long tid;
	 
	
	public long getOrderSrno() {
		return orderSrno;
	}
	public void setOrderSrno(long orderSrno) {
		this.orderSrno = orderSrno;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
	public Long getTid() {
		return tid;
	}
	public void setTid(Long tid) {
		this.tid = tid;
	}
	@Override
	public String toString() {
		return "OrderHeader [orderSrno=" + orderSrno + ", orderId=" + orderId + ", userUuid=" + userUuid
				+ ", contactName=" + contactName + ", orderDate=" + orderDate + ", totalQauntity=" + totalQauntity
				+ ", totalAmount=" + totalAmount + ", totalDiscount=" + totalDiscount + ", payableAmount="
				+ payableAmount + ", address=" + address + ", city=" + city + ", pinCode=" + pinCode + ", mobileNo="
				+ mobileNo + ", email=" + email + ", orderStatus=" + orderStatus + ", paymentJson=" + paymentJson
				+ ", paidStatus=" + paidStatus + ", tid=" + tid + "]";
	}
	 
	 
	
}
