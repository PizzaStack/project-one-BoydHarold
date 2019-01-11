package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.revature.ExpenseReimbursementSystem.Authentication;
import com.revature.ExpenseReimbursementSystem.Employee;
import com.revature.service.AuthenticationService;

public class AuthenticationServlet extends HttpServlet {

	private AuthenticationService authenticationService = new AuthenticationService();
	private String auth;
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String address;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter output = response.getWriter();
		JSONObject userInformation = new JSONObject();
		userInformation.put("auth", auth);
		userInformation.put("id", id);
		userInformation.put("firstname", firstName);
		userInformation.put("lastname", lastName);
		userInformation.put("emailaddress", emailAddress);
		userInformation.put("address", address);
		output.print(userInformation);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		BufferedReader br = request.getReader();
		String line = "";
		String credentials = "";
		String username = "";
		String password = "";
		String type = "";

		while((line = br.readLine()) != null) {
			credentials = line;
		}

		if(credentials.indexOf(",") > 0) {
			username = credentials.substring(0,credentials.indexOf(","));
			credentials = credentials.substring(credentials.indexOf(",") + 1, credentials.length());
			password = credentials.substring(0,credentials.indexOf(","));
			
			if(credentials.indexOf(",") > 0) {
				type = credentials.substring(credentials.indexOf(",") + 1, credentials.length());
			}
		}
		
		Authentication authentication = new Authentication(username, password);
		
		if(type.equals("employee")) {
			List<String> employeeInformation = authenticationService.authenticateEmployee(authentication);
			auth = employeeInformation.get(1);
			if(employeeInformation.get(1).equals("true")) {
				Employee employee = authenticationService.getEmployeeInformation(username, password);
				id = employee.getEmployeeId();
				firstName = employee.getFirstName();
				lastName = employee.getLastName();
				emailAddress = employee.getEmailAddress();
				address = employee.getAddress();
			}
		} else if(type.equals("manager")){
			List<String> managerInformation = authenticationService.authenticateManager(authentication);
			auth = managerInformation.get(1);
		}
		
		
	}
	

	
}
