package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.pojo.ImportPurchasePojo;
import com.leonet.entity.ImportPurchaseEntity;


public interface ImportPurchaseRepo extends JpaRepository<ImportPurchaseEntity, Long> {

	ImportPurchaseEntity findBypurchaseId(long PurchaseId);

	//ImportPurchaseEntity findById(Long id);
	
	List<ImportPurchaseEntity> findAllByApplied(int applied);

	void save(ImportPurchasePojo importpurchasePojo);

}
