package com.arjunsaji.test.controller.user;

import com.arjunsaji.test.config.Constants;
import com.arjunsaji.test.request.CreateUserRequest;
import com.arjunsaji.test.request.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(Constants.API_V1_USER)
public class UserController {

    final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, HttpServletRequest req, HttpServletResponse res) {
        return userService.login(request,req,res);
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        return userService.me(authentication.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProfile(@PathVariable("id") UUID id, @RequestParam("name") String name) {
        return userService.editProfile(name,id);
    }
}
