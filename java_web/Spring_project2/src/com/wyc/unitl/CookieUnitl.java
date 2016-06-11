/**
 * cookie�����࣬���ڼ�¼�û���Ϣ,Ŀǰ��û���õ�
 */
package com.wyc.unitl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.wyc.model.User;

public class CookieUnitl {

	static public void setCookie(HttpServletResponse response,User user){
		//��ȡ��������ʷ��ʷ�����ʱ���ݹ�����cookie����
		//Cookie[] cookies = request.getCookies();
		//���û���������д��cookie
		Cookie cookie1=new Cookie("name", user.getUser_name());
		//����cookie��ʱ��
		cookie1.setMaxAge(24*60*60);
		Cookie cookie2=new Cookie("password",user.getUser_password());
		cookie2.setMaxAge(24*60*60);
		//��cookie������ӵ�response�����У����������������response�����е�����ʱ�ͻ��cookieҲ������ͻ��������
		response.addCookie(cookie1);
		response.addCookie(cookie2);
	}
}
