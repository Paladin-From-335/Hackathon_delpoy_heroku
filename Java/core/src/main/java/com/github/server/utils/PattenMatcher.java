package com.github.server.utils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattenMatcher {

    public static boolean isValidEmail(String email) {
        email = email.toLowerCase(Locale.ROOT);
        Pattern emailPattern = Pattern.compile("^(.[a-z][0-9])@(.[a-z])$");
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
