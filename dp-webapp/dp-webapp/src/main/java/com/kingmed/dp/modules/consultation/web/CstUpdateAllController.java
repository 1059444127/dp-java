package com.kingmed.dp.modules.consultation.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kingmed.dp.module.consultation.entity.CstSlide;
import com.kingmed.dp.module.consultation.entity.Scanner;
import com.kingmed.dp.module.consultation.entity.Slide;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.modules.consultation.service.CstSlideService;
import com.kingmed.dp.modules.consultation.service.ScannerService;
import com.kingmed.dp.modules.consultation.service.SlideService;

@Controller
@RequestMapping("cons/updateAll")
public class CstUpdateAllController  extends BaseController{

	@Autowired
	private CstSlideService cstSlideService;
	
	@Autowired
	private SlideService slideService;
	
	@Autowired
	private ScannerService scannerService;
	
	@RequestMapping(value="/updateSlide",method=RequestMethod.POST)
	public void updateSlide(HttpServletRequest request,HttpServletResponse response,CstSlide cstSlide) throws IOException{
		String result = "0";
		try {
			cstSlideService.save(cstSlide);
			result = "1";
			LogUtils.saveLog(request, "添加会诊-数字切片");
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	
	@RequestMapping(value="/slide",method=RequestMethod.POST)
	public void slide(HttpServletRequest request,HttpServletResponse response,Slide slide) throws IOException{
		String result = "0";
		try {
			slideService.save(slide);;
			result = "1";
			LogUtils.saveLog(request, "上传数字切片");
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	
	@RequestMapping(value="/queryPointUrl",method=RequestMethod.POST)
	public void getUrlByScanner(HttpServletRequest request,HttpServletResponse response,String url,String point) throws IOException{
		String result = "-1";;
		try {
			Scanner scanner = scannerService.getByScannerCode(point);
			if(scanner!=null&&"1".equals(scanner.getMosaic())){
				result = url;
			}
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
}
