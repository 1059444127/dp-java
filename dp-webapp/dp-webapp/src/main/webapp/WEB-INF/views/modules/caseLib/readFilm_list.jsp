<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section class="content-header">
	<h1>读片会</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 病例库</a></li>
		<li class="active">读片会</li>
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
	      			<form id="search_form" action="${ctx }/cons/lib/readFilm/list" method="post">
	      				<input type="hidden" name="pageSize" id="pageSize" value="10">
	      				<input type="hidden" name="pageNo" id="pageNo" value="1">
	      				<input type="hidden" name="orderBy" id="orderBy" value="a.create_date desc">
	      				<div style="width:100%;">
	      					<div class="col-xs-5">
								<div class="form-group">
									<label for="labelSearch" class="col-xs-4 control-label padding">名称：</label>
									<div class="col-xs-8 padding">
										<input type="text" class="form-control input-sm"  id="name" name="name" />
									</div>
								</div>
							</div>
							<div class="col-xs-5">
								<div class="form-group">
									<label for="typeSearch" class="col-xs-4 control-label padding">地址：</label>
									<div class="col-xs-8 padding">
										<input type="text" class="form-control input-sm"  id="address" name="address" />
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
						<table id="table_list" class="table table-bordered table-hover" style="width:100%;">
							<thead>
								<tr>
									<th width="50px">序号</th>
									<th>名称</th>
									<th>地址</th>
									<th width="95px;">日期</th>
									<th>主办单位</th>
									<th>备注</th>
									<th width="105px">操作</th>
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
					<form id="add-form" action="${ctx }/cons/lib/readFilm/update" method="post"
						role="form" class="form-horizontal">
						<input type="hidden" name="id" id="id">
						<div class="form-group">
							<label for="value" class="col-xs-4 control-label">名称:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="name" id="name"
									placeholder="请输入读片会名称!" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="label" class="col-xs-4 control-label">地址:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="address" id="address"
									placeholder="请输入读片会地址!" required="required">
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-xs-4 control-label">日期:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="dates" id="dates"
									placeholder="请输入读片会日期!" required="required" readonly >
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-xs-4 control-label">主办单位:</label>
							<div class="col-xs-5">
								<input type="text" class="form-control input-sm" name="orgName" id="orgName"
									placeholder="请输入读片会主办单位!" required="required">
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

<script src="${ctxStatic}/modules/caseLib/readFilm_list.js" ></script>