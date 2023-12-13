package com.example.user.validators;

import java.util.regex.Pattern;

public class PhoneValidator {
    public static boolean phoneMatch(String phoneNumber, String regexPattern) {
        return Pattern.compile(regexPattern)
            .matcher(phoneNumber)
            .matches();
    }
}
