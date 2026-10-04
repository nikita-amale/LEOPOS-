package com.leonet.common.pojo;

import java.util.Date;

public class DeletSalePojo {
	
	private Long saleId;
	private Date date;
	private String referenceno;
	private long memberid;
	private String member_name;
	private String purchaseorder;
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
	private double grand_total;
	private String sale_status;
	private double paid;
	private String paymentstatus;
	private Date due_date;
	private String cf1;
	private String ctype;
	private String createdBy;
	private int isActive;
	private String customeraddress;
	private String pincode;
	private String phonemain;
	private double creditpay;
	public Long getSaleId() {
		return saleId;
	}
	public void setSaleId(Long saleId) {
		this.saleId = saleId;
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
	public String getPurchaseorder() {
		return purchaseorder;
	}
	public void setPurchaseorder(String purchaseorder) {
		this.purchaseorder = purchaseorder;
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
	public double getGrand_total() {
		return grand_total;
	}
	public void setGrand_total(double grand_total) {
		this.grand_total = grand_total;
	}
	public String getSale_status() {
		return sale_status;
	}
	public void setSale_status(String sale_status) {
		this.sale_status = sale_status;
	}
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
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
	public double getCreditpay() {
		return creditpay;
	}
	public void setCreditpay(double creditpay) {
		this.creditpay = creditpay;
	}
	@Override
	public String toString() {
		return "DeletSalePojo [saleId=" + saleId + ", date=" + date + ", referenceno=" + referenceno + ", memberid="
				+ memberid + ", member_name=" + member_name + ", purchaseorder=" + purchaseorder + ", warehouse_id="
				+ warehouse_id + ", note=" + note + ", total=" + total + ", product_discount=" + product_discount
				+ ", product_rollprice=" + product_rollprice + ", order_discount_id=" + order_discount_id
				+ ", total_discount=" + total_discount + ", order_discount=" + order_discount + ", product_tax="
				+ product_tax + ", order_tax_id=" + order_tax_id + ", order_tax=" + order_tax + ", total_tax="
				+ total_tax + ", grand_total=" + grand_total + ", sale_status=" + sale_status + ", paid=" + paid
				+ ", paymentstatus=" + paymentstatus + ", due_date=" + due_date + ", cf1=" + cf1 + ", ctype=" + ctype
				+ ", createdBy=" + createdBy + ", isActive=" + isActive + ", customeraddress=" + customeraddress
				+ ", pincode=" + pincode + ", phonemain=" + phonemain + ", creditpay=" + creditpay + "]";
	}
	
	
	

}
