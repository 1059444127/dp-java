<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section class="content-header">
	<h1>病例库类型</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 病例库</a></li>
		<li class="active">病例库类型</li>
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
	      			<form id="search_form" action="${ctx }/cons/lib/patType/list" method="post">
	      				<input type="hidden" name="pageSize" id="pageSize" value="10">
	      				<input type="hidden" name="pageNo" id="pageNo" value="1">
	      				<input type="hidden" name="orderBy" id="orderBy" value="a.create_date desc">
	      				<div style="width:100%;">
	      					<div class="col-xs-5">
								<div class="form-group">
									<label for="nameSearch" class="col-xs-4 control-label padding">名称：</label>
									<div class="col-xs-8 padding">
										<input type="text" class="form-control input-sm"  name="name" id="name">
									</div>
								</div>
							</div>
							<%-- <div class="col-xs-5">
								<div class="form-group">
									<label for="statusSearch" class="col-xs-4 control-label padding">状态：</label>
									<div class="col-xs-8 padding">
									<dict:select defaultValue="true" name="state"
															id="statusSearch" dictName="cstCase_status" cssClass="input-sm" />
									</div>
								</div>
							</div> --%>
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
			<div class="box minWidthM">
					<div class="box-body table-responsive">
						<table id="table_list" class="table table-bordered table-hover" style="width:100%;">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>代码</th>
									<th>类型</th>
									<th>创建时间</th>
									<th>更新时间</th>
									<th>备注</th>
									<th style="width:105px;">操作</th>
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
					<form id="add-form" action="${ctx }/cons/lib/patType/update" method="post"
						role="form" class="form-horizontal">
						<input type="hidden" name="id" id="id">
						<div class="form-group">
							<label for="value" class="col-xs-4 control-label">名称:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="name" id="name"
									placeholder="请输入名称!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
						</div>
						<div class="form-group">
							<label for="type" class="col-xs-4 control-label">病例库等级:</label>
							<div class="col-xs-5">
							<dict:select defaultValue="true" name="grade" id="grade" dictName="PAT_TYPE_GRADE" cssClass="form-control input-sm"/>
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
							<label for="label" class="col-xs-4 control-label">代码:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="code" id="code"
									placeholder="请输入代码!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
						</div>
						<div class="form-group">
							<label for="type" class="col-xs-4 control-label">类型:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="type" id="type"
									placeholder="请输入类型!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
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
	<ul id="treePatType" class="ztree"
		style="margin-top: 0; width: 180px; background: #f0f6e4;"></ul>
</div>
<!-- 获取ztree对象初始化data数据（将同级的treeNode转换成父子节点结构） -->
<div style="display: none;">
	<ul id="treeParent" class="ztree"></ul>
</div>

<script src="${ctxStatic}/modules/caseLib/patType_list.js" ></script>