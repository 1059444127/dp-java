package com.kingmed.dp.module.caseLib.entity;

/******************************************************************
** 类    名：CaseLib
** 描    述：病例库-病例
** 创 建 者：bianj
** 创建时间：2016-05-27 11:09:20
******************************************************************/

import java.sql.Timestamp;

import org.os.common.persistence.DataEntity;

/**
 * 病例库-病例(T_CASE_LIB)
 * 
 * @author bianj
 * @version 1.0.0 2016-05-27
 */
public class CaseLib extends DataEntity<CaseLib> {
    /** 版本号 */
    private static final long serialVersionUID = 4278698644421280838L;
    
    /** 病例库编号 */
    private Integer libNo;
    
    /** 归属用户 */
    private String userId;
    
    /** 归属机构 */
    private String officeId;
    
    /** 归属区域 */
    private String areaId;
    
    /** 病例 */
    private String caseId;
    
    /** 性别 */
    private String gender;
    
    /** 年龄 */
    private String aget;
    
    /** 年龄单位 */
    private String ageUnit;
    
    /** 临床诊断 */
    private String cliDiag;
    
    /** 临床主述 */
    private String cliMas;
    
    /** 手术部位 */
    private String opeFind;
    
    /** 活检巨检部位 */
    private String bioOrg;
    
    /** 诊断意见 */
    private String dia;
    
    /** 病例库创建方式 */
    private String libCreateType;
    
    /** 病例库状态 */
    private String libState;
    
    /** 病例库类型 */
    private String patTypeId;
    
    /** 病例库ICD编码 */
    private String icdNo;
    
    /** 病例库标签 */
    private String tag;
    
    /** 病例库提交者 */
    private String libSubmitBy;
    
    /** 病例库提交时间 */
    private Timestamp libSubmitDate;
    
    /** 病例库审核者 */
    private String libAuditBy;
    
    /** 病例库审核时间 */
    private Timestamp libAuditDate;
    
    /** 来源APP */
    private String srcApp;
    
    /** 来源APP环境 */
    private String srcEnv;
    
    /** 来源App版本 */
    private String srcAppVersion;
    
    /**简要病史*/
    private String history;
    
    /**医院*/
    private String hospital;
    
    /**缩略图 切片信息*/
    private CaseLibSlide caseLibSlide;
    
    private String start_aget;
    
    private String end_aget;
    
    /**临床资料*/
    private String cliData;
    
    /**大体描述*/
    private String genDesc;
    
    private String parentIds;
    
    /**关键字搜索*/
    private String content;
    /**
     * 获取病例库编号
     * 
     * @return 病例库编号
     */
    public Integer getLibNo() {
        return this.libNo;
    }
     
    /**
     * 设置病例库编号
     * 
     * @param libNo
     *          病例库编号
     */
    public void setLibNo(Integer libNo) {
        this.libNo = libNo;
    }
    
    /**
     * 获取归属用户
     * 
     * @return 归属用户
     */
    public String getUserId() {
        return this.userId;
    }
     
    /**
     * 设置归属用户
     * 
     * @param userId
     *          归属用户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取归属机构
     * 
     * @return 归属机构
     */
    public String getOfficeId() {
        return this.officeId;
    }
     
    /**
     * 设置归属机构
     * 
     * @param officeId
     *          归属机构
     */
    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }
    
    /**
     * 获取归属区域
     * 
     * @return 归属区域
     */
    public String getAreaId() {
        return this.areaId;
    }
     
    /**
     * 设置归属区域
     * 
     * @param areaId
     *          归属区域
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
    
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
     * 获取性别
     * 
     * @return 性别
     */
    public String getGender() {
        return this.gender;
    }
     
    /**
     * 设置性别
     * 
     * @param gender
     *          性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * 获取年龄
     * 
     * @return 年龄
     */
    public String getAget() {
        return this.aget;
    }
     
    /**
     * 设置年龄
     * 
     * @param aget
     *          年龄
     */
    public void setAget(String aget) {
        this.aget = aget;
    }
    
    /**
     * 获取年龄单位
     * 
     * @return 年龄单位
     */
    public String getAgeUnit() {
        return this.ageUnit;
    }
     
    /**
     * 设置年龄单位
     * 
     * @param ageUnit
     *          年龄单位
     */
    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }
    
    /**
     * 获取临床诊断
     * 
     * @return 临床诊断
     */
    public String getCliDiag() {
        return this.cliDiag;
    }
     
    /**
     * 设置临床诊断
     * 
     * @param cliDiag
     *          临床诊断
     */
    public void setCliDiag(String cliDiag) {
        this.cliDiag = cliDiag;
    }
    
    /**
     * 获取临床主述
     * 
     * @return 临床主述
     */
    public String getCliMas() {
        return this.cliMas;
    }
     
    /**
     * 设置临床主述
     * 
     * @param cliMas
     *          临床主述
     */
    public void setCliMas(String cliMas) {
        this.cliMas = cliMas;
    }
    
    /**
     * 获取手术部位
     * 
     * @return 手术部位
     */
    public String getOpeFind() {
        return this.opeFind;
    }
     
    /**
     * 设置手术部位
     * 
     * @param opeFind
     *          手术部位
     */
    public void setOpeFind(String opeFind) {
        this.opeFind = opeFind;
    }
    
    /**
     * 获取活检巨检部位
     * 
     * @return 活检巨检部位
     */
    public String getBioOrg() {
        return this.bioOrg;
    }
     
    /**
     * 设置活检巨检部位
     * 
     * @param bioOrg
     *          活检巨检部位
     */
    public void setBioOrg(String bioOrg) {
        this.bioOrg = bioOrg;
    }
    
    /**
     * 获取诊断意见
     * 
     * @return 诊断意见
     */
    public String getDia() {
        return this.dia;
    }
     
    /**
     * 设置诊断意见
     * 
     * @param dia
     *          诊断意见
     */
    public void setDia(String dia) {
        this.dia = dia;
    }
    
    /**
     * 获取病例库创建方式
     * 
     * @return 病例库创建方式
     */
    public String getLibCreateType() {
        return this.libCreateType;
    }
     
    /**
     * 设置病例库创建方式
     * 
     * @param libCreateType
     *          病例库创建方式
     */
    public void setLibCreateType(String libCreateType) {
        this.libCreateType = libCreateType;
    }
    
    /**
     * 获取病例库状态
     * 
     * @return 病例库状态
     */
    public String getLibState() {
        return this.libState;
    }
     
    /**
     * 设置病例库状态
     * 
     * @param libState
     *          病例库状态
     */
    public void setLibState(String libState) {
        this.libState = libState;
    }
    
    /**
     * 获取病例库类型
     * 
     * @return 病例库类型
     */
    public String getPatTypeId() {
        return this.patTypeId;
    }
     
    /**
     * 设置病例库类型
     * 
     * @param patTypeId
     *          病例库类型
     */
    public void setPatTypeId(String patTypeId) {
        this.patTypeId = patTypeId;
    }
    
    /**
     * 获取病例库ICD编码
     * 
     * @return 病例库ICD编码
     */
    public String getIcdNo() {
        return this.icdNo;
    }
     
    /**
     * 设置病例库ICD编码
     * 
     * @param icdNo
     *          病例库ICD编码
     */
    public void setIcdNo(String icdNo) {
        this.icdNo = icdNo;
    }
    
    /**
     * 获取病例库标签
     * 
     * @return 病例库标签
     */
    public String getTag() {
        return this.tag;
    }
     
    /**
     * 设置病例库标签
     * 
     * @param tag
     *          病例库标签
     */
    public void setTag(String tag) {
        this.tag = tag;
    }
    
    /**
     * 获取病例库提交者
     * 
     * @return 病例库提交者
     */
    public String getLibSubmitBy() {
        return this.libSubmitBy;
    }
     
    /**
     * 设置病例库提交者
     * 
     * @param libSubmitBy
     *          病例库提交者
     */
    public void setLibSubmitBy(String libSubmitBy) {
        this.libSubmitBy = libSubmitBy;
    }
    
    /**
     * 获取病例库提交时间
     * 
     * @return 病例库提交时间
     */
    public Timestamp getLibSubmitDate() {
        return this.libSubmitDate;
    }
     
    /**
     * 设置病例库提交时间
     * 
     * @param libSubmitDate
     *          病例库提交时间
     */
    public void setLibSubmitDate(Timestamp libSubmitDate) {
        this.libSubmitDate = libSubmitDate;
    }
    
    /**
     * 获取病例库审核者
     * 
     * @return 病例库审核者
     */
    public String getLibAuditBy() {
        return this.libAuditBy;
    }
     
    /**
     * 设置病例库审核者
     * 
     * @param libAuditBy
     *          病例库审核者
     */
    public void setLibAuditBy(String libAuditBy) {
        this.libAuditBy = libAuditBy;
    }
    
    /**
     * 获取病例库审核时间
     * 
     * @return 病例库审核时间
     */
    public Timestamp getLibAuditDate() {
        return this.libAuditDate;
    }
     
    /**
     * 设置病例库审核时间
     * 
     * @param libAuditDate
     *          病例库审核时间
     */
    public void setLibAuditDate(Timestamp libAuditDate) {
        this.libAuditDate = libAuditDate;
    }
    
    /**
     * 获取来源APP
     * 
     * @return 来源APP
     */
    public String getSrcApp() {
        return this.srcApp;
    }
     
    /**
     * 设置来源APP
     * 
     * @param srcApp
     *          来源APP
     */
    public void setSrcApp(String srcApp) {
        this.srcApp = srcApp;
    }
    
    /**
     * 获取来源APP环境
     * 
     * @return 来源APP环境
     */
    public String getSrcEnv() {
        return this.srcEnv;
    }
     
    /**
     * 设置来源APP环境
     * 
     * @param srcEnv
     *          来源APP环境
     */
    public void setSrcEnv(String srcEnv) {
        this.srcEnv = srcEnv;
    }
    
    /**
     * 获取来源App版本
     * 
     * @return 来源App版本
     */
    public String getSrcAppVersion() {
        return this.srcAppVersion;
    }
     
    /**
     * 设置来源App版本
     * 
     * @param srcAppVersion
     *          来源App版本
     */
    public void setSrcAppVersion(String srcAppVersion) {
        this.srcAppVersion = srcAppVersion;
    }

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public CaseLibSlide getCaseLibSlide() {
		return caseLibSlide;
	}

	public void setCaseLibSlide(CaseLibSlide caseLibSlide) {
		this.caseLibSlide = caseLibSlide;
	}

	public String getStart_aget() {
		return start_aget;
	}

	public void setStart_aget(String start_aget) {
		this.start_aget = start_aget;
	}

	public String getEnd_aget() {
		return end_aget;
	}

	public void setEnd_aget(String end_aget) {
		this.end_aget = end_aget;
	}

	public String getCliData() {
		return cliData;
	}

	public void setCliData(String cliData) {
		this.cliData = cliData;
	}

	public String getGenDesc() {
		return genDesc;
	}

	public void setGenDesc(String genDesc) {
		this.genDesc = genDesc;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
}