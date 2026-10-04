/**
 * 
 */
package com.leonet.serviceimpl;

import java.math.BigDecimal;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.leonet.entity.MemberUser;
import com.leonet.entity.PaymentEntity;
import com.leonet.entity.RegisterEntity;
import com.leonet.entity.RegisterhistoryEntity;
import com.leonet.entity.ReturnCashEntity;
import com.leonet.entity.ReturnItemEntity;
import com.leonet.entity.ReturnCashItemsEntity;
import com.leonet.entity.ReturnsEntity;
import com.leonet.entity.UnitEntity;
import com.leonet.common.entity.FTEntity;
import com.leonet.common.entity.FinancialTransactionEntity;
import com.leonet.common.entity.ProductDetailsEntity;
import com.leonet.common.entity.SalesEntity;
import com.leonet.common.entity.SalesItemEntity;
import com.leonet.common.entity.SpecialSalesEntity;
import com.leonet.common.entity.SpecialSalesItemEntity;
import com.leonet.common.pojo.AddItemReqPojo;
import com.leonet.common.pojo.PaymentPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.common.pojo.ReturnCashItemPojo;
import com.leonet.common.pojo.ReturnCashPojo;
import com.leonet.common.pojo.ReturnItemPojo;
import com.leonet.common.pojo.ReturnPojo;
import com.leonet.common.pojo.SalePojo;
import com.leonet.common.pojo.UserRegistrationPojo;
import com.leonet.repo.BulkPaymentRepo;
import com.leonet.repo.FTRepo;
import com.leonet.repo.FinancialTransactionRepo;
import com.leonet.repo.MemberUserRepo;
import com.leonet.repo.PaymentRepo;
import com.leonet.repo.ProductDetailsRepo;
import com.leonet.repo.RegisterRepo;
import com.leonet.repo.ReturCashItemRepo;
import com.leonet.repo.ReturnItemRepo;
import com.leonet.repo.ReturnsCashRepo;
import com.leonet.repo.ReturnsRepo;
import com.leonet.repo.RgisterhistoryRepo;
import com.leonet.repo.SalesItemRepo;
import com.leonet.repo.SalesRepo;
import com.leonet.repo.SpecialSalesItemRepo;
import com.leonet.repo.SpecialSalesRepo;
import com.leonet.repo.UnitRepo;
import com.leonet.service.ReturnsService;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Service
public class ReturnsServiceImpl implements ReturnsService {

	@Autowired
	MemberUserRepo memberUserRepo;

	@Autowired
	ProductDetailsRepo productDetailsRepo;

	@Autowired
	Mapper mapper;

	@Autowired
	PaymentRepo paymentRepo;

	@Autowired
	BulkPaymentRepo bulkpaymentRepo;
	
	@Autowired
	RgisterhistoryRepo registerhistoryRepo;

	@Autowired
	RegisterRepo registerRepo;

	@Autowired
	ReturnsRepo returnsRepo;
	
	@Autowired
	ReturnsCashRepo returnscashRepo;

	@Autowired
	ReturnItemRepo returnItemRepo;
	
	@Autowired
	ReturCashItemRepo returncashItemRepo;

	@Autowired
	FinancialTransactionRepo financialTransactionsRepo;

	@Autowired
	FTRepo fTRepo;

	@Autowired
	SalesRepo salesRepo;

	@Autowired
	UnitRepo unitRepo;

	@Autowired
	SpecialSalesRepo specialsalesRepo;

	@Autowired
	SpecialSalesItemRepo specialsalesItemRepo;
	@Autowired
	SalesItemRepo salesItemRepo;

	@Override
	public ResultVO addReturn(List<AddItemReqPojo> addItemReqPojos,long applyReturn) {
		ResultVO resultVO = new ResultVO();

		try {
			LeoLogger.info("ReturnServiceImpl--addReturn");
           if(applyReturn==1) {
			ReturnsEntity returnEntity = new ReturnsEntity();
			// SalesEntity saleEntity = new SalesEntity();
			MemberUser memberPojo = new MemberUser();

			double tax_rate = 0, total = 0, unit_price = 0, return_amount = 0, paid_total = 0;
			int quantity = 0;
			Long applysaleId = 0L;

			// Declare Bigdecimal equivalents
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			BigDecimal bd_rettotal = new BigDecimal(0.0);
			BigDecimal bd_return_amount = new BigDecimal(0.0);
			BigDecimal bd_orig_return_amount = new BigDecimal(0.0);
			BigDecimal bd_paid_total = new BigDecimal(0.0);
			

			// BigDecimal db_sale_balance = new BigDecimal(0.0);

			// this loop is for sales total

			// SalesItemEntity saleitemEntity =new SalesItemEntity();

			for (AddItemReqPojo additem : addItemReqPojos) {

				memberPojo = memberUserRepo.findById(additem.getCustomerId());
				returnEntity.setDate(new Date());
				returnEntity.setMemberid(memberPojo.getId());
				returnEntity.setMember_name(memberPojo.getName());
				returnEntity.setNote(additem.getNote());
				returnEntity.setCustomeraddress(memberPojo.getAddress());
				returnEntity.setPincode(memberPojo.getPincode());
				returnEntity.setMembername(memberPojo.getName());
				applysaleId = additem.getApplysaleId();
				returnEntity.setIsActive(0);
				
				bd_total = additem.getSubtotal();
				bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
				returnEntity.setAmount(bd_total.doubleValue());
				//LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>>Total....." + bd_total);

				LeoLogger.info("ReturnserviceImpl >>>> Sale id to be applied return is ....." + applysaleId.toString());

				// ProductDetailsEntity productDetailsPojo =
				// productDetailsRepo.findByProductId(additem.getProductId());

				// if (productDetailsPojo != null)
				unit_price = additem.getPrice().doubleValue();
				quantity = Integer.parseInt(additem.getQuantity());

				returnEntity.setNote(additem.getNote());
				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (additem.getSubtotal().doubleValue() * 0.125 );
				}else {
					total = total + (unit_price * quantity);
					tax_rate =0.0;
				}

			}

			//LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>> Apply Invoice saleId is >>> " + applysaleId.toString());
			bd_tax_rate = new BigDecimal(tax_rate);
			bd_tax_rate = bd_tax_rate.setScale(2, RoundingMode.HALF_UP);
			returnEntity.setTax(bd_tax_rate.doubleValue());
			//LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>>Tax Rate....." + bd_tax_rate);

			bd_total = new BigDecimal(total);
			bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
			returnEntity.setAmount(bd_total.doubleValue());
			//LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>>Total....." + bd_total);

			bd_rettotal = bd_total.add(bd_tax_rate);
			// bd_rettotal = bd_rettotal.setScale(2);
			returnEntity.setUserid(bd_rettotal.doubleValue());
			//LeoLogger.info("ReturnserviceImpl>>>>addReturn>>>Total....." + bd_rettotal);
			
			
			returnEntity.setReturnType(applyReturn);
			ReturnsEntity retenty = returnsRepo.save(returnEntity);
			UnitEntity unitentity = new UnitEntity();
			if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
			return_amount = total + tax_rate;
			bd_return_amount = new BigDecimal(total).add(new BigDecimal(tax_rate));
			bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
			bd_orig_return_amount = bd_return_amount;
			}else {
				return_amount = total;
				bd_return_amount = new BigDecimal(total);
				bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
				bd_orig_return_amount = bd_return_amount;	
			}
			//LeoLogger.info("ReturnserviceImpl>>>addReturn>>Return Total....." + bd_return_amount);

			// Logic to be applied to all invoices

		

			// ********** Adjust the Return amount against the invoices *******************

			if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {

				LeoLogger.info("ReturnserviceImpl>>>>addReturn>>>Member pojo is not a special customer "
						+ memberPojo.getCtype());
				// for (SalesEntity salesEntityRes : salesEntityList) {
				SalesEntity saleapplyEntity = salesRepo.findBySaleId(applysaleId);
				//SalesEntity saleapplyEntity = salesRepo.findBySaleIdAndIsActive(applysaleId,0);
				
				// SalesItemEntity saleitemEntity = salesItemRepo.findBySaleid(applysaleId);
				// LeoLogger.info("ReturnserviceImpl>>>addReturn>>Sales Status" +
				// saleitemEntity);
				LeoLogger.info("ReturnserviceImpl>>>addReturn>>Sales Status" + saleapplyEntity.getSale_status());
				Date d = new Date();
				int year = d.getYear();
				int currentYear = year + 1900;
				int currentmonth = d.getMonth() + 1;

				returnEntity.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + retenty.getReturnId());
				returnEntity.setSalereferenceno(saleapplyEntity.getReferenceno());
				returnsRepo.save(returnEntity);

				if ((return_amount >= (saleapplyEntity.getGrand_total() - saleapplyEntity.getPaid()))
						&& saleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due")) {
					// Addjust Sales entity paid value for hthe selected
					paid_total = saleapplyEntity.getGrand_total() - saleapplyEntity.getPaid();
					LeoLogger.info(
							"ReturnServiceImpl !Speical -- Sale apply entity balance amount is less than due amount ### Paid Total here is  "
									+ paid_total);
					saleapplyEntity.setPaid(saleapplyEntity.getGrand_total());
					saleapplyEntity.setPaymentstatus("Paid");
					salesRepo.save(saleapplyEntity);
					bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
					bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
					return_amount = return_amount - paid_total;
					//returnEntity.setSaleid (applysaleId.toString());
					applyPaymentReturn(applysaleId, bd_paid_total, retenty, currentYear, currentmonth,applyReturn);

					LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
							+ bd_return_amount.toString());

				} else if (saleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due")) {

					paid_total = saleapplyEntity.getPaid();
					LeoLogger.info(
							"ReturnServiceImpl ! NotSpeical -- ### Sale apply entity balance amount is greater than the return amount ### Paid Total here  "
									+ paid_total);
					LeoLogger.info("ReturnServiceImpl ### Adjustung sale with return amount and the value is  "
							+ bd_return_amount);
					paid_total = paid_total + bd_return_amount.doubleValue();
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
					saleapplyEntity.setPaid(bd_paid_total.doubleValue());
					bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
					if (saleapplyEntity.getGrand_total() <= saleapplyEntity.getPaid())
						saleapplyEntity.setPaymentstatus("Paid");
					// returnEntity.setSaleid(applysaleId.toString());
					salesRepo.save(saleapplyEntity);

					applyPaymentReturn(applysaleId, bd_return_amount, retenty, currentYear, currentmonth,applyReturn);
					return_amount = 0;
					bd_return_amount = new BigDecimal(0.0);
					LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
							+ bd_return_amount.toString());

				}

				if (bd_return_amount.compareTo(new BigDecimal(0.0)) > 0) {
					LeoLogger.info(
							"ReturnserviceImpl >>> addReturn>> !Speical -- ### Return amount is greater than 0 after applying to Applies sale ### Paid Total here  >>>"
									+ bd_return_amount.toPlainString());
					// Adjusting it to pending invoices if any
					List<SalesEntity> salesEntityList = salesRepo
							.findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(memberPojo.getId(), "Due");
					LeoLogger.info("ReturnserviceImpl>>>addReturn>>Sales list in ascending order "
							+ salesEntityList.toString());

					// set deposit of the customer with balance amount
					for (SalesEntity salesEntityRes : salesEntityList) {

						if (return_amount >= (salesEntityRes.getGrand_total() - salesEntityRes.getPaid()))

						{
							// Addjust Sales entity paid value for hthe selected

							paid_total = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
							LeoLogger.info("ReturnServiceImpl ### addReturn>> !Speical Sale id >>> "
									+ salesEntityRes.getSaleId());
							LeoLogger.info(
									"ReturnServiceImpl ### addReturn>> !Speical -- ### Return amount is greater than the sale balance ### Paid Total here  >>>  "
											+ paid_total);
							salesEntityRes.setPaid(salesEntityRes.getGrand_total());
							salesEntityRes.setPaymentstatus("Paid");
							salesEntityRes.setSale_status("Paid");
							salesRepo.save(salesEntityRes);
							bd_paid_total = new BigDecimal(paid_total);
							bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
							applyPaymentReturn(salesEntityRes.getSaleId(), bd_paid_total, retenty, currentYear, currentmonth,applyReturn);
							bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
							return_amount = return_amount - paid_total;
							LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
									+ bd_return_amount.toString());

						} else if (salesEntityRes.getPaymentstatus().equalsIgnoreCase("Due")) {

							paid_total = salesEntityRes.getPaid();
							LeoLogger.info(
									"ReturnServiceImpl ### !Speical -- ### Return amount is less than the sale balance ### Paid Total here  "
											+ paid_total);
							LeoLogger.info("ReturnServiceImpl ### Adjustung sale with return amount and the value is  "
									+ bd_return_amount);
							paid_total = paid_total + bd_return_amount.doubleValue();
							bd_paid_total = new BigDecimal(paid_total);
							bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
							salesEntityRes.setPaid(bd_paid_total.doubleValue());
							if (salesEntityRes.getGrand_total() == salesEntityRes.getPaid())
								salesEntityRes.setPaymentstatus("Paid");
							salesRepo.save(salesEntityRes);
							applyPaymentReturn(salesEntityRes.getSaleId(), bd_return_amount, retenty, currentYear,
									currentmonth,applyReturn);
							return_amount = 0;
							bd_return_amount = new BigDecimal(0.0);
							LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
									+ bd_return_amount.toString());
							break;

						}
					}

				}

			} else {

				LeoLogger.info("ReturnserviceImpl>>>>addReturn >>>>>>>> Reached Special sales else part");

				LeoLogger.info("ReturnserviceImpl>>>>addReturn>>>Member pojo " + memberPojo.getCtype());
				// for (SalesEntity salesEntityRes : salesEntityList) {
				SpecialSalesEntity specialsaleapplyEntity = specialsalesRepo.findBySaleId(applysaleId);
				Date d = new Date();
				int year = d.getYear();
				int currentYear = year + 1900;
				int currentmonth = d.getMonth() + 1;

				 returnEntity.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + retenty.getReturnId());
				returnEntity.setSalereferenceno(specialsaleapplyEntity.getReferenceno());
				// returnEntity.setSaleid(applysaleId);

				// saleEntity = salesRepo.findBySaleId(applysaleId);

				// LeoLogger.info("addReturn >>>>>>>> saleEntity is >>> " +
				// saleEntity.toString());

				if (return_amount >= (specialsaleapplyEntity.getGrand_total() - specialsaleapplyEntity.getPaid())
						&& specialsaleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due"))

				{
					// Addjust Sales entity paid value for hthe selected

					paid_total = specialsaleapplyEntity.getGrand_total() - specialsaleapplyEntity.getPaid();
					LeoLogger.info(
							"ReturnServiceImpl ### Speical Sale apply entity balance amount is greater than the return amount ### Paid Total here is  "
									+ paid_total);
					specialsaleapplyEntity.setPaid(specialsaleapplyEntity.getGrand_total());
					specialsaleapplyEntity.setSale_status("Paid");
					specialsaleapplyEntity.setPaymentstatus("Paid");
					specialsalesRepo.save(specialsaleapplyEntity);
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
					applyPayment(applysaleId, bd_paid_total, retenty, currentYear, currentmonth);
					total = total - paid_total;
					bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
					return_amount = return_amount - paid_total;
					LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
							+ bd_return_amount.toString());

				} else if (specialsaleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due")) {

					paid_total = specialsaleapplyEntity.getPaid();
					LeoLogger.info(
							"ReturnServiceImpl ### Speical Sale apply entity balance amount is less than the return amount ### Paid Total here is  "
									+ paid_total);
					LeoLogger.info("ReturnServiceImpl ### Adjustung sale with return amount and the value is  "
							+ bd_return_amount);
					paid_total = paid_total + bd_return_amount.doubleValue();
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
					specialsaleapplyEntity.setPaid(bd_paid_total.doubleValue());
					if (specialsaleapplyEntity.getGrand_total() == specialsaleapplyEntity.getPaid()) {
						specialsaleapplyEntity.setSale_status("Paid");
						specialsaleapplyEntity.setPaymentstatus("Paid");
					}
					specialsalesRepo.save(specialsaleapplyEntity);
					applyPayment(applysaleId, bd_return_amount, retenty, currentYear, currentmonth);
					return_amount = 0;
					bd_return_amount = new BigDecimal(0.0);
					LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
							+ bd_return_amount.toString());

				}

				returnsRepo.save(returnEntity);

				if (bd_return_amount.compareTo(new BigDecimal(0.0)) > 0) {
					LeoLogger.info("ReturnserviceImpl>>>addReturn>> Return amount is still left for Special sales >>>"
							+ bd_return_amount.toPlainString());
					// Adjusting it to pending invoices if any

					List<SpecialSalesEntity> specialsalesEntityList = specialsalesRepo
							.findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(memberPojo.getId(), "Due");
					LeoLogger.info("ReturnserviceImpl>>>addReturn>>>Sales list in ascending order "
							+ specialsalesEntityList.toString());

					// set deposit of the customer with balance amount
					for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

						if (return_amount >= (specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid()))

						{
							// Addjust Sales entity paid value for hthe selected

							paid_total = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
							LeoLogger.info("ReturnServiceImpl ### Paid Total here is  " + paid_total);
							specialsalesEntityRes.setPaid(specialsalesEntityRes.getGrand_total());
							specialsalesEntityRes.setPaymentstatus("Paid");
							specialsalesEntityRes.setSale_status("Paid");
							specialsalesRepo.save(specialsalesEntityRes);
							bd_paid_total = new BigDecimal(paid_total);
							bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
							applyPayment(specialsalesEntityRes.getSaleId(), bd_paid_total, retenty, currentYear,
									currentmonth);
							bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
							return_amount = return_amount - paid_total;
							LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
									+ bd_return_amount.toString());

						} else if (specialsalesEntityRes.getPaymentstatus().equalsIgnoreCase("Due")) {

							paid_total = specialsalesEntityRes.getPaid();
							LeoLogger.info(
									"ReturnServiceImpl ### Return amount was less so total payment done so far is  "
											+ paid_total);
							LeoLogger.info("ReturnServiceImpl ### Adjustung sale with return amount and the value is  "
									+ bd_return_amount);
							paid_total = paid_total + bd_return_amount.doubleValue();
							bd_paid_total = new BigDecimal(paid_total);
							bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
							specialsalesEntityRes.setPaid(bd_paid_total.doubleValue());
							if (specialsalesEntityRes.getGrand_total() == specialsaleapplyEntity.getPaid()) {
								specialsalesEntityRes.setSale_status("Paid");
							    specialsalesEntityRes.setPaymentstatus("Paid");
							}
							specialsalesRepo.save(specialsalesEntityRes);
							applyPayment(specialsalesEntityRes.getSaleId(), bd_return_amount, retenty, currentYear,
									currentmonth);
							return_amount = 0;
							bd_return_amount = new BigDecimal(0.0);
							LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
									+ bd_return_amount.toString());
							break;

						}
					}

				}
			}

			
			///////////// Removing this condition like general sell return.
	  /*		if (return_amount > 0) {
				// Here instead of setting like we need to make a credit note

				LeoLogger.info("ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   "
						+ bd_return_amount.toString());
				BigDecimal db_credit_amount = new BigDecimal(0.0);
				db_credit_amount = new BigDecimal(memberPojo.getCreditpayment()).add(bd_return_amount);
				db_credit_amount = db_credit_amount.setScale(2, RoundingMode.HALF_UP);
				memberPojo.setCreditpayment(db_credit_amount.doubleValue());
				memberUserRepo.save(memberPojo);
			}  */

			// this loop is for sales breakdown
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				if (additem.getUnit() != null && additem.getUnit() != "") {
					//unitentity = unitRepo.findByUnitname(additem.getRoll());
					unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				}
				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
					
					SalesItemEntity saleitemEntity = salesItemRepo.findTopBySaleidAndProductidAndRoll(applysaleId,additem.getProductId(),additem.getRoll());
					
					LeoLogger.info("ReturnserviceImpl>>>addReturn>>Sales Items are >>> " + saleitemEntity.toString());

					long qty = (Long.parseLong(saleitemEntity.getReturnqty()));
					long rqty = 0;

					rqty = Long.parseLong(additem.getQuantity()) + qty;
					saleitemEntity.setReturnqty(Long.toString(rqty));
					salesItemRepo.save(saleitemEntity);

				} else {
					SpecialSalesItemEntity ssaleitemEntity = specialsalesItemRepo.findTopBySaleidAndProductidAndRoll(applysaleId,
							additem.getProductId(),additem.getRoll());
					long qty = (Long.parseLong(ssaleitemEntity.getReturnqty()));
					long rqty = 0;

					rqty = Long.parseLong(additem.getQuantity()) + qty;
					ssaleitemEntity.setReturnqty(Long.toString(rqty));
					specialsalesItemRepo.save(ssaleitemEntity);

				}

				double tax = 0;

				tax = (additem.getPrice().doubleValue() * Long.parseLong(additem.getQuantity())) * .125;

				ReturnItemEntity returnItemEntity = new ReturnItemEntity();
				returnItemEntity.setReturnid(retenty.getReturnId());
				returnItemEntity.setProductid(additem.getProductId());
				returnItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
			
				returnItemEntity.setTax(String.valueOf(additem.getPrice().doubleValue() * 0.125));
				
				returnItemEntity.setProduct_name(additem.getProductName());
				
				// returnItemEntity.setReturnqty(additem.getQuantity());

				bd_real_unit_price = additem.getPrice();
				bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
				returnItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());

				bd_subtotal = additem.getSubtotal();
				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				returnItemEntity.setSubtotal(bd_subtotal.doubleValue());
                
				bd_grand_total = bd_subtotal.add(additem.getPrice() .multiply(new BigDecimal(0.125)));
				bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
				returnItemEntity.setTotal(bd_grand_total.doubleValue());
				
				LeoLogger.info("ReturnserviceImpl>>addReturn>>Total....." + bd_total);

				returnItemEntity.setTax(Double.toString(tax));
				LeoLogger.info("ReturnserviceImpl>>addReturn>>unitentity.getId()....." + unitentity.getId());
				returnItemEntity.setUnitid(unitentity.getId());
				returnItemEntity.setUnitname(additem.getRoll());

				returnItemRepo.save(returnItemEntity);
				// salesItemRepo.save(saleitemEntity);

				if (!additem.getRoll().equalsIgnoreCase("Piece")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					//LeoLogger.info("ReturnserviceImpl>>addReturn>>" + unitentity.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					LeoLogger.info(qty.toString());
					LeoLogger.info(unit.toString());
					qty = qty .multiply(unit) ;
					returnItemEntity.setQuantity(qty);

					LeoLogger.info("ReturnserviceImpl---addReturn--qt....." + qty);
				} else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					returnItemEntity.setQuantity(qty);
					returnItemRepo.save(returnItemEntity);
				}

				// Subtracting Quantities here from products

				if (!unitentity.getUnitname().equalsIgnoreCase("Piece")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit =( unitentity.getQuantity());
					//LeoLogger.info(qty.toString());
					qty = qty .multiply(unit) ;
					LeoLogger.info(unit.toString());
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(qty) );

				}

				else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					productDetailsEnt
							.setQuantity(productDetailsEnt.getQuantity() .add(qty) );
				}
				productDetailsRepo.save(productDetailsEnt);

			}

			applyPaymentReturnFinancial(applysaleId, bd_grand_total, bd_orig_return_amount, retenty,applyReturn);

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
			// >>>>>>>>>>>>>>>>>>>>>>>>>>

			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());
			


			if (registerEntity != null)

			{
				LeoLogger.info("ReturnserviceImpl>>>addReturn>>> Register entry found for today  ....."
						+ registerEntity.getSalesamount());
				LeoLogger.info("ReturnserviceImpl>>>addReturn>>> Register entry found for today refund ....."
						+returnEntity.getAmount()+returnEntity.getTax());
				
				registerEntity.setReferenceno(registerEntity.getReferenceno() + "," + retenty.getReturnId());
				registerEntity.setRefunds(registerEntity.getRefunds()+ (returnEntity.getAmount()+returnEntity.getTax()));
				registerEntity.setClosingbal(registerEntity.getClosingbal()-  (returnEntity.getAmount()+returnEntity.getTax()));

				registerRepo.save(registerEntity);
			}else
			{
				LeoLogger.info("ReturnserviceImpl>>>addReturn>>> Register entry found for today  .... else part.");
				
				RegisterEntity newregisterEntity = new RegisterEntity();
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				newregisterEntity.setCashpayment(0.00);
				newregisterEntity.setCreditcardpayment(0.00);
				newregisterEntity.setOpeningbal(1000.00);
				newregisterEntity.setClosingbal(1000.00- (returnEntity.getAmount()+returnEntity.getTax()));
				newregisterEntity.setChequepayment(0.00);
				newregisterEntity.setRefunds(  (returnEntity.getAmount()+returnEntity.getTax()));
				newregisterEntity.setReferenceno(String.valueOf(retenty.getReturnId()));
				newregisterEntity.setSalesamount(1000.00);
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);
			}
           

			// **************************** Register Details Entry ended
			// ********************
			
			//***Register history Entry***
			applyRegisterhistoryreturn(retenty);
           }else if(applyReturn==2) {
        	   ReturnsEntity returnEntity = new ReturnsEntity();
   			// SalesEntity saleEntity = new SalesEntity();
   			MemberUser memberPojo = new MemberUser();

   			double tax_rate = 0, total = 0, unit_price = 0, return_amount = 0, paid_total = 0;
   			int quantity = 0;
   			Long applysaleId = 0L;
   			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;

   			// Declare Bigdecimal equivalents
   			BigDecimal bd_tax_rate = new BigDecimal(0.0);
   			BigDecimal bd_total = new BigDecimal(0.0);
   			BigDecimal bd_grand_total = new BigDecimal(0.0);
   			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
   			BigDecimal bd_subtotal = new BigDecimal(0.0);
   			BigDecimal bd_rettotal = new BigDecimal(0.0);
   			BigDecimal bd_return_amount = new BigDecimal(0.0);
   			BigDecimal bd_orig_return_amount = new BigDecimal(0.0);
   			BigDecimal bd_paid_total = new BigDecimal(0.0);

   			// BigDecimal db_sale_balance = new BigDecimal(0.0);

   			// this loop is for sales total

   			// SalesItemEntity saleitemEntity =new SalesItemEntity();

   			for (AddItemReqPojo additem : addItemReqPojos) {

   				memberPojo = memberUserRepo.findById(additem.getCustomerId());
   				returnEntity.setDate(new Date());
   				returnEntity.setMemberid(memberPojo.getId());
   				returnEntity.setMember_name(memberPojo.getName());
   				returnEntity.setNote(additem.getNote());
   				returnEntity.setCustomeraddress(memberPojo.getAddress());
   				returnEntity.setPincode(memberPojo.getPincode());
   				returnEntity.setMembername(memberPojo.getName());
   				applysaleId = additem.getApplysaleId();
   				returnEntity.setIsActive(0);
   				
   				SalesEntity saleapplyEntity = salesRepo.findBySaleId(applysaleId);
   				returnEntity.setSalereferenceno(saleapplyEntity.getReferenceno());
   				
   				bd_total = additem.getSubtotal();
   				bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
   				returnEntity.setAmount(bd_total.doubleValue());
   				LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>>Total....." + bd_total);

   				LeoLogger.info("ReturnserviceImpl >>>> Sale id to be applied return is ....." + applysaleId.toString());

   				// ProductDetailsEntity productDetailsPojo =
   				// productDetailsRepo.findByProductId(additem.getProductId());

   				// if (productDetailsPojo != null)
   				unit_price = additem.getPrice().doubleValue();
   				quantity = Integer.parseInt(additem.getQuantity());

   				returnEntity.setNote(additem.getNote());

   				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {

   					total = total + (unit_price * quantity);
   					tax_rate = tax_rate + (additem.getSubtotal().doubleValue() * 0.125 );
   					}else {
   						total = total + (unit_price * quantity);
   						tax_rate =0.0;
   					}

   			}

   			LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>> Apply Invoice saleId is >>> " + applysaleId.toString());
   			bd_tax_rate = new BigDecimal(tax_rate);
   			bd_tax_rate = bd_tax_rate.setScale(2, RoundingMode.HALF_UP);
   			returnEntity.setTax(bd_tax_rate.doubleValue());
   			//LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>>Tax Rate....." + bd_tax_rate);

   			bd_total = new BigDecimal(total);
   			bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
   			returnEntity.setAmount(bd_total.doubleValue());
   			//LeoLogger.info("ReturnserviceImpl>>>>>addReturn>>>Total....." + bd_total);

   			bd_rettotal = bd_total.add(bd_tax_rate);
   			// bd_rettotal = bd_rettotal.setScale(2);
   			returnEntity.setUserid(bd_rettotal.doubleValue());
   			//LeoLogger.info("ReturnserviceImpl>>>>addReturn>>>Total....." + bd_rettotal);
   			
   			ReturnsEntity retenty = returnsRepo.save(returnEntity);
   			UnitEntity unitentity = new UnitEntity();

   			return_amount = total + tax_rate;
   			bd_return_amount = new BigDecimal(total).add(new BigDecimal(tax_rate));
   			bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
   			bd_orig_return_amount = bd_return_amount;
   			//LeoLogger.info("ReturnserviceImpl>>>addReturn>>Return Total....." + bd_return_amount);
   			
   			
   			BigDecimal db_credit_amount = new BigDecimal(0.0);
			db_credit_amount = new BigDecimal(memberPojo.getCreditpayment()).add(bd_return_amount);
			db_credit_amount = db_credit_amount.setScale(2, RoundingMode.HALF_UP);
			memberPojo.setCreditpayment(db_credit_amount.doubleValue());
			memberUserRepo.save(memberPojo);
			
			
			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				if (additem.getUnit() != null && additem.getUnit() != "") {
					//unitentity = unitRepo.findByUnitname(additem.getRoll());
					unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				}
				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
					
					SalesItemEntity saleitemEntity = salesItemRepo.findTopBySaleidAndProductidAndRoll(applysaleId,additem.getProductId(),additem.getRoll());
					
					LeoLogger.info("ReturnserviceImpl>>>addReturn>>Sales Items are >>> " + saleitemEntity.toString());

					long qty = (Long.parseLong(saleitemEntity.getReturnqty()));
					long rqty = 0;

					rqty = Long.parseLong(additem.getQuantity()) + qty;
					saleitemEntity.setReturnqty(Long.toString(rqty));
					salesItemRepo.save(saleitemEntity);

				} else {
					SpecialSalesItemEntity ssaleitemEntity = specialsalesItemRepo.findTopBySaleidAndProductidAndRoll(applysaleId,
							additem.getProductId(),additem.getRoll());
					long qty = (Long.parseLong(ssaleitemEntity.getReturnqty()));
					long rqty = 0;

					rqty = Long.parseLong(additem.getQuantity()) + qty;
					ssaleitemEntity.setReturnqty(Long.toString(rqty));
					specialsalesItemRepo.save(ssaleitemEntity);

				}

				
				BigDecimal price = productDetailsEnt.getprice();
				long qt = Long.parseLong(additem.getQuantity());
				BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(qt));
				BigDecimal tax = totalPrice.multiply(BigDecimal.valueOf(0.125));
	
				LeoLogger.info("SaleServiceImpl---addSale--totalPrice." + totalPrice);

				//tax = (additem.getPrice() * Long.parseLong(additem.getQuantity())) * .125;

				ReturnItemEntity returnItemEntity = new ReturnItemEntity();
				returnItemEntity.setReturnid(retenty.getReturnId());
				returnItemEntity.setProductid(additem.getProductId());
				returnItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
				returnItemEntity.setTax(String.valueOf(additem.getPrice() .multiply(new BigDecimal(0.125)) ));				
				returnItemEntity.setProduct_name(additem.getProductName());
				
				// returnItemEntity.setReturnqty(additem.getQuantity());

				bd_real_unit_price =additem.getPrice();
				bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
				returnItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());

				bd_subtotal =additem.getSubtotal();
				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				returnItemEntity.setSubtotal(bd_subtotal.doubleValue());

				bd_grand_total = bd_subtotal.add(bd_subtotal.multiply(new BigDecimal("0.125")));
				bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
				returnItemEntity.setTotal(bd_grand_total.doubleValue());
				LeoLogger.info("ReturnserviceImpl>>addReturn>>Total....." + bd_total);

				returnItemEntity.setTax((tax).toString());
				returnItemEntity.setUnitid(unitentity.getId());
				returnItemEntity.setUnitname(additem.getRoll());

				returnEntity.setReturnType(applyReturn);
				returnItemRepo.save(returnItemEntity);
				// salesItemRepo.save(saleitemEntity);

				if (!additem.getRoll().equalsIgnoreCase("Piece")) {
					BigDecimal qty =  new BigDecimal(additem.getQuantity());
					//LeoLogger.info("ReturnserviceImpl>>addReturn>>" + unitentity.getQuantity());
					BigDecimal unit = (unitentity.getQuantity());
					LeoLogger.info(qty.toString());
					LeoLogger.info(unit.toString());
					qty = qty .multiply(unit) ;
					returnItemEntity.setQuantity(qty);

					LeoLogger.info("ReturnserviceImpl---addReturn--qt....." + qty);
				} else {
					BigDecimal qty =  new BigDecimal(additem.getQuantity());
					returnItemEntity.setQuantity(qty);
					returnItemRepo.save(returnItemEntity);
				}

				// Subtracting Quantities here from products

				if (!unitentity.getUnitname().equalsIgnoreCase("Piece")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit = ( unitentity.getQuantity());
					LeoLogger.info(qty.toString());
					qty = qty .multiply(unit) ;
					LeoLogger.info(unit.toString());
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(qty) );

				}

				else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					productDetailsEnt
							.setQuantity(productDetailsEnt.getQuantity()  .add(qty) );
				}
				productDetailsRepo.save(productDetailsEnt);
				
				
			}
				
				applyPaymentReturnFinancial(applysaleId, bd_grand_total, bd_orig_return_amount, retenty,applyReturn);
				applyPaymentReturn(applysaleId, bd_paid_total, retenty, currentYear,currentmonth,applyReturn);

				// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
				// >>>>>>>>>>>>>>>>>>>>>>>>>>

				RegisterEntity registerEntity = new RegisterEntity();

				registerEntity = registerRepo.findAByDate(new Date());

				LeoLogger.info("ReturnserviceImpl>>>addReturn>>> Register entry found for today  refund....."
						+ registerEntity.getRefunds());
				if (registerEntity != null)

				{
					LeoLogger.info("ReturnserviceImpl>>>addReturn>>> Register entry found for today  ....."
							+ registerEntity.getSalesamount());
					registerEntity.setReferenceno(registerEntity.getReferenceno() + "," + retenty.getReturnId());
					registerEntity.setRefunds(registerEntity.getRefunds()+ (returnEntity.getAmount()+returnEntity.getTax()));
					registerEntity.setClosingbal(registerEntity.getClosingbal()- (returnEntity.getAmount()+returnEntity.getTax()));

					registerRepo.save(registerEntity);
				}else
				{
					RegisterEntity newregisterEntity = new RegisterEntity();
					newregisterEntity.setCashinhand(1000.00);
					newregisterEntity.setDate(new Date());
					newregisterEntity.setCashpayment(0.00);
					newregisterEntity.setCreditcardpayment(0.00);
					newregisterEntity.setOpeningbal(1000.00);
					newregisterEntity.setClosingbal(1000.00- (returnEntity.getAmount()+returnEntity.getTax()));
					newregisterEntity.setChequepayment(0.00);
					newregisterEntity.setRefunds( (returnEntity.getAmount()+returnEntity.getTax()));
					newregisterEntity.setReferenceno(String.valueOf(retenty.getReturnId()));
					newregisterEntity.setSalesamount(1000.00);
					newregisterEntity.setStatus("Open");
					registerRepo.save(newregisterEntity);
				}
	           

				// **************************** Register Details Entry ended
				// ********************
				
				//***Register history Entry***
				applyRegisterhistoryreturn(retenty);

			
           }//else if

			resultVO.setMsgDescr("Return Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultVO;
	}
	
	private void applyRegisterhistoryreturncash(ReturnCashEntity retenty) {
		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(retenty.getAmount()+retenty.getTax());
		newregisterEntity.setReferenceno(retenty.getReferenceno());
		newregisterEntity.setSalesamount(0);
		newregisterEntity.setStatus("ReturnCash");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistoryreturn---New Registerhistory Entry made");
		
	}

	private void applyRegisterhistoryreturn(ReturnsEntity retenty) {
		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(retenty.getAmount()+retenty.getTax());
		newregisterEntity.setReferenceno(retenty.getReferenceno());
		newregisterEntity.setSalesamount(0);
		newregisterEntity.setStatus("Return");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistoryreturn---New Registerhistory Entry made");
		
	}
	
	private void applyRegisterhistoryreturnedit(ReturnsEntity returnEntity) {
		 //After editing/delete if the entry should go once then please uncomment the following
		/*List<RegisterhistoryEntity> registerList = registerhistoryRepo.findByreferenceno(returnEntity.getReferenceno());
		
		registerhistoryRepo.delete(registerEntity);*/
		
		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(returnEntity.getAmount()+returnEntity.getTax());
		newregisterEntity.setReferenceno(returnEntity.getReferenceno());
		newregisterEntity.setSalesamount(0);
		newregisterEntity.setStatus("Return Edit");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistoryreturnedit---New Registerhistory Entry made");
	}
	
	private void applyRegisterhistorydeletereturn(ReturnsEntity returnEntity) {
		 //After editing/delete if the entry should go once then please uncomment the following
		/*List<RegisterhistoryEntity> registerList = registerhistoryRepo.findByreferenceno(returnEntity.getReferenceno());
		
		registerhistoryRepo.delete(registerEntity);*/
		
		RegisterhistoryEntity newregisterEntity = new RegisterhistoryEntity();
		newregisterEntity.setCashinhand(1000.00);
		newregisterEntity.setDate(new Date());
		newregisterEntity.setCashpayment(0.00);
		newregisterEntity.setCreditcardpayment(0.00);
		newregisterEntity.setOpeningbal(1000.00);
		newregisterEntity.setClosingbal(1000.00);
		newregisterEntity.setChequepayment(0.00);
		newregisterEntity.setRefunds(0);
		newregisterEntity.setReferenceno(returnEntity.getReferenceno());
		newregisterEntity.setSalesamount(0);
		newregisterEntity.setStatus("Return Delete");
		registerhistoryRepo.save(newregisterEntity);

		LeoLogger.info("SaleServiceImpl--- applyRegisterhistorydeletereturn---New Registerhistory Entry made");
	}
	


	private void applyPayment(Long applysaleId, BigDecimal bd_orig_return_amount, ReturnsEntity retenty,
			int currentYear, int currentmonth) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setstatus("Return Payment");
		paymentEntity.setGrand_total(bd_orig_return_amount.doubleValue());
		paymentEntity.setMember_name(retenty.getMember_name());
		paymentEntity.setMember_id(retenty.getMemberid());
		paymentEntity.setPaymentdate(new Date());
		paymentEntity.setReferenceno(
				"ReturnPay" + currentYear + "/" + currentmonth + "/" + retenty.getReturnId() + "-" + applysaleId);
		paymentEntity.setPtype("CR");
		paymentEntity.setPref(retenty.getReturnId() + "-" + applysaleId);
		paymentEntity.setRsaleId(applysaleId);
		paymentEntity.setstatus("Credit Return");
		MemberUser memberUser = memberUserRepo.findById(retenty.getMemberid());
		paymentEntity.setCtype(memberUser.getCtype());

		paymentRepo.save(paymentEntity);

		if (retenty.getSaleid() != null && retenty.getSaleid() != "0") {
			retenty.setSaleid(retenty.getSaleid() + "," + applysaleId);
		} else {
			retenty.setSaleid(applysaleId.toString());
		}
		returnsRepo.save(retenty);

	}
	
	private void applyPaymentReturn(Long applysaleId, BigDecimal bd_orig_return_amount, ReturnsEntity retenty,
			int currentYear, int currentmonth,long applyReturn) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setstatus("Return Payment");
		if(applyReturn==1) {
		paymentEntity.setGrand_total(bd_orig_return_amount.doubleValue());
		}else {
			paymentEntity.setGrand_total(retenty.getAmount()+retenty.getTax());	
		}
		paymentEntity.setMember_name(retenty.getMember_name());
		paymentEntity.setMember_id(retenty.getMemberid());
		paymentEntity.setPaymentdate(new Date());
		paymentEntity.setReferenceno(
				"ReturnPay" + currentYear + "/" + currentmonth + "/" + retenty.getReturnId() + "-" + applysaleId);
		paymentEntity.setPtype("CR");
		paymentEntity.setPref(retenty.getReturnId() + "-" + applysaleId);
		paymentEntity.setRsaleId(applysaleId);
		if(applyReturn==1) {
		paymentEntity.setstatus("Credit Return--Payment");
		}else if(applyReturn==2) {
			paymentEntity.setstatus("Credit Return--Store");
		}
		MemberUser memberUser = memberUserRepo.findById(retenty.getMemberid());
		paymentEntity.setCtype(memberUser.getCtype());

		paymentRepo.save(paymentEntity);

		if (retenty.getSaleid() != null && retenty.getSaleid() != "0") {
			retenty.setSaleid(retenty.getSaleid() + "," + applysaleId);
		} else {
			retenty.setSaleid(applysaleId.toString());
		}
		returnsRepo.save(retenty);

	}

	private void applyPaymentFinancial(Long applysaleId, BigDecimal bd_grand_total, BigDecimal bd_orig_return_amount,
			ReturnsEntity retenty) {
		Date d = new Date();
		int year = d.getYear();
		int currentYear = year + 1900;
		int currentmonth = d.getMonth() + 1;

		retenty.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + retenty.getReturnId());

		// <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();

		// convert date to calendar and add 30 days
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);

		Calendar calendarInstance = Calendar.getInstance();
		calendarInstance.add(Calendar.DATE, 30);

		FinancialTransactionEntity ftrnLatest = financialTransactionsRepo
				.findTopByCustomerIdOrderByFanIdDesc(retenty.getMemberid());

		FinancialTransactionEntity ftrnEntity = new FinancialTransactionEntity();

		// ftEntity.setInvoideId(retenty.getReturnId());
		ftrnEntity.setInvoideId(applysaleId);
		ftrnEntity.setAmount(bd_orig_return_amount.doubleValue());
		ftrnEntity.setCustomerId(retenty.getMemberid());
		ftrnEntity.setCustomerName(retenty.getMember_name());
		ftrnEntity.setDate(new Date());
		ftrnEntity.setDueDate(calendarInstance.getTime());
		ftrnEntity.setType("Credit Return");
		ftrnEntity.setReferenceno(retenty.getSalereferenceno());

		if (ftrnLatest == null) {
			ftrnEntity.setBalance(bd_grand_total.doubleValue());
		} else {
			BigDecimal ftTotal = new BigDecimal(0.0);
			LeoLogger.info("ReturnserviceImpl>>>addReturn>>Latest Financial Transation balance  ....."
					+ ftrnLatest.getBalance());
			ftTotal = new BigDecimal(ftrnLatest.getBalance()).subtract(bd_orig_return_amount);
			ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
			ftrnEntity.setBalance(ftTotal.doubleValue());
		}

		financialTransactionsRepo.save(ftrnEntity);

		// ************************ Financial transaction Entry ended
		// *******************

		// ****FT entry started****
		FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(retenty.getMemberid());

		FTEntity fttEntity = new FTEntity();
		fttEntity.setInvoideId(applysaleId);
		fttEntity.setAmount(bd_orig_return_amount.doubleValue());
		fttEntity.setCustomerId(retenty.getMemberid());
		fttEntity.setCustomerName(retenty.getMember_name());
		fttEntity.setDate(new Date());
		fttEntity.setDueDate(calendarInstance.getTime());
		fttEntity.setType("Credit Return");
		fttEntity.setReferenceno(retenty.getSalereferenceno());

		if (fttLatest == null) {
			fttEntity.setBalance(bd_grand_total.doubleValue());
		} else {
			BigDecimal fttTotal = new BigDecimal(0.0);
			LeoLogger.info("ReturnserviceImpl>>>addReturn>>Latest Financial Transation balance  ....."
					+ fttLatest.getBalance());
			fttTotal = new BigDecimal(fttLatest.getBalance()).subtract(bd_orig_return_amount);
			fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
			fttEntity.setBalance(fttTotal.doubleValue());
		}

		fTRepo.save(fttEntity);
		// ****FT entry ended***

	}
	
	private void applyPaymentReturnFinancial(Long applysaleId, BigDecimal bd_grand_total, BigDecimal bd_orig_return_amount,
			ReturnsEntity retenty,long applyReturn) {
		Date d = new Date();
		int year = d.getYear();
		int currentYear = year + 1900;
		int currentmonth = d.getMonth() + 1;

		retenty.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + retenty.getReturnId());

		// <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

		// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();

		// convert date to calendar and add 30 days
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate);

		Calendar calendarInstance = Calendar.getInstance();
		calendarInstance.add(Calendar.DATE, 30);

		FinancialTransactionEntity ftrnLatest = financialTransactionsRepo
				.findTopByCustomerIdOrderByFanIdDesc(retenty.getMemberid());

		FinancialTransactionEntity ftrnEntity = new FinancialTransactionEntity();

		// ftEntity.setInvoideId(retenty.getReturnId());
		ftrnEntity.setInvoideId(applysaleId);
		ftrnEntity.setAmount(bd_orig_return_amount.doubleValue());
		ftrnEntity.setCustomerId(retenty.getMemberid());
		ftrnEntity.setCustomerName(retenty.getMember_name());
		ftrnEntity.setDate(new Date());
		ftrnEntity.setDueDate(calendarInstance.getTime());
		if(applyReturn==1) {
			ftrnEntity.setType("Credit Return--Payment");
		}else if(applyReturn==2) {
			ftrnEntity.setType("Credit Return--Store");
		}
		ftrnEntity.setReferenceno(retenty.getSalereferenceno());

		if (ftrnLatest == null) {
			if(applyReturn==1) {
			ftrnEntity.setBalance(bd_grand_total.doubleValue());
			}else {
				ftrnEntity.setBalance(0);				
			}
		} else {
			if(applyReturn==1) {
			BigDecimal ftTotal = new BigDecimal(0.0);
			LeoLogger.info("ReturnserviceImpl>>>addReturn>>Latest Financial Transation balance  ....."
					+ ftrnLatest.getBalance());
			ftTotal = new BigDecimal(ftrnLatest.getBalance()).subtract(bd_orig_return_amount);
			ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
			ftrnEntity.setBalance(ftTotal.doubleValue());
			}else {
				BigDecimal ftTotal = new BigDecimal(0.0);
				LeoLogger.info("ReturnserviceImpl>>>addReturn>>Latest Financial Transation balance  ....."
						+ ftrnLatest.getBalance());
				ftTotal = new BigDecimal(ftrnLatest.getBalance());
				ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
				ftrnEntity.setBalance(ftTotal.doubleValue());
								
			}
		}

		financialTransactionsRepo.save(ftrnEntity);

		// ************************ Financial transaction Entry ended
		// *******************

		// ****FT entry started****
		FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(retenty.getMemberid());

		FTEntity fttEntity = new FTEntity();
		fttEntity.setInvoideId(applysaleId);
		fttEntity.setAmount(bd_orig_return_amount.doubleValue());
		fttEntity.setCustomerId(retenty.getMemberid());
		fttEntity.setCustomerName(retenty.getMember_name());
		fttEntity.setDate(new Date());
		fttEntity.setDueDate(calendarInstance.getTime());
		if(applyReturn==1) {
			fttEntity.setType("Credit Return--Payment");
		}else if(applyReturn==2) {
			fttEntity.setType("Credit Return--Store");
		}
		fttEntity.setReferenceno(retenty.getSalereferenceno());

		if (fttLatest == null) {
			if(applyReturn==1) {
			fttEntity.setBalance(bd_grand_total.doubleValue());
			}else {
				fttEntity.setBalance(0);				
			}
		} else {
			if(applyReturn==1) {
			BigDecimal fttTotal = new BigDecimal(0.0);
			LeoLogger.info("ReturnserviceImpl>>>addReturn>>Latest Financial Transation balance  ....."
					+ fttLatest.getBalance());
			fttTotal = new BigDecimal(fttLatest.getBalance()).subtract(bd_orig_return_amount);
			fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
			fttEntity.setBalance(fttTotal.doubleValue());
			}else {
				BigDecimal fttTotal = new BigDecimal(0.0);
				LeoLogger.info("ReturnserviceImpl>>>addReturn>>Latest Financial Transation balance  ....."
						+ fttLatest.getBalance());
				fttTotal = new BigDecimal(fttLatest.getBalance());
				fttTotal = fttTotal.setScale(2, RoundingMode.HALF_UP);
				fttEntity.setBalance(fttTotal.doubleValue());
			}
		}

		fTRepo.save(fttEntity);
		// ****FT entry ended***

	}

	@Override
	public List<ReturnPojo> getReturnsList() {
		List<ReturnsEntity> returnsEntityList = new ArrayList<ReturnsEntity>();
		List<ReturnPojo> returnPojoList = new ArrayList<ReturnPojo>();
		try {
			// LeoLogger.info("in Returns");
			returnsEntityList = returnsRepo.findAllByOrderByReturnIdDesc();
			for (ReturnsEntity returnEntityRes : returnsEntityList) {

				ReturnPojo returnPojo = new ReturnPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public List<ReturnItemPojo> getReturnItembyreturnId(String returnId) {
		List<ReturnItemEntity> returnsItemEntityList = returnItemRepo.findByReturnidOrderByIdAsc(Long.parseLong(returnId));
		List<ReturnItemPojo> returnItemPojoList = new ArrayList<ReturnItemPojo>();
		try {
			// LeoLogger.info("in Return get Items by returnId");

			for (ReturnItemEntity returnsItemEntityRes : returnsItemEntityList) {

				UnitEntity unitentity = unitRepo.findById(returnsItemEntityRes.getUnitid());
				ReturnItemPojo returnItemPojo = new ReturnItemPojo();
				BigDecimal qty = returnsItemEntityRes.getQuantity();
				BigDecimal unit = (unitentity.getQuantity());
				returnItemPojo = mapper.map(returnsItemEntityRes, ReturnItemPojo.class);
				if (!returnsItemEntityRes.getUnitname().equalsIgnoreCase("Piece")) {
					qty.divide(unit, 4, RoundingMode.HALF_UP);
					BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);
					returnItemPojo.setQuantity(result);
					LeoLogger.info("ReturnServiceImpl---getReturnItembyreturnId--Printing result>>>>>>>>" + qty);

					LeoLogger.info("ReturnServiceImpl---getReturnItembyreturnId---Printing Quantity  after>>>>>>>>"
							+ returnItemPojo.getQuantity());
				} else {
					returnItemPojo.setQuantity(qty);
					LeoLogger.info("ReturnServiceImpl---getReturnItembyreturnId---Printing Quantity  after>>>>>>>>"
							+ returnItemPojo.getQuantity());
				}
				// returnItemPojo = mapper.map(returnsItemEntityRes, ReturnItemPojo.class);
				returnItemPojoList.add(returnItemPojo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		LeoLogger.info("returnItemPojoList ....." + returnItemPojoList.toString());
		return returnItemPojoList;
	}

	@Override
	public List<ReturnsEntity> getReturnItembymemberId(long memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReturnPojo> getReturnsListbyMemberId(long memberId) {
		List<ReturnsEntity> returnEntityList = returnsRepo.findAllBymemberidOrderByReturnIdDesc(memberId);
		List<ReturnPojo> returnPojoList = new ArrayList<ReturnPojo>();
		try {
			LeoLogger.info("ReturnServiceImpl---getReturnsListbyMemberId--");
			for (ReturnsEntity returnEntityRes : returnEntityList) {
				
				ReturnPojo ReturnPojo = new ReturnPojo();
				ReturnPojo = mapper.map(returnEntityRes, ReturnPojo.class);
				returnPojoList.add(ReturnPojo);
					LeoLogger.info("ReturnServiceImpl---getReturnsListbyMemberId--in  getReturnsListbyMemberId"
							+ returnPojoList.toString());
					
				

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public ReturnPojo getreturnbyreturnId(String returnId) {
		ReturnsEntity returnEntity = new ReturnsEntity();
		returnEntity = returnsRepo.findByReturnId(Long.parseLong(returnId));
		LeoLogger.info("ReturnserviceImpl---getsalebySaleId---" + returnEntity.toString());
		ReturnPojo returnPojo = new ReturnPojo();
		returnPojo = mapper.map(returnEntity, ReturnPojo.class);
		returnPojo.setCreatedBy(returnEntity.getCreatedBy());
		return returnPojo;
	}

	@Override
	public UserRegistrationPojo getMemberByMemberid(long memberid) {

		MemberUser memberEntity = memberUserRepo.findById(memberid);
		UserRegistrationPojo memberPojo = new UserRegistrationPojo();
		memberPojo = mapper.map(memberEntity, UserRegistrationPojo.class);
		return memberPojo;
	}

	@Override
	public ResultVO updateReturns(List<AddItemReqPojo> addItemReqPojos, double returnid) {
		// TODO Auto-generated method stub
		ResultVO resultVO = new ResultVO();
		try {
			
			Long returnId = 0L;
			returnId = (long) returnid;
			LeoLogger.info("ReturnserviceImpl--- Update Return with Return ID >>>>> " + returnid);
			// BigDecimal old_total = new BigDecimal(0.0);
			BigDecimal New_return_grand_total = new BigDecimal(0.0);
			BigDecimal Old_return_grand_total = new BigDecimal(0.0);
			BigDecimal Orig_old_return_total= new BigDecimal(0.0);
			double paid_amount = 0.0;
			
			ReturnsEntity returnEntity = returnsRepo.findByReturnId(returnId);
			LeoLogger.info("ReturnServiceImpl---UpdateReturns Retrun entity is >>>>> " + returnEntity.toString());
			
			List<PaymentEntity> paymentEntityList =new ArrayList<PaymentEntity>();
			PaymentEntity pety = new PaymentEntity();
			
			MemberUser memberUser = memberUserRepo.findById(returnEntity.getMemberid());
			LeoLogger.info("ReturnServiceImpl----UpdateReturns--Memberid >>>>>>>>>> " + returnEntity.getMemberid());
			
			
			//Revere Payments & Sales 
			
			reversePayments(returnEntity, paymentEntityList, memberUser);
			
			//payment credit return delete
			
			
			List<ReturnItemEntity> returnItemEntityList = new ArrayList<ReturnItemEntity>();
		
		   // Calculating the return total of old return
			
			if (returnId != null) {

				LeoLogger.info("ReturnServiceImpl---if condition---returnEntity found >>> " + returnEntity.toString());
				returnItemEntityList = returnItemRepo.findByReturnid(returnEntity.getReturnId());
				LeoLogger.info("ReturnServiceImpl--updateReturns---if condition---returnItemEntityList found >>>> "
						+ returnItemEntityList.toString());
				Old_return_grand_total = new BigDecimal(returnEntity.getAmount() + returnEntity.getTax());
				Orig_old_return_total = new BigDecimal(returnEntity.getAmount() + returnEntity.getTax());
				LeoLogger.info("ReturnServiceImpl--updateReturns----Total Previous/Old Return amount Stored in variable Old_return_grand_total is >>>>> "
						+ Old_return_grand_total);
				
			}
			
			// ************************************************************************************************************************
			//Reverse Register 
			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());


			if (registerEntity != null)

			{
				
				registerEntity.setRefunds(registerEntity.getRefunds()-Old_return_grand_total.doubleValue());
				registerEntity.setClosingbal(registerEntity.getClosingbal()+Old_return_grand_total.doubleValue());
				registerRepo.save(registerEntity);
			}
			
		// Setting Return Quantity in sales items to previous State
			
		//	setreturnqtySales(addItemReqPojos, memberUser);

				
			// ************************************************************************************************************************
			
			// Adjusting Inventory by Restoring the state before the return
								
			restoreInventory(returnItemEntityList);
			
			// ************************************************************************************************************************
			
			// Delete all returns items in returnitems table against the sale id

			returnItemRepo.deleteAll(returnItemEntityList);
			LeoLogger.info("ReturnServiceImpl---updateReturn >>>>>>>> All Return items deleted associated with the return");
			
			// ************************************************************************************************************************

			
			
			
			double tax_rate = 0, total = 0, unit_price = 0, return_amount = 0, paid_total = 0;
			int quantity = 0;
			Long applysaleId = 0L;

			// Declare BigDecimal equivalents
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			BigDecimal bd_rettotal = new BigDecimal(0.0);
			BigDecimal bd_return_amount = new BigDecimal(0.0);
			BigDecimal bd_new_return_amount = new BigDecimal(0.0);
			BigDecimal Diff_amount = new BigDecimal(0.0);
			BigDecimal bd_paid_total = new BigDecimal(0.0);

			for (AddItemReqPojo additem : addItemReqPojos) {
				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());

				memberPojo = memberUserRepo.findById(additem.getCustomerId());
				
				applysaleId = additem.getApplysaleId();

				unit_price = additem.getPrice().doubleValue();
				quantity = Integer.parseInt(additem.getQuantity());

				returnEntity.setNote(additem.getNote());
				bd_total =additem.getSubtotal();
				bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
				returnEntity.setAmount(bd_total.doubleValue());
				LeoLogger.info("ReturnserviceImpl>>updateReturns>>Total....." + bd_total);

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (additem.getSubtotal().doubleValue() * 0.125 );

				// saleEntity.setUser_id();
			}

			LeoLogger.info(
					"ReturnserviceImpl >>>>> updateReturns >>> Apply Invoice saleId is >>> " + applysaleId.toString());
			bd_tax_rate = new BigDecimal(tax_rate);
			bd_tax_rate = bd_tax_rate.setScale(2, RoundingMode.HALF_UP);
			returnEntity.setTax(bd_tax_rate.doubleValue());
			//LeoLogger.info("ReturnserviceImpl >>>>>updateReturns>>>Tax Rate....." + bd_tax_rate);

			bd_total = new BigDecimal(total);
			bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
			returnEntity.setAmount(bd_total.doubleValue());
			//LeoLogger.info("ReturnserviceImpl>>updateReturns>>Total....." + bd_total);

			bd_rettotal = bd_total.add(bd_tax_rate);
			bd_rettotal = bd_rettotal.setScale(2);
			returnEntity.setUserid(bd_rettotal.doubleValue());
			//LeoLogger.info("ReturnserviceImpl>>>updateReturns>>>Total....." + bd_rettotal);
			New_return_grand_total = bd_rettotal;

			returnsRepo.save(returnEntity);
			// returnsRepo.save(returnEntity);
			UnitEntity unitentity = new UnitEntity();
			MemberUser memberPojo = memberUserRepo.findById(returnEntity.getMemberid());

			return_amount = total + tax_rate;
			bd_return_amount = new BigDecimal(total).add(new BigDecimal(tax_rate));
			bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
			bd_new_return_amount = bd_return_amount;
			LeoLogger.info("ReturnserviceImpl  >>updateReturns  >> Return Total....." + bd_return_amount);
			LeoLogger.info("ReturnserviceImpl  >> Update Returns  >> Total Return Amount to be applied ....." + return_amount);
			
			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;
			
			// ********** Adjust the Return amount against the invoices  ( Same as Add return )    *******************
			
		  	if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {

				LeoLogger.info("ReturnserviceImpl>>>> Update Return >>>Member pojo is not a special customer " + memberPojo.getCtype());
				// for (SalesEntity salesEntityRes : salesEntityList) {
				SalesEntity saleapplyEntity = salesRepo.findBySaleId(applysaleId);
				//SalesItemEntity saleitemEntity = salesItemRepo.findBySaleid(applysaleId);
				//LeoLogger.info("ReturnserviceImpl>>>addReturn>>Sales Status" + saleitemEntity);
				LeoLogger.info("ReturnserviceImpl>>> > Update Return >>Sales Status" + saleapplyEntity.getSale_status());
				//returnEntity.setSalereferenceno(saleapplyEntity.getReferenceno());
				returnsRepo.save(returnEntity);
				
				if ((return_amount >= (saleapplyEntity.getGrand_total() - saleapplyEntity.getPaid()))
								&& saleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due"))
				  {
					// Addjust Sales entity paid value for hthe selected
					paid_total = saleapplyEntity.getGrand_total() - saleapplyEntity.getPaid();
					LeoLogger.info("ReturnServiceImpl !Speical -- Update Return >> Sale apply entity balance amount is less than due amount ### Paid Total here is  " + paid_total);
					saleapplyEntity.setPaid(saleapplyEntity.getGrand_total());
					saleapplyEntity.setPaymentstatus("Paid");
					salesRepo.save(saleapplyEntity);
					bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
					bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
					return_amount = return_amount - paid_total;
					//returnEntity.setSaleid(applysaleId.toString());
					applyPayment(applysaleId, bd_paid_total, returnEntity, currentYear, currentmonth);
					LeoLogger.info(	"ReturnServiceImpl ### Update Return >> Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());

				} 
				else if (saleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due")) 
				 {
     				paid_total = saleapplyEntity.getPaid();
					LeoLogger.info("ReturnServiceImpl ! NotSpeical Update Return-- ### Sale apply entity balance amount is greater than the return amount ### Paid Total here  "
							+ paid_total);
					LeoLogger.info("ReturnServiceImpl ### Adjustung sale with return amount and the value is  "
									+ bd_return_amount);
					paid_total = paid_total + bd_return_amount.doubleValue();
					bd_paid_total = new BigDecimal(paid_total);
					bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
					saleapplyEntity.setPaid(bd_paid_total.doubleValue());
					bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
					if(saleapplyEntity.getGrand_total() <= saleapplyEntity.getPaid())
						saleapplyEntity.setPaymentstatus("Paid");
					//	returnEntity.setSaleid(applysaleId.toString());
					salesRepo.save(saleapplyEntity);
					applyPayment(applysaleId, bd_return_amount, returnEntity, currentYear, currentmonth);
					return_amount = 0;
					bd_return_amount = new BigDecimal(0.0);
					LeoLogger.info(	"ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());
				 }
						
     			if (bd_return_amount.compareTo(new BigDecimal(0.0)) > 0) 
				 {
					LeoLogger.info("ReturnserviceImpl >>> Update Return>> !Speical -- ### Return amount is greater than 0 after applying to Applies sale ### Paid Total here  >>>"
							+ bd_return_amount.toPlainString());
							// Adjusting it to pending invoices if any
					List<SalesEntity> salesEntityList = salesRepo.findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(memberPojo.getId(), "Due");
					LeoLogger.info("ReturnserviceImpl>>> Update Return >>Sales list in ascending order >> "	+ salesEntityList.toString());

							// set deposit of the customer with balance amount
	     	   for (SalesEntity salesEntityRes : salesEntityList) {

 				 if (return_amount >= (salesEntityRes.getGrand_total() - salesEntityRes.getPaid()))
					{
									// Addjust Sales entity paid value for hthe selected
						paid_total = salesEntityRes.getGrand_total() - salesEntityRes.getPaid();
						LeoLogger.info("ReturnServiceImpl ### Update Return >> !Speical Sale id >>> " + salesEntityRes.getSaleId());
						LeoLogger.info("ReturnServiceImpl ### Update Return >> !Speical -- ### Return amount is greater than the sale balance ### Paid Total here  >>>  " + paid_total);
						salesEntityRes.setPaid(salesEntityRes.getGrand_total());
						salesEntityRes.setPaymentstatus("Paid");
						salesEntityRes.setSale_status("Paid");
						salesRepo.save(salesEntityRes);
						bd_paid_total = new BigDecimal(paid_total);
						bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
						applyPayment(salesEntityRes.getSaleId(), bd_paid_total, returnEntity, currentYear, currentmonth);
						bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
						return_amount = return_amount - paid_total;
						LeoLogger.info(	"ReturnServiceImpl ### Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());

					} else if (salesEntityRes.getPaymentstatus().equalsIgnoreCase("Due")) {

						paid_total = salesEntityRes.getPaid();
						LeoLogger.info("ReturnServiceImpl ### !Speical -- ### Return amount is less than the sale balance ### Paid Total here  "
													+ paid_total);
						LeoLogger.info("ReturnServiceImpl ### Adjustung sale with return amount and the value is  "
									+ bd_return_amount);
						paid_total = paid_total + bd_return_amount.doubleValue();
						bd_paid_total = new BigDecimal(paid_total);
						bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
						salesEntityRes.setPaid(bd_paid_total.doubleValue());
						if(salesEntityRes.getGrand_total() == salesEntityRes.getPaid())
							salesEntityRes.setPaymentstatus("Paid");
							salesRepo.save(salesEntityRes);
							applyPayment(salesEntityRes.getSaleId(), bd_return_amount, returnEntity, currentYear, currentmonth);
							return_amount = 0;
							bd_return_amount = new BigDecimal(0.0);
							LeoLogger.info(	"ReturnServiceImpl ### Update return >> Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());
							break;
     					}
					}

	     			}
						
					} else {

						LeoLogger.info("ReturnserviceImpl>>>> Update Return >>>>>>>> Reached Special sales else part");

						LeoLogger.info("ReturnserviceImpl>>>> Update Return >>>Member pojo " + memberPojo.getCtype());
						// for (SalesEntity salesEntityRes : salesEntityList) {
						SpecialSalesEntity specialsaleapplyEntity = specialsalesRepo.findBySaleId(applysaleId);
						
						//returnEntity.setSalereferenceno(specialsaleapplyEntity.getReferenceno());
				
					// LeoLogger.info("addReturn >>>>>>>> saleEntity is >>> " +
						// saleEntity.toString());

						if (return_amount >= (specialsaleapplyEntity.getGrand_total() - specialsaleapplyEntity.getPaid())
								&& specialsaleapplyEntity.getSale_status().equalsIgnoreCase("Due"))

						{
							// Addjust Sales entity paid value for hthe selected

							paid_total = specialsaleapplyEntity.getGrand_total() - specialsaleapplyEntity.getPaid();
							LeoLogger.info("ReturnServiceImpl ### Update Return >> Speical Sale apply entity balance amount is greater than the return amount ### Paid Total here is  " + paid_total);
							specialsaleapplyEntity.setPaid(specialsaleapplyEntity.getGrand_total());
							specialsaleapplyEntity.setSale_status("Paid");
							specialsalesRepo.save(specialsaleapplyEntity);
							bd_paid_total = new BigDecimal(paid_total);
							bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
							applyPayment(applysaleId, bd_paid_total, returnEntity, currentYear, currentmonth);
							return_amount = return_amount - paid_total;
							bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
							LeoLogger.info(	"ReturnServiceImpl ### Update Return >> Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());
							
							
						} else if (specialsaleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due")) {

							paid_total = specialsaleapplyEntity.getPaid();
							LeoLogger.info("ReturnServiceImpl ### Update Return >> Speical Sale apply entity balance amount is less than the return amount ### Paid Total here is  "
									+ paid_total);
							LeoLogger.info("ReturnServiceImpl ### Update Return >> Adjustung sale with return amount and the value is  "
									+ bd_return_amount);
							paid_total = paid_total + bd_return_amount.doubleValue();
							bd_paid_total = new BigDecimal(paid_total);
							bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);					
							specialsaleapplyEntity.setPaid(bd_paid_total.doubleValue());
							if(specialsaleapplyEntity.getGrand_total() == specialsaleapplyEntity.getPaid())
							specialsaleapplyEntity.setSale_status("Paid");
							specialsalesRepo.save(specialsaleapplyEntity);
							applyPayment(applysaleId, bd_return_amount, returnEntity, currentYear, currentmonth);
							return_amount = 0;
							bd_return_amount = new BigDecimal(0.0);
							LeoLogger.info(	"ReturnServiceImpl ### Update Return >> Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());

						}

						returnsRepo.save(returnEntity);

						
						if (bd_return_amount.compareTo(new BigDecimal(0.0)) > 0) {
							LeoLogger.info("ReturnserviceImpl>>> Update Return >> Return amount is still left for Special sales >>>"
									+ bd_return_amount.toPlainString());
							// Adjusting it to pending invoices if any
							List<SpecialSalesEntity> specialsalesEntityList = specialsalesRepo.findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(memberPojo.getId(), "Due");
							LeoLogger.info("ReturnserviceImpl>>> Update Return >> Sales list in ascending order "
									+ specialsalesEntityList.toString());

							// set deposit of the customer with balance amount
							for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

								if (return_amount >= (specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid()))

								{
									// Addjust Sales entity paid value for hthe selected

									paid_total = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
									LeoLogger.info("ReturnServiceImpl ### Update Return >> Paid Total here is  " + paid_total);
									specialsalesEntityRes.setPaid(specialsalesEntityRes.getGrand_total());
									specialsalesEntityRes.setPaymentstatus("Paid");
									specialsalesRepo.save(specialsalesEntityRes);
									bd_paid_total = new BigDecimal(paid_total);
									bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);
									applyPayment(specialsalesEntityRes.getSaleId(), bd_paid_total, returnEntity, currentYear, currentmonth);
									bd_return_amount = bd_return_amount.subtract(new BigDecimal(paid_total));
									LeoLogger.info(	"ReturnServiceImpl ### Update Return >> Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());

								} else if (specialsalesEntityRes.getPaymentstatus().equalsIgnoreCase("Due")) {

									paid_total = specialsalesEntityRes.getPaid();
									LeoLogger.info(
											"ReturnServiceImpl ### Update Return >> Return amount was less so total payment done so far is  "
													+ paid_total);
									LeoLogger.info("ReturnServiceImpl ### Update Return >> Adjustung sale with return amount and the value is  "
											+ bd_return_amount);
									paid_total = paid_total + bd_return_amount.doubleValue();
									bd_paid_total = new BigDecimal(paid_total);
									bd_paid_total = bd_paid_total.setScale(2, RoundingMode.HALF_UP);	
									specialsalesEntityRes.setPaid(bd_paid_total.doubleValue());
									if(specialsalesEntityRes.getGrand_total() == specialsaleapplyEntity.getPaid())
									specialsalesEntityRes.setSale_status("Paid");
									specialsalesRepo.save(specialsalesEntityRes);
									applyPayment(specialsalesEntityRes.getSaleId(), bd_return_amount, returnEntity, currentYear, currentmonth);
									return_amount = 0;
									bd_return_amount = new BigDecimal(0.0);
									LeoLogger.info(	"ReturnServiceImpl ### Update Return >> Return amount Left after applying is >>>>>>>   " + bd_return_amount.toString());
									break;

								}
							}

						}
					}
	
		  // *********************************      END Return amount adjustment end                   ****************************************	   
	
		 // Add Return Items in Returnitem table and Adjust new quuantities in product table
		  	
		// this loop is for sales breakdown
		  	
		addReturnItemNAdjustProducts(addItemReqPojos, returnEntity, bd_total, unitentity, memberPojo);
		  	
	    // *********************************   Add Return Items in Returnitem table and Adjust new quuantities          ****************************************	
		  	
		  	
		  	
		  // <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

			// DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date currentDate = new Date();

			// convert date to calendar and add 30 days
			Calendar c = Calendar.getInstance();
			c.setTime(currentDate);

			Calendar calendarInstance = Calendar.getInstance();
			calendarInstance.add(Calendar.DATE, 30);

				
			String doubleVar = returnEntity.getSaleid();
		//	long id = (long) doubleVar;
		//	LeoLogger.info("ReturnserviceImpl---updateReturns--id  ....." + id);

			

			if (bd_new_return_amount.compareTo(Orig_old_return_total) > 0) {
			
				BigDecimal difference = new BigDecimal(0.0);
				BigDecimal balance = new BigDecimal(0.0);
				BigDecimal fnamount = new BigDecimal(0.0);
				difference = bd_new_return_amount.subtract(Orig_old_return_total);
				LeoLogger.info(	"ReturnServiceImpl ### Update Return >> New Return amount is > old return amount with Difference >>>>>>>   " + difference.toString());
				FinancialTransactionEntity ftransLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(returnEntity.getMemberid());
				balance = new BigDecimal(ftransLatest.getBalance()).subtract(difference);
				fnamount = new BigDecimal(ftransLatest.getAmount()).add(difference);
				balance = balance.setScale(2, RoundingMode.HALF_UP);
				fnamount = fnamount.setScale(2, RoundingMode.HALF_UP);
				
				ftransLatest.setAmount(fnamount.doubleValue());
				ftransLatest.setBalance(balance.doubleValue());
				financialTransactionsRepo.save(ftransLatest);
				
				FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(returnEntity.getMemberid());
				fttLatest.setAmount(fnamount.doubleValue());
				fttLatest.setBalance(balance.doubleValue());
				fTRepo.save(fttLatest);
				
				if(memberPojo.getCreditpayment()>0) {
					memberPojo.setCreditpayment(memberPojo.getCreditpayment() + difference.doubleValue());
					memberUserRepo.save(memberPojo);
				}
				
				
			} else if (bd_new_return_amount.compareTo(Orig_old_return_total) < 0) {
			
				BigDecimal difference = new BigDecimal(0.0);
				BigDecimal balance = new BigDecimal(0.0);
				BigDecimal fnamount = new BigDecimal(0.0);
				difference = Orig_old_return_total.subtract(bd_new_return_amount);
				LeoLogger.info(	"ReturnServiceImpl ### Update Return >> New Return amount is > old return amount with Difference >>>>>>>   " + difference.toString());
				FinancialTransactionEntity ftransLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(returnEntity.getMemberid());
				balance = new BigDecimal(ftransLatest.getBalance()).add(difference);
				fnamount = new BigDecimal(ftransLatest.getAmount()).subtract(difference);
				balance = balance.setScale(2, RoundingMode.HALF_UP);
				fnamount = fnamount.setScale(2, RoundingMode.HALF_UP);
				
				ftransLatest.setAmount(fnamount.doubleValue());
				ftransLatest.setBalance(balance.doubleValue());
				
				financialTransactionsRepo.save(ftransLatest);
				
				FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(returnEntity.getMemberid());
				fttLatest.setAmount(fnamount.doubleValue());
				fttLatest.setBalance(balance.doubleValue());
				fTRepo.save(fttLatest);
				
				if(memberPojo.getCreditpayment()>0) {
					memberPojo.setCreditpayment(memberPojo.getCreditpayment() - difference.doubleValue());
					memberUserRepo.save(memberPojo);
					if(memberPojo.getCreditpayment()<0)
					{
						memberPojo.setCreditpayment(0);
						memberUserRepo.save(memberPojo);
					}
				}
				
				
			}
			
			// ******************************************************** Return amount adjustment end *****************************************
			
	

			
			// enterCreditPayment(applysaleId, bd_orig_return_amount, retenty, currentYear, currentmonth);

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details >>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			RegisterEntity rregisterEntity = new RegisterEntity();

			rregisterEntity = registerRepo.findAByDate(new Date());

			if (rregisterEntity != null)

			{
				rregisterEntity.setRefunds(rregisterEntity.getRefunds()+New_return_grand_total.doubleValue());
				rregisterEntity.setClosingbal(rregisterEntity.getClosingbal()-New_return_grand_total.doubleValue());
				registerRepo.save(rregisterEntity);
			}

			// **************************** Register Details Entry ended  ********************
			//***Register history Entry***
			applyRegisterhistoryreturnedit(returnEntity);

			resultVO.setMsgDescr("Return Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}


	private void addReturnItemNAdjustProducts(List<AddItemReqPojo> addItemReqPojos, ReturnsEntity returnEntity,
			BigDecimal bd_total, UnitEntity unitentity, MemberUser memberPojo) {
		BigDecimal bd_grand_total;
		BigDecimal bd_real_unit_price;
		BigDecimal bd_subtotal;
		for (AddItemReqPojo additem : addItemReqPojos) {

			ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

			if (additem.getUnit() != null && additem.getUnit() != "") {
				unitentity = unitRepo.findByUnitname(additem.getRoll());
			}
			
		
			BigDecimal price = productDetailsEnt.getprice();
			long qt = Long.parseLong(additem.getQuantity());
			BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(qt));
			BigDecimal tax = totalPrice.multiply(BigDecimal.valueOf(0.125));
			LeoLogger.info("SaleServiceImpl---addSale--price." + price);
			LeoLogger.info("SaleServiceImpl---addSale--totalPrice." + totalPrice);

			//tax = (additem.getPrice() * Long.parseLong(additem.getQuantity())) * .125;

			ReturnItemEntity returnItemEntity = new ReturnItemEntity();
			returnItemEntity.setReturnid(returnEntity.getReturnId());
			returnItemEntity.setProductid(additem.getProductId());
			returnItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
			returnItemEntity.setTax(String.valueOf(additem.getPrice().doubleValue() * 0.125));
			returnItemEntity.setProduct_name(additem.getProductName());
			// returnItemEntity.setReturnqty(additem.getQuantity());

			bd_real_unit_price =additem.getPrice();
			bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
			returnItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());

			bd_subtotal = additem.getSubtotal();
			bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
			returnItemEntity.setSubtotal(bd_subtotal.doubleValue());

			bd_grand_total = bd_subtotal.add(additem.getPrice().multiply(new BigDecimal(0.125)));
			bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
			returnItemEntity.setTotal(bd_grand_total.doubleValue());
			LeoLogger.info("ReturnserviceImpl>> Update Return >>Total....." + bd_total);

			returnItemEntity.setTax((tax).toString());
			returnItemEntity.setUnitid(unitentity.getId());
			returnItemEntity.setUnitname(additem.getRoll());

			returnItemRepo.save(returnItemEntity);
			// salesItemRepo.save(saleitemEntity);

			if (!additem.getRoll().equalsIgnoreCase("Piece")) {
				BigDecimal qty = new BigDecimal(additem.getQuantity());
				LeoLogger.info("ReturnserviceImpl>>addReturn>>" + unitentity.getQuantity());
				BigDecimal unit =( unitentity.getQuantity());
				LeoLogger.info(qty.toString());
				LeoLogger.info(unit.toString());
				qty = qty .multiply(unit) ;
				returnItemEntity.setQuantity(qty);

				LeoLogger.info("ReturnserviceImpl---addReturn--qt....." + qty);
			} else {
				BigDecimal qty = new BigDecimal(additem.getQuantity());
				returnItemEntity.setQuantity(qty);
				returnItemRepo.save(returnItemEntity);
			}

			// Subtracting Quantities here from products

			if (!unitentity.getUnitname().equalsIgnoreCase("Piece")) {
				BigDecimal qty = new BigDecimal(additem.getQuantity());
				BigDecimal unit =( unitentity.getQuantity());
				LeoLogger.info(qty.toString());
				qty = qty .multiply(unit) ;
				LeoLogger.info(unit.toString());
				productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(qty) );

			}

			else {
				BigDecimal qty = new BigDecimal(additem.getQuantity());
				productDetailsEnt
						.setQuantity(productDetailsEnt.getQuantity() .add(qty) );

			}
			productDetailsRepo.save(productDetailsEnt);

		}
	}
	
	private void addReturncashItemNAdjustProducts(List<AddItemReqPojo> addItemReqPojos, ReturnCashEntity returnEntity,
			BigDecimal bd_total, UnitEntity unitentity, MemberUser memberPojo) {
		BigDecimal bd_grand_total;
		BigDecimal bd_real_unit_price;
		BigDecimal bd_subtotal;
		for (AddItemReqPojo additem : addItemReqPojos) {

			ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

			if (additem.getUnit() != null && additem.getUnit() != "") {
				//unitentity = unitRepo.findByUnitname(additem.getRoll());
				unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
			}
			
		
			BigDecimal price = productDetailsEnt.getprice();
			long qt = Long.parseLong(additem.getQuantity());
			BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(qt));
			BigDecimal tax = totalPrice.multiply(BigDecimal.valueOf(0.125));
			LeoLogger.info("SaleServiceImpl---addSale--price." + price);
			LeoLogger.info("SaleServiceImpl---addSale--totalPrice." + totalPrice);

			//tax = (additem.getPrice() * Long.parseLong(additem.getQuantity())) * .125;

			ReturnCashItemsEntity returnItemEntity = new ReturnCashItemsEntity();
			returnItemEntity.setReturnid(returnEntity.getReturnId());
			returnItemEntity.setProductid(additem.getProductId());
			returnItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
			returnItemEntity.setTax(String.valueOf(additem.getPrice().doubleValue() * 0.125));
			returnItemEntity.setProduct_name(additem.getProductName());
			// returnItemEntity.setReturnqty(additem.getQuantity());

			bd_real_unit_price = additem.getPrice();
			bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
			returnItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());

			bd_subtotal = additem.getSubtotal();
			bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
			returnItemEntity.setSubtotal(bd_subtotal.doubleValue());

			bd_grand_total = bd_subtotal.add(additem.getPrice() .multiply(new BigDecimal(0.125)));
			bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
			returnItemEntity.setTotal(bd_grand_total.doubleValue());
			LeoLogger.info("ReturnserviceImpl>> Update Return >>Total....." + bd_total);

			returnItemEntity.setTax((tax).toString());
			returnItemEntity.setUnitid(unitentity.getId());
			returnItemEntity.setUnitname(additem.getRoll());

			returncashItemRepo.save(returnItemEntity);
			// salesItemRepo.save(saleitemEntity);

			if (!additem.getRoll().equalsIgnoreCase("Piece")) {
				BigDecimal qty = new BigDecimal(additem.getQuantity());
				LeoLogger.info("ReturnserviceImpl>>addReturn>>" + unitentity.getQuantity());
				BigDecimal unit = ( unitentity.getQuantity());
				LeoLogger.info(qty.toString());
				LeoLogger.info(unit.toString());
				qty = qty .multiply(unit) ;
				returnItemEntity.setQuantity(qty);

				LeoLogger.info("ReturnserviceImpl---addReturn--qt....." + qty);
			} else {
				returnItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
				returncashItemRepo.save(returnItemEntity);
			}

			// Subtracting Quantities here from products

			if (!unitentity.getUnitname().equalsIgnoreCase("Piece")) {
				BigDecimal qty =new BigDecimal(additem.getQuantity());
				BigDecimal unit =( unitentity.getQuantity());
				LeoLogger.info(qty.toString());
				qty = qty .multiply(unit) ;
				LeoLogger.info(unit.toString());
				productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(qty) );

			}

			else {
				BigDecimal qty =new BigDecimal(additem.getQuantity());
				productDetailsEnt
						.setQuantity(productDetailsEnt.getQuantity() .add(qty));
			}
			productDetailsRepo.save(productDetailsEnt);

		}
	}

	private void reversePayments(ReturnsEntity returnEntity, List<PaymentEntity> paymentEntityList,
			MemberUser memberUser) {
		PaymentEntity pety;
		String rsaleidarray = returnEntity.getSaleid();
		LeoLogger.info("ReturnServiceImpl---updateReturns--Sale ID Comma separated >>>" + rsaleidarray);
		// String CSV = "Google,Apple,Microsoft";

		StringTokenizer rsaleid = new StringTokenizer(rsaleidarray, ",");
		SalesEntity saleEnty = new SalesEntity();
		SpecialSalesEntity specialsaleEnty = new SpecialSalesEntity();

		while (rsaleid.hasMoreTokens()) {

			long longValue = (new Double(rsaleid.nextToken())).longValue();
			LeoLogger.info("ReturnServiceImpl---updateReturns--rsaleid >>>" + longValue);
			// pety = paymentRepo.findByrsaleIdAndStatus(longValue,"Credit Return");
			pety = paymentRepo.findByPref(returnEntity.getReturnId() + "-" + longValue);

			if (pety != null) {
				// Here find sale id by rsale id with condition of special customer and make the
				// status to due and subtract from paid amount.
				paymentEntityList.add(pety);
				LeoLogger.info("ReturnServiceImpl---updateReturns--paymentEntityList" + paymentEntityList.toString());
				// LeoLogger.info("ReturnServiceImpl---updateReturn --- Next Token >>> " +
				// rsaleid.nextToken());

				if (!memberUser.getCtype().equalsIgnoreCase("Special")) {

					saleEnty = salesRepo.findBySaleId(longValue);
					if (saleEnty != null) {

						BigDecimal revAmount = new BigDecimal(0.0);
						revAmount = new BigDecimal(saleEnty.getPaid()).subtract(new BigDecimal(pety.getGrand_total()));
						revAmount = revAmount.setScale(2, RoundingMode.HALF_UP);
						saleEnty.setPaid(revAmount.doubleValue());
						saleEnty.setPaymentstatus("Due");
						saleEnty.setSale_status("Due");
						salesRepo.save(saleEnty);
						LeoLogger.info("ReturnServiceImpl---updateReturns--Reversed Amount of >> " + revAmount
								+ " for Sale ID >> " + saleEnty.getSaleId());
					}
				} else {
					specialsaleEnty = specialsalesRepo.findBySaleId(longValue);
					if (specialsaleEnty != null) {
						BigDecimal revAmount = new BigDecimal(0.0);
						revAmount = new BigDecimal(specialsaleEnty.getPaid())
								.subtract(new BigDecimal(pety.getGrand_total()));
						revAmount = revAmount.setScale(2, RoundingMode.HALF_UP);
						specialsaleEnty.setPaid(revAmount.doubleValue());
						saleEnty.setPaymentstatus("Due");
						specialsaleEnty.setSale_status("Due");
						specialsalesRepo.save(specialsaleEnty);
						LeoLogger.info("ReturnServiceImpl---updateReturns--Reversed Amount of >> " + revAmount
								+ " for Special Sale ID >> " + specialsaleEnty.getSaleId());
					}

				}

			}
		}
		returnEntity.setSaleid("0");
		returnsRepo.save(returnEntity);
		paymentRepo.deleteAll(paymentEntityList);
	}

	private void enterCreditPayment(Long applysaleId, BigDecimal bd_orig_return_amount, ReturnsEntity retenty,
			int currentYear, int currentmonth) {
		PaymentEntity paymentEntity = new PaymentEntity();
		paymentEntity.setstatus("Credit Return");
		paymentEntity.setGrand_total(bd_orig_return_amount.doubleValue());
		paymentEntity.setMember_name(retenty.getMember_name());
		paymentEntity.setMember_id(retenty.getMemberid());
		paymentEntity.setPaymentdate(new Date());
		paymentEntity.setReferenceno("Credit Return" + currentYear + "/" + currentmonth + "/" + applysaleId);
		paymentEntity.setRsaleId(applysaleId);
		paymentEntity.setstatus("Credit Return");

		paymentRepo.save(paymentEntity);
	}

	private void saveFinancials(BigDecimal New_return_grand_total, BigDecimal Old_return_grand_total,
			ReturnsEntity returnEntity, Long applysaleId, BigDecimal bd_grand_total, Calendar calendarInstance) {
		BigDecimal Diff_amount;
		FinancialTransactionEntity ftLatest = financialTransactionsRepo
				.findTopByCustomerIdOrderByFanIdDesc(returnEntity.getMemberid());

		FinancialTransactionEntity ftEntity = new FinancialTransactionEntity();

		ftEntity.setInvoideId(applysaleId);
		if (New_return_grand_total.compareTo(Old_return_grand_total) == 1) {
			Diff_amount = New_return_grand_total.subtract(Old_return_grand_total);
			Diff_amount = Diff_amount.setScale(2, RoundingMode.HALF_UP);
			ftEntity.setAmount(New_return_grand_total.doubleValue());
		} else {
			Diff_amount = New_return_grand_total.subtract(Old_return_grand_total);
			Diff_amount = Diff_amount.setScale(2, RoundingMode.HALF_UP);
			ftEntity.setAmount(New_return_grand_total.doubleValue());
		}
		ftEntity.setCustomerId(returnEntity.getMemberid());
		ftEntity.setCustomerName(returnEntity.getMember_name());
		ftEntity.setDate(new Date());
		ftEntity.setDueDate(calendarInstance.getTime());
		ftEntity.setType("Credit Return");

		if (ftLatest == null) {
			ftEntity.setBalance(bd_grand_total.doubleValue());
		} else {
			BigDecimal ftTotal = new BigDecimal(0.0);
			BigDecimal ftrTotal = new BigDecimal(ftLatest.getBalance());
			LeoLogger.info("ReturnserviceImpl---updateReturns---Latest Financial Transation balance  ....."
					+ ftLatest.getBalance());
			ftTotal = ftrTotal.subtract(Diff_amount);
			ftTotal = ftTotal.setScale(2, RoundingMode.HALF_UP);
			ftEntity.setBalance(ftTotal.doubleValue());
		}
		financialTransactionsRepo.save(ftEntity);
	}

	private void restoreInventory(List<ReturnItemEntity> returnItemEntityList) {
		for (ReturnItemEntity returnItmAdd : returnItemEntityList) {

			LeoLogger.info("ReturnServiceImpl-- Inside restoreInventory Method");
			ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(returnItmAdd.getProductid());
			LeoLogger.info(
					"ReturnServiceImpl---updateReturn ( Restoring Inventory ) >>>>>>>> Product Quantity for product "
							+ productDetailsEnt.getname() + "  Before is >>> " + productDetailsEnt.getQuantity());
			productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(returnItmAdd.getQuantity()) );
			productDetailsRepo.save(productDetailsEnt);
			LeoLogger.info(
					"ReturnServiceImpl---updateReturn  ( Restoring Inventory )  >>>>>>>> Product Quantity for product "
							+ productDetailsEnt.getname() + "  After is >>> " + productDetailsEnt.getQuantity());

		}
	}
	
	private void restoreInventoryreturn(List<ReturnCashItemsEntity> returnItemEntityList) {
		for (ReturnCashItemsEntity returnItmAdd : returnItemEntityList) {

			LeoLogger.info("ReturnServiceImpl-- Inside restoreInventory Method");
			ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(returnItmAdd.getProductid());
			LeoLogger.info(
					"ReturnServiceImpl---updateReturn ( Restoring Inventory ) >>>>>>>> Product Quantity for product "
							+ productDetailsEnt.getname() + "  Before is >>> " + productDetailsEnt.getQuantity());
			productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .subtract(returnItmAdd.getQuantity()) );
			productDetailsRepo.save(productDetailsEnt);
			LeoLogger.info(
					"ReturnServiceImpl---updateReturn  ( Restoring Inventory )  >>>>>>>> Product Quantity for product "
							+ productDetailsEnt.getname() + "  After is >>> " + productDetailsEnt.getQuantity());

		}
	}

	private void setreturnqtySales(List<AddItemReqPojo> addItemReqPojos, MemberUser memberUser) {

		LeoLogger.info("ReturnServiceImpl-- Inside setreturnqtySales Method");

		for (AddItemReqPojo additem : addItemReqPojos) {
			Long applysaleId = 0L;
			applysaleId = additem.getApplysaleId();
			if (!memberUser.getCtype().equalsIgnoreCase("Special")) {
				SalesItemEntity saleitemEntity = salesItemRepo.findBySaleidAndProductid(applysaleId,
						additem.getProductId());
				LeoLogger.info(
						"ReturnserviceImpl>>> Update Return ( Under Normal Customer ) >>Sales Item present in sales"
								+ saleitemEntity.toString());
				LeoLogger.info("ReturnserviceImpl >>> Update Return ( Under Normal Customer ),  " + applysaleId);
				LeoLogger.info(
						"ReturnserviceImpl >>> Update Return ( Under Normal Customer )s" + additem.getProductId());
				// int retqy = Integer.parseInt(saleitemEntity.getReturnqty()) -
				// Integer.parseInt(additem.getQuantity());
				saleitemEntity.setReturnqty("0");
				LeoLogger.info("ReturnserviceImpl>> Updare Return --- Deleting Returnqty delete");
				salesItemRepo.save(saleitemEntity);
			} else {

				SpecialSalesItemEntity ssaleitemEntity = specialsalesItemRepo.findBySaleidAndProductid(applysaleId,
						additem.getProductId());
				LeoLogger.info(
						"ReturnserviceImpl>>> Update Return ( Under Special Customer ) >>Sales Item present in sales"
								+ ssaleitemEntity.toString());
				LeoLogger.info(
						"ReturnserviceImpl>>> Update Return ( Under Special Customer ) >>Sales Status" + applysaleId);
				LeoLogger.info("ReturnserviceImpl>>> Update Return ( Under Special Customer ) >>Sales Status"
						+ additem.getProductId());
				// int retqy = Integer.parseInt(ssaleitemEntity.getReturnqty()) -
				// Integer.parseInt(additem.getQuantity());
				ssaleitemEntity.setReturnqty("0");
				specialsalesItemRepo.save(ssaleitemEntity);

			}

		}
	}

	@Override
	public ResultVO deleteReturn(long returnid) {
		ResultVO resultVO = new ResultVO();
		try {
			
			Long returnId = 0L;
			returnId = (long) returnid;
			LeoLogger.info("ReturnserviceImpl--- Update Return with Return ID >>>>> " + returnid);
			// BigDecimal old_total = new BigDecimal(0.0);
			
			BigDecimal Old_return_grand_total = new BigDecimal(0.0);
		
					
			ReturnsEntity returnEntity = returnsRepo.findByReturnId(returnId);
			LeoLogger.info("ReturnServiceImpl---UpdateReturns Retrun entity is >>>>> " + returnEntity.toString());
			
			List<PaymentEntity> paymentEntityList =new ArrayList<PaymentEntity>();
			PaymentEntity pety = new PaymentEntity();
			
			MemberUser memberUser = memberUserRepo.findById(returnEntity.getMemberid());
			LeoLogger.info("ReturnServiceImpl----UpdateReturns--Memberid >>>>>>>>>> " + returnEntity.getMemberid());
			
			
			//Revere Payments & Sales 
			
			reversePayments(returnEntity, paymentEntityList, memberUser);
			
			//payment credit return delete
			
			
			List<ReturnItemEntity> returnItemEntityList = new ArrayList<ReturnItemEntity>();
		
		   // Calculating the return total of old return
			
			if (returnId != null) {

				LeoLogger.info("ReturnServiceImpl---Delete Return---returnEntity found >>> " + returnEntity.toString());
				returnItemEntityList = returnItemRepo.findByReturnid(returnEntity.getReturnId());
				LeoLogger.info("ReturnServiceImpl--Delete Returns---if condition---returnItemEntityList found >>>> "
						+ returnItemEntityList.toString());
				Old_return_grand_total = new BigDecimal(returnEntity.getAmount() + returnEntity.getTax());
				LeoLogger.info("ReturnServiceImpl--Delete Return ----Total Previous/Old Return amount Stored in variable Old_return_grand_total is >>>>> "
						+ Old_return_grand_total);
				
			}
			
			// ************************************************************************************************************************
			
		// Setting Return Quantity in sales items to previous State
			
		//	setreturnqtySales(addItemReqPojos, memberUser);

				
			// ************************************************************************************************************************
			
			// Adjusting Inventory by Restoring the state before the return
								
			restoreInventory(returnItemEntityList);
			returnEntity.setIsActive(1);
			//returnItemRepo.deleteAll(returnItemEntityList);
			//returnsRepo.delete(returnEntity);
			// ************************************************************************************************************************
			
			// Delete all returns items in returnitems table against the sale id

	
			  	
		  // <<<<<<<<<<<<<<<<<<<<<< Enter Financial transaction >>>>>>>>>>>>>>>>>>>>

			
			if(memberUser.getCreditpayment()>0) {
				
				BigDecimal difference = new BigDecimal(0.0);
				
				if(Old_return_grand_total.compareTo(new BigDecimal(memberUser.getCreditpayment()))>0)
				{
					Old_return_grand_total = Old_return_grand_total.subtract(new BigDecimal(memberUser.getCreditpayment()));
					memberUser.setCreditpayment(0);
				}
				else
				{
					difference = new BigDecimal(memberUser.getCreditpayment()).subtract(Old_return_grand_total);
					difference = difference.setScale(2, RoundingMode.HALF_UP);
					Old_return_grand_total = new BigDecimal(0.0);
					memberUser.setCreditpayment(difference.doubleValue());
				}
				
				memberUserRepo.save(memberUser);
			}
			

			if (Old_return_grand_total.compareTo(new BigDecimal(0.0)) > 0) {
			
				
				BigDecimal balance = new BigDecimal(0.0);
				BigDecimal fnamount = new BigDecimal(0.0);
			
				LeoLogger.info(	"ReturnServiceImpl ### Delete Return >> New Return amount is > old return amount with Difference >>>>>>>   " + Old_return_grand_total.toString());
				FinancialTransactionEntity ftransLatest = financialTransactionsRepo
						.findTopByCustomerIdOrderByFanIdDesc(returnEntity.getMemberid());
				balance = new BigDecimal(ftransLatest.getBalance()).add(Old_return_grand_total);
				fnamount = new BigDecimal(ftransLatest.getAmount()).subtract(Old_return_grand_total);
				balance = balance.setScale(2, RoundingMode.HALF_UP);
				fnamount = fnamount.setScale(2, RoundingMode.HALF_UP);
				
				ftransLatest.setAmount(fnamount.doubleValue());
				ftransLatest.setBalance(balance.doubleValue());
				
				FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(returnEntity.getMemberid());
				fttLatest.setAmount(fnamount.doubleValue());
				fttLatest.setBalance(balance.doubleValue());
				financialTransactionsRepo.save(ftransLatest);
				fTRepo.save(fttLatest);
				
				if(fnamount.compareTo(new BigDecimal(0.0)) <= 0)
				{
					financialTransactionsRepo.delete(ftransLatest);
					fTRepo.delete(fttLatest);
					LeoLogger.info(	"ReturnServiceImpl ### Delete Return >> Deleting Irrelevant financail entry");
				}				
				
				
				
			} 
				
			
			
			// ******************************************************** Return amount adjustment end *****************************************
			
	

			
			// enterCreditPayment(applysaleId, bd_orig_return_amount, retenty, currentYear, currentmonth);

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details >>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());

			if (registerEntity != null)

			{
				double total= returnEntity.getAmount()+returnEntity.getTax();
				registerEntity.setRefunds(registerEntity.getRefunds()-total);
				registerEntity.setClosingbal(registerEntity.getClosingbal()+total);
				registerRepo.save(registerEntity);
			}

			// **************************** Register Details Entry ended  ********************
			//****Register history Entrty***
			applyRegisterhistorydeletereturn(returnEntity);

			resultVO.setMsgDescr("Return Deleted Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public ResultVO addReturnCash(List<AddItemReqPojo> addItemReqPojos) {
		ResultVO resultVO = new ResultVO();
		try {
			LeoLogger.info("ReturnServiceImpl--addReturnCash");

			ReturnCashEntity returncashEntity = new ReturnCashEntity();
			MemberUser memberPojo = new MemberUser();

			double tax_rate = 0, total = 0, unit_price = 0, return_amount = 0, paid_total = 0;
			int quantity = 0;
		

			// Declare Bigdecimal equivalents
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			BigDecimal bd_rettotal = new BigDecimal(0.0);
			BigDecimal bd_return_amount = new BigDecimal(0.0);
			BigDecimal bd_orig_return_amount = new BigDecimal(0.0);
			BigDecimal bd_paid_total = new BigDecimal(0.0);


			for (AddItemReqPojo additem : addItemReqPojos) {

				memberPojo = memberUserRepo.findById(additem.getCustomerId());
				returncashEntity.setDate(new Date());
				returncashEntity.setMemberid(memberPojo.getId());
				returncashEntity.setMember_name(memberPojo.getName());
				returncashEntity.setNote(additem.getNote());
				returncashEntity.setCustomeraddress(memberPojo.getAddress());
				returncashEntity.setPincode(memberPojo.getPincode());
				returncashEntity.setMembername(memberPojo.getName());
				//applysaleId = additem.getApplysaleId();
				returncashEntity.setIsActive(0);
				
				bd_total =additem.getSubtotal();
				bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
				returncashEntity.setAmount(bd_total.doubleValue());
				LeoLogger.info("ReturnserviceImpl>>>>>addReturnCash>>>Total....." + bd_total);

				

				// ProductDetailsEntity productDetailsPojo =
				// productDetailsRepo.findByProductId(additem.getProductId());

				// if (productDetailsPojo != null)
				unit_price = additem.getPrice().doubleValue();
				quantity = Integer.parseInt(additem.getQuantity());

				returncashEntity.setNote(additem.getNote());
				if (!memberPojo.getCtype().equalsIgnoreCase("Special")) {
				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (additem.getSubtotal().doubleValue() * 0.125 );
				}else {
					total = total + (unit_price * quantity);
					tax_rate =0.0;
				}

			}

			//LeoLogger.info("ReturnserviceImpl>>>>>addReturnCash>>> Apply Invoice saleId is >>> " + applysaleId.toString());
			bd_tax_rate = new BigDecimal(tax_rate);
			bd_tax_rate = bd_tax_rate.setScale(2, RoundingMode.HALF_UP);
			returncashEntity.setTax(bd_tax_rate.doubleValue());
			LeoLogger.info("ReturnserviceImpl>>>>>addReturnCash>>>Tax Rate....." + bd_tax_rate);

			bd_total = new BigDecimal(total);
			bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
			returncashEntity.setAmount(bd_total.doubleValue());
			LeoLogger.info("ReturnserviceImpl>>>>>addReturnCash>>>Total....." + bd_total);

			bd_rettotal = bd_total.add(bd_tax_rate);
			// bd_rettotal = bd_rettotal.setScale(2);
			returncashEntity.setUserid(bd_rettotal.doubleValue());
			LeoLogger.info("ReturnserviceImpl>>>>addReturnCash>>>Total....." + bd_rettotal);
						


			Long applysaleId = addItemReqPojos.get(0).getApplysaleId();
			LeoLogger.info("ReturnserviceImpl >>>>> updateNewStoreCreditReturn >>>>>>>>> Apply Invoice saleId  >>> " + applysaleId);
					
			if (memberPojo.getCtype().equalsIgnoreCase("Special")) {
				SpecialSalesEntity specialSale = specialsalesRepo.findBySaleId(applysaleId);
				returncashEntity.setSalereferenceno(specialSale.getReferenceno());
					}
			else {
				SalesEntity sale = salesRepo.findBySaleId(applysaleId);
			    returncashEntity.setSalereferenceno(sale.getReferenceno());
			}
					
			returncashEntity.setSaleid(String.valueOf(applysaleId));
			
			ReturnCashEntity retenty = returnscashRepo.save(returncashEntity);
			UnitEntity unitentity = new UnitEntity();

			return_amount = total + tax_rate;
			bd_return_amount = new BigDecimal(total).add(new BigDecimal(tax_rate));
			bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
			bd_orig_return_amount = bd_return_amount;
			LeoLogger.info("ReturnserviceImpl>>>addReturnCash>>Return Total....." + bd_return_amount);
			Date d = new Date();
			int year = d.getYear();
			int currentYear = year + 1900;
			int currentmonth = d.getMonth() + 1;
			

			returncashEntity.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + retenty.getReturnId());
			//returncashEntity.setSaleid (applysaleId.toString());
			
			
			returnscashRepo.save(returncashEntity);
			

			for (AddItemReqPojo additem : addItemReqPojos) {

				ProductDetailsEntity productDetailsEnt = productDetailsRepo.findByProductId(additem.getProductId());

				if (additem.getUnit() != null && additem.getUnit() != "") {
					//unitentity = unitRepo.findByUnitname(additem.getRoll());
					unitentity = unitRepo.findById(Long.parseLong(additem.getUnit()));
				}
				

			
				BigDecimal price = productDetailsEnt.getprice();
				long qt = Long.parseLong(additem.getQuantity());
				BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(qt));
				BigDecimal tax = totalPrice.multiply(BigDecimal.valueOf(0.125));
				LeoLogger.info("SaleServiceImpl---addSale--price." + price);
				LeoLogger.info("SaleServiceImpl---addSale--totalPrice." + totalPrice);

			//	tax = (additem.getPrice() * Long.parseLong(additem.getQuantity())) * .125;

				ReturnCashItemsEntity returncashItemEntity = new ReturnCashItemsEntity();
				returncashItemEntity.setReturnid(retenty.getReturnId());
				returncashItemEntity.setProductid(additem.getProductId());
				returncashItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
				returncashItemEntity.setTax(String.valueOf(additem.getPrice().doubleValue() * 0.125));
				returncashItemEntity.setProduct_name(additem.getProductName());
				
				// returnItemEntity.setReturnqty(additem.getQuantity());

				bd_real_unit_price = additem.getPrice();
				bd_real_unit_price = bd_real_unit_price.setScale(2, RoundingMode.HALF_UP);
				returncashItemEntity.setReal_unit_price(bd_real_unit_price.doubleValue());

				bd_subtotal = additem.getSubtotal();
				bd_subtotal = bd_subtotal.setScale(2, RoundingMode.HALF_UP);
				returncashItemEntity.setSubtotal(bd_subtotal.doubleValue());

				bd_grand_total = bd_subtotal.add(additem.getPrice() .multiply(new BigDecimal(0.125)));
				bd_grand_total = bd_grand_total.setScale(2, RoundingMode.HALF_UP);
				returncashItemEntity.setTotal(bd_grand_total.doubleValue());
				LeoLogger.info("ReturnserviceImpl>>addReturn>>Total....." + bd_total);
				LeoLogger.info("ReturnserviceImpl>>addReturn>>" + unitentity.getId());

				returncashItemEntity.setTax((tax).toString());
				returncashItemEntity.setUnitid(unitentity.getId());
				returncashItemEntity.setUnitname(additem.getRoll());
				LeoLogger.info("ReturnserviceImpl>>addReturn>>" + unitentity.getId());

				returncashItemRepo.save(returncashItemEntity);
				

				if (!additem.getRoll().equalsIgnoreCase("Piece")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					LeoLogger.info("ReturnserviceImpl>>addReturn>>" + unitentity.getQuantity());
					BigDecimal unit =(unitentity.getQuantity());
					LeoLogger.info(qty.toString());
					LeoLogger.info(unit.toString());
					qty = qty .multiply(unit) ;
					returncashItemEntity.setQuantity(qty);

					LeoLogger.info("ReturnserviceImpl---addReturn--qt....." + qty);
				} else {
					
					returncashItemEntity.setQuantity(new BigDecimal(additem.getQuantity()));
					returncashItemRepo.save(returncashItemEntity);
				}
				

				// Subtracting Quantities here from products

				if (!unitentity.getUnitname().equalsIgnoreCase("Piece")) {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					BigDecimal unit =(unitentity.getQuantity());
					LeoLogger.info(qty.toString());
					qty = qty .multiply(unit) ;
					LeoLogger.info(unit.toString());
					productDetailsEnt.setQuantity(productDetailsEnt.getQuantity() .add(qty));

				}

				else {
					BigDecimal qty = new BigDecimal(additem.getQuantity());
					productDetailsEnt
							.setQuantity(productDetailsEnt.getQuantity() .add(qty));
				}
				productDetailsRepo.save(productDetailsEnt);

			}
	
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details
			// >>>>>>>>>>>>>>>>>>>>>>>>>>

			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());


			if (registerEntity != null)

			{
				LeoLogger.info("ReturnserviceImpl>>>addReturn>>> Register entry found for today  ....."
						+ registerEntity.getSalesamount());
				registerEntity.setReferenceno(registerEntity.getReferenceno() + "," + retenty.getReturnId());
				registerEntity.setRefunds(registerEntity.getRefunds()+ (returncashEntity.getAmount()+returncashEntity.getTax()));
				registerEntity.setClosingbal(registerEntity.getClosingbal()-(returncashEntity.getAmount()+returncashEntity.getTax()));

				registerRepo.save(registerEntity);
			}else
			{
				RegisterEntity newregisterEntity = new RegisterEntity();
				newregisterEntity.setCashinhand(1000.00);
				newregisterEntity.setDate(new Date());
				newregisterEntity.setCashpayment(0.00);
				newregisterEntity.setCreditcardpayment(0.00);
				newregisterEntity.setOpeningbal(1000.00);
				newregisterEntity.setClosingbal(1000.00-(returncashEntity.getAmount()+returncashEntity.getTax()));
				newregisterEntity.setChequepayment(0.00);
				newregisterEntity.setRefunds(bd_grand_total.doubleValue());
				newregisterEntity.setReferenceno(String.valueOf(retenty.getReturnId()));
				newregisterEntity.setSalesamount(1000.00);
				newregisterEntity.setStatus("Open");
				registerRepo.save(newregisterEntity);
			}

			// **************************** Register Details Entry ended
			// ********************
			//****Registerhistory Entry//
			applyRegisterhistoryreturncash(returncashEntity);

			resultVO.setMsgDescr("Return Added Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<ReturnCashPojo> getReturnscashList() {
		List<ReturnCashEntity> returnsEntityList = new ArrayList<ReturnCashEntity>();
		List<ReturnCashPojo> returnPojoList = new ArrayList<ReturnCashPojo>();
		try {
			// LeoLogger.info("in Returns");
			returnsEntityList = returnscashRepo.findAllByOrderByReturnIdDesc();
			for (ReturnCashEntity returnEntityRes : returnsEntityList) {

				ReturnCashPojo returnPojo = new ReturnCashPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnCashPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public ReturnCashPojo getreturncashbyreturnId(String returnId) {
		ReturnCashEntity returnEntity = new ReturnCashEntity();
		returnEntity = returnscashRepo.findByReturnId(Long.parseLong(returnId));
		LeoLogger.info("ReturnserviceImpl---getsalebySaleId---" + returnEntity.toString());
		ReturnCashPojo returnPojo = new ReturnCashPojo();
		returnPojo = mapper.map(returnEntity, ReturnCashPojo.class);
		returnPojo.setCreatedBy(returnEntity.getCreatedBy());
		return returnPojo;	
		}

	@Override
	public ResultVO updateReturnsCash(List<AddItemReqPojo> addItemReqPojos, double returnid) {
		ResultVO resultVO = new ResultVO();
		try {
			
			Long returnId = 0L;
			returnId = (long) returnid;
			LeoLogger.info("ReturnserviceImpl--- Update Return with Return ID >>>>> " + returnid);
			// BigDecimal old_total = new BigDecimal(0.0);
			BigDecimal New_return_grand_total = new BigDecimal(0.0);
			BigDecimal Old_return_grand_total = new BigDecimal(0.0);
			BigDecimal Orig_old_return_total= new BigDecimal(0.0);
			double paid_amount = 0.0;
			
			ReturnCashEntity returncashEntity = returnscashRepo.findByReturnId(returnId);
			LeoLogger.info("ReturnServiceImpl---UpdateReturns Retrun entity is >>>>> " + returncashEntity.toString());
			
			//List<PaymentEntity> paymentEntityList =new ArrayList<PaymentEntity>();
			//PaymentEntity pety = new PaymentEntity();
			
			MemberUser memberUser = memberUserRepo.findById(returncashEntity.getMemberid());
			LeoLogger.info("ReturnServiceImpl----UpdateReturns--Memberid >>>>>>>>>> " + returncashEntity.getMemberid());
			
			
			//Revere Payments & Sales 
			
			//reversePayments(returnEntity, paymentEntityList, memberUser);
			
			//payment credit return delete
			
			
			List<ReturnCashItemsEntity> returncashItemEntityList = new ArrayList<ReturnCashItemsEntity>();
		
		   // Calculating the return total of old return
			
			if (returnId != null) {

				LeoLogger.info("ReturnServiceImpl---if condition---returnEntity found >>> " + returncashEntity.toString());
				returncashItemEntityList = returncashItemRepo.findByReturnid(returncashEntity.getReturnId());
				LeoLogger.info("ReturnServiceImpl--updateReturns---if condition---returnItemEntityList found >>>> "
						+ returncashItemEntityList.toString());
				Old_return_grand_total = new BigDecimal(returncashEntity.getAmount() + returncashEntity.getTax());
				Orig_old_return_total = new BigDecimal(returncashEntity.getAmount() + returncashEntity.getTax());
				LeoLogger.info("ReturnServiceImpl--updateReturns----Total Previous/Old Return amount Stored in variable Old_return_grand_total is >>>>> "
						+ Old_return_grand_total);
				
			}
			
			// ************************************************************************************************************************
			//Reverse Register 
			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());


			if (registerEntity != null)

			{
				
				registerEntity.setRefunds(registerEntity.getRefunds()-Old_return_grand_total.doubleValue());
				registerEntity.setClosingbal(registerEntity.getClosingbal()+Old_return_grand_total.doubleValue());
				registerRepo.save(registerEntity);
			}
			
		// Setting Return Quantity in sales items to previous State
			
		//	setreturnqtySales(addItemReqPojos, memberUser);

				
			// ************************************************************************************************************************
			
			// Adjusting Inventory by Restoring the state before the return
								
			restoreInventoryreturn(returncashItemEntityList);
			
			// ************************************************************************************************************************
			
			// Delete all returns items in returnitems table against the sale id

			returncashItemRepo.deleteAll(returncashItemEntityList);
			LeoLogger.info("ReturnServiceImpl---updateReturn >>>>>>>> All Return items deleted associated with the return");
			
			// ************************************************************************************************************************

			double tax_rate = 0, total = 0, unit_price = 0, return_amount = 0, paid_total = 0;
			int quantity = 0;
			//Long applysaleId = 0L;

			// Declare BigDecimal equivalents
			BigDecimal bd_tax_rate = new BigDecimal(0.0);
			BigDecimal bd_total = new BigDecimal(0.0);
			BigDecimal bd_grand_total = new BigDecimal(0.0);
			BigDecimal bd_real_unit_price = new BigDecimal(0.0);
			BigDecimal bd_subtotal = new BigDecimal(0.0);
			BigDecimal bd_rettotal = new BigDecimal(0.0);
			BigDecimal bd_return_amount = new BigDecimal(0.0);
			BigDecimal bd_new_return_amount = new BigDecimal(0.0);
			BigDecimal Diff_amount = new BigDecimal(0.0);
			BigDecimal bd_paid_total = new BigDecimal(0.0);

			for (AddItemReqPojo additem : addItemReqPojos) {
				MemberUser memberPojo = memberUserRepo.findById(additem.getCustomerId());

				memberPojo = memberUserRepo.findById(additem.getCustomerId());
				
				//applysaleId = additem.getApplysaleId();

				unit_price = additem.getPrice().doubleValue();
				quantity = Integer.parseInt(additem.getQuantity());

				returncashEntity.setNote(additem.getNote());
				bd_total =additem.getSubtotal();
				bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
				returncashEntity.setAmount(bd_total.doubleValue());
				LeoLogger.info("ReturnserviceImpl>>updateReturns>>Total....." + bd_total);

				total = total + (unit_price * quantity);
				tax_rate = tax_rate + (additem.getSubtotal().doubleValue() * 0.125 );

				// saleEntity.setUser_id();
			}

			
			bd_tax_rate = new BigDecimal(tax_rate);
			bd_tax_rate = bd_tax_rate.setScale(2, RoundingMode.HALF_UP);
			returncashEntity.setTax(bd_tax_rate.doubleValue());
			LeoLogger.info("ReturnserviceImpl >>>>>updateReturns>>>Tax Rate....." + bd_tax_rate);

			bd_total = new BigDecimal(total);
			bd_total = bd_total.setScale(2, RoundingMode.HALF_UP);
			returncashEntity.setAmount(bd_total.doubleValue());
			LeoLogger.info("ReturnserviceImpl>>updateReturns>>Total....." + bd_total);

			bd_rettotal = bd_total.add(bd_tax_rate);
			bd_rettotal = bd_rettotal.setScale(2);
			returncashEntity.setUserid(bd_rettotal.doubleValue());
			LeoLogger.info("ReturnserviceImpl>>>updateReturns>>>Total....." + bd_rettotal);
			New_return_grand_total = bd_rettotal;

			returnscashRepo.save(returncashEntity);
			// returnsRepo.save(returnEntity);
			UnitEntity unitentity = new UnitEntity();
			MemberUser memberPojo = memberUserRepo.findById(returncashEntity.getMemberid());

			return_amount = total + tax_rate;
			bd_return_amount = new BigDecimal(total).add(new BigDecimal(tax_rate));
			bd_return_amount = bd_return_amount.setScale(2, RoundingMode.HALF_UP);
			bd_new_return_amount = bd_return_amount;
			LeoLogger.info("ReturnserviceImpl  >>updateReturns  >> Return Total....." + bd_return_amount);
			LeoLogger.info("ReturnserviceImpl  >> Update Returns  >> Total Return Amount to be applied ....." + return_amount);
			
			 // *********************************      END Return amount adjustment end                   ****************************************	   
			
			 // Add Return Items in Returnitem table and Adjust new quuantities in product table
			  	
			// this loop is for sales breakdown
			  	
			addReturncashItemNAdjustProducts(addItemReqPojos, returncashEntity, bd_total, unitentity, memberPojo);
			  	
		    // *********************************   Add Return Items in Returnitem table and Adjust new quuantities          ****************************************	
			  	
			
		
			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details >>>>>>>>>>>>>>>>>>>>>>>>>>>
			
						RegisterEntity rregisterEntity = new RegisterEntity();

						rregisterEntity = registerRepo.findAByDate(new Date());

						if (rregisterEntity != null)

						{
							rregisterEntity.setRefunds(rregisterEntity.getRefunds()+New_return_grand_total.doubleValue());
							rregisterEntity.setClosingbal(rregisterEntity.getClosingbal()-New_return_grand_total.doubleValue());
							registerRepo.save(rregisterEntity);
						}

						// **************************** Register Details Entry ended  ********************

						resultVO.setMsgDescr("Return Added Sucessfully");
						resultVO.setMsgCode("001");
						resultVO.setError(false);
						return resultVO;

					} catch (Exception e) {
						e.printStackTrace();
					}
					return resultVO;

}

	@Override
	public List<ReturnCashItemPojo> getReturnItemCashbyreturnId(String returnId) {
		List<ReturnCashItemsEntity> returnsItemEntityList = returncashItemRepo.findByReturnid(Long.parseLong(returnId));
		List<ReturnCashItemPojo> returnItemPojoList = new ArrayList<ReturnCashItemPojo>();
		try {
			// LeoLogger.info("in Return get Items by returnId");

			for (ReturnCashItemsEntity returnsItemEntityRes : returnsItemEntityList) {

				UnitEntity unitentity = unitRepo.findById(returnsItemEntityRes.getUnitid());
				ReturnCashItemPojo returnItemPojo = new ReturnCashItemPojo();
				BigDecimal qty = returnsItemEntityRes.getQuantity();
				BigDecimal unit = (unitentity.getQuantity());
				returnItemPojo = mapper.map(returnsItemEntityRes, ReturnCashItemPojo.class);
				if (!returnsItemEntityRes.getUnitname().equalsIgnoreCase("Piece")) {

					  BigDecimal result = qty.divide(unit, 4, RoundingMode.HALF_UP);
					returnItemPojo.setQuantity(result);
					LeoLogger.info("ReturnServiceImpl---getReturnItembyreturnId--Printing result>>>>>>>>" + qty);

					LeoLogger.info("ReturnServiceImpl---getReturnItembyreturnId---Printing Quantity  after>>>>>>>>"
							+ returnItemPojo.getQuantity());
				} else {
					returnItemPojo.setQuantity(qty);
					LeoLogger.info("ReturnServiceImpl---getReturnItembyreturnId---Printing Quantity  after>>>>>>>>"
							+ returnItemPojo.getQuantity());
				}
				// returnItemPojo = mapper.map(returnsItemEntityRes, ReturnItemPojo.class);
				returnItemPojoList.add(returnItemPojo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		LeoLogger.info("returnItemPojoList ....." + returnItemPojoList.toString());
		return returnItemPojoList;
	}

	@Override
	public ResultVO deleteReturnCash(long returnid) {
		ResultVO resultVO = new ResultVO();
		try {
			
			Long returnId = 0L;
			returnId = (long) returnid;
			LeoLogger.info("ReturnserviceImpl--- Update Return with Return ID >>>>> " + returnid);
			// BigDecimal old_total = new BigDecimal(0.0);
			
			BigDecimal Old_return_grand_total = new BigDecimal(0.0);
		
					
			ReturnCashEntity returncashEntity = returnscashRepo.findByReturnId(returnId);
			LeoLogger.info("ReturnServiceImpl---UpdateReturns Retrun entity is >>>>> " + returncashEntity.toString());
			
			
			
			MemberUser memberUser = memberUserRepo.findById(returncashEntity.getMemberid());
			LeoLogger.info("ReturnServiceImpl----UpdateReturns--Memberid >>>>>>>>>> " + returncashEntity.getMemberid());
			
			
		
			List<ReturnCashItemsEntity> returncashItemEntityList = new ArrayList<ReturnCashItemsEntity>();
		
		   // Calculating the return total of old return
			
			if (returnId != null) {

				LeoLogger.info("ReturnServiceImpl---Delete Return---returnEntity found >>> " + returncashEntity.toString());
				returncashItemEntityList = returncashItemRepo.findByReturnid(returncashEntity.getReturnId());
				LeoLogger.info("ReturnServiceImpl--Delete Returns---if condition---returnItemEntityList found >>>> "
						+ returncashItemEntityList.toString());
				Old_return_grand_total = new BigDecimal(returncashEntity.getAmount() + returncashEntity.getTax());
				LeoLogger.info("ReturnServiceImpl--Delete Return ----Total Previous/Old Return amount Stored in variable Old_return_grand_total is >>>>> "
						+ Old_return_grand_total);
				
			}
			
			// ************************************************************************************************************************
			
		// Setting Return Quantity in sales items to previous State
			
		//	setreturnqtySales(addItemReqPojos, memberUser);

				
			// ************************************************************************************************************************
			
			// Adjusting Inventory by Restoring the state before the return
								
			restoreInventoryreturn(returncashItemEntityList);
			returncashEntity.setIsActive(1);
			//returnItemRepo.deleteAll(returnItemEntityList);
			//returnsRepo.delete(returnEntity);
			// ************************************************************************************************************************
			
			// Delete all returns items in returnitems table against the sale id

	
		
			
		

			// <<<<<<<<<<<<<<<<<<<<<<<<<<<<< Enter Register Details >>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			RegisterEntity registerEntity = new RegisterEntity();

			registerEntity = registerRepo.findAByDate(new Date());

			if (registerEntity != null)

			{
				double total= returncashEntity.getAmount()+returncashEntity.getTax();
				registerEntity.setRefunds(registerEntity.getRefunds()-total);
				registerEntity.setClosingbal(registerEntity.getClosingbal()+total);
				registerRepo.save(registerEntity);
			}

			// **************************** Register Details Entry ended  ********************

			resultVO.setMsgDescr("Return Deleted Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}

	@Override
	public List<ReturnPojo> getReturnsListNew(ModelMap modelMap, int page) {
		List<ReturnsEntity> returnsEntityList = new ArrayList<ReturnsEntity>();
		List<ReturnPojo> returnPojoList = new ArrayList<ReturnPojo>();
		
int recordsLength=10;
		
		
		LeoLogger.info("SaleServiceImpl---getSalesList-");
		
		
		Pageable paging =  PageRequest.of(page, recordsLength);
		Page<ReturnsEntity> returns;			
		//sale = salesRepo.findAll(paging);
		returns= returnsRepo.findAllByOrderByReturnIdDesc(paging);
		System.out.println("page= "+page);
		System.out.println(returns.getNumber());
		System.out.println(returns.getNumberOfElements());
		System.out.println(returns.getSize());
		System.out.println(returns.getTotalElements());
		System.out.println(returns.getTotalPages());
		System.out.println(returns.hasNext());
		System.out.println(returns.hasPrevious());
		//salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		returnsEntityList = returns.getContent();		
		modelMap.addAttribute("totalPages", returns.getTotalPages());
		modelMap.addAttribute("totalRecords", returns.getTotalElements());
		modelMap.addAttribute("currentRecords", returns.getNumberOfElements());
		modelMap.addAttribute("previous", returns.hasPrevious());
		modelMap.addAttribute("next", returns.hasNext());
		modelMap.addAttribute("page", returns.getNumber());
		modelMap.addAttribute("pageSize", returns.getSize());
		try {
			// LeoLogger.info("in Returns");
			//returnsEntityList = returnsRepo.findAllByOrderByReturnIdDesc();
			for (ReturnsEntity returnEntityRes : returnsEntityList) {

				ReturnPojo returnPojo = new ReturnPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public List<ReturnPojo> getReturnsListpage(ModelMap modelMap, int page, int pageSize) {
		List<ReturnsEntity> returnsEntityList = new ArrayList<ReturnsEntity>();
		List<ReturnPojo> returnPojoList = new ArrayList<ReturnPojo>();
		

		
		
		LeoLogger.info("SaleServiceImpl---getSalesList-");
		
		
		Pageable paging =  PageRequest.of(page, pageSize);
		Page<ReturnsEntity> returns;			
		//sale = salesRepo.findAll(paging);
		returns= returnsRepo.findAllByOrderByReturnIdDesc(paging);
		System.out.println("page= "+page);
		System.out.println(returns.getNumber());
		System.out.println(returns.getNumberOfElements());
		System.out.println(returns.getSize());
		System.out.println(returns.getTotalElements());
		System.out.println(returns.getTotalPages());
		System.out.println(returns.hasNext());
		System.out.println(returns.hasPrevious());
		//salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		returnsEntityList = returns.getContent();		
		modelMap.addAttribute("totalPages", returns.getTotalPages());
		modelMap.addAttribute("totalRecords", returns.getTotalElements());
		modelMap.addAttribute("currentRecords", returns.getNumberOfElements());
		modelMap.addAttribute("previous", returns.hasPrevious());
		modelMap.addAttribute("next", returns.hasNext());
		modelMap.addAttribute("page", returns.getNumber());
		modelMap.addAttribute("pageSize", returns.getSize());
		try {
			// LeoLogger.info("in Returns");
			//returnsEntityList = returnsRepo.findAllByOrderByReturnIdDesc();
			for (ReturnsEntity returnEntityRes : returnsEntityList) {

				ReturnPojo returnPojo = new ReturnPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public List<ReturnPojo> getSearchReturn(ModelMap modelMap, String search) {
		List<ReturnsEntity> returnsEntityList = new ArrayList<ReturnsEntity>();
		List<ReturnPojo> returnPojoList = new ArrayList<ReturnPojo>();
		try {
			// LeoLogger.info("in Returns");
			returnsEntityList = returnsRepo.findAllByReferencenoContainingOrMembernameContainingOrderByReturnIdDesc(search,search);
			for (ReturnsEntity returnEntityRes : returnsEntityList) {

				ReturnPojo returnPojo = new ReturnPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public List<ReturnCashPojo> getReturnscashListNew(ModelMap modelMap, int page) {
		List<ReturnCashEntity> returnsEntityList = new ArrayList<ReturnCashEntity>();
		List<ReturnCashPojo> returnPojoList = new ArrayList<ReturnCashPojo>();
int recordsLength=10;
		
		
		LeoLogger.info("SaleServiceImpl---getSalesList-");
		
		
		Pageable paging =  PageRequest.of(page, recordsLength);
		Page<ReturnCashEntity> returns;			
		//sale = salesRepo.findAll(paging);
		returns= returnscashRepo.findAllByOrderByReturnIdDesc(paging);
		System.out.println("page= "+page);
		System.out.println(returns.getNumber());
		System.out.println(returns.getNumberOfElements());
		System.out.println(returns.getSize());
		System.out.println(returns.getTotalElements());
		System.out.println(returns.getTotalPages());
		System.out.println(returns.hasNext());
		System.out.println(returns.hasPrevious());
		//salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		returnsEntityList = returns.getContent();		
		modelMap.addAttribute("totalPages", returns.getTotalPages());
		modelMap.addAttribute("totalRecords", returns.getTotalElements());
		modelMap.addAttribute("currentRecords", returns.getNumberOfElements());
		modelMap.addAttribute("previous", returns.hasPrevious());
		modelMap.addAttribute("next", returns.hasNext());
		modelMap.addAttribute("page", returns.getNumber());
		modelMap.addAttribute("pageSize", returns.getSize());
		try {
			// LeoLogger.info("in Returns");
			//returnsEntityList = returnscashRepo.findAllByOrderByReturnIdDesc();
			for (ReturnCashEntity returnEntityRes : returnsEntityList) {

				ReturnCashPojo returnPojo = new ReturnCashPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnCashPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public List<ReturnCashPojo> getReturnscashListpage(ModelMap modelMap, int page, int pageSize) {
		List<ReturnCashEntity> returnsEntityList = new ArrayList<ReturnCashEntity>();
		List<ReturnCashPojo> returnPojoList = new ArrayList<ReturnCashPojo>();

		
		
		LeoLogger.info("SaleServiceImpl---getSalesList-");
		
		
		Pageable paging =  PageRequest.of(page, pageSize);
		Page<ReturnCashEntity> returns;			
		//sale = salesRepo.findAll(paging);
		returns= returnscashRepo.findAllByOrderByReturnIdDesc(paging);
		System.out.println("page= "+page);
		System.out.println(returns.getNumber());
		System.out.println(returns.getNumberOfElements());
		System.out.println(returns.getSize());
		System.out.println(returns.getTotalElements());
		System.out.println(returns.getTotalPages());
		System.out.println(returns.hasNext());
		System.out.println(returns.hasPrevious());
		//salesEntityList = salesRepo.findAllByOrderBySaleIdDesc();
		returnsEntityList = returns.getContent();		
		modelMap.addAttribute("totalPages", returns.getTotalPages());
		modelMap.addAttribute("totalRecords", returns.getTotalElements());
		modelMap.addAttribute("currentRecords", returns.getNumberOfElements());
		modelMap.addAttribute("previous", returns.hasPrevious());
		modelMap.addAttribute("next", returns.hasNext());
		modelMap.addAttribute("page", returns.getNumber());
		modelMap.addAttribute("pageSize", returns.getSize());
		try {
			// LeoLogger.info("in Returns");
			//returnsEntityList = returnscashRepo.findAllByOrderByReturnIdDesc();
			for (ReturnCashEntity returnEntityRes : returnsEntityList) {

				ReturnCashPojo returnPojo = new ReturnCashPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnCashPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public List<ReturnCashPojo> getSearchReturncash(ModelMap modelMap, String search) {
		List<ReturnCashEntity> returnsEntityList = new ArrayList<ReturnCashEntity>();
		List<ReturnCashPojo> returnPojoList = new ArrayList<ReturnCashPojo>();
		try {
			// LeoLogger.info("in Returns");
			returnsEntityList = returnscashRepo.findAllByReferencenoContainingOrMembernameContainingOrderByReturnIdDesc(search,search);
			for (ReturnCashEntity returnEntityRes : returnsEntityList) {

				ReturnCashPojo returnPojo = new ReturnCashPojo();
				returnPojo = mapper.map(returnEntityRes, ReturnCashPojo.class);
				returnPojoList.add(returnPojo);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPojoList;
	}

	@Override
	public ResultVO updateAllReturn(List<AddItemReqPojo> addItemReqPojos, long returnId, long applyReturn , boolean isOldCashReturn) {

		ResultVO resultVO = new ResultVO();
		
		try {
		
			ReturnsEntity returnEntity= null;
			ReturnCashEntity returncashEntity = null;
			MemberUser memberUser = null;
			
			if(isOldCashReturn) {
			 returncashEntity = returnscashRepo.findByReturnId(returnId);	
			 memberUser = memberUserRepo.findById(returncashEntity.getMemberid());
			LeoLogger.info("ReturnServiceImpl---updateAllReturn Retrun Cash entity is >>>>> " + returncashEntity);
			
			}else {
				
		        returnEntity = returnsRepo.findByReturnId(returnId);
		        memberUser = memberUserRepo.findById(returnEntity.getMemberid());
				LeoLogger.info("ReturnServiceImpl---updateAllReturn Return entity is >>>>> " + returnEntity);
				
			}
			
			LeoLogger.info("ReturnServiceImpl----updateAllReturn --Member>>>>>>>>>> " + memberUser);
			
			
			if(returnEntity != null) {
			
			if(returnEntity.getReturnType() == 1) {
				
				reverseOldPendingInvoiceReturn(returnEntity,memberUser) ;
				
			}else if(returnEntity.getReturnType() == 2) {
				
				reverseOldStoreCreditReturn(returnEntity,memberUser);
				
			}else {
				
				resultVO.setMsgDescr("Error occured while updating return!");
				resultVO.setError(true);				
				return resultVO;
			}
			
			}
						
			 if(isOldCashReturn) {
				
				 reverseOldCashRefundReturn(returncashEntity,memberUser);
				
			}
			
			if(applyReturn == 1) {
				
				updateNewPendingInvoiceReturn(addItemReqPojos,returnId,isOldCashReturn,memberUser) ;
				
			}else if(applyReturn == 2) {
				
				updateNewStoreCreditReturn(addItemReqPojos,returnId,isOldCashReturn,memberUser);
				
			}else if(applyReturn == 3) {
				
				updateNewCashRefundReturn(addItemReqPojos,returnId,isOldCashReturn,memberUser);
				
			}
			
			
			resultVO.setMsgDescr("Return Updated Sucessfully!");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			
			return resultVO;
		}catch (Exception e) {
			e.printStackTrace();
			LeoLogger.error("Error..................."+e.getMessage());			
			resultVO.setMsgDescr("Error occured while updating return!");
			resultVO.setError(true);
			
			return resultVO;
		}
		
		
		
	}
	
	
	private void reverseOldPendingInvoiceReturn(ReturnsEntity returnEntity, MemberUser memberUser)  {
	
		List<ReturnItemEntity>  returnItemEntityList = returnItemRepo.findByReturnid(returnEntity.getReturnId());
		LeoLogger.info("ReturnServiceImpl--reverseOldPendingInvoiceReturn---returnItemEntityList found >>>> "+ returnItemEntityList);
	
		BigDecimal OldReturnGrandTotal = new BigDecimal(returnEntity.getAmount() + returnEntity.getTax()).setScale(2, RoundingMode.HALF_UP);

		LeoLogger.info("ReturnServiceImpl--reverseOldPendingInvoiceReturn---Total Previous/Old Return amount  >>>>> "
				+ OldReturnGrandTotal);		
		
		LeoLogger.info("ReturnServiceImpl----reverseOldPendingInvoiceReturn --reverse sales and removing payments>>>>>>>>>> ");	
		reversePayments(returnEntity, new ArrayList<PaymentEntity>(), memberUser);
		
		
		LeoLogger.info("ReturnServiceImpl----reverseOldPendingInvoiceReturn --reversing inventory>>>>>>>>>> ");				
		restoreInventory(returnItemEntityList);		

		LeoLogger.info("ReturnServiceImpl----reverseOldPendingInvoiceReturn --removing return items>>>>>>>>>> ");
		returnItemRepo.deleteAll(returnItemEntityList);
		
		LeoLogger.info("ReturnServiceImpl----reverseOldPendingInvoiceReturn--reverse FinancialTransaction And FT>>>>>>>>>> ");	
		reverseFinancialTransactionAndFT(OldReturnGrandTotal,memberUser.getId(),returnEntity.getReturnType());
		
		LeoLogger.info("ReturnServiceImpl----reverseOldPendingInvoiceReturn --reversing Register>>>>>>>>>> ");    
		reverseRegister(OldReturnGrandTotal);
			
		
		
	}
	
	private void reverseOldStoreCreditReturn(ReturnsEntity returnEntity, MemberUser memberUser)  {
		
	
		List<ReturnItemEntity>  returnItemEntityList = returnItemRepo.findByReturnid(returnEntity.getReturnId());
		LeoLogger.info("ReturnServiceImpl--reverseOldStoreCreditReturn-----returnItemEntityList found >>>> "+ returnItemEntityList);
				

		BigDecimal OldReturnGrandTotal = new BigDecimal(returnEntity.getAmount() + returnEntity.getTax()).setScale(2, RoundingMode.HALF_UP);

		LeoLogger.info("ReturnServiceImpl--reverseOldStoreCreditReturn---Total Previous/Old Return amount  >>>>> "
				+ OldReturnGrandTotal);
	
		LeoLogger.info("ReturnServiceImpl----reverseOldStoreCreditReturn --reversing inventory>>>>>>>>>> ");				
		restoreInventory(returnItemEntityList);
		

		LeoLogger.info("ReturnServiceImpl----reverseOldStoreCreditReturn --removing return items>>>>>>>>>> ");
		returnItemRepo.deleteAll(returnItemEntityList);
		
		LeoLogger.info("ReturnServiceImpl----reverseOldStoreCreditReturn --Member Credit Amount before Reversing >>>>>>>>>> "+memberUser.getCreditpayment());
		BigDecimal creditAmount = new BigDecimal(memberUser.getCreditpayment()).subtract(OldReturnGrandTotal).setScale(2, RoundingMode.HALF_UP);		
		memberUser.setCreditpayment(creditAmount.doubleValue());
		memberUserRepo.save(memberUser);
		LeoLogger.info("ReturnServiceImpl----reverseOldStoreCreditReturn --Member Credit Amount After Reversing >>>>>>>>>> "+memberUser.getCreditpayment());
		
		
		LeoLogger.info("ReturnServiceImpl----reverseOldStoreCreditReturn --removing payment by pref>>>>>>>>>> "+returnEntity.getReturnId() + "-" + returnEntity.getSaleid());
		PaymentEntity oldCreditpayment  = paymentRepo.findByPref(returnEntity.getReturnId() + "-" + returnEntity.getSaleid());
		paymentRepo.delete(oldCreditpayment);
		
		LeoLogger.info("ReturnServiceImpl----reverseOldStoreCreditReturn--reverse FinancialTransaction And FT>>>>>>>>>> ");	
		reverseFinancialTransactionAndFT(OldReturnGrandTotal,memberUser.getId(),returnEntity.getReturnType());

		LeoLogger.info("ReturnServiceImpl----reverseOldPendingInvoiceReturn --reversing Register>>>>>>>>>> ");    
		reverseRegister(OldReturnGrandTotal);
		
	}
	
	private void reverseOldCashRefundReturn(ReturnCashEntity returncashEntity , MemberUser memberUser)  {
					
		List<ReturnCashItemsEntity> returncashItemEntityList = returncashItemRepo.findByReturnid(returncashEntity.getReturnId());
		LeoLogger.info("ReturnServiceImpl--reverseOldCashRefundReturn------returnItemEntityList found >>>> "+ returncashItemEntityList);			
		
		BigDecimal OldReturnGrandTotal = new BigDecimal(returncashEntity.getAmount() + returncashEntity.getTax()).setScale(2, RoundingMode.HALF_UP);

		LeoLogger.info("ReturnServiceImpl--reverseOldCashRefundReturn---Total Previous/Old Return amount  >>>>> "
				+ OldReturnGrandTotal);
		
		LeoLogger.info("ReturnServiceImpl----reverseOldCashRefundReturn --reversing inventory>>>>>>>>>> ");				
		restoreInventoryreturn(returncashItemEntityList);
		
		LeoLogger.info("ReturnServiceImpl----reverseOldCashRefundReturn --removing return items>>>>>>>>>> ");
		returncashItemRepo.deleteAll(returncashItemEntityList);
		
		LeoLogger.info("ReturnServiceImpl----reverseOldCashRefundReturn --reversing Register>>>>>>>>>> ");    
		reverseRegister(OldReturnGrandTotal);
		
		
	}
	
	private void updateNewPendingInvoiceReturn(List<AddItemReqPojo> addItemReqPojos,long returnId ,boolean isOldCashReturn, MemberUser memberUser)  {
		
		double unit_price = 0;
		int quantity = 0;
		
		BigDecimal taxAmount= new BigDecimal(0.0);
		BigDecimal subTotal= new BigDecimal(0.0);
		BigDecimal totalAmount= new BigDecimal(0.0);
		BigDecimal totalAmountFixed= new BigDecimal(0.0);
		
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		int currentmonth = date.getMonth() + 1;
				
		ReturnsEntity returnEntity = null;
		
		if(isOldCashReturn) {
			LeoLogger.info("ReturnserviceImpl >>>>> updateNewPendingInvoiceReturn >>>>>>>>> Deleting old ReturnCashEntity cause new return is not cash >>> " + returnId);
			ReturnCashEntity returncashEntity = returnscashRepo.findByReturnId(returnId);
			returnscashRepo.delete(returncashEntity);
			returnEntity = new ReturnsEntity();
		}else {			
			returnEntity = returnsRepo.findByReturnId(returnId);			
		}
		

		for (AddItemReqPojo additem : addItemReqPojos) {
				
			unit_price = additem.getPrice().doubleValue();
			quantity = Integer.parseInt(additem.getQuantity());
			
			subTotal = subTotal.add(new BigDecimal(unit_price * quantity).setScale(2, RoundingMode.HALF_UP));					
			taxAmount = taxAmount.add(additem.getSubtotal() .multiply(new BigDecimal(0.125))).setScale(2, RoundingMode.HALF_UP);

		}
		
		Long applysaleId = addItemReqPojos.get(0).getApplysaleId();
		LeoLogger.info("ReturnserviceImpl >>>>> updateNewPendingInvoiceReturn >>>>>>>>> Apply Invoice saleId  >>> " + applysaleId);
		
          if (memberUser.getCtype().equalsIgnoreCase("Special"))			
			  taxAmount = new BigDecimal(0.0);	
      
		    
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewPendingInvoiceReturn>>>>>>>>>>>Tax Amount....." + taxAmount);		
		returnEntity.setTax(taxAmount.doubleValue());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewPendingInvoiceReturn>>>>>>>>>>>Sub Total Amount....." + subTotal);
		returnEntity.setAmount(subTotal.doubleValue());
	
		totalAmount =subTotal.add(taxAmount).setScale(2, RoundingMode.HALF_UP);
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewPendingInvoiceReturn>>>>>>>>>>> Total Amount....." + totalAmount);
		returnEntity.setUserid(totalAmount.doubleValue());
		
		
		totalAmountFixed = totalAmount;
		LeoLogger.info("ReturnserviceImpl>>>updateNewPendingInvoiceReturn>>> copying value to  totalAmountFixed....." + totalAmountFixed);				
		
		returnEntity.setMemberid(memberUser.getId());
		returnEntity.setMember_name(memberUser.getName());		
		returnEntity.setCustomeraddress(memberUser.getAddress());
		returnEntity.setPincode(memberUser.getPincode());
		returnEntity.setMembername(memberUser.getName());			
		returnEntity.setReturnType(1);
		returnEntity.setIsActive(0);		
		returnEntity.setNote(addItemReqPojos.get(0).getNote());


		if(returnEntity.getReturnId() == 0) {
		returnEntity.setDate(date);
		}
		
		returnEntity = returnsRepo.save(returnEntity);
	
		returnEntity.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + returnEntity.getReturnId());
		
		
	  	if (! memberUser.getCtype().equalsIgnoreCase("Special")) {
	  		
			LeoLogger.info("ReturnserviceImpl>>>> updateNewPendingInvoiceReturn >>>Customer is not a special customer " + memberUser.getCtype());
			SalesEntity saleapplyEntity = salesRepo.findBySaleId(applysaleId);
			double dueAmount = saleapplyEntity.getGrand_total() - saleapplyEntity.getPaid();
			
			returnEntity.setSalereferenceno(saleapplyEntity.getReferenceno());
			
			LeoLogger.info("ReturnserviceImpl>>> > updateNewPendingInvoiceReturn >>Sales Status >>>" + saleapplyEntity.getSale_status());
	
			
			if ( totalAmount.doubleValue() >= dueAmount && saleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due"))
			  {		
				LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is more than due >>>>>>>   " + totalAmount);
				BigDecimal dueAmountBD = new BigDecimal(dueAmount).setScale(2, RoundingMode.HALF_UP);					
				LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmount);
				
				saleapplyEntity.setPaid(saleapplyEntity.getGrand_total());
				saleapplyEntity.setPaymentstatus("Paid");
				saleapplyEntity.setSale_status("Paid");
				salesRepo.save(saleapplyEntity);				
				
				totalAmount = totalAmount.subtract(dueAmountBD).setScale(2, RoundingMode.HALF_UP);				
			
				applyPaymentReturn(applysaleId, dueAmountBD, returnEntity, currentYear, currentmonth,1);
				LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return amount Left after applying is >>>>>>>   " + totalAmount);

			} 
			else if (saleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due")) 
			 {
				LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is less than due >>>>>>>   " + totalAmount);
				BigDecimal	paidTotal = new BigDecimal(saleapplyEntity.getPaid()).setScale(2, RoundingMode.HALF_UP);
				LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmount);
		
				saleapplyEntity.setPaid(paidTotal.add(totalAmount).doubleValue());
				
				salesRepo.save(saleapplyEntity);
				applyPaymentReturn(applysaleId, totalAmount, returnEntity, currentYear, currentmonth,1);				
			
				totalAmount = new BigDecimal(0.0);
			
				LeoLogger.info(	"ReturnServiceImpl ###  updateNewPendingInvoiceReturn>>>> Return amount Left after applying is >>>>>>>   " + totalAmount);
			 }
			
					
 			if (totalAmount.compareTo(new BigDecimal(0.0)) > 0) 
			 {
				LeoLogger.info("ReturnserviceImpl >>> updateNewPendingInvoiceReturn>>  -- ### Return amount is greater than 0 after applying to Applies sale  >>>"
						+ totalAmount.toPlainString());
					
				List<SalesEntity> salesList = salesRepo.findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(memberUser.getId(), "Due");
				LeoLogger.info("ReturnserviceImpl>>> updateNewPendingInvoiceReturn >>Sales list in ascending order >> "	+ salesList);

     	      for (SalesEntity sale : salesList) {
     	    		double dueAmountSale = sale.getGrand_total() - sale.getPaid();

				 if (totalAmount.doubleValue() >= dueAmountSale)
				{
						
				    LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is more than due >>>>>>>   " + totalAmount);
					BigDecimal dueAmountSaleBD = new BigDecimal(dueAmountSale).setScale(2, RoundingMode.HALF_UP);
					LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmountSale);
										
					sale.setPaid(sale.getGrand_total());
					sale.setPaymentstatus("Paid");
					sale.setSale_status("Paid");
					salesRepo.save(sale);					
					
					applyPaymentReturn(sale.getSaleId(), dueAmountSaleBD, returnEntity, currentYear, currentmonth,1);
					
					totalAmount = totalAmount.subtract(dueAmountSaleBD).setScale(2, RoundingMode.HALF_UP);	
									
					LeoLogger.info(	"ReturnServiceImpl ###updateNewPendingInvoiceReturn>>>> Return amount Left after applying is >>>>>>>   " + totalAmount);

				} else if (sale.getPaymentstatus().equalsIgnoreCase("Due")) {

					LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is less than due >>>>>>>   " + totalAmount);
					LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmountSale);
					BigDecimal	paidTotal = new BigDecimal(sale.getPaid()).setScale(2, RoundingMode.HALF_UP);								
					sale.setPaid(paidTotal.add(totalAmount).doubleValue());
					
					salesRepo.save(sale);
					
					applyPaymentReturn(sale.getSaleId(), totalAmount, returnEntity, currentYear, currentmonth,1);							
									
						totalAmount = new BigDecimal(0.0);
						LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return amount Left after applying is >>>>>>>   " + totalAmount.toString());
						break;
 					}
				}

     			}
					
				} else {

					LeoLogger.info("ReturnserviceImpl>>>> updateNewPendingInvoiceReturn >>>>>>>> Special sales ");

					LeoLogger.info("ReturnserviceImpl>>>> updateNewPendingInvoiceReturn >>>Member type" + memberUser.getCtype());
					
					SpecialSalesEntity specialsaleapplyEntity = specialsalesRepo.findBySaleId(applysaleId);
					double dueAmount = specialsaleapplyEntity.getGrand_total() - specialsaleapplyEntity.getPaid();
					
					returnEntity.setSalereferenceno(specialsaleapplyEntity.getReferenceno());
		
					if (totalAmount.doubleValue() >= dueAmount	&& specialsaleapplyEntity.getSale_status().equalsIgnoreCase("Due"))

					{					
						LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is more than due >>>>>>>   " + totalAmount);
						BigDecimal dueAmountBD = new BigDecimal(dueAmount).setScale(2, RoundingMode.HALF_UP);	
						LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmount);
						
						specialsaleapplyEntity.setPaid(specialsaleapplyEntity.getGrand_total());
						specialsaleapplyEntity.setPaymentstatus("Paid");
						specialsaleapplyEntity.setSale_status("Paid");
						specialsalesRepo.save(specialsaleapplyEntity);			
						
						totalAmount = totalAmount.subtract(dueAmountBD).setScale(2, RoundingMode.HALF_UP);				

						applyPaymentReturn(applysaleId, dueAmountBD, returnEntity, currentYear, currentmonth,1);	
						LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return amount Left after applying is >>>>>>>   " + totalAmount);
						
						
					} else if (specialsaleapplyEntity.getPaymentstatus().equalsIgnoreCase("Due")) {

						LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is less than due >>>>>>>   " + totalAmount);
						BigDecimal	paidTotal = new BigDecimal(specialsaleapplyEntity.getPaid()).setScale(2, RoundingMode.HALF_UP);			
						LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmount);
						
						specialsaleapplyEntity.setPaid(paidTotal.add(totalAmount).doubleValue());
						
						specialsalesRepo.save(specialsaleapplyEntity);
						
						applyPaymentReturn(applysaleId, totalAmount, returnEntity, currentYear, currentmonth,1);					
					
						totalAmount = new BigDecimal(0.0);
						LeoLogger.info(	"ReturnServiceImpl ###updateNewPendingInvoiceReturn>>>>>>>> Return amount Left after applying is >>>>>>>   " + totalAmount.toString());

					}

					returnsRepo.save(returnEntity);

					
					if (totalAmount.compareTo(new BigDecimal(0.0)) > 0) {
						LeoLogger.info("ReturnserviceImpl>>> updateNewPendingInvoiceReturn>> Return amount is still left for Special sales >>>"
								+ totalAmount.toPlainString());
					
						List<SpecialSalesEntity> specialsalesEntityList = specialsalesRepo.findAllByMemberidAndPaymentstatusOrderBySaleIdAsc(memberUser.getId(), "Due");
						LeoLogger.info("ReturnserviceImpl>>> updateNewPendingInvoiceReturn>> Sales list in ascending order "
								+ specialsalesEntityList.toString());

				
						for (SpecialSalesEntity specialsalesEntityRes : specialsalesEntityList) {

							
							double dueAmountSpecialSale = specialsalesEntityRes.getGrand_total() - specialsalesEntityRes.getPaid();
							if (totalAmount.doubleValue() >= dueAmountSpecialSale)

							{							
								LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is more than due >>>>>>>   " + totalAmount);
								BigDecimal dueAmountSpecialSaleBD = new BigDecimal(dueAmountSpecialSale).setScale(2, RoundingMode.HALF_UP);
								LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmountSpecialSale);
							
								specialsaleapplyEntity.setPaid(specialsaleapplyEntity.getGrand_total());
								specialsaleapplyEntity.setPaymentstatus("Paid");
								specialsaleapplyEntity.setSale_status("Paid");
								specialsalesRepo.save(specialsaleapplyEntity);			
								
								totalAmount = totalAmount.subtract(dueAmountSpecialSaleBD).setScale(2, RoundingMode.HALF_UP);				
								
								applyPaymentReturn(specialsalesEntityRes.getSaleId(), dueAmountSpecialSaleBD, returnEntity, currentYear, currentmonth,1);
								
								
								LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return amount Left after applying is >>>>>>>   " + totalAmount);
								

							} else if (specialsalesEntityRes.getPaymentstatus().equalsIgnoreCase("Due")) {

								LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Return  amount is less than due >>>>>>>   " + totalAmount);
								LeoLogger.info(	"ReturnServiceImpl ### updateNewPendingInvoiceReturn >> Due amount is>>>>>>>   " + dueAmountSpecialSale);
								BigDecimal	paidTotal = new BigDecimal(specialsaleapplyEntity.getPaid()).setScale(2, RoundingMode.HALF_UP);			
								
								specialsaleapplyEntity.setPaid(paidTotal.add(totalAmount).doubleValue());
								
								specialsalesRepo.save(specialsaleapplyEntity);
								applyPaymentReturn(specialsalesEntityRes.getSaleId(), totalAmount, returnEntity, currentYear, currentmonth,1);								
								
								totalAmount = new BigDecimal(0.0);
								LeoLogger.info(	"ReturnServiceImpl ###updateNewPendingInvoiceReturn>>>>> Return amount Left after applying is >>>>>>>   " + totalAmount.toString());
																
								break;

							}
						}

					}
				}
	  			
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewPendingInvoiceReturn>>>>>>>>>>> Updating the Financial transaction and FT" );
		updateFinancialTransactionAndFT(totalAmountFixed,memberUser.getId(),returnEntity.getReturnType());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewPendingInvoiceReturn>>>>>>>>>>> Adding return items and Changing Inventory" );
		addReturnItemNAdjustProducts(addItemReqPojos, returnEntity, totalAmountFixed, new UnitEntity(), memberUser);
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewPendingInvoiceReturn>>>>>>>>>>>Updating Register" );
		updateRegister(totalAmountFixed);
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewPendingInvoiceReturn>>>>>>>>>>>Adding entry" );
		applyRegisterhistoryreturnedit(returnEntity);
	
	}
	
	private void updateNewStoreCreditReturn(List<AddItemReqPojo> addItemReqPojos,long returnId ,boolean isOldCashReturn, MemberUser memberUser)  {
				
		double  unit_price = 0;
		int quantity = 0;
		BigDecimal taxAmount= new BigDecimal(0.0);
		BigDecimal subTotal= new BigDecimal(0.0);
		
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		int currentmonth = date.getMonth() + 1;
		
		ReturnsEntity returnEntity = null;
		
		if(isOldCashReturn) {	
			LeoLogger.info("ReturnserviceImpl >>>>> updateNewStoreCreditReturn >>>>>>>>> Deleting old ReturnCashEntity cause new return is not cash >>> " + returnId);
			ReturnCashEntity returncashEntity = returnscashRepo.findByReturnId(returnId);
			returnscashRepo.delete(returncashEntity);
			returnEntity = new ReturnsEntity();
		}else {			
			returnEntity = returnsRepo.findByReturnId(returnId);			
		}	
	
		for (AddItemReqPojo additem : addItemReqPojos) {
				
			unit_price = additem.getPrice().doubleValue();
			quantity = Integer.parseInt(additem.getQuantity());
			
			subTotal = subTotal.add(new BigDecimal(unit_price * quantity).setScale(2, RoundingMode.HALF_UP));				
			taxAmount = taxAmount.add(additem.getSubtotal().multiply(new BigDecimal(0.125)) ).setScale(2, RoundingMode.HALF_UP);

		}
			
		returnEntity.setMemberid(memberUser.getId());
		returnEntity.setMember_name(memberUser.getName());		
		returnEntity.setCustomeraddress(memberUser.getAddress());
		returnEntity.setPincode(memberUser.getPincode());
		returnEntity.setMembername(memberUser.getName());			
		returnEntity.setReturnType(2);
		returnEntity.setIsActive(0);		
		returnEntity.setNote(addItemReqPojos.get(0).getNote());

		if(returnEntity.getReturnId() == 0) {
		returnEntity.setDate(date);
		}
				
		Long applysaleId = addItemReqPojos.get(0).getApplysaleId();
		LeoLogger.info("ReturnserviceImpl >>>>> updateNewStoreCreditReturn >>>>>>>>> Apply Invoice saleId  >>> " + applysaleId);	
		
		returnEntity.setSaleid(String.valueOf(applysaleId));
			
		
		if (memberUser.getCtype().equalsIgnoreCase("Special")) {
			SpecialSalesEntity specialSale = specialsalesRepo.findBySaleId(applysaleId);
			 returnEntity.setSalereferenceno(specialSale.getReferenceno());
			 
		}
		else {
			SalesEntity	 sale = salesRepo.findBySaleId(applysaleId);
			 returnEntity.setSalereferenceno(sale.getReferenceno());
		}
		
		   if (memberUser.getCtype().equalsIgnoreCase("Special"))			
				  taxAmount = new BigDecimal(0.0);	
	      
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>>Tax Amount....." + taxAmount);		
		returnEntity.setTax(taxAmount.doubleValue());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>>Sub Total Amount....." + subTotal);
		returnEntity.setAmount(subTotal.doubleValue());
		

		BigDecimal totalAmount =subTotal.add(taxAmount).setScale(2, RoundingMode.HALF_UP);
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>> Total Amount....." + totalAmount);
		returnEntity.setUserid(totalAmount.doubleValue());
		
		returnEntity = returnsRepo.save(returnEntity);				
		returnEntity.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + returnEntity.getReturnId());
				
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>>Return....." + returnEntity);
	
		
		LeoLogger.info("ReturnServiceImpl----updateNewStoreCreditReturn --Member Credit Amount before Adding >>>>>>>>>> "+memberUser.getCreditpayment());
		BigDecimal creditAmount = new BigDecimal(memberUser.getCreditpayment()).add(totalAmount).setScale(2, RoundingMode.HALF_UP);		
		memberUser.setCreditpayment(creditAmount.doubleValue());
		memberUserRepo.save(memberUser);
		LeoLogger.info("ReturnServiceImpl----updateNewStoreCreditReturn --Member Credit Amount After Adding >>>>>>>>>> "+memberUser.getCreditpayment());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>> Adding return items and Changing Inventory" );
		addReturnItemNAdjustProducts(addItemReqPojos, returnEntity, totalAmount, new UnitEntity(), memberUser);
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>> Adding Payment" );
		applyPaymentReturn(applysaleId, totalAmount, returnEntity, currentYear,currentmonth,returnEntity.getReturnType());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>> Updating the Financial transaction and FT" );
		updateFinancialTransactionAndFT(totalAmount,memberUser.getId(),returnEntity.getReturnType());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>>Updating Register" );
		updateRegister(totalAmount);
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewStoreCreditReturn>>>>>>>>>>>Adding entry" );
		applyRegisterhistoryreturnedit(returnEntity);

		
	}
	
	private void updateNewCashRefundReturn(List<AddItemReqPojo> addItemReqPojos,long returnId ,boolean isOldCashReturn, MemberUser memberUser)  {
		
		double unit_price = 0;
		int quantity = 0;

		BigDecimal taxAmount= new BigDecimal(0.0);
		BigDecimal subTotal= new BigDecimal(0.0);
		
		Date date = new Date();
		int year = date.getYear();
		int currentYear = year + 1900;
		int currentmonth = date.getMonth() + 1;
		
		ReturnCashEntity returncashEntity = null;
		
		if(isOldCashReturn) {				
			returncashEntity = returnscashRepo.findByReturnId(returnId);				
		}else {
			LeoLogger.info("ReturnserviceImpl >>>>> updateNewStoreCreditReturn >>>>>>>>> Deleting old ReturnEntity cause new return is  cash >>> " + returnId);
			ReturnsEntity returnEntity = returnsRepo.findByReturnId(returnId);
			returnsRepo.delete(returnEntity);
			returncashEntity = new ReturnCashEntity();
		}
		

	
		for (AddItemReqPojo additem : addItemReqPojos) {
				
			unit_price = additem.getPrice().doubleValue();
			quantity = Integer.parseInt(additem.getQuantity());
			
			subTotal = subTotal.add(new BigDecimal(unit_price * quantity).setScale(2, RoundingMode.HALF_UP));					
			taxAmount = taxAmount.add(additem.getSubtotal().multiply(new BigDecimal(0.125))).setScale(2, RoundingMode.HALF_UP);

		}		
	
		
		returncashEntity.setMemberid(memberUser.getId());
		returncashEntity.setMember_name(memberUser.getName());		
		returncashEntity.setCustomeraddress(memberUser.getAddress());
		returncashEntity.setPincode(memberUser.getPincode());
		returncashEntity.setMembername(memberUser.getName());			
		returncashEntity.setIsActive(0);		
		returncashEntity.setNote(addItemReqPojos.get(0).getNote());

		if(returncashEntity.getReturnId() == 0) {
			returncashEntity.setDate(date);
		}
		
		Long applysaleId = addItemReqPojos.get(0).getApplysaleId();
		LeoLogger.info("ReturnserviceImpl >>>>> updateNewCashRefundReturn >>>>>>>>> Apply Invoice saleId  >>> " + applysaleId);
		
		returncashEntity.setSaleid(String.valueOf(applysaleId));
		
		returncashEntity = returnscashRepo.save(returncashEntity);				
		returncashEntity.setReferenceno("RETURN" + currentYear + "/" + currentmonth + "/" + returncashEntity.getReturnId());



		
		if (memberUser.getCtype().equalsIgnoreCase("Special")) {
			SpecialSalesEntity specialSale = specialsalesRepo.findBySaleId(applysaleId);
			 returncashEntity.setSalereferenceno(specialSale.getReferenceno());
		}
		else {
			SalesEntity sale = salesRepo.findBySaleId(applysaleId);
			 returncashEntity.setSalereferenceno(sale.getReferenceno());
		}
		
		   if (memberUser.getCtype().equalsIgnoreCase("Special"))			
				  taxAmount = new BigDecimal(0.0);	
	      
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewCashRefundReturn>>>>>>>>>>>Tax Amount....." + taxAmount);		
		returncashEntity.setTax(taxAmount.doubleValue());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewCashRefundReturn>>>>>>>>>>>Sub Total Amount....." + subTotal);
		returncashEntity.setAmount(subTotal.doubleValue());
		

		BigDecimal totalAmount =subTotal.add(taxAmount).setScale(2, RoundingMode.HALF_UP);
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewCashRefundReturn>>>>>>>>>>> Total Amount....." + totalAmount);
		returncashEntity.setUserid(totalAmount.doubleValue());
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewCashRefundReturn>>>>>>>>>>> Adding return items and Changing Inventory" );
		addReturncashItemNAdjustProducts(addItemReqPojos, returncashEntity, totalAmount, new UnitEntity(), memberUser);
		
		LeoLogger.info("ReturnserviceImpl >>>>>updateNewCashRefundReturn>>>>>>>>>>> Updating Register" );
		updateRegister(totalAmount);	
		
	}
	
	

	@Override
	public ResultVO newDeleteReturn(long returnId) {
		
		ResultVO resultVO = new ResultVO();
		try {
		
			ReturnsEntity returnEntity = returnsRepo.findByReturnId(returnId);		
			LeoLogger.info("ReturnServiceImpl---New Delete Return  entity is >>>>> " + returnEntity);
			
			if(returnEntity.getReturnType() != 1 && returnEntity.getReturnType() != 2 )  {
				
				resultVO.setMsgDescr("Error occured while Deleting return!");
				resultVO.setError(true);				
				return resultVO;
				
			}
			
			List<ReturnItemEntity>  returnItemEntityList = returnItemRepo.findByReturnid(returnEntity.getReturnId());
			LeoLogger.info("ReturnServiceImpl--New Delete Return-----returnItemEntityList found >>>> "+ returnItemEntityList);
			
			MemberUser memberUser = memberUserRepo.findById(returnEntity.getMemberid());
			LeoLogger.info("ReturnServiceImpl----New Delete Return --Member>>>>>>>>>> " + memberUser);
			

			BigDecimal returnGrandTotal = new BigDecimal(returnEntity.getAmount() + returnEntity.getTax()).setScale(2, RoundingMode.HALF_UP);

			LeoLogger.info("ReturnServiceImpl--New Delete Return---Total Previous/Old Return amount  >>>>> "
					+ returnGrandTotal);
			
		
			
			LeoLogger.info("ReturnServiceImpl----New Delete Return --reversing inventory>>>>>>>>>> ");				
			restoreInventory(returnItemEntityList);
			
			
			if(returnEntity.getReturnType() == 1)  {
			
			LeoLogger.info("ReturnServiceImpl----New Delete Return --reverse sales and removing payments>>>>>>>>>> ");	
			reversePayments(returnEntity, new ArrayList<PaymentEntity>(), memberUser);
			
			LeoLogger.info("ReturnServiceImpl----New Delete Return --reverse FinancialTransaction And FT>>>>>>>>>> ");	
			reverseFinancialTransactionAndFT(returnGrandTotal,memberUser.getId(),returnEntity.getReturnType());
			
			}
			
			
			if(returnEntity.getReturnType() == 2)  {
				
			LeoLogger.info("ReturnServiceImpl----New Delete Return --Member Credit Amount before Deliting >>>>>>>>>> "+memberUser.getCreditpayment());
			BigDecimal creditAmount = new BigDecimal(memberUser.getCreditpayment()).subtract(returnGrandTotal).setScale(2, RoundingMode.HALF_UP);		
			memberUser.setCreditpayment(creditAmount.doubleValue());
			memberUserRepo.save(memberUser);
			LeoLogger.info("ReturnServiceImpl----New Delete Return --Member Credit Amount After Deliting >>>>>>>>>> "+memberUser.getCreditpayment());
			
			
			LeoLogger.info("ReturnServiceImpl----New Delete Return --removing payment by pref>>>>>>>>>> "+returnEntity.getReturnId() + "-" + returnEntity.getSaleid());
			PaymentEntity oldCreditpayment  = paymentRepo.findByPref(returnEntity.getReturnId() + "-" + returnEntity.getSaleid());
			paymentRepo.delete(oldCreditpayment);
			
			LeoLogger.info("ReturnServiceImpl----New Delete Return --reverse FinancialTransaction And FT>>>>>>>>>> ");	
			reverseFinancialTransactionAndFT(returnGrandTotal,memberUser.getId(),returnEntity.getReturnType());

			}
		
			returnEntity.setIsActive(1);
			returnsRepo.save(returnEntity);
	
			reverseRegister(returnGrandTotal) ;

			applyRegisterhistorydeletereturn(returnEntity);

			resultVO.setMsgDescr("Return Deleted Sucessfully");
			resultVO.setMsgCode("001");
			resultVO.setError(false);
			return resultVO;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}
	
	
	private void reverseRegister(BigDecimal OldReturnGrandTotal) {
		
		
		RegisterEntity registerEntity = new RegisterEntity();

		registerEntity = registerRepo.findAByDate(new Date());


		if (registerEntity != null)

		{
			LeoLogger.info("ReturnServiceImpl----Reverse Register--Before Reverse Refund>>>>>>>>>>"+registerEntity.getRefunds() );	
			LeoLogger.info("ReturnServiceImpl----Reverse Register--Before Reverse Closing balance>>>>>>>>>>"+registerEntity.getClosingbal() );	
			
			registerEntity.setRefunds(registerEntity.getRefunds() - OldReturnGrandTotal.doubleValue());
			registerEntity.setClosingbal(registerEntity.getClosingbal() + OldReturnGrandTotal.doubleValue());
			registerRepo.save(registerEntity);	
			
			LeoLogger.info("ReturnServiceImpl----Reverse Register--After Reverse Refund>>>>>>>>>>"+registerEntity.getRefunds() );	
			LeoLogger.info("ReturnServiceImpl----Reverse Register--After Reverse Closing balance>>>>>>>>>>"+registerEntity.getClosingbal() );	
		}
		
	}
	
	private void updateRegister(BigDecimal newReturnGrandTotal) {
				
		RegisterEntity registerEntity = new RegisterEntity();

		registerEntity = registerRepo.findAByDate(new Date());

		if (registerEntity != null)

		{		
			LeoLogger.info("ReturnServiceImpl----Update Register--Before Update Refund>>>>>>>>>>"+registerEntity.getRefunds() );	
			LeoLogger.info("ReturnServiceImpl----Update Register--Before Update Closing balance>>>>>>>>>>"+registerEntity.getClosingbal() );	
			
			registerEntity.setRefunds(registerEntity.getRefunds() + newReturnGrandTotal.doubleValue());
			registerEntity.setClosingbal(registerEntity.getClosingbal() - newReturnGrandTotal.doubleValue());
			registerRepo.save(registerEntity);
			
			LeoLogger.info("ReturnServiceImpl----Update Register--After Update Refund>>>>>>>>>>"+registerEntity.getRefunds() );	
			LeoLogger.info("ReturnServiceImpl----Update Register--After Update Closing balance>>>>>>>>>>"+registerEntity.getClosingbal() );	
		}
		
	}
	
	private void reverseFinancialTransactionAndFT(BigDecimal OldReturnGrandTotal , long memberId ,long applyReturn) {
		
		FinancialTransactionEntity ftransLatest = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(memberId);
		LeoLogger.info("ReturnServiceImpl----  Financial Transaction  --Balance and Amount Before reversing >>>>>>>>>>Balance  "+ftransLatest.getBalance() +" Amount "+ftransLatest.getAmount());
		BigDecimal balance =new BigDecimal(0.0);
		
		if(applyReturn == 1)
		 balance = new BigDecimal(ftransLatest.getBalance()).add(OldReturnGrandTotal).setScale(2, RoundingMode.HALF_UP);
		else
		 balance = new BigDecimal(ftransLatest.getBalance()).setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal financialAmount = new BigDecimal(ftransLatest.getAmount()).subtract(OldReturnGrandTotal).setScale(2, RoundingMode.HALF_UP);
			
		ftransLatest.setAmount(financialAmount.doubleValue());
		ftransLatest.setBalance(balance.doubleValue());		
		financialTransactionsRepo.save(ftransLatest);		
		LeoLogger.info("ReturnServiceImpl----Financial Transaction  --Balance and Amount After reversing >>>>>>>>>>Balance  "+ftransLatest.getBalance() +" Amount "+ftransLatest.getAmount());
		
		FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(memberId);
		LeoLogger.info("ReturnServiceImpl----FT  --Balance and Amount Before reversing >>>>>>>>>>Balance  "+fttLatest.getBalance() +" Amount "+fttLatest.getAmount());
		
		fttLatest.setAmount(financialAmount.doubleValue());
		fttLatest.setBalance(balance.doubleValue());
		fTRepo.save(fttLatest);		
		LeoLogger.info("ReturnServiceImpl---- FT  --Balance and Amount After reversing >>>>>>>>>>Balance  "+fttLatest.getBalance() +" Amount "+fttLatest.getAmount());
							
	}
	
	private void updateFinancialTransactionAndFT(BigDecimal OldReturnGrandTotal , long memberId ,long applyReturn) {
		
		FinancialTransactionEntity ftransLatest = financialTransactionsRepo.findTopByCustomerIdOrderByFanIdDesc(memberId);
		LeoLogger.info("ReturnServiceImpl----  Financial Transaction  --Balance and Amount Before updating >>>>>>>>>>Balance  "+ftransLatest.getBalance() +" Amount "+ftransLatest.getAmount());
		BigDecimal balance =new BigDecimal(0.0);
		
		if(applyReturn == 1)
		 balance = new BigDecimal(ftransLatest.getBalance()).subtract(OldReturnGrandTotal).setScale(2, RoundingMode.HALF_UP);
		else
		 balance = new BigDecimal(ftransLatest.getBalance()).setScale(2, RoundingMode.HALF_UP);
		
		BigDecimal financialAmount = new BigDecimal(ftransLatest.getAmount()).add(OldReturnGrandTotal).setScale(2, RoundingMode.HALF_UP);
			
		ftransLatest.setAmount(financialAmount.doubleValue());
		ftransLatest.setBalance(balance.doubleValue());		
		financialTransactionsRepo.save(ftransLatest);		
		LeoLogger.info("ReturnServiceImpl----Financial Transaction  --Balance and Amount After updating >>>>>>>>>>Balance  "+ftransLatest.getBalance() +" Amount "+ftransLatest.getAmount());
		
		FTEntity fttLatest = fTRepo.findTopByCustomerIdOrderByFanIdDesc(memberId);
		LeoLogger.info("ReturnServiceImpl----FT  --Balance and Amount Before updating >>>>>>>>>>Balance  "+fttLatest.getBalance() +" Amount "+fttLatest.getAmount());
		
		fttLatest.setAmount(financialAmount.doubleValue());
		fttLatest.setBalance(balance.doubleValue());
		fTRepo.save(fttLatest);		
		LeoLogger.info("ReturnServiceImpl---- FT  --Balance and Amount After updating >>>>>>>>>>Balance  "+fttLatest.getBalance() +" Amount "+fttLatest.getAmount());
							
	}
	
	
	
	
	
	
}
