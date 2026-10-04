package com.leonet.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leonet.common.pojo.SysAuditReportDTO;
import com.leonet.constant.Action;
import com.leonet.entity.SysAudit;


@Repository
public interface SysAuditRepo extends JpaRepository<SysAudit ,Long>{
	
	
	@Query("SELECT new com.leonet.common.pojo.SysAuditReportDTO(a.id, a.createdDate, a.action, a.createdBy, a.desciption) "
		     + "FROM SysAudit a WHERE "
		     + "(:fromDate IS NULL OR a.createdDate >= :fromDate) AND "
		     + "(:toDate IS NULL OR a.createdDate <= :toDate) AND "
		     + "(:action IS NULL OR a.action = :action) AND "
		     + "(:createdBy IS NULL OR :createdBy = '' OR a.createdBy = :createdBy)")
       List<SysAuditReportDTO> findAuditWithFiltersJPQL(
           @Param("fromDate") Date fromDate,
           @Param("toDate") Date toDate,
           @Param("action") Action action,
           @Param("createdBy") String createdBy
       );  

	@Query("SELECT new com.leonet.common.pojo.SysAuditReportDTO(a.id, a.createdDate, a.action, a.createdBy, a.desciption) "
		     + "FROM SysAudit a WHERE "
		     + "(:fromDate IS NULL OR a.createdDate >= :fromDate) AND "
		     + "(:toDate IS NULL OR a.createdDate <= :toDate) AND "
		     + "(:action IS NULL OR a.action = :action) AND "
		     + "(:createdBy IS NULL OR :createdBy = '' OR a.createdBy = :createdBy)")
		Page<SysAuditReportDTO> findAuditWithPaginationJPQL(
		    @Param("fromDate") Date fromDate,
		    @Param("toDate") Date toDate,
		    @Param("action") Action action,
		    @Param("createdBy") String createdBy,
		    Pageable pageable
		);


       @Query("SELECT DISTINCT sa.action FROM SysAudit sa")
       List<Action> findDistinctActions();

}
