package com.leonet.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ImportPurchasePojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.PurchaseFilePojo;
import com.leonet.common.pojo.PurchaseItemOnFilePojo;
import com.leonet.common.pojo.PurchaseItemPojo;
import com.leonet.common.pojo.PurchaseOnFilePojo;
import com.leonet.common.pojo.PurchasePojo;
import com.leonet.common.pojo.QuotesItemPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.entity.PurchaseFileEntity;
import com.leonet.repo.ImportPurchaseRepo;
import com.leonet.repo.PurchaseFileRepo;
import com.leonet.repo.PurchaseRepo;
import com.leonet.service.ImportPurchaseService;
import com.leonet.service.SaleService;
import com.leonet.util.LeoLogger;


@Controller
public class ImportPurchaseController {
	@Value("${imagesPath}")
	private String imagesPath;

	@Value("${viwePath}")
	private String viwePath;

	@Autowired(required = false)
	ImportPurchaseService importpurchaseService;

	@Autowired(required = false)
	ImportPurchaseRepo importpurchaseRepo;
	
	@Autowired
	PurchaseFileRepo purchasefileRepo;
	


	@Autowired(required = false)
	Mapper modelMapper;

	@Autowired
	SaleService saleService;

	@GetMapping(value = "/importPurchase")
	public String purchase(ModelMap modelMap) {
		
		List<ImportPurchasePojo> importpurchasePojo = importpurchaseService.getImportPurchaseList();
		//LeoLogger.info("ImportPurchase Controller ---purchase---importPurchasePojo==" + importpurchasePojo.toString());
		modelMap.addAttribute("importPurchasePojo", importpurchasePojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "ImportPurchase";
	}

	@PostMapping("/importPurchase")
	public @ResponseBody ResultVO importpurchase(HttpServletRequest request, @RequestBody ImportPurchasePojo importpurchasePojo,
			Model modelMap) {
		LeoLogger.info("Import Purchase Controller ---savePurchaseBeforeApply");
		//LeoLogger.info("Import Purchase Controller ---importPurchase-- ImportPurchasePojo Request Pojo JSON ....."
		//		+ importpurchasePojo.toString());
		ResultVO resultVO = importpurchaseService.savePurchaseBeforeApply(importpurchasePojo);
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

	@PostMapping("/applypurchase")
	public @ResponseBody ResultVO applypurchase(ModelMap modelMap) {
		LeoLogger.info("Import Purchase Controller ---savePurchaseAftereApply");
		ResultVO resultVO = importpurchaseService.savePurchaseAftereApply();
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

	@GetMapping(value = "/viewPurchase")
	public String viewPurchase(ModelMap modelMap) {
		LeoLogger.info("ImportPurchase Controller---viewPurchase");
		List<PurchasePojo> purchasePojo = importpurchaseService.getPurchaseList();
		//LeoLogger.info("ImportPurchase Controller---purchasePojo==" + purchasePojo.toString());
		modelMap.addAttribute("purchasePojo", purchasePojo);

		return "ViewPurchase";
	}

	@GetMapping("/getPurchaseitembypurchaseId")
	public @ResponseBody List<PurchaseItemPojo> getPurchaseitembypurchaseId(ModelMap modelMap,
			@RequestParam("purchaseId") String purchaseId) {
		List<PurchaseItemPojo> purchaseItemListPojo = importpurchaseService.getPurchaseitembypurchaseId(purchaseId);
		List<PurchasePojo> purchasePojo = importpurchaseService.getPurchaseList();
	    LeoLogger.info("ImportPurchase Controller----getPurchaseitembypurchaseId---purchaseItemListPojo==" + purchaseItemListPojo.toString());
		//LeoLogger.info("ImportPurchase Controller---purchasePojo==" + purchasePojo.toString());
		modelMap.addAttribute("purchaseItemListPojo", purchaseItemListPojo.toString());
		modelMap.addAttribute("purchasePojo", purchasePojo);
		return purchaseItemListPojo;
	}

	@PostMapping("/updateImportPurchase")
	public String updateProductDetails(@ModelAttribute("importPurchase") ImportPurchasePojo importPurchasePojo,
			ModelMap modelMap) throws IOException {
		LeoLogger.info("ImportPurchase Controller----updateImportPurchase---inside updateImportPurchase ::::"+importPurchasePojo);
		ResultVO resultVO = new ResultVO();

		resultVO = importpurchaseService.updateimportPurchase(importPurchasePojo);

		modelMap.addAttribute("Msg", resultVO.getMsgDescr());
		return "redirect:/importPurchase";
	}

	@PostMapping("/deleteImportPurchase")
	public @ResponseBody ResultVO deleteImportPurchase(@RequestParam("id") long id) {
		ResultVO resultVO = importpurchaseService.deleteImportPurchase(id);
		//LeoLogger.info(id);
		LeoLogger.info("ImportPurchase Controller----deleteImportPurchase---resultVO==" + resultVO.toString());
		return resultVO;
	}

	@PostMapping(value = "/getPurchase/{id}")
	public @ResponseBody ImportPurchasePojo getPurchaseById(@PathVariable("id") Long Id, ModelMap modelMap) {
		ImportPurchasePojo purchasePojo = importpurchaseService.getImportPurchaseById(Id);
		return purchasePojo;
	}
	@GetMapping(value = "/addPurchase")
	public String addPurchase(ModelMap modelMap) {
		List<PurchaseItemOnFilePojo> purchasePojo = importpurchaseService.getPurchaseOnFileList();
		//LeoLogger.info("ImportPurchase Controller ---purchase---importPurchasePojo==" + importpurchasePojo.toString());
		modelMap.addAttribute("purchasePojo", purchasePojo);
		modelMap.addAttribute("imagesPath", viwePath);
		return "AddPurchase";
	}
	@PostMapping("/addPurchase")
	public String addPurchase(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info("ImportPurchase Controller----addPurchase --- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = importpurchaseService.addPurchase(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddPurchase";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/addPurchase";
		}

	}
	

	@PostMapping("/upload")
	public String upload(@RequestParam("fileupload") MultipartFile multipartFile, @ModelAttribute("purchasefilePojo") PurchaseFilePojo purchasefilePojo,ModelMap modelMap) throws IOException {
	
		LeoLogger.info("ImportPurchase Controller----upload ---." );
		
		
		purchasefilePojo.setFile(multipartFile.getBytes());
		purchasefilePojo.setTitle(multipartFile.getOriginalFilename());
		
		System.out.println(multipartFile.getOriginalFilename());
		System.out.println(multipartFile.getSize());
		System.out.println(multipartFile.getContentType());
		System.out.println(multipartFile.getName());
						
			
		
		
		System.out.println("IN CONTROLLER");
		System.out.println("purchasefilePojo   :" + purchasefilePojo.toString());			
		
		ResultVO  resultVO = importpurchaseService.savefile(purchasefilePojo);
		
		System.out.println(resultVO.getMsgDescr());
		
       if(resultVO.getMsgCode().equals("001")) {
			
			if(resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		//return "ImportPurchase";
		}else {
			if(resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		//return "ImportPurchase";
			
			//return "redirect:/importPurchase";	

	}
       return "redirect:/importPurchase";


	}
	
	
	
	@PostMapping("/uploadfile")
	public String uploadfile(@RequestParam("fileupload") MultipartFile multipartFile, @ModelAttribute("purchasefilePojo") PurchaseFilePojo purchasefilePojo, ModelMap modelMap) throws IOException {
	
		LeoLogger.info("ImportPurchase Controller----upload ---." );
		
		
		purchasefilePojo.setFile(multipartFile.getBytes());
		purchasefilePojo.setTitle(multipartFile.getOriginalFilename());
		
		System.out.println(multipartFile.getOriginalFilename());
		System.out.println(multipartFile.getSize());
		System.out.println(multipartFile.getContentType());
		System.out.println(multipartFile.getName());
						
			
		
		
		System.out.println("IN CONTROLLER");
		System.out.println("purchasefilePojo   :" + purchasefilePojo.toString());			
		
		ResultVO  resultVO = importpurchaseService.uploadfile(purchasefilePojo);
		
		System.out.println(resultVO.getMsgDescr());
		
       if(resultVO.getMsgCode().equals("001")) {
			
			if(resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		//return "ImportPurchase";
		}else {
			if(resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
		//return "ImportPurchase";
			
			//return "redirect:/importPurchase";	

	}
       return "redirect:/addPurchase";


	}
	
	@GetMapping("/download")
	public void downloadfiles(@Param("id") Long id,HttpServletResponse response)throws IOException {
	 List<PurchaseFileEntity> result =purchasefileRepo.findAllByPurchaseid(id);
	 LeoLogger.info("Sales Controller ---editSales"+result);
	 
	 PurchaseFileEntity purchasefileentity= result.get(0);
	 
	 String headerKey ="Content-Disposition";
	 String headerValue="attachement; filename="+purchasefileentity.getTitle();
	 
	 response.setHeader(headerKey, headerValue);
	ServletOutputStream outputStream =response.getOutputStream();
	
	outputStream.write(purchasefileentity.getFile());
	outputStream.close();
	}
	
	@PostMapping("/addPurchaseBeforSubmit")
	public String addPurchaseBeforSubmit(HttpServletRequest request, @RequestBody List<AddItemReqPojo> addItemReqPojos,
			Model modelMap) {

		LeoLogger.info("ImportPurchase Controller----addPurchase --- Add item Request Pojo JSON ....." + addItemReqPojos.toString());

		ResultVO resultVO = importpurchaseService.addPurchaseBeforSubmit(addItemReqPojos);

		if (resultVO.getMsgCode().equals("001")) {

			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "AddPurchase";
		} else {
			if (resultVO.isError() == false) {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			} else {
				modelMap.addAttribute("Msg", resultVO.getMsgDescr());
			}
			return "redirect:/addPurchase";
		}

	}
	
	@PostMapping("/editsellingpercentage")
	public String editsellingpercentage(ModelMap map, @RequestBody ImportPurchasePojo importpurchasePojo) {
		LeoLogger.info("ImportPurchase Controller---editsellingpercentage...." + importpurchasePojo.toString());

		ResultVO resultVO = importpurchaseService.editsellingpercentage(importpurchasePojo);
		map.addAttribute("result", resultVO);
		 return "redirect:/importPurchase";
	}
	
	
	@PostMapping("/transferpurchasetoquote")
	public @ResponseBody ResultVO transferpurchasetoquote(ModelMap modelMap,@RequestParam("purchaseId") String purchaseId,@RequestParam("customerId") String customerId) {
		
		    ResultVO resultVO  = importpurchaseService.TransferPurchaseToQuotes(purchaseId,customerId);
			
		   
			//LeoLogger.info("ImportPurchase Controller---purchasePojo==" + purchasePojo.toString());
			
		    return resultVO;
		

	}

	}
	
	



