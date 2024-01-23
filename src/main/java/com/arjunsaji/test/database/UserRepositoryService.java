package com.arjunsaji.test.database;

import com.arjunsaji.test.entity.Users;
import com.arjunsaji.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    final UserRepository userRepository;

    public void save(Users users) {
        userRepository.save(users);
    }

    public Users findByEmail(String username) {
        return userRepository.findByEmail(username);
    }
}
