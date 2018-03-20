package com.sample.mvc.daoTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.sample.mvc.dao.EmployeeDAOImpl;
import com.sample.mvc.model.EmployeeCommand;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOImplTest {
	
	@Mock
	SessionFactory sessionFactory;
	Serializable serializable=mock(Serializable.class);
	Session session=mock(Session.class);
	EmployeeCommand emp;
	
	@InjectMocks
	EmployeeDAOImpl employeeDaoImpl=new EmployeeDAOImpl();
	
	@Before
    public void setup() {
 
        MockitoAnnotations.initMocks(this);
        emp = new EmployeeCommand();
    	 emp.setCode(105);
    	 emp.setName("vikas");
    	 emp.setCity("pune");
        }
	
	@Test
	public void addEmployeeTest()
	{
		
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.save(any(EmployeeCommand.class))).thenReturn(serializable);
		employeeDaoImpl.addEmployee(emp);
	}
	
	
	@Test
	public void deleteEmployeeTest()
	{
		EmployeeCommand employee = null;
		int deleteId=105;
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.load(EmployeeCommand.class, deleteId)).thenReturn(emp);
		when(session.load(EmployeeCommand.class, 101)).thenReturn(null);
		assertNotNull(emp);
		assertNull(employee);
		employee=new EmployeeCommand();
		assertNotNull(employee);
		assertNotNull(sessionFactory.getCurrentSession().load(EmployeeCommand.class, deleteId));
		doNothing().when(session).delete(any(EmployeeCommand.class));
		employeeDaoImpl.deleteEmployee(deleteId);
		assertNull(sessionFactory.getCurrentSession().load(EmployeeCommand.class, 101));
	}
	
	
	@Test
	public void listContactTest()
	{
		List<EmployeeCommand> empList=mock(List.class);
        Query query=mock(Query.class);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.createQuery(anyString())).thenReturn(query);
		when(query.list()).thenReturn(empList);
		when(empList.add(any(EmployeeCommand.class))).thenReturn(true);
		//System.out.println(sessionFactory.getCurrentSession().createQuery("view").list());
		assertEquals(empList.size(),sessionFactory.getCurrentSession().createQuery(anyString()).list().size());
		//System.out.println(employeeDaoImpl.listContact().size());
		//System.out.println(empList.get(1).getCity());
		assertNotNull(employeeDaoImpl.listContact());
		
	}
	
	
	@Test
	public void getEmployeebyIdTest()
	{
		int getId=105;
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.get(EmployeeCommand.class,getId)).thenReturn(emp);
		//System.out.println(employeeDaoImpl.getEmployeebyId(getId).getCity());
		assertEquals("pune",employeeDaoImpl.getEmployeebyId(getId).getCity());
		//for failing in circle ci
		//assertEquals("punee",employeeDaoImpl.getEmployeebyId(getId).getCity());
	}
	
	
	@Test
	public void updateEmployeeTest()
	{
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		doNothing().when(session).update(any(EmployeeCommand.class));
		employeeDaoImpl.updateEmployee(emp);
	}
	
}
