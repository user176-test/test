package com.home.controllers;

import com.home.entity.User;
import com.home.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final UserRepository userRepository;

    @GetMapping("get/{userId}")
    public User get(@PathVariable long userId) {
        return userRepository.findById(userId).orElse(new User(0, ""));
    }

    @GetMapping("list")
    public List<User> list() {
        return userRepository.getUsers();
    }
}