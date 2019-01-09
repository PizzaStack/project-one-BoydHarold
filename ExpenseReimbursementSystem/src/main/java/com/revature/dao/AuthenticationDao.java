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
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT eu.UserId, eu.EmployeeId, eu.Username, eu.Password, eu.Status, CONCAT(e.FirstName,' ',e.LastName)\r\n" + 
					"FROM EmployeeUsers eu\r\n" + 
					"JOIN Employee e\r\n" + 
					"ON eu.EmployeeId = e.EmployeeId;");
			ResultSet rs = ps.executeQuery();
			Authentication authentication;
			while(rs.next()) {
				authentication = new Authentication(
						rs.getString(3),
						rs.getString(4)
						);
				authentication.setReferenceId(rs.getInt(2));
				authentication.setUserId(rs.getInt(1));
				authentication.setStatus(rs.getInt(5));
				authentication.setFullName(rs.getString(6));
				accounts.add(authentication);
			}
		} catch(SQLException e) {
			
		}
		
		return accounts;
	}

	public List<Authentication> getManagerAccounts(){
		List<Authentication> accounts = new ArrayList<>();
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT mu.UserId, mu.ManagerId, mu.Username, mu.Password, mu.Status, CONCAT(m.FirstName,' ',m.LastName)\r\n" + 
					"FROM ManagerUsers mu\r\n" + 
					"JOIN Manager m\r\n" + 
					"ON mu.ManagerId = m.ManagerId;");
			ResultSet rs = ps.executeQuery();
			Authentication authentication;
			while(rs.next()) {
				authentication = new Authentication(
						rs.getString(3),
						rs.getString(4)
						);
				authentication.setReferenceId(rs.getInt(2));
				authentication.setUserId(rs.getInt(1));
				authentication.setStatus(rs.getInt(5));
				authentication.setFullName(rs.getString(6));
				accounts.add(authentication);
			}
		} catch(SQLException e) {
			
		}
		
		return accounts;
	}
}
