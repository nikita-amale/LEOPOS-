package com.leonet.common.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.OrderDetails;


@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer>{

	
	OrderDetails save(OrderDetails orderDetails);

	Page<OrderDetails> findAll(Pageable pageable);

	Page findByUserUuid(Pageable paging, String userUuid);

	List<OrderDetails> findByOrderId(long orderId);
	
	
	 
}
