package com.psl.gems.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder pwEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.usersByUsernameQuery("select username, password, enabled from USER where username = ?")
		.authoritiesByUsernameQuery("select username, role from USER where username = ?")
		.dataSource(dataSource).passwordEncoder(pwEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/user/**").hasAuthority("member")
		.antMatchers("/employee/**").hasAuthority("librarian")
		.antMatchers("/login/**").permitAll()
		.antMatchers("/register/**").permitAll()
		.antMatchers("/logout/**").permitAll()
		.antMatchers("/CSS/**").permitAll()
		.antMatchers("/Images/**").permitAll()
		.antMatchers("/**").authenticated().and().formLogin().loginPage("/login");
		
//		http.csrf().ignoringAntMatchers("/h2-console/**");
		http.headers().frameOptions().disable();
	}
}
