package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import com.revature.ExpenseReimbursementSystem.Authentication;
import com.revature.ExpenseReimbursementSystem.Employee;
import com.revature.ExpenseReimbursementSystem.Manager;
import com.revature.dao.AuthenticationDao;
import com.revature.dao.EmployeeDao;
import com.revature.dao.ManagerDao;
import com.revature.jdbc.ConnectionHelper;

public class AuthenticationService {
	private List<Authentication> employeeAccounts;
	private List<Authentication> managerAccounts;
	private List<String> employeeInfo;
	private List<String> managerInfo;
	private AuthenticationDao auth;
	private ConnectionHelper connectionHelper;
	

	
	public List<String> authenticateEmployee(Authentication authentication) {
		connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		employeeInfo = new ArrayList<String>();
		boolean authenticated = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int employeeId = 0;
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
					employeeId = employeeAccount.getReferenceId();
				} 
			} 
		}

		if(usernameAuthenticated == true && passwordAuthenticated == true && status == 1) {
			authenticated = true;
		}
		
		employeeInfo.add(String.valueOf(employeeId));
		employeeInfo.add(String.valueOf(authenticated));
		
		return employeeInfo;
	}
	
	public Employee getEmployeeInformation(String username, String password) {
    	Authentication authentication = new Authentication(username,password);
    	AuthenticationService authenticationService = new AuthenticationService();
		List<String> employeeInfo = new ArrayList<String>();
		employeeInfo = authenticationService.authenticateEmployee(authentication);
		EmployeeDao employeeDao = new EmployeeDao();
		Employee employee = employeeDao.getEmployeeById(Integer.parseInt(employeeInfo.get(0)));
		return employee;
	}

	public List<String> authenticateManager(Authentication authentication) {
		connectionHelper = new ConnectionHelper();
		connectionHelper.establishConnection();
		managerInfo = new ArrayList<String>();
		boolean authenticated = false;
		boolean usernameAuthenticated = false;
		boolean passwordAuthenticated = false;
		int managerId = 0;
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
					managerId = managerAccount.getReferenceId();
				}
			}
		}

		if(usernameAuthenticated == true && passwordAuthenticated == true && status == 1) {
			authenticated = true;
		}
		
		managerInfo.add(String.valueOf(managerId));
		managerInfo.add(String.valueOf(authenticated));
		
		return managerInfo;
	}
	
	public Manager getManagerInformation(String username, String password) {
    	Authentication authentication = new Authentication(username,password);
    	AuthenticationService authenticationService = new AuthenticationService();
		List<String> managerInfo = new ArrayList<String>();
		managerInfo = authenticationService.authenticateManager(authentication);
		ManagerDao managerDao = new ManagerDao();
		Manager manager = managerDao.getManagerById(Integer.parseInt(managerInfo.get(0)));
		return manager;
	}
}
