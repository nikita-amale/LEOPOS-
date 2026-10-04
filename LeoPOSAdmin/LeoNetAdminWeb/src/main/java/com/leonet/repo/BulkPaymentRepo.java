package com.leonet.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.BulkPaymentEntity;

public interface BulkPaymentRepo extends JpaRepository<BulkPaymentEntity, Integer> {

	List<BulkPaymentEntity> findAllByMemberId(long memberId);
	List<BulkPaymentEntity> findAllByMemberIdOrderByBulkIdDesc(long memberId);
	BulkPaymentEntity findByBulkId(long bulkid);
	
}
