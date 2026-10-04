package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.SalesEntity;
import com.leonet.entity.RsaleEntity;
 
public interface RsaleRepo extends JpaRepository<RsaleEntity, Integer>{

	//	SalesEntity findByReference_no(String reference_no);
	RsaleEntity findByRsaleId(Long rsaleId);

	List<RsaleEntity> findAllByOrderByRsaleIdDesc();
	
	List<RsaleEntity> findAllByMemberid(long memberId);
	
}
