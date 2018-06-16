package com.springcourse.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.entity.Course;
import com.springcourse.entity.Enrol;
import com.springcourse.entity.Student;
import com.springcourse.service.EnrolServiceImpl;
import com.springcourse.service.StudentServiceImpl;

@RestController
public class StudentRestController {
	
	private StudentServiceImpl studentService;
	private EnrolServiceImpl enrolService;
	
	@Autowired
	public StudentRestController(StudentServiceImpl studentService, EnrolServiceImpl enrolService) {
		this.studentService = studentService;
		this.enrolService = enrolService;
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> listStudents(){
		
		List<Student> students = studentService.getStudents();
		
		return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
	}
	
	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
		
		Optional<Student> student = studentService.getStudent(id);
		if(student.isPresent()) {
			return new ResponseEntity<Student>(student.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/students/{id}/courses")
	public ResponseEntity<List<Course>> getCourse(@PathVariable("id") int id) {
		
		List<Enrol> enrols = enrolService.getEnrolByStudentId(id);
		List<Course> courses = new ArrayList<>();
		
		if(!enrols.isEmpty()) {
			for(Enrol e : enrols) {
				courses.add(e.getCourse());
			} 
			return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Course>>(HttpStatus.NOT_FOUND);
		}
	
	} 
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") int id) {
		
		studentService.deleteStudent(id);
		
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
	
	
}


