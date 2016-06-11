/**
 * �û���֤Service
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
	 * ��֤�û�
	 * @param user ��װ��User
	 * @return String
	 */
	public String check_user(User user){
		//�û�Ĭ�ϴ���
		String check_type=null;
		//sql���
		String sql="select User_id,User_password from user where User_name=?";
		//�󶨵Ĳ���
		String[] param={user.getUser_name()};
		User res=this.sqlHelper.check_user(sql, param);
		if(res==null){
			//�û�������
			check_type="nouser_error";
		}else{
			//����û�����
			if(user.getPassword().equals(res.getPassword())){
				//���������ȷ�����û���id���ظ��ͻ���
				check_type=Integer.toString(res.getUser_id());
			}else{
				//����������
				check_type="password_error";
			}
		}
		return check_type;
	}
}
