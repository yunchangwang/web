package com.wyc.mapping;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wyc.model.Admin;
import com.wyc.model.User;

public interface AdminMapping {
	/**
	 * 验证管理员
	 * @param admin Admin
	 * @return Admin
	 */
	public Admin check_admin(Admin admin);
	/**
	 * 得到用户表中的数据总数
	 * @return int
	 */
	public int get_count();
	/**
	 * 分页查询病患
	 * @param num 查询的起始行
	 * @param pageSize 查询的范围
	 * @return ArrayList<User>
	 */
	public ArrayList<User> select_userName_all(@Param("num")int num,@Param("pageSize")int pageSize);
	/**
	 * 查询单独的用户
	 * @param User_id 用户ID
	 * @param User_name 用户名
	 * @return User
	 */
	public User select_user_one(@Param("User_id")int User_id,@Param("User_name")String User_name);
}
