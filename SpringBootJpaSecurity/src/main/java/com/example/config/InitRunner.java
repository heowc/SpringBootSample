package com.example.config;

import com.example.domain.User;
import com.example.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitRunner implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        User user = new User("heowc", "1234", "won chul", "010-xxxx-xxxx", "https://heowc.github.io");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
