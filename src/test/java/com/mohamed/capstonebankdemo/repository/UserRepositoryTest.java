package com.mohamed.capstonebankdemo.repository;

import com.mohamed.capstonebankdemo.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {
@Mock
private UserRepository userRepository;

    LocalDateTime time = LocalDateTime.now();

    User user = new User(1,"mohamed", "mohamed", "mohamedmohamed100@gmail.com", "password", time, time);

    @Test
    void getUserEmail() {

        assertEquals("mohamedmohamed100@gmail.com", userRepository.getUserEmail(user.getEmail()));

    }

    @Test
    void getUserPassword() {
        assertEquals("password", user.getPassword());
    }
}