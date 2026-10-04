/**
 * 
 */
package com.leonet.common.pojo;

/**
 * @author YOGESH
 *
 */
public class AdminRegistrationPojo {

	private String userName;
	private String password;
	private String mobileNo;
	private String email;
	private ResultVO resultVO;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public ResultVO getResultVO() {
		return resultVO;
	}
	public void setResultVO(ResultVO resultVO) {
		this.resultVO = resultVO;
	}
	@Override
	public String toString() {
		return "AdminRegistrationPojo [userName=" + userName + ", password=" + password + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", resultVO=" + resultVO + "]";
	}

	
}
