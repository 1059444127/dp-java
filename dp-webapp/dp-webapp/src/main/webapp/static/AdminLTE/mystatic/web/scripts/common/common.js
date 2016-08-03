//jquery based

 $(function(){ 
               $(".list_table tr:odd").addClass("odd");        //加奇行样式 
               $(".list_table tr:even").addClass("even");        //加偶行样式 
               $(".list_table tr:first").addClass("first");    //为第一行加样式 
               $(".list_table tr:last").addClass("last");        //为最后行加样式 
               //为行元素加上鼠标移入和移出事件 
            $(".list_table tr").mouseover(function() { 
                $(this).addClass("mouseOver")    //加上样式 
            }).mouseout(function() { 
                $(this).removeClass("mouseOver")//去掉样式 
            }); 
            
   //为所有需要验证的字段复制属性    2012-10-20 added     
  $(".valid").each(function(){
  		var parent = $(this).prev("input");
  			if($(this).attr("for")){
  			//一些非常情况下面要为指定的元素绑定信息
  			var id = $(this).attr("for");
  				parent = $("#"+id);
  			}
  		parent.attr("datatype",$(this).attr("lang"));
  		parent.attr("errormsg",$(this).attr("title"));
  		parent.attr("nullmsg",$(this).attr("title"));
  		if(this.id == 'ignore'){
  			parent.attr("ignore",'ignore');
  		}
  });
  //验证表单 2012-10-20 added 
  var valid_Form;
  if($("#opForm").size() == 1){
	  valid_Form = $("#opForm").Validform({
  		tiptype:function(msg){
			alert(msg);
		},
		tipSweep:true
  	});
  }
 
  //对返回上一级按键进行操作
  	if($("#menuId").size() > 0){
  		$("#returnButton").show().bind("click",function(){
  			goback($("#menuId").val());
  		});	
  	}
  
  
 
}) 
$(document).ready(function() {

////////////////////// LEFT菜单 begin //////////////////////
	//设置menu id
	var menuId = "#menu";
	//所有二级菜单隐藏
	$(menuId).find("dd").hide();
	//鼠标划过状态
	$(menuId).find("dt").hover(function(){ $(this).addClass("mouseon")}, function(){$(this).removeClass("mouseon") })
	//有submenu的
	$(menuId).find("dt").filter(function(){return $(this).parent().find("dd").size()!=0}).hover(function(){ $(this).addClass("hover")}, function(){$(this).removeClass("hover") })
	//无submenu的
	$(menuId).find("dt").filter(function(){return $(this).parent().find("dd").size()==0}).attr("class","nosm").hover(function(){ $(this).addClass("nosmhover")}, function(){$(this).removeClass("nosmhover") })
	//点击事件
	$(menuId).find("dt").filter(function(){return $(this).parent().find("dd").size()!=0}).click(function(){
			if($(this).parent().find("dd").get(0).style.display == "block"){
				$(this).parent().find("dd").hide();
				$(this).removeClass("on");
			} else {
				$("dt").removeClass("on").removeClass("nosmon"); 
				$("dd").hide();
				$(this).addClass("on");
				$(this).parent().find("dd").show(); 
			}
	})
	$(menuId).find("dt").filter(function(){return $(this).parent().find("dd").size()==0}).click(function(){
				$("dd").hide();
				$("dt").removeClass("on").removeClass("nosmon"); 
				$(this).addClass("nosmon");
	})
	//所有A标签失焦
	$(menuId).find("a").focus(function(){$(this).get(0).blur()})
	//二级菜单点击样式
	$(menuId).find("dd").click(function(){ $("dd").removeClass("focus");$(this).addClass("focus"); })
////////////////////// LEFT菜单 end ////////////////////////

////////////////////// switchBar begin //////////////////////
	//设置switchBar id
	var switchBarId = "#switchBar";
	$(switchBarId).children().click(function() {
			$("#leftBar").toggle();					//显示与隐藏边栏
			$(this).toggleClass("switchArrowR");	//切换箭头按钮类
	});
////////////////////// switchBar end ////////////////////////

////////////////////// tableHover begin //////////////////////
	//设置tableHover类名
	/*
	var tableHover = ".tableHover";
	$(tableHover).find("tr").hover(function(){ $(this).addClass("trHover")}, function(){$(this).removeClass("trHover") });
	*/
	
////////////////////// tableHover end ////////////////////////

////////////////////// checkBoxSelect begin //////////////////////
	//checkSelector和checkNode值相等的为一组。
	$("input[type=checkbox]").filter(function(){return $(this).attr("checkSelector") != null;}).click(function(){
		var p = $(this);
		$("input[type=checkbox]").filter(function(){return $(this).attr("checkNode") == p.attr("checkSelector") && $(this).attr("disabled")==undefined})
			.each(function(){$(this).get(0).checked = p.attr("checked");});
	})
////////////////////// checkBoxSelect end ////////////////////////

////////////////////// initForm begin //////////////////////
	//如果页面有input或者textarea则聚焦
	//在input中设置nofocus="nofocus"则可在初始化页面时不聚焦
	try{
		$("input[type=text]").focus(function(){ $(this).addClass("onFocus"); })
		$("input[type=text]").blur(function(){ $(this).removeClass("onFocus"); })
		$("textarea").focus(function(){ $(this).addClass("onFocus"); })
		$("textarea").blur(function(){ $(this).removeClass("onFocus"); })
		$("input[type=text]").filter(function(){return $(this).attr("nofocus") != "nofocus"}).get(0).focus();
	}
	catch(e){}
////////////////////// initForm end /////////////////////////

////////////////////// toggle<tbody> begin //////////////////////
	//以subtitle作为需要点击的class
	$(".subtitle").click(function(){
								  var t = $(this).parent().parent().next("tbody");
								  t.toggle();
										
								  if(t.get(0).style.display == "none"){$(this).get(0).className = "subtitleClose"} 
								  else{$(this).get(0).className = "subtitle"}
								  })
////////////////////// toggle<tbody> end ////////////////////////

////////////////////// selectBox左右交换option begin //////////////////////
	//左至右按钮id
	var leftToRight = "#btltr";
	//右至左按钮id
	var RightToleft = "#btrtl";
	//左侧box id
	var boxLeft = "#selLeft";
	//右侧box id
	var boxRight = "#selRight";
	//左至右按钮动作
	$(leftToRight).click(function(){var l = $(boxLeft).get(0);var r = $(boxRight).get(0);
							   		for (var i=0;i<l.length;i++) {
										if(l[i].selected == true){r.options.add(new Option(l[i].text,l[i].value));l.remove(i--);}
									}
							   })
	//右至左按钮动作
	$(RightToleft).click(function(){var l = $(boxLeft).get(0);var r = $(boxRight).get(0);
							   		for (var i=0;i<r.length;i++) {
										if(r[i].selected == true){l.options.add(new Option(r[i].text,r[i].value));r.remove(i--);}
									}
							   })
////////////////////// selectBox左右交换option end ////////////////////////

////////////////////// selectBox上下移动option begin //////////////////////
	//目标box在html页面中设置box的属性值
	//向上按钮 id
	var btup = "#btup";
	//向下按钮 id
	var btdown = "#btdown";
	$(btup).click(function(){var box = $("#" + $(btup).attr("box")).get(0);var k=0;var j=0;
							 for(var i=0;i<box.options.length;i++){
						   	 	 if (box.options[i].selected) {k++;j=i; if(k>1){alert("请选择单个选项进行排序");return false;}}
							 } 
								 if(j==0){return false;}
							 	 var o = box.options[j];
							 	 box.remove(j);
								 box.options.add(o,j-1);
							 })
	$(btdown).click(function(){var box = $("#" + $(btdown).attr("box")).get(0);var k=0;var j=box.options.length;
							 for(var i=0;i<box.options.length;i++){
								 if (box.options[i].selected) {k++;j=i; if(k>1){alert("请选择单个选项进行排序");return false;}}
							 }
								 if(j==box.options.length){return false;}
								 var o = box.options[j];
								 box.remove(j);
								 box.options.add(o,j+1);
							 })
////////////////////// selectBox上下移动option end ////////////////////////
})

//none jquery
////////////////////// popUp弹出窗口 begin //////////////////////
	function popUp(url,width,height,winname,left,top){
		var left = (left==''||left==null)?(screen.width - width)/2:left;
		var top = (top==''||top==null)?(screen.height - height)/2:top;
		var winnames = (winname=='')?'popUpWin':winname;
		window.open(url, winnames, 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+', top='+top+',screenX='+left+',screenY='+top+'');
	}
	
	function popUpModal(url,args,width,height,left,top){
		var left = (left==''||left==null)?(screen.width - width)/2:left;
		var top = (top==''||top==null)?(screen.height - height)/2:top;
		var newWin =  window.showModalDialog(url, args, 'help:0;resizable:1;status=0;scrollbars=0;dialogWidth:'+width+'px;dialogHeight:'+height+'px;center=true');
					//if(newWin){
					// 	 newWin.moveTo(-4,-4);
					//	 newWin.resizeTo(screen.availWidth+9,screen.availHeight+9);
					// 	 newWin.attachEvent("onbeforeunload",function(){
					 		/*callback handle*/
					//	});
					//	newWin.focus();
	 				//	}
		return newWin;
	}
	
	
	//弹出窗口
var popUpWin=0;
function showModWin(URLStr,width, height, winname,left, top){
  var left = (left==''||left==null)?(screen.width/2) - width/2:left;
  var top = (top==''||top==null)?(screen.height/2) - height/2:top;
  if(popUpWin)
  {
    if(!popUpWin.closed) popUpWin.close();
  }
  var winnames = (winname=='')?'popUpWin':winname;
	popUpWin = window.showModalDialog(URLStr,
	"","help:0;resizable:0;status=0;scrollbars=0;dialogWidth:"+width+"px;dialogHeight:"+height+"px;center=true");

}
	
////////////////////// popUp end ////////////////////////

//todo
////////////////////// begin ////////////////////////
function toShow(id){
	document.getElementById(id).style.display = "";
}

function toHide(id){
	document.getElementById(id).style.display = "none";
}
////////////////////// end ////////////////////////

////////////////////// liaoxin 8.20 add////////////////////////
//show and hide
////////////////////// begin ////////////////////////
function showAndHide(sid){
			var target = document.getElementById(sid);
			if(target.style.display == "none"){
				target.style.display = "";
			} else {
				target.style.display = "none";
			}
		}
function showAndHideCheckbox(c,sid){
			var target = document.getElementById(sid);
			if(c.checked == true){
				target.style.display = "";
			} else {
				target.style.display = "none";
			}
		} 
//c is checkbox,sid is area.
 function showRadio(hidId,showId,radioName){
          var showid = document.getElementById(showId);
          var hideid = document.getElementById(hidId);
          var radios  =  document.getElementsByName(radioName);
          for (var i =0;i <radios.length; i++){
               if(radios[0].checked){
				   showid.style.display ="none";hideid.style.display="";
				   }
	           if(radios[1].checked){
				   showid.style.display ="";hideid.style.display="none";
				   }
   }
 }
////////////////////// end ////////////////////////
//show or hide twoid
////////////////////// begin ////////////////////////
function toShowTwo(id1,id2){
	document.getElementById(id1).style.display = "";
	document.getElementById(id2).style.display = "";
}

function toHideTwo(id1,id2){
	document.getElementById(id1).style.display = "none";
	document.getElementById(id2).style.display = "none";
}
////////////////////// end ////////////////////////

//show or hide n id
////////////////////// begin ////////////////////////

function hideAll(a){
	for(var i=0;i<a.length;i++){
		document.getElementById(a[i]).style.display = "none";
	}
}
function toShowN(c,sid1,sid2){
	var sid1 = document.getElementById(sid1);
	if (sid2!=null) {
		var sid2 = document.getElementById(sid2);
	}
	
	var arr = new Array("op","gp");
			if(c.checked == true){
				hideAll(arr);
	            sid1.style.display = "";
				if (sid2!=null) {
	            	sid2.style.display = "";
				}

			} else {
			}
}


////////////////////// end ////////////////////////



////////////////////// 返回上一步操作 ////////////////////////
////////////////////// begin ////////////////////////
//注册当前这个功能页面
$.registerForm = function(formId){
		var result = false;
		var parmas = "";
				if(formId){
					if(typeof(formId) == 'string'){
						params = $('#'+formId).attr("action")+"?"+$('#'+formId).formSerialize();
					}else{
						params = $(formId).attr("action")+"?"+$(formId).formSerialize();
					}
				}else{
						params = $(window.document.forms[0]).attr("action")+"?"+$(window.document.forms[0]).formSerialize();
				}		
		$(window.top.document).find("#prevForm").val(params);
		result = true;
		return result;
}

$.unRegisterForm = function(formId){
$(window.top.document).find("#prevForm").val('');
return true;
}

$.dynaSubmitForm = function(serialForm){
			var index = serialForm.indexOf("?");
			var url = "";
			var json = {};
			if(index > -1){
				var url = serialForm.substr(0,index);
				var params = serialForm.substr(index+1);
				if(params){
					var arr = params.split("&");
					if(arr && arr.length > 0){
						for(var i = 0 ; i < arr.length ; i++){
							var tempArr = arr[i].split("=");
							json[tempArr[0]] = decodeURIComponent(tempArr[1]);
						}
					}
				}
			}else{
				url = serialForm;
			}
			var dynaForm = "<form id='dynaForm' action='"+url+"' method='post'>";
			for(var key in json){
					dynaForm += ("<input type='hidden' name='"+key+"' value='"+json[key]+"'>");
			}
				dynaForm += "</form>";
				$(dynaForm).appendTo(document.body).submit();
}

$.ctx = function(){
	return getBasePath();
}


function goback(menuId){
	var prevForm = $(window.top.document).find("#prevForm").val();
	if(prevForm && prevForm != ''){
		 $(window.top.document).find("#prevForm").val('');
		 $.dynaSubmitForm(prevForm);
	}else{
		if(menuId){
			var url = getBasePath()+"/plat/common/redirect.action?bean.cdid="+menuId;
			// window.location.href = url;
			if(window.parent == window.parent.parent) {
				window.location.href = url;
			}else{
				window.parent.location.href = url;
			}
		}else{
			history.back();
		}
	}
	
}

//获取系统当前页面的应用名称
function getBasePath(){
	//如果应用配置成根目录将无法正常调用此功能
        var path = window.document.location.href;
        var pathName = window.document.location.pathname;
        var pos = path.indexOf(pathName);
        var serverUrl = path.substring(0, pos);
        var basePath = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
      	return basePath;
}



var ctx = getBasePath();
////////////////////// end ////////////////////////

///////////////////iframe 自动适应高度 start///////////////////////////////
/***
 *  jquery 的经典写法，由于没有测试暂时 放在这 
 *  黄斌 2015-07-25
 *   $(obj).height(0); //用于每次刷新时控制IFRAME高度初始化 
      var height = $(obj).contents().height() + 10; 
      $(obj).height( height < 500 ? 500 : height ); 
 * 
 */
function autoHeight(obj){

var win=obj; 
if (document.getElementById) 
{ 
if (win && !window.opera) 
{ 
var aHeight = win.contentWindow.document.body.scrollHeight;
var bHeight = win.contentWindow.document.documentElement.scrollHeight;
var cHeight = Math.max(bHeight,aHeight);
if(win.Document && win.Document.body.scrollHeight) 
win.style.height = (cHeight+10)+"px"; 
else  if (win.contentDocument && win.contentDocument.body.offsetHeight) 
win.style.height = (cHeight+10)+"px"; 
} 
} 
}


////////////////////iframe 自动适应高度 end//////////////////////////////

////////////////////// 选择电话本中群组 ////////////////////////
////////////////////// begin ////////////////////////
		  $(document).ready(function(){
		  	$("#gmenu > ul >li").click(function(){
				$("#gmenu > ul >li").removeClass("on");
				$(this).addClass("on");
			})
		  })
		  
		  $(document).ready(function(e) {
			  try{
				  $(".moreBtn").each(function(i,item){
					  $(this).bind('click',function(){
							$(this).closest("table").find(".moreQuerys").toggle();
							if( $("#moreBtn").val() != ''){
								$("#moreBtn").val('');
								$(this).val('更多条件');
							}else{
								$("#moreBtn").val(1);
								$(this).val('收起条件');
							}
							
						});
					  
					  $(this).after("<input id='moreBtn' type='hidden' name='moreBtn'>");
					  if($(this).attr("status") && $(this).attr("status") != ''){
						 $("#moreBtn").val(1);
						 $(this).closest("table").find(".moreQuerys").toggle();
						 $(this).val('收起条件');
					  }
					  
				  })
					
				  
				  $(".select1").uedSelect({
						width : 345			  
					});
					$(".select2").uedSelect({
						width : 152  
					});
					$(".select3").uedSelect({
						width : 100
					});
			  }catch(e){
				  
			  }
			});

////////////////////// end ////////////////////////