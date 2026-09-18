package com.normal.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.normal.model.Student;
import com.normal.repository.StudentRepository;

public class StudentService {
	@Autowired StudentRepository studentRepo;
	public String createStudent(Student name) {
		Student saved=studentRepo.createStudent(name);
		return "student is created";
	}

}
