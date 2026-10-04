package com.leonet.service;

import com.leonet.common.pojo.AdminRegistrationPojo;
import com.leonet.common.pojo.ChangePasswordPojo;
import com.leonet.common.pojo.ForgotPasswordPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.UserRegistrationPojo;

public interface LoginService {

	ResultVO adminRegistrationProcess(AdminRegistrationPojo adminRegistrationPojo);

	ResultVO forgotPasswordProcess(ForgotPasswordPojo forgotPasswordPojo);

	ResultVO generateOTP(String mobileNo);

	ResultVO validateOTP(ForgotPasswordPojo forgotPasswordPojo);

	ResultVO changePasswordProcess(ChangePasswordPojo changePasswordPojo);

	ResultVO memberRegistrationProcess(UserRegistrationPojo userRegistrationPojo);

}
