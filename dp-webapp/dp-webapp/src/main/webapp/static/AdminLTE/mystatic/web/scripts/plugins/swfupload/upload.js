var jsArr = ['/scripts/plugins/swfupload/swfupload.js'
			 ,'/scripts/plugins/swfupload/swfupload.queue.js'
			 ,'/scripts/plugins/swfupload/fileprogress.js'
			 ,'/scripts/plugins/swfupload/handlers.js'];
			 //getBasePath() 函数 在 common.js文件当中 ，
var ctx = getBasePath();		 
for(var i = 0 ; i < jsArr.length ;i++){
	var  script = document.createElement("script");
		 script.type = "text/javascript" ;
		 script.language="javascript";
		 script.src = ctx+jsArr[i];
		 var head = document.getElementsByTagName("head")[0];
		 head.appendChild(script);
}

function renderTable(post_params){
	var url = ctx+"/plat/common/tCommonDocument!getResult.action?version="+Math.random();
	var ver = Math.random();
	var img = ctx+"/skins/common/icon_del.gif"
	var downImg = ctx+"/skins/common/icon_hy.gif"
	$.getJSON(url,post_params,function(data){
			if(data.length){
				var html = "";
					for(var i = 0 ; i < data.length ; i++){
						var item = data[i];
						var del  = renderItem(item);
	   					var line = "<tr class='fileInfos'>"
						line +=" <td  align='center'  > "
						line += "<a href='javascript:void(0);' style='color:blue;text-decoration : none;' onclick='download(\""+item.DOCID+"\")' title='"+item.NAME+"'>"+item.NAME+"</a></td>"
						line +="<td align='center' ><img src='"+showFileImge(item.NAME)+"'></td>"
						line +="<td align='center' >"+item.SIZE+"</td>"
					    line +='<td align="center"><a href="javascript:void(0);"><img alt="下载文件" src="'+downImg+'"  onclick="download(\''+item.DOCID+'\')"></a>';
					    if(del){
					    line += '	<a href="javascript:void(0);"><img alt="删除文件" src="'+img+'" onclick="deleteFile(\''+item.DOCID+'\',\''+item.NAME+'\')"></a>'
					    }
					    line +='</td>';
						line += "</tr>";
						html += line;
						}
					$(".fileInfos").remove();
					$("#fileTitle").after(html);
				}else{
					$(".fileInfos").remove();
				}
			});
	
}

function deleteFile(docid,wdmc){
var url =  ctx+"/plat/common/tCommonDocument!delete.action?version="+Math.random()+"&bean.docid="+docid;
	var confirm = window.confirm('确定要永久删除文档：'+wdmc+'?')
	if(confirm)
	{
		$.post(url,{},function(data){
			if($.trim(data) == '1'){
			renderTable(uploadSettins['post_params']);
			}else{
				alert('文件删除出现异常');
			}
		});
	}
}


function download(wdbh){
	var url = ctx+"/plat/common/tCommonDocument!download.action?bean.docid="+wdbh;
	var frm = document.getElementById("downloadFrame");
	frm.src = url;
}

/**
上传成功时进行回调
*/
function myUploadSuccess(file, serverData) {
			try {
				var progress = new FileProgress(file, this.customSettings.progressTarget);
				progress.setComplete();
				progress.setStatus("Complete.");
				progress.toggleCancel(false);
				renderTable(this.settings['post_params']);
			} catch (ex) {
				this.debug(ex);
			}	
}


function getSettings(){
var uploadSettins={
			flash_url : ctx+"/scripts/plugins/swfupload/swfupload.swf",
			upload_url : ctx+"/plat/common/tCommonDocument!upload.action",
			file_post_name : "document",
			file_size_limit : "100 MB",
			file_types : '*.*',
			query_params : null,//查询参数
			file_types_description : "All Files",
			file_upload_limit : 100,
			file_queue_limit : 0,
			custom_settings : {
				progressTarget : 'fsUploadProgress',
				cancelButtonId : 'btnCancel'
			},
			debug: false,
			button_width: "65",
			button_height: "25",
			button_cursor: SWFUpload.CURSOR.HAND,
			button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
			button_placeholder_id: 'spanButtonPlaceHolder',
			button_cursor: SWFUpload.CURSOR.HAND,
			button_text: '<span class="theFont">浏览文件</span>',
			button_text_style: ".theFont { font-size: 12; }",
			button_text_left_padding: 12,
			button_text_top_padding: 3,
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : myUploadSuccess,
			upload_complete_handler : uploadComplete,
			queue_complete_handler : queueComplete	// Queue plugin event
		};
		return uploadSettins;
}



/**显示文件对应的图标*/
function showFileImge (fileName){
	var basePath =  getBasePath()+"/scripts/plugins/swfupload/fileicon/";
	if(fileName!=null && fileName.indexOf(".") >0){
		var fileParts = fileName.split(".");
		var filemat = fileParts[fileParts.length-1].toLowerCase();
		if(filemat == ("doc") || filemat == ("docx")){
            basePath += "doc.gif";
        }else if(filemat == ("xls") || filemat == ("xlsx")){
            basePath += "xls.gif";
        }else if(filemat == ("ppt") || filemat == ("pptx")){
            basePath += "ppt.gif";
        }else if(filemat == ("vsd")){
            basePath += "vsd.gif";
        }else if(filemat == ("jpg")){
            basePath += "png.gif";
        }else if(filemat == ("gif")){
            basePath += "png.gif";
        }else if(filemat == ("png")){
            basePath += "png.gif";
        }else if(filemat == ("txt")){
            basePath += "txt.gif";
        }else if(filemat == ("mp3")){
            basePath += "rar.gif";
        }else if(filemat == ("rar")){
            basePath += "rar.gif";
        }else if(filemat  ==  ("zip")){
            basePath += "rar.gif";
        }else{
            basePath += "txt.gif";
        }
        return basePath; 
	}
	return ('/images/platform/dtree/page.gif');
}
	 