package com.leonet.common.pojo;



public class PurchaseItemPojo {

	private long id;

	private long purchaseid;

	private long product_id;

	private String mpn;

	private String product_name;

	private long quantity;

	private long warehouse_id;

	private String total;
	private float sellingprice;
	private float unitcost;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(long purchaseid) {
		this.purchaseid = purchaseid;
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}
	
	

	public float getSellingprice() {
		return sellingprice;
	}

	public void setSellingprice(float sellingprice) {
		this.sellingprice = sellingprice;
	}

	public float getUnitcost() {
		return unitcost;
	}

	public void setUnitcost(float unitcost) {
		this.unitcost = unitcost;
	}

	@Override
	public String toString() {
		return "PurchaseItemPojo [id=" + id + ", purchaseid=" + purchaseid + ", product_id=" + product_id + ", mpn="
				+ mpn + ", product_name=" + product_name + ", quantity=" + quantity + ", warehouse_id=" + warehouse_id
				+ ", total=" + total + ", sellingprice=" + sellingprice + ", unitcost=" + unitcost + "]";
	}

	
	

}
