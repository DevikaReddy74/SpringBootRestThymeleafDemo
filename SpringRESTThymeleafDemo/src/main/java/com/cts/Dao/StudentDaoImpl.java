package com.cts.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Student;
import com.cts.repository.StudentRepository;

@Service
public class StudentDaoImpl  implements StudentDao{

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void createUpdateStudent(Student student) {
		studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAllByOrderByLastNameAsc();
	}

	@Override
	public Student getStudentById(int id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteStudentByID(int id) {
		studentRepository.deleteById(id);
	}
	
	
}
