package com.springboot.api.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.bean.Profile;
import com.springboot.api.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ArrayList<Profile> getAllProfiles(){
		ArrayList<Profile> profiles = profileService.getAllProfiles();
		return profiles;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Profile getProfileById(@PathVariable String id){
		Profile profile = profileService.getProfile(Integer.parseInt(id));
		return profile;
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Profile updateProfile(@PathVariable String id, @RequestParam Map<String, String> params){
		String name = params.get("name");
		String age = params.get("age");
		String gender = params.get("gender");
		
		Profile profile = new Profile();
		profile.setName(name);
		profile.setAge(Integer.parseInt(age));
		profile.setGender(gender);
		
		profile = profileService.updateProfile(Integer.parseInt(id), profile);
		return profile;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public List<Profile> addProfile(Profile newProfile){		
		return profileService.addProfile(newProfile);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String deleteProfile(@PathVariable String id){		
		String result = profileService.deleteProfile(Integer.parseInt(id));
		return result;
	}
}
