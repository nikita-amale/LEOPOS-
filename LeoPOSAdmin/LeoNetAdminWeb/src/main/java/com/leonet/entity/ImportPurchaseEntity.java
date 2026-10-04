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
@Table(name = "importpurchase")
public class ImportPurchaseEntity {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "purchaseId")
	private long purchaseId;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "productId")
	private Long productId;
	
	@Column(name = "productName")
	private String productName;
	
	@Column(name = "Qty")
	private long qty;
	
	@Column(name = "US_Dollar")
	private float usdollar;
	
	@Column(name = "BZD_price")
	private float bzdprice;
	
	@Column(name = "PercentageCost")
	private float percentagecost;
	
	@Column(name = "Cost")
	private float cost;
	
	@Column(name = "UnitCost")
	private float unitcost;
	
	@Column(name = "SellingPercentage")
	private float sellingpercentage;
	
	@Column(name = "SellingPrice")
	private BigDecimal sellingprice;
	
	@Column(name = "Applied")
	private int applied;
	
	@Column(name = "supplier")
	private String supplier;
	
	@Column(name = "oldprice")
	private BigDecimal oldprice;
	
	@Column(name = "mpn")
	private String mpn;

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getQty() {
		return qty;
	}

	public void setQty(long qty) {
		this.qty = qty;
	}

	public float getUsdollar() {
		return usdollar;
	}

	public void setUsdollar(float usdollar) {
		this.usdollar = usdollar;
	}

	public float getBzdprice() {
		return bzdprice;
	}

	public void setBzdprice(float bzdprice) {
		this.bzdprice = bzdprice;
	}

	public float getPercentagecost() {
		return percentagecost;
	}

	public void setPercentagecost(float percentagecost) {
		this.percentagecost = percentagecost;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(float unitcost) {
		this.unitcost = unitcost;
	}

	public float getSellingpercentage() {
		return sellingpercentage;
	}

	public void setSellingpercentage(float sellingpercentage) {
		this.sellingpercentage = sellingpercentage;
	}

	public BigDecimal getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(BigDecimal sellingprice) {
		this.sellingprice = sellingprice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getApplied() {
		return applied;
	}

	public void setApplied(int applied) {
		this.applied = applied;
	}
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	

	public BigDecimal getOldprice() {
		return oldprice;
	}

	public void setOldprice(BigDecimal oldprice) {
		this.oldprice = oldprice;
	}
	

	public String getMpn() {
		return mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	@Override
	public String toString() {
		return "ImportPurchaseEntity [purchaseId=" + purchaseId + ", date=" + date + ", productId=" + productId
				+ ", productName=" + productName + ", qty=" + qty + ", usdollar=" + usdollar + ", bzdprice=" + bzdprice
				+ ", percentagecost=" + percentagecost + ", cost=" + cost + ", unitcost=" + unitcost
				+ ", sellingpercentage=" + sellingpercentage + ", sellingprice=" + sellingprice + ", applied=" + applied
				+ ", supplier=" + supplier + ", oldprice=" + oldprice + ", mpn=" + mpn + "]";
	}

	
	
	
}
