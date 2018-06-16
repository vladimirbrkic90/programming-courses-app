package com.springcourse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.entity.Course;

@Service
public class CourseServiceImpl {

	private CourseRepository courseRepository;
	
	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	public List<Course> getCourses() {
		
		List<Course> courses = new ArrayList<>();
		
		courseRepository.findAll().forEach((Course c) -> courses.add(c));
		
		return courses;
	}
	
	@Transactional
	public Optional<Course> getCourse(int id) {
		
		Optional<Course> course = courseRepository.findById(id);
		
		return course;
	}
	
	@Transactional
	public List<Course> getCoursesByTitle(String title) {
		
		List<Course> courses = courseRepository.findByTitle(title);
		
		return courses;
	}
	
	@Transactional
	public void saveCourse(Course theCourse) {
		
		courseRepository.save(theCourse);
	}
	
	@Transactional
	public void deleteCourse(int id) {
		
		courseRepository.deleteById(id);
	}
}


