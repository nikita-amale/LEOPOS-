/**
 * 
 */
package com.leonet.common.pojo;

/**
 * @author YOGESH
 *
 */
public class ChangePasswordPojo {

 
	private String userName;
	private String currentPassword;
	private String newPassword;
	private String mobileNo;
	private String otp;
 	private ResultVO resultVO;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public ResultVO getResultVO() {
		return resultVO;
	}
	public void setResultVO(ResultVO resultVO) {
		this.resultVO = resultVO;
	}
	
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Override
	public String toString() {
		return "ChangePasswordPojo [userName=" + userName + ", currentPassword=" + currentPassword + ", newPassword="
				+ newPassword + ", mobileNo=" + mobileNo + ", otp=" + otp + ", resultVO=" + resultVO + "]";
	}
	 
}
