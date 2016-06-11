package com.wyc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.service.diskService;
import com.wyc.service.photoService;
import com.wyc.unitl.ApplicationContextUnitl;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class DelPhotoServlet extends HttpServlet {

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

		response.setCharacterEncoding("utf-8");
		String User_id=request.getParameter("User_id");
		String User_name=request.getParameter("User_name");
		String path=request.getParameter("path");
		//删除头像和备份头像
		//1.得到头像的路径
		String delPath=request.getSession().getServletContext().getRealPath("/")+"images/"+path;
		//2.得到备份头像的路径
		String back_path=request.getSession().getServletContext().getRealPath("/")+"images/"+User_name+"/"+path;
		//3.调用删除
		diskService diskservice=(diskService) ApplicationContextUnitl.getApplicationContext().getBean("diskService");
		diskservice.del_head(delPath, back_path);
		JSONObject obj = new JSONObject();
		//从数据库修改头像路径
		photoService photoservice=(photoService) ApplicationContextUnitl.getApplicationContext().getBean("photoservice");
		if(photoservice.delete_photo(User_id)==1){
			obj.put("error", 0);
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
