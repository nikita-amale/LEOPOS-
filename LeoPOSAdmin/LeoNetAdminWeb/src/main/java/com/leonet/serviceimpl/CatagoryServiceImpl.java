/**
 * 
 */
package com.leonet.serviceimpl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.common.entity.BarCodeEntity;
import com.leonet.common.entity.ProductCategotyEntity;
import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.entity.User;
import com.leonet.common.entity.ProductDiscount;
import com.leonet.common.entity.ProductSubCategoryEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.VendorEntity;
import com.leonet.common.pojo.AdminDetails;
import com.leonet.common.pojo.BarCodePojo;
import com.leonet.common.pojo.PlanPojo;
import com.leonet.common.pojo.ProductCategoryPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.ProductSubCategoryPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.SalesPercentagePojo;
import com.leonet.common.pojo.UnitPojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.pojo.VendorPojo;
import com.leonet.common.repo.BarCodeRepo;
import com.leonet.common.repo.ProductCategotyRepo;
import com.leonet.common.repo.ProductDetailsRepository;
import com.leonet.common.repo.ProductDiscountRepo;
import com.leonet.common.repo.ProductSubCategoryRepo;
import com.leonet.common.repo.VendorRepo;
import com.leonet.entity.AdminUser;
import com.leonet.entity.MemberUser;
import com.leonet.entity.PlanEntity;
import com.leonet.entity.SalesPercentageEntity;
import com.leonet.entity.UnitEntity;
import com.leonet.repo.AdminUserRepo;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.PlanRepo;
import com.leonet.repo.ProductDetailsRepo;
import com.leonet.repo.SalesPercentageRepo;
import com.leonet.repo.UnitRepo;
import com.leonet.service.CatagoryService;
import com.leonet.util.CurrentUserUtil;
import com.leonet.util.LeoLogger;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.leonet.constant.Action;

/**
 * @author YOGESH
 *
 */
@Service
public class CatagoryServiceImpl implements CatagoryService {

	@Autowired
	VendorRepo vendorRepo;

	@Autowired
	ProductCategotyRepo addCatagoryRepository;

	@Autowired
	ProductSubCategoryRepo addSubCatagoryRepository;

	@Autowired
	ProductDetailsRepository productDetailsRepository;

	@Autowired
	MemberUserRepo memberUserRepo;

	@Autowired
	ProductDiscountRepo productDiscountRepo;

	@Autowired
	BarCodeRepo barCodeRepo;

	@Autowired
	AdminUserRepo userRepo;

	@Autowired
	MemberUserRepo memberRepo;

	@Autowired
	ProductDetailsRepo productDetailsRepo;

	@Autowired
	PlanRepo planRepo;

	@Autowired
	UnitRepo unitRepo;

	@Autowired
	SalesPercentageRepo salespercentRepo;

	@Autowired
	Mapper mapper;
	
	@Autowired
	private com.leonet.service.SysAuditService sysAuditService;

	private static final String FOLDER_PATH = "images/";

	@Override
	public ResultVO addCatagory(ProductCategoryPojo catagoryPojo) {
		ResultVO resultVO = new ResultVO();
		ProductCategotyEntity catagoryEntity = new ProductCategotyEntity();
		try {

			ProductCategotyEntity catagoryEntityRes = addCatagoryRepository.findByCatCode(catagoryPojo.getCatCode());

			if (catagoryEntityRes == null) {

				catagoryEntity.setCatName(catagoryPojo.getCatName());
				catagoryEntity.setCatDesc(catagoryPojo.getCatDesc());
				catagoryEntity.setIsActive(0);
				addCatagoryRepository.save(catagoryEntity);

				resultVO.setMsgDescr("Save Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				catagoryEntityRes.setCatName(catagoryPojo.getCatName());
				catagoryEntityRes.setCatDesc(catagoryPojo.getCatDesc());
				addCatagoryRepository.save(catagoryEntityRes);

				resultVO.setMsgDescr("Update Sucessfully");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO addSubCatagory(ProductSubCategoryPojo subCatagoryPojo) {
		ResultVO resultVO = new ResultVO();
		ProductSubCategoryEntity subCatagoryEntity = new ProductSubCategoryEntity();
		try {

			ProductSubCategoryEntity subCatagoryEntityRes = addSubCatagoryRepository
					.findBySubCatCode(subCatagoryPojo.getSubCatCode());

			if (subCatagoryEntityRes == null) {
				subCatagoryEntity.setCatCode(subCatagoryPojo.getCatCode());
				subCatagoryEntity.setSubCatName(subCatagoryPojo.getSubCatName());
				subCatagoryEntity.setSubCatDesc(subCatagoryPojo.getSubCatDesc());
				subCatagoryEntity.setIsActive(0);
				subCatagoryEntity.setSubCatLogo(subCatagoryPojo.getSubCatLogo());

				addSubCatagoryRepository.save(subCatagoryEntity);

				resultVO.setMsgDescr("Save Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				subCatagoryEntityRes.setSubCatName(subCatagoryPojo.getSubCatName());
				subCatagoryEntityRes.setSubCatDesc(subCatagoryPojo.getSubCatDesc());
				subCatagoryEntityRes.setSubCatLogo(subCatagoryPojo.getSubCatLogo());

				addSubCatagoryRepository.save(subCatagoryEntityRes);
				resultVO.setMsgDescr("Update Sucessfully");
				resultVO.setMsgCode("002");
				resultVO.setError(false);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<ProductCategoryPojo> getCatagoryList() {
		List<ProductCategotyEntity> CatagoryEntityList = new ArrayList<ProductCategotyEntity>();
		List<ProductCategoryPojo> catagoryPojoList = new ArrayList<ProductCategoryPojo>();

		try {
			CatagoryEntityList = addCatagoryRepository.findByIsActiveOrderByIdDesc(0);
			catagoryPojoList = mapper.map(CatagoryEntityList, List.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return catagoryPojoList;
	}

	@Override
	public List<ProductSubCategoryPojo> getSubCatagory(long catagoryId) {

		List<ProductSubCategoryEntity> subCatagoryEntityList = new ArrayList<ProductSubCategoryEntity>();
		List<ProductSubCategoryPojo> subCatagoryPojoList = new ArrayList<ProductSubCategoryPojo>();

		try {
			subCatagoryEntityList = addSubCatagoryRepository.findByCatCodeAndIsActiveOrderByIdDesc(catagoryId, 0);
			subCatagoryPojoList = mapper.map(subCatagoryEntityList, List.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subCatagoryPojoList;
	}

	@Override
	public ResultVO addProductDetails(ProductDetailsPojo productDetailsPojo) {
		ResultVO resultVO = new ResultVO();
		ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity();
		ProductDiscount productDiscount = new ProductDiscount();
		try {

			List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();

			ProductCategotyEntity catagoryEntityRes = addCatagoryRepository
					.findByCatCode(productDetailsPojo.getcategory_id());
			ProductSubCategoryEntity subCatagoryEntityRes = addSubCatagoryRepository
					.findBySubCatCode(productDetailsPojo.getsubcategory_id());
			String catName = catagoryEntityRes.getCatName();
			String subCatName = subCatagoryEntityRes.getSubCatName();

			List<ProductCategotyEntity> productCategotyEntity = addCatagoryRepository.findAll();
			List<ProductSubCategoryEntity> productSubCategoryEntity = addSubCatagoryRepository.findAll();

			String catname1 = catName.charAt(0) + "".trim();
			String subcatname1 = subCatName.charAt(0) + "".trim();
			int count = 1;
			productDetailsEntityList = productDetailsRepository.findAll();
			if (productDetailsEntityList != null) {
				for (ProductDetailsEntity productDetailsEntity1 : productDetailsEntityList) {
					long catcode = productDetailsPojo.getcategory_id();
					long subcatecode = productDetailsPojo.getsubcategory_id();

					List<ProductCategotyEntity> catList = productCategotyEntity.stream()
							.filter(s -> s.getCatCode() == catcode).collect(Collectors.toList());

					List<ProductSubCategoryEntity> subcatList = productSubCategoryEntity.stream()
							.filter(s -> s.getSubCatCode() == subcatecode).collect(Collectors.toList());

					String catcodename = catList.get(0).getCatName().toLowerCase();
					String subcatcodename = subcatList.get(0).getSubCatName().toLowerCase();
					String st1 = catcodename.charAt(0) + "".trim();
					String st2 = subcatcodename.charAt(0) + "".trim();

					if (st1.equalsIgnoreCase(catname1) && st2.equalsIgnoreCase(subcatname1)) {
						count++;
					}
				}
			}
			String itemCode = "";
			if (count <= 9) {
				itemCode = catname1.toUpperCase() + subcatname1.toUpperCase() + "00000".concat(count + "");

			} else if (count >= 10 && count <= 99) {
				itemCode = catname1.toUpperCase() + subcatname1.toUpperCase() + "0000".concat(count + "");

			} else if (count >= 100 && count <= 999) {
				itemCode = catname1.toUpperCase() + subcatname1.toUpperCase() + "000".concat(count + "");

			} else if (count >= 1000 && count <= 9999) {
				itemCode = catname1.toUpperCase() + subcatname1.toUpperCase() + "00".concat(count + "");

			} else if (count >= 10000 && count <= 99999) {
				itemCode = catname1.toUpperCase() + subcatname1.toUpperCase() + "0".concat(count + "");

			}

			// productDetailsEntity.setProductId(productDetailsPojo.getProductId());

			productDetailsEntity.setname(productDetailsPojo.getname());
			productDetailsEntity.setcategory_id(productDetailsPojo.getcategory_id());
			productDetailsEntity.setsubcategory_id(productDetailsPojo.getsubcategory_id());
			productDetailsEntity.setproduct_details(productDetailsPojo.getproduct_details());
			productDetailsEntity.setprice(productDetailsPojo.getprice());
			productDetailsEntity.setrollprice(productDetailsPojo.getrollprice());
			// productDetailsEntity.set(0)
			productDetailsEntity.setcost(productDetailsPojo.getcost());
			productDetailsEntity.setcf1(productDetailsPojo.getcf1());
			productDetailsEntity.settrack_quantity(productDetailsPojo.gettrack_quantity());
			productDetailsEntity.setpromotion(productDetailsPojo.getpromotion());

			productDetailsEntity.setstart_date(new Date());

			productDetailsEntity.setunit(productDetailsPojo.getunit());
			productDetailsEntity.setQuantity(BigDecimal.ZERO);
			productDetailsEntity.settax_rate(productDetailsPojo.gettax_rate());
			MultipartFile file = productDetailsPojo.getProductImage();

			saveProductImage(file, productDetailsEntity);

			productDetailsRepository.save(productDetailsEntity);

			productDiscount.setFromDate(new Date());
			productDiscountRepo.save(productDiscount);

			Long code = productDetailsEntity.getProductId();
			LeoLogger.info("CatagoryServiceImpl---getProductDetailsList---in product code" + code);
			productDetailsEntity.setcode(code.toString());
			productDetailsRepository.save(productDetailsEntity);

			resultVO.setMsgDescr("Product Saved Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	public void saveProductImage(MultipartFile file, ProductDetailsEntity productDetailsEntity) throws IOException {

		System.out.println("********inside saveProductImage ******");

		if (Objects.nonNull(file)) {
			System.out.println("********image not null ======");

			String fileName = file.getOriginalFilename();
			String filePath = FOLDER_PATH + fileName;

			// Create the directory if it doesn't exist
			File directory = new File(FOLDER_PATH);
			Path uploadPath = Paths.get(FOLDER_PATH);

			if (!directory.exists()) {
				System.out.println("**********create directory ******");
				Files.createDirectories(uploadPath);
			}

			// Save the file information to the database

			productDetailsEntity.setProductFileName(fileName + System.currentTimeMillis());
			productDetailsEntity.setProductFilePath(filePath);

			// Upload the file to the specified directory
			// Path targetPath = Path.of(filePath);
			Path targetPath = Paths.get(filePath);
			System.out.println("**********file path ========" + targetPath);

			Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("******************file upload success ************ ");
			LeoLogger.info("**************file upload success ***********");
		}

	}

	@Override
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		ProductDetailsEntity fileData = productDetailsRepository.findByProductFileName(fileName);
		System.out.println("fileData  =" + fileData);
		if (Objects.nonNull(fileData)) {
			String filePath = fileData.getProductFilePath();
			byte[] images = Files.readAllBytes(new File(filePath).toPath());
			return images;
		}
		return null;

	}

	@Override
	public List<ProductDetailsPojo> getProductDetailsList() {
		List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();
		try {
			LeoLogger.info("CatagoryServiceImpl---getProductDetailsList---in product");
			productDetailsEntityList = productDetailsRepository.findAll();
			for (ProductDetailsEntity productDetailsEntityRes : productDetailsEntityList) {
				// ProductDiscount productDiscount =
				// productDiscountRepo.findByItemCodeAndIsActive(productDetailsEntityRes.getcode(),
				// 0);
				// if(productDiscount ==null) {
				ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
				productDetailsPojo = mapper.map(productDetailsEntityRes, ProductDetailsPojo.class);
				productDetailsPojo.setProductFileName(productDetailsEntityRes.getProductFileName());

				productDetailsPojoList.add(productDetailsPojo);
				// }else {
				// ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
				// productDetailsPojo = mapper.map(productDetailsEntityRes,
				// ProductDetailsPojo.class);
				// productDetailsPojoList.add(productDetailsPojo);

				// }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetailsPojoList;
	}

	@Override
	public List<ProductDetailsPojo> getProductDetailsListPage(ModelMap modelMap, int page) {
		List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();
		try {
			LeoLogger.info("CatagoryServiceImpl---getProductDetailsList---in product");
			int recordsLength = 25;
			Pageable paging = PageRequest.of(page, recordsLength);
			Page<ProductDetailsEntity> productDetailsEntity;

			productDetailsEntity = productDetailsRepository.findAllByOrderByProductIdAsc(paging);
			LeoLogger.info("page= " + page);
			LeoLogger.info(productDetailsEntity.getNumber() + "");
			LeoLogger.info(productDetailsEntity.getNumberOfElements() + "");
			LeoLogger.info(productDetailsEntity.getSize() + "");
			LeoLogger.info(productDetailsEntity.getTotalElements() + "");
			System.out.println(productDetailsEntity.getTotalPages());
			System.out.println(productDetailsEntity.hasNext());
			System.out.println(productDetailsEntity.hasPrevious());
			// salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
			productDetailsEntityList = productDetailsEntity.getContent();
			modelMap.addAttribute("totalPages", productDetailsEntity.getTotalPages());
			modelMap.addAttribute("totalRecords", productDetailsEntity.getTotalElements());
			modelMap.addAttribute("currentRecords", productDetailsEntity.getNumberOfElements());
			modelMap.addAttribute("previous", productDetailsEntity.hasPrevious());
			modelMap.addAttribute("next", productDetailsEntity.hasNext());
			modelMap.addAttribute("page", productDetailsEntity.getNumber());

			// productDetailsEntityList = productDetailsRepository.findAll();
			for (ProductDetailsEntity productDetailsEntityRes : productDetailsEntityList) {
				// ProductDiscount productDiscount =
				// productDiscountRepo.findByItemCodeAndIsActive(productDetailsEntityRes.getcode(),
				// 0);
				// if(productDiscount ==null) {
				ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
				productDetailsPojo = mapper.map(productDetailsEntityRes, ProductDetailsPojo.class);
				productDetailsPojoList.add(productDetailsPojo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetailsPojoList;
	}

	@Override
	public ResultVO deleteProductDetails(String productCode) {
		ResultVO resultVO = new ResultVO();
		/*
		 * try {
		 * 
		 * ProductDetailsEntity ProductDetailsEntityRes = productDetailsRepository
		 * .findByItemCodeAndIsActive(productCode, 0);
		 * 
		 * if (ProductDetailsEntityRes != null) {
		 * 
		 * //ProductDetailsEntityRes.setIsActive(1);
		 * productDetailsRepository.save(ProductDetailsEntityRes);
		 * 
		 * resultVO.setMsgDescr("Delete Sucessfully"); resultVO.setError(false); return
		 * resultVO; } else {
		 * 
		 * resultVO.setMsgDescr("Not Found"); resultVO.setError(false); return resultVO;
		 * }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		return resultVO;
	}

	@Override
	public ResultVO deleteCategoryDetails(long catCode) {
		ResultVO resultVO = new ResultVO();
		ProductCategotyEntity catagoryEntity = new ProductCategotyEntity();
		try {
			catagoryEntity = addCatagoryRepository.findByCatCodeAndIsActive(catCode, 0);

			if (catagoryEntity != null) {

				catagoryEntity.setIsActive(1);
				addCatagoryRepository.save(catagoryEntity);

				resultVO.setMsgDescr("Delete Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("Not Found");
				resultVO.setError(false);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<ProductSubCategoryPojo> getSubCategoryDetailsList(long catagoryId) {

		List<ProductSubCategoryEntity> subCatagoryEntityList = new ArrayList<ProductSubCategoryEntity>();
		List<ProductSubCategoryPojo> subCatagoryPojoList = new ArrayList<ProductSubCategoryPojo>();

		try {
			ProductCategotyEntity catagoryEntity = addCatagoryRepository.findByCatCodeAndIsActive(catagoryId, 0);
			subCatagoryEntityList = addSubCatagoryRepository.findByCatCodeAndIsActiveOrderByIdDesc(catagoryId, 0);
			for (ProductSubCategoryEntity productSubCategoryEntity : subCatagoryEntityList) {
				ProductSubCategoryPojo productSubCategoryPojo = new ProductSubCategoryPojo();
				productSubCategoryPojo = mapper.map(productSubCategoryEntity, ProductSubCategoryPojo.class);
				productSubCategoryPojo.setCatName(catagoryEntity.getCatName());
				subCatagoryPojoList.add(productSubCategoryPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subCatagoryPojoList;
	}

	@Override
	public ResultVO deleteSubCategoryDetails(long catSubCode) {
		ResultVO resultVO = new ResultVO();
		ProductSubCategoryEntity productSubCategoryEntity = new ProductSubCategoryEntity();
		try {
			productSubCategoryEntity = addSubCatagoryRepository.findBysubCatCodeAndIsActive(catSubCode, 0);

			if (productSubCategoryEntity != null) {

				productSubCategoryEntity.setIsActive(1);
				addSubCatagoryRepository.save(productSubCategoryEntity);

				resultVO.setMsgDescr("Delete Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("Not Found");
				resultVO.setError(false);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<AdminDetails> getUserDetails() {

		List<AdminUser> user = userRepo.findAll();
		List<AdminDetails> userDtlVO = mapper.map(user, List.class);

		return userDtlVO;
	}

	@Override
	public List<UserRegistrationPojo> getMemberDetails() {

		List<MemberUser> member = memberRepo.findAll();
	    String role = CurrentUserUtil.getRole();
	    LeoLogger.info("Logged-in User Role: {}", role);

	    // if role is CUSTOM, filter out members with ctype = Special
	    if ("custom".equalsIgnoreCase(role) || "cashier".equalsIgnoreCase(role) || "sales".equalsIgnoreCase(role)) {
	        List<MemberUser> filteredList = new ArrayList<>();
	        for (MemberUser cust : member) {
	            if (!"Special".equalsIgnoreCase(cust.getCtype())) {
	                filteredList.add(cust);
	            }
	        }
	        member = filteredList;
	    }
		List<UserRegistrationPojo> memberDtlVO = mapper.map(member, List.class);

		return memberDtlVO;
	}

	@Override
	public ResultVO addvendor(VendorPojo vendorPojo) {
		LeoLogger.info("CatagoryServiceImpl---addvendor---vendorPojo" + vendorPojo.toString());
		ResultVO resultVO = new ResultVO();
		VendorEntity vendorEntity = new VendorEntity();

		VendorEntity vendorEntityRes = vendorRepo.findByVenderCodeAndIsActive(vendorPojo.getVenderCode(), 0);
		try {
			if (vendorEntityRes == null) {
				vendorEntity.setVendorName(vendorPojo.getVendorName());
				vendorEntity.setAddress(vendorPojo.getAddress());
				vendorEntity.setEmail(vendorPojo.getEmail());
				vendorEntity.setPhoneNumber(vendorPojo.getPhoneNumber());
				vendorEntity.setIsActive(0);
				vendorEntity.setRemark(vendorPojo.getRemark());
				vendorRepo.save(vendorEntity);

				resultVO.setMsgDescr("Save Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				vendorEntityRes.setVendorName(vendorPojo.getVendorName());
				vendorEntityRes.setAddress(vendorPojo.getAddress());
				vendorEntityRes.setEmail(vendorPojo.getEmail());
				vendorEntityRes.setPhoneNumber(vendorPojo.getPhoneNumber());
				vendorEntityRes.setRemark(vendorPojo.getRemark());
				vendorRepo.save(vendorEntityRes);

				resultVO.setMsgDescr("Update Sucessfully");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO deleteVendorDetails(long id) {
		ResultVO resultVO = new ResultVO();
		VendorEntity vendorEntity = new VendorEntity();
		try {
			vendorEntity = vendorRepo.findByVenderCodeAndIsActive(id, 0);

			if (vendorEntity != null) {

				vendorEntity.setIsActive(1);
				vendorRepo.save(vendorEntity);

				resultVO.setMsgDescr("Delete Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("Not Found");
				resultVO.setError(false);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VendorPojo> getVendorList() {

		List<VendorEntity> vendorEntityList = new ArrayList<VendorEntity>();
		List<VendorPojo> vendorPojoList = new ArrayList<VendorPojo>();

		try {
			vendorEntityList = vendorRepo.findByIsActiveOrderByIdDesc(0);
			vendorPojoList = mapper.map(vendorEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vendorPojoList;
	}

	@Override
	public List<ProductDetailsPojo> getProductDetails(long subCatagoryId) {
		List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();
		try {
			// productDetailsEntityList =
			// productDetailsRepository.findBySubCatCodeAndIsActiveOrderByProductIdDesc(subCatagoryId,
			// 0);
			// productDetailsPojoList = mapper.map(productDetailsEntityList, List.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetailsPojoList;
	}

	@Override
	public ResultVO addProductBarCodeDetails(BarCodePojo barCodePojo) {
		ResultVO resultVO = new ResultVO();
		BarCodeEntity barCodeEntity = new BarCodeEntity();
		try {

			BarCodeEntity barCodeEntityRes = barCodeRepo.findByBarCodeIdAndIsActive(barCodePojo.getBarCodeId(), 0);

			if (barCodeEntityRes == null) {

				barCodeEntity.setCatCode(barCodePojo.getCatCode());
				barCodeEntity.setSubCatCode(barCodePojo.getSubCatCode());
				barCodeEntity.setProductCode(barCodePojo.getProductCode());
				barCodeEntity.setBarCodeImg(barCodePojo.getBarCodeImg());
				barCodeEntity.setIsActive(0);
				barCodeRepo.save(barCodeEntity);

				resultVO.setMsgDescr("Save Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				barCodeEntityRes.setCatCode(barCodePojo.getCatCode());
				barCodeEntityRes.setSubCatCode(barCodePojo.getSubCatCode());
				barCodeEntityRes.setProductCode(barCodePojo.getProductCode());
				barCodeEntityRes.setBarCodeImg(barCodePojo.getBarCodeImg());
				barCodeRepo.save(barCodeEntityRes);

				resultVO.setMsgDescr("Update Sucessfully");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<BarCodePojo> getBarCodeList(long subCatagoryId) {
		List<BarCodeEntity> barCodeEntityList = new ArrayList<BarCodeEntity>();
		List<BarCodePojo> barCodePojoList = new ArrayList<BarCodePojo>();

		try {
			barCodeEntityList = barCodeRepo.findBySubCatCodeAndIsActiveOrderByBarCodeSrnoDesc(subCatagoryId, 0);
			for (BarCodeEntity barCodeEntity : barCodeEntityList) {
				BarCodePojo barCodePojo = new BarCodePojo();
				barCodePojo = mapper.map(barCodeEntity, BarCodePojo.class);
				String encode = Base64.getEncoder().encodeToString(barCodeEntity.getBarCodeImg());
				barCodePojo.setImg(encode);
				barCodePojoList.add(barCodePojo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return barCodePojoList;
	}

	@Override
	public ResultVO deleteBarCodeDetails(long barCode) {
		ResultVO resultVO = new ResultVO();
		try {

			BarCodeEntity barCodeEntityRes = barCodeRepo.findBybarCodeIdAndIsActive(barCode, 0);

			if (barCodeEntityRes != null) {

				barCodeEntityRes.setIsActive(1);
				barCodeRepo.save(barCodeEntityRes);

				resultVO.setMsgDescr("Delete Sucessfully");
				resultVO.setError(false);
				return resultVO;
			} else {

				resultVO.setMsgDescr("Not Found");
				resultVO.setError(false);
				return resultVO;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO updateProductDetails(ProductDetailsPojo productDetailsPojo,HttpServletRequest request) {
		LeoLogger.info("CatagoryServiceImpl---updateProductDetails---Printing  Here Product Object : "
				+ productDetailsPojo.toString());
		ResultVO resultVO = new ResultVO();
		try {

			ProductDetailsEntity productDetailsEntityRes = productDetailsRepository
					.findByProductId(productDetailsPojo.getProductId());

			if (productDetailsEntityRes != null) {
				LeoLogger.info("CatagoryServiceImpl--updateProductDetails----in not null");
				
				String auditMessage = buildUpdateProductAudit(productDetailsPojo, productDetailsEntityRes);

				productDetailsEntityRes.setname(productDetailsPojo.getname());
				productDetailsEntityRes.setcategory_id(productDetailsPojo.getcategory_id());
				productDetailsEntityRes.setsubcategory_id(productDetailsPojo.getsubcategory_id());
				productDetailsEntityRes.setprice(productDetailsPojo.getprice());
				productDetailsEntityRes.setrollprice(productDetailsPojo.getrollprice());
				productDetailsEntityRes.setcost(productDetailsPojo.getcost());
				productDetailsEntityRes.setcf1(productDetailsPojo.getcf1());
				productDetailsEntityRes.settrack_quantity(productDetailsPojo.gettrack_quantity());
				productDetailsEntityRes.settax_rate(productDetailsPojo.gettax_rate());
				
				productDetailsEntityRes.setQuantity(productDetailsPojo.getQuantity());

				System.out.println("******promotiion =========" + productDetailsPojo.getpromotion());
				productDetailsEntityRes.setpromotion(productDetailsPojo.getpromotion());
				MultipartFile file = productDetailsPojo.getUpdatedImage();
				Part filePart = request.getPart("updatedImage");
				
				System.out.println("===filePart  size======  "+filePart.getSize());

				if (filePart != null && filePart.getSize() > 0) {
					saveProductImage(file, productDetailsEntityRes);
				}

				productDetailsRepository.save(productDetailsEntityRes);

				resultVO.setMsgDescr("Product Updated Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				sysAuditService.setSysAudit(Action.PRODUCT_UPDATE, auditMessage);
				return resultVO;
			} else {
				resultVO.setMsgDescr("Not Found");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}
	
	
	public String buildUpdateProductAudit(ProductDetailsPojo newData, ProductDetailsEntity oldData) {
	    List<String> changes = new ArrayList<>();


	    if (!Objects.equals(oldData.getprice(), newData.getprice())) {
	        changes.add("Price: " + oldData.getprice() + " to " + newData.getprice());
	    }

	    if (!Objects.equals(oldData.getcost(), newData.getcost())) {
	        changes.add("Cost: " + oldData.getcost() + " to " + newData.getcost());
	    }
	    
	    if (!Objects.equals(oldData.getQuantity(), newData.getQuantity())) {
	        changes.add("Quantity: " + oldData.getQuantity() + " to " + newData.getQuantity());
	    }
	  
	 
	    String auditMsg = "Product[" + oldData.getProductId() + "] updated: " + String.join(", ", changes);
	    return auditMsg.length() > 255 ? auditMsg.substring(0, 255) : auditMsg;
	}

	@Override
	public List<ProductDetailsPojo> getItemCodeList(String itemCode) {
		ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();
		/*
		 * try { productDetailsEntity =
		 * productDetailsRepository.findByItemCodeAndIsActive(itemCode, 0);
		 * ProductCategotyEntity catagoryEntity =
		 * addCatagoryRepository.findByCatCodeAndIsActive(productDetailsEntity.
		 * getcategory_id(), 0); ProductSubCategoryEntity subCatagoryEntity =
		 * addSubCatagoryRepository.findBySubCatCodeAndIsActive(productDetailsEntity.
		 * getsubcategory_id(), 0); ProductDetailsPojo productDetailsPojo = new
		 * ProductDetailsPojo(); productDetailsPojo = mapper.map(productDetailsEntity,
		 * ProductDetailsPojo.class);
		 * productDetailsPojo.setcategory_id(catagoryEntity.getCatCode());
		 * productDetailsPojo.setsubcategory_id(subCatagoryEntity.getSubCatCode());
		 * productDetailsPojoList.add(productDetailsPojo);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		return productDetailsPojoList;
	}

	@Override
	public List<ProductDetailsPojo> getItemNameList(String productName) {
		ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();
		/*
		 * try { productDetailsEntity =
		 * productDetailsRepository.findByProductNameAndIsActive(productName, 0);
		 * ProductCategotyEntity catagoryEntity =
		 * addCatagoryRepository.findByCatCodeAndIsActive(productDetailsEntity.
		 * getcategory_id(), 0); ProductSubCategoryEntity subCatagoryEntity =
		 * addSubCatagoryRepository.findBySubCatCodeAndIsActive(productDetailsEntity.
		 * getsubcategory_id(), 0); ProductDetailsPojo productDetailsPojo = new
		 * ProductDetailsPojo(); productDetailsPojo = mapper.map(productDetailsEntity,
		 * ProductDetailsPojo.class);
		 * productDetailsPojo.setcategory_id(catagoryEntity.getCatCode());
		 * productDetailsPojo.setsubcategory_id(subCatagoryEntity.getSubCatCode());
		 * productDetailsPojoList.add(productDetailsPojo);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		return productDetailsPojoList;
	}

	@Override
	public ResultVO updateMemberDetails(UserRegistrationPojo memberDetailsPojo) {
		LeoLogger.info("CatagoryServiceImpl---updateMemberDetails---Printing here Member Object : "
				+ memberDetailsPojo.toString());
		ResultVO resultVO = new ResultVO();
		try {

			// MemberUser memberDetailsEntityRes =
			// memberUserRepo.findByUsername(memberDetailsPojo.getUserName());
			MemberUser memberDetailsEntityRes = memberUserRepo.findById(memberDetailsPojo.getId());
			LeoLogger.info("CatagoryServiceImpl---updateMemberDetails--- " + memberDetailsPojo.getId());

			if (memberDetailsEntityRes != null) {
				LeoLogger.info("CatagoryServiceImpl---updateMemberDetails--- Member already exists and is not null");

				memberDetailsEntityRes.setName(memberDetailsPojo.getName());

				memberDetailsEntityRes.setEmail(memberDetailsPojo.getEmail());
				memberDetailsEntityRes.setSecondemail(memberDetailsPojo.getSecondemail());
				;

				memberDetailsEntityRes.setEmail(memberDetailsPojo.getFirstemail());

				memberDetailsEntityRes.setDOB(memberDetailsPojo.getDOB());
				memberDetailsEntityRes.setPhonemain(memberDetailsPojo.getPhonemain());
				memberDetailsEntityRes.setPhonealter(memberDetailsPojo.getPhonealter());
				memberDetailsEntityRes.setPhonewhatsapp(memberDetailsPojo.getPhonewhatsapp());
				memberDetailsEntityRes.setDeposit(memberDetailsPojo.getDeposit());
				memberDetailsEntityRes.setCreditfacility(memberDetailsPojo.getCreditfacility());
				memberDetailsEntityRes.setCompany(memberDetailsPojo.getCompany());
				memberDetailsEntityRes.setChild1age(memberDetailsPojo.getChild1age());
				memberDetailsEntityRes.setChild2(memberDetailsPojo.getChild2());
				memberDetailsEntityRes.setChild2age(memberDetailsPojo.getChild1age());
				memberDetailsEntityRes.setCtype(memberDetailsPojo.getCtype());
				memberDetailsEntityRes.setPincode(memberDetailsPojo.getPincode());
				memberDetailsEntityRes.setAddress(memberDetailsPojo.getAddress());
				memberDetailsEntityRes.setPricegroup(memberDetailsPojo.getPricegroup());
				memberDetailsEntityRes.setUserName(memberDetailsPojo.getUserName());
				memberDetailsEntityRes.setThreshholdamount(memberDetailsPojo.getThreshholdamount());
				memberDetailsEntityRes.setThreshholddays(memberDetailsPojo.getThreshholddays());
				memberDetailsEntityRes.setContactname(memberDetailsPojo.getContactname());
				memberDetailsEntityRes.setEmail(memberDetailsPojo.getEmail());
				// LeoLogger.info("CatagoryServiceImpl---updateMemberDetails---"+memberDetailsPojo.getBlocked());
				if (memberDetailsPojo.getBlocked() == 0) {
					memberDetailsEntityRes.setBlocked(0);
				} else {
					memberDetailsEntityRes.setBlocked(1);
				}

				memberUserRepo.save(memberDetailsEntityRes);

				resultVO.setMsgDescr("Member Updated Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				resultVO.setMsgDescr("Not Found");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<PlanPojo> getPlanList() {
		List<PlanEntity> planEntityList = new ArrayList<PlanEntity>();
		List<PlanPojo> planPojoList = new ArrayList<PlanPojo>();

		try {
			planEntityList = planRepo.findAll();
			
			planPojoList = mapper.map(planEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return planPojoList;
	}

	@Override
	public ResultVO purchasePlanDetails(PlanPojo planPojo) {
		LeoLogger.info(
				"CatagoryServiceImpl---purchasePlanDetails---Printing here Member Object : " + planPojo.toString());
		ResultVO resultVO = new ResultVO();

		try {

			MemberUser memberDetailsEntityRes = memberUserRepo.findByUsername(planPojo.getUserName());

			if (memberDetailsEntityRes != null) {
				LeoLogger.info("CatagoryServiceImpl---purchasePlanDetails--- Member already exists and is not null");

				memberDetailsEntityRes.setPlan_id(planPojo.getId());
				memberDetailsEntityRes.setBpts(planPojo.getBooksPts());
				memberDetailsEntityRes.setTgpts(planPojo.getGamestoysPts());

				memberUserRepo.save(memberDetailsEntityRes);

				resultVO.setMsgDescr("Plan Added Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				resultVO.setMsgDescr("Not Found");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	@Override
	public PlanPojo getplanbyPlanid(String planId) {
		PlanPojo planPojo = new PlanPojo();

		return planPojo;
	}

	@Override
	public List<ProductDetailsPojo> getProductDetailsAll() {
		List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();

		try {
			productDetailsEntityList = productDetailsRepo.findAll();
			productDetailsPojoList = mapper.map(productDetailsEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productDetailsPojoList;
	}

	@Override
	public List<UnitPojo> getUnitsAll() {

		List<UnitEntity> unitEntityList = new ArrayList<UnitEntity>();
		List<UnitPojo> unitPojoList = new ArrayList<UnitPojo>();

		try {
			unitEntityList = unitRepo.findAll();
			unitPojoList = mapper.map(unitEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return unitPojoList;
	}

	@Override
	public List<SalesPercentagePojo> getSalesPercent() {

		List<SalesPercentageEntity> salesPercentageEntityList = new ArrayList<SalesPercentageEntity>();
		List<SalesPercentagePojo> salesPercentagePojoList = new ArrayList<SalesPercentagePojo>();

		try {
			salesPercentageEntityList = salespercentRepo.findAll();
			salesPercentagePojoList = mapper.map(salesPercentageEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return salesPercentagePojoList;
	}

	@Override
	public List<ProductDetailsPojo> getProductDetailsPageNew(ModelMap modelMap, int page, int pageSize) {

		List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();
		Pageable paging = PageRequest.of(page, pageSize);
		Page<ProductDetailsEntity> product;
		// sale = salesRepo.findAll(paging);
		product = productDetailsRepository.findAllByOrderByProductIdAsc(paging);
		System.out.println("page= " + page);
		System.out.println(product.getNumber());
		System.out.println(product.getNumberOfElements());
		System.out.println(product.getSize());
		System.out.println(product.getTotalElements());
		System.out.println(product.getTotalPages());
		System.out.println(product.hasNext());
		System.out.println(product.hasPrevious());
		// salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		productDetailsEntityList = product.getContent();
		modelMap.addAttribute("totalPages", product.getTotalPages());
		modelMap.addAttribute("totalRecords", product.getTotalElements());
		modelMap.addAttribute("currentRecords", product.getNumberOfElements());
		modelMap.addAttribute("previous", product.hasPrevious());
		modelMap.addAttribute("next", product.hasNext());
		try {
			LeoLogger.info("CatagoryServiceImpl---getProductDetailsList---in product");
			// productDetailsEntityList =
			// productDetailsRepository.findAllByOrderByProductIdAsc();
			for (ProductDetailsEntity productDetailsEntityRes : productDetailsEntityList) {
				// ProductDiscount productDiscount =
				// productDiscountRepo.findByItemCodeAndIsActive(productDetailsEntityRes.getcode(),
				// 0);
				// if(productDiscount ==null) {
				ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
				productDetailsPojo = mapper.map(productDetailsEntityRes, ProductDetailsPojo.class);
				productDetailsPojoList.add(productDetailsPojo);
				// }else {
				// ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
				// productDetailsPojo = mapper.map(productDetailsEntityRes,
				// ProductDetailsPojo.class);
				// productDetailsPojoList.add(productDetailsPojo);

				// }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetailsPojoList;
	}

	@Override
	public List<ProductDetailsPojo> getSearchProduct(String search) {
		List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();
		List<ProductDetailsPojo> productDetailsPojoList = new ArrayList<ProductDetailsPojo>();
		try {
			LeoLogger.info("CatagoryServiceImpl---getProductDetailsList---in product");
			productDetailsEntityList = productDetailsRepository
					.findByCodeContainingOrNameContainingOrCf1ContainingOrderByProductIdAsc(search, search, search);
			for (ProductDetailsEntity productDetailsEntityRes : productDetailsEntityList) {
				// ProductDiscount productDiscount =
				// productDiscountRepo.findByItemCodeAndIsActive(productDetailsEntityRes.getcode(),
				// 0);
				// if(productDiscount ==null) {
				ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
				productDetailsPojo = mapper.map(productDetailsEntityRes, ProductDetailsPojo.class);
				productDetailsPojoList.add(productDetailsPojo);
				// }else {
				// ProductDetailsPojo productDetailsPojo = new ProductDetailsPojo();
				// productDetailsPojo = mapper.map(productDetailsEntityRes,
				// ProductDetailsPojo.class);
				// productDetailsPojoList.add(productDetailsPojo);

				// }
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDetailsPojoList;
	}

	@Override
	public ResultVO addDeposit(UserRegistrationPojo memberDetailsPojo) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);
		LeoLogger.info("CatagoryServiceImpl---memberDetailsPojo" +memberDetailsPojo);
		
		MemberUser detailsEntity = memberUserRepo.findById(memberDetailsPojo.getId());
		LeoLogger.info("CatagoryServiceImpl---memberUserRepo" +detailsEntity);
		//ProductDetailsEntity detailsEntity = getProductById(productDetailsPojo.getProductId());
		if (detailsEntity != null) {
			detailsEntity.setDeposit(memberDetailsPojo.getDeposit());
			memberUserRepo.save(detailsEntity);
			resultVO.setError(false);
		}
		return resultVO;
	}

	@Override
	public ResultVO addProduct(ProductDetailsPojo productDetailsPojo) {
		ResultVO resultVO = new ResultVO();
		ProductDetailsEntity productDetailsEntity = new ProductDetailsEntity();
		ProductDiscount productDiscount = new ProductDiscount();
		try {

			List<ProductDetailsEntity> productDetailsEntityList = new ArrayList<ProductDetailsEntity>();

			

		

			// productDetailsEntity.setProductId(productDetailsPojo.getProductId());

			productDetailsEntity.setname(productDetailsPojo.getname());
			
			productDetailsEntity.setcategory_id(0);
		
			productDetailsEntity.setsubcategory_id(0);
			productDetailsEntity.setproduct_details(productDetailsPojo.getproduct_details());
			productDetailsEntity.setprice(productDetailsPojo.getprice());
			productDetailsEntity.setrollprice(productDetailsPojo.getrollprice());
			// productDetailsEntity.set(0)
			productDetailsEntity.setcost(productDetailsPojo.getcost());
			productDetailsEntity.setcf1(productDetailsPojo.getcf1());
			productDetailsEntity.settrack_quantity(0);
			productDetailsEntity.setpromotion(productDetailsPojo.getpromotion());

			productDetailsEntity.setstart_date(new Date());

			productDetailsEntity.setunit(productDetailsPojo.getunit());
			productDetailsEntity.setQuantity(BigDecimal.ZERO);
			productDetailsEntity.settax_rate(productDetailsPojo.gettax_rate());
			
			
		//	MultipartFile file = productDetailsPojo.getProductImage();
	
		//	saveProductImage(file, productDetailsEntity);
			

			productDetailsRepository.save(productDetailsEntity);

			productDiscount.setFromDate(new Date());
			productDiscountRepo.save(productDiscount);

			Long code = productDetailsEntity.getProductId();
			LeoLogger.info("CatagoryServiceImpl---getProductDetailsList---in product code" + code);
			productDetailsEntity.setcode(code.toString());
			productDetailsRepository.save(productDetailsEntity);

			resultVO.setMsgDescr("Product Saved Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

}
