package com.leonet.common.pojo;

public class OrderPojo {

	private String name;
	private String mobile;
	private String email;
	private String city;
	private String address;
	private String pinCode;
	private String userUuid;
	private String payableAmount;
	private String totalQauntity;
	private String totalDiscount;
	private String totalAmount;
	private String orderJson;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOrderJson() {
		return orderJson;
	}
	public void setOrderJson(String orderJson) {
		this.orderJson = orderJson;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	 
	public String getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(String payableAmount) {
		this.payableAmount = payableAmount;
	}
	public String getTotalQauntity() {
		return totalQauntity;
	}
	public void setTotalQauntity(String totalQauntity) {
		this.totalQauntity = totalQauntity;
	}
	public String getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Override
	public String toString() {
		return "OrderPojo [name=" + name + ", mobile=" + mobile + ", email=" + email + ", city=" + city + ", address="
				+ address + ", pinCode=" + pinCode + ", userUuid=" + userUuid + ", payableAmount=" + payableAmount
				+ ", totalQauntity=" + totalQauntity + ", totalDiscount=" + totalDiscount + ", totalAmount="
				+ totalAmount + ", orderJson=" + orderJson + "]";
	}
	 
	 
}
