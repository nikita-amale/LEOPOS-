package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.ProductRentalEntity;
 
public interface ProductRentalRepo extends JpaRepository<ProductRentalEntity, Integer>{

	//	SalesEntity findByReference_no(String reference_no);
	List<ProductRentalEntity> findAll();
	ProductRentalEntity findByRproductId(Long productId);
	ProductRentalEntity findByProductFileName(String fileName);
	
}
