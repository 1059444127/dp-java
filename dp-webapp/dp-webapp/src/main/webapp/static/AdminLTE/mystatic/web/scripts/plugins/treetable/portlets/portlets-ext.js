var tdsize=0;
var current_div_index=0;
var current_td_index=0;
var current_div_width=0;
var current_div_height=0;
var current_div_leftPadding=1;

//------------修改div的过程-------------
function divresize(event,ui){
	//---操作的divid----
	var id=event.target.id;
	var target=$("#"+id);
	//---所属的td-------
	var targetParent=target.parent();
	//----td的宽度----
	var tdwidth=targetParent.width();
	var ind=target.index();
	var nextAllTd=targetParent.nextAll();
	var rowWidth=tdwidth;
	var nextShow=false;
	var gesize=ui.size.width;
	
	nextAllTd.each(function(i,v){
		var vwidth=$(v).width();
		rowWidth+=vwidth;
		var show=$("#"+id+"_showKK_"+i);
		var di=$(v).children().eq(ind);
		if(gesize<tdwidth||nextShow){
			if(show.attr("id")){
				show.remove();
			}
		}else if(tdwidth<=gesize && gesize<rowWidth &&!nextShow){
			if(!show.attr("id")){
				di.before('<div followid="'+id+'" id="'+id+'_showKK_'+i+'" style="height:'+ui.size.height+'px;margin-bottom: 8px;"  class="portlet2-state-disabled"  ></div>');
			}
			nextShow=true;
		}else if(tdwidth<=gesize && gesize>rowWidth && !nextShow){
			if(!show.attr("id")){
				di.before('<div followid="'+id+'" id="'+id+'_showKK_'+i+'" style="height:'+ui.size.height+'px;margin-bottom: 8px;"   class="portlet2-state-disabled"  ></div>');
			}
		}
	});
	
}

//-------------修改div大小之后的事件-----------------
function stopresize(event,ui){
	var id=event.target.id;
	var target=$("#"+id);
	var ind=target.index();
	var targetParent=target.parent();
	var tdwidth=targetParent.width();
	var gesize=ui.size.width;
	var  colcount=1;
	if(target.width()<=tdwidth){
		target.width(((tdwidth-2)/tdwidth)*100+"%");
		target.attr("colcount",1);
	}else{
		var nextAllTd=targetParent.nextAll();
		var rowWidth=tdwidth;
		var hasnext=true;
		nextAllTd.each(function(i,v){
			var vwidth=$(v).width();
			rowWidth+=vwidth;
			if(tdwidth < gesize && gesize <= rowWidth && hasnext){
				target.width(((rowWidth+(i+1)*5)/tdwidth)*100+"%");
				hasnext=false;
				colcount=colcount+i+1;
				target.attr("colcount",colcount);
				return ;
			}
		});
	}
	$("div[id*='_showKK_']").each(function(i,v){
		var followid = $(v).attr("followid");
		var currentIndex = $(v).index();
		var index = $("#"+followid).index();
		if(currentIndex != index){
			$(v).siblings().eq(index).before($(v));
		}
	});
}


function startMove(event,ui){
	var context =ui.item.context;
	var id=context.id;
	var target=$("#"+id);
	target.width("500");
	var colcount =target.attr("colcount");
	if(!colcount){
		colcount=1;
	}
	var rowcount =target.attr("rowcount");
	if(colcount>1){
		var targetParent=target.parent();
		var nextAllTd=targetParent.nextAll();
		var tdcount=nextAllTd.size();
		var show=$("div[id^='"+id+"_showKK_']");
		show.each(function(i,v){
			$(v).remove();
		});
	}

}


function stopMove(event,ui){
	var context =ui.item.context;
	var id=context.id;
	var target=$("#"+id);
	var colcount =target.attr("colcount");
	var rowcount =target.attr("rowcount");
	var height =target.height();
	if(!colcount){
		colcount=1;
	}
	if(colcount>1){
		var ind = target.index();
		var targetParent=target.parent();
		var nextAllTd=targetParent.nextAll();
		var tdwidth=targetParent.width();
		var rowWidth=tdwidth;
		var nextAllTdSize =nextAllTd.size();
		if(nextAllTdSize+1<colcount){
			target.attr("colcount","1");
			target.width(((tdwidth-2)/tdwidth)*100+"%");
		}else{
			nextAllTd.each(function(i,v){
				if(i+1<colcount){
					rowWidth+=$(v).width();
					var show=$("#"+id+"_showKK_"+i);
					var di=$(v).children().eq(ind);
					if(!show.attr("id")){
						di.before('<div followid="'+id+'" id="'+id+'_showKK_'+i+'"  style="height:'+height+'px;margin-bottom: 8px;" class="portlet2-state-disabled"  ></div>');
					}
					target.width(((rowWidth+(i+1)*5)/tdwidth)*100+"%");
				}
			});
		}
	}
	$("div[id*='_showKK_']").each(function(i,v){
		var followid = $(v).attr("followid");
		var currentIndex = $(v).index();
		var index = $("#"+followid).index();
		if(currentIndex != index){
			$(v).siblings().eq(index).before($(v));
		}
	});
}

function loadDiv(target,colcount,rowcount){
	var id=target.attr("id");
	var ind = target.index();
	var height =target.height();
	var targetParent=target.parent();
	var nextAllTd=targetParent.nextAll();
	var tdwidth=targetParent.width();
	var rowWidth=tdwidth;
	var nextAllTdSize =nextAllTd.size();
	nextAllTd.each(function(i,v){
		if(i+1<colcount){
			rowWidth+=$(v).width();
			var show=$("#"+id+"_showKK_"+i);
			var di=$(v).children().eq(ind);
			if(!show.attr("id")){
				di.before('<div followid="'+id+'" id="'+id+'_showKK_'+i+'"  style="height:'+height+'px;margin-bottom: 8px;" class="portlet2-state-disabled"  ></div>');
			}
			target.width(((rowWidth+(i+1)*5)/tdwidth)*100+"%");
		}
	});
}

