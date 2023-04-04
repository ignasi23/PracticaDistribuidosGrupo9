package com.example.practicadistribuidosgrupo9;

public class User {
    private String firstName;
    private String lastName;
    private String password;
    private Boolean adminRole;

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        adminRole = false;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdminRole() {
        return adminRole;
    }
    public void setAdminRole() {
        adminRole = true;
    }


}

