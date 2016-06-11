/**
 * 测试数据的存储
 */
package com.wyc.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyc.Services.RecoderService;
import com.wyc.model.Rcoder;
import com.wyc.model.RecoderList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SuppressWarnings("serial")
public class test3 extends HttpServlet {

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
		//接收json数组
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
		JSONArray array=JSONArray.fromObject(sb.toString());
//		System.out.println(array.toString())
//		for(int i=1;i<array.size();i++){
//			JSONObject obj=array.getJSONObject(i);
//			System.out.println(obj.get("accuracy"));
//		}
		//封装得到的值
		RecoderList recoderList=new RecoderList();
		recoderList.setUser_id(Integer.parseInt(array.getJSONObject(0).getString("user_id")));
		ArrayList<Rcoder> recoder_list=new ArrayList<Rcoder>();
		for(int i=1;i<array.size();i++){
			Rcoder recoder=new Rcoder();
			JSONObject obj=array.getJSONObject(i);
			recoder.setPhoto_num(Integer.parseInt(obj.getString("photo_num")));
			recoder.setAccuracy(Double.parseDouble(obj.getString("accuracy")));
			recoder.setRecode_time(obj.getString("recode_time"));
			recoder.setAction_time(Double.parseDouble(obj.getString("action_time")));
			recoder_list.add(recoder);
		}
		recoderList.setRecoder_list(recoder_list);
		//交给后台取处理
		JSONObject robj=new JSONObject();
		if(new RecoderService().recoder(recoderList)){
			robj.put("type", "success");
		}else{
			robj.put("type", "error");
		}
		//返回处理结果
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
