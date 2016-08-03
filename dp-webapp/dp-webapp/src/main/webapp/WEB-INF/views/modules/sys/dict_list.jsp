<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section class="content-header">
	<h1>数据字典</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
		<li class="active">数据字典</li>
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
	      			<form id="dict_form" action="${ctx }/admin/dict/list" method="post">
	      				<input type="hidden" name="pageSize" id="pageSize" value="10">
	      				<input type="hidden" name="pageNo" id="pageNo" value="1">
	      				<input type="hidden" name="orderBy" id="orderBy" value="type desc">
	      				<div style="width:100%;">
	      					<div class="col-xs-5">
								<div class="form-group">
									<label for="labelSearch" class="col-xs-4 control-label padding">标签名：</label>
									<div class="col-xs-8 padding">
										<input type="text" class="form-control input-sm"  id="label" name="label" />
									</div>
								</div>
							</div>
							<div class="col-xs-5">
								<div class="form-group">
									<label for="typeSearch" class="col-xs-4 control-label padding">类型：</label>
									<div class="col-xs-8 padding">
										<input type="text" class="form-control input-sm"  id="type" name="type" />
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
			<div class="box minWidthM">
					<div class="box-body table-responsive">
						<table id="dict_list" class="table table-bordered table-hover" style="width:100%;">
							<thead>
								<tr>
									<th>序号</th>
									<th>类型</th>
									<th>标签</th>
									<th>描述</th>
									<th>键值</th>
<!-- 									<th>排序</th> -->
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
					<form id="add-form" action="${ctx }/admin/dict/update" method="post"
						role="form" class="form-horizontal">
						<input type="hidden" name="id" id="id">
						<div class="form-group">
							<label for="value" class="col-xs-4 control-label">键值:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="value" id="value"
									placeholder="请输入键值!" required="required">
							</div>
							<span style="color:red;float:left;line-height: 30px;">*</span>
						</div>
						<div class="form-group">
							<label for="label" class="col-xs-4 control-label">标签:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="label" id="label"
									placeholder="请输入标签!" required="required">
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
							<label for="description" class="col-xs-4 control-label">描述:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="description"
									id="description" placeholder="请输入描述!" required="required">
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

<script src="${ctxStatic}/modules/sys/dict_list.js" ></script>