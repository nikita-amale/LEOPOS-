package com.leonet.repo;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.PurchaseItemOnFileEntity;
public interface PurchaseItemOnFileRepo  extends JpaRepository<PurchaseItemOnFileEntity, Integer> {

}
