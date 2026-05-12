package com.student.taskmanager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Creates the BCrypt tool we used in the UserService
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // The Rulebook
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // We disable this so Postman can easily send POST requests
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // Anyone can register/login
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Only Admins
                        .requestMatchers("/tasks/**").hasAnyRole("USER", "ADMIN") // Users or Admins
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // This allows Postman to send the username/password easily

        return http.build();
    }
}