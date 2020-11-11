package com.insurance.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.dto.InsuranceResponseDto;
import com.insurance.dto.UserInsuranceRequestDto;
import com.insurance.dto.UserInsuranceResponceDto;
import com.insurance.service.InsuranceService;
import com.insurance.service.RegisterInsuranceService;
import com.insurance.service.UserRegisterInsuranceService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/insurance")
@Api(value = "Insurance service", description = "Rest API for user insurance service", tags = {"UserInsuranceService"})
public class InsuranceController {

	@Autowired
	InsuranceService insuranceService;

	@Autowired
	RegisterInsuranceService registerInsuranceService;

	@Autowired
	UserRegisterInsuranceService userRegisterInsuranceService;

	private final static Logger logger = LoggerFactory.getLogger(InsuranceController.class);

	@GetMapping("/insurancepolicies")
	public ResponseEntity<List<InsuranceResponseDto>> getAllInsurance(@RequestParam int pageNumber,
			@RequestParam int pageSize) {
		logger.info("Before calling list of policies ");
		List<InsuranceResponseDto> insuranceResponseDto = insuranceService.getListofInsurance(pageNumber, pageSize);
		return new ResponseEntity<List<InsuranceResponseDto>>(insuranceResponseDto, new HttpHeaders(), HttpStatus.OK);

	}

	@PostMapping("/registerinsurance")
	public ResponseEntity<String> saveInsurance(@Valid @RequestBody UserInsuranceRequestDto userInsuranceRequestDto) {
		logger.info("Before calling register insurance  ");
		registerInsuranceService.registerUserInsurance(userInsuranceRequestDto);
		return new ResponseEntity<String>("Registered successfull", HttpStatus.ACCEPTED);
	}

	@GetMapping("/userregisteredinsurance")
	public ResponseEntity<List<UserInsuranceResponceDto>> getUserRegisterInsurance(@RequestParam int pageNumber,
			@RequestParam int pageSize, @RequestParam int userId) {
		logger.info("Before calling user registered insurance ");
		List<UserInsuranceResponceDto> userInsuranceResponseDto = userRegisterInsuranceService
				.getUserListofInsurance(pageNumber, pageSize, userId);
		return new ResponseEntity<List<UserInsuranceResponceDto>>(userInsuranceResponseDto, new HttpHeaders(),
				HttpStatus.OK);
	}
}
