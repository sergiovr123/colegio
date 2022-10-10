package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class ColegioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColegioApplication.class, args);
	}
	
	public enum Rol {

	    ROLE_ADMIN,
	    ROLE_EMPLEADO

	}

//	@Bean
//	SecurityFilterChain web(HttpSecurity http) throws Exception {
//		http
//			// ...
//			.authorizeHttpRequests(authorize -> authorize                               
//				.mvcMatchers("/create**", "/signup", "/about").permitAll()        
//				.mvcMatchers("/admin/**").hasRole("ADMIN")                              
//				.anyRequest().denyAll()                                               
//			);
//
//		return http.build();
//	}
}
