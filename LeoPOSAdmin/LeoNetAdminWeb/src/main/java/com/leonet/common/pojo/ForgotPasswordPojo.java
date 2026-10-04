/**
 * 
 */
package com.leonet.common.pojo;

/**
 * @author YOGESH
 *
 */
public class ForgotPasswordPojo {

	private String userName;
 	
	private String email;
 	
	private String password;
	
	private String mobileNo;
	
	private String otp;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "ForgotPasswordPojo [userName=" + userName + ", email=" + email + ", password=" + password
				+ ", mobileNo=" + mobileNo + ", otp=" + otp + "]";
	}
	
}
