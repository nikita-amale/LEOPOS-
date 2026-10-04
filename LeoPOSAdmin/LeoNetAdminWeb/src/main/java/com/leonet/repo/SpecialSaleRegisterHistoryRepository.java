package com.leonet.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.SpecialSaleRegisterHistory;

@Repository
public interface SpecialSaleRegisterHistoryRepository extends JpaRepository<SpecialSaleRegisterHistory, Long> {

	List<SpecialSaleRegisterHistory> findByRegisterDate(LocalDate registerDate);

	List<SpecialSaleRegisterHistory> findByRegisterDateBetween(LocalDate startDate, LocalDate endDate);
	
	List<SpecialSaleRegisterHistory> findByReferenceNo(String referenceNo);

}
