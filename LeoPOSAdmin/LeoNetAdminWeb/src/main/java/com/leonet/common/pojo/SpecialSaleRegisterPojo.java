package com.leonet.common.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;


public class SpecialSaleRegisterPojo {

    private Long id;
    private LocalDate registerDate;

    private BigDecimal cashPayment;

    private BigDecimal openingBalance;

    private BigDecimal saleAmount;

    private BigDecimal closingBalance;
    
    

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
    
    
}
