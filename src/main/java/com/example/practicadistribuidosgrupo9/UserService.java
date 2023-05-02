package com.example.practicadistribuidosgrupo9;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User admin = new User("ADMIN", "ADMIN", "ADMIN", "ADMIN@GMAIL.COM");
        admin.setAdminRole();
        Optional<User> existingUser = userRepository.findByUserName("ADMIN@GMAIL.COM");
        if(!existingUser.isPresent()){
            userRepository.save(admin);
        }
    }

    public void updateUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void deleteUser(String email) {
        userRepository.deleteByUserName(email.toUpperCase());
    }

    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByUserName(email.toUpperCase());
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            // Lanza una excepción o devuelve un valor predeterminado
            throw new RuntimeException("User not found");
        }
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.existsByUserName(email.toUpperCase());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}


