<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mycss/login.css"/>
	<link rel="stylesheet" type="text/css" href="mycss/foot_common.css"/>
	<script type="text/javascript" src="myjs/login.js"></script>
  </head>
  
  <body>
  	<center>
    <div class="main_div">
    	<h1>管理员登录</h1>
    	<form action="LoginServlet" method="post">
    		<table>
			<tr>
			<td>用户姓名:</td>
			<td><input type="text" name="name" id="name"/></td>
			<td><span id="span1"></span></td>
			</tr>
			<tr>
			<td>用户密码:</td>
			<td><input type="password" name="password" id="password" onclick="return check_id()"/></td>
			<td><span id="span2"></span></td>
			</tr>
			<tr>
			<td>验证码:</td>
			<td><input type="text" name="code"/></td>
			<td><img src="CodeServlet" onclick="reflushCode(this);" title="看不清楚,点击刷新" style="cursor:pointer;"/></td>
			</tr>
			<tr>
			<td><input type="submit" value="登录" onclick="return check_all()"/>
			</td><td><input type="reset" value="重新填写" onclick="clear_all()"/></td>
			</tr>
		</table>
    	</form>
    	<c:choose>
    		<c:when test="${error_no==1 }">
    			<font color="red" size="20px">该用户不存在</font>
    		</c:when>
    		<c:when test="${error_no==3 }">
    			<font color="red" size="20px">密码错误</font>
    		</c:when>
    		<c:when test="${error_no==4 }">
    			<font color="red" size="20px">验证码错误</font>
    		</c:when>
    	</c:choose>
    </div>
    </center>
    <jsp:include page="foot_common.jsp"></jsp:include>
  </body>
</html>
