/**
 * author MONINDER
 */
package com.leonet.controller;

import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.ReturnCashItemPojo;
import com.leonet.common.pojo.ReturnCashPojo;
import com.leonet.common.pojo.ReturnItemPojo;
import com.leonet.common.pojo.ReturnPojo;
import com.leonet.common.pojo.SaleItemPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.entity.MemberUser;
import com.leonet.repo.MemberUserRepo;
import com.leonet.service.ReturnsService;
import com.leonet.util.CurrentUserUtil;
import com.leonet.util.CustomFileUploadUtil;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Controller
public class ReturnsController {
	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@Autowired
	CustomFileUploadUtil fileUploadUtil;
	
	@Autowired
	ReturnsService returnsService;
	
	@Autowired
	MemberUserRepo memberUserRepo;

	
	@GetMapping(value = "/addReturns")
	public String addSales(Model model) {
		return "AddReturns";
	}

	@PostMapping("/addReturn")
	@ResponseBody
	public String addReturn(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,@RequestParam("applyReturn") long applyReturn,
			  Model modelMap) {

		LeoLogger.info("Return Controller --- addReturn--Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = returnsService.addReturn(addItemReqPojos,applyReturn);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/addReturn";
		}
		
	}


	@GetMapping(value = "/viewReturns")
	public String viewReturns(ModelMap modelMap) {
		List<ReturnPojo> returnPojo = returnsService.getReturnsList();
		LeoLogger.info("Returns Controller--viewReturns");
		//LeoLogger.info("returnPojo==" + returnPojo.toString());
		modelMap.addAttribute("returnPojo", returnPojo);
		
		return "ViewReturns";
	}
	
	@GetMapping(value = "/viewReturnNew")
	public String viewReturnNew(ModelMap modelMap) {
		int page=0;
		List<ReturnPojo> returnPojo = returnsService.getReturnsListNew(modelMap,page);
		LeoLogger.info("Returns Controller--viewReturns");
		//LeoLogger.info("returnPojo==" + returnPojo.toString());
		
		   String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role: {}", role);

		    // only filter if role is CUSTOM
		    if ("custom".equalsIgnoreCase(role) || "sales".equalsIgnoreCase(role)) {
		        List<ReturnPojo> filteredList = new ArrayList<>();
		        for (ReturnPojo ret : returnPojo) {
		            // fetch the member by memberid
		            MemberUser member = memberUserRepo.findById(ret.getMemberid());
		            if (member != null && !"Special".equalsIgnoreCase(member.getCtype())) {
		                filteredList.add(ret);
		            }
		        }
		        returnPojo = filteredList;
		    }

		modelMap.addAttribute("returnPojo", returnPojo);
		
		return "ViewReturnNew";
	}
	@GetMapping("/getReturnsListNew")
	public String getReturnsListNew(ModelMap modelMap,@RequestParam("page") int page) {
		List<ReturnPojo> returnPojo = returnsService.getReturnsListNew(modelMap,page);
		
		LeoLogger.info("Returns Controller--viewReturns");
		//LeoLogger.info("Sales Controller ---viewSales---salesPojo==" + salesPojo.toString());
		modelMap.addAttribute("returnPojo", returnPojo);

		return "ViewReturnNew";
	}
	@GetMapping("/getReturnsLispage")
	public String getReturnsLispage(ModelMap modelMap,@RequestParam(defaultValue = "0") int page,@RequestParam("pageSize") int pageSize) {
		LeoLogger.info("Return Controller -getQuotesListpage" );
		List<ReturnPojo> returnPojo = returnsService.getReturnsListpage(modelMap,page,pageSize);
		
		LeoLogger.info("return Controller ---viewReturn---"+page);
		LeoLogger.info("return Controller ---viewReturn---return==" + returnPojo.toString());
		modelMap.addAttribute("returnPojo", returnPojo);

		return "ViewReturnNew";
	}
	@GetMapping("/searchReturn")
	public String searchReturn(ModelMap modelMap,@RequestParam("search") String search) {
	

		List<ReturnPojo> returnPojo = returnsService.getSearchReturn(modelMap,search);
		
		
		   String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role: {}", role);

		    // only filter if role is CUSTOM
		    if ("custom".equalsIgnoreCase(role) || "sales".equalsIgnoreCase(role)) {
		        List<ReturnPojo> filteredList = new ArrayList<>();
		        for (ReturnPojo ret : returnPojo) {
		            // fetch the member by memberid
		            MemberUser member = memberUserRepo.findById(ret.getMemberid());
		            if (member != null && !"Special".equalsIgnoreCase(member.getCtype())) {
		                filteredList.add(ret);
		            }
		        }
		        returnPojo = filteredList;
		    }
		
		LeoLogger.info("Sales Controller ---searchQuote---" +search);
		//LeoLogger.info("Sales Controller ---viewSales---salesPojo==" + salesPojo.toString());
		modelMap.addAttribute("returnPojo", returnPojo);

		return "ViewReturnNew";
	}
	
		
	@GetMapping("/getReturnitembyreturnId")
	public @ResponseBody List<ReturnItemPojo>  getReturnitembyreturnId(ModelMap modelMap, @RequestParam("returnId") String returnId) {
	List<ReturnItemPojo> returnItemListPojo = returnsService.getReturnItembyreturnId(returnId);
			//LeoLogger.info("returnItemListPojo==" + returnItemListPojo.toString());
			modelMap.addAttribute("returnItemListPojo", returnItemListPojo.toString());
			return returnItemListPojo;
		}
	@GetMapping(value = "/editReturns")
	public String editReturns(Model model, @RequestParam("returnId") String returnId,ModelMap modelMap) {
		ReturnPojo returnPojo= new ReturnPojo();
		returnPojo= returnsService. getreturnbyreturnId(returnId);
		
		UserRegistrationPojo memberPojo = returnsService.getMemberByMemberid(returnPojo.getMemberid());
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("returnPojo", returnPojo);
		return "EditReturns";
	}
	@PostMapping("/updateReturns")
	@ResponseBody
	public ResultVO updateReturns(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,@RequestParam("returnid") double returnid,
			Model modelMap ) {
		//LeoLogger.info("Sales Controller ---updateSales");
		LeoLogger.info("Returns Controller ---updateReturns--- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = returnsService.updateReturns(addItemReqPojos,returnid);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
	//		return "EditSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
	//		return "redirect:/editSales";
		}
		
		return resultVO;
	}
	@PostMapping("/deleteReturn")
	public @ResponseBody ResultVO deleteReturn(@RequestParam("id") long id) {
		LeoLogger.info("Return Controller----delete Return---returnid==" + id);
		ResultVO resultVO = returnsService.deleteReturn(id);
		//LeoLogger.info(id);
		
		return resultVO;
	}
	@GetMapping(value = "/addReturnscash")
	public String addReturnscash(Model model) {
		return "AddReturnCash";
	}
	@PostMapping("/addReturnCash")
	@ResponseBody
	public String addReturnCash(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			  Model modelMap) {

		LeoLogger.info("Return Controller --- addReturnCash--Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = returnsService.addReturnCash(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/addReturn";
		}
		
	}
	
	@GetMapping(value = "/viewReturnscash")
	public String viewReturnscash(ModelMap modelMap) {
		List<ReturnCashPojo> returnPojo = returnsService.getReturnscashList();
		LeoLogger.info("Returns Controller--viewReturnscash");
		//LeoLogger.info("returnPojo==" + returnPojo.toString());
		
		   String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role: {}", role);

		    // only filter if role is CUSTOM
		   if ("custom".equalsIgnoreCase(role)) {
		        List<ReturnCashPojo> filteredList = new ArrayList<>();
		        for (ReturnCashPojo ret : returnPojo) {
		            // fetch the member by memberid
		            MemberUser member = memberUserRepo.findById(ret.getMemberid());
		            if (member != null && !"Special".equalsIgnoreCase(member.getCtype())) {
		                filteredList.add(ret);
		            }
		        }
		        returnPojo = filteredList;
		    }   
		modelMap.addAttribute("returnPojo", returnPojo);
		
		return "ViewReturnCash";
	}
	
	@GetMapping(value = "/viewReturnscashNew")
	public String viewReturnscashNew(ModelMap modelMap) {
		int page=0;
		List<ReturnCashPojo> returnPojo = returnsService.getReturnscashListNew(modelMap,page);
		LeoLogger.info("Returns Controller--viewReturnscash");
		//LeoLogger.info("returnPojo==" + returnPojo.toString());
		
		   String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role: {}", role);

		    // only filter if role is CUSTOM
		   if ("sales".equalsIgnoreCase(role)) {
		        List<ReturnCashPojo> filteredList = new ArrayList<>();
		        for (ReturnCashPojo ret : returnPojo) {
		            // fetch the member by memberid
		            MemberUser member = memberUserRepo.findById(ret.getMemberid());
		            if (member != null && !"Special".equalsIgnoreCase(member.getCtype())) {
		                filteredList.add(ret);
		            }
		        }
		        returnPojo = filteredList;
		    }  
		modelMap.addAttribute("returnPojo", returnPojo);
		
		return "ViewReturnCashNew";
	}
	@GetMapping("/getReturnCashLispage")
	public String getReturnCashLispage(ModelMap modelMap,@RequestParam(defaultValue = "0") int page,@RequestParam("pageSize") int pageSize) {
		LeoLogger.info("Return Controller -getQuotesListpage" );
		List<ReturnCashPojo> returnPojo = returnsService.getReturnscashListpage(modelMap,page,pageSize);
		
		LeoLogger.info("return Controller ---viewReturn---"+page);
		LeoLogger.info("return Controller ---viewReturn---return==" + returnPojo.toString());
		modelMap.addAttribute("returnPojo", returnPojo);

		return "ViewReturnCashNew";
	}
	@GetMapping("/getReturnscashListNew")
	public String getReturnscashListNew(ModelMap modelMap,@RequestParam("page") int page) {
		List<ReturnCashPojo> returnPojo = returnsService.getReturnscashListNew(modelMap,page);
		
		LeoLogger.info("Sales Controller ---viewSales---"+page);
		//LeoLogger.info("Sales Controller ---viewSales---salesPojo==" + salesPojo.toString());
		modelMap.addAttribute("returnPojo", returnPojo);

		return "ViewReturnCashNew";
	}
	@GetMapping("/searchReturncash")
	public String searchReturncash(ModelMap modelMap,@RequestParam("search") String search) {
	

		List<ReturnCashPojo> returnPojo = returnsService.getSearchReturncash(modelMap,search);
		
		LeoLogger.info("Sales Controller ---searchQuote---" +search);
		//LeoLogger.info("Sales Controller ---viewSales---salesPojo==" + salesPojo.toString());
		  String role = CurrentUserUtil.getRole();
		    LeoLogger.info("Logged-in User Role: {}", role);

		    // only filter if role is CUSTOM
		   if ("sales".equalsIgnoreCase(role)) {
		        List<ReturnCashPojo> filteredList = new ArrayList<>();
		        for (ReturnCashPojo ret : returnPojo) {
		            // fetch the member by memberid
		            MemberUser member = memberUserRepo.findById(ret.getMemberid());
		            if (member != null && !"Special".equalsIgnoreCase(member.getCtype())) {
		                filteredList.add(ret);
		            }
		        }
		        returnPojo = filteredList;
		    }  
		
		modelMap.addAttribute("returnPojo", returnPojo);

		return "ViewReturnCashNew";
	}
	@GetMapping(value = "/editReturnsCash")
	public String editReturnsCash(Model model, @RequestParam("returnId") String returnId,ModelMap modelMap) {
		ReturnCashPojo returnPojo= new ReturnCashPojo();
		returnPojo= returnsService. getreturncashbyreturnId(returnId);
		
		UserRegistrationPojo memberPojo = returnsService.getMemberByMemberid(returnPojo.getMemberid());
		modelMap.addAttribute("memberPojo", memberPojo);
		modelMap.addAttribute("returnPojo", returnPojo);
		return "EditReturnCash";
	}
	
	@PostMapping("/updateReturnscash")
	@ResponseBody
	public ResultVO updateReturnscash(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,@RequestParam("returnid") double returnid,
			Model modelMap ) {
		//LeoLogger.info("Sales Controller ---updateSales");
		LeoLogger.info("Returns Controller ---updateReturns--- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = returnsService.updateReturnsCash(addItemReqPojos,returnid);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
	//		return "EditSales";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
	//		return "redirect:/editSales";
		}
		
		return resultVO;
	}
	@GetMapping("/getReturnitemcashbyreturnId")
	public @ResponseBody List<ReturnCashItemPojo>  getReturnitemcashbyreturnId(ModelMap modelMap, @RequestParam("returnId") String returnId) {
	List<ReturnCashItemPojo> returnItemListPojo = returnsService.getReturnItemCashbyreturnId(returnId);
			//LeoLogger.info("returnItemListPojo==" + returnItemListPojo.toString());
			modelMap.addAttribute("returnItemListPojo", returnItemListPojo.toString());
			return returnItemListPojo;
		}
	@PostMapping("/deleteReturnCash")
	public @ResponseBody ResultVO deleteReturnCash(@RequestParam("id") long id) {
		LeoLogger.info("Return Controller---deleteReturnCash---returnid==" + id);
		ResultVO resultVO = returnsService.deleteReturnCash(id);
		//LeoLogger.info(id);
		
		return resultVO;
	}
	
	@PostMapping("/updateAllReturn")	
	@ResponseBody
	public ResultVO updateAllReturn(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,@RequestParam("returnid") long returnid,@RequestParam("applyReturn") long applyReturn,@RequestParam("isOldCashReturn") boolean isOldCashReturn,
			Model modelMap ) {

		LeoLogger.info("Returns Controller ---updateAllReturns--- Product List....." + addItemReqPojos.toString());
		LeoLogger.info("Returns Controller ---updateAllReturns--- Return id....." + returnid);
		LeoLogger.info("Returns Controller ---updateAllReturns--- Apply Return ....." + applyReturn);
		LeoLogger.info("Returns Controller ---updateAllReturns--- Is old Cash Return ....." + isOldCashReturn);

		ResultVO resultVO = returnsService.updateAllReturn(addItemReqPojos, returnid, applyReturn ,isOldCashReturn);
		
		//ResultVO resultVO = new ResultVO();
		resultVO.setMsgDescr("Return Added Sucessfully");
		resultVO.setMsgCode("001");
		resultVO.setError(false);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}

		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}

		}
		
		return resultVO;
	}
	
	@PostMapping("/newDeleteReturn")
	public @ResponseBody ResultVO newDeleteReturn(@RequestParam("id") long id) {
		LeoLogger.info("Return Controller----new delete Return---returnid==" + id);
		ResultVO resultVO = returnsService.newDeleteReturn(id);
				
		return resultVO;
	}
	
	
}
