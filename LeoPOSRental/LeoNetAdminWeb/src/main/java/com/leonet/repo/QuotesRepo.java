package com.leonet.repo;
import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.QuotesEntity;

public interface QuotesRepo extends JpaRepository<QuotesEntity, Integer> {

}
