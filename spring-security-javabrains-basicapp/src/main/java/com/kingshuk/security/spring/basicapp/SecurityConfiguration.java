package com.kingshuk.security.spring.basicapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//First we tell it the type of authentication we need
		// @formatter:off
		auth.inMemoryAuthentication()
			.withUser("rishi2616")
			.password("Iofdtiger#16")
			.roles("USER")
			.and()
			.withUser("rishi1626")
			.password("deekshab13")
			.roles("ADMIN");
			// @formatter:on
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin").hasAnyRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER, ADMIN")
			.antMatchers("/").permitAll()
			.and().formLogin();
	}



	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
