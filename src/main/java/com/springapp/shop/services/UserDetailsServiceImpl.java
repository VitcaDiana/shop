package com.springapp.shop.services;

import com.springapp.shop.entities.User;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        return new org.springframework.security.core.userdetails.User (user.getUsername(), user.getPassword(), buildSimpleGrantedAuthorities(user));
    }

    @Transactional
    public List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(User user){
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().name()))
                .collect(Collectors.toList());
    }



}

