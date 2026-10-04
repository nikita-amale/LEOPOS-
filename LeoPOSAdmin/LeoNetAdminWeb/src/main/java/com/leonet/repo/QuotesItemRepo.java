package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.QuotesItemEntity;

public interface QuotesItemRepo extends JpaRepository<QuotesItemEntity, Integer> {
	
	List<QuotesItemEntity> findByQuotesid(long quoteId);
	void deleteAllByquotesid(long QuotesId);
	List<QuotesItemEntity> findByQuotesidOrderByIdAsc(long quoteId);

}
