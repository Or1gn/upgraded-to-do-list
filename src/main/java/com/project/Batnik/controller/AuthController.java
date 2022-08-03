package com.project.Batnik.controller;

import com.project.Batnik.model.RQ.RegistrationRQ;
import com.project.Batnik.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @Operation(summary = "Registration",
            description = "This controller allows register a new user")
    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody RegistrationRQ request){
        return ResponseEntity.ok(userService.addUser(request));
    }

    @Operation(summary = "Activate account",
            description = "This controller allows to activate registered account")
    @GetMapping("/activation/{code}")
    public ResponseEntity<String> activateUser(@PathVariable String code){
        return ResponseEntity.ok(userService.activateUser(code));
    }

    @Operation(summary = "Refresh token",
            description = "This controller allows to refresh expired token")
    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }
}
