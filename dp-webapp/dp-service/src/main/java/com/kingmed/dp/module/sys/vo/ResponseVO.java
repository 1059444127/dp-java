package com.kingmed.dp.module.sys.vo;

import java.io.Serializable;
import java.util.List;

import org.os.common.persistence.Page;

import com.kingmed.dp.module.sys.entity.Dict;
/**
 * 用于datatables分页参数
 * @author zl
 * @date   2016年4月13日
 * @param <T>
 */

public class ResponseVO<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String iDisplayStart;//多少条开始
	private String iDisplayLength;//每页多少条数据
	private String iTotalRecords;
	private String iTotalDisplayRecords;
	private List<T> aaData;
	private String totalPage;//总页数
	private String pageCount;//第几页
	
	public List<T> getAaData() {
		return aaData;
	}
	public void setAaData(List<T> aaData) {
		this.aaData = aaData;
	}
	
	public String getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(String iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public String getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(String iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public String getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(String iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public String getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageCount() {
		return pageCount;
	}
	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}
	/**
	 * 
	 * @param pages
	 * @return
	 */
	public ResponseVO(Page<T> pages){
		this.setAaData(pages.getList());
		this.setiDisplayStart((pages.getPageNo()-1)*pages.getPageSize()+"");
		this.setiDisplayLength(pages.getPageSize()+"");
		this.setiTotalRecords(pages.getCount()+"");
		this.setiTotalDisplayRecords(pages.getCount()+"");
		this.setTotalPage((pages.getCount()!=0?(pages.getCount()%pages.getPageSize()==0?pages.getCount()/pages.getPageSize():pages.getCount()/pages.getPageSize()+1):1)+"");
		this.setPageCount(pages.getPageNo()+"");
	}
}
