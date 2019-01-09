package com.revature.dbservice;

import java.util.ArrayList;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Authentication;
import com.revature.dao.AuthenticationDao;
import com.revature.jdbc.ConnectionHelper;

public class AuthenticationService {
	private List<Authentication> employeeAccounts;
	private List<Authentication> managerAccounts;
	private AuthenticationDao auth;
	private ConnectionHelper connectionHelper;
	

	
	public boolean authenticateEmployee(Authentication authentication) {
		connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		boolean authenticated = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int status = 0;
		String password = "";
		
		auth = new AuthenticationDao();
		employeeAccounts = new ArrayList<>();
		employeeAccounts = auth.getEmployeeAccounts();
		
		for(Authentication employeeAccount : employeeAccounts) {

			if(authentication.getUsername().equals(employeeAccount.getUsername())) {
				usernameAuthenticated = true;
				password = employeeAccount.getPassword();
				
				if(password.equals(authentication.getPassword())) {
					passwordAuthenticated = true;
					status = employeeAccount.getStatus();
				}
			}
		}

		if(usernameAuthenticated == true && passwordAuthenticated == true && status == 1) {
			authenticated = true;
		}
		return authenticated;
	}

	public boolean authenticateManager(Authentication authentication) {
		connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		boolean authenticated = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int status = 0;
		String password = "";
		
		auth = new AuthenticationDao();
		managerAccounts = new ArrayList<>();
		managerAccounts = auth.getManagerAccounts();
		
		for(Authentication managerAccount : managerAccounts) {
			
			if(authentication.getUsername().equals(managerAccount.getUsername())) {
				usernameAuthenticated = true;
				password = managerAccount.getPassword();
				
				if(password.equals(authentication.getPassword())) {
					passwordAuthenticated = true;
					status = managerAccount.getStatus();
				}
			}
		}

		if(usernameAuthenticated == true && passwordAuthenticated == true && status == 1) {
			authenticated = true;
		}
		
		return authenticated;
	}
}
