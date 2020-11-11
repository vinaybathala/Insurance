package com.insurance.service;

import javax.validation.Valid;

import com.insurance.dto.BankRequestDto;
import com.insurance.dto.UserInsuranceRequestDto;

public interface RegisterInsuranceService   {

	

	public String registerUserInsurance(UserInsuranceRequestDto userInsuranceRequestDto);

	public String getInfo();

	/////testing
	
	public String registerUserInsurance(@Valid BankRequestDto bankRequestDto);

}


