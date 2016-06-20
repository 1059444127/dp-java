package com.kingmed.dp.gateway.web.service;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.kingmed.dp.gateway.command.SlideCommand;
import com.kingmed.dp.gateway.dto.SlideDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zhengjunjie
 */
@RequestScoped
@Path("/dp")
public class SlideService {

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
        //TODO 调用 SlideCommand上传切片地址
        SlideDto slide = null;
        String fs = new SlideCommand(slide).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() {
        return Response.ok("测试成功").build();
    }

}
