/**
 * zl
 * 区域管理页面js
 */

//ztree 使用start
	var myTree;
	$(document).ready(function () {
		setting1.callback={onClick: showMyTreearea};
		setting2.callback={onClick: onClick};
		 $.fn.zTree.init($("#treeParent"),setting1, [{"id":1,"pId":0,"name":'初始化'}]);
		 myTree = $.fn.zTree.getZTreeObj("treeParent");
		 initTree();
	});
	
	 function initTree() {
		 $.get(ctx+"/admin/area/tree",{},function(data){
			 if(data == null){
				 alert("树菜单加载异常!");
			 }else{
				//zTree左侧树目录
		         $.fn.zTree.init($("#MyTreearea"),setting1, myTree.transformTozTreeNodes(data));
			 }
		 });
	 }
	function showMyTreearea(event, treeId, treeNode){
		$("#parentIds").val(treeNode.id);
		queryList();
	}
	
	//上级机构树选择
	function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treearea"),
			nodes = zTree.getSelectedNodes();
			if(nodes.length>0){
				$("#parent").val(nodes[0].name);
				$("#parentId").val(nodes[0].id);
			}
			hideMenu();
		}
	//初始化上级机构下拉框树，并隐藏当前机构及其子机构
	function initAddFormTree(){
		$.get(ctx+"/admin/area/tree",{},function(data){
			if(data == null){
				alert("树菜单加载异常!");
			}else{
				//zTree上级机构下拉框树目录
		        $.fn.zTree.init($("#treearea"), setting2,  myTree.transformTozTreeNodes(data));
		        var zTree = $.fn.zTree.getZTreeObj("treearea"),
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
	
	//list  start
	$(function(){
		queryList();
	});
	function queryList(){
		$.ajax({
		    url: ctx+"/admin/area/searchList",
		    datatype: 'json',
		    type: "Post",
		    data: $('#area_form').serialize(),
		    success: function (data) {
		      if (data != null) {
		    	  var htmlTable="";
		        $.each(data.aaData, function (index, item) { //遍历返回的json
		          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.code + "</td><td>" + $("#type option[value='"+item.type+"']").text() + "</td><td>" + item.remarks + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
		        });
		        if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
	            }
		        $("#area_list tbody").html(htmlTable);
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
	        url: ctx+"/admin/area/searchList",
	        type: "Post",
	        data: $('#area_form').serialize(),
	        success: function (data) {
	          if (data != null) {
	        	  var htmlTable="";
	            $.each(data.aaData, function (index, item) { //遍历返回的json
	            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.code + "</td><td>" + $("#type option[value='"+item.type+"']").text() + "</td><td>" + item.remarks + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	            });
	            if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='6'>没有查询到记录!</td></tr>";
	            }
	            $("#area_list tbody").html(htmlTable);
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

	//选中变色
	$('#area_list tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			areaTable.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	// 数据删除
	function del(id) {
		if (confirm("确定要删除吗?")) {
			$.get(ctx+"/admin/area/del", {
				id : id
			}, function(data) {
				var datastr = eval('(' + data + ')');
				if (datastr.status == 1) {
					aqueryList();//刷新表数据
					initTree();//刷新树数据
				} else {
					alert(datastr.resultMsg);
				}
			});
		}
	}
	// 数据编辑
	function editrow(id) {
		clearForm();
		$.get(ctx+"/admin/area/update", {
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
				$("#my_modal_title").html("区域管理-修改");
				$("#add-div").modal({
					backdrop : 'static',
					keyboard : false
				});//弹出框show
			}
		});
	}
	//进入添加页面
	function add(){
		clearForm();
		$("#my_modal_title").html("区域管理-添加");
		//backdrop 为 static 时，点击模态对话框的外部区域不会将其关闭。
		//keyboard 为 false 时，按下 Esc 键不会关闭 Modal。
		$("#add-div").modal({
			backdrop : 'static',
			keyboard : false
		});//弹出框show
	}
	
	//提交弹出框表单（新增修改操作）
	function myFormSubmit() {
		if(validate_form($("#add-form"),[document.getElementById("name"),sort])){
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
						queryList();//刷新列表数据
						initTree();//刷新树数据
						alert("保存成功!");
					}
				});
			}
		}
	}
	//清除弹出框表单数据
	function clearForm(){
		$("#id").val("");
		$("#parentId").val("");
		var myform = document.getElementById("add-form");
		myform.reset();//清除原数据
	}

