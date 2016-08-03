/**
 * zl
 * 区域管理页面js
 */

//ztree 使用start
	var myTree;
	$(document).ready(function () {
		setting1.callback={onClick: showMyTreePatType};
		setting2.callback={onClick: showReadFilm};
		 $.fn.zTree.init($("#treeParent"),setting1, [{"id":1,"pId":0,"name":'初始化'}]);
		 myTree = $.fn.zTree.getZTreeObj("treeParent");
		 initTree();
	});
	
	 function initTree() {
		 var read_data;
		 $.get(ctx+"/cons/lib/readFilm/tree",{},function(read_data){
			 if(read_data == null){
				 alert("树菜单为空!");
			 }else{
				 //读片会树
				 $.fn.zTree.init($("#readFilmTree"),setting2, myTree.transformTozTreeNodes(read_data));
			 }
		 });
		 
		 $.get(ctx+"/cons/lib/patType/tree",{},function(data){
			 if(data == null){
				 alert("树菜单为空!");
			 }else{
				 //合并json
				//var result = eval('('+(JSON.stringify(read_data)+JSON.stringify(data)).replace('][',',')+')');
				//zTree左侧树目录
		         $.fn.zTree.init($("#MyTreearea"),setting1, myTree.transformTozTreeNodes(data));
			 }
		 });
	 }
	function showMyTreePatType(event, treeId, treeNode){
		$("#parentIds").val(treeNode.id);
		queryList();
	}
	function showReadFilm(event, treeId, treeNode){
		if(treeNode.id=='0'){
			return;
		}
		if(treeNode.id!=treeNode.name){
			window.open(ctx+"/cons/lib/readFilm/detail?id="+treeNode.id);
		}
	}
	//ztree  end
	
	//list  start
	
	/**
	 * 展示缩略图或是列表
	 * */
	if(viewType==1){
		function queryList(){
			$.ajax({
			    url: ctx+"/cons/lib/caseLib/searchList",
			    datatype: 'json',
			    type: "Post",
			    data: $('#search_form').serialize(),
			    success: function (data) {
			      if (data != null) {
			    	  var htmlTable="";
			        $.each(data.aaData, function (index, item) { //遍历返回的json
			        	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.opeFind + "</td><td><a href='"+ctx+"/cons/lib/caseLib/detail?id="+item.id+"' target='_blank'><img src='" + item.caseLibSlide.overview + "' height='55' width='70' style='padding: 5px;border: 1px solid #68aa08;border-radius: 5px;'/></a></td><td>" + item.history + "</td><td>" + item.hospital + "</td></tr>";
			        });
			        if(htmlTable == ""){
		            	htmlTable += "<tr><td colspan='5'>没有查询到记录!</td></tr>";
		            }
			        $("#table_list tbody").html(htmlTable);
			        $("#pageNo").val(data.pageCount); //取到pageCount的值(把返回数据转成object类型)
			        //currentPage = data.totalPage; //得到urrentPage
			        
			        $("#myPageDiv").html('');
			        //生成分页div
			        pageDiv(data.pageCount,data.totalPage);
			        dataRange(data);
			      }
			    }
			  });
		}
		
		
		function onPageClicked(pageCount){
			$("#pageNo").val(pageCount);
			$.ajax({
		        url: ctx+"/cons/lib/caseLib/searchList",
		        type: "Post",
		        data: $('#search_form').serialize(),
		        success: function (data) {
		          if (data != null) {
		        	  var htmlTable="";
		            $.each(data.aaData, function (index, item) { //遍历返回的json
		            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.opeFind + "</td><td><a href='"+ctx+"/cons/lib/caseLib/detail?id="+item.id+"' target='_blank'><img src='" + item.caseLibSlide.overview + "' height='55' width='70' style='padding: 5px;border: 1px solid #68aa08;border-radius: 5px;'/></a></td><td>" + item.history + "</td><td>" + item.hospital + "</td></tr>";
		            });
		            if(htmlTable == ""){
		            	htmlTable += "<tr><td colspan='5'>没有查询到记录!</td></tr>";
		            }
		            $("#table_list tbody").html(htmlTable);
		            $("#pageNo").val(data.pageCount); //取到pageCount的值(把返回数据转成object类型) 当前是第几页
		            //currentPage = data.totalPage; //得到urrentPage  总页数
		            
		            $("#myPageDiv").html('');
		            //生成分页div
		            pageDiv(data.pageCount,data.totalPage);
		            dataRange(data);
		          }
		        }
		      });
		}
	}else{
		function queryList(){
			$.ajax({
			    url: ctx+"/cons/lib/caseLib/searchList",
			    datatype: 'json',
			    type: "Post",
			    data: $('#search_form').serialize(),
			    success: function (data) {
			      if (data != null) {
			    	  var htmlTable="";
			        $.each(data.aaData, function (index, item) { //遍历返回的json
			          htmlTable += "<div class='col-xs-3' style='width:24%;border-radius: 5px;padding: 5px 10px 10px 10px;box-shadow: 2px 2px 2px 1px #B2B4B2;margin: 0px 5px 5px 0;background:#ecf0f5;'>" +
			          		"<div class='row sgre'><div class='col-xs-6'><img src='"+item.caseLibSlide.label+"' alt='' height='94' width='100%' style='padding: 5px;border: 1px solid #68aa08;border-radius: 5px;'/></div><div class='col-xs-6'><a href='"+ctx+"/cons/lib/caseLib/detail?id="+item.id+"' target='_blank'><img src='"+item.caseLibSlide.overview+"' alt='' height='94' width='100%' style='padding: 5px;border: 1px solid #68aa08;border-radius: 5px;' /></a></div></div>" +
			          		"<div class='row'><div class='col-xs-6'>序号：" + (parseInt(data.iDisplayStart)+index+1) + "</div><div class='col-xs-6'>倍率：40</div></div>" +
			          		"<div class='row'><div class='col-xs-12'>标签：" + item.opeFind + "</div></div>" +
			          		"<div class='row'><div class='col-xs-12'>医院名称：" + item.hospital + "</div></div><div class='row'><div class='col-xs-12'>病理号：" + item.caseId + "</div></div></div>";
			        });
			        if(htmlTable == ""){
		            	htmlTable += "没有查询到记录!";
		            }
			        $("#div_list").html(htmlTable);
			        $("#pageNo").val(data.pageCount); //取到pageCount的值(把返回数据转成object类型)
			        //currentPage = data.totalPage; //得到urrentPage
			        
			        $("#myPageDiv").html('');
			        //生成分页div
			        pageDiv(data.pageCount,data.totalPage);
			        dataRange(data);
			      }
			    }
			  });
		}
		
		
		function onPageClicked(pageCount){
			$("#pageNo").val(pageCount);
			$.ajax({
		        url: ctx+"/cons/lib/caseLib/searchList",
		        type: "Post",
		        data: $('#search_form').serialize(),
		        success: function (data) {
		          if (data != null) {
		        	  var htmlTable="";
		            $.each(data.aaData, function (index, item) { //遍历返回的json
		            	htmlTable += "<div class='col-xs-3' style='width:24%;border-radius: 5px;padding: 5px 10px 10px 10px;box-shadow: 2px 2px 2px 1px #B2B4B2;margin: 0px 5px 5px 0;background:#ecf0f5;'>" +
		          		"<div class='row sgre'><div class='col-xs-6'><img src='"+item.caseLibSlide.label+"' alt='' height='94' width='100%' style='padding: 5px;border: 1px solid #68aa08;border-radius: 5px;'/></div><div class='col-xs-6'><a href='"+ctx+"/cons/lib/caseLib/detail?id="+item.id+"' target='_blank'><img src='"+item.caseLibSlide.overview+"' alt='' height='94' width='100%' style='padding: 5px;border: 1px solid #68aa08;border-radius: 5px;' /></a></div></div>" +
		          		"<div class='row'><div class='col-xs-6'>序号：" + (parseInt(data.iDisplayStart)+index+1) + "</div><div class='col-xs-6'>倍率：40</div></div>" +
		          		"<div class='row'><div class='col-xs-12'>标签：" + item.opeFind + "</div></div>" +
		          		"<div class='row'><div class='col-xs-12'>医院名称：" + item.hospital + "</div></div><div class='row'><div class='col-xs-12'>病理号：" + item.caseId + "</div></div></div>";
		            });
		            if(htmlTable == ""){
		            	htmlTable += "没有查询到记录!";
		            }
		            $("#div_list").html(htmlTable);
		            $("#pageNo").val(data.pageCount); //取到pageCount的值(把返回数据转成object类型) 当前是第几页
		            //currentPage = data.totalPage; //得到urrentPage  总页数
		            
		            $("#myPageDiv").html('');
		            //生成分页div
		            pageDiv(data.pageCount,data.totalPage);
		            dataRange(data);
		          }
		        }
		      });
		}
		
		
		/**单独的分页 start*/
		function pageDiv(pageCount,currentPage){
			var htmlPage = "<ul class='pagination' id='dictPage'>";
			
		    
		    if(pageCount == 1){
		    	htmlPage += "<li class='disabled'><span>首页</span></li><li class='disabled'><span>上一页</span></li>";
		    }else{
		    	htmlPage += "<li><a href=\"javascript:onPageClicked(1)\"><span>首页</span></a></li><li><a href=\"javascript:pagePre()\"><span>上一页</span></a></li>";
		    }
		    if(currentPage<=8){
		    	for(var i=1;i<=currentPage;i++){
		        	if(pageCount == i){
		        		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
		        	}else{
		        		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
		        	}
		        }
		    }else{
		    	if(pageCount<=4){
		    		for(var i=1;i<=currentPage;i++){
		            	if(pageCount == i){
		            		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
		            	}else if(i>8){
		            		htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
		            		break;
		            	}else{
		            		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
		            	}
		            }
		    	}else{
		    		if((parseInt(pageCount)+4)>currentPage){
		    			htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
		        		for(var i=parseInt(pageCount)-4;i<=currentPage;i++){
		                	if(pageCount == i){
		                		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
		                	}else{
		                		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
		                	}
		                }
		    		}else{
		    			htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
		        		for(var i=parseInt(pageCount)-4;i<(parseInt(pageCount)+4);i++){
		                	if(pageCount == i){
		                		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
		                	}else{
		                		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
		                	}
		                }
		        		htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
		    		}
		    	}
		    }
		    
		    if(pageCount == currentPage){
		    	htmlPage +="<li class='disabled'><span>下一页</span></li><li class='disabled'><span>末页</span></li>";
		    }else{
		    	htmlPage +="<li><a href=\"javascript:pageNext()\"><span>下一页</span></a></li><li><a href=\"javascript:onPageClicked('"+currentPage+"')\"><span>末页</span></a></li>";
		    }
		    htmlPage +="</ul>";
		    $("#myPageDiv").html(htmlPage);
		}
		/**
		 * 上一页
		 */
		function pagePre(){
			onPageClicked($("#pageNo").val()-1);
		}
		/**
		 * 下一页
		 */
		function pageNext(){
			onPageClicked(parseInt($("#pageNo").val())+1);
		}


		function changeNumber(obj){
			$("#pageSize").val($(obj).val());
			queryList();
		}
		/**
		 * 给左下角数据动态赋值
		 */
		function dataRange(data){
			$("#totalCount").html(data.iTotalRecords);
		    $("#startNum").html(parseInt(data.iDisplayStart)+1);
		    var endNum = parseInt(data.iDisplayStart)+parseInt(data.iDisplayLength);
		    if(endNum<parseInt(data.iTotalRecords)){
		    	$("#endNum").html(endNum);
		    }else{
		    	$("#endNum").html(data.iTotalRecords);
		    }
		}
		/**单独分页 end*/
	}
	
	$(function(){
		queryList();
	});
	
	/*查询全部切片*/
	function queryClear(){
		document.getElementById("search_form").reset();
		$("#orderBy").val("id desc");
		$("#parentIds").val("");
		queryList();
	}
	/*时间排序*/
	function querySort(){
		$("#orderBy").val("update_date desc");
		queryList();
	}
	
	function showOverView(){
		showContent(ctx+"/cons/lib/caseLib/index?viewType=-1");
	}
	
	function showList(){
		showContent(ctx+"/cons/lib/caseLib/index?viewType=1");
	}
	
	
	/*
	function queryList(){
		$.ajax({
		    url: ctx+"/cons/patType/searchList",
		    datatype: 'json',
		    type: "Post",
		    data: $('#search_form').serialize(),
		    success: function (data) {
		      if (data != null) {
		    	  var htmlTable="";
		        $.each(data.aaData, function (index, item) { //遍历返回的json
		          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.code + "</td><td>" + item.type + "</td><td>" + item.remarks + "</td></tr>";
		        });
		        if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='5'>没有查询到记录!</td></tr>";
	            }
		        $("#table_list tbody").html(htmlTable);
		        $("#pageNo").val(data.pageCount); //取到pageCount的值(把返回数据转成object类型)
		        //currentPage = data.totalPage; //得到urrentPage
		        
		        $("#myPageDiv").html('');
		        //生成分页div
		        pageDiv(data.pageCount,data.totalPage);
		        dataRange(data);
		      }
		    }
		  });
	}
	
	
	function onPageClicked(pageCount){
		$("#pageNo").val(pageCount);
		$.ajax({
	        url: ctx+"/cons/patType/searchList",
	        type: "Post",
	        data: $('#search_form').serialize(),
	        success: function (data) {
	          if (data != null) {
	        	  var htmlTable="";
	            $.each(data.aaData, function (index, item) { //遍历返回的json
	            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.code + "</td><td>" + item.type + "</td><td>" + item.remarks + "</td></tr>";
	            });
	            if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='5'>没有查询到记录!</td></tr>";
	            }
	            $("#table_list tbody").html(htmlTable);
	            $("#pageNo").val(data.pageCount); //取到pageCount的值(把返回数据转成object类型) 当前是第几页
	            //currentPage = data.totalPage; //得到urrentPage  总页数
	            
	            $("#myPageDiv").html('');
	            //生成分页div
	            pageDiv(data.pageCount,data.totalPage);
	            dataRange(data);
	          }
	        }
	      });
	}*/