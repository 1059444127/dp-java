package com.kingmed.dp.module.caseLib.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CaseLibSlide
** 描    述：
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * (T_CASE_LIB_SLIDE)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CaseLibSlide extends DataEntity<CaseLibSlide> {
    /** 版本号 */
    private static final long serialVersionUID = 885103186830765651L;
    /** 病例库 */
    private CaseLib caseLib;
    
    /** 条码 */
    private String barcode;
    
    /** 抗体名称 */
    private String antitbodyName;
    
    /** 抗体代码 */
    private String antibodyCode;
    
    /** 抗体序号 */
    private String antibodySeq;
    
    /** 标签图 */
    private String label;
    
    /** 缩略图 */
    private String overview;
    
    /** 标签&缩略图 */
    private String labelWithOverview;
    
    /** 书签切片URL */
    private String url;
    
    /** 扫描仪类型 */
    private String scanner;
    
    /**读片会id*/
    private String readFilmId;
    
    
    
    public CaseLib getCaseLib() {
		return caseLib;
	}

	public void setCaseLib(CaseLib caseLib) {
		this.caseLib = caseLib;
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
     * 获取抗体序号
     * 
     * @return 抗体序号
     */
    public String getAntibodySeq() {
        return this.antibodySeq;
    }
     
    /**
     * 设置抗体序号
     * 
     * @param antibodySeq
     *          抗体序号
     */
    public void setAntibodySeq(String antibodySeq) {
        this.antibodySeq = antibodySeq;
    }
    
    /**
     * 获取标签图
     * 
     * @return 标签图
     */
    public String getLabel() {
        return this.label;
    }
     
    /**
     * 设置标签图
     * 
     * @param label
     *          标签图
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

	public String getReadFilmId() {
		return readFilmId;
	}

	public void setReadFilmId(String readFilmId) {
		this.readFilmId = readFilmId;
	}
    
    
}