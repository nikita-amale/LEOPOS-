package com.leonet.common.pojo;

import java.util.Date;

public class ReturnCashPojo {
	private long returnId;
	private Date date;
	private String referenceno;
	private double amount;
	private double tax;
	private long memberid;
	private String member_name;
	private double userid;
	private String note;
	private String createdBy;
	private String saleid;
	private String salereferenceno;
	private int isActive;
	 private String customeraddress;
	 private String pincode;
	 private String phonemain;
	
	
	public long getReturnId() {
		return returnId;
	}
	public void setReturnId(long returnId) {
		this.returnId = returnId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public long getMemberid() {
		return memberid;
	}
	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public double getUserid() {
		return userid;
	}
	public void setUserid(double userid) {
		this.userid = userid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public String getSaleid() {
		return saleid;
	}
	public void setSaleid(String saleid) {
		this.saleid = saleid;
	}
	
	public String getSalereferenceno() {
		return salereferenceno;
	}
	public void setSalereferenceno(String salereferenceno) {
		this.salereferenceno = salereferenceno;
	}
	
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	public String getCustomeraddress() {
		return customeraddress;
	}
	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPhonemain() {
		return phonemain;
	}
	public void setPhonemain(String phonemain) {
		this.phonemain = phonemain;
	}
	@Override
	public String toString() {
		return "ReturnCashPojo [returnId=" + returnId + ", date=" + date + ", referenceno=" + referenceno + ", amount="
				+ amount + ", tax=" + tax + ", memberid=" + memberid + ", member_name=" + member_name + ", userid="
				+ userid + ", note=" + note + ", createdBy=" + createdBy + ", saleid=" + saleid + ", salereferenceno="
				+ salereferenceno + ", isActive=" + isActive + ", customeraddress=" + customeraddress + ", pincode="
				+ pincode + ", phonemain=" + phonemain + "]";
	}
	

}
