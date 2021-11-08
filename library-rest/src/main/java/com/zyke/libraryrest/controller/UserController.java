package com.zyke.libraryrest.controller;

import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.model.User;
import com.zyke.libraryrest.service.UserService;
import com.zyke.libraryrest.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${api-path}/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public User registerUser(@RequestBody UserRegisterDto user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
