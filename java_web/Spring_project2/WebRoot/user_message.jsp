<%@ page language="java" import="java.util.*,com.wyc.model.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mycss/user_message.css">
	<link rel="stylesheet" type="text/css" href="mycss/foot_common.css"/>
  </head>
  
  <body>
  <div class="right_div">
		<div class="right_div_div1">
			<font><a href="main_manager.jsp">返回管理页面</a></font>
		</div>
		<div class="right_div_div2">
			<font><a href="user_search.jsp">重新查询</a></font>
		</div>
		<div class="right_div_div3">
			<font><a href="LogoutServlet">安全退出</a></font>
		</div>
 </div>
  <center>
  <h1>查询结果</h1>
  <div class="main_div">
  	<div class="main_div_div1">
  		<a href="TestManagerServlet?User_id=${user.user_id }&User_name=${user.user_name}"><img id="img1" alt="查看记录" src="${user.path }" width="80%" height="80%"/></a>
  	</div>
  	<div>
	  	<div class="main_div_div2">
	  		<h2>用户信息</h2>
	  		<table>
	  			<tr><td>用户ID:</td><td><c:out value="${user.user_id }"></c:out></td></tr>
	  			<tr><td>用户名:</td><td><c:out value="${user.user_name }"></c:out></td></tr>
	  		</table>
	  	</div>
  	</div>
  </div>
  </center>
  <jsp:include page="foot_common.jsp"></jsp:include>
  </body>
</html>
