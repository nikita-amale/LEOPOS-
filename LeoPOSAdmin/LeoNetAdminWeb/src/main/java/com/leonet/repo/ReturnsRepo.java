package com.leonet.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.ReturnsEntity;
 

public interface ReturnsRepo extends JpaRepository<ReturnsEntity, Integer>{
	List<ReturnsEntity> findAllByOrderByReturnIdDesc();
	ReturnsEntity findByReturnId( long parseLong);
	ReturnsEntity findBysaleid(double saleid);
	ReturnsEntity deleteAllBysaleid(double saleid);
	
	Page<ReturnsEntity> findAllByOrderByReturnIdDesc(Pageable paging);
	List<ReturnsEntity> findAllByReferencenoContainingOrMembernameContainingOrderByReturnIdDesc(String referenceno,String membername);
	List<ReturnsEntity> findAllBymemberidOrderByReturnIdDesc(long memberId);


		
}
