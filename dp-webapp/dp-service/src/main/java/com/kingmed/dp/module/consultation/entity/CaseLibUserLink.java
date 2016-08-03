package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CaseLibUserLink
** 描    述：病例库-用户访问
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 病例库-用户访问(T_CASE_LIB_USER_LINK)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CaseLibUserLink extends DataEntity<CaseLibUserLink> {
    /** 版本号 */
    private static final long serialVersionUID = -7480000742792565975L;
    /** 病例 */
    private String caseId;
    
    /** 数字切片 */
    private String caseLibSlideId;
    
    /** 访问者 */
    private String vistorBy;
    
    /** 访问者IP */
    private String vistorIp;
    
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
     * 获取数字切片
     * 
     * @return 数字切片
     */
    public String getCaseLibSlideId() {
        return this.caseLibSlideId;
    }
     
    /**
     * 设置数字切片
     * 
     * @param caseLibSlideId
     *          数字切片
     */
    public void setCaseLibSlideId(String caseLibSlideId) {
        this.caseLibSlideId = caseLibSlideId;
    }
    
    /**
     * 获取访问者
     * 
     * @return 访问者
     */
    public String getVistorBy() {
        return this.vistorBy;
    }
     
    /**
     * 设置访问者
     * 
     * @param vistorBy
     *          访问者
     */
    public void setVistorBy(String vistorBy) {
        this.vistorBy = vistorBy;
    }
    
    /**
     * 获取访问者IP
     * 
     * @return 访问者IP
     */
    public String getVistorIp() {
        return this.vistorIp;
    }
     
    /**
     * 设置访问者IP
     * 
     * @param vistorIp
     *          访问者IP
     */
    public void setVistorIp(String vistorIp) {
        this.vistorIp = vistorIp;
    }
}