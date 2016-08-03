package com.kingmed.dp.modules.caseLib.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.os.common.persistence.Page;
import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.kingmed.dp.module.caseLib.entity.GuestBook;
import com.kingmed.dp.module.sys.entity.User;
import com.kingmed.dp.module.sys.utils.LogUtils;
import com.kingmed.dp.module.sys.vo.ResponseVO;
import com.kingmed.dp.modules.caseLib.service.GuestBookService;

@Controller
@RequestMapping("cons/lib/guestBook")
public class GuestBookController  extends BaseController{

	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response) throws IOException{
		return "modules/consultation/guestBook_list";
	}
	
	@ResponseBody
	@RequestMapping("/searchList")
	public ResponseVO<GuestBook> searchList(HttpServletRequest request,HttpServletResponse response,GuestBook guestBook){
		Page<GuestBook> pages = guestBookService.findPage(new Page<GuestBook>(request,response), guestBook);
		return new ResponseVO<GuestBook>(pages);
	}
	@ResponseBody
	@RequestMapping("/listNoPage")
	public List<GuestBook> listNoPage(HttpServletRequest request,HttpServletResponse response,GuestBook guestBook){
		List<GuestBook> lists = guestBookService.findList(guestBook);
		return lists;
	}
	
	/**
	 * 根据id查出数据库单条GuestBook数据，每次请求都会先执行此方法，后在执行请求方法
	 * @param guestBook
	 * @param map
	 */
	@ModelAttribute
	public void findGuestBook(GuestBook guestBook,Map<String,Object> map){
		if(StringUtils.isNotBlank(guestBook.getId())){
			GuestBook model = guestBookService.get(guestBook.getId());
			map.put("guestBook",model);
		}
	}
	@RequestMapping(value="/save",method= RequestMethod.POST)
	public void save(HttpServletRequest request,HttpServletResponse response,GuestBook guestBook) throws IOException{
		String result = "0";
		if(!(guestBook.getUser()!=null&&guestBook.getUser().getId()!=null)){
			User u = new User();
			u.setId("-1");
			guestBook.setUser(u);
		}
		try {
			guestBookService.save(guestBook);
			result = "1";
		} catch (Exception e) {
			result = "-1";
			e.printStackTrace();
		}
		response.getWriter().write(result);
	}
	
	@RequestMapping(value="/del",method= RequestMethod.GET)
	public void delete(HttpServletRequest request,HttpServletResponse response,GuestBook model) throws IOException{
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", 0);
		if(StringUtils.isNotBlank(model.getId())){
			try {
				guestBookService.delete(model);
				result.put("status", 1);
				LogUtils.saveLog(request, "删除最近访问数据");
			} catch (Exception e) {
				result.put("status", 2);
				result.put("resultMsg", "删除失败！");
				e.printStackTrace();
			}
		}else{
			result.put("status", 2);
			result.put("resultMsg", "删除失败，id为空！");
		}
		String jsonStr = JSON.toJSONString(result);
		response.getWriter().write(jsonStr);
	}
}
