var fileIdSerialNo = 1; // 页面上传附件表单ID的后缀系列号

	/* 获取页面上传附件表单ID的后缀系列号 */
	function getFileIdSerialNo() {
		return fileIdSerialNo++;
	}

	 function checkLast(str){  
	        var flag=false;  
	        var ext=str.split('.')[str.split('.').length-1];  
	        if(ext=='doc'||ext=='docx'||ext=='pdf'||ext=='xls'||ext=='xlsx'||ext=='xml'||ext=='ppt'||ext=='pptx'){  
	            flag=true;  
	        }  
	        return flag;  
	    } 
	function checkFileSize(value,id,comp){  
        if(!checkLast(value.toLowerCase())){  
            alert("上传文件格式错误！");  
            $("#scan_"+id).html('<input type="file" class="inputText" name="doc" theme="simple" id="'+id+'" title="文件后缀必须以以下结尾：doc,docx,pdf,xls,xlsx,xml,pptx,ppt" onchange="checkFileSize(this.value,this.id,'+comp+');"></s:file>');  
        }else{
        	value = value.replace(eval("/,/gi") ," ");
        	var fn = $("#"+comp).val();
        	if(fn != null && fn != 'undefinded' && fn != ''){
        		$("#"+comp).val(fn +","+value);
        	}else{
        		$("#"+comp).val(value);
        	}
        }
    } 
	
	/* 添加文件上传域 */
	function addFile(compid) {
		var id = getFileIdSerialNo();
		var htmlStr = '<tr><td><span id="scan_file_'+id+'"><input type="file" id="file_'
				+ id
				+ '" name="doc" class="inputText" title="文件后缀必须以以下结尾：doc,docx,pdf,xls,xlsx,xml,pptx,ppt"  onchange="checkFileSize(this.value,this.id,\''+compid+'\')"/>&nbsp;<a href="#" class=\"tablelink\" onclick="removeTr(this);return false;">移除</a></scan></td></tr>';
		$("#tab"+compid).append(htmlStr);
	}

	/* 删除添加的文件上传域 */
	function removeTr(obj) {
		//var rowIndex = obj.parentNode.parentNode.rowIndex;
		//document.getElementById("itemtb").deleteRow(rowIndex);
		var trObj = obj.parentNode.parentNode;
		trObj.parentNode.removeChild(trObj);
	}

	/* 删除附件 */
	function deleteFile(fileId,componentId) {
		if (fileId == undefined || fileId == null || fileId == '') {
			return;
		}
		if (confirm('您确认要彻底删除该附件吗？')) {
			var alarm_id = $("#alarm_id").val();
			var assetId = $("#assetId").val();
			var assetTypeId = $("#assetTypeId").val();
			debugger;
			var params = {
				fileId : fileId,
				componentId : componentId,
				alarm_id : alarm_id,
				assetId : assetId,
				assetTypeId : assetTypeId
			};
			var url = "${ctx}/alarm/values/deleteFile.action?";
			jQuery.getJSON(url, params, deleteFileHandler);
		}
	}

	/* 删除附件的回调函数 */
	function deleteFileHandler(data) {
		if (data == undefined || data == null) {
			alert('删除文件失败!');
			return;
		}
		if (data && data.status == '1') {
			var additionFileTag = document.getElementById("adFile_" + data.fileId);
			var parentNode = additionFileTag.parentNode;
			if (parentNode != undefined && parentNode != null){
				parentNode.removeChild(additionFileTag);
				alert('删除文件成功!');
			}
		} else if (data && data.status == '2') {
			alert(data.message);
		} else {
			alert('删除数据失败!');
		}
	}
	
	function selectAsset(){
		$("#opForm").submit();
	}
	function addAsset(){
		var baseUrl = "${ctx}/alarm/values/toEdit.action";
			$.registerForm('opForm');
			$("#viewType").val(2);
			$("#opForm").attr("action",baseUrl).submit();
		
	}
	function editAsset(alarm_id,assetId){
		var baseUrl = "${ctx}/alarm/values/toEdit.action?alarm_id="+alarm_id+"&assetId="+assetId;
		$("#viewType").val(0);
		$.registerForm('opForm');
		$("#opForm").attr("action",baseUrl).submit();
		
	}
	function copyAsset(assetId){
		var baseUrl = "${ctx}/alarm/values/queryCopyInfo.action?alarm_id="+assetId;
		baseUrl += "&viewType=0" ;
		debugger;
		baseUrl += "&menuId="+$("#menuId").val();
		baseUrl += "&assetTypeId="+ $("#assetTypeId").val();
		baseUrl += "&tabs=" + $("#tabs").val();
		window.location.href = baseUrl;
	}
	function viewAsset(alarm_id,assetId){
		var baseUrl = "${ctx}/alarm/values/queryInfo.action?alarm_id="+alarm_id+"&assetId="+assetId;
		
		$("#viewType").val(1);
		$.registerForm('opForm');
		$("#opForm").attr("action",baseUrl).submit();
		
	}
	function auditAsset(url,viewType){
		var baseUrl = url;
		$("#viewType").val(viewType);
		$.registerForm('opForm');
		$("#opForm").attr("action",baseUrl).submit();
		
	}
	function deleteAsset(){
		var baseUrl = "${ctx}/alarm/values/remove.action?";
		if( $(".chk:checked").size() != 0){
			$.dialog.confirm('你确定要删除选中的数据吗?', function(){
				var ids = "";
				$(".chk:checked").each(function(){
					ids += ","+$(this).val();	
				});
				ids = ids.substring(1);
				baseUrl += "ids="+ids;
				$.getJSON(baseUrl,{},function(data){
					if(data && data.status == '1'){
					     alert("操作成功");
					     $("#opForm").submit();
					}else{
						alert('操作失败!');
					}
				});
			}, function(){
			    $.dialog.tips('执行取消操作');
			});
		}else{
			$.dialog.alert('请选中需要删除的行数据!');
		}
	}
	function downloadExcel(){
		var baseUrl = "${ctx }/alarm/values/downloadExcel.action?";
		baseUrl += "assetTypeId="+$("#assetTypeId").val();
		$("#downloadFrame").attr("src",baseUrl);
	}

	function exportAsset(){
		var baseUrl = "${ctx }/alarm/values/exportAsset.action?";
		var ids = "";
		$(".chk:checked").each(function(){
			ids += ","+$(this).val();	
		});
		ids = ids.substring(1);
		baseUrl += "assetTypeId="+$("#assetTypeId").val();
		baseUrl += "&ids="+ids;
		$("#downloadFrame").attr("src",baseUrl);
		
	}

	function importAsset(){
		var baseUrl = "${ctx}/alarm/values/toImport.action?"; 
		$.registerForm('opForm');
		$("#opForm").attr("action",baseUrl).submit();
	}
	function changeTab(typeId){
		$("#assetTypeId").val(typeId);
		$("#opForm").submit();
	}
	
	