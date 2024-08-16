package com.api.measureconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.measureconverter.model.UserEntity;
import com.api.measureconverter.services.AuthenticationService;
import com.api.measureconverter.utils.dto.LoginDto;
import com.api.measureconverter.utils.dto.RegisterDto;
import com.api.measureconverter.utils.dto.TokenDto;
import com.api.measureconverter.utils.jwt.JwtUtil;
import com.api.measureconverter.utils.reponse.Response;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<Response<UserEntity>> signup(@RequestBody RegisterDto registerDto)
            throws IllegalArgumentException {

        UserEntity userEntity = authenticationService.signup(registerDto);

        Response<UserEntity> response = new Response.Builder<UserEntity>()
                .result("Success")
                .status(200)
                .data(userEntity)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<TokenDto>> login(@RequestBody LoginDto loginDto) {
        UserEntity authenticatedUser = authenticationService.authenticate(loginDto);

        String jwtToken = jwtUtil.generateToken(authenticatedUser);

        TokenDto loginResponse = TokenDto
                .builder()
                .token(jwtToken)
                .expiresIn(jwtUtil.extractExpirationDate(jwtToken))
                .build();

        Response<TokenDto> response = new Response.Builder<TokenDto>()
                .result("Success")
                .status(200)
                .data(loginResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
