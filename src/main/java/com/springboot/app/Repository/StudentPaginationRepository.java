package com.springboot.app.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.Model.*;

@Repository
public interface StudentPaginationRepository extends PagingAndSortingRepository<Student, Integer> {

}