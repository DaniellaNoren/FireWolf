package com.example.demo.models.user;

import java.io.Serializable;

public class UserRegisterModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String userName;
    private String password;

    public UserRegisterModel() {
    }

    public UserRegisterModel(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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
}
