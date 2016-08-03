/**
 * zl
 * 数据字典页面js
 */

var myTree;
	$(document).ready(function () {
		setting2.callback={onClick: onClick};
		 $.fn.zTree.init($("#treeParent"),setting1, [{"id":1,"pId":0,"name":'初始化'}]);
		 myTree = $.fn.zTree.getZTreeObj("treeParent");
	});
	
	//上级机构树选择
	function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treePatType"),
			nodes = zTree.getSelectedNodes();
			if(nodes.length>0){
				$("#parent").val(nodes[0].name);
				$("#parentId").val(nodes[0].id);
			}
			hideMenu();
		}
	//初始化上级机构下拉框树，并隐藏当前机构及其子机构
	function initAddFormTree(){
		$.get(ctx+"/cons/lib/patType/tree",{},function(data){
			if(data == null){
				alert("树菜单加载异常!");
			}else{
				//zTree上级机构下拉框树目录
		        $.fn.zTree.init($("#treePatType"), setting2,  myTree.transformTozTreeNodes(data));
		        var zTree = $.fn.zTree.getZTreeObj("treePatType"),
		        nodes = zTree.getNodesByParam("id", $("#id").val(), null);
		        if(nodes.length>0){
		        	zTree.removeNode(nodes[0]);
		        }
			}
		});
	}
	
	function showMenu() {
		initAddFormTree();
		var cityObj = $("#parent");
		var cityOffset = $("#parent").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "parent" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	//ztree  end


$(function () {
	queryList();
});

function queryList(){
	$.ajax({
	    url: ctx+"/cons/lib/patType/searchList",
	    datatype: 'json',
	    type: "Post",
	    data: $('#search_form').serialize(),
	    success: function (data) {
	      if (data != null) {
	    	  var htmlTable="";
	        $.each(data.aaData, function (index, item) { //遍历返回的json
	          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.code + "</td><td>" + item.type + "</td><td>" + item.createDate + "</td><td>" + item.updateDate + "</td><td>" + item.remarks + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	        });
	        if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='8'>没有查询到记录!</td></tr>";
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
        url: ctx+"/cons/lib/patType/searchList",
        type: "Post",
        data: $('#search_form').serialize(),
        success: function (data) {
          if (data != null) {
        	  var htmlTable="";
            $.each(data.aaData, function (index, item) { //遍历返回的json
            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.code + "</td><td>" + item.type + "</td><td>" + item.createDate + "</td><td>" + item.updateDate + "</td><td>" + item.remarks + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
            });
            if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='8'>没有查询到记录!</td></tr>";
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
		$.get(ctx+"/cons/lib/patType/del", {
			id : id
		}, function(data) {
			var datastr = eval('(' + data + ')');
			if (datastr.status == 1) {
				queryList();
				alert("删除成功!");
			} else {
				alert(datastr.resultMsg);
			}
		});
	}
	
}
	// 数据编辑
	function editrow(id) {
		clearForm();
		$.get(ctx+"/cons/lib/patType/update", {
			id : id
		}, function(data) {
			if (data != null) {
				var jsondata = eval('(' + data + ')');
				var fields = $("#add-form").serializeArray();
				jQuery.each(fields, function(i, field) {
					//jquery根据name属性查找
					if ((field.name).indexOf(".") != -1) {//对name是.id的字段特殊处理
						var values = field.name.split(".");
						$("#add-form :input[name='" + field.name + "']").val(jsondata[values[0]][values[1]]);
					} else {
						$("#add-form :input[name='" + field.name + "']").val(jsondata[field.name]);
					}
				});
				$("#my_modal_title").html("病例库类型-修改");
				$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
			}
		});
	}
	//进入添加页面
	function add(){
		clearForm();
		$("#my_modal_title").html("病例库类型-添加");
		$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
	}
	
	//提交弹出框（新增修改操作）
	function myFormSubmit() {
		var name = $("#add-form #name").val();
		if(name==""){
			alert("请输入名称！");
			return;
		}
		if(validate_form($("#add-form"),[code,type,sort])){
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
