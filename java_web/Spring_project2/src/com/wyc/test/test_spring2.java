/**
 * ≤‚ ‘¿‡
 * @author WYC
 */
package com.wyc.test;

//import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.wyc.mapping.UserMapping;
//import com.wyc.model.User;

public class test_spring2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		SqlSession sqlSession=null;
//		try {
//			ApplicationContext factory=new ClassPathXmlApplicationContext("beans.xml");
////			SqlSessionFactory sqlSessionFactory=(SqlSessionFactory) factory.getBean("sqlSessionFactory");
////			sqlSession=sqlSessionFactory.openSession();
////			UserMapping userMapper = sqlSession.getMapper(UserMapping.class);
////			ArrayList<User> list=userMapper.fenye();
//			SqlSessionFactory sqlSessionFactory=(SqlSessionFactory) factory.getBean("sqlSessionFactory2");
//			sqlSession=sqlSessionFactory.openSession();
//			EmpMapping empMapper=sqlSession.getMapper(EmpMapping.class);
//			ArrayList<Emp> list=empMapper.fenye();
//			for(int i=0;i<list.size();i++){
//				System.out.println(list.get(i).getEmail());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			sqlSession.close();
//		}
		String str="aufaiosfajs.txt";
		System.out.println((new Date()).getTime());
		System.out.println((new Random()).nextInt(1000));
		str=str.substring(str.lastIndexOf("."));
		System.out.println(str);
		String str2=(new StringBuilder().append((new Date()).getTime()).append((new Random()).nextInt(1000)).append(str)).toString();
		System.out.println(str2);
	}

}
