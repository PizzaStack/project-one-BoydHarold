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
		try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {

        }
		
		try {
			if(connection == null) {
				getConnectionInfo();
			connection = DriverManager.getConnection(url, username, password);
			}
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public void closeConnection() {
		try {
			if(connection != null) {
			connection.close();
			connection = null;
			}
		} catch (SQLException e) {
			
		}
	}
	
	private void getConnectionInfo(){
		
		File connectionFile = new File("C:\\Users\\boydt\\Desktop\\Project One\\project-one-BoydHarold\\ExpenseReimbursementSystem\\JDBCConnectionInfo.txt");
		
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
			System.out.println(e);
		} catch(IOException e) {
			System.out.println(e);
		}
		
	}
}
