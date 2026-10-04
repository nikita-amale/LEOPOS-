package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.common.entity.SalesEntity;
 

public interface SalesRepo extends JpaRepository<SalesEntity, Integer>{

	//	SalesEntity findByReference_no(String reference_no);
	SalesEntity findByReferenceno(String referenceno);
	SalesEntity findBySaleId(Long saleId);
}
