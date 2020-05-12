package com.moneyapi.security;


import com.moneyapi.model.User;
import com.moneyapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuario e/ou senha não informado"));

        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), getPermission(user));
    }

    private Collection<? extends GrantedAuthority> getPermission(User user) {

        Set<SimpleGrantedAuthority> authorities =  new HashSet<>();
        user.getPermissionList().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));

        return authorities;
    }
}
