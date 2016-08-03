/**
 * 切片详情
 */

/* 分享插件配置 start*/
//全局变量，动态的文章ID
var ShareId = "";
//绑定所有分享按钮所在A标签的鼠标移入事件，从而获取动态ID
$(function () {
    $(".bdsharebuttonbox a").mouseover(function () {
        ShareId = $(this).attr("data-id");
    });
});
/* 
* 动态设置百度分享URL的函数,具体参数
* cmd为分享目标id,此id指的是插件中分析按钮的ID
*，我们自己的文章ID要通过全局变量获取
* config为当前设置，返回值为更新后的设置。
*/
function SetShareUrl(cmd, config) {            
    if (ShareId) {
        config.bdUrl = "http://10.5.5.112:8080/dp-webapp/cons/lib/caseLib/detail?id=" + ShareId;    
    }
    return config;
}

window._bd_share_config = {
	"common" : {
		onBeforeClick:SetShareUrl,
		"bdSnsKey" : {},
		"bdText" : "",
		"bdMini" : "2",
		"bdPic" : "",
		"bdStyle" : "0",
		"bdSize" : "16"
	},
	"share" : {},
	"image" : {
		"viewList" : [ "qzone", "tsina",
				"tqq", "renren", "weixin" ],
		"viewText" : "分享到：",
		"viewSize" : "16"
	},
	"selectShare" : {
		"bdContainerClass" : null,
		"bdSelectMiniList" : [ "qzone",
				"tsina", "tqq", "renren",
				"weixin" ]
	}
};
$(function(){
	//插件的JS加载部分getElementsByTagName('head')[0] || body
	with (document)0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+ ~(-new Date() / 36e5)];
});
/* 分享插件配置 end*/


$(function (){ 
	$("[data-toggle='popover']").popover();
  });
$(function(){
	showGuestBooks();
	showRVSlide();
	saveRecentVisit();
});
//记录最近访问该切片的用户
function saveRecentVisit(){
	$.ajax({
        url: ctx+"/cons/lib/recentVisit/save",
        type: "Post",
        data: {userId:$("#userId").val(),"caseLibSlide.id":slideId},
        success: function (data) {}
	})
}
//显示当前用户最近访问的切片
function showRVSlide(){
	$.ajax({
        url: ctx+"/cons/lib/recentVisit/listNoPage",
        type: "Post",
        data: {userId:$("#userId").val()},
        success: function (data) {
        	var html = "";
        	for(i in data){
        		html += "<div class='img_div text-center'><a href='"+ctx+"/cons/lib/caseLib/detail?id="+data[i].caseLibSlide.id+"'><img src='"+data[i].caseLibSlide.overview+"' alt='' width='100%'/></a>"+data[i].caseLibSlide.antitbodyName+"</div>";
        	}
        	$("#RVSlide").html(html);
        },
        error:function(){
        	
        }
	})
}
//最近访问图片点击事件
function slideDetail(id){
	showContent(ctx+'/cons/lib/caseLib/detail?id='+id);
}
//显示留言信息列表
function showGuestBooks(){
	$.ajax({
        url: ctx+"/cons/lib/guestBook/listNoPage",
        type: "Post",
        data: {libSlideId:slideId},
        success: function (data) {
        	var html = "";
        	var userName = "游客";
        	for(i in data){
        		if(data[i].user!=null&&data[i].user.name!=null&&data[i].user.name!=""){
        			userName = data[i].user.name;
        		}
        		html += "<div class='row'><span class=\"glyphicon glyphicon-user\"></span>&nbsp;"+userName+"："+data[i].content+"&nbsp;&nbsp;("+data[i].createDate+")</div>";
        	}
        	$("#messageContent").html(html);
        },
        error:function(){
        	
        }
	})
}
//发布留言
function release(){
	$.ajax({
        url: ctx+"/cons/lib/guestBook/save",
        type: "Post",
        data: {"user.id":$("#userId").val(),libSlideId:slideId,content:$("#releaseContent").val()},
        success: function (data) {
        	if(data=='1'){
        		$("#releaseContent").val("");
        		showGuestBooks();
        	}
        },
        error:function(){
        	
        }
	})
}
function showUserInfo(userId,id){
	$.ajax({
        url: ctx+"/admin/user/update",
        type: "Get",
        data: {id:userId},
        success: function (data) {
        	if(data!=null){
	        	try {
					var jsonData = eval('(' + data + ')');
					var html = "姓&nbsp;&nbsp;名：" + jsonData.name + "<br/>登录名："
							+ jsonData.loginName + "<br/>所属部门："
							+ jsonData.office.name;
					$("#" + id).html(html);
				} catch (e) {
					$("#"+id).html("姓&nbsp;&nbsp;名： &nbsp;&nbsp;<br/>&nbsp;登录名：匿名访问<br/>所属部门：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
				}
        	}else{
        		$("#"+id).html("姓&nbsp;&nbsp;名： &nbsp;&nbsp;<br/>&nbsp;登录名：匿名访问<br/>所属部门：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp");
        	}
        },
        error:function(){
        	
        }
	})
}

//function onclikMenu(){
//	showContent('/dp-webapp/cons/lib/caseLib/index');
//}





