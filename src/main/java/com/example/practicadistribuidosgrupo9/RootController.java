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

}