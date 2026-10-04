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
@Table(name = "product_sub_cat_dtl")
public class ProductSubCategoryEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5717824560933627120L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private long catCode;
	private long subCatCode=Long.parseLong(new SimpleDateFormat("yyMMddhhmmSSS").format(new Date()));
	private String subCatName;
	private String subCatDesc;
	private String subCatLogo;
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
	public long getSubCatCode() {
		return subCatCode;
	}
	public void setSubCatCode(long subCatCode) {
		this.subCatCode = subCatCode;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getSubCatDesc() {
		return subCatDesc;
	}
	public void setSubCatDesc(String subCatDesc) {
		this.subCatDesc = subCatDesc;
	}
	public String getSubCatLogo() {
		return subCatLogo;
	}
	public void setSubCatLogo(String subCatLogo) {
		this.subCatLogo = subCatLogo;
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
	@Override
	public String toString() {
		return "ProductSubCategoryEntity [id=" + id + ", catCode=" + catCode + ", subCatCode=" + subCatCode
				+ ", subCatName=" + subCatName + ", subCatDesc=" + subCatDesc + ", subCatLogo=" + subCatLogo
				+ ", isActive=" + isActive + ", seq=" + seq + "]";
	}
	
	
}
