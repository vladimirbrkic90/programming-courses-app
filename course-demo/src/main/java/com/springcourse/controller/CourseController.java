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
import com.springcourse.service.CourseServiceImpl;
import com.springcourse.service.InstructorServiceImpl;

@Controller
@RequestMapping("/course")
public class CourseController {

	private CourseServiceImpl courseService;
	
	private InstructorServiceImpl instructorService;
	
	@Autowired
	public CourseController(CourseServiceImpl courseService, InstructorServiceImpl instructorService) {
		this.courseService = courseService;
		this.instructorService = instructorService;
	}
	
	@GetMapping("/list")
	public String listCourses(Model model) {
		
		List<Course> courses = courseService.getCourses();

		model.addAttribute("courses", courses);
		
		return "list-courses";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Course theCourse = new Course();
		List<Instructor> instructors = instructorService.getInstructors();
		
		model.addAttribute("course", theCourse);
		model.addAttribute("instructors", instructors);
		
		
		return "course-form";
	}
	
	@PostMapping("/saveCourse")
	public String saveCourse(@Valid @ModelAttribute("course") Course theCourse, BindingResult bindingResult,
							@RequestParam("instructor") Instructor i) {
		
		Optional<Instructor> ins = instructorService.getInstructor(i.getId());
		//theCourse.setInstructor(ins.get());
		if(bindingResult.hasErrors()) {
			return "course-form";
		}
		else {
		//	Optional<Instructor> ins = instructorService.getInstructor(i.getId());
			theCourse.setInstructor(ins.get());
			
			courseService.saveCourse(theCourse);
			
			return "redirect:/course/list";
		}
		
	}
	
	@GetMapping("/showCourseReviews")
	public String showCourseReviews(@RequestParam("courseId") int id, Model model) {
		
		Optional<Course> course = courseService.getCourse(id);
		
		model.addAttribute("course", course.get());
		model.addAttribute("courseReviews", course.get().getReviews());
		
		return "course-reviews";
	}
	
	@GetMapping("/deleteCourse")
	public String deleteCourse(@RequestParam("courseId") int id) {
		
		courseService.deleteCourse(id);
		
		return "redirect:/course/list";
	}
	
	@GetMapping("showFormForUpdate")
	public String updateCourse(@RequestParam("courseId") int id,
							    Model model) {
		
		Optional<Course> course = courseService.getCourse(id);
		
		
		model.addAttribute("course", course.get());
		
		
		model.addAttribute("instructors", instructorService.getInstructors() );
		
		return "course-form";
	}
	
}




