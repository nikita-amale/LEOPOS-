package com.leonet.common.pojo;

public class UnitPojo {

	private long id;
	private String unitname;
	private long quantity;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "UnitPojo [id=" + id + ", unitname=" + unitname + ", quantity=" + quantity + "]";
	}
	
}
