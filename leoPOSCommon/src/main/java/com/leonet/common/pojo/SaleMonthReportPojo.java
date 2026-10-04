package com.leonet.common.pojo;

import java.sql.Date;

public class SaleMonthReportPojo {
	private Long sale_id;
	private String referenceno;
	private long memberid;
	private String membername;
	private String ctype;
	private double total;
	private double total_discount;
	private double total_tax;
	private double grandtotal;
	private String paymentstatus;
	private double paid;
	  private String Date; 

	public SaleMonthReportPojo() {
		super();
	}
	
	public SaleMonthReportPojo(Long saleId, Long memberid ,String referenceno,String membername,String ctype,String paymentstatus,double total,double total_tax, double grand_total,double total_discount,double paid,String Date) {
		super();
		this.sale_id = saleId;
		this.memberid = memberid;
		this.referenceno= referenceno;
		this.membername=membername;
		this.ctype=ctype;
	    this.paymentstatus=paymentstatus;
	    this.total=total;
	    this.total_discount=total_discount;
	    this.paid=paid;
	    this.total_tax=total_tax;
	    this.grandtotal=grand_total;
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

	

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public double getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(double grandtotal) {
		this.grandtotal = grandtotal;
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
		return "SaleMonthReportPojo [sale_id=" + sale_id + ", referenceno=" + referenceno + ", memberid=" + memberid
				+ ", membername=" + membername + ", ctype=" + ctype + ", total=" + total + ", total_discount="
				+ total_discount + ", total_tax=" + total_tax + ", grandtotal=" + grandtotal + ", paymentstatus="
				+ paymentstatus + ", paid=" + paid + ", Date=" + Date + "]";
	}

	
	

	
	
	



	

}
