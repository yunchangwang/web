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
	
	private String uploadPath = null; // �ϴ��ļ���Ŀ¼
    private String tempPath =null; // ��ʱ�ļ�Ŀ¼
    File tempPathFile;
 
    @SuppressWarnings("unchecked")
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
    	//���������������
    	response.setCharacterEncoding("utf-8");
    	uploadPath = request.getSession().getServletContext().getRealPath("/")+"images";
    	System.out.println(uploadPath);
    	tempPath = request.getSession().getServletContext().getRealPath("/")+"images\\buffer";
    	//�����ļ���
    	mkdir(uploadPath,tempPath);
    	try {
           // Create a factory for disk-based file items
           DiskFileItemFactory factory = new DiskFileItemFactory();
 
           // Set factory constraints
           factory.setSizeThreshold(4096); // ���û�������С��������4kb
           factory.setRepository(tempPathFile);// ���û�����Ŀ¼
 
           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);
 
           // Set overall request size constraint
           upload.setSizeMax(4194304); // ��������ļ��ߴ磬������4MB
 
           List<FileItem> items = upload.parseRequest(request);// �õ����е��ļ�
           //�õ��ļ��ĸ���
           int length=items.size();
           JSONObject obj = new JSONObject();  
           //���ļ�����������
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
           // ������ת����ҳ��
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
     * ���ͷ����
     * @param fileName �ϴ���ͷ����
     * @return �²�����ͷ����
     */
    private String getNewPath(String fileName){
    	String str=fileName.substring(fileName.lastIndexOf("."));
    	String new_fileName=(new StringBuilder().append((new Date()).getTime()).append((new Random()).nextInt(1000)).append(str)).toString();
    	return new_fileName;
    }

}
