package com.sample.mvc.service;

import java.util.List;

import com.sample.mvc.model.EmployeeCommand;

public interface EmployeeService {
	public void addEmployee(EmployeeCommand employee);
	public List<EmployeeCommand> listContact();
	public void deleteEmployee(Integer employeeId);
	public EmployeeCommand getEmployeebyId(Integer employeeId);
	public void updateEmployee(EmployeeCommand employeeCommand);
}
