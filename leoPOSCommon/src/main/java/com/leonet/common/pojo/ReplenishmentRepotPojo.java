package com.leonet.common.pojo;

public class ReplenishmentRepotPojo {
	

	private Long productId;
	private String productName;
	private String cf1;
	private Long soldquantity;
	private Long availableqty;
	
	public ReplenishmentRepotPojo() {
		super();
	}
	public ReplenishmentRepotPojo(Long productId, String productName, String cf1,Long soldquantity,Long availableqty) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.cf1 = cf1;
		this.soldquantity=soldquantity;
		this.availableqty=availableqty;
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
	
	public String getCf1() {
		return cf1;
	}
	public void setCf1(String cf1) {
		this.cf1 = cf1;
	}
	public Long getSoldquantity() {
		return soldquantity;
	}
	public void setSoldquantity(Long soldquantity) {
		this.soldquantity = soldquantity;
	}
	public Long getAvailableqty() {
		return availableqty;
	}
	public void setAvailableqty(Long availableqty) {
		this.availableqty = availableqty;
	}
	@Override
	public String toString() {
		return "ReplenishmentRepotPojo [productId=" + productId + ", productName=" + productName + ", cf1=" + cf1
				+ ", soldquantity=" + soldquantity + ", availableqty=" + availableqty + "]";
	}

	

}
