package com.example.user.validators;

import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean emailMatch(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
            .matcher(emailAddress)
            .matches();
    }
}
