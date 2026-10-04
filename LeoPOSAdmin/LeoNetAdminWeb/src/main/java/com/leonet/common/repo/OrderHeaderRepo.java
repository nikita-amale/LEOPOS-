package com.leonet.common.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.OrderHeader;


@Repository
public interface OrderHeaderRepo extends JpaRepository<OrderHeader, Integer>{

	
 
	OrderHeader findByOrderId(long orderId);

	Page findByUserUuid(Pageable paging, String userUuid);
}
