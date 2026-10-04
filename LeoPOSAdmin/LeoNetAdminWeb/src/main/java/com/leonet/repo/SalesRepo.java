package com.leonet.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.pojo.CustomerPurchasePojo;
import com.leonet.common.pojo.CustomerReportPojo;
import com.leonet.common.pojo.PaymentReportPojo;
import com.leonet.common.pojo.ProfitLossReportPojo;
import com.leonet.common.pojo.ReplenishmentRepotPojo;
import com.leonet.common.pojo.SaleMonthReportPojo;
import com.leonet.common.pojo.SaleReportSummaryPojo;

public interface SalesRepo extends JpaRepository<SalesEntity, Integer>, JpaSpecificationExecutor<SalesEntity> {

	// SalesEntity findByReference_no(String reference_no);
	SalesEntity findByReferenceno(String referenceno);

	SalesEntity findBySaleId(Long saleId);
	//SalesEntity findByMemberid(Long memberId);

	List<SalesEntity> findAllByMemberid(long memberId);
	List<SalesEntity> findBySaleIdOrderBySaleIdAsc(long saleId);


	List<SalesEntity> findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(long memberId, String Pstatus);
	//List<SalesEntity> findAllByMemberidAndPaymentstatusAndOrderBySaleIdAsc(long memberId, String Pstatus);
	
	List<SalesEntity> findAllByMemberidAndPaymentstatusAndIsActiveOrderBySaleIdAsc(long memberId, String Pstatus,int isactive);

	List<SalesEntity> findAllByMemberidOrderBySaleId(long memberId);
	List<SalesEntity> findSaleIdByMemberidOrderBySaleId(long memberId);
	List<SalesEntity> findAllByMemberidAndPaymentstatusOrderBySaleId(long memberId,String paymentStatus );

	List<SalesEntity> findAllByOrderBySaleIdDesc();
	
	
	// @Query("SELECT s FROM SalesEntity s ORDER BY s.date DESC")
	 Optional<SalesEntity> findTopByOrderByDateDesc();
	
	List<SalesEntity> findAllByCtypeOrderBySaleIdDesc(String ctype );
	
	List<SalesEntity> findByCtypeContainingOrReferencenoContainingOrMembernameContainingOrGrandtotalContainingOrderBySaleIdDesc(String ctype,String referenceno,String membername,String grandtotal);
	//List<SalesEntity> findAllOrderBySaleIdDesc();

	@Query(name = "find_Sale_Summary_dto",nativeQuery = true)
	List<SaleReportSummaryPojo> findAllSaleSummary(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(name = "find_Replenishment",nativeQuery = true)
	List<ReplenishmentRepotPojo> findReplenishmentRepotPojo(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(name = "find_Sale_Month_dto",nativeQuery = true)
	List<SaleMonthReportPojo> findSaleSummary(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(name = "find_Payment_Report",nativeQuery = true)
	List<PaymentReportPojo> findPaymentReport();
	
	@Query(value =
	        "SELECT * FROM (" +
	        " SELECT p.id, p.bulkid, p.grand_total, p.ptype, p.memberid, p.member_name, p.paymentdate " +
	        " FROM payment p WHERE p.bulkid = 0 AND p.paymentdate BETWEEN :fromDate AND :toDate " +
	        " UNION ALL " +
	        " SELECT bp.paymentid, bp.bulk_id, bp.amount, bp.ptype, bp.member_id, bp.member_name, bp.date " +
	        " FROM bulkpayment bp WHERE bp.date BETWEEN :fromDate AND :toDate " +
	        ") t ORDER BY paymentdate DESC",
	        
	        countQuery =
	        "SELECT COUNT(*) FROM (" +
	        " SELECT p.id FROM payment p WHERE p.bulkid = 0 AND p.paymentdate BETWEEN :fromDate AND :toDate " +
	        " UNION ALL " +
	        " SELECT bp.paymentid FROM bulkpayment bp WHERE bp.date BETWEEN :fromDate AND :toDate " +
	        ") t",
	        
	        nativeQuery = true)
	Page<Object[]> findPaymentReportRaw(
	        @Param("fromDate") Date fromDate,
	        @Param("toDate") Date toDate,
	        Pageable pageable);
	
	@Query(value =
		    "SELECT * FROM (" +
		    " SELECT p.id, p.bulkid, p.grand_total, p.ptype, p.memberid, p.member_name, p.paymentdate " +
		    " FROM payment p WHERE p.bulkid = 0 AND p.paymentdate BETWEEN :fromDate AND :toDate " +
		    " UNION ALL " +
		    " SELECT bp.paymentid, bp.bulk_id, bp.amount, bp.ptype, bp.member_id, bp.member_name, bp.date " +
		    " FROM bulkpayment bp WHERE bp.date BETWEEN :fromDate AND :toDate " +
		    ") t ORDER BY paymentdate DESC",
		    nativeQuery = true)
		List<Object[]> findPaymentReportFull(
		        @Param("fromDate") Date fromDate,
		        @Param("toDate") Date toDate);
	
	@Query(name = "find_Customer_Purchase_dto",nativeQuery = true)
	List<CustomerPurchasePojo> findAllCustomerPurchase(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	List<SalesEntity> findBySaleIdIn(List<Long> bulkSaleList);
	
	@Query(name = "find_total_sale_report",nativeQuery = true)
	List<SaleReportSummaryPojo> findAllTotalSale(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	@Query(name = "find_total_sale_report_by_ctype",nativeQuery = true)
	List<SaleReportSummaryPojo> findAllTotalSaleByCType(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("cType") String cType);
	
	
	@Query(name = "find_customer_report",nativeQuery = true)
	List<CustomerReportPojo> findAllCustomerReport();
	
	@Query(name = "find_profitloss_report",nativeQuery = true)
	List<ProfitLossReportPojo> findAllProfitLossReport(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	//Page<SalesEntity> findAll(Pageable paging);
	Page<SalesEntity> findAllByOrderBySaleIdDesc(Pageable paging);
	
	  @Query(nativeQuery = true, name = "find_sales_by_current_and_previous_month")
	    Page<SalesEntity> findSalesByCurrentAndPreviousMonth(Pageable pageable);
	  
	  
	  @Query("SELECT s FROM SalesEntity s WHERE s.date BETWEEN :startDate AND :endDate ORDER BY s.date DESC")
	    Page<SalesEntity> findSalesByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);
	  
	  @Query("SELECT s FROM SalesEntity s WHERE " +
		       "(s.ctype LIKE %:ctype% OR s.referenceno LIKE %:referenceno% OR s.membername LIKE %:membername% OR s.grandtotal LIKE %:grandtotal%) " +
		       "AND s.date BETWEEN :startDate AND :endDate " +
		       "ORDER BY s.saleId DESC")
		List<SalesEntity> findSalesByMultipleFieldsAndDateRange(
		        @Param("ctype") String ctype,
		        @Param("referenceno") String referenceno,
		        @Param("membername") String membername,
		        @Param("grandtotal") String grandtotal,
		        @Param("startDate") Date startDate,
		        @Param("endDate") Date endDate);

	  Page<SalesEntity> findByDateBetween(Date start, Date end, Pageable pageable);

	  Long countByDateBetween(Date start, Date end);
	  
}
