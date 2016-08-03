/**
 * zl
 * 未诊断页面js
 */

$(function () {
	queryList();
});

function queryList(){
	$.ajax({
	    url: ctx+"/cons/cstCase/searchList",
	    datatype: 'json',
	    type: "Post",
	    data: $('#cstCase_form').serialize(),
	    success: function (data) {
	      if (data != null) {
	    	  var htmlTable="";
	        $.each(data.aaData, function (index, item) { //遍历返回的json
	        	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td><a href='#' onclick=\"showContent('"+ctx+"/cons/cstCase/detail?caseLib.caseId="+item.caseLib.id+"')\" target='_self'>"+item.caseLib.id+"</a></td><td>" + item.name + "</td><td>" + item.gender + "</td><td>" + item.age + "</td><td>" + item.ageUnit + "</td><td>" + item.company.name + "</td><td>" + item.state + "</td><td>" + item.createDate + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\"class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	        });
	        if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='10'>没有查询到记录!</td></tr>";
            }
	        $("#cstCase_list tbody").html(htmlTable);
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
        url: ctx+"/cons/cstCase/searchList",
        type: "Post",
        data: $('#cstCase_form').serialize(),
        success: function (data) {
          if (data != null) {
        	  var htmlTable="";
            $.each(data.aaData, function (index, item) { //遍历返回的json
              htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td><a href='#' onclick=\"showContent('"+ctx+"/cons/cstCase/detail?caseLib.caseId="+item.caseLib.id+"')\" target='_self'>"+item.caseLib.id+"</a></td><td>" + item.name + "</td><td>" + item.gender + "</td><td>" + item.age + "</td><td>" + item.ageUnit + "</td><td>" + item.company.name + "</td><td>" + item.state + "</td><td>" + item.createDate + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\"class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
            });
            if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='10'>没有查询到记录!</td></tr>";
            }
            $("#cstCase_list tbody").html(htmlTable);
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
