/**
 * 负责android的手机端接收用户验证时传递的信息
 */
package com.wyc.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wyc.Services.LoginService;
import com.wyc.model.User;

@SuppressWarnings("serial")
public class test_adr extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8"); 
		PrintWriter pw=response.getWriter();
		
		InputStream in = request.getInputStream();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        String line = null;  
        StringBuffer s = new StringBuffer();  
        while ((line = reader.readLine()) != null) {  
            s.append(line);  
        }  
        reader.close();  
        
        JSONObject json = JSONObject.fromObject(s.toString());
        String user_name=json.getString("name");
        //String user_name = new String(json.getString("name").getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(user_name);
        String password = json.getString("password");  
		
		//将信息封装
		User user=new User();
		user.setUser_name(user_name);
		user.setPassword(password);
		JSONObject obj=new JSONObject();
		String check_type=new LoginService().check_user(user);
		if(!check_type.equals("nouser_error")&&!check_type.equals("password_error")){
			System.out.println(user_name+" "+password);
			obj.put("type","success");
			obj.put("user_id", check_type);
			//obj.put("username", "wyc");
			//obj.put("password","123");
		}else{
			obj.put("type",check_type);
			System.out.println("error");
		}
		pw.println(obj.toString());
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  this.doGet(request, response);
	}
	
	
}
