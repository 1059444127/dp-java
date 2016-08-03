/**
 * zl
 * 用户管理页面js
 */

//ztree 使用start
	var myTree;
	$(document).ready(function () {
		setting1.callback={onClick: showMyTreeOffice};
		setting2.callback={onClick: onClick};
		 $.fn.zTree.init($("#treeParent"),setting1, [{"id":1,"pId":0,"name":'初始化'}]);
		 myTree = $.fn.zTree.getZTreeObj("treeParent");
		 initTree();
	});
	function initTree() {
		$.get(ctx+"/admin/office/tree",{},function(data){
			if(data == null){
				alert("树菜单加载异常!");
			}else{
				//zTree左侧树目录
		         $.fn.zTree.init($("#MyTreeOffice"),setting1, myTree.transformTozTreeNodes(data));
			}
		});
	}
	function showMyTreeOffice(event, treeId, treeNode){
		if(treeNode.type == 1){//公司
			$("#hiddenCompanyId").val(treeNode.id);
			$("#hiddenOfficeId").val("");
		}else if(treeNode.type == 2){//部门
			$("#hiddenCompanyId").val("");
			$("#hiddenOfficeId").val(treeNode.id);
		}else{
			$("#hiddenCompanyId").val("-1");
			$("#hiddenOfficeId").val("-1");
		}
		queryList();
	}
	
	//归属部门树选择
	function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeOffice"),
			nodes = zTree.getSelectedNodes();
			if(nodes.length>0){
				if(nodes[0].type!=2){//只能选择部门
					return;
				}
				$("#officeName").val(nodes[0].name);
				$("#officeId").val(nodes[0].id);
				$("#companyId").val(nodes[0].pId);
			}
			hideMenu();
		}
	//初始化下拉框树，并隐藏公司与部门以外的类型数据
	function initAddFormTree(){
		$.get(ctx+"/admin/office/tree",{},function(data){
			if(data == null){
				alert("树菜单加载异常!");
			}else{
				//zTree上级机构下拉框树目录
		        $.fn.zTree.init($("#treeOffice"), setting2,  myTree.transformTozTreeNodes(data));
		        var zTree = $.fn.zTree.getZTreeObj("treeOffice");
		        var nodes = zTree.getNodes();
		        for(n in nodes){
		        	if(!(nodes[n].type == 1 || nodes[n].type == 2)){
		        		zTree.removeNode(nodes[n]);
		        	}
		        }
			}
		});
	}
	
	function showMenu() {
		initAddFormTree();
		var cityObj = $("#officeName");
		var cityOffset = $("#officeName").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "officeName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}
	//ztree  end
	
	$(function () {
		queryList();
	});
	//list  start
	function queryList(){
		$.ajax({
		    url: ctx+"/admin/user/searchList",
		    datatype: 'json',
		    type: "Post",
		    data: $('#user_form').serialize(),
		    success: function (data) {
		      if (data != null) {
		    	  var htmlTable="";
		        $.each(data.aaData, function (index, item) { //遍历返回的json
		          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.company.name + "</td><td>" + item.office.name + "</td><td>" + item.loginName + "</td><td>" + item.name + "</td><td>" + item.phone + "</td><td>" + item.mobile + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
		        });
		        if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='8'>没有查询到记录!</td></tr>";
	            }
		        $("#user_list tbody").html(htmlTable);
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
	        url: ctx+"/admin/user/searchList",
	        type: "Post",
	        data: $('#user_form').serialize(),
	        success: function (data) {
	          if (data != null) {
	        	  var htmlTable="";
	            $.each(data.aaData, function (index, item) { //遍历返回的json
	            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.company.name + "</td><td>" + item.office.name + "</td><td>" + item.loginName + "</td><td>" + item.name + "</td><td>" + item.phone + "</td><td>" + item.mobile + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	            });
	            if(htmlTable == ""){
	            	htmlTable += "<tr><td colspan='8'>没有查询到记录!</td></tr>";
	            }
	            $("#user_list tbody").html(htmlTable);
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
			$.get(ctx+"/admin/user/del", {
				id : id
			}, function(data) {
				var datastr = eval('(' + data + ')');
				if (datastr.status == 1) {
					queryList();//刷新表数据
					initTree();//刷新树数据
				} else {
					alert(datastr.resultMsg);
				}
			});
		}
	}
	// 数据编辑
	function editrow(id) {
		$("#userPassword").hide();
		clearForm();
		$.get(ctx+"/admin/user/update", {
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
					}else {
						$("#add-form :input[name='" + field.name + "']").val(jsondata[field.name]);
					}
				});
				var roles = jsondata.roleList;
				for(n in roles){
					$("#add-form input:checkbox[name='roleList'][value='"+roles[n].id+"']").attr('checked','true');
				}
				
				$("#my_modal_title").html("用户管理-修改");
				$("#add-div").modal({
					backdrop : 'static',
					keyboard : false
				});//弹出框show
			}
		});
	}
	//进入添加页面
	function add(){
		$("#userPassword").show();
		clearForm();
		$("#my_modal_title").html("用户管理-添加");
		//backdrop 为 static 时，点击模态对话框的外部区域不会将其关闭。
		//keyboard 为 false 时，按下 Esc 键不会关闭 Modal。
		$("#add-div").modal({
			backdrop : 'static',
			keyboard : false
		});//弹出框show
	}
	
	//提交弹出框（新增修改操作）
	function myFormSubmit() {
		var arrData = [document.getElementById("name"),document.getElementById("officeName"),loginName,email];
		if(validate_form($("#add-form"),arrData)){
			var password = $("#password").val();
			if(($("#id").val()=="" || $("#id").val() == null) && (password == null || password == "")){
				alert("请输入密码!");
				return;
			}
			if($("input:checkbox[name='roleList']:checked").val() == 'undefined'){
				alert("请选择角色!");
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
						initTree();//刷新树数据
						alert("保存成功!");
					}
				});
			}
		}
	}
	
	//两次密码是否一致
	function contrast() {
		if ($("#password").val() != $("#repassword").val()
				&& $("#repassword").val() != '') {
			$("#user_msg div").html("两次密码不一致，请重新输入!");
			$("#user_msg").show();
		} else {
			$("#user_msg div").html("");
			$("#user_msg").hide();
		}
	}
	//清除弹出框表单数据
	function clearForm() {
		$("#id").val("");
		$("#companyId").val("");
		$("#officeId").val();
		$("input:checkbox[name='roleList']").removeAttr("checked");
		var myform = document.getElementById("add-form");
		myform.reset();//清除原数据
	}