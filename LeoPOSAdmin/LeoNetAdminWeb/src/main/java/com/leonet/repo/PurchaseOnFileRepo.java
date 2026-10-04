package com.leonet.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.PurchaseOnFileEntity;
public interface PurchaseOnFileRepo extends JpaRepository<PurchaseOnFileEntity, Integer>  {

}
