package com.leonet.common.pojo;

import java.util.Date;


public class RequestQuotePojo {

	
	private long rqId;
	private Date date;
	private String referenceno;
	private long member_id;
	private String member_name;
	private long user_id;
	private String email;
	private String suppliername;
	public long getRqId() {
		return rqId;
	}
	public void setRqId(long rqId) {
		this.rqId = rqId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReferenceno() {
		return referenceno;
	}
	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}
	public long getMember_id() {
		return member_id;
	}
	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	@Override
	public String toString() {
		return "RequestQuotePojo [rqId=" + rqId + ", date=" + date + ", referenceno=" + referenceno + ", member_id="
				+ member_id + ", member_name=" + member_name + ", user_id=" + user_id + ", email=" + email
				+ ", suppliername=" + suppliername + "]";
	}
	

	

}
