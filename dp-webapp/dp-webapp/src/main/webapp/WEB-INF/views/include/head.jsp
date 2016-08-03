<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="http://www.kingmed.com.cn/"/>
<meta name="renderer" content="webkit"><meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10,IE=edge" />
<meta http-equiv="Expires" content="0"><meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Cache-Control" content="no-store">

<title>金域检验远程病理</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=0.5,maximum-scale=4.0, user-scalable=yes" name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/dist/css/AdminLTE.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/plugins/iCheck/square/blue.css">
<!-- DATA TABLES -->
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/plugins/datatables/dataTables.bootstrap.css">
<%-- <link rel="stylesheet" href="${ctxStatic}/AdminLTE/plugins/datatables/jquery.dataTables.min.css"> --%>
<!-- daterange picker -->
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/plugins/daterangepicker/daterangepicker-bs3.css">
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/plugins/datepicker/datepicker3.css">

<link rel="stylesheet" href="${ctxStatic}/AdminLTE/dist/css/skins/_all-skins.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->


<!-- jQuery 2.2.0 -->
<script type="text/javascript" src="${ctxStatic}/AdminLTE/plugins/treeTable/jquery.js"></script>
<script src="${ctxStatic}/AdminLTE/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="${ctxStatic}/AdminLTE/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="${ctxStatic}/AdminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${ctxStatic}/AdminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- FastClick -->
<script src="${ctxStatic}/AdminLTE/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${ctxStatic}/AdminLTE/dist/js/app.min.js"></script>
<!-- Sparkline -->
<script src="${ctxStatic}/AdminLTE/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${ctxStatic}/AdminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${ctxStatic}/AdminLTE/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- SlimScroll 1.3.0 -->
<script src="${ctxStatic}/AdminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS 1.0.1 -->
<script src="${ctxStatic}/AdminLTE/plugins/chartjs/Chart.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${ctxStatic}/AdminLTE/dist/js/demo.js"></script>
<!-- date-range-picker -->
<script src="${ctxStatic}/AdminLTE/plugins/daterangepicker/moment.min.js"></script>
<script src="${ctxStatic}/AdminLTE/plugins/daterangepicker/daterangepicker.js"></script>
<script src="${ctxStatic}/AdminLTE/plugins/datepicker/bootstrap-datepicker.js"></script>

<script src="${ctxStatic}/AdminLTE/mystatic/js/mydatatable.js"></script>

<!-- ztree -->
<script src="${ctxStatic}/AdminLTE/plugins/zTree/zTree_config.js"></script>
<link href="${ctxStatic}/AdminLTE/plugins/zTree/css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
<script src="${ctxStatic}/AdminLTE/plugins/zTree/js/jquery.ztree.all-3.2.min.js" ></script>

<!-- 可收缩菜单（左侧隐藏） -->
<script src="${ctxStatic}/AdminLTE/mystatic/js/menufold.js" ></script>
<link href="${ctxStatic}/AdminLTE/mystatic/css/custom.css" rel="stylesheet">

<!-- 表单验证js -->
<script src="${ctxStatic}/AdminLTE/mystatic/js/form_validate.js" ></script>