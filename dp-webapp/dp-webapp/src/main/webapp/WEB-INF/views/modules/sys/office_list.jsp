<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section class="content-header">
	<h1>组织机构</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">组织机构</li>
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
				<td width="195px" id="leftTd" valign="top">
					<!--左侧sidebar-->
					<div class="col-md-3" style="width: 210px; height: 100%;padding-right:5px;"
						id="leftDiv">
						<a href="javascript:void(0);"
							class="btn btn-primary btn-block margin-bottom">组织机构</a>
						<div class="box box-solid" style="height: 100%;overflow-x:auto;overflow-y:auto;">
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
									<td id="arr" valign="middle"><span class="glyphicon glyphicon-chevron-left	"></span></td>
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
								<form id="office_form" action="${ctx }/admin/office/list"
									method="post">
									<input type="hidden" name="pageSize" id="pageSize" value="10">
				      				<input type="hidden" name="pageNo" id="pageNo" value="1">
				      				<input type="hidden" name="orderBy" id="orderBy" value="sort desc">
				      				<input type="hidden" name="parentIds" id="parentIds">
									<div style="width: 100%;">
									<div class="row">
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
													<label for="typeSearch" class="col-xs-4 control-label padding">区域类型：</label>
													<div class="col-xs-8 padding">
														<dict:select defaultValue="true" name="type"
															id="typeSearch" dictName="office_type" cssClass="input-sm" />
													</div>
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
								<div  class="table-responsive">
									<table id="office_list"
										class="table table-bordered table-hover table-mailbox">
										<thead>
											<tr>
<!-- 												<th>排序</th> -->
												<th>编号</th>
												<th>机构名称</th>
												<th>归属区域</th>
												<th>区域编码</th>
												<th>机构类型</th>
												<th>电话</th>
												<th>是否可用</th>
												<th width="65px">操作</th>
											</tr>
										<thead>
										<tbody>
	
										</tbody>
									</table>
									<%@include file="/WEB-INF/views/include/page.jsp" %>
								</div>
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
					<form id="add-form" action="${ctx }/admin/office/update" method="post" class="form-horizontal">
						<input type="hidden" name="id" id="id">
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">名称:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="name"
									id="name" placeholder="请输入名称" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>

							<label for="area" class="col-sm-2 control-label">归属地区:</label>
							<div class="col-sm-3">
								<input type="hidden" name="area.id" id="areaId">
									<input type="text" class="form-control input-sm" name="area.name"
									id="areaName" onclick="showMenu('areaName');" placeholder="请选择归属地区!" required="required" readonly>
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
						</div>

						<div class="form-group">
							<label for="code" class="col-sm-2 control-label">区域编码:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="code"
									id="code" placeholder="" required="required" readonly>
							</div>
							<label for="type" class="col-sm-2 control-label">类型:</label>
							<div class="col-sm-3">
							<dict:select name="type" id="type" dictName="office_type" defaultValue="false" cssClass="form-control input-sm" />
							</div>
						</div>
						<div class="form-group">
							<label for="grade" class="col-sm-2 control-label">机构等级:</label>
							<div class="col-sm-3">
							<dict:select name="grade" id="grade" dictName="office_grade" defaultValue="false" cssClass="form-control input-sm" />
							</div>

							<label for="parent" class="col-sm-2 control-label">上级机构:</label>
							<div class="col-sm-3">
								<input type="hidden" name="parent.id" id="parentId"> <input
									type="text" class="form-control input-sm" name="parent.name"
									id="parent" onclick="showMenu('parent');" readonly placeholder="请选择上级机构!">
							</div>
						</div>
						<div class="form-group">
							<label for="address" class="col-sm-2 control-label">联系地址:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="address"
									id="address" placeholder="" required="required">
							</div>

							<label for="zipCode" class="col-sm-2 control-label">邮政编码:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="zipCode"
									id="zipCode" placeholder="" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="fax" class="col-sm-2 control-label">传真:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="fax"
									id="fax" placeholder="" required="required">
							</div>

							<label for="master" class="col-sm-2 control-label">负责人:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm" name="master"
									id="master" placeholder="">
							</div>
						</div>
						<div class="form-group">
							<label for="phone" class="col-sm-2 control-label">电话:</label>
							<div class="col-sm-3">
								<input type="tel" class="form-control input-sm" name="phone"
									id="phone" placeholder="" required="required">
							</div>

							<label for="useable" class="col-sm-2 control-label">是否可用:</label>
							<div class="col-sm-3">
								<dict:select name="useable" id="useable" dictName="YN" defaultValue="false" cssClass="form-control input-sm"/>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-2 control-label">邮箱:</label>
							<div class="col-sm-3">
								<input type="email" class="form-control input-sm" name="email"
									id="email" placeholder="" required="required">
							</div>

							<label for="primaryPerson" class="col-sm-2 control-label">主负责人:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm"
									name="primaryPerson.id" id="primaryPerson" placeholder=""
									required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="sort" class="col-sm-2 control-label">排序:</label>
							<div class="col-sm-3">
								<input type="number" class="form-control input-sm" name="sort"
									id="sort" placeholder="请输入排序!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
							<label for="deputyPerson" class="col-sm-2 control-label">副负责人:</label>
							<div class="col-sm-3">
								<input type="text" class="form-control input-sm"
									name="deputyPerson.id" id="deputyPerson" placeholder=""
									required="required">
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
<!-- 上级机构下拉框树 -->
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute; z-index: 2147483647;">
	<ul id="treeOffice" class="ztree"
		style="margin-top: 0; width: 180px; background: #f0f6e4;"></ul>
</div>
<!-- 获取ztree对象初始化data数据（将同级的treeNode转换成父子节点结构） -->
<div style="display: none;">
	<ul id="treeParent" class="ztree"></ul>
</div>

<script src="${ctxStatic}/modules/sys/office_list.js" ></script>