package com.wyc.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wyc.mapping.PhotoMapping;

public class photoService {
	private SqlSessionFactory sqlSessionFactory=null;
	private SqlSession sqlSession=null;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	/**
	 * �����ϴ�ͷ��
	 * @param User_id �û�ID
	 * @param path �ϴ�·��
	 * @return �������ݿ������
	 */
	public int save_photo(String User_id,String path){
		this.sqlSession=this.sqlSessionFactory.openSession();
		PhotoMapping PhotoMapper=this.sqlSession.getMapper(PhotoMapping.class);
		return PhotoMapper.save_photo(Integer.parseInt(User_id), path);
	}
	/**
	 * ɾ���ϴ�ͷ��
	 * @param User_id �û�ID
	 * @return ɾ�����ݿ������
	 */
	public int delete_photo(String User_id){
		this.sqlSession=this.sqlSessionFactory.openSession();
		PhotoMapping PhotoMapper=this.sqlSession.getMapper(PhotoMapping.class);
		return PhotoMapper.delete_photo(Integer.parseInt(User_id));
	}
	/**
	 * �õ�ͷ���·��
	 * @param User_id �û�ID
	 * @return ͷ������·��
	 */
	public String get_path(String User_id){
		this.sqlSession=this.sqlSessionFactory.openSession();
		PhotoMapping PhotoMapper=this.sqlSession.getMapper(PhotoMapping.class);
		return PhotoMapper.get_path(Integer.parseInt(User_id));
	}
	
}
