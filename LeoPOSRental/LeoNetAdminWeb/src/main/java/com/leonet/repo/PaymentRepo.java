package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.PaymentEntity;


public interface PaymentRepo extends JpaRepository<PaymentEntity, Integer> {
	List<PaymentEntity> findAllByMemberid(long memberId);
	List<PaymentEntity> findAllByrsaleIdOrderByIdDesc(long rsaleId);

}
