<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<style type='text/css'>
th {
	text-align:center;
}
label.padding {
	margin-top: 7px;
	padding-right: 0px;
	padding-bottom: 7px;
	text-align: right;
}
div.padding {
	padding-left: 0px;
	padding-right: 0px;
}
</style>
<section class="content-header">
  <h1>
                              日志列表
    <small></small>
  </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
    <li><a href="#">日志列表</a></li>
    <!-- <li class="active">Data tables</li> -->
  </ol>
</section>

<!-- Main content -->
<section class="content">
  <div class="row">
    <div class="col-xs-12">
      <div class="box box-solid" style="margin-bottom: 4px;">
      	<div class="box-body">
      	<form id="searchForm" action="${ctx }/admin/job/list" method="post">
      		<input type="hidden" name="pageSize" id="pageSize" value="10">
   			<input type="hidden" name="pageNo" id="pageNo" value="1">
   			<input type="hidden" name="orderBy" id="orderBy" value="type desc">
          <div class="row">
          	<div class="col-xs-4" style="padding-right: 0px;">
            	<div class="form-group">
					<label class="col-xs-4 control-label padding">用户名：</label>
					<div class="col-xs-8 padding">
						<input type="text" class="form-control" name="userName" id="sUserName" >
					</div>
				</div>
           	</div> 
           	<div class="col-xs-4" style="padding-right: 0px;">
            	<div class="form-group">
					<label class="col-xs-4 control-label padding">服务器地址：</label>
					<div class="col-xs-8 padding">
						<input type="text" class="form-control" name="url" id="sUrl" >
					</div>
				</div>
           	</div> 
           	<div class="col-xs-4" style="text-align:right;">
           		<input type="button" class="btn btn-primary" style="margin-right: 20px;" onclick="search()" value="查询">
           		<input type="button" class="btn btn-primary" onclick="add()" value="添加">
          		
          	</div>
           </div>
           <!-- <div class="row">
           	<div class="col-xs-3">
           	</div> 
           	<div class="col-xs-4"></div>
           	<div class="col-xs-4" style="text-align:right;">
           		<input type="button" class="btn btn-primary" onclick="add()" value="添加">
          		<input type="button" class="btn btn-primary" style="margin-right: 20px;" onclick="search()" value="搜索">
          	</div>
           </div> -->
         </form>
       </div><!-- /.box-header -->
     </div>
      <div class="box">
        <!-- <div class="box-header">
          <h3 class="box-title">Data Table With Full Features</h3>
        </div> --><!-- /.box-header -->
        <div class="box-body">
          <table id="jobTable" class="table table-bordered table-hover" style="width:100%;">
            <thead>
              <tr>
              	<th width="3%">编号</th>
                <th width="12%">用户名</th>
                <th width="20%">密码</th>
                <th width="20%">操作url</th>
                <th width="12%">创建者</th>
                <th width="20%">创建时间</th>
                <th width="15%">操作</th>
              </tr>
            </thead>
            <tbody>
            </tbody>
          </table>
          <%@include file="/WEB-INF/views/include/page.jsp" %>
        </div><!-- /.box-body -->
      </div><!-- /.box -->
    </div><!-- /.col -->
  </div><!-- /.row -->
</section><!-- /.content -->

<%-- 修改、新增页面 --%>
	<div class="modal fade" id="add-div" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="my_modal_title"></h4>
				</div>
				<div class="modal-body">
					<div style="width: 100%; text-align: center;">
						<form id="form" class="form-horizontal" style="margin-top: 10px;text-align: center;" action="${ctx }/dp/menuMange/saveMenu" method="post">
						<input type="hidden" class="form-control" name="id" id="id" value="">
						<div class="form-group">
							<label class="col-sm-3 control-label">用户名：</label>
							<div class="col-sm-4 padding">
								<input type="text" class="form-control" name="userName" id="userName" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" >密码：</label>
							<div class="col-sm-4 padding">
								<input type="text" class="form-control" name="userPassword" id="userPassword" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label" >url：</label>
							<div class="col-sm-4 padding">
								<input type="text" class="form-control" name="url" id="url" value="">
							</div>
							<span style="color:red;float:left;line-height: 30px;">  *</span>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">备注信息：</label>
							<div class="col-sm-4 padding">
								<input type="text" class="form-control" name="remarks" id="remarks" value="">
							</div>
						</div>
						<div calss="box-footer">
							<input class="btn btn-default" type="button" onclick="save()" value="保存">
							<input type="button" class="btn btn-default" aria-label="Close" data-dismiss="modal" value="取消"/>
								
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

<script src="${ctxStatic}/modules/jobIsParams/jobIsParams.js" ></script>
