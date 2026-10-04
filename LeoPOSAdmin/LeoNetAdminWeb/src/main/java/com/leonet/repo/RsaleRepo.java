package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.RsaleEntity;
 
public interface RsaleRepo extends JpaRepository<RsaleEntity, Integer>{

	//	SalesEntity findByReference_no(String reference_no);
	RsaleEntity findByRsaleId(Long rsaleId);
}
