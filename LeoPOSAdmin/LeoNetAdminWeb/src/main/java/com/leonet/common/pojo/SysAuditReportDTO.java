package com.leonet.common.pojo;


import java.util.Date;

import com.leonet.constant.Action;

public class SysAuditReportDTO {

	private Long id;
	private Date createdDate;
	private String action;
	private String userName;
	private String desciption;
	
	
	
	public SysAuditReportDTO(Long id, Date createdDate, Action action, String userName, String desciption) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.action = action.toString();
		this.userName = userName;
		this.desciption = desciption;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	@Override
	public String toString() {
		return "SysAuditReportDTO [id=" + id + ", createdDate=" + createdDate + ", action=" + action + ", userName="
				+ userName + ", desciption=" + desciption + "]";
	}
	
	
}
