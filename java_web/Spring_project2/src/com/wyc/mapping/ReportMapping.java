package com.wyc.mapping;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wyc.model.report;

public interface ReportMapping {
	/**
	 * ��ҳ��ѯ���Խ��
	 * @param User_id ����ID
	 * @param num ��ѯ����ʼ��
	 * @param pageSize ��ѯ�ķ�Χ
	 * @return ArrayList<report> 
	 */
	public ArrayList<report> select_userAccuracy_all(@Param("User_id")int User_id,@Param("num")int num,@Param("pageSize")int pageSize);
	/**
	 * �õ���������������
	 * @param User_id ����ID
	 * @return int
	 */
	public int get_count(@Param("User_id")int User_id);
}
