package com.springcourse.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.entity.Course;
import com.springcourse.entity.Enrol;
import com.springcourse.entity.Student;

@Service
public class StudentServiceImpl {
	
	@Autowired
	UserDetailsManager userDetailsManager;
	
	private StudentRepository studentRepository;
	
	private EnrolRepository enrolRepository;
	
	private CourseRepository courseRepository;
	
	@Autowired
	public StudentServiceImpl(StudentRepository studentRepository, EnrolRepository enrolRepository, CourseRepository courseRepository) {
		this.studentRepository = studentRepository;
		this.enrolRepository = enrolRepository;
		this.courseRepository = courseRepository;
	}
	
	@Transactional
	public List<Student> getStudents() {
		
		List<Student> students = new ArrayList<>();
		
		studentRepository.findAll().forEach((Student s) -> students.add(s));
		
		return students;
	}
	
	@Transactional
	public Optional<Student> getStudent(int id) {
		
		Optional<Student> student = studentRepository.findById(id);
		
		return student;
	}
	
	@Transactional
	public Optional<Student> getStudentByEmail(String email) {
		
		Optional<Student> student = studentRepository.findByEmail(email);
		
		return student;
	}
	
	@Transactional
	public Optional<Student> getStudentByUsername(String username) {
		
		Optional<Student> student = studentRepository.findByUsername(username);
		
		return student;
	}
	
	@Transactional
	public Optional<Student> getStudentByUsernameAndPassword(String username, String password) {
		
		Optional<Student> student = studentRepository.findByUsernameAndPassword(username, password);
		
		return student;
	}
	
	@Transactional
	public void saveStudent(Student student) {
		
		studentRepository.save(student);
	}
	
	@Transactional
	public void deleteStudent(int id) {
		//Optional<Student> student = getStudent(id);
		
		Optional<Student> student = studentRepository.findById(id);
		String username = student.get().getUsername();
		
		studentRepository.deleteById(id);
		userDetailsManager.deleteUser(username);
	}
	
	public List<Course> recommendation(int id) {
		
		Optional<Student> studentX = studentRepository.findById(id);
		System.out.println("=========>>>>>>>"+studentX);
	//	List<Student> students = (List<Student>) studentRepository.findAll();
		List<Student> students = new ArrayList<>();
		studentRepository.findAll().forEach((Student s) -> students.add(s));
		
		Map<Integer, Double> jaccard = new HashMap<Integer, Double>();
		double jaccardCoefficient = 0;
		for (Student student : students) {
			jaccardCoefficient = jaccardCoefficient(studentX.get(), student);
			if (jaccardCoefficient != 1 && jaccardCoefficient != 0) {
				jaccard.put(student.getId(), jaccardCoefficient);
			}
		}
		
		jaccard = sortByValue(jaccard);
		List<Integer> similarStudentIds = new ArrayList<>();
		Set<Integer> key = jaccard.keySet();
		for (int i = 0; i < key.size(); i++) {
			similarStudentIds.add((Integer) key.toArray()[i]);
		}
		
		List<Course> courses = new ArrayList<>();
		for(int i = 0; i < similarStudentIds.size(); i++) {
			List<Enrol> enrols = enrolRepository.findByStudentId(similarStudentIds.get(i));
			for(int j = 0; j < enrols.size(); j++) {
				Integer idCourse = enrolRepository.courseId(enrols.get(j).getId());
				Optional<Course> course = courseRepository.findById(idCourse);
				courses.add(course.get());
			}
		}
		
		List<Course> coursesForStudent = enrolRepository.findCourses(studentX.get().getId());
		List<Course> coursePrint = new ArrayList<>();
		for(Course c : courses) {
			if(!coursesForStudent.contains(c)) {
				coursePrint.add(c);
			}
		}
		
		Set<Course> hashSet = new HashSet<>();
		hashSet.addAll(coursePrint);
		coursePrint.clear();
		coursePrint.addAll(hashSet);
		if(coursePrint.size() >= 5) {
			coursePrint = coursePrint.subList(0, 5);			
		}
		
		return coursePrint;
	}
	
	public Double jaccardCoefficient(Student studentX, Student studentY) {
		
		List<Enrol> enrolsX = enrolRepository.findByStudentId(studentX.getId());
		List<Enrol> enrolsY = enrolRepository.findByStudentId(studentY.getId());
		
		List<Course> coursesX = new ArrayList<>();
		for(Enrol e : enrolsX) {
			coursesX.add(e.getCourse());
		}
		
		List<Course> coursesY = new ArrayList<>();
		for(Enrol e : enrolsY) {
			coursesY.add(e.getCourse());
		}
		
		List<Course> intersection = intersection(coursesX, coursesY);
		List<Course> union = union(coursesX, coursesY);
		double jaccardCoefficient = (double)intersection.size()/(double)union.size();
		return jaccardCoefficient;
	}
	
	public <T> List<T> union(List<T> list1, List<T> list2) {
		Set<T> set = new HashSet<T>();
		set.addAll(list1);
		set.addAll(list2);
		return new ArrayList<T>(set);
	}
	
	public <T> List<T> intersection(List<T> list1, List<T> list2) {
		List<T> list = new ArrayList<T>();
		for(T t: list1) {
			if(list2.contains(t)) { 
				list.add(t);
			}
		}
		return list;
	}
	
	private static <K, V> Map<K, V> sortByValue(Map<K, V> map) {
		List<Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Object>() {
			@SuppressWarnings("unchecked")
			public int compare(Object o1, Object o2) {
				return ((Comparable<V>) ((Map.Entry<K, V>) (o2)).getValue())
						.compareTo(((Map.Entry<K, V>) (o1)).getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Iterator<Entry<K, V>> it = list.iterator(); it.hasNext();) {
			Map.Entry<K, V> entry = (Map.Entry<K, V>) it.next();
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}

