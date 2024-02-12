package com.ecommerce.backend.controllers;

import com.ecommerce.backend.DTOs.request.LoginRequestDTO;
import com.ecommerce.backend.DTOs.request.RegistrationRequestDTO;
import com.ecommerce.backend.DTOs.response.LoginResponseDTO;
import com.ecommerce.backend.DTOs.response.RegistrationResponseDTO;
import com.ecommerce.backend.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public RegistrationResponseDTO registerUser(@RequestBody RegistrationRequestDTO request) {
        return authenticationService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody LoginRequestDTO request) {
        return authenticationService.loginUser(request.username(), request.password());
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return authenticationService.confirmToken(token);
    }
}
