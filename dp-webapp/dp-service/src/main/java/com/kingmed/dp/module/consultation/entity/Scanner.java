package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

public class Scanner extends DataEntity<Scanner>{

	private static final long serialVersionUID = -30063309782203566L;

	private String companyId;
	
	private String commonPoint;
	
	private String scannerCode; 
	
	private String mosaic;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCommonPoint() {
		return commonPoint;
	}

	public void setCommonPoint(String commonPoint) {
		this.commonPoint = commonPoint;
	}

	public String getScannerCode() {
		return scannerCode;
	}

	public void setScannerCode(String scannerCode) {
		this.scannerCode = scannerCode;
	}

	public String getMosaic() {
		return mosaic;
	}

	public void setMosaic(String mosaic) {
		this.mosaic = mosaic;
	}
	
	
}
