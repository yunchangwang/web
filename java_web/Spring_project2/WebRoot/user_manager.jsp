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
    
    <title>病患管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mycss/foot_common.css"/>
	<link rel="stylesheet" type="text/css" href="mycss/user_manger.css"/>
	</head>
	<body>
	<div class="right_div">
		<div class="right_div_div1">
			<font><a href="main_manager.jsp">返回管理页面</a></font>
		</div>
		<div class="right_div_div2">
			<font><a href="user_search.jsp">查询用户</a></font>
		</div>
		<div class="right_div_div3">
			<font><a href="LogoutServlet">安全退出</a></font>
		</div>
	</div>
	<center>
	<h1>病患管理</h1>
	<div class="main_div">
		<c:forEach items="${List }" var="user">
			<div class="div1">
				<a href="TestManagerServlet?User_id=${user.user_id }&User_name=${user.user_name}"><img alt="头像" src="${user.path }"/></a>
				<font>姓名:${user.user_name }</font>
				<a href="MainPhotoServlet?User_id=${user.user_id }&User_name=${user.user_name}"><font>上传照片</font></a>
			</div>
		</c:forEach>
	</div>
	<c:choose>
		<c:when test="${pageCount!=0 }">
			<a href="UserManagerServlet?pageNow=1">首页</a>
			<c:if test="${pageNow!=1 }">
				<a href="UserManagerServlet?pageNow=${pageNow-1 }">【上一页】</a>
			</c:if>
			<c:choose>
				<c:when test="${pageCount>8+pageNow }">
					<c:forEach begin="${pageNow }" end="${pageNow+8 }" step="1" var="i">
						<a href="UserManagerServlet?pageNow=i">【${i }】</a>
					</c:forEach>
					<a href="UserManagerServlet?pageNow=${pageNow+8 }">【...】</a>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageNow }" end="${pageCount }" step="1" var="i">
						<a href="UserManagerServlet?pageNow=${i }">【${i }】</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageNow!=pageCount }">
				<a href="UserManagerServlet?pageNow=${pageNow+1 }">【下一页】</a>
			</c:if>
			<a href="UserManagerServlet?pageNow=${pageCount }">尾页</a>
		</c:when>
		<c:otherwise>
			<h1>目前为止还没有用户</h1>
		</c:otherwise>
	</c:choose>
	</center>
	<jsp:include page="foot_common.jsp"></jsp:include>
	</body>
</html>
