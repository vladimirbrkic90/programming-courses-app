package com.springcourse;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;  
	
	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http
            .authorizeRequests()
            	.antMatchers("/").permitAll()
            		.antMatchers("/resources/**", "/enrol/course/list", "/enrol/showCourseEnrolForm", "/enrol/saveEnrol", "/security/showRegistrationForm2",
            				"/security/processRegistrationForm2", "/security/contact", "/security/about", "/enrol/course/searchCourse", "/course/showCourseReviews").permitAll()
            		.antMatchers("/instructor/showFormForAdd", "/instructor/showFormForUpdate", "/instructor/deleteInstructor", "/instructor/list").hasAuthority("ADMIN")
            		.antMatchers("/course/showFormForAdd", "/course/showFormForUpdate", "/course/deleteCourse").hasAuthority("ADMIN")
            		.antMatchers("/student/deleteStudent").hasAuthority("ADMIN")
            		.antMatchers("/enrol/deleteEnrol").hasAuthority("ADMIN")
            		.antMatchers("/student/showCourseVideo", "/student/showMyCourses").hasAuthority("STUDENT")
                	.anyRequest().authenticated()
                	.and()
                .formLogin()
                	.successHandler(successHandler)
                	.loginPage("/security/login")
                //	.defaultSuccessUrl("/instructor/list")
                	.loginProcessingUrl("/authenticateTheUser")
                	.permitAll()
                	.and()
                .logout()
                	.permitAll()
                	.and()
                .exceptionHandling().accessDeniedPage("/security/access-denied")
                	.and()
                	.csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
    		
    			auth.
    				jdbcAuthentication()
    				.dataSource(dataSource)
    				.passwordEncoder(bCryptPasswordEncoder); 
    				 
    	
    	
   /*     auth.
        		inMemoryAuthentication()
       		.withUser("admin").password("1234").roles("USER", "ADMIN")
       		.and()
       		.withUser("user").password("123").roles("USER");  */
        			
    }
    
    @Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/students", "/students/**")
	       .antMatchers("/instructors", "/instructors/**")
	       .antMatchers("/courses", "/courses/**");
	}
    
    @Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(dataSource);
		
		return jdbcUserDetailsManager; 
	}
    
    
    
    
    
}
