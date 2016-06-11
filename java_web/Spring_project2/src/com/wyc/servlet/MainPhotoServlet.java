package com.wyc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.model.User;
import com.wyc.unitl.ApplicationContextUnitl;

@SuppressWarnings("serial")
public class MainPhotoServlet extends HttpServlet {

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

		//得到上传照片所需的信息
		String User_id=null;
		String User_name=null;
		if(request.getParameter("User_id")!=null){
			User_id=request.getParameter("User_id");
		}
		if(request.getParameter("User_name")!=null){
			User_name=new String(request.getParameter("User_name").getBytes("ISO-8859-1"),"UTF-8");
		}
		//将信息封装
		User user=(User)ApplicationContextUnitl.getApplicationContext().getBean("user");
		user.setUser_id(Integer.parseInt(User_id));
		user.setUser_name(User_name);
		//将信息加入到request
		request.setAttribute("user", user);
		//跳转页面
		request.getRequestDispatcher("up_photo.jsp").forward(request, response);
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
