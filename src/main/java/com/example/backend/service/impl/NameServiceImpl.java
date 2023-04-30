package com.example.backend.service.impl;

import com.example.backend.exceptions.NameNotFoundException;
import com.example.backend.exceptions.ServerException;
import com.example.backend.model.Names;
import com.example.backend.repository.NamesRepository;
import com.example.backend.service.NameService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Comparator;

@Service
public class NameServiceImpl implements NameService {

    private NamesRepository namesRepository;
    private BufferedReader bufferedReader;
    @Value("${names}")
    private String fileName;

    public NameServiceImpl(NamesRepository namesRepository){
        this.namesRepository = namesRepository;
    }

    @Override
    public String getAgeByName(@NonNull String name) throws NameNotFoundException, ServerException {
        try {
            updateStatistics(name);
            File file = ResourceUtils.getFile("classpath:" + fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
            var data = bufferedReader.lines()
                    .map(it -> it.split("_"))
                    .filter(it -> it[0].equals(name)).distinct()
                    .findFirst();
            if (data.isPresent()) return data.get()[1];
            else throw new NameNotFoundException();
        } catch (FileNotFoundException e) {
            throw new ServerException();
        }  finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new ServerException();
            }
        }
    }



    @Override
    public String getNameWithHighestAge() throws ServerException {
        try{
            File file = ResourceUtils.getFile("classpath:" + fileName);
            bufferedReader = new BufferedReader(new FileReader(file));
            var data = bufferedReader.lines()
                    .map(it -> it.split("_"))
                    .max(Comparator.comparing(it -> it[1]));

            if (data.isPresent()) return data.get()[0];
            else throw new ServerException();
        } catch (FileNotFoundException e) {
            throw new ServerException();
        }  finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new ServerException();
            }
        }
    }

    private void updateStatistics(@NonNull String name){
        Names names = namesRepository.getNamesByName(name);
        if(names == null) {
            Names newName = new Names(name, 1L);
            namesRepository.save(newName);
        }
        else {
            names.incrementNumberOfRequests();
            namesRepository.save(names);
        }
    }

}
