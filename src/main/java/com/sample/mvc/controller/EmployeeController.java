package com.sample.mvc.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sample.mvc.model.EmployeeCommand;
import com.sample.mvc.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewHome() {
		return "home";
	}
	
	@RequestMapping("/list")
	public String listContacts(Map<String, Object> map) {
	    map.put("contact", new EmployeeCommand());
	    map.put("contactList", employeeService.listContact());
	    return "list";
	}
	 
	@RequestMapping(value = "/jsp/add", method = RequestMethod.POST)
	public ModelAndView addEmployee(
			@ModelAttribute EmployeeCommand employeeCommand) {
		employeeService.addEmployee(employeeCommand);
		return new ModelAndView("redirect:/list");
	}
	 
	@RequestMapping(value = "/edit/{code}")
	public String getEmployeeById(@PathVariable ("code") Integer empCode, Model model) {
		EmployeeCommand employee = employeeService.getEmployeebyId(empCode);
		model.addAttribute("employee", employee);
		return "edit";
	}
	
	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
	public String updateEmployee(
			@ModelAttribute EmployeeCommand employeeCommand) {
		employeeService.updateEmployee(employeeCommand);
		return "redirect:/list";
	}
	
    @RequestMapping(value = "/delete/{employeeId}")
    public String deleteEmplyee(@PathVariable ("employeeId") Integer employeeId)
    {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/list";
    }
}
