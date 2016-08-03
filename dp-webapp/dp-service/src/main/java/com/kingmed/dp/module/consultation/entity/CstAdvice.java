package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CstAdvice
** 描    述：会诊-反馈意见
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 会诊-反馈意见(T_CST_ADVICE)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CstAdvice extends DataEntity<CstAdvice> {
    /** 版本号 */
    private static final long serialVersionUID = 1733081365016862241L;
    /** 病例 */
    private String caseId;
    
    /** 内容 */
    private String content;
    
    /** 发送系统 */
    private String srcApp;
    
    /** 接收系统 */
    private String dstApp;
    
    /** 发送状态 */
    private String sendStatus;
    
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
    
    /**
     * 获取发送系统
     * 
     * @return 发送系统
     */
    public String getSrcApp() {
        return this.srcApp;
    }
     
    /**
     * 设置发送系统
     * 
     * @param srcApp
     *          发送系统
     */
    public void setSrcApp(String srcApp) {
        this.srcApp = srcApp;
    }
    
    /**
     * 获取接收系统
     * 
     * @return 接收系统
     */
    public String getDstApp() {
        return this.dstApp;
    }
     
    /**
     * 设置接收系统
     * 
     * @param dstApp
     *          接收系统
     */
    public void setDstApp(String dstApp) {
        this.dstApp = dstApp;
    }
    
    /**
     * 获取发送状态
     * 
     * @return 发送状态
     */
    public String getSendStatus() {
        return this.sendStatus;
    }
     
    /**
     * 设置发送状态
     * 
     * @param sendStatus
     *          发送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
}