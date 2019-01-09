package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.ExpenseReimbursementSystem.Authentication;
import com.revature.dbservice.AuthenticationService;

public class AuthenticationServlet extends HttpServlet {

	private AuthenticationService authenticationService = new AuthenticationService();
	private static String auth;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		PrintWriter output = response.getWriter();
		output.write(auth);
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
			auth = String.valueOf(authenticationService.authenticateEmployee(authentication));
		} else if(type.equals("manager")){
			auth = String.valueOf(authenticationService.authenticateEmployee(authentication));
		}
		
		
	}
	

	
}
