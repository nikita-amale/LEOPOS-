package com.leonet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bulkpayment")

public class BulkPaymentEntity {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bulkId")
	private long bulkId;

	@Column(name = "memberId")
	private long memberId;

	@Column(name = "memberName")
	private String memberName;

	@Column(name = "amount")
	private double amount;

	@Column(name = "date")
	private Date date;

	@Column(name = "paymentid")
	private String paymentid;

	@Column(name = "ptype")
	private String ptype;

	@Column(name = "pref")
	private String pref;

	@Column(name = "status")
	private String status;
	
	@Column(name = "note")
	private String note;

	public long getBulkId() {
		return bulkId;
	}

	public void setBulkId(long bulkId) {
		this.bulkId = bulkId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPref() {
		return pref;
	}

	public void setPref(String pref) {
		this.pref = pref;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "BulkPaymentEntity [bulkId=" + bulkId + ", memberId=" + memberId + ", memberName=" + memberName
				+ ", amount=" + amount + ", date=" + date + ", paymentid=" + paymentid + ", ptype=" + ptype + ", pref="
				+ pref + ", status=" + status + ", note=" + note + "]";
	}

	

	

}
