package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CstSupDiagnose
** 描    述：会诊-补充意见
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 会诊-补充意见(T_CST_SUP_DIAGNOSE)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CstSupDiagnose extends DataEntity<CstSupDiagnose> {
    /** 版本号 */
    private static final long serialVersionUID = 8507242595025406857L;
    
    /** 病例 */
    private String caseId;
    
    /** 内容 */
    private String content;
    
    
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
     * 获取内容
     * 
     * @return 内容
     */
    public String getContent() {
        return this.content;
    }
     
    /**
     * 设置内容
     * 
     * @param content
     *          内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}