package com.insurance.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserInsuranceRequestDto {

	@NotNull(message = "please enter a valid from account number")
	//@Min(value = 0, message = "Invalid account number")
	private Long fromAccount;

	@NotNull(message = "please enter a valid user Id ")
	//@Min(value = 0, message = "Invalid UserId")
	private int userId;

	List<InsuranceRequestDto> insuranceDetails = new ArrayList<InsuranceRequestDto>();

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<InsuranceRequestDto> getInsuranceDetails() {
		return insuranceDetails;
	}

	public void setInsuranceDetails(List<InsuranceRequestDto> insuranceDetails) {
		this.insuranceDetails = insuranceDetails;
	}

}
