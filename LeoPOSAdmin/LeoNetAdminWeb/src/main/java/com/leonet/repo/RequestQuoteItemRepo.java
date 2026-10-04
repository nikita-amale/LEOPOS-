package com.leonet.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.entity.RequestQuoteItemEntity;

 
public interface RequestQuoteItemRepo extends JpaRepository<RequestQuoteItemEntity, Integer>{
	List<RequestQuoteItemEntity> findByid(Long memberId);
	RequestQuoteItemEntity findByProductid(long productId);
	List<RequestQuoteItemEntity> findByrqidOrderByIdAsc(long rqid);

	
}
