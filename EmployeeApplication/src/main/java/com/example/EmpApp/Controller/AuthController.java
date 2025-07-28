package com.example.EmpApp.Controller;

import com.example.EmpApp.Entity.Employee;
import com.example.EmpApp.Repository.EmployeeRepository;
import com.example.EmpApp.dto.AuthRequest;
import com.example.EmpApp.dto.GenericResponseDto;
import com.example.EmpApp.Security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private EmployeeRepository employeeRepo;

    @PostMapping("/login")
    public ResponseEntity<GenericResponseDto<Map<String, String>>> login(@RequestBody AuthRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GenericResponseDto<>("Invalid credentials", null, false));
        }

        Employee emp = employeeRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

        String access = jwtService.generateAccessToken(emp.getEmail(), emp.getEmployeeId());
        String refresh = jwtService.generateRefreshToken(emp.getEmail());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", access);
        tokens.put("refreshToken", refresh);

        return ResponseEntity.ok(new GenericResponseDto<>("Login successful", tokens, true));
    }

    @PostMapping("/refresh")
    public ResponseEntity<GenericResponseDto<Map<String, String>>> refresh(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GenericResponseDto<>("Missing or invalid Authorization header", null, false));
        }

        String refreshToken = authHeader.substring(7);
        if (!jwtService.isTokenValid(refreshToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new GenericResponseDto<>("Invalid refresh token", null, false));
        }

        String email = jwtService.extractEmail(refreshToken);
        Employee emp = employeeRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String newAccessToken = jwtService.generateAccessToken(email, emp.getEmployeeId());

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", newAccessToken);

        return ResponseEntity.ok(new GenericResponseDto<>("Access token refreshed", tokenMap, true));
    }
}
