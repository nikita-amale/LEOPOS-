package com.leonet.common.pojo;

import java.util.Arrays;


public class PurchaseFilePojo {
	private Long id;
	private String title;

	private byte[] File;


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



	@Override
	public String toString() {
		return "PurchaseFilePojo [id=" + id + ", title=" + title + ",  File="
				+ File + "]";
	}

	


}
