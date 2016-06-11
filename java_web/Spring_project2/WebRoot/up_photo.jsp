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
    
    <title>照片上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="mycss/up_photo.css"/>
	<link rel="stylesheet" type="text/css" href="mycss/foot_common.css"/>
	<script type="text/javascript" src="jsUntil/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="jsUntil/jquery-form.js"></script>
	<script type="text/javascript" src="myjs/up_photo.js"></script>
	<script type="text/javascript">
		//在加载网页的同时访问后台得到头像路径
		$(document).ready(function(){
			var form=$("form[name=myform2]");
	    	var options  = {    
	            url:'GetPathServlet',    
	            type:'post', 
	            success:function(data)    
	            {    
	                var jsondata = eval("("+data+")");
	                if(jsondata.error == "0"){ 
	                	var pic1=jsondata.path;
	                    $("#img1").attr("src",pic1);
	                    strs = pic1.split('/');
       					var path = strs [strs .length - 1];
       					$("#path").val(path);
	                }else{
	                   window.alert("头像加载失败,请刷新");
	                   $("#img1").attr("src","images/0000.gif");
	                }
	            },
	            error:function(){
	                window.alert("头像加载失败,请刷新");
	                $("#img1").attr("src","images/0000.gif");
	            }   
	        };    
	        form.ajaxSubmit(options);
		});
	</script>
  </head>
  
  <body>
  <div class="right_div">
		<div class="right_div_div1">
			<font><a href="UserManagerServlet">病患管理</a></font>
		</div>
		<div class="right_div_div2">
			<font><a href="LogoutServlet">安全退出</a></font>
		</div>
</div>
  <center>
  <h1>上传照片</h1>
  <div class="main_div">
  	<div class="main_div_div1">
  		<img id="img1" alt="头像" src="" width="80%" height="80%"/>
  		<form name="myform" method="post" enctype="multipart/form-data">
       		<input type="file" id="file1" name="file" value="浏览" onchange="check(this)"/><br/>
       		<input type="button" value="上传" onclick="subimtBtn()"/>
       		<input type="button" id="save" value="保存" onclick="saveBtn()"/>
       		<input type="button" id="delete" value="取消" onclick="deleteBtn()"/>
    	</form>
    	<form name="myform2" method="post">
    		<input type="hidden" name="User_id" value="${user.user_id }"/>
       		<input type="hidden" name="User_name" value="${user.user_name }"/>
    		<input id="path" type="hidden" name="path" value=""/>
    	</form>
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
