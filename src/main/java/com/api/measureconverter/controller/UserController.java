package com.api.measureconverter.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.measureconverter.services.UserService;
import com.api.measureconverter.utils.dto.UserDto;
import com.api.measureconverter.utils.reponse.Response;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Response<UserDto>> create(@RequestBody UserDto user) {
        UserDto result = userService.create(user.toEntity());

        Response<UserDto> response = new Response.Builder<UserDto>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Response<List<UserDto>>> getAll() {
        List<UserDto> result = userService.getAll();

        Response<List<UserDto>> response = new Response.Builder<List<UserDto>>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<Response<List<UserDto>>> getAllActive() {
        List<UserDto> result = userService.getActiveOnly();

        Response<List<UserDto>> response = new Response.Builder<List<UserDto>>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getActiveById")
    public ResponseEntity<Response<UserDto>> getActiveById(@RequestParam UUID id) {
        UserDto result = userService.getActiveById(id);

        Response<UserDto> response = new Response.Builder<UserDto>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response<UserDto>> delete(@RequestParam UUID id) {
        UserDto result = userService.delete(id);

        Response<UserDto> response = new Response.Builder<UserDto>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }
}
