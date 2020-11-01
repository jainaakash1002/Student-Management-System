package com.springboot.app.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.app.Model.*;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveDetails, Integer>{
	//public ArrayList<Leavedetails> findByLeaveName(String leavedetails);
	@Transactional
	@Modifying
	@Query("UPDATE LeaveDetails l SET l.status = :status WHERE l.leaveId= :leaveId")
	void updateLeaveStatuse(@Param("status") String status, @Param("leaveId") Integer  leaveId);
	
}