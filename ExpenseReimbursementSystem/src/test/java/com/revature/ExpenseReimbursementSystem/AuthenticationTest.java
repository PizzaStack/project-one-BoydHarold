package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.dao.ManagerDao;
import com.revature.jdbc.ConnectionHelper;
import com.revature.service.AuthenticationService;

public class AuthenticationTest {
	@Test
	public void canInstantiate() {
		Authentication authentication = new Authentication("username","password");
	}

	@Test
	public void anEmployeeShouldBeAuthenticated() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
		Authentication authentication = new Authentication("schruted","ilovebeets");
		AuthenticationService authenticationService = new AuthenticationService();
		List<String> employeeInfo = new ArrayList<String>();
		employeeInfo = authenticationService.authenticateEmployee(authentication);
		boolean authenticated = Boolean.parseBoolean(employeeInfo.get(1));
		assertTrue(authenticated);
		ch.closeConnection();
	}
	
	@Test
	public void aManagerShouldBeAuthenticated() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
		Authentication authentication = new Authentication("scottm","thatswhatshesaid");
		AuthenticationService authenticationService = new AuthenticationService();
		List<String> managerInfo = new ArrayList<String>();
		managerInfo = authenticationService.authenticateManager(authentication);
		boolean authenticated = Boolean.parseBoolean(managerInfo.get(1));
		assertTrue(authenticated);
		ch.closeConnection();
	}
	
	@Test
	public void anEmployeesInformationShouldBeReturnedAtLogin() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
    	Authentication authentication = new Authentication("schruted","ilovebeets");
    	AuthenticationService authenticationService = new AuthenticationService();
		List<String> employeeInfo = new ArrayList<String>();
		employeeInfo = authenticationService.authenticateEmployee(authentication);
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee = employeeDao.getEmployeeById(Integer.parseInt(employeeInfo.get(0)));
		assertEquals("Dwight",employee.getFirstName());
		assertEquals("Schrute",employee.getLastName());
		assertEquals("Dwight.Schrute@dundermifflin.com",employee.getEmailAddress());
		assertEquals("123 Wallaby Ln",employee.getAddress());
		assertEquals(1,employee.getStatus());
		ch.closeConnection();
	}
	
	@Test
	public void aManagersInformationShouldBeReturnedAtLogin() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
    	Authentication authentication = new Authentication("scottm","thatswhatshesaid");
    	AuthenticationService authenticationService = new AuthenticationService();
		List<String> managerInfo = new ArrayList<String>();
		managerInfo = authenticationService.authenticateManager(authentication);
		ManagerDao managerDao = new ManagerDao();
		Manager manager = managerDao.getManagerById(Integer.parseInt(managerInfo.get(0)));
		assertEquals("Michael",manager.getFirstName());
		assertEquals("Scott",manager.getLastName());
		assertEquals("Michael.Scott@dundermifflin.com",manager.getEmailAddress());
		assertEquals("736 Good Pl",manager.getAddress());
		assertEquals(1,manager.getStatus());
		ch.closeConnection();
	}
}
