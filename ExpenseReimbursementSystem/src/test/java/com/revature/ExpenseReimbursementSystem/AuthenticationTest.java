package com.revature.ExpenseReimbursementSystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.jdbc.ConnectionHelper;

public class AuthenticationTest {
	@Test
	public void canInstantiate() {
		Authentication authentication = new Authentication(1,"username","password");
	}

	@Test
	public void anEmployeeShouldBeAuthenticated() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
		Authentication authentication = new Authentication(1,"schruted","ilovebeets");
		boolean authenticated = authentication.authenticateEmployee(authentication);
		assertTrue(authenticated);
		ch.closeConnection();
	}
	
	@Test
	public void aManagerShouldBeAuthenticated() {
    	ConnectionHelper ch = new ConnectionHelper();
    	ch.establishConnection();
		Authentication authentication = new Authentication(1,"scottm","thatswhatshesaid");
		boolean authenticated = authentication.authenticateManager(authentication);
		assertTrue(authenticated);
		ch.closeConnection();
	}
}
