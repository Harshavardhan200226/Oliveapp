package com.normal.Repository;

import org.springframework.data.repository.CrudRepository;

import com.normal.Model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
