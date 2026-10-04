package com.leonet.common.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class RsalePojo {

	
	private long rsaleId;
	private String rquoteId;
	private Date date;
	private long member_id;
	private long memberid;
	private String member_name;
	private long user_id;
	private String note;
	private double total;
	private double product_discount;
	private long order_discount_id;
	private double total_discount;
	private double order_discount;
	private double product_tax; 
	private long order_tax_id;
	private double order_tax;
	private double total_tax;
	private double grand_total;
	private double paid;
	private String payment_status;
	private Date due_date;
	private String customeraddress;
	private String pincode;
	private String phonemain;
	private String isactive;
	private String ctype;
	private Date deliverydate;
	private Date pickupdate;

	 private String Referenceno;
	public long getRsaleId() {
		return rsaleId;
	}
	public void setRsaleId(long rsaleId) {
		this.rsaleId = rsaleId;
	}
	public String getRquoteId() {
		return rquoteId;
	}
	public void setRquoteId(String rquoteId) {
		this.rquoteId = rquoteId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
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
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
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
	
	public String getIsactive() {
		return isactive;
	}
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}
	
	public String getReferenceno() {
		return Referenceno;
	}
	public void setReferenceno(String referenceno) {
		Referenceno = referenceno;
	}
	
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	
	public long getMemberid() {
		return memberid;
	}
	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}
	
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	public Date getPickupdate() {
		return pickupdate;
	}
	public void setPickupdate(Date pickupdate) {
		this.pickupdate = pickupdate;
	}
	@Override
	public String toString() {
		return "RsalePojo [rsaleId=" + rsaleId + ", rquoteId=" + rquoteId + ", date=" + date + ", member_id="
				+ member_id + ", memberid=" + memberid + ", member_name=" + member_name + ", user_id=" + user_id
				+ ", note=" + note + ", total=" + total + ", product_discount=" + product_discount
				+ ", order_discount_id=" + order_discount_id + ", total_discount=" + total_discount
				+ ", order_discount=" + order_discount + ", product_tax=" + product_tax + ", order_tax_id="
				+ order_tax_id + ", order_tax=" + order_tax + ", total_tax=" + total_tax + ", grand_total="
				+ grand_total + ", paid=" + paid + ", payment_status=" + payment_status + ", due_date=" + due_date
				+ ", customeraddress=" + customeraddress + ", pincode=" + pincode + ", phonemain=" + phonemain
				+ ", isactive=" + isactive + ", ctype=" + ctype + ", deliverydate=" + deliverydate + ", pickupdate="
				+ pickupdate + ", Referenceno=" + Referenceno + "]";
	}
	

	
	
	
	
	
	
	
	
}
