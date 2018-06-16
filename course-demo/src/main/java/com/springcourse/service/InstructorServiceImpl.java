package com.springcourse.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.entity.Instructor;

@Service
public class InstructorServiceImpl {
	
	private InstructorRepository instructorRepository;
	
	@Autowired
	public InstructorServiceImpl(InstructorRepository instructorRepository) {
		this.instructorRepository = instructorRepository;
	}
	
	public List<Instructor> getInstructors() {
		
		List<Instructor> instructors = new ArrayList<>();
		instructorRepository.findAll().forEach((Instructor i) -> instructors.add(i));
		
		return instructors;
	}
	
	@Transactional
	public Optional<Instructor> getInstructor(int id) {
		
		Optional<Instructor> instructor = instructorRepository.findById(id);
		
		return instructor;
	}
	
	@Transactional
	public void saveInstructor(Instructor theInstructor) {
		
		instructorRepository.save(theInstructor);
		
	}
	
	@Transactional
	public void deleteInstructor(Integer id) {
		
		instructorRepository.deleteById(id);
		
	}

}
