package com.leonet.common.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.common.entity.User;
 
public interface UserRepo extends JpaRepository<User, Integer>{

	
	User findByUsername(String username);
}
