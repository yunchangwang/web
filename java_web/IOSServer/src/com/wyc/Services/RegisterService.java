/**
 * �û�ע��Service
 * @author WYC
 */
package com.wyc.Services;

import com.wyc.Database.SqlHelper;
import com.wyc.model.User;

public class RegisterService {
	private SqlHelper sqlHelper=null;
	public RegisterService(){
		this.sqlHelper=new SqlHelper();
	}
	/**
	 * �ύ��̨ע���û�
	 * @param user ��װ��User
	 * @return boolean
	 */
	public boolean register_user(User user){
		//boolean b=true;
		String sql="insert into user (User_name,User_password) values ('"+user.getUser_name()+"','"+user.getPassword()+"')";
		return this.sqlHelper.register_user(sql);
	}
}
