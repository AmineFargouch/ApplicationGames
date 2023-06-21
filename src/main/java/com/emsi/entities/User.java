package com.emsi.entities;

public class User {
    private String email;
    private String password;

    public User(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
