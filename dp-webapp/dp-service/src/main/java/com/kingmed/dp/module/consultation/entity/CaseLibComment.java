package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CaseLibComment
** 描    述：病例库-点评
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 病例库-点评(T_CASE_LIB_COMMENT)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CaseLibComment extends DataEntity<CaseLibComment> {
    /** 版本号 */
    private static final long serialVersionUID = 2362490235459145127L;
    /** 病例 */
    private String libCaseId;
    
    /** 内容 */
    private String content;
    
    
    /**
     * 获取病例
     * 
     * @return 病例
     */
    public String getLibCaseId() {
        return this.libCaseId;
    }
     
    /**
     * 设置病例
     * 
     * @param libCaseId
     *          病例
     */
    public void setLibCaseId(String libCaseId) {
        this.libCaseId = libCaseId;
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