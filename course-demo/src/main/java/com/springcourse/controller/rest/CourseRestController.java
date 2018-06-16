package com.springcourse.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.entity.Course;
import com.springcourse.service.CourseServiceImpl;

@RestController
public class CourseRestController {
	
	private CourseServiceImpl courseService;
	
	@Autowired
	public CourseRestController(CourseServiceImpl courseService) {
		this.courseService = courseService;
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getCourses() {
		
		List<Course> courses = courseService.getCourses();
		
		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
	}
	
	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") int id) {
		
		Optional<Course> course = courseService.getCourse(id);
		if(course.isPresent()) {
			return new ResponseEntity<Course>(course.get(), HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}

	}
	
	@PostMapping("/courses")
	public ResponseEntity<Void> createCourse(@RequestBody Course course) {
		
		courseService.saveCourse(course);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/courses/{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable("id") int id) {
		
		courseService.deleteCourse(id);
		
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("courses/{id}")
	public ResponseEntity<Optional<Course>> updateCourse(@PathVariable("id") int id, @RequestBody Course course) {
		
		Optional<Course> theCourse = courseService.getCourse(id);
		
		theCourse.get().setTitle(course.getTitle());
		theCourse.get().setPrice(course.getPrice());
		theCourse.get().setInstructor(course.getInstructor());
		
		return new ResponseEntity<Optional<Course>>(theCourse, HttpStatus.OK);
	}

}












