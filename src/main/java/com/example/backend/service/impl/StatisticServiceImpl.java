package com.example.backend.service.impl;

import com.example.backend.exceptions.ServerException;
import com.example.backend.model.Names;
import com.example.backend.repository.NamesRepository;
import com.example.backend.service.NameService;
import com.example.backend.service.StatisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {

    NamesRepository namesRepository;
    NameService nameService;

    @Override
    public List<Names> getStatisticByName() {
        return namesRepository.getAllNames();
    }

    @Override
    public String getNameWithHighestAge() throws ServerException {
        return nameService.getNameWithHighestAge();
    }

}
