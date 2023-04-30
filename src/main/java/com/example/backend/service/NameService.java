package com.example.backend.service;

import com.example.backend.exceptions.NameNotFoundException;
import com.example.backend.exceptions.ServerException;
import lombok.NonNull;
import org.springframework.stereotype.Service;



@Service
public interface NameService {

    String getAgeByName(@NonNull String name) throws NameNotFoundException, ServerException;
    String getNameWithHighestAge() throws ServerException;

}
