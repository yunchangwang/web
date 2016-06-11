package com.wyc.mapping;

import org.apache.ibatis.annotations.Param;

public interface PhotoMapping {
	/**
	 * 保存头像
	 * @param User_id 病患ID
	 * @param path 头像的路径
	 * @return int
	 */
	public int save_photo(@Param("User_id")int User_id,@Param("path")String path);
	/**
	 * 删除头像
	 * @param User_id 病患ID
	 * @return int
	 */
	public int delete_photo(@Param("User_id")int User_id);
	/**
	 * 根据病患ID得到头像路径
	 * @param User_id 病患ID
	 * @return String
	 */
	public String get_path(@Param("User_id")int User_id);
}
