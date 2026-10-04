package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.PlanEntity;
 
public interface PlanRepo extends JpaRepository<PlanEntity, Integer>{

		PlanEntity findByPlanname(String planname);

}
