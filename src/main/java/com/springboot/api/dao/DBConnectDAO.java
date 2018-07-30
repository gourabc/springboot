package com.springboot.api.dao;

import java.sql.Connection;

public interface DBConnectDAO {
	
	Connection getConnection();

}
