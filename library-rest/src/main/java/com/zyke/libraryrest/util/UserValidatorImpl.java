package com.zyke.libraryrest.util;

import com.zyke.libraryrest.dto.UserRegisterDto;
import com.zyke.libraryrest.exception.ValidationException;
import informationValidation.EmailValidator;
import informationValidation.PasswordChecker;
import informationValidation.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component("mainUserValidator")
@PropertySource("classpath:uservalidator.properties")
public class UserValidatorImpl implements UserValidator{

    @Autowired
    Environment environment;

    private final int MIN_PASSWORD_LENGTH;
    private final int DEFAULT_PHONE_NUMBER_LENGTH;
    private final String DEFAULT_PREFIX_FROM;
    private final String DEFAULT_PREFIX_TO;


    public UserValidatorImpl(@Value("${min-password-length}") int min_password_length,
                             @Value("${phone-number-length-default}") int default_phone_number_length,
                             @Value("${phone-replace-from-prefix-default}") String default_prefix_from,
                             @Value("${phone-replace-to-prefix-default}") String default_prefix_to) {
        this.MIN_PASSWORD_LENGTH = min_password_length;
        this.DEFAULT_PHONE_NUMBER_LENGTH = default_phone_number_length;
        this.DEFAULT_PREFIX_FROM = default_prefix_from;
        this.DEFAULT_PREFIX_TO = default_prefix_to;
    }

    @Override
    public UserRegisterDto validate(UserRegisterDto user) throws ValidationException {
        EmailValidator emailValidator = new EmailValidator();
        PasswordChecker passwordValidator = new PasswordChecker();
        PhoneValidator phoneValidator = new PhoneValidator();
        if(validateEmail(emailValidator, user.getEmail()) && validatePassword( passwordValidator, user.getPassword())) {
            user.setPhoneNumber(validatePhoneNumber(phoneValidator, user));
        }
        return user;
    }

    private boolean validateEmail(EmailValidator emailValidator, String email) throws ValidationException {

        if(!emailValidator.ContainsAtSymbol(email)) {
            throw new ValidationException("Email does not contain at sign");
        }
        if(emailValidator.ContainsIllegalSymbols(email)) {
            throw new ValidationException("Email contains illegal symbols");
        }
        try {
            if(!emailValidator.IsDomainTLDCorrect(email)) {
                throw new ValidationException("Email top level domain invalid");
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private boolean validatePassword(PasswordChecker passwordChecker, String password) throws ValidationException {

        if(!passwordChecker.IsLongEnough(password, MIN_PASSWORD_LENGTH)) {
            throw new ValidationException("Password not long enough");
        }
        if(!passwordChecker.ContainsSpecialChar(password)) {
            throw new ValidationException("Password does not contain a special character");
        }
        if(!passwordChecker.ContainsUppercase(password)) {
            throw new ValidationException("Password does not contain an uppercase character");
        }
        return true;
    }

    private String validatePhoneNumber(PhoneValidator phoneValidator, UserRegisterDto user) throws ValidationException {

        char replaceFrom = environment.getProperty("phone-replace-from-prefix-" + user.getPhoneNumberCountry(), this.DEFAULT_PREFIX_FROM).charAt(0);
        String replaceTo = environment.getProperty("phone-replace-to-prefix-" + user.getPhoneNumberCountry(), this.DEFAULT_PREFIX_TO);
        int phoneNumberLength = Integer.parseInt(environment.getProperty("phone-number-length-" + user.getPhoneNumberCountry(), Integer.toString(this.DEFAULT_PHONE_NUMBER_LENGTH)));
        String replacedPhoneNumber;

        if(!phoneValidator.ContainsOnlyNumbers(user.getPhoneNumber())) {
            throw new ValidationException("Phone number does not contain only numbers");
        }

        replacedPhoneNumber = phoneValidator.ReplaceBegining(user.getPhoneNumber(), replaceFrom, replaceTo);

        if(!phoneValidator.IsLengthCorrect(replacedPhoneNumber, phoneNumberLength)) {
            throw new ValidationException("Phone number exceeds length");
        }

        return replacedPhoneNumber;
    }
}