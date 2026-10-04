package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.AgingDetailsEntity;
 

public interface AgingDetailsRepo extends JpaRepository<AgingDetailsEntity, Integer>{

	AgingDetailsEntity findByCustomerId(long customerid);

	//	SalesEntity findByReference_no(String reference_no);
	//FinancialTransactionEntity findBy(String referenceno);

	
}
