package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Names {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long numberOfRequests;

    public Names(String name, Long numberOfRequests){
        this.name = name;
        this.numberOfRequests = numberOfRequests;
    }

    public void incrementNumberOfRequests(){
        numberOfRequests++;
    }
}
