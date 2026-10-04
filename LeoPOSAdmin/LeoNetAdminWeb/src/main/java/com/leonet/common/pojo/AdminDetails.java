/**
 * 
 */
package com.leonet.common.pojo;

/**
 * @author YOGESH
 *
 */
public class AdminDetails {
	
	private Long id;
	 
    private String username;
    private String password;
    private String role;
	private String mobileNo;
	private String email;
	private String otp;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", mobileNo=" + mobileNo + ", email=" + email + ", otp=" + otp + "]";
	}
    
	
	
}
