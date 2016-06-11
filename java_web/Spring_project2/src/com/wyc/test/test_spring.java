/**
 * ������
 * @author WYC
 */
package com.wyc.test;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wyc.model.User;
import com.wyc.mapping.*;
import com.wyc.unitl.Md5Unitl;
public class test_spring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession sqlSession=null;
		try {
			ApplicationContext factory=new ClassPathXmlApplicationContext("beans.xml");
			//Reader reader=Resources.getResourceAsReader("beans.xml");
			SqlSessionFactory sqlSessionFactory=(SqlSessionFactory) factory.getBean("sqlSessionFactory");
			sqlSession = sqlSessionFactory.openSession();
			 UserMapping userMapper = sqlSession.getMapper(UserMapping.class);
			 User user=new User();
			 user.setUser_name("���]��");
			 user.setUser_password(Md5Unitl.MD5("wyc123"));
			 int id=userMapper.insertUser(user);
			 System.out.println("�û���ID:"+id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		
		//����һ��MD5����
//		System.out.println(Md5Unitl.MD5("123456789"));
//		System.out.println(Md5Unitl.MD5("123456789"));
//		System.out.println(Md5Unitl.MD5("123456789"));
//		System.out.println(Md5Unitl.MD5("123456789"));
	}

}
