package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CaseLibNo
** 描    述：病例库-编号
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 病例库-编号(T_CASE_LIB_NO)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CaseLibNo extends DataEntity<CaseLibNo> {
    /** 版本号 */
    private static final long serialVersionUID = 1570564915389769775L;
    /** 病例库编号 */
    private Integer caseLibNo;
    
    /**
     * 获取病例库编号
     * 
     * @return 病例库编号
     */
    public Integer getCaseLibNo() {
        return this.caseLibNo;
    }
     
    /**
     * 设置病例库编号
     * 
     * @param caseLibNo
     *          病例库编号
     */
    public void setCaseLibNo(Integer caseLibNo) {
        this.caseLibNo = caseLibNo;
    }
}