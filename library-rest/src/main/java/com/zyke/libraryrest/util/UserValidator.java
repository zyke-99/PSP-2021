package com.zyke.libraryrest.util;

import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.exception.ValidationException;
import com.zyke.libraryrest.model.User;
import org.springframework.stereotype.Component;

public interface UserValidator {

    public UserRegisterDto validate(UserRegisterDto user) throws ValidationException;

}