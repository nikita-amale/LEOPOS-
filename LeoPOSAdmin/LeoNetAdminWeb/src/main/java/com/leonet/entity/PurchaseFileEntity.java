package com.leonet.entity;


import javax.persistence.Column;


import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;





@Entity
@Table(name = "PurchaseFileEntity")
public class PurchaseFileEntity {
	
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "purchaseid")
	private Long purchaseid;
	
	@Column(name = "title")
	private String title;
	 
	@Column(name = "file")
	private byte[] File;
	
	

	
	public PurchaseFileEntity(Long id, Long purchaseid, String title, byte[] file) {
		super();
		this.id = id;
		this.purchaseid = purchaseid;
		this.title = title;
		File = file;
	}

	public PurchaseFileEntity() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	
	public byte[] getFile() {
		return File;
	}

	public void setFile(byte[] file) {
		File = file;
	}

	public Long getPurchaseid() {
		return purchaseid;
	}

	public void setPurchaseid(Long purchaseid) {
		this.purchaseid = purchaseid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PurchaseFileEntity [id=" + id + ", purchaseid=" + purchaseid + ", title=" + title + "]";
	}

	
}

		
	
