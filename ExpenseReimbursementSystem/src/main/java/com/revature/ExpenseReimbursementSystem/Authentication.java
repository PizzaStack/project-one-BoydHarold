package com.revature.ExpenseReimbursementSystem;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AuthenticationDao;

public class Authentication {
	private int userId;
	private int referenceId;
	private String username;
	private String password;
	private int status;
	
	public Authentication(int referenceId, String username, String password) {
		this.referenceId = referenceId;
		this.username = username;
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean authenticateEmployee(Authentication authentication) {
		boolean authenticated = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int status = 0;
		String password = "";
		
		AuthenticationDao auth = new AuthenticationDao();
		List<Authentication> employeeAccounts = new ArrayList<>();
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
		boolean authenticated = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int status = 0;
		String password = "";
		
		AuthenticationDao auth = new AuthenticationDao();
		List<Authentication> managerAccounts = new ArrayList<>();
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
