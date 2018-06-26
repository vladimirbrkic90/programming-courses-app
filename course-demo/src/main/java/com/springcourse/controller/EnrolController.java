package com.springcourse.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springcourse.entity.Course;
import com.springcourse.entity.Enrol;
import com.springcourse.entity.Student;
import com.springcourse.service.CourseServiceImpl;
import com.springcourse.service.EnrolServiceImpl;
import com.springcourse.service.StudentServiceImpl;

@Controller
@RequestMapping("/enrol")
public class EnrolController {

	private EnrolServiceImpl enrolService;
	
	private StudentServiceImpl studentService;
	
	private CourseServiceImpl courseService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	@Autowired
	public EnrolController(EnrolServiceImpl enrolService, StudentServiceImpl studentService, CourseServiceImpl courseService) {
		this.enrolService = enrolService;
		this.studentService = studentService;
		this.courseService = courseService;
	}
	
	@GetMapping("/list")
	public String getEnrols(Model model) {
		
		List<Enrol> enrols = enrolService.getEnrols();
		model.addAttribute("enrols", enrols);
		
		return "list-enrols";
	}
	
	@GetMapping("course/list")
	public String getCourses(Model model) {
		
		List<Course> courses = enrolService.getCourses();
		model.addAttribute("courses", courses);
		
		return "index-courses";
	}
	
	@PostMapping("course/searchCourse")
	public String searchCourse(@RequestParam("theSearchName") String theSearchName, Model model) {
		
		List<Course> courses = courseService.getCoursesByTitle(theSearchName);
		model.addAttribute("courses", courses);
		
		if(courses.isEmpty()) {
			model.addAttribute("error2", "There is no such course!");
			List<Course> courses1 = enrolService.getCourses();
			model.addAttribute("courses", courses1);
			return "index-courses";
		}
		
		return "index-courses";
	}
	
	@GetMapping("/showCourseEnrolForm")
	public String addEnrol(@RequestParam("courseId") int courseId, Model model) {
		
		Optional<Course> course = enrolService.getCourse(courseId);
		Enrol enrol = new Enrol();
		course.get().addEnrol(enrol);
		
	//	Student student = new Student();
		
	/*	Optional<Student> s = studentService.getStudentByEmail(student.getEmail());
		//System.out.println(s.get().getEmail());
		
		if(s.isPresent()) {
			Optional<Student> ss = studentService.getStudent(s.get().getId());
			enrol.setStudent(ss.get());
		}
		else {
			enrol.setStudent(student);
		} */
		
		//enrol.setStudent(student);
		
		model.addAttribute("enrol", enrol);
		System.out.println(enrol.getCourse());
		
			
		return "enrol-form2";
	}
	
	@PostMapping("/saveEnrol")
	public String saveEnrol(@Valid @ModelAttribute("enrol") Enrol enrol,
							BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "enrol-form2";
		}
		else {
		//	Optional<Student> s = studentService.getStudentByEmail(enrol.getStudent().getEmail());
			boolean username = userDetailsManager.userExists(enrol.getStudent().getUsername());
			
			if(!username) {
				model.addAttribute("error", "Invalid username or password");
				return "enrol-form2";
			}
			
			Optional<Student> s = studentService.getStudentByUsername(enrol.getStudent().getUsername());
		//	String encodedPassword = bCryptPasswordEncoder.encode(enrol.getStudent().getPassword());
			boolean password = bCryptPasswordEncoder.matches(enrol.getStudent().getPassword(), s.get().getPassword());
		//	Optional<Student> s = studentService.getStudentByUsernameAndPassword(enrol.getStudent().getUsername(), enrol.getStudent().getPassword());
		//	Optional<Student> s = studentService.getStudentByUsername(enrol.getStudent().getUsername());
			
			if(username && password) {
				//Optional<Student> ss = studentService.getStudent(s.get().getId());
				Optional<Student> ss = studentService.getStudentByUsername(enrol.getStudent().getUsername());
				enrol.setStudent(ss.get());
			}
			else {
				model.addAttribute("error", "Invalid username or password");
				return "enrol-form2";
			}
			
			Optional<Enrol> enroll = enrolService.getEnrolByStudentAndCourse(enrol.getStudent().getId(), enrol.getCourse().getId());
			if(enroll.isPresent()) {
				model.addAttribute("course", enroll.get().getCourse().getTitle());
				List<Course> courses = enrolService.getCourses();
				model.addAttribute("courses", courses);
				model.addAttribute("error", "You already enrolled that course!");
				
				return "index-courses";
			}
			
			
			//String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String timeStamp = now.format(formatter);
			
			enrol.setDate(timeStamp);
			
			System.out.println(enrol.getCourse());
			System.out.println(enrol.getStudent()); 
			
		/*	Course c = enrol.getCourse();
			Student s1 = enrol.getStudent();
			c.addStudent(s1); */
			
			Optional<Course> c = enrolService.getCourse(enrol.getCourse().getId());
			List<Course> courses = enrolService.getCourses();
			model.addAttribute("courses", courses);
			model.addAttribute("coursee", c.get().getTitle());
			enrolService.saveEnrol(enrol);
			
			return "index-courses";
		} 
	
	} 
	
	@GetMapping("/deleteEnrol")
	public String deleteEnrol(@RequestParam("enrolId") int id) {

		enrolService.deleteEnrol(id);
		
		return "redirect:/enrol/list";
	}
	
}

















