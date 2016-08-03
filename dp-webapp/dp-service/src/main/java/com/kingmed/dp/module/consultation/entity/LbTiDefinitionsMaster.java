package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：LbTiDefinitionsMaster
** 描    述：会诊-检查项目
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 会诊-检查项目(LB_TI_DEFINITIONS_MASTER)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class LbTiDefinitionsMaster extends DataEntity<LbTiDefinitionsMaster> {
    /** 版本号 */
    private static final long serialVersionUID = 4241829769435830882L;
    
    /** TEST_ITEM_NAME_CN */
    private String testItemNameCn;
    
    /** TEST_ITEM_NAME_ALT_CN */
    private String testItemNameAltCn;
    
    /** TEST_ITEM_NAME_ENG */
    private String testItemNameEng;
    
    /** TEST_ITEM_NAME_ALT_ENG */
    private String testItemNameAltEng;
    
    /** SCIENCE_NAME */
    private String scienceName;
    
    /** TEST_ITEM_CODE */
    private String testItemCode;
    
    /**
     * 获取TEST_ITEM_NAME_CN
     * 
     * @return TEST_ITEM_NAME_CN
     */
    public String getTestItemNameCn() {
        return this.testItemNameCn;
    }
     
    /**
     * 设置TEST_ITEM_NAME_CN
     * 
     * @param testItemNameCn
     *          TEST_ITEM_NAME_CN
     */
    public void setTestItemNameCn(String testItemNameCn) {
        this.testItemNameCn = testItemNameCn;
    }
    
    /**
     * 获取TEST_ITEM_NAME_ALT_CN
     * 
     * @return TEST_ITEM_NAME_ALT_CN
     */
    public String getTestItemNameAltCn() {
        return this.testItemNameAltCn;
    }
     
    /**
     * 设置TEST_ITEM_NAME_ALT_CN
     * 
     * @param testItemNameAltCn
     *          TEST_ITEM_NAME_ALT_CN
     */
    public void setTestItemNameAltCn(String testItemNameAltCn) {
        this.testItemNameAltCn = testItemNameAltCn;
    }
    
    /**
     * 获取TEST_ITEM_NAME_ENG
     * 
     * @return TEST_ITEM_NAME_ENG
     */
    public String getTestItemNameEng() {
        return this.testItemNameEng;
    }
     
    /**
     * 设置TEST_ITEM_NAME_ENG
     * 
     * @param testItemNameEng
     *          TEST_ITEM_NAME_ENG
     */
    public void setTestItemNameEng(String testItemNameEng) {
        this.testItemNameEng = testItemNameEng;
    }
    
    /**
     * 获取TEST_ITEM_NAME_ALT_ENG
     * 
     * @return TEST_ITEM_NAME_ALT_ENG
     */
    public String getTestItemNameAltEng() {
        return this.testItemNameAltEng;
    }
     
    /**
     * 设置TEST_ITEM_NAME_ALT_ENG
     * 
     * @param testItemNameAltEng
     *          TEST_ITEM_NAME_ALT_ENG
     */
    public void setTestItemNameAltEng(String testItemNameAltEng) {
        this.testItemNameAltEng = testItemNameAltEng;
    }
    
    /**
     * 获取SCIENCE_NAME
     * 
     * @return SCIENCE_NAME
     */
    public String getScienceName() {
        return this.scienceName;
    }
     
    /**
     * 设置SCIENCE_NAME
     * 
     * @param scienceName
     *          SCIENCE_NAME
     */
    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }
    
    /**
     * 获取TEST_ITEM_CODE
     * 
     * @return TEST_ITEM_CODE
     */
    public String getTestItemCode() {
        return this.testItemCode;
    }
     
    /**
     * 设置TEST_ITEM_CODE
     * 
     * @param testItemCode
     *          TEST_ITEM_CODE
     */
    public void setTestItemCode(String testItemCode) {
        this.testItemCode = testItemCode;
    }
}