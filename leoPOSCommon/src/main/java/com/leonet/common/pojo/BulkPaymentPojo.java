package com.leonet.common.pojo;

import java.util.Date;

import javax.persistence.Column;

public class BulkPaymentPojo {

	private long bulkId;

	private double amount;

	private long memberId;

	private String memberName;

	private Date date;

	private String paymentid;
	private String status;

	private String ptype;

	private String pref;
	 
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
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPref(String pref) {
		this.pref = pref;
	}

	@Override
	public String toString() {
		return "BulkPaymentPojo [bulkId=" + bulkId + ", amount=" + amount + ", memberId=" + memberId + ", memberName="
				+ memberName + ", date=" + date + ", paymentid=" + paymentid + ", status=" + status + ", ptype=" + ptype
				+ ", pref=" + pref + ", note=" + note + "]";
	}

	

	

}
