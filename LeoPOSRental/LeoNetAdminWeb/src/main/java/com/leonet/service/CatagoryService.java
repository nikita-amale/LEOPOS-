/**
 * 
 */
package com.leonet.service;

import java.util.List;

import com.leonet.common.pojo.AdminDetails;
import com.leonet.common.pojo.BarCodePojo;
import com.leonet.common.pojo.PlanPojo;
import com.leonet.common.pojo.ProductCategoryPojo;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.ProductSubCategoryPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.common.pojo.VendorPojo;
 

/**
 * @author YOGESH
 *
 */
public interface CatagoryService {

	ResultVO addCatagory(ProductCategoryPojo catagoryPojo);

	ResultVO addSubCatagory(ProductSubCategoryPojo subCatagoryPojo);

	List<ProductCategoryPojo> getCatagoryList();

	List<ProductSubCategoryPojo> getSubCatagory(long catagoryId);

	ResultVO addProductDetails(ProductDetailsPojo productDetailsPojo);

	List<ProductDetailsPojo> getProductDetailsList();

	ResultVO deleteProductDetails(String productCode);

	ResultVO deleteCategoryDetails(long catCode);

	List<ProductSubCategoryPojo> getSubCategoryDetailsList(long catagoryId);

	ResultVO deleteSubCategoryDetails(long catSubCode);

	List<AdminDetails> getUserDetails();
	
    ResultVO addvendor(VendorPojo vendorPojo);

	List<VendorPojo> getVendorList();

	ResultVO deleteVendorDetails(long id);

	List<ProductDetailsPojo> getProductDetails(long subCatagoryId);

	ResultVO addProductBarCodeDetails(BarCodePojo barCodePojo);
 
	List<BarCodePojo> getBarCodeList(long subCatagoryId);

	ResultVO deleteBarCodeDetails(long barCode);

	ResultVO updateProductDetails(ProductDetailsPojo productDetailsPojo);
	
	

	List<ProductDetailsPojo> getItemCodeList(String itemCode);

	List<ProductDetailsPojo> getItemNameList(String itemName);

	List<UserRegistrationPojo> getMemberDetails();

	ResultVO updateMemberDetails(UserRegistrationPojo memberDetailsPojo);

	List<PlanPojo> getPlanList();

	ResultVO purchasePlanDetails( PlanPojo planPojo);

	PlanPojo getplanbyPlanid(String planId);

	List<ProductDetailsPojo> getProductDetailsAll();
 
}
