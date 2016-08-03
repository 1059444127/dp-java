<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section class="content-header">
	<h1>角色管理</h1>
	<ol class="breadcrumb">
		<li><a href="javascript:void(0);"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">角色管理</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<!-- Info boxes -->

	<!-- Main row -->
	<div class="row">
		<!-- Left col -->
		<div class="col-xs-12">
					<!-- 搜索 start-->
						<div class="box box-solid minWidthM" style="margin-bottom: 4px;">
							<div class="box-body">
								<form id="role_form" action="${ctx }/admin/role/list"
									method="post">
									<input type="hidden" name="pageSize" id="pageSize" value="10">
				      				<input type="hidden" name="pageNo" id="pageNo" value="1">
				      				<input type="hidden" name="orderBy" id="orderBy" value="id desc">
									<div style="width: 100%;">
										<div class="col-xs-5">
												<div class="form-group">
													<label for="nameSearch" class="col-xs-4 control-label padding">名称：</label>
													<div class="col-xs-8 padding">
														<input type="text" class="form-control input-sm"
															id="nameSearch" name="name">
													</div>
												</div>
										</div>
										<div class="col-xs-5">
											<div class="form-group">
												<label for="typeSearch" class="col-xs-4 control-label padding">类型：</label>
												<div class="col-xs-8 padding">
													<dict:select name="roleType" id="typeSearch" dictName="role_type" defaultValue="true" cssClass="input-sm"/>
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
			<div class="box minWidthM">
					<div class="box-body">
						<table id="role_list" class="table table-bordered table-hover" style="width:100%;">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>英文名称</th>
									<th>类型</th>
									<th>菜单管理</th>
									<th>是否有效</th>
									
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
		</div>
		<!-- /.col -->
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
					<form id="add-form" action="${ctx }/admin/role/update" method="post"
						role="form" class="form-horizontal">
						<input type="hidden" name="id" id="id">
						<div class="form-group">
							<label for="value" class="col-xs-4 control-label">角色名称:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="name" id="name"
									placeholder="" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
						</div>
						<div class="form-group">
							<label for="label" class="col-xs-4 control-label">归属机构:</label>
							<div class="col-xs-5">
								<input type="hidden" id="officeId" name="office.id" />
								<input type="text" id="officeName" name="office.name" class="form-control input-sm" 
								placeholder="" required="required" onclick="showMenu('office')" readonly/>
							</div>
						</div>
						<div class="form-group">
							<label for="label" class="col-xs-4 control-label">菜单选择:</label>
							<div class="col-xs-5">
								<input type="hidden" id="menuId" name="menuList" />
								<input type="text" id="menuName" name="menuName" class="form-control input-sm" 
								placeholder="" required="required" onclick="showMenu('menu')" readonly/>
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-xs-4 control-label">数据范围:</label>
							<div class="col-xs-5">
								<dict:select name="dataScope" id="dataScope" dictName="ROLE_DATA_SCOPE" defaultValue="false" value="8" cssClass="form-control input-sm"/>
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-xs-4 control-label">角色类型:</label>
							<div class="col-xs-5">
								<dict:select name="roleType" id="roleType" dictName="role_type" defaultValue="false" cssClass="form-control input-sm"/>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-xs-4 control-label">英文名称:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="enname"
									id="enname" placeholder="" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="sort" class="col-xs-4 control-label">是否有效:</label>
							<div class="col-xs-5">
							<dict:select name="useable" id="useable" dictName="YN" defaultValue="false" cssClass="form-control input-sm"/>
							</div>
						</div>
						<div class="form-group">
							<label for="remarks" class="col-xs-4 control-label">备注:</label>
							<div class="col-xs-5">
								<textarea rows="3" class="form-control input-sm" name="remarks" id="remarks"
									placeholder="" required="required"></textarea>
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

<!-- 菜单分配弹出模态窗口 -->
<div class="modal fade" id="menu-div" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">菜单分配</h4>
			</div>
			<div class="modal-body">
				<div style="width: 100%; text-align: center;">
					<form id="updateMenu-form" action="${ctx }/admin/role/update" method="post"
						role="form" class="form-horizontal">
						<input type="hidden" name="roleId" id="roleId">
						<div class="form-group">
							<label for="label" class="col-sm-3 control-label">菜单选择:</label>
							<div class="col-sm-6">
								<input type="hidden" id="listMenuId" name="menuList" />
								<div id="menuTree" class="menuContent">
									<ul id="treeRoleMenu" class="ztree"
										style="margin-top: 0; width: 280px; background: #f0f6e4;"></ul>
								</div>
							</div>
						</div>
						<input class="btn btn-default" type="button"
							onclick="listMenuTreeSubmit()" value="确定"> &nbsp; <input
							class="btn btn-default" aria-label="Close" data-dismiss="modal"
							type="button" value="取消">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- input下拉框树 -->
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute; z-index: 2147483647;">
	<ul id="treeRole" class="ztree"
		style="margin-top: 0; width: 218px; background: #f0f6e4;"></ul>
</div>
<!-- 获取ztree对象初始化data数据（将同级的treeNode转换成父子节点结构） -->
<div style="display: none;">
	<ul id="treeParent" class="ztree"></ul>
</div>

<script src="${ctxStatic}/modules/sys/role_list.js" ></script>