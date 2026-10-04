package com.leonet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POS_product")
public class PlanPurchaseEntity {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}



	private int member_id;
	private int plan_id;
	private int gamestoysPts;
	private int booksPts;
	private int validity;
	private Date purchasedate;
	
	


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
		return "PlanPurchaseEntity [member_id=" + member_id + ", plan_id=" + plan_id + ", gamestoysPts=" + gamestoysPts + ", booksPts="
				+ booksPts + ", validity=" + validity + ", purchasedate=" + purchasedate + "]";
	}

	 
    
    
}
