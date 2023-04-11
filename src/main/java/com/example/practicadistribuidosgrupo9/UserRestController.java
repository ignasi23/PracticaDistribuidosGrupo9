package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestParam("firstName") String firstName,
                                                            @RequestParam("lastName") String lastName,
                                                            @RequestParam("password") String password,
                                                            @RequestParam("userName") String userName) {
        User existingUser = userService.getUserByEmail(userName);
        if (existingUser != null) {
            return ResponseEntity.badRequest().build();
        }
        User newUser = new User(firstName, lastName, password, userName);
        userService.addUser(newUser);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestParam("userName") String userName,
                                                         @RequestParam("password") String password) {
        User user = userService.getUserByEmail(userName);
        if (user != null && user.getPassword().equals(password)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "User logged in successfully.");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> getUser(@PathVariable("userName") String userName) {
        User user = userService.getUserByEmail(userName);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{userName}")
    public ResponseEntity<Map<String, String>> updateUser(@PathVariable("userName") String userName,
                                                          @RequestParam("firstName") String firstName,
                                                          @RequestParam("lastName") String lastName,
                                                          @RequestParam("password") String password) {
        User user = userService.getUserByEmail(userName);
        if (user != null) {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            userService.updateUser(user);

            Map<String, String> response = new HashMap<>();
            response.put("message", "User updated successfully.");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("userName") String userName) {
        User user = userService.getUserByEmail(userName);
        if (user != null) {
            userService.deleteUser(userName);

            Map<String, String> response = new HashMap<>();
            response.put("message", "User deleted successfully.");
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
