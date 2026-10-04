package com.leonet.serviceimpl;

import java.math.BigDecimal;


import java.math.RoundingMode;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ImportPurchasePojo;
import com.leonet.common.pojo.PurchaseFilePojo;
import com.leonet.common.pojo.PurchaseItemOnFilePojo;
import com.leonet.common.pojo.PurchaseItemPojo;
import com.leonet.common.pojo.PurchaseOnFilePojo;
import com.leonet.common.pojo.PurchasePojo;

import com.leonet.common.pojo.ResultVO;
import com.leonet.common.repo.ProductDetailsRepository;
import com.leonet.entity.ImportPurchaseEntity;
import com.leonet.entity.MemberUser;
import com.leonet.entity.PurchaseEntity;
import com.leonet.entity.PurchaseFileEntity;
import com.leonet.entity.PurchaseItemEntity;
import com.leonet.entity.PurchaseItemOnFileEntity;
import com.leonet.entity.PurchaseOnFileEntity;
import com.leonet.entity.QuotesEntity;
import com.leonet.entity.QuotesItemEntity;
import com.leonet.repo.ImportPurchaseRepo;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.ProductDetailsRepo;
import com.leonet.repo.PurchaseFileRepo;
import com.leonet.repo.PurchaseItemOnFileRepo;
import com.leonet.repo.PurchaseItemRepo;
import com.leonet.repo.PurchaseOnFileRepo;
import com.leonet.repo.PurchaseRepo;
import com.leonet.repo.QuotesItemRepo;
import com.leonet.repo.QuotesRepo;
import com.leonet.service.ImportPurchaseService;
import com.leonet.service.ProductService;
import com.leonet.util.LeoLogger;

@Service
public class ImportPurchaseServiceImpl implements ImportPurchaseService {
	@Autowired
	ImportPurchaseRepo importpurchaseRepo;

	@Autowired
	PurchaseRepo purchaseRepo;
	
	@Autowired
	PurchaseFileRepo purchasefileRepo;


	@Autowired
	PurchaseOnFileRepo purchaseonfileRepo;
	

	@Autowired
	PurchaseItemOnFileRepo purchaseitemonfileRepo;
	

	@Autowired
	PurchaseItemRepo purchaseitemRepo;

	@Autowired
	ProductDetailsRepo productDetailsRepo;
	
	@Autowired
	MemberUserRepo memberUserRepo;
	
	@Autowired
	QuotesRepo quotesRepo;

	@Autowired
	QuotesItemRepo quotesItemRepo;

	@Autowired
	Mapper mapper;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	ProductDetailsRepository productDetailsRepository;
	

	@Override
	public ResultVO savePurchaseBeforeApply(ImportPurchasePojo importpurchasePojo) {
		ResultVO resultVO = new ResultVO();
		ImportPurchaseEntity importpurchaseEntity = new ImportPurchaseEntity();
		ProductDetailsEntity productDetailsEntityList = new ProductDetailsEntity();
		productDetailsEntityList = productDetailsRepository.findByProductId(importpurchasePojo.getProductId());
		LeoLogger.info("Import Purchase ServiceImpl ---savePurchaseBeforeApply productname" +importpurchasePojo.getProductName());
		LeoLogger.info("Import Purchase ServiceImpl ---savePurchaseBeforeApply price" +productDetailsEntityList);

		try {
			LeoLogger.info("Import Purchase ServiceImpl ---savePurchaseBeforeApply");

			importpurchaseEntity.setQty(importpurchasePojo.getQty());
			importpurchaseEntity.setUsdollar(importpurchasePojo.getUsdollar());
			importpurchaseEntity.setBzdprice(importpurchasePojo.getUsdollar());
			importpurchaseEntity.setPercentagecost(importpurchasePojo.getPercentagecost());
			importpurchaseEntity.setProductName(importpurchasePojo.getProductName());
			importpurchaseEntity.setCost(importpurchasePojo.getCost());
			importpurchaseEntity.setUnitcost(importpurchasePojo.getUnitcost());
			importpurchaseEntity.setSellingpercentage(importpurchasePojo.getSellingpercentage());
			importpurchaseEntity.setBzdprice(importpurchasePojo.getBzdprice());
			importpurchaseEntity.setSellingprice(importpurchasePojo.getSellingprice());
			importpurchaseEntity.setProductId(importpurchasePojo.getProductId());
			importpurchaseEntity.setSupplier(importpurchasePojo.getSupplier());
			importpurchaseEntity.setOldprice(productDetailsEntityList.getprice());
			importpurchaseEntity.setMpn(productDetailsEntityList.getcf1());
			importpurchaseEntity.setApplied(0);
		
			

			importpurchaseRepo.save(importpurchaseEntity);

			resultVO.setMsgDescr("Saved Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}

	@Override
	public ResultVO savePurchaseAftereApply() {
		LeoLogger.info("ImportPurchaseServiceImpl---savePurchaseAftereApply");
		ResultVO resultVO = new ResultVO();
		// PurchaseEntity purchaseEntity = new PurchaseEntity();
		// LeoLogger.info("importPurchasePojo==" + importpurchasePojo.toString());
		
		try {
			List<ImportPurchasePojo> importpurchasePojo = getImportPurchasePojoFromEntity();
			PurchaseEntity purchaseEntity = new PurchaseEntity();
			ImportPurchaseEntity importPurchaseEntity = new ImportPurchaseEntity();
			//LeoLogger.info("ImportPurchaseServiceImpl---savePurchaseAftereApply---ImportPurchasePojo outside....." + importpurchasePojo.toString());
			double grand_total = 0, total = 0, unit_price = 0, quantity = 0,price=0,price_total=0,p_total=0;
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_price_grand_total = new BigDecimal(0.0);
			
			LeoLogger.info("ImportPurchaseServiceImpl--savePurchaseAftereApply----Unit Price Before >>>>>> " + unit_price + ".......Quantity  Before>>>>> " + quantity);
			for (ImportPurchasePojo importpurchase : importpurchasePojo) {
				//LeoLogger.info("ImportPurchaseServiceImpl---ImportPurchasePojo ....." + importpurchase.toString());
			//	if (importpurchase != null)
				unit_price = importpurchase.getUnitcost();
				quantity = importpurchase.getQty();
				LeoLogger.info("ImportPurchaseServiceImpl---savePurchaseAftereApply---Unit Price After >>>>>> " + unit_price + ".......Quantity After >>>>> " + quantity);
				total = total + (unit_price * quantity);
				LeoLogger.info("ImportPurchaseServiceImpl---savePurchaseAftereApply---Total >>>>>> " + total);
				purchaseEntity.setSupplier(importpurchase.getSupplier());
				
				price = importpurchase.getSellingprice().doubleValue();
				price_total=price_total+(price*quantity);
				LeoLogger.info("ImportPurchaseServiceImpl---savePurchaseAftereApply---price_total >>>>>> " + price_total);
				
				
			}
			grand_total = total;
			bd_grand_total = new BigDecimal(grand_total);
			bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
			
			p_total = price_total;
			bd_price_grand_total = new BigDecimal(p_total);
			bd_price_grand_total = bd_price_grand_total.setScale(2, RoundingMode.HALF_UP);
			LeoLogger.info("ImportPurchaseServiceImpl---savePurchaseAftereApply---p_total >>>>>> " + p_total);
			LeoLogger.info("ImportPurchaseServiceImpl---savePurchaseAftereApply---bd_price_grand_total >>>>>> " + bd_price_grand_total);

			purchaseEntity.setBalance(0);
			purchaseEntity.setGrand_total(bd_grand_total.doubleValue());
			purchaseEntity.setPaid(1);
			purchaseEntity.getUser_id();
			purchaseEntity.setDate(new Date());
			purchaseEntity.setPricegrandtotal(bd_price_grand_total.doubleValue());
			
			
		
			//purchaseRepo.save(purchaseEntity)

			purchaseEntity = purchaseRepo.save(purchaseEntity);

			for (ImportPurchasePojo importpurchase : importpurchasePojo) {
			
				importPurchaseEntity = importpurchaseRepo.findBypurchaseId(importpurchase.getPurchaseId());
				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(importpurchase.getProductId());
				
				BigDecimal avgcost = new BigDecimal(0.0);
				avgcost = new BigDecimal(importpurchase.getUnitcost()).add(new BigDecimal(productDetailsEnt.getcost()));
				avgcost = avgcost.divide(new BigDecimal(2.0));
				avgcost = avgcost.setScale(2, RoundingMode.HALF_UP);
				
				if(importpurchase.getUnitcost()<productDetailsEnt.getcost()) {
					productDetailsEnt.setcost(avgcost.floatValue());	
				}else {
					productDetailsEnt.setcost(importpurchase.getUnitcost());
				}
				if (importpurchase.getSellingprice().compareTo(productDetailsEnt.getprice()) > 0) {
				 	productDetailsEnt.setprice(importpurchase.getSellingprice());
				 
				}
				
			//	BigDecimal avgprice = new BigDecimal(0.0);
			//	avgprice = new BigDecimal(importpurchase.getSellingprice()).add(new BigDecimal(productDetailsEnt.getprice()));
			//	avgprice = avgprice.divide(new BigDecimal(2.0));
			//	avgprice = avgprice.setScale(2, RoundingMode.HALF_UP);
				
				// Update Product Quantities & Average price & Cost
				//productDetailsEnt.setprice(Float.parseFloat(avgprice.toString()));
				//productDetailsEnt.setcost(Float.parseFloat(avgcost.toString()));
				productDetailsEnt.setQuantity(productDetailsEnt.getQuantity().add(BigDecimal.valueOf(importpurchase.getQty())));
		
				// Set import purchase row to applied
				if(importPurchaseEntity !=null)
				{
					importPurchaseEntity.setApplied(1);
					//importPurchaseEntity.set
					importpurchaseRepo.save(importPurchaseEntity);
					
				}
				                           
				PurchaseItemEntity purchaseitemEntity = new PurchaseItemEntity();
				purchaseitemEntity.setProduct_id(importpurchase.getProductId());
				purchaseitemEntity.setQuantity(importpurchase.getQty());
				purchaseitemEntity.setPurchaseId(purchaseEntity.getPurchaseId());
				purchaseitemEntity.setProduct_name(productDetailsEnt.getname());
				purchaseitemEntity.setMpn(productDetailsEnt.getcf1());
				purchaseitemEntity.setTotal(bd_grand_total.doubleValue());
				purchaseitemEntity.setWarehouse_id(productDetailsEnt.getwarehouse());
				purchaseitemEntity.setSellingprice(importpurchase.getSellingprice());
				purchaseitemEntity.setUnitcost(importpurchase.getUnitcost());
				purchaseitemRepo.save(purchaseitemEntity);
				productDetailsRepo.save(productDetailsEnt);

			}
			resultVO.setMsgDescr("Purchase Applied Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;

	}

	@Override
	public List<ImportPurchasePojo> getImportPurchaseList() {
		List<ImportPurchaseEntity> importPurchaseEntityList = new ArrayList<ImportPurchaseEntity>();
		List<ImportPurchasePojo> importPurchasePojoList = new ArrayList<ImportPurchasePojo>();
		try {
			LeoLogger.info("ImportPurchaseServiceImpl---getImportPurchaseList---in Import Purchase");
			importPurchaseEntityList = importpurchaseRepo.findAll();
			for (ImportPurchaseEntity importPurchaseEntityRes : importPurchaseEntityList) {

				ImportPurchasePojo importPurchasePojo = new ImportPurchasePojo();
				importPurchasePojo = mapper.map(importPurchaseEntityRes, ImportPurchasePojo.class);
				importPurchasePojoList.add(importPurchasePojo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return importPurchasePojoList;
	}

	@Override
	public List<PurchasePojo> getPurchaseList() {
		List<PurchaseEntity> purchaseEntityList = new ArrayList<PurchaseEntity>();
		List<PurchasePojo> purchasePojoList = new ArrayList<PurchasePojo>();
		try {
			LeoLogger.info("ImportPurchaseServiceImpl---getPurchaseList---in Purvhase");
			purchaseEntityList = purchaseRepo.findAllByOrderByPurchaseIdDesc();
			for (PurchaseEntity purchaseEntityEntityRes : purchaseEntityList) {

				PurchasePojo purchasePojo = new PurchasePojo();
				purchasePojo = mapper.map(purchaseEntityEntityRes, PurchasePojo.class);
				purchasePojo.setCreatedBy(purchaseEntityEntityRes.getCreatedBy());
				purchasePojoList.add(purchasePojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return purchasePojoList;
	}
	@Override
	public List<PurchaseItemPojo> getPurchaseitembypurchaseId(String purchaseId) {
		List<PurchaseItemEntity> purchaseItemEntityList = purchaseitemRepo.findByPurchaseId(Long.parseLong(purchaseId));
		List<PurchaseItemPojo> purchaseItemPojoList = new ArrayList<PurchaseItemPojo>();
		try {
			LeoLogger.info("ImportPurchaseServiceImpl---getPurchaseitembypurchaseId---in purchase get Items by purchaseId");

			for (PurchaseItemEntity purchaseItemEntityRes : purchaseItemEntityList) {
				PurchaseItemPojo purchaseItemPojo = new PurchaseItemPojo();
				purchaseItemPojo = mapper.map(purchaseItemEntityRes, PurchaseItemPojo.class);
				purchaseItemPojoList.add(purchaseItemPojo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LeoLogger.info("ImportPurchaseServiceImpl---getPurchaseitembypurchaseId---purchaseItemPojoList ....." + purchaseItemPojoList.toString());
		return purchaseItemPojoList;
	}

	@Override
	public ResultVO updateimportPurchase(ImportPurchasePojo importpurchasePojo) {
		ResultVO resultVO = new ResultVO();
		try {

			ImportPurchaseEntity importPurchaseEntityRes = importpurchaseRepo.findBypurchaseId(importpurchasePojo.getPurchaseId());
				

		if (importPurchaseEntityRes != null) {

			boolean isPresent = Optional.of(importpurchasePojo.getProductId()).isPresent();
			String productName = importPurchaseEntityRes.getProductName();
			if (isPresent) {
				ProductDetailsEntity detailsEntity = productService.getProductById(importpurchasePojo.getProductId());
				productName = Optional.of(detailsEntity.getname()).get();
				importpurchasePojo.setMpn(detailsEntity.getcf1());
			}
			
			importpurchasePojo.setProductName(productName);
			importpurchasePojo.setDate(importPurchaseEntityRes.getDate());
			importpurchasePojo.setApplied(importPurchaseEntityRes.getApplied());
			importpurchasePojo.setSupplier(importPurchaseEntityRes.getSupplier());
			importpurchasePojo.setOldprice(importPurchaseEntityRes.getOldprice());
			
			
			LeoLogger.info("ImportPurchaseServiceImpl---updateimportPurchase---Printing Here Product Object : " + importpurchasePojo.toString());
			
			importPurchaseEntityRes = mapper.map(importpurchasePojo, ImportPurchaseEntity.class);
			
			LeoLogger.info("ImportPurchaseServiceImpl---updateimportPurchase---after mapping response:"+importPurchaseEntityRes);
			
			importpurchaseRepo.save(importPurchaseEntityRes);
			
			resultVO.setMsgDescr("Import Purchase Updated Sucessfully");
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
	public ResultVO deleteImportPurchase(long id) {
		ResultVO resultVO = new ResultVO();
		ImportPurchaseEntity importPurchaseEntity = new ImportPurchaseEntity();
		try {
			importPurchaseEntity = importpurchaseRepo.findBypurchaseId(id);
			System.out.println(id);

			if (importPurchaseEntity  != null) {

				importPurchaseEntity.setApplied(2);
				importpurchaseRepo.save(importPurchaseEntity );

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
	public ImportPurchasePojo getImportPurchaseById(Long id) {
		ImportPurchaseEntity importPurchaseEntity = importpurchaseRepo.findBypurchaseId(id);
		ImportPurchasePojo importPurchasePojo = mapper.map(importPurchaseEntity, ImportPurchasePojo.class);
		return importPurchasePojo;
	}

	@Override
	public List<ImportPurchaseEntity> findAllByApplied() {
		return importpurchaseRepo.findAllByApplied(0);
	}
	
	private List<ImportPurchasePojo> getImportPurchasePojoFromEntity(){
		List<ImportPurchaseEntity> importPurchaseEntities = findAllByApplied();
		return importPurchaseEntities.stream()
				.map(importPurchasePojo -> mapper.map(importPurchasePojo, ImportPurchasePojo.class))
				.collect(Collectors.toList());
	}
	


	@Override
	public ResultVO addPurchase(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			PurchaseEntity purchaseEntity = new PurchaseEntity();
			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0,pprice=0,price_total=0,p_total=0;;
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_price_grand_total = new BigDecimal(0.0);
			

			for (AddItemReqPojo additem : addItemReqPojos) {


				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				if (productDetailsPojo != null)
					unit_price = additem.getPrice().doubleValue();
				quantity = Long.parseLong(additem.getQuantity());
				
				BigDecimal avgcost = new BigDecimal(0.0);
				avgcost = additem.getPrice().add(new BigDecimal(productDetailsPojo.getcost()));
				avgcost = avgcost.divide(new BigDecimal(2.0));
				avgcost = avgcost.setScale(2, RoundingMode.HALF_UP);
				LeoLogger.info("ImportPurchaseServiceImpl---addPurchase---add.price"+additem.getPrice());
				LeoLogger.info("ImportPurchaseServiceImpl---addPurchase---product cost"+productDetailsPojo.getcost());
				if(additem.getPrice().doubleValue()<productDetailsPojo.getcost()) {
					productDetailsPojo.setcost(avgcost.floatValue());	
					LeoLogger.info("ImportPurchaseServiceImpl---addPurchase---if"+avgcost.floatValue());
				}else {
					productDetailsPojo.setcost(additem.getPrice().floatValue());
					LeoLogger.info("ImportPurchaseServiceImpl---addPurchase---else"+additem.getPrice());
				}
				

			
				//purchaseEntity.s(additem.getNote());

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				purchaseEntity.setGrand_total(additem.getSubtotal().doubleValue());
				purchaseEntity.setSupplier(additem.getUnitname());
				
				

			}
			grand_total=total; 
			bd_grand_total = new BigDecimal(grand_total);
			bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

			purchaseEntity.setBalance(0);
			purchaseEntity.setGrand_total(bd_grand_total.doubleValue());
		
			purchaseEntity.setPaid(1);
			purchaseEntity.getUser_id();
			purchaseEntity.setDate(new Date());
			
			
			PurchaseEntity purhaseenty = purchaseRepo.save(purchaseEntity);
			
		

			List<PurchaseItemEntity> purchaseItemList = new ArrayList<>();

			for (AddItemReqPojo additem : addItemReqPojos) {
		

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());
			    BigDecimal addQuantity = new BigDecimal(additem.getQuantity());
			    productDetailsEnt.setQuantity(productDetailsEnt.getQuantity().add(addQuantity));
				

				BigDecimal price = productDetailsEnt.getprice();
				BigDecimal qty = new BigDecimal(additem.getQuantity());
				BigDecimal taxRate = new BigDecimal("0.125");

				BigDecimal tax = price.multiply(qty).multiply(taxRate);
				double subtotalValue = additem.getSubtotal().doubleValue();
				BigDecimal subtotalBigDecimal = new BigDecimal(Double.toString(subtotalValue));
				

			
				PurchaseItemEntity purchaseitemEntity = new PurchaseItemEntity();
				purchaseitemEntity.setProduct_id(additem.getProductId());
				purchaseitemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				purchaseitemEntity.setPurchaseId(purchaseEntity.getPurchaseId());
				purchaseitemEntity.setProduct_name(productDetailsEnt.getname());
				purchaseitemEntity.setMpn(productDetailsEnt.getcf1());
				purchaseitemEntity.setTotal(bd_grand_total.doubleValue());
				purchaseitemEntity.setWarehouse_id(productDetailsEnt.getwarehouse());
				purchaseitemEntity.setSellingprice(subtotalBigDecimal);
				purchaseitemEntity.setUnitcost(additem.getPrice().floatValue());
				
				purchaseitemRepo.save(purchaseitemEntity);
				productDetailsRepo.save(productDetailsEnt);
				
				pprice = purchaseitemEntity.getSellingprice().doubleValue();
				price_total=price_total+(pprice*purchaseitemEntity.getQuantity());
				
				
			}
			p_total = price_total;
			bd_price_grand_total = new BigDecimal(p_total);
			bd_price_grand_total = bd_price_grand_total.setScale(2, RoundingMode.HALF_UP);
			
			purchaseEntity.setPricegrandtotal(bd_price_grand_total.doubleValue());
			//To set the file status to 1 after the purchase is made.
			List<PurchaseItemOnFileEntity> purchaseItemOnFile= purchaseitemonfileRepo.findAll();
			for (PurchaseItemOnFileEntity purchaseitemfile : purchaseItemOnFile) {
				purchaseitemfile.setFile(1);
				purchaseitemonfileRepo.save(purchaseitemfile);
			}

			
			resultVO.setMsgDescr("Purchase Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO savefile(PurchaseFilePojo purchasefilePojo) {
		System.out.println("purchasefilePojo   " + purchasefilePojo.toString());
		ResultVO resultVO = new ResultVO();
		//List<ImportPurchasePojo> importpurchasePojo = getImportPurchasePojoFromEntity();
		List<PurchaseEntity> purchasePojo = purchaseRepo.findAll();
		PurchaseFileEntity purchasefileEntity = new PurchaseFileEntity();
		try {
			for (PurchaseEntity purchase : purchasePojo) {
				purchasefileEntity.setPurchaseid(purchase.getPurchaseId()+1);
				}

			purchasefileEntity.setTitle(purchasefilePojo.getTitle());
			
			purchasefileEntity.setFile(purchasefilePojo.getFile());

			purchasefileRepo.save(purchasefileEntity);
			resultVO.setMsgDescr(" Saved Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return resultVO;

	}

	@Override
	public ResultVO uploadfile(PurchaseFilePojo purchasefilePojo) {
		System.out.println("purchasefilePojo   " + purchasefilePojo.toString());
		ResultVO resultVO = new ResultVO();
		List<PurchaseEntity> purchasePojo = purchaseRepo.findAll();
		LeoLogger.info("ImportPurchaseServiceImpl---updateimportPurchase---after mapping response:"+purchasePojo);
		PurchaseFileEntity purchasefileEntity = new PurchaseFileEntity();
		try {
			for (PurchaseEntity purchase : purchasePojo) {
			purchasefileEntity.setPurchaseid(purchase.getPurchaseId()+1);
			}

			purchasefileEntity.setTitle(purchasefilePojo.getTitle());
			
			purchasefileEntity.setFile(purchasefilePojo.getFile());

			purchasefileRepo.save(purchasefileEntity);
			resultVO.setMsgDescr(" Saved Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO addPurchaseBeforSubmit(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();

		try {

			PurchaseOnFileEntity purchaseEntity = new PurchaseOnFileEntity();
			double grand_total = 0, tax_rate = 0, total = 0, quantity = 0, unit_price = 0;
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			

			for (AddItemReqPojo additem : addItemReqPojos) {


				ProductDetailsEntity productDetailsPojo = productDetailsRepo.findByProductId(additem.getProductId());

				if (productDetailsPojo != null)
					unit_price = additem.getPrice().doubleValue();
				quantity = Long.parseLong(additem.getQuantity());
				
				/*BigDecimal avgcost = new BigDecimal(0.0);
				avgcost = new BigDecimal(additem.getPrice()).add(new BigDecimal(productDetailsPojo.getcost()));
				avgcost = avgcost.divide(new BigDecimal(2.0));
				avgcost = avgcost.setScale(2, RoundingMode.HALF_UP);
				
				if(additem.getPrice()<productDetailsPojo.getcost()) {
					productDetailsPojo.setcost(avgcost.floatValue());	
				}else {
					productDetailsPojo.setcost(additem.getPrice());
				}*/

			
				//purchaseEntity.s(additem.getNote());

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (unit_price * 0.125 * quantity);
				purchaseEntity.setGrand_total(additem.getSubtotal().doubleValue());
				purchaseEntity.setSupplier(additem.getUnitname());

			}
			grand_total=total; 
			bd_grand_total = new BigDecimal(grand_total);
			bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);

			purchaseEntity.setBalance(0);
			purchaseEntity.setGrand_total(bd_grand_total.doubleValue());
			purchaseEntity.setFile(0);
		
			purchaseEntity.setPaid(1);
			purchaseEntity.getUser_id();
			purchaseEntity.setDate(new Date());
			
			
			PurchaseOnFileEntity purhaseenty = purchaseonfileRepo.save(purchaseEntity);
			
		

			List<PurchaseItemEntity> purchaseItemList = new ArrayList<>();

			for (AddItemReqPojo additem : addItemReqPojos) {
		

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());
			//	productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() + (Long.parseLong(additem.getQuantity())));

				BigDecimal price = productDetailsEnt.getprice();
				BigDecimal qty = new BigDecimal(additem.getQuantity());
				BigDecimal taxRate = new BigDecimal("0.125");

				BigDecimal tax = price.multiply(qty).multiply(taxRate);
			
				PurchaseItemOnFileEntity purchaseitemEntity = new PurchaseItemOnFileEntity();
				purchaseitemEntity.setProduct_id(additem.getProductId());
				purchaseitemEntity.setQuantity(Long.parseLong(additem.getQuantity()));
				purchaseitemEntity.setPurchaseId(purchaseEntity.getPurchaseId());
				purchaseitemEntity.setProduct_name(productDetailsEnt.getname());
				purchaseitemEntity.setMpn(productDetailsEnt.getcf1());
				purchaseitemEntity.setTotal(bd_grand_total.doubleValue());
				purchaseitemEntity.setWarehouse_id(productDetailsEnt.getwarehouse());
				purchaseitemEntity.setSellingprice(additem.getPrice().floatValue());
				purchaseitemEntity.setUnitcost(additem.getSubtotal().floatValue());
				purchaseitemEntity.setFile(0);
				purchaseitemEntity.setSuppliername(additem.getUnitname());
				purchaseitemonfileRepo.save(purchaseitemEntity);
				//productDetailsRepo.save(productDetailsEnt);
			}

			
			resultVO.setMsgDescr("Purchase Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<PurchaseItemOnFilePojo> getPurchaseOnFileList() {
		List<PurchaseItemOnFileEntity> purchaseEntityList = new ArrayList<PurchaseItemOnFileEntity>();
		List<PurchaseItemOnFilePojo> purchasePojoList = new ArrayList<PurchaseItemOnFilePojo>();
		try {
			LeoLogger.info("ImportPurchaseServiceImpl---getImportPurchaseList---in Import Purchase");
			purchaseEntityList = purchaseitemonfileRepo.findAll();
			for (PurchaseItemOnFileEntity purchaseEntityRes : purchaseEntityList) {
				if(purchaseEntityRes.getFile()==0) {

				PurchaseItemOnFilePojo purchasePojo = new PurchaseItemOnFilePojo();
				purchasePojo = mapper.map(purchaseEntityRes, PurchaseItemOnFilePojo.class);
				purchasePojoList.add(purchasePojo);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return purchasePojoList;
	}

	@Override
	public ResultVO editsellingpercentage(ImportPurchasePojo importpurchasePojo) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		ImportPurchaseEntity importPurchaseEntityRes =  importpurchaseRepo.findBypurchaseId(importpurchasePojo.getPurchaseId());
		if (importPurchaseEntityRes != null) {
			importPurchaseEntityRes.setSellingpercentage(importpurchasePojo.getSellingpercentage());
			importPurchaseEntityRes.setSellingprice(importpurchasePojo.getSellingprice());
			importpurchaseRepo.save(importPurchaseEntityRes);
			resultVO.setError(false);
		}
		return resultVO;
		
		
	}

	@Override
	public ResultVO TransferPurchaseToQuotes(String purchaseId,String customerId) {
		List<PurchaseItemEntity> purchaseItemEntityList = purchaseitemRepo.findByPurchaseId(Long.parseLong(purchaseId));
		List<PurchaseEntity> purchaseEntityList = purchaseRepo.findByPurchaseId(Long.parseLong(purchaseId));
		List<PurchaseItemPojo> purchaseItemPojoList = new ArrayList<PurchaseItemPojo>();
		MemberUser customer = memberUserRepo.findById(Long.valueOf(customerId));	
		QuotesEntity quotesEntity = new QuotesEntity();
		
		QuotesEntity quote=new QuotesEntity();
		
		
		LeoLogger.info("ImportPurchaseServiceImpl---TransferPurchaseToQuotes---purchaseEntityList"+purchaseEntityList);
		LeoLogger.info("ImportPurchaseServiceImpl---TransferPurchaseToQuotes---purchaseItemEntityList"+purchaseItemEntityList);
		try {
			LeoLogger.info("ImportPurchaseServiceImpl---getPurchaseitembypurchaseId---in purchase get Items by purchaseId");

			for (PurchaseEntity purchaseEntityRes : purchaseEntityList) {
				
				
				BigDecimal tax= new BigDecimal(purchaseEntityRes.getPricegrandtotal());
				tax= tax.multiply(new BigDecimal(0.125));
				
				LeoLogger.info("ImportPurchaseServiceImpl---getPurchaseitembypurchaseId---tax" +tax);
				
				BigDecimal final_total= new BigDecimal(purchaseEntityRes.getPricegrandtotal());
				final_total= final_total.add(tax);
				LeoLogger.info("ImportPurchaseServiceImpl---getPurchaseitembypurchaseId---final_total"+final_total);
				
			
				quotesEntity.setMember_name(customer.getName());
				quotesEntity.setMemberid(Long.valueOf(customerId));
				quotesEntity.setDate(new Date());
				quotesEntity.setOrder_tax(0);
				quotesEntity.setPayment_status("Due");
				quotesEntity.setOrder_discount(0);
				quotesEntity.setquotes_status("Available");
				quotesEntity.setCtype(customer.getCtype());
				quotesEntity.setCustomeraddress(customer.getAddress());
				quotesEntity.setPincode(customer.getPincode());
				quotesEntity.setPhonemain(customer.getPhonemain());
				quotesEntity.setMembername(customer.getName());
				quotesEntity.setTotal(purchaseEntityRes.getPricegrandtotal());
				quotesEntity.setTotal_tax(tax.doubleValue());
				quotesEntity.setGrandtotal(String.valueOf(final_total));
				quotesEntity.setQuotesId(0);
			
			    quote = quotesRepo.save(quotesEntity);
				Date today = new Date();
				int year = today.getYear();
				int currentYear = year + 1900;
				int currentMonth = today.getMonth() + 1;
				quotesEntity.setReferenceno("QUOTES" + currentYear + "/" + currentMonth + "/" + quote.getQuotesId());
				
				 quotesRepo.save(quotesEntity);
				
				
			}
			
			for (PurchaseItemEntity purchaseItemEntityRes : purchaseItemEntityList) {
				BigDecimal unitPrice = purchaseItemEntityRes.getSellingprice();
				BigDecimal tax = unitPrice.multiply(BigDecimal.valueOf(0.125));
				BigDecimal qty=new BigDecimal(purchaseItemEntityRes.getQuantity());
			
				BigDecimal subtotal=unitPrice.multiply(qty);
				QuotesItemEntity quotesItemEntity = new QuotesItemEntity();
				quotesItemEntity.setIsPriceChange(1L);
				quotesItemEntity.setProduct_code(String.valueOf(purchaseItemEntityRes.getProduct_id()));
				quotesItemEntity.setProduct_id((purchaseItemEntityRes.getProduct_id()));
				quotesItemEntity.setProduct_name(purchaseItemEntityRes.getProduct_name());
				quotesItemEntity.setReal_unit_price(purchaseItemEntityRes.getSellingprice().doubleValue());
				quotesItemEntity.setTax(String.valueOf(purchaseItemEntityRes.getSellingprice().doubleValue()*0.125));
				quotesItemEntity.setSubtotal(subtotal.doubleValue());
				quotesItemEntity.setQuantity(BigDecimal.valueOf(purchaseItemEntityRes.getQuantity()));
				quotesItemEntity.setSale_item_id(1);
				quotesItemEntity.setRoll("Piece");
				quotesItemEntity.setQuotesid(quote.getQuotesId());
				
				
				quotesItemRepo.save(quotesItemEntity);
				
			
			}
			
		
			
			quotesRepo.save(quotesEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LeoLogger.info("ImportPurchaseServiceImpl---getPurchaseitembypurchaseId---purchaseItemPojoList ....." + purchaseItemPojoList.toString());
		return null;
		
	}

	

	



	

	

	
	
}
