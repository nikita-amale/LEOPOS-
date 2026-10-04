/**
 * 
 */
package com.leonet.serviceimpl;

import java.util.ArrayList;

import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.entity.PlanEntity;
import com.leonet.common.pojo.PlanPojo;
import com.leonet.common.pojo.ResultVO;
import com.leonet.repo.PlanRepo;
import com.leonet.service.PlanService;
import com.leonet.util.LeoLogger;

/**
 * @author MONINDER
 *
 */
@Service
public class PlanServiceImpl implements PlanService {


	@Autowired
	PlanRepo planRepo;


	@Autowired
	Mapper mapper;

	@Override
	public ResultVO addPlan(PlanPojo planPojo) {
		ResultVO resultVO = new ResultVO();
		LeoLogger.info("PlanServiceImpl --- addPlan");
		//System.out.println("planPojo" + planPojo.toString());
		
		PlanEntity planEntity = new PlanEntity();
		
		try {

			PlanEntity planEntityRes = planRepo.findByPlanname(planPojo.getPlanName());

			if (planEntityRes == null) {

				planEntity.setPlanName(planPojo.getPlanName());
				planEntity.setBonusPts(0);
				planEntity.setBooksPts(planPojo.getBooksPts());
				planEntity.setDeposit(planPojo.getDeposit());
				planEntity.setGamestoysPts(planPojo.getGamestoysPts());
				planEntity.setValidity(planPojo.getValidity());
				planEntity.setMonthlyFee(planPojo.getMonthlyFee());
				
				planRepo.save(planEntity);

				resultVO.setMsgDescr("Plan Saved Sucessfully");
				resultVO.setMsgCode("001");
				resultVO.setError(false);
				return resultVO;
			} else {
				planEntity.setPlanName(planPojo.getPlanName());
				planEntity.setBonusPts(0);
				planEntity.setBooksPts(planPojo.getBooksPts());
				planEntity.setDeposit(planPojo.getDeposit());
				planEntity.setGamestoysPts(planPojo.getGamestoysPts());
				planEntity.setValidity(planPojo.getValidity());
				planEntity.setMonthlyFee(planPojo.getMonthlyFee());

				resultVO.setMsgDescr("Plan Updated Sucessfully");
				resultVO.setMsgCode("002");
				resultVO.setError(true);
				return resultVO;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVO;
	}



}
