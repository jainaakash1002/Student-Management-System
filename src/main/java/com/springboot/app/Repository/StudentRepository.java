package com.springboot.app.Repository;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.Model.*;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	// @Modifying(clearAutomatically = true)
	public ArrayList<Student> findByStudentName(String studentName);

	@Transactional
	@Query("UPDATE Student s SET s.gpa = :gpa WHERE s.id = :sid")
	int updateGpa(@Param("sid") int id, @Param("gpa") double gpa);

	@Query("select s from Student s where s.id= :stdId")
	public Student findStudentByid(@Param("stdId") String stdId);

	@Query("select s from Student s where s.id=?1")
	public Student findxByid(String id);

	@Transactional
	@Modifying
	@Query("UPDATE Student s SET s.mobile = :mobileNO,s.EmailID = :mailID,s.address = :Address WHERE s.id = :sid")
	void updateDetails(@Param("sid") String id, @Param("mobileNO") int mobileNO, @Param("mailID") String mailID,
			@Param("Address") String Address);

	@Transactional
	@Modifying
	@Query("UPDATE Student s SET s.studentName = :sname,s.gender = :gender,s.DOB = :dob,s.degree=:degree,s.semester=:semester,s.address=:address,s.mobile=:mobile,s.gpa=:gpa,s.password=:password,s.EmailID=:mail WHERE s.id = :sid")
	void updateStudent(@Param("sid") String id, @Param("sname") String name, @Param("gender") String gender,
			@Param("dob") Date dob, @Param("degree") String degree, @Param("semester") int semester,
			@Param("address") String address, @Param("mobile") int mobile, @Param("gpa") double gpa,
			@Param("password") String password, @Param("mail") String mail);

	@Transactional
	@Modifying
	@Query("UPDATE Student s SET s.gpa=:sgpa WHERE s.id=:sid")
	void updateScore(@Param("sid") String id, @Param("sgpa") double gpa);

	@Transactional
	@Modifying
	@Query("UPDATE Student s SET s.sessionid = :SessionID WHERE s.id = :sid")
	void updateSessionId(@Param("sid") String id, @Param("SessionID") String SessionID);

	@Query("select s.id from Student s where s.sessionid = :SessionID")
	public String GetidbySessionID(@Param("SessionID") String SessionID);

	@Transactional
	@Modifying
	@Query(value = "insert into student_course (student_id,course_id,course_name) values (:stdID, :courseid, :coursename)", nativeQuery = true)
	void insertStudentCourse(@Param("stdID") String stdID, @Param("courseid") Integer courseid,
			@Param("coursename") String coursename);
	
	@Query("select s from Student s where s.EmailID= :stdId")
	public Student findStudentByEmailID(@Param("stdId") String stdId);

}