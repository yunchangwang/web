/**
 * ��¼��������Service
 * @author WYC
 */
package com.wyc.Services;

import java.util.ArrayList;

import com.wyc.Database.SqlHelper;
import com.wyc.model.Rcoder;
import com.wyc.model.RecoderList;

public class RecoderService {
	private SqlHelper sqlHelper=null;
	
	public RecoderService(){
		this.sqlHelper=new SqlHelper();
	}
	/**
	 * �ύ��̨sqlHelper��¼����
	 * @param recoderList ������������
	 * @return boolean
	 */
	public boolean recoder(RecoderList recoderList){
//		boolean b=false;
		String sql="insert into report (Photo_nums,Accuracy,Time,Action_time,User_id) values ";
		int user_id=recoderList.getUser_id();
		ArrayList<Rcoder> recoder_list=recoderList.getRecoder_list();
		//����ƴ��sql������mysql�������봦��
		for(int i=0;i<recoder_list.size();i++){
			Rcoder recoder=recoder_list.get(i);
			String param=null;
			if(i==recoder_list.size()-1){
				param="("+recoder.getPhoto_num()+","+recoder.getAccuracy()+",'"+recoder.getRecode_time()+"',"+recoder.getAction_time()+","+user_id+")";
			}else{
				param="("+recoder.getPhoto_num()+","+recoder.getAccuracy()+",'"+recoder.getRecode_time()+"',"+recoder.getAction_time()+","+user_id+"),";
			}
			//���յ�sql���
			sql=sql+param;
		}
		//System.out.println(sql);
		return this.sqlHelper.recoder(sql, recoder_list.size());
	}
}
