package com.kingmed.dp.module.consultation.entity;

import org.os.common.persistence.DataEntity;

/******************************************************************
** 类    名：CstHelpCapture
** 描    述：会诊-辅助图片
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

/**
 * 会诊-辅助图片(T_CST_HELP_CAPTURE)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CstHelpCapture extends DataEntity<CstHelpCapture> {
    /** 版本号 */
    private static final long serialVersionUID = -3495306456862858887L;
    
    /** 病例 */
    private String caseId;
    
    /** 名称 */
    private String name;
    
    /** 图片 */
    private String image;
    
    
    
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
     * 获取名称
     * 
     * @return 名称
     */
    public String getName() {
        return this.name;
    }
     
    /**
     * 设置名称
     * 
     * @param name
     *          名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取图片
     * 
     * @return 图片
     */
    public String getImage() {
        return this.image;
    }
     
    /**
     * 设置图片
     * 
     * @param image
     *          图片
     */
    public void setImage(String image) {
        this.image = image;
    }
}