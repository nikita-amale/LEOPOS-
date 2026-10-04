package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.entity.SpecialSalesItemEntity;

public interface SpecialSalesItemRepo extends JpaRepository<SpecialSalesItemEntity, Integer> {
	List<SpecialSalesItemEntity> findBySaleid(long saleId);
	List<SpecialSalesItemEntity> findBySaleidOrderByIdAsc(long saleId);
	SpecialSalesItemEntity findBySaleidAndProductid(Long saleId,Long productid);
	SpecialSalesItemEntity findTopBySaleidAndProductidAndRoll(Long saleId,Long productid,String roll);

}
