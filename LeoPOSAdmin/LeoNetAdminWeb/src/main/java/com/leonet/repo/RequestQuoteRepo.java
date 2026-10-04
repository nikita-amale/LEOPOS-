package com.leonet.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.RequestQuoteEntity;
import com.leonet.entity.QuotesEntity;

 
public interface RequestQuoteRepo extends JpaRepository<RequestQuoteEntity, Long>{
	List<RequestQuoteEntity> 	findAllByOrderByRqIdDesc();
	RequestQuoteEntity findByRqId(long rqId);
	

	
}
