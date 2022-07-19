package com.project.Batnik.controller;

import com.project.Batnik.model.RQ.RegistrationRQ;
import com.project.Batnik.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/registration")
    public ResponseEntity<String> register(@RequestBody RegistrationRQ request){
        return ResponseEntity.ok(userService.addUser(request));
    }

    @GetMapping("/activation/{code}")
    public ResponseEntity<String> activateUser(@PathVariable String code){
        return ResponseEntity.ok(userService.activateUser(code));
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        userService.refreshToken(request, response);
    }
}
