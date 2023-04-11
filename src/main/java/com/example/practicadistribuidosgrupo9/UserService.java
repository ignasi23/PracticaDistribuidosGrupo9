package com.example.practicadistribuidosgrupo9;
import com.example.practicadistribuidosgrupo9.User;
import org.springframework.stereotype.Service;
import com.example.practicadistribuidosgrupo9.UserController;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private Map<String, User> users = new ConcurrentHashMap<>();

    public UserService() {
        User admin = new User("ADMIN", "ADMIN", "ADMIN", "ADMIN@GMAIL.COM");
        admin.setAdminRole();
        users.put("ADMIN@GMAIL.COM", admin);
    }

    public User getUserByEmail(String email) {
        return users.get(email.toUpperCase());
    }

    public void addUser(User user) {
        users.put(user.getUserName().toUpperCase(), user);
    }

    public boolean userExists(String email) {
        return users.containsKey(email.toUpperCase());
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }
}

