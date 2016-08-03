<%@ page language="java" pageEncoding="utf-8"%>
<%-- <%@ include file="/WEB-INF/views/include/head.jsp"%> --%>
<%-- <%@ include file="webs.html"%> --%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>金域病理</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=0.5,maximum-scale=4.0, user-scalable=yes" name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/bootstrap/css/bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/dist/css/AdminLTE.min.css">
<script src="${ctxStatic}/AdminLTE/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${ctxStatic}/AdminLTE/bootstrap/js/bootstrap.min.js"></script>

<link href="${ctxStatic}/AdminLTE/mystatic/web/skins/uimaker/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/AdminLTE/mystatic/web/skins/uimaker/css/select.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/jquery-latest.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/common/common.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/common/json2.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/common/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/jquery.Validform.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/jquery.autocomplete.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/jquery.form.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/jquery-jtemplates.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/select2/js/select2.min.js"></script>

<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/fusioncharts/fusioncharts.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/fusioncharts/themes/fusioncharts.theme.fint.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/lhgcore.lhgdialog.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/portlets/jquery-ui.js"></script>
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/portlets/portlets-ext.js"></script>
<link href="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/css/jquery.treetable.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/portlets/jquery-ui.theme.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/portlets/jquery-ui.structure.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/css/jquery.treetable.theme.default.css" rel="stylesheet" type="text/css" />
<link href="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/portlets/portlets.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctxStatic}/AdminLTE/mystatic/web/scripts/plugins/treetable/jquery.treetable.js"></script>
<script type="text/javascript">
	var num = 0;
	var is_second = 0;
</script>

<style type="text/css">
body div a{
	font-size:16px;
	font-family: "Segoe UI","Lucida Grande",Helvetica,Arial,"Microsoft YaHei",FreeSans,Arimo,"Droid Sans","wenquanyi micro hei","Hiragino Sans GB","Hiragino Sans GB W3",Arial,sans-serif;
}
.navigates{
    background: #58a5cf url("../img/bg_all_01.jpg") no-repeat top left;
}
.navigates table{
	text-align: center;
}
.navigates a{
	font-size:12px;
	color:white;
}
.font_golden{color:#b3871c}
.font_blue{color:#4785b5}
.row_100{width:100px;}
.row_150{width:150px;}
.size_18{font-size:18px;}
.right_border{
	border-right: white 1px solid;
}
.my_top{
	margin-top: 18px;
}
.more{
	float: right;
    margin-top: 10px;
    color: #4d4a4a;
    padding-right: 20px;
    font-size: 12px;
}
.clear{clear: both;}
.index-title{
	    border-bottom: 2px solid #3099d8;
}
.index-title h5{
	cursor: pointer;
    float: left;
    color: #4d4a4a;
    font-size: 18px;
    padding-bottom: 5px;
    }
.floor a{
	margin-left: 10px;
	color:orange;
	}
</style>
</head>
<body class="container" style="width:1024px;">
<div class="row">
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
					<div class="row">
							<a href="#" class="pull-left"><img alt="logo" src="${ctxStatic }/AdminLTE/mystatic/web/images/logo.png"></a>
							<div class="col-lg-4" style="margin-left:10%;margin-top: 25px;">
					            <div class="input-group">
					               <input type="text" class="form-control" placeholder="输入关键字">
					               <span class="input-group-btn">
					                  <button class="btn btn-primary"  style="width:100px;height:34px;" type="button">
					                    	 搜索
					                  </button>
					               </span>
					            </div><!-- /input-group -->
					         </div><!-- /.col-lg-6 -->
					         <div style="float:right;margin-top: 35px;">
						         <a href="javascript:void(0)" class="font_golden">问题及反馈</a> &nbsp;&nbsp;
						         <a href="javascript:void(0)" class="font_blue">登陆</a> | <a href="javascript:void(0)"  class="font_golden">注册</a>
					         </div>
					</div>
						<div style="z-index:-1;position:absolute;left:0px;top:78px;height:61px;width:100%;" class="navigates">
						</div>
						<div class="row navigates my_top" style="z-index:2;">
							<div style="margin-top: 5px;">
							<ul class="list-inline" style="float:left;line-height: 22px;width:29%;">
								<li style="border-right: 1px solid #93cae6;width:33.33%;"><!-- <span class="glyphicon glyphicon-home" style="color:white;font-size:28px;"></span> --><a href="#" style="font-size: 18px;">病理<br/>资讯</a></li>
								<li style="width:33.33%;"><a href="#">政策公告</a><br/><a href="#">会议活动</a></li>
								<li style="width:30%;"><a href="#">行业资讯</a><br/><a href="#">人才招聘</a></li>
							</ul>
							<ul class="list-inline" style="float:left;line-height: 22px;width:48%;">
								<li style="border-right: 1px solid #93cae6;width:18%;height:44px;float:left;"><a href="#" style="font-size: 18px;;">医学<br/>资源</a></li>
								<li style="width:18%;"><a href="#">直播课堂</a><br/><a href="#">视频讲座</a></li>
								<li style="width:18%;"><a href="#">专业书刊</a><br/><a href="#">病例分析</a></li>
								<li style="width:18%;"><a href="#">分子病理</a><br/><a href="#">名医名篇</a></li>
								<li style="width:18%;"><a href="#">数字读片</a><br/><a href="#">名医专家</a></li>
							</ul>
							<ul class="list-inline" style="float:left;line-height: 22px;width:10%;">
								<li><a href="#" style="font-size: 18px;;">病理<br/>论坛</a></li>
							</ul>
							<ul class="list-inline" style="float:left;line-height: 22px;width:13%;">
								<li><a href="#" style="font-size: 18px;;">远程病理<br/>会诊平台</a></li>
							</ul>
							</div>
						</div>
				</div>
			</div>
					<div class="row-fluid my_top">
						<div class="span12">
							<div class="row">
								<img alt="head" src="${ctxStatic }/AdminLTE/mystatic/file/20160708095828787.png">
							
							<div class="my_top" style="float:left;width:650px;">
								<div id="myCarousel" class="carousel slide">
								   <!-- 轮播（Carousel）指标 -->
								   <ol class="carousel-indicators">
								      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
								      <li data-target="#myCarousel" data-slide-to="1"></li>
								      <li data-target="#myCarousel" data-slide-to="2"></li>
								      <li data-target="#myCarousel" data-slide-to="3"></li>
								   </ol>   
								   <!-- 轮播（Carousel）项目 -->
								   <div class="carousel-inner">
								      <div class="item active">
									      <div style="width:650px;height:310px;background: url('${ctxStatic }/AdminLTE/mystatic/file/thumb_650_310_20160708034122711.png');">
									      	<div class="bg-primary" style="position:absolute;bottom:0;width:100%;height:33px;line-height: 33px;font-size: 16px;">thumb_650_310_20160708034122711</div>
									      </div>
								      </div>
								      <div class="item">
									      <div style="width:650px;height:310px;background: url('${ctxStatic }/AdminLTE/mystatic/file/thumb_650_310_20160603112459734.png');">
									      	<div class="bg-primary" style="position:absolute;bottom:0;width:100%;height:33px;line-height: 33px;font-size: 16px;">thumb_650_310_20160603112459734</div>
									      </div>
								      </div>
								      <div class="item">
									      <div style="width:650px;height:310px;background: url('${ctxStatic }/AdminLTE/mystatic/file/thumb_650_310_20160701095907205.png');">
									      	<div class="bg-primary" style="position:absolute;bottom:0;width:100%;height:33px;line-height: 33px;font-size: 16px;">thumb_650_310_20160701095907205</div>
									      </div>
								      </div>
								      <div class="item">
								      	<div style="width:650px;height:310px;background: url('${ctxStatic }/AdminLTE/mystatic/file/20160513110353663.png');">
								      		<div class="bg-primary" style="position:absolute;bottom:0;width:100%;height:33px;line-height: 33px;font-size: 16px;">20160513110353663</div>
								      	</div>
								      </div>
								   </div>
								   <!-- 轮播（Carousel）导航 -->
								   <a class="carousel-control left" href="#myCarousel" 
								      data-slide="prev">&lsaquo;</a>
								   <a class="carousel-control right" href="#myCarousel" 
								      data-slide="next">&rsaquo;</a>
								</div> 
							</div>
							<div class="my_top" style="float:left;width:348px;height:310px;margin-left:25px;background: url('${ctxStatic }/AdminLTE/mystatic/file/bg_focus_1.jpg');">
								<h2>头条新闻</h2>
								<div>
									<a href="#" target="_blank" title="远程病理 病理诊断的新模式">
										<h3>■ 远程病理 病理诊断的新模式</h3>
										<p>病理学被称为“医学之本”，尤其在肿瘤的诊断中更是有举足轻重的地位。医疗资源的分布不均，利用“互联网+”，充分发展基于互联网的医疗卫...</p>
									</a>
									<ul class="list-group">
										
									</ul>
								</div>
								
							</div>
						  </div>
						  
						  <div class="row my_top">
						  	<div class="dataGrid" style="padding:0px;">
								 <form action="${ctx}/plat/common/saveUserDevPortlets.action" enctype="multipart/form-data"  method="post" id="opForm" name="opForm">
								
								    <input type="hidden" name="proId" id="proId" value="${proId}" />
								    <input type="hidden" name="columnId" id="columnId" value="${columnId}" />
								    <input type="hidden" name="columnData" id="columnData"  />
								    <input type="hidden" name="num" id="num"  />
								</form>
								
								<table cellspacing="0" id="columns" style="width: 100%;table-layout: fixed;margin:auto 0;" >
									<tr >
										<c:forEach items="${columnList }" var ="columnMap">
											<c:set var="columnId" value='${columnMap["id"]}' />
											<c:set var="columnStyle" value='${columnMap["style"]}' />
											
											<!-- 修改宽度超过100%换行 -->
											<script type="text/javascript">
												var value = '${columnMap["style"]}';
												num += parseInt(value.substring(6));
												if(num>100&&is_second == 0){
													document.write("</tr><tr><td style='padding:0px;' colspan='2'><table style=\"width:100%;margin:auto 0;\"><tr>");
													num = parseInt(value.substring(6));
													is_second = 1;
												}else if(num>100&&is_second == 1){
													document.write("</tr></table></td><tr><td style='padding:0px;' colspan='2'><table style=\"width:100%;margin:auto 0;\"><tr>");
													num = parseInt(value.substring(6));
													is_second = 0;
												}
											</script>
											
											<td  class="columns" style="${columnStyle}" id='${columnId }' >
													<c:forEach items='${rowMapList[columnId] }' var="rowMap">
														<c:set var="rowMapRef" value='${rowMap["ref"]}' />
														<c:set var="rowcount" value='${rowMap["rowcount"] }' />
														<c:set var="colcount" value='${rowMap["colcount"] }' />
														<c:set var="sfyx" value='${rowMap["sfyx"]}' />
														<c:set var="movable" value='${rowMap["movable"]}' />
														<c:set var="closed" value='${rowMap["closed"]}' />
														<c:set var="show" value='${rowMap["show"]}' />
														<c:set var="resultVal" value='${resultMap[rowMapRef]}' />
														<c:set var="divStyle" value='${resultVal["style"]}' />
														<c:choose>
															<c:when  test="${sfyx==1&&true==show}">
																<div rowcount='${rowcount}' style="${divStyle}" colcount='${colcount}' class='${false==movable?"portlet2 portlet2-state-disabled":"portlet"  }' id='${rowMapRef }' >
																	 <div class="listtitle"  >
																	 <span class="more1">
																	 <a>更多>></a>
<%-- 																	 <c:if test="${true==closed  }"> --%>
<%-- 																	 	<a   onclick="removeRow('${rowMapRef }')"  >[隐藏]</a> --%>
<%-- 																	 </c:if> --%>
																	 </span>
																			 ${resultVal["title"]}
																	 </div>
															    	 <div id='mslineContainer_${rowMapRef}'>
															    	 	<c:choose>
															    	 		<c:when test='${resultVal["method"] eq "AJAX"}'>
															    	 			<script type="text/javascript">
																	    	 		$(document).ready(function(){
																	    	 			var url = '${ctx}${resultVal["url"]}';
																	    	 			$.get(url,{},function(html){
																	    	 				$("#mslineContainer_${rowMapRef}").html(html);
																	    	 			});
																	    	 		});
								 									    	 	</script>
															    	 		</c:when>
								<%-- 							    	 		<c:when test='${resultVal["method"] eq "INCLUDE"}'> --%>
								<%-- 							    	 			<jsp:include page='${resultVal["url"]  }'></jsp:include>  --%>
								<%-- 							    	 		</c:when> --%>
															    	 	</c:choose>
															    	 
															    	 </div> 
																</div>
															</c:when>
															<c:otherwise>
														<%-- 		<c:if test="${fn:contains(rowMapRef, '_showKK_')}">
																	<c:set var="rowMapPid" value="${fn:split(rowMapRef, '_')[0]}"></c:set>
																	<div followid="${rowMapPid }" id="${rowMapRef }"  style="height:100px;margin-bottom: 8px;" class="portlet2-state-disabled"  >
																	</div>
																</c:if> --%>
															</c:otherwise>
														</c:choose>
								
													</c:forEach>
											</td>
										</c:forEach>	
									</tr>
								
									</table><!-- /#columns -->
									</div>
						  </div>
						  
						</div>
					</div>
					<div class="row-fluid">
						<div class="span12">
							<div>
								<h4><a>合作伙伴</a></h4>
								<ul class="list-inline">
									<li><img alt="" src="${ctxStatic }/AdminLTE/mystatic/file/free_partners_1.jpg"></li>
									<li><img alt="" src="${ctxStatic }/AdminLTE/mystatic/file/free_partners_2.jpg"></li>
									<li><img alt="" src="${ctxStatic }/AdminLTE/mystatic/file/free_partners_3.jpg"></li>
									<li><img alt="" src="${ctxStatic }/AdminLTE/mystatic/file/free_partners_4.jpg"></li>
									<li><img alt="" src="${ctxStatic }/AdminLTE/mystatic/file/free_partners_5.jpg"></li>
								</ul>
							</div>
							<div class="floor">
								 <center>
           						 <span>友情链接:</span>
                                    <a href="http://www.cmacsp.org/cn/" target="_blank">中华医学会病理学分会</a> 
                                                <a href="http://www.labweb.cn" target="_blank">中华检验医学网</a> 
                                                <a href="http://bbs.91360.com/" target="_blank">病理论坛</a> 
                                                <a href="http://hao.91360.com" target="_blank">病理网址导航</a> 
                                                <a href="http://bbs.xctmr.com" target="_blank">影像园</a> 
                                                <a href="http://www.ccmtv.cn" target="_blank">医学视频网</a> 
                                                <a href="http://www.yigoonet.com/" target="_blank">医谷</a> 
                                                <a href="http://www.91360.com" target="_blank">智慧病理网</a> 
                                                <a href="http://www.labdd.com/" target="_blank">检验地带网</a> 
                                                <a href="http://h.91360.com/" target="_blank">数字病理远程会诊</a> 
                                                <a href="http://www.nhfpc.gov.cn/" target="_blank">卫计委信息中心</a> 
                                                <a href="http://www.caca.org.cn/" target="_blank">中国抗癌学会</a> 
                                                <a href="http://qp.91360.com" target="_blank">数字切片库</a> 
                                                <a href="http://www.g-medon.com/" target="_blank">环球医学</a> 
                                                <a href="http://h.91360.com" target="_blank">远程病理会诊平台</a> 
                                                <a href="http://www.f61.com" target="_blank">儿科</a> 
                                                <a href="http://bingli.iiyi.com" target="_blank">病例中心</a> 
                                                <a href="http://ysk.99.com.cn" target="_blank">医生大全</a> 
                                                <a href="http://bbs.med126.com/" target="_blank">医学论坛</a> 
                                                <a href="http://www.yayinchuxue.com" target="_blank">牙龈出血</a> 
                                                <a href="http://www.nec.cc" target="_blank">询医健康网</a> 
                                                <a href="http://www.lunyiwang.net/" target="_blank">论医网</a> 
                                                <a href="http://www.91ylxxw.com" target="_blank">医疗信息网</a> 
                                                <a href="http://www.meiyujiankang.com" target="_blank">远程会诊</a> 
                                                <a href="http://huizhen.999120.net/" target="_blank">导医远程会诊  </a> 
                                                <a href="http://www.yaofangwang.com/yao/" target="_blank">药品大全</a> 
                                                <a href="http://zl.hxyjw.com/  " target="_blank">医界名家</a> 
                                                <a href="http://www.yynet.cn" target="_blank">上海医元网</a> 
                                                <a href="http://www.bozhong.com" target="_blank">播种网</a> 
                                                <a href="http://www.iivd.net/" target="_blank">小桔灯网</a> 
                                                <a href="http://jxl.91360.com" target="_blank">纪小龙切片库</a> 
                                                <a href="http://www.mypharma.com" target="_blank">医药网</a> 
                                                <a href="http://www.med999.net/ " target="_blank">医家园</a> 
                                                <a href="http://www.jjkk.org " target="_blank">健健康康网</a> 
                                                <a href="http://ask.cn939.com" target="_blank">中医咨询</a> 
                                </center>
								
							</div>
						</div>
					</div>
			
	</div>
</div>

<script type="text/javascript">
//统一进行门户拖动效果
$(document).ready(function(){
	$( "#menuid" ).menu();
	$(".columns").sortable({
		connectWith: ".columns",
		handle: ".listtitle",
		cancel: ".portlet2-state-disabled",
		placeholder: "portlet-placeholder ui-corner-all",
		start:function(event,ui){
			startMove(event,ui);
		},
		stop:function(event,ui){
			stopMove(event,ui);
		}
	});
	$(".portlet,.portlet2").resizable({
		autoHide:  false,
		handles: "e",
		grid: [50, 0] ,
		distance:50,
		stop:function(event,ui){
			stopresize(event,ui);
		},
		resize:function(event,ui){
			divresize(event,ui);
		}
	});
	$(".portlet,.portlet2").each(function(i,v){
		var colcount=$(v).attr("colcount");
		var rowcount=$(v).attr("rowcount");
		if(1 < colcount || 1 < rowcount ){
			loadDiv($(v),colcount,rowcount);
		}
	});
});
function saverow(){
	var data="";
	$("#columns").children().children().children().each(function(i,v){
		var id = $(v).attr("id");
		var row="";
		$(v).children().each(function(j,k){
			var rowVal = $(k).attr("id");
			var colcount = $(k).attr("colcount");
			if(!colcount){
				colcount = 1;
			}
			var rowcount = $(k).attr("rowcount");
			if(!rowcount){
				rowcount = 1;
			}
			row+=rowVal+"@"+colcount+"@"+rowcount+"@1#";
		});
		data+=",{'column':'"+id+"','row':'"+row+"'}";
	});
	var colData="["+data.substring(1)+"]";
	$("#columnData").val(colData);
	$("#opForm").submit();
}
function showInfo(obj){
	 $.dialog.alert(obj);
}

function toDefault(){
	$("#opForm").attr("action","${ctx}/plat/common/todefaultUserPortlets.action");
	$("#opForm").submit();
}
function switchToNext(obj){
	$(obj).css("display","none");
	$(obj).next().css("display","");
	$(obj).parent().parent().attr("ishow","1");
}
function switchToPrv(obj){
	$(obj).css("display","none");
	$(obj).parent().parent().attr("ishow","0");
	$(obj).prev().css("display","");
}
function removeRow(obj){
	var row=$("#"+obj);
	$("#columnData").val(row.attr("id"));
	$.post("${ctx}/plat/common/hiddenUserPortlets.action",$("#opForm").serialize(),function(result){
		if(result.MSG=="TRUE"){
			row.remove();
			$("div[id^='"+obj+"_showKK_']").each(function(i,v){
				$(v).remove();
			});
		}else{
			$.dialog.alert('操作失败!');
		}
	},"json");
	
}



function openWin(urls){
	var baseUrl =urls;
	var api=$.dialog({
		lock: true, 
		max: false, 
		min: false ,
		title:"模块列表",
		width:'700px',
		height:'420px',
		content: 'url:'+baseUrl,
		ok:function(){
			var columnId=$(this.iframe)[0].contentWindow.getColumnId();
			var data=$(this.iframe)[0].contentWindow.checkObjs();
			if(data!=""){
				$("#columnData").val(data.substring(1));
				$("#columnId").val(columnId);
				$("#opForm").attr("action","${ctx}/plat/common/updateUserPortlets.action");
				$("#opForm").submit();
			}
	    },
		okVal:"确定",
		close :	function(){
			//更新下消息数据
		}
	});

}



</script>
</body>
</html>