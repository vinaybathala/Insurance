package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.insurance.dto.InsuranceResponseDto;
import com.insurance.entity.Insurance;
import com.insurance.exception.InsuranceMaintenanceException;
import com.insurance.repository.InsuranceRepository;
import com.insurance.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	InsuranceRepository insuranceRepository;

	@Override
	public List<InsuranceResponseDto> getListofInsurance(int pageNumber, int pageSize) {
		Page<Insurance> insurance;
		List<InsuranceResponseDto> insuranceListDtos = new ArrayList<>();
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		insurance = insuranceRepository.findAll(pageable);

		// exception insurance maintainance exception
		if (insurance.isEmpty()) {
			throw new InsuranceMaintenanceException(" upgrading the insurancelist");
		}

//		for (Insurance policy : insurance) {
//			InsuranceResponseDto insuranceDto = new InsuranceResponseDto();
//			BeanUtils.copyProperties(policy, insuranceDto);
//			insuranceListDtos.add(insuranceDto);
//
//		}
		
		////
		
		insurance.stream().forEach(policy -> {
			InsuranceResponseDto insuranceDto = new InsuranceResponseDto();
			BeanUtils.copyProperties(policy, insuranceDto);
			insuranceListDtos.add(insuranceDto);
			
		});
		
		return insuranceListDtos;
	}

}
