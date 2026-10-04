package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.PurchaseFileEntity;

public interface PurchaseFileRepo extends JpaRepository<PurchaseFileEntity, Integer> {
	
	List<PurchaseFileEntity>  findAllByPurchaseid(long purchaseid);

}
