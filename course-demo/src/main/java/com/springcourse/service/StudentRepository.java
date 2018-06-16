package com.springcourse.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springcourse.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	
//	Optional<Student> findByEmail(String email);
	
	@Query("SELECT s FROM Student s WHERE s.email = :email")
	Optional<Student> findByEmail(@Param("email") String email);
	
	@Query("SELECT s FROM Student s WHERE s.username = :username")
	Optional<Student> findByUsername(@Param("username") String username);
	
	@Query("SELECT s FROM Student s WHERE s.username = :username AND s.password = :password")
	Optional<Student> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
