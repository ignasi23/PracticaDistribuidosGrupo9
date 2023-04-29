package com.example.practicadistribuidosgrupo9;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResponseRepository extends JpaSpecificationExecutor<ResponseController>, JpaRepository<ResponseController,Long> {
}
