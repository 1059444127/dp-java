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
							<div class="col-lg-3" style="margin-left:15%;margin-top: 25px;">
					            <div class="input-group">
					               <input type="text" class="form-control" placeholder="输入关键字">
					               <span class="input-group-btn">
					                  <button class="btn btn-primary" type="button">
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
						  	<div style="float:left;width:68%;height:500px;">
						  		<div class="index-title">
						  			<a href=""><h5>标题</h5></a>
						  			<a href=""><span class="more">更多>></span></a>
						  			<div class="clear"></div>
						  		</div>
						  		<div>内容</div>
						  	</div>
						  	<div style="float:left;width:30%;height:500px;margin-left: 2%;">
						  		<div class="index-title">
						  			<a href=""><h5>标题</h5></a>
						  			<a href=""><span class="more">更多>></span></a>
						  			<div class="clear"></div>
						  		</div>
						  		<div>内容</div>
						  	</div>
						  </div>
						  
						  <div class="row my_top">
						  	<div style="float:left;width:68%;height:500px;">
						  		<div class="index-title">
						  			<a href=""><h5>标题</h5></a>
						  			<a href=""><span class="more">更多>></span></a>
						  			<div class="clear"></div>
						  		</div>
						  		<div>内容</div>
						  	</div>
						  	<div style="float:left;width:30%;height:500px;margin-left: 2%;">
						  		<div class="index-title">
						  			<a href=""><h5>标题</h5></a>
						  			<a href=""><span class="more">更多>></span></a>
						  			<div class="clear"></div>
						  		</div>
						  		<div>内容</div>
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
</body>
</html>