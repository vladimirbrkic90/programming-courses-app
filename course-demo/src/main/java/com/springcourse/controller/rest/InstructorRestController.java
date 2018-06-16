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

import com.springcourse.entity.Instructor;
import com.springcourse.entity.InstructorDetail;
import com.springcourse.service.InstructorDetailRepository;
import com.springcourse.service.InstructorServiceImpl;

@RestController
public class InstructorRestController {
	
	private InstructorServiceImpl instructorService;
	private InstructorDetailRepository instructorDetailRepository;
	
	@Autowired
	public InstructorRestController(InstructorServiceImpl instructorService, InstructorDetailRepository instructorDetailRepository) {
		this.instructorService = instructorService;
		this.instructorDetailRepository = instructorDetailRepository;
	}
	
	@GetMapping("/instructors")
	public ResponseEntity<List<Instructor>> getInstructors(){
		
		List<Instructor> instructors = instructorService.getInstructors();
		
		return new ResponseEntity<List<Instructor>>(instructors, HttpStatus.OK);
	}
	
	@GetMapping("/instructors/{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") int id) {
		
		Optional<Instructor> instructor = instructorService.getInstructor(id);
		if(instructor.isPresent()) {
			return new ResponseEntity<Instructor>(instructor.get(), HttpStatus.OK);	
		}
		else {
			return new ResponseEntity<Instructor>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/instructors")
	public ResponseEntity<Void> createInstructor(@RequestBody Instructor instructor) {
		
		instructorService.saveInstructor(instructor);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/instructors/{id}")
	public ResponseEntity<Instructor> deleteInstructor(@PathVariable("id") int id) {
		
		instructorService.deleteInstructor(id);
		
		return new ResponseEntity<Instructor>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/instructors/{id}")
	public ResponseEntity <Optional<Instructor>> updateInstructor(@PathVariable("id") int id, @RequestBody Instructor instructor) {
		
		Optional<Instructor> theInstructor = instructorService.getInstructor(id);
		
		theInstructor.get().setFirstName(instructor.getFirstName());
		theInstructor.get().setLastName(instructor.getLastName());
		theInstructor.get().setEmail(instructor.getEmail());
		
	/*	theInstructor.get().getInstructorDetail().setYoutubeChannel(instructor.getInstructorDetail().getYoutubeChannel());
		theInstructor.get().getInstructorDetail().setHobby(instructor.getInstructorDetail().getHobby()); */
		
		instructorService.saveInstructor(theInstructor.get());
		
		return new ResponseEntity <Optional<Instructor>>(theInstructor, HttpStatus.OK);
	}
	
	@GetMapping("/instructors/{id}/details")
	public ResponseEntity<InstructorDetail> showInstructorDetails(@PathVariable("id") int id) {
		
		Optional<Instructor> instructor = instructorService.getInstructor(id);
		
		if(instructor.isPresent()) {
			return new ResponseEntity<InstructorDetail>(instructor.get().getInstructorDetail(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<InstructorDetail>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/instructors/{id}/createDetails")
	public ResponseEntity<Void> createDetails(@PathVariable("id") int id, @RequestBody InstructorDetail details) {
		
		Optional<Instructor> instructor = instructorService.getInstructor(id);
		
		//details.setInstructor(instructor.get());
		instructor.get().setInstructorDetail(details);
		instructorDetailRepository.save(details);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}


















