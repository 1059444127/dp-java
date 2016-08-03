package com.kingmed.dp.module.consultation.entity;

import java.util.Date;

import org.os.common.persistence.DataEntity;

import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.sys.entity.Area;
import com.kingmed.dp.module.sys.entity.Office;
import com.kingmed.dp.module.sys.entity.User;

public class CstCase extends DataEntity<CstCase>{
	
	private static final long serialVersionUID = 1L;
	
	 /*病例*/
	 private CaseLib caseLib;
	 /*实验号*/
	 private String testNo;
	 /*子公司*/
	 private Office company;
	 /*实验室*/
	 private String lab;
	 /*归属用户*/
	 private User user;
	 /*归属机构*/
	 private Office office;
	 /*归属区域*/
	 private Area area;
	 /*标本条码*/
	 private String kmbarcode;
	 /*标本id*/
	 private String requestId;
	 /*姓名*/
	 private String name;
	 /*性别*/
	 private char gender;
	 /*年龄*/
	 private String age;
	 /*年龄单位*/
	 private int ageUnit;
	 /*临床诊断*/
	 private String cliDia;
	 /*临床主述*/
	 private String cliMas;
	 /*家族史*/
	 private String famHis;
	 /*其它病史*/
	 private String othHis;
	 /*其它辅助检查*/
	 private String othSup;
	 /*手术部位*/
	 private String opeOrg;
	 /*活检巨检部位*/
	 private String bioOrg;
	 /*手术所见*/
	 private String opeFind;
	 /*初诊意见*/
	 private String priPatDia;
	 /*会诊者*/
	 private String cstPatExp;
	 /*会诊状态*/
	 private String state;
	 /*检查项目*/
	 private String testCode;
	 /*诊断意见*/
	 private String cstDia;
	 /*来源APP*/
	 private String srcApp;
	 /*来源APP环境*/
	 private String srcEnv;
	 /*来源App版本*/
	 private String srcAppVersion;
	 /*切片数量*/
	 private String slideNum;
	 /*本地报告单路径*/
	 private String sendPath;
	 /*申请时间*/
	 private Date applyDate;
	
	public CaseLib getCaseLib() {
		return caseLib;
	}
	public void setCaseLib(CaseLib caseLib) {
		this.caseLib = caseLib;
	}
	public String getTestNo() {
		return testNo;
	}
	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getKmbarcode() {
		return kmbarcode;
	}
	public void setKmbarcode(String kmbarcode) {
		this.kmbarcode = kmbarcode;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	public int getAgeUnit() {
		return ageUnit;
	}
	public void setAgeUnit(int ageUnit) {
		this.ageUnit = ageUnit;
	}
	public String getCliDia() {
		return cliDia;
	}
	public void setCliDia(String cliDia) {
		this.cliDia = cliDia;
	}
	public String getCliMas() {
		return cliMas;
	}
	public void setCliMas(String cliMas) {
		this.cliMas = cliMas;
	}
	public String getFamHis() {
		return famHis;
	}
	public void setFamHis(String famHis) {
		this.famHis = famHis;
	}
	public String getOthHis() {
		return othHis;
	}
	public void setOthHis(String othHis) {
		this.othHis = othHis;
	}
	public String getOthSup() {
		return othSup;
	}
	public void setOthSup(String othSup) {
		this.othSup = othSup;
	}
	public String getOpeOrg() {
		return opeOrg;
	}
	public void setOpeOrg(String opeOrg) {
		this.opeOrg = opeOrg;
	}
	public String getBioOrg() {
		return bioOrg;
	}
	public void setBioOrg(String bioOrg) {
		this.bioOrg = bioOrg;
	}
	public String getOpeFind() {
		return opeFind;
	}
	public void setOpeFind(String opeFind) {
		this.opeFind = opeFind;
	}
	public String getPriPatDia() {
		return priPatDia;
	}
	public void setPriPatDia(String priPatDia) {
		this.priPatDia = priPatDia;
	}
	public String getCstPatExp() {
		return cstPatExp;
	}
	public void setCstPatExp(String cstPatExp) {
		this.cstPatExp = cstPatExp;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getCstDia() {
		return cstDia;
	}
	public void setCstDia(String cstDia) {
		this.cstDia = cstDia;
	}
	public String getSrcApp() {
		return srcApp;
	}
	public void setSrcApp(String srcApp) {
		this.srcApp = srcApp;
	}
	public String getSrcEnv() {
		return srcEnv;
	}
	public void setSrcEnv(String srcEnv) {
		this.srcEnv = srcEnv;
	}
	public String getSrcAppVersion() {
		return srcAppVersion;
	}
	public void setSrcAppVersion(String srcAppVersion) {
		this.srcAppVersion = srcAppVersion;
	}
	public Office getCompany() {
		return company;
	}
	public void setCompany(Office company) {
		this.company = company;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public String getSlideNum() {
		return slideNum;
	}
	public void setSlideNum(String slideNum) {
		this.slideNum = slideNum;
	}
	public String getSendPath() {
		return sendPath;
	}
	public void setSendPath(String sendPath) {
		this.sendPath = sendPath;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
}