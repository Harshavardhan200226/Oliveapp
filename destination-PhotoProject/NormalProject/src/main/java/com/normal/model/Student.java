package com.normal.model;
public class Student {
	protected String name;
	protected String college;
	protected String course;
	protected String branch;
	protected int id;
	protected String section;
	Student(String name, String college, String course, String branch, int id, String section){
		this.name=name;
		this.college=college;
		this.course=course;
		this.branch=branch;
		this.id=id;
		this.section=section;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	
}
