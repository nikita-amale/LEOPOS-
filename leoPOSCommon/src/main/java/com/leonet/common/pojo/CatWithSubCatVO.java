package com.leonet.common.pojo;

import java.util.List;

import com.leonet.common.entity.ProductSubCategoryEntity;


public class CatWithSubCatVO {

	
	private long id;
	private long catCode;
	private String catName;
	private String catDesc;
	private String catLogo;
	private int isActive;
	private int seq;
	
	private List<ProductSubCategoryEntity> productSubCategoryEntityList;

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

	public List<ProductSubCategoryEntity> getProductSubCategoryEntityList() {
		return productSubCategoryEntityList;
	}

	public void setProductSubCategoryEntityList(List<ProductSubCategoryEntity> productSubCategoryEntityList) {
		this.productSubCategoryEntityList = productSubCategoryEntityList;
	}

	@Override
	public String toString() {
		return "CatWithSubCatVO [id=" + id + ", catCode=" + catCode + ", catName=" + catName + ", catDesc=" + catDesc
				+ ", catLogo=" + catLogo + ", isActive=" + isActive + ", seq=" + seq + ", productSubCategoryEntityList="
				+ productSubCategoryEntityList + "]";
	}
	
	
}
