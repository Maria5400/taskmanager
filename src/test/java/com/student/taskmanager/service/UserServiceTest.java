package com.student.taskmanager.service;

import com.student.taskmanager.model.User;
import com.student.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testRegisterUser() {
        User user = new User();
        user.setUsername("student");
        user.setPassword("plain_password");

        User savedUser = new User();
        savedUser.setRole("ROLE_USER");
        savedUser.setPassword("scrambled_password");

        // Tell the fake encoder to scramble the password
        when(passwordEncoder.encode("plain_password")).thenReturn("scrambled_password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userService.registerUser(user);

        // Prove that our service successfully assigned the correct role and encrypted the password!
        assertEquals("ROLE_USER", result.getRole());
        assertEquals("scrambled_password", result.getPassword());
    }
}