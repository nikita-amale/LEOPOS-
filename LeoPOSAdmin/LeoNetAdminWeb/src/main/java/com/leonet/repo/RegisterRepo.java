package com.leonet.repo;

import java.time.LocalDate;
import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.QuotesEntity;
import com.leonet.entity.RegisterEntity;
 

public interface RegisterRepo extends JpaRepository<RegisterEntity, Integer>{

	//	SalesEntity findByReference_no(String reference_no);
	RegisterEntity findAByDate(Date today);
	RegisterEntity findByreferenceno(String referenceno);
	RegisterEntity findByid(long rid);
	List<RegisterEntity> findAllByOrderByIdDesc();
	RegisterEntity findAByDate(LocalDate currentDatee);
	
	
}
