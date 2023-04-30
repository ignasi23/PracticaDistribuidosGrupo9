package com.example.practicadistribuidosgrupo9;
import com.example.practicadistribuidosgrupo9.User;
import org.springframework.stereotype.Service;
import com.example.practicadistribuidosgrupo9.UserController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService() {
        User admin = new User("ADMIN", "ADMIN", "ADMIN", "ADMIN@GMAIL.COM");
        admin.setAdminRole();
        userRepository.save(admin);
    }

    public void updateUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void deleteUser(String email) {
        userRepository.deleteByUserName(email.toUpperCase());
    }

    public User getUserByEmail(String email) {
        return userRepository.findByUserName(email.toUpperCase());
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


