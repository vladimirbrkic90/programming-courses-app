package com.springcourse.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springcourse.entity.Course;
import com.springcourse.entity.Enrol;
import com.springcourse.entity.Student;
import com.springcourse.service.CourseServiceImpl;
import com.springcourse.service.EnrolServiceImpl;
import com.springcourse.service.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	private StudentServiceImpl studentService;
	
	private EnrolServiceImpl enrolService;
	
	private CourseServiceImpl courseService;
	
	@Autowired
	public StudentController(StudentServiceImpl studentService, EnrolServiceImpl enrolService, CourseServiceImpl courseService) {
		this.studentService = studentService;
		this.enrolService = enrolService;
		this.courseService = courseService;
	}
	
	@GetMapping("/list")
	public String getStudents(Authentication auth, Model model) {
		
		List<Student> students = studentService.getStudents();
		model.addAttribute("students", students);
		
	//	System.out.println(auth.getPrincipal());
		
		return "list-students";
	}
	
	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("studentId") int id) {
		
		studentService.deleteStudent(id);
		
		return "redirect:/student/list";
	}
	
	@GetMapping("/showCourses")
	public String showStudentCourses(@RequestParam("studentId") int id, Model model) {
		
		Optional<Student> student = studentService.getStudent(id);
		model.addAttribute("student", student.get());
		
		List<Enrol> enrols = enrolService.getEnrolByStudentId(id);
		model.addAttribute("enrols", enrols);
		
	//	Course course = enrol.get().getCourse();
	//.addAttribute("course", course);	
		
		return "studentCourses";
	}
	
	@GetMapping("/showMyCourses")
	public String showMyCourses(Authentication auth, Model model) {
		
		Optional<Student> student = studentService.getStudentByUsername(auth.getName());
		
		List<Enrol> enrols = enrolService.getEnrolByStudentId(student.get().getId());
		model.addAttribute("enrols", enrols);
		System.out.println(student);
		List<Course> courses = studentService.recommendation(student.get().getId());
		System.out.println(courses);
		model.addAttribute("courses", courses);
	
		return "index-student";
	}
	
	@GetMapping("/showCourseVideo")
	public String showCourseVideo(@RequestParam("courseId") int id, Model model) {
		
		Optional<Course> course = courseService.getCourse(id);
		model.addAttribute("course", course.get());
		model.addAttribute("courseVideo", course.get().getVideo());
		model.addAttribute("courseTest", course.get().getTest());
		
		if(course.get().getId() == 25) {
			return "index";
		}
		
		return "course-video";
	}


}
