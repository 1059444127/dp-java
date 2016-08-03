<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<section class="content-header bordered">
	<strong>未诊断</strong>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i> 会诊</a></li>
		<li class="active">未诊断</li>
	</ol>
</section>
<ul class="nav nav-list"> 
     <li class="divider"></li>  
</ul>
<!-- Main content -->
<section class="content">
	<!-- Info boxes -->

	<!-- Main row -->
	<div class="row">
		<!-- Left col -->
		<div class="col-xs-12">
		<!-- 头部 start-->
			 	<ul class="nav nav-tabs navbar-default" id="myTab">
					<li class="active"><a data-toggle="tab" href="#ex1">病理信息</a></li>
					<li><a data-toggle="tab" href="#ex2" onclick="showAddProject()">加做项目</a></li>
					<li><a data-toggle="tab" href="#ex3" onclick="showLeave()">反馈信息</a></li>
					<li class="pull-right"><a href="javascript:void(0)" onclick="showReportImg()">预览</a></li>
<!-- 					<li class="pull-right"><a href="#">返回列表</a></li> -->
					<li class="pull-right"><a href="javascript:void(0)" onclick="saveCstDia()">保存</a></li>
					<li class="pull-right"><a href="javascript:void(0)" onclick="sendSubmit()">提交最终诊断</a></li>
				</ul>
			<!-- 头部 end-->
			<!-- 内容start -->
			<div class="box" style="border-top:0px;">
			<div class="box-body">
			<div class="tab-content" id="myTabContent">
			<input type="hidden" name="id" id="id" value="${cstCase.id }"/>
				<div id="ex1" class="tab-pane fade active in center-block">
					<fieldset style="padding:.15em .325em .5em;margin:0 2px;border:1px solid silver"> 
						<legend class="text-warning" style="padding:.2em;border:0;width:auto">基本信息</legend>
						<div class="row center-block">
							<div class="col-xs-4 text-center">
								<dl>
									<dt class="bg-info input-sm col-xs-5">病理号：</dt>
									<dd class="input-sm">${cstCase.caseLib.id }</dd>
								</dl>
							</div>
							<div class="col-xs-4 text-center">
								<dl>
									 <dt class="bg-info input-sm col-xs-5">性名：</dt>
									 <dd class="input-sm">${cstCase.name }</dd>
								</dl>
							</div>
							<div class="col-xs-4 text-center">
								<dl>
									 <dt class="bg-info input-sm col-xs-5">切片数量：</dt>
									 <dd class="input-sm">${cstCase.slideNum} </dd>
								</dl>
							</div>
						</div>
						<div class="row center-block">
							<div class="col-xs-4 text-center">
								<dl>
									 <dt class="bg-info input-sm col-xs-5">性别：</dt>
									 <dd class="input-sm">${cstCase.gender }</dd>
								</dl>
							</div>
							<div class="col-xs-4 text-center">
								<dl>
									 <dt class="bg-info input-sm col-xs-5">年龄：</dt>
									 <dd class="input-sm">${cstCase.age }</dd>
								</dl>
							</div>
							<div class="col-xs-4 text-center">
								<dl>
									 <dt class="bg-info input-sm col-xs-5">切片：</dt>
									 <dd class="input-sm"><a class="btn btn-xs" href="${ctx }/cons/cstCase/picture?caseId=${cstCase.caseLib.id}&typed=0" target="_blank"><img alt="切片" src="${ctxStatic}/AdminLTE/mystatic/images/slice_button.png"></a></dd>
								</dl>
							</div>
						</div>
					</fieldset>
					<fieldset style="padding:.15em .325em .5em;margin:0 2px;border:1px solid silver"> 
						<legend class="text-warning" style="padding:.2em;border:0;width:auto">基本病史信息</legend>
						<div class="col-xs-12">
							<dl>
								<dt class="bg-info input-sm">临床诊断</dt>
								<dd class="input-sm">${cstCase.cliDia }</dd>
							</dl>
						</div>
						<div class="row center-block">
							<div class="col-xs-2">
								<dl>
									<dt class="bg-info input-sm">临床主诉</dt>
									<dd>${cstCase.cliMas }</dd>
								</dl>
							</div>
							<div class="col-xs-5">
								<dl>
									<dt class="bg-info input-sm">家族史</dt>
									<dd>${cstCase.famHis }</dd>
								</dl>
							</div>
							<div class="col-xs-5">
								<dl>
									<dt class="bg-info input-sm">其它病史</dt>
									<dd>${cstCase.othHis }</dd>
								</dl>
							</div>
						</div>
						<div class="col-xs-12">
							<dl>
								<dt class="bg-info input-sm">其它辅助检查结果（影像学，其它检查结果）</dt>
								<dd class="input-sm">${cstCase.othSup }</dd>
							</dl>
						</div>
						<div class="col-xs-12">
							<dl>
								<dt class="bg-info input-sm">辅助图片</dt>
								<dd class="input-sm ci_jt" id="ci_jt_fz" style="height:110px;"></dd>
							</dl>
						</div>
						<div class="row center-block">
							<div class="col-xs-2">
								<dl>
									<dt class="bg-info input-sm">手术部位</dt>
									<dd>${cstCase.opeOrg }</dd>
								</dl>
							</div>
							<div class="col-xs-5">
								<dl>
									<dt class="bg-info input-sm">活检（巨检）部位</dt>
									<dd>${cstCase.bioOrg }</dd>
								</dl>
							</div>
							<div class="col-xs-5">
								<dl>
									<dt class="bg-info input-sm">手术所见</dt>
									<dd>${cstCase.opeFind }</dd>
								</dl>
							</div>
						</div>
						<div class="col-xs-12">
							<dl>
								<dt class="bg-info input-sm">初诊意见</dt>
								<dd class="input-sm">${cstCase.priPatDia }</dd>
							</dl>
						</div>
					</fieldset>
					<fieldset style="padding:.15em .325em .5em;margin:0 2px;border:1px solid silver"> 
						<legend class="lead text-warning" style="padding:.2em;border:0;width:auto">会诊意见</legend>
						<div class="col-xs-12">
							<dl>
								<dt class="bg-info input-sm">图片<button class="btn btn-xs" id="refresh" >刷新</button></dt>
								<dd class="input-sm ci_jt" id="ci_jt" style="height:110px;"></dd>
							</dl>
							<input type="hidden" name="ci_jt" value="" />
						</div>
						<div class="col-xs-12">
							<dl>
								<dt class="bg-info input-sm">诊断意见</dt>
								<dd class="input-sm">
									<div class="col-xs-12">
										<textarea rows="4"  class="form-control input-sm" name="cstDia" id="cstDia" >${cstCase.cstDia }</textarea>
									</div>
								</dd>
							</dl>
						</div>
					</fieldset>
				</div>
				<div id="ex2" class="tab-pane fade center-block">
					<table class="table table-bordered table-hover">
					<caption class="bg-info">&nbsp;<button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#addProject" onclick="clearModalContent('addProject')" data-backdrop="static"><span class="glyphicon glyphicon-plus"></span>申请加做</button></caption>
						<thead>
							<tr>
								<th>序号</th>
								<th>加做内容</th>
								<th>创建人</th>
								<th>时间</th>
							</tr>
						</thead>
						<tbody id="addProjectList"></tbody>
					</table>
				</div>
				<div id="ex3" class="tab-pane fade center-block">
					<div class="table-responsive"><!-- 响应式表格-->
						<table class="table table-bordered table-hover">
							<caption class="bg-info">&nbsp;<button class="btn btn-xs btn-primary" data-toggle="modal" data-target="#addLeave" onclick="clearModalContent('addLeave')" data-backdrop="static"><span class="glyphicon glyphicon-plus"></span>添加反馈</button></caption>
							<thead>
								<tr>
									<th>序号</th>
									<th>留言</th>
									<th>反馈人</th>
									<th>时间</th>
								</tr>
							</thead>
							<tbody id="leaveList"></tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- /.内容 end -->
		</div><!-- /.box-body end -->
		</div><!-- /.box end -->
		</div>
		
		<!-- /.col -->
	</div>
	<!-- /.row -->
</section>
<!-- 添加反馈信息弹出模态窗口 -->
<div class="modal fade" id="addLeave">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="my_modal_title">反馈信息</h4>
			</div>
			<div class="modal-body">
				<div style="width: 100%; text-align: center;">
					<form id="add-leave-form" action="${ctx }/cons/cstAdvice/update" method="post"
						role="form" class="form-horizontal">
						<input type="hidden" name="caseId" id="caseId" value="${cstCase.caseLib.id}">
						<div class="form-group">
							<label for="content" class="col-xs-2 control-label">反馈内容:</label>
							<div class="col-xs-9">
								<textarea rows="10" class="form-control input-sm" name="content" id="content"
									placeholder="" required="required"></textarea>
							</div>
						</div>
						<input class="btn btn-default" type="button"
							onclick="myFormSubmit()" value="提交反馈"> &nbsp; <input
							class="btn btn-default" aria-label="Close" data-dismiss="modal"
							type="button" value="取消">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 申请加做项目弹出模态窗口 -->
<div class="modal fade" id="addProject">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="my_modal_title">申请加做项目</h4>
			</div>
			<div class="modal-body">
				<div style="width: 100%; text-align: center;">
					<div class="panel-heading clearfix">
						<form id="add-leave-form" action="${ctx }/cons/cstSupAntibody/update" method="post"
							role="form" class="form-horizontal">
							<div class="col-xs-6">
								<div class="form-group">
									<label for="scienceName" class="col-xs-4 control-label">项目名：</label>
		                            <div class="col-xs-8">
		                                <input type="email" class="form-control" name="scienceName" id="scienceName" placeholder="项目名">
		                            </div>
								</div>
							</div>
							<div class="col-xs-2">
							<input type="button" onclick="projectList()" class="btn btn-default" value="查询" />
							</div>
						</form>
					</div>
					<div class="table-responsive"><!-- 响应式表格-->
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th><input type="checkbox"/></th>
									<th>序号</th>
									<th>scienceName</th>
									<th>testItemCode</th>
								</tr>
							</thead>
							<tbody id="projectList"></tbody>
						</table>
					</div>
					<input class="btn btn-default" type="button"
						onclick="myProjectSubmit()" value="提交加做"> &nbsp; <input
						class="btn btn-default" aria-label="Close" data-dismiss="modal"
						type="button" value="取消">
					
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 预览图片显示信息弹出模态窗口 -->
<div class="modal fade" id="reportImg">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="my_modal_title">报告单</h4>
			</div>
			<div class="modal-body" id="reportBody">
				<div class="col-xs-12" style="text-align: center;">
					<img id="reportImgDiv" alt="" src="" class='col-xs-12'>
				</div>
			</div>
		</div>
	</div>
</div>
<script src='${ctxStatic}/AdminLTE/mystatic/js/jtConstant.js'></script>
<script src='${ctxStatic}/AdminLTE/mystatic/js/piclook.js'></script>
<link rel='stylesheet' href='${ctxStatic}/AdminLTE/mystatic/css/16sucai.css' type='text/css' />
<script type="text/javascript">
//病理号
var case_id = '${cstCase.caseLib.id }';//
var company_id = '${cstCase.company.id}';//子公司代码
var kmbarcode = '${cstCase.kmbarcode}';//标本条码
var test_code = '${cstCase.testCode}';//项目代码
var src_app = '${cstCase.srcApp}';//信息来源
var src_env = '${cstCase.srcEnv}';//产生app环境
/**
 * 提交最终诊断按钮
 */
function sendSubmit(){
	if(confirm("确定要提交最终诊断吗?")){
		if(saveCstDia("0")){
			if(showReportImg("0")){
				var jsonText = document.getElementsByName("ci_jt")[0].value;
				var cstDia = $("#cstDia").val();
				$.ajax({
					cache : true,
					type : "POST",
					url : ctx+"/cons/cstCase/sendInfo",
					data:{caseId:case_id,companyId:company_id,kmbarcode:kmbarcode,jsonText:jsonText,cstDia:cstDia,testCode:test_code,srcApp:src_app,srcEnv:src_env},
					dataType:"json",
					async : false,
					error : function(data) {
						alert("提交失败!");
					},
					success : function(data) {
						if(data == '0'){
							alert("发送成功!");
						}else if(data == '-2'){
							alert("后台方法执行异常!");
						}else if(data == '-1'){
							alert("LIR中发送数据异常");
						}
					}
				});	
			}
		}
	}
}

/**
 * 顶部保存草稿按钮，实际上只保存诊断意见一个字段
 */
function saveCstDia(obj){
	var result = false;
	var cstDia = $("#cstDia").val();
	var id = $("#id").val();
	if(obj != "0"){
		if(!confirm("确定要保存吗?")){return false;}
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"/cons/cstCase/update",
		data:{cstDia:cstDia,id:id},
		dataType:"json",
		async : false,
		error : function(data) {
			if(obj == "0"){
				alert("保存草稿失败!");
				result = false;
			}else{
				alert("保存草稿失败!");
			}
		},
		success : function(data) {
			if(obj == "0"){
				result = true;
			}else{
				alert("保存成功!");
			}
		}
	});	
	return result;
}

//刷新截图
$("#refresh").click(function(){
    $.ajax({
        url:ctx+"/cons/file/findAll",
        type:"post",
        dataType:"json",
        data:{testId:case_id,time:new Date().getTime()},
        	success: function(html){
        		var htmlData = new Array();
        		
		            	//获取到后台传过来的截图json不能为空
					if(html!=null){
						var htmls;
	                    if(html.length>0||html=="[]"){
			                    $("#box ul").html("");
			                    for(var i=0;i<=html.length-1;i++) {
			                    	htmls = JSON.parse(html[i].image);
				            					//对截图的名称进行切割，去除自带的UUID
			                    		var name=htmls[0].name.substring(0,htmls[0].name.indexOf(".jpg"));
			                    		$("#box ul").append("<li><img  src='"+ctx+htmls[0].path+"' width='198px' height='98px' name='"+name+"' id='"+(i+1)+"' title='"+html[i].id+"'> </li>");
			                    		init();
			                    		var jsonData = {};
			                    		jsonData.name = name;
			                    		jsonData.path = htmls[0].path;
			                    		htmlData[i] = jsonData;
	                    		}
			                    // 把数组转为JSON格式的字符串  modify by liao.xiangJun at 2016-03-03 -- begin
			                    var ci_jtJson = JSON.stringify(htmlData);
	                    		// 把当前最新的截图信息存到CI_JT字段中
		                    	document.getElementsByName("ci_jt")[0].value = ci_jtJson;
			                    // 把数组转为JSON格式的字符串  modify by liao.xiangJun at 2016-03-03 -- end
						}
              }
        }
    });
});
//删除截图
function doDel(obj) {
//modify by ltw at 2015-12-03
$.ajax({
    url:ctx+"/cons/file/delete",
    type:"post",
    dataType:"json",
	data:{testId:case_id,filePath:obj.name,id:obj.id,time:new Date().getTime()},
	success: function(html) {
    if(html!="" && html!=null &&html=="1") {
    	// 获取ci_jt的数据 modify by liao.xiangJun at 2016-03-03 -- begin
    var ci_jtJson = document.getElementsByName("ci_jt")[0].value;
    var ci_jtArray = eval(ci_jtJson);
    	// 获取ci_jt的数据 modify by liao.xiangJun at 2016-03-03 -- end

        //删除指定的截图
        for(var i=0;i<aLi.length;i++) {
	         //根据路径来删除
            if(obj.name.indexOf($(aLi[i]).children().attr("src"))>0) {
		            $(aLi[i]).remove();

    				// 删除图片的时候同时修改字段 modify by liao.xiangJun at 2016-03-03 -- begin
		            for(var j = 0; j < ci_jtArray.length; j++){
		            	if(obj.name.indexOf(ci_jtArray[j]['path']) > 0){
		            		ci_jtArray.splice(j, 1);
		            	}
		            }
		            document.getElementsByName("ci_jt")[0].value = JSON.stringify(ci_jtArray);
    				// 删除图片的时候同时修改字段 modify by liao.xiangJun at 2016-03-03 -- end

		            break;
            }
        }
        //当所有的截图删除完后，退出
        if(aLi.length==0) {
            //关闭查看截图的frame
            oFrame.style.display = 'none';
            oBg.style.display = 'none';
            oBot.style.display = 'none';
            oFrame.onmousedown = null;
            oFrame.style.cursor = 'auto';
        }

    for(var i=0;i<=aImg.length-1;i++) {
        //重新设置截图的id
        aImg[i].id=i+1;
    }
    //删除后加载第一张图片
    if(aImg && aImg.length > 0){
    	oFrame.innerHTML='<img src="'+aImg[0].src+'" id="'+aImg[0].id+'" /><span style="font-size: 15px;position: relative;bottom: -20px;right:-70px;">第1/'+(aImg.length)+'张</span><span style="font-size: 15px;position: relative;bottom: 310px;font-weight: bold;">'+aImg[0].name+'</span><span style="font-size: 15px;position: relative;bottom: 20px;right:-230px;"><input type="button" name='+aImg[0].src+' id='+aImg[0].title+' value="删除" onclick="doDel(this)"></span>';
    }
	}
				//删除截图提示 modify by ltw  at 2015-12-02
				else if(html == '3'){
					alert("诊断意见已经提交，不能删除");
				} else if(html == '0'){
					alert("更新失败");
				} else{
					alert("该用户不是病理专家，不能删除");
				}
			}
});
}

/*
 * desc : 显示截图(会诊意见 - 图片)
 * Date : 2016-6-1
 * author:zhanglei
*/
$(function(){
	var htmlData = new Array();
	var html="<div class='box' id='box' style='overflow: hidden;padding-top: 0px;'>";
	html+="<ul>";
	
			              // html+="<li><img  src='"+getContextPath()+path+"' width='200px' height='100px' name='"+name[0]+"-"+name[1]+"-"+name[2]+"' id='"+(i+1)+"'> </li>  "
	$.ajax({
						cache : true,
						type : "POST",
						url : ctx+"/cons/cstCase/queryImg?caseId="+case_id,
						async : false,
						error : function(data) {
							alert("查询失败!");
						},
						success : function(data) {
							for(n in data){
								var name = data[n].name.substring(0,data[n].name.indexOf(".jpg"));
								var paths = JSON.parse(data[n].image);
								html+="<li><img  src='"+ctx+paths[0].path+"' width='198px' height='98px' name='"+ name +"' id='"+(parseInt(n)+1)+"' title='"+data[n].id+"'> </li>  ";
								var jsonData = {};
								jsonData.name = name;
								jsonData.path = paths[0].path;
								htmlData[n] = jsonData;
							}
							var ci_jtJson = JSON.stringify(htmlData);
                    		// 把当前最新的截图信息存到CI_JT字段中
	                    	document.getElementsByName("ci_jt")[0].value = ci_jtJson;
						}
					});	
			              
	
			              
	html+="</ul></div>";
	html+="<div id='bg'></div>";
	html+="<div id='bottom'>";
	html+="<ul>";
	html+="<li class='prev'></li>";
	html+= "<li class='img'></li>";
	html+="<li class='next'></li>";
	html+="<li class='close'></li>";
	html+= "</ul>";
	html+="</div>";
	html+="<div id='frame'></div>";
	html+="<div id='del'></div>";
	
	$("#ci_jt").html(html);
});

/*
 * desc : 显示截图(辅助图片)
 * Date : 2016-6-1
 * author:zhanglei
*/

$(function(){
	var html="<div class='box1' id='box1'>";
	html+="<ul>";
	
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"/cons/cstCase/queryImgHelp?caseId="+case_id,
		async : false,
		error : function(data) {
			alert("查询失败!");
		},
		success : function(data) {
			for(n in data){
				html+="<li><img  src='"+ctx+data[n].image+"' width='198px' height='98px' name='"+ data[n].name.substring(0,data[n].name.indexOf(".jpg")) +"' id='"+(parseInt(n)+1)+"'> </li>  ";
			}
		}
	});	
	
	html+="</ul></div>";
	html+="<div id='bg1'></div>";
	html+="<div id='bottom1'>";
	html+="<ul>";
	html+="<li class='prev'></li>";
	html+= "<li class='img'></li>";
	html+="<li class='next'></li>";
	html+="<li class='close'></li>";
	html+= "</ul>";
	html+="</div>";
	html+="<div id='frame1'></div>";
	$("#ci_jt_fz").html(html);
});
window.onload();

/**
 * 加载加做项目数据
 */
function showAddProject(){
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"/cons/cstSupAntibody/listNoPage?caseId="+case_id,
		async : false,
		error : function(data) {
			alert("查询失败!");
		},
		success : function(data) {
			var htmls = "";
			for(n in data){
				htmls += "<tr><td>"+(parseInt(n)+1)+"</td><td>"+data[n].antibody_name+":"+data[n].antibody_code+"</td><td>"+data[n].createBy.name+"</td><td>"+data[n].createDate+"</td></tr>";
			}
			$("#addProjectList").html(htmls);
		}
	});	
	
}

function projectList(){
	var params = $("#scienceName").val();
	if(params==''){
		return;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"/cons/cstCase/projectList?scienceName="+params,
		async : false,
		error : function(data) {
			alert("查询失败!");
		},
		success : function(data) {
			var htmls = "";
			for(n in data){
				htmls += "<tr><td><input type='checkbox' value='"+data[n].id+"' /></td><td>"+(parseInt(n)+1)+"</td><td>"+data[n].scienceName+"</td><td>"+data[n].testItemCode+"</td></tr>";
			}
			$("#projectList").html(htmls);
		}
	});	
	
}
//提交加做项目
function myProjectSubmit(){
	var str="";
	$("#projectList input[name='checkbox']:checkbox").each(function(){ 
		 if($(this).attr("checked")){
             str += ","+$(this).val();
         }
	});
	return;//先确定加做项目两个表的对应关系
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"/cons/",
		data:{caseId:case_id},
		async : false,
		error : function(data) {
			alert("查询失败!");
		},
		success : function(data) {
			alert("提交成功!");
			showAddProject();
		}
	});	
	
}

/**
 * 查询反馈数据信息
 */
function showLeave(){
// 	if($("#leaveList").html()!=''){
// 		return;
// 	}
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"/cons/cstAdvice/listNoPage?caseId="+case_id,
		async : false,
		error : function(data) {
			alert("查询失败!");
		},
		success : function(data) {
			var htmls = "";
			for(n in data){
				htmls += "<tr><td>"+(parseInt(n)+1)+"</td><td>"+data[n].content+"</td><td>"+data[n].createBy.name+"</td><td>"+data[n].createDate+"</td></tr>";
			}
			$("#leaveList").html(htmls);
		}
	});	
}

//反馈信息提交弹出框（新增修改操作）
function myFormSubmit() {
		if($("#content").val()==''){
			alert("请输入反馈内容!");
			return;
		}
		var url = document.getElementById("add-leave-form").action;
		if (confirm("确定要保存数据吗?")) {
			$.ajax({
				cache : true,
				type : "POST",
				url : url,
				data : $('#add-leave-form').serialize(),// 你的formid
				async : false,
				error : function(data) {
					alert("保存失败!");
				},
				success : function(data) {
					$("#addLeave").modal("hide");
					showLeave();
				}
			});
		}
}
/**
 *预览
 */
function showReportImg(obj){
	var result = false;
	$.ajax({
		cache : true,
		type : "POST",
		url : ctx+"/cons/report/createReport",
		data : {format:'format',caseId:case_id},
		async : false,
		error : function(data) {
			alert("创建报告单失败!");
			result = false;
		},
		success : function(data) {
			if(obj == "0"){
				result = true;
			}else{
				if(data!=null&&data!=""){
					var imgSrc = '${ctx}/'+data.substring(data.indexOf('uploads'));
					var img = new Image();
					img.src = imgSrc;
					//document.all.reportBody.style.height = 800;
					setTimeout(function(){
						document.getElementById("reportBody").style.height = img.height*(600/img.width);
						$("#reportImgDiv").attr("src",imgSrc+"?v="+Math.random());
						$("#reportImg").modal({backdrop: 'static', keyboard: false});//弹出框show
					}, 500);
				}
			}
		}
	});
	return result;
}

//清除modal拟态框的内容
function clearModalContent(id){
	if(id == 'addLeave'){
		$("#"+id+" #content").val("");
	}else if(id == 'addProject'){
		$("#"+id+" #scienceName").val("");
		$("#"+id+" #projectList").html("");
	}
}


</script>

