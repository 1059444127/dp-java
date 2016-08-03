// JavaScript Document

function getObj(obj){
        return typeof(obj)=="string"?document.getElementById(obj):obj
}

function allt(id,hideId){
	 createMask();	
    e  = getObj(id)
    var etop = e.offsetTop;
    var eleft = e.offsetLeft;
	var xx = 282 - e.offsetWidth;

	
    while( e = e.offsetParent){
        etop += e.offsetTop;
        eleft += e.offsetLeft;
    }

                getObj(hideId).style.left=(eleft-xx) + "px";
                getObj(hideId).style.top=(etop+22) + "px";
                getObj(hideId).style.display='';
				//document.body.style.filter="progid:DXImageTransform.Microsoft.Alpha(opacity=25)";
				//document.body.style.opacity="0.3";
}

 function closeAllt(id,iformid)
 {removeMask();
 		 $("#"+id).hide()
         $("#"+iformid).submit();
	//	document.getElementById(id).style.display="none";
 }
 
 function createMask(){
	 $("select").hide();
	 $("body").append("<div class='mask' style='filter:Alpha(opacity=80);opacity:0.8;width:1000px;height:1000px;background:#fff;position:absolute;left:0;top:0'></div>")
	parent.leftFrame.$("body").append("<div class='mask' style='filter:Alpha(opacity=80);opacity:0.8;width:300px;height:1000px;background:#fff;position:absolute;left:0;top:0'></div>")

  
	 }
	 
function removeMask(){
	$(".mask").remove();
	parent.leftFrame.$(".mask").remove();
	 $("select").show();
	}