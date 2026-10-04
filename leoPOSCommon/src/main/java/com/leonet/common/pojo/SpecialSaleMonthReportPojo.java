package com.leonet.common.pojo;

public class SpecialSaleMonthReportPojo {
	private Long sale_id;
	private String referenceno;
	private long memberid;
	private String member_name;
	private String ctype;
	private double total;
	private double total_discount;
	private double total_tax;
	private double grand_total;
	private String paymentstatus;
	private double paid;
	private String Date; 
	
	public SpecialSaleMonthReportPojo() {
		super();
	}
	
	public SpecialSaleMonthReportPojo(Long saleId, Long memberid ,String referenceno,String membername,String ctype,String paymentstatus,double total,double total_tax, double grand_total,double total_discount,double paid,String Date) {
		super();
		this.sale_id = saleId;
		this.memberid = memberid;
		this.referenceno= referenceno;
		this.member_name=membername;
		this.ctype=ctype;
	    this.paymentstatus=paymentstatus;
	    this.total=total;
	    this.total_discount=total_discount;
	    this.paid=paid;
	    this.total_tax=total_tax;
	    this.grand_total=grand_total;
	    this.Date = Date;
	
	}

	public Long getSale_id() {
		return sale_id;
	}

	public void setSale_id(Long sale_id) {
		this.sale_id = sale_id;
	}

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
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

	public double getGrand_total() {
		return grand_total;
	}

	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal_discount() {
		return total_discount;
	}

	public void setTotal_discount(double total_discount) {
		this.total_discount = total_discount;
	}

	public double getTotal_tax() {
		return total_tax;
	}

	public void setTotal_tax(double total_tax) {
		this.total_tax = total_tax;
	}

	

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}
	

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	@Override
	public String toString() {
		return "SpecialSaleMonthReportPojo [sale_id=" + sale_id + ", referenceno=" + referenceno + ", memberid="
				+ memberid + ", member_name=" + member_name + ", ctype=" + ctype + ", total=" + total
				+ ", total_discount=" + total_discount + ", total_tax=" + total_tax + ", grand_total=" + grand_total
				+ ", paymentstatus=" + paymentstatus + ", paid=" + paid + ", Date=" + Date + "]";
	}

	

	
	
 
}
