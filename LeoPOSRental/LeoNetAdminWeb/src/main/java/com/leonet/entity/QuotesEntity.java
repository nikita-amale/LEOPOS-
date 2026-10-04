package com.leonet.entity;

import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "quotes")
public class QuotesEntity {
	
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quotesId")
	private long quotesId;
	
	@Column(name = "date")
	private Date date;

	
	private String referenceno;
	
	@Column(name = "member_id")
	private long member_id;
	
	@Column(name = "member_name")
	private String member_name;
	
	@Column(name = "user_id")
	private long user_id;
	
	@Column(name = "warehouse_id")
	private long warehouse_id;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "total")
	private double total;
	
	@Column(name = "product_discount")
	private double product_discount;
	
	@Column(name = "product_rollprice")
	private double product_rollprice;
	
	@Column(name = "order_discount_id")
	private long order_discount_id;
	
	@Column(name = "total_discount")
	private double total_discount;
	
	@Column(name = "order_discount")
	private double order_discount;
	
	@Column(name = "product_tax")
	private double product_tax; 
	
	@Column(name = "order_tax_id")
	private long order_tax_id;
	
	@Column(name = "order_tax")
	private double order_tax;
	
	@Column(name = "total_tax")
	private double total_tax;
	
	@Column(name = "grand_total")
	private double grand_total;
	
	@Column(name = "quotes_status")
	private String quotes_status;
	
	@Column(name = "payment_status")
	private String payment_status;
	
	@Column(name = "due_date")
	private Date due_date;

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

	public String getquotes_status() {
		return quotes_status;
	}

	public void setquotes_status(String quotes_status) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SalesEntity [quotesId=" + quotesId + ", date=" + date + ", reference_no=" + referenceno + ", member_id="
				+ member_id + ", member_name=" + member_name + ", user_id=" + user_id + ", warehouse_id=" + warehouse_id
				+ ", note=" + note + ", total=" + total + ", product_discount=" + product_discount
				+ ", product_rollprice=" + product_rollprice + ", order_discount_id=" + order_discount_id
				+ ", total_discount=" + total_discount + ", order_discount=" + order_discount + ", product_tax="
				+ product_tax + ", order_tax_id=" + order_tax_id + ", order_tax=" + order_tax + ", total_tax="
				+ total_tax + ", grand_total=" + grand_total + ", quotes_status=" + quotes_status + ", payment_status="
				+ payment_status + ", due_date=" + due_date + "]";
	}

}
