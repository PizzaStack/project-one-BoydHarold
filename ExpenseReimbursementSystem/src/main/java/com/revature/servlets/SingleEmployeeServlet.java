package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.revature.ExpenseReimbursementSystem.Employee;
import com.revature.dbservice.EmployeeService;


public class SingleEmployeeServlet extends HttpServlet {
	private EmployeeService employeeService = new EmployeeService();
	private String updateStat;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter output = response.getWriter();
		output.write(updateStat);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader br = request.getReader();
		String line = "";
		String body = "";
	
		while((line = br.readLine()) != null) {
			body = line;
		}
		
		JSONObject json = new JSONObject(body);
		Employee employee = new Employee(json.getString("firstname"),json.getString("lastname"),json.getString("emailaddress"),json.getString("address"),1);
		
		employee.setEmployeeId(json.getInt("employeeid"));
		boolean updateStatus = employeeService.updateEmployee(employee);
		updateStat = String.valueOf(updateStatus);
	}

}
