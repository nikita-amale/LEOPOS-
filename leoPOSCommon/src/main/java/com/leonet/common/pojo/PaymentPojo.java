package com.leonet.common.pojo;

import java.util.Date;

import javax.persistence.Column;

public class PaymentPojo {
	private Long id;

	private long rsaleId;

	private Date paymentdate;
	private String referenceno;
	private String salesreferenceno;

	private long member_id;

	private String member_name;

	private double grand_total;

	private String status;
	
	private String ptype;
	private String ctype;
	
	private String pref;

	private long bulkid;
	
	private String note;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getRsaleId() {
		return rsaleId;
	}

	public void setRsaleId(long rsaleId) {
		this.rsaleId = rsaleId;
	}

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	public long getMember_id() {
		return member_id;
	}

	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getBulkid() {
		return bulkid;
	}

	public void setBulkid(long bulkid) {
		this.bulkid = bulkid;
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
	
	

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	
	
	
	


	public String getSalesreferenceno() {
		return salesreferenceno;
	}

	public void setSalesreferenceno(String salesreferenceno) {
		this.salesreferenceno = salesreferenceno;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	@Override
	public String toString() {
		return "PaymentPojo [id=" + id + ", rsaleId=" + rsaleId + ", paymentdate=" + paymentdate + ", referenceno="
				+ referenceno + ", salesreferenceno=" + salesreferenceno + ", member_id=" + member_id + ", member_name="
				+ member_name + ", grand_total=" + grand_total + ", status=" + status + ", ptype=" + ptype + ", ctype="
				+ ctype + ", pref=" + pref + ", bulkid=" + bulkid + ", note=" + note + "]";
	}
	




	
	

	


	

	


}
