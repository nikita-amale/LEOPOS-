package com.leonet.repo;
import java.util.Date;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.leonet.entity.QuotesEntity;


public interface QuotesRepo extends JpaRepository<QuotesEntity, Integer> {
	QuotesEntity findByquotesId(long quoteId);
	//ListQuotesEntity> findByIsquotesIdByIdDesc(int i);
	List<QuotesEntity> findAllByMemberidOrderByQuotesIdDesc(long memberId);
	List<QuotesEntity> findAllByOrderByQuotesIdDesc();
	
	Page<QuotesEntity> findAllByOrderByQuotesIdDesc(Pageable paging);
	
	List<QuotesEntity> findByCtypeContainingOrReferencenoContainingOrMembernameContainingOrGrandtotalContainingOrderByQuotesIdDesc(String ctype,String referenceno,String membername,String grandtotal);
  
	 @Query("SELECT s FROM QuotesEntity s WHERE s.date BETWEEN :startDate AND :endDate ORDER BY s.date DESC")
	    Page<QuotesEntity> findQuotesByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	

}
