<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>金域病理远程会诊平台</title>
<script type="text/javascript">

</script>
<style type="text/css">

.picture_right{
	position: absolute; 
	left: 0px; 
	top: 0px; 
	width: 100%; 
	height: 100%;
}
</style>
</head>
<body>
<iframe class="picture_right" id="iframe1" name="" title="" src="${data.url }"></iframe>

</body>
</html>