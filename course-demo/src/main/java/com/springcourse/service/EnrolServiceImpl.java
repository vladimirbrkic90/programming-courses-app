package com.springcourse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.entity.Course;
import com.springcourse.entity.Enrol;

@Service
public class EnrolServiceImpl {

	private EnrolRepository enrolRepository;
	
	private CourseRepository courseRepository;
	
	@Autowired
	public EnrolServiceImpl(EnrolRepository enrolRepository, CourseRepository courseRepository) {
		this.enrolRepository = enrolRepository;
		this.courseRepository = courseRepository;
	}
	
	@Transactional
	public List<Enrol> getEnrols() {
		
		List<Enrol> enrols = new ArrayList<>();
		
		//enrolRepository.findAll().forEach((Enrol e) -> enrols.add(e));
		
		for(Enrol e : enrolRepository.findAll()) {
			enrols.add(e);
		}
		
		return enrols;
	}
	
	public Optional<Enrol> getEnrol(int id) {
		
		Optional<Enrol> enrol = enrolRepository.findById(id);
		
		return enrol;
	}
	
/*	@Transactional
	public Optional<Enrol> getEnrolByStudentId(int id) {
		
		Optional<Enrol> enrol = enrolRepository.findByStudentId(id);
		
		return enrol;
	} */
	
	public List<Enrol> getEnrolByStudentId(int id) {
		
		List<Enrol> enrols = new ArrayList<>();
		
		enrolRepository.findByStudentId(id).forEach((Enrol e) -> enrols.add(e));
		
		return enrols; 
	} 
	
	@Transactional
	public Optional<Enrol> getEnrolByStudentAndCourse(int student_id, int course_id) {
		
		Optional<Enrol> enrol = enrolRepository.findEnrol(student_id, course_id);
		
		return enrol;
	}
	
	@Transactional
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
	public void saveEnrol(Enrol enrol) {
		
		enrolRepository.save(enrol);
	}
	
	@Transactional
	public void deleteEnrol(int id) {
		
		enrolRepository.deleteById(id);
	}
	
}
