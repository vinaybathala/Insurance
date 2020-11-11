package com.insurance.dto;

public class InsuranceRequestDto {

	private Integer insuranceId;

	private Boolean monthlyEmi;

	private Boolean yearlyEmi;

	public Integer getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(Integer insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Boolean getMonthlyEmi() {
		return monthlyEmi;
	}

	public void setMonthlyEmi(Boolean monthlyEmi) {
		this.monthlyEmi = monthlyEmi;
	}

	public Boolean getYearlyEmi() {
		return yearlyEmi;
	}

	public void setYearlyEmi(Boolean yearlyEmi) {
		this.yearlyEmi = yearlyEmi;
	}

}
