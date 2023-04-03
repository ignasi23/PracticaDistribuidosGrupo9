package com.example.practicadistribuidosgrupo9;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    private Map<String, String> users;

    public AuthController() {
        users = new HashMap<>();
        // Agregar usuarios de ejemplo
        /*users.put("user1", "password1");
        users.put("user2", "password2");*/
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String email, @RequestParam String password, HttpSession session) {
        String username = email.toUpperCase();
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

    @PostMapping("/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password, HttpSession session) {
        String username = email.toUpperCase();
        if (!users.containsKey(username)) {
            users.put(username, password);
            session.setAttribute("user", username);
            return "redirect:/login";
        } else {
            return "redirect:/login?registerError=true";
        }
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"error\": \"Error al procesar el JSON\"}";
        }
    }
}
