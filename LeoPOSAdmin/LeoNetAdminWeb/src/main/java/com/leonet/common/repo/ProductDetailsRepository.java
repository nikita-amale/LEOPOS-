/**
 * 
 */
package com.leonet.common.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.leonet.common.entity.ProductDetailsEntity;

/**
 * @author YOGESH
 *
 */
@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetailsEntity, Long>{

 //	List<ProductDetailsEntity> findFirst10ByIsActiveOrderByProductIdDesc(int isActive);
	//List<ProductDetailsEntity> findBySubCatCodeAndIsActiveOrderByProductIdDesc(long subCatCode, int i);
 	//List<ProductDetailsEntity> findByCatCodeAndSubCatCodeAndIsActiveOrderByProductIdDesc(long catCode, long subCatCode, int i);
//	ProductDetailsEntity findByItemCodeAndIsActive(String productCode, int i);
 	//ProductDetailsEntity findByCatCodeAndSubCatCode(long catCode, long subCatCode);
//	ProductDetailsEntity findByProductNameAndIsActive(String productName, int i);
	ProductDetailsEntity findByProductId(long productId);

	Page<ProductDetailsEntity> findAllByOrderByProductIdAsc(Pageable paging);
	List<ProductDetailsEntity> findByCodeContainingOrNameContainingOrCf1ContainingOrderByProductIdAsc(String code,String name,String cf1);
	ProductDetailsEntity findByProductFileName(String fileName);
	
	
	
	
	//Page<ProductDetailsEntity> findAllByOrderBySaleIdDesc(Pageable paging);
} 
