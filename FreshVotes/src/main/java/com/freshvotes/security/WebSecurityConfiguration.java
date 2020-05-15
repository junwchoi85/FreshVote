package com.freshvotes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		auth.inMemoryAuthentication()
			.passwordEncoder(getPasswordEncoder())
			.withUser("jun@gmail.com")
			.password(getPasswordEncoder().encode("1234"))
			.roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		http.csrf().disable()
			.authorizeRequests()
			  .antMatchers("/").permitAll()
			  .anyRequest().hasRole("USER").and()
			.formLogin().loginPage("/login")
			  .permitAll().and()
			.logout()
			  .logoutUrl("/logout")
			  .permitAll();
		
	}
}
