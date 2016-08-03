package com.kingmed.dp.modules.sys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.os.common.persistence.BaseEntity;
import org.os.common.persistence.Page;
import org.os.common.service.BaseService;
import org.os.common.service.CrudService;
import org.os.common.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.module.sys.entity.JobIsParams;
import com.kingmed.dp.module.sys.entity.User;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.modules.sys.dao.JobIsParamsDAO;

@Service
@Transactional(readOnly = false)
public class JobIsParamsService extends CrudService<JobIsParamsDAO, JobIsParams>{

	@Autowired
	private JobIsParamsDAO jobIsParamsDAO;
	
	@Override
	public JobIsParamsDAO getDao() {
		return jobIsParamsDAO;
	}
	
	/**
	 * 获得需要激活的jobParams
	 * @return
	 */
	public List<Map<String,List<Map<String,Object>>>> findAllList() {
		List<Map<String,Object>> lists = jobIsParamsDAO.findAllList();
		List<Map<String,List<Map<String,Object>>>> datas = new ArrayList<Map<String,List<Map<String,Object>>>>();
		Map<String,List<Map<String,Object>>> items = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(lists!=null&&lists.size()>0){
			list.add(lists.get(0));
			for(int i=1;i<lists.size();i++){
				if(lists.get(i).get("type").equals(list.get(0).get("type"))){
					list.add(lists.get(i));
				}else{
					items = new HashMap<String,List<Map<String,Object>>>();
					items.put("data", list);
					datas.add(items);
					/*开始下一组数据*/
					items = new HashMap<String,List<Map<String,Object>>>();
					list = new ArrayList<Map<String,Object>>();
					list.add(lists.get(i));
				}
			}
			items = new HashMap<String,List<Map<String,Object>>>();
			items.put("data", list);
			datas.add(items);
		}
		return datas;
	}
	
	/**
	 * 读取配置文件内的参数列表
	 * @return
	 * @throws IOException
	 */
	public List<JobIsParams> getPropertiesList() throws IOException {
		List<JobIsParams> list = new ArrayList<>();
		
//		Properties prop =  new  Properties();    
//        InputStream in = Object.class.getResourceAsStream("jobIsParams.properties");    
//        prop.load(in); 
        
        PropertiesLoader prop = new PropertiesLoader("jobIsParams.properties");
        
        String paramsName = prop.getProperty("paramsName").trim();
        String sBeginIndex = prop.getProperty("beginIndex").trim();
        String sParamsLength = prop.getProperty("paramsLength").trim();
        int beginIndex = Integer.parseInt(sBeginIndex);
        int paramsLength = Integer.parseInt(sParamsLength);
        
        for(int i=beginIndex; i<paramsLength; i++) {
        	JobIsParams jobIsParams = new JobIsParams();
        	String param = prop.getProperty(paramsName+""+i).trim();
        	String[] params = param.split(",");
        	jobIsParams.setUserName(params[0]);
        	jobIsParams.setUserPassword(params[1]);
        	jobIsParams.setUrl(params[2]);
        	list.add(jobIsParams);
        }
        
		return list;
	}
	
	/**
	 * 获得jobParams列表
	 * @param page
	 * @param jobParams
	 * @return
	 */
	public Page<JobIsParams> findAllList(Page<JobIsParams> page, JobIsParams jobIsParams) {
		Page<JobIsParams> list = null;
		User user = UserUtils.getUser();
		if (user.isAdmin()){
			list = super.findPage(page, jobIsParams);
		}else{
			jobIsParams.getSqlMap().put("dsf", BaseService.dataScopeFilter(user, "o", "u"));
			list = super.findPage(page, jobIsParams);
		}
		return list;
	}
	
	/**
	 * 根据id获得jobparames
	 * @param id
	 * @return
	 */
	public Map<String, Object> findJobIsParams(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isEmpty(id)) {
			map.put("false", true);
		} else {
			JobIsParams job = super.get(id);
			if(job != null) {
				map.put("success", true);
				map.put("job", job);
			} else {
				map.put("false", true);
			}
		}
		return map;
	}
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public Map<String, Object> saveJob(JobIsParams entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(StringUtils.isNotBlank(entity.getId())) {
				entity = copyJobIsParams(entity);
			}
			
			/*需要添加重复添加验证*/
			
			save(entity);
			map.put("success", true);
			map.put("message", "保存成功。");
			//UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
		} catch(Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "保存失败！");
		}
		return map;
	}
	
	/**
	 * 更新entity保存
	 * @param entity
	 * @return
	 */
	private JobIsParams copyJobIsParams(JobIsParams entity) {
		JobIsParams jobIsParams = get(entity);
		jobIsParams.setUrl(entity.getUrl());
		jobIsParams.setUserName(entity.getUserName());
		jobIsParams.setUserPassword(entity.getUserPassword());
		return jobIsParams;
	}

	/**
	 * 删除 
	 * @param entity
	 * @return
	 */
	public Map<String, Object> delJobIsParams(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			JobIsParams entity = get(id);
			entity.setDelFlag(BaseEntity.DEL_FLAG_DELETE);
			delete(entity);
			map.put("success", true);
			map.put("message", "删除成功。");
		} catch(Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("message", "删除失败！");
		}
		return map;
	}

}
