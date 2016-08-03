function getResult(item) {
	var result= '<tr id=\''+item.id+'\' pid=\''+item.parentId+'\'>'+
			'<td style="text-align:left">'+item.name+'</td>'+  
			'<td>'+item.href+'</td>'+    
			'<td style="text-align:center;">'+getSort(item.sort)+'</td>'+  
			'<td>'+(item.isShow == 1 ? "显示" : "不显示")+'</td>'+  
			'<td style="text-align:left">'+item.permission+'</td>'+  
			'<td class="align-left" >'+  
			'<a href="#" onclick="addMenuAdmin(\''+item.name+'\',\''+item.id+'\')">添加子菜单</a>';
			if(item.parentId != "00") {
				result += '&nbsp;&nbsp;&nbsp;<a href="javascript:editMenuAdmin(\''+item.id+'\')">修改</a>'+  
				'&nbsp;&nbsp;&nbsp;<a onclick="delMenuAdmin(\''+item.id+'\')" href="javascript:;">删除</a>'; 
			}
			result += '</td>'+
			'</tr>';
	return result;
}

function getSort(sort) {
	if(sort == null || sort == '' || sort == undefined) {
		return '0';
	} else {
		return sort;
	}
}

var option;
$(function(){  

	loadMenuList('0');
	
	option = {
        theme:'vsStyle',
        expandLevel : 3,
        beforeExpand : function($treeTable, id) {
            //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
            if ($('.' + id, $treeTable).length) { return; }
            //这里的html可以是ajax请求
            var html = '<tr id="8" pId="6"><td>5.1</td><td>可以是ajax请求来的内容</td></tr>'
                     + '<tr id="9" pId="6"><td>5.2</td><td>动态的内容</td></tr>';

            $treeTable.addChilds(html);
        },
        onSelect : function($treeTable, id) {
            window.console && console.log('onSelect:' + id);
        }
    };
//    $('#treeTable').treeTable(option);
});  

function seatch() {
	
}

function loadMenuList(parentId) {
	$.ajax({  
        url:ctx+'/admin/menu/parameterList',  
        data:{"parentId":parentId},  
        type:"POST",  
        cache:false,  
        async:true,  
        dateType:"json",  
        error:function(XMLHttpRequest,textStatus) {  
            alert('服务器连接失败，请稍候重试！');  
        },  
        success: function(data){  
        	var table = '<table id="treeTable" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">';
        	table += '<thead>';
        	table += '<th width="20%">名称</th>';
    		table += '<th width="20%">链接</th>';
			table += '<th width="10%">排序</th>';
			table += '<th width="10%">可见</th>';
			table += '<th width="20%">权限标识</th>';
			table += '<th width="20%">操作</th>';
			table += '</thead>';
			table += '<tbody>';
			table += '</tbody>';
			table += '</table>';
			$("#table-div").html(table);	
        	if(data) {
	        	var str = '';
	        	$.each(data, function(index, item){
	        		str += getResult(item);
	        	});
	        	$("#treeTable tbody").html(str);
        	}
        	
        	$('#treeTable').treeTable(option);
        }  
  });  
}

function addMenuAdmin(parentName, parentId) {
	var myform = document.getElementById("menuForm");
	myform.reset();
	$("#id").val("");
	$("#parentName").val(parentName);
	$("#parentId").val(parentId);
	$("#name").val('');
	$("#href").val('');
	$("#target").val('');
	$("#sort").val('');
	$("#remarks").val('');
	$("#permission").val('');
	$("#my_modal_title").html("新建菜单");
	$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
}

function editMenuAdmin(id) {
	var myform = document.getElementById("menuForm");
	myform.reset();
	 $.ajax({  
         url:ctx+'/admin/menu/edit',  
         data:{"id":id},  
         type:"POST",  
         cache:false,  
         async:true,  
         dateType:"json",  
         error:function(XMLHttpRequest,textStatus) {  
             alert('服务器连接失败，请稍候重试！');  
         },  
         success: function(map){  
			$("#id").val(map.data.id);
			$("#parentName").val(map.parentName);
			$("#parentId").val(map.parentId);
			$("#name").val(map.data.name);
			$("#href").val(map.data.href);
			$("#target").val(map.data.target);
			$("#icon").val(map.data.icon);
			$("#sort").val(getSort(map.data.sort));
			$("#isShow").val(map.data.isShow);
			$("#remarks").val(map.data.remarks);
			$("#permission").val(map.data.permission);
			
            $("#my_modal_title").html("编辑菜单-"+map.data.name);
         	$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
         }  
   }); 
	
}

function delMenuAdmin(id){  
    if(confirm(' 确认要删除吗？')){  
       $.ajax({  
              url:ctx+'/admin/menu/del',  
              data:{"id":id},  
              type:"POST",  
              cache:false,  
              async:true,  
              dateType:"json",  
              error:function(XMLHttpRequest,textStatus) {  
                  alert('服务器连接失败，请稍候重试！');  
              },  
              success: function(response){  
                  if(!response.success){  
                        alert(response.message);  
                        return;  
                  }  
                  loadMenuList('0');
   				  //刷新左侧菜单栏
                  loadLeftMenuList();
              }  
        });   
    }  
}  

function saveMenuAdmin(){  
	var name = $("#name").val();
	var sort = $("#sort").val();
	if(isNull(name)) {
		alert("菜单名称不能为空！");
		return;
	}
	if(isNull(name)) {
		alert("排序不能为空！");
		return;
	}
    if(confirm(' 确认要保存吗？')){  
    	var jsonData = $("#menuForm").serializeArray();
       $.ajax({  
              url:ctx+'/admin/menu/save',  
              data:jsonData,  
              type:"POST",  
              cache:false,  
              async:true,  
              dateType:"json",  
              error:function(XMLHttpRequest,textStatus) {  
                  alert('服务器连接失败，请稍候重试！'); 
              },  
              success: function(response){  
                  if(!response.success){  
                        alert(response.message);  
                  } else {
                	  alert(response.message);
     				 $("#add-div").modal("hide");
     				loadMenuList('0');
     				//刷新左侧菜单栏
     				loadLeftMenuList();
                  }
              }  
        });   
    }  
} 






