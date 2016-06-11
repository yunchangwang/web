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
import com.wyc.unitl.LogMessageUnitl;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class SavePhotoServlet extends HttpServlet {

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
		//将头像进行备份(每个用户都有一个自己的文件夹)
		//1.获得用户文件夹路径
		String back_path=request.getSession().getServletContext().getRealPath("/")+"images/"+User_name;
		//2.获得照片路径
		String up_path=request.getSession().getServletContext().getRealPath("/")+"images/"+path;
		//2.备份文件
		diskService disservice=(diskService) ApplicationContextUnitl.getApplicationContext().getBean("diskService");
		disservice.back_up(up_path, back_path, path,request);
		//将新的路径保存到数据库
		photoService photoservice=(photoService) ApplicationContextUnitl.getApplicationContext().getBean("photoservice");
		JSONObject obj = new JSONObject();
		if(photoservice.save_photo(User_id, "images/"+path)==1){
			//将信息写入up_message.log
			LogMessageUnitl.setUpMessage(User_id, request.getSession().getServletContext().getRealPath("/")+"up_message.log", up_path);
			obj.put("error", 0);
		}else{
			String message="数据库删除头像出错,出错用户ID为:"+User_id+"(请手动删除path)";
			LogMessageUnitl.setErrMessage(request.getSession().getServletContext().getRealPath("/")+"error_message.log", message);
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
