package com.leonet.common.pojo;

public class SalesPercentagePojo {

	private long id;
	private String ctype;
	private long percentage;
	private String pricegroup;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public long getPercentage() {
		return percentage;
	}
	public void setPercentage(long percentage) {
		this.percentage = percentage;
	}
	
	public String getPricegroup() {
		return pricegroup;
	}
	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
	}
	@Override
	public String toString() {
		return "SalesPercentagePojo [id=" + id + ", ctype=" + ctype + ", percentage=" + percentage + ", pricegroup="
				+ pricegroup + "]";
	}
	
	
	
}
