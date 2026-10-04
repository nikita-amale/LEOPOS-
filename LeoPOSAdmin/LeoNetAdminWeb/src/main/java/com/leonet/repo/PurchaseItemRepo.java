package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.PurchaseItemEntity;

public interface PurchaseItemRepo extends JpaRepository<PurchaseItemEntity, Integer> {

	List<PurchaseItemEntity> findByPurchaseId(long parseLong);
	

}
