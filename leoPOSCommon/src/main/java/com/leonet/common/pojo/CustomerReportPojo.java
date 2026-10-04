package com.leonet.common.pojo;

public class CustomerReportPojo {
	private Long id;
	private String email;
	private String name;
	private String ctype;
	private String phoneno;
	private Double totalSale;
	private Double totalAmount;
	private Double totalPaid;
	private Double balance;


	public CustomerReportPojo() {
		super();
	}
	

	public CustomerReportPojo(Long id,String name,String email,String phoneno,String ctype,Double totalSale, Double totalAmount, Double totalPaid,Double balance) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneno = phoneno;
		this.totalSale = totalSale;
		this.totalAmount = totalAmount;
		this.totalPaid = totalPaid;
		this.balance = balance;
		this.ctype=ctype;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public Double getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(Double totalSale) {
		this.totalSale = totalSale;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Double getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(Double totalPaid) {
		this.totalPaid = totalPaid;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public String getCtype() {
		return ctype;
	}


	public void setCtype(String ctype) {
		this.ctype = ctype;
	}


	@Override
	public String toString() {
		return "CustomerReportPojo [id=" + id + ", email=" + email + ", name=" + name + ", ctype=" + ctype
				+ ", phoneno=" + phoneno + ", totalSale=" + totalSale + ", totalAmount=" + totalAmount + ", totalPaid="
				+ totalPaid + ", balance=" + balance + "]";
	}


	
	

}
