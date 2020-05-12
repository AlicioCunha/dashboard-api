package com.moneyapi.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder endEncoder = new BCryptPasswordEncoder();

        System.out.println(endEncoder.encode("admin"));
    }
}

//$2a$10$U0mXNiVmBax4H3HfgiiWQe6Z8LrVvWZbaREbEYU9qXpTpFThKqX52
//$2a$10$cUMTZwLZOeq.hMCmi0ukLeMJfm6areVax9dwLDhAvoImWu.8q2Fam
