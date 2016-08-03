/*
 jquery based
 
 lhgcore.lhgdialog.min.js based
*/

var jsonConfig;
var baseConfig = [
		 {"id" : "rwlz","type" : "2","name" : "任务流转","width" : "640","height" : "420","command":"/plat/workflow/tWorkflowTask!transition.action"}
		,{"id" : "rwth","type" : "2","name" : "任务退回","width" : "540","height" : "360","command":"/plat/workflow/tWorkflowTask!sendBack.action"}
		,{"id" : "rwqx","type" : "2","name" : "取消任务","width" : "540","height" : "360","command":"/plat/workflow/tWorkflowTask!cancel.action"}
		,{"id" : "rwcs","type" : "2","name" : "任务抄送","width" : "360","height" : "500","command":"/plat/workflow/tWorkflowTask!copyfor.action"}
		,{"id" : "rwyy","type" : "2","name" : "来文已阅","width" : "540","height" : "360","command":"/plat/workflow/tWorkflowTask!reader.action"}
		,{"id" : "rwjs","type" : "2","name" : "结束任务","width" : "540","height" : "360","command":"/plat/workflow/tWorkflowTask!finishProcess.action"}
		,{"id" : "jbxx","type" : "1","name" : "基本信息","width" : "-1","height" : "-1","command":"/plat/workflow/tWorkflowTask!processTask.action"}
		,{"id" : "lclb","type" : "1","name" : "流程列表","width" : "-1","height" : "-1","command":"/plat/workflow/tWorkflowTask!taskDetails.action"}
		,{"id" : "lcst","type" : "1","name" : "流程视图","width" : "-1","height" : "-1","command":"/plat/workflow/tWorkflowTask!taskDiagram.action"}
		,{"id" : "fjxx","type" : "1","name" : "附件信息","width" : "-1","height" : "-1","command":"/plat/workflow/tWorkflowTask!attachment.action"}
		];

$(document).ready(function(){
     	var actions = 	$("#jsonActions").val();
     		
     	if(actions && actions != ''){
     		jsonConfig = eval(actions);
     	}
     	if(jsonConfig){
     		$.each(jsonConfig,function(index,data){
     		$("#"+data.id).bind("click",function(){
     			wfInfos(this.id);
     		});
     		});
     	}
     
});


function queryParams(){
	var params = "v="+Math.random();
	if($("#_processId").size() > 0){
		params += "&processId="+$("#_processId").val();
	}
	
	if($("#_taskId").size() > 0){
		params += "&taskId="+$("#_taskId").val();
	}
	return params;
}


function wfInfos(id){
	var config = null;
	//取配置的默认值
	
	for(var i = 0 ; i < baseConfig.length ; i++){
		var data = baseConfig[i];
		if(id == data.id){
			config = data;
			break;	
		}
	}	
	
	//取页面配置值
	if(!config && jsonConfig ){
		for(var i = 0 ; i < jsonConfig.length ; i++){
		var data = jsonConfig[i];
		if(id == data.id){
			config = data;
			break;	
		}
		}
	}
	if(config && config['command']){
		var baseUrl = 	getBasePath() + config['command'];
		var params = queryParams();
		var index = baseUrl.indexOf("?");
			if(index < 0){
				baseUrl += "?"+params;
			}else{
				baseUrl += "&"+params;
			}
		 if("1" == config['type']){
		 	window.location.href = baseUrl;
		 }else if("2" == config['type']){
		 	if(!config['width']){
		 	config['width'] = 480;
		 	config['height'] = 320;
		 	}
		 	$.dialog({ 
		    id: config['id'], 
		    title: config['name'],
		    width: config['width']+"px", 
    		height: config['height']+"px", 
		    content: 'url:'+baseUrl, 
		    lock:true 
			});
		 }else if("5" == config['type']){
		 	try{
		 		eval(config['command']);
		 	}catch(e){
		 		alert("执行函数异常"+config['command']);
		 	}
		 	
		 }else{
		 	var newWin = window.open(baseUrl,config['id']);
		 		if(newWin){
		 			newWin.focus();
		 		}
		 		
		 }
	}
	
	
}


//获取系统当前页面的应用名称
function getBasePath(){
        var path = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = path.indexOf(pathName);
        var serverUrl = path.substring(0, pos);
        var basePath = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
      	return basePath;
}