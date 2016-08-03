<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>  

<section class="content-header">
	<h1>云病理切片库</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 病例库</a></li>
		<li><a href="javascript:void(0)"><i class="fa fa-dashboard"></i>云病理切片库</a></li>
		<li class="active">切片明细</li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<!-- Info boxes -->
	
	<!-- Main row -->
	<div class="col-xs-9 pull-left">
		<!-- Left col -->
		<div class="col-xs-12">
		<!-- 头部 start-->
			 	<ul class="nav nav-tabs navbar-default" id="myTab">
					<li class="active"><a data-toggle="tab" href="#ex1">病例详情</a></li>
				</ul>
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content" id="slidDetail1">
				<div id="ex1" class="tab-pane fade active in center-block">
					<div class="col-xs-4">
						<a href="${ctx }/cons/lib/caseLibSlide/picture?id=${caseLib.caseLibSlide.id}" target="_blank">
						<img src='${caseLib.caseLibSlide.overview }' alt='' width='100%' height="300"/></a>
						<br/><!-- 分享模块 -->
						<div class="bdsharebuttonbox" data-tag="share_${caseLib.id}">
							<a class="bds_more" href="#" data-cmd="more" data-id="${caseLib.id}"></a>
							<a class="bds_qzone" href="#" data-cmd="qzone" data-id="${caseLib.id}"></a>
							<a class="bds_tsina" href="#" data-cmd="tsina" data-id="${caseLib.id}"></a>
							<a class="bds_tqq" href="#" data-cmd="tqq" data-id="${caseLib.id}"></a>
							<a class="bds_renren" href="#" data-cmd="renren" data-id="${caseLib.id}"></a>
							<a class="bds_weixin" href="#" data-cmd="weixin" data-id="${caseLib.id}"></a>
						</div>
					</div>
					<div class="col-xs-8">
						<dl class="dl-horizontal"><dt>患者详情:</dt><dd>${caseLib.gender=='m'?'男':caseLib.gender=='f'?'女':'不确定' },${caseLib.aget }${caseLib.ageUnit },${caseLib.cliDiag }</dd></dl>
						<dl class="dl-horizontal"><dt>取材部位:</dt><dd>${caseLib.opeFind }</dd></dl>
						<dl class="dl-horizontal"><dt>临床资料:</dt><dd>${caseLib.cliData }</dd></dl>
						<dl class="dl-horizontal"><dt>大体所见:</dt><dd>${caseLib.genDesc }</dd></dl>
						<dl class="dl-horizontal"><dt>免疫组化:</dt><dd></dd></dl>
						<dl class="dl-horizontal"><dt>备注:</dt><dd>${caseLib.remarks }</dd></dl>
						<dl class="dl-horizontal"><dt>读片意见:</dt><dd></dd></dl>
						<dl class="dl-horizontal"><dt>浏览次数:</dt><dd>${number }</dd></dl>
					</div>
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
				<li class="active"><a data-toggle="tab" href="#ex2">留言讨论</a></li>
			</ul>
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content" id="slidDetail2">
				<div id="ex2" class="tab-pane fade active in center-block">
					<div class="col-xs-12">
						<div id="messageContent"><div class="row"></div></div>
						<div class="row"><textarea rows="3" cols="100%" id="releaseContent" placeholder="在这里我们言论自由，尽情的说吧..."></textarea></div>
						<div class="row"><button class="btn btn-primary" onclick="release()">发布</button></div>
					</div>
				</div>
			</div>
			<!-- /.内容 end -->
		</div>
		</div>
		</div>
	</div>
	
	<!-- 右边最近访客 -->
	<div class="col-xs-3 pull-left">
		<!-- 右边最近访客 -->
		<div class="col-xs-12">
		<!-- 头部 start-->
			 	<ul class="nav nav-tabs navbar-default" id="myTab">
					<li class="active"><a data-toggle="tab" href="#ex3">最近访客</a></li>
				</ul>
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content" id="slidDetail3">
				<div id="ex3" class="tab-pane fade active in center-block">
					<div class="col-xs-12">
						<c:forEach items="${recentVisits }" var="item">
							<div class="row">
								<a href="javascript:showUserInfo('${item.userId }','${item.id }')" title="${item.ip }"  
								      data-container="body" data-toggle="popover" data-placement="left" data-html="true"
								      data-content="<div style='width:200px;height:100px;padding:5px;line-height:30px;' id='${item.id }'>姓&nbsp;&nbsp;名： &nbsp;&nbsp;<br/>登录名：&nbsp;&nbsp;<br/>所属部门：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>"><span class="glyphicon glyphicon-user"></span>&nbsp;
								${item.ip } &nbsp;(<fmt:formatDate value="${item.createDate }" pattern="MM月dd日"/>)
								</a>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<!-- /.内容 end -->
		</div>
		</div>
		</div>
		
		<!-- 右边最近访问 -->
		<div class="col-xs-12">
		<!-- 头部 start-->
			 	<ul class="nav nav-tabs navbar-default" id="myTab">
					<li class="active"><a data-toggle="tab" href="#ex4">最近访问</a></li>
				</ul>
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content" id="slidDetail4">
				<div id="ex4" class="tab-pane fade active in center-block">
					<div class="col-xs-12" id="RVSlide">
					</div>
				</div>
			</div>
			<!-- /.内容 end -->
		</div>
		</div>
		</div>
	</div>
</section>
<input type="hidden" id="userId" value="<shiro:principal property='id'/>"/>
<script type="text/javascript">
var ctx = "${ctx }";
var slideId = '${caseLib.caseLibSlide.id}';
</script>
<script src="${ctxStatic}/modules/caseLib/slide_detail.js" ></script>
<style type="text/css">
#ex2 .row{
	border-bottom:1px dashed #333;
	line-height: 30px;
	margin-top:5px;
	padding:5px;
}
#ex3 .row{
	border-bottom:1px dashed #333;
	line-height: 30px;
	margin-top:5px;
	padding:5px;
}
#ex1 img{
	padding: 5px;
    border: 1px solid #68aa08;
    border-radius: 5px;
}
#ex4 .img_div{
	padding: 5px;
    border: 1px solid #68aa08;
    border-radius: 5px;
    margin-top:5px;
}
#ex1 dl{
	margin-left:-100px;
}
#ex1 dt{
    color: #666;
}
</style>

