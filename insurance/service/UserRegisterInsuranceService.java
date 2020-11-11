package com.insurance.service;

import java.util.List;

import com.insurance.dto.UserInsuranceResponceDto;

public interface UserRegisterInsuranceService {

	public List<UserInsuranceResponceDto> getUserListofInsurance(int pageNumber, int pageSize, int userId);

}
