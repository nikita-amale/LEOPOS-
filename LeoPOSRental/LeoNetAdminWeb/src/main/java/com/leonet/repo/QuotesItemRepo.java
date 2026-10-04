package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.QuotesItemEntity;

public interface QuotesItemRepo extends JpaRepository<QuotesItemEntity, Integer> {

}
