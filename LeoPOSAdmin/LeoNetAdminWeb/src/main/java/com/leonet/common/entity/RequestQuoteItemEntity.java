/**

 * 
 */
package com.leonet.common.entity;

import java.util.Date;


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
@Table(name = "requestquote_items")
public class RequestQuoteItemEntity {

	
	
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "rqid")
	private long rqid;
	
	@Column(name = "product_id")
	private long productid;
	
	@Column(name = "product_code")
	private String product_code;
	
	@Column(name = "product_name")
	private String product_name;
	
	@Column(name = "product_type")
	private String product_type;
	
	@Column(name = "mpn")
	private String mpn;
	
	@Column(name = "quantity")
	private long quantity;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public long getRqid() {
		return rqid;
	}

	public void setRqid(long rqid) {
		this.rqid = rqid;
	}

	public long getProduct_id() {
		return productid;
	}

	public void setProduct_id(long productid) {
		this.productid = productid;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}

	public String getMpn() {
		return mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "RequestQuoteItemEntity [id=" + id + ", rqid=" + rqid + ", product_id=" + productid
				+ ", product_code=" + product_code + ", product_name=" + product_name + ", product_type=" + product_type
				+ ", mpn=" + mpn + ", quantity=" + quantity + "]";
	}
	
	
	
	
	
	

	 
}
