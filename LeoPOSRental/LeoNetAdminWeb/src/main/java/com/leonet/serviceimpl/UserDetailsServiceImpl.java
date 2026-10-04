package com.leonet.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leonet.config.MyUserDetails;
import com.leonet.entity.AdminUser;
import com.leonet.repo.AdminUserRepo;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	 
    @Autowired
    private AdminUserRepo userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        AdminUser user = userRepository.findByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
          
        return new MyUserDetails(user);
    }
}
