package com.wyc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.wyc.unitl.LogMessageUnitl;

public class diskService {

	/**
	 * 备份上传的头像
	 * @param up_path 上传路径
	 * @param back_path 备份路径
	 */
	public void back_up(String up_path,String back_path,String path,HttpServletRequest request) {
		//判断该文件夹是否存在,如果不存在创建之
		File file=new File(back_path);
		FileInputStream fis=null;
		FileOutputStream fos=null;
		if(!file.exists()){
			file.mkdir();
		}
		try {
			//设置缓冲区
			byte[] buff=new byte[1024];
			fis=new FileInputStream(up_path);
			fos=new FileOutputStream(back_path+"/"+path);
			int count=0;
			while((count=fis.read(buff,0,buff.length))>0){
				fos.write(buff, 0, count);
				fos.flush();
			}
		} catch (IOException e) {
			String message="备份头像出错,出错路径为:"+back_path+"/"+path;
			LogMessageUnitl.setErrMessage(request.getSession().getServletContext().getRealPath("/")+"error_message.log", message);
			//System.out.println("将错误写入log文件");
		}finally{
			try {
				if(fis!=null){
					fis.close();
					fis=null;
				}
				if(fos!=null){
					fos.close();
					fos=null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 同时删除头像和备份头像
	 * @param del_path 上传头像路径
	 * @param back_path 备份头像路径
	 */
	public void del_head(String del_path,String back_path){
		File del_file=new File(del_path);
		File back_file=new File(back_path);
		if(del_file.exists()){
			del_file.delete();
		}
		if(back_file.exists()){
			back_file.delete();
		}
	}
}
