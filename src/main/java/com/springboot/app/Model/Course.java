package com.springboot.app.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String courseid;

	private String courseName;
	
	@DateTimeFormat(iso=ISO.DATE)	
	private Date startDate;
	
	@DateTimeFormat(iso=ISO.DATE)	
	private Date endDate;
	
	private int units;

	
	@OneToMany(mappedBy="course")
	List<StudentCourse> std_course;
	
	
	//relationship with department and course is 1-M
	@ManyToOne
	private Department departmentName;


	public Course(String courseid, String courseName, Date startDate, Date endDate, int units) {
		super();
		this.courseid = courseid;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.units = units;
	}


	public Course() {
		super();
	}


	public String getCourseid() {
		return courseid;
	}


	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public int getUnits() {
		return units;
	}


	public void setUnits(int units) {
		this.units = units;
	}

}