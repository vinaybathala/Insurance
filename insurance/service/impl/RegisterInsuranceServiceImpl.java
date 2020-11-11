package com.insurance.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurance.dto.BankRequestDto;
import com.insurance.dto.InsuranceRequestDto;
import com.insurance.dto.UserInsuranceRequestDto;
import com.insurance.dto.UserTransactionRequestDto;
import com.insurance.entity.Insurance;
import com.insurance.entity.User;
import com.insurance.entity.UserRegistrationPolicy;
import com.insurance.exception.InsuranceNotFoundException;
import com.insurance.exception.UserNotFoundException;
import com.insurance.feignclients.DBSBankClient;
import com.insurance.repository.InsuranceRepository;
import com.insurance.repository.UserInsuranceRepository;
import com.insurance.repository.UserRepository;
import com.insurance.service.RegisterInsuranceService;

@Service
public class RegisterInsuranceServiceImpl implements RegisterInsuranceService {

	@Autowired
	InsuranceRepository insuranceRepository;

	@Autowired
	UserInsuranceRepository userInsuranceRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	DBSBankClient dBSBankClient;

	private boolean checkValidUser(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public String registerUserInsurance(UserInsuranceRequestDto userInsuranceRequestDto) {

		Long fromAccount = userInsuranceRequestDto.getFromAccount();

		int userId = userInsuranceRequestDto.getUserId();

		List<InsuranceRequestDto> userList = new ArrayList();

		for (InsuranceRequestDto policy : userInsuranceRequestDto.getInsuranceDetails()) {

			userList.add(policy);

		}

		boolean validuser = this.checkValidUser(userId);

		long totalFundTransfer = 0;

		// exception for user
		if (!validuser) {
			throw new UserNotFoundException("Not a valid user");

		}

		for (InsuranceRequestDto policy : userList) {
			Optional<Insurance> insurance;
			int insuranceId = policy.getInsuranceId();
			insurance = insuranceRepository.findById(insuranceId);

			// exception for insurance
			if (!insurance.isPresent()) {
				throw new InsuranceNotFoundException("No Insurance found with this ID");

			}

			Insurance insurances = insurance.get();

			Insurance insurancedto = insurance.get();
			boolean monthlypay = policy.getMonthlyEmi();
			boolean yearpay = policy.getYearlyEmi();
			long toAccount = insurancedto.getToAccount();
			long transferfund;

			if (yearpay) {
				transferfund = insurancedto.getInsuranceEmiYearly();
				totalFundTransfer = (totalFundTransfer + transferfund);
				UserTransactionRequestDto userTransactionRequestDto = new UserTransactionRequestDto();
				userTransactionRequestDto.setToAccount(insurancedto.getToAccount());
				userTransactionRequestDto.setFromAccounNumber(fromAccount);
				userTransactionRequestDto.setAmount(totalFundTransfer);
				userTransactionRequestDto.setRemarks("yearlyemi");
				dBSBankClient.fundTransfer(userTransactionRequestDto);

			}

			else {
				transferfund = insurancedto.getInsuranceEmiMonthly();
				totalFundTransfer = (totalFundTransfer + transferfund);
				UserTransactionRequestDto userTransactionRequestDto = new UserTransactionRequestDto();
				userTransactionRequestDto.setToAccount(insurancedto.getToAccount());
				userTransactionRequestDto.setFromAccounNumber(fromAccount);
				userTransactionRequestDto.setAmount(totalFundTransfer);
				userTransactionRequestDto.setRemarks("monthlyemi");
				dBSBankClient.fundTransfer(userTransactionRequestDto);

			}

			UserRegistrationPolicy userRegistration = new UserRegistrationPolicy();
			userRegistration.setSelectedPolicyId(insurancedto.getInsuranceId());
			userRegistration.setSelectedPolicyName(insurancedto.getInsuranceName());
			userRegistration.setSelectedPolicySumAssured(insurancedto.getInsuranceSumAssured());
			userRegistration.setStatus("success");
			userRegistration.setUserRegistrationId(userId);

			userInsuranceRepository.save(userRegistration);

		}

		return ("your policy is registered succesully");
	}

	@Override
	public String getInfo() {
		return "success";
	}

	@Override
	public String registerUserInsurance(@Valid BankRequestDto bankRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
