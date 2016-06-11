package com.wyc.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wyc.mapping.AdminMapping;
import com.wyc.model.Admin;
import com.wyc.model.User;
import com.wyc.model.fenye;
import com.wyc.unitl.ApplicationContextUnitl;
import com.wyc.unitl.Md5Unitl;
public class adminService {
//	private SqlHelper sqlHelper=null;
	private SqlSessionFactory sqlSessionFactory=null;
	private SqlSession sqlSession=null;
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	/**
	 * spring依赖注入
	 * @param sqlSessionFactory 注入的属性
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * 提交AdminMapping验证用户
	 * @param admin Admin
	 * @return String
	 */
	public String check_Admin(Admin admin){
		//用户默认存在
		String flag="2";
		this.sqlSession=this.sqlSessionFactory.openSession();
		AdminMapping adminMapper=this.sqlSession.getMapper(AdminMapping.class);
		Admin res=adminMapper.check_admin(admin);
		if(res==null){
			//用户不存在
			flag="1";
		}else{
			//因为密码加密
			if(!Md5Unitl.MD5(admin.getAdmin_password()).equals(res.getAdmin_password())){
				//密码错误
				//System.out.println(Md5Unitl.MD5(admin.getAdmin_password()));
				//System.out.println(res.getAdmin_password());
				flag="3";
			}
		}
		return flag;
	}
	
	/**
	 * 提交AdminMapping分页查询用户
	 * @param pageNow 当前页
	 * @param pageSize 每一页的用户数量
	 * @return fenye
	 */
	public fenye select_userName_all(int pageNow,int pageSize){
		int pageCount=0;
		this.sqlSession=this.sqlSessionFactory.openSession();
		AdminMapping adminMapper=this.sqlSession.getMapper(AdminMapping.class);
		//得到病患总数据量
		int Count=adminMapper.get_count();
		ArrayList<User> List=adminMapper.select_userName_all((pageNow-1)*pageSize, pageSize);
		fenye fenyelist=(fenye)ApplicationContextUnitl.getApplicationContext().getBean("fenye");
		//计算分页时页面的数量
		if(Count%pageSize==0){
			pageCount=Count/pageSize;
		}else{
			pageCount=Count/pageSize+1;
		}
		//进一步封装
		fenyelist.setPageCount(pageCount);
		fenyelist.setList(List);
		return fenyelist;
	}
	
	/**
	 * 根据用户名或ID查找用户
	 * @param User_id 用户ID
	 * @param User_name 用户名
	 * @return User
	 */
	public User select_user_one(int User_id,String User_name){
		this.sqlSession=this.sqlSessionFactory.openSession();
		AdminMapping adminMapper=this.sqlSession.getMapper(AdminMapping.class);
		User user=adminMapper.select_user_one(User_id, User_name);
		return user;
	}
}
