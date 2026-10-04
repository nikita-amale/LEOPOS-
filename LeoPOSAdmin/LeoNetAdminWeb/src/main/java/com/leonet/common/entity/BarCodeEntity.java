/**
 * 
 */
package com.leonet.common.entity;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author YOGESH
 *
 */
@Entity
@Table(name = "bar_code")
public class BarCodeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_srno")
	private long barCodeSrno;
	
    private long barCodeId=Long.parseLong(new SimpleDateFormat("yyMMddhhmmSSS").format(new Date()))+new Random().nextInt(20);
	
	private long subCatCode;
	
	private long catCode;

	private String productCode;
	
	private byte[] barCodeImg;
	
	private int isActive;

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

	@Override
	public String toString() {
		return "BarCodePojo [barCodeId=" + barCodeId + ", subCatCode=" + subCatCode + ", catCode=" + catCode
				+ ", productCode=" + productCode + ", barCodeImg=" + Arrays.toString(barCodeImg) + ", isActive="
				+ isActive + "]";
	}
	
	
}
