package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insurance.dto.UserInsuranceResponceDto;
import com.insurance.entity.UserRegistrationPolicy;
import com.insurance.exception.RegistrationIdNotFoundException;
import com.insurance.repository.UserInsuranceRepository;
import com.insurance.service.UserRegisterInsuranceService;

@Service
public class UserRegisterInsuranceServiceImpl implements UserRegisterInsuranceService {

	@Autowired
	UserInsuranceRepository userInsuranceRepository;

	@Override
	public List<UserInsuranceResponceDto> getUserListofInsurance(int pageNumber, int pageSize, int userId) {
		Page<UserRegistrationPolicy> userRegistration;
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		userRegistration = userInsuranceRepository.findByuserRegistrationId(userId, pageable);

		// exception for userRegistration
		if (userRegistration.isEmpty()) {
			throw new RegistrationIdNotFoundException(" No policies are registered with this ID");
		}
		List<UserInsuranceResponceDto> userInsuranceResponceDtos = new ArrayList<>();
		UserInsuranceResponceDto userInsuranceResponceDto = new UserInsuranceResponceDto();
		
//		for( UserRegistrationPolicy userRegistrations : userRegistration) {
//			userInsuranceResponceDto = new UserInsuranceResponceDto();
//			BeanUtils.copyProperties(userRegistrations, userInsuranceResponceDto);
//			userInsuranceResponceDtos.add(userInsuranceResponceDto);
//		}

		userRegistration.stream().forEach(userRegistrationPolicy -> {
			UserInsuranceResponceDto userInsuranceDto = new UserInsuranceResponceDto();
			BeanUtils.copyProperties(userRegistrationPolicy, userInsuranceDto);
			userInsuranceResponceDtos.add(userInsuranceDto);

		});

		return userInsuranceResponceDtos;
	}

}
