function subimtBtn() {
		if($("#img1").attr("src")!="images/0000.gif"){
    		window.alert("请先取消原照片");
    		return;
    	} 
		if($("#file1").val()==""){
			window.alert("请先选择照片");  
			return;
		}
		//转换成loading图片
		$("#img1").attr("src","images/loading.gif");
        var form = $("form[name=myform]");  
        var options  = {    
            url:'UpPhotoServlet',    
            type:'post', 
            //dataType:'json',
            clearForm:true,
            success:function(data)    
            {    
                var jsondata = eval("("+data+")");
                if(jsondata.length==1){
	                if(jsondata.error1 == "0"){  
	                    var url1 = jsondata.path1;
	                    //window.alert(url1); 
	                    $("#img1").attr("src","images/"+url1);
	                    $("#path").val(url1);
	                }else{
	                    window.alert("上传的图片出错");  
	                    $("#img1").attr("src","images/0000.gif");
	                } 
                }else{
                	window.alert("上传的图片出错");
                	$("#img1").attr("src","images/0000.gif");
                }
            },
            error:function(){
            	window.alert("上传失败");
            	$("#img1").attr("src","images/0000.gif");
            }   
        };    
        form.ajaxSubmit(options);  
        //$("#fileForm").submit();  
    }  
    //保存照片
    function saveBtn(){
    	if($("#path").val()==""){
    		window.alert("请先上传照片");
    		return;
    	}
    	if($("#img1").attr("src")=="images/0000.gif"){
    		window.alert("请先完成照片上传");
    		return;
    	} 
    	//var img_src=$("#img1").attr("src");
    	//window.alert(img_src);
    	var form=$("form[name=myform2]");
    	var options  = {    
            url:'SavePhotoServlet',    
            type:'post', 
            //dataType:'json',
            success:function(data)    
            {    
                var jsondata = eval("("+data+")");
                if(jsondata.error == "0"){ 
                    window.alert("保存成功");
                }else{
                    window.alert("保存失败");
                } 
            },
            error:function(){
                window.alert("保存失败");
            }   
        };    
        form.ajaxSubmit(options); 
    }
    //删除图片
    function deleteBtn(){
    	if($("#img1").attr("src")=="images/0000.gif"){
    		window.alert("没有可删除的照片");
    		return;
    	} 
    	//得到用户的照片路径
    	//var img_src=$("#img1").attr("src");
    	//$("#path").val(img_src);
    	var form=$("form[name=myform2]");
    	var options  = {    
            url:'DelPhotoServlet',    
            type:'post', 
            success:function(data)    
            {    
                var jsondata = eval("("+data+")");
                if(jsondata.error == "0"){
                    window.alert("删除成功");
                    $("#img1").attr("src","images/0000.gif");
                    $("#path").val("");
                }else if(jsondata.error == "1"){
                    window.alert("删除失败");
                }
            },
            error:function(){
                window.alert("删除失败");
            }   
        };    
        form.ajaxSubmit(options);
    }
    
    //判断文件的格式
    function check(obj){
    	//var strs = new Array(); //定义一数组
        var pic1= $("#"+obj.id).val();
        strs = pic1.split('.');
        var suffix = strs [strs .length - 1];

        if (suffix != 'jpg' && suffix != 'gif' && suffix != 'jpeg' && suffix != 'png') {
            alert("你选择的不是图片,请选择图片！");
            var file = document.getElementById(obj.id);
            file.outerHTML = file.outerHTML; //这样清空，在IE8下也能执行成功
             //obj.select(); document.selection.clear(); 好像这种方法也可以清空 input file 的value值，不过我没测试
        }
    }