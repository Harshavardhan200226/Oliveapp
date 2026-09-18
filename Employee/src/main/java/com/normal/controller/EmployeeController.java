package com.normal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.normal.Model.Employee;
import com.normal.Repository.EmployeeRepository;

@Controller
public class EmployeeController{
	@Autowired EmployeeRepository empRepository;
	@RequestMapping("/home")
	public String home() {
		return "index.jsp";
	}
	@RequestMapping("/addEmp")
	public String addEmployee(Employee employee) {
		empRepository.save(employee);
		return "index.jsp";
	}
}
