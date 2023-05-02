package com.example.practicadistribuidosgrupo9;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;
    public static final String REDIR = "redirect:/login";

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

    @PostMapping("/logout")
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
    public String verify(@CookieValue(name = "user", defaultValue = "") String user) {
        if (user.isEmpty() || !userService.userExists(user.toUpperCase())) {
            return REDIR;
        } else {
            return "profile";
        }
    }

    @GetMapping("/get_reports")
    public String getReports() throws JsonProcessingException {
        List<OrderReport> Reports = OrderController.orderReports;
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
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByEmail(username.toUpperCase());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    @DeleteMapping("/users/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        User user = userService.getUserByEmail(username.toUpperCase());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(user);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/users/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String password) {
        User user = userService.getUserByEmail(username.toUpperCase());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }
}