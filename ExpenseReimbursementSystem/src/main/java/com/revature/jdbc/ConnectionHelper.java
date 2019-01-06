package com.revature.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
	public static Connection connection;
	private String username;
	private String password;
	private String url;
	
	public void establishConnection() {
		getConnectionInfo();
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
			
		}
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			
		}
	}
	
	public void getConnectionInfo(){
		File connectionFile = new File("JDBCConnectionInfo.txt");
		
		try
		(
			FileInputStream fis = new FileInputStream(connectionFile);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
		){
			String line = "";
			int count = 0;
			while((line = br.readLine()) != null) {
				switch(count) {
				case 0 :
					username = line;
					break;
				case 1 :
					password = line;
					break;
				case 2 :
					url = line;
					break;
				}
				count++;
			}
		} catch(FileNotFoundException e) {
			
		} catch(IOException e) {
			
		}
	}
}
