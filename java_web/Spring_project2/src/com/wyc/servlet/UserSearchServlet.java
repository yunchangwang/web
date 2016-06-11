package com.wyc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.model.User;
import com.wyc.service.adminService;
import com.wyc.unitl.ApplicationContextUnitl;

@SuppressWarnings("serial")
public class UserSearchServlet extends HttpServlet {

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
		response.setCharacterEncoding("utf-8");
		//得到查询用户的信息
		String message=request.getParameter("message");
		//封装用户信息
		int User_id=0;
		User user=null;
		adminService adminservice=(adminService)ApplicationContextUnitl.getApplicationContext().getBean("adminService");
		try {
			User_id=Integer.parseInt(message);
			user=adminservice.select_user_one(User_id,message);
		} catch (Exception e) {
			// TODO: handle exception
			user=adminservice.select_user_one(-1,message);
		}
		if(user!=null){
			//将用户信息放入request
			request.setAttribute("user", user);
			//跳转到指定的页面
			request.getRequestDispatcher("user_message.jsp").forward(request, response);
		}else{
			request.setAttribute("error_no", "1");
			request.getRequestDispatcher("user_search.jsp").forward(request, response);
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
