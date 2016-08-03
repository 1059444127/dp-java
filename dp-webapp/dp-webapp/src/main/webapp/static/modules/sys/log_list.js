


$(function () {
	queryList();
});

function queryList(){
	getLogParams();
	$.ajax({
	    url: ctx+"/admin/log/list",
	    datatype: 'json',
	    type: "Post",
	    data: $('#searchForm').serialize(),
	    success: function (data) {
	      if (data != null) {
	    	  var htmlTable="";
	        $.each(data.aaData, function (index, item) { //遍历返回的json
	        	var typeName;
	        	if(isNull(item.type)) {
	        		typeName = "-";
		    	} else if(item.type == '1'){
		    		typeName = "接入日志";
		    	} else if(item.type == '2'){
		    		typeName = "错误日志";
		    	} else {
		    		typeName = "其他日志";
		    	}
	          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + typeName + "</td><td>" + item.title + "</td><td>" + item.createByName + "</td><td>" + item.createDate + "</td><td>"+item.requestUri+"</td></tr>";
	        });
	        if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
            }
	        $("#logTable tbody").html(htmlTable);
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
        url: ctx+"/admin/log/list",
        type: "Post",
        data: $('#searchForm').serialize(),
        success: function (data) {
          if (data != null) {
        	  var htmlTable="";
            $.each(data.aaData, function (index, item) { //遍历返回的json
            	var typeName;
	        	if(isNull(item.type)) {
	        		typeName = "-";
		    	} else if(item.type == '1'){
		    		typeName = "接入日志";
		    	} else if(item.type == '2'){
		    		typeName = "错误日志";
		    	} else {
		    		typeName = "其他日志";
		    	}
	          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + typeName + "</td><td>" + item.title + "</td><td>" + item.createByName + "</td><td>" + item.createDate + "</td><td>"+item.requestUri+"</td></tr>";
            });
            if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
            }
            $("#logTable tbody").html(htmlTable);
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


$("#datepicker").focus(function(){ 
	//Date range picker
	$('#datepicker').daterangepicker();
});


function getLogParams(){
	//获得时间区间
	var datepicker = $('#datepicker').val();
	var beginDate;
	var endDate;
	if(datepicker.length > 15) {
		var arr=new Array(); 
		arr=datepicker.split('~');
		beginDate = arr[0].replace(" ","");
		endDate = arr[1].replace(" ","");
	}
	/* alert(beginDate + "|" + endDate); */
	$("#beginDate").val(beginDate);
	$("#endDate").val(endDate);
}

function searchLog(){
	queryList();
}

function resetSearch(){
	document.getElementById("searchForm").reset();
}
