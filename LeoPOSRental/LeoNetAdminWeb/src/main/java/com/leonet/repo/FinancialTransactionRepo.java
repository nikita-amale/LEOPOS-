package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.FinancialTransactionEntity;


public interface FinancialTransactionRepo extends JpaRepository<FinancialTransactionEntity, Integer> {
	FinancialTransactionEntity findTopByCustomerIdOrderByFanIdDesc(long customerid);

}
