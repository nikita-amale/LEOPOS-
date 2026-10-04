package com.leonet.common.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.ProductSubCategoryEntity;

@Repository
public interface ProductSubCategoryRepo extends JpaRepository<ProductSubCategoryEntity, Integer>{
 	
	List<ProductSubCategoryEntity> findByCatCodeAndIsActiveOrderByIdDesc(long catCode, int isActive);

	ProductSubCategoryEntity findBySubCatCode(long subCatCode);

	List<ProductSubCategoryEntity> findByCatCodeOrderByIdDesc(long catagoryId);
 
	ProductSubCategoryEntity findBysubCatCodeAndIsActive(long catSubCode, int i);

	ProductSubCategoryEntity findBySubCatCodeAndIsActive(long subCatCode, int i);

	
}
