/**
 * 
 */
package com.leonet.common.pojo;
 

/**
 * @author YOGESH
 *
 */
public class ProductSubCategoryPojo {

	
	private long id;
	private long catCode;
	private long subCatCode;
	private String subCatName;
	private String subCatDesc;
	private String subCatLogo;
	private String catName;
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
	
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	@Override
	public String toString() {
		return "ProductSubCategoryPojo [id=" + id + ", catCode=" + catCode + ", subCatCode=" + subCatCode
				+ ", subCatName=" + subCatName + ", subCatDesc=" + subCatDesc + ", subCatLogo=" + subCatLogo
				+ ", catName=" + catName + ", isActive=" + isActive + ", seq=" + seq + "]";
	}
	
	
}
