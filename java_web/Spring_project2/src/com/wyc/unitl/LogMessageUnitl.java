/**
 * ��־����,���鲻Ҫʹ���ˣ�����ʹ��logback
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
		//���û���Ϣд��log�ļ�
		String time=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String message="����Ա"+Admin_name+" ��"+time+"��¼\r\n";
		write(path,message);
	}
	public static void setUpMessage(String User_id,String path,String upPath){
		String message="IDΪ"+User_id+"�Ĳ���ͷ�����,·��Ϊ:"+upPath+"\r\n";
		write(path,message);
	}
	public static void setErrMessage(String path,String message){
		String err="������Ϣ:"+message+"\r\n";
		write(path,err);
	}
	//�ж��ļ��Ƿ����,�������򴴽�
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
	//����Ϣд��ָ�����ļ�
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
