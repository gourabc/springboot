package com.springboot.api.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.bean.Profile;
import com.springboot.api.dao.ProfileDAO;

@Service
public class ProfileService {

	@Autowired
	private ProfileDAO profileDAOImpl;
	
	public ArrayList<Profile> getAllProfiles(){
		return profileDAOImpl.getAllProfiles();		
	}
	
	public Profile getProfile(int id){
		return profileDAOImpl.getProfile(id);
	}
	
	public Profile updateProfile(int id, Profile profile){
		return profileDAOImpl.updateProfile(id, profile);
	}
	
	public List<Profile> addProfile(Profile newProfile){
		return profileDAOImpl.addProfile(newProfile);
	}
	
	public String deleteProfile(int id){
		return profileDAOImpl.deleteProfile(id);
	}
}
