package com.kingmed.dp.modules.consultation.service;

import org.os.common.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.kingmed.dp.client.KMCoreHttpService;
import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.consultation.entity.CstAdvice;
import com.kingmed.dp.module.consultation.entity.CstCase;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.modules.consultation.dao.CstAdviceDAO;
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
public class CstAdviceService extends CrudService<CstAdviceDAO,CstAdvice>{

	@Autowired
	private CstAdviceDAO cstAdviceDAO;
	@Autowired
	private CstCaseDAO cstCaseDAO;
	
	@Override
	public CstAdviceDAO getDao() {
		return cstAdviceDAO;
	}
	
	public void insert(CstAdvice cstAdvice) throws Exception{
		String lirUrl = "";
		CstCase cc = new CstCase();
		CaseLib cl = new CaseLib();
		cl.setCaseId(cstAdvice.getCaseId());
		cc.setCaseLib(cl);
		CstCase cstCase = cstCaseDAO.get(cc);
		
		if("KMCS".equals(cstCase.getSrcApp())){//核心环境，判断信息来源src_app
			lirUrl = cstCaseDAO.getAppValue(cstCase.getSrcEnv()+"_"+cstCase.getSrcApp());
			JsonResult jsonResult = new JsonResult();
			KMCoreHttpService kmServcie = new KMCoreHttpService();
			jsonResult = kmServcie.sendDiagnosisAndPicInfo(cstCase.getKmbarcode(), cstCase.getCaseLib().getCaseId(), cstCase.getTestCode(), cstAdvice.getContent(), UserUtils.getUser().getId(), UserUtils.getUser().getName(), lirUrl);
			Object result = jsonResult.getResult();
			int status = jsonResult.getStatus();//接口状态码，0正常，1异常
			Object rnt="";
			if(status == 0){
				JSONObject jsonObject = JSONObject.parseObject(result+"");//new net.sf.json.JSONObject.fromObject(result);
				rnt = jsonObject.get("errorCode");//rnt=1表示成功，否则失败
				if((int)rnt !=1){
					throw new Exception("发送失败!");
				}
			}else{
				throw new Exception("调用核心接口系统异常!");
			}
		}else{
			if("P".equals(cstCase.getSrcEnv())){//正式环境
				lirUrl = cstCaseDAO.getAppValue("p_lir_url");
			}else{//测试环境
				lirUrl = cstCaseDAO.getAppValue("t_lir_url");
			}
			String rnt = "";
			LIRService lir = new LIRService();
			/*parent：需要确认，以前是有反馈主表和反馈子表   "select * from tlk_leave_info_new where id ='"+getCurrentDocument().getId()+"'" 查出parent*/
			rnt = lir.sendExpertMSGinfo(cstCase.getCompany().getId(), "parent", cstCase.getKmbarcode(), cstCase.getCaseLib().getCaseId(), cstAdvice.getContent(), UserUtils.getUser().getName(), lirUrl);
			if(!"0".equals(rnt)){
				throw new Exception("发送失败!");
			}
		}
		//保存本地数据
		save(cstAdvice);
	}

}
