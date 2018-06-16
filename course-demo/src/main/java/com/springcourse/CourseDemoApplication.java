package com.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CourseDemoApplication {
	
	 /*	@Bean(name = "dataSource")
	    public DriverManagerDataSource dataSource() {
	        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/student-course-mvc?useSSL=false");
	        driverManagerDataSource.setUsername("hbstudent");
	        driverManagerDataSource.setPassword("hbstudent");
	        return driverManagerDataSource;
	    } */
	 	
	 	 @Bean
	     public BCryptPasswordEncoder passwordEncoder() {
	         BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	         return bCryptPasswordEncoder;
	     } 

	public static void main(String[] args) {
		SpringApplication.run(CourseDemoApplication.class, args);
	}
}
