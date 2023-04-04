package com.example.practicadistribuidosgrupo9;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private Map<String, User> users;

    public AuthController() {
        users = new HashMap<>();

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String email, @RequestParam String password, HttpSession session, HttpServletResponse response) {
        User user = users.get(email.toUpperCase());
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", email.toUpperCase());
            //cookie
            Cookie sessionCookie = new Cookie("user", email.toUpperCase());
            sessionCookie.setMaxAge(60 * 60 * 24); // 1 día de duración
            response.addCookie(sessionCookie);
            return "redirect:/?firstName=" + user.getFirstName() + "&lastName=" + user.getLastName();
        } else {
            return "redirect:/login?error=true";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        // Invalidar la sesión
        session.invalidate();
        // Eliminar la cookie
        Cookie sessionCookie = new Cookie("user", "");
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
        return "redirect:/login";
    }


    @PostMapping("/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password, HttpSession session) {
        String username = email.toUpperCase();
        if (!users.containsKey(username)) {
            users.put(username.toUpperCase(), new User(firstName, lastName, password));
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

    @GetMapping("/verify")
    public String index(@CookieValue(name = "user", defaultValue = "") String user, Model model) {
        if (user.isEmpty() || !users.containsKey(user)) {
            return "redirect:/login";
        } else {
            // La sesión del usuario es válida
            User currentUser = users.get(user);
            model.addAttribute("firstName", currentUser.getFirstName());
            model.addAttribute("lastName", currentUser.getLastName());
            return "index";
        }
    }

}
