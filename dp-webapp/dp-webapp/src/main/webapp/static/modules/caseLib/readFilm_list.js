/**
 * zl
 * 数据字典页面js
 */
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

$("#dates").focus(function(){ 
	//Date range picker
	$("#dates").datepicker();
});

$(function () {
	queryList();
});

function queryList(){
	$.ajax({
	    url: ctx+"/cons/lib/readFilm/searchList",
	    datatype: 'json',
	    type: "Post",
	    data: $('#search_form').serialize(),
	    success: function (data) {
	      if (data != null) {
	    	  var htmlTable="";
	        $.each(data.aaData, function (index, item) { //遍历返回的json
	          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.address + "</td><td>" + new Date(item.dates).Format("yyyy-MM-dd") + "</td><td>" + item.orgName + "</td><td>" + item.remarks + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	        });
	        if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
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
        url: ctx+"/cons/lib/readFilm/searchList",
        type: "Post",
        data: $('#search_form').serialize(),
        success: function (data) {
          if (data != null) {
        	  var htmlTable="";
            $.each(data.aaData, function (index, item) { //遍历返回的json
            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.address + "</td><td>" + new Date(item.dates).Format("yyyy-MM-dd") + "</td><td>" + item.orgName + "</td><td>" + item.remarks + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
            });
            if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
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

function del(id){
	// 数据删除
	if (confirm("确定要删除吗?")) {
		$.get(ctx+"/cons/lib/readFilm/del", {
			id : id
		}, function(data) {
			var datastr = eval('(' + data + ')');
			if (datastr.status == 1) {
				queryList();
				//alert("删除成功!");
			} else {
				alert(datastr.resultMsg);
			}
		});
	}
	
}
	// 数据编辑
	function editrow(id) {
		clearForm();
		$.get(ctx+"/cons/lib/readFilm/update", {
			id : id
		}, function(data) {
			if (data != null) {
				var jsondata = eval('(' + data + ')');
				var fields = $("#add-form").serializeArray();
				jQuery.each(fields, function(i, field) {
					//jquery根据name属性查找
					if(field.name=='dates'){
						$("#add-form :input[name='" + field.name + "']").val(new Date(jsondata[field.name]).Format("yyyy-MM-dd"));
					}else{
						$("#add-form :input[name='" + field.name + "']").val(
								jsondata[field.name]);
					}
				});
				$("#my_modal_title").html("读片会-修改");
				$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
			}
		});
	}
	//进入添加页面
	function add(){
		clearForm();
		$("#my_modal_title").html("读片会-添加");
		$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
	}
	
	//提交弹出框（新增修改操作）
	function myFormSubmit() {
		var name = $("#add-form #name").val();
		if(name==""){
			alert("请输入读片会名称！");
			return;
		}
		var url = document.getElementById("add-form").action;
		if (confirm("确定要保存数据吗?")) {
			$.ajax({
				cache : true,
				type : "POST",
				url : url,
				data : $('#add-form').serialize(),// 你的formid
				async : false,
				error : function(data) {
					alert("保存失败!");
				},
				success : function(data) {
					$("#add-div").modal("hide");
					queryList();
					alert("保存成功!");
				}
			});
		}
		
	}
	
	//清除弹出框表单数据
	function clearForm() {
		$("#id").val("");
		var myform = document.getElementById("add-form");
		myform.reset();//清除原数据
	}
