package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.MemberUser;
 
public interface MemberUserRepo extends JpaRepository<MemberUser, Integer>{

	MemberUser findByUsername(String username);
	MemberUser findById(Long id);
    List<MemberUser> findAll();
	
	
}
