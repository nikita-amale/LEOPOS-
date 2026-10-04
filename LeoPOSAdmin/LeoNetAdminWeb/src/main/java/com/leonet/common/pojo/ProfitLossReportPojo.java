package com.leonet.common.pojo;

public class ProfitLossReportPojo {
    private Double income;
    private Double costOfGoodsSold;
    private Double grossProfit;

    public ProfitLossReportPojo() {
        super();
    }

    public ProfitLossReportPojo(Double income, Double costOfGoodsSold, Double grossProfit) {
        super();
        this.income = income;
        this.costOfGoodsSold = costOfGoodsSold;
        this.grossProfit = grossProfit;
    }

	

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Double getCostOfGoodsSold() {
		return costOfGoodsSold;
	}

	public void setCostOfGoodsSold(Double costOfGoodsSold) {
		this.costOfGoodsSold = costOfGoodsSold;
	}

	public Double getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Double grossProfit) {
		this.grossProfit = grossProfit;
	}

	@Override
	public String toString() {
		return "ProfitLossReportPojo [income=" + income + ", costOfGoodsSold=" + costOfGoodsSold + ", grossProfit="
				+ grossProfit + "]";
	}

	

}
