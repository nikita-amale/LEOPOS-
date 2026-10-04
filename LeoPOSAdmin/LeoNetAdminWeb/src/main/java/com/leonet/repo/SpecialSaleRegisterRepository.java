package com.leonet.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leonet.common.entity.SpecialSaleRegister;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SpecialSaleRegisterRepository extends JpaRepository<SpecialSaleRegister, Long> {

    Optional<SpecialSaleRegister> findByRegisterDate(LocalDate registerDate);
    

    Page<SpecialSaleRegister> findAllByOrderByIdDesc(Pageable pageable);


}
