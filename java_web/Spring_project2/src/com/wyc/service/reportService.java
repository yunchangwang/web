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
	 * ��ѯָ���û��Ĳ�������
	 * @param User_id ����ID
	 * @param pageNow ��ǰҳ
	 * @param pageSize ÿһҳ�Ĳ�������
	 * @return fenye
	 */
	public fenye select_userAccuracy_all(int User_id,int pageNow,int pageSize){
		int pageCount=0;
		this.sqlSession=this.sqlSessionFactory.openSession();
		ReportMapping reportMapper=this.sqlSession.getMapper(ReportMapping.class);
		int Count=reportMapper.get_count(User_id);
		ArrayList<report> List=reportMapper.select_userAccuracy_all(User_id,(pageNow-1)*pageSize,pageSize);
		//�Խ�����з�ҳ��װ
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
