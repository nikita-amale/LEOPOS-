package com.leonet.common.pojo;

public class CustomerPurchasePojo {
	private String saleId;
	private String productName;
	private Long quantity;
	private String member_name;
	private Long memberid;
	private Double grandTotal;
	 
	public CustomerPurchasePojo() {
		super();
	}
	
	public CustomerPurchasePojo(String saleId, String member_name,Double grandtotal) {
		super();
		this.saleId = saleId;
	
	
		this.member_name = member_name;
		this.grandTotal=grandtotal;
		
		
	}
	

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	

	

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}
	

	public Long getMemberid() {
		return memberid;
	}

	public void setMemberid(Long memberid) {
		this.memberid = memberid;
	}

	@Override
	public String toString() {
		return "CustomerPurchasePojo [saleId=" + saleId + ", productName=" + productName + ", quantity=" + quantity
				+ ", member_name=" + member_name + ", memberid=" + memberid + ", grandTotal=" + grandTotal + "]";
	}

	


	
	
	

}
