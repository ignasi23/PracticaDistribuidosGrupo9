package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/responses")
public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping("/submit")
    public String submitResponse(Response response, RedirectAttributes redirectAttributes) {
        responseRepository.save(response);
        redirectAttributes.addFlashAttribute("successMessage", "¡Mensaje enviado correctamente!");
        return "redirect:/contact";
    }

    @GetMapping
    public String viewResponses(Model model) {
        List<Response> responses = responseRepository.findAllOrderedByName();
        model.addAttribute("responses", responses);
        return "responses";
    }

}
