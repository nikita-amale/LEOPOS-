package com.leonet.common.pojo;

import java.util.Date;

public class PlanPurchasePojo {

	private long id;
	private int member_id;
	private int plan_id;
	private int gamestoysPts;
	private int booksPts;
	private int validity;
	private Date purchasedate;
	
	

	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
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




	public int getMember_id() {
		return member_id;
	}



	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}



	public int getPlan_id() {
		return plan_id;
	}



	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}



	public Date getPurchasedate() {
		return purchasedate;
	}



	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}



	@Override
	public String toString() {
		return "PlanPurchasePojo [member_id=" + member_id + ", plan_id=" + plan_id + ", gamestoysPts=" + gamestoysPts + ", booksPts="
				+ booksPts + ", validity=" + validity + ", purchasedate=" + purchasedate + "]";
	}


	 
	 
}
