package com.kingmed.dp.gateway.web.service;

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

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import com.kingmed.dp.gateway.command.CstCaseCommand;
import com.kingmed.dp.gateway.command.FileNameCommand;
import com.kingmed.dp.gateway.command.ProjectParamCommand;
import com.kingmed.dp.gateway.command.UsersCommand;
import com.kingmed.dp.gateway.dto.CstCaseDto;
import com.kingmed.dp.gateway.dto.ParamsDto;
import com.kingmed.dp.gateway.dto.ProjectParamDto;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author zhanglei
 */
@RequestScoped
@Path("/cst")
public class DocumentService {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Inject
    public DocumentService(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @POST
    @Path("/case/createDocument")
    @RequiresPermissions("CST:allowed")
    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDocument() {
    	CstCaseDto cstCase = new CstCaseDto(request);
        String fs = new CstCaseCommand(cstCase).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }

    @POST
    @Path("/slide/updateDocument")
    @RequiresPermissions("CST:allowed")
    @RequiresAuthentication
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDocument() {
    	ProjectParamDto projectParam = new ProjectParamDto(request);
        String fs = new ProjectParamCommand(projectParam).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }

    @GET
    @Path("/case/slide/snapshot")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFileName() {

    	ParamsDto params = new ParamsDto(request,new String[]{"testId","barcode","antiBody"});
        String fs = new FileNameCommand(params).execute();
        //解析上传结果
        return Response.ok(fs).build();
    }

    @GET
    @Path("/dr")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
    	ParamsDto params = new ParamsDto(request,new String[]{});
        String fs = new UsersCommand(params).execute();
        //解析上传结果
        //return Response.ok(fs).build();
				return Response.ok(fs).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON + ";charset=UTF-8").build();

    }

}
