package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.RquoteItemEntity;
 
public interface RquoteItemRepo extends JpaRepository<RquoteItemEntity, Integer>{

	List<RquoteItemEntity> findByRquoteid(long rquoteId);


	
}
