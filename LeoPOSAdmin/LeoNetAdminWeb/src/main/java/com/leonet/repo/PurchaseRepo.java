package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.PurchaseEntity;
import com.leonet.entity.QuotesEntity;



public interface PurchaseRepo extends JpaRepository<PurchaseEntity, Integer> {
//	PurchaseEntity findByPurchaseId(Long purchaseId);
	List<PurchaseEntity> findAllByOrderByPurchaseIdDesc();
	List<PurchaseEntity>  findAll();
	
	List<PurchaseEntity> findByPurchaseId(Long purchaseId);
	

}
