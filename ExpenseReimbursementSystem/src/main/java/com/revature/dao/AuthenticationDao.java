package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Authentication;
import com.revature.jdbc.ConnectionHelper;

public class AuthenticationDao {
	public List<Authentication> getEmployeeAccounts(){
		List<Authentication> accounts = new ArrayList<>();
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM EmployeeUsers;");
			ResultSet rs = ps.executeQuery();
			Authentication authentication;
			while(rs.next()) {
				authentication = new Authentication(
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4)
						);
				authentication.setUserId(rs.getInt(1));
				authentication.setStatus(rs.getInt(5));
				accounts.add(authentication);
			}
		} catch(SQLException e) {
			
		}
		
		return accounts;
	}

	public List<Authentication> getManagerAccounts(){
		List<Authentication> accounts = new ArrayList<>();
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM ManagerUsers;");
			ResultSet rs = ps.executeQuery();
			Authentication authentication;
			while(rs.next()) {
				authentication = new Authentication(
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4)
						);
				authentication.setUserId(rs.getInt(1));
				authentication.setStatus(rs.getInt(5));
				accounts.add(authentication);
			}
		} catch(SQLException e) {
			
		}
		
		return accounts;
	}
}
