package com.leonet.repo;


import java.util.Date;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SpecialSalesEntity;

import com.leonet.common.pojo.CustomerPurchaseSpecialPojo;

import com.leonet.common.pojo.SaleReportSummaryPojo;
import com.leonet.common.pojo.SpecialSaleMonthReportPojo;

public interface SpecialSalesRepo extends JpaRepository<SpecialSalesEntity, Integer> {
	List<SpecialSalesEntity> findAllByOrderBySaleIdDesc();
	List<SpecialSalesEntity> findByDateBetweenOrderByDateDesc(Date startDate, Date endDate);
	
	@Query("SELECT s FROM SpecialSalesEntity s WHERE " +
		       "(s.date BETWEEN :startDate AND :endDate) AND " +
		       "(s.ctype LIKE %:ctype% OR s.referenceno LIKE %:referenceno% OR " +
		       "s.membername LIKE %:membername% OR " +
		       "CAST(s.grand_total AS string) LIKE %:grandtotal%) " +
		       "ORDER BY s.saleId DESC")
		List<SpecialSalesEntity> searchByDateAndFields(
		    @Param("startDate") Date startDate,
		    @Param("endDate") Date endDate,
		    @Param("ctype") String ctype,
		    @Param("referenceno") String referenceno,
		    @Param("membername") String membername,
		    @Param("grandtotal") String grandtotal);


	SpecialSalesEntity findBySaleId(Long saleId);
	List<SpecialSalesEntity> findAllByMemberid(long memberId);
	List<SpecialSalesEntity> findAllByMemberidOrderBySaleId(long memberId);
	List<SpecialSalesEntity> findAllByMemberidAndPaymentstatusOrderBySaleId(long memberId,String paymentStatus );
	List<SpecialSalesEntity> findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(long memberId, String Pstatus);
	List<SpecialSalesEntity> findAllByMemberidAndPaymentstatusAndIsActiveOrderBySaleIdAsc(long memberId, String Pstatus,int isactive);
	List<SpecialSalesEntity> findAllByAndDateBetweenOrderBySaleIdDesc(Date startDate, Date endDate);
	List<SpecialSalesEntity> findBySaleIdOrderBySaleIdAsc(long saleId);
	@Query(name = "find_total_special_sale_report",nativeQuery = true)
	List<SaleReportSummaryPojo> findAllTotalSpecialSale(@Param("startDate") String startDate, @Param("endDate") String endDate);
	List<SpecialSalesEntity> findBySaleIdIn(List<Long> bulkSaleList);
	@Query(name = "find_Special_Customer_Purchase_dto",nativeQuery = true)
	List<CustomerPurchaseSpecialPojo> findAllCustomerPurchase(@Param("startDate") String startDate, @Param("endDate") String endDate);
	@Query(name = "find_SpecialSale_Month_dto",nativeQuery = true)
	List<SpecialSaleMonthReportPojo> findSpecialSaleSummary(@Param("startDate") String startDate, @Param("endDate") String endDate);
	
	List<SpecialSalesEntity> findByCtypeContainingOrReferencenoContainingOrMembernameContainingOrderBySaleIdDesc(String ctype,String referenceno,String membername);
	
}
