package com.sample.mvc.serviceTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.sample.mvc.dao.EmployeeDAO;
import com.sample.mvc.model.EmployeeCommand;
import com.sample.mvc.service.EmployeeServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	
	public EmployeeDAO employeeDao=mock(EmployeeDAO.class);
	
	private EmployeeCommand emp;
	
	@InjectMocks
	public EmployeeServiceImpl employeeService=new EmployeeServiceImpl();
	
	
	
	 @Before
	    public void setup() {
	 
	        // Process mock annotations
	        MockitoAnnotations.initMocks(this); 
	        emp = new EmployeeCommand();
	      	 emp.setCode(105);
	         emp.setName("vikas");
	         emp.setCity("pune");
	 }
	 
	 
	 
	@Test
	public void testGetEmployeeById() {
		
      //employeeDao.getEmployeebyId(10);
      when(employeeDao.getEmployeebyId(20)).thenReturn(emp);
      //System.out.println((employeeDao.getEmployeebyId(20)).getCode());
     
      //System.out.println(employeeService.getEmployeebyId(10));
      //doReturn(emp).when(employeeDao.getEmployeebyId(anyInt()));
      //doReturn(emp).when(employeeDao).getEmployeebyId(10);
		Mockito.verify(employeeDao,Mockito.times(0)).getEmployeebyId(20);
		//assertEquals(10,employeeService.getEmployeebyId(10));
		 assertEquals(105,employeeService.getEmployeebyId(20).getCode());
	}
	
	
	@Test()
	public void testUpdateEmployee()
	{
		//EmployeeDAOImpl employeeDaoImpl=mock(EmployeeDAOImpl.class);
	     boolean thrown = false;
		//doNothing().when(spy(employeeDaoImpl)).updateEmployee(any(EmployeeCommand.class));
	      //doThrow(new NullPointerException()).when(employeeDao).updateEmployee(emp);
		//verify(employeeDaoImpl).updateEmployee(emp);
	      //System.out.println(employeeDao.updateEmployee(emp));
	      //assertNull(employeeDao.updateEmployee(emp));
	     doNothing().when(employeeDao).updateEmployee(any(EmployeeCommand.class));
	     //verify(employeeDao).updateEmployee(any(EmployeeCommand.class));
	     try{
	     employeeService.updateEmployee(emp);
	     thrown=true;
	     }
	     catch(Exception e)
	     {
	    	 thrown=false;
	     }
	     assertTrue(thrown);
	    	
	    	  
	}
	
	@Test
	public void testListContact()
	{
		List<EmployeeCommand> empList=new ArrayList<EmployeeCommand>();
		empList.add(emp);
		
		when(employeeDao.listContact()).thenReturn(empList);
		//System.out.println(employeeService.listContact().size());
		assertEquals(1,employeeService.listContact().size());
		//verify(employeeDao).listContact();
	}
	
	
	
	@Test
	public void testDeleteEmployee()
	{
		doNothing().when(employeeDao).deleteEmployee(anyInt());
	employeeService.deleteEmployee(102);
	}
	
	
	
	@Test
	public void testAddEmployee()
	{
		doNothing().when(employeeDao).addEmployee(any(EmployeeCommand.class));
	employeeService.addEmployee(emp);
	}
}
