package com.example.lab1_s2.service;

import com.example.lab1_s2.model.User;
import com.example.lab1_s2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    // Task 3: private repository object
    private final UserRepository userRepository;

    // Task 4: private passwordEncoder object
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void registerUser (String username, String password){
        User User = new User();
        User.setUsername(username);
        User.setPassword(passwordEncoder.encode(password));
        User.setRole("USER");
        userRepository.save(User);
    }
}
