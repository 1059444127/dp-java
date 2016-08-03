package com.kingmed.dp.modules.sys.web;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.os.common.security.shiro.session.SessionDAO;
import org.os.common.utils.CacheUtils;
import org.os.common.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;
import com.kingmed.dp.module.sys.utils.UserUtils;
import com.kingmed.dp.modules.sys.security.SystemAuthorizingRealm.Principal;

/**
 * 
 * @author jack
 * @version 2016年3月22日
 */

@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private SessionDAO sessionDAO;
	
    @RequestMapping(value="",method= RequestMethod.GET)
    public String index1(Model model, @ModelAttribute LoginCommand command ) {
    	return "redirect:/dp";
    }
    
    @RequestMapping(value="/",method= RequestMethod.GET)
    public String index(Model model, @ModelAttribute LoginCommand command ) {
    	return "redirect:/dp/login";
    }
    
    @RequestMapping(value="/dp",method= RequestMethod.GET)
    public String showLoginForm(Model model, @ModelAttribute LoginCommand command ) {
        return "modules/sys/index";
    }

    @RequestMapping(value="/dp/login",method= RequestMethod.POST)
    public String login(Model model, @ModelAttribute LoginCommand command, BindingResult errors) {
    	Principal principal = UserUtils.getPrincipal();
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		if(principal != null && !principal.isMobileLogin()){
			logger.info("已经登录");
			return "modules/sys/index";
		}
    	return "modules/sys/login";
    }
    
    @RequestMapping(value="/dp/login",method= RequestMethod.GET)
    public String doLogin(Model model, @ModelAttribute LoginCommand command, BindingResult errors) {
    	Principal principal = UserUtils.getPrincipal();
		if (logger.isDebugEnabled()){
			logger.debug("login, active session size: {}", sessionDAO.getActiveSessions(false).size());
		}
		if(principal != null && !principal.isMobileLogin()){
			logger.info("已经登录");
			return "modules/sys/index";
		}
    	return "modules/sys/login";
    }

    @RequestMapping("/dp/logout")
    public String logout() {
    	Subject subject = SecurityUtils.getSubject();
    	if (subject.isAuthenticated()) {
    		String username = ((Principal)subject.getPrincipal()).getLoginName();
    		UserUtils.clearCache();//清除当前用户缓存
    		subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
    		if (logger.isDebugEnabled()) {
    			logger.debug("用户" + username + "退出登录");
    		}
    	}
        return "modules/sys/login";
    }
}


