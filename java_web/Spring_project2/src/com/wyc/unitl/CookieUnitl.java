/**
 * cookie工具类，用于记录用户信息,目前还没有用到
 */
package com.wyc.unitl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.wyc.model.User;

public class CookieUnitl {

	static public void setCookie(HttpServletResponse response,User user){
		//获取浏览器访问访问服务器时传递过来的cookie数组
		//Cookie[] cookies = request.getCookies();
		//将用户名和密码写入cookie
		Cookie cookie1=new Cookie("name", user.getUser_name());
		//设置cookie的时间
		cookie1.setMaxAge(24*60*60);
		Cookie cookie2=new Cookie("password",user.getUser_password());
		cookie2.setMaxAge(24*60*60);
		//将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
		response.addCookie(cookie1);
		response.addCookie(cookie2);
	}
}
