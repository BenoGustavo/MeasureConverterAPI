package com.api.measureconverter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.measureconverter.error.custom.Unauthorized401Exception;
import com.api.measureconverter.model.UserEntity;
import com.api.measureconverter.repositories.UserRepository;
import com.api.measureconverter.utils.dto.LoginDto;
import com.api.measureconverter.utils.dto.RegisterDto;
import com.api.measureconverter.utils.enums.Roles;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserEntity signup(@RequestBody RegisterDto registerDto) throws IllegalArgumentException {
        UserEntity userEntity = registerDto.toEntity();
        userEntity.setRole(Roles.ROLE_USER);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        return userRepository.save(userEntity);
    }

    public UserEntity authenticate(LoginDto loginDto) throws AuthenticationException, UsernameNotFoundException {
        if (isUserAuthenticated()) {
            throw new Unauthorized401Exception("Logout first before authenticating");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                            loginDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new Unauthorized401Exception("Invalid email or password");
        }

        UserEntity user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getDeleteAt() != null) {
            throw new Unauthorized401Exception("Deleted user");
        }

        userRepository.updateLastLogin(user.getId());
        return user;
    }

    private boolean isUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal() != "anonymousUser";
    }
}
