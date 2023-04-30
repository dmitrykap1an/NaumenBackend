package com.example.backend.repository;

import com.example.backend.model.Names;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public
interface NamesRepository extends JpaRepository<Names, Long> {

    @Modifying
    @Query("select a from Names a")
    List<Names> getAllNames();

    Names getNamesByName(String name);

    @Modifying
    @Query("update Names a set a.numberOfRequests = a.numberOfRequests + 1 where a.name = :name")
    void updateStatisticsByName(
            @Param("name") String name
    );
}
