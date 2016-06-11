package com.wyc.mapping;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.wyc.model.Admin;
import com.wyc.model.User;

public interface AdminMapping {
	/**
	 * ��֤����Ա
	 * @param admin Admin
	 * @return Admin
	 */
	public Admin check_admin(Admin admin);
	/**
	 * �õ��û����е���������
	 * @return int
	 */
	public int get_count();
	/**
	 * ��ҳ��ѯ����
	 * @param num ��ѯ����ʼ��
	 * @param pageSize ��ѯ�ķ�Χ
	 * @return ArrayList<User>
	 */
	public ArrayList<User> select_userName_all(@Param("num")int num,@Param("pageSize")int pageSize);
	/**
	 * ��ѯ�������û�
	 * @param User_id �û�ID
	 * @param User_name �û���
	 * @return User
	 */
	public User select_user_one(@Param("User_id")int User_id,@Param("User_name")String User_name);
}
