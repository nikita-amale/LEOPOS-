/**
 * 
 */
package com.leonet.serviceimpl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.common.pojo.AdminRegistrationPojo;
import com.leonet.common.pojo.ChangePasswordPojo;
import com.leonet.common.pojo.ForgotPasswordPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.entity.AdminUser;
import com.leonet.entity.MemberUser;
import com.leonet.repo.AdminUserRepo;
import com.leonet.repo.MemberUserRepo;
import com.leonet.service.LoginService;

/**
 * @author YOGESH
 *
 */
@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	AdminUserRepo adminUserRepo;
	
	@Autowired
	MemberUserRepo memberUserRepo;
	
	
	@Override
	public ResultVO adminRegistrationProcess(AdminRegistrationPojo adminRegistrationPojo) {
		ResultVO resultVO = new ResultVO();

		System.out.println("adminRegistrationPojo" + adminRegistrationPojo.toString());
		AdminUser AdminUser = new AdminUser();
		try {

			AdminUser adminUserRes = adminUserRepo.findByUsername(adminRegistrationPojo.getUserName());
	         

			if (adminUserRes == null) {
				AdminUser.setUsername(adminRegistrationPojo.getUserName());
				AdminUser.setPassword(adminRegistrationPojo.getPassword());
				AdminUser.setEmail(adminRegistrationPojo.getEmail());
				AdminUser.setMobileNo(adminRegistrationPojo.getMobileNo());
				AdminUser.setRole("Admin");
				 
				adminUserRepo.save(AdminUser);

				resultVO.setMsgDescr("User Save Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("User Allready Registered");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}


	@Override
	public ResultVO forgotPasswordProcess(ForgotPasswordPojo forgotPasswordPojo) {

		ResultVO resultVO = new ResultVO();

		try {
			AdminUser adminUserRes = adminUserRepo.findByMobileNo(forgotPasswordPojo.getMobileNo());
		 
			if (adminUserRes == null) {

				resultVO.setMsgDescr("mobile no  not found");
				resultVO.setError(true);
				return resultVO;
			} else {
				if (forgotPasswordPojo.getOtp().equals(adminUserRes.getOtp())) {

					adminUserRes.setPassword(forgotPasswordPojo.getPassword());
					adminUserRepo.save(adminUserRes);
					
					resultVO.setMsgDescr("password save sucessfully");
					resultVO.setError(false);
					return resultVO;
				} else {

					resultVO.setMsgDescr("not validate");
					resultVO.setError(true);
					return resultVO;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO generateOTP(String mobileNo) {

		ResultVO resultVO = new ResultVO();
 		String OTP = "";
		try {

			AdminUser adminUserRes = adminUserRepo.findByMobileNo(mobileNo);
			if (adminUserRes == null) {

				resultVO.setMsgDescr("mobile no  not found");
				resultVO.setError(true);
				return resultVO;

			} else {
				OTP = randomNumKeyGeneration();

				adminUserRes.setOtp(OTP);
				adminUserRepo.save(adminUserRes);
				
				resultVO.setMsgDescr("OTP Successfully generated");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("otp"+OTP);
		return resultVO;

	}

	public String randomNumKeyGeneration() {

		String Values = "0123456789";

		int len = 6;
		char[] password = new char[len];
		Random rndm_method = new Random();
		String psw = "";

		for (int i = 0; i < len; i++) {
			password[i] = Values.charAt(rndm_method.nextInt(Values.length()));
			psw = psw + password[i];
		}
		return psw;
	}

	@Override
	public ResultVO validateOTP(ForgotPasswordPojo forgotPasswordPojo) {
		ResultVO resultVO = new ResultVO();

		try {

			AdminUser adminUserRes = adminUserRepo.findByMobileNo(forgotPasswordPojo.getMobileNo());

			if (adminUserRes == null) {

				resultVO.setMsgDescr("mobile no  not found");
				resultVO.setError(true);
				return resultVO;
			} else {
				if (forgotPasswordPojo.getOtp().equals(adminUserRes.getOtp())) {

					resultVO.setMsgDescr("validate sucessfully");
					resultVO.setError(false);
					return resultVO;
				} else {

					resultVO.setMsgDescr("Not validate");
					resultVO.setError(true);
					return resultVO;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}

	@Override
	public ResultVO changePasswordProcess(ChangePasswordPojo changePasswordPojo) {

		ResultVO resultVO = new ResultVO();

		try {

			AdminUser adminUserRes = adminUserRepo.findByMobileNo(changePasswordPojo.getMobileNo());

			if (adminUserRes == null) {

				resultVO.setMsgDescr("mobile no  not found");
				resultVO.setError(true);
				return resultVO;
			} else {
				if (changePasswordPojo.getOtp().equals(adminUserRes.getOtp())) {

					if (changePasswordPojo.getCurrentPassword().equals(adminUserRes.getPassword())) {
						adminUserRes.setPassword(changePasswordPojo.getNewPassword());
						adminUserRepo.save(adminUserRes);
					 
					resultVO.setMsgDescr("password save sucessfully");
					resultVO.setError(false);
					return resultVO;
					}else {
						
						resultVO.setMsgDescr("password not match");
						resultVO.setError(false);
						return resultVO;
					}
					
				} else {

					resultVO.setMsgDescr("not validate");
					resultVO.setError(true);
					return resultVO;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}
	
	@Override
	public ResultVO memberRegistrationProcess(UserRegistrationPojo userRegistrationPojo) {
		ResultVO resultVO = new ResultVO();

		System.out.println("userRegistrationPojo" + userRegistrationPojo.toString());
		MemberUser memberUser = new MemberUser();
		try {

			MemberUser memberUserRes = memberUserRepo.findByUsername(userRegistrationPojo.getUserName());
	         

			if (memberUserRes == null) {
				memberUser.setUserName(userRegistrationPojo.getUserName());
				memberUser.setPassword("123456");
				memberUser.setEmail(userRegistrationPojo.getEmail());
				memberUser.setAddress(userRegistrationPojo.getAddress());
				memberUser.setBpts(0);
				memberUser.setCity("Pune");
				memberUser.setCountry("India");
				memberUser.setDeposit(0);
				memberUser.setGender("Default");
				memberUser.setDOB(userRegistrationPojo.getDOB());
				memberUser.setName(userRegistrationPojo.getName());
				memberUser.setPhonemain(userRegistrationPojo.getPhonemain());
				memberUser.setPhonealter(userRegistrationPojo.getPhonealter());
				memberUser.setPhonewhatsapp(userRegistrationPojo.getPhonewhatsapp());
				memberUser.setPincode(userRegistrationPojo.getPincode());
				memberUser.setPlan_id(userRegistrationPojo.getPlan_id());
				
				memberUser.setCompany(userRegistrationPojo.getCompany());
				memberUser.setChild2(userRegistrationPojo.getChild2());
				memberUser.setChild1age(userRegistrationPojo.getChild1age());
				memberUser.setChild2age(userRegistrationPojo.getChild2age());
				
				memberUser.setTgpts(0);
				memberUser.setRole("");
				 
				memberUserRepo.save(memberUser);

				resultVO.setMsgDescr("Member Saved Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("Member Allready Registered");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}


}
