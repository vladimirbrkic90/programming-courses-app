package com.springcourse.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springcourse.entity.Course;
import com.springcourse.entity.Enrol;

public interface EnrolRepository extends CrudRepository<Enrol, Integer> {
	
	@Query("Select e from Enrol e JOIN e.student s WHERE s.id =:id")
	List<Enrol> findByStudentId(@Param("id") int id);
	
	//List<Enrol> findByStudentId(int id);
	
	//@Query("Select e from Enrol e JOIN e.student s ON s.id =:student_id JOIN e.course c ON c.id =:course_id")
	@Query("Select e from Enrol e JOIN e.student s JOIN e.course c WHERE s.id =:student_id AND c.id =:course_id")
	Optional<Enrol> findEnrol(@Param("student_id") int student_id, @Param("course_id") int course_id);
	
	@Query("Select e.course.id from Enrol e WHERE e.id =:id")
	Integer courseId(@Param("id") Integer id);
	
	@Query("Select e.course from Enrol e WHERE e.student.id =:id")
	List<Course> findCourses(@Param("id") int id);

}
