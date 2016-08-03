<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<section class="content-header">
	<h1>云病理切片库</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 病例库</a></li>
		<li class="active">云病理切片库</li>
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
						<a href="javascript:void(0);" class="btn btn-primary btn-block margin-bottom">云病理数字切片库</a>
						<div class="box box-solid" style="height:90%;overflow-x:auto;overflow-y:auto;">
							<div>
								<ul id="readFilmTree" class="ztree"></ul>
								<ul id="MyTreearea" class="ztree" style="margin-top:-10px;"></ul>
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
				      			<form id="search_form" action="${ctx }/cons/lib/caseLib/index" method="post">
					      			<c:if test="${viewType==1 }">
						      			<input type="hidden" name="pageSize" id="pageSize" value="10">
					      			</c:if>
					      			<c:if test="${viewType!=1 }">
						      			<input type="hidden" name="pageSize" id="pageSize" value="8">
					      			</c:if>
				      				<input type="hidden" name="pageNo" id="pageNo" value="1">
				      				<input type="hidden" name="orderBy" id="orderBy" value="id desc">
				      				<input type="hidden" name="parentIds" id="parentIds">
				      				<div style="width:100%;">
				      					<div class="pull-left" style="width:150px;line-height:30px;">
											<div class="radio-inline">
											   <label for="R1"><input style="margin-top:10px;" type="radio" name="gender" id="R1"
											         value="m" checked> 男
											   </label>
											</div>
											<div class="radio-inline">
											   <label for="R2"><input style="margin-top:10px;" type="radio" name="gender" id="R2"
											         value="f">
											         	女
											   </label>
											</div>
<!-- 											<div class="radio-inline"> -->
<!-- 											   <label for="R3"><input style="margin-top:10px;" type="radio" name="gender" id="R3" -->
<!-- 											         value=""> -->
<!-- 											         	不确定 -->
<!-- 											   </label> -->
<!-- 											</div> -->
											<div class="radio-inline">
											   <label for="R4"><input checked style="margin-top:10px;" type="radio" name="gender" id="R4"
											         value="">
											         	全部
											   </label>
											</div>
										</div>
					      				<div class="col-xs-3 padding" style="width:250px;">
											<div class="form-group">
												<label for="nameSearch" class="col-xs-3 control-label padding" style="width:60px;">年龄：</label>
												<div class="col-xs-2 padding">
													<input type="text" class="form-control input-sm"  id="startAge" name="start_aget">
												</div>
												<div class="col-xs-2 padding" style="line-height: 30px;width:40px;">&nbsp;岁至&nbsp;</div>
												<div class="col-xs-2 padding">
													<input type="text" class="form-control input-sm"  id="endAge" name="end_aget">
												</div><div class="col-xs-1 padding" style="line-height: 30px;">&nbsp;岁</div>
											</div>
										</div>
										<div class="col-xs-4 padding">
											<div class="form-group">
												<label for="typeSearch" class="col-xs-3 control-label padding" style="width:80px;">关键词：</label>
												<div class="col-xs-8 padding">
													<input type="text" class="form-control input-sm"  id="content" name="content">
												</div>
											</div>
										</div>
										<span style="float:right;margin-right:30px;">
											<input class="btn btn-primary" type="button" onclick="queryList();" value="搜索" >
											<input class="btn btn-primary" type="button" onclick="queryClear()" value="全部切片" >
											<input class="btn btn-primary" type="button" onclick="querySort()" value="时间排序" >
											<input class="btn btn-primary" type="button" onclick="showList()" value="详细列表" >
											<input class="btn btn-primary" type="button" onclick="showOverView()" value="缩略图" >
										</span>
									</div>
							   </form>
			      			</div>
			      		 </div>
						<!-- 搜索 end-->
						<!-- TABLE: LATEST ORDERS -->
						<div class="box">
							<div class="box-body">
								<c:if test="${viewType==1 }">
									<table id="table_list"
										class="table  table-bordered table-hover minWidthM"
										style="width: 100%;text-align: center;">
										<thead>
											<tr>
												<th style="text-align: center;">序号</th>
												<th style="text-align: center;">部位</th>
												<th style="text-align: center;">缩略图</th>
												<th style="text-align: center;">简要病史</th>
												<th style="text-align: center;">医院名称</th>
											</tr>
										<thead>
										<tbody>

										</tbody>
									</table>
									<%@include file="/WEB-INF/views/include/page.jsp" %>
								</c:if>
								<c:if test="${viewType!=1 }">
									<div id="div_list" class="col-xs-12">
									</div>
									
									<div class="pull-left">
										每页&nbsp;<select id="changeNumber" class="selected" onchange="changeNumber(this)">
											<option value="8" selected>8</option>
											<option value="12">12</option>
											<option value="20">20</option>
											<option value="40">40</option>
											<option value="100">100</option>
										</select>&nbsp;条，第<span id="startNum"></span>至<span id="endNum"></span>条数据，总共<span id="totalCount"></span>条数据
									</div>
									<div id="myPageDiv" class="pull-right"></div>
								</c:if>
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

<!-- 获取ztree对象初始化data数据（将同级的treeNode转换成父子节点结构） -->
<div style="display: none;">
	<ul id="treeParent" class="ztree"></ul>
</div>
<script type="text/javascript">
	var viewType = '${viewType}';
</script>
<script src="${ctxStatic}/modules/caseLib/index.js" ></script>
<style type="text/css">

#div_list .row{
	height:30px;
	line-height:30px;
}
.sgre > div:hover {
  z-index: 1;
}

.sgre > div:hover > img {
  transform: scale(1.5, 1.5);
  transition: .3s transform;
}
</style>

