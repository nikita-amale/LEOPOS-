package com.leonet.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.leonet.common.entity.Auditable;


@Entity
@Table(name = "returns")
public class ReturnsEntity extends Auditable<String> implements Serializable {
	
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "returnId")
	private long returnId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "referenceno")
	private String referenceno;
	
	@Column(name = "salereferenceno")
	private String salereferenceno;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "tax")
	private double tax;
	
	@Column(name = "memberid")
	private long memberid;
	
	@Column(name = "member_name")
	private String member_name;
	
	@Column(name = "total")
	private double userid;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "saleid")
	private String saleid;
	
	@Column(name = "is_active")
	private int isActive;
	@Column(name = "customeraddress")
	 private String customeraddress;
	
	@Column(name = "pincode")
	 private String pincode;
	
	@Column(name = "phonemain")
	 private String phonemain;
	
	@Column(name = "membername")
	private String membername;
	
	@Column(name = "returnType")
	private long returnType;
	
	
	
	

	public long getReturnType() {
		return returnType;
	}

	public void setReturnType(long returnType) {
		this.returnType = returnType;
	}

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
	
	public String getSalereferenceno() {
		return salereferenceno;
	}

	public void setSalereferenceno(String salereferenceno) {
		this.salereferenceno = salereferenceno;
	}
	

	public String getSaleid() {
		return saleid;
	}

	public void setSaleid(String saleid) {
		this.saleid = saleid;
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
	

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	@Override
	public String toString() {
		return "ReturnsEntity [returnId=" + returnId + ", date=" + date + ", referenceno=" + referenceno
				+ ", salereferenceno=" + salereferenceno + ", amount=" + amount + ", tax=" + tax + ", memberid="
				+ memberid + ", member_name=" + member_name + ", userid=" + userid + ", note=" + note + ", saleid="
				+ saleid + ", isActive=" + isActive + ", customeraddress=" + customeraddress + ", pincode=" + pincode
				+ ", phonemain=" + phonemain + ", membername=" + membername + ", returnType=" + returnType + "]";
	}

	
	

	
	
		
}
