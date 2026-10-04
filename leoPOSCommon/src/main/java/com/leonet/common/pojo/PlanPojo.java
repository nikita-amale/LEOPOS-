package com.leonet.common.pojo;

public class PlanPojo {

	private long id;
	private String planName;
	private String deposit;
	private float monthlyFee;
	private int gamestoysPts;
	private int booksPts;
	private int validity;
	private int bonusPts;
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getPlanName() {
		return planName;
	}



	public void setPlanName(String planName) {
		this.planName = planName;
	}



	public String getDeposit() {
		return deposit;
	}



	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}



	public float getMonthlyFee() {
		return monthlyFee;
	}



	public void setMonthlyFee(float monthlyFee) {
		this.monthlyFee = monthlyFee;
	}



	public int getGamestoysPts() {
		return gamestoysPts;
	}



	public void setGamestoysPts(int gamestoysPts) {
		this.gamestoysPts = gamestoysPts;
	}



	public int getBooksPts() {
		return booksPts;
	}



	public void setBooksPts(int booksPts) {
		this.booksPts = booksPts;
	}



	public int getValidity() {
		return validity;
	}



	public void setValidity(int validity) {
		this.validity = validity;
	}



	public int getBonusPts() {
		return bonusPts;
	}



	public void setBonusPts(int bonusPts) {
		this.bonusPts = bonusPts;
	}



	@Override
	public String toString() {
		return "PlanPojo [planName=" + planName + ", deposit=" + deposit + ", monthlyFee=" + monthlyFee + ", gamestoysPts=" + gamestoysPts + ", booksPts="
				+ booksPts + ", validity=" + validity + ", bonusPts=" + bonusPts + "]";
	}



	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}



	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}


	 
	 
}
