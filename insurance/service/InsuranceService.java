package com.insurance.service;

import java.util.List;

import com.insurance.dto.InsuranceResponseDto;

public interface InsuranceService {

	public List <InsuranceResponseDto> getListofInsurance( int pageNumber, int pageSize );
}
