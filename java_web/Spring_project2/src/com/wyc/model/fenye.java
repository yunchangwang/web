package com.wyc.model;

import java.util.ArrayList;

public class fenye {
	private int pageCount;
	private ArrayList<User> List;
	private ArrayList<report> List2;
	public ArrayList<report> getList2() {
		return List2;
	}
	public void setList2(ArrayList<report> list2) {
		List2 = list2;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public ArrayList<User> getList() {
		return List;
	}
	public void setList(ArrayList<User> list) {
		List = list;
	}
	
	
}
