package com.leonet.common.pojo;

public class VendorPojo {

	private long id;
	private long venderCode;
	private String  vendorName;
	private String address;
	private String email;
	private String phoneNumber;
	private String remark;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getVenderCode() {
		return venderCode;
	}
	public void setVenderCode(long venderCode) {
		this.venderCode = venderCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "VendorPojo [id=" + id + ", venderCode=" + venderCode + ", vendorName=" + vendorName + ", address="
				+ address + ", email=" + email + ", phoneNumber=" + phoneNumber + ", remark=" + remark + "]";
	}
	 
	
}
