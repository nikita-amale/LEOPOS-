/**
 * 
 */
package com.leonet.common.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.BarCodeEntity;

/**
 * @author YOGESH
 *
 */
@Repository
public interface BarCodeRepo extends JpaRepository<BarCodeEntity, Long>{
 
	List<BarCodeEntity> findBySubCatCodeAndIsActiveOrderByBarCodeSrnoDesc(long subCatagoryId, int i);

	BarCodeEntity findByBarCodeIdAndIsActive(long barCodeId, int i);

	BarCodeEntity findBybarCodeIdAndIsActive(long barCode, int i);

	
	
	
}
