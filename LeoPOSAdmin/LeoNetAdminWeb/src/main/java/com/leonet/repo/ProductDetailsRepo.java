package com.leonet.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonet.common.entity.ProductDetailsEntity;
 
public interface ProductDetailsRepo extends JpaRepository<ProductDetailsEntity, Long>{

	//	SalesEntity findByReference_no(String reference_no);
	List<ProductDetailsEntity> findAll();
	ProductDetailsEntity findByProductId(Long productId);
	
	  @Query("SELECT COALESCE(SUM(p.quantity), 0) FROM ProductDetailsEntity p")
	  BigDecimal getTotalQuantity();
}
