package com.example.practicadistribuidosgrupo9;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    public static final String REDIR = "redirect:/login";

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestParam String email, @RequestParam String password, HttpSession session, HttpServletResponse response) {
        User user = userService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", email.toUpperCase());
            //cookie
            Cookie sessionCookie = new Cookie("user", email);
            sessionCookie.setMaxAge(60 * 60 * 24); // 1 day
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
        return REDIR;
    }


    @PostMapping("/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String password, HttpSession session) {
        String userName = email.toUpperCase();
        if (!userService.userExists(userName)) {
            userService.addUser(new User(firstName, lastName, password, userName));
            session.setAttribute("user", userName);
            return REDIR;
        } else {
            return "redirect:/login?registerError=true";
        }
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(userService.getAllUsers());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{\"error\": \"Error al procesar el JSON\"}";
        }
    }

    @GetMapping("/verify")
    public String verify(@CookieValue(name = "user", defaultValue = "") String user, Model model) {
        if (user.isEmpty() || !userService.userExists(user.toUpperCase())) {
            return REDIR;
        } else {
            // valid users's session
            User currentUser = userService.getUserByEmail(user.toUpperCase());
            model.addAttribute("firstName", currentUser.getFirstName());
            model.addAttribute("lastName", currentUser.getLastName());
            model.addAttribute("userEmail", user);
            model.addAttribute("userOrders", currentUser.getOrders());
            if (currentUser.getAdminRole()) {
                model.addAttribute("adminRole", true);
                //model.addAttribute("reports", OrderController.orderReports);
            }

            return "profile";
        }
    }

    @GetMapping("/get_reports")
    @ResponseBody
    public String getReports() throws JsonProcessingException {
        List<OrderReport> Reports = OrderReportStorage.orderReports;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        ArrayNode arrayMatches = rootNode.putArray("arrayMatches");
        for (OrderReport r : Reports) {
            List<Object> o = new ArrayList<>();
            o.add(r.getUserName());
            o.add(r.getOrderID());
            o.add(r.getReportMsg());
            JsonNode j = mapper.convertValue(o, JsonNode.class);
            arrayMatches.add(j);
        }
        String json = mapper.writeValueAsString(rootNode);
        return json;
    }



}
