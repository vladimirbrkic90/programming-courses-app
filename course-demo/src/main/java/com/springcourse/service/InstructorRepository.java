package com.springcourse.service;

import org.springframework.data.repository.CrudRepository;

import com.springcourse.entity.Instructor;

public interface InstructorRepository extends CrudRepository<Instructor, Integer>{

}
