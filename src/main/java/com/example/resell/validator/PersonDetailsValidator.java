package com.example.resell.validator;

import com.example.resell.exception.WrongDetailsException;
import com.example.resell.model.Person;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PersonDetailsValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^@\\s]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!#$%&'()*+,[-/]./:;<=>?@^_`{|}~]).{10,}$");

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

    public void validateUserDetails(Person person) {
        validateEmail(person.getEmail());
        validatePassword(person.getPassword());
    }

}
