<%@page import="com.kingmed.dp.module.sys.entity.Menu"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  
  <script type="text/javascript">
	var ctx = "${ctx }";
  </script>
  
  <style type='text/css'>
  	label.control-label {
		padding-right: 0px;
		padding-bottom: 7px;
	}
	div.padding {
		padding-left: 0px;
	}
	
  </style>
	<section class="content-header">
       <h1>
                                  菜单管理
       </h1>
  <ol class="breadcrumb">
    <li><a href="#"><i class="fa fa-dashboard"></i> 系统管理</a></li>
    <li><a href="#">菜单管理</a></li>
    <!-- <li class="active">Data tables</li> -->
  </ol>
    </section>
    <section class="content">
    <div class="row">
	<div class="col-xs-12">
	<div class="box minWidthM">
		<div class="box-body">
		<div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
		<div class="row" id="table-div">
		</div>
		</div>
		</div>
	</div>
	</div>
	</div>
	</section>
	<%-- 修改、新增菜单页面 --%>
	<div class="modal fade" id="add-div" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="my_modal_title"></h4>
				</div>
				<div class="modal-body">
					<div style="width: 100%; text-align: center;">
						<form id="menuForm" class="form-horizontal" style="margin-top: 10px;text-align: center;" action="${ctx }/dp/menuMange/saveMenu" method="post">
						<input type="hidden" class="form-control" name="id" id="id" value="${id }">
						<div class="form-group">
							<label class="col-xs-3 control-label">父级菜单：</label>
							<div class="col-xs-4 padding">
								<input type="text" class="form-control" name="parentName" id="parentName" value="${menu.parent.name }" readonly="readonly">
								<input type="hidden" class="form-control" name="parentId" id="parentId" value="">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label" >名称 *：</label>
							<div class="col-xs-4 padding">
								<input type="text" class="form-control" name="name" id="name" value="${menu.name }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label" >链接：</label>
							<div class="col-xs-4 padding">
								<input type="text" class="form-control" name="href" id="href" value="${menu.href }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label" >目标：</label>
							<div class="col-xs-4 padding">
								<dict:select name="target" id="target" dictName="TARGET" defaultValue="true" cssClass="form-control select2 select2-hidden-accessible" />
								<%-- <input type="text" class="form-control" name="target" id="target" value="${menu.target }"> --%>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label" >权限标识：</label>
							<div class="col-xs-4 padding">
								<input type="text" class="form-control" name="permission" id="permission" value="${menu.permission }">
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label" >图标：</label>
							<div class="col-xs-4 padding">
								<dict:select name="icon" id="icon" dictName="MENU_ICON" defaultValue="true" cssClass="form-control select2 select2-hidden-accessible" />
								<!-- <select id="icon" name="icon" class="form-control select2 select2-hidden-accessible">
									<option value="fa fa-list-alt">菜单</option>
									<option value="fa fa-chain">链接</option>
									<option value="fa fa-gear">设置</option>
									<option value="fa fa-user">人员</option>
									<option value="fa fa-info">消息</option>
								</select> -->
								<%-- <input type="text" class="form-control" name="icon" id="icon" value="${menu.icon }"> --%>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label" >排序 *：</label>
							<div class="col-xs-4 padding">
								<input type="number" class="form-control" name="sort" id="sort" value="${menu.sort }" min="5" max="100000" step="5">
							</div>
						</div>
						<div class="form-group">
								<label class="col-xs-3 control-label" >是否在菜单中显示：</label>
								<div class="col-xs-4 padding">
									<dict:select name="isShow" id="isShow" dictName="menu_is_show" defaultValue="" cssClass="form-control select2 select2-hidden-accessible" />
									<!-- <select id="isShow" name="isShow" class="form-control select2 select2-hidden-accessible">
										<option value="1">显示</option>
										<option value="0">不显示</option>
									</select> -->
								</div>
								<!-- <div>
									<input type="radio" class="radio-inline" name="isShow" id="isTrue" value="1">显示
								</div>
								<div>
									<input type="radio" class="radio-inline" name="isShow" id="isFalse" value="0">不显示
								</div> -->
						</div>
						<div class="form-group">
							<label class="col-xs-3 control-label">备注信息：</label>
							<div class="col-xs-4 padding">
								<input type="text" class="form-control" name="remarks" id="remarks" value="${menu.remarks }">
							</div>
						</div>
						<div class="box-footer">
							<input class="btn btn-default" type="button" onclick="saveMenuAdmin()" value="保存">
							<input type="button" class="btn btn-default" aria-label="Close" data-dismiss="modal" value="取消"/>
						</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	

<!-- TreeTable -->
<script type="text/javascript" src="${ctxStatic}/AdminLTE/plugins/treeTable/jquery.treeTable.js"></script>

<script type="text/javascript" src="${ctxStatic}/modules/sys/manageMenu.js"></script>
