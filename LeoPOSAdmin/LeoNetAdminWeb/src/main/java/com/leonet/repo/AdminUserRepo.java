package com.leonet.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leonet.entity.AdminUser;
 
public interface AdminUserRepo extends JpaRepository<AdminUser, Integer>{

	
	AdminUser findByUsername(String username);

	AdminUser findByMobileNo(String mobileNo);
	
	 @Query("SELECT u.username FROM AdminUser u")
	 List<String> findAllUserNames();
}
