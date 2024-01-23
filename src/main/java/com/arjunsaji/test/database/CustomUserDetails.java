package com.arjunsaji.test.database;

import com.arjunsaji.test.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    final UserRepositoryService userRepositoryService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepositoryService.findByEmail(username);
        if (user == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        return new UserPrincipal(user);
    }
}
