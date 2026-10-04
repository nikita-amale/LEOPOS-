package com.leonet.common.pojo;

import javax.persistence.Column;

public class RequestQuotesItemsPojo {
	
private long id;
	
	
	private long rqid;
	
	
	private long productid;
	

	private String productcode;
	
	
	private String productname;
	

	private String producttype;
	
	
	private String mpn;
	

	private long quantity;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getRqid() {
		return rqid;
	}


	public void setRqid(long rqid) {
		this.rqid = rqid;
	}


	public long getProductid() {
		return productid;
	}


	public void setProductid(long productid) {
		this.productid = productid;
	}


	public String getProductcode() {
		return productcode;
	}


	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getProducttype() {
		return producttype;
	}


	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}


	public String getMpn() {
		return mpn;
	}


	public void setMpn(String mpn) {
		this.mpn = mpn;
	}


	public long getQuantity() {
		return quantity;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "RequestQuotesItemsPojo [id=" + id + ", rqid=" + rqid + ", productid=" + productid + ", productcode="
				+ productcode + ", productname=" + productname + ", producttype=" + producttype + ", mpn=" + mpn
				+ ", quantity=" + quantity + "]";
	}
	
	

}
