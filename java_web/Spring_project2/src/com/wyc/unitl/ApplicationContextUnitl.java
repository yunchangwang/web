package com.wyc.unitl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextUnitl {
	private static ApplicationContext factory=null;
	//构造私有函数,这样整个程序中就只有一个实例
	private ApplicationContextUnitl(){}
	//只执行一次
	static{
		//加载spring配置文件，并生成工厂
		factory=new ClassPathXmlApplicationContext(new String[]{"beans.xml","modelBeans.xml","serviceBeans.xml"});
	}
	
	static public ApplicationContext getApplicationContext(){
		return factory;
	}
}
