package com.leonet.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserUtil {
	
	 public static String getRole() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	        if (authentication == null || authentication.getAuthorities() == null) {
	            return "";
	        }

	        return authentication.getAuthorities()
	                .stream()
	                .findFirst()
	                .map(auth -> auth.getAuthority().replace("ROLE_", ""))
	                .orElse("");
	    }

}
