/**
 * 
 */
package com.leonet.common.pojo;

import java.util.Arrays;

/**
 * @author YOGESH
 *
 */
public class BarCodePojo {

	private long barCodeId;
	
	private long subCatCode;
	
	private long catCode;

	private String productCode;
	
	private byte[] barCodeImg;
	
	private int isActive;

	private String img;
	
	public long getBarCodeId() {
		return barCodeId;
	}

	public void setBarCodeId(long barCodeId) {
		this.barCodeId = barCodeId;
	}

	public long getSubCatCode() {
		return subCatCode;
	}

	public void setSubCatCode(long subCatCode) {
		this.subCatCode = subCatCode;
	}

	public long getCatCode() {
		return catCode;
	}

	public void setCatCode(long catCode) {
		this.catCode = catCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public byte[] getBarCodeImg() {
		return barCodeImg;
	}

	public void setBarCodeImg(byte[] barCodeImg) {
		this.barCodeImg = barCodeImg;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "BarCodePojo [barCodeId=" + barCodeId + ", subCatCode=" + subCatCode + ", catCode=" + catCode
				+ ", productCode=" + productCode + ", barCodeImg=" + Arrays.toString(barCodeImg) + ", isActive="
				+ isActive + ", img=" + img + "]";
	}
	
	
}
