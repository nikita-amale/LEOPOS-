package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.ReturnItemEntity;
 

public interface ReturnItemRepo extends JpaRepository<ReturnItemEntity, Integer>{

	List<ReturnItemEntity> findByReturnid(long parseLong);
	List<ReturnItemEntity> findByReturnidOrderByIdAsc(long parseLong);

		
}
