package com.leonet.common.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_cat_dtl")
public class ProductCategotyEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private long catCode=Long.parseLong(new SimpleDateFormat("yyMMddhhmmSSS").format(new Date()));
	private String catName;
	private String catDesc;
	private String catLogo;
	private int isActive;
	private int seq;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCatCode() {
		return catCode;
	}
	public void setCatCode(long catCode) {
		this.catCode = catCode;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getCatDesc() {
		return catDesc;
	}
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	public String getCatLogo() {
		return catLogo;
	}
	public void setCatLogo(String catLogo) {
		this.catLogo = catLogo;
	}
	@Override
	public String toString() {
		return "ProductCategotyEntity [id=" + id + ", catCode=" + catCode + ", catName=" + catName + ", catDesc="
				+ catDesc + ", catLogo=" + catLogo + ", isActive=" + isActive + ", seq=" + seq + "]";
	}
	 
	
}
