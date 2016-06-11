package com.wyc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.wyc.service.photoService;
import com.wyc.unitl.ApplicationContextUnitl;

@SuppressWarnings("serial")
public class GetPathServlet extends HttpServlet {

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
		//得到用户的ID
		String User_id=request.getParameter("User_id");
		//得到用户的头像路径
		photoService photoservice=(photoService) ApplicationContextUnitl.getApplicationContext().getBean("photoservice");
		String path=photoservice.get_path(User_id);
		//json返回Ajax
		JSONObject obj=new JSONObject();
		if(path!=null){
			obj.put("error", 0);
			obj.put("path", path);
		}else{
			obj.put("error", 1);
		}
		PrintWriter pw=response.getWriter();
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
