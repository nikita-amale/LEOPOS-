package com.leonet.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leonet.constant.Action;
import com.leonet.entity.SysAudit;

import com.leonet.service.SysAuditService;
import com.leonet.repo.SysAuditRepo;
import com.leonet.util.LeoLogger;


@Service
public class SysAuditServiceImpl implements SysAuditService {
	
	@Autowired
	private SysAuditRepo sysAuditRepo;

	@Override
	public void setSysAudit( Action action, String description) {
		
		SysAudit sysAudit = new SysAudit();
		sysAudit.setAction(action);
		sysAudit.setDesciption(description);		
		sysAuditRepo.save(sysAudit);
		
		LeoLogger.info("################# [setSysAuditLogs]....[User : "+sysAudit.getCreatedBy()+"] [Activity Type : "+action+"] [Logging Date : "+sysAudit.getCreatedDate()+"] [Logs : "+description+"]");

	}

}
