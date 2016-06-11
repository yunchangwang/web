package com.wyc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.model.fenye;
import com.wyc.model.report;
import com.wyc.service.reportService;
import com.wyc.unitl.ApplicationContextUnitl;

@SuppressWarnings("serial")
public class TestManagerServlet extends HttpServlet {

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
		//获得用户的测试信息
		int pageNow=1;
		int pageSize=5;
		if(request.getParameter("pageNow")!=null){
			pageNow=Integer.parseInt(request.getParameter("pageNow"));
		}
		int User_id=Integer.parseInt(request.getParameter("User_id"));
		String User_name=new String(request.getParameter("User_name").getBytes("ISO-8859-1"),"UTF-8");
		//创建service类
		reportService reportservice=(reportService)ApplicationContextUnitl.getApplicationContext().getBean("reportService");
		fenye f=reportservice.select_userAccuracy_all(User_id,pageNow,pageSize);
		ArrayList<report> list=f.getList2();
		int pageCount=f.getPageCount();
		if(pageCount<pageNow&&pageCount!=0){
			pageNow=pageNow-1;
		}
		//将信息放入request
		request.setAttribute("User_id", User_id);
		request.setAttribute("User_name", User_name);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("list", list);
		//跳转页面
		request.getRequestDispatcher("test_manager.jsp").forward(request, response);
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
