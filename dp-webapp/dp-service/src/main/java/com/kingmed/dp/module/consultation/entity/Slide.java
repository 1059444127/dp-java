package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

public class Slide extends DataEntity<Slide>{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1065114635036200233L;
	
	private String barcode;
	
	private String label;
	
	private String overview; 
	
	private String labelWithOverview;
	
	private String url; 
	
	private String scannerModel;
	
	private String createDateTime;
	
    private String scannerCode;
    
    private String physicalPath;
    
    private String path;
    
    private String token;
    
    private String point;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getLabelWithOverview() {
		return labelWithOverview;
	}

	public void setLabelWithOverview(String labelWithOverview) {
		this.labelWithOverview = labelWithOverview;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getScannerModel() {
		return scannerModel;
	}

	public void setScannerModel(String scannerModel) {
		this.scannerModel = scannerModel;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getScannerCode() {
		return scannerCode;
	}

	public void setScannerCode(String scannerCode) {
		this.scannerCode = scannerCode;
	}

	public String getPhysicalPath() {
		return physicalPath;
	}

	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	
	
}
