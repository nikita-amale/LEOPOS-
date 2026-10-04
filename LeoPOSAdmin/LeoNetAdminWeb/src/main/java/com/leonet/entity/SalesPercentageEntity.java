/**

 * 
 */
package com.leonet.entity;


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
@Table(name = "salespercentage")
public class SalesPercentageEntity {

	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "ctype")
	private String ctype;
	
	@Column(name = "pricegroup")
	private String pricegroup;
	
	@Column(name = "percentage")
	private long percentage;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public long getPercentage() {
		return percentage;
	}

	public void setPercentage(long percentage) {
		this.percentage = percentage;
	}
	
	

	public String getPricegroup() {
		return pricegroup;
	}

	public void setPricegroup(String pricegroup) {
		this.pricegroup = pricegroup;
	}

	@Override
	public String toString() {
		return "SalesPercentageEntity [id=" + id + ", ctype=" + ctype + ", pricegroup=" + pricegroup + ", percentage="
				+ percentage + "]";
	}

	
	 
}
