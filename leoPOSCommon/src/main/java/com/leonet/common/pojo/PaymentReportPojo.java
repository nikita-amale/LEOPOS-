package com.leonet.common.pojo;

import java.util.Date;

public class PaymentReportPojo {
	
	private Long id;
	private long bulkid;
	private double grand_total;
	private String ptype;
	private long member_id;
	private String member_name;
	private Date paymentdate;
	
	public PaymentReportPojo() {
		super();
	}
	
	public PaymentReportPojo(Long id, long bulkid, double grand_total, String ptype, long member_id, String member_name,
			Date paymentdate) {
		super();
		this.id = id;
		this.bulkid = bulkid;
		this.grand_total = grand_total;
		this.ptype = ptype;
		this.member_id = member_id;
		this.member_name = member_name;
		this.paymentdate = paymentdate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getBulkid() {
		return bulkid;
	}

	public void setBulkid(long bulkid) {
		this.bulkid = bulkid;
	}

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
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

	public Date getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(Date paymentdate) {
		this.paymentdate = paymentdate;
	}

	@Override
	public String toString() {
		return "PaymentReportPojo [id=" + id + ", bulkid=" + bulkid + ", grand_total=" + grand_total + ", ptype="
				+ ptype + ", member_id=" + member_id + ", member_name=" + member_name + ", paymentdate=" + paymentdate
				+ "]";
	}
	
	
	
	
	

}
