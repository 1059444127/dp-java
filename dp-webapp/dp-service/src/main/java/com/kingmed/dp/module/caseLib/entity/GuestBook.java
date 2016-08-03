package com.kingmed.dp.module.caseLib.entity;

import org.os.common.persistence.DataEntity;

import com.kingmed.dp.module.sys.entity.User;

/**
 * 读片会(T_READ_FILM)
 * 
 * @author zl
 * @version 1.0.0 2016-07-27
 */
public class GuestBook extends DataEntity<GuestBook> {

	/** 版本号 */
	private static final long serialVersionUID = -1575549894383304688L;
	 
	 private User user;
	 
	 private String content;
	 
	 private String libSlideId;

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLibSlideId() {
		return libSlideId;
	}

	public void setLibSlideId(String libSlideId) {
		this.libSlideId = libSlideId;
	}
	 
}