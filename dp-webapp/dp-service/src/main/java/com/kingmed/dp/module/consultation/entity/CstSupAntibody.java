package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CstSupAntibody
** 描    述：会诊-加做项目
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 会诊-加做项目(T_CST_SUP_ANTIBODY)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CstSupAntibody extends DataEntity<CstSupAntibody> {
    /** 版本号 */
    private static final long serialVersionUID = -5109020229993401817L;
    /** 病例 */
    private String caseId;
    
    /** 抗体代码 */
    private String antibodyCode;
    
    /** 抗体名称 */
    private String antibodyName;
    
    /** 加做状态 */
    private String status;
    
    
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
     * 获取抗体名称
     * 
     * @return 抗体名称
     */
    public String getAntibodyName() {
        return this.antibodyName;
    }
     
    /**
     * 设置抗体名称
     * 
     * @param antibodyName
     *          抗体名称
     */
    public void setAntibodyName(String antibodyName) {
        this.antibodyName = antibodyName;
    }
    
    /**
     * 获取加做状态
     * 
     * @return 加做状态
     */
    public String getStatus() {
        return this.status;
    }
     
    /**
     * 设置加做状态
     * 
     * @param status
     *          加做状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
}