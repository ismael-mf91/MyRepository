package com.example.myrestapplication.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String basicAuth;
    private String userName;

    public LoggedInUser(String basicAuth, String userName) {
        this.basicAuth = basicAuth;
        this.userName = userName;
    }

    public String getBasicAuth() {
        return basicAuth;
    }

    public String getUserName() {
        return userName;
    }
}
