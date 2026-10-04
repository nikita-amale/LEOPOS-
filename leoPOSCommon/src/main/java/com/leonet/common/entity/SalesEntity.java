
/**

 * 
 */
package com.leonet.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.leonet.common.pojo.ReplenishmentRepotPojo;
import com.leonet.common.pojo.SaleMonthReportPojo;
import com.leonet.common.pojo.SaleReportSummaryPojo;

/**
 * @author Moninder
 *
 */
@Entity
@Table(name = "sales")
@NamedNativeQueries(value = {
		 @NamedNativeQuery(name = "find_Sale_Summary_dto",  query =
				 "SELECT\n" +
						    "    product_code,\n" +
						    "    product_name,\n" +
						    "    GROUP_CONCAT(DISTINCT CONCAT(member_name, '(', ctype, ')') ORDER BY member_name) AS member_name,\n" +
						    "    GROUP_CONCAT(DISTINCT sale_id ORDER BY sale_id) AS sale_id,\n" +
						    "    GROUP_CONCAT(DISTINCT referenceno ORDER BY referenceno) AS referenceno,\n" +
						    "    SUM(quantity) AS total_quantity\n" +
						    "FROM (\n" +
						    "    SELECT\n" +
						    "        si.product_code,\n" +
						    "        si.product_name,\n" +
						    "        ss.sale_id,\n" +
						    "        GROUP_CONCAT(DISTINCT ss.member_name ORDER BY ss.member_name) AS member_name,\n" +
						    "        SUM(si.quantity) AS quantity,\n" +
						    "        GROUP_CONCAT(DISTINCT ss.referenceno ORDER BY ss.referenceno) AS referenceno,\n" +
						    "        MAX(ss.ctype) AS ctype\n" +
						    "    FROM\n" +
						    "        sales ss\n" +
						    "        INNER JOIN sales_items si ON ss.sale_id = si.sale_id\n" +
						    "    WHERE\n" +
						    "        DATE(ss.date) >= :startDate AND DATE(ss.date) <= :endDate AND ss.is_active = 0\n" +
						    "    GROUP BY\n" +
						    "        si.product_code, si.product_name, ss.sale_id\n" +
						    "\n" +
						    "    UNION ALL\n" +
						    "\n" +
						    "    SELECT\n" +
						    "        spsi.product_code,\n" +
						    "        spsi.product_name,\n" +
						    "        sss.sale_id,\n" +
						    "        GROUP_CONCAT(DISTINCT sss.member_name ORDER BY sss.member_name) AS member_name,\n" +
						    "        SUM(spsi.quantity) AS quantity,\n" +
						    "        GROUP_CONCAT(DISTINCT sss.referenceno ORDER BY sss.referenceno) AS referenceno,\n" +
						    "        MAX(sss.ctype) AS ctype\n" +
						    "    FROM\n" +
						    "        specialsales sss\n" +
						    "        INNER JOIN specialsales_items spsi ON sss.sale_id = spsi.sale_id\n" +
						    "    WHERE\n" +
						    "        DATE(sss.date) >= :startDate AND DATE(sss.date) <= :endDate AND sss.is_active = 0\n" +
						    "    GROUP BY\n" +
						    "        spsi.product_code, spsi.product_name, sss.sale_id\n" +
						    ") AS combined_results\n" +
						    "GROUP BY\n" +
						    "    product_code, product_name",
	        resultSetMapping = "report_Sale_Summary_dto"
	    ),
		
		@NamedNativeQuery(name = "find_total_sale_report", query = "SELECT IFNULL(ROUND(SUM(total),2),0) AS total,"
				+ " IFNULL(ROUND(SUM(total_tax),2),0) As total_tax, "
				+ " IFNULL(ROUND(SUM(grand_total),2),0) AS grand_total "
				+ " FROM sales "
				+ " WHERE DATE(date) >= :startDate AND DATE(date) <= :endDate "
				+ "AND is_active=0 "
				, resultSetMapping = "report_total_Sale_dto"),
		
		@NamedNativeQuery(name = "find_total_sale_report_by_ctype", query = "SELECT IFNULL(ROUND(SUM(total),2),0) AS total,"
				+ " IFNULL(ROUND(SUM(total_tax),2),0) As total_tax, "
				+ " IFNULL(ROUND(SUM(grand_total),2),0) AS grand_total "
				+ " FROM sales "
				+ " WHERE DATE(date) >= :startDate AND DATE(date) <= :endDate "
				+ " AND ctype = :cType AND is_active=0 "
				, resultSetMapping = "report_total_Sale_dto"),
		
		@NamedNativeQuery(name = "find_Sale_Month_dto", query = "SELECT sales.`sale_id`,DATE(sales.created_date) AS Date,sales.`ctype`,sales.`membername`,sales.`memberid`,sales.`total`,sales.`total_tax`,sales.total_discount,sales.paid,\r\n"
				+ "sales.`grandtotal`,sales.`paymentstatus`,sales.`referenceno` "
				+ "FROM sales "
				+ " WHERE DATE(date) >= :startDate AND DATE(date) <= :endDate"
				+ " ORDER BY created_date ASC "
				, resultSetMapping = "report_Sale_Month_dto"),
		
		@NamedNativeQuery(
			    name = "find_Replenishment",
			    query = "SELECT "
			            + "subquery.code, "
			            + "MAX(subquery.NAME) AS NAME, "
			            + "MAX(subquery.cf1) AS cf1, "
			            + "COALESCE(SUM(subquery.sold_quantity), 0) AS sold_quantity, "
			            + "MAX(subquery.available_quantity) AS available_quantity "
			            + "FROM ("
			            + "    SELECT "
			            + "        p.code, "
			            + "        MAX(p.name) AS NAME, "
			            + "        MAX(p.cf1) AS cf1, "
			            + "        MAX(p.quantity) AS available_quantity, "
			            + "        SUM(si.quantity) AS sold_quantity "
			            + "    FROM "
			            + "        product_details p "
			            + "    LEFT JOIN "
			            + "        sales_items si ON p.code = si.productid "
			            + "    LEFT JOIN "
			            + "        sales s ON s.sale_id = si.sale_id "
			            + "    WHERE s.is_active=0 AND "
			            + "        DATE(s.created_date) BETWEEN :startDate AND :endDate "
			            + "    GROUP BY "
			            + "        p.code "

			            + "    UNION ALL "

			            + "    SELECT "
			            + "        p.code, "
			            + "        MAX(p.name) AS NAME, "
			            + "        MAX(p.cf1) AS cf1, "
			            + "        MAX(p.quantity) AS available_quantity, "
			            + "        SUM(ssi.quantity) AS sold_quantity "
			            + "    FROM "
			            + "        product_details p "
			            + "    LEFT JOIN "
			            + "        specialsales_items ssi ON p.code = ssi.productid "
			            + "    LEFT JOIN "
			            + "        specialsales ss ON ss.sale_id = ssi.sale_id "
			            + "    WHERE ss.is_active=0 AND "
			            + "        DATE(ss.created_date) BETWEEN :startDate AND :endDate "
			            + "    GROUP BY "
			            + "        p.code "
			            + ") AS subquery "
			            + "GROUP BY "
			            + "    subquery.code "
			            + "HAVING "
			            + "    COALESCE(SUM(subquery.sold_quantity), 0) > MAX(subquery.available_quantity)",
			    resultSetMapping = "report_Replenishment"
			)


		})
@SqlResultSetMappings(value = {
		@SqlResultSetMapping(name = "report_Sale_Summary_dto", classes = @ConstructorResult(targetClass = SaleReportSummaryPojo.class, columns = {
				@ColumnResult(name = "product_code", type = Long.class),
				@ColumnResult(name = "product_name", type = String.class),
				@ColumnResult(name = "total_quantity", type = Long.class),
				@ColumnResult(name = "member_name", type = String.class),
				@ColumnResult(name = "sale_id", type = String.class),
				@ColumnResult(name = "referenceno", type = String.class)})),
		
		@SqlResultSetMapping(name = "report_total_Sale_dto", classes = @ConstructorResult(targetClass = SaleReportSummaryPojo.class, columns = {
				@ColumnResult(name = "total", type = Double.class),
				@ColumnResult(name = "total_tax", type = Double.class),
				@ColumnResult(name = "grand_total", type = Double.class) })) ,
		
		@SqlResultSetMapping(name = "report_Sale_Month_dto", classes = @ConstructorResult(targetClass = SaleMonthReportPojo.class, columns = {
				@ColumnResult(name = "sale_id", type = Long.class),
				@ColumnResult(name = "memberid", type = Long.class),
				@ColumnResult(name = "referenceno", type = String.class),
				@ColumnResult(name = "membername", type = String.class),
				@ColumnResult(name = "ctype", type = String.class),
				@ColumnResult(name = "paymentstatus", type = String.class),
				@ColumnResult(name = "total", type = Double.class),
				@ColumnResult(name = "total_tax", type = Double.class),
				@ColumnResult(name = "grandtotal", type = Double.class),
				@ColumnResult(name = "total_discount", type = Double.class),
				@ColumnResult(name = "paid", type = Double.class),
				@ColumnResult(name = "Date", type = String.class)})) ,
		
		
		@SqlResultSetMapping(name = "report_Replenishment", classes = @ConstructorResult(targetClass = ReplenishmentRepotPojo.class, columns = {
				@ColumnResult(name = "code", type = Long.class),
				@ColumnResult(name = "name", type = String.class),
				@ColumnResult(name = "cf1", type = String.class),
				@ColumnResult(name = "sold_quantity", type = Long.class),
				@ColumnResult(name = "available_quantity", type = Long.class) }))
		})
public class SalesEntity extends Auditable<String> implements Serializable {

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

	@Column(name = "due_date")
	@Temporal(TemporalType.DATE)
	private Date due_date;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}
	
	public double getCreditpay() {
		return creditpay;
	}

	public void setCreditpay(double creditpay) {
		this.creditpay = creditpay;
	}
	

	public String getGrandtotal() {
		return grandtotal;
	}

	public void setGrandtotal(String grandtotal) {
		this.grandtotal = grandtotal;
	}

	@Override
	public String toString() {
		return "SalesEntity [saleId=" + saleId + ", date=" + date + ", referenceno=" + referenceno + ", memberid="
				+ memberid + ", member_name=" + member_name + ", membername=" + membername + ", purchaseorder="
				+ purchaseorder + ", warehouse_id=" + warehouse_id + ", note=" + note + ", total=" + total
				+ ", product_discount=" + product_discount + ", product_rollprice=" + product_rollprice
				+ ", order_discount_id=" + order_discount_id + ", total_discount=" + total_discount
				+ ", order_discount=" + order_discount + ", product_tax=" + product_tax + ", order_tax_id="
				+ order_tax_id + ", order_tax=" + order_tax + ", total_tax=" + total_tax + ", grand_total="
				+ grand_total + ", grandtotal=" + grandtotal + ", sale_status=" + sale_status + ", paid=" + paid
				+ ", paymentstatus=" + paymentstatus + ", due_date=" + due_date + ", cf1=" + cf1 + ", ctype=" + ctype
				+ ", fileName=" + fileName + ", isActive=" + isActive + ", customeraddress=" + customeraddress
				+ ", pincode=" + pincode + ", phonemain=" + phonemain + ", creditpay=" + creditpay + "]";
	}

	

	
	

}
