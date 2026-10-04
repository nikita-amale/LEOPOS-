/**

 * 
 */
package com.leonet.common.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Moninder
 *
 */
@Entity
@Table(name = "requestquote")
public class RequestQuoteEntity {

	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rqId")
	private long rqId;

	@Column(name = "date")
	private Date date;

	private String referenceno;

	@Column(name = "user_id")
	private long user_id;
	
	@Column(name = "Suppliername")
	private String Suppliername;
	
	@Column(name = "email")
	private String email;

	@Column(name = "file_name")
	private String fileName;

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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

	public String getSuppliername() {
		return Suppliername;
	}

	public void setSuppliername(String suppliername) {
		Suppliername = suppliername;
	}

	@Override
	public String toString() {
		return "RequestQuoteEntity [rqId=" + rqId + ", date=" + date + ", referenceno=" + referenceno + ", user_id="
				+ user_id + ", Suppliername=" + Suppliername + ", email=" + email + ", fileName=" + fileName + "]";
	}

	
}
