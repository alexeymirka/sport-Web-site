package com.mirka.ms.model.entity;

import java.util.Objects;

public class User {

    private int id;
    private String login;
    private UserType userType;

    public User(int id, String login, UserType userType) {
        this.id = id;
        this.login = login;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, userType);
    }
}