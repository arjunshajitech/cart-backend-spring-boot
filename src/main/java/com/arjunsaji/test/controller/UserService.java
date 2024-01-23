package com.arjunsaji.test.controller;

import com.arjunsaji.test.database.UserPrincipal;
import com.arjunsaji.test.database.UserRepositoryService;
import com.arjunsaji.test.entity.Users;
import com.arjunsaji.test.request.CreateUserRequest;
import com.arjunsaji.test.request.LoginRequest;
import com.arjunsaji.test.response.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    final UserRepositoryService userRepositoryService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationProvider authenticationProvider;
    final SecurityContextRepository securityContextRepository;
    final UserDetailsService userDetailsService;
    final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public ResponseEntity<?> createUser(CreateUserRequest request) {
        Users users = userRepositoryService.findByEmail(request.getEmail());
        if (users == null) {
            String password = passwordEncoder.encode(request.getPassword());
            userRepositoryService.save(new Users(request.getName(), request.getEmail(), password));
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message("User created."));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new Message("User already exists."));
    }

    public ResponseEntity<?> login(LoginRequest request, HttpServletRequest req, HttpServletResponse res) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(request.getEmail());
            UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.authenticated(
                    request.getEmail(), request.getPassword(), userPrincipal.getAuthorities());
            Authentication authentication = authenticationProvider.authenticate(token);
            SecurityContext context = securityContextHolderStrategy.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextHolderStrategy.setContext(context);
            securityContextRepository.saveContext(context, req, res);
        } catch (Exception ex) {
            throw new BadCredentialsException("Bad credentials");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new Message("Login success"));
    }
}
