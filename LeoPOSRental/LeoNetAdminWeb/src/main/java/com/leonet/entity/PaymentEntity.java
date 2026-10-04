package com.leonet.entity;

import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "payment")
public class PaymentEntity {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "rsaleId")
	private long rsaleId;
	
    @Column(name = "paymentdate")
	private Date date;
	
    @Column(name = "memberid")
	private long memberid;
	
    @Column(name = "member_name")
	private String member_name;
	
    @Column(name = "Ptype")
	private String Ptype;
    
    @Column(name = "Pref")
   	private String Pref;
    
    @Column(name = "Grand_total")
   	private Float Grand_total;
	
    @Column(name = "status")
	private String status;
	
	public long getRsaleId() {
		return rsaleId;
	}

	public void setRsaleId(long rsaleId) {
		this.rsaleId = rsaleId;
	}
	
	

	public long getMemberid() {
		return memberid;
	}

	public void setMemberid(long memberid) {
		this.memberid = memberid;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPtype() {
		return Ptype;
	}

	public void setPtype(String ptype) {
		Ptype = ptype;
	}

	public String getPref() {
		return Pref;
	}

	public void setPref(String pref) {
		Pref = pref;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getstatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}
	

	public Float getGrand_total() {
		return Grand_total;
	}

	public void setGrand_total(Float grand_total) {
		Grand_total = grand_total;
	}

	@Override
	public String toString() {
		return "PaymentEntity [id=" + id + ", rsaleId=" + rsaleId + ", date=" + date + ", memberid=" + memberid
				+ ", member_name=" + member_name + ", Ptype=" + Ptype + ", Pref=" + Pref + ", Grand_total="
				+ Grand_total + ", status=" + status + "]";
	}

	
	
	




}
