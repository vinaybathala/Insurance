package com.insurance.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.BankRequestDto;
import com.insurance.service.RegisterInsuranceService;

@RestController
@RequestMapping("/testing")
public class InputValidations {

	@Autowired
	RegisterInsuranceService registerInsuranceService;

	@PostMapping("/validation")
	public String doinginputvalidations(@Valid @RequestBody BankRequestDto bankRequestDto) {
		registerInsuranceService.registerUserInsurance(bankRequestDto);
		return "success";

	}

}
