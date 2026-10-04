package com.leonet.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "delet_sale") // Optional: specify table name if it differs from the class name
public class DeletSaleEntity {

    private static final long serialVersionUID = 7018838303060755967L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleId")
    private long saleId;

    @Column(name = "date")
    private Date date;

    @Column(name = "referenceno")
    private String referenceno;

    @Column(name = "memberid")
    private long memberid;

    @Column(name = "member_name")
    private String member_name;

    @Column(name = "membername")
    private String membername;

    @Column(name = "purchaseorder")
    private String purchaseorder;

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

    @Column(name = "grandtotal")
    private String grandtotal;

    @Column(name = "sale_status")
    private String sale_status;

    @Column(name = "paid")
    private double paid;

    @Column(name = "paymentstatus")
    private String paymentstatus;

   

    @Column(name = "cf1")
    private String cf1;

    @Column(name = "ctype")
    private String ctype;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "customeraddress")
    private String customeraddress;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "phonemain")
    private String phonemain;

    @Column(name = "creditpay")
    private double creditpay;

    public long getSaleId() {
        return saleId;
    }

    public void setSaleId(long saleId) {
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

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
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

    public String getGrandtotal() {
        return grandtotal;
    }

    public void setGrandtotal(String grandtotal) {
        this.grandtotal = grandtotal;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	@Override
	public String toString() {
		return "DeletSaleEntity [saleId=" + saleId + ", date=" + date + ", referenceno=" + referenceno + ", memberid="
				+ memberid + ", member_name=" + member_name + ", membername=" + membername + ", purchaseorder="
				+ purchaseorder + ", warehouse_id=" + warehouse_id + ", note=" + note + ", total=" + total
				+ ", product_discount=" + product_discount + ", product_rollprice=" + product_rollprice
				+ ", order_discount_id=" + order_discount_id + ", total_discount=" + total_discount
				+ ", order_discount=" + order_discount + ", product_tax=" + product_tax + ", order_tax_id="
				+ order_tax_id + ", order_tax=" + order_tax + ", total_tax=" + total_tax + ", grand_total="
				+ grand_total + ", grandtotal=" + grandtotal + ", sale_status=" + sale_status + ", paid=" + paid
				+ ", paymentstatus=" + paymentstatus + ", cf1=" + cf1 + ", ctype=" + ctype + ", fileName=" + fileName
				+ ", isActive=" + isActive + ", customeraddress=" + customeraddress + ", pincode=" + pincode
				+ ", phonemain=" + phonemain + ", creditpay=" + creditpay + "]";
	}


}
