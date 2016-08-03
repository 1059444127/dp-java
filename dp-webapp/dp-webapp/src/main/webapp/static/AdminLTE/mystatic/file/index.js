/*! jQuery Cookie Plugin v1.4.0 */
(function(factory){if(typeof define==='function'&&define.amd){define(['jquery'],factory);}else if(typeof exports==='object'){factory(require('jquery'));}else{factory(jQuery);}}(function($){var pluses=/\+/g;function encode(s){return config.raw?s:encodeURIComponent(s);}function decode(s){return config.raw?s:decodeURIComponent(s);}function stringifyCookieValue(value){return encode(config.json?JSON.stringify(value):String(value));}function parseCookieValue(s){if(s.indexOf('"')===0){s=s.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,'\\');}try{s=decodeURIComponent(s.replace(pluses,' '));return config.json?JSON.parse(s):s;}catch(e){}}function read(s,converter){var value=config.raw?s:parseCookieValue(s);return $.isFunction(converter)?converter(value):value;}var config=$.cookie=function(key,value,options){if(value!==undefined&&!$.isFunction(value)){options=$.extend({},config.defaults,options);if(typeof options.expires==='number'){var days=options.expires,t=options.expires=new Date();t.setTime(+t+days*864e+5);}return(document.cookie=[encode(key),'=',stringifyCookieValue(value),options.expires?'; expires='+options.expires.toUTCString():'',options.path?'; path='+options.path:'',options.domain?'; domain='+options.domain:'',options.secure?'; secure':''].join(''));}var result=key?undefined:{};var cookies=document.cookie?document.cookie.split('; '):[];for(var i=0,l=cookies.length;i<l;i++){var parts=cookies[i].split('=');var name=decode(parts.shift());var cookie=parts.join('=');if(key&&key===name){result=read(cookie,value);break;}if(!key&&(cookie=read(cookie))!==undefined){result[name]=cookie;}}return result;};config.defaults={};$.removeCookie=function(key,options){if($.cookie(key)===undefined){return false;}$.cookie(key,'',$.extend({},options,{expires:-1}));return!$.cookie(key);};}));

var isMobile ={Android:function(){return navigator.userAgent.match(/Android/i)},BlackBerry:function(){return navigator.userAgent.match(/BlackBerry/i)},iOS:function(){return navigator.userAgent.match(/iPhone|iPad|iPod/i)},Opera:function(){return navigator.userAgent.match(/Opera Mini/i)},Windows:function(){return navigator.userAgent.match(/IEMobile/i) || navigator.userAgent.match(/WPDesktop/i)},any:function(){return(isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows())}};

$(document).ready(function(){
	mobileJump($('.mobile-site'))
	//会议活动-城市模块样式控制--限制字符
	$(".hiring .city").each(function(){
	  	var len = $(this).text().length;
	  	if(len == 3 || len == 4 || len == 5){
	    	$(this).addClass("city_font"+len);
	  	}else{
	  		$(this).addClass("city");
	  	}
	});
	//人才招聘-城市模块样式控制--限制字符
	$(".date_cont .date_city").each(function(){
	  	var len = $(this).text().length;
	  	if(len == 4 || len == 5){
	  		$(this).addClass("date_city"+len);
	  	}else{
	  		$(this).addClass("date_city");
	  	}
	});
	//名医专栏-切换
	$('.expert_ul_text li').mouseover(function(){ 
	  	$(".expert_item").hide().eq($('.expert_ul_text li').index(this)).show();
	});
	//列表页分享
	$('.bshare-custom').each(function(){
        var data = $(this).data('share');
        data = eval('('+data+')');
        bShare.addEntry({
            title: data.title, // 获取文章标题
            url: data.url, // 获取文章链接
            summary: '', // 获取文章摘要
            pic: data.pic
        });
    });
    checkLogin();
    //$('#Article .content img').LoadImage(true, 660, 660,'{IMG_PATH}s_nopic.gif');
    // guide2H();  
    tabMe();
    viewPoints($('.view-point-wrap'));
    renderVideo('my-player-wrap');
    addImgTitle();
    myCarousel('#csl-partners',5,true);
});

var BBS_URL = 'http://bbs.91360.com/';
function checkLogin(){
	var wrap = $('#login-wrap');
	wrap.length && reqJSONP(BBS_URL+'pathosec-login.html?action=check&callback=?', 'get', '', function(data){
		if(data.rsm){
			var user = data.user;
			if(user.uid){
				buildUserFrame(user).appendTo(wrap);
			}else{
				$('<a href="javascript:;" class=""><span class="font_blue pr8">登录</span></a>').on('click', function(){popLoginDlg(wrap)}).appendTo(wrap);
				$('<span class="font_gray">|</span>').appendTo(wrap);
				$('<a href="http://passport.91360.com/?goto='+encodeURIComponent(window.location.href)+'"><span class="font_golden pl8">注册</span></a>').appendTo(wrap);
			}
		}
	});
}

function popLoginDlg(wrap){
	var dlg = buildDialog('modal-login'), content = dlg.find('.modal-content');
	if(content.html() == ''){
		dlg.find('.modal-dialog').addClass('modal-sm');
		dlg.find('.modal-content').html('<div class="modal-header"><button type="button" class="close close-button">&times;</button><p class="modal-title">登录</p></div><div class="modal-body"><form id="login-form"><div class="form-group"><label for="user-name">用户</label><input type="text" class="form-control" name="username" id="user-name" placeholder="请输入用户名/邮箱/手机"></div><div class="form-group"><label for="user-passwd">密码</label><input type="password" name="password" class="form-control" id="user-passwd" placeholder="请输入密码"></div><div class="checkbox"><label><input type="checkbox" id="remember" name="remember" value="1"> 自动登录</label></div><button type="submit" class="btn btn-effect-3 btn-sm btn-block">登录</button></form></div>');
		dlg.find('form').on('submit', function(){
			var $this = $(this), username = $this.find('#user-name').val(), password = $this.find('#user-passwd').val(), remember = $this.find('#remember').val();
			if(!username){
				alert('请输入用户名');
			}else if(!password){
				alert('请输入密码');
			}else{
				reqJSONP(BBS_URL+'pathosec-login.html?action=login&callback=?', 'post', {username:username,password:password,remember:remember}, function(data){
					if(data.rsm){
						if(data.url){
							window.location.href = BBS_URL+data.url;
						}else if(data.user){
							wrap.empty().append(buildUserFrame(data.user));
							dlg.remove();
							if(data.user.syn) $(data.user.syn).appendTo('body');
						}
					}else{
						alert(data.err);
					}
				});
			}
			return false;
		});
	}
	dlg.show();
}

function buildDialog(id){
	if($('#'+id).length){
		return $('#'+id);
	}else{
		var modal = $('<div id="'+id+'" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>');
		modal.on('click', '.close', function(){
			modal.hide();
		});
		return modal.appendTo('body');
	}
}
function buildUserFrame(user){
	return $('<div class="user-block-wrap"><a href="'+BBS_URL+'space-uid-'+user.uid+'.html" target="_blank" title="你好，'+user.username+'" class="user-avatar"><img src="'+user.avatar+'" alt="avatar" /></a><ul class="user-menu-list"><li><a href="'+BBS_URL+'member.php?mod=logging&action=logout&referer='+encodeURIComponent(window.location.href)+'&formhash='+user.formhash+'">注销</a></li></ul></div>');
}
function reqJSONP(e,o,r,t){"function"!=typeof t&&(t=_ajaxPostProcesser),$.ajax({url:e,type:o||"get",dataType:"jsonp",data:r||""}).done(t).fail(function(a){""!=$.trim(a.responseText)&&(console.log(a.responseText))})}
function _ajaxPostProcesser(e){e.err?alert(e.err):e.rsm&&(e.url?window.location=decodeURIComponent(e.url):alert(e.msg))}

function viewPoints(wrap){
	var vpId = wrap.data('vpid');
	if(!vpId) return false;
	$.getJSON(window.basePath+'api.php?op=viewpoint&action=get&vpid='+vpId, function(json){
		if(json.rsm){
			wrap.find('.btn-vp-ups').find('.vp-number').text(json.ups+'');
			wrap.find('.btn-vp-downs').find('.vp-number').text(json.downs+'');
			wrap.find('.btn-vp-olds').find('.vp-number').text(json.olds+'');
		}
	});
	$('.btn-vp').on('click', function(){
		var $btn = $(this), action = $btn.data('action');
		if($btn.is('.btn-vp-scb')){
			var qrw = $btn.find('.subscribe-qrcode-wrap');
			if(qrw.is(':visible')){
				qrw.fadeOut('fast');
			}else{
				qrw.fadeIn('fast');
			}
		}
		if(!action) return true;
		if($btn.data('voted')){	//发布过意见了
			popover($btn, '你已经点过啦 >_<');
			return false; 
		}
		$.post(window.basePath+'api.php?op=viewpoint&action='+action+'&vpid='+vpId, function(json){
			// console.log(json);
			if(json.rsm){
				popover($btn, json.msg);
				if(json.plus){
					var num = $btn.find('.vp-number'), val = parseInt(num.text());
					num.remove().clone().text(val+1+'').appendTo($btn);
				}
				$btn.addClass('btn-vp-pushed').data('voted', 1);
			}else{
				popover($btn, json.err);
			}
		}, 'json');
	});
}

function popover(wrap, msg, pos, theme){
	if($('.vp-popover').length > 0) $('.vp-popover').remove();
	var pos = pos || 'top', theme = theme || 'primary', offset = wrap.offset();
	$('<div class="vp-popover vp-popover-'+theme+' vp-popover-'+pos+'"><div class="vp-popover-inner">'+msg+'</div><div class="vp-popover-caret"></div></div>').appendTo('body').css({'top':offset.top-wrap.innerHeight()+30,'left':offset.left+10}).fadeIn('slow').delay(3000).fadeOut('fast',function(){$(this).remove()});
}

function tabMe(){
	$('.tab-me').find('a').on('click', function(){
		var li = $(this).closest('li');
		if(li.hasClass('active')) return false;
		li.addClass('active').siblings().removeClass('active');
		var pane = $('.tab-me-content').find('.tab-pane');
		pane.eq(li.index()).addClass('active').siblings().removeClass('active');
	});
}

function guide2H(){
	var k = $.cookie('ikwhereish'); //我知道新平台在哪里了
	if(k) return false;
	$.cookie('ikwhereish', '1', {expires: 365});
	var overlay = $('<div class="tips-overlay"></div>');
	$('body').append(overlay);
	if(isMobile.any()){
		var h = $(window).height(), w = h/2*0.8, m = h/2*0.05,
			style = 'width:'+w+'px;height:'+w+'px;margin-left:-'+(parseInt(w/2))+'px',
			goh = $('<a class="m-guard-goh" href="http://m.91360.com/" style="'+style+';margin-bottom:'+m+'px"></a>'),
			stay = $('<a class="m-guard-stay" href="javascript:;" style="'+style+';margin-top:'+m+'px"></a>').on('click', function(){overlay.remove();goh.remove();$(this).remove()});
		$('body').append(goh).append(stay);
	}else{
		var offset = $('ul.head_icon4').offset(), 
			style = 'top:'+(offset.top+60)+'px;left:'+(offset.left-200)+'px',
			bubble = $('<div class="grumble grumble200" style="'+style+'">&nbsp;</div>'),
			gtext = $('<div class="grumble-text grumble-text200" style="'+style+'"><div class="outer"><div class="inner">我们搬到这里了.</div></div></div>')
				.hover(function(){bubble.addClass('hover')}, function(){bubble.removeClass('hover')})
				.on('click', function(){$('ul.head_icon4').removeClass('head-icon-highlight');overlay.remove();bubble.remove();gbtn.remove();$(this).remove();}),
			gbtn = $('<a class="grumble-text-btn" style="top:'+(offset.top+280)+'px;left:'+(offset.left-80)+'px" href="javascript:;">我知道啦</a>').on('click', function(){$('ul.head_icon4').removeClass('head-icon-highlight');overlay.remove();bubble.remove();gtext.remove();$(this).remove();})
		$('ul.head_icon4').addClass('head-icon-highlight');
		$('body').append(bubble).append(gtext).append(gbtn);
	}
}

function show_ajax(obj) {
	var keywords = $(obj).text();
	var offset = $(obj).offset();
	var jsonitem = '';
	$.getJSON(window.basePath+"index.php?m=content&c=index&a=json_list&type=keyword&modelid={$modelid}&id={$id}&keywords="+encodeURIComponent(keywords),
			function(data){
			var j = 1;
			var string = "<div class='point key-float'><div style='position:relative'><div class='arro'></div>";
			string += "<a href='JavaScript:;' onclick='$(this).parent().parent().remove();' hidefocus='true' class='close'><span>关闭</span></a><div class='contents f12'>";
			if(data!=0) {
			  $.each(data, function(i,item){
				j = i+1;
				jsonitem += "<a href='"+item.url+"' target='_blank'>"+j+"、"+item.title+"</a><BR>";
				
			  });
				string += jsonitem;
			} else {
				string += '没有找到相关的信息！';
			}
				string += "</div><span class='o1'></span><span class='o2'></span><span class='o3'></span><span class='o4'></span></div></div>";		
				$(obj).after(string);
				$('.key-float').mouseover(
					function (){
						$(this).siblings().css({"z-index":0})
						$(this).css({"z-index":1001});
					}
				)
				$(obj).next().css({ "left": +offset.left-100, "top": +offset.top+$(obj).height()+12});
			});
}

function add_favorite(title) {
	$.getJSON(window.basePath+'api.php?op=add_favorite&title='+encodeURIComponent(title)+'&url='+encodeURIComponent(location.href)+'&'+Math.random()+'&callback=?', function(data){
		if(data.status==1)	{
			$("#favorite").html('收藏成功');
		} else {
			alert('请登录');
		}
	});
}

function getURLParameter(name) {
	return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
}

function mobileJump(wrap){
	if(getURLParameter('force') == 'pc'){
		$('body').on('click', 'a', function(){
			var url = $(this).attr('href');
			url += (url.indexOf('?') > 0 ? '&' : '?') + 'force=pc';
			$(this).attr('href', url);
		});
	}else{
		if(wrap.length && isMobile.any()) window.location.href = wrap.data('url')+window.location.hash;
	}
}

function renderVideo(wrap){
	if($('#'+wrap).length){
		$.getScript('http://img.91360.com/cms/flash/player.min.js', function(){
			window.a.renderAt(wrap);
		});
	}
}
function addImgTitle(){
	$('img').each(function(){
		var alt=$(this).attr('alt'),title=$(this).attr('title');
		if(alt && !title) $(this).attr('title', alt);
	});
}

function myCarousel(wraper, speed, auto){  
	var flag = 'left',
		wraper = $(wraper),
		prev = wraper.find('.cc-carousel-prev'),
		next = wraper.find('.cc-carousel-next'),
		ul = wraper.find('.cc-carousel-content ul'),
		s = speed || 5,
		item = ul.find('li'),
		itemW = item.outerWidth(true);
	next.click(function(){ 
		ul.animate({'margin-left':-itemW},function(){ 
			ul.find('li:first').appendTo(ul); 
			ul.css({'margin-left':0}); 
		}); 
		flag = "left";
	}); 
	prev.click(function(){ 
		ul.find('li:last').prependTo(ul); 
		ul.css({'margin-left':-itemW}); 
		ul.animate({'margin-left':0}); 
		flag = "right";
	}); 
	if(item.length>4){
		if (auto){ 
			ad = setInterval(function() { flag == "left" ? next.click() : prev.click()}, s*1000); 
			wraper.hover(function(){clearInterval(ad);},function(){ad = setInterval(function() {flag == "left" ? next.click() : prev.click()},s*1000);});
		}
	} 
} 
