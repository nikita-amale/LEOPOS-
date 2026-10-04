package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.common.entity.SalesItemEntity;
 
public interface SalesItemRepo extends JpaRepository<SalesItemEntity, Integer>{

	
}
