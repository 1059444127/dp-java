$(function(){  
	
	loadLeftMenuList();
	
});


function loadLeftMenuList() {
	$.ajax({  
        url:ctx+'/admin/menu/list',  
        type:"POST",  
        cache:false,  
        async:true,  
        dateType:"json",  
        error:function(XMLHttpRequest,textStatus) {  
            alert('服务器连接失败，请稍候重试！');  
        },  
        success: function(data){ 
        	if(data.success) {
        		var menu = data.data.menu;
        		var str = "";//<li class='header'>"+menu.name+"</li>
        		if(data.data.child) {
        			str += initLeftMenu(data.data.childList);
        		}
        		$("#leftMenu").html(str);
        	}
        }  
  });  
}

function initLeftMenu(data) {
	var str = "";
	$(data).each(function(index,item){
		var menu = item.menu;
		if(item.child) {
			if(index == 0) {
				str += "<li class='treeview active'>";
			} else {
				str += "<li class='treeview'>";
			}
			if(isNull(menu.href)) {
				str += "<a href='#'>";
			} else {
				str += "<a href='showContent('"+ctx+menu.href+"')' target='"+menu.target+"'>";
			}
			str += "<i class='"+menu.icon+"'></i>";
			str += " <span>"+menu.name+"</span>";
			str += "<i class='fa fa-angle-left pull-right'></i>";
			str += "</a>";
			str += "<ul class='treeview-menu'>";
			str += initLeftMenu(item.childList);
			str += "</ul>";
			str += "</li>";
		} else {
			str += "<li>";
			if(isNull(menu.href)) {
				str += "<a href='#'>";
			} else {
				str += "<a href='#' onclick=\"showContent('"+ctx+menu.href+"')\" target='"+menu.target+"'>";
			}
			str += "<i class='fa "+menu.icon+"'></i>";
			str += " <span>"+menu.name+"</span>";
			str += "</a>";
			str += "</li>";
		}
	});
	return str;
}

function isNull(str) {
	if(str == null || str == '' || str == undefined) {
		return true;
	} else {
		return false;
	}
}

/**
 * 页面跳转
 * @param arg
 */
function showContent(arg){
	if(arg.indexOf("druid/index.html") == -1){
		$.get(arg,{},function(data){
			$("#contentText").html(data);
		});
	}else{
		$("#contentText").html("<iframe id=\"indexIFrame\" src=\"\" style=\"width:100%;height:84%;\"></iframe>");
		$("#indexIFrame").attr("src",arg);
	}
}

function logout(obj){
	var url = $(obj).attr('href');
	if(confirm("确定要退出吗？")){
    location.href = url + '?__t=' + Math.random();
	}
}

