package com.example.backend.controller;

import com.example.backend.exceptions.ServerException;
import com.example.backend.model.Names;
import com.example.backend.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/statistic")
@AllArgsConstructor
public class StatisticController {

    StatisticService statisticService;

    @GetMapping("/statsByName")
    public ResponseEntity<List<Names>> getStatisticByName(){
        return ResponseEntity.ok(statisticService.getStatisticByName());
    }

    @GetMapping("/nameByHighestAge")
    public ResponseEntity<String> getNameWithHighestAge(){
            try {
                String name = statisticService.getNameWithHighestAge();
                return ResponseEntity.ok(name);
            }
            catch (ServerException e){
                return ResponseEntity.badRequest().body("Не найдено");
            }

        }
}
