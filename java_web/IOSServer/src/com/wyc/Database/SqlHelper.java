/**
 * 数据库操作类
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
	 * 验证用户登录
	 * @param sql sql语句
	 * @param param sql中的参数
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
				//用户存在
				user.setUser_id(this.rs.getInt(1));
				user.setPassword(this.rs.getString(2));
			}else{
				//用户不存在
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
	 * 注册用户
	 * @param sql sql语句
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
	 * 记录测试结果
	 * @param sql sql语句
	 * @param row_num 测试结果大小
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
	 * 释放数据库连接资源
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
