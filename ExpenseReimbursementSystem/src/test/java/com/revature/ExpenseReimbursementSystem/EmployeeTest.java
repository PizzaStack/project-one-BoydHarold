package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.jdbc.ConnectionHelper;
import com.revature.service.EmployeeService;

public class EmployeeTest {
	
	@Test
	public void anEmployeeCanUpdateTheirInformationInTheSystem() {
		ConnectionHelper connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		EmployeeService employeeService = new EmployeeService();
		Employee employee = new Employee("Jim","Halpert","Jim.Halpert@dundermifflin.com","456 Zoo Ave",1);
		employee.setEmployeeId(2);
		boolean updateStatus = employeeService.updateEmployee(employee);
		assertTrue(updateStatus);
		connectionHelper.closeConnection();
	}
}
