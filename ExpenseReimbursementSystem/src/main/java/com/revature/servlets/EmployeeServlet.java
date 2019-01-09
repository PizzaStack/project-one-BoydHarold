package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.revature.ExpenseReimbursementSystem.Employee;
import com.revature.dbservice.EmployeeService;

public class EmployeeServlet extends HttpServlet {
	private EmployeeService employeeService = new EmployeeService();
	private List<Employee> employees;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter output = response.getWriter();
		employees = employeeService.getEmployees();
		
		for(Employee employee : employees) {
			JSONObject jo = new JSONObject();
			jo.put("firstname", employee.getFirstName());
			jo.put("lastname", employee.getLastName());
			jo.put("employeeid", employee.getEmployeeId());
			jo.put("address", employee.getAddress());
			jo.put("emailaddress", employee.getEmailAddress());
			output.print(jo);
		}
		

	}

}
