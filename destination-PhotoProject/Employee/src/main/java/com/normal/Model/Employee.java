package com.normal.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Employee {
@Id
public int id;
@Column
private String empName;
@Column 
private String empAdd;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getEmpAdd() {
	return empAdd;
}
public void setEmpAdd(String empAdd) {
	this.empAdd = empAdd;
}

}
