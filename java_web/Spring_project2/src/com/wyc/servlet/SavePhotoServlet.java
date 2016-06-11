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
		//��ͷ����б���(ÿ���û�����һ���Լ����ļ���)
		//1.����û��ļ���·��
		String back_path=request.getSession().getServletContext().getRealPath("/")+"images/"+User_name;
		//2.�����Ƭ·��
		String up_path=request.getSession().getServletContext().getRealPath("/")+"images/"+path;
		//2.�����ļ�
		diskService disservice=(diskService) ApplicationContextUnitl.getApplicationContext().getBean("diskService");
		disservice.back_up(up_path, back_path, path,request);
		//���µ�·�����浽���ݿ�
		photoService photoservice=(photoService) ApplicationContextUnitl.getApplicationContext().getBean("photoservice");
		JSONObject obj = new JSONObject();
		if(photoservice.save_photo(User_id, "images/"+path)==1){
			//����Ϣд��up_message.log
			LogMessageUnitl.setUpMessage(User_id, request.getSession().getServletContext().getRealPath("/")+"up_message.log", up_path);
			obj.put("error", 0);
		}else{
			String message="���ݿ�ɾ��ͷ�����,�����û�IDΪ:"+User_id+"(���ֶ�ɾ��path)";
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
