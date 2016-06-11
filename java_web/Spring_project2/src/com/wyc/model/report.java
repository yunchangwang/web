package com.wyc.model;

public class report {
	private long Id;
	private int Photo_nums;
	private float Accuracy;
	private java.util.Date Time;
	private float Action_time;
	private int User_id;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public int getPhoto_nums() {
		return Photo_nums;
	}
	public void setPhoto_nums(int photo_nums) {
		Photo_nums = photo_nums;
	}
	public float getAccuracy() {
		return Accuracy;
	}
	public void setAccuracy(float accuracy) {
		Accuracy = accuracy;
	}
	public java.util.Date getTime() {
		return Time;
	}
	public void setTime(java.util.Date time) {
		Time = time;
	}
	public float getAction_time() {
		return Action_time;
	}
	public void setAction_time(float action_time) {
		Action_time = action_time;
	}
	public int getUser_id() {
		return User_id;
	}
	public void setUser_id(int user_id) {
		User_id = user_id;
	}
	
}
