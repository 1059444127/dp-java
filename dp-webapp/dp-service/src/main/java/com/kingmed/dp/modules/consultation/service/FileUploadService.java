package com.kingmed.dp.modules.consultation.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingmed.dp.module.caseLib.entity.CaseLib;
import com.kingmed.dp.module.consultation.entity.CstCase;
import com.kingmed.dp.module.consultation.entity.CstSlideScreenshot;
import com.kingmed.dp.module.consultation.utils.Constants;
import com.kingmed.dp.module.sys.entity.User;
import com.kingmed.dp.module.sys.utils.UserUtils;

/***
 * 
 * @author 龙庭伟
 * date:15-08-10
 */
@Service
public class FileUploadService {
	
	private static Logger log = Logger.getLogger(FileUploadService.class);
	
	@Autowired
	private CstSlideScreenshotService screenshotService;
	
	@Autowired
	private CstCaseService cstCaseService;
    
	 
	 //add by zhanglei at 2016-6-03
	
	/***
	 *  新增截图（在截图表中添加一条截图数据）
	 * @param TestId 病理号
	 * @param jtJson 截图的name跟path组成的json字符文本
	 * @param imgName 文件名
	 * @throws Exception   
	 */
	public void updateFileByTestId(String testId,String imgName,String jtJson) 
	{
		try {	
			CstSlideScreenshot params = new CstSlideScreenshot();
			params.setCaseId(testId);
			params.setName(imgName);
			params.setImage(jtJson);
			screenshotService.save(params);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("update error");
		}
	}
	
	/***
	 * 通过截图文件名和病理号来删除截图
	 * @param testId 病理号
	 * @param jtPath 截图的名称
	 * @return 0是更新失败； 1是更新成功；2不是病理专家；3诊断意见已提交
	 * @throws Exception
	 */
	public int deleteDocByJtName(String testId,String jtPath,String id) 
	{
		//String fileName=null;
		int n=0;
		//ReportService reportService = ReportService.getInstance();
		try {
			CstCase params = new CstCase();
			CaseLib model = new CaseLib();
			model.setCaseId(testId);
			params.setCaseLib(model);
			CstCase doc=cstCaseService.get(params);
			User user=UserUtils.getUser();
			//获取提交诊断意见状态modify by ltw at 2015-12-01
			if("1".equals(doc.getState()))
			{
				log.warn("诊断意见已提交");
				n=Constants.DIAGNOSESUBMITED;
				return n;
			}
			//当前用户是否是病理专家modify by ltw at 2015-12-01
			if(!user.getId().equals(doc.getCstPatExp()))
			{
				log.warn("该用户:"+user.getName()+"不是病理专家");
				n=Constants.UNPATHOLOGYFLAG;
				return n;
			}
			//fileName = fileName+".jpg";//jtPath.substring(jtPath.lastIndexOf("/")+1);
			//jtPath=jtPath.substring(jtPath.lastIndexOf("/")+1);
			System.out.println("要删除截图的名称========"+jtPath);
			//执行更新(删除操作)
			CstSlideScreenshot params1 = new CstSlideScreenshot();
			params1.setId(id);
			screenshotService.delete(params1);
			n = 1;
		}  catch (Exception e) {
			e.printStackTrace();
			log.error("update fail");
		}
		return n;
	}
	
	/***
	 * //根据病理号查询截图
	 * @param testId 病理号
	 * @return 记录
	 */
	public List<CstSlideScreenshot> getByCaseId(String testId)
	{
		List<CstSlideScreenshot> lists = null;
		try {
			
			lists = screenshotService.getImgs(testId);
		} catch (Exception e) {
			log.error("Exception in getByCaseId!" + "\n" + e.getMessage());
			e.printStackTrace();
		}
		return lists;
	}
	
}
