/**
 * zl
 * 数据字典页面js
 */


$(function () {
	queryList();
});

function queryList(){
	$.ajax({
	    url: ctx+"/admin/dict/searchList",
	    datatype: 'json',
	    type: "Post",
	    data: $('#dict_form').serialize(),
	    success: function (data) {
	      if (data != null) {
	    	  var htmlTable="";
	        $.each(data.aaData, function (index, item) { //遍历返回的json
	          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.type + "</td><td>" + item.label + "</td><td>" + item.description + "</td><td>" + item.value + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	        });
	        if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
            }
	        $("#dict_list tbody").html(htmlTable);
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
        url: ctx+"/admin/dict/searchList",
        type: "Post",
        data: $('#dict_form').serialize(),
        success: function (data) {
          if (data != null) {
        	  var htmlTable="";
            $.each(data.aaData, function (index, item) { //遍历返回的json
              htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.type + "</td><td>" + item.label + "</td><td>" + item.description + "</td><td>" + item.value + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\"class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
            });
            if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
            }
            $("#dict_list tbody").html(htmlTable);
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
		$.get(ctx+"/admin/dict/del", {
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
		$.get(ctx+"/admin/dict/update", {
			id : id
		}, function(data) {
			if (data != null) {
				var jsondata = eval('(' + data + ')');
				var fields = $("#add-form").serializeArray();
				jQuery.each(fields, function(i, field) {
					//jquery根据name属性查找
					$("#add-form :input[name='" + field.name + "']").val(
							jsondata[field.name]);
				});
				$("#my_modal_title").html("数据字典-修改");
				$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
			}
		});
	}
	//进入添加页面
	function add(){
		clearForm();
		$("#my_modal_title").html("数据字典-添加");
		$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
	}
	
	//提交弹出框（新增修改操作）
	function myFormSubmit() {
		if(validate_form($("#add-form"),[value,label,type,description,sort])){
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
	}
	
	//清除弹出框表单数据
	function clearForm() {
		$("#id").val("");
		var myform = document.getElementById("add-form");
		myform.reset();//清除原数据
	}
