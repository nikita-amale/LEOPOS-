package com.leonet.entity;

import java.util.Date;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "qcheck")
public class QCheckEntity {
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long Id;
	
	@Column(name = "date")
	private Date date;

	
	@Column(name = "userid")
	private long userid;
	
	@Column(name = "productcode")
	private long productcode;
	
	@Column(name = "quantitybefore")
	private long quantitybefore;
	
	@Column(name = "quantityafter")
	private long quantityafter;
	
	@Column(name = "module")
	private String module;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getProductcode() {
		return productcode;
	}

	public void setProductcode(long productcode) {
		this.productcode = productcode;
	}

	public long getQuantitybefore() {
		return quantitybefore;
	}

	public void setQuantitybefore(long quantitybefore) {
		this.quantitybefore = quantitybefore;
	}

	public long getQuantityafter() {
		return quantityafter;
	}

	public void setQuantityafter(long quantityafter) {
		this.quantityafter = quantityafter;
	}
	

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "QCheckEntity [Id=" + Id + ", date=" + date + ", userid=" + userid + ", productcode=" + productcode
				+ ", quantitybefore=" + quantitybefore + ", quantityafter=" + quantityafter + ", module=" + module
				+ "]";
	}

	
	

}
