package com.example.resell.validator;


import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.Admin;
import com.example.resell.model.Customer;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class AdminDetailsValidator {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!#$%&'()*+,[-/]./:;<=>?@^_`{|}~]).{10,}$");

    private void validateUsername(String username) {
        if (username == null || username.isBlank() || username.isEmpty()) {
            throw new WrongDetailsException("Null or blank username");
        }
    }

    private void validatePassword(String password) {
        Matcher passwordMatcher = PASSWORD_PATTERN.matcher(password);
        if (!passwordMatcher.matches()) {
            throw new WrongDetailsException("Wrong password! Password should contain: at least 10 characters, at least 1 upper case, at least 1 lower case, at least 1 special character");
        }
    }

    public void validateAdminDetails(Admin admin) {
        validateUsername(admin.getUsername());
        validatePassword(admin.getPassword());
    }
}
