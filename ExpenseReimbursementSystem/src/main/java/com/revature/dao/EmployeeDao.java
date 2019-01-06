package com.revature.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Employee;
import com.revature.jdbc.ConnectionHelper;

public class EmployeeDao {
	
	public List<Employee> getEmployees(){
		List<Employee> employees = new ArrayList<>();
		Employee employee;
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM Employee;");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employee = new Employee(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
						);
				employee.setEmployeeId(rs.getInt(1));
				employees.add(employee);
			}
		} catch(SQLException e) {
			
		}
		
		return employees;
	}
	
	public Employee getEmployeeById(int employeeId) {
		Employee employee = null;
		
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("SELECT * FROM Employee WHERE EmployeeId = ?;");
			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				employee = new Employee(
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6)
						);
				employee.setEmployeeId(rs.getInt(1));
			}
		} catch(SQLException e) {
			
		}
		
		return employee;
	}
	
	public void updateEmployee(Employee employee) {
		try {
			PreparedStatement ps = ConnectionHelper.connection.prepareStatement("UPDATE Employee SET FirstName = ?, LastName = ?, EmailAddress = ?, Address = ? WHERE EmployeeId = ?;");
			ps.setString(1, employee.getFirstName());
			ps.setString(2, employee.getLastName());
			ps.setString(3, employee.getEmailAddress());
			ps.setString(4, employee.getAddress());
			ps.setInt(5, employee.getEmployeeId());
			ps.executeUpdate();
		} catch(SQLException e) {
			
		}
	}
	
}
