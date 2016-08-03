<%@ page language="java" pageEncoding="utf-8"%>

<script type="text/javascript">
/**
 * pageCount 当前是第几页
 *
 * currentPage 总共多少页
 */
function pageDiv(pageCount,currentPage){
	var htmlPage = "<ul class='pagination' id='dictPage'>";
	
    
    if(pageCount == 1){
    	htmlPage += "<li class='disabled'><span>首页</span></li><li class='disabled'><span>上一页</span></li>";
    }else{
    	htmlPage += "<li><a href=\"javascript:onPageClicked(1)\"><span>首页</span></a></li><li><a href=\"javascript:pagePre()\"><span>上一页</span></a></li>";
    }
    if(currentPage<=8){
    	for(var i=1;i<=currentPage;i++){
        	if(pageCount == i){
        		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
        	}else{
        		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
        	}
        }
    }else{
    	if(pageCount<=4){
    		for(var i=1;i<=currentPage;i++){
            	if(pageCount == i){
            		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
            	}else if(i>8){
            		htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
            		break;
            	}else{
            		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
            	}
            }
    	}else{
    		if((parseInt(pageCount)+4)>currentPage){
    			htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
        		for(var i=parseInt(pageCount)-4;i<=currentPage;i++){
                	if(pageCount == i){
                		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
                	}else{
                		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
                	}
                }
    		}else{
    			htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
        		for(var i=parseInt(pageCount)-4;i<(parseInt(pageCount)+4);i++){
                	if(pageCount == i){
                		htmlPage += "<li class='active'><a href=\"javascript:void(0)\">"+i+" <span class=\"sr-only\">"+i+"</span></a></li>";
                	}else{
                		htmlPage +="<li><a href=\"javascript:onPageClicked('"+i+"')\"><span>"+i+"</span></a></li>";
                	}
                }
        		htmlPage +="<li><a href='javascript:void(0);'><span>...</span></a></li>";
    		}
    	}
    }
    
    if(pageCount == currentPage){
    	htmlPage +="<li class='disabled'><span>下一页</span></li><li class='disabled'><span>末页</span></li>";
    }else{
    	htmlPage +="<li><a href=\"javascript:pageNext()\"><span>下一页</span></a></li><li><a href=\"javascript:onPageClicked('"+currentPage+"')\"><span>末页</span></a></li>";
    }
    htmlPage +="</ul>";
    $("#myPageDiv").html(htmlPage);
}
/**
 * 上一页
 */
function pagePre(){
	onPageClicked($("#pageNo").val()-1);
}
/**
 * 下一页
 */
function pageNext(){
	onPageClicked(parseInt($("#pageNo").val())+1);
}


function changeNumber(obj){
	$("#pageSize").val($(obj).val());
	queryList();
}
/**
 * 给左下角数据动态赋值
 */
function dataRange(data){
	$("#totalCount").html(data.iTotalRecords);
    $("#startNum").html(parseInt(data.iDisplayStart)+1);
    var endNum = parseInt(data.iDisplayStart)+parseInt(data.iDisplayLength);
    if(endNum<parseInt(data.iTotalRecords)){
    	$("#endNum").html(endNum);
    }else{
    	$("#endNum").html(data.iTotalRecords);
    }
}
</script>


<div class="pull-left">
	每页&nbsp;<select id="changeNumber" class="selected" onchange="changeNumber(this)">
		<option value="10" selected>10</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="50">50</option>
		<option value="100">100</option>
	</select>&nbsp;条，第<span id="startNum"></span>至<span id="endNum"></span>条数据，总共<span id="totalCount"></span>条数据
</div>
<div id="myPageDiv" class="pull-right"></div>
