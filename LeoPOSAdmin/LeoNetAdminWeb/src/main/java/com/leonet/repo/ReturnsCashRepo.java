package com.leonet.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.ReturnCashEntity;





public interface ReturnsCashRepo extends JpaRepository<ReturnCashEntity, Integer>{
	List<ReturnCashEntity> findAllByOrderByReturnIdDesc();
	ReturnCashEntity findByReturnId( long parseLong);
	List<ReturnCashEntity> findAllByMemberid(long memberid);
	List<ReturnCashEntity> findAllByMemberidAndIsActive(long memberid, int isActive);
	
	Page<ReturnCashEntity> findAllByOrderByReturnIdDesc(Pageable paging);
	List<ReturnCashEntity> findAllByReferencenoContainingOrMembernameContainingOrderByReturnIdDesc(String referenceno,String membername);


}
