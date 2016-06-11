<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mycss/user_search.css"/>
	<link rel="stylesheet" type="text/css" href="mycss/foot_common.css"/>
  </head>
  
  <body>
  	<div class="right_div">
		<div class="right_div_div1">
			<font><a href="main_manager.jsp">返回管理页面</a></font>
		</div>
		<div class="right_div_div2">
			<font><a href="LogoutServlet">安全退出</a></font>
		</div>
	</div>
    <center>
    	<h1>用户查询</h1>
    	<form action="UserSearchServlet" method="post">
    		<table>
    			<tr><td>请输入用户名或者用户ID</td><td><input type="text" name="message"/></td><td><input value="查询" type="submit"/></td></tr>
    		</table>
    	</form>
    	<c:choose>
    		<c:when test="${error_no==1 }">
    			<font color="red" size="20px">该用户不存在</font>
    		</c:when>
    	</c:choose>
    </center>
    <jsp:include page="foot_common.jsp"></jsp:include>
  </body>
</html>
