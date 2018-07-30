package com.springboot.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO {

	@Autowired
	private DBConnectDAO dbConnectDAOImpl;

	@Override
	public boolean loginUser(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean isValid = false;
		try{
			con = dbConnectDAOImpl.getConnection();

			ps = con.prepareStatement("select * from t_test_user where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if (rs.next()) {
				isValid = true;
			}
		}catch(Exception e){
			System.out.println("Error in LoginDAOImpl : loginUser - " + e);
		}
		return isValid;
	}
}
