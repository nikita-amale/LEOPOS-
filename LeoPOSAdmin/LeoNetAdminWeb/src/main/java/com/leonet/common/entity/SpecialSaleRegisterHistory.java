package com.leonet.common.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
@Table(name = "special_sale_register_history")
public class SpecialSaleRegisterHistory {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "referenceno")
    private String referenceNo;

    @Column(name = "cash_payment", precision = 15, scale = 2)
    private BigDecimal cashPayment = BigDecimal.ZERO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public BigDecimal getCashPayment() {
		return cashPayment;
	}

	public void setCashPayment(BigDecimal cashPayment) {
		this.cashPayment = cashPayment;
	}

    // Getters and Setters
    
    
    

}
