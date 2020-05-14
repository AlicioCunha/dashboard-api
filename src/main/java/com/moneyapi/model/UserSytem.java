package com.moneyapi.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserSytem extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    private User user;

    public UserSytem(User username, Collection<? extends GrantedAuthority> authorities) {
        super(username.getEmail(), username.getPassword(), authorities);
        this.user = username;
    }

    public User getUser() {
        return user;
    }
}
