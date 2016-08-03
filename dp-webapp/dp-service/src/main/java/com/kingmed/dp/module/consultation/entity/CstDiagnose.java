package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CstDiagnose
** 描    述：会诊-诊断意见书
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 会诊-诊断意见书(T_CST_DIAGNOSE)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CstDiagnose extends DataEntity<CstDiagnose> {
    /** 版本号 */
    private static final long serialVersionUID = 1447771903467023552L;
    
    /** 病例 */
    private String caseId;
    
    /** 诊断意见书 */
    private String report;
    
   
    
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
     * 获取诊断意见书
     * 
     * @return 诊断意见书
     */
    public String getReport() {
        return this.report;
    }
     
    /**
     * 设置诊断意见书
     * 
     * @param report
     *          诊断意见书
     */
    public void setReport(String report) {
        this.report = report;
    }
}