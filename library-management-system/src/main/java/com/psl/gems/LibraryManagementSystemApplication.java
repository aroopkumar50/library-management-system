package com.psl.gems;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.psl.gems.dao.UserRepository;
import com.psl.gems.model.User;

@SpringBootApplication
public class LibraryManagementSystemApplication {
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}
	
	@Bean
	InitializingBean initialCode() {
		return () -> {
			User user = new User();
			user.setName("Admin");
			user.setUsername("admin");
			user.setPassword("user");
			user.setRole("librarian");
			userRepository.save(user);
			for (User usr : userRepository.findAll()) {
				System.out.println(usr);
			}
		};
	}

}
