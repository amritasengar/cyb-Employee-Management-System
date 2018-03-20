package com.sample.mvc.dao;

import java.util.List;

import com.sample.mvc.model.EmployeeCommand;

public interface EmployeeDAO {
	public void addEmployee(EmployeeCommand employee);
	public List<EmployeeCommand> listContact();
	public void deleteEmployee(Integer employeeId);
	public EmployeeCommand getEmployeebyId(Integer employeeID );
	public void updateEmployee(EmployeeCommand employeeCommand);
}
