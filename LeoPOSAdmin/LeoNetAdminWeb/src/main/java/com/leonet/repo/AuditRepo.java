package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.AuditEntity;

public interface AuditRepo extends JpaRepository<AuditEntity, Integer> {

}
