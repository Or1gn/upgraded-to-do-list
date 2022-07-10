package com.project.Batnik.controller;

import com.project.Batnik.model.RQ.RegistrationRQ;
import com.project.Batnik.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
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
}
