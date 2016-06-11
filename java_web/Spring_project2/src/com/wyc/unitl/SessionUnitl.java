/**
 * Session工具，负责验证码的注入和释放以及用户信息的会话
 */
package com.wyc.unitl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.wyc.model.Admin;

public class SessionUnitl {
	//将验证码加入到Session中
	static public void setCode(HttpServletRequest request,String Code){
		HttpSession session=request.getSession();
		session.setAttribute("Code", Code);
	}
	//得到用户的验证码
	static public String getCode(HttpServletRequest request){
		return (String) request.getSession().getAttribute("Code");
	}
	//注销验证码
	static public void deleteCode(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("Code");
	}
	
	//将管理员放入Session
	static public void setAdmin(HttpServletRequest request,Admin admin){
		HttpSession session=request.getSession();
		session.setAttribute("Admin", admin);
		//设置session的有效时间
		session.setMaxInactiveInterval(1800);
	}
	//得到Session中的用户
	static public Admin getAdmin(HttpServletRequest request){
		return (Admin)request.getSession().getAttribute("Admin");
	}
	//注销用户
	static public void deletAdmin(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.removeAttribute("Admin");
	}
}
