package com.leonet.common.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.VendorEntity;

public interface VendorRepo extends JpaRepository<VendorEntity, Integer>{

	VendorEntity findById(long id);

	VendorEntity findByVendorName(String vendorName);

	VendorEntity findByVenderCode(long venderCode);

	List<VendorEntity> findByIsActiveOrderByIdDesc(int i);

	VendorEntity findByVenderCodeAndIsActive(long venderCode, int i);

}
