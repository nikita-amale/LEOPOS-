package com.leonet.entity;

import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "purchase_item")
public class PurchaseItemEntity {
		
	
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	
	@Column(name = "puchaseid")
	private long purchaseId;
	
	@Column(name = "product_id")
	private long product_id;
	
	@Column(name = "mpn")
	private String mpn;
	
	@Column(name = "product_name")
	private String product_name;
		
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "warehouse_id")
	private long warehouse_id;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "SellingPrice")
	private BigDecimal sellingprice;
	
	
	@Column(name = "UnitCost")
	private float unitcost;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getMpn() {
		return mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getWarehouse_id() {
		return warehouse_id;
	}

	public void setWarehouse_id(long warehouse_id) {
		this.warehouse_id = warehouse_id;
	}



	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	

	public BigDecimal getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(BigDecimal sellingprice) {
		this.sellingprice = sellingprice;
	}

	public float getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(float unitcost) {
		this.unitcost = unitcost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PurchaseItemEntity [id=" + id + ", purchaseId=" + purchaseId + ", product_id=" + product_id + ", mpn="
				+ mpn + ", product_name=" + product_name + ", quantity=" + quantity + ", warehouse_id=" + warehouse_id
				+ ", total=" + total + ", sellingprice=" + sellingprice + ", unitcost=" + unitcost + "]";
	}

	
		
	
}
