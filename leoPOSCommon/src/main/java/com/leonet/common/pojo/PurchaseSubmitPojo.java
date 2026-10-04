package com.leonet.common.pojo;

import java.util.Date;

public class PurchaseSubmitPojo {
	private long purchaseId;
	private Date date;
	private long qty;
	private long usdollar;
	private long bzdprice;
	private float percentagecost;
	private long cost;
	private float unitcost;
	private float sellingpercentaget;
	private long sellingprice;
	private long applied;
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
	public long getUsdollar() {
		return usdollar;
	}
	public void setUsdollar(long usdollar) {
		this.usdollar = usdollar;
	}
	public long getBzdprice() {
		return bzdprice;
	}
	public void setBzdprice(long bzdprice) {
		this.bzdprice = bzdprice;
	}
	public float getPercentagecost() {
		return percentagecost;
	}
	public void setPercentagecost(float percentagecost) {
		this.percentagecost = percentagecost;
	}
	public long getCost() {
		return cost;
	}
	public void setCost(long cost) {
		this.cost = cost;
	}
	public float getUnitcost() {
		return unitcost;
	}
	public void setUnitcost(float unitcost) {
		this.unitcost = unitcost;
	}
	public float getSellingpercentaget() {
		return sellingpercentaget;
	}
	public void setSellingpercentaget(float sellingpercentaget) {
		this.sellingpercentaget = sellingpercentaget;
	}
	public long getSellingprice() {
		return sellingprice;
	}
	public void setSellingprice(long sellingprice) {
		this.sellingprice = sellingprice;
	}
	
	public long getApplied() {
		return applied;
	}
	public void setApplied(long applied) {
		this.applied = applied;
	}
	@Override
	public String toString() {
		return "PurchaseSubmitPojo [purchaseId=" + purchaseId + ", date=" + date + ", qty=" + qty + ", usdollar="
				+ usdollar + ", bzdprice=" + bzdprice + ", percentagecost=" + percentagecost + ", cost=" + cost
				+ ", unitcost=" + unitcost + ", sellingpercentaget=" + sellingpercentaget + ", sellingprice="
				+ sellingprice + ", applied=" + applied + "]";
	}
	
	
	

}
