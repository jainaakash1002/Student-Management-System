package com.springboot.app.Model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class StudentCourse implements Serializable {

	@EmbeddedId
	StudentCoursekey id;
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int reg_id;
	
	@ManyToOne
	@MapsId("Student_id")
    @JoinColumn(name = "Student_id")
    private  Student student;
 

    @ManyToOne
    @MapsId("Course_id")
    @JoinColumn(name = "Course_id")
    private Course course;
 
    private String grade;
    
    private String courseName;
    
	public StudentCourse() {
		super();
	}

	public StudentCourse(int reg_id, Student student, Course course, String grade, String courseName) {
		super();
		//this.reg_id = reg_id;
		this.student = student;
		this.course = course;
		this.grade = grade;
		this.courseName = courseName;
	}

//	public int getReg_id() {
//		return reg_id;
//	}
//
//	public void setReg_id(int reg_id) {
//		this.reg_id = reg_id;
//	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public String getCourseid() {
		return course.getCourseid();
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
   
}