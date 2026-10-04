package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.RquoteEntity;
 
public interface RquoteRepo extends JpaRepository<RquoteEntity, Integer>{

	//	SalesEntity findByReference_no(String reference_no);
	RquoteEntity findByRquoteId(Long rquoteId);
}
