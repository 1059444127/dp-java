package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CstSlide
** 描    述：会诊-数字切片
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 会诊-数字切片(T_CST_SLIDE)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CstSlide extends DataEntity<CstSlide> {
    /** 版本号 */
    private static final long serialVersionUID = -1643406213070788756L;
    
    /** 病例 */
    private String caseId;
    
    /** 条码 */
    private String barcode;
    
    /**标本条码*/
	private String kmbarcode;
	 
	/**标本id*/
	private String requestId;
    
    /** 抗体名称 */
    private String antitbodyName;
    
    /** 抗体代码 */
    private String antibodyCode;
    
    /** 标签 */
    private String label;
    
    /** 缩略图 */
    private String overview;
    
    /** 标签&缩略图 */
    private String labelWithOverview;
    
    /** 书签切片URL */
    private String url;
    
    /** 扫描仪类型 */
    private String scanner;
    
    
    /**
     * 获取病例
     * 
     * @return 病例
     */
    public String getCaseId() {
        return this.caseId;
    }
     
    /**
     * 设置病例
     * 
     * @param caseId
     *          病例
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
    
    /**
     * 获取条码
     * 
     * @return 条码
     */
    public String getBarcode() {
        return this.barcode;
    }
     
    /**
     * 设置条码
     * 
     * @param barcode
     *          条码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    
    public String getKmbarcode() {
		return kmbarcode;
	}

	public void setKmbarcode(String kmbarcode) {
		this.kmbarcode = kmbarcode;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
     * 获取抗体名称
     * 
     * @return 抗体名称
     */
    public String getAntitbodyName() {
        return this.antitbodyName;
    }
     
    /**
     * 设置抗体名称
     * 
     * @param antitbodyName
     *          抗体名称
     */
    public void setAntitbodyName(String antitbodyName) {
        this.antitbodyName = antitbodyName;
    }
    
    /**
     * 获取抗体代码
     * 
     * @return 抗体代码
     */
    public String getAntibodyCode() {
        return this.antibodyCode;
    }
     
    /**
     * 设置抗体代码
     * 
     * @param antibodyCode
     *          抗体代码
     */
    public void setAntibodyCode(String antibodyCode) {
        this.antibodyCode = antibodyCode;
    }
    
    /**
     * 获取标签
     * 
     * @return 标签
     */
    public String getLabel() {
        return this.label;
    }
     
    /**
     * 设置标签
     * 
     * @param label
     *          标签
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    /**
     * 获取缩略图
     * 
     * @return 缩略图
     */
    public String getOverview() {
        return this.overview;
    }
     
    /**
     * 设置缩略图
     * 
     * @param overview
     *          缩略图
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }
    
    /**
     * 获取标签&缩略图
     * 
     * @return 标签&缩略图
     */
    public String getLabelWithOverview() {
        return this.labelWithOverview;
    }
     
    /**
     * 设置标签&缩略图
     * 
     * @param labelWithOverview
     *          标签&缩略图
     */
    public void setLabelWithOverview(String labelWithOverview) {
        this.labelWithOverview = labelWithOverview;
    }
    
    /**
     * 获取书签切片URL
     * 
     * @return 书签切片URL
     */
    public String getUrl() {
        return this.url;
    }
     
    /**
     * 设置书签切片URL
     * 
     * @param url
     *          书签切片URL
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * 获取扫描仪类型
     * 
     * @return 扫描仪类型
     */
    public String getScanner() {
        return this.scanner;
    }
     
    /**
     * 设置扫描仪类型
     * 
     * @param scanner
     *          扫描仪类型
     */
    public void setScanner(String scanner) {
        this.scanner = scanner;
    }
}