package com.leonet.common.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.ProductCategotyEntity;

@Repository
public interface ProductCategotyRepo extends JpaRepository<ProductCategotyEntity, Integer>{

	
 	List<ProductCategotyEntity> findByIsActiveOrderByIdDesc(int isActive);
	ProductCategotyEntity findByCatCode(long catCode);
 	ProductCategotyEntity findByCatCodeAndIsActive(long catCode, int i);
}
