/**
 * 
 */
package com.leonet.common.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.ProductDiscount;
import com.leonet.common.pojo.ProductDiscountPojo;

/**
 * @author YOGESH
 *
 */
@Repository
public interface ProductDiscountRepo extends JpaRepository<ProductDiscount, Long>{

	ProductDiscount findByDiscountCodeAndIsActive(long discountCode,int i);

	List<ProductDiscount> findByCatCodeAndSubCatCodeOrderByDiscountIdDesc(long catagoryId, long subCatagoryId);

	List<ProductDiscount> findByCatCodeAndSubCatCodeAndIsActiveOrderByDiscountIdDesc(long catagoryId, long subCatagoryId, int i);

	ProductDiscount findByItemCodeAndIsActive(String itemCode, int i);

	 
}
