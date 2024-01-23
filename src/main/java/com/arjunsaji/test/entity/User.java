package com.arjunsaji.test.entity;

import com.arjunsaji.test.dto.UserRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
