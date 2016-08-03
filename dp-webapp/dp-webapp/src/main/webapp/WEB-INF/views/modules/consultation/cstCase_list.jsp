<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<section class="content-header">
	<h1>未诊断</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 会诊</a></li>
		<li class="active">未诊断</li>
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
	      			<form id="cstCase_form" action="${ctx }/cons/cstCase/list" method="post">
	      				<input type="hidden" name="pageSize" id="pageSize" value="10">
	      				<input type="hidden" name="pageNo" id="pageNo" value="1">
	      				<input type="hidden" name="orderBy" id="orderBy" value="create_date desc">
	      				<div style="width:100%;">
	      					<div class="col-xs-5">
								<div class="form-group">
									<label for="nameSearch" class="col-xs-4 control-label padding">病人姓名：</label>
									<div class="col-xs-8 padding">
										<input type="text" class="form-control input-sm"  name="name" id="name">
									</div>
								</div>
							</div>
							<div class="col-xs-5">
								<div class="form-group">
									<label for="statusSearch" class="col-xs-4 control-label padding">状态：</label>
									<div class="col-xs-8 padding">
									<dict:select defaultValue="true" name="state"
															id="statusSearch" dictName="cstCase_status" cssClass="input-sm" />
									</div>
								</div>
							</div>
							<span style="float:right;margin-right:30px;">
								<input class="btn btn-primary" type="button" onclick="queryList();" value="查询" >
								<input class="btn btn-primary" type="reset" value="重置" >
							</span>
						</div>
				   </form>
      			</div>
      		 </div>
			<!-- 搜索 end-->
			<!-- TABLE: LATEST ORDERS -->
			<div class="box minWidthM">
					<div class="box-body table-responsive">
						<table id="cstCase_list" class="table table-bordered table-hover" style="width:100%;">
							<thead>
								<tr>
									<th>编号</th>
									<th>病理号</th>
									<th>病人姓名</th>
									<th>性别</th>
									<th>年龄</th>
									<th>年龄单位</th>
									<th>子公司</th>
									<th>状态</th>
									<th>申请时间</th>
									<th width="105px">剩余诊断时间</th>
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

<script src="${ctxStatic}/modules/consultation/cstCase_list.js" ></script>