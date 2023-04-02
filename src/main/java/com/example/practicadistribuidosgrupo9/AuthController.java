package com.example.practicadistribuidosgrupo9;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    private Map<String, String> users;

    public AuthController() {
        users = new HashMap<>();
        // Agregar usuarios de ejemplo
        users.put("user1", "password1");
        users.put("user2", "password2");
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String username, @RequestParam String password, HttpSession session) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/";
        } else {
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
