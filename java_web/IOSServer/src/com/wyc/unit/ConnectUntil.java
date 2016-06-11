package com.wyc.unit;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectUntil {
	private static String Driver="com.mysql.jdbc.Driver";
	private static String Url="jdbc:mysql://localhost:3306/KB";
	private static String UserName="root";
	private static String password="";
	private static Connection ct;
	
	public static Connection getConnection(){
		try {
			//加载mysql驱动
			Class.forName(Driver);
			//得到mysql连接
			ct=DriverManager.getConnection(Url,UserName,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ct;
	}
}
