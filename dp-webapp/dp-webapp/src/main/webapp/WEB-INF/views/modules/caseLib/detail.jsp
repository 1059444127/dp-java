<%@page
	import="com.kingmed.dp.modules.sys.security.SystemAuthorizingRealm.Principal"%>
<%@page import="com.kingmed.dp.module.sys.utils.UserUtils"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>金域病理</title>
  <script type="text/javascript">
	var ctx = "${ctx }";
	var ctxStatic = "${ctxStatic}";
  </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<header class="main-header"> <!-- Logo --> <a
			href="${ctx }/dp/login" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
			<span class="logo-mini"><b>金</b>域</span> <!-- logo for regular state and mobile devices -->
			<span class="logo-lg" style="font-size:18px;"><b>金域病理远程会诊平台</b></span>
		</a> <!-- Header Navbar: style can be found in header.less --> <nav
			class="navbar navbar-static-top" role="navigation"> <!-- Sidebar toggle button-->
		<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
			role="button"> <span class="sr-only">Toggle navigation</span>
		</a> <!-- Navbar Right Menu -->
		<div class="navbar-custom-menu">
			<ul class="nav navbar-nav">
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <img
						src="${ctxStatic}/AdminLTE/dist/img/user2-160x160.jpg"
						class="user-image" alt="User Image"> <span class="hidden-xs"><shiro:principal property="loginName"/></span>
				</a>
					<ul class="dropdown-menu">
						<!-- Menu Footer-->
						<li class="user-footer">
							<!-- <div class="pull-left">
								<a href="javascript:void(0);" class="btn btn-default btn-flat">Profile</a>
							</div> -->
							<div class="pull-right">
								<a href="${ctx }/dp/logout" id="logout" onclick="logout(this);return false;"
									class="btn btn-default btn-flat">退出登陆</a><!-- Sign out -->
							</div>
						</li>
					</ul>
				</li>
			</ul>
		</div>

		</nav> 
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
		<section class="sidebar"> <!-- Sidebar user panel -->
		
		<ul id="leftMenu" class="sidebar-menu">
		</ul>
		</section> <!-- /.sidebar --> 
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper" style="min-height: 400px;">
			<!-- Content Header (Page header) -->
			<div id="contentText" class="tab-content" style="overflow-x:auto;overflow-y:hidden">
			<c:if test="${ !empty caseLib}">
				<%@include file="slide_detail.jsp" %>
			</c:if>
			<c:if test="${ !empty readFilm}">
				<%@include file="readFilm_detail.jsp" %>
			</c:if>
			</div>	
				
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>版本</b> 1.0
		</div>版权所有© 
		<strong> 
 　　			金域检验 2013-2015。
		</strong> 保留一切权利。<!-- All rights reserved. --> 
		</footer>
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->
	
	<script type="text/javascript" src="${ctxStatic}/modules/sys/index.js"></script>

	<script type="text/javascript">
	
</script>
</body>
</html>