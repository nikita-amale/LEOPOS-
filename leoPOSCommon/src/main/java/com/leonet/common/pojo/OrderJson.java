package com.leonet.common.pojo;

public class OrderJson {

	private long name;
	private double price;
	private int count;
	private String pname;
	private String discode;
	private double mrp;
	private String img;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	 
	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	 

	public String getDiscode() {
		return discode;
	}

	public void setDiscode(String discode) {
		this.discode = discode;
	}

	
	public long getName() {
		return name;
	}

	public void setName(long name) {
		this.name = name;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "OrderJson [name=" + name + ", price=" + price + ", count=" + count + ", pname=" + pname + ", discode="
				+ discode + ", mrp=" + mrp + ", img=" + img + "]";
	}

 
	 
	
}
