package com.springcourse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springcourse.entity.Course;
import com.springcourse.entity.Student;
import com.springcourse.service.EnrolServiceImpl;
import com.springcourse.service.StudentServiceImpl;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Controller
@RequestMapping("/security")
public class SecurityController {
	
	public static final String ACCOUNT_SID = "AC4011e841817abb988b0392f7ff13c932";
	public static final String AUTH_TOKEN = "78b7bc1ae5f18474b4de9be74b2f93e9";
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private EnrolServiceImpl enrolService;
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		
		return "access-denied";
	}
	
	@GetMapping("/contact")
	public String contactPage() {
		
		return "contact";
	}
	
	@GetMapping("/about")
	public String aboutPage() {
		
		return "about";
	}
	
	@GetMapping("/showRegistrationForm2")
	public String showRegistrationForm(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm2")
	public String processRegistrationForm(@ModelAttribute("student") Student student, Model model) {
		
		String username = student.getUsername();
		String password = bCryptPasswordEncoder.encode(student.getPassword());
		student.setPassword(password);
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("STUDENT");
		
		User tempUser = new User(username, password, authorities);
		userDetailsManager.createUser(tempUser);
		
		List<Course> courses = enrolService.getCourses();
		model.addAttribute("courses", courses);
		model.addAttribute("success", "You have successfully registered!");
		studentService.saveStudent(student);
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
			    new PhoneNumber(student.getPhoneNumber()),
			    new PhoneNumber("+15052952803"),
			    "Uspesna registracija, vas username je: " + student.getUsername())
			.create();
		
		return "index-courses";
	}

}
