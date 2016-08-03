package com.kingmed.dp.module.caseLib.entity;

import java.util.Date;

import org.os.common.persistence.DataEntity;
import org.os.common.utils.Reflections;
import org.os.common.utils.StringUtils;

/**
 * 读片会(T_READ_FILM)
 * 
 * @author zl
 * @version 1.0.0 2016-07-27
 */
public class ReadFilm extends DataEntity<ReadFilm> {
    
	/** 版本号 */
	private static final long serialVersionUID = -4932693646820570837L;

	 private String name;
	  
	 private String address;
	  
	 private Date dates;
	  
	 private String orgName;
	 
	 protected ReadFilm parent;	// 父级编号
	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public ReadFilm getParent() {
		return parent;
	}

	public void setParent(ReadFilm parent) {
		this.parent = parent;
	}
	
	public String getParentId() {
		String id = null;
		if (parent != null){
			id = (String)Reflections.getFieldValue(parent, "id");
		}
		return StringUtils.isNotBlank(id) ? id : "0";
	}
    
}