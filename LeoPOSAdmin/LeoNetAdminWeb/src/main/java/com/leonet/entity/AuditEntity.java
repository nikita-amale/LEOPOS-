package com.leonet.entity;

import java.util.Date;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "audit")
public class AuditEntity {
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long Id;
	
	@Column(name = "date")
	private Date date;

	
	@Column(name = "userid")
	private long userid;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "desciption")
	private String desciption;
	
	@Column(name = "module")
	private String module;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AuditEntity [Id=" + Id + ", date=" + date + ", userid=" + userid + ", action=" + action
				+ ", desciption=" + desciption + ", module=" + module + "]";
	}
	
	

}
