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
	 * 保存上传头像
	 * @param User_id 用户ID
	 * @param path 上传路径
	 * @return 插入数据库的行数
	 */
	public int save_photo(String User_id,String path){
		this.sqlSession=this.sqlSessionFactory.openSession();
		PhotoMapping PhotoMapper=this.sqlSession.getMapper(PhotoMapping.class);
		return PhotoMapper.save_photo(Integer.parseInt(User_id), path);
	}
	/**
	 * 删除上传头像
	 * @param User_id 用户ID
	 * @return 删除数据库的行数
	 */
	public int delete_photo(String User_id){
		this.sqlSession=this.sqlSessionFactory.openSession();
		PhotoMapping PhotoMapper=this.sqlSession.getMapper(PhotoMapping.class);
		return PhotoMapper.delete_photo(Integer.parseInt(User_id));
	}
	/**
	 * 得到头像的路径
	 * @param User_id 用户ID
	 * @return 头像的相对路径
	 */
	public String get_path(String User_id){
		this.sqlSession=this.sqlSessionFactory.openSession();
		PhotoMapping PhotoMapper=this.sqlSession.getMapper(PhotoMapping.class);
		return PhotoMapper.get_path(Integer.parseInt(User_id));
	}
	
}
