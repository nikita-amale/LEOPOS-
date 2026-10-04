package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leonet.entity.QCheckEntity;

public interface QCheckRepo extends JpaRepository<QCheckEntity, Integer> {

}
