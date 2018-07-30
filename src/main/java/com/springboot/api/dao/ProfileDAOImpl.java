package com.springboot.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.api.bean.Profile;

@Repository
public class ProfileDAOImpl implements ProfileDAO {
	
	@Autowired
	private DBConnectDAO dbConnectDAOImpl;

	@Override
	public ArrayList<Profile> getAllProfiles() {
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
	public Profile getProfile(int id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Profile profile = new Profile();
		try{
			con = dbConnectDAOImpl.getConnection();

			ps = con.prepareStatement("select profile_id, name, age, gender from t_test_user where profile_id = ?");			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
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
	public Profile updateProfile(int id, Profile profile) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
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
					profile.setProfile_id(rs.getInt(1));
					profile.setName(rs.getString(2));
					profile.setAge(rs.getInt(3));
					profile.setGender(rs.getString(4));
				}
			}				
		}catch(Exception e){
			System.out.println("Error in ProfileDAOImpl : updateProfile - " + e);
		}
		return profile;
	}
	
	@Override
	public List<Profile> addProfile(Profile newProfile) {
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
	public String deleteProfile(int id) {
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
	
}
