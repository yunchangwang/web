package com.wyc.mapping;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wyc.model.report;

public interface ReportMapping {
	/**
	 * 分页查询测试结果
	 * @param User_id 病患ID
	 * @param num 查询的起始行
	 * @param pageSize 查询的范围
	 * @return ArrayList<report> 
	 */
	public ArrayList<report> select_userAccuracy_all(@Param("User_id")int User_id,@Param("num")int num,@Param("pageSize")int pageSize);
	/**
	 * 得到测试数据总数量
	 * @param User_id 病患ID
	 * @return int
	 */
	public int get_count(@Param("User_id")int User_id);
}
