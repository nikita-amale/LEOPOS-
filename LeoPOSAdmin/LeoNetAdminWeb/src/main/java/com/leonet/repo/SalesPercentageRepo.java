package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.SalesPercentageEntity;
 
public interface SalesPercentageRepo extends JpaRepository<SalesPercentageEntity, Integer>{
	SalesPercentageEntity findByCtype(String ctype );

	SalesPercentageEntity findByCtypeAndPricegroup(String cType, String priceGroup);

	
}
