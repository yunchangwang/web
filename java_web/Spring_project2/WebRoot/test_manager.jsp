<%@ page language="java" import="java.util.*,com.wyc.model.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测试结果</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mycss/test_manager.css">
	<link rel="stylesheet" type="text/css" href="mycss/foot_common.css"/>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="myjs/test_manager.js"></script>
  </head>
  
  <body>
  	<script src="js/highcharts.js"></script>
	<script src="js/modules/exporting.js"></script>
	<script src="js/modules/data.js"></script>
	<h1 style="text-align: center;">用户${User_name }的测试结果</h1>
    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

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
<table id="datatable">
	<thead>
		<tr>
			<th></th>
			<th>测试数量</th>
			<th>正确率</th>
			<th>反应时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="report" varStatus="status">
			<tr>
				<th>第${status.index+1 }次</th>
				<td>${report.photo_nums }</td>
				<td>${report.accuracy }</td>
				<td>${report.action_time }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	<c:choose>
		<c:when test="${pageCount!=0 }">
			<a href="TestManagerServlet?pageNow=1&User_id=${User_id }&User_name=${User_name}">首页</a>
			<c:if test="${pageNow!=1 }">
				<a href="TestManagerServlet?pageNow=${pageNow-1 }&User_id=${User_id }&User_name=${User_name}">【上一页】</a>
			</c:if>
			<c:choose>
				<c:when test="${pageCount>8+pageNow }">
					<c:forEach begin="${pageNow }" end="${pageNow+8 }" var="i">
						<a href="TestManagerServlet?pageNow=${i }&User_id=${User_id }&User_name=${User_name}">【${i }】</a>
					</c:forEach>
					<a href="TestManagerServlet?pageNow=${pageNow+8 }&User_id=${User_id }&User_name=${User_name}">【...】</a>
				</c:when>
				<c:otherwise>
					<c:forEach begin="${pageNow }" end="${pageCount }" var="i">
						<a href="TestManagerServlet?pageNow=${i }&User_id=${User_id }&User_name=${User_name}">【${i }】</a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:if test="${pageNow!=pageCount }">
				<a href="TestManagerServlet?pageNow=${pageNow+1 }&User_id=${User_id }&User_name=${User_name}">【下一页】</a>
			</c:if>
			<a href="TestManagerServlet?pageNow=${pageCount }&User_id=${User_id }&User_name=${User_name}">尾页</a>
		</c:when>
		<c:otherwise>
			<h1>目前为止还没有记录</h1>
		</c:otherwise>
	</c:choose>
	<div class="foot_div">
	  	<hr/>
	  	<div class="foot_div_div1"><span>本网站由杭州电子科技大学所有<a href="http://www.hdu.edu.cn"><img id="img2" src="images/hangdian.jpg" style="cursor:pointer;"/></a></span></div>
	  	<div class="foot_div_div2"><span>遇到问题请联系:15988174838</span></div>
	</div>
  </body>
</html>
