/**
 * ʹ���˿��֮�������Ͳ���Ҫ�ˣ����ǲ��˽��ܵĺ��������߿��Բ鿴
 */
package com.wyc.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wyc.unitl.*;
import com.wyc.model.Admin;
import com.wyc.model.User;
import com.wyc.model.report;
import com.wyc.model.fenye;

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
	 * @return Admin
	 */
	public Admin check_Admin(String sql,String[] param){
		Admin admin=new Admin();
		try {
			this.ps=this.ct.prepareStatement(sql);
			for(int i=0;i<param.length;i++){
				this.ps.setString(i+1, param[0]);
			}
			this.rs=this.ps.executeQuery();
			if(this.rs.next()){
				//�û�����
				admin.setAdmin_name(this.rs.getString(1));
			}else{
				//�û�������
				admin=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			this.close();
		}
		return admin;
	}
	
	public fenye select_userName_all(String sql1,String sql2,int pageSize){
		fenye f=new fenye();
		try {
			this.ps=this.ct.prepareStatement(sql1);
			this.rs=this.ps.executeQuery();
			ArrayList<User> List=new ArrayList<User>();
			while(this.rs.next()){
				User user=new User();
				user.setUser_id(this.rs.getInt(1));
				user.setUser_name(this.rs.getString(2));
				List.add(user);
			}
//			this.ps=null;
//			this.rs=null;
			//����pageCount
			this.ps=this.ct.prepareStatement(sql2);
			this.rs=this.ps.executeQuery();
			int pageCount=0;
			if(this.rs.next()){
				int rowCount=this.rs.getInt(1);
				if(rowCount%pageSize==0){
					pageCount=rowCount/pageSize;
				}else{
					pageCount=rowCount/pageSize+1;
				}
			}
			//�߶ȷ�װ
			f.setPageCount(pageCount);
			f.setList(List);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		
		return f;
	}
	
//	public int getPageCount(String sql2,int pageSize){
//		int pageCount=0;
//		try {
//			//����pageCount
//			this.ps=this.ct.prepareStatement(sql2);
//			this.rs=this.ps.executeQuery();
//			if(this.rs.next()){
//				int rowCount=this.rs.getInt(1);
//				if(rowCount%pageSize==0){
//					pageCount=rowCount/pageSize;
//				}else{
//					pageCount=rowCount/pageSize+1;
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			this.close();
//		}
//		return pageCount;
//	}
	
	public ArrayList<report> select_userAccuracy_all(String sql){
		ArrayList<report> List=new ArrayList<report>();
		try {
			this.ps=this.ct.prepareStatement(sql);
			this.rs=this.ps.executeQuery();
			while(this.rs.next()){
				report r=new report();
				r.setId(this.rs.getLong(1));
				r.setPhoto_nums(this.rs.getInt(2));
				r.setAccuracy(this.rs.getFloat(3));
				r.setTime(this.rs.getTimestamp(4));
				r.setAction_time(this.rs.getFloat(5));
				r.setUser_id(this.rs.getInt(6));
				List.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.close();
		}
		return List;
	}
	
	//�ر�����
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
