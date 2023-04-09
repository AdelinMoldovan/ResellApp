package com.example.resell.validator;


import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.Admin;
import com.example.resell.model.Customer;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AdminDetailsValidator {


    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!#$%&'()*+,[-/]./:;<=>?@^_`{|}~]).{10,}$");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^(?:(?:\\+|00)\\d{1,3}\\s?)?\\d{10,}$");
    private void validateEmail(String email) {
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        if (!emailMatcher.matches()) {
            throw new WrongDetailsException("Wrong email! Email must represent this format [email]@[app].[domain]");
        }
    }

    private void validatePassword(String password) {
        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
        if (!passwordMatcher.matches()) {
            throw new WrongDetailsException("Wrong password! Password should contain: at least 10 characters, at least 1 upper case, at least 1 lower case, at least 1 special character");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        Matcher phoneNumberMatcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);
        if (!phoneNumberMatcher.matches()) {
            throw new WrongDetailsException("Wrong phone number! Phone number phone number can either contain country code or not: country code is either starting with '+' or '00'");
        }
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.isBlank() || firstName.isEmpty()) {
            throw new WrongDetailsException("Null or blank first name");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.isBlank() || lastName.isEmpty()) {
            throw new WrongDetailsException("Null or blank last name");
        }
    }

    public void validateAdminDetails(Admin admin) {
        validateFirstName(admin.getFirstName());
        validateLastName(admin.getLastName());
        validateEmail(admin.getEmail());
        validatePassword(admin.getPassword());
        validatePhoneNumber(admin.getPhoneNumber());
    }

}
