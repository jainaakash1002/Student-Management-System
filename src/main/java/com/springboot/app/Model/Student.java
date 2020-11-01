package com.springboot.app.Model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotEmpty(message="Please enter ID")
	private String id;	
	private String studentName;
	private String gender;
	@DateTimeFormat(iso=ISO.DATE)
	@Past
	private Date DOB;
	private String degree;
	private int semester;
	private double gpa;
	@Size(min=2,max=50,message="Please enter password")
	private String password;
	private String address;
	private int mobile;
	@Email(regexp="(.+)@(.+)$", message="Invalid email pattern")
	private String EmailID;
	private String sessionid;
	
	@OneToMany(mappedBy="student")
	List<StudentCourse> std_course;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	public String getEmailID() {
		return EmailID;
	}

	public void setEmailID(String emailID) {
		EmailID = emailID;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public Student(String id, String studentName, String gender, Date dOB, String degree, int semester, double gpa, String password, String address, int mobile, String EmailID, String sessionid) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.gender = gender;
		DOB = dOB;
		this.degree = degree;
		this.semester = semester;
		this.gpa = gpa;
		this.password = password;
		this.address = address;
		this.mobile = mobile;
		this.EmailID = EmailID;
		this.sessionid = sessionid;
	}

	public Student() {
		super();
	}	
}