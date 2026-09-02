package com.bus.reservation.model;

public class UserDetails {
    private String userName;
    private String password;

    // 1. Default Constructor (No-Args)
    public UserDetails() {
    }

    // 2. Parameterized Constructor
    public UserDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // 3. Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 4. ToString Method (Useful for logging)
    @Override
    public String toString() {
        return "UserDetails [userName=" + userName + ", password=" + password + "]";
    }
}