package com.example.backend.controller;

import com.example.backend.exceptions.NameNotFoundException;
import com.example.backend.exceptions.ServerException;
import com.example.backend.service.NameService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class NameController {

    NameService nameService;

    @GetMapping("/name/{name}")
    public ResponseEntity<String> getAgeByName(@PathVariable("name") String name) {
        try {
            String age = nameService.getAgeByName(name);
            return ResponseEntity.ok(age);
        } catch (NameNotFoundException | ServerException e) {
            return ResponseEntity.badRequest().body("404");
        }
    }
}
