package com.cts.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.Dao.StudentDao;
import com.cts.exception.StudentNotFoundException;
import com.cts.model.Student;

@Controller
public class StudentRestController {

	@Autowired
	private StudentDao studentDao;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/addStudent")
	public String addStudentForm(Model model) {
		Student student=new Student();
		model.addAttribute("student", student);
		return "addStudentForm";
	}

//	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	@PostMapping("/saveStudents")
	public String addinStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "redirect:/addStudentForm";
		} else {
			studentDao.createUpdateStudent(student);
			return "redirect:/students";
		}
	}

	@GetMapping("/showStudent")
	public String getStudentById(@RequestParam("id") int id, Model model) {
		Student student;
		try {
			student = studentDao.getStudentById(id);
		} catch (Exception e) {
			String message = "Student " + id + " Not found";
			throw new StudentNotFoundException(message);
		}
		model.addAttribute("student", student);
		return "showStudent";
	}

	@GetMapping("/students")
	public String getAllStudents(Model model) {
		List<Student> students = studentDao.getAllStudents();
		model.addAttribute("students",students);
		return "showAllStudents";
	}

	@GetMapping("/updateStudents")
	public String UpdatingStudentForm(@RequestParam("id") int id, Model model) {
		Student student=studentDao.getStudentById(id);
		model.addAttribute("student", student);
		return "addStudentForm";
	}
	

	@GetMapping("/deleteStudents")
	public String delStudentById(@RequestParam("id") int id) {
		try {
			studentDao.deleteStudentByID(id);
		} catch (Exception e) {
			String message = "Student " + id + " Not found";
			throw new StudentNotFoundException(message);
		}
		return "redirect:/students";
	}

}
