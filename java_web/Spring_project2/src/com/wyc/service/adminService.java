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
	 * spring����ע��
	 * @param sqlSessionFactory ע�������
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * �ύAdminMapping��֤�û�
	 * @param admin Admin
	 * @return String
	 */
	public String check_Admin(Admin admin){
		//�û�Ĭ�ϴ���
		String flag="2";
		this.sqlSession=this.sqlSessionFactory.openSession();
		AdminMapping adminMapper=this.sqlSession.getMapper(AdminMapping.class);
		Admin res=adminMapper.check_admin(admin);
		if(res==null){
			//�û�������
			flag="1";
		}else{
			//��Ϊ�������
			if(!Md5Unitl.MD5(admin.getAdmin_password()).equals(res.getAdmin_password())){
				//�������
				//System.out.println(Md5Unitl.MD5(admin.getAdmin_password()));
				//System.out.println(res.getAdmin_password());
				flag="3";
			}
		}
		return flag;
	}
	
	/**
	 * �ύAdminMapping��ҳ��ѯ�û�
	 * @param pageNow ��ǰҳ
	 * @param pageSize ÿһҳ���û�����
	 * @return fenye
	 */
	public fenye select_userName_all(int pageNow,int pageSize){
		int pageCount=0;
		this.sqlSession=this.sqlSessionFactory.openSession();
		AdminMapping adminMapper=this.sqlSession.getMapper(AdminMapping.class);
		//�õ�������������
		int Count=adminMapper.get_count();
		ArrayList<User> List=adminMapper.select_userName_all((pageNow-1)*pageSize, pageSize);
		fenye fenyelist=(fenye)ApplicationContextUnitl.getApplicationContext().getBean("fenye");
		//�����ҳʱҳ�������
		if(Count%pageSize==0){
			pageCount=Count/pageSize;
		}else{
			pageCount=Count/pageSize+1;
		}
		//��һ����װ
		fenyelist.setPageCount(pageCount);
		fenyelist.setList(List);
		return fenyelist;
	}
	
	/**
	 * �����û�����ID�����û�
	 * @param User_id �û�ID
	 * @param User_name �û���
	 * @return User
	 */
	public User select_user_one(int User_id,String User_name){
		this.sqlSession=this.sqlSessionFactory.openSession();
		AdminMapping adminMapper=this.sqlSession.getMapper(AdminMapping.class);
		User user=adminMapper.select_user_one(User_id, User_name);
		return user;
	}
}
