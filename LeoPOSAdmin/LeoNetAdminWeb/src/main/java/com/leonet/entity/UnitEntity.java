/**

 * 
 */
package com.leonet.entity;


import java.math.BigDecimal;

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
@Table(name = "unit")
public class UnitEntity {

	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 7018838303060755967L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "unitname")
	private String unitname;
	
	@Column(name = "quantity")
	private BigDecimal quantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "UnitEntity [id=" + id + ", unitname=" + unitname + ", quantity=" + quantity + "]";
	}
		
	 
}
