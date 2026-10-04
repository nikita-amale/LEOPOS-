package com.leonet.common.pojo;


import java.util.Date;


public class QuotesPojo {
	
	

	private long quotesId;
	

	private Date date;

	
	private String referenceno;
	

	private long memberid;
	
	
	private String member_name;
	

	private long user_id;
	

	private long warehouse_id;
	
	private String note;
	
	
	private double total;
	
	
	private double product_discount;

	

	private double product_rollprice;
	
	
	private long order_discount_id;
	

	private double total_discount;
	

	private double order_discount;
	
	
	private double product_tax; 
	

	private long order_tax_id;
	

	private double order_tax;
	

	private double total_tax;
	

	private String grandtotal;
	
	
	private String quotes_status;
	
	
	private String payment_status;

	private Date due_date;
	
	private String cf1;
	private String ctype;
	private String createdBy;
	 private String customeraddress;
	 private String pincode;
	 private String phonemain;
	private double creditpayment;
	private int blocked;
	

	public long getQuotesId() {
		return quotesId;
	}

	public void setQuotesId(long quotesId) {
		this.quotesId = quotesId;
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

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getProduct_discount() {
		return product_discount;
	}

	public void setProduct_discount(double product_discount) {
		this.product_discount = product_discount;
	}

	public double getProduct_rollprice() {
		return product_rollprice;
	}

	public void setProduct_rollprice(double product_rollprice) {
		this.product_rollprice = product_rollprice;
	}

	public long getOrder_discount_id() {
		return order_discount_id;
	}

	public void setOrder_discount_id(long order_discount_id) {
		this.order_discount_id = order_discount_id;
	}

	public double getTotal_discount() {
		return total_discount;
	}

	public void setTotal_discount(double total_discount) {
		this.total_discount = total_discount;
	}

	public double getOrder_discount() {
		return order_discount;
	}

	public void setOrder_discount(double order_discount) {
		this.order_discount = order_discount;
	}

	public double getProduct_tax() {
		return product_tax;
	}

	public void setProduct_tax(double product_tax) {
		this.product_tax = product_tax;
	}

	public long getOrder_tax_id() {
		return order_tax_id;
	}

	public void setOrder_tax_id(long order_tax_id) {
		this.order_tax_id = order_tax_id;
	}

	public double getOrder_tax() {
		return order_tax;
	}

	public void setOrder_tax(double order_tax) {
		this.order_tax = order_tax;
	}

	public double getTotal_tax() {
		return total_tax;
	}

	public void setTotal_tax(double total_tax) {
		this.total_tax = total_tax;
	}

	

	public String getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(String grandtotal) {
		this.grandtotal = grandtotal;
	}

	public String getQuotes_status() {
		return quotes_status;
	}

	public void setQuotes_status(String quotes_status) {
		this.quotes_status = quotes_status;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}
	
	

	public String getCf1() {
		return cf1;
	}

	public void setCf1(String cf1) {
		this.cf1 = cf1;
	}
	

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	

	public double getCreditpayment() {
		return creditpayment;
	}

	public void setCreditpayment(double creditpayment) {
		this.creditpayment = creditpayment;
	}
	

	public int getBlocked() {
		return blocked;
	}

	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

	@Override
	public String toString() {
		return "QuotesPojo [quotesId=" + quotesId + ", date=" + date + ", referenceno=" + referenceno + ", memberid="
				+ memberid + ", member_name=" + member_name + ", user_id=" + user_id + ", warehouse_id=" + warehouse_id
				+ ", note=" + note + ", total=" + total + ", product_discount=" + product_discount
				+ ", product_rollprice=" + product_rollprice + ", order_discount_id=" + order_discount_id
				+ ", total_discount=" + total_discount + ", order_discount=" + order_discount + ", product_tax="
				+ product_tax + ", order_tax_id=" + order_tax_id + ", order_tax=" + order_tax + ", total_tax="
				+ total_tax + ", grandtotal=" + grandtotal + ", quotes_status=" + quotes_status + ", payment_status="
				+ payment_status + ", due_date=" + due_date + ", cf1=" + cf1 + ", ctype=" + ctype + ", createdBy="
				+ createdBy + ", customeraddress=" + customeraddress + ", pincode=" + pincode + ", phonemain="
				+ phonemain + ", creditpayment=" + creditpayment + ", blocked=" + blocked + "]";
	}

	


	

	

	

	

	
	
	
	


}
