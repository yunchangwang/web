/**
 * Session���ߣ�������֤���ע����ͷ��Լ��û���Ϣ�ĻỰ
 */
package com.wyc.unitl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wyc.model.Admin;

public class SessionUnitl {
	//����֤����뵽Session��
	static public void setCode(HttpServletRequest request,String Code){
		HttpSession session=request.getSession();
		session.setAttribute("Code", Code);
	}
	//�õ��û�����֤��
	static public String getCode(HttpServletRequest request){
		return (String) request.getSession().getAttribute("Code");
	}
	//ע����֤��
	static public void deleteCode(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("Code");
	}
	
	//������Ա����Session
	static public void setAdmin(HttpServletRequest request,Admin admin){
		HttpSession session=request.getSession();
		session.setAttribute("Admin", admin);
		//����session����Чʱ��
		session.setMaxInactiveInterval(1800);
	}
	//�õ�Session�е��û�
	static public Admin getAdmin(HttpServletRequest request){
		return (Admin)request.getSession().getAttribute("Admin");
	}
	//ע���û�
	static public void deletAdmin(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("Admin");
	}
}
