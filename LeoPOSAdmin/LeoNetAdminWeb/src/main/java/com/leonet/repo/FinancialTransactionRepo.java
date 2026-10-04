package com.leonet.repo;



import java.util.Date;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.FTEntity;
import com.leonet.common.entity.FinancialTransactionEntity;


 

public interface FinancialTransactionRepo extends JpaRepository<FinancialTransactionEntity, Integer>{

	FinancialTransactionEntity findTopByCustomerIdOrderByFanIdDesc(long customerid);

	List<FinancialTransactionEntity> findByCustomerId(long memberId);
	List<FinancialTransactionEntity> findByCustomerIdOrderByFanIdDesc(long memberId);

	List<FinancialTransactionEntity> findByCustomerIdAndInvoideIdAndType(long memberId,long invoideId,String type);
	
	List<FinancialTransactionEntity> findByCustomerIdAndDateBetweenOrderByDate(long customerid,Date startDate, Date endDate);
	
	List<FinancialTransactionEntity> findByInvoideId(long invoideId);
	
	//	SalesEntity findByReference_no(String reference_no);
	//FinancialTransactionEntity findBy(String referenceno);

	
}
