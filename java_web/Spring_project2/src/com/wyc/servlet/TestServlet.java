/**
 * 开发结束之后要删除的Servlet
 */
package com.wyc.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class TestServlet extends HttpServlet {

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

		//将用户的信息在这里进行放入,在转到testres.jsp,在用标签取出
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//默认用户信息
		String User_name="wyc";
		int User_id=337;
		//将用户信息写入log文件
		String messagePath=request.getSession().getServletContext().getRealPath("/")+"message.log";
		File file=new File(messagePath);
		FileOutputStream fos=new FileOutputStream(file);
		OutputStreamWriter osw=new OutputStreamWriter(fos);
		BufferedWriter bw=new BufferedWriter(osw);
		String time=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String message="用户"+User_name+" 于"+time+"登录"+",用户ID为"+User_id+"\r\n";
		bw.write(message);
		bw.close();
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
