package com.leonet.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonet.entity.AdminUser;
 
public interface AdminUserRepo extends JpaRepository<AdminUser, Integer>{

	
	AdminUser findByUsername(String username);

	AdminUser findByMobileNo(String mobileNo);
}
