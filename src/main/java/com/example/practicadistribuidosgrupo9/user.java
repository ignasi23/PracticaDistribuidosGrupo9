package com.example.practicadistribuidosgrupo9;

import java.util.ArrayList;
import java.util.List;

public class user {
    private String username;
    private String password;

    private String name;
    private String lastName;

    public user(String username, String password,String name,String lastName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName=lastName;

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

