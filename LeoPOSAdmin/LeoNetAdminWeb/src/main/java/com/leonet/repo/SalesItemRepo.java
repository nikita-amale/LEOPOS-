package com.leonet.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.pojo.CustomerPurchasePojo;

 
public interface SalesItemRepo extends JpaRepository<SalesItemEntity, Integer>{
	
	List<SalesItemEntity> findBySaleid(long saleId);
	List<SalesItemEntity> findBySaleidOrderByIdAsc(long saleId);
	void deleteAllBySaleid(long saleId);
	//SalesItemEntity findBySaleid(long saleId);
	SalesItemEntity findBySaleidAndProductid(Long saleId,Long productid);
	SalesItemEntity findTopBySaleidAndProductidAndRoll(Long saleId,Long productid,String roll);

	@Query("SELECT s FROM SalesItemEntity s WHERE s.saleid IN ?1")
	List<SalesItemEntity> findAllBySaleIdIn(List<Long> saleId);
	
	List<SalesItemEntity> findBySaleidIn(List<Long> saleIds);


}
