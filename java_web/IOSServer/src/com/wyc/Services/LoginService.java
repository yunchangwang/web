/**
 * 用户验证Service
 * @author WYC
 */
package com.wyc.Services;

import com.wyc.Database.SqlHelper;
import com.wyc.model.User;

public class LoginService {
	private SqlHelper sqlHelper=null;
	
	public LoginService(){
		this.sqlHelper=new SqlHelper();
	}
	/**
	 * 验证用户
	 * @param user 封装的User
	 * @return String
	 */
	public String check_user(User user){
		//用户默认存在
		String check_type=null;
		//sql语句
		String sql="select User_id,User_password from user where User_name=?";
		//绑定的参数
		String[] param={user.getUser_name()};
		User res=this.sqlHelper.check_user(sql, param);
		if(res==null){
			//用户不存在
			check_type="nouser_error";
		}else{
			//如果用户存在
			if(user.getPassword().equals(res.getPassword())){
				//如果密码正确，将用户的id返回给客户端
				check_type=Integer.toString(res.getUser_id());
			}else{
				//如果密码错误
				check_type="password_error";
			}
		}
		return check_type;
	}
}
