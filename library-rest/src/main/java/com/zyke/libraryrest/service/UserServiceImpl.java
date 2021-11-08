package com.zyke.libraryrest.service;

import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.exception.UserNotFoundException;
import com.zyke.libraryrest.exception.UserRegisterException;
import com.zyke.libraryrest.exception.ValidationException;
import com.zyke.libraryrest.model.User;
import com.zyke.libraryrest.repository.UserRepository;
import com.zyke.libraryrest.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier("${user-validator-implementation}")
    UserValidator userValidator;

    public User registerUser(UserRegisterDto userRegisterDto) {
        try {
            userRegisterDto = userValidator.validate(userRegisterDto);
        } catch (ValidationException exception) {
            throw new UserRegisterException(exception.getMessage());
        }
        User user = new User(userRegisterDto.getEmail(),
                userRegisterDto.getPhoneNumber(),
                userRegisterDto.getPassword(),
                userRegisterDto.getName(),
                userRegisterDto.getSurname(),
                userRegisterDto.getAddress());
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        try {
            return userRepository.findById(id).get();
        } catch (NoSuchElementException ex) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

}
