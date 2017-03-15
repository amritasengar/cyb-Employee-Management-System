package com.sample.mvc.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sample.mvc.model.EmployeeCommand;
import com.sample.mvc.model.LogMessage;
import com.sample.mvc.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	private Log logger = LogFactory.getLog(EmployeeController.class);

	public String logMessage(String method, String resourcePath) {
		LogMessage logMessage = new LogMessage();
		logMessage.setApplicationName("EMSapplication");
		logMessage.setContinerHostName(System.getenv("HOST_NAME"));
		logMessage.setEcsCluster(System.getenv("ECS_CLUSTER"));
		logMessage.setHttpMethod(method);
		logMessage.setResourcePath(resourcePath);
		try {
			logMessage.setServerAddress(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		return gson.toJson(logMessage);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewHome() throws UnknownHostException {
		logger.info(logMessage("GET", "/home"));
		return "home";
	}

	@RequestMapping("/list")
	public String listContacts(Map<String, Object> map) throws UnknownHostException {
		map.put("contact", new EmployeeCommand());
		map.put("contactList", employeeService.listContact());

		logger.info(logMessage("GET", "/list"));

		return "list";
	}

	@RequestMapping(value = "/jsp/add", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute EmployeeCommand employeeCommand) throws UnknownHostException {
		employeeService.addEmployee(employeeCommand);
		logger.info(logMessage("POST", "/add"));
		return new ModelAndView("redirect:/list");
	}

	@RequestMapping(value = "/edit/{code}")
	public String getEmployeeById(@PathVariable("code") Integer empCode, Model model) throws UnknownHostException {
		EmployeeCommand employee = employeeService.getEmployeebyId(empCode);
		model.addAttribute("employee", employee);
		logger.info(logMessage("GET", "/edit"));

		return "edit";
	}

	@RequestMapping(value = "/edit/update", method = RequestMethod.POST)
	public String updateEmployee(@ModelAttribute EmployeeCommand employeeCommand) throws UnknownHostException {
		employeeService.updateEmployee(employeeCommand);
		logger.info(logMessage("POST", "/edit/update"));
		return "redirect:/list";
	}

	@RequestMapping(value = "/delete/{employeeId}")
	public String deleteEmplyee(@PathVariable("employeeId") Integer employeeId) throws UnknownHostException {
		employeeService.deleteEmployee(employeeId);

		logger.info(logMessage("GET", "/delete"));

		return "redirect:/list";
	}
}
