package com.wyc.mapping;
import java.util.ArrayList;

import com.wyc.model.*; 
public interface UserMapping {
	public User checkUser(User user);
	public int Count();
	public int insertUser(User user);
	public ArrayList<User> fenye();
}
