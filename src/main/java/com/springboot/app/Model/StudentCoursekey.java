package com.springboot.app.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class StudentCoursekey implements Serializable {

	@Column(name="Student_id")
	private int stdid;
	
	@Column(name="Course_id")
	private int courseid;

	public int getStdid() {
		return stdid;
	}

	public void setStdid(int stdid) {
		this.stdid = stdid;
	}

	public int getCourseid() {
		return courseid;
	}

	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public StudentCoursekey(int stdid, int courseid) {
		super();
		this.stdid = stdid;
		this.courseid = courseid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + courseid;
		result = prime * result + stdid;
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