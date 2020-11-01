package com.springboot.app.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.Model.*;

@Repository
public interface StudentCourseRepository extends JpaRepository< StudentCourse, Integer> {

	
	//public  ArrayList<StudentCourse> findAll();
	
//	@Query("select s from StudentCourse s ")
//	public ArrayList<StudentCourse>  findStudentCourseall();
	

}