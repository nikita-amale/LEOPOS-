package com.leonet.common.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(
    name = "special_sale_register",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "register_date")
    }
)
public class SpecialSaleRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "cash_payment", precision = 15, scale = 2)
    private BigDecimal cashPayment = BigDecimal.ZERO;

    @Column(name = "opening_balance", precision = 15, scale = 2)
    private BigDecimal openingBalance = BigDecimal.ZERO;
    

    @Column(name = "sale_amount", precision = 15, scale = 2)
    private BigDecimal saleAmount = BigDecimal.ZERO;

    @Column(name = "closing_balance", precision = 15, scale = 2)
    private BigDecimal closingBalance = BigDecimal.ZERO;
    
    

	public BigDecimal getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}

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

	public BigDecimal getCashPayment() {
		return cashPayment;
	}

	public void setCashPayment(BigDecimal cashPayment) {
		this.cashPayment = cashPayment;
	}

	public BigDecimal getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(BigDecimal openingBalance) {
		this.openingBalance = openingBalance;
	}

	public BigDecimal getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(BigDecimal closingBalance) {
		this.closingBalance = closingBalance;
	}

	@Override
	public String toString() {
		return "SpecialSaleRegister [id=" + id + ", registerDate=" + registerDate + ", cashPayment=" + cashPayment
				+ ", openingBalance=" + openingBalance + ", saleAmount=" + saleAmount + ", closingBalance="
				+ closingBalance + "]";
	}
    
    

}
