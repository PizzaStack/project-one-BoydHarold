package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.jdbc.ConnectionHelper;

public class ConnectionServlet extends HttpServlet {
	private ConnectionHelper connectionHelper = new ConnectionHelper();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		
		String command = "";
		String line = "";
		
		while((command = br.readLine()) != null) {
			line = command;
		}
		
		
		
		if(line.equals("OFF")) {
			if(ConnectionHelper.connection != null) {
			connectionHelper.closeConnection();
			System.out.println(line);
			response.sendRedirect("http://localhost:8080/ExpenseReimbursementSystem/index.html");
			}
		}
		
		
		

	}
}
