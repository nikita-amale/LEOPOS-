/**
 * 
 */
package com.leonet.serviceimpl;

import java.util.ArrayList;


import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.common.entity.SalesEntity;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ProductRentalPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.RquoteItemPojo;
import com.leonet.common.pojo.RquotePojo;
import com.leonet.common.pojo.RsalePojo;
import com.leonet.common.pojo.RsalesItemPojo;
import com.leonet.common.pojo.SaleItemPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.repo.ProductCategotyRepo;
import com.leonet.entity.MemberUser;
import com.leonet.entity.ProductRentalEntity;
import com.leonet.entity.RquoteEntity;
import com.leonet.entity.RquoteItemEntity;
import com.leonet.entity.RsaleEntity;
import com.leonet.entity.RsalesItemEntity;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.ProductRentalRepo;
import com.leonet.repo.RquoteItemRepo;
import com.leonet.repo.RquoteRepo;
import com.leonet.repo.RsaleItemRepo;
import com.leonet.repo.RsaleRepo;
import com.leonet.service.RentalService;


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

				System.out.println("Member Pojo ....." + memberPojo.toString());

				ProductRentalEntity productRentalEntity = productRentalRepo.findByRproductId(additem.getProductId());

				if (productRentalEntity != null)
					unit_price = productRentalEntity.getRprice();
				quantity = Long.parseLong(additem.getQuantity());

				rquoteEntity.setNote(additem.getNote());

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (unit_price * 0.125 * quantity);

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
				rquoteItemEntity.setItem_tax(additem.getPrice().doubleValue() * 0.125);
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
	public List<RquotePojo> getRentalQuotesList() {
		List<RquoteEntity> rquoteEntityList = new ArrayList<RquoteEntity>();
		List<RquotePojo> rquotePojoList = new ArrayList<RquotePojo>();
		try {
			System.out.println("in Rental Quotes View");
			rquoteEntityList = rquoteRepo.findAll();
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
				rsaleEntity.setMember_id(rquoteEntity.getMember_id());
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
			rsaleEntityList = rsaleRepo.findAll();
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
		
		List<RsalesItemEntity> rsalesItemEntityList =  rsaleItemRepo.findByRsaleid(Long.parseLong(rsaleId));
		List<RsalesItemPojo>   rsalesItemPojoList = new ArrayList<RsalesItemPojo>();
		try {
			System.out.println("in Rental Get RSales Items by RsId");
			
			for (RsalesItemEntity rsalesItemEntityRes : rsalesItemEntityList) {
				RsalesItemPojo rsaleItemPojo = new RsalesItemPojo();
				rsaleItemPojo = mapper.map(rsalesItemEntityRes, RsalesItemPojo.class);
				rsalesItemPojoList.add(rsaleItemPojo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rsalesItemPojoList ....." + rsalesItemPojoList.toString());
		return rsalesItemPojoList;
	}

	
}
