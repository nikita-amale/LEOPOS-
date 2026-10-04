package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.RequestQuoteEntity;
 
public interface RequestQuoteRepo extends JpaRepository<RequestQuoteEntity, Integer>{

	
}
