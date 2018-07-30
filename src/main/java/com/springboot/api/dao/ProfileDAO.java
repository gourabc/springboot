package com.springboot.api.dao;

import java.util.List;
import java.util.ArrayList;

import com.springboot.api.bean.Profile;

public interface ProfileDAO {

	ArrayList<Profile> getAllProfiles();
	Profile getProfile(int id);
	Profile updateProfile(int id, Profile profile);
	List<Profile> addProfile(Profile newProfile);
	String deleteProfile(int id);	
	
}
