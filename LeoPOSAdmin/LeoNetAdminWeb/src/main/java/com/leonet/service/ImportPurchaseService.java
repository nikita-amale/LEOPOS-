package com.leonet.service;

import java.util.List;


import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.ImportPurchasePojo;
import com.leonet.common.pojo.PurchaseFilePojo;
import com.leonet.common.pojo.PurchaseItemOnFilePojo;
import com.leonet.common.pojo.PurchaseItemPojo;
import com.leonet.common.pojo.PurchaseOnFilePojo;
import com.leonet.common.pojo.PurchasePojo;

import com.leonet.common.pojo.ResultVO;
import com.leonet.entity.ImportPurchaseEntity;



public interface ImportPurchaseService {
	ResultVO savePurchaseBeforeApply(ImportPurchasePojo importpurchasePojo);
	ResultVO savePurchaseAftereApply();
	ResultVO TransferPurchaseToQuotes(String purchaseId,String customerId);
	List<PurchasePojo> getPurchaseList();
	
	ResultVO addPurchase(List<AddItemReqPojo> addItemReqPojos);

	
	List<PurchaseItemPojo> getPurchaseitembypurchaseId(String purchaseId);
	ResultVO updateimportPurchase(ImportPurchasePojo importpurchasePojo);
	ResultVO addPurchaseBeforSubmit(List<AddItemReqPojo> addItemReqPojos);
	

	List<ImportPurchasePojo> getImportPurchaseList();
	
	List<PurchaseItemOnFilePojo> getPurchaseOnFileList();
	ResultVO deleteImportPurchase(long id);
	ImportPurchasePojo getImportPurchaseById(Long id);
	
	List<ImportPurchaseEntity> findAllByApplied();
	ResultVO savefile(PurchaseFilePojo purchasefilePojo);
	ResultVO uploadfile(PurchaseFilePojo purchasefilePojo);
	ResultVO editsellingpercentage(ImportPurchasePojo importpurchasePojo);

}
