package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.model.Usuario;
import com.example.demo.model.UsuarioService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired UsuarioService service;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/").permitAll()
				.mvcMatchers("/admin/**").hasRole("ADMIN")
				.mvcMatchers("/empleado/**").hasRole("EMPLEADO") 
				.anyRequest(). authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		 Collection<UserDetails> details = new ArrayList<>();
		 for (Usuario usuario : service.getUsuarios()) {
			
			 @SuppressWarnings("deprecation")
				UserDetails user =
					 User.withDefaultPasswordEncoder()
						.username(usuario.getUser_name())
						.password(usuario.getPassword())
						.roles(usuario.getTipo())
						.build();
			 
			 details.add(user);
			
		}
		 return new InMemoryUserDetailsManager(details);

		
	}
}