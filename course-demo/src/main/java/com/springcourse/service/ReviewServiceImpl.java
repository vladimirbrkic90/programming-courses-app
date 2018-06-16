package com.springcourse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.entity.Review;

@Service
public class ReviewServiceImpl {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Transactional
	public List<Review> getReviews(int id) {
		
		List<Review> list = reviewRepository.findReviewsByCourseId(id);
		
		return list;
	}
	
	@Transactional
	public void addReview(Review review) {
		
		reviewRepository.save(review);
	}

}
