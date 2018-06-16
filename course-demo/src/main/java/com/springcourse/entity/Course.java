package com.springcourse.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="is required!")
	@Size(min=1, message="is required!")
	@Column(name="title")
	private String title;
	
//	@NotNull(message="is required!")
//	@Size(min=1, message="is required!")
	@Column(name="price")
	private String price;
	
	@Column(name="video")
	private String video;
	
	@Column(name="image")
	private String image;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="instructor_id")
	@JsonIgnore
	private Instructor instructor;
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	@JsonIgnore
	private List<Review> reviews;
	
	@ManyToMany(fetch=FetchType.LAZY, 
			 cascade= {CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="course_student",
			   joinColumns=@JoinColumn(name="course_id"),
			   inverseJoinColumns=@JoinColumn(name="student_id")
			  )
	@JsonIgnore 
	private List<Student> students;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="course", 
			   cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Enrol> enrols;
	
	public Course() {
		
	}

	public List<Enrol> getEnrol() {
		return enrols;
	}

	public void setEnrol(List<Enrol> enrols) {
		this.enrols = enrols;
	}
	
	// add a convinience method
	public void addEnrol(Enrol theEnrol) {
		
		if(enrols == null) {
			enrols = new ArrayList<>();
		}
		
		enrols.add(theEnrol);
		theEnrol.setCourse(this);
	}

	public Course(String title, String price) {
		this.title = title;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	// add a convinience method
	public void addReview(Review theReview) {
		
		if(reviews == null) {
			reviews = new ArrayList<>();
		}
		
		reviews.add(theReview);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	// add a convinience method
	public void addStudent(Student theStudent) {
		
		if(students == null) {
			students = new ArrayList<>();
		}
		
		students.add(theStudent);
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

}
