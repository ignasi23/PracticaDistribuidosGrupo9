package com.example.practicadistribuidosgrupo9;
import com.example.practicadistribuidosgrupo9.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.practicadistribuidosgrupo9.UserController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initAdminUser() {
        if (userRepository.findByEmail("ADMIN@GMAIL.COM") == null) {
            User admin = new User("ADMIN", "ADMIN", "ADMIN", "ADMIN@GMAIL.COM");
            admin.setAdminRole();
            userRepository.save(admin);
        }
    }

    public void updateUser(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email.toUpperCase());
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email.toUpperCase());
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email.toUpperCase()) != null;
    }

    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }
}


