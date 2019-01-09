package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.dbservice.AuthenticationService;
import com.revature.jdbc.ConnectionHelper;

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
		boolean authenticated = authenticationService.authenticateEmployee(authentication);
		assertTrue(authenticated);
		ch.closeConnection();
	}
	
	@Test
	public void aManagerShouldBeAuthenticated() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
		Authentication authentication = new Authentication("scottm","thatswhatshesaid");
		AuthenticationService authenticationService = new AuthenticationService();
		boolean authenticated = authenticationService.authenticateManager(authentication);
		assertTrue(authenticated);
		ch.closeConnection();
	}
	
	@Test
	public void anEmployeesInformationShouldBeReturnedAtLogin() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
    	Authentication authentication = new Authentication("schruted","ilovebeets");
    	AuthenticationService authenticationService = new AuthenticationService();
    	authentication = getEmployeeInformation
	}
}
