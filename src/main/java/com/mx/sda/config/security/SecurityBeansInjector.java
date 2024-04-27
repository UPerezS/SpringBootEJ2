package com.mx.sda.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mx.sda.exception.ObjectNotFoundException;
import com.mx.sda.persistencia.repository.UsuarioRepository;

@Configuration
public class SecurityBeansInjector {
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
		throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider();
		authenticationStrategy.setPasswordEncoder(passwordEncoder());
		authenticationStrategy.setUserDetailsService(userDetailsService());
		
		return authenticationStrategy;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return(username) -> {
			return userRepository.findByUsername(username)
					.orElseThrow(() -> new ObjectNotFoundException("Error: este usuario no existe: " + username));
		};
	}
}
