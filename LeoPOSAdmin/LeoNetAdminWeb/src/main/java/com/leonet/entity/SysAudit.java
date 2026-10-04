package com.leonet.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.leonet.common.entity.Auditable;
import com.leonet.constant.Action;



@Entity
@Table(name = "sys_audit")
public class SysAudit extends Auditable<String>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
    private Action action;
	
	
	
	@Column(name = "desciption")
	private String desciption;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Action getAction() {
		return action;
	}



	public void setAction(Action action) {
		this.action = action;
	}



	public String getDesciption() {
		return desciption;
	}



	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}



	@Override
	public String toString() {
		return "SysAudit [id=" + id + ", action=" + action + ", desciption=" + desciption + "]";
	}


}
