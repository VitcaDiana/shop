package com.springapp.shop.services;

import com.springapp.shop.dtos.AuthRequestDTO;
import com.springapp.shop.entities.Role;
import com.springapp.shop.entities.RoleType;
import com.springapp.shop.entities.User;
import com.springapp.shop.exceptions.ResourceNotFoundException;
import com.springapp.shop.repositories.RoleRepository;
import com.springapp.shop.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    private AuthenticationManager authenticationManager;

    private JwtTokenService jwtTokenService;

    private UserDetailsServiceImpl userDetailsService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository,JwtTokenService jwtTokenService, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public String authenticate (AuthRequestDTO authRequestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        return jwtTokenService.generateToken(userDetails);
    }

    @Transactional
    public User register (AuthRequestDTO authRequestDTO){
        Optional<User> userOptional = userRepository.findUserByUsername(authRequestDTO.getUsername());
        if (userOptional.isPresent()){
            throw new ResourceNotFoundException("aleardy exist");
        }
        User user = new User();
        user.setUsername(authRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER);
        user.getRoles().add(role);
        role.getUsers().add(user);
        return userRepository.save(user);
    }

}
