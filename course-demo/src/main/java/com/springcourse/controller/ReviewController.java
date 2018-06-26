package com.springcourse.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springcourse.entity.Course;
import com.springcourse.entity.Enrol;
import com.springcourse.entity.Review;
import com.springcourse.service.CourseServiceImpl;
import com.springcourse.service.EnrolServiceImpl;
import com.springcourse.service.ReviewServiceImpl;

@Controller
@RequestMapping("/review")
public class ReviewController {
	
	CourseServiceImpl courseService;
	
	ReviewServiceImpl reviewService;
	
	EnrolServiceImpl enrolService;
	
	@Autowired
	public ReviewController(CourseServiceImpl courseService, ReviewServiceImpl reviewService, EnrolServiceImpl enrolService) {
		this.courseService = courseService;
		this.reviewService = reviewService;
		this.enrolService = enrolService;
	}
	
	@GetMapping("/showReviewAddForm")
	public String addReview(@RequestParam("courseId") int courseId, Model model) {
		
		Optional<Course> course = courseService.getCourse(courseId);
		Review review = new Review();
		course.get().addReview(review);
		
		model.addAttribute("review", review);
		System.out.println(review.getCourse());
		
		return "review-form";
	}
	
	@PostMapping("/saveReview")
	public String saveReview(@ModelAttribute("review") Review review, Model model) {
		
		reviewService.addReview(review);
		
		return "redirect:/student/showMyCourses";
	}
	
	

}
