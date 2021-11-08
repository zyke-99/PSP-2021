package com.zyke.libraryrest.service;

import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.model.User;

import java.util.List;

public interface UserService {

    public User registerUser(UserRegisterDto userRegisterDto);

    public User getUserById(long id);

    public List<User> getAllUsers();

}
