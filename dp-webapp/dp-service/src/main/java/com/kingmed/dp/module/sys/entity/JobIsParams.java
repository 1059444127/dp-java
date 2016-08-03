package com.kingmed.dp.module.sys.entity;

import java.util.List;

import org.os.common.persistence.DataEntity;

public class JobIsParams extends DataEntity<JobIsParams>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String userPassword;

	private String url;
	
	private Integer order;
	
	private String type;
	
	private List<JobIsParams> childs;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public List<JobIsParams> getChilds() {
		return childs;
	}
	public void setChilds(List<JobIsParams> childs) {
		this.childs = childs;
	}
	public String getCreateByName() {
		if(createBy != null) {
			return createBy.getName();
		}
		return null;
	}
	
	
	public String toString(){
		return "userName="+userName+" | userPassword="+userPassword+" | url="+url;
	}
	
}
