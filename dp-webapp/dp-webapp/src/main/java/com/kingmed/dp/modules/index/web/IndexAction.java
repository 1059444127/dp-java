package com.kingmed.dp.modules.index.web;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kingmed.dp.module.consultation.utils.PortletsConstant;

@Controller
@RequestMapping("/plat/common/")
public class IndexAction {

	private static final String templatePath="portlets-config.xml";
	
	private List datas;
	
	private String proId;
	
	private String code;
	
	@RequestMapping("test")
	public String test(){
		return "modules/web/item";
	}
	
	public String  getPath() throws UnsupportedEncodingException{
		URL path = Thread.currentThread().getContextClassLoader().getResource("");
		String cpath=path.getPath();
		cpath=URLDecoder.decode(cpath, "UTF-8");
		//System.out.println("-------------配置文件地址为："+classPath+"-------------------");
		return cpath;
	}
	
	@RequestMapping("fullModuleMap")
	private void fullModuleMap(Map<String, Map<String, String>> resultMap,
			List moduleList) {
		if(moduleList!=null&&moduleList.size()>0){
			int size =moduleList.size();
			for(int i=0;i<size;i++){
				DefaultElement defaultElement=(DefaultElement)moduleList.get(i);
				//鑾峰彇ID鐨勫睘鎬�
				DefaultAttribute defaultAttribute= (DefaultAttribute)defaultElement.attribute("id");
				String id=defaultAttribute.getValue();
				Iterator elt = defaultElement.elementIterator();
				Map<String,String> proMap =new HashMap<String, String>();
				while(elt.hasNext()){
					//鑾峰彇鍐呭
					DefaultElement defaultelt = (DefaultElement)elt.next();
					String eleName = defaultelt.getName();
					String eleValue = defaultelt.getStringValue();
					proMap.put(eleName, eleValue);
				}
				resultMap.put(id, proMap);
			}
		}
	}
	
	@RequestMapping("fullRowMap")
	private void fullRowMap(Map<String, String> rowMap, DefaultElement rowei) {
		 Attribute ref = rowei.attribute(PortletsConstant.ROW_ATTR_REF);
		 Attribute movable = rowei.attribute(PortletsConstant.ROW_ATTR_MOVABLE);
		 Attribute closed =rowei.attribute(PortletsConstant.ROW_ATTR_CLOSED);
		 Attribute show = rowei.attribute(PortletsConstant.ROW_ATTR_SHOW);
		 Attribute id = rowei.attribute(PortletsConstant.ROW_ATTR_ID);
		 rowMap.put(PortletsConstant.ROW_ATTR_REF,ref.getValue() );
		 rowMap.put(PortletsConstant.ROW_ATTR_ID,id.getValue() );
		 rowMap.put(PortletsConstant.ROW_ATTR_MOVABLE, movable==null?"true":movable.getValue());
		 rowMap.put(PortletsConstant.ROW_ATTR_CLOSED, closed==null?"true":closed.getValue());
		 rowMap.put(PortletsConstant.ROW_ATTR_SHOW, show==null?"true":show.getValue());
	}
	
    @RequestMapping("index")
    public String index(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	Map<String,Map<String,String>> resultMap = new HashMap<String, Map<String,String>>();//key是模块的ID，value是模块的详细信息
		List<Map<String,String>> projectAttrList = new ArrayList<Map<String,String>>();//方案的信息，id，name 等属性
		List<Map<String,String>> columnList=new ArrayList<Map<String,String>>();
		Map<String,List<Map<String,String>>> rowMapList = new HashMap<String, List<Map<String,String>>>();//key是column的id属性 value是row的内容list
		//---------解析模板--------------------
		String path=getPath()+templatePath;
		File templateFile = new File(path);
		if(templateFile.exists() == false){
			response.setContentType("text/html; charset=UTF-8");
			String script = "<script>alert('模板源文件不存在')</script>";
			response.getWriter().print(script);
			return null;
		}
		String encode = "utf-8";
		String text=FileUtils.readFileToString(templateFile,encode);
		Document document = DocumentHelper.parseText(text); 
		Element root = document.getRootElement();
		Element modules = root.element(PortletsConstant.ROOT_MODULES);
		List moduleList = modules.elements(PortletsConstant.MODULE);
		//----------------------获得模块信息的MAP key是模块Map<id,map<属性名称，属性值>> -------------------------------------
		fullModuleMap(resultMap, moduleList);
//		IUser user = getLoginUser();
//		List<IRole> roleList = user.getRoles();

		Element projects = root.element(PortletsConstant.ROOT_PORTLETS);
		List projectList = projects.elements(PortletsConstant.PROJECT);
		if(projectList!=null&&projectList.size()>0){
			Map<String,Map<String,String>> rmap  = new HashMap<String, Map<String,String>>();
			int len =projectList.size();
 			for(int i=0;i<len;i++){
				Map<String,String> projectMap=new HashMap<String, String>();
				// ----------获得project（方案）的信息
				DefaultElement project =(DefaultElement)projectList.get(i);
				Iterator alt = project.attributeIterator();
				while(alt.hasNext()){
					DefaultAttribute defaultAttribute= (DefaultAttribute)alt.next();
					projectMap.put(defaultAttribute.getName(), defaultAttribute.getValue());
				}
				projectAttrList.add(projectMap);
				if(proId==null&&i==0){
					proId=projectMap.get("id");
				}
				if(proId!=null&&!proId.equals(projectMap.get("id"))){
					continue;
				}
				//---------------查询用户的模块列表-----------
//				TRmsUserPortlets trmsUserPortlets = new TRmsUserPortlets();
//				trmsUserPortlets.setProid(proId);
//				trmsUserPortlets.setUserid(user.getUserId());
				List<Map<String,Object>> trmsUserPortletsList =null;//trmsUserPortletsService.findList(trmsUserPortlets);
				boolean flag=false ;
				if(trmsUserPortletsList!=null&&trmsUserPortletsList.size()>0){
					flag=true;
					for(Map<String,Object> trmsUserPortletsMap:trmsUserPortletsList){
						Map<String,String> rowMap = new HashMap<String, String>();
						String colId =String.valueOf(trmsUserPortletsMap.get("COL_Y"));
						String ref =String.valueOf(trmsUserPortletsMap.get("MODULEID"));
						String sfyx =String.valueOf(trmsUserPortletsMap.get("SFYX"));
						String colcount =String.valueOf(trmsUserPortletsMap.get("COLCOUNT")==null?"1":trmsUserPortletsMap.get("COLCOUNT"));
						String rowcount =String.valueOf(trmsUserPortletsMap.get("ROWCOUNT")==null?"1":trmsUserPortletsMap.get("ROWCOUNT"));
						rowMap.put(PortletsConstant.ROW_ATTR_REF,ref);
						rowMap.put("sfyx",sfyx);
						rowMap.put("colcount",colcount);
						rowMap.put("rowcount",rowcount);
						List<Map<String, String>> rlist = rowMapList.get(colId);
						if(rlist == null){
							rlist= new ArrayList<Map<String,String>>();
							rlist.add(rowMap);
							rowMapList.put(colId, rlist);
						}else {
							rlist.add(rowMap);
						}
					}
				}
//				TRmsRolePortlets  trmsRolePortlets= new TRmsRolePortlets();
//				trmsRolePortlets.setRoleList(roleList);
//				trmsRolePortlets.setProid(proId);
				//该用户所有的有权限的模块
				List list = null;//service.queryModuleId(trmsRolePortlets);
				//-----------获得列的信息------------
				Iterator ei = project.elementIterator(PortletsConstant.PROJECT_COLUMN);
				
				while(ei.hasNext()){
					List<Map<String,String>> rowList = new ArrayList<Map<String,String>>();
					DefaultElement defaultElement= (DefaultElement)ei.next();
					Iterator colAttrIte = defaultElement.attributeIterator();
					Map<String,String> colAttrMap  =  new HashMap<String, String>();
					while(colAttrIte.hasNext()){
						DefaultAttribute colDefaultAttribute= (DefaultAttribute)colAttrIte.next();
						colAttrMap.put(colDefaultAttribute.getName(), colDefaultAttribute.getValue());
					}
					columnList.add(colAttrMap);
					String columnId =  colAttrMap.get("id");
					//---------获得行的信息------------
					 Iterator roweiItl = defaultElement.elementIterator(PortletsConstant.COLUMN_ROW);
					 while(roweiItl.hasNext()){
						 Map<String,String> rowMap = new HashMap<String, String>();
						 DefaultElement  rowei=(DefaultElement) roweiItl.next();
						 fullRowMap(rowMap, rowei);
						 //如果角色授权里面包含有
//						 if(list.contains(rowMap.get(PortletsConstant.ROW_ATTR_REF))){
							 if(flag){
								 rmap.put(rowMap.get(PortletsConstant.ROW_ATTR_REF), rowMap);
							 }else{
								 rowMap.put("sfyx", "1");
								 rowList.add(rowMap);
							 }
//						 }
					 }
					 if(!flag){
						 rowMapList.put(columnId, rowList);
					 }
				}
				if(flag){
					Set<Entry<String, List<Map<String, String>>>> eset = rowMapList.entrySet();
					for( Entry<String, List<Map<String, String>>> entry:eset){
						List<Map<String, String>> setList = entry.getValue();
						for(Map<String, String> m:setList){
							String key =m.get(PortletsConstant.ROW_ATTR_REF);
							Map<String, String> tm = rmap.get(key);
							list.remove(key);
							if(tm!=null){
								m.putAll(tm);
							}
						}
					}
				}
				break;
			}
		}
		
		/* 单位首页下拉通知框 */
//		if(getLoginUser().getRoleType(1)==2||getLoginUser().getRoleType(1)==4){
//			String orgid = getLoginUser().getDepartId();
//			TAqjcNotice tAqjcNotice = new TAqjcNotice();
//			tAqjcNotice.setOrgid(orgid/*"5QNMLO5GGT-01-5QNMLWOJA4"*/);
//			List list = indexService.queryZc_list(tAqjcNotice);
//			
//			request.setAttribute("list", list);
//		}
		/* end*/
		
		request.setAttribute("resultMap", resultMap);
		request.setAttribute("columnList", columnList);
		request.setAttribute("rowMapList", rowMapList);
		return "modules/web/web";
    }
}

