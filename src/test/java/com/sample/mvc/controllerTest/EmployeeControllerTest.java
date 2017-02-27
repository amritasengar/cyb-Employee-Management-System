package com.sample.mvc.controllerTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.sample.mvc.controller.EmployeeController;
import com.sample.mvc.model.EmployeeCommand;
import com.sample.mvc.service.EmployeeService;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
	

	
	
	 public EmployeeService employeeService=mock(EmployeeService.class);
	 
	 
	    @InjectMocks
	    public EmployeeController employeeController=new EmployeeController();
	    EmployeeCommand emp;
	    private MockMvc mockMvc;
	 
	    @Before
	    public void setup() {
	 
	        MockitoAnnotations.initMocks(this);
	 
	        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	        emp = new EmployeeCommand();
	    	 emp.setCode(105);
	    	 emp.setName("vikas");
	    	 emp.setCity("pune");
	        }
	    
	        

	    
	    @Test
	    public void testAddEmployee() throws Exception {
	     
	    	ModelAndView modelandview=mock(ModelAndView.class);
	        Mockito.doNothing().when(employeeService).addEmployee(any(EmployeeCommand.class));
	        System.out.println(employeeController.addEmployee(emp));
	        assertNotNull(employeeController.addEmployee(emp));
	        assertFalse(employeeController.addEmployee(emp).isEmpty());
	    }
	    
	    @Test
		public void testGetEmployeeById()   throws Exception {
	    	
	    	 
			
	    	/*Model model=mock(Model.class); 
			this.mockMvc
					.perform(post("/edit/{code}", 105))
					.andExpect(view().name("edit"))
					.andExpect(forwardedUrl("edit"))
//					.andExpect(model().attribute("employee",  105 hasProperty("code", is(105))))
					;

			when(employeeService.getEmployeebyId(anyInt())).thenReturn(emp);
			verify(employeeService).getEmployeebyId(anyInt());
	    	//System.out.println(employeeController.getEmployeeById(anyInt(), any(Model.class)));
			assertEquals("vikas",employeeService.getEmployeebyId(anyInt()).getName());
			model.addAttribute("employee", emp);
			assertEquals("edit",employeeController.getEmployeeById(20, model));
	    	//when(employeeService.getEmployeebyId(20)).thenReturn(emp);
	    	//System.out.println(employeeController.getEmployeeById(20, model));
*/			
	    	
	    	Model model=mock(Model.class);
			when(employeeService.getEmployeebyId(anyInt())).thenReturn(emp);
			assertEquals("vikas",employeeService.getEmployeebyId(anyInt()).getName());
			assertEquals("pune",employeeService.getEmployeebyId(anyInt()).getCity());
			model.addAttribute("employee", emp);
			assertEquals("edit",employeeController.getEmployeeById(20, model));
		}
	    
	    
	    @Test
		public void testViewHome()
		{
			
			Assert.assertEquals("home", employeeController.viewHome());
			
		}
	    
	    @Test
	    public void testUpdateEmployee() throws Exception
	    {
           doNothing().when(employeeService).updateEmployee(any(EmployeeCommand.class));
	    	//Mockito.verify(employeeService).updateEmployee(any(EmployeeCommand.class));
	    	//assertThat(employeeService.updateEmployee(emp));
	    	//assertTrue("message", employeeService.updateEmployee(emp));
           //System.out.println(employeeController.updateEmployee(emp));
           assertEquals("redirect:/list",employeeController.updateEmployee(emp));
	    	
	    }
	    
	    
	    @Test
	    public void testDeleteEmployee() throws Exception
	    {
	    	doNothing().when(employeeService).deleteEmployee(anyInt());
	    	assertEquals("redirect:/list",employeeController.deleteEmplyee(101));
	    }
	    
	    
	    @Test
	    public void testListContacts() throws Exception
	    {
          List<EmployeeCommand> empList=new ArrayList<EmployeeCommand>();
          empList.add(emp);
          when(employeeService.listContact()).thenReturn(empList);
          Map<String,Object> map=new HashMap<String,Object>();
          map.put("contact", new EmployeeCommand());
          map.put("contactList",employeeService.listContact() );
          //System.out.println(map);
	    	//doNothing().when(employeeService).deleteEmployee(anyInt());
	    	//assertEquals("redirect:/list",employeeController.deleteEmplyee(101));
          assertEquals(2,map.size());
          assertEquals("list",employeeController.listContacts(map));
	    }
	    
}
