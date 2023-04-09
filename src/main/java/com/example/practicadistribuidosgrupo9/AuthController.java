package com.example.practicadistribuidosgrupo9;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    public static Map<String, User> users;

    public AuthController() {
        users = new HashMap<>();
        User admin = new User("ADMIN", "ADMIN", "ADMIN"); //crear variable admin en vez de tres veces literal
        admin.setAdminRole();
        users.put("ADMIN@GMAIL.COM", admin);
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
            Cookie sessionCookie = new Cookie("user", email);
            sessionCookie.setMaxAge(60 * 60 * 24); // 1 día de duración
            response.addCookie(sessionCookie);
            return "redirect:/?firstName=" + user.getFirstName() + "&lastName=" + user.getLastName();
        } else {
            return "redirect:/login?error=true";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        // invalidate the session
        session.invalidate();
        // Delete the cookie
        Cookie sessionCookie = new Cookie("user", "");
        sessionCookie.setMaxAge(0);
        response.addCookie(sessionCookie);
        return "redirect:/login"; //usar constante (se repite 3 veces)
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
    public String verify(@CookieValue(name = "user", defaultValue = "") String user, Model model) {
        if (user.isEmpty() || !users.containsKey(user.toUpperCase())) {
            return "redirect:/login";
        } else {
            // valid users's sesssion
            User currentUser = users.get(user.toUpperCase());
            model.addAttribute("firstName", currentUser.getFirstName());
            model.addAttribute("lastName", currentUser.getLastName());
            model.addAttribute("userEmail", user);
            model.addAttribute("userOrders", currentUser.getOrders());
            if(currentUser.getAdminRole()) {
                model.addAttribute("adminRole", true);
                model.addAttribute("reports", OrderController.orderReports);

            }

            return "profile";
        }
    }



}
