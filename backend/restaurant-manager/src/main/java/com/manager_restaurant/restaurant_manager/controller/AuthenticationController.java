package com.manager_restaurant.restaurant_manager.controller;

import com.manager_restaurant.restaurant_manager.Infra.security.TokenService;
import com.manager_restaurant.restaurant_manager.dto.AuthenticationDTO;
import com.manager_restaurant.restaurant_manager.dto.RegisterDTO;
import com.manager_restaurant.restaurant_manager.model.Users;
import com.manager_restaurant.restaurant_manager.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin( "*")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        var email = authentication.getName();
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        System.out.println(authentication.getAuthorities());
        return ResponseEntity.ok(Map.of(
                "id", user.getId_user(),
                "email", user.getEmail(),
                "role", user.getRole()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationDTO data) {
        var user = userRepository.findByEmail(data.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if (!new BCryptPasswordEncoder().matches(data.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha inválida");
        }
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDTO data) {
        Optional<Users> existing = userRepository.findByEmail(data.email());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Users newUser = new Users(data.user_name(), data.email(), encryptedPassword, data.role());
        this.userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}



