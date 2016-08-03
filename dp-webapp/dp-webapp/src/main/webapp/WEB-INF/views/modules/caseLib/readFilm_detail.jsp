<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<section class="content-header">
	<h1>&nbsp;&nbsp;云病理切片库</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 病理库</a></li>
		<li><a href="javascript:onclikMenu();"><i class="fa fa-dashboard"></i>云病理切片库</a></li>
		<li class="active">读片会</li>
	</ol>
	<div class="col-xs-12" style="border-bottom: 1px solid #68aa08;margin-top:15px;margin-left:15px;"></div>
</section>

<!-- Main content -->
<section class="content">
		<div class="col-xs-12">
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content">
				<h2>当前读片会：${readFilm.name }</h2>
			</div>
			<!-- /.内容 end -->
		</div>
		</div>
		</div>
	
		<div class="col-xs-12">
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content" id="ex1">
					<div class="col-xs-3">
						<img src='http://image.upathology.com/decodetile/getqiepianimg.aspx?filename=130194-5_20160715081955&type=2' alt='' height="250px;" width='100%' />
					</div>
					<div class="col-xs-9">
						<dl class="dl-horizontal"><dt>读片会名称:</dt><dd>${readFilm.name}</dd></dl>
						<dl class="dl-horizontal"><dt>读片会地点:</dt><dd>${readFilm.address }</dd></dl>
						<dl class="dl-horizontal"><dt>读片会时间:</dt><dd><fmt:formatDate value="${readFilm.dates }" pattern="yyyy-MM-dd"/></dd></dl>
						<dl class="dl-horizontal"><dt>主办单位:</dt><dd>${readFilm.orgName }</dd></dl>
						<dl class="dl-horizontal"><dt>介绍:</dt><dd>${readFilm.remarks }</dd></dl>
					</div>
			</div>
			<!-- /.内容 end -->
		</div>
		</div>
		</div>
		<!-- /.col -->
	
		<!-- 左边留言 -->
		<div class="col-xs-12">
			<!-- 头部 start-->
		 	<ul class="nav nav-tabs navbar-default" id="myTab">
				<li class="active"><a data-toggle="tab" href="#ex2">读片会列表</a></li>
			</ul>
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content">
				<div id="ex2" class="tab-pane fade active in center-block">
				<c:forEach items="${list }" var="item">
					<div class="col-xs-3" style="height:165px;">
						<table>
							<tr>
								<td rowspan="4">
									<a href='${ctx }/cons/lib/caseLib/detail?id=${item.caseLib.id }'>
									<img alt="" src="${item.overview }" width="95%" height="162px"/>
									</a>
								</td>
								<td>病理号：${item.caseLib.id }</td>
							</tr>
							<tr>
								<td>性别：${item.caseLib.gender=='m'?'男':item.caseLib.gender=='f'?'女':'不确定' }</td>
							</tr>
							<tr>
								<td>年龄：${item.caseLib.aget }</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
				</c:forEach>

				</div>
			</div>
			<!-- /.内容 end -->
		</div>
		</div>
		</div>
	
	
</section>
<script type="text/javascript">
	function onclikMenu(){
		showContent('/dp-webapp/cons/lib/caseLib/index');
	}
</script>
<style type="text/css">
#ex2 table{
	height:120px;
	width:100%;
	border:0px;
	line-height: 30px;
	margin-top:5px;
	padding:5px;
}
#ex2 table td{
	width:50%;
}

#ex1 img{
	padding: 5px;
    border: 1px solid #68aa08;
    border-radius: 5px;
}
#ex1 dl{
	margin-left:-100px;
}
#ex1 dt{
    color: #666;
}

</style>

