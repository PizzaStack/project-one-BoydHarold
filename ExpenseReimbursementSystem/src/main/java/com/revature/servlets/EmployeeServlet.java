package com.revature.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.revature.ExpenseReimbursementSystem.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServlet extends HttpServlet {
	private EmployeeService employeeService = new EmployeeService();
	private List<Employee> employees;
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter output = response.getWriter();
		employees = employeeService.getEmployees();
		JSONArray jsonArray = new JSONArray();
		for(Employee employee : employees) {
			JSONObject jo = new JSONObject();
			jo.put("firstname", employee.getFirstName());
			jo.put("lastname", employee.getLastName());
			jo.put("employeeid", employee.getEmployeeId());
			jo.put("address", employee.getAddress());
			jo.put("emailaddress", employee.getEmailAddress());
			jsonArray.put(jo);
		}
		
		output.print(jsonArray);
	}

}
