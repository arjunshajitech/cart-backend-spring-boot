package com.arjunsaji.test.database;

import com.arjunsaji.test.entity.User;
import com.arjunsaji.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserRepositoryService {

    final UserRepository userRepository;

    public void save(User users) {
        userRepository.save(users);
    }

    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}
