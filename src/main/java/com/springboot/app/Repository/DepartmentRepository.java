package com.springboot.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.Model.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	//public ArrayList<Department> findByDeptName(String department);

}