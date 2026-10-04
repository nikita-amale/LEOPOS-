package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.UnitEntity;
 
public interface UnitRepo extends JpaRepository<UnitEntity, Integer>{

	UnitEntity findById(long unitId);
	UnitEntity findByUnitname(String unitname);
}
