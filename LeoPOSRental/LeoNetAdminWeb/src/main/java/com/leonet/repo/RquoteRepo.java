package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.RquoteEntity;
 
public interface RquoteRepo extends JpaRepository<RquoteEntity, Integer>{

	RquoteEntity findByRquoteId(Long rquoteId);

	List<RquoteEntity> findAllByOrderByRquoteIdDesc();
}
