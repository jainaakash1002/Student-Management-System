package com.springboot.app.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentCoursekey implements Serializable {

	@Column(name="Student_id")
	private String stdid;
	
	@Column(name="Course_id")
	private String courseid;

	public String getStdid() {
		return stdid;
	}

	public void setStdid(String stdid) {
		this.stdid = stdid;
	}

	public String getCourseid() {
		return courseid;
	}

	public void setCourseid(String courseid) {
		this.courseid = courseid;
	}

	public StudentCoursekey(String stdid, String courseid) {
		super();
		this.stdid = stdid;
		this.courseid = courseid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) courseid.hashCode();
		result = prime * result + (int) stdid.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCoursekey other = (StudentCoursekey) obj;
		if (courseid != other.courseid)
			return false;
		if (stdid != other.stdid)
			return false;
		return true;
	}	
}