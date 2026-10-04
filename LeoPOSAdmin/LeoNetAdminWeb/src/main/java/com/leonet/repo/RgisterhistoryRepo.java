package com.leonet.repo;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.RegisterhistoryEntity;
public interface RgisterhistoryRepo  extends JpaRepository<RegisterhistoryEntity, Integer>{
	//RegisterhistoryEntity findByreferenceno(String referenceno);
	List<RegisterhistoryEntity> findByreferenceno(String referenceno);
	List<RegisterhistoryEntity>  findByDate(Date currentDatee);
	List<RegisterhistoryEntity> findById(long  id);
	List<RegisterhistoryEntity>  findByDate(String currentDatee);
	
	List<RegisterhistoryEntity> findAllByReferencenoAndStatus(String referenceno, String status);
}
