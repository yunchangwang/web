package com.wyc.mapping;

import org.apache.ibatis.annotations.Param;

public interface PhotoMapping {
	/**
	 * ����ͷ��
	 * @param User_id ����ID
	 * @param path ͷ���·��
	 * @return int
	 */
	public int save_photo(@Param("User_id")int User_id,@Param("path")String path);
	/**
	 * ɾ��ͷ��
	 * @param User_id ����ID
	 * @return int
	 */
	public int delete_photo(@Param("User_id")int User_id);
	/**
	 * ���ݲ���ID�õ�ͷ��·��
	 * @param User_id ����ID
	 * @return String
	 */
	public String get_path(@Param("User_id")int User_id);
}
