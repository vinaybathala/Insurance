package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.entity.UserRegistrationPolicy;

public interface UserInsuranceRepository extends JpaRepository<UserRegistrationPolicy, Integer> {

	public Page<UserRegistrationPolicy> findByuserRegistrationId(int userId, Pageable pageable);

}
