package com.springboot.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.api.bean.Profile;

@Repository
public class ProfileDAOImpl implements ProfileDAO {
	
	//If DB Connection is available	
	@Autowired
	private DBConnectDAO dbConnectDAOImpl;
	
	//In-App Implementation starts here
	private ArrayList<Profile> profileList = new ArrayList<Profile>();
	
	@Override
	public ArrayList<Profile> getAllProfiles() {
		//Create an initial list of Profiles
		if(profileList.isEmpty()) {
			Profile profile1 = new Profile(1, "Gourab", 30, "male");
			Profile profile2 = new Profile(2, "Jack", 18, "male");
			Profile profile3 = new Profile(3, "Natasha", 22, "female");
			Profile profile4 = new Profile(4, "Kyle", 35, "male");
			Profile profile5 = new Profile(5, "Sam", 41, "male");
			
			profileList.add(profile1);
			profileList.add(profile2);
			profileList.add(profile3);
			profileList.add(profile4);
			profileList.add(profile5);
		}		
		return profileList;
	}

	@Override
	public Profile getProfile(int id) {
		for(Profile profile : profileList) {
			if(profile.getProfile_id() == id){
				return profile;
			}
		}
		return null;
	}

	@Override
	public Profile updateProfile(int id, Profile updatedProfile) {
		for(Profile profile : profileList) {
			if(profile.getProfile_id() == id){
				profile.setName(updatedProfile.getName());
				profile.setAge(updatedProfile.getAge());
				profile.setGender(updatedProfile.getGender());
				
				return profile;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Profile> addProfile(Profile newProfile) {
		newProfile.setProfile_id(profileList.size()+1);
		profileList.add(newProfile);
		return profileList;
	}

	@Override
	public String deleteProfile(int id) {
		Iterator<Profile> it = profileList.iterator();
		
		while(it.hasNext()){
			Profile profile = it.next();
			if(profile.getProfile_id() == id){
				it.remove();
				return "Profile deleted.";
			}
		}
		
		return "Profile does not exist.";		
	}	
	//In-App Implementation ends here

	//DB Implementation starts here
	@Override
	public ArrayList<Profile> getAllDBProfiles() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		try{
			con = dbConnectDAOImpl.getConnection();

			ps = con.prepareStatement("select profile_id, name, age, gender from t_test_user");
			rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()){
				while (rs.next()) {
					Profile profile = new Profile();
					profile.setProfile_id(rs.getInt(1));
					profile.setName(rs.getString(2));
					profile.setAge(rs.getInt(3));
					profile.setGender(rs.getString(4));
					
					profiles.add(profile);
				}
			}			
		}catch(Exception e){
			System.out.println("Error in ProfileDAOImpl : getAllProfiles - " + e);
		}
		return profiles;
	}

	@Override
	public Profile getDBProfile(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Profile profile = null;
		try{
			con = dbConnectDAOImpl.getConnection();

			ps = con.prepareStatement("select profile_id, name, age, gender from t_test_user where profile_id = ?");			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				profile = new Profile();
				profile.setProfile_id(rs.getInt(1));
				profile.setName(rs.getString(2));
				profile.setAge(rs.getInt(3));
				profile.setGender(rs.getString(4));
			}			
		}catch(Exception e){
			System.out.println("Error in ProfileDAOImpl : getProfile - " + e);
		}
		return profile;
	}

	@Override
	public Profile updateDBProfile(int id, Profile profile) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Profile updateProfile = null;
		try{
			con = dbConnectDAOImpl.getConnection();
			
			ps = con.prepareStatement("update t_test_user set name = ?, age = ?, gender = ? where profile_id = ?");
			ps.setString(1, profile.getName());
			ps.setInt(2, profile.getAge());
			ps.setString(3, profile.getGender());
			ps.setInt(4, id);
			
			int res = ps.executeUpdate();
			
			if(res>0){
				ps = con.prepareStatement("select profile_id, name, age, gender from t_test_user where profile_id = ?");			
				ps.setInt(1, id);
				rs = ps.executeQuery();
				
				if(rs.next()){
					updateProfile = new Profile();
					updateProfile.setProfile_id(rs.getInt(1));
					updateProfile.setName(rs.getString(2));
					updateProfile.setAge(rs.getInt(3));
					updateProfile.setGender(rs.getString(4));
				}
			}
		}catch(Exception e){
			System.out.println("Error in ProfileDAOImpl : updateProfile - " + e);
		}
		return updateProfile;
	}
	
	@Override
	public ArrayList<Profile> addDBProfile(Profile newProfile) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Profile> profiles = new ArrayList<Profile>();
		try{
			con = dbConnectDAOImpl.getConnection();

			//Insert new profile
			ps = con.prepareStatement("insert into t_test_user (name, age, gender, status, created_on) values (?, ?, ?, 1, now())");
			ps.setString(1, newProfile.getName());
			ps.setInt(2, newProfile.getAge());
			ps.setString(3, newProfile.getGender());
			
			int res = ps.executeUpdate();
			
			if(res>0) {
				System.out.println("Profile inserted");
			}
			
			ps = con.prepareStatement("select profile_id, name, age, gender from t_test_user");
			rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()){
				while (rs.next()) {
					Profile profile = new Profile();
					profile.setProfile_id(rs.getInt(1));
					profile.setName(rs.getString(2));
					profile.setAge(rs.getInt(3));
					profile.setGender(rs.getString(4));
					
					profiles.add(profile);
				}
			}			
		}catch(Exception e){
			System.out.println("Error in ProfileDAOImpl : addProfile - " + e);
		}
		return profiles;
	}

	@Override
	public String deleteDBProfile(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result = null;
		try{
			con = dbConnectDAOImpl.getConnection();

			ps = con.prepareStatement("delete from t_test_user where profile_id = ?");			
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			
			if(res>0){
				result = "Profile deleted";
			} else {
				result = "Profile not found.";
			}
		}catch(Exception e){
			System.out.println("Error in ProfileDAOImpl : getProfile - " + e);
		}
		return result;
	}
	//DB Implementation ends here
}
