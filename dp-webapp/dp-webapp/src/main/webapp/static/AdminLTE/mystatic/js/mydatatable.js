/**
 * datatables分页、搜索
 * 通过officeTable.column(num).search(this.value).draw();绑定查询事件，nun原表示第几列 num<8
 * 通过返回值.sSearch_[num]取搜索框中条件数据,例：num=2,给前端name输入框绑定keyup事件，
 * 则returnArray.name = returnArray.sSearch_2,将name参数设置到参数列表中
 * datas   datatables中的aoData
 */
function getMyParams(datas){
	var pageSize;//分页大小
	var pageNo;//第几页
	var sorts="";//排序
	var sSearch_0 = "", sSearch_1 = "", sSearch_2 = "", sSearch_3 = "", sSearch_4 = "", sSearch_5 = "", sSearch_6 = "", sSearch_7 = "", sSearch_8 = "";
	
	var sortDir = "";//升序或是降序
	var sortcol = 0;//第几列排序
	for (num in datas) {
		if (datas[num]['name'] == 'iSortCol_0') {
			sortcol = datas[num]['value'];
		}
	}
	for (num in datas) {
		if (datas[num]['name'] == 'iDisplayLength') {
			pageSize = datas[num]['value'];
		} else if (datas[num]['name'] == 'iDisplayStart') {
			pageNo = datas[num]['value'];
		} else if (datas[num]['name'] == 'sSortDir_0') {
			sortDir = datas[num]['value'];
		} else if (datas[num]['name'] == ('mDataProp_' + sortcol)) {
			sorts = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_0') {
			sSearch_0 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_1') {
			sSearch_1 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_2') {
			sSearch_2 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_3') {
			sSearch_3 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_4') {
			sSearch_4 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_5') {
			sSearch_5 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_6') {
			sSearch_6 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_7') {
			sSearch_7 = datas[num]['value'];
		}else if (datas[num]['name'] == 'sSearch_8') {
			sSearch_8 = datas[num]['value'];
		}
	}
	pageNo = pageNo / pageSize + 1;
	if(sorts.indexOf(".")!=-1){
		sorts.replace(".", "_");
	}else{
		sorts = sorts + " " + sortDir;
	}
	var params = {"pageSize":pageSize,"pageNo":pageNo,"orderBy":sorts};
	params.sSearch_0 = sSearch_0;
	params.sSearch_1 = sSearch_1;
	params.sSearch_2 = sSearch_2;
	params.sSearch_3 = sSearch_3;
	params.sSearch_4 = sSearch_4;
	params.sSearch_5 = sSearch_5;
	params.sSearch_6 = sSearch_6;
	params.sSearch_7 = sSearch_7;
	params.sSearch_8 = sSearch_8;
	
	return params;
}

function initMyDatatables(tableid,url,colData,retrieveData,sortNum){
	return table = $('#'+tableid).DataTable({
		"bServerSide" : true,
		"sAjaxSource" : url,
		"fnServerData" : retrieveData,
		"bSort" : true,
		"bDeferRender": true,
		"bAutoWidth": false,//自动宽度
		"scrollX": true,
		columns : colData,
		"sPaginationType" : "full_numbers", //分页，一共两种样式，full_numbers和two_button(默认) 
		"oLanguage" : {
			"sProcessing" : "正在加载中......",
			"sLengthMenu" : "每页显示 _MENU_ 条记录",
			"sZeroRecords" : "没有数据！",
			"sEmptyTable" : "表中无数据存在！",
			"sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
			"sInfoEmpty" : "显示0到0条记录",
			"sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
			"sSearch" : "搜索",
			"oPaginate" : {
				"sFirst" : "首页",
				"sPrevious" : "上一页",
				"sNext" : "下一页",
				"sLast" : "末页"
			}
		},
		order : [ sortNum, 'asc' ],
		"dom": '<"top"l>rt<"bottom"ip><"clear">'//隐藏搜索框
	});
}
var myOperation = {
		"data" : "id",
		orderable : false,
		"mRender" : function(data, type, full) {
			return "<button id='editrow' class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>&nbsp;<button id='delrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button>";
		}
	};

