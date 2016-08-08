package com.kingmed.dp.gateway.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.kingmed.dp.gateway.command.SlideCommand;
import com.kingmed.dp.gateway.dto.SlideDto;

/**
 *
 * @author zhengjunjie
 */
@RequestScoped
@Path("/dp")
public class SlideService {
	
	private static Logger logger = Logger.getLogger(SlideService.class);

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Inject
    public SlideService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @POST
    @Path("/slide")
    @RequiresPermissions("WSI:allowed")
    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadURL() {
        // 调用 SlideCommand上传切片地址
    	SlideDto slide = new SlideDto(request);
    	
        String fs = new SlideCommand(slide).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }

    @GET
    @Path("/test")
    @RequiresPermissions("TEST:allowed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        return Response.ok("测试成功").build();
    }
    
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login() {
    	// 得到当前执行的用户
    	Subject currentUser=SecurityUtils.getSubject();
    	// 创建token令牌，用户名/密码
    	UsernamePasswordToken token=new UsernamePasswordToken(request.getParameter("username"), request.getParameter("password"));
    	try {
			// 身份认证
			currentUser.login(token);
			return Response.ok("登陆成功").build();
		} catch (AuthenticationException e) {
			logger.error("登陆失败："+e);
			e.printStackTrace();
		}
    	
    	return Response.ok("登陆失败").build();
    }

}
