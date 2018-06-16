package com.springcourse.controller;

import java.util.List;
import java.util.Optional;

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

import com.springcourse.entity.Course;
import com.springcourse.entity.Instructor;
import com.springcourse.entity.InstructorDetail;
import com.springcourse.service.InstructorServiceImpl;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	private InstructorServiceImpl instructorService;
	
	@Autowired
	public InstructorController(InstructorServiceImpl instructorService) {
		this.instructorService = instructorService;
	}
	
	@GetMapping("/list")
	public String listInstructors(Model model) {
		
		List<Instructor> theInstructors = instructorService.getInstructors();
		model.addAttribute("instructors", theInstructors);
		
		return "list-instructors";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Instructor theInstructor = new Instructor();
		InstructorDetail instructorDetail = new InstructorDetail();
		
		model.addAttribute("instructor", theInstructor);
		//model.addAttribute("instructorDetail", instructorDetail);
		
		theInstructor.setInstructorDetail(instructorDetail);
		
		return "instructor-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateInstructor(@RequestParam("instructorId") int id, Model model) {
		
		Optional<Instructor> instructor = instructorService.getInstructor(id);
		
		model.addAttribute("instructor", instructor.get());
		
		return "instructor-form";
	}
	
	@GetMapping("/showInstructorDetails")
	public String showInstructorDetails(@RequestParam("instructorId") int id, Model model) {
		
		Optional<Instructor> instructor = instructorService.getInstructor(id);
		
		model.addAttribute("instructor", instructor.get());
		model.addAttribute("instructorDetails", instructor.get().getInstructorDetail());
		
		return "instructorDetails";
	}
	
	@PostMapping("/saveInstructor")
	public String saveInstructor(@Valid @ModelAttribute("instructor") Instructor theInstructor,
								BindingResult bindingResult) {
	
		if(bindingResult.hasErrors()) {
			return "instructor-form";
		}
		else {
			instructorService.saveInstructor(theInstructor);
			return "redirect:/instructor/list";
		}
		
	}
	
	@GetMapping("/deleteInstructor")
	public String deleteInstructor(@RequestParam("instructorId") int id) {
		
		Optional<Instructor> i = instructorService.getInstructor(id);
		
		List<Course> course = i.get().getCourses();
		/*for(Course c : course) {
			c.setInstructor(null);
		}*/
		
		course.forEach(c -> c.setInstructor(null));
		
		instructorService.deleteInstructor(id);
		
		return "redirect:/instructor/list";
	}

}
