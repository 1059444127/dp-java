<%@page import="com.kingmed.dp.module.sys.entity.Log"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<style type='text/css'>
th {
	text-align:center;
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
      <div class="box box-solid minWidthM" style="margin-bottom: 4px;">
      	<div class="box-body">
      	<form id="searchForm" action="${ctx }/admin/log/list" method="post">
      		<input type="hidden" name="pageSize" id="pageSize" value="10">
			<input type="hidden" name="pageNo" id="pageNo" value="1">
			<input type="hidden" name="orderBy" id="orderBy" value="create_date desc">
	      	<input type="hidden" name="beginDate" id="beginDate">	
	      	<input type="hidden" name="endDate" id="endDate">
          <div class="row">
          	<div class="col-xs-5">
            	<div class="form-group">
					<label class="col-xs-4 control-label padding">标题：</label>
					<div class="col-xs-8 padding">
						<input type="text" class="form-control  input-sm" name="title" id="title" >
					</div>
				</div>
           	</div> 
           	<div class="col-xs-5">
           		<div class="form-group">
					<label class="col-xs-4 control-label padding">类型：</label>
					<div class="col-xs-8 padding">
						<dict:select name="type" id="type" dictName="LOG_TYPE" defaultValue="true" cssClass="form-control input-sm" />
						<!-- <select class="form-control" name="type" id="type">
							<option value="">请选择</option>
							<option value="1">接入日志</option>
							<option value="2">错误日志</option>
						</select> -->
					</div>
				</div>
           	</div> 
           	<div class="col-xs-5">
           		<div class="form-group">
           		 	<!-- Date range -->
					<label class="col-xs-4 control-label padding">提交日期：</label>
					<div class="col-xs-8 input-group padding">
						<input id="datepicker" class="form-control  input-sm" type="text" readonly="readonly">
					</div>
				</div>
           	</div> 
           
           	<div class="col-xs-5">
           		<div class="form-group">
					<label class="col-xs-4 control-label padding">异常信息：</label>
					<div class="col-xs-8 padding">
						<input type="text" class="form-control input-sm" name="exception" id="exception">
					</div>
				</div>
           	</div> 
           	</div>
           	<div class="row">
           <span style="float: right; margin-right: 30px;">
           		<input type="button" class="btn btn-primary" style="margin-right: 20px;" onclick="searchLog()" value="查询">
           		<input type="button" class="btn btn-primary" onclick="resetSearch()" value="重置">
          	</span>
           </div>
         </form>
       </div><!-- /.box-header -->
     </div>
      <div class="box  minWidthM">
        <!-- <div class="box-header">
          <h3 class="box-title">Data Table With Full Features</h3>
        </div> --><!-- /.box-header -->
        <div class="box-body">
          <table id="logTable" class="table table-bordered table-hover" style="width:100%;">
            <thead>
              <tr>
              	<th width="1%">id</th>
                <th width="10%">日志类型</th>
                <th width="20%">日志标题</th>
                <th width="12%">创建者</th>
                <th width="27%">创建时间</th>
                <th width="30%">操作url</th>
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
<script src="${ctxStatic}/modules/sys/log_list.js" ></script>
