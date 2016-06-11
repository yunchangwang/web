/**
 * ���ݿ������
 * @author WYC
 */
package com.wyc.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wyc.model.User;
import com.wyc.unit.ConnectUntil;

public class SqlHelper {
	private Connection ct;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
	public SqlHelper(){
		this.ct=ConnectUntil.getConnection();
	}
	
	/**
	 * ��֤�û���¼
	 * @param sql sql���
	 * @param param sql�еĲ���
	 * @return User
	 */
	public User check_user(String sql,String[] param){
		User user=new User();
		try {
			this.ps=this.ct.prepareStatement(sql);
			for(int i=0;i<param.length;i++){
				this.ps.setString(i+1, param[0]);
			}
			this.rs=this.ps.executeQuery();
			if(this.rs.next()){
				//�û�����
				user.setUser_id(this.rs.getInt(1));
				user.setPassword(this.rs.getString(2));
			}else{
				//�û�������
				user=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			this.close();
		}
		return user;
	}
	/**
	 * ע���û�
	 * @param sql sql���
	 * @return boolean
	 */
	public boolean register_user(String sql){
		boolean b=false;
		try {
			this.ps=this.ct.prepareStatement(sql);
			if(this.ps.executeUpdate()==1){
				b=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	/**
	 * ��¼���Խ��
	 * @param sql sql���
	 * @param row_num ���Խ����С
	 * @return boolean
	 */
	public boolean recoder(String sql,int row_num){
		boolean b=false;
		try {
			this.ps=this.ct.prepareStatement(sql);
			if(this.ps.executeUpdate()==row_num){
				b=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
	}
	
	/**
	 * �ͷ����ݿ�������Դ
	 */
	public void close(){
		try {
			if(rs!=null){
				rs.close();
				rs=null;
			}
			if(ps!=null){
				ps.close();
				ps=null;
			}
			if(ct!=null){
				ct.close();
				ct=null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
