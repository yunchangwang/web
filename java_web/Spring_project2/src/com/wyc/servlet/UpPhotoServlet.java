package com.wyc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@SuppressWarnings("serial")
public class UpPhotoServlet extends HttpServlet {

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
	
	private String uploadPath = null; // 上传文件的目录
    private String tempPath =null; // 临时文件目录
    File tempPathFile;
 
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
    	//解决中文乱码问题
    	response.setCharacterEncoding("utf-8");
    	uploadPath = request.getSession().getServletContext().getRealPath("/")+"images";
    	System.out.println(uploadPath);
    	tempPath = request.getSession().getServletContext().getRealPath("/")+"images\\buffer";
    	//创建文件夹
    	mkdir(uploadPath,tempPath);
    	try {
           // Create a factory for disk-based file items
           DiskFileItemFactory factory = new DiskFileItemFactory();
 
           // Set factory constraints
           factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
           factory.setRepository(tempPathFile);// 设置缓冲区目录
 
           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);
 
           // Set overall request size constraint
           upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
 
           List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
           //得到文件的个数
           int length=items.size();
           JSONObject obj = new JSONObject();  
           //将文件的数量返回
           obj.put("length", length);
           for(int i=0;i<items.size();i++){
        	   FileItem fi=items.get(i);
        	   String fileName=fi.getName();
        	   if(fileName!=null){
        		   File fullFile = new File(fi.getName());
                   String new_fileName=getNewPath(fullFile.getName());
                   File savedFile = new File(uploadPath, new_fileName);
                   fi.write(savedFile);
                   System.out.println("upload succeed");
                   obj.put("error"+(i+1), 0);  
                   obj.put("path"+(i+1), new_fileName);  
        	   }
           }
           PrintWriter out = response.getWriter(); 
           out.println(obj.toString());
       } catch (Exception e) {
           // 可以跳转出错页面
           e.printStackTrace();
       }
    }
 
    private void mkdir(String uploadPath,String tempPath) throws ServletException {
       File uploadFile = new File(uploadPath);
       if (!uploadFile.exists()) {
           uploadFile.mkdirs();
       }
       File tempPathFile = new File(tempPath);
        if (!tempPathFile.exists()) {
           tempPathFile.mkdirs();
       }
    }
    
    /**
     * 随机头像名
     * @param fileName 上传的头像名
     * @return 新产生的头像名
     */
    private String getNewPath(String fileName){
    	String str=fileName.substring(fileName.lastIndexOf("."));
    	String new_fileName=(new StringBuilder().append((new Date()).getTime()).append((new Random()).nextInt(1000)).append(str)).toString();
    	return new_fileName;
    }

}
