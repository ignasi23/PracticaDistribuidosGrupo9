package com.example.practicadistribuidosgrupo9;
import com.example.practicadistribuidosgrupo9.User;
import org.springframework.stereotype.Service;
import com.example.practicadistribuidosgrupo9.UserController;

import java.util.Map;

@Service
public class UserService {

    public static Map<String, User> users = UserController.users;

    public User authenticateUser(String email, String password) {
        User user = users.get(email.toUpperCase());
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    public boolean registerUser(String firstName, String lastName, String email, String password) {
        String username = email.toUpperCase();
        if (!users.containsKey(username)) {
            users.put(username, new User(firstName, lastName, password));
            return true;
        } else {
            return false;
        }
    }

    public User getUserByEmail(String email) {
        return users.get(email.toUpperCase());
    }

    public void logoutUser(String email) {
        // No implementation needed as session is managed by Spring Security
    }

    public boolean isAdmin(String email) {
        User user = users.get(email.toUpperCase());
        return user != null && user.getAdminRole();
    }
}
