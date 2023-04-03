package com.example.practicadistribuidosgrupo9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PracticaDistribuidosGrupo9Application {
    public static void main(String[] args) {
        SpringApplication.run(PracticaDistribuidosGrupo9Application.class, args);}
}