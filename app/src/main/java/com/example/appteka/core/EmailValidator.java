package com.example.appteka.core;

import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean isEmailValid(String email) {
        return Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
                .matcher(email).matches();
    }

}
