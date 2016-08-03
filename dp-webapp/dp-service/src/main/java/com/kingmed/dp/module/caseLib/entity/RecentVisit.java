package com.kingmed.dp.module.caseLib.entity;

import org.os.common.persistence.DataEntity;

/**
 * 读片会(T_READ_FILM)
 * 
 * @author zl
 * @version 1.0.0 2016-05-27
 */
public class RecentVisit extends DataEntity<RecentVisit> {
	
	/** 版本号 */
	private static final long serialVersionUID = -2781145935706285321L;

	 private String userId;
	  
	 private String ip;
	 
	 private CaseLibSlide caseLibSlide;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public CaseLibSlide getCaseLibSlide() {
		return caseLibSlide;
	}

	public void setCaseLibSlide(CaseLibSlide caseLibSlide) {
		this.caseLibSlide = caseLibSlide;
	}
	 
}