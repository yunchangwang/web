/**
 * 负责IOS的手机端接收用户验证时传递的信息
 */
package com.wyc.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.Services.LoginService;
import com.wyc.model.User;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class test extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//得到网络输出流
		PrintWriter pw=response.getWriter();
		//接收用户姓名和密码
		String user_name=request.getParameter("name");
		String password=request.getParameter("password");
		//将信息封装
		User user=new User();
		user.setUser_name(user_name);
		user.setPassword(password);
		//创建json类
		JSONObject obj=new JSONObject();
		String check_type=new LoginService().check_user(user);
		if(!check_type.equals("nouser_error")&&!check_type.equals("password_error")){
			System.out.println(user_name+" "+password);
			//填写json信息
			obj.put("type","success");
			obj.put("user_id", check_type);
			//obj.put("username", "wyc");
			//obj.put("password","123");
		}else{
			obj.put("type",check_type);
			System.out.println("error");
		}
		//发送会手机端
		pw.println(obj.toString());
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
