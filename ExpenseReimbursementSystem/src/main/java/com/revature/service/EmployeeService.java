package com.revature.service;

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
	
	public boolean updateEmployee(Employee employee) {
		boolean updateStatus = false;
		employeeDao.updateEmployee(employee);
		Employee updatedEmployee = employeeDao.getEmployeeById(employee.getEmployeeId());
		
		if(updatedEmployee.getFirstName().equals(employee.getFirstName()) && updatedEmployee.getLastName().equals(employee.getLastName())
				&& updatedEmployee.getEmailAddress().equals(employee.getEmailAddress()) && updatedEmployee.getAddress().equals(employee.getAddress())) {
			updateStatus = true;
		}
		return updateStatus;
	}
}
