<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>金域病理远程会诊平台</title>
<script type="text/javascript">

	var testId = '${model.caseId}';
	//保存江丰图片UUID add  by ltw  at 2015-12-01
	var jFFileName;

	function showJT(url,name,title){
		$("#iframe1").attr("src",url);
		$("#iframe1").attr("name",name);
		$("#iframe1").attr("title",title);
	}
	 
	$(function(){
        //$("#jtDiv").css("top",(-screen.height*0.04));
        //$("#jtDiv").css("left",(screen.width*0.74));
                     //modify by ltw at 2015-12-01
      //监听截图 add by ltw at 2015-11-26
	    window.addEventListener('message', function (e) {
            if (e.data.indexOf("[ShotSuccess]") != -1) {
            	 jFFileName=e.data.replace("[ShotSuccess]", "");
                 // 去除自动命名多余的字符  modify by liao.xiangJun at 2016-03-03 -- begin
               var src= document.getElementById('iframe1').src;
               var paramMeters=src.split("?")[1].split("&");
               var barcode="";
     //add by ltw at 2015-12-01
               var jFServerIP="";
               jFServerIP=src.split("?")[0].split("/")[2].split(":")[0];
               for(var i=0;i<paramMeters.length;i++)
               {
              	 if(paramMeters[i].indexOf("barcode")>-1)
              	 {
              		 barcode= paramMeters[i].split("=")[1];
              	 }
               }

               var filename="";//物理文件名
               var displayName="";//对话框显示的文件名，以及浏览图片时显示的名称

                 $.ajax({
                 	// url: 'http://192.168.2.138:3000/dp/cst/testId/' + testId + '/slide/' + barcode + '/antiBodies/' + document.getElementById('iframe1').title + '/screenShotName',
                 	url: 'http://' + location.hostname + ':3000/dp/cst/testId/' + testId + '/slide/' + barcode + '/antiBodies/' + document.getElementById('iframe1').title + '/screenShotName',
                 	type: 'GET',
                 	async: false,
                 	success: function(msg){
                 		console.info(msg);
                 		filename = msg.filename;
                 		displayName = msg.displayName;
                 	}
                 });

                 // var str=prompt("upload as",testId+"-"+ document.getElementById('iframe1').title+"-"+e.data.replace("[ShotSuccess]", "").split("-")[0]); // 没去除之前的
                 var str=prompt("upload as",displayName);
                 // 去除自动命名多余的字符  modify by liao.xiangJun at 2016-03-03 -- end

                 if(str && str.replace(/(^\s*)|(\s*$)/g, "").length >0)
                 {
					 str=str+".jpg";
                   //上传截图 add by ltw at 2015-11-26
                   $.ajax({
                    	   type: "POST",
                    	   url: "${ctx}/cons/file/update",
              	       data: "fileName=" + str + "&testId=" + testId + "&jFFileName=" + jFFileName + "&barcode=" + barcode + "&jFServerIP=" + jFServerIP + "&physicalFilename=" + filename + ".jpg",
                    	   success: function(msg){
                    	   }
                    });
                 }
            }

        }, false);
	    //截图 modify by ltw at 2015-11-25
	    $("#jt").click(function(){
	    	if(document.getElementById("iframe1").name == "JiangFeng"){
	    		var iframe = document.getElementById('iframe1').contentWindow;
	            iframe.postMessage("[Screenshot]|[0*0]", "*");
	    	}
		});

	 });

</script>
<style type="text/css">

.picture_head{
	width: 100%;
	height: 49px;
	margin: 0px;
	padding: 0px;
	background:url("${ctxStatic}/AdminLTE/mystatic/images/header_bg.png") repeat-x;
 }
.picture_left{
	overflow:auto;
	position:absolute;
	left:0px; 
	top:55px;
	width:12%;
	height:100%;
	float:left;
}
.picture_left img{
	width:100%;
	height:60px;
	align:center;
}

.picture_right{
	position: absolute; 
	left: 12%; 
	top: 55px; 
	width: 87%; 
	height: 92%;
}
</style>
</head>
<body>
<div class="picture_head"></div>

<div class="picture_left">
	<c:forEach items="${pictures }" var="item">
		<div style="width: 190px;border: 0px solid teal;margin: 5px, 5px, 5px, 5px;text-align: center;">
		${item.label }
		<a href="javascript:void(0)" onclick="showJT(this.id,this.name,this.title)" id="${item.url }" name="JiangFeng" title="${item.label }"><img alt="base64 encode" src="data:image/gif;base64,${item.overview }"></a>
		</div>
	</c:forEach>
</div>

<iframe class="picture_right" id="iframe1" name="JiangFeng" title="${model.label }" src="${model.url }"></iframe>

<div class="" style="position: relative; top: -27.04px; left: 88%;" id="jtDiv">
	<c:if test="${typed!='1' }"><input type="button" value="截图" id="jt"></c:if>
</div>
</body>
</html>