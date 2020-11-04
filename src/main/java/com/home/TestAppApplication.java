package com.home;

import com.home.entity.User;
import com.home.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootApplication
public class TestAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestAppApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		userRepository.getUsers().addAll(IntStream.range(1, 3).mapToObj(p -> new User(p, "User" + p)).collect(Collectors.toList()));
	}
}