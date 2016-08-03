<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<link rel="stylesheet" href="${ctxStatic}/AdminLTE/mystatic/css/login.css">
<script src="${ctxStatic}/AdminLTE/mystatic/js/login.js" ></script>
<html>
<head>
  	<title>金域检验远程病理</title>
</head>
<body id="bodyid">
<img src='${ctxStatic }/AdminLTE/mystatic/images/bg.png' width="100%" height="100%" style="z-index:-100;position:absolute;left:0;top:0">
<form action="${ctx}/dp/login" method="post" id="loginform" autocomplete="off">
<div align="center" class="container loginBg">
    <div class="login">
		<ul class="titleName">
        	<li class="english"></li>
            <li class="chinese"></li>
        </ul>
        <ul class="loginInfo">
            <li>
	            <span>账号：</span>
	            <input type="text" class="userName" onblur="disableTips(this)" onfocus="showTips(this,'请输入您的账号')" value="" name="username" id="username"/>
            </li>
            <li>
            	<span>密码：</span>
            	<input class="password" type=password name=password onblur="disableTips(this)" onfocus="showTips(this,'请输入您的密码')" value="" name="password" id="password" autocomplete="off"/>            
            </li>
           	
<!-- 				<li class="checkCode"> -->
<!-- 					<span>验证码：</span> -->
<!-- 					<s:textfield theme="simple"  onblur="disableTips(this)" onfocus="showTips(this,'{*[page.login.character]*}')" name="checkcode" /> -->
<%-- 					<img src="<s:url value="/checkCodeImg"/>" align="absmiddle" onclick="getSecurityCode();" width="70" height="26" id="checkCodeImg" /> --%>
<!-- 					<span class="invisibility" onclick="getSecurityCode()">看不清</span> -->
<!-- 				</li> -->
				<li class="notcheckCode">
				</li>
            <li class="keepInfo"><input type="checkbox" name="keepinfo" value="yes"/>记住密码</li> 
            <li class="tipsArea"><div id="tipsArea">&nbsp;</div></li>           
            <li class="btnOk"><input type="submit" alt="登录" value="登录" class="loginBtn loginBg" /></li>
        </ul>
    </div>
    <div class="errorMsg">
	<c:if test="${message !='' && message != null }">
		<span class="errorMessage"> 
		<b>错误:</b>
		${message}
		</span>
	</c:if>
	</div>
    <div class="copyright">
    	<span>Copyright © 2013 广州金域医学检验中心有限公司</span>
    </div>       
</div>
</form>
</body>
</html>
