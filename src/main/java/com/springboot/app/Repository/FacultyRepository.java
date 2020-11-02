package com.springboot.app.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.app.Model.*;

@Repository
public interface FacultyRepository extends JpaRepository<Staff, String> {
	@Query("select f from Staff f where f.id= :staffId")
	public Staff findStaffByid(@Param("staffId") String userId);

	@Transactional
	@Modifying
	@Query(value = "insert into staff (id, name,password,role,department_name_id) values ( :SID,:SNAME,:SPASSWORD,:SROLE,:DNAMEiD)", nativeQuery = true)
	void insertStaff(@Param("SID") String SID, @Param("SNAME") String SNAME, @Param("SPASSWORD") String SPASSWORD,
			@Param("SROLE") Integer SROLE, @Param("DNAMEiD") Integer DNAMEiD);
	
	@Query("select s from Staff s where s.name= :stfName")
	public Staff findStaffByname(@Param("stfName") String stfName);

}