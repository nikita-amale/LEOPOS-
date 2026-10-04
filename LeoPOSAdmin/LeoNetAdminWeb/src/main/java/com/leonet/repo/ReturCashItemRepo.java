package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.ReturnCashItemsEntity;

public interface ReturCashItemRepo extends JpaRepository<ReturnCashItemsEntity, Integer>{
	List<ReturnCashItemsEntity> findByReturnid(long parseLong);

}
