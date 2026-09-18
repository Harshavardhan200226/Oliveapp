package com.normal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.normal.model.Student;
import com.normal.service.StudentService;

@RestController
public class StudentController {
	@Autowired StudentService studentService;
	@RequestMapping("/students")
	@PostMapping("/createStudents")
	public String createStudent(Student name) {
		String saved=studentService.createStudent(name);
		return "Student is created successfully";
	}
}
