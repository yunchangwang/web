package com.wyc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.model.Admin;
import com.wyc.service.adminService;
import com.wyc.unitl.ApplicationContextUnitl;
import com.wyc.unitl.LogMessageUnitl;
import com.wyc.unitl.SessionUnitl;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

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
		//乱码解决
		response.setCharacterEncoding("utf-8");
		//得到管理员姓名和密码
		String User_name=request.getParameter("name");
		String User_password=request.getParameter("password");
		//得到验证码
		String Code=request.getParameter("code");
		if(!SessionUnitl.getCode(request).equals(Code)){
			request.setAttribute("error_no", "4");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		//封装到相应的model
		Admin admin=(Admin) ApplicationContextUnitl.getApplicationContext().getBean("admin");
		admin.setAdmin_name(User_name);
		admin.setAdmin_password(User_password);
		//用相应的service解决
		String flag=((adminService)ApplicationContextUnitl.getApplicationContext().getBean("adminService")).check_Admin(admin);
		if(flag!="2"){
			request.setAttribute("error_no", flag);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else{
			//登录成功后将管理员信息加入Session
			SessionUnitl.setAdmin(request, admin);
			//注销验证码
			SessionUnitl.deleteCode(request);
			//将信息写入文件中
			LogMessageUnitl.setLoginMessage(admin, request.getSession().getServletContext().getRealPath("/")+"login_message.log");
			request.getRequestDispatcher("main_manager.jsp").forward(request, response);
		}
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
