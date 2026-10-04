package com.leonet.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plan")
public class PlanEntity {

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



	private String planname;
	private String deposit;
	private float monthlyFee;
	private int gamestoysPts;
	private int booksPts;
	private int validity;
	private int bonusPts;
	

	public String getPlanName() {
		return planname;
	}



	public void setPlanName(String planName) {
		this.planname = planName;
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
		return "PlanEntity [planName=" + planname + ", deposit=" + deposit + ", monthlyFee=" + monthlyFee + ", gamestoysPts=" + gamestoysPts + ", booksPts="
				+ booksPts + ", validity=" + validity + ", bonusPts=" + bonusPts + "]";
	}
	 
    
    
}
