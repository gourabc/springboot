package com.springboot.api.bean;

public class Profile {
	int profile_id;	
	String name;
	int age;
	String gender;
	
	//Default Constructor
	public Profile(){}
	
	//In-App Implementation
	public Profile(int profile_id, String name, int age, String gender){
		this.profile_id = profile_id;
		this.name = name;
		this.age = age;		
		this.gender = gender;
	}
	
	public int getProfile_id() {
		return profile_id;
	}
	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
