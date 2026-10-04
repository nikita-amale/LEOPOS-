package com.leonet.repo;

import java.util.Date;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.FTEntity;





public interface FTRepo extends JpaRepository<FTEntity, Integer> {
	FTEntity findTopByCustomerIdOrderByFanIdDesc(long customerid);
	List<FTEntity> findByCustomerIdAndInvoideIdAndType(long memberId,long invoideId,String type);
	List<FTEntity> findByCustomerIdAndDateBetweenOrderByDate(long customerid,Date startDate, Date endDate);
	FTEntity deleteAllByCustomerIdAndInvoideId(long memberId,long invoideId);
	List<FTEntity> findByInvoideId(long invoideId);
	FTEntity findTopByCustomerIdAndReferencenoOrderByFanIdDesc(long customerid,String referenceno);

}
