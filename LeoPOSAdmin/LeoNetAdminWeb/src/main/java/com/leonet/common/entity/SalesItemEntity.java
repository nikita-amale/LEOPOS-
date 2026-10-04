/**

 * 
 */
package com.leonet.common.entity;

import java.math.BigDecimal;

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

import com.leonet.common.pojo.CustomerPurchasePojo;
import com.leonet.common.pojo.CustomerReportPojo;
import com.leonet.common.pojo.ProfitLossReportPojo;

/**
 * @author Moninder
 *
 */
@Entity
@Table(name = "sales_items")
@NamedNativeQueries(value = {
	    @NamedNativeQuery(
	        name = "find_profitloss_report",
	        		query = "SELECT \r\n"
	        				+ "    ROUND(SUM(total_income), 2) AS income, \r\n"
	        				+ "    ROUND(SUM(total_cost_of_goods_sold), 2) AS cost_of_goods_sold, \r\n"
	        				+ "    ROUND(SUM(total_income) - SUM(total_cost_of_goods_sold), 2) AS gross_profit\r\n"
	        				+ "FROM (\r\n"
	        				+ "    SELECT \r\n"
	        				+ "        ROUND(SUM(sales.total), 2) AS total_income, \r\n"
	        				+ "        ROUND(SUM(cost_quantity), 2) AS total_cost_of_goods_sold\r\n"
	        				+ "    FROM \r\n"
	        				+ "        sales\r\n"
	        				+ "    JOIN (\r\n"
	        				+ "        SELECT \r\n"
	        				+ "            sale_id, \r\n"
	        				+ "            SUM(cost * quantity) AS cost_quantity\r\n"
	        				+ "        FROM \r\n"
	        				+ "            sales_items\r\n"
	        				+ "        GROUP BY \r\n"
	        				+ "            sale_id\r\n"
	        				+ "    ) AS items_cost \r\n"
	        				+ "    ON sales.sale_id = items_cost.sale_id\r\n"
	        				+ "    WHERE \r\n"
	        				+ "        is_active = 0 \r\n"
	        				+ "        AND DATE(sales.date) >= :startDate \r\n"
	        				+ "        AND DATE(sales.date) <= :endDate\r\n"
	        				+ "\r\n"
	        				+ "    UNION ALL\r\n"
	        				+ "\r\n"
	        				+ "    SELECT \r\n"
	        				+ "        ROUND(SUM(specialsales.total), 2) AS total_income, \r\n"
	        				+ "        ROUND(SUM(cost_quantity), 2) AS total_cost_of_goods_sold\r\n"
	        				+ "    FROM \r\n"
	        				+ "        specialsales\r\n"
	        				+ "    JOIN (\r\n"
	        				+ "        SELECT \r\n"
	        				+ "            sale_id, \r\n"
	        				+ "            SUM(cost * quantity) AS cost_quantity\r\n"
	        				+ "        FROM \r\n"
	        				+ "            specialsales_items\r\n"
	        				+ "        GROUP BY \r\n"
	        				+ "            sale_id\r\n"
	        				+ "    ) AS specialsales_items \r\n"
	        				+ "    ON specialsales.sale_id = specialsales_items.sale_id\r\n"
	        				+ "    WHERE \r\n"
	        				+ "        is_active = 0 \r\n"
	        				+ "        AND DATE(specialsales.date) >= :startDate \r\n"
	        				+ "        AND DATE(specialsales.date) <= :endDate\r\n"
	        				+ ") AS combined_sales",
	        resultSetMapping = "report_profitloss_dto"
	    ),
		
@NamedNativeQuery(name = "find_Customer_Purchase_dto", query = "SELECT GROUP_CONCAT(DISTINCT(specialsales.`sale_id`) SEPARATOR ',') AS sale_id, " +
        "specialsales.`member_name`, SUM(specialsales.`grand_total`) AS grandTotal " +
        "FROM specialsales " +
        "WHERE DATE(specialsales.date) >= :startDate " +  // Start date parameter
        "AND DATE(specialsales.date) <= :endDate " +    // End date parameter
        "AND specialsales.member_name != 'Cash customer' " +
        "AND specialsales.is_active = 0 " +
        "GROUP BY specialsales.member_name " +

        "UNION ALL " +

        "SELECT GROUP_CONCAT(DISTINCT(sales.`sale_id`) SEPARATOR ',') AS sale_id, " +
        "sales.`member_name`, SUM(sales.`grand_total`) AS grandTotal " +
        "FROM sales " +
        "WHERE DATE(sales.date) >= :startDate " +  // Start date parameter
        "AND DATE(sales.date) <= :endDate " +    // End date parameter
        "AND sales.member_name != 'Cash customer' " +
        "AND sales.is_active = 0 " +
        "GROUP BY sales.member_name " +
        "ORDER BY 2 ASC"
		
		, resultSetMapping = "report_customer_purchase_dto")
})
@SqlResultSetMappings(value = {
		 @SqlResultSetMapping(
			        name = "report_profitloss_dto",
			        classes = @ConstructorResult(
			            targetClass = ProfitLossReportPojo.class,
			            columns = {
			                @ColumnResult(name = "income", type = Double.class),  // Match with the field name in ProfitLossReportPojo
			                @ColumnResult(name = "cost_of_goods_sold", type = Double.class),  // Match with the field name in ProfitLossReportPojo
			                @ColumnResult(name = "gross_profit", type = Double.class)  })), // Match with the field name in ProfitLossReportPojo  
		
		@SqlResultSetMapping(name = "report_customer_purchase_dto", classes = @ConstructorResult(targetClass = CustomerPurchasePojo.class, columns = {
				@ColumnResult(name = "sale_id", type = String.class),
				@ColumnResult(name = "member_name", type = String.class),
				@ColumnResult(name = "grandTotal", type = Double.class),
				

				}))
		
			
		})


public class SalesItemEntity {
	

	private static final Long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sale_id")
	private Long saleid;
	
	@Column(name = "productid")
	private Long productid;
	
	@Column(name = "product_code")
	private String product_code;
	
	@Column(name = "product_name")
	private String product_name;
	
	@Column(name = "product_type")
	private String product_type;
	
	@Column(name = "option_id")
	private Long option_id;
	
	@Column(name = "quantity")
	private BigDecimal quantity;
	
	@Column(name = "warehouse_id")
	private Long warehouse_id;
	
	@Column(name = "item_tax")
	private Double item_tax;
	
	@Column(name = "tax_rate_id")
	private Long tax_rate_id;
	
	@Column(name = "tax")
	private String tax;
	
	@Column(name = "discount")
	private String discount;
	
	@Column(name = "item_discount")
	private Double item_discount;
	
	@Column(name = "subtotal")
	private Double subtotal;
	
	@Column(name = "serial_no")
	private String serial_no; 
	
	@Column(name = "real_unit_price")
	private Double real_unit_price;
	
	@Column(name = "sale_item_id")
	private Long sale_item_id;
	
	@Column(name = "product_unit_id")
	private Long product_unit_id;
	
	@Column(name = "unit_quantity")
	private String unit_quantity;
	
	@Column(name = "comment")
	private String comment;
	
	
	@Column(name = "roll")
	private String roll;
	
	@Column(name = "gst")
	private String gst;
	
	@Column(name = "cost")
	private float cost;
	
	@Column(name = "returnqty")
	private String returnqty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSaleid() {
		return saleid;
	}

	public void setSale_id(Long saleid) {
		this.saleid = saleid;
	}

	public Long getProduct_id() {
		return productid;
	}

	public void setProduct_id(Long product_id) {
		this.productid = product_id;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public Long getOption_id() {
		return option_id;
	}

	public void setOption_id(Long option_id) {
		this.option_id = option_id;
	}

	

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(Long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}

	public Double getItem_tax() {
		return item_tax;
	}

	public void setItem_tax(Double item_tax) {
		this.item_tax = item_tax;
	}

	public Long getTax_rate_id() {
		return tax_rate_id;
	}

	public void setTax_rate_id(Long tax_rate_id) {
		this.tax_rate_id = tax_rate_id;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public Double getItem_discount() {
		return item_discount;
	}

	public void setItem_discount(Double item_discount) {
		this.item_discount = item_discount;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public String getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}

	public Double getReal_unit_price() {
		return real_unit_price;
	}

	public void setReal_unit_price(Double real_unit_price) {
		this.real_unit_price = real_unit_price;
	}

	public Long getSale_item_id() {
		return sale_item_id;
	}

	public void setSale_item_id(Long sale_item_id) {
		this.sale_item_id = sale_item_id;
	}

	public Long getProduct_unit_id() {
		return product_unit_id;
	}

	public void setProduct_unit_id(Long product_unit_id) {
		this.product_unit_id = product_unit_id;
	}

	public String getUnit_quantity() {
		return unit_quantity;
	}

	public void setUnit_quantity(String unit_quantity) {
		this.unit_quantity = unit_quantity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}
	
	

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	



	public String getReturnqty() {
		return returnqty;
	}

	public void setReturnqty(String returnqty) {
		this.returnqty = returnqty;
	}
	

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "SalesItemEntity [id=" + id + ", saleid=" + saleid + ", productid=" + productid + ", product_code="
				+ product_code + ", product_name=" + product_name + ", product_type=" + product_type + ", option_id="
				+ option_id + ", quantity=" + quantity + ", warehouse_id=" + warehouse_id + ", item_tax=" + item_tax
				+ ", tax_rate_id=" + tax_rate_id + ", tax=" + tax + ", discount=" + discount + ", item_discount="
				+ item_discount + ", subtotal=" + subtotal + ", serial_no=" + serial_no + ", real_unit_price="
				+ real_unit_price + ", sale_item_id=" + sale_item_id + ", product_unit_id=" + product_unit_id
				+ ", unit_quantity=" + unit_quantity + ", comment=" + comment + ", roll=" + roll + ", gst=" + gst
				+ ", cost=" + cost + ", returnqty=" + returnqty + "]";
	}

	

	

	

	
	
	

	 
}
