package com.example.practicadistribuidosgrupo9;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLException;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PracticaDistribuidosGrupo9Application {
    public static void main(String[] args) throws SQLException {
        Server server = Server.createTcpServer().start(); // Create instance of Server and run
        SpringApplication.run(PracticaDistribuidosGrupo9Application.class, args);
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        conn.close();
        server.stop();
    }

}