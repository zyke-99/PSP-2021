package com.zyke.libraryrest.util;

import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.exception.ValidationException;
import com.zyke.libraryrest.model.User;
import org.springframework.stereotype.Component;

@Component("simpleOtherValidator")
public class OtherPossibleValidatorImpl implements UserValidator{
    @Override
    public UserRegisterDto validate(UserRegisterDto user) throws ValidationException {
        throw new ValidationException("Validation exception");
    }
}
