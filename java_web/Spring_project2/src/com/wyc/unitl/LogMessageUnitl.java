/**
 * 日志工具,建议不要使用了，建议使用logback
 */
package com.wyc.unitl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

import com.wyc.model.Admin;

public class LogMessageUnitl {
	private static File file=null;
	private static BufferedWriter bw=null;
	private static FileOutputStream fos=null;
	private static OutputStreamWriter osw=null;
	
	static public void setLoginMessage(Admin admin,String path){
		String Admin_name=admin.getAdmin_name();
		//将用户信息写入log文件
		String time=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String message="管理员"+Admin_name+" 于"+time+"登录\r\n";
		write(path,message);
	}
	public static void setUpMessage(String User_id,String path,String upPath){
		String message="ID为"+User_id+"的病人头像更改,路径为:"+upPath+"\r\n";
		write(path,message);
	}
	public static void setErrMessage(String path,String message){
		String err="错误信息:"+message+"\r\n";
		write(path,err);
	}
	//判断文件是否存在,不存在则创建
	private static void create(String path){
		File file=new File(path);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	//将信息写入指定的文件
	private static void write(String path,String message){
		create(path);
		try {
			file=new File(path);
			fos = new FileOutputStream(file,true);
			osw=new OutputStreamWriter(fos);
			bw=new BufferedWriter(osw);
			bw.write(message);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.close();
					bw=null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
