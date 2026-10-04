package com.leonet.common.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "vendor")
public class VendorEntity implements Serializable{
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "vender_code")
	private long venderCode=Long.parseLong(new SimpleDateFormat("yyMMddhhmmSSS").format(new Date()));

	@Column(name = "vendor_name")
	private String vendorName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone_Number")
	private String phoneNumber;
	
	@Column(name = "is_active")
	private int isActive;
	
	@Column(name = "remark")
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "VendorEntity [id=" + id + ", venderCode=" + venderCode + ", vendorName=" + vendorName + ", address="
				+ address + ", email=" + email + ", phoneNumber=" + phoneNumber + ", isActive=" + isActive + ", remark="
				+ remark + "]";
	}

	 
}
