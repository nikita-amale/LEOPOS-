package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.PaymentEntity;
 

public interface PaymentRepo extends JpaRepository<PaymentEntity, Integer>{
	List<PaymentEntity> findAllBymemberid(long memberId);
	List<PaymentEntity> findAllBymemberidOrderByIdAsc(long memberId);
	List<PaymentEntity> findAllBymemberidOrderByIdDesc(long memberId);
	List<PaymentEntity> findAllByrsaleIdAndCtypeOrderByIdDesc(long rsaleId,String ctype);
	List<PaymentEntity> findAllByrsaleIdAndStatus(long rsaleId,String status);
	PaymentEntity findByrsaleIdAndStatus(long rsaleId,String status);
	PaymentEntity findByPref(String pref);
	PaymentEntity findById(long id);
	List<PaymentEntity> findAllBybulkid(long bulkId);
	List<PaymentEntity> findAllBymemberidAndCtype(long memberId,String ctype);
	
	List<PaymentEntity> findAllByOrderByIdDesc();

}
