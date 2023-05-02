package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseRestController {

    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping("/submit")
    public String submitResponse(@RequestBody Response response, RedirectAttributes redirectAttributes) {
        responseRepository.save(response);
        redirectAttributes.addFlashAttribute("successMessage", "¡Mensaje enviado correctamente!");
        return "redirect:/contact";
    }

    @GetMapping
    public List<Response> viewResponses() {
        return responseRepository.findAllOrderedByName();
    }
}