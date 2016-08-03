package com.kingmed.dp.module.caseLib.utils;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.os.common.utils.SpringContextHolder;

import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.caseLib.entity.PatType;
import com.kingmed.dp.module.caseLib.entity.ReadFilm;
import com.kingmed.dp.modules.caseLib.dao.CaseLibDAO;
import com.kingmed.dp.modules.caseLib.dao.PatTypeDAO;
import com.kingmed.dp.modules.caseLib.dao.ReadFilmDAO;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */

public class CaseUtils {

	private static PatTypeDAO patTypeDAO = SpringContextHolder.getBean(PatTypeDAO.class);
	private static CaseLibDAO caseLibDAO = SpringContextHolder.getBean(CaseLibDAO.class);
	private static ReadFilmDAO readFilmDAO = SpringContextHolder.getBean(ReadFilmDAO.class);


	public static final String CACHE_PAT_TYPE_LIST = "patTypeList";
	
	public static final String CACHE_CASE_LIB_LIST = "caseLibList";
	
	public static final String CACHE_READ_FILM_LIST = "readFilmList";
	
	public static final String CACHE_List_OR_VIEW = "listOrOverView";
	
	
	/**
	 * 获取当前用户授权的病理库类型数据
	 * @return
	 */
	public static List<PatType> getPatTypeList(){
		@SuppressWarnings("unchecked")
		List<PatType> patTypeList = (List<PatType>)getCache(CACHE_PAT_TYPE_LIST);
		if (patTypeList == null){
			patTypeList = patTypeDAO.findAllList(new PatType());
			putCache(CACHE_PAT_TYPE_LIST, patTypeList);
		}
		return patTypeList;
	}
	/**
	 * 获取当前用户授权的病理库数据
	 * @return
	 */
	public static List<CaseLib> getCaseLibList(){
		@SuppressWarnings("unchecked")
		List<CaseLib> caseLibList = (List<CaseLib>)getCache(CACHE_CASE_LIB_LIST);
		if (caseLibList == null){
			caseLibList = caseLibDAO.findAllList(new CaseLib());
			putCache(CACHE_CASE_LIB_LIST, caseLibList);
		}
		return caseLibList;
	}
	/**
	 * 获取页面展示类型（列表或缩略图）
	 * @return
	 */
	public static String getType(){
		String type =  (String) getCache(CACHE_List_OR_VIEW);
		if(type == null||"".equals(type)){
			type="1";
			putCache(CACHE_List_OR_VIEW,type);
		}
		return type;
	}
	/**
	 * 设置页面展示类型
	 * @param type
	 */
	public static void setType(String type){
		putCache(CACHE_List_OR_VIEW,type);
	}
	
	public static List<ReadFilm> getReadFilmList(){
		@SuppressWarnings("unchecked")
		List<ReadFilm> readFilmList = (List<ReadFilm>)getCache(CACHE_READ_FILM_LIST);
		if (readFilmList == null){
			readFilmList = readFilmDAO.findTreeList(new ReadFilm());
			putCache(CACHE_READ_FILM_LIST, readFilmList);
		}
		return readFilmList;
	}
	
	
	
	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	
	// ============== User Cache ==============
	
	public static Object getCache(String key) {
		return getCache(key, null);
	}
	
	public static Object getCache(String key, Object defaultValue) {
		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		getSession().removeAttribute(key);
	}
}
