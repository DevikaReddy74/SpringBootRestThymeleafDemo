package com.cts.Dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.model.Student;

@Service
public interface StudentDao {

	public void createUpdateStudent(Student student);
	public List<Student> getAllStudents();
	public Student getStudentById(int id);
	public void updateStudent(Student student);
	public void deleteStudentByID(int id);
}
