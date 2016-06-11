function check_id(){
	if($("name").value==""){
		$("span1").innerText="请填写正确的用户名";
		return false;
	}else{
		$("span1").innerText="";
		return true;
	}
}
function check_password(){
	if($("password").value==""){
		$("span2").innerText="密码不能为空";
		return false;
	}else{
		$("span2").innerText="";
		return true;
	}
}
function check_all(){
	if(check_id()&&check_password()){
		clear_all();
	}else{
		return false;
	}
}
function clear_all(){
	var list=document.getElementsByTagName("span");
	for (var i=0; i<list.length;i++ )
	{
		list[i].innerText="";
	}
}

function $(id){
	return document.getElementById(id);
}

//验证码
function reflushCode(obj){
	obj.src="CodeServlet?d="+new Date();
}