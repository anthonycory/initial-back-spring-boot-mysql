package com.example.demo.utils;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {
    public static String hash(String plainPassword) {
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(plainPassword);

        return encodedPassword;
    }

    public static boolean check(String plainPassword, String hashedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(plainPassword, hashedPassword);
    }
}
