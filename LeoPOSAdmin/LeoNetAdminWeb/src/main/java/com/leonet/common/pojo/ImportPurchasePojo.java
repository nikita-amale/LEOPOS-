package com.leonet.common.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;

public class ImportPurchasePojo {
	private long purchaseId;
	private Date date;
	private long qty;
	private long productId;
	private String productName;
	private float usdollar;
	private float bzdprice;
	private float percentagecost;
	private float cost;
	private float unitcost;
	private float sellingpercentage;
	private BigDecimal sellingprice;
	private String supplier;
	private int applied;
	private BigDecimal oldprice;
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
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
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
	public int getApplied() {
		return applied;
	}
	public void setApplied(int applied) {
		this.applied = applied;
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
		return "ImportPurchasePojo [purchaseId=" + purchaseId + ", date=" + date + ", qty=" + qty + ", productId="
				+ productId + ", productName=" + productName + ", usdollar=" + usdollar + ", bzdprice=" + bzdprice
				+ ", percentagecost=" + percentagecost + ", cost=" + cost + ", unitcost=" + unitcost
				+ ", sellingpercentage=" + sellingpercentage + ", sellingprice=" + sellingprice + ", supplier="
				+ supplier + ", applied=" + applied + ", oldprice=" + oldprice + ", mpn=" + mpn + "]";
	}
	
	

}
