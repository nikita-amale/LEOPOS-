/**
 * 
 */
package com.leonet.service;

import java.math.BigDecimal;

import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.ResultVO;
 

/**
 * @author YOGESH
 *
 */
public interface ProductService {

	ResultVO editProduct(ProductDetailsPojo productDetailsPojo);
    ResultVO editQuantity(ProductDetailsPojo productDetailsPojo);
	ResultVO rollprice(ProductDetailsPojo productDetailsPojo);
	ResultVO productName(ProductDetailsPojo productDetailsPojo);
	ResultVO mpn(ProductDetailsPojo productDetailsPojo);
	
	ProductDetailsEntity getProductById(Long productId);
	String removeImage(Long productId);
	
	public void updateProductCost(Long productId, Float productCost);
	
	 BigDecimal getTotalQuantity();

}
