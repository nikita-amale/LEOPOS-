package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.RsalesItemEntity;
 
public interface RsaleItemRepo extends JpaRepository<RsalesItemEntity, Integer>{
	List<RsalesItemEntity> findByRsaleid(long rsaleId);

	
}
