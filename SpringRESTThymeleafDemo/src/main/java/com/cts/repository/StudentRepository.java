package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.model.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {
	
//	Adding a method to sort the students by lastName
	public List<Student> findAllByOrderByLastNameAsc();

}
