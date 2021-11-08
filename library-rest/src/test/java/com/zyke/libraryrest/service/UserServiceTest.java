package com.zyke.libraryrest.service;


import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.exception.UserNotFoundException;
import com.zyke.libraryrest.exception.UserRegisterException;
import com.zyke.libraryrest.exception.ValidationException;
import com.zyke.libraryrest.model.User;
import com.zyke.libraryrest.repository.UserRepository;
import com.zyke.libraryrest.util.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserValidator userValidator;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void RegisterUser_CorrectDataPassed_ShouldNotThrowException() throws ValidationException {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail.com", "867777777", "LT", "Password_", "name", "surname", "address");
        when(userValidator.validate(Mockito.any())).thenReturn(userRegisterDto);
        assertDoesNotThrow(() -> {
            userService.registerUser(userRegisterDto);
        });
        verify(userRepository).save(Mockito.any());
    }

    @Test
    public void RegisterUser_IncorrectDataPassed_ShouldThrowException() throws ValidationException {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        when(userValidator.validate(Mockito.any())).thenThrow(new ValidationException("Not valid data"));
        assertThrows(UserRegisterException.class, () -> {
            userService.registerUser(userRegisterDto);
        });
    }

    @Test
    public void GetUserById_NonExistingUserIdProvided_ShouldThrowException() {
        when(userRepository.findById(Mockito.anyLong())).thenThrow(new UserNotFoundException("User not found"));
        assertThrows(UserNotFoundException.class, () -> {
           userService.getUserById(1);
        });
    }

    @Test
    public void GetUserById_ExistingUserIdProvided_ShouldReturnUser() {
        User user = new User("myemail@gmail.com", "867777777", "Password_", "name", "surname", "address");
        when(userRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(user));
        User userFromService = userService.getUserById(1);
        assertAll("Get user by id",
                () -> assertEquals(user.getEmail(), userFromService.getEmail()),
                () -> assertEquals(user.getPhoneNumber(), userFromService.getPhoneNumber()),
                () -> assertEquals(user.getPassword(), userFromService.getPassword()),
                () -> assertEquals(user.getName(), userFromService.getName()),
                () -> assertEquals(user.getSurname(), userFromService.getSurname()),
                () -> assertEquals(user.getAddress(), userFromService.getAddress()));
    }

    @Test
    public void GetAllUsers_ThereAre3ExistingUsers_ExistingUsersReturned() {
        List<User> userList = new ArrayList<User>();
        userList.add(new User("myemail@gmail.com", "867777777", "Password_", "name", "surname", "address"));
        userList.add(new User("myemail@gmail.com", "867777777", "Password_", "name", "surname", "address"));
        userList.add(new User("myemail@gmail.com", "867777777", "Password_", "name", "surname", "address"));
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userList.toArray().length, userService.getAllUsers().toArray().length);
    }

}
