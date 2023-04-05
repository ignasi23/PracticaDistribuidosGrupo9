package com.example.practicadistribuidosgrupo9;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class RootController {


    /**
     * Home view
     */
    @GetMapping("/")
    public String lobby(Model model) {
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        return "shop";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "/about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "/contact";
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "/index";
    }
    @GetMapping("/women")
    public String women(Model model) {
        return "/women";
    }
    @GetMapping("/men")
    public String men(Model model) {
        return "/men";
    }
    @GetMapping("/product1")
    public String product1(Model model) {
        return "/product1";
    }

    @GetMapping("/product2")
    public String product2(Model model) {
        return "/product2";
    }

    @GetMapping("/product3")
    public String product3(Model model) {
        return "/product3";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        return "/profile";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {

        return "/checkout";
    }
}