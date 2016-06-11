package com.wyc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.wyc.unitl.LogMessageUnitl;

public class diskService {

	/**
	 * �����ϴ���ͷ��
	 * @param up_path �ϴ�·��
	 * @param back_path ����·��
	 */
	public void back_up(String up_path,String back_path,String path,HttpServletRequest request) {
		//�жϸ��ļ����Ƿ����,��������ڴ���֮
		File file=new File(back_path);
		FileInputStream fis=null;
		FileOutputStream fos=null;
		if(!file.exists()){
			file.mkdir();
		}
		try {
			//���û�����
			byte[] buff=new byte[1024];
			fis=new FileInputStream(up_path);
			fos=new FileOutputStream(back_path+"/"+path);
			int count=0;
			while((count=fis.read(buff,0,buff.length))>0){
				fos.write(buff, 0, count);
				fos.flush();
			}
		} catch (IOException e) {
			String message="����ͷ�����,����·��Ϊ:"+back_path+"/"+path;
			LogMessageUnitl.setErrMessage(request.getSession().getServletContext().getRealPath("/")+"error_message.log", message);
			//System.out.println("������д��log�ļ�");
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
	 * ͬʱɾ��ͷ��ͱ���ͷ��
	 * @param del_path �ϴ�ͷ��·��
	 * @param back_path ����ͷ��·��
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
