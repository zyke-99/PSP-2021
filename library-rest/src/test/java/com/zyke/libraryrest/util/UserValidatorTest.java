package com.zyke.libraryrest.util;

import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserValidatorImpl.class})
public class UserValidatorTest {

    @Autowired
    @Qualifier(value = "mainUserValidator")
    UserValidator userValidator;

    //String email, String phoneNumber, String phoneNumberCountry, String password
    @Test
    public void Validate_TLDNotProvided_ShouldThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail", "867777777", "LT", "Password_", "name", "surname", "address");
        assertThrows(ValidationException.class, () -> {
           userValidator.validate(userRegisterDto);
        });
    }

    @Test
    public void Validate_EmailContainsIllegalSymbols_ShouldThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("mye/-mail@gmail.com", "867777777", "LT", "Password_", "name", "surname", "address");
        assertThrows(ValidationException.class, () -> {
            userValidator.validate(userRegisterDto);
        });
    }

    @Test
    public void Validate_PasswordDoesNotContainSpecialCharacter_ShouldThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail.com", "867777777", "LT", "Password", "name", "surname", "address");
        assertThrows(ValidationException.class, () -> {
            userValidator.validate(userRegisterDto);
        });
    }

    @Test
    public void Validate_PasswordDoesNotContainUppercaseCharacter_ShouldThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail.com", "867777777", "LT", "password_", "name", "surname", "address");
        assertThrows(ValidationException.class, () -> {
            userValidator.validate(userRegisterDto);
        });
    }

    @Test
    public void Validate_PasswordLengthLessThanRequired_ShouldThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail.com", "867777777", "LT", "Pass_", "name", "surname", "address");
        assertThrows(ValidationException.class, () -> {
            userValidator.validate(userRegisterDto);
        });
    }

    @Test
    public void Validate_PhoneNumberIsNotCorrectLength_ShouldThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail.com", "86777", "LT", "Password_", "name", "surname", "address");
        assertThrows(ValidationException.class, () -> {
            userValidator.validate(userRegisterDto);
        });
    }

    @Test
    public void Validate_PhoneNumberContainsNotOnlyNumbers_ShouldThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail.com", "86777abcd", "LT", "Password_", "name", "surname", "address");
        assertThrows(ValidationException.class, () -> {
            userValidator.validate(userRegisterDto);
        });
    }

    @Test
    public void Validate_ValidDataProvided_ShouldNotThrowException() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("myemail@gmail.com", "867777777", "LT", "Password_", "name", "surname", "address");
        assertDoesNotThrow(() -> {
            userValidator.validate(userRegisterDto);
        });
    }

}
