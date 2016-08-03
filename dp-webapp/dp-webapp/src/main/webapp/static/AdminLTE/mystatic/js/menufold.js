

//中间点击隐藏显示左边菜单
function opTab(){
    var mnu = document.getElementById("leftDiv");
    var arrdiv = document.getElementById("arrow");
    var arrtxt = $("#arr");
    if((mnu.style.display == "block") || (mnu.style.display == "")){
    	 mnu.style.display = "none";
    	 arrtxt.html("<span class=\"glyphicon glyphicon-chevron-right\"></span>");
         arrdiv.style.pixelLeft = 0;
         $("#leftTd").animate({width:"0px"},{duration:500});
    } else {
    	$("#leftTd").animate({width:"195px"},{duration:500});
        mnu.style.display = "block";
        arrdiv.style.pixelLeft = mnu.style.width.substring(0 , mnu.style.width.length - 2);
        arrtxt.html("<span class=\"glyphicon glyphicon-chevron-left\"></span>");
    }
  }



