/**
 * 这个用户注册
 */
package com.wyc.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.Services.RegisterService;
import com.wyc.model.User;

import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class test2 extends HttpServlet {

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
		//1.接收用户的注册信息
		StringBuilder sb=new StringBuilder();
		BufferedReader reader=request.getReader();
		try {
			String line=null;
			while((line=reader.readLine())!=null){
				sb.append(line).append('\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			reader.close();
		}
//		System.out.println(sb.toString());
		JSONObject obj=JSONObject.fromObject(sb.toString());
		System.out.println(obj.getString("username"));
		System.out.println(obj.getString("password"));
		//2.封装用户的注册信息
		User user=new User();
		user.setUser_name(obj.getString("username"));
		user.setPassword(obj.getString("password"));
		//3.交给后台的service处理
		JSONObject robj=new JSONObject();
		if(new RegisterService().register_user(user)){
			robj.put("type", "success");
		}else{
			robj.put("type", "error");
		}
		//4.返回是否注册成功的信息
		PrintWriter pw=response.getWriter();
		pw.println(robj.toString());
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
