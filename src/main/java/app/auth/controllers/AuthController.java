package app.auth.controllers;

import app.auth.servicies.AuthService;
import app.auth.dto.jwtresponse.JwtResponse;
import app.auth.dto.request.SignInRequest;
import app.auth.dto.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<JwtResponse> createUser(@RequestBody SignUpRequest request) {
        JwtResponse response = authService.signup(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> enter(@RequestBody SignInRequest request) {
        JwtResponse response = authService.signin(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }
}
