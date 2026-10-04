/**
 * 
 */
package com.leonet.serviceimpl;

import java.io.File;

import java.io.IOException;
import java.math.BigDecimal;


import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.leonet.common.entity.FinancialTransactionEntity;
import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.AddPaymentReqPojo;
import com.leonet.common.pojo.PaymentPojo;
import com.leonet.common.pojo.ProductRentalPojo;
import com.leonet.common.pojo.QuotesPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.RquoteItemPojo;
import com.leonet.common.pojo.RquotePojo;
import com.leonet.common.pojo.RsalePojo;
import com.leonet.common.pojo.RsalesItemPojo;
import com.leonet.common.pojo.SaleItemPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.repo.ProductCategotyRepo;
import com.leonet.entity.MemberUser;
import com.leonet.entity.PaymentEntity;
import com.leonet.entity.ProductRentalEntity;
import com.leonet.entity.QuotesEntity;
import com.leonet.entity.QuotesItemEntity;
import com.leonet.entity.RquoteEntity;
import com.leonet.entity.RquoteItemEntity;
import com.leonet.entity.RsaleEntity;
import com.leonet.entity.RsalesItemEntity;

import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.PaymentRepo;
import com.leonet.repo.ProductRentalRepo;
import com.leonet.repo.RquoteItemRepo;
import com.leonet.repo.RquoteRepo;
import com.leonet.repo.RsaleItemRepo;
import com.leonet.repo.RsaleRepo;
import com.leonet.service.RentalService;
import org.springframework.web.multipart.MultipartFile;




@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	ProductRentalRepo productRentalRepo;

	@Autowired
	Mapper mapper;

	@Autowired
	ProductCategotyRepo addCatagoryRepository;

	@Autowired
	MemberUserRepo memberUserRepo;

	@Autowired
	RquoteRepo rquoteRepo;

	@Autowired
	RsaleRepo rsaleRepo;

	@Autowired
	RquoteItemRepo rquoteItemRepo;

	@Autowired
	RsaleItemRepo rsaleItemRepo;

	@Autowired
	PaymentRepo paymentRepo;
	private static final String FOLDER_PATH = "images/";

	@Override
	public ResultVO addProductRental(ProductRentalPojo productRentalPojo) {
		ResultVO resultVO = new ResultVO();
		ProductRentalEntity productRentalEntity = new ProductRentalEntity();

		try {

			// List<ProductRentalEntity> productRentalEntityList = new
			// ArrayList<ProductRentalEntity>();

			// productDetailsEntity.setProductId(productDetailsPojo.getProductId());
			productRentalEntity.setName(productRentalPojo.getName());
			productRentalEntity.setCategory_id(productRentalPojo.getCategory_id());
			productRentalEntity.setSubcategory_id(productRentalPojo.getSubcategory_id());
			productRentalEntity.setProduct_details(productRentalPojo.getProduct_details());
			productRentalEntity.setRprice(productRentalPojo.getRprice());
			// productDetailsEntity.set(0)
			productRentalEntity.setCost(productRentalPojo.getCost());
			productRentalEntity.setBrand(productRentalPojo.getBrand());
			productRentalEntity.setRproductId(productRentalPojo.getRproductId());
			productRentalEntity.setQuantity(0);
			productRentalEntity.setCreation_date(new Date());
			productRentalEntity.setTax_rate(12);
			MultipartFile file = productRentalPojo.getProductImage();

			saveProductImage(file, productRentalEntity);

			productRentalRepo.save(productRentalEntity);

			resultVO.setMsgDescr("Rental Product Saved Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}
	public void saveProductImage(MultipartFile file, ProductRentalEntity productRentalEntity) throws IOException {

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

			productRentalEntity.setProductFileName(fileName + System.currentTimeMillis());
			productRentalEntity.setProductFilePath(filePath);

			// Upload the file to the specified directory
			// Path targetPath = Path.of(filePath);
			Path targetPath = Paths.get(filePath);
			System.out.println("**********file path ========" + targetPath);

			Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("******************file upload success ************ ");
			System.out.println("**************file upload success ***********");
		}

	}
	
	@Override
	public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
		ProductRentalEntity fileData = productRentalRepo.findByProductFileName(fileName);
		System.out.println("fileData  =" + fileData);
		if (Objects.nonNull(fileData)) {
			String filePath = fileData.getProductFilePath();
			byte[] images = Files.readAllBytes(new File(filePath).toPath());
			return images;
		}
		return null;

	}

	@Override
	public List<ProductRentalPojo> getProductRentalList() {
		List<ProductRentalEntity> productRentalEntityList = new ArrayList<ProductRentalEntity>();
		List<ProductRentalPojo> productRentalPojoList = new ArrayList<ProductRentalPojo>();
		try {
			System.out.println("in Rental product");
			productRentalEntityList = productRentalRepo.findAll();
			for (ProductRentalEntity productRentalEntityRes : productRentalEntityList) {
				// ProductDiscount productDiscount =
				// productDiscountRepo.findByItemCodeAndIsActive(productDetailsEntityRes.getcode(),
				// 0);
				// if(productDiscount ==null) {
				ProductRentalPojo productRentalPojo = new ProductRentalPojo();
				productRentalPojo = mapper.map(productRentalEntityRes, ProductRentalPojo.class);
				productRentalPojoList.add(productRentalPojo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productRentalPojoList;
	}

	@Override
	public ResultVO deleteProductRental(String productCode) {
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
	public List<ProductRentalPojo> getProductRental(long subCatagoryId) {
		// List<ProductRentalEntity> productRentalEntityList = new
		// ArrayList<ProductRentalEntity>();
		List<ProductRentalPojo> productRentalPojoList = new ArrayList<ProductRentalPojo>();
		try {
			// productDetailsEntityList =
			// productDetailsRepository.findBySubCatCodeAndIsActiveOrderByProductIdDesc(subCatagoryId,
			// 0);
			// productDetailsPojoList = mapper.map(productDetailsEntityList, List.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productRentalPojoList;
	}

	@Override
	public ResultVO updateProductRental(ProductRentalPojo productRentalPojo) {
		System.out.println("Printing Here Product Object : " + productRentalPojo.toString());
		ResultVO resultVO = new ResultVO();
		try {

			/*
			 * ProductDetailsEntity productDetailsEntityRes =
			 * productDetailsRepository.findByProductId(productDetailsPojo.getProductId());
			 * 
			 * 
			 * if (productDetailsEntityRes != null) { System.out.println("in not null");
			 * 
			 * 
			 * productDetailsEntityRes.setname(productDetailsPojo.getname());
			 * productDetailsEntityRes.setcategory_id(productDetailsPojo.getcategory_id());
			 * productDetailsEntityRes.setsubcategory_id(productDetailsPojo.
			 * getsubcategory_id());
			 * productDetailsEntityRes.setprice(productDetailsPojo.getprice());
			 * productDetailsEntityRes.setcost(productDetailsPojo.getcost());
			 * productDetailsEntityRes.setcf1(productDetailsPojo.getcf1());
			 * productDetailsEntityRes.settrack_quantity(productDetailsPojo.
			 * gettrack_quantity());
			 * productDetailsEntityRes.settax_rate(productDetailsPojo.gettax_rate());
			 * 
			 * 
			 * productDetailsRepository.save(productDetailsEntityRes);
			 * 
			 * 
			 * resultVO.setMsgDescr("Rental Product Updated Sucessfully");
			 * resultVO.setMsgCode("001"); resultVO.setError(false); return resultVO; } else
			 * { resultVO.setMsgDescr("Not Found"); resultVO.setMsgCode("002");
			 * resultVO.setError(true); return resultVO;
			 * 
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductRentalPojo> getProductRentalAll() {
		List<ProductRentalEntity> productRentalEntityList = new ArrayList<ProductRentalEntity>();
		List<ProductRentalPojo> productRentalPojoList = new ArrayList<ProductRentalPojo>();

		try {
			productRentalEntityList = productRentalRepo.findAll();
			productRentalPojoList = mapper.map(productRentalEntityList, List.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productRentalPojoList;
	}

	@Override
	public ResultVO addRentalquote(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			RquoteEntity rquoteEntity = new RquoteEntity();
			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				rquoteEntity.setDate(new Date());
				rquoteEntity.setMember_id(memberPojo.getId());
				rquoteEntity.setMember_name(memberPojo.getName());
				rquoteEntity.setDeliverydate(additem.getDeliverydate());
				rquoteEntity.setPickupdate(additem.getPickupdate());

				System.out.println("Member Pojo ....." + memberPojo.toString());

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());
				System.out.println("in Rental Quotes "+(additem.getQuantity()));

				if (productRentalEntity != null)
					unit_price = productRentalEntity.getRprice();
				quantity = Long.parseLong(additem.getQuantity());

				rquoteEntity.setNote(additem.getNote());
			

				total = total + (unit_price * quantity);
				if (additem.getTax().equalsIgnoreCase("YES")) {
					tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				} else {
					tax_rate = 0.0;
				}

				rquoteEntity.setTotal_discount(0);
				rquoteEntity.setTotal_tax(tax_rate);
				// saleEntity.setUser_id();
			}
			grand_total = total + tax_rate;

			rquoteEntity.setOrder_tax(0);
			rquoteEntity.setProduct_tax(tax_rate);
			rquoteEntity.setOrder_discount(0);
			rquoteEntity.setTotal(total);
			rquoteEntity.setGrand_total(grand_total);
			rquoteEntity.setStatus("Available");
			RquoteEntity rqEnt = rquoteRepo.save(rquoteEntity);

			// this loop is for Quotes breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				double tax = 0;

				tax = (productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity())) * .125;

				RquoteItemEntity rquoteItemEntity = new RquoteItemEntity();
				rquoteItemEntity.setRquoteid(rqEnt.getRquoteId());
				rquoteItemEntity.setProduct_id(additem.getProductId());
				rquoteItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				rquoteItemEntity.setItem_tax(additem.getPrice() * 0.125);
				rquoteItemEntity.setGst("12.5");
				rquoteItemEntity.setItem_discount(0);
				rquoteItemEntity.setProduct_code(additem.getProductId().toString());
				rquoteItemEntity.setProduct_name(additem.getProductName());
				rquoteItemEntity.setReal_unit_price(productRentalEntity.getRprice());
				rquoteItemEntity.setSubtotal(productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity()));
				rquoteItemEntity.setTax(Double.toString(tax));
				rquoteItemEntity.setProductFileName(productRentalEntity.getProductFileName());
				rquoteItemEntity.setProductFilePath(productRentalEntity.getProductFilePath());

				rquoteItemRepo.save(rquoteItemEntity);

				// NO need to Subtract Quantities here in Quote

				/*
				 * productDetailsEnt .setQuantity(productDetailsEnt.getQuantity() -
				 * Integer.parseInt(additem.getQuantity()));
				 * productDetailsRepo.save(productDetailsEnt);
				 */
			}

			resultVO.setMsgDescr("Quote Rental Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<RquotePojo> getRentalQuotesList() {
		List<RquoteEntity> rquoteEntityList = new ArrayList<RquoteEntity>();
		List<RquotePojo> rquotePojoList = new ArrayList<RquotePojo>();
		try {
			System.out.println("in Rental Quotes View");
			rquoteEntityList = rquoteRepo.findAllByOrderByRquoteIdDesc();
			for (RquoteEntity rquoteEntityRes : rquoteEntityList) {

				RquotePojo rquotePojo = new RquotePojo();
				rquotePojo = mapper.map(rquoteEntityRes, RquotePojo.class);
				rquotePojoList.add(rquotePojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rquotePojoList;
	}

	@Override
	public List<RquoteItemPojo> getRentalQuotesItemList() {
		List<RquoteItemEntity> rquoteItemEntityList = new ArrayList<RquoteItemEntity>();
		List<RquoteItemPojo> rquoteItemPojoList = new ArrayList<RquoteItemPojo>();
		try {
			System.out.println("in Rental Quotes Item View");
			rquoteItemEntityList = rquoteItemRepo.findAll();
			for (RquoteItemEntity rquoteItemEntityRes : rquoteItemEntityList) {

				RquoteItemPojo rquoteItemPojo = new RquoteItemPojo();
				rquoteItemPojo = mapper.map(rquoteItemEntityRes, RquoteItemPojo.class);
				rquoteItemPojoList.add(rquoteItemPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rquoteItemPojoList;
	}

	@Override
	public ResultVO convertToSale(String rquoteId) {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();

		try {

			RquoteEntity rquoteEntity = rquoteRepo.findByRquoteId(Long.parseLong(rquoteId));
			RsaleEntity rsaleEntity = new RsaleEntity();

			if (rquoteEntity != null)

			{

				rquoteEntity.setStatus("Converted");
				rsaleEntity.setDate(new Date());
				rsaleEntity.setMemberid(rquoteEntity.getMember_id());
				rsaleEntity.setMember_name(rquoteEntity.getMember_name());
				rsaleEntity.setOrder_tax(0);
				rsaleEntity.setProduct_tax(rquoteEntity.getProduct_tax());
				rsaleEntity.setTotal_tax(rquoteEntity.getTotal_tax());
				rsaleEntity.setOrder_discount(0);
				rsaleEntity.setTotal(rquoteEntity.getTotal());
				rsaleEntity.setGrand_total(rquoteEntity.getGrand_total());
				rsaleEntity.setRquoteId(String.valueOf(rquoteEntity.getRquoteId()));
				rsaleEntity.setPaid(0);
				rsaleEntity.setPayment_status(1);
				rsaleEntity.setDeliverydate(rquoteEntity.getDeliverydate());
				rsaleEntity.setPickupdate(rquoteEntity.getPickupdate());
				rsaleEntity.setNote(rquoteEntity.getNote());
				RsaleEntity rsEnt = rsaleRepo.save(rsaleEntity);

				List<RquoteItemEntity> rquoteItemEntity = rquoteItemRepo.findByRquoteid(Long.parseLong(rquoteId));
				System.out.println("rquoteItemEntity ....." + rquoteItemEntity.toString());

				// this loop is for Sale to Quotes breakdown

				for (RquoteItemEntity rqitems : rquoteItemEntity) {

					RsalesItemEntity rsalesItemEntity = new RsalesItemEntity();

					rsalesItemEntity.setRsaleid(rsEnt.getRsaleId());
					rsalesItemEntity.setProduct_id(rqitems.getProduct_id());
					rsalesItemEntity.setQuantity(rqitems.getQuantity());
					rsalesItemEntity.setItem_tax(rqitems.getItem_tax());
					rsalesItemEntity.setGst("12.5");
					rsalesItemEntity.setItem_discount(0);
					rsalesItemEntity.setProduct_code(rqitems.getProduct_code());
					rsalesItemEntity.setProduct_name(rqitems.getProduct_name());
					rsalesItemEntity.setReal_unit_price(rqitems.getReal_unit_price());
					rsalesItemEntity.setSubtotal(rqitems.getSubtotal());
					rsalesItemEntity.setTax(rqitems.getTax());
					rsalesItemEntity.setProductFileName(rqitems.getProductFileName());
					rsalesItemEntity.setProductFilePath(rqitems.getProductFilePath());

					rsaleItemRepo.save(rsalesItemEntity);

				}

				resultVO.setMsgDescr("Quote converted to Sale Sucessfully !");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	@Override
	public List<RsalePojo> getRentalSaleList() {
		List<RsaleEntity> rsaleEntityList = new ArrayList<RsaleEntity>();
		List<RsalePojo> rsalePojoList = new ArrayList<RsalePojo>();
		try {
			System.out.println("in Rental Sales View");
			rsaleEntityList = rsaleRepo.findAllByOrderByRsaleIdDesc();
			for (RsaleEntity rsaleEntityRes : rsaleEntityList) {

				RsalePojo rsalePojo = new RsalePojo();
				rsalePojo = mapper.map(rsaleEntityRes, RsalePojo.class);
				rsalePojoList.add(rsalePojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsalePojoList;
	}

	@Override
	public List<RquoteItemPojo> getRentalQuotesItembyRqId(String rquoteId) {

		List<RquoteItemEntity> rquoteItemEntityList = rquoteItemRepo.findByRquoteid(Long.parseLong(rquoteId));
		List<RquoteItemPojo> rquoteItemPojoList = new ArrayList<RquoteItemPojo>();
		try {
			System.out.println("in Rental Get REntal Items by RqId");
			for (RquoteItemEntity rquoteItemEntityRes : rquoteItemEntityList) {

				RquoteItemPojo rquoteItemPojo = new RquoteItemPojo();
				rquoteItemPojo = mapper.map(rquoteItemEntityRes, RquoteItemPojo.class);
				rquoteItemPojoList.add(rquoteItemPojo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rquoteItemPojoList ....." + rquoteItemPojoList.toString());
		return rquoteItemPojoList;
	}

	public List<RsalesItemPojo> getRentalSalesItembyRsId(String rsaleId) {

		List<RsalesItemEntity> rsalesItemEntityList = rsaleItemRepo.findByRsaleid(Long.parseLong(rsaleId));
		List<RsalesItemPojo> rsalesItemPojoList = new ArrayList<RsalesItemPojo>();
		try {
			System.out.println("in Rental Get RSales Items by RsId");

			for (RsalesItemEntity rsalesItemEntityRes : rsalesItemEntityList) {
				RsalesItemPojo rsaleItemPojo = new RsalesItemPojo();
				rsaleItemPojo = mapper.map(rsalesItemEntityRes, RsalesItemPojo.class);
				rsalesItemPojoList.add(rsaleItemPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rsalesItemPojoList ....." + rsalesItemPojoList.toString());
		return rsalesItemPojoList;
	}

	@Override
	public ResultVO updateProductRentalDetails(ProductRentalPojo productRentalPojo,HttpServletRequest request) {
		System.out.println("Printing Here Product Object : " + productRentalPojo.toString());
		ResultVO resultVO = new ResultVO();
		try {

			ProductRentalEntity productRentalEntityRes = productRentalRepo
					.findByRproductId(productRentalPojo.getRproductId());

			if (productRentalEntityRes != null) {
				System.out.println("in not null");

				productRentalEntityRes.setName(productRentalPojo.getName());
				productRentalEntityRes.setBrand(productRentalPojo.getBrand());
				productRentalEntityRes.setRprice(productRentalPojo.getRprice());
				productRentalEntityRes.setCost(productRentalPojo.getCost());
				MultipartFile file = productRentalPojo.getUpdatedImage();
				Part filePart = request.getPart("updatedImage");
				
				System.out.println("===filePart  size======  "+filePart.getSize());

				if (filePart != null && filePart.getSize() > 0) {
					saveProductImage(file, productRentalEntityRes);
				}


				productRentalRepo.save(productRentalEntityRes);

				resultVO.setMsgDescr("Product Updated Sucessfully");
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
	public ResultVO addPayment(AddPaymentReqPojo addPaymentReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			RsaleEntity rsaleEntity = rsaleRepo.findByRsaleId(addPaymentReqPojos.getSaleId());
			PaymentEntity paymentEntity = new PaymentEntity();

			double paid_total = rsaleEntity.getPaid();

			BigDecimal bd_paid_total = new BigDecimal(0.0);

			paid_total = paid_total + addPaymentReqPojos.getAmount();
			bd_paid_total = new BigDecimal(paid_total);
			bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
			
			BigDecimal payamount = new BigDecimal(0.0);
			payamount = new BigDecimal(addPaymentReqPojos.getAmount());
			payamount = payamount.setScale(2, RoundingMode.HALF_UP);

			BigDecimal bd_balance = new BigDecimal(0.0);
			double balance = rsaleEntity.getGrand_total() - rsaleEntity.getPaid();
			
			bd_balance = new BigDecimal(balance);
			bd_balance = bd_balance.setScale(2, RoundingMode.HALF_UP);
			balance=bd_balance.doubleValue();
			rsaleEntity.setPaid(bd_paid_total.doubleValue());
			if (payamount.doubleValue() >= bd_balance.doubleValue()) {
				rsaleEntity.setPayment_status(0);
				
			}
			
			rsaleRepo.save(rsaleEntity);

			// Make Payment Entry in Payment table

			paymentEntity.setstatus("Paid");
			paymentEntity.setMemberid(rsaleEntity.getMemberid());
			paymentEntity.setMember_name(rsaleEntity.getMember_name());
			paymentEntity.setPtype(addPaymentReqPojos.getPtype());
			paymentEntity.setPref(addPaymentReqPojos.getPref());
			paymentEntity.setRsaleId(addPaymentReqPojos.getSaleId());
			paymentEntity.setGrand_total(addPaymentReqPojos.getAmount());
			paymentEntity.setDate(new Date());
			paymentRepo.save(paymentEntity);

			resultVO.setMsgDescr("Payment Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<RsalePojo> getRsalesListbyMemberId(long memberId) {
		List<RsaleEntity> rsalesEntityList = rsaleRepo.findAllByMemberid(memberId);
		List<RsalePojo> rsalesPojoList = new ArrayList<RsalePojo>();
		try {
			System.out.println("in  getRSalesListbyMemberId");
			// salesEntityList = salesRepo.findAllByMemberid(memberId);
			for (RsaleEntity salesEntityRes : rsalesEntityList) {

				RsalePojo rsalePojo = new RsalePojo();
				rsalePojo = mapper.map(salesEntityRes, RsalePojo.class);
				rsalesPojoList.add(rsalePojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsalesPojoList;
	}

	@Override
	public List<PaymentPojo> getPaymentListbyMemberId(long memberId) {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllByMemberid(memberId);
		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {
			System.out.println("in  getRSalesListbyMemberId");
			// salesEntityList = salesRepo.findAllByMemberid(memberId);
			for (PaymentEntity paymentEntityRes : paymentEntityList) {

				PaymentPojo paymentPojo = new PaymentPojo();
				paymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
				paymentPojoList.add(paymentPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public double caltoatl(long memberId) {
		List<RsaleEntity> rsalesEntity = rsaleRepo.findAllByMemberid(memberId);
		double grand_total = 0.0;

		if (rsalesEntity != null)
			for (RsaleEntity rsalesEnt : rsalesEntity) {
				grand_total = grand_total + rsalesEnt.getGrand_total();

			}
		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
		return bd_grand_total.doubleValue();
	}

	@Override
	public double paymenttoatl(long memberId) {
		List<PaymentEntity> paymetEntity = paymentRepo.findAllByMemberid(memberId);
		double grand_total = 0.0;

		if (paymetEntity != null)
			for (PaymentEntity paymentEnt : paymetEntity) {
				grand_total = grand_total + paymentEnt.getGrand_total();

			}
		BigDecimal bd_grand_total = new BigDecimal(grand_total);
		bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

		return bd_grand_total.doubleValue();

	}

	@Override
	public double rsaletoatl(long memberId) {
		List<RsaleEntity> rsalesEntity = rsaleRepo.findAllByMemberid(memberId);
		double grand_total = 0.0, paid = 0.0, total = 0.0;

		if (rsalesEntity != null)
			for (RsaleEntity rsalesEnt : rsalesEntity) {
				grand_total = grand_total + rsalesEnt.getGrand_total();
				paid = paid + rsalesEnt.getPaid();

				total = grand_total - paid;

			}
		BigDecimal bd_total = new BigDecimal(total);
		bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);

		return bd_total.doubleValue();
	}

	@Override
	public ResultVO addRentalsale(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			RsaleEntity rsaleEntity = new RsaleEntity();
			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				rsaleEntity.setDate(new Date());
				rsaleEntity.setMemberid(memberPojo.getId());
				rsaleEntity.setMember_name(memberPojo.getName());
				rsaleEntity.setCustomeraddress(memberPojo.getAddress());
				rsaleEntity.setPhonemain(memberPojo.getPhonemain());
				rsaleEntity.setPincode(memberPojo.getPincode());

				System.out.println("Member Pojo ....." + memberPojo.toString());

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				if (productRentalEntity != null)
					unit_price = productRentalEntity.getRprice();
				quantity = Long.parseLong(additem.getQuantity());

				rsaleEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);
				if (additem.getTax().equalsIgnoreCase("YES")) {
					tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				} else {
					tax_rate = 0.0;
				}

				rsaleEntity.setTotal_discount(0);
				rsaleEntity.setTotal_tax(tax_rate);
				// saleEntity.setUser_id();
			}
			grand_total = total + tax_rate;

			rsaleEntity.setOrder_tax(0);
			rsaleEntity.setProduct_tax(tax_rate);
			rsaleEntity.setOrder_discount(0);
			rsaleEntity.setTotal(total);
			rsaleEntity.setGrand_total(grand_total);
			rsaleEntity.setPayment_status(1);
			RsaleEntity rqEnt = rsaleRepo.save(rsaleEntity);

			// this loop is for Quotes breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				double tax = 0;

				tax = (productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity())) * .125;

				RsalesItemEntity rsaleItemEntity = new RsalesItemEntity();
				rsaleItemEntity.setRsaleid(rqEnt.getRsaleId());
				rsaleItemEntity.setProduct_id(additem.getProductId());
				rsaleItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				rsaleItemEntity.setItem_tax(additem.getPrice() * 0.125);
				rsaleItemEntity.setGst("12.5");
				rsaleItemEntity.setItem_discount(0);
				rsaleItemEntity.setProduct_code(additem.getProductId().toString());
				rsaleItemEntity.setProduct_name(additem.getProductName());
				rsaleItemEntity.setReal_unit_price(productRentalEntity.getRprice());
				rsaleItemEntity.setSubtotal(productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity()));
				rsaleItemEntity.setTax(Double.toString(tax));
				rsaleItemEntity.setProductFileName(productRentalEntity.getProductFileName());
				rsaleItemEntity.setProductFilePath(productRentalEntity.getProductFilePath());

				rsaleItemRepo.save(rsaleItemEntity);

				// NO need to Subtract Quantities here in Quote

				/*
				 * productDetailsEnt .setQuantity(productDetailsEnt.getQuantity() -
				 * Integer.parseInt(additem.getQuantity()));
				 * productDetailsRepo.save(productDetailsEnt);
				 */
			}

			resultVO.setMsgDescr("Sales Rental Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public UserRegistrationPojo getMemberByMemberid(long memberid) {
		MemberUser memberEntity = memberUserRepo.findById(memberid);
		UserRegistrationPojo memberPojo = new UserRegistrationPojo();
		memberPojo = mapper.map(memberEntity, UserRegistrationPojo.class);
		return memberPojo;
	}

	@Override
	public List<PaymentPojo> getPaymentListbySaleId(long SaleId) {
		List<PaymentEntity> paymentEntityList = paymentRepo.findAllByrsaleIdOrderByIdDesc(SaleId);

		List<PaymentPojo> paymentPojoList = new ArrayList<PaymentPojo>();
		try {

			for (PaymentEntity paymentEntityRes : paymentEntityList) {
				// if (paymentEntityRes.getBulkid() == 0) {

				PaymentPojo PaymentPojo = new PaymentPojo();
				PaymentPojo = mapper.map(paymentEntityRes, PaymentPojo.class);
				paymentPojoList.add(PaymentPojo);

				// }

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paymentPojoList;
	}

	@Override
	public RquotePojo getquotebyquotesId(String id) {
		RquoteEntity quoteEntity = new RquoteEntity();
		quoteEntity = rquoteRepo.findByRquoteId(Long.valueOf(id));
		//LeoLogger.info("SaleServiceImpl---getquotebyquotesId---" + quoteEntity.toString());
		RquotePojo quotesPojo = new RquotePojo();
		quotesPojo = mapper.map(quoteEntity, RquotePojo.class);
		return quotesPojo;
	}

	@Override
	public ResultVO updateRentalQuotes(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {
		

			Long quotesId = 0L;
			BigDecimal Old_sale_grand_total = new BigDecimal(0.0);
			BigDecimal New_sale_grand_total = new BigDecimal(0.0);
			BigDecimal Diff_amount = new BigDecimal(0.0);
			RquoteEntity rquoteEntity = new RquoteEntity();

			List<RquoteItemEntity> quotesItemEntityList = new ArrayList<RquoteItemEntity>();

			// Get Sales Entity and Sales Items Entity using the sale id received
			for (AddItemReqPojo additem : addItemReqPojos) {
				System.out.println(additem);
				quotesId = additem.getSaleId();
			}
		
			if (quotesId != null) {
				rquoteEntity = rquoteRepo.findByRquoteId(quotesId);
				quotesItemEntityList = rquoteItemRepo.findByRquoteid(quotesId);


				Old_sale_grand_total = new BigDecimal(rquoteEntity.getGrand_total());

			}

			// Add quantity of products in productdetails for Sales Items received from the

			rquoteItemRepo.deleteAll(quotesItemEntityList);
			// quotesItemRepo.deleteAllByquotesid(quotesId);
			// Update Existing Sale Entity instead of making a new one

			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {
				

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				rquoteEntity.setDate(new Date());
				rquoteEntity.setMember_id(memberPojo.getId());
				rquoteEntity.setMember_name(memberPojo.getName());
				rquoteEntity.setDeliverydate(additem.getDeliverydate());
				rquoteEntity.setPickupdate(additem.getPickupdate());

				System.out.println("Member Pojo ....." + memberPojo.toString());

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				if (productRentalEntity != null)
					unit_price = productRentalEntity.getRprice();
				quantity = Long.parseLong(additem.getQuantity());

				rquoteEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);
				if (additem.getTax().equalsIgnoreCase("YES")) {
					tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				} else {
					tax_rate = 0.0;
				}

				rquoteEntity.setTotal_discount(0);
				rquoteEntity.setTotal_tax(tax_rate);
				// saleEntity.setUser_id();
			}
			grand_total = total + tax_rate;

			rquoteEntity.setOrder_tax(0);
			rquoteEntity.setProduct_tax(tax_rate);
			rquoteEntity.setOrder_discount(0);
			rquoteEntity.setTotal(total);
			rquoteEntity.setGrand_total(grand_total);
			rquoteEntity.setStatus("Available");
			RquoteEntity rqEnt = rquoteRepo.save(rquoteEntity);

			// this loop is for Quotes breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				double tax = 0;

				tax = (productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity())) * .125;

				RquoteItemEntity rquoteItemEntity = new RquoteItemEntity();
				rquoteItemEntity.setRquoteid(rqEnt.getRquoteId());
				rquoteItemEntity.setProduct_id(additem.getProductId());
				rquoteItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				rquoteItemEntity.setItem_tax(additem.getPrice() * 0.125);
				rquoteItemEntity.setGst("12.5");
				rquoteItemEntity.setItem_discount(0);
				rquoteItemEntity.setProduct_code(additem.getProductId().toString());
				rquoteItemEntity.setProduct_name(additem.getProductName());
				rquoteItemEntity.setReal_unit_price(productRentalEntity.getRprice());
				rquoteItemEntity.setSubtotal(productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity()));
				rquoteItemEntity.setTax(Double.toString(tax));

				rquoteItemRepo.save(rquoteItemEntity);

				// NO need to Subtract Quantities here in Quote

				/*
				 * productDetailsEnt .setQuantity(productDetailsEnt.getQuantity() -
				 * Integer.parseInt(additem.getQuantity()));
				 * productDetailsRepo.save(productDetailsEnt);
				 */
			}

			resultVO.setMsgDescr("Quote Rental Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

	@Override
	public RsalePojo getsalebyrsaleId(String id) {
		RsaleEntity quoteEntity = new RsaleEntity();
		quoteEntity = rsaleRepo.findByRsaleId(Long.valueOf(id));
		//LeoLogger.info("SaleServiceImpl---getquotebyquotesId---" + quoteEntity.toString());
		RsalePojo salesPojo = new RsalePojo();
		salesPojo = mapper.map(quoteEntity, RsalePojo.class);
		return salesPojo;
	}

	@Override
	public ResultVO updateRentalSales(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {
		

			Long quotesId = 0L;
			BigDecimal Old_sale_grand_total = new BigDecimal(0.0);
			BigDecimal New_sale_grand_total = new BigDecimal(0.0);
			BigDecimal Diff_amount = new BigDecimal(0.0);
			RsaleEntity rsaleEntity = new RsaleEntity();

			List<RsalesItemEntity> quotesItemEntityList = new ArrayList<RsalesItemEntity>();

			// Get Sales Entity and Sales Items Entity using the sale id received
			for (AddItemReqPojo additem : addItemReqPojos) {
				System.out.println(additem);
				quotesId = additem.getSaleId();
			}
		
			if (quotesId != null) {
				rsaleEntity = rsaleRepo.findByRsaleId(quotesId);
				quotesItemEntityList = rsaleItemRepo.findByRsaleid(quotesId);


				Old_sale_grand_total = new BigDecimal(rsaleEntity.getGrand_total());

			}

			// Add quantity of products in productdetails for Sales Items received from the

			rsaleItemRepo.deleteAll(quotesItemEntityList);
			// quotesItemRepo.deleteAllByquotesid(quotesId);
			// Update Existing Sale Entity instead of making a new one

			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;

			// this loop is for sales total
			for (AddItemReqPojo additem : addItemReqPojos) {

				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());
				rsaleEntity.setDate(new Date());
				rsaleEntity.setMemberid(memberPojo.getId());
				rsaleEntity.setMember_name(memberPojo.getName());
				rsaleEntity.setCustomeraddress(memberPojo.getAddress());
				rsaleEntity.setPhonemain(memberPojo.getPhonemain());
				rsaleEntity.setPincode(memberPojo.getPincode());

				System.out.println("Member Pojo ....." + memberPojo.toString());

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				if (productRentalEntity != null)
					unit_price = productRentalEntity.getRprice();
				quantity = Long.parseLong(additem.getQuantity());

				rsaleEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);
				if (additem.getTax().equalsIgnoreCase("YES")) {
					tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				} else {
					tax_rate = 0.0;
				}

				rsaleEntity.setTotal_discount(0);
				rsaleEntity.setTotal_tax(tax_rate);
				// saleEntity.setUser_id();
			}
			grand_total = total + tax_rate;

			rsaleEntity.setOrder_tax(0);
			rsaleEntity.setProduct_tax(tax_rate);
			rsaleEntity.setOrder_discount(0);
			rsaleEntity.setTotal(total);
			rsaleEntity.setGrand_total(grand_total);
			rsaleEntity.setPayment_status(1);
			RsaleEntity rqEnt = rsaleRepo.save(rsaleEntity);

			// this loop is for Quotes breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				double tax = 0;

				tax = (productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity())) * .125;

				RsalesItemEntity rsaleItemEntity = new RsalesItemEntity();
				rsaleItemEntity.setRsaleid(rqEnt.getRsaleId());
				rsaleItemEntity.setProduct_id(additem.getProductId());
				rsaleItemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				rsaleItemEntity.setItem_tax(additem.getPrice() * 0.125);
				rsaleItemEntity.setGst("12.5");
				rsaleItemEntity.setItem_discount(0);
				rsaleItemEntity.setProduct_code(additem.getProductId().toString());
				rsaleItemEntity.setProduct_name(additem.getProductName());
				rsaleItemEntity.setReal_unit_price(productRentalEntity.getRprice());
				rsaleItemEntity.setSubtotal(productRentalEntity.getRprice() * Long.parseLong(additem.getQuantity()));
				rsaleItemEntity.setTax(Double.toString(tax));

				rsaleItemRepo.save(rsaleItemEntity);

				// NO need to Subtract Quantities here in Quote

				/*
				 * productDetailsEnt .setQuantity(productDetailsEnt.getQuantity() -
				 * Integer.parseInt(additem.getQuantity()));
				 * productDetailsRepo.save(productDetailsEnt);
				 */
			}

			resultVO.setMsgDescr("Sales Rental Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}

}
