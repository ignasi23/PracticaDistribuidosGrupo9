package com.example.practicadistribuidosgrupo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/responses")
public class ResponseRestController {

    @Autowired
    private ResponseRepository responseRepository;

    @PostMapping("/submit")
    public ResponseEntity<String> submitResponse(@RequestBody Response response) {
        responseRepository.save(response);
        return ResponseEntity.status(HttpStatus.CREATED).body("¡Mensaje enviado correctamente!");
    }

    @GetMapping
    public ResponseEntity<List<Response>> viewResponses() {
        List<Response> responses = responseRepository.findAllOrderedByName();
        return ResponseEntity.ok(responses);
    }
}
