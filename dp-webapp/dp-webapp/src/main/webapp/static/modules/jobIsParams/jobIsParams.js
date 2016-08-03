

$(function () {
	queryList();
});

function queryList(){
	$.ajax({
	    url: ctx+"/admin/job/list",
	    datatype: 'json',
	    type: "Post",
	    data: $('#searchForm').serialize(),
	    success: function (data) {
	      if (data != null) {
	    	  var htmlTable="";
	        $.each(data.aaData, function (index, item) { //遍历返回的json
	          htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.userName + "</td><td>" + item.userPassword + "</td><td>" + item.url + "</td><td>" + item.createByName + "</td><td>" + item.createDate + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
	        });
	        if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='7'>没有查询到记录!</td></tr>";
            }
	        $("#jobTable tbody").html(htmlTable);
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
        url: ctx+"/admin/job/list",
        type: "Post",
        data: $('#searchForm').serialize(),
        success: function (data) {
          if (data != null) {
        	  var htmlTable="";
            $.each(data.aaData, function (index, item) { //遍历返回的json
            	htmlTable +="<tr><td>" + (parseInt(data.iDisplayStart)+index+1) + "</td><td>" + item.userName + "</td><td>" + item.userPassword + "</td><td>" + item.url + "</td><td>" + item.createByName + "</td><td>" + item.createDate + "</td><td><button id='editrow' onclick=\"editrow('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' onclick=\"del('"+item.id+"');\" class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button></td></tr>";
            });
            if(htmlTable == ""){
            	htmlTable += "<tr><td colspan='7'>没有查询到记录!</td></tr>";
            }
            $("#jobTable tbody").html(htmlTable);
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

function searchJob(){
	queryList();
}

function resetSearch(){
	document.getElementById("searchForm").reset();
}

function add() {
	var myform = document.getElementById("form");
	myform.reset();
	$("#id").val("");
	$("#userName").val('');
	$("#userPassword").val('');
	$("#url").val('');
	$("#remarks").val('');
	$("#my_modal_title").html("新建");
	$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
}

function editrow(id) {
	var myform = document.getElementById("form");
	myform.reset();
	 $.ajax({  
         url:ctx+'/admin/job/edit',  
         data:{"id":id},  
         type:"POST",  
         cache:false,  
         async:true,  
         dateType:"json",  
         error:function(XMLHttpRequest,textStatus) {  
             alert('服务器连接失败，请稍候重试！');  
         },  
         success: function(map){  
			$("#id").val(map.job.id);
			$("#userName").val(map.job.userName);
			$("#userPassword").val(map.job.userPassword);
			$("#url").val(map.job.url);
			$("#remarks").val(map.job.remarks);
			
            $("#my_modal_title").html("编辑");
         	$("#add-div").modal({backdrop: 'static', keyboard: false});//弹出框show
         }  
   }); 
	
}

function del(id){  
    if(confirm(' 确认要删除吗？')){  
       $.ajax({  
              url:ctx+'/admin/job/del',  
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
   				  //刷新左侧菜单栏
                  searchJob();
              }  
        });   
    }  
}  

function save(){
	var url = $("#url").val();
	if(isNull(url)) {
		alert("url不能为空！");
		return;
	}
    if(confirm(' 确认要保存吗？')){  
    	var jsonData = $("#form").serializeArray();
       $.ajax({  
              url:ctx+'/admin/job/save',  
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
     				searchJob();
                  }
              }  
        });   
    }  
} 