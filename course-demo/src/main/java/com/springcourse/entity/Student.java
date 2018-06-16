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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	//@NotNull(message="is required!")
	//@Size(min=1, message="is required!")
	@Column(name="last_name")
	private String lastName;
	
	//@NotNull(message="is required!")
	//@Size(min=1, message="is required!")
	@Column(name="email")
	private String email;
	
	@Transient
	private String phoneNumber;
	
	@NotNull(message="is required!")
	@Size(min=1, message="is required!")
	@Column(name="username")
	private String username;
	
	@NotNull(message="is required!")
	@Size(min=1, message="is required!")
	@Column(name="password") 
	private String password;
	
	@ManyToMany(fetch=FetchType.LAZY, 
			 cascade= {CascadeType.MERGE,
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(name="course_student",
			   joinColumns=@JoinColumn(name="student_id"),
			   inverseJoinColumns=@JoinColumn(name="course_id")
			  )
	@JsonIgnore
	private List<Course> courses;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="student", 
			   cascade= CascadeType.ALL)
	@JsonIgnore
	private List<Enrol> enrols;
	
	public Student() {
		
	}

	public Student(String username, String firstName, String lastName, String email, String password) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public List<Enrol> getEnrols() {
		return enrols;
	}

	public void setEnrols(List<Enrol> enrols) {
		this.enrols = enrols;
	}
	
	// add a convinience method
	public void addEnrol(Enrol theEnrol) {
			
		if(enrols == null) {
			enrols = new ArrayList<>();
		}
			
		enrols.add(theEnrol);
		theEnrol.setStudent(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
	
}









