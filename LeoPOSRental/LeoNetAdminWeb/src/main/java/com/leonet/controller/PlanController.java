/**
 * author MONINDER
 */
package com.leonet.controller;

 
import java.io.IOException;

import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.leonet.common.pojo.PlanPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.service.PlanService;
import com.leonet.util.FileUploadUtil;

/**
 * @author MONINDER
 *
 */
@Controller
public class PlanController {
	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@Autowired
	FileUploadUtil fileUploadUtil;

	@Autowired
	PlanService planService;

	@GetMapping(value = "/addPlan")
	public String addPlan(Model model) {
		return "AddPlan";
	}

	@PostMapping("/addPlan")
	public String addPlan(@ModelAttribute("addPlan") PlanPojo planPojo, ModelMap modelMap) {
		ResultVO resultVO = planService.addPlan(planPojo);
		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddPlan";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/addPlan";
		}
   
	}



}

