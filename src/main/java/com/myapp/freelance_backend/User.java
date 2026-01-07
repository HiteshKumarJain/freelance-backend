package com.myapp.freelance_backend;

public class User {
    private String name;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
