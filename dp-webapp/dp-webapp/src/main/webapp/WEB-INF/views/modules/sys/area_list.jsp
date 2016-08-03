<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section class="content-header">
	<h1>区域管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">区域管理</li>
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
				<td style="width:195px;" id="leftTd" valign="top">
					<!--左侧sidebar-->
					<div class="col-md-3" style="width:210px;height:100%;padding-right:5px;" id="leftDiv">
						<a href="javascript:void(0);" class="btn btn-primary btn-block margin-bottom">区域结构</a>
						<div class="box box-solid" style="height:90%;overflow-x:auto;overflow-y:auto;">
							<div>
								<ul id="MyTreearea" class="ztree"></ul>
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
						 <div class="box box-solid minWidthM" style="margin-bottom: 4px;">
			      			<div class="box-body">
				      			<form id="area_form" action="${ctx }/admin/area/list" method="post">
					      			<input type="hidden" name="pageSize" id="pageSize" value="10">
				      				<input type="hidden" name="pageNo" id="pageNo" value="1">
				      				<input type="hidden" name="orderBy" id="orderBy" value="id desc">
				      				<input type="hidden" name="parentIds" id="parentIds">
				      				<div style="width:100%;">
					      				<div class="col-xs-5">
											<div class="form-group">
												<label for="nameSearch" class="col-xs-4 control-label padding">区域名称：</label>
												<div class="col-xs-8 padding">
													<input type="text" class="form-control input-sm"  id="nameSearch" name="name">
												</div>
											</div>
										</div>
										<div class="col-xs-5">
											<div class="form-group">
												<label for="typeSearch" class="col-xs-4 control-label padding">区域类型：</label>
												<div class="col-xs-8 padding">
													<dict:select defaultValue="true"
														name="type" id="typeSearch" dictName="area_type"
														cssClass="input-sm" />
												</div>
											</div>
										</div>
										<span style="float:right;margin-right:30px;">
											<input class="btn btn-primary" type="button" onclick="queryList();" value="查询" >
											<input class="btn btn-primary" type="button" onclick="add();" value="添加" >
										</span>
									</div>
							   </form>
			      			</div>
			      		 </div>
						<!-- 搜索 end-->
						<!-- TABLE: LATEST ORDERS -->
						<div class="box">
							<div class="box-body">
									<table id="area_list"
										class="table  table-bordered table-hover minWidthM"
										style="width: 100%;">
										<thead>
											<tr>
												<th>编号</th>
												<th>区域名称</th>
												<th>区域编号</th>
												<th>区域类型</th>
												<th>备注</th>
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
					<form id="add-form" action="${ctx }/admin/area/update" method="post"
						class="form-horizontal">
						<input type="hidden" name="id" id="id">
						
						<div class="form-group">
							<label for="value" class="col-xs-4 control-label">区域名称:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="name" id="name"
									placeholder="请输入区域名称!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
						</div>
						<div class="form-group">
							<label for="label" class="col-xs-4 control-label">区域编号:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="code" id="code"
									placeholder="" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-xs-4 control-label">区域类型:</label>
							<div class="col-xs-5">
							<dict:select defaultValue="true" name="type" id="type" dictName="area_type" cssClass="form-control input-sm"/>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-xs-4 control-label">上级区域:</label>
							<div class="col-xs-5">
								<input type="hidden" name="parent.id" id="parentId"> <input
								type="text" class="form-control input-sm" name="parent.name"
								id="parent" onclick="showMenu();" >
							</div>
						</div>
						<div class="form-group">
							<label for="sort" class="col-xs-4 control-label">排序:</label>
							<div class="col-xs-5">
								<input type="number" class="form-control input-sm" name="sort" id="sort"
									placeholder="请输入排序!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
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
<!-- 上级机构下拉框树 -->
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute; z-index: 2147483647;">
	<ul id="treearea" class="ztree"
		style="margin-top: 0; width: 180px; background: #f0f6e4;"></ul>
</div>
<!-- 获取ztree对象初始化data数据（将同级的treeNode转换成父子节点结构） -->
<div style="display: none;">
	<ul id="treeParent" class="ztree"></ul>
</div>
<script src="${ctxStatic}/modules/sys/area_list.js" ></script>
