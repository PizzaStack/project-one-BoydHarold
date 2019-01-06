package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Manager;
import com.revature.jdbc.ConnectionHelper;

public class ManagerDao {
	public List<Manager> getManagers(){
		List<Manager> managers = new ArrayList<>();
		Manager manager;
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM Manager;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				manager = new Manager(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
						);
				manager.setManagerId(rs.getInt(1));
				managers.add(manager);
			}
		} catch(SQLException e) {
			
		}
		
		return managers;
	}
	
	public Manager getManagerById(int managerId) {
		Manager manager = null;
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM Manager WHERE ManagerId = ?;");
			ps.setInt(1, managerId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				manager = new Manager(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
						);
				manager.setManagerId(rs.getInt(1));
			}
		} catch(SQLException e) {
			
		}
		
		return manager;
	}
}
