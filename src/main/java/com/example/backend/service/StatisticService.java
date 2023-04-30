package com.example.backend.service;

import com.example.backend.exceptions.ServerException;
import com.example.backend.model.Names;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatisticService {

    List<Names> getStatisticByName();

    String getNameWithHighestAge() throws ServerException;
}
