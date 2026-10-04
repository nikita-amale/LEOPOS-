package com.leonet.common.pojo;

public class SaleReportSummaryPojo {

	private Long productId;
	private String productName;
	private Long quantity;
	private Double total;
	private Double totalTax;
	private Double grandTotal;
	private String membername;
	private String saleId;
	private String referenceno;

	public SaleReportSummaryPojo() {
		super();
	}

	public SaleReportSummaryPojo(Long productId,String productName,  Long quantity,String membername, String saleId, String referenceno) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
		this.membername=membername;
		this.saleId=saleId;
		this.referenceno=referenceno;
	}

	public SaleReportSummaryPojo(Double total, Double totalTax, Double grandTotal) {
		super();
		this.total = total;
		this.totalTax = totalTax;
		this.grandTotal = grandTotal;
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

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
		this.totalTax = totalTax;
	}

	public Double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(Double grandTotal) {
		this.grandTotal = grandTotal;
	}
	

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	
	

	public String getReferenceno() {
		return referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}

	@Override
	public String toString() {
		return "SaleReportSummaryPojo [productId=" + productId + ", productName=" + productName + ", quantity="
				+ quantity + ", total=" + total + ", totalTax=" + totalTax + ", grandTotal=" + grandTotal
				+ ", membername=" + membername + ", saleId=" + saleId + ", referenceno=" + referenceno + "]";
	}

	

	



	
}
