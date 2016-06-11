package com.wyc.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.model.User;
import com.wyc.model.fenye;
import com.wyc.service.adminService;
import com.wyc.unitl.ApplicationContextUnitl;

@SuppressWarnings("serial")
public class UserManagerServlet extends HttpServlet {

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
		//�õ������û������������Ϣ
		int pageNow=1;
		int pageSize=8;
		if(request.getParameter("pageNow")!=null){
			pageNow=Integer.parseInt(request.getParameter("pageNow"));
		}
		//������Ӧ��service������
		adminService adminservice=(adminService)ApplicationContextUnitl.getApplicationContext().getBean("adminService");
		fenye f=adminservice.select_userName_all(pageNow, pageSize);
		ArrayList<User> List=f.getList();
		int pageCount=f.getPageCount();
		if(pageCount<pageNow&&pageCount!=0){
			pageNow=pageNow-1;
		}
		//����Ϣ���뵽request��
		request.setAttribute("List", List);
		request.setAttribute("pageNow", pageNow);
		request.setAttribute("pageCount", pageCount);
		//��תҳ��
		request.getRequestDispatcher("user_manager.jsp").forward(request, response);
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
