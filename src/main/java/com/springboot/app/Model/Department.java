package com.springboot.app.Model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) int id;
	private String name;

	
	@OneToMany(mappedBy = "departmentName")
	private List<Course> courses;
	
	
	@OneToMany(mappedBy = "departmentName")
	private List<Staff> staffs;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}


	public List<Staff> getStaffs() {
		return staffs;
	}


	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}


	public Department(int id, String name, List<Course> courses, List<Staff> staffs) {
		super();
		this.id = id;
		this.name = name;
		this.courses = courses;
		this.staffs = staffs;
	}
	
	
}