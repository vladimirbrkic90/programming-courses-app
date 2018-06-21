package com.springcourse.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springcourse.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
//	List<Review> findReviewsByCourseId(int id);

}
