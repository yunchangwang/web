<%@ page language="java" import="java.util.*,com.wyc.unitl.*,com.wyc.model.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主管理页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mycss/main_manage.css">
	<link rel="stylesheet" type="text/css" href="mycss/foot_common.css"/>
  </head>
  
  <body>
    <center>
	<h1>welcome Master <c:out value="${Admin.admin_name }"></c:out></h1>
	<table>
		<tr><td id="td"><a href="UserManagerServlet">病患管理</a></td></tr>
		<tr><td id="td"><a href="user_search.jsp">查询病患</a></td></tr>
		<tr><td id="td"><a href="LogoutServlet">安全退出</a></td></tr>
	</table>
	</center>
	<jsp:include page="foot_common.jsp"></jsp:include>
  </body>
</html>
