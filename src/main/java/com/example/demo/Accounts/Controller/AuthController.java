package com.example.demo.Accounts.Controller;
import com.example.demo.Accounts.Request.LoginRequest;
import com.example.demo.Accounts.Request.SpaceRequest;
import com.example.demo.Accounts.Spaces.Services.SpaceService;
import com.example.demo.Core.Services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final SpaceService spaceService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, AuthService authService,
                          SpaceService spaceService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.spaceService = spaceService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        return authService.authenticateAndGenerateTokens(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @PostMapping("/register/space")
    public boolean registerSpace(@RequestBody SpaceRequest space) {
        space.setPassword(passwordEncoder.encode(space.getPassword()));
        space.setUserType("SPACE");
        spaceService.create(space);
        return true;
    }
}
