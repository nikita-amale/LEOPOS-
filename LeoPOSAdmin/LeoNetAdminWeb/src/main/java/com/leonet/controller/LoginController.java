/**
 * 
 */
package com.leonet.controller;

import java.io.IOException;

import java.net.URISyntaxException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.leonet.common.pojo.AdminRegistrationPojo;
import com.leonet.common.pojo.ChangePasswordPojo;
import com.leonet.common.pojo.ForgotPasswordPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.service.LoginService;
import com.leonet.service.SaleService;
import com.leonet.util.LeoLogger;

/**
 * @author YOGESH
 *
 */
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	@Autowired
	SaleService saleService;
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model) {
		String errorMessge = null;
		if (error != null) {
			errorMessge = "Username or Password is incorrect !!";
		}
		if (logout != null) {
			errorMessge = "You have been successfully logged out !!";
		}
		model.addAttribute("errorMessge", errorMessge);
		return "Login";
	}

	@GetMapping(value = "/register")
	public String Register(Model model) {
		return "Register";
	}

	@PostMapping("/userRegiProccess")
	public String userRegistrationProcess(
			@ModelAttribute("adminRegistration") AdminRegistrationPojo adminRegistrationPojo, HttpSession session,
			ModelMap modelMap) {
		ResultVO resultVO = loginService.adminRegistrationProcess(adminRegistrationPojo);

		if (resultVO.isError == true) {

			modelMap.addAttribute("errorMessge", resultVO.getMsgDescr());
			return "Register";
		} else {

			return "redirect:login";
		}

	}

	@GetMapping(value = "/forgotPassword")
	public String forgotPassword(Model model) {
		return "ForgotPassword";
	}

	@PostMapping("/forgotPasswordProcess")
	public String forgotPasswordProcess(@ModelAttribute("forgotPassword") ForgotPasswordPojo forgotPasswordPojo,
			ModelMap modelMap) {
		ResultVO resultVO = loginService.forgotPasswordProcess(forgotPasswordPojo);

		LeoLogger.info("Login Controller ---forgotPasswordProcess---resultVO===" + resultVO.toString());
		if (resultVO.isError == true) {

			return "redirect:forgotPassword";
		} else {

			return "redirect:login";
		}

	}

	@GetMapping("/generateOTP")
	public @ResponseBody ResultVO generateOTP(@RequestParam("mobileNo") String mobileNo, ModelMap modelMap) {
 		ResultVO resultVO = loginService.generateOTP(mobileNo);
 		
 		return resultVO;
	}
	
	@GetMapping("/validateOTP")
	public ResultVO validateOTP(@ModelAttribute("forgotPassword") ForgotPasswordPojo forgotPasswordPojo, ModelMap modelMap) {
		ResultVO resultVO  = loginService.validateOTP(forgotPasswordPojo);
 		
 		return resultVO;
	}
	
	@GetMapping(value = "/changePassword")
	public String changePassword(Model model) {
		return "ChangePassword";
	}
	
	@PostMapping("/changePasswordProcess")
	public String changePasswordProcess(@ModelAttribute("adminLogin") ChangePasswordPojo changePasswordPojo, ModelMap modelMap) {
 		ResultVO resultVO = loginService.changePasswordProcess(changePasswordPojo);
 		
 		if(resultVO.isError == true) {
			
 			return "redirect:changePassword";
		}else {
			
			return "redirect:login";
		}
 		
	}
	
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout=true";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String loginPage(ModelMap modelMap, Principal principal,HttpSession session) {
		LeoLogger.info("Login Controller --home ");

		session.setAttribute("userName", principal.getName());
		double total = 0.0,qtotal=0.0,paymenttotal=0.0,returntotal=0.0,bulkpayment=0.0;
		total = saleService.calgrandtoatl();
		qtotal = saleService.calqtoatl();
		paymenttotal = saleService.calptoatl();
		returntotal = saleService.calrtoatl();
		bulkpayment = saleService.calbtoatl();
		modelMap.addAttribute("grand_total", total);
		modelMap.addAttribute("quotegrand_total", qtotal);
		modelMap.addAttribute("paymentgrand_total", paymenttotal);
		modelMap.addAttribute("returngrand_total", returntotal);
		modelMap.addAttribute("bulktotal", bulkpayment);
		return "index";
	}
	
	
	@GetMapping(value = "/registerMember")
	public String RegisterMember(Model model) {
		return "RegisterMember";
	}
	
	
	@PostMapping("/memberRegiProccess")
	public String memberRegistrationProcess(@ModelAttribute("memberDetails") UserRegistrationPojo userRegistrationPojo,
			 ModelMap modelMap) throws IOException, URISyntaxException {
 	
		LeoLogger.info("Login Controller ---memberRegiProccess---userRegistrationPojo   "+ userRegistrationPojo.getName());
		ResultVO resultVO = loginService.memberRegistrationProcess(userRegistrationPojo);
		
		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "RegisterMember";

	}
	

}
