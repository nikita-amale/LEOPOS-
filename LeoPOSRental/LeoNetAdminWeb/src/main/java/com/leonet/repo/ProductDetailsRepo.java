package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.ProductDetailsEntity;
 
public interface ProductDetailsRepo extends JpaRepository<ProductDetailsEntity, Integer>{

	//	SalesEntity findByReference_no(String reference_no);
	List<ProductDetailsEntity> findAll();
	ProductDetailsEntity findByProductId(Long productId);
}
