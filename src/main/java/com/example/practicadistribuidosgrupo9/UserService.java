package com.example.practicadistribuidosgrupo9;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        User admin = new User("ADMIN", "ADMIN", "ADMIN", "ADMIN@GMAIL.COM");
        admin.setAdminRole();
        List<User> existingUser = userRepository.findByUserName("ADMIN@GMAIL.COM");
        if (existingUser.isEmpty()) {
            userRepository.save(admin);
        }
    }

    public void updateUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserByEmail(String email) {
        List<User> userOptional = userRepository.findByUserName(email.toUpperCase());
        if (!userOptional.isEmpty()) {
            return userOptional.get(0);
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
