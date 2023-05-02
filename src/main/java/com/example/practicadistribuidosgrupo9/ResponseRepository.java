package com.example.practicadistribuidosgrupo9;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    @Query("SELECT r FROM Response r ORDER BY r.name ASC")
    List<Response> findAllOrderedByName();
}

