package com.wyc.service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.wyc.mapping.ReportMapping;
import com.wyc.model.fenye;
import com.wyc.model.report;
import com.wyc.unitl.ApplicationContextUnitl;

public class reportService {
	private SqlSessionFactory sqlSessionFactory=null;
	private SqlSession sqlSession=null;
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

//	private SqlHelper sqlHelper;
//	
//	public reportService(){
//		this.sqlHelper=new SqlHelper();
//	}
	
	/**
	 * 查询指定用户的测试数据
	 * @param User_id 病患ID
	 * @param pageNow 当前页
	 * @param pageSize 每一页的测试数量
	 * @return fenye
	 */
	public fenye select_userAccuracy_all(int User_id,int pageNow,int pageSize){
		int pageCount=0;
		this.sqlSession=this.sqlSessionFactory.openSession();
		ReportMapping reportMapper=this.sqlSession.getMapper(ReportMapping.class);
		int Count=reportMapper.get_count(User_id);
		ArrayList<report> List=reportMapper.select_userAccuracy_all(User_id,(pageNow-1)*pageSize,pageSize);
		//对结果进行分页封装
		fenye f=(fenye)ApplicationContextUnitl.getApplicationContext().getBean("fenye");
		if(Count%pageSize==0){
			pageCount=Count/pageSize;
		}else{
			pageCount=Count/pageSize+1;
		}
		f.setPageCount(pageCount);
		f.setList2(List);
		return f;
	}
}
