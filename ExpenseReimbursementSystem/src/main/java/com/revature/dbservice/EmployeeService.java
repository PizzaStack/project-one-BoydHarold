package com.revature.dbservice;

import java.util.List;

import com.revature.ExpenseReimbursementSystem.Employee;
import com.revature.dao.EmployeeDao;

public class EmployeeService {

	private EmployeeDao employeeDao;
	
	public EmployeeService() {
		employeeDao = new EmployeeDao();
	}

	
	public List<Employee> getEmployees(){
		List<Employee> employees = employeeDao.getEmployees();
		return employees;
	}
}
