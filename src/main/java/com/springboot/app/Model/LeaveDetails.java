package com.springboot.app.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class LeaveDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	private int days;
	@DateTimeFormat(iso = ISO.DATE)
	private Date start_date;
	@DateTimeFormat(iso = ISO.DATE)
	private Date End_date;
	private String description;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne
	private Staff staff;

	public String getStaffId() {
		return staff.getId();
	}

	public LeaveDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveDetails(int leaveId, int days, Date start_date, Date end_date, String description, String status, Staff staff) {
		super();
		this.leaveId = leaveId;
		this.days = days;
		this.start_date = start_date;
		End_date = end_date;
		this.description = description;
		this.status = status;
		this.staff = staff;
	}

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return End_date;
	}

	public void setEnd_date(Date end_date) {
		End_date = end_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Leavedetails [leaveId=" + leaveId + ", days=" + days + ", start_date=" + start_date + ", End_date="+ End_date + ", description=" + description + ", status=" + status + ", staff=" + staff + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + days;
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
		LeaveDetails other = (LeaveDetails) obj;
		if (days != other.days)
			return false;
		return true;
	}

}