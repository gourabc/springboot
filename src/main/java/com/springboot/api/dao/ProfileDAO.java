package com.springboot.api.dao;

import java.util.List;
import java.util.ArrayList;

import com.springboot.api.bean.Profile;

public interface ProfileDAO {

	//In-App implementation
	ArrayList<Profile> getAllProfiles();
	Profile getProfile(int id);
	Profile updateProfile(int id, Profile profile);
	ArrayList<Profile> addProfile(Profile newProfile);
	String deleteProfile(int id);
	
	//If DB Connection is available	
	ArrayList<Profile> getAllDBProfiles();
	Profile getDBProfile(int id);
	Profile updateDBProfile(int id, Profile profile);
	ArrayList<Profile> addDBProfile(Profile newProfile);
	String deleteDBProfile(int id);
	
	
}
