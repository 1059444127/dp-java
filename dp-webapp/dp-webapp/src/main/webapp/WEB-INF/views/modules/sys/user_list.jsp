<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section class="content-header">
	<h1>用户管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">用户管理</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<!-- Info boxes -->

	<!-- Main row -->
	<div class="row">
		<!-- Left col -->


		<table style="width: 100%; height: 450px;" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="195px" id="leftTd">
					<!--左侧sidebar-->
					<div class="col-md-3" style="width: 210px; height: 100%;padding-right:5px;"
						id="leftDiv">
						<a href="javascript:void(0);"
							class="btn btn-primary btn-block margin-bottom">组织机构</a>
						<div class="box box-solid" style="height: 90%;overflow-x:auto;overflow-y:auto;">
							<div>
								<ul id="MyTreeOffice" class="ztree"></ul>
							</div>
						</div>
					</div>
				</td>
				<td width="2px">
					<!--打开关闭switch-->
					<div class="switch">
						<div id="arrow" class="pulltab" onclick="opTab()">
							<table class="inner">
								<tr>
									<td id="arr" valign="middle"><span class="glyphicon glyphicon-chevron-left"></span></td>
								</tr>
							</table>
						</div>
					</div>
				</td>
				<td valign="top" width="100%" height="100%">
					<div class="col-xs-12">
						<!-- 搜索 start-->
						<div class="box box-solid" style="margin-bottom: 4px;">
							<div class="box-body">
								<form id="user_form" action="${ctx }/admin/user/list"
									method="post">
									<input type="hidden" name="pageSize" id="pageSize" value="10">
				      				<input type="hidden" name="pageNo" id="pageNo" value="1">
				      				<input type="hidden" name="orderBy" id="orderBy" value="id desc">
				      				<input type="hidden" name="company.id" id="hiddenCompanyId">
									<input type="hidden" name="office.id" id="hiddenOfficeId">
									<div style="width: 100%;">
									<div class="col-xs-5">
										<div class="form-group">
											<label for="loginnameSearch" class="col-xs-4 control-label padding">登录名：</label>
											<div class="col-xs-8 padding">
												<input type="text" class="form-control input-sm"
													id="loginnameSearch" name="loginName">
											</div>
											</div>
									</div>
									<div class="col-xs-5">
										<div class="form-group">
											<label for="nameSearch" class="col-xs-4 control-label padding">姓名：</label>
											<div class="col-xs-8 padding">
												<input type="text" class="form-control input-sm"
													id="nameSearch" name="name">
											</div>
										</div>
									</div>
										<span style="float: right; margin-right: 30px;"> <input
											class="btn btn-primary" type="button" onclick="queryList();"
											value="查询"> <input class="btn btn-primary"
											type="button" onclick="add();" value="添加">
										</span>
									</div>
								</form>
							</div>
						</div>
						<!-- 搜索 end-->
						<!-- TABLE: LATEST ORDERS -->
						<div class="box">
							<div class="box-body">
								<table id="user_list" class="table table-bordered table-hover minWidthM"
									style="width: 100%;">
									<thead>
										<tr>
											<th>编号</th>
											<th>归属公司</th>
											<th>归属部门</th>
											<th>登录名</th>
											<th>姓名</th>
											<th>电话</th>
											<th>手机</th>
											<th width="65px">操作</th>
										</tr>
									<thead>
									<tbody>

									</tbody>
								</table>
								<%@include file="/WEB-INF/views/include/page.jsp" %>
							</div>
							<!-- /.box-body -->
							<!-- /.box-footer -->
						</div>
						<!-- /.box -->
					</div> <!-- /.col -->
				</td>
			</tr>
		</table>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>

<!-- 修改添加内容弹出模态窗口 -->
<div class="modal fade" id="add-div" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
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
					<form id="add-form" action="${ctx }/admin/user/update" method="post"
						class="form-horizontal">
						<input type="hidden" name="id" id="id">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">姓名:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm text-danger" name="name"
									id="name" placeholder="请输入姓名!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>

							<label for="officeId" class="col-sm-2 control-label">归属部门:</label>
							<div class="col-sm-3">
								<input type="hidden" id="companyId" name="company.id" />
								<input type="hidden" id="officeId" name="office.id" />
								<input type="text" id="officeName" name="office.name" class="form-control input-sm" 
								placeholder="请选择归属部门!" required="required" onclick="showMenu()" readonly/>
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
						</div>

						<div class="form-group">
							<label for="loginName" class="col-sm-2 control-label">登录名:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="loginName"
									id="loginName" placeholder="请输入登录名!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
							
							<label for="userType" class="col-sm-2 control-label">用户类型:</label>
							<div class="col-sm-3">
							<dict:select name="userType" id="userType" dictName="user_type" defaultValue="true" cssClass="form-control input-sm"/>
							</div>
						</div>
						<div class="form-group" id="userPassword">
							<label for="password" class="col-sm-2 control-label">密码:</label>
							<div class="col-sm-3">
								<input type="password" class="form-control input-sm" name="password"
									id="password" placeholder="请输入密码!" onblur="contrast()" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>

							<label for="repassword" class="col-sm-2 control-label">重复密码:</label>
							<div class="col-sm-3">
							<input type="password" class="form-control input-sm" name="repassword"
									id="repassword" placeholder="" onblur="contrast()" required="required">
								
							</div>
						</div>
						<div class="form-group">
							<label for="no" class="col-sm-2 control-label">工号:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="no"
									id="no" placeholder="" required="required">
							</div>

							<label for="email" class="col-sm-2 control-label">邮箱:</label>
							<div class="col-sm-3">
								<input type="email" class="form-control input-sm" name="email"
									id="email" placeholder="" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label">电话:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="phone"
									id="phone" placeholder="" required="required">
							</div>

							<label for="mobile" class="col-sm-2 control-label">手机:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="mobile"
									id="mobile" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="photo" class="col-sm-2 control-label">用户头像:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="photo"
									id="photo" placeholder="" required="required">
							</div>

							<label class="col-sm-2 control-label"></label>
							<div class="col-sm-3">
							</div>
						</div>
						<div class="form-group">
							<label for="remarks" class="col-sm-2 control-label">备注:</label>
							<div class="col-sm-8">
								<textarea rows="3" class="form-control input-sm" name="remarks"
									id="remarks" placeholder="" required="required"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="roleList" class="col-sm-2 control-label">角色:</label>
							<div class="col-sm-10">
								<c:forEach var="item" items="${roles }">
									<fieldset style="float:left;width:140px;border: 1px solid #ddd;line-height:25px;margin-top:3px;margin-left:3px; ">
										<label class="checkbox-inline" style="padding-top:3px;">
										  <input type="checkbox" name="roleList" id="${item.id }" value="${item.id }" placeholder="请选择角色!"> ${item.name }
										</label>
									</fieldset>
								</c:forEach>
							</div>
						</div>
						<div class="form-group" id="user_msg" style="display:none;color:red;">
						<label for="useable" class="col-sm-2 control-label"></label>
							<div class="col-sm-8">
							</div>
						</div>
						<input class="btn btn-default" type="button"
							onclick="myFormSubmit()" value="确定"> &nbsp; <input
							class="btn btn-default" aria-label="Close" data-dismiss="modal"
							type="button" value="取消">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 公司部门下拉框树 -->
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute; z-index: 2147483647;">
	<ul id="treeOffice" class="ztree"
		style="margin-top: 0; width: 180px; background: #f0f6e4;"></ul>
</div>
<!-- 获取ztree对象初始化data数据（将同级的treeNode转换成父子节点结构） -->
<div style="display: none;">
	<ul id="treeParent" class="ztree"></ul>
</div>

<script src="${ctxStatic}/modules/sys/user_list.js" ></script>