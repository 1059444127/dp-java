/**
 * DocumentWSSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.kingmed.dp.soap.service.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.kingmed.dp.gateway.client.DocService;
import com.kingmed.dp.gateway.util.HttpRequest;
import com.kingmed.dp.gateway.util.ModelUtil;

import cn.myapps.webservice.client.DocumentService;
import cn.myapps.webservice.client.DocumentServiceProxy;

public class DocumentWSSoapBindingImpl implements com.kingmed.dp.soap.service.client.DocumentWS{
	
	private static Logger logger = Logger.getLogger(DocumentWSSoapBindingImpl.class);
	private DocumentService documentService = new DocumentServiceProxy();
	
    public int updateDocumentByDomainUser(java.lang.String documentId, java.util.HashMap parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault {
    	return documentService.updateDocumentByDomainUser(documentId, parameters, domainUserId, applicationId);
    }

    public int createDocumentByDomainUser(java.lang.String formName, java.util.HashMap parameters, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault {
    	logger.debug("createDocumentByDomainUser参数值：formName="+formName+",parameters="+parameters+",domainUserId="+domainUserId+",applicationId="+applicationId);
		int rnt = -1;
		try {
			//调用旧系统的api  写入数据
			if("未诊断列表".equals(formName)){
				rnt = documentService.createDocumentByDomainUser("未诊断列表", (HashMap<String,Object>)parameters, domainUserId, applicationId);
			}else if("leave_info_new".equals(formName)){
				rnt = documentService.createDocumentByDomainUser("leave_info_new", (HashMap<String,Object>)parameters, domainUserId, applicationId);
			}else if("number_section".equals(formName)){
				rnt = documentService.createDocumentByDomainUser("number_section", (HashMap<String,Object>)parameters, domainUserId, applicationId);
			}
			if(rnt == 0){
				logger.info("旧系统创建会诊请求与反馈信息执行成功！"+formName);
			}else{
				rnt = -1;
				logger.info("旧系统创建会诊请求与反馈信息执行失败！"+formName+"+rnt:"+rnt);
			}
			
			if("1".equals(ModelUtil.IS_TO_SEND)){
				//调用dp-webapp的api 写入数据
				String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",ModelUtil.getDPLoginData());
				
				String dp="";
				if("未诊断列表".equals(formName)){
					Map<String,Object> caseJson = ModelUtil.getCstCase(parameters);
					dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstCase/update", caseJson,cookie);
					if("".equals(dp)){
			    		logger.info(DocService.class+"登陆新系统失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
			    	}
					logger.debug("新系统病例信息创建"+dp+"条成功！");
				}else if("leave_info_new".equals(formName)){
//					Map<String,Object> leaveJson = ModelUtil.getCstAdvice(leaveParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstAdvice/save",leaveJson,cookie);
		    		logger.debug("反馈信息创建"+dp+"条成功！");
				}else if("number_section".equals(formName)){
//					Map<String,Object> sectionJson = ModelUtil.getslide(sectionParams,caseParams.get("ci_test_id")+"");
//		    		dp = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/updateSlide",sectionJson,cookie);
		    		logger.debug("切片信息创建"+dp+"条成功！");
				}
	    		if("1".equals(dp)){
					rnt = 0;
					logger.info("新系统创建会诊请求与反馈信息执行成功！");
				}else{
					rnt = -1;
					logger.info("新系统创建会诊请求与反馈信息执行失败！"+formName+"dp:"+dp);
				}
			}
		} catch (Exception e) {
			rnt = -1;
			logger.error("创建会诊请求与反馈信息，异常日志："+e);
			e.printStackTrace();
		}
		return rnt;
    }

    public int createDocumentByDomainUserV1(java.util.HashMap caseParams, java.util.HashMap leaveParams, java.util.HashMap sectionParams, java.lang.String domainUserId, java.lang.String applicationId) throws java.rmi.RemoteException, com.kingmed.dp.soap.service.fault.DocumentWSFault {
    	int rnt = -1;
		//调用obpm的api  写入数据
		int rnt1 = documentService.createDocumentByDomainUser("未诊断列表", caseParams, domainUserId, applicationId);
        int rnt2 = documentService.createDocumentByDomainUser("leave_info_new", leaveParams, domainUserId, applicationId);
        int rnt3 = documentService.createDocumentByDomainUser("number_section", sectionParams, domainUserId, applicationId);
        if(rnt1 == 0 && rnt1 == 0 && rnt3 == 0){
			rnt = 0;
			logger.info("旧系统创建会诊请求与反馈信息执行成功！");
		}else{
			rnt = -1;
			logger.info(DocumentWSSoapBindingImpl.class+"旧系统创建会诊请求与反馈信息执行失败！病例信息rnt1:"+rnt1 +",反馈信息rnt2:" +rnt2 + ",切片信息rnt3:" +rnt3);
		}
        if("1".equals(ModelUtil.IS_TO_SEND)){
	        //调用dp-webapp的api 写入数据
			Map<String, Object> jsons = ModelUtil.getDPLoginData();
			String cookie = HttpRequest.postMethodLogin(ModelUtil.dp_url+"/dp/login",jsons);
			String dp1 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstCase/update", ModelUtil.getCstCase(caseParams),cookie);
			if("".equals(dp1)){
	    		logger.info(DocumentWSSoapBindingImpl.class+"登陆失败，请检查用户名密码是否正确！username:"+ModelUtil.DP_USERNAME+",password:"+ModelUtil.DP_PASSWORD);
	    	}
			String dp2 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/cstAdvice/save",ModelUtil.getCstAdvice(leaveParams,caseParams.get("ci_test_id").toString()),cookie);
			String dp3 = HttpRequest.postMethod(ModelUtil.dp_url+"/cons/updateAll/updateSlide", ModelUtil.getslide(sectionParams,caseParams.get("ci_test_id").toString()),cookie);
			if("1".equals(dp1) && "1".equals(dp2) && "1".equals(dp3)){
				rnt = 0;
				logger.info("新系统创建会诊请求与反馈信息执行成功！");
			}else{
				rnt = -1;
				logger.info(DocumentWSSoapBindingImpl.class+"新系统创建会诊请求与反馈信息执行失败！病例信息dp1:"+dp1 +",反馈信息dp2:" +dp2 + ",切片信息dp3:" +dp3);
			}
        }
		return rnt;
    }

}
