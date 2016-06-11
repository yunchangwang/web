package com.wyc.unitl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUnitl {
	private static ApplicationContext factory=null;
	//����˽�к���,�������������о�ֻ��һ��ʵ��
	private ApplicationContextUnitl(){}
	//ִֻ��һ��
	static{
		//����spring�����ļ��������ɹ���
		factory=new ClassPathXmlApplicationContext(new String[]{"beans.xml","modelBeans.xml","serviceBeans.xml"});
	}
	
	static public ApplicationContext getApplicationContext(){
		return factory;
	}
}
