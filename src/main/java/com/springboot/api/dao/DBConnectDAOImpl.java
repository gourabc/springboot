package com.springboot.api.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class DBConnectDAOImpl implements DBConnectDAO {
	
	@Value("${mysql.database}")
	private String database;
	
	@Value("${mysql.host}")
	private String host;
	
	@Value("${mysql.username}")
	private String username;
	
	@Value("${mysql.password}")
	private String password;

	public Connection getConnection() {	
		Connection connection = null;			
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(host + database, username, password);

			if (connection != null) {
				return connection;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Error connecting to : "+ host + database);			
		}
		return connection;
	}	
}
