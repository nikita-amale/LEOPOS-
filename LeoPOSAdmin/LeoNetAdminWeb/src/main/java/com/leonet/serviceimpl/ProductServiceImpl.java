/**
 * 
 */
package com.leonet.serviceimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.pojo.ProductDetailsPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.constant.Action;
import com.leonet.repo.ProductDetailsRepo;
import com.leonet.service.ProductService;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDetailsRepo productDetailsRepo;
	
	@Autowired
	private com.leonet.service.SysAuditService sysAuditService;

	@Override
	public ResultVO editProduct(ProductDetailsPojo productDetailsPojo) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		ProductDetailsEntity detailsEntity = getProductById(productDetailsPojo.getProductId());
		if (detailsEntity != null) {
			detailsEntity.setprice(productDetailsPojo.getprice());
			productDetailsRepo.save(detailsEntity);
			resultVO.setError(false);
		}
		return resultVO;
	}

	@Override
	public ProductDetailsEntity getProductById(Long productId) {
		Optional<ProductDetailsEntity> optProductDetails = productDetailsRepo.findById(productId);
		if (optProductDetails.isPresent()) {
			return optProductDetails.get();
		}
		return null;
	}

	@Override
	public ResultVO rollprice(ProductDetailsPojo productDetailsPojo) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		ProductDetailsEntity detailsEntity = getProductById(productDetailsPojo.getProductId());
		if (detailsEntity != null) {
			detailsEntity.setrollprice(productDetailsPojo.getrollprice());
			productDetailsRepo.save(detailsEntity);
			resultVO.setError(false);
		}
		return resultVO;
	}

	@Override
	public String removeImage(Long productId) {
		String msg = "";
		
		ProductDetailsEntity productDetails = getProductById(productId);
		if(Objects.nonNull(productDetails)) {
			productDetails.setProductFileName("");
			productDetails.setProductFilePath("");
			productDetailsRepo.save(productDetails);
			msg ="Image removed successsfully";
		}else {
			msg ="Product not found";
		}
		// TODO Auto-generated method stub
		return msg;
	}

	@Override
	public ResultVO productName(ProductDetailsPojo productDetailsPojo) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		ProductDetailsEntity detailsEntity = getProductById(productDetailsPojo.getProductId());
		LeoLogger.info("Catagory Controller ---productName  " + productDetailsPojo.getname());
		if (detailsEntity != null) {
			detailsEntity.setname(productDetailsPojo.getname());
			productDetailsRepo.save(detailsEntity);
			resultVO.setError(false);
		}
		return resultVO;
	}

	@Override
	public ResultVO mpn(ProductDetailsPojo productDetailsPojo) {
		ResultVO resultVO = new ResultVO();
		resultVO.setMsgCode("001");
		resultVO.setError(true);

		ProductDetailsEntity detailsEntity = getProductById(productDetailsPojo.getProductId());
		LeoLogger.info("Catagory Controller ---mpn  " + productDetailsPojo.getcf1());
		if (detailsEntity != null) {
			detailsEntity.setcf1(productDetailsPojo.getcf1());
			productDetailsRepo.save(detailsEntity);
			resultVO.setError(false);
		}
		return resultVO;
	}

	@Override
	public void updateProductCost(Long productId, Float productCost) {

		ProductDetailsEntity product = getProductById(productId);

        if (product != null) {         
            product.setcost(productCost);
           productDetailsRepo.save(product);
     	  LeoLogger.info("ProductServiceImpl---updateProductCost---- new updated productCost :  " + productCost);
     	
        } 
    }

	@Override
	public BigDecimal getTotalQuantity() {
		return productDetailsRepo.getTotalQuantity();
	}

	@Override
	public ResultVO editQuantity(ProductDetailsPojo productDetailsPojo) {
	    ResultVO resultVO = new ResultVO();
	    resultVO.setMsgCode("001");
	    resultVO.setError(true);

	    ProductDetailsEntity detailsEntity = getProductById(productDetailsPojo.getProductId());
	    if (detailsEntity != null) {
	    	String auditMessage = buildUpdateProductAudit(productDetailsPojo, detailsEntity);
	        detailsEntity.setQuantity(productDetailsPojo.getQuantity());
	        productDetailsRepo.save(detailsEntity);
	        resultVO.setError(false);
	        resultVO.setMsgCode("000"); 
	        
	        if (auditMessage != null) {
	    	sysAuditService.setSysAudit(Action.QUANTITY_UPDATE, auditMessage); 
	        }
	    }
	    return resultVO;
	}
	
	
	
	public String buildUpdateProductAudit(ProductDetailsPojo newData, ProductDetailsEntity oldData) {
	    List<String> changes = new ArrayList<>();

	    if (!Objects.equals(oldData.getQuantity(), newData.getQuantity())) {
	        changes.add("Quantity changed from " + oldData.getQuantity() + " to " + newData.getQuantity());
	    }

	    if (changes.isEmpty()) {
	        return null; 
	    }

	    String auditMsg = "Product[" + oldData.getProductId() + "] updated: " + String.join(", ", changes);
	    return auditMsg.length() > 255 ? auditMsg.substring(0, 255) : auditMsg;
	}



		
	

}
