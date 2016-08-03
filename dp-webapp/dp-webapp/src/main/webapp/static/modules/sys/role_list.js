/**
 * zl
 * 用户管理页面js
 */

//ztree 使用start
	var myTree;
	$(document).ready(function () {
		 setting.callback={onCheck: onCheck};
		 setting2.callback={onClick: onClick};
		 $.fn.zTree.init($("#treeParent"),setting1, [{"id":1,"pId":0,"name":'初始化'}]);
		 myTree = $.fn.zTree.getZTreeObj("treeParent");
		 
	});
	//归属部门树选择
	function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeRole"),
			nodes = zTree.getSelectedNodes();
			if(nodes.length>0){
				$("#officeName").val(nodes[0].name);
				$("#officeId").val(nodes[0].id);
			}
			hideMenu();
		}
	//菜单树选择
	function onCheck(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeRole"),
			nodes = zTree.getCheckedNodes(true);
			var menuName = "";
			var menuids = "";
			if(nodes.length>0){
				for(n in nodes){
					menuName +=","+nodes[n].name;
					menuids +=","+nodes[n].id;
				}
				$("#menuName").val(menuName.substring(1));
				$("#menuId").val(menuids.substring(1));
			}else{
				$("#menuName").val("");
				$("#menuId").val("");
			}
			//hideMenu();
		}
	//初始化组织机构下拉框树
	function initAddFormTree(){
		$.get(ctx+"/admin/office/tree",{},function(data){
			if(data == null){
				alert("树菜单加载异常!");
			}else{
				//zTree机构下拉框树目录
		        $.fn.zTree.init($("#treeRole"), setting2,  myTree.transformTozTreeNodes(data));
			}
		});
	}
	//菜单树初始化
	function initAddFormMenuTree(){
		$.get(ctx+"/admin/menu/tree",{},function(data){
			if(data == null){
				alert("树菜单加载异常!");
			}else{
				var menuIds = ($("#menuId").val()+"").split(",");
				for(m in data){
					for(n in menuIds){
						if(data[m].id == menuIds[n]){
							data[m].checked = true;
						}
					}
				}
				//zTree机构下拉框树目录
		        $.fn.zTree.init($("#treeRole"), setting,  myTree.transformTozTreeNodes(data));
			}
		});
	}
	
	function showMenu(arg) {
		var cityObj;
		var cityOffset;
		if(arg == 'menu'){
			initAddFormMenuTree();
			cityObj = $("#menuName");
			cityOffset = $("#menuName").offset();
		}else if(arg == 'office'){
			initAddFormTree();
			cityObj = $("#officeName");
			cityOffset = $("#officeName").offset();
		}
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "officeName" || event.target.id == "menuName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	//ztree  end
	
	//list  start -----------------------------------------
	$(function () {
		queryList();
	});

	function queryList(){
		$.ajax({
		    url: ctx+"/admin/role/searchList",
		    datatype: 'json',
		    type: "Post",
		    data: $('#role_form').serialize(),
		    success: function (data) {
		      if (data != null) {
		    	  var htmlTable="";
		        $.each(data.aaData, function (index, item) { //遍历返回的json
		          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.enname + "</td><td>" + $("#roleType option[value='"+item.roleType+"']").text() + "</td><td><a href='javascript:void(0);' onclick=\"distributionMenu('"+item.id+"')\">分配菜单</a></td><td>"+$("#useable option[value='"+item.useable+"']").text()+"</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
		        });
		        if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='7'>没有查询到记录!</td></tr>";
	            }
		        $("#role_list tbody").html(htmlTable);
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
	        url: ctx+"/admin/role/searchList",
	        type: "Post",
	        data: $('#role_form').serialize(),
	        success: function (data) {
	          if (data != null) {
	        	  var htmlTable="";
	            $.each(data.aaData, function (index, item) { //遍历返回的json
	            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.name + "</td><td>" + item.enname + "</td><td>" + $("#roleType option[value='"+item.roleType+"']").text() + "</td><td><a href='javascript:void(0);' onclick=\"distributionMenu('"+item.id+"')\">分配菜单</a></td><td>"+$("#useable option[value='"+item.useable+"']").text()+"</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	            });
	            if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='7'>没有查询到记录!</td></tr>";
	            }
	            $("#role_list tbody").html(htmlTable);
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

	// 数据删除
	function del(id) {
		if (confirm("确定要删除吗?")) {
			$.get(ctx+"/admin/role/del", {
				id : id
			}, function(data) {
				var datastr = eval('(' + data + ')');
				if (datastr.status == 1) {
					queryList();
					//setTimeout("showContent('"+ctx+"/admin/role/list')",500);
				} else {
					alert(datastr.resultMsg);
				}
			});
		}
	}
	
	// 数据编辑
	function editrow(id) {
		clearForm();
		$.get(ctx+"/admin/role/update", {
			id : id
		}, function(data) {
			if (data != null) {
				var jsondata = eval('(' + data + ')');
				var fields = $("#add-form").serializeArray();
				jQuery.each(fields, function(i, field) {
					try {
						//jquery根据name属性查找
						if ((field.name).indexOf(".") != -1) {//对name是.id的字段特殊处理
							var values = field.name.split(".");
							$("#add-form :input[name='" + field.name + "']").val(jsondata[values[0]][values[1]]);
						} else if(field.name == 'menuList'){//菜单 
							var menuIds = "";
							for(n in jsondata.menuList){
								menuIds +=","+jsondata.menuList[n].id;
							}
							if(menuIds!="")
								$("#add-form :input[name='" + field.name + "']").val(menuIds.substring(1));
						}else if(field.name =='menuName'){//菜单
							var menuNames = "";
							for(n in jsondata.menuList){
								menuNames +=","+jsondata.menuList[n].name;
							}
							if(menuNames!="")
								$("#add-form :input[name='" + field.name + "']").val(menuNames.substring(1));
						}else {
							$("#add-form :input[name='" + field.name + "']").val(jsondata[field.name]);
						}
					} catch (e) {
						$("#add-form :input[name='" + field.name + "']").val("");
					}
				});
				$("#my_modal_title").html("角色管理-修改");
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
		$("#my_modal_title").html("角色管理-添加");
		//backdrop 为 static 时，点击模态对话框的外部区域不会将其关闭。
		//keyboard 为 false 时，按下 Esc 键不会关闭 Modal。
		$("#add-div").modal({
			backdrop : 'static',
			keyboard : false
		});//弹出框show
	}
	
	//提交弹出框（新增修改操作）
	function myFormSubmit() {
		var name = $("#add-form #name").val();
		if(name==''){
			alert("角色名称不能为空！");
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
					queryList();//刷新列表数据
					//setTimeout("showContent('"+ctx+"/admin/role/list')",500);
					alert("保存成功!");
				}
			});
		}
	}
	//清除弹出框表单数据
	function clearForm() {
		$("#id").val("");
		$("#menuId").val("");
		$("#officeId").val("");
		var myform = document.getElementById("add-form");
		myform.reset();//清除原数据
	}

	//列表中的分配菜单弹出框 start ------------------------------
	setting3.check = {enable: true,chkStyle: "checkbox"};
	setting3.callback = {onCheck: onListTreeCheck};
	var subForm = false;
	function distributionMenu(id){
		subForm = false;
		$("#roleId").val(id);
		$.get(ctx+"/admin/role/updateMenu",{id:id},function(data){
			
			$.get(ctx+"/admin/menu/tree",{},function(treeData){
				if(treeData == null){
					alert("树菜单加载异常!");
				}else{
					for(m in treeData){
						for(n in data){
							if(treeData[m].id == data[n].menu_id){
								treeData[m].checked = true;
							}
						}
					}
					//zTree机构下拉框树目录
			        $.fn.zTree.init($("#treeRoleMenu"), setting3,  myTree.transformTozTreeNodes(treeData));
				}
			});
			
		});
		$("#menu-div").modal({
			backdrop : 'static',
			keyboard : false
		});//弹出框show
	}
	
	//菜单树选择
	function onListTreeCheck(e, treeId, treeNode) {
			subForm = true;
			var zTree = $.fn.zTree.getZTreeObj("treeRoleMenu"),
			nodes = zTree.getCheckedNodes(true);
			var menuids = "";
			if(nodes.length>0){
				for(n in nodes){
					menuids +=","+nodes[n].id;
				}
				$("#listMenuId").val(menuids.substring(1));
			}else{
				$("#listMenuId").val("");
			}
		}

	function listMenuTreeSubmit(){
		if(subForm){//没更改就不能提交
			if (confirm("确定要保存数据吗?")) {
				$.ajax({
					cache : true,
					type : "POST",
					url : ctx+"/admin/role/updateMenu",
					data : {id:$("#roleId").val(),menuList:$("#listMenuId").val()},
					async : false,
					error : function(data) {
						alert("更新菜单失败!");
					},
					success : function(data) {
						$("#menu-div").modal("hide");
						alert("更新成功！");
					}
				});
			}
		}
	}