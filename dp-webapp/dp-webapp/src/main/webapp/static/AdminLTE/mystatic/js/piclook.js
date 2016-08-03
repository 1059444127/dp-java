function getStyle(obj, attr)
{
	return obj.currentStyle?obj.currentStyle[attr]:getComputedStyle(obj, false)[attr];
}
var aImg;
var oFrame;
var oBox;
var aLi;
var oBg;
var oBot;
//初始化obj
var constant=new ClassSize();
/*
  查看截图
  author:龙庭伟
  date:2015-8-5
*/
function init()
{
	oBox = document.getElementById('box');
    oBg = document.getElementById('bg');
	oBot = document.getElementById('bottom');
	var aBli = oBot.getElementsByTagName('li');
	oFrame= document.getElementById('frame');
    aLi = oBox.getElementsByTagName('li');
	aImg= oBox.getElementsByTagName('img');
	var i = iNow =  0;
	for(i=0;i<aLi.length;i++){
		aLi[i].index = i;
		//为每一张截图添加点击事件
		aLi[i].onclick = function(){
			with(oFrame.style){display = 'block',bottom='83px',//top = this.offsetTop +'px',
				left = this.offsetLeft +'px',width = this.offsetWidth +'px',height = this.offsetHeight +'px';}
			for(var i=0;i<=aImg.length-1;i++)
			{
				if(aImg[i].id==(this.index+1))
				{
					oFrame.innerHTML='<img src="'+aImg[i].src+'" id="'+(this.index+1)+'" /><span style="font-size: 15px;position: relative;bottom: -10px;right:-100px;">第'+aImg[i].id+'/'+aImg.length+'张</span><span style="font-size: 15px;position: relative;bottom: 310px;font-weight: bold;">'+aImg[i].name+'</span><span style="font-size: 15px;position: relative;bottom: 20px;right:-230px;"><input type="button" name='+aImg[i].src+' id='+aImg[i].title+' value="删除" onclick="doDel(this)"></span>';
				}
			}
			var iWidth = constant.getConstants("PICWIDTH");
			var iHeight =constant.getConstants("PICHEIGHT");
			var oImg = oFrame.getElementsByTagName('img')[0];
			var iLeft = parseInt((document.documentElement.clientWidth / 2) - (iWidth /2));
			var iTop = parseInt((document.documentElement.clientHeight / 2) - (iHeight /2) - 50);
			with(oImg.style){height = width = '100%';}
			startMove(oFrame, {opacity:100, left:iLeft-150, bottom:83,/*top:iTop,*/ width:iWidth, height:iHeight});
			oBg.style.display = 'block';
			oBot.style.display = 'block';
			iNow = this.index + 1;
		};
	}
	//上一张图片
	aBli[0].onclick = function(){
		iNow--;
		if(iNow == 0)iNow = aLi.length;
		for(var i=0;i<=aImg.length-1;i++)
		{
			if(aImg[i].id==iNow)
			{
				oFrame.innerHTML='<img src="'+aImg[i].src+'" id="'+(this.index+1)+'" /><span style="font-size: 15px;position: relative;bottom: -10px;right:-100px;">第'+aImg[i].id+'/'+aImg.length+'张</span><span style="font-size: 15px;position: relative;bottom: 310px;font-weight: bold;">'+aImg[i].name+'</span><span style="font-size: 15px;position: relative;bottom: 20px;right:-230px;"><input type="button" name='+aImg[i].src+' id='+aImg[i].title+' value="删除" onclick="doDel(this)"></span>';
			}
		}
	};
	aBli[1].onclick = function(){
		oFrame.style.cursor = 'move';
		oFrame.onmousedown = function(e){
			var oEvent = e || event;
			var X = oEvent.clientX - oFrame.offsetLeft;
			var Y = oEvent.clientY - oFrame.offsetTop;
			document.onmousemove = function(e){
				var oEvent = e || event;
				var L = oEvent.clientX - X;
				var T = oEvent.clientY - Y;
				if(L < 0){
					L = 0;
				}else if(L > document.documentElement.clientWidth - oFrame.offsetWidth){
					L = document.documentElement.clientWidth - oFrame.offsetWidth
				}
				if(T < 0){
					T = 0;
				}else if(T > document.documentElement.clientHeight - oFrame.offsetHeight){
					T = document.documentElement.clientHeight - oFrame.offsetHeight;
				}
				oFrame.style.left = L + 'px';
				//oFrame.style.top = T + 'px';
				oFrame.style.margin = 0;
				return false;
			}
			document.onmouseup = function(){
				document.onmouseup = null;
				document.onmousemove = null;
			};
			return false;
		};
	};
	//下一张图片
	aBli[2].onclick = function(){
		iNow++;
		if(iNow > aLi.length)iNow = 1;
		for(var i=0;i<=aImg.length-1;i++)
		{
			if(aImg[i].id==iNow)
			{
				oFrame.innerHTML='<img src="'+aImg[i].src+'" id="'+(this.index+1)+'" /><span style="font-size: 15px;position: relative;bottom: -10px;right:-100px;">第'+aImg[i].id+'/'+aImg.length+'张</span><span style="font-size: 15px;position: relative;bottom: 310px;font-weight: bold;">'+aImg[i].name+'</span><span style="font-size: 15px;position: relative;bottom: 20px;right:-230px;"><input type="button" name='+aImg[i].src+' id='+aImg[i].title+' value="删除" onclick="doDel(this)"></span>';
			}
		}
	};
	//关闭
	aBli[3].onclick = function(){
			oFrame.style.display = 'none';
			oBg.style.display = 'none';
			oBot.style.display = 'none';
			oFrame.onmousedown = null;
			oFrame.style.cursor = 'auto';
	};
	
};
/*
  查看辅助图片
  author:龙庭伟
  date:2015-8-24
*/
function initHelpPic()
{
	oBox1 = document.getElementById('box1');
    oBg1= document.getElementById('bg1');
	oBot1 = document.getElementById('bottom1');
	var aBli1 = oBot1.getElementsByTagName('li');
	oFrame1= document.getElementById('frame1');
    aLi1 = oBox1.getElementsByTagName('li');
	aImg1= oBox1.getElementsByTagName('img');
	var i = iNow =  0;
	for(i=0;i<aLi1.length;i++){
		aLi1[i].index = i;
		aLi1[i].onclick = function(){
			with(oFrame1.style){display = 'block',bottom='83px',/*top = this.offsetTop +'px',*/left = this.offsetLeft +'px',width = this.offsetWidth +'px',height = this.offsetHeight +'px';}
			for(var i=0;i<=aImg1.length-1;i++)
			{  
				if(aImg1[i].id==(this.index+1))
				{
					oFrame1.innerHTML='<img src="'+aImg1[i].src+'" id="'+(this.index+1)+'" /><span style="font-size: 15px;position: relative;bottom: -10px;right:-100px;">第'+aImg1[i].id+'/'+aImg1.length+'张</span><span id="picName" name="tee" style="font-size: 15px;position: relative;bottom: 390px;font-weight: bold;right:30px;">'+aImg1[i].name+'</span>';
				}
			}
		setFrameSize();    
		iNow = this.index + 1;
		};
	}
	//上一张图片
	aBli1[0].onclick = function(){
		iNow--;
		if(iNow == 0)iNow = aLi1.length;
		for(var i=0;i<=aImg1.length-1;i++)
		{
			if(aImg1[i].id==iNow)
			{
				oFrame1.innerHTML='<img src="'+aImg1[i].src+'" id="'+(this.index+1)+'"  /><span style="font-size: 15px;position: relative;bottom: -10px;right:-100px;">第'+aImg1[i].id+'/'+aImg1.length+'张</span><span id="picName" name="tee" style="font-size: 15px;position: relative;bottom: 390px;font-weight: bold;right:30px;">'+aImg1[i].name+'</span>';
			}
		}
		setFrameSize();
	};
	aBli1[1].onclick = function(){
		oFrame1.style.cursor = 'move';
		oFrame1.onmousedown = function(e){
			var oEvent = e || event;
			var X = oEvent.clientX - oFrame1.offsetLeft;
			var Y = oEvent.clientY - oFrame1.offsetTop;
			document.onmousemove = function(e){
				var oEvent = e || event;
				var L = oEvent.clientX - X;
				var T = oEvent.clientY - Y;
				if(L < 0){
					L = 0;
				}else if(L > document.documentElement.clientWidth - oFrame1.offsetWidth){
					L = document.documentElement.clientWidth - oFrame1.offsetWidth
				}
				if(T < 0){
					T = 0;
				}else if(T > document.documentElement.clientHeight - oFrame1.offsetHeight){
					T = document.documentElement.clientHeight - oFrame1.offsetHeight;
				}
				oFrame1.style.left = L + 'px';
				//oFrame1.style.top = T + 'px';
				oFrame1.style.margin = 0;
				return false;
			}
			document.onmouseup = function(){
				document.onmouseup = null;
				document.onmousemove = null;
			};
			return false;
		};
	};
	//下一张图片
	aBli1[2].onclick = function(){
		iNow++;
		if(iNow > aLi1.length)iNow = 1;
		for(var i=0;i<=aImg1.length-1;i++){
			if(aImg1[i].id==iNow){
				oFrame1.innerHTML='<img src="'+aImg1[i].src+'" id="'+(this.index+1)+'" /><span style="font-size: 15px;position: relative;bottom: -10px;right:-100px;">第'+aImg1[i].id+'/'+aImg1.length+'张</span><span id="picName" name="tee" style="font-size: 15px;position: relative;bottom: 390px;font-weight: bold;right:30px;">'+aImg1[i].name+'</span>';
			}
		}
		setFrameSize();
	};
	//关闭
	aBli1[3].onclick = function(){
		startMove(oFrame1, {opacity:0, left:aImg1[iNow-1].offsetLeft, top:aImg1[iNow-1].offsetTop, width:150, height:100}, function(){
			oFrame1.style.display = 'none';
			oBg1.style.display = 'none';
			oBot1.style.display = 'none';
			oFrame1.onmousedown = null;
			oFrame1.style.cursor = 'auto';
		});
	};
	
};
//辅助图片的显示大小
function setFrameSize(){
	var oImg = oFrame1.getElementsByTagName('img')[0];
	var iWidth = oImg.width;
	var iHeight =oImg.height;
	//超出限定的宽度
	if(iWidth>constant.getConstants("PiCLIMITWIDTH")){
		iWidth=constant.getConstants("PiCLIMITWIDTH");
	}
	//超出限定的高度
	if(iHeight>constant.getConstants("PiCLIMITHEIGHT")){
		iHeight=constant.getConstants("PiCLIMITHEIGHT");
	}
	$("#picName").css("bottom",(iHeight+30));
	var iLeft = parseInt((document.documentElement.clientWidth / 2) - (iWidth /2));
	var iTop = parseInt((document.documentElement.clientHeight / 2) - (iHeight /2) - 10);
	with(oImg.style){height = width = '100%';}
	startMove(oFrame1, {opacity:100, left:iLeft-150, bottom:83,/*top:iTop,*/ width:iWidth, height:iHeight});
	oBg1.style.display = 'block';
	oBot1.style.display = 'block';
}
function startMove(obj, json, onEnd){
	clearInterval(obj.timer);
	obj.timer=setInterval(function (){
		doMove(obj, json, onEnd);
	}, 30);
}
function doMove(obj, json, onEnd){
	var attr='';
	var bStop=true;
	for(attr in json){
		var iCur=0;
		if(attr=='opacity'){
			iCur=parseInt(parseFloat(getStyle(obj, attr))*100);
		}else{
			iCur=parseInt(getStyle(obj, attr));
		}
		var iSpeed=(json[attr]-iCur)/5;
		iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
		
		if(json[attr]!=iCur){
			bStop=false;
		}
		if(attr=='opacity'){
			obj.style.filter='alpha(opacity:'+(iCur+iSpeed)+')';
			obj.style.opacity=(iCur+iSpeed)/100;
		}else{
			obj.style[attr]=iCur+iSpeed+'px';
		}
	}
	if(bStop){
		clearInterval(obj.timer);		
		if(onEnd){
			onEnd();
		}
	}
}
//页面加载
window.onload = function(){
	init();
	initHelpPic();
}
