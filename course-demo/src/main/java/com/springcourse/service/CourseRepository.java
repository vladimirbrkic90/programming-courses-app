package com.springcourse.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springcourse.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	
	@Query("Select c from Course c where c.title like %:title%")
	List<Course> findByTitle(@Param("title") String title);

}
