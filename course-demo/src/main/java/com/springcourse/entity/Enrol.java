package com.springcourse.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="enrol")
public class Enrol {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@JsonIgnore
	private int id;
	
	@Column(name="enrol_date")
	private String date;
	
	@ManyToOne(cascade= {CascadeType.MERGE,
				CascadeType.DETACH, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name="student_id")
	@Valid
	@JsonIgnore
	private Student student;
	
	@ManyToOne(cascade= {CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinColumn(name="course_id")
	@JsonIgnore
	private Course course;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="review_id")
	@JsonIgnore
	private Review review;
	
	public Enrol() {
		
	}

	public Enrol(String date, Student student, Course course, Review review) {
		this.date = date;
		this.student = student;
		this.course = course;
		this.review = review;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "Enrol [id=" + id + ", date=" + date + ", student=" + student + ", course=" + course + ", review="
				+ review + "]";
	}
	

}
