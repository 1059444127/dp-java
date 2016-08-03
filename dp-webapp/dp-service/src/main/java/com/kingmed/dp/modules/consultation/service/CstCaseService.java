package com.kingmed.dp.modules.consultation.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingmed.dp.client.KMCoreHttpService;
import com.kingmed.dp.module.consultation.entity.CstCase;
import com.kingmed.dp.module.consultation.entity.CstHelpCapture;
import com.kingmed.dp.module.consultation.entity.CstSlide;
import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;
import com.kingmed.dp.modules.consultation.dao.CstCaseDAO;
import com.kingmed.dp.util.JsonResult;

import cn.myapps.client.LIRService;

/**
* @author 作者 :
* @version 创建时间：2016年5月27日 上午10:19:36
* 类说明
*/
@Service
@Transactional(readOnly = true)
public class CstCaseService extends CrudService<CstCaseDAO,CstCase>{

	@Autowired
	private CstCaseDAO cstCaseDAO;
	
	@Override
	public CstCaseDAO getDao() {
		return cstCaseDAO;
	}
	
	public List<CstSlideScreenshot> getImgs(String caseId){
		return cstCaseDAO.getImgs(caseId);
	}
	
	public List<CstHelpCapture> getImgsHelp(String caseId){
		return cstCaseDAO.getImgsHelp(caseId);
	}
	
	public String getAppValue(String appKey){
		return cstCaseDAO.getAppValue(appKey);
	}
	
	public List<CstSlide> getCstSlide(String caseId){
		return cstCaseDAO.getCstSlide(caseId);
	}
	
	public String sendInfo(String caseId,String jsonText,String pathContent,CstCase model) throws Exception{
		String result1 = "";
		String result2 = "";
		String result3 = "";
		
		String url = "";
		if("KMCS".equals(model.getSrcApp())){//调用核心系统接口
			KMCoreHttpService kmService = new KMCoreHttpService();
			Object rnt;
			url = getAppValue(model.getSrcEnv()+"_"+model.getSrcApp());
			JsonResult jsonResult = new JsonResult();
			
			if(StringUtils.isNotBlank(jsonText)){
				JSONArray jsonArray = JSONArray.parseArray(jsonText);
				List<Map<String, String>> datas = (List) jsonArray;
				for(int i=0;i<datas.size();i++){
					String path = datas.get(i).get("path");
					String aPicName = datas.get(i).get("name");
					try {
						jsonResult = kmService.sendDiagnosisAndPicInfo(model.getKmbarcode(), model.getTestNo(), model.getTestCode(), model.getCliDia(), pathContent + path, aPicName, url);
						System.out.println("发送第"+i+"张截图返回状态："+result1);
					} catch (Exception e) {
						result1 = "-1";
						e.printStackTrace();
					}
				}
				
			}
			
			Object result = jsonResult.getResult();
			int status = jsonResult.getStatus();//接口状态码，0正常，1异常
			if(status == 0){
				JSONObject jsonObject = JSONObject.parseObject(result+"");//new net.sf.json.JSONObject.fromObject(result);
				rnt = jsonObject.get("errorCode");//rnt=1表示成功，否则失败
				if(!"1".equals(rnt)){
					throw new Exception("发送失败!");
				}
			}else{
				throw new Exception("调用核心接口系统异常!");
			}
			return rnt+"";
		}else{
			LIRService lir = new LIRService();
			url = getAppValue("p_lir_url");
			if(StringUtils.isNotBlank(jsonText)){
				JSONArray jsonArray = JSONArray.parseArray(jsonText);
				List<Map<String, String>> datas = (List) jsonArray;
				for(int i=0;i<datas.size();i++){
					String path = datas.get(i).get("path");
					String aPicName = datas.get(i).get("name");
					try {
						result1 = lir.sendPathPictureinfo(model.getCompany().getId(), model.getKmbarcode(), caseId, pathContent + path, aPicName, "图片描述", url);
						System.out.println("发送第"+i+"张截图返回状态："+result1);
					} catch (Exception e) {
						result1 = "-1";
						e.printStackTrace();
					}
				}
				
			}
			if(StringUtils.isNotBlank(model.getCstDia())){
				result2 = lir.sendPathDiagnosisinfo(model.getCompany().getId(), model.getKmbarcode(), caseId, model.getCstDia(), url);
				System.out.println("发送诊断意见返回状态："+result2);
			}
			List<Map<String, String>> datas = (List)JSONArray.parseArray(model.getSendPath());
			for(int i = 0;i<datas.size();i++){
				result3 = lir.SendPicReport(model.getCompany().getId(), model.getKmbarcode(), caseId,model.getTestCode(),"report"+i,"报告单","A4", datas.get(i).get("path"),url);//调用LIR接口报告单接口
			}
			System.out.println("发送报告单返回状态："+result3);
			if("0".equals(result1)&&"0".equals(result2)&&"0".equals(result3)){
				return "0";
			}
			
			if("0".equals(result1) && "0".equals(result2) && "0".equals(result3)){	
				return "-1";
			}
		}
		return null;
	}

}
